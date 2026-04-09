/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.sample;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 Batik SVGGraphics2D 生成美观的顺序图
 */
public class SequenceDiagramGenerator {

    // 颜色方案
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 255);
    private static final Color PARTICIPANT_BG = new Color(63, 81, 181);
    private static final Color PARTICIPANT_TEXT = Color.WHITE;
    private static final Color MESSAGE_COLOR = new Color(33, 150, 243);
    private static final Color RETURN_COLOR = new Color(76, 175, 80);
    private static final Color ACTIVATION_BG = new Color(225, 245, 254);
    private static final Color GRID_LINE_COLOR = new Color(240, 240, 240);

    // 尺寸配置
    private static final int PARTICIPANT_WIDTH = 120;
    private static final int PARTICIPANT_HEIGHT = 50;
    private static final int MARGIN = 40;
    private static final int VERTICAL_SPACING = 80;
    private static final int MESSAGE_HEIGHT = 30;
    private static final int ACTIVATION_WIDTH = 10;

    private List<Participant> participants = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private int currentY = MARGIN;

    // 参与者类
    static class Participant {
        String name;
        int x;
        int index;

        Participant(String name, int x) {
            this.name = name;
            this.x = x;
        }
    }

    // 消息类
    static class Message {
        String from;
        String to;
        String text;
        boolean isReturn;
        String note;

        Message(String from, String to, String text, boolean isReturn, String note) {
            this.from = from;
            this.to = to;
            this.text = text;
            this.isReturn = isReturn;
            this.note = note;
        }
    }

    /**
     * 添加参与者
     */
    public void addParticipant(String name) {
        int x = MARGIN + participants.size() * (PARTICIPANT_WIDTH + 80);
        participants.add(new Participant(name, x));
    }

    /**
     * 添加消息
     */
    public void addMessage(String from, String to, String text) {
        messages.add(new Message(from, to, text, false, null));
    }

    /**
     * 添加返回消息
     */
    public void addReturnMessage(String from, String to, String text) {
        messages.add(new Message(from, to, text, true, null));
    }

    /**
     * 添加带注释的消息
     */
    public void addMessageWithNote(String from, String to, String text, String note) {
        messages.add(new Message(from, to, text, false, note));
    }

    /**
     * 生成顺序图
     */
    public void generateDiagram(String outputPath) throws Exception {
        // 计算画布尺寸
        int width = MARGIN * 2 + participants.size() * (PARTICIPANT_WIDTH + 80);
        int height = MARGIN * 2 + participants.size() * VERTICAL_SPACING +
                messages.size() * MESSAGE_HEIGHT * 2;

        // 创建 SVG 文档
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);


        // 创建 SVGGraphics2D
        SVGGraphics2D g2d = new SVGGraphics2D(document);
        g2d.setSVGCanvasSize(new Dimension(width, height));

        // 绘制背景
        drawBackground(g2d, width, height);

        // 绘制参与者
        drawParticipants(g2d);

        // 绘制消息
        drawMessages(g2d);

        // 绘制生命线
        drawLifelines(g2d, height);

        // 保存 SVG 文件
        try (Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8")) {
            g2d.stream(out, true); // true 表示使用 CSS 样式
        }

        System.out.println("顺序图已生成: " + outputPath);
    }

    /**
     * 绘制背景和网格
     */
    private void drawBackground(SVGGraphics2D g2d, int width, int height) {
        // 绘制背景
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, width, height);

        // 绘制网格线
        g2d.setColor(GRID_LINE_COLOR);
        g2d.setStroke(new BasicStroke(0.5f));

        // 水平网格线
        for (int y = MARGIN; y < height; y += 20) {
            g2d.draw(new Line2D.Double(MARGIN, y, width - MARGIN, y));
        }

        // 垂直网格线
        for (Participant p : participants) {
            g2d.draw(new Line2D.Double(p.x + PARTICIPANT_WIDTH / 2, MARGIN + PARTICIPANT_HEIGHT,
                    p.x + PARTICIPANT_WIDTH / 2, height - MARGIN));
        }
    }

    /**
     * 绘制参与者
     */
    private void drawParticipants(SVGGraphics2D g2d) {
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));

        for (Participant p : participants) {
            // 绘制参与者框
            RoundRectangle2D rect = new RoundRectangle2D.Double(
                    p.x, MARGIN, PARTICIPANT_WIDTH, PARTICIPANT_HEIGHT, 15, 15);

            // 渐变填充（模拟）
            GradientPaint gradient = new GradientPaint(
                    p.x, MARGIN, PARTICIPANT_BG.brighter(),
                    p.x, MARGIN + PARTICIPANT_HEIGHT, PARTICIPANT_BG.darker());

            g2d.setPaint(gradient);
            g2d.fill(rect);

            // 边框
            g2d.setColor(PARTICIPANT_BG.darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(rect);

            // 参与者名称
            g2d.setColor(PARTICIPANT_TEXT);
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(p.name);
            int textX = p.x + (PARTICIPANT_WIDTH - textWidth) / 2;
            int textY = MARGIN + PARTICIPANT_HEIGHT / 2 + fm.getAscent() / 2 - 2;

            g2d.drawString(p.name, textX, textY);
        }

        currentY = MARGIN + PARTICIPANT_HEIGHT + 40;
    }

    /**
     * 绘制生命线
     */
    private void drawLifelines(SVGGraphics2D g2d, int totalHeight) {
        g2d.setColor(new Color(200, 200, 220));
        g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10, new float[]{5, 5}, 0));

        for (Participant p : participants) {
            int centerX = p.x + PARTICIPANT_WIDTH / 2;
            g2d.draw(new Line2D.Double(centerX, MARGIN + PARTICIPANT_HEIGHT + 20,
                    centerX, totalHeight - MARGIN));
        }
    }

    /**
     * 绘制消息
     */
    private void drawMessages(SVGGraphics2D g2d) {
        int messageCount = 0;

        for (Message msg : messages) {
            Participant from = findParticipant(msg.from);
            Participant to = findParticipant(msg.to);

            if (from != null && to != null) {
                int fromX = from.x + PARTICIPANT_WIDTH / 2;
                int toX = to.x + PARTICIPANT_WIDTH / 2;
                int y = currentY + messageCount * MESSAGE_HEIGHT * 2;

                // 绘制激活条
                drawActivationBar(g2d, Math.min(fromX, toX), y);

                // 绘制消息线
                drawMessageLine(g2d, fromX, toX, y, msg.isReturn);

                // 绘制箭头
                drawArrow(g2d, fromX, toX, y, msg.isReturn);

                // 绘制消息文本
                drawMessageText(g2d, fromX, toX, y, msg.text, msg.isReturn);

                // 绘制注释（如果有）
                if (msg.note != null) {
                    drawNote(g2d, Math.min(fromX, toX) + 20, y - 15, msg.note);
                }

                messageCount++;
            }
        }
    }

    /**
     * 绘制激活条
     */
    private void drawActivationBar(SVGGraphics2D g2d, int x, int y) {
        g2d.setColor(ACTIVATION_BG);
        g2d.fillRect(x - ACTIVATION_WIDTH / 2, y - 10, ACTIVATION_WIDTH, MESSAGE_HEIGHT + 20);
        g2d.setColor(MESSAGE_COLOR);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(x - ACTIVATION_WIDTH / 2, y - 10, ACTIVATION_WIDTH, MESSAGE_HEIGHT + 20);
    }

    /**
     * 绘制消息线
     */
    private void drawMessageLine(SVGGraphics2D g2d, int fromX, int toX, int y, boolean isReturn) {
        if (isReturn) {
            g2d.setColor(RETURN_COLOR);
            g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10, new float[]{5, 5}, 0));
        } else {
            g2d.setColor(MESSAGE_COLOR);
            g2d.setStroke(new BasicStroke(2));
        }

        g2d.draw(new Line2D.Double(fromX, y, toX, y));
    }

    /**
     * 绘制箭头
     */
    private void drawArrow(SVGGraphics2D g2d, int fromX, int toX, int y, boolean isReturn) {
        int arrowSize = 8;
        int direction = fromX < toX ? 1 : -1;

        if (isReturn) {
            direction = -direction; // 返回消息箭头方向相反
        }

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(toX, y);
        arrowHead.addPoint(toX - direction * arrowSize, y - arrowSize / 2);
        arrowHead.addPoint(toX - direction * arrowSize, y + arrowSize / 2);

        g2d.setColor(isReturn ? RETURN_COLOR : MESSAGE_COLOR);
        g2d.fill(arrowHead);
    }

    /**
     * 绘制消息文本
     */
    private void drawMessageText(SVGGraphics2D g2d, int fromX, int toX, int y,
                                 String text, boolean isReturn) {
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        g2d.setColor(isReturn ? RETURN_COLOR.darker() : MESSAGE_COLOR.darker());

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int centerX = (fromX + toX) / 2;
        int textX = centerX - textWidth / 2;

        // 文本背景
        g2d.setColor(new Color(255, 255, 255, 200));
        RoundRectangle2D textBg = new RoundRectangle2D.Double(
                textX - 5, y - fm.getHeight() + 5,
                textWidth + 10, fm.getHeight(), 5, 5);
        g2d.fill(textBg);

        // 文本边框
        g2d.setColor(isReturn ? RETURN_COLOR : MESSAGE_COLOR);
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(textBg);

        // 绘制文本
        g2d.setColor(isReturn ? RETURN_COLOR.darker() : MESSAGE_COLOR.darker());
        g2d.drawString(text, textX, y - 5);
    }

    /**
     * 绘制注释
     */
    private void drawNote(SVGGraphics2D g2d, int x, int y, String note) {
        g2d.setFont(new Font("Microsoft YaHei", Font.ITALIC, 11));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(note);

        // 注释框
        g2d.setColor(new Color(255, 248, 225));
        RoundRectangle2D noteRect = new RoundRectangle2D.Double(
                x, y, textWidth + 20, fm.getHeight() + 10, 8, 8);
        g2d.fill(noteRect);

        // 注释边框
        g2d.setColor(new Color(255, 193, 7));
        g2d.setStroke(new BasicStroke(1));
        g2d.draw(noteRect);

        // 注释文本
        g2d.setColor(new Color(130, 100, 0));
        g2d.drawString(note, x + 10, y + fm.getAscent() + 5);
    }

    /**
     * 查找参与者
     */
    private Participant findParticipant(String name) {
        return participants.stream()
                .filter(p -> p.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
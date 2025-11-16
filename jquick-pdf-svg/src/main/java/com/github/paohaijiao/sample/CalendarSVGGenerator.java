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
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CalendarSVGGenerator {

    public static void main(String[] args) {
        try {
            // 创建SVG文档
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // 创建SVGGraphics2D
            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
            svgGenerator.setSVGCanvasSize(new Dimension(872, 282));

            // 设置背景
            svgGenerator.setColor(Color.WHITE);
            svgGenerator.fillRect(0, 0, 872, 282);

            // 绘制日历网格
            drawCalendarGrid(svgGenerator);

            // 绘制彩色方块
            drawColoredCells(svgGenerator);

            // 绘制月份分隔线和边框
            drawMonthDividers(svgGenerator);

            // 绘制文本标签
            drawTextLabels(svgGenerator);

            // 保存SVG文件
            try (Writer out = new OutputStreamWriter(new FileOutputStream("d://test//calendar-output.svg"), "UTF-8")) {
                svgGenerator.stream(out, true);
            }

            System.out.println("SVG文件已生成: calendar-output.svg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawCalendarGrid(SVGGraphics2D g2d) {
        g2d.setColor(new Color(232, 235, 240));
        g2d.setStroke(new BasicStroke(1));

        // 绘制7行 x 53列的网格
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 53; col++) {
                int x = 80 + col * 20;
                int y = 60 + row * 20;
                g2d.setColor(Color.WHITE);
                g2d.fillRect(x, y, 20, 20);
                g2d.setColor(new Color(232, 235, 240));
                g2d.drawRect(x, y, 20, 20);
            }
        }
    }

    private static void drawColoredCells(SVGGraphics2D g2d) {
        // 定义颜色映射（根据原始SVG中的RGB值）
        Color[] colors = {
                new Color(159, 177, 237), new Color(144, 164, 234), new Color(122, 146, 229),
                new Color(187, 200, 242), new Color(186, 199, 242), new Color(195, 206, 244),
                new Color(182, 195, 241), new Color(163, 180, 237), new Color(208, 217, 246),
                new Color(121, 146, 229), new Color(160, 177, 237), new Color(143, 163, 233),
                new Color(99, 128, 225), new Color(189, 201, 243), new Color(149, 169, 235),
                new Color(104, 132, 226), new Color(135, 157, 232), new Color(205, 215, 246),
                new Color(110, 137, 227), new Color(176, 191, 240), new Color(201, 211, 245),
                new Color(126, 150, 230), new Color(119, 144, 229), new Color(190, 202, 243),
                new Color(190, 202, 243), new Color(99, 128, 225), new Color(172, 187, 239),
                new Color(182, 196, 241), new Color(83, 114, 222), new Color(202, 212, 245),
                new Color(152, 171, 235), new Color(192, 204, 243), new Color(118, 143, 228),
                new Color(141, 162, 233), new Color(155, 173, 236), new Color(183, 196, 241),
                new Color(155, 173, 236), new Color(127, 150, 230), new Color(176, 190, 240),
                new Color(144, 164, 234), new Color(96, 125, 224), new Color(149, 168, 235)
        };

        // 简化的颜色填充示例（实际应根据数据填充）
        // 这里随机填充一些单元格作为示例
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 53; col++) {
                if (Math.random() > 0.7) { // 随机选择30%的单元格填充颜色
                    int colorIndex = (row * 53 + col) % colors.length;
                    g2d.setColor(colors[colorIndex]);
                    g2d.fillRect(80 + col * 20 + 1, 60 + row * 20 + 1, 18, 18);
                }
            }
        }
    }

    private static void drawMonthDividers(SVGGraphics2D g2d) {
        g2d.setColor(new Color(84, 85, 90));
        g2d.setStroke(new BasicStroke(1));

        // 月份分隔线位置（根据原始SVG调整）
        int[] monthStarts = {80, 180, 260, 340, 440, 520, 600, 700, 780, 860, 960, 1040, 1140};

        for (int i = 0; i < monthStarts.length - 1; i++) {
            int x = monthStarts[i];
            g2d.drawLine(x, 60, x, 200);
        }

        // 绘制顶部和底部边框
        g2d.drawLine(79, 60, 1140, 60);
        g2d.drawLine(79, 200, 1120, 200);
    }

    private static void drawTextLabels(SVGGraphics2D g2d) {
        g2d.setColor(new Color(134, 135, 140));
        g2d.setFont(new Font("sans-serif", Font.BOLD, 20));

        // 绘制年份（垂直文本）
        drawVerticalText(g2d, "2017", 50, 130);

        // 设置月份字体
        g2d.setColor(new Color(84, 85, 90));
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

        // 月份标签
        String[] months = {"1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月"};
        int[] monthPositions = {130, 210, 290, 380, 470, 550, 640, 730, 810, 910, 990, 1080};

        for (int i = 0; i < months.length; i++) {
            drawCenteredText(g2d, months[i], monthPositions[i], 44);
        }

        // 星期标签
        String[] weekdays = {"日", "一", "二", "三", "四", "五", "六"};
        int[] weekdayPositions = {70, 90, 110, 130, 150, 170, 190};

        for (int i = 0; i < weekdays.length; i++) {
            drawRightAlignedText(g2d, weekdays[i], 70, weekdayPositions[i]);
        }
    }

    private static void drawVerticalText(SVGGraphics2D g2d, String text, int x, int y) {
        // 保存当前变换
        AffineTransform originalTransform = g2d.getTransform();

        // 旋转坐标系来绘制垂直文本
        g2d.rotate(-Math.PI / 2, x, y);
        drawCenteredText(g2d, text, x, y);

        // 恢复原始变换
        g2d.setTransform(originalTransform);
    }

    private static void drawCenteredText(SVGGraphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D bounds = fm.getStringBounds(text, g2d);
        int textX = x - (int) bounds.getWidth() / 2;
        int textY = y - (int) bounds.getHeight() / 2 + fm.getAscent();
        g2d.drawString(text, textX, textY);
    }

    private static void drawRightAlignedText(SVGGraphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D bounds = fm.getStringBounds(text, g2d);
        int textX = x - (int) bounds.getWidth();
        int textY = y - (int) bounds.getHeight() / 2 + fm.getAscent();
        g2d.drawString(text, textX, textY);
    }
}

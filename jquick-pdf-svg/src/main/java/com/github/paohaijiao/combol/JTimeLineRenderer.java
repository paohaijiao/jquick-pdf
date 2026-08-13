package com.github.paohaijiao.combol;
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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

import static com.github.paohaijiao.combol.JTimeLineData.*;

/**
 * 商务风格奇偶排列箭头流程图渲染器
 *
 * 结构:
 *   奇数索引: 矩形框在上方，圆圈在下方
 *   偶数索引: 矩形框在下方，圆圈在上方
 *
 *   矩形框1(上)     矩形框2(下)     矩形框3(上)     矩形框4(下)     矩形框5(上)
 *       ↓              ↑              ↓              ↑              ↓
 *       ●  →  →  →  → ●  →  →  →  → ●  →  →  →  → ●  →  →  →  → ●
 */
@Data
public class JTimeLineRenderer extends JAbstractChartRenderer {

    // 页面布局常量
    private static final int PAGE_MARGIN = 30;
    private static final int HEADER_H = 120;
    private static final Color PAGE_SHADOW = new Color(30, 41, 59, 26);
    private static final Color PAGE_BG = new Color(232, 237, 245);
    private static final Color HEADER_GRAD_START = new Color(38, 55, 98);
    private static final Color HEADER_GRAD_END = new Color(82, 115, 185);
    private static final Color SUBTITLE_ON_BAND = new Color(214, 224, 244);
    private static final Color TIMELINE_GLOW = new Color(79, 129, 189, 40);
    private static final Color ARROW_COLOR = new Color(110, 140, 205);
    private static final Color CONNECTOR_COLOR = new Color(150, 170, 205);
    private static final Color BOX_BORDER = new Color(228, 234, 244);
    private static final Color DESC_COLOR = new Color(90, 105, 125);
    private static final Color WATERMARK_COLOR = new Color(80, 112, 180, 14);
    private static final Color DIVIDER_COLOR = new Color(226, 232, 240);

    private JTimeLineData config;

    private LayoutParams layoutParams;

    public JTimeLineRenderer() {
        this.layoutParams = new LayoutParams();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 1300;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 500;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JTimeLineData config = (JTimeLineData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getNodes(), "nodes require not null");
        this.config = config;
        this.config.setWidth(width);
        this.config.setHeight(height);
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawBackground(svgGenerator);
        drawWatermark(svgGenerator);
        drawHeader(svgGenerator);
        drawMainTimeline(svgGenerator);
        drawAllBoxesAndCircles(svgGenerator);
        drawAllConnections(svgGenerator);
        drawFooter(svgGenerator);
    }

    /**
     * 计算布局参数
     */
    private void calculateLayout() {
        int width = config.getWidth();
        int height = config.getHeight();
        List<JTimeLineData.FlowNode> nodes = config.getNodes();
        int nodeCount = nodes.size();
        // 矩形框尺寸
        int boxWidth = config.getBoxWidth();
        int boxHeight = config.getBoxHeight();
        // 圆圈半径
        int circleRadius = config.getCircleRadius();
        // 主时间线Y坐标（圆圈中心Y坐标）
        int timelineY = height / 2;
        // 矩形框与圆圈之间的垂直间距
        int boxCircleGap = config.getBoxCircleGap();
        // 计算每个节点的X坐标（等间距分布）
        int startX = config.getStartX();
        int endX = width - config.getEndX();
        double spacing = nodeCount > 1 ? (double)(endX - startX) / (nodeCount - 1) : 0;

        // 存储每个节点的位置信息
        int[] circleX = new int[nodeCount];
        int[] boxX = new int[nodeCount];
        int[] boxY = new int[nodeCount];
        boolean[] isBoxAbove = new boolean[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            int centerX = startX + (int) Math.round(i * spacing);
            circleX[i] = centerX;
            boxX[i] = centerX - boxWidth / 2;

            // 奇偶排列：偶数索引（0-based）矩形框在上方，奇数索引在下方
            boolean above = (i % 2 == 0);
            isBoxAbove[i] = above;

            if (above) {
                // 矩形框在圆圈上方
                boxY[i] = timelineY - circleRadius - boxCircleGap - boxHeight;
                // 确保不超出顶部边界
                if (boxY[i] < config.getMinTopMargin()) {
                    boxY[i] = config.getMinTopMargin();
                }
            } else {
                // 矩形框在圆圈下方
                boxY[i] = timelineY + circleRadius + boxCircleGap;
                // 确保不超出底部边界
                if (boxY[i] + boxHeight > height - config.getMinBottomMargin()) {
                    boxY[i] = height - config.getMinBottomMargin() - boxHeight;
                }
            }
        }

        layoutParams.boxWidth = boxWidth;
        layoutParams.boxHeight = boxHeight;
        layoutParams.circleRadius = circleRadius;
        layoutParams.boxCircleGap = boxCircleGap;
        layoutParams.timelineY = timelineY;
        layoutParams.nodeCount = nodeCount;
        layoutParams.circleX = circleX;
        layoutParams.boxX = boxX;
        layoutParams.boxY = boxY;
        layoutParams.isBoxAbove = isBoxAbove;
        layoutParams.footerY = height - 20;
    }

    /**
     * 绘制背景（海报式白色页面 + 柔和阴影）
     */
    private void drawBackground(SVGGraphics2D svg) {
        int w = config.getWidth();
        int h = config.getHeight();
        // 画布底色
        svg.setPaint(PAGE_BG);
        svg.fillRect(0, 0, w, h);
        // 页面投影
        svg.setPaint(PAGE_SHADOW);
        svg.fillRoundRect(PAGE_MARGIN + 4, PAGE_MARGIN + 6, w - PAGE_MARGIN * 2, h - PAGE_MARGIN * 2, 24, 24);
        // 白色页面
        svg.setPaint(Color.WHITE);
        svg.fillRoundRect(PAGE_MARGIN, PAGE_MARGIN, w - PAGE_MARGIN * 2, h - PAGE_MARGIN * 2, 24, 24);
    }

    /**
     * 装饰性水印圆环，衬托时间线区域
     */
    private void drawWatermark(SVGGraphics2D svg) {
        int cx = config.getWidth() / 2;
        int cy = layoutParams.timelineY;
        svg.setPaint(WATERMARK_COLOR);
        svg.setStroke(new BasicStroke(1.5f));
        svg.drawOval(cx - 200, cy - 200, 400, 400);
        svg.drawOval(cx - 260, cy - 260, 520, 520);
        svg.drawOval(cx - 320, cy - 320, 640, 640);
    }

    /**
     * 绘制头部：顶部渐变横幅 + 主标题/副标题
     */
    private void drawHeader(SVGGraphics2D svg) {
        int w = config.getWidth();
        // 顶部渐变横幅（上圆角）
        GradientPaint headerGrad = new GradientPaint(PAGE_MARGIN, 0, HEADER_GRAD_START,
                w - PAGE_MARGIN, 0, HEADER_GRAD_END);
        svg.setPaint(headerGrad);
        Path2D band = new Path2D.Double();
        band.moveTo(PAGE_MARGIN, PAGE_MARGIN + 24);
        band.quadTo(PAGE_MARGIN, PAGE_MARGIN, PAGE_MARGIN + 24, PAGE_MARGIN);
        band.lineTo(w - PAGE_MARGIN - 24, PAGE_MARGIN);
        band.quadTo(w - PAGE_MARGIN, PAGE_MARGIN, w - PAGE_MARGIN, PAGE_MARGIN + 24);
        band.lineTo(w - PAGE_MARGIN, PAGE_MARGIN + HEADER_H);
        band.lineTo(PAGE_MARGIN, PAGE_MARGIN + HEADER_H);
        band.closePath();
        svg.fill(band);

        int textX = PAGE_MARGIN + 50;
        // 主标题（白色）
        String mainTitle = config.getMainTitle();
        if (mainTitle != null && !mainTitle.isEmpty()) {
            svg.setFont(config.getMainTitleFont().deriveFont(Font.BOLD, 26f));
            svg.setPaint(Color.WHITE);
            svg.drawString(mainTitle, textX, PAGE_MARGIN + 66);
        }
        // 副标题（浅蓝白）
        String subtitle = config.getSubtitle();
        if (subtitle != null && !subtitle.isEmpty()) {
            svg.setFont(config.getSubtitleFont().deriveFont(Font.PLAIN, 13f));
            svg.setPaint(SUBTITLE_ON_BAND);
            svg.drawString(subtitle, textX, PAGE_MARGIN + 96);
        }
    }

    /**
     * 绘制主时间线（光晕 + 渐变主线）
     */
    private void drawMainTimeline(SVGGraphics2D svg) {
        int startX = layoutParams.circleX[0] - layoutParams.circleRadius;
        int endX = layoutParams.circleX[layoutParams.nodeCount - 1] + layoutParams.circleRadius;
        int timelineY = layoutParams.timelineY;

        // 柔和光晕
        svg.setStroke(new BasicStroke(9f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.setPaint(TIMELINE_GLOW);
        svg.drawLine(startX, timelineY, endX, timelineY);
        // 渐变主线
        svg.setPaint(new GradientPaint(startX, timelineY, new Color(120, 148, 218),
                endX, timelineY, new Color(52, 84, 150)));
        svg.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.drawLine(startX, timelineY, endX, timelineY);
    }

    /**
     * 绘制所有矩形框和圆圈节点
     */
    private void drawAllBoxesAndCircles(SVGGraphics2D svg) {
        for (int i = 0; i < layoutParams.nodeCount; i++) {
            // 绘制矩形框
            drawBox(svg, i);
            // 绘制矩形框到圆圈的连接线
            drawBoxToCircleLine(svg, i);
            // 绘制圆圈节点
            drawCircleNode(svg, i);
        }
    }

    /**
     * 绘制矩形框（带阴影、圆角）
     */
    private void drawBox(SVGGraphics2D svg, int index) {
        JTimeLineData.FlowNode node = config.getNodes().get(index);
        int boxX = layoutParams.boxX[index];
        int boxY = layoutParams.boxY[index];
        int boxWidth = layoutParams.boxWidth;
        int boxHeight = layoutParams.boxHeight;
        Color boxColor = node.getBoxColor() != null ? node.getBoxColor() : config.getDefaultBoxColor();

        // 边界检查
        if (boxX < 10) {
            boxX = 10;
        }
        if (boxX + boxWidth > config.getWidth() - 10) {
            boxX = config.getWidth() - boxWidth - 10;
        }
        // 阴影效果
        svg.setPaint(new Color(30, 41, 59, 30));
        svg.fillRoundRect(boxX + 3, boxY + 4, boxWidth, boxHeight, 10, 10);
        // 矩形框白色背景
        svg.setPaint(Color.WHITE);
        svg.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);
        // 细边框
        svg.setStroke(new BasicStroke(1f));
        svg.setPaint(BOX_BORDER);
        svg.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);
        // 左侧彩色竖条
        svg.setPaint(boxColor);
        svg.fillRoundRect(boxX, boxY, 6, boxHeight, 3, 3);

        // 绘制矩形框内的文字
        drawBoxText(svg, node, boxColor, boxX, boxY, boxWidth, boxHeight);
    }

    /**
     * 绘制矩形框内的文字
     */
    private void drawBoxText(SVGGraphics2D svg, JTimeLineData.FlowNode node, Color boxColor, int boxX, int boxY, int boxWidth, int boxHeight) {
        // 标题（使用节点主题色）
        String title = node.getTitle();
        if (title != null && !title.isEmpty()) {
            svg.setFont(config.getBoxTitleFont());
            svg.setPaint(boxColor);
            FontMetrics fm = svg.getFontMetrics();
            int textX = boxX + (boxWidth - fm.stringWidth(title)) / 2;
            int textY = boxY + config.getBoxTitlePaddingTop();
            svg.drawString(title, textX, textY);
        }

        // 描述（多行，用|分隔）
        String description = node.getDescription();
        if (description != null && !description.isEmpty()) {
            svg.setFont(config.getBoxDescFont());
            svg.setPaint(DESC_COLOR);

            String[] lines = description.split("\\|");
            FontMetrics fm = svg.getFontMetrics();
            int startY = boxY + config.getBoxDescPaddingTop();
            int lineHeight = config.getBoxDescLineHeight();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                int textX = boxX + (boxWidth - fm.stringWidth(line)) / 2;
                int textY = startY + i * lineHeight;
                svg.drawString(line, textX, textY);
            }
        }
    }

    /**
     * 绘制矩形框到圆圈的连接线
     */
    private void drawBoxToCircleLine(SVGGraphics2D svg, int index) {
        int circleX = layoutParams.circleX[index];
        int circleY = layoutParams.timelineY;
        int circleRadius = layoutParams.circleRadius;
        int boxX = layoutParams.boxX[index];
        int boxY = layoutParams.boxY[index];
        int boxWidth = layoutParams.boxWidth;
        int boxHeight = layoutParams.boxHeight;

        boolean isBoxAbove = layoutParams.isBoxAbove[index];

        int lineStartX;
        int lineStartY;
        int lineEndX;
        int lineEndY;

        if (isBoxAbove) {
            // 矩形框在上方，连接线从矩形框底部到圆圈顶部
            lineStartX = boxX + boxWidth / 2;
            lineStartY = boxY + boxHeight;
            lineEndX = circleX;
            lineEndY = circleY - circleRadius;
        } else {
            // 矩形框在下方，连接线从圆圈底部到矩形框顶部
            lineStartX = circleX;
            lineStartY = circleY + circleRadius;
            lineEndX = boxX + boxWidth / 2;
            lineEndY = boxY;
        }

        svg.setStroke(new BasicStroke(1.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                0f, new float[]{4f, 4f}, 0f));
        svg.setPaint(CONNECTOR_COLOR);
        svg.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);
    }

    /**
     * 绘制圆圈节点
     */
    private void drawCircleNode(SVGGraphics2D svg, int index) {
        JTimeLineData.FlowNode node = config.getNodes().get(index);
        int circleX = layoutParams.circleX[index];
        int circleY = layoutParams.timelineY;
        int radius = layoutParams.circleRadius;
        Color nodeColor = node.getNodeColor() != null ? node.getNodeColor() : config.getDefaultNodeColor();
        // 外光晕
        svg.setPaint(new Color(nodeColor.getRed(), nodeColor.getGreen(), nodeColor.getBlue(), 55));
        svg.fillOval(circleX - radius - 5, circleY - radius - 5, (radius + 5) * 2, (radius + 5) * 2);
        // 外圈白色
        svg.setPaint(Color.WHITE);
        svg.fillOval(circleX - radius - 2, circleY - radius - 2, (radius + 2) * 2, (radius + 2) * 2);
        // 主圆圈
        svg.setPaint(nodeColor);
        svg.fillOval(circleX - radius, circleY - radius, radius * 2, radius * 2);
        // 高光
        svg.setPaint(new Color(255, 255, 255, 180));
        svg.fillOval(circleX - radius + 3, circleY - radius + 3, radius - 3, radius - 3);
        // 圆圈内的序号
        svg.setFont(config.getCircleNumberFont());
        svg.setPaint(Color.WHITE);
        String number = String.valueOf(index + 1);
        FontMetrics fm = svg.getFontMetrics();
        int textX = circleX - fm.stringWidth(number) / 2;
        int textY = circleY + fm.getAscent() / 2 - 1;
        svg.drawString(number, textX, textY);
    }

    /**
     * 绘制所有节点之间的箭头连接
     */
    private void drawAllConnections(SVGGraphics2D svg) {
        for (int i = 0; i < layoutParams.nodeCount - 1; i++) {
            drawArrowBetweenNodes(svg, i, i + 1);
        }
    }

    /**
     * 绘制两个节点之间的箭头
     */
    private void drawArrowBetweenNodes(SVGGraphics2D svg, int fromIndex, int toIndex) {
        int fromCircleX = layoutParams.circleX[fromIndex];
        int toCircleX = layoutParams.circleX[toIndex];
        int circleY = layoutParams.timelineY;
        int radius = layoutParams.circleRadius;
        // 箭头起点（从圆圈的右侧）
        int arrowStartX = fromCircleX + radius;
        // 箭头终点（到下一个圆圈的左侧）
        int arrowEndX = toCircleX - radius;
        // 绘制连接线
        svg.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.setPaint(ARROW_COLOR);
        svg.drawLine(arrowStartX, circleY, arrowEndX - 8, circleY);
        // 绘制箭头头部
        drawArrowHead(svg, arrowEndX - 8, circleY, arrowEndX, circleY);
    }

    /**
     * 绘制箭头头部
     */
    private void drawArrowHead(SVGGraphics2D svg, int fromX, int fromY, int toX, int toY) {
        int arrowSize = 10;
        double angle = Math.atan2(toY - fromY, toX - fromX);
        int x1 = toX - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int y1 = toY - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
        int x2 = toX - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int y2 = toY - (int) (arrowSize * Math.sin(angle + Math.PI / 6));
        Path2D arrowHead = new Path2D.Double();
        arrowHead.moveTo(toX, toY);
        arrowHead.lineTo(x1, y1);
        arrowHead.lineTo(x2, y2);
        arrowHead.closePath();
        svg.fill(arrowHead);
    }

    /**
     * 绘制底部：分隔线 + 右下对齐脚注
     */
    private void drawFooter(SVGGraphics2D svg) {
        if (config.getFooterText() == null || config.getFooterText().isEmpty()) {
            return;
        }
        int w = config.getWidth();
        int h = config.getHeight();
        // 分隔线
        svg.setStroke(new BasicStroke(1f));
        svg.setPaint(DIVIDER_COLOR);
        svg.drawLine(PAGE_MARGIN + 50, h - PAGE_MARGIN - 40, w - PAGE_MARGIN - 50, h - PAGE_MARGIN - 40);

        // 脚注（右下对齐）
        svg.setFont(config.getFooterFont());
        svg.setPaint(COLOR_LIGHT);
        FontMetrics fm = svg.getFontMetrics();
        int textX = w - PAGE_MARGIN - 50 - fm.stringWidth(config.getFooterText());
        svg.drawString(config.getFooterText(), textX, h - PAGE_MARGIN - 14);
    }

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {
        int boxWidth;
        int boxHeight;
        int circleRadius;
        int boxCircleGap;
        int timelineY;
        int nodeCount;
        int[] circleX;
        int[] boxX;
        int[] boxY;
        boolean[] isBoxAbove;
        int footerY;
    }




}
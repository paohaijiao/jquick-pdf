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
package com.github.paohaijiao.matrix;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * 相关系数矩阵渲染器。
 * <p>
 * 现代仪表盘风格：浅色画布 + 居中热力图 + 列头渐变条 + 右上图例卡片。
 * 只负责美化绘制，不修改任何数据模型（JCorrelationMatrixOption）。
 */
public class JCorrelationMatrixRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_WIDTH = 872;

    private static final int DEFAULT_HEIGHT = 400;

    // ---------- 配色 ----------
    private static final Color CANVAS_BG = new Color(243, 246, 252);
    private static final Color TITLE_COLOR = new Color(30, 41, 59);
    private static final Color SUBTITLE_COLOR = new Color(100, 116, 139);
    private static final Color LABEL_COLOR = new Color(71, 85, 105);
    private static final Color STRIP_BG = new Color(237, 242, 250);
    private static final Color MATRIX_BORDER = new Color(215, 223, 236);
    private static final Color LEGEND_BORDER = new Color(226, 232, 240);
    private static final Color CELL_TEXT_DARK = new Color(51, 65, 85);

    // 发散色标：0 为浅色，±1 为强色
    private static final Color ZERO_POS = new Color(241, 245, 251);
    private static final Color POS_COLOR = new Color(37, 99, 235);
    private static final Color ZERO_NEG = new Color(253, 242, 242);
    private static final Color NEG_COLOR = new Color(220, 38, 38);

    // ---------- 布局 ----------
    private static final int CELL_W = 84;
    private static final int CELL_H = 26;
    private static final int START_Y = 140;
    private static final int LEGEND_X = 640;
    private static final int LEGEND_Y = 34;
    private static final int LEGEND_W = 208;
    private static final int LEGEND_H = 72;

    @Override
    protected int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    protected int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        JCorrelationMatrixOption matrixOption = option.getCorrelationMatrixOption();
        double[][] data = getCorrelationData(matrixOption);
        int rows = data.length;
        int cols = data[0].length;

        // 画布背景
        g2d.setColor(CANVAS_BG);
        g2d.fillRect(0, 0, width, height);

        drawHeader(g2d, width, matrixOption);
        drawLegend(g2d, matrixOption);

        int startX = (width - cols * CELL_W) / 2;
        drawColumnStrip(g2d, startX, cols, matrixOption);
        drawMatrixBorder(g2d, startX, rows, cols);
        drawCorrelationCells(g2d, startX, data);
        drawAxisLabels(g2d, startX, rows, cols, matrixOption);
    }

    private void drawHeader(SVGGraphics2D g2d, int width, JCorrelationMatrixOption option) {
        JCorrelationMatrixOption.Title title = option != null ? option.title() : null;
        if (title == null) {
            return;
        }
        if (title.text() != null) {
            g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
            g2d.setColor(TITLE_COLOR);
            String text = title.text();
            g2d.drawString(text, (width - g2d.getFontMetrics().stringWidth(text)) / 2, 34);
        }
        if (title.subtext() != null) {
            g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
            g2d.setColor(SUBTITLE_COLOR);
            String subtext = title.subtext();
            g2d.drawString(subtext, (width - g2d.getFontMetrics().stringWidth(subtext)) / 2, 56);
        }
        // 标题下方装饰线
        g2d.setColor(POS_COLOR);
        g2d.fillRoundRect((width - 40) / 2, 64, 40, 3, 2, 2);
    }

    private void drawLegend(SVGGraphics2D g2d, JCorrelationMatrixOption option) {
        // 白色圆角卡片
        g2d.setColor(Color.WHITE);
        g2d.fill(new RoundRectangle2D.Double(LEGEND_X, LEGEND_Y, LEGEND_W, LEGEND_H, 10, 10));
        g2d.setColor(LEGEND_BORDER);
        g2d.draw(new RoundRectangle2D.Double(LEGEND_X, LEGEND_Y, LEGEND_W, LEGEND_H, 10, 10));

        // 标题
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g2d.setColor(LABEL_COLOR);
        g2d.drawString("相关系数", LEGEND_X + 12, LEGEND_Y + 19);

        // 发散渐变条：-1（红）→ 0（浅色）→ +1（蓝）
        int barX = LEGEND_X + 12;
        int barY = LEGEND_Y + 28;
        int barW = LEGEND_W - 24;
        int barH = 16;
        for (int i = 0; i < barW; i++) {
            double v = (i * 2.0 / (barW - 1)) - 1.0;
            g2d.setColor(divergingColor(v));
            g2d.fillRect(barX + i, barY, 1, barH);
        }
        g2d.setColor(LEGEND_BORDER);
        g2d.drawRect(barX, barY, barW, barH);

        // 刻度
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
        g2d.setColor(LABEL_COLOR);
        FontMetrics fm = g2d.getFontMetrics();
        int labelY = barY + barH + 16;
        g2d.drawString("-1", barX, labelY);
        g2d.drawString("0", barX + barW / 2, labelY);
        g2d.drawString("1", barX + barW - fm.stringWidth("1"), labelY);
    }

    private void drawColumnStrip(SVGGraphics2D g2d, int startX, int cols, JCorrelationMatrixOption option) {
        // 列头浅色渐变条
        int stripW = cols * CELL_W;
        g2d.setColor(STRIP_BG);
        g2d.fill(new RoundRectangle2D.Double(startX, START_Y - 22, stripW, 22, 8, 8));
        // 列标签
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g2d.setColor(LABEL_COLOR);
        String[] dimensions = getDimensions(option, cols, cols);
        FontMetrics fm = g2d.getFontMetrics();
        for (int j = 0; j < cols; j++) {
            int cx = startX + j * CELL_W + CELL_W / 2;
            g2d.drawString(dimensions[j], cx - fm.stringWidth(dimensions[j]) / 2, START_Y - 6);
        }
    }

    private void drawMatrixBorder(SVGGraphics2D g2d, int startX, int rows, int cols) {
        g2d.setColor(MATRIX_BORDER);
        g2d.setStroke(new BasicStroke(1.2f));
        g2d.draw(new RoundRectangle2D.Double(startX, START_Y, cols * CELL_W, rows * CELL_H, 8, 8));
    }

    private void drawCorrelationCells(SVGGraphics2D g2d, int startX, double[][] data) {
        int rows = data.length;
        int cols = data[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = data[i][j];
                Color cellColor = divergingColor(value);
                int x = startX + j * CELL_W;
                int y = START_Y + i * CELL_H;

                // 圆角单元格（留 1px 间隙，形成现代热力图效果）
                RoundRectangle2D cell = new RoundRectangle2D.Double(x + 1, y + 1, CELL_W - 2, CELL_H - 2, 5, 5);
                g2d.setColor(cellColor);
                g2d.fill(cell);

                // 对角线（自相关=1）加白色高亮内圈
                if (i == j) {
                    g2d.setColor(withAlpha(Color.WHITE, 150));
                    g2d.setStroke(new BasicStroke(1.5f));
                    g2d.draw(new RoundRectangle2D.Double(x + 2.5, y + 2.5, CELL_W - 5, CELL_H - 5, 4, 4));
                }

                // 数值文字
                String text = String.format("%.2f", value);
                boolean strong = Math.abs(value) >= 0.7;
                g2d.setFont(new Font("Microsoft YaHei", strong ? Font.BOLD : Font.PLAIN, 12));
                g2d.setColor(luminance(cellColor) < 0.5 ? Color.WHITE : CELL_TEXT_DARK);
                FontMetrics fm = g2d.getFontMetrics();
                g2d.drawString(text, x + (CELL_W - fm.stringWidth(text)) / 2,
                        y + (CELL_H - fm.getHeight()) / 2 + fm.getAscent());
            }
        }
    }

    private void drawAxisLabels(SVGGraphics2D g2d, int startX, int rows, int cols, JCorrelationMatrixOption option) {
        g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g2d.setColor(LABEL_COLOR);
        FontMetrics fm = g2d.getFontMetrics();
        String[] dimensions = getDimensions(option, rows, cols);
        for (int i = 0; i < rows; i++) {
            String label = dimensions[i];
            int x = startX - 46;
            int y = START_Y + i * CELL_H + (CELL_H + fm.getAscent()) / 2 - 1;
            g2d.drawString(label, x - fm.stringWidth(label) / 2, y);
        }
    }

    // ---------- 数据与工具方法 ----------

    private double[][] getCorrelationData(JCorrelationMatrixOption option) {
        if (option != null && option.dataset() != null && option.dataset().sourceArray() != null) {
            return option.dataset().sourceArray();
        }
        return new double[][]{
                {1.00, -0.20, 0.03, -0.62, -0.54, -0.21, 0.63, 0.30},
                {-0.20, 1.00, 0.36, -0.61, -0.26, 0.05, 0.16, 0.41},
                {0.03, 0.36, 1.00, -0.74, -0.94, 0.71, -0.90, -0.66},
                {-0.62, -0.61, -0.74, 1.00, 0.37, -0.66, 0.54, -0.66},
                {-0.54, -0.26, -0.94, 0.37, 1.00, -0.05, -0.46, 0.71},
                {-0.21, 0.05, 0.71, -0.66, -0.05, 1.00, -0.84, -0.40},
                {0.63, 0.16, -0.90, 0.54, -0.46, -0.84, 1.00, -0.55},
                {0.30, 0.41, -0.66, -0.66, 0.71, -0.40, -0.55, 1.00}
        };
    }

    private String[] getDimensions(JCorrelationMatrixOption option, int rows, int cols) {
        if (option != null && option.dataset() != null && option.dataset().dimensions() != null) {
            return option.dataset().dimensions();
        }
        String[] dimensions = new String[Math.max(rows, cols)];
        for (int i = 0; i < dimensions.length; i++) {
            dimensions[i] = "Var" + (i + 1);
        }
        return dimensions;
    }

    /**
     * 发散色标：正相关由浅蓝渐变至深蓝，负相关由浅红渐变至深红，0 处为浅色。
     */
    private static Color divergingColor(double value) {
        if (value >= 0) {
            return mix(ZERO_POS, POS_COLOR, Math.min(1.0, value));
        }
        return mix(ZERO_NEG, NEG_COLOR, Math.min(1.0, -value));
    }

    private static Color mix(Color a, Color b, double t) {
        return new Color(
                (int) (a.getRed() + (b.getRed() - a.getRed()) * t),
                (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t),
                (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * t));
    }

    private static double luminance(Color c) {
        return (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()) / 255.0;
    }

    private static Color withAlpha(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }
}

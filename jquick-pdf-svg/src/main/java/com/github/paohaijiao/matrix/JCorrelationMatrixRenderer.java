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

/**
 * 相关系数矩阵渲染器
 */
public class JCorrelationMatrixRenderer extends JAbstractChartRenderer {

    private static final Color GRID_COLOR = new Color(207, 210, 215);

    private static final Color BORDER_COLOR = new Color(84, 85, 90);

    private static final Color LEGEND_BG_COLOR = new Color(240, 240, 240);

    @Override
    protected int getDefaultWidth() {
        return 872;
    }

    @Override
    protected int getDefaultHeight() {
        return 400;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        JCorrelationMatrixOption jCorrelationMatrixOption = option.getCorrelationMatrixOption();
        drawTitle(g2d, option, width);
        int startX = 87;
        int startY = 120;
        int cellWidth = 77;
        int cellHeight = 19;
        double[][] correlationData = getCorrelationData(jCorrelationMatrixOption);
        int rows = correlationData.length;
        int cols = correlationData[0].length;
        drawGrid(g2d, startX, startY, cellWidth, cellHeight, rows, cols);
        drawCorrelationCells(g2d, startX, startY, cellWidth, cellHeight, correlationData);
        drawAxisLabels(g2d, startX, startY, cellWidth, cellHeight, rows, cols, jCorrelationMatrixOption);
        drawLegend(g2d, 367, 80);
    }

    private double[][] getCorrelationData(JCorrelationMatrixOption option) {
        if (option.dataset() != null && option.dataset().sourceArray() != null) {
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

    private void drawGrid(SVGGraphics2D g2d, int startX, int startY, int cellWidth, int cellHeight, int rows, int cols) {
        g2d.setColor(GRID_COLOR);
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i <= rows; i++) {
            int y = startY + i * cellHeight;
            g2d.drawLine(startX, y, startX + cols * cellWidth, y);
        }
        for (int i = 0; i <= cols; i++) {
            int x = startX + i * cellWidth;
            g2d.drawLine(x, startY, x, startY + rows * cellHeight);
        }
        g2d.setColor(BORDER_COLOR);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(startX, startY, cols * cellWidth, rows * cellHeight);
    }

    private void drawCorrelationCells(SVGGraphics2D g2d, int startX, int startY, int cellWidth, int cellHeight, double[][] correlationData) {
        int rows = correlationData.length;
        int cols = correlationData[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = correlationData[i][j];
                Color cellColor = getColorForCorrelation(value);
                int x = startX + j * cellWidth;
                int y = startY + i * cellHeight;
                g2d.setColor(cellColor);
                g2d.fillRect(x, y, cellWidth, cellHeight);
                g2d.setColor(GRID_COLOR);
                g2d.drawRect(x, y, cellWidth, cellHeight);
                String text = String.format("%.2f", value);
                g2d.setColor(Math.abs(value) > 0.5 ? Color.WHITE : Color.BLACK);
                g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int textX = x + (cellWidth - textWidth) / 2;
                int textY = y + (cellHeight - textHeight) / 2 + fm.getAscent();
                g2d.drawString(text, textX, textY);
            }
        }
    }

    private void drawAxisLabels(SVGGraphics2D g2d, int startX, int startY, int cellWidth, int cellHeight, int rows, int cols, JCorrelationMatrixOption option) {
        g2d.setColor(BORDER_COLOR);
        g2d.setFont(LABEL_FONT);
        String[] dimensions = getDimensions(option, rows, cols);
        for (int i = 0; i < cols; i++) {
            String label = dimensions[i];
            int x = startX + i * cellWidth + cellWidth / 2;
            int y = startY - 10;
            drawCenteredText(g2d, label, x, y);
        }
        for (int i = 0; i < rows; i++) {
            String label = dimensions[i];
            int x = startX - 40;
            int y = startY + i * cellHeight + cellHeight / 2;
            drawCenteredText(g2d, label, x, y);
        }
    }

    private String[] getDimensions(JCorrelationMatrixOption option, int rows, int cols) {
        if (option.dataset() != null && option.dataset().dimensions() != null) {
            return option.dataset().dimensions();
        }
        String[] dimensions = new String[Math.max(rows, cols)];
        for (int i = 0; i < dimensions.length; i++) {
            dimensions[i] = "Var" + (i + 1);
        }
        return dimensions;
    }

    private Color getColorForCorrelation(double value) {
        int baseRed = 80;
        int baseGreen = 112;
        int baseBlue = 221;
        double absValue = Math.abs(value);
        int intensity = (int) (absValue * 255);
        if (value >= 0) {
            return new Color(baseRed + (255 - baseRed) * (1 - intensity / 255) / 2, baseGreen + (255 - baseGreen) * (1 - intensity / 255) / 2, baseBlue);
        } else {
            return new Color(255, 200 + (55 * intensity / 255), 200 + (55 * intensity / 255));
        }
    }

    private void drawCenteredText(SVGGraphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        g2d.drawString(text, x - textWidth / 2, y);
    }

    private void drawLegend(SVGGraphics2D g2d, int x, int y) {
        g2d.setColor(LEGEND_BG_COLOR);
        g2d.fillRect(x - 30, y - 15, 180, 60);
        g2d.setColor(GRID_COLOR);
        g2d.drawRect(x - 30, y - 15, 180, 60);
        g2d.setColor(BORDER_COLOR);
        g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        g2d.drawString("相关系数", x, y - 5);
        int barWidth = 140;
        int barHeight = 20;
        for (int i = 0; i < barWidth; i++) {
            double value = (i * 2.0 / barWidth) - 1.0;
            Color color = getColorForCorrelation(value);
            g2d.setColor(color);
            g2d.fillRect(x + i, y + 10, 1, barHeight);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y + 10, barWidth, barHeight);
        g2d.drawString("-1", x, y + 45);
        g2d.drawString("1", x + barWidth, y + 45);
    }
}

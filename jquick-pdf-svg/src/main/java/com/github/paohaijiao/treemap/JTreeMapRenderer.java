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
package com.github.paohaijiao.treemap;

import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.series.JSeries;
import com.github.paohaijiao.style.JItemStyle;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * packageName com.github.paohaijiao.treemap
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/7
 */
public class JTreeMapRenderer  extends JAbstractChartRenderer {
    private static final Color[] COLORS = generateEChartsStyleColors();
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("0.0%");
    private static final int BORDER_RADIUS = 4;
    private static final int LABEL_PADDING = 8;

    @Override
    protected void drawChart(SVGGraphics2D svg, JOption option, int width, int height) {
        // ECharts风格的背景
        svg.setPaint(new Color(250, 250, 250));
        svg.fillRect(0, 0, width, height);
        drawTitle(svg, option, width);

        JTreeMapSeries series = findTreeMapSeries(option);
        if (series == null) return;

        Rectangle area = new Rectangle(50, 70, width - 100, height - 120);
        drawEChartsTreeMap(svg, series, area);
    }

    private JTreeMapSeries findTreeMapSeries(JOption option) {
        if (option.series() == null) return null;
        return option.series().stream()
                .filter(s -> s instanceof JTreeMapSeries)
                .map(s -> (JTreeMapSeries) s)
                .findFirst()
                .orElse(null);
    }

    private void drawEChartsTreeMap(SVGGraphics2D svg, JTreeMapSeries series, Rectangle area) {
        List<JData> dataList = series.data();
        double total = dataList.stream().mapToDouble(d -> ((Number)d.value()).doubleValue()).sum();

        // ECharts的squarified算法
        squarify(svg, new ArrayList<>(dataList), new ArrayList<>(), area, total,
                series, 0, true, calculateDepth(dataList));
    }

    private void squarify(SVGGraphics2D svg, List<JData> remaining, List<JData> row,
                          Rectangle rect, double total, JTreeMapSeries series,
                          int colorIndex, boolean horizontal, int maxDepth) {
        if (remaining.isEmpty()) {
            layoutRow(svg, row, rect, total, series, colorIndex, horizontal, maxDepth);
            return;
        }

        JData next = remaining.get(0);
        List<JData> newRow = new ArrayList<>(row);
        newRow.add(next);

        if (row.isEmpty() || worstRatio(row, rect, horizontal) >= worstRatio(newRow, rect, horizontal)) {
            squarify(svg, remaining.subList(1, remaining.size()), newRow, rect, total,
                    series, colorIndex, horizontal, maxDepth);
        } else {
            Rectangle[] split = layoutRow(svg, row, rect, total, series, colorIndex, horizontal, maxDepth);
            squarify(svg, remaining, new ArrayList<>(), split[1], total,
                    series, (colorIndex + row.size()) % COLORS.length, !horizontal, maxDepth);
        }
    }

    private Rectangle[] layoutRow(SVGGraphics2D svg, List<JData> row, Rectangle rect,
                                  double total, JTreeMapSeries series, int colorIndex,
                                  boolean horizontal, int maxDepth) {
        double sum = row.stream().mapToDouble(d -> ((Number)d.value()).doubleValue()).sum();
        double ratio = sum / total;

        Rectangle rowRect, remainingRect;
        if (horizontal) {
            int h = (int)(rect.height * ratio);
            rowRect = new Rectangle(rect.x, rect.y, rect.width, h);
            remainingRect = new Rectangle(rect.x, rect.y + h, rect.width, rect.height - h);

            double accumulated = 0;
            for (JData data : row) {
                double r = ((Number)data.value()).doubleValue() / sum;
                int w = (int)(rowRect.width * r);
                Rectangle cell = new Rectangle(
                        rowRect.x + (int)(rowRect.width * accumulated),
                        rowRect.y, w, rowRect.height
                );
                drawEChartsNode(svg, data, cell, (colorIndex + row.indexOf(data)) % COLORS.length,
                        ((Number)data.value()).doubleValue() / total, maxDepth);
                accumulated += r;
            }
        } else {
            int w = (int)(rect.width * ratio);
            rowRect = new Rectangle(rect.x, rect.y, w, rect.height);
            remainingRect = new Rectangle(rect.x + w, rect.y, rect.width - w, rect.height);

            double accumulated = 0;
            for (JData data : row) {
                double r = ((Number)data.value()).doubleValue() / sum;
                int h = (int)(rowRect.height * r);
                Rectangle cell = new Rectangle(
                        rowRect.x,
                        rowRect.y + (int)(rowRect.height * accumulated),
                        rowRect.width, h
                );
                drawEChartsNode(svg, data, cell, (colorIndex + row.indexOf(data)) % COLORS.length,
                        ((Number)data.value()).doubleValue() / total, maxDepth);
                accumulated += r;
            }
        }
        return new Rectangle[]{rowRect, remainingRect};
    }

    private void drawEChartsNode(SVGGraphics2D svg, JData data, Rectangle rect,
                                 int colorIndex, double ratio, int maxDepth) {

        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                rect.x, rect.y, rect.width, rect.height, BORDER_RADIUS, BORDER_RADIUS
        );

        // 根据层级计算颜色深浅
        Color color = adjustColorByDepth(COLORS[colorIndex], calculateNodeDepth(data), maxDepth);
        svg.setPaint(color);
        svg.fill(roundedRect);

        // 白色半透明边框
        svg.setPaint(new Color(255, 255, 255, 150));
        svg.setStroke(new BasicStroke(1.5f));
        svg.draw(roundedRect);

        // 绘制标签（仅当空间足够时）
        if (rect.width > 80 && rect.height > 50) {
            drawEChartsLabel(svg, data, rect, ratio);
        }
    }

    private void drawEChartsLabel(SVGGraphics2D svg, JData data, Rectangle rect, double ratio) {
        String name = data.name();
        String value = formatValue(data.value());
        String percent = PERCENT_FORMAT.format(ratio);

        svg.setPaint(Color.WHITE);
        Font font = new Font("Microsoft YaHei", Font.PLAIN, calculateFontSize(rect));
        svg.setFont(font);

        FontMetrics fm = svg.getFontMetrics();
        String[] lines = {name, value, percent};
        int totalHeight = fm.getHeight() * lines.length;
        int y = rect.y + (rect.height - totalHeight) / 2 + fm.getAscent();

        for (String line : lines) {
            int x = rect.x + (rect.width - fm.stringWidth(line)) / 2;
            // 文字阴影效果
            svg.setPaint(new Color(0, 0, 0, 100));
            svg.drawString(line, x + 1, y + 1);
            svg.setPaint(Color.WHITE);
            svg.drawString(line, x, y);
            y += fm.getHeight();
        }
    }

    // 生成ECharts风格的颜色数组
    private static Color[] generateEChartsStyleColors() {
        return new Color[] {
                new Color(55, 162, 218), new Color(116, 21, 219),
                new Color(254, 194, 16), new Color(255, 125, 64),
                new Color(50, 207, 193), new Color(97, 216, 0),
                new Color(244, 91, 105), new Color(148, 112, 196),
                new Color(247, 159, 31), new Color(18, 205, 201)
        };
    }

    // 根据节点深度调整颜色
    private Color adjustColorByDepth(Color base, int depth, int maxDepth) {
        float factor = 0.8f + (0.2f * depth / maxDepth);
        return new Color(
                Math.min(255, (int)(base.getRed() * factor)),
                Math.min(255, (int)(base.getGreen() * factor)),
                Math.min(255, (int)(base.getBlue() * factor))
        );
    }

    // 计算节点深度
    private int calculateNodeDepth(JData data) {
        if (data.getChildren() == null || data.getChildren().isEmpty()) return 0;
        int maxChildDepth = 0;
        for (JData child : data.getChildren()) {
            maxChildDepth = Math.max(maxChildDepth, calculateNodeDepth(child));
        }
        return maxChildDepth + 1;
    }

    // 计算树的最大深度
    private int calculateDepth(List<JData> dataList) {
        int max = 0;
        for (JData data : dataList) {
            max = Math.max(max, calculateNodeDepth(data));
        }
        return max;
    }
    private int calculateFontSize(Rectangle rect, int lineCount) {
        // 基础计算：取矩形短边的1/8（经验值）
        int shortSide = Math.min(rect.width, rect.height);
        int baseSize = shortSide / 8;

        // 根据行数调整（确保行间距合理）
        int heightBasedSize = (int)(rect.height / (lineCount * 1.8));

        // 边界控制（最小10px，最大不超过短边的1/4）
        int minSize = 10;
        int maxSize = Math.max(16, shortSide / 4);

        // 取高度和基础计算的最小值，并限制在范围内
        return Math.max(minSize, Math.min(maxSize, Math.min(baseSize, heightBasedSize)));
    }

    // 重载简化版（默认3行文本）
    private int calculateFontSize(Rectangle rect) {
        return calculateFontSize(rect, 3);
    }
    private String formatValue(Object value) {
        if (!(value instanceof Number)) {
            return String.valueOf(value);
        }

        double num = ((Number) value).doubleValue();
        String[] units = {"", "K", "M", "B"};
        int unitIndex = 0;

        // 计算合适的单位
        while (num >= 1000 && unitIndex < units.length - 1) {
            num /= 1000;
            unitIndex++;
        }

        // 决定小数位数
        String formatPattern;
        if (num >= 100) {
            formatPattern = "%,.0f%s";  // 100+ 显示整数
        } else if (num >= 10) {
            formatPattern = "%,.1f%s";  // 10-100 显示1位小数
        } else {
            formatPattern = "%,.2f%s";  // <10 显示2位小数
        }

        return String.format(formatPattern, num, units[unitIndex]);
    }
    private double worstRatio(List<JData> row, Rectangle rect, boolean horizontal) {
        double sum = row.stream().mapToDouble(d -> ((Number)d.value()).doubleValue()).sum();
        double area = horizontal ? rect.getWidth() * rect.getHeight() : rect.getHeight() * rect.getWidth();
        double ratio = sum / area;

        double maxRatio = Double.MIN_VALUE;
        double minRatio = Double.MAX_VALUE;

        for (JData data : row) {
            double value = ((Number) data.value()).doubleValue();
            double r = value / (sum * ratio);
            maxRatio = Math.max(maxRatio, r);
            minRatio = Math.min(minRatio, r);
        }

        // 返回最大比例差（ECharts标准计算方式）
        return Math.max(maxRatio / minRatio, minRatio / maxRatio);
    }
}

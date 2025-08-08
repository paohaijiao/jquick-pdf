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
import com.github.paohaijiao.series.JSeries;
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
    private static final Color[] COLORS = {
            new Color(65, 105, 225), new Color(60, 179, 113),
            new Color(255, 165, 0),  new Color(220, 20, 60),
            new Color(147, 112, 219), new Color(30, 144, 255)
    };

    private static final Color[] DARK_COLORS = {
            new Color(25, 65, 165),  new Color(20, 119, 63),
            new Color(205, 105, 0),  new Color(160, 0, 20),
            new Color(97, 62, 159),  new Color(0, 94, 184)
    };

    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("0.0%");
    private static final int MIN_PARTITION_SIZE = 30;
    private static final int PADDING = 1;
    private static final int LABEL_PADDING = 5;
    private static final Color BORDER_COLOR = new Color(255, 255, 255, 100);

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(new Color(245, 245, 245));
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);

        JTreeMapSeries series = findTreeMapSeries(option);
        if (series == null || series.data() == null || series.data().isEmpty()) {
            return;
        }

        Rectangle area = new Rectangle(50, 70, width - 100, height - 120);
        drawEChartsStyleTreeMap(svgGenerator, series, area);
    }

    private void drawEChartsStyleTreeMap(SVGGraphics2D svgGenerator, JTreeMapSeries series, Rectangle area) {
        List<JData> roots = series.data();
        double total = calculateTotalValue(roots);

        // 使用队列进行广度优先遍历
        Queue<LayoutNode> queue = new LinkedList<>();
        int colorIndex = 0;

        for (JData root : roots) {
            queue.add(new LayoutNode(root, area, false, colorIndex));
            colorIndex = (colorIndex + 1) % COLORS.length;
        }

        while (!queue.isEmpty()) {
            LayoutNode node = queue.poll();
            JData data = node.data;
            Rectangle rect = node.rect;

            if (rect.width < MIN_PARTITION_SIZE || rect.height < MIN_PARTITION_SIZE) {
                continue;
            }

            // 绘制当前节点
            double value = ((Number) data.value()).doubleValue();
            double ratio = value / total;
            drawEChartsNode(svgGenerator, data, rect, node.colorIndex, ratio, node.isHorizontal);
            if (data.getChildren() != null && !data.getChildren().isEmpty()) {
                List<JData> children = new ArrayList<>(data.getChildren());
                children.sort(Comparator.comparingDouble(d -> -((Number) d.value()).doubleValue()));

                double childrenTotal = calculateTotalValue(children);
                List<Rectangle> childRects = calculateChildRects(rect, children, childrenTotal, !node.isHorizontal);

                for (int i = 0; i < children.size(); i++) {
                    if (i < childRects.size()) {
                        queue.add(new LayoutNode(children.get(i), childRects.get(i), !node.isHorizontal, node.colorIndex));
                    }
                }
            }
        }
    }

    private List<Rectangle> calculateChildRects(Rectangle parentRect, List<JData> children,
                                                double total, boolean isHorizontal) {
        List<Rectangle> rects = new ArrayList<>();
        double accumulated = 0;

        for (JData child : children) {
            double value = ((Number) child.value()).doubleValue();
            double ratio = value / total;

            Rectangle rect;
            if (isHorizontal) {
                int height = (int)(parentRect.height * ratio);
                rect = new Rectangle(
                        parentRect.x,
                        parentRect.y + (int)(parentRect.height * accumulated),
                        parentRect.width,
                        height
                );
            } else {
                int width = (int)(parentRect.width * ratio);
                rect = new Rectangle(
                        parentRect.x + (int)(parentRect.width * accumulated),
                        parentRect.y,
                        width,
                        parentRect.height
                );
            }

            rects.add(rect);
            accumulated += ratio;
        }

        return rects;
    }

    private void drawEChartsNode(SVGGraphics2D svgGenerator, JData data, Rectangle rect,
                                 int colorIndex, double ratio, boolean isParentHorizontal) {
        // 绘制背景 (使用深色作为底色)
        Color baseColor = DARK_COLORS[colorIndex % DARK_COLORS.length];
        svgGenerator.setPaint(baseColor);
        svgGenerator.fillRect(rect.x, rect.y, rect.width, rect.height);

        // 添加渐变效果 (使用浅色作为高光)
        Color highlightColor = COLORS[colorIndex % COLORS.length];
        GradientPaint gradient = isParentHorizontal
                ? new GradientPaint(rect.x, rect.y, highlightColor, rect.x, rect.y + rect.height, baseColor)
                : new GradientPaint(rect.x, rect.y, highlightColor, rect.x + rect.width, rect.y, baseColor);

        svgGenerator.setPaint(gradient);
        svgGenerator.fillRect(rect.x, rect.y, rect.width, rect.height);

        // 绘制边框
        svgGenerator.setPaint(BORDER_COLOR);
        svgGenerator.drawRect(rect.x, rect.y, rect.width, rect.height);

        // 绘制标签
        if (rect.width > MIN_PARTITION_SIZE * 2 && rect.height > MIN_PARTITION_SIZE * 2) {
            drawEChartsLabel(svgGenerator, data, rect, ratio);
        }
    }

    private void drawEChartsLabel(SVGGraphics2D svgGenerator, JData data, Rectangle rect, double ratio) {
        String name = data.name();
        String value = formatSize(((Number) data.value()).doubleValue());
        String percent = PERCENT_FORMAT.format(ratio);

        svgGenerator.setPaint(Color.WHITE);

        // 计算字体大小
        int fontSize = Math.min(rect.width, rect.height) / 8;
        fontSize = Math.max(10, Math.min(16, fontSize));
        Font font = new Font("Microsoft YaHei", Font.BOLD, fontSize);
        svgGenerator.setFont(font);

        FontMetrics metrics = svgGenerator.getFontMetrics();
        int nameWidth = metrics.stringWidth(name);
        int valueWidth = metrics.stringWidth(value);
        int percentWidth = metrics.stringWidth(percent);

        int maxWidth = Math.max(nameWidth, Math.max(valueWidth, percentWidth));

        if (maxWidth + LABEL_PADDING * 2 > rect.width) {
            // 空间不足，只显示名称
            int x = rect.x + (rect.width - nameWidth) / 2;
            int y = rect.y + (rect.height + metrics.getAscent()) / 2;
            svgGenerator.drawString(name, x, y);
        } else {
            // 显示完整信息
            int x = rect.x + (rect.width - nameWidth) / 2;
            int y = rect.y + rect.height / 2 - metrics.getHeight();
            svgGenerator.drawString(name, x, y);

            x = rect.x + (rect.width - valueWidth) / 2;
            y += metrics.getHeight();
            svgGenerator.drawString(value, x, y);

            x = rect.x + (rect.width - percentWidth) / 2;
            y += metrics.getHeight();
            svgGenerator.drawString(percent, x, y);
        }
    }

    private double calculateTotalValue(List<JData> dataList) {
        return dataList.stream()
                .mapToDouble(data -> ((Number) data.value()).doubleValue())
                .sum();
    }

    private String formatSize(double size) {
        if (size < 1024) return String.format("%.0f B", size);
        if (size < 1024 * 1024) return String.format("%.1f KB", size / 1024);
        if (size < 1024 * 1024 * 1024) return String.format("%.1f MB", size / (1024 * 1024));
        return String.format("%.1f GB", size / (1024 * 1024 * 1024));
    }

    private JTreeMapSeries findTreeMapSeries(JOption option) {
        if (option.series() == null) return null;
        return option.series().stream()
                .filter(s -> s instanceof JTreeMapSeries)
                .map(s -> (JTreeMapSeries) s)
                .findFirst()
                .orElse(null);
    }

    private static class LayoutNode {
        JData data;
        Rectangle rect;
        boolean isHorizontal;
        int colorIndex;

        LayoutNode(JData data, Rectangle rect, boolean isHorizontal, int colorIndex) {
            this.data = data;
            this.rect = rect;
            this.isHorizontal = isHorizontal;
            this.colorIndex = colorIndex;
        }
    }

    @Override
    protected int getDefaultWidth() {
        return 900;
    }

    @Override
    protected int getDefaultHeight() {
        return 600;
    }
}

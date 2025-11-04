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
package com.github.paohaijiao.sunburst;
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
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.series.SunburstSeries;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSunburstChart extends JAbstractChartRenderer {

    private static class SunburstNode {
        String name;
        Color color;
        Color borderColor;
        List<SunburstNode> children = new ArrayList<>();
        double value = 1;
        double startAngle;
        double endAngle;
        int depth;
        double radius;
        double innerRadius;
        SunburstNode parent;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // 解析数据并构建完整的树结构
        SunburstNode root = buildTreeFromOption(option);

        // 计算角度和半径
        calculateTreeValues(root);
        calculateTreeAngles(root, 0, 2 * Math.PI);

        int maxRadius = Math.min(centerX, centerY) - 100;
        calculateTreeRadii(root, 0, maxRadius, 3); // 3层深度

        // 绘制标题
        drawTitle(svgGenerator, option, width);

        // 绘制旭日图
        drawSunburst(svgGenerator, root, centerX, centerY);

        // 绘制标签
        drawLabels(svgGenerator, root, centerX, centerY);
    }

    private SunburstNode buildTreeFromOption(JOption option) {
        SunburstNode root = new SunburstNode();
        root.name = "root";
        root.depth = 0;
        root.value = 0;

        List<Map<String, Object>> seriesData = null;
        if (option.getSeries() != null && !option.getSeries().isEmpty()) {
            Object firstSeries = option.getSeries().get(0);
            if (firstSeries instanceof SunburstSeries) {
                seriesData = ((SunburstSeries) firstSeries).getData();
            }
        }

        if (seriesData == null || seriesData.isEmpty()) {
            seriesData = createCoffeeFlavorData();
        }

        List<Object> colors = option.getColor();
        if (colors == null || colors.isEmpty()) {
            colors = getHarmoniousColors();
        }

        // 构建多层树结构
        int colorIndex = 0;
        for (Map<String, Object> categoryData : seriesData) {
            SunburstNode categoryNode = createNodeFromData(categoryData, 1, colors, colorIndex++);
            categoryNode.parent = root;
            root.children.add(categoryNode);
            root.value += categoryNode.value;
        }

        return root;
    }

    private SunburstNode createNodeFromData(Map<String, Object> data, int depth, List<Object> colors, int colorIndex) {
        SunburstNode node = new SunburstNode();
        node.name = (String) data.get("name");
        node.value = getDoubleValue(data.get("value"));
        node.depth = depth;

        // 设置颜色 - 根据深度设置不同的颜色策略
        if (depth == 1) {
            node.color = parseColor(colors.get(colorIndex % colors.size()));
        } else if (depth == 2) {
            // 第二层使用父节点颜色的变体
            Color parentColor = parseColor(colors.get((colorIndex) % colors.size()));
            node.color = adjustColor(parentColor, 0.7f, 1.2f);
        } else {
            // 第三层使用更浅的颜色
            Color parentColor = parseColor(colors.get((colorIndex) % colors.size()));
            node.color = adjustColor(parentColor, 0.5f, 1.4f);
        }
        node.borderColor = Color.WHITE;

        // 递归创建子节点
        List<Map<String, Object>> childrenData = (List<Map<String, Object>>) data.get("children");
        if (childrenData != null && !childrenData.isEmpty()) {
            for (Map<String, Object> childData : childrenData) {
                SunburstNode childNode = createNodeFromData(childData, depth + 1, colors, colorIndex);
                childNode.parent = node;
                node.children.add(childNode);
            }
        }

        return node;
    }

    private void calculateTreeValues(SunburstNode node) {
        if (node.children.isEmpty()) {
            return;
        }

        double total = 0;
        for (SunburstNode child : node.children) {
            calculateTreeValues(child);
            total += child.value;
        }

        // 如果父节点没有值，使用子节点值的总和
        if (node.value == 0) {
            node.value = total;
        }
    }

    private void calculateTreeAngles(SunburstNode node, double startAngle, double endAngle) {
        node.startAngle = startAngle;
        node.endAngle = endAngle;

        if (!node.children.isEmpty()) {
            double total = 0;
            for (SunburstNode child : node.children) {
                total += child.value;
            }

            double currentStart = startAngle;
            for (SunburstNode child : node.children) {
                double childSpan = (endAngle - startAngle) * (child.value / total);
                calculateTreeAngles(child, currentStart, currentStart + childSpan);
                currentStart += childSpan;
            }
        }
    }

    private void calculateTreeRadii(SunburstNode node, double innerRadius, double outerRadius, int maxDepth) {
        if (node.depth > maxDepth) {
            return;
        }

        node.innerRadius = innerRadius;
        node.radius = outerRadius;

        if (!node.children.isEmpty()) {
            double layerHeight = (outerRadius - innerRadius) / (maxDepth - node.depth + 1);
            double childInnerRadius = outerRadius;
            double childOuterRadius = outerRadius + layerHeight;

            for (SunburstNode child : node.children) {
                calculateTreeRadii(child, childInnerRadius, childOuterRadius, maxDepth);
            }
        }
    }

    private void drawSunburst(SVGGraphics2D g, SunburstNode node, int centerX, int centerY) {
        // 不绘制根节点
        if (node.depth > 0) {
            double startDeg = Math.toDegrees(node.startAngle);
            double extentDeg = Math.toDegrees(node.endAngle - node.startAngle);

            if (extentDeg > 0.5) { // 只绘制足够大的扇形
                Arc2D arc = new Arc2D.Double(
                        centerX - node.radius, centerY - node.radius,
                        node.radius * 2, node.radius * 2,
                        startDeg, extentDeg, Arc2D.PIE
                );

                g.setColor(node.color);
                g.fill(arc);

                g.setColor(node.borderColor);
                g.setStroke(new BasicStroke(1.0f));
                g.draw(arc);
            }
        }

        for (SunburstNode child : node.children) {
            drawSunburst(g, child, centerX, centerY);
        }
    }

    private void drawLabels(SVGGraphics2D g, SunburstNode node, int centerX, int centerY) {
        if (node.depth > 0 && node.name != null && (node.endAngle - node.startAngle) > 0.1) {
            double midAngle = (node.startAngle + node.endAngle) / 2;
            double labelRadius = node.innerRadius + (node.radius - node.innerRadius) * 0.5;

            Point2D labelPoint = getPoint(centerX, centerY, labelRadius, midAngle);

            Font font = new Font("微软雅黑", Font.PLAIN, 14 - node.depth * 2);
            g.setFont(font);
            g.setColor(Color.BLACK);

            String label = node.name;
            // 只显示足够空间的标签
            if ((node.endAngle - node.startAngle) > 0.3) {
                int x = (int) labelPoint.getX();
                int y = (int) labelPoint.getY();

                int textWidth = g.getFontMetrics().stringWidth(label);
                if (midAngle > Math.PI / 2 && midAngle < 3 * Math.PI / 2) {
                    x -= textWidth;
                }

                y += g.getFontMetrics().getHeight() / 4;
                g.drawString(label, x, y);
            }
        }

        for (SunburstNode child : node.children) {
            drawLabels(g, child, centerX, centerY);
        }
    }

    private static Point2D getPoint(int centerX, int centerY, double radius, double angle) {
        return new Point2D.Double(
                centerX + radius * Math.cos(angle),
                centerY + radius * Math.sin(angle)
        );
    }

    private Color adjustColor(Color color, float saturationFactor, float brightnessFactor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newSaturation = Math.max(0, Math.min(1, hsb[1] * saturationFactor));
        float newBrightness = Math.max(0, Math.min(1, hsb[2] * brightnessFactor));
        return Color.getHSBColor(hsb[0], newSaturation, newBrightness);
    }

    // 咖啡风味数据 - 参考ECharts官方示例
    private List<Map<String, Object>> createCoffeeFlavorData() {
        List<Map<String, Object>> data = new ArrayList<>();

        // 第一层: 主要风味类别
        data.add(createCategory("花香", 10,
                createSubCategory("Floral", 4),
                createSubCategory("Tea", 6)
        ));

        data.add(createCategory("果香", 14,
                createSubCategory("Berry", 5),
                createSubCategory("Dried Fruit", 3),
                createSubCategory("Other Fruit", 3),
                createSubCategory("Citrus Fruit", 3)
        ));

        data.add(createCategory("酸味/发酵", 5,
                createSubCategory("Sour", 2),
                createSubCategory("Alcohol/Fermented", 3)
        ));

        data.add(createCategory("青草/植物", 12,
                createSubCategory("Olive Oil", 3),
                createSubCategory("Raw", 3),
                createSubCategory("Green/Vegetative", 3),
                createSubCategory("Beany", 3)
        ));

        data.add(createCategory("烘烤", 15,
                createSubCategory("Pipe Tobacco", 4),
                createSubCategory("Tobacco", 4),
                createSubCategory("Burnt", 3),
                createSubCategory("Cereal", 4)
        ));

        data.add(createCategory("香料", 9,
                createSubCategory("Pungent", 3),
                createSubCategory("Pepper", 3),
                createSubCategory("Brown Spice", 3)
        ));

        data.add(createCategory("坚果/可可", 13,
                createSubCategory("Nutty", 7),
                createSubCategory("Cocoa", 6)
        ));

        data.add(createCategory("甜味", 17,
                createSubCategory("Brown Sugar", 4),
                createSubCategory("Vanilla", 4),
                createSubCategory("Vanillin", 3),
                createSubCategory("Overall Sweet", 3),
                createSubCategory("Sweet Aromatics", 3)
        ));

        data.add(createCategory("其他", 5,
                createSubCategory("Chemical", 2),
                createSubCategory("Musty/Earthy", 3)
        ));

        return data;
    }

    private Map<String, Object> createCategory(String name, double value, Map<String, Object>... children) {
        Map<String, Object> category = new java.util.HashMap<>();
        category.put("name", name);
        category.put("value", value);
        category.put("children", java.util.Arrays.asList(children));
        return category;
    }

    private Map<String, Object> createSubCategory(String name, double value) {
        Map<String, Object> subCategory = new java.util.HashMap<>();
        subCategory.put("name", name);
        subCategory.put("value", value);
        return subCategory;
    }

    private List<Object> getHarmoniousColors() {
        return java.util.Arrays.asList(
                "#c23531", "#2f4554", "#61a0a8", "#d48265", "#91c7ae",
                "#749f83", "#ca8622", "#bda29a", "#6e7074", "#546570"
        );
    }

    private double getDoubleValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 1.0;
    }

    private Color parseColor(Object colorObj) {
        if (colorObj instanceof String) {
            try {
                return Color.decode((String) colorObj);
            } catch (NumberFormatException e) {
                return Color.GRAY;
            }
        }
        return Color.GRAY;
    }
}
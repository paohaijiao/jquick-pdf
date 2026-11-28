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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树形图渲染器，所有配置通过JOption传入
 */
@JQuickSpi(name = "tree-map", order = 12, chartType = JChartType.TREEMAP)
public class JTreeMapRenderer extends JAbstractChartRenderer {

    /**
     * 内置调色板：未在颜色表中命中且无部门规则可推断的节点，
     * 按名称稳定哈希取色，保证同名节点颜色一致、整体色彩丰富
     */
    private static final Color[] DEFAULT_PALETTE = {
            new Color(66, 133, 244),   // blue
            new Color(219, 68, 55),    // red
            new Color(244, 180, 0),    // amber
            new Color(15, 157, 88),    // green
            new Color(123, 31, 162),   // purple
            new Color(0, 172, 193),    // cyan
            new Color(255, 112, 67),   // deep orange
            new Color(57, 175, 209),   // light blue
            new Color(103, 58, 183),   // deep purple
            new Color(139, 195, 74),   // light green
            new Color(255, 87, 34),    // orange red
            new Color(0, 150, 136),    // teal
            new Color(233, 30, 99),    // pink
            new Color(121, 85, 72),    // brown
            new Color(63, 81, 181),    // indigo
            new Color(205, 220, 57)    // lime
    };

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(getBackgroundColor(option));
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);
        TreeMapOption treemapOption = getTreemapOption(option);
        if (treemapOption == null || treemapOption.getRoot() == null) {
            drawNoDataMessage(svgGenerator, width, height);
            return;
        }
        calculateTreeLayout(treemapOption.getRoot(), treemapOption.getMarginLeft(), treemapOption.getMarginTop(),
                width - treemapOption.getMarginLeft() - treemapOption.getMarginRight(),
                height - treemapOption.getMarginTop() - treemapOption.getMarginBottom());

        drawTreemap(svgGenerator, treemapOption.getRoot(), treemapOption);
        if (treemapOption.isShowLegend()) {
            addLegend(svgGenerator, width, height, treemapOption);
        }
    }

    /**
     * 从JOption中获取树形图专用配置
     */
    private TreeMapOption getTreemapOption(JOption option) {
        if (option.getTreemapOption() instanceof TreeMapOption) {
            return option.getTreemapOption();
        }
        return new TreeMapOption();
    }

    /**
     * 获取背景颜色
     */
    private Color getBackgroundColor(JOption option) {
        TreeMapOption treemapOption = getTreemapOption(option);
        return treemapOption.getBackgroundColor() != null ? treemapOption.getBackgroundColor() : BACKGROUND_COLOR;
    }

    /**
     * 绘制无数据提示
     */
    private void drawNoDataMessage(SVGGraphics2D svgGenerator, int width, int height) {
        svgGenerator.setColor(Color.GRAY);
        svgGenerator.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        String message = "暂无数据";
        int textWidth = svgGenerator.getFontMetrics().stringWidth(message);
        svgGenerator.drawString(message, width / 2 - textWidth / 2, height / 2);
    }

    /**
     * 计算整个树的布局
     */
    private void calculateTreeLayout(JTreeMapNode node, double x, double y, double width, double height) {
        if (node == null) return;
        if (node.getRect() == null) {
            node.setRect(new Rectangle2D.Double(x, y, width, height));
        }
        if (node.hasChildren()) {
            squarifyLayout(node.getChildren(), x, y, width, height);
            for (JTreeMapNode child : node.getChildren()) {
                layoutSubtree(child, 1);
            }
        }
    }

    /**
     * 递归布局子树：先按深度收缩间距，再对下一层执行 squarify
     */
    private void layoutSubtree(JTreeMapNode node, int depth) {
        if (!node.hasChildren()) {
            return;
        }
        Rectangle2D rect = node.getRect();
        if (rect == null) {
            return;
        }
        double gap = depth <= 1 ? 4.0 : 2.0;
        double innerX = rect.getX() + gap;
        double innerY = rect.getY() + gap;
        double innerWidth = rect.getWidth() - 2 * gap;
        double innerHeight = rect.getHeight() - 2 * gap;
        if (innerWidth > 0 && innerHeight > 0) {
            squarifyLayout(node.getChildren(), innerX, innerY, innerWidth, innerHeight);
            for (JTreeMapNode child : node.getChildren()) {
                layoutSubtree(child, depth + 1);
            }
        }
    }

    /**
     * Squarified treemap 布局（Bruls et al.）：逐行贪心放置，
     * 行方向随剩余矩形的短边切换，使矩形长宽比尽量接近 1，形成纵横交错的排布
     */
    private void squarifyLayout(List<JTreeMapNode> nodes, double x, double y, double width, double height) {
        if (nodes == null || nodes.isEmpty() || width <= 0 || height <= 0) {
            return;
        }
        List<JTreeMapNode> items = new ArrayList<>();
        double total = 0;
        for (JTreeMapNode node : nodes) {
            if (node.getValue() > 0) {
                items.add(node);
                total += node.getValue();
            }
        }
        if (items.isEmpty()) {
            return;
        }
        items.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        double scale = width * height / total;
        double rectX = x;
        double rectY = y;
        double rectWidth = width;
        double rectHeight = height;
        List<JTreeMapNode> row = new ArrayList<>();
        List<Double> rowAreas = new ArrayList<>();
        double rowArea = 0;
        int index = 0;
        while (index < items.size()) {
            double nextArea = items.get(index).getValue() * scale;
            if (!row.isEmpty()) {
                double side = Math.min(rectWidth, rectHeight);
                if (worstAspectRatio(row, rowAreas, side)
                        < worstAspectRatio(row, append(rowAreas, nextArea), side)) {
                    double thickness = placeRow(row, rowAreas, rectX, rectY, rectWidth, rectHeight);
                    if (rectWidth >= rectHeight) {
                        rectX += thickness;
                        rectWidth -= thickness;
                    } else {
                        rectY += thickness;
                        rectHeight -= thickness;
                    }
                    row = new ArrayList<>();
                    rowAreas = new ArrayList<>();
                    rowArea = 0;
                }
            }
            row.add(items.get(index));
            rowAreas.add(nextArea);
            rowArea += nextArea;
            index++;
        }
        if (!row.isEmpty()) {
            placeRow(row, rowAreas, rectX, rectY, rectWidth, rectHeight);
        }
    }

    /**
     * 计算一行矩形的最大长宽比（越接近 1 越方正）
     */
    private double worstAspectRatio(List<JTreeMapNode> row, List<Double> areas, double side) {
        double areaSum = 0;
        for (double area : areas) {
            areaSum += area;
        }
        double thickness = areaSum / side;
        double worst = 0;
        for (double area : areas) {
            double length = thickness <= 0 ? 0 : area / thickness;
            if (length <= 0) {
                continue;
            }
            double ratio = Math.max(thickness / length, length / thickness);
            worst = Math.max(worst, ratio);
        }
        return worst;
    }

    /**
     * 沿剩余矩形短边方向放置一行，返回该行的厚度（即被占用的宽度或高度）
     */
    private double placeRow(List<JTreeMapNode> row, List<Double> areas,
                            double x, double y, double width, double height) {
        double areaSum = 0;
        for (double area : areas) {
            areaSum += area;
        }
        if (areaSum <= 0) {
            return 0;
        }
        if (width >= height) {
            double thickness = areaSum / height;
            double offset = 0;
            for (int i = 0; i < row.size(); i++) {
                double length = areas.get(i) / thickness;
                row.get(i).setRect(new Rectangle2D.Double(x, y + offset, thickness, length));
                offset += length;
            }
            return thickness;
        }
        double thickness = areaSum / width;
        double offset = 0;
        for (int i = 0; i < row.size(); i++) {
            double length = areas.get(i) / thickness;
            row.get(i).setRect(new Rectangle2D.Double(x + offset, y, length, thickness));
            offset += length;
        }
        return thickness;
    }

    /**
     * 追加元素并返回新列表（保持原列表不变）
     */
    private List<Double> append(List<Double> areas, double value) {
        List<Double> copy = new ArrayList<>(areas);
        copy.add(value);
        return copy;
    }

    /**
     * 绘制树形图
     */
    private void drawTreemap(SVGGraphics2D svgGenerator, JTreeMapNode node, TreeMapOption option) {
        if (node.getRect() == null) return;
        if (node.getName().equals("公司业务")) {
            if (node.hasChildren()) {
                for (JTreeMapNode child : node.getChildren()) {
                    drawTreemap(svgGenerator, child, option);
                }
            }
            return;
        }
        Color color = getNodeColor(node, option);
        svgGenerator.setColor(color);
        svgGenerator.fill(node.getRect());
        svgGenerator.setColor(option.getBorderColor());
        svgGenerator.setStroke(new BasicStroke(option.getBorderWidth()));
        svgGenerator.draw(node.getRect());
        drawNodeLabel(svgGenerator, node, color, option);
        if (node.hasChildren()) {
            for (JTreeMapNode child : node.getChildren()) {
                drawTreemap(svgGenerator, child, option);
            }
        }
    }

    /**
     * 获取节点颜色
     */
    private Color getNodeColor(JTreeMapNode node, TreeMapOption option) {
        if (node.getColor() != null) {
            return node.getColor();
        }

        Map<String, Color> categoryColors = option.getCategoryColors();
        if (categoryColors.containsKey(node.getName())) {
            return categoryColors.get(node.getName());
        }

        Map<String, Color> departmentColors = option.getDepartmentColors();
        if (departmentColors.containsKey(node.getName())) {
            return departmentColors.get(node.getName());
        }
        return generateSimilarColor(node, option);
    }

    /**
     * 生成相似颜色
     */
    private Color generateSimilarColor(JTreeMapNode node, TreeMapOption option) {
        String department = inferDepartment(node.getName(), option);
        Map<String, Color> departmentColors = option.getDepartmentColors();
        if (department != null && departmentColors.containsKey(department)) {
            Color baseColor = departmentColors.get(department);
            return adjustColorBrightness(baseColor, node.getName().hashCode() % option.getColorDeltaRange() - (option.getColorDeltaRange() / 2)
            );
        }
        if (!departmentColors.isEmpty()) {
            Color defaultBase = departmentColors.values().iterator().next();
            return adjustColorBrightness(defaultBase, node.getName().hashCode() % 30 - 15);
        }
        return DEFAULT_PALETTE[Math.abs(node.getName().hashCode()) % DEFAULT_PALETTE.length];
    }

    /**
     * 推断部门
     */
    private String inferDepartment(String categoryName, TreeMapOption option) {
        List<TreeMapMapping> rules = option.getDepartmentRules();
        for (TreeMapMapping rule : rules) {
            if (categoryName.contains(rule.getKeyword())) {
                return rule.getDepartment();
            }
        }
        return null;
    }

    /**
     * 调整颜色亮度
     */
    private Color adjustColorBrightness(Color color, int delta) {
        int r = Math.max(0, Math.min(255, color.getRed() + delta));
        int g = Math.max(0, Math.min(255, color.getGreen() + delta));
        int b = Math.max(0, Math.min(255, color.getBlue() + delta));
        return new Color(r, g, b);
    }

    /**
     * 绘制节点标签
     */
    private void drawNodeLabel(SVGGraphics2D svgGenerator, JTreeMapNode node, Color bgColor, TreeMapOption option) {
        Rectangle2D rect = node.getRect();
        if (rect.getWidth() < option.getMinLabelWidth() || rect.getHeight() < option.getMinLabelHeight()) {
            return;
        }
        Color textColor = getContrastColor(bgColor);
        svgGenerator.setColor(textColor);
        svgGenerator.setFont(new Font(option.getFontFamily(), Font.PLAIN, option.getFontSize()));
        String label = node.getName();
        if (rect.getWidth() > option.getMinValueLabelWidth() && rect.getHeight() > option.getMinValueLabelHeight()) {
            label += " (" + node.getValue() + ")";
        }
        int textWidth = svgGenerator.getFontMetrics().stringWidth(label);
        if (textWidth > rect.getWidth() - 10 && label.length() > option.getMaxLabelLength()) {
            label = label.substring(0, option.getMaxLabelLength() - 3) + "...";
            textWidth = svgGenerator.getFontMetrics().stringWidth(label);
        }
        if (textWidth < rect.getWidth() - 10) {
            double centerX = rect.getX() + rect.getWidth() / 2;
            double centerY = rect.getY() + rect.getHeight() / 2;
            svgGenerator.drawString(label, (float) (centerX - textWidth / 2), (float) (centerY + option.getFontSize() / 3));
        }
    }

    /**
     * 获取对比色
     */
    private Color getContrastColor(Color backgroundColor) {
        double brightness = (backgroundColor.getRed() * 299 + backgroundColor.getGreen() * 587 + backgroundColor.getBlue() * 114) / 1000.0;
        return brightness > 130 ? Color.BLACK : Color.WHITE;
    }

    /**
     * 添加图例
     */
    private void addLegend(SVGGraphics2D svgGenerator, int width, int height, TreeMapOption option) {
        int startX = option.getMarginLeft();
        int startY = height - option.getMarginBottom() + 10;
        int boxSize = option.getLegendBoxSize();
        svgGenerator.setFont(new Font(option.getFontFamily(), Font.PLAIN, option.getLegendFontSize()));
        for (Map.Entry<String, Color> entry : option.getDepartmentColors().entrySet()) {
            svgGenerator.setColor(entry.getValue());
            svgGenerator.fillRect(startX, startY, boxSize, boxSize);
            svgGenerator.setColor(Color.BLACK);
            svgGenerator.drawRect(startX, startY, boxSize, boxSize);
            svgGenerator.drawString(entry.getKey(), startX + boxSize + 5, startY + boxSize - 3);
            startX += option.getLegendItemWidth();
        }
        if (option.getLegendNote() != null) {
            svgGenerator.setColor(option.getLegendNoteColor());
            svgGenerator.drawString(option.getLegendNote(), option.getMarginLeft(), startY + boxSize + option.getLegendNoteSpacing());
        }
    }

    @Override
    protected int getDefaultWidth() {
        return 1200;
    }

    @Override
    protected int getDefaultHeight() {
        return 800;
    }
}

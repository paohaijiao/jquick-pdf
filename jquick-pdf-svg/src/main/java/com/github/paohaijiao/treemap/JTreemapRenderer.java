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
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

/**
 * 树形图渲染器，所有配置通过JOption传入
 */
public class JTreemapRenderer extends JAbstractChartRenderer {

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(getBackgroundColor(option));
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);
        TreemapOption treemapOption = getTreemapOption(option);
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
    private TreemapOption getTreemapOption(JOption option) {
        if (option.getTreemapOption() instanceof TreemapOption) {
            return option.getTreemapOption();
        }
        return new TreemapOption();
    }

    /**
     * 获取背景颜色
     */
    private Color getBackgroundColor(JOption option) {
        TreemapOption treemapOption = getTreemapOption(option);
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
     * 计算整个树的布局（新增方法）
     */
    private void calculateTreeLayout(JTreemapNode node, double x, double y, double width, double height) {
        if (node == null) return;
        if (node.getRect() == null) {// 为当前节点设置矩形（如果是根节点，通常不显示）
            node.setRect(new Rectangle2D.Double(x, y, width, height));
        }
        if (node.hasChildren()) {// 递归计算子节点布局
            calculateLayoutImproved(node.getChildren(), x, y, width, height);
        }
    }

    /**
     * 改进的树形图布局算法（切片切丁算法）
     */
    private void calculateLayoutImproved(List<JTreemapNode> nodes, double x, double y, double width, double height) {
        if (nodes == null || nodes.isEmpty()) return;
        double total = nodes.stream().mapToDouble(JTreemapNode::getValue).sum();
        if (total <= 0) return;
        nodes.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));// 按值排序以获得更好的视觉效果
        boolean vertical = width < height; // 根据宽高比决定分割方向
        double current = vertical ? y : x;
        double remaining = vertical ? height : width;
        for (int i = 0; i < nodes.size(); i++) {
            JTreemapNode node = nodes.get(i);
            double ratio = node.getValue() / total;
            double size = remaining * ratio;
            Rectangle2D rect;
            if (vertical) {
                rect = new Rectangle2D.Double(x, current, width, size);
            } else {
                rect = new Rectangle2D.Double(current, y, size, height);
            }
            node.setRect(rect);

            if (node.hasChildren()) { // 递归处理子节点 - 修复：正确处理所有层级的子节点
                // 为子节点预留一些边距
                double childMargin = 1.0; // 1像素边距，避免重叠
                double childX = rect.getX() + childMargin;
                double childY = rect.getY() + childMargin;
                double childWidth = rect.getWidth() - 2 * childMargin;
                double childHeight = rect.getHeight() - 2 * childMargin;
                if (childWidth > 0 && childHeight > 0) {
                    calculateLayoutImproved(node.getChildren(), childX, childY, childWidth, childHeight);
                }
            }
            current += size;
            remaining -= size;
        }
    }

    /**
     * 绘制树形图
     */
    private void drawTreemap(SVGGraphics2D svgGenerator, JTreemapNode node, TreemapOption option) {
        if (node.getRect() == null) return;
        if (node.getName().equals("公司业务")) {
            // 只绘制子节点
            if (node.hasChildren()) {
                for (JTreemapNode child : node.getChildren()) {
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
        if (node.hasChildren()) {// 递归绘制子节点
            for (JTreemapNode child : node.getChildren()) {
                drawTreemap(svgGenerator, child, option);
            }
        }
    }

    /**
     * 获取节点颜色
     */
    private Color getNodeColor(JTreemapNode node, TreemapOption option) {
        if (node.getColor() != null) {// 优先使用节点自身配置的颜色
            return node.getColor();
        }

        Map<String, Color> categoryColors = option.getCategoryColors();
        if (categoryColors.containsKey(node.getName())) {// 使用分类颜色映射
            return categoryColors.get(node.getName());
        }

        Map<String, Color> departmentColors = option.getDepartmentColors();
        if (departmentColors.containsKey(node.getName())) {// 使用部门颜色映射
            return departmentColors.get(node.getName());
        }
        return generateSimilarColor(node, option);// 自动生成相似颜色
    }

    /**
     * 生成相似颜色
     */
    private Color generateSimilarColor(JTreemapNode node, TreemapOption option) {
        String department = inferDepartment(node.getName(), option);
        Map<String, Color> departmentColors = option.getDepartmentColors();
        if (department != null && departmentColors.containsKey(department)) {
            Color baseColor = departmentColors.get(department);
            return adjustColorBrightness(baseColor, node.getName().hashCode() % option.getColorDeltaRange() - (option.getColorDeltaRange() / 2)
            );
        }
        if (!departmentColors.isEmpty()) {// 默认使用第一个部门的颜色
            Color defaultBase = departmentColors.values().iterator().next();
            return adjustColorBrightness(defaultBase, node.getName().hashCode() % 30 - 15);
        }
        return Color.LIGHT_GRAY;
    }

    /**
     * 推断部门
     */
    private String inferDepartment(String categoryName, TreemapOption option) {
        List<TreemapDepartmentRule> rules = option.getDepartmentRules();
        for (TreemapDepartmentRule rule : rules) {
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
    private void drawNodeLabel(SVGGraphics2D svgGenerator, JTreemapNode node, Color bgColor, TreemapOption option) {
        Rectangle2D rect = node.getRect();
        if (rect.getWidth() < option.getMinLabelWidth() || rect.getHeight() < option.getMinLabelHeight()) {
            return; // 矩形太小不绘制标签
        }
        Color textColor = getContrastColor(bgColor);// 计算文本颜色（与背景色对比）
        svgGenerator.setColor(textColor);
        svgGenerator.setFont(new Font(option.getFontFamily(), Font.PLAIN, option.getFontSize()));
        String label = node.getName();
        if (rect.getWidth() > option.getMinValueLabelWidth() && rect.getHeight() > option.getMinValueLabelHeight()) {
            label += " (" + node.getValue() + ")";
        }// 决定是否显示数值
        int textWidth = svgGenerator.getFontMetrics().stringWidth(label);// 文本过长时截断
        if (textWidth > rect.getWidth() - 10 && label.length() > option.getMaxLabelLength()) {
            label = label.substring(0, option.getMaxLabelLength() - 3) + "...";
            textWidth = svgGenerator.getFontMetrics().stringWidth(label);
        }
        // 居中绘制文本
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
    private void addLegend(SVGGraphics2D svgGenerator, int width, int height, TreemapOption option) {
        int startX = option.getMarginLeft();
        int startY = height - option.getMarginBottom() + 10;
        int boxSize = option.getLegendBoxSize();
        svgGenerator.setFont(new Font(option.getFontFamily(), Font.PLAIN, option.getLegendFontSize()));
        // 绘制部门图例
        for (Map.Entry<String, Color> entry : option.getDepartmentColors().entrySet()) {
            // 绘制颜色块
            svgGenerator.setColor(entry.getValue());
            svgGenerator.fillRect(startX, startY, boxSize, boxSize);
            svgGenerator.setColor(Color.BLACK);// 绘制边框
            svgGenerator.drawRect(startX, startY, boxSize, boxSize);
            svgGenerator.drawString(entry.getKey(), startX + boxSize + 5, startY + boxSize - 3);
            startX += option.getLegendItemWidth();
        }
        if (option.getLegendNote() != null) {// 绘制图例说明
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

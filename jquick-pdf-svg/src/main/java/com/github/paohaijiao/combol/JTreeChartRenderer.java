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
package com.github.paohaijiao.combol;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 树形结构图渲染器 - 简化可靠版本
 * 使用层级水平布局，避免节点重叠
 */
@Data
public class JTreeChartRenderer extends JAbstractChartRenderer {

    private LayoutParams layoutParams;

    private JTreeChartData config;
    // 存储每层的节点列表
    private Map<Integer, List<JTreeChartData.TreeNode>> levelNodesMap;

    public JTreeChartRenderer() {
        this.layoutParams = new LayoutParams();
        this.levelNodesMap = new LinkedHashMap<>();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 1000;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 800;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JTreeChartData config = (JTreeChartData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getRootNode(), "root node require not null");
        this.config = config;
        updateConfigDimensions(width, height);
        this.levelNodesMap.clear(); // 重置数据
        calculateLayout();// 计算布局
        // 开启抗锯齿
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);// 绘制背景
        drawAllConnections(svgGenerator, config.getRootNode());// 绘制连接线
        drawAllNodes(svgGenerator, config.getRootNode());// 绘制所有节点
        drawTitle(svgGenerator, option, width);// 绘制标题
        drawLegendAndFooter(svgGenerator); // 绘制图例和底部说明
    }

    /**
     * 更新配置中的宽高
     */
    private void updateConfigDimensions(int width, int height) {
        this.config.setWidth(width);
        this.config.setHeight(height);
    }
    /**
     * 计算布局 - 优化版本，动态调整边距和间距
     */
    private void calculateLayout() {
        int width = config.getWidth();
        int height = config.getHeight();
        collectNodesByLevel(config.getRootNode(), 0);// 收集所有节点按层级分组
        Map<Integer, Integer> levelNodeCount = new HashMap<>();// 计算各层节点数量
        for (Map.Entry<Integer, List<JTreeChartData.TreeNode>> entry : levelNodesMap.entrySet()) {
            levelNodeCount.put(entry.getKey(), entry.getValue().size());
        }
        int maxDepth = levelNodesMap.size() - 1;
        int titleHeight = calculateTitleHeight();// 计算标题区域高度
        // 图例和底部区域高度
        int legendHeight = (config.getLegendList() != null && !config.getLegendList().isEmpty()) ? 50 : 0;
        int footerHeight = (config.getFooterText() != null && !config.getFooterText().isEmpty()) ? 35 : 0;
        int bottomMargin = legendHeight + footerHeight + 30;
        // 可用绘图区域
        int drawableTop = titleHeight + 20;
        // 垂直间距
        int verticalSpacing = config.getVerticalSpacing();
        // 计算每层Y坐标
        Map<Integer, Integer> levelYMap = new HashMap<>();
        int currentY = drawableTop;
        for (int depth = 0; depth <= maxDepth; depth++) {
            int nodeHeight = getNodeHeightByDepth(depth);
            levelYMap.put(depth, currentY + nodeHeight / 2);
            currentY += nodeHeight + verticalSpacing;
        }
        // 计算每层节点的X坐标
        for (Map.Entry<Integer, List<JTreeChartData.TreeNode>> entry : levelNodesMap.entrySet()) {
            int depth = entry.getKey();
            List<JTreeChartData.TreeNode> nodes = entry.getValue();
            int nodeCount = nodes.size();
            int nodeWidth = getNodeWidthByDepth(depth);
            int minMargin = Math.max(20, Math.min(80, width / 20));// 动态计算最小边距（根据节点数量调整）
            // 计算节点所需的最小总宽度
            int minTotalWidth = nodeCount * nodeWidth + (nodeCount - 1) * 20; // 最小间距20
            // 可用宽度
            int availableWidthForNodes = width - 2 * minMargin;
            int actualNodeWidth = nodeWidth;
            int actualSpacing = 20;
            if (nodeCount == 1) {// 只有一个节点时，直接居中
                actualNodeWidth = nodeWidth;
                actualSpacing = 0;
            } else if (minTotalWidth > availableWidthForNodes) {// 需要缩小节点宽度
                actualNodeWidth = (availableWidthForNodes - (nodeCount - 1) * 20) / nodeCount;
                actualNodeWidth = Math.max(70, actualNodeWidth); // 最小宽度70
                actualSpacing = 20;
            } else {
                // 计算合适的间距，使布局美观
                // 安全计算间距，避免除以零
                actualSpacing = (availableWidthForNodes - nodeCount * nodeWidth) / (nodeCount - 1);
                actualSpacing = Math.max(20, Math.min(300, actualSpacing));// 放宽间距范围，支持更大的间距
            }
            int actualTotalWidth;// 重新计算实际总宽度
            if (nodeCount == 1) {
                actualTotalWidth = actualNodeWidth;
            } else {
                actualTotalWidth = nodeCount * actualNodeWidth + (nodeCount - 1) * actualSpacing;
            }
            int startX = (width - actualTotalWidth) / 2;// 计算起始X位置，使节点组居中，使用动态边距
            startX = Math.max(10, startX);// 确保不会超出边界
            int endX = startX + actualTotalWidth;// 检查是否会超出右边界
            if (endX > width - 10) {
                startX = width - actualTotalWidth - 10;
            }
            for (int i = 0; i < nodeCount; i++) {// 分配X坐标
                JTreeChartData.TreeNode node = nodes.get(i);
                int nodeX;
                if (nodeCount == 1) {
                    nodeX = startX + actualNodeWidth / 2;
                } else {
                    nodeX = startX + i * (actualNodeWidth + actualSpacing) + actualNodeWidth / 2;
                }
                int nodeY = levelYMap.get(depth);
                node.setX(nodeX);
                node.setY(nodeY);
                node.setWidth(actualNodeWidth);
                node.setHeight(getNodeHeightByDepth(depth));
                node.setDepth(depth);
            }
        }

        // 保存布局参数
        layoutParams.topMargin = titleHeight;
        layoutParams.bottomMargin = bottomMargin;
        layoutParams.legendY = height - footerHeight - legendHeight + 20;
        layoutParams.footerY = height - 18;
        layoutParams.levelYMap = levelYMap;
    }

    /**
     * 递归收集所有节点，按层级分组
     */
    private void collectNodesByLevel(JTreeChartData.TreeNode node, int depth) {
        if (node == null) return;
        levelNodesMap.computeIfAbsent(depth, k -> new ArrayList<>()).add(node);// 添加到对应层级
        if (node.getChildren() != null) {// 递归处理子节点
            for (JTreeChartData.TreeNode child : node.getChildren()) {
                collectNodesByLevel(child, depth + 1);
            }
        }
    }

    /**
     * 计算标题区域高度
     */
    private int calculateTitleHeight() {
        int titleHeight = 0;
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 45;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 28;
            }
        } else {
            titleHeight = 20;
        }
        return titleHeight;
    }

    /**
     * 根据深度获取节点宽度
     */
    private int getNodeWidthByDepth(int depth) {
        if (depth == 0) return config.getRootNodeWidth();
        if (depth == 1) return config.getLevelNodeWidth();
        if (depth == 2) return config.getLeafNodeWidth();
        return Math.max(80, config.getLeafNodeWidth() - (depth - 2) * 8);// 更深层级的节点逐渐变小
    }

    /**
     * 根据深度获取节点高度
     */
    private int getNodeHeightByDepth(int depth) {
        if (depth == 0) return config.getRootNodeHeight();
        if (depth == 1) return config.getLevelNodeHeight();
        if (depth == 2) return config.getLeafNodeHeight();
        return Math.max(38, config.getLeafNodeHeight() - (depth - 2) * 4);
    }

    /**
     * 绘制图表背景
     */
    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getBackgroundColor() != null ? config.getBackgroundColor() : new Color(240, 244, 248));
        svg.fillRoundRect(0, 0, config.getWidth(), config.getHeight(), 12, 12);
        svg.setPaint(new Color(26, 82, 118));       // 顶部装饰线
        svg.fillRoundRect(0, 0, config.getWidth(), 4, 2, 2);
    }

    /**
     * 绘制所有连接线
     */
    private void drawAllConnections(SVGGraphics2D svg, JTreeChartData.TreeNode node) {
        if (node == null) return;
        List<JTreeChartData.TreeNode> children = node.getChildren();
        if (children != null && !children.isEmpty()) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            int nodeHeight = node.getHeight();
            svg.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            svg.setPaint(new Color(155, 183, 212));
            for (JTreeChartData.TreeNode child : children) {
                int childX = child.getX();
                int childY = child.getY();
                int childHeight = child.getHeight();
                int startX = nodeX;// 起点：父节点底部中心
                int startY = nodeY + nodeHeight / 2;
                int endX = childX;// 终点：子节点顶部中心
                int endY = childY - childHeight / 2;

                int midY = (startY + endY) / 2;// 绘制折线
                svg.drawLine(startX, startY, startX, midY);
                svg.drawLine(startX, midY, endX, midY);
                svg.drawLine(endX, midY, endX, endY);
            }
            // 递归
            for (JTreeChartData.TreeNode child : children) {
                drawAllConnections(svg, child);
            }
        }
    }

    /**
     * 绘制所有节点
     */
    private void drawAllNodes(SVGGraphics2D svg, JTreeChartData.TreeNode node) {
        if (node == null) return;
        drawNode(svg, node, node.getDepth());
        if (node.getChildren() != null) {
            for (JTreeChartData.TreeNode child : node.getChildren()) {
                drawAllNodes(svg, child);
            }
        }
    }

    /**
     * 绘制单个节点
     */
    private void drawNode(SVGGraphics2D svg, JTreeChartData.TreeNode node, int depth) {
        int x = node.getX() - node.getWidth() / 2;
        int y = node.getY() - node.getHeight() / 2;
        int width = node.getWidth();
        int height = node.getHeight();
        int arc = 8;
        svg.setPaint(new Color(0, 0, 0, 30));// 绘制阴影
        svg.fillRoundRect(x + 2, y + 2, width, height, arc, arc);
        // 绘制节点背景
        if (depth == 0) {
            GradientPaint gradient = new GradientPaint(x, y, new Color(26, 82, 118), x + width, y + height, new Color(27, 94, 122));
            svg.setPaint(gradient);
        } else if (depth == 1) {
            GradientPaint gradient = new GradientPaint(x, y, new Color(44, 127, 184), x + width, y + height, new Color(37, 128, 176));
            svg.setPaint(gradient);
        } else {
            svg.setPaint(Color.WHITE);
        }
        svg.fillRoundRect(x, y, width, height, arc, arc);
        svg.setStroke(new BasicStroke(1.5f)); // 绘制边框
        svg.setPaint(depth == 0 ? new Color(26, 82, 118) : new Color(44, 127, 184));
        svg.drawRoundRect(x, y, width, height, arc, arc);

        svg.setFont(getNodeFont(depth));// 绘制文字
        Color textColor = (depth <= 1) ? Color.WHITE : new Color(30, 70, 104);
        svg.setPaint(textColor);
        String title = node.getTitle();
        if (title != null) {
            FontMetrics fm = svg.getFontMetrics();
            int titleY = y + height / 2 - (hasSubtitle(node) ? 8 : 4);
            // 文字截断处理
            String displayTitle = truncateText(title, width, fm);
            svg.drawString(displayTitle, x + width / 2 - fm.stringWidth(displayTitle) / 2, titleY + fm.getHeight() / 3);
        }
        // 副标题
        if (node.getSubtitle() != null && !node.getSubtitle().isEmpty()) {
            svg.setFont(getNodeSubFont(depth));
            svg.setPaint(depth <= 1 ? new Color(220, 235, 250) : new Color(90, 125, 154));
            String subtitle = node.getSubtitle();
            FontMetrics fm = svg.getFontMetrics();
            String displaySubtitle = truncateText(subtitle, width - 20, fm);
            svg.drawString(displaySubtitle, x + width / 2 - fm.stringWidth(displaySubtitle) / 2, y + height / 2 + 12);
        }
    }

    /**
     * 判断是否有副标题
     */
    private boolean hasSubtitle(JTreeChartData.TreeNode node) {
        return node.getSubtitle() != null && !node.getSubtitle().isEmpty();
    }

    /**
     * 文字截断
     */
    private String truncateText(String text, int maxWidth, FontMetrics fm) {
        if (fm.stringWidth(text) <= maxWidth) return text;
        for (int i = text.length(); i > 0; i--) {
            String sub = text.substring(0, i) + "...";
            if (fm.stringWidth(sub) <= maxWidth) {
                return sub;
            }
        }
        return text.substring(0, Math.min(3, text.length())) + "...";
    }

    /**
     * 获取节点主字体
     */
    private Font getNodeFont(int depth) {
        if (depth == 0) return new Font("Microsoft YaHei", Font.BOLD, 14);
        if (depth == 1) return new Font("Microsoft YaHei", Font.BOLD, 12);
        return new Font("Microsoft YaHei", Font.PLAIN, 11);
    }

    /**
     * 获取节点副字体
     */
    private Font getNodeSubFont(int depth) {
        if (depth == 0) return new Font("Microsoft YaHei", Font.PLAIN, 10);
        if (depth == 1) return new Font("Microsoft YaHei", Font.PLAIN, 9);
        return new Font("Microsoft YaHei", Font.PLAIN, 9);
    }

    /**
     * 绘制图例和底部说明
     */
    private void drawLegendAndFooter(SVGGraphics2D svg) {
        if (config.getLegendList() != null && !config.getLegendList().isEmpty()) {
            drawLegend(svg);
        }
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            svg.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
            svg.setPaint(new Color(139, 160, 184));
            String footer = config.getFooterText();
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(footer, config.getWidth() / 2 - fm.stringWidth(footer) / 2, layoutParams.footerY);
        }
        // 底部装饰线
        svg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3, 3}, 0));
        svg.setPaint(new Color(197, 213, 232));
        svg.drawLine(100, layoutParams.footerY - 25, config.getWidth() - 100, layoutParams.footerY - 25);
        svg.setStroke(new BasicStroke(1));
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        List<JTreeChartData.LegendItem> legends = config.getLegendList();
        int legendItemWidth = 120;
        int totalWidth = legends.size() * legendItemWidth;
        int startX = Math.max(20, (config.getWidth() - totalWidth) / 2);
        int rectSize = 16;
        int rectRx = 3;
        for (int i = 0; i < legends.size(); i++) {
            JTreeChartData.LegendItem item = legends.get(i);
            int legendX = startX + i * legendItemWidth;
            svg.setPaint(item.getColor());
            svg.fillRoundRect(legendX, layoutParams.legendY - rectSize, rectSize, rectSize, rectRx, rectRx);
            svg.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
            svg.setPaint(new Color(74, 98, 122));
            svg.drawString(item.getText(), legendX + rectSize + 8, layoutParams.legendY);
        }
    }

    @Override
    protected void drawTitle(SVGGraphics2D svgGenerator, JOption option, int width) {
        String title = config.getTitleText();
        String subtitle = config.getSubtitleText();
        if (title != null && !title.isEmpty()) {
            svgGenerator.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
            svgGenerator.setPaint(new Color(26, 82, 118));
            FontMetrics fm = svgGenerator.getFontMetrics();
            svgGenerator.drawString(title, width / 2 - fm.stringWidth(title) / 2, 35);
            if (subtitle != null && !subtitle.isEmpty()) {
                svgGenerator.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
                svgGenerator.setPaint(new Color(120, 140, 166));
                fm = svgGenerator.getFontMetrics();
                svgGenerator.drawString(subtitle, width / 2 - fm.stringWidth(subtitle) / 2, 60);
            }
        }
    }

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {

        int topMargin, bottomMargin;

        int legendY, footerY;

        Map<Integer, Integer> levelYMap;

    }
}
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
import com.github.paohaijiao.graph.JGraphData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.*;
import java.util.List;

/**
 * 网络拓扑图渲染器 - 支持节点和连线的网络拓扑展示
 * 支持自动布局和手动布局两种模式
 */
@Data
public class JNetworkTopologyRenderer extends JAbstractChartRenderer {

    private final LayoutParams layoutParams;

    private JNetworkTopologyData config;

    private Map<String, Point> nodePositions;

    public JNetworkTopologyRenderer() {
        this.layoutParams = new LayoutParams();
        this.nodePositions = new HashMap<>();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 900;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 700;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JNetworkTopologyData config = (JNetworkTopologyData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getNodes(), "nodes list require not null");
        JAssert.notNull(config.getLinks(), "links list require not null");
        this.config = config;
        updateConfigDimensions(width, height);
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);
        drawAllLinks(svgGenerator);
        drawAllNodes(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
        drawLegend(svgGenerator);
    }

    /**
     * 更新配置中的宽高
     */
    private void updateConfigDimensions(int width, int height) {
        this.config.setWidth(width);
        this.config.setHeight(height);
    }

    /**
     * 计算布局参数
     */
    private void calculateLayout() {
        int width = config.getWidth();
        int height = config.getHeight();
        // 计算标题占用的高度
        int titleHeight = 0;
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 40;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 25;
            }
        } else {
            titleHeight = 20;
        }
        // 图例和底部说明占用的高度
        int legendHeight = config.getNodes().size() <= 4 ? 50 : 70;
        int footerHeight = config.getFooterText() != null && !config.getFooterText().isEmpty() ? 30 : 10;
        // 计算可用图表区域
        int topMargin = titleHeight;
        int bottomMargin = legendHeight + footerHeight;
        int leftMargin = 50;
        int rightMargin = 50;

        int chartTop = topMargin;
        int chartBottom = height - bottomMargin;
        int chartLeft = leftMargin;
        int chartRight = width - rightMargin;
        int chartWidth = chartRight - chartLeft;
        int chartHeight = chartBottom - chartTop;

        // 保存布局参数
        layoutParams.chartTop = chartTop;
        layoutParams.chartBottom = chartBottom;
        layoutParams.chartLeft = chartLeft;
        layoutParams.chartRight = chartRight;
        layoutParams.chartWidth = chartWidth;
        layoutParams.chartHeight = chartHeight;
        layoutParams.topMargin = topMargin;
        layoutParams.bottomMargin = bottomMargin;
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
        layoutParams.legendY = chartBottom + 25;
        layoutParams.footerY = height - 12;

        // 计算节点位置
        calculateNodePositions(chartLeft, chartTop, chartWidth, chartHeight);
    }

    /**
     * 计算节点位置
     */
    private void calculateNodePositions(int left, int top, int width, int height) {
        List<JNetworkTopologyData.Node> nodes = config.getNodes();
        nodePositions.clear();
        if (config.isAutoLayout()) {           // 自动布局 - 使用力导向布局算法
            calculateForceDirectedLayout(left, top, width, height);
        } else {// 手动布局 - 使用节点中指定的坐标
            for (JNetworkTopologyData.Node node : nodes) {
                if (node.getX() != null && node.getY() != null) {
                    nodePositions.put(node.getId(), new Point(node.getX(), node.getY()));
                } else {// 如果没有指定坐标，使用默认布局
                    calculateDefaultLayout(left, top, width, height);
                    break;
                }
            }
        }
    }

    /**
     * 力导向布局算法
     */
    private void calculateForceDirectedLayout(int left, int top, int width, int height) {
        List<JNetworkTopologyData.Node> nodes = config.getNodes();
        int nodeCount = nodes.size();
        if (nodeCount == 0) return;
        Random random = new Random(config.getLayoutSeed());// 初始化随机位置
        Map<String, Point> positions = new HashMap<>();
        for (JNetworkTopologyData.Node node : nodes) {
            int x = left + 50 + random.nextInt(width - 100);
            int y = top + 50 + random.nextInt(height - 100);
            positions.put(node.getId(), new Point(x, y));
        }
        // 力导向布局迭代
        double k = Math.sqrt(width * height / nodeCount); // 最优距离
        double iterations = config.getLayoutIterations();
        for (int iter = 0; iter < iterations; iter++) {
            Map<String, Point> newPositions = new HashMap<>();
            for (JNetworkTopologyData.Node nodeA : nodes) {
                double forceX = 0;
                double forceY = 0;
                Point posA = positions.get(nodeA.getId());
                // 计算斥力（所有节点之间）
                for (JNetworkTopologyData.Node nodeB : nodes) {
                    if (nodeA.getId().equals(nodeB.getId())) continue;
                    Point posB = positions.get(nodeB.getId());
                    double dx = posA.x - posB.x;
                    double dy = posA.y - posB.y;
                    double distance = Math.max(Math.hypot(dx, dy), 1);
                    double repulsion = k * k / distance;
                    forceX += repulsion * (dx / distance);
                    forceY += repulsion * (dy / distance);
                }
                // 计算引力（有连线的节点之间）
                for (JNetworkTopologyData.Link link : config.getLinks()) {
                    if (link.getSourceId().equals(nodeA.getId())) {
                        Point posB = positions.get(link.getTargetId());
                        if (posB != null) {
                            double dx = posB.x - posA.x;
                            double dy = posB.y - posA.y;
                            double distance = Math.max(Math.hypot(dx, dy), 1);
                            double attraction = distance * distance / k;
                            forceX += attraction * (dx / distance);
                            forceY += attraction * (dy / distance);
                        }
                    } else if (link.getTargetId().equals(nodeA.getId())) {
                        Point posB = positions.get(link.getSourceId());
                        if (posB != null) {
                            double dx = posB.x - posA.x;
                            double dy = posB.y - posA.y;
                            double distance = Math.max(Math.hypot(dx, dy), 1);
                            double attraction = distance * distance / k;
                            forceX += attraction * (dx / distance);
                            forceY += attraction * (dy / distance);
                        }
                    }
                }

                // 更新位置
                double damping = 0.85;
                double newX = posA.x + forceX * damping;
                double newY = posA.y + forceY * damping;
                newX = Math.max(left + 30, Math.min(left + width - 30, newX));// 边界约束
                newY = Math.max(top + 30, Math.min(top + height - 30, newY));
                newPositions.put(nodeA.getId(), new Point((int) newX, (int) newY));
            }
            positions = newPositions;
        }

        nodePositions.putAll(positions);
    }

    /**
     * 默认布局 - 圆形布局
     */
    private void calculateDefaultLayout(int left, int top, int width, int height) {
        List<JNetworkTopologyData.Node> nodes = config.getNodes();
        int nodeCount = nodes.size();
        if (nodeCount == 0) return;
        int centerX = left + width / 2;
        int centerY = top + height / 2;
        int radius = Math.min(width, height) / 2 - 50;
        for (int i = 0; i < nodeCount; i++) {
            JNetworkTopologyData.Node node = nodes.get(i);
            double angle = 2 * Math.PI * i / nodeCount;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));
            nodePositions.put(node.getId(), new Point(x, y));
        }
    }

    /**
     * 绘制图表背景
     */
    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getBackgroundColor());
        svg.fillRect(0, 0, config.getWidth(), config.getHeight());
        if (config.isShowGrid()) {// 绘制网格背景
            svg.setPaint(config.getGridColor());
            svg.setStroke(new BasicStroke(0.5f));
            int gridSize = config.getGridSize();
            for (int x = layoutParams.chartLeft; x <= layoutParams.chartRight; x += gridSize) {
                svg.drawLine(x, layoutParams.chartTop, x, layoutParams.chartBottom);
            }
            for (int y = layoutParams.chartTop; y <= layoutParams.chartBottom; y += gridSize) {
                svg.drawLine(layoutParams.chartLeft, y, layoutParams.chartRight, y);
            }
        }
    }

    /**
     * 绘制所有连线
     */
    private void drawAllLinks(SVGGraphics2D svg) {
        for (JNetworkTopologyData.Link link : config.getLinks()) {
            Point sourcePos = nodePositions.get(link.getSourceId());
            Point targetPos = nodePositions.get(link.getTargetId());

            if (sourcePos != null && targetPos != null) {
                drawSingleLink(svg, link, sourcePos, targetPos);
            }
        }
    }

    /**
     * 绘制单条连线
     */
    private void drawSingleLink(SVGGraphics2D svg, JNetworkTopologyData.Link link, Point source, Point target) {
        // 设置连线样式
        svg.setStroke(new BasicStroke(link.getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.setPaint(link.getLineColor());
        // 计算连线路径
        Path2D path = new Path2D.Double();
        if (link.isCurved() && config.isCurvedLinks()) {
            // 曲线连线
            int dx = target.x - source.x;
            int dy = target.y - source.y;
            int ctrlX = source.x + dx / 2 + dy / 3;
            int ctrlY = source.y + dy / 2 - dx / 3;
            path.moveTo(source.x, source.y);
            path.curveTo(ctrlX, ctrlY, ctrlX, ctrlY, target.x, target.y);
        } else {
            // 直线连线
            path.moveTo(source.x, source.y);
            path.lineTo(target.x, target.y);
        }
        svg.draw(path);
        // 绘制箭头（如果启用）
        if (link.isShowArrow() || config.isShowArrows()) {
            drawArrow(svg, source, target, link);
        }
        // 绘制连线标签
        if (link.getLabel() != null && !link.getLabel().isEmpty() && config.isShowLinkLabels()) {
            drawLinkLabel(svg, link, source, target);
        }
        // 绘制连线上的数据流动画点（如果启用）
        if (config.isShowDataFlow() && link.isActive()) {
            drawDataFlowPoint(svg, source, target, link);
        }
    }

    /**
     * 绘制箭头
     */
    private void drawArrow(SVGGraphics2D svg, Point source, Point target, JNetworkTopologyData.Link link) {
        double angle = Math.atan2(target.y - source.y, target.x - source.x);
        int arrowX = target.x;
        int arrowY = target.y;
        // 计算箭头位置（考虑节点半径）
        JNetworkTopologyData.Node targetNode = findNodeById(link.getTargetId());
        if (targetNode != null) {
            int nodeRadius = targetNode.getRadius() != null ? targetNode.getRadius() : config.getDefaultNodeRadius();
            arrowX = target.x - (int) (nodeRadius * Math.cos(angle));
            arrowY = target.y - (int) (nodeRadius * Math.sin(angle));
        }

        int arrowSize = config.getArrowSize();
        Path2D arrow = new Path2D.Double();
        arrow.moveTo(arrowX, arrowY);
        arrow.lineTo(arrowX - arrowSize * Math.cos(angle - Math.PI / 6), arrowY - arrowSize * Math.sin(angle - Math.PI / 6));
        arrow.lineTo(arrowX - arrowSize * Math.cos(angle + Math.PI / 6), arrowY - arrowSize * Math.sin(angle + Math.PI / 6));
        arrow.closePath();
        svg.fill(arrow);
    }

    /**
     * 绘制连线标签
     */
    private void drawLinkLabel(SVGGraphics2D svg, JNetworkTopologyData.Link link, Point source, Point target) {
        int midX = (source.x + target.x) / 2;
        int midY = (source.y + target.y) / 2;
        svg.setFont(config.getLinkLabelFont());
        svg.setPaint(link.getLabelColor() != null ? link.getLabelColor() : config.getTextColor());
        FontMetrics fm = svg.getFontMetrics();
        int labelX = midX - fm.stringWidth(link.getLabel()) / 2;
        int labelY = midY - 5;
        // 绘制标签背景
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2,
                    fm.stringWidth(link.getLabel()) + 6, fm.getHeight());
        }
        svg.setPaint(link.getLabelColor() != null ? link.getLabelColor() : config.getTextColor());
        svg.drawString(link.getLabel(), labelX, labelY);
    }

    /**
     * 绘制数据流动画点
     */
    private void drawDataFlowPoint(SVGGraphics2D svg, Point source, Point target, JNetworkTopologyData.Link link) {
        // 使用正弦波模拟动画效果
        long time = System.currentTimeMillis();
        double progress = (time % config.getFlowAnimationDuration()) / (double) config.getFlowAnimationDuration();
        int x = (int) (source.x + (target.x - source.x) * progress);
        int y = (int) (source.y + (target.y - source.y) * progress);
        svg.setPaint(link.getFlowColor() != null ? link.getFlowColor() : link.getLineColor());
        int flowPointSize = config.getFlowPointSize();
        svg.fillOval(x - flowPointSize / 2, y - flowPointSize / 2, flowPointSize, flowPointSize);
    }

    /**
     * 绘制所有节点
     */
    private void drawAllNodes(SVGGraphics2D svg) {
        for (JNetworkTopologyData.Node node : config.getNodes()) {
            Point pos = nodePositions.get(node.getId());
            if (pos != null) {
                drawSingleNode(svg, node, pos);
            }
        }
    }

    /**
     * 绘制单个节点
     */
    private void drawSingleNode(SVGGraphics2D svg, JNetworkTopologyData.Node node, Point pos) {
        int radius = node.getRadius() != null ? node.getRadius() : config.getDefaultNodeRadius();
        Color nodeColor = node.getColor() != null ? node.getColor() : config.getDefaultNodeColor();
        Color borderColor = node.getBorderColor() != null ? node.getBorderColor() : config.getDefaultBorderColor();
        // 绘制阴影效果
        if (config.isShowShadow()) {
            svg.setPaint(new Color(0, 0, 0, 50));
            svg.fillOval(pos.x - radius + 3, pos.y - radius + 3, radius * 2, radius * 2);
        }
        // 绘制节点形状
        svg.setPaint(nodeColor);
        JNetworkTopologyData.NodeShape shape = node.getShape() != null ? node.getShape() : config.getDefaultShape();
        switch (shape) {
            case RECTANGLE:
                svg.fillRect(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
                break;
            case TRIANGLE:
                drawTriangle(svg, pos, radius);
                break;
            case DIAMOND:
                drawDiamond(svg, pos, radius);
                break;
            case CIRCLE:
            default:
                svg.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
                break;
        }
        // 绘制节点边框
        svg.setPaint(borderColor);
        svg.setStroke(new BasicStroke(config.getBorderWidth()));
        switch (shape) {
            case RECTANGLE:
                svg.drawRect(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
                break;
            case TRIANGLE:
                drawTriangle(svg, pos, radius);
                break;
            case DIAMOND:
                drawDiamond(svg, pos, radius);
                break;
            case CIRCLE:
            default:
                svg.drawOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
                break;
        }
        // 绘制节点内部图标（简单实现）
        if (node.getIcon() != null && config.isShowIcons()) {
            drawNodeIcon(svg, node, pos, radius);
        }
        // 绘制节点标签
        if (node.getLabel() != null && !node.getLabel().isEmpty()) {
            drawNodeLabel(svg, node, pos, radius);
        }
        // 绘制节点状态指示器
        if (node.getStatus() != null && config.isShowStatus()) {
            drawNodeStatus(svg, node, pos, radius);
        }
    }

    /**
     * 绘制三角形
     */
    private void drawTriangle(SVGGraphics2D svg, Point pos, int radius) {
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(pos.x, pos.y - radius);
        triangle.lineTo(pos.x - radius, pos.y + radius);
        triangle.lineTo(pos.x + radius, pos.y + radius);
        triangle.closePath();
        svg.fill(triangle);
        svg.draw(triangle);
    }

    /**
     * 绘制菱形
     */
    private void drawDiamond(SVGGraphics2D svg, Point pos, int radius) {
        Path2D diamond = new Path2D.Double();
        diamond.moveTo(pos.x, pos.y - radius);
        diamond.lineTo(pos.x + radius, pos.y);
        diamond.lineTo(pos.x, pos.y + radius);
        diamond.lineTo(pos.x - radius, pos.y);
        diamond.closePath();
        svg.fill(diamond);
        svg.draw(diamond);
    }

    /**
     * 绘制节点图标
     */
    private void drawNodeIcon(SVGGraphics2D svg, JNetworkTopologyData.Node node, Point pos, int radius) {
        svg.setPaint(Color.WHITE);
        String icon = node.getIcon();
        svg.setFont(new Font("Segoe UI Emoji", Font.PLAIN, radius));
        FontMetrics fm = svg.getFontMetrics();
        svg.drawString(icon, pos.x - fm.charWidth(icon.charAt(0)) / 2, pos.y + radius / 2);
    }

    /**
     * 绘制节点标签
     */
    private void drawNodeLabel(SVGGraphics2D svg, JNetworkTopologyData.Node node, Point pos, int radius) {
        svg.setFont(config.getNodeLabelFont());
        svg.setPaint(config.getTextColor());
        String label = node.getLabel();
        FontMetrics fm = svg.getFontMetrics();
        int labelX = pos.x - fm.stringWidth(label) / 2;
        int labelY = pos.y + radius + 15;
        // 标签位置调整
        if (labelY > layoutParams.chartBottom - 5) {
            labelY = pos.y - radius - 5;
        }
        // 绘制标签背景
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2,
                    fm.stringWidth(label) + 6, fm.getHeight());
            svg.setPaint(config.getTextColor());
        }
        svg.drawString(label, labelX, labelY);
    }

    /**
     * 绘制节点状态指示器
     */
    private void drawNodeStatus(SVGGraphics2D svg, JNetworkTopologyData.Node node, Point pos, int radius) {
        Color statusColor;
        switch (node.getStatus().toLowerCase()) {
            case "active":
            case "online":
            case "running":
                statusColor = Color.GREEN;
                break;
            case "warning":
            case "degraded":
                statusColor = Color.ORANGE;
                break;
            case "error":
            case "offline":
            case "down":
                statusColor = Color.RED;
                break;
            default:
                statusColor = Color.GRAY;
        }

        svg.setPaint(statusColor);
        int statusRadius = config.getStatusRadius();
        svg.fillOval(pos.x + radius - statusRadius, pos.y - radius - statusRadius, statusRadius * 2, statusRadius * 2);
        // 状态外圈
        svg.setPaint(Color.WHITE);
        svg.setStroke(new BasicStroke(1.5f));
        svg.drawOval(pos.x + radius - statusRadius, pos.y - radius - statusRadius, statusRadius * 2, statusRadius * 2);
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        if (!config.isShowLegend()) return;
        int width = config.getWidth();
        Map<String, Color> legendItems = new LinkedHashMap<>();
        // 收集图例项
        for (JNetworkTopologyData.Node node : config.getNodes()) {
            if (node.getLegendGroup() != null && node.getColor() != null) {
                legendItems.put(node.getLegendGroup(), node.getColor());
            }
        }
        if (legendItems.isEmpty()) return;
        int itemHeight = 20;
        int itemWidth = 100;
        int itemsPerRow = Math.min(legendItems.size(), 4);
        int totalWidth = itemsPerRow * itemWidth;
        int legendStartX = Math.max(20, (width - totalWidth) / 2);
        int i = 0;
        for (Map.Entry<String, Color> entry : legendItems.entrySet()) {
            int legendX = legendStartX + (i % itemsPerRow) * itemWidth;
            int legendY = layoutParams.legendY + (i / itemsPerRow) * itemHeight;
            // 绘制颜色方块
            svg.setPaint(entry.getValue());
            svg.fillRect(legendX, legendY - 12, 12, 12);
            svg.setPaint(Color.BLACK);
            svg.drawRect(legendX, legendY - 12, 12, 12);
            // 绘制文字
            svg.setFont(config.getLegendFont());
            svg.setPaint(config.getTextColor());
            svg.drawString(entry.getKey(), legendX + 18, legendY);
            i++;
        }
    }

    /**
     * 绘制底部说明
     */
    private void drawFooter(SVGGraphics2D svg) {
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            svg.setFont(config.getFooterFont());
            svg.setPaint(config.getFooterColor());
            String footer = config.getFooterText();
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(footer, config.getWidth() / 2 - fm.stringWidth(footer) / 2, layoutParams.footerY);
        }
    }

    /**
     * 根据ID查找节点
     */
    private JNetworkTopologyData.Node findNodeById(String id) {
        for (JNetworkTopologyData.Node node : config.getNodes()) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {
        int chartTop, chartBottom, chartLeft, chartRight;
        int chartWidth, chartHeight;
        int topMargin, bottomMargin, leftMargin, rightMargin;
        int legendY, footerY;
    }

}
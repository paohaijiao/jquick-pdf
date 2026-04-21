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
import java.awt.geom.Path2D;
import java.util.*;
import java.util.List;

/**
 * 网络拓扑图渲染器 - 支持节点和连线的网络拓扑展示
 * 支持自动布局和手动布局两种模式
 * 已修复文字重影和文字重叠问题
 */
@Data
public class JAdvancedTopologyRenderer extends JAbstractChartRenderer {

    private final LayoutParams layoutParams;

    private JAdvancedTopologyData config;

    private Map<String, Point> nodePositions;


    private transient Map<Font, FontMetrics> fontMetricsCache;// 缓存FontMetrics，避免重复计算导致的渲染问题

    private transient List<Rectangle> drawnLabelBounds;// 存储已绘制的标签边界，用于检测和避免重叠

    public JAdvancedTopologyRenderer() {
        this.layoutParams = new LayoutParams();
        this.nodePositions = new HashMap<>();
        this.fontMetricsCache = new HashMap<>();
        this.drawnLabelBounds = new ArrayList<>();
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
        JAdvancedTopologyData config = (JAdvancedTopologyData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getNodes(), "nodes list require not null");
        JAssert.notNull(config.getLinks(), "links list require not null");
        this.config = config;
        updateConfigDimensions(width, height);
        // 重置标签边界列表
        drawnLabelBounds = new ArrayList<>();
        calculateLayout();
        // 清除字体度量缓存
        fontMetricsCache.clear();
        // 设置渲染提示，提高文字渲染质量
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        svgGenerator.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        drawChartBackground(svgGenerator);
        drawAllLinks(svgGenerator);
        drawAllNodes(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
        drawLegend(svgGenerator);
    }

    /**
     * 获取字体度量（带缓存）
     */
    private FontMetrics getFontMetrics(SVGGraphics2D svg, Font font) {
        if (!fontMetricsCache.containsKey(font)) {
            fontMetricsCache.put(font, svg.getFontMetrics(font));
        }
        return fontMetricsCache.get(font);
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
        int titleHeight = 0;        // 计算标题占用的高度
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 40;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 25;
            }
        } else {
            titleHeight = 20;
        }
        int legendHeight = config.getNodes().size() <= 4 ? 50 : 70;// 图例和底部说明占用的高度
        int footerHeight = config.getFooterText() != null && !config.getFooterText().isEmpty() ? 30 : 10;
        int topMargin = titleHeight;// 计算可用图表区域
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
        calculateNodePositions(chartLeft, chartTop, chartWidth, chartHeight);// 计算节点位置
    }

    /**
     * 计算节点位置
     */
    private void calculateNodePositions(int left, int top, int width, int height) {
        List<JAdvancedTopologyData.Node> nodes = config.getNodes();
        nodePositions.clear();
        if (config.isAutoLayout()) {
            calculateForceDirectedLayout(left, top, width, height);// 自动布局 - 使用力导向布局算法
        } else {
            for (JAdvancedTopologyData.Node node : nodes) {// 手动布局 - 使用节点中指定的坐标
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
     * 力导向布局算法 - 修复节点重叠问题
     */
    private void calculateForceDirectedLayout(int left, int top, int width, int height) {
        List<JAdvancedTopologyData.Node> nodes = config.getNodes();
        int nodeCount = nodes.size();
        if (nodeCount == 0) return;
        // 按层级分组，用于层级约束
        Map<String, List<JAdvancedTopologyData.Node>> layerGroups = new HashMap<>();
        for (JAdvancedTopologyData.Node node : nodes) {
            String layer = node.getLegendGroup() != null ? node.getLegendGroup() : "default";
            layerGroups.computeIfAbsent(layer, k -> new ArrayList<>()).add(node);
        }
        // 定义层级的垂直位置比例（Y轴范围）
        Map<String, Double> layerYRange = new HashMap<>();
        String[] layerOrder = {"Core Layer", "Aggregation Layer", "Access Layer", "Servers", "default"};
        int layerIndex = 0;
        for (String layer : layerOrder) {
            if (layerGroups.containsKey(layer)) {
                double yStart = top + (layerIndex * (height / (double) layerGroups.size()));
                double yEnd = top + ((layerIndex + 1) * (height / (double) layerGroups.size()));
                layerYRange.put(layer, (yStart + yEnd) / 2);
                layerIndex++;
            }
        }
        // 初始化随机位置
        Random random = new Random(config.getLayoutSeed());
        Map<String, Point> positions = new HashMap<>();
        Map<String, Point> velocities = new HashMap<>();
        for (JAdvancedTopologyData.Node node : nodes) {
            int x = left + 50 + random.nextInt(width - 100);
            String layer = node.getLegendGroup() != null ? node.getLegendGroup() : "default";
            Double targetY = layerYRange.get(layer);
            int y;
            if (targetY != null) {
                int layerHeight = height / layerGroups.size();// 在同一层内随机分布Y坐标
                y = (int) (targetY - layerHeight / 2 + random.nextInt(layerHeight));
                y = Math.max(top + 30, Math.min(top + height - 30, y));
            } else {
                y = top + 50 + random.nextInt(height - 100);
            }
            positions.put(node.getId(), new Point(x, y));
            velocities.put(node.getId(), new Point(0, 0));
        }
        // 力导向布局迭代
        double k = Math.sqrt(width * height / nodeCount) * 1.5; // 增大理想距离
        double iterations = config.getLayoutIterations();
        double damping = 0.85;
        double maxForce = 5.0; // 限制最大力，防止震荡
        // 计算每个节点的半径
        Map<String, Integer> nodeRadii = new HashMap<>();
        for (JAdvancedTopologyData.Node node : nodes) {
            int radius = node.getRadius() != null ? node.getRadius() : config.getDefaultNodeRadius();
            nodeRadii.put(node.getId(), radius);
        }
        for (int iter = 0; iter < iterations; iter++) {
            Map<String, Point> newPositions = new HashMap<>();
            Map<String, Point> newVelocities = new HashMap<>();
            for (JAdvancedTopologyData.Node nodeA : nodes) {
                double forceX = 0;
                double forceY = 0;
                Point posA = positions.get(nodeA.getId());
                Point velA = velocities.get(nodeA.getId());
                int radiusA = nodeRadii.get(nodeA.getId());
                String layerA = nodeA.getLegendGroup() != null ? nodeA.getLegendGroup() : "default";
                // 计算斥力（所有节点之间）- 考虑节点半径
                for (JAdvancedTopologyData.Node nodeB : nodes) {
                    if (nodeA.getId().equals(nodeB.getId())) continue;
                    Point posB = positions.get(nodeB.getId());
                    int radiusB = nodeRadii.get(nodeB.getId());
                    double dx = posA.x - posB.x;
                    double dy = posA.y - posB.y;
                    double minDistance = (radiusA + radiusB) * 1.5; // 最小距离为半径和的1.5倍
                    double distance = Math.max(Math.hypot(dx, dy), minDistance);
                    // 增强斥力公式
                    double repulsion = k * k / distance * 2;
                    forceX += repulsion * (dx / distance);
                    forceY += repulsion * (dy / distance);
                }

                // 计算引力（有连线的节点之间）
                for (JAdvancedTopologyData.Link link : config.getLinks()) {
                    Point connectedPos = null;
                    if (link.getSourceId().equals(nodeA.getId())) {
                        connectedPos = positions.get(link.getTargetId());
                    } else if (link.getTargetId().equals(nodeA.getId())) {
                        connectedPos = positions.get(link.getSourceId());
                    }
                    if (connectedPos != null) {
                        double dx = connectedPos.x - posA.x;
                        double dy = connectedPos.y - posA.y;
                        double distance = Math.max(Math.hypot(dx, dy), 1);
                        double attraction = distance * distance / k * 1.5;// 增强引力
                        forceX += attraction * (dx / distance);
                        forceY += attraction * (dy / distance);
                    }
                }
                Double targetY = layerYRange.get(layerA);// 添加层级约束力 - 将节点拉向其所属层级的Y轴中心
                if (targetY != null) {
                    double dyToTarget = targetY - posA.y;
                    forceY += dyToTarget * 0.05; // 温和的层级约束力
                }
                int margin = 40;// 添加边界排斥力，防止节点跑出边界
                if (posA.x < left + margin) {
                    forceX += (left + margin - posA.x) * 0.1;
                }
                if (posA.x > left + width - margin) {
                    forceX -= (posA.x - (left + width - margin)) * 0.1;
                }
                if (posA.y < top + margin) {
                    forceY += (top + margin - posA.y) * 0.1;
                }
                if (posA.y > top + height - margin) {
                    forceY -= (posA.y - (top + height - margin)) * 0.1;
                }
                // 限制最大力
                forceX = Math.max(-maxForce, Math.min(maxForce, forceX));
                forceY = Math.max(-maxForce, Math.min(maxForce, forceY));
                // 更新速度（增加惯性）
                double newVelX = velA.x * damping + forceX * 0.3;
                double newVelY = velA.y * damping + forceY * 0.3;
                // 限制最大速度
                newVelX = Math.max(-10, Math.min(10, newVelX));
                newVelY = Math.max(-10, Math.min(10, newVelY));
                // 更新位置
                double newX = posA.x + newVelX;
                double newY = posA.y + newVelY;
                // 边界约束（留出节点半径的空间）
                int radius = nodeRadii.get(nodeA.getId());
                newX = Math.max(left + radius + 10, Math.min(left + width - radius - 10, newX));
                newY = Math.max(top + radius + 10, Math.min(top + height - radius - 10, newY));
                newPositions.put(nodeA.getId(), new Point((int) newX, (int) newY));
                newVelocities.put(nodeA.getId(), new Point((int) newVelX, (int) newVelY));
            }
            positions = newPositions;
            velocities = newVelocities;
        }

        // 最终调整：确保同一层级的节点不重叠
        for (String layer : layerGroups.keySet()) {
            List<JAdvancedTopologyData.Node> layerNodes = layerGroups.get(layer);
            Map<String, Point> finalPositions = positions;// 按X坐标排序
            layerNodes.sort((a, b) -> {
                Point pa = finalPositions.get(a.getId());
                Point pb = finalPositions.get(b.getId());
                return Integer.compare(pa.x, pb.x);
            });
            // 调整X位置避免重叠
            for (int i = 0; i < layerNodes.size() - 1; i++) {
                JAdvancedTopologyData.Node nodeA = layerNodes.get(i);
                JAdvancedTopologyData.Node nodeB = layerNodes.get(i + 1);
                Point posA = positions.get(nodeA.getId());
                Point posB = positions.get(nodeB.getId());
                int radiusA = nodeRadii.get(nodeA.getId());
                int radiusB = nodeRadii.get(nodeB.getId());
                int minDistance = (radiusA + radiusB) * 2;
                if (Math.abs(posA.x - posB.x) < minDistance) {
                    // 调整位置
                    int newX = posB.x + (minDistance - (posB.x - posA.x));
                    if (newX < left + width - radiusB - 10) {
                        positions.put(nodeB.getId(), new Point(newX, posB.y));
                    }
                }
            }
        }
        nodePositions.putAll(positions);
    }

    /**
     * 默认布局 - 圆形布局
     */
    private void calculateDefaultLayout(int left, int top, int width, int height) {
        List<JAdvancedTopologyData.Node> nodes = config.getNodes();
        int nodeCount = nodes.size();
        if (nodeCount == 0) return;
        int centerX = left + width / 2;
        int centerY = top + height / 2;
        int radius = Math.min(width, height) / 2 - 50;
        for (int i = 0; i < nodeCount; i++) {
            JAdvancedTopologyData.Node node = nodes.get(i);
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
        // 绘制网格背景
        if (config.isShowGrid()) {
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
        for (JAdvancedTopologyData.Link link : config.getLinks()) {
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
    private void drawSingleLink(SVGGraphics2D svg, JAdvancedTopologyData.Link link, Point source, Point target) {
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
    private void drawArrow(SVGGraphics2D svg, Point source, Point target, JAdvancedTopologyData.Link link) {
        double angle = Math.atan2(target.y - source.y, target.x - source.x);
        int arrowX = target.x;
        int arrowY = target.y;
        // 计算箭头位置（考虑节点半径）
        JAdvancedTopologyData.Node targetNode = findNodeById(link.getTargetId());
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
     * 绘制连线标签 - 修复文字重叠
     */
    private void drawLinkLabel(SVGGraphics2D svg, JAdvancedTopologyData.Link link, Point source, Point target) {
        int midX = (source.x + target.x) / 2;
        int midY = (source.y + target.y) / 2;
        Font labelFont = config.getLinkLabelFont();
        svg.setFont(labelFont);
        FontMetrics fm = getFontMetrics(svg, labelFont);
        String label = link.getLabel();
        int stringWidth = fm.stringWidth(label);
        int stringHeight = fm.getHeight();
        // 尝试多个偏移位置避免重叠
        int[][] offsets = {
                {0, -12},     // 上方
                {0, 12},      // 下方
                {-12, -8},    // 左上方
                {12, -8},     // 右上方
                {-12, 8},     // 左下方
                {12, 8},      // 右下方
                {-18, 0},     // 左侧
                {18, 0}       // 右侧
        };

        Point bestPos = null;
        Rectangle bestBounds = null;
        for (int[] offset : offsets) {
            int labelX = midX + offset[0] - stringWidth / 2;
            int labelY = midY + offset[1];
            Rectangle bounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
            // 检查是否在图表区域内
            if (bounds.y < layoutParams.chartTop || bounds.y + bounds.height > layoutParams.chartBottom - 10) {
                continue;
            }
            // 检查是否与其他标签重叠
            boolean overlapping = false;
            for (Rectangle existing : drawnLabelBounds) {
                if (bounds.intersects(existing)) {
                    overlapping = true;
                    break;
                }
            }
            if (!overlapping) {
                bestPos = new Point(labelX + stringWidth / 2, labelY);
                bestBounds = bounds;
                break;
            }
        }
        // 使用默认位置
        if (bestPos == null) {
            bestPos = new Point(midX, midY - 12);
            int labelX = bestPos.x - stringWidth / 2;
            int labelY = bestPos.y;
            bestBounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        drawnLabelBounds.add(bestBounds);
        int labelX = bestPos.x - stringWidth / 2;
        int labelY = bestPos.y;
        // 先绘制背景（如果需要）
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        // 绘制文字
        svg.setPaint(link.getLabelColor() != null ? link.getLabelColor() : config.getTextColor());
        svg.drawString(link.getLabel(), labelX, labelY);
    }

    /**
     * 绘制数据流动画点
     */
    private void drawDataFlowPoint(SVGGraphics2D svg, Point source, Point target, JAdvancedTopologyData.Link link) {
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
        for (JAdvancedTopologyData.Node node : config.getNodes()) {
            Point pos = nodePositions.get(node.getId());
            if (pos != null) {
                drawSingleNode(svg, node, pos);
            }
        }
    }

    /**
     * 绘制单个节点 - 修复文字重叠
     */
    private void drawSingleNode(SVGGraphics2D svg, JAdvancedTopologyData.Node node, Point pos) {
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
        JAdvancedTopologyData.NodeShape shape = node.getShape() != null ? node.getShape() : config.getDefaultShape();
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
        // 绘制节点标签 - 修复文字重叠
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
    private void drawNodeIcon(SVGGraphics2D svg, JAdvancedTopologyData.Node node, Point pos, int radius) {
        svg.setPaint(Color.WHITE);
        String icon = node.getIcon();
        Font iconFont = new Font("Segoe UI Emoji", Font.PLAIN, radius);
        svg.setFont(iconFont);
        FontMetrics fm = getFontMetrics(svg, iconFont);
        int charWidth = fm.charWidth(icon.charAt(0));
        svg.drawString(icon, pos.x - charWidth / 2, pos.y + radius / 2);
    }

    /**
     * 获取节点标签的候选位置列表（按优先级排序）
     */
    private List<Point> getLabelPositions(Point pos, int radius, int labelWidth, int labelHeight) {
        List<Point> positions = new ArrayList<>();
        int offset = config.getNodeLabelOffset() != 0 ? config.getNodeLabelOffset() : 15;
        // 位置优先级：下方 > 右方 > 左方 > 上方 > 右下方 > 左下方 > 右上方 > 左上方
        positions.add(new Point(pos.x, pos.y + radius + offset)); // 下方
        positions.add(new Point(pos.x + radius + 10 + labelWidth / 2, pos.y)); // 右方
        positions.add(new Point(pos.x - radius - 10 - labelWidth / 2, pos.y));// 左方
        positions.add(new Point(pos.x, pos.y - radius - 8)); // 上方
        positions.add(new Point(pos.x + radius / 2 + 5, pos.y + radius / 2 + offset - 5));// 右下方
        positions.add(new Point(pos.x - radius / 2 - 5, pos.y + radius / 2 + offset - 5));// 左下方
        positions.add(new Point(pos.x + radius / 2 + 5, pos.y - radius / 2 - 8));// 右上方
        positions.add(new Point(pos.x - radius / 2 - 5, pos.y - radius / 2 - 8));// 左上方
        return positions;
    }

    /**
     * 绘制节点标签 - 修复文字重叠问题
     */
    private void drawNodeLabel(SVGGraphics2D svg, JAdvancedTopologyData.Node node, Point pos, int radius) {
        Font labelFont = config.getNodeLabelFont();
        svg.setFont(labelFont);
        FontMetrics fm = getFontMetrics(svg, labelFont);
        String label = node.getLabel();
        int stringWidth = fm.stringWidth(label);
        int stringHeight = fm.getHeight();
        // 如果启用智能标签布局，尝试多个位置避免重叠
        Point bestPosition = null;
        Rectangle bestBounds = null;
        if (config.isSmartLabelLayout()) {
            List<Point> labelPositions = getLabelPositions(pos, radius, stringWidth, stringHeight);
            for (Point testPos : labelPositions) {
                int labelX = testPos.x - stringWidth / 2;
                int labelY = testPos.y;
                Rectangle bounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
                // 检查是否在图表区域内
                if (bounds.y < layoutParams.chartTop || bounds.y + bounds.height > layoutParams.chartBottom - 10) {
                    continue;
                }
                // 检查是否与其他标签重叠
                boolean overlapping = false;
                for (Rectangle existing : drawnLabelBounds) {
                    if (bounds.intersects(existing)) {
                        overlapping = true;
                        break;
                    }
                }
                if (!overlapping) {
                    bestPosition = testPos;
                    bestBounds = bounds;
                    break;
                }
            }
        }
        // 如果没有找到不重叠的位置或未启用智能布局，使用默认位置
        if (bestPosition == null) {
            int offset = config.getNodeLabelOffset() != 0 ? config.getNodeLabelOffset() : 15;
            bestPosition = new Point(pos.x, pos.y + radius + offset);
            int labelX = bestPosition.x - stringWidth / 2;
            int labelY = bestPosition.y;
            bestBounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        // 记录已使用的标签位置
        if (config.isSmartLabelLayout()) {
            drawnLabelBounds.add(bestBounds);
        }
        int labelX = bestPosition.x - stringWidth / 2;
        int labelY = bestPosition.y;
        // 绘制标签背景（如果需要）
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
            svg.setPaint(config.getTextColor());
        } else {
            svg.setPaint(config.getTextColor());
        }
        // 绘制文字
        svg.drawString(label, labelX, labelY);
    }

    /**
     * 绘制节点状态指示器
     */
    private void drawNodeStatus(SVGGraphics2D svg, JAdvancedTopologyData.Node node, Point pos, int radius) {
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
     * 绘制图例 - 修复文字重叠和布局问题
     */
    private void drawLegend(SVGGraphics2D svg) {
        if (!config.isShowLegend()) return;
        int width = config.getWidth();
        Map<String, Color> legendItems = new LinkedHashMap<>();
        // 收集图例项
        for (JAdvancedTopologyData.Node node : config.getNodes()) {
            if (node.getLegendGroup() != null && node.getColor() != null) {
                legendItems.putIfAbsent(node.getLegendGroup(), node.getColor());
            }
        }
        if (legendItems.isEmpty()) return;
        int itemHeight = 22;
        int colorBoxSize = 12;
        Font legendFont = config.getLegendFont();
        svg.setFont(legendFont);
        FontMetrics fm = getFontMetrics(svg, legendFont);
        // 计算每个图例项的实际宽度
        List<Integer> itemWidths = new ArrayList<>();
        int totalWidth = 0;
        for (String key : legendItems.keySet()) {
            int textWidth = fm.stringWidth(key);
            int itemWidth = colorBoxSize + 8 + textWidth;
            itemWidths.add(itemWidth);
            totalWidth += itemWidth;
        }
        // 添加项目之间的间距
        int spacing = 25;
        totalWidth += spacing * (legendItems.size() - 1);
        // 如果总宽度超过画布宽度，则换行显示
        int itemsPerRow = legendItems.size();
        int startX;
        int currentY = layoutParams.legendY;
        if (totalWidth > width - 40) {// 需要换行，计算每行最多显示多少个
            itemsPerRow = Math.max(1, (width - 40) / (itemWidths.get(0) + spacing));
            startX = 20;
        } else {// 居中显示
            startX = Math.max(20, (width - totalWidth) / 2);
        }
        int i = 0;
        int currentX = startX;
        int row = 0;
        for (Map.Entry<String, Color> entry : legendItems.entrySet()) {
            int legendY = currentY + row * itemHeight;
            int textWidth = fm.stringWidth(entry.getKey());
            // 换行逻辑
            if (i > 0 && i % itemsPerRow == 0) {
                row++;
                currentX = startX;
                legendY = currentY + row * itemHeight;
            }
            // 绘制颜色方块
            svg.setPaint(entry.getValue());
            svg.fillRect(currentX, legendY - colorBoxSize, colorBoxSize, colorBoxSize);
            svg.setPaint(Color.BLACK);
            svg.setStroke(new BasicStroke(0.5f));
            svg.drawRect(currentX, legendY - colorBoxSize, colorBoxSize, colorBoxSize);
            // 绘制文字
            svg.setPaint(config.getTextColor());
            svg.drawString(entry.getKey(), currentX + colorBoxSize + 5, legendY);
            // 移动到下一个图例项位置
            currentX += itemWidths.get(i) + spacing;
            i++;
        }
    }

    /**
     * 绘制底部说明 - 修复文字重影
     */
    private void drawFooter(SVGGraphics2D svg) {
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            Font footerFont = config.getFooterFont();
            svg.setFont(footerFont);
            svg.setPaint(config.getFooterColor());
            String footer = config.getFooterText();
            FontMetrics fm = getFontMetrics(svg, footerFont);
            int stringWidth = fm.stringWidth(footer);
            svg.drawString(footer, config.getWidth() / 2 - stringWidth / 2, layoutParams.footerY);
        }
    }

    @Override
    protected void drawTitle(SVGGraphics2D svgGenerator, JOption option, int width) {
        String title = config.getTitleText();
        String subtitle = config.getSubtitleText();
        if (title != null && !title.isEmpty()) {
            Font titleFont = config.getTitleFont();
            svgGenerator.setFont(titleFont);
            svgGenerator.setPaint(config.getTextColor());
            FontMetrics fm = getFontMetrics(svgGenerator, titleFont);
            int titleWidth = fm.stringWidth(title);
            svgGenerator.drawString(title, width / 2 - titleWidth / 2, 35);
            if (subtitle != null && !subtitle.isEmpty()) {
                Font subtitleFont = config.getSubtitleFont();
                svgGenerator.setFont(subtitleFont);
                fm = getFontMetrics(svgGenerator, subtitleFont);
                int subtitleWidth = fm.stringWidth(subtitle);
                svgGenerator.drawString(subtitle, width / 2 - subtitleWidth / 2, 58);
            }
        }
    }

    /**
     * 根据ID查找节点
     */
    private JAdvancedTopologyData.Node findNodeById(String id) {
        for (JAdvancedTopologyData.Node node : config.getNodes()) {
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
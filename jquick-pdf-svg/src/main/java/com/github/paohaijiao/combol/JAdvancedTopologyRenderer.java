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


@Data
public class JAdvancedTopologyRenderer extends JAbstractChartRenderer {

    private final LayoutParams layoutParams;

    private JAdvancedTopologyData config;

    private Map<String, Point> nodePositions;


    private transient Map<Font, FontMetrics> fontMetricsCache;

    private transient List<Rectangle> drawnLabelBounds;

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
        drawnLabelBounds = new ArrayList<>();
        calculateLayout();
        fontMetricsCache.clear();
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
        int titleHeight = 0;
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
        calculateNodePositions(chartLeft, chartTop, chartWidth, chartHeight);// 计算节点位置
    }

    /**
     * 计算节点位置
     */
    private void calculateNodePositions(int left, int top, int width, int height) {
        List<JAdvancedTopologyData.Node> nodes = config.getNodes();
        nodePositions.clear();
        if (config.isAutoLayout()) {
            calculateForceDirectedLayout(left, top, width, height);
        } else {
            for (JAdvancedTopologyData.Node node : nodes) {
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
        Map<String, List<JAdvancedTopologyData.Node>> layerGroups = new HashMap<>();
        for (JAdvancedTopologyData.Node node : nodes) {
            String layer = node.getLegendGroup() != null ? node.getLegendGroup() : "default";
            layerGroups.computeIfAbsent(layer, k -> new ArrayList<>()).add(node);
        }
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
        Random random = new Random(config.getLayoutSeed());
        Map<String, Point> positions = new HashMap<>();
        Map<String, Point> velocities = new HashMap<>();
        for (JAdvancedTopologyData.Node node : nodes) {
            int x = left + 50 + random.nextInt(width - 100);
            String layer = node.getLegendGroup() != null ? node.getLegendGroup() : "default";
            Double targetY = layerYRange.get(layer);
            int y;
            if (targetY != null) {
                int layerHeight = height / layerGroups.size();
                y = (int) (targetY - layerHeight / 2 + random.nextInt(layerHeight));
                y = Math.max(top + 30, Math.min(top + height - 30, y));
            } else {
                y = top + 50 + random.nextInt(height - 100);
            }
            positions.put(node.getId(), new Point(x, y));
            velocities.put(node.getId(), new Point(0, 0));
        }
        double k = Math.sqrt(width * height / nodeCount) * 1.5;
        double iterations = config.getLayoutIterations();
        double damping = 0.85;
        double maxForce = 5.0;
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
                for (JAdvancedTopologyData.Node nodeB : nodes) {
                    if (nodeA.getId().equals(nodeB.getId())) continue;
                    Point posB = positions.get(nodeB.getId());
                    int radiusB = nodeRadii.get(nodeB.getId());
                    double dx = posA.x - posB.x;
                    double dy = posA.y - posB.y;
                    double minDistance = (radiusA + radiusB) * 1.5;
                    double distance = Math.max(Math.hypot(dx, dy), minDistance);
                    double repulsion = k * k / distance * 2;
                    forceX += repulsion * (dx / distance);
                    forceY += repulsion * (dy / distance);
                }

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
                Double targetY = layerYRange.get(layerA);
                if (targetY != null) {
                    double dyToTarget = targetY - posA.y;
                    forceY += dyToTarget * 0.05;
                }
                int margin = 40;
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
                forceX = Math.max(-maxForce, Math.min(maxForce, forceX));
                forceY = Math.max(-maxForce, Math.min(maxForce, forceY));
                double newVelX = velA.x * damping + forceX * 0.3;
                double newVelY = velA.y * damping + forceY * 0.3;
                newVelX = Math.max(-10, Math.min(10, newVelX));
                newVelY = Math.max(-10, Math.min(10, newVelY));
                double newX = posA.x + newVelX;
                double newY = posA.y + newVelY;
                int radius = nodeRadii.get(nodeA.getId());
                newX = Math.max(left + radius + 10, Math.min(left + width - radius - 10, newX));
                newY = Math.max(top + radius + 10, Math.min(top + height - radius - 10, newY));
                newPositions.put(nodeA.getId(), new Point((int) newX, (int) newY));
                newVelocities.put(nodeA.getId(), new Point((int) newVelX, (int) newVelY));
            }
            positions = newPositions;
            velocities = newVelocities;
        }

        for (String layer : layerGroups.keySet()) {
            List<JAdvancedTopologyData.Node> layerNodes = layerGroups.get(layer);
            Map<String, Point> finalPositions = positions;
            layerNodes.sort((a, b) -> {
                Point pa = finalPositions.get(a.getId());
                Point pb = finalPositions.get(b.getId());
                return Integer.compare(pa.x, pb.x);
            });
            for (int i = 0; i < layerNodes.size() - 1; i++) {
                JAdvancedTopologyData.Node nodeA = layerNodes.get(i);
                JAdvancedTopologyData.Node nodeB = layerNodes.get(i + 1);
                Point posA = positions.get(nodeA.getId());
                Point posB = positions.get(nodeB.getId());
                int radiusA = nodeRadii.get(nodeA.getId());
                int radiusB = nodeRadii.get(nodeB.getId());
                int minDistance = (radiusA + radiusB) * 2;
                if (Math.abs(posA.x - posB.x) < minDistance) {
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
        svg.setStroke(new BasicStroke(link.getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.setPaint(link.getLineColor());
        Path2D path = new Path2D.Double();
        if (link.isCurved() && config.isCurvedLinks()) {
            int dx = target.x - source.x;
            int dy = target.y - source.y;
            int ctrlX = source.x + dx / 2 + dy / 3;
            int ctrlY = source.y + dy / 2 - dx / 3;
            path.moveTo(source.x, source.y);
            path.curveTo(ctrlX, ctrlY, ctrlX, ctrlY, target.x, target.y);
        } else {
            path.moveTo(source.x, source.y);
            path.lineTo(target.x, target.y);
        }
        svg.draw(path);
        if (link.isShowArrow() || config.isShowArrows()) {
            drawArrow(svg, source, target, link);
        }
        if (link.getLabel() != null && !link.getLabel().isEmpty() && config.isShowLinkLabels()) {
            drawLinkLabel(svg, link, source, target);
        }
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
        int[][] offsets = {
                {0, -12},
                {0, 12},
                {-12, -8},
                {12, -8},
                {-12, 8},
                {12, 8},
                {-18, 0},
                {18, 0}
        };

        Point bestPos = null;
        Rectangle bestBounds = null;
        for (int[] offset : offsets) {
            int labelX = midX + offset[0] - stringWidth / 2;
            int labelY = midY + offset[1];
            Rectangle bounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
            if (bounds.y < layoutParams.chartTop || bounds.y + bounds.height > layoutParams.chartBottom - 10) {
                continue;
            }
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
        if (bestPos == null) {
            bestPos = new Point(midX, midY - 12);
            int labelX = bestPos.x - stringWidth / 2;
            int labelY = bestPos.y;
            bestBounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        drawnLabelBounds.add(bestBounds);
        int labelX = bestPos.x - stringWidth / 2;
        int labelY = bestPos.y;
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        svg.setPaint(link.getLabelColor() != null ? link.getLabelColor() : config.getTextColor());
        svg.drawString(link.getLabel(), labelX, labelY);
    }

    /**
     * 绘制数据流动画点
     */
    private void drawDataFlowPoint(SVGGraphics2D svg, Point source, Point target, JAdvancedTopologyData.Link link) {
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
        if (config.isShowShadow()) {
            svg.setPaint(new Color(0, 0, 0, 50));
            svg.fillOval(pos.x - radius + 3, pos.y - radius + 3, radius * 2, radius * 2);
        }
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
        if (node.getIcon() != null && config.isShowIcons()) {
            drawNodeIcon(svg, node, pos, radius);
        }
        if (node.getLabel() != null && !node.getLabel().isEmpty()) {
            drawNodeLabel(svg, node, pos, radius);
        }
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
        positions.add(new Point(pos.x, pos.y + radius + offset));
        positions.add(new Point(pos.x + radius + 10 + labelWidth / 2, pos.y));
        positions.add(new Point(pos.x - radius - 10 - labelWidth / 2, pos.y));
        positions.add(new Point(pos.x, pos.y - radius - 8));
        positions.add(new Point(pos.x + radius / 2 + 5, pos.y + radius / 2 + offset - 5));
        positions.add(new Point(pos.x - radius / 2 - 5, pos.y + radius / 2 + offset - 5));
        positions.add(new Point(pos.x + radius / 2 + 5, pos.y - radius / 2 - 8));
        positions.add(new Point(pos.x - radius / 2 - 5, pos.y - radius / 2 - 8));
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
        Point bestPosition = null;
        Rectangle bestBounds = null;
        if (config.isSmartLabelLayout()) {
            List<Point> labelPositions = getLabelPositions(pos, radius, stringWidth, stringHeight);
            for (Point testPos : labelPositions) {
                int labelX = testPos.x - stringWidth / 2;
                int labelY = testPos.y;
                Rectangle bounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
                if (bounds.y < layoutParams.chartTop || bounds.y + bounds.height > layoutParams.chartBottom - 10) {
                    continue;
                }
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
        if (bestPosition == null) {
            int offset = config.getNodeLabelOffset() != 0 ? config.getNodeLabelOffset() : 15;
            bestPosition = new Point(pos.x, pos.y + radius + offset);
            int labelX = bestPosition.x - stringWidth / 2;
            int labelY = bestPosition.y;
            bestBounds = new Rectangle(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
        }
        if (config.isSmartLabelLayout()) {
            drawnLabelBounds.add(bestBounds);
        }
        int labelX = bestPosition.x - stringWidth / 2;
        int labelY = bestPosition.y;
        if (config.isShowLabelBackground()) {
            svg.setPaint(config.getLabelBackgroundColor());
            svg.fillRect(labelX - 3, labelY - fm.getAscent() + 2, stringWidth + 6, stringHeight);
            svg.setPaint(config.getTextColor());
        } else {
            svg.setPaint(config.getTextColor());
        }
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
        List<Integer> itemWidths = new ArrayList<>();
        int totalWidth = 0;
        for (String key : legendItems.keySet()) {
            int textWidth = fm.stringWidth(key);
            int itemWidth = colorBoxSize + 8 + textWidth;
            itemWidths.add(itemWidth);
            totalWidth += itemWidth;
        }
        int spacing = 25;
        totalWidth += spacing * (legendItems.size() - 1);
        int itemsPerRow = legendItems.size();
        int startX;
        int currentY = layoutParams.legendY;
        if (totalWidth > width - 40) {
            itemsPerRow = Math.max(1, (width - 40) / (itemWidths.get(0) + spacing));
            startX = 20;
        } else {
            startX = Math.max(20, (width - totalWidth) / 2);
        }
        int i = 0;
        int currentX = startX;
        int row = 0;
        for (Map.Entry<String, Color> entry : legendItems.entrySet()) {
            int legendY = currentY + row * itemHeight;
            int textWidth = fm.stringWidth(entry.getKey());
            if (i > 0 && i % itemsPerRow == 0) {
                row++;
                currentX = startX;
                legendY = currentY + row * itemHeight;
            }
            svg.setPaint(entry.getValue());
            svg.fillRect(currentX, legendY - colorBoxSize, colorBoxSize, colorBoxSize);
            svg.setPaint(Color.BLACK);
            svg.setStroke(new BasicStroke(0.5f));
            svg.drawRect(currentX, legendY - colorBoxSize, colorBoxSize, colorBoxSize);
            svg.setPaint(config.getTextColor());
            svg.drawString(entry.getKey(), currentX + colorBoxSize + 5, legendY);
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
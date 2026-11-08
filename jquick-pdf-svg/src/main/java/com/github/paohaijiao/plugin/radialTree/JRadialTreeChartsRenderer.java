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
package com.github.paohaijiao.plugin.radialTree;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import com.github.paohaijiao.treemap.JTreeMapNode;
import com.github.paohaijiao.treemap.TreeMapOption;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.plugin.radialTree
 *
 * @author Martin
 * @version 1.0.0
 * @classname JRadialTreeChartsRenderer
 * @date 2026/11/8
 * @description Radial tree renderer.
 * <p>
 * The tree is taken from {@link JRadialTreeChartData} through {@code JOption.setData(...)}.
 * A plain {@link TreeMapOption} root is still accepted and converted on the fly, so existing
 * callers keep working. Radius encodes the level, angle encodes the leaf share inside the
 * parent, and every link is a cubic curve between two polar points. All painting is done with
 * Graphics2D calls on the given SVGGraphics2D instance.
 */
@JQuickSpi(name = "radial-tree", order = 34, chartType = JChartType.RadialTree)
public class JRadialTreeChartsRenderer extends JAbstractChartRenderer {

    private static final int SMALL_CANVAS_WIDTH = 760;

    private static final int SMALL_CANVAS_HEIGHT = 620;

    private static final int LARGE_CANVAS_WIDTH = 960;

    private static final int LARGE_CANVAS_HEIGHT = 800;

    private static final int LARGE_TREE_LIMIT = 120;

    /**
     * Tiny angular gap between siblings. Keeping it small keeps the outer ring closed,
     * so the leaves cover the full circle instead of forming a broken arc.
     */
    private static final double ANGLE_GAP = 0.008d;

    private static final Font BRANCH_FONT = new Font("Microsoft YaHei", Font.BOLD, 11);

    private static final Font LEAF_FONT = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private static final Font SMALL_LEAF_FONT = new Font("Microsoft YaHei", Font.PLAIN, 9);

    private static final Font NOTE_FONT = new Font("Microsoft YaHei", Font.PLAIN, 9);

    private static final Color ROOT_COLOR = new Color(0x37474F);

    private static final Color RING_COLOR = new Color(0xEC, 0xEF, 0xF1, 120);

    private JRadialTreeChartData config;

    private int canvasWidth = SMALL_CANVAS_WIDTH;

    private int canvasHeight = SMALL_CANVAS_HEIGHT;

    @Override
    public String renderToString(JOption option) throws IOException {
        JRadialTreeChartData data = resolveConfig(option);
        this.config = data;
        data.validateAndCalculate();
        boolean large = data.getNodeCount() > LARGE_TREE_LIMIT;
        this.canvasWidth = data.getWidth() > 0 ? data.getWidth() : (large ? LARGE_CANVAS_WIDTH : SMALL_CANVAS_WIDTH);
        this.canvasHeight = data.getHeight() > 0 ? data.getHeight() : (large ? LARGE_CANVAS_HEIGHT : SMALL_CANVAS_HEIGHT);
        return super.renderToString(option);
    }

    @Override
    protected int getDefaultWidth() {
        return canvasWidth;
    }

    @Override
    protected int getDefaultHeight() {
        return canvasHeight;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        JRadialTreeChartData data = config != null ? config : resolveConfig(option);
        svgGenerator.setPaint(data.getBackgroundColor());
        svgGenerator.fillRect(0, 0, width, height);
        if (data.getRoot() == null || data.getRoot().getChildren().isEmpty()) {
            drawEmptyHint(svgGenerator, width, height);
            return;
        }
        if (data.getNodeCount() <= 0) {
            data.validateAndCalculate();
        }
        drawHeader(svgGenerator, option, data, width);
        Chart chart = new Chart(width, height, data);
        Node root = chart.build(data.getRoot());
        drawRing(svgGenerator, chart);
        drawLinks(svgGenerator, root);
        drawNodes(svgGenerator, root);
        drawLabels(svgGenerator, chart, root, data.getNodeCount());
        drawNote(svgGenerator, chart, data);
    }

    private JRadialTreeChartData resolveConfig(JOption option) {
        if (option != null && option.getData() instanceof JRadialTreeChartData) {
            return (JRadialTreeChartData) option.getData();
        }
        JRadialTreeChartData data = new JRadialTreeChartData();
        if (option != null) {
            if (option.title() != null) {
                data.setTitleText(option.title().text());
                data.setSubtitleText(option.title().subtext());
            }
            TreeMapOption treeOption = option.getTreemapOption();
            if (treeOption != null && treeOption.getRoot() != null) {
                data.setRoot(convert(treeOption.getRoot()));
            }
        }
        return data;
    }

    private static JRadialTreeChartData.RadialNode convert(JTreeMapNode node) {
        JRadialTreeChartData.RadialNode result = new JRadialTreeChartData.RadialNode(node.getName(), node.getValue());
        for (JTreeMapNode child : node.getChildren()) {
            result.addChild(convert(child));
        }
        return result;
    }

    private void drawHeader(SVGGraphics2D svgGenerator, JOption option, JRadialTreeChartData data, int width) {
        if (data.getTitleText() == null) {
            drawTitle(svgGenerator, option, width);
            return;
        }
        svgGenerator.setFont(TITLE_FONT);
        svgGenerator.setPaint(data.getBranchLabelColor());
        String title = data.getTitleText();
        svgGenerator.drawString(title, width / 2f - svgGenerator.getFontMetrics().stringWidth(title) / 2f, 40);
        if (data.getSubtitleText() != null) {
            svgGenerator.setFont(LABEL_FONT);
            String subtitle = data.getSubtitleText();
            svgGenerator.drawString(subtitle, width / 2f - svgGenerator.getFontMetrics().stringWidth(subtitle) / 2f, 80);
        }
    }

    private void drawRing(SVGGraphics2D svgGenerator, Chart chart) {
        svgGenerator.setPaint(RING_COLOR);
        double radius = chart.innerRadius * 0.86d;
        svgGenerator.fill(new Ellipse2D.Double(chart.centerX - radius, chart.centerY - radius, radius * 2, radius * 2));
    }

    private void drawLinks(SVGGraphics2D svgGenerator, Node node) {
        for (Node child : node.children) {
            if (child.point == null || child.parentPoint == null) {
                continue;
            }
            double middleRadius = (child.parentRadius + child.radius) / 2.0d;
            Point2D first = child.chart.point(node.angle, middleRadius);
            Point2D second = child.chart.point(child.angle, middleRadius);
            Path2D.Double curve = new Path2D.Double();
            curve.moveTo(child.parentPoint.getX(), child.parentPoint.getY());
            curve.curveTo(first.getX(), first.getY(), second.getX(), second.getY(), child.point.getX(), child.point.getY());
            float lineWidth = Math.max(1.1f, 2.6f - child.depth * 0.45f);
            svgGenerator.setPaint(withAlpha(child.color, child.depth == 1 ? 150 : 110));
            svgGenerator.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            svgGenerator.draw(curve);
            drawLinks(svgGenerator, child);
        }
    }

    private void drawNodes(SVGGraphics2D svgGenerator, Node node) {
        if (node.point == null) {
            return;
        }
        double radius = node.depth == 0 ? 11d : (node.depth == 1 ? 6.2d : 3.0d);
        Shape circle = new Ellipse2D.Double(node.point.getX() - radius, node.point.getY() - radius, radius * 2, radius * 2);
        svgGenerator.setPaint(node.depth == 0 ? Color.WHITE : node.color);
        svgGenerator.fill(circle);
        svgGenerator.setPaint(node.depth == 0 ? ROOT_COLOR : Color.WHITE);
        svgGenerator.setStroke(new BasicStroke(node.depth == 0 ? 3f : 1.2f));
        svgGenerator.draw(circle);
        for (Node child : node.children) {
            drawNodes(svgGenerator, child);
        }
    }

    /**
     * Branch labels are painted first so the top level always keeps its name. Remaining labels
     * are painted from the outermost level inwards, and a label is dropped as soon as its box
     * collides with an already painted one. For large trees only leaves join the second pass.
     */
    private void drawLabels(SVGGraphics2D svgGenerator, Chart chart, Node root, int total) {
        boolean largeTree = total > LARGE_TREE_LIMIT;
        List<Node> nodes = new ArrayList<>();
        collect(root, nodes);
        List<Rectangle2D> occupied = new ArrayList<>();
        occupied.add(chart.centerBounds());
        for (Node node : nodes) {
            if (node.depth == 1) {
                paintLabel(svgGenerator, chart, node, occupied, BRANCH_FONT, config.getBranchLabelColor(), 15d);
            }
        }
        for (int i = nodes.size() - 1; i >= 0; i--) {
            Node node = nodes.get(i);
            if (node.depth <= 1) {
                continue;
            }
            if (largeTree && !node.children.isEmpty()) {
                continue;
            }
            paintLabel(svgGenerator, chart, node, occupied, largeTree ? SMALL_LEAF_FONT : LEAF_FONT, config.getLeafLabelColor(), 9d);
        }
    }

    private boolean paintLabel(SVGGraphics2D svgGenerator, Chart chart, Node node,
                               List<Rectangle2D> occupied, Font font, Color color, double offset) {
        if (node.point == null) {
            return false;
        }
        String text = node.data.getName();
        svgGenerator.setFont(font);
        int textWidth = svgGenerator.getFontMetrics().stringWidth(text);
        int ascent = svgGenerator.getFontMetrics().getAscent();
        Point2D anchor = chart.point(node.angle, node.radius + offset);
        boolean rightSide = Math.cos(node.angle) >= 0;
        double x = rightSide ? anchor.getX() : anchor.getX() - textWidth;
        double y = anchor.getY() + ascent / 2.0d - 2;
        Rectangle2D bounds = new Rectangle2D.Double(x - 2, y - ascent, textWidth + 4, ascent + 4);
        if (intersects(occupied, bounds)) {
            return false;
        }
        occupied.add(bounds);
        svgGenerator.setPaint(color);
        svgGenerator.drawString(text, (float) x, (float) y);
        return true;
    }

    private void drawNote(SVGGraphics2D svgGenerator, Chart chart, JRadialTreeChartData data) {
        String note = data.getFooterText();
        svgGenerator.setFont(NOTE_FONT);
        svgGenerator.setPaint(data.getNoteColor());
        int textWidth = svgGenerator.getFontMetrics().stringWidth(note);
        svgGenerator.drawString(note, (float) (chart.centerX - textWidth / 2.0d), (float) (chart.centerY + chart.outerRadius + 42));
    }

    private void drawEmptyHint(SVGGraphics2D svgGenerator, int width, int height) {
        svgGenerator.setFont(LABEL_FONT);
        svgGenerator.setPaint(NOTE_FONT_COLOR);
        String hint = "未提供树形数据：请通过 JOption.setData(new JRadialTreeChartData(...)) 传入节点树";
        int textWidth = svgGenerator.getFontMetrics().stringWidth(hint);
        svgGenerator.drawString(hint, (float) (width / 2.0d - textWidth / 2.0d), (float) (height / 2.0d));
    }

    private static final Color NOTE_FONT_COLOR = new Color(0x90A4AE);

    private static boolean intersects(List<Rectangle2D> occupied, Rectangle2D bounds) {
        for (Rectangle2D item : occupied) {
            if (item.intersects(bounds)) {
                return true;
            }
        }
        return false;
    }

    private static void collect(Node node, List<Node> nodes) {
        nodes.add(node);
        for (Node child : node.children) {
            collect(child, nodes);
        }
    }

    private static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * Canvas geometry and polar layout, shared by every node of one render pass.
     */
    private static final class Chart {

        private final double centerX;

        private final double centerY;

        private final double innerRadius;

        private final double outerRadius;

        private final int maxDepth;

        private final double startAngle;

        private final JRadialTreeChartData config;

        private Chart(int width, int height, JRadialTreeChartData config) {
            this.config = config;
            this.maxDepth = config.getMaxDepth();
            this.startAngle = Math.toRadians(config.getStartAngle());
            this.centerX = width / 2.0d;
            this.centerY = height / 2.0d + 16;
            this.outerRadius = Math.min(width, height - 110) / 2.0d - 38;
            this.innerRadius = Math.max(34d, outerRadius * 0.2d);
        }

        private Node build(JRadialTreeChartData.RadialNode data) {
            Node root = new Node(data, null, 0, 0, this, config);
            layout(root, startAngle, Math.PI * 2, 0);
            return root;
        }

        private void layout(Node node, double startAngle, double span, int depth) {
            node.chart = this;
            node.angle = startAngle + span / 2.0d;
            node.radius = radiusOf(depth);
            node.point = point(node.angle, node.radius);
            if (node.parent == null) {
                node.parentPoint = node.point;
                node.parentRadius = node.radius;
            } else {
                node.parentPoint = node.parent.point;
                node.parentRadius = node.parent.radius;
            }
            if (depth >= maxDepth || node.children.isEmpty()) {
                return;
            }
            double total = 0;
            for (Node child : node.children) {
                total += child.weight();
            }
            double cursor = startAngle;
            for (Node child : node.children) {
                double share = span * child.weight() / total;
                layout(child, cursor + share * ANGLE_GAP, share * (1 - 2 * ANGLE_GAP), depth + 1);
                cursor += share;
            }
        }

        private double radiusOf(int depth) {
            if (maxDepth <= 0) {
                return innerRadius;
            }
            return innerRadius + (outerRadius - innerRadius) * depth / maxDepth;
        }

        private Point2D point(double angle, double radius) {
            return new Point2D.Double(centerX + radius * Math.cos(angle), centerY + radius * Math.sin(angle));
        }

        private Rectangle2D centerBounds() {
            return new Rectangle2D.Double(centerX - 12, centerY - 12, 24, 24);
        }
    }

    /**
     * Runtime layout node, holds the resolved angle, radius, parent anchor and branch colour.
     */
    private static final class Node {

        private final JRadialTreeChartData.RadialNode data;

        private final Node parent;

        private final int depth;

        private final Color color;

        private final List<Node> children = new ArrayList<>();

        private Chart chart;

        private double angle;

        private double radius;

        private Point2D point;

        private Point2D parentPoint;

        private double parentRadius;

        private Node(JRadialTreeChartData.RadialNode data, Node parent, int depth, int branchIndex,
                     Chart chart, JRadialTreeChartData config) {
            this.data = data;
            this.parent = parent;
            this.depth = depth;
            this.chart = chart;
            List<Color> palette = config.getBranchColors();
            this.color = depth == 0 ? ROOT_COLOR : palette.get(Math.floorMod(branchIndex, palette.size()));
            for (JRadialTreeChartData.RadialNode child : data.getChildren()) {
                int index = depth == 0 ? children.size() : branchIndex;
                children.add(new Node(child, this, depth + 1, index, chart, config));
            }
        }

        private double weight() {
            return Math.max(1, data.getLeafCount());
        }
    }
}

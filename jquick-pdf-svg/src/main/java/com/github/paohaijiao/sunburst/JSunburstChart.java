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
import com.github.paohaijiao.data.JSunburstData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class JSunburstChart extends JAbstractChartRenderer {

    private static final int SVG_WIDTH = 800;

    private static final int SVG_HEIGHT = 800;

    private static final int CENTER_X = SVG_WIDTH / 2;

    private static final int CENTER_Y = 355;

    private static final int[] RADII = {45, 105, 165, 225, 280};

    private static final Color NO_DATA_FILL = new Color(248, 249, 250);

    private static final Color NO_DATA_STROKE = new Color(233, 236, 239);

    private static final Color[] THEME_COLORS = {

            new Color(255, 107, 107),

            new Color(255, 71, 87),

            new Color(78, 205, 196),

            new Color(38, 166, 154),

            new Color(255, 209, 102),

            new Color(255, 171, 0),

            new Color(239, 83, 80),

            new Color(211, 47, 47),

            new Color(236, 64, 122),

            new Color(194, 24, 91),

            new Color(77, 208, 225),

            new Color(0, 188, 212),

            new Color(255, 138, 128),

            new Color(255, 82, 82),

            new Color(255, 128, 171),

            new Color(240, 98, 146)
    };

    @Override
    public String renderToString(JOption option) throws IOException {
        org.w3c.dom.Document document = org.apache.batik.anim.dom.SVGDOMImplementation.getDOMImplementation().createDocument("http://www.w3.org/2000/svg", "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setSVGCanvasSize(new Dimension(SVG_WIDTH, SVG_HEIGHT));
        svgGenerator.setColor(Color.WHITE);
        svgGenerator.fillRect(0, 0, SVG_WIDTH, SVG_HEIGHT);
        drawChart(svgGenerator, option, SVG_WIDTH, SVG_HEIGHT);
        StringWriter writer = new StringWriter();
        svgGenerator.stream(writer, true);
        return writer.toString();
    }

    @Override
    protected void drawChart(SVGGraphics2D g, JOption option, int width, int height) {
        JSunburstData rootData = option.getSunburstData();
        drawTitle(g, option.getTitle().getText());
        drawSunburst(g, rootData);
        drawLegend(g, rootData);
        drawInteractionInfo(g);
    }

    /**
     * 绘制旭日图核心方法
     */
    private void drawSunburst(SVGGraphics2D g, JSunburstData root) {
        drawCenterCircle(g);
        for (int depth = 0; depth < RADII.length - 1; depth++) {
            drawRingBackground(g, depth);
        }
        drawDataNodes(g, root, 0, 360, 0);
    }

    /**
     * 绘制中心圆
     */
    private void drawCenterCircle(SVGGraphics2D g) {
        g.setColor(new Color(240, 240, 240));
        g.fillOval(CENTER_X - RADII[0], CENTER_Y - RADII[0], RADII[0] * 2, RADII[0] * 2);
        g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(1));
        g.drawOval(CENTER_X - RADII[0], CENTER_Y - RADII[0], RADII[0] * 2, RADII[0] * 2);

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        drawCenteredString(g, "总数据", CENTER_X, CENTER_Y);
    }

    /**
     * 绘制环形背景
     */
    private void drawRingBackground(SVGGraphics2D g, int depth) {
        int innerRadius = RADII[depth];
        int outerRadius = RADII[depth + 1];

        Area outerCircle = new Area(new Arc2D.Double(CENTER_X - outerRadius, CENTER_Y - outerRadius,
                outerRadius * 2, outerRadius * 2, 0, 360, Arc2D.PIE
        ));

        Area innerCircle = new Area(new Arc2D.Double(CENTER_X - innerRadius, CENTER_Y - innerRadius,
                innerRadius * 2, innerRadius * 2, 0, 360, Arc2D.PIE
        ));

        outerCircle.subtract(innerCircle);
        g.setColor(NO_DATA_FILL);
        g.fill(outerCircle);
        g.setColor(NO_DATA_STROKE);
        g.setStroke(new BasicStroke(1));
        g.draw(outerCircle);
    }

    /**
     * 递归绘制数据节点
     */
    private void drawDataNodes(SVGGraphics2D g, JSunburstData node, double startAngle, double endAngle, int depth) {
        if (depth >= RADII.length - 1) return;
        double angleRange = endAngle - startAngle;
        double currentStart = startAngle;
        for (JSunburstData child : node.getChildren()) {
            double childAngleRange = angleRange * child.getValue();
            double childEndAngle = currentStart + childAngleRange;

            if (childAngleRange > 0.1) {
                drawSector(g, child, depth, currentStart, childEndAngle);
                if (!child.getChildren().isEmpty()) {
                    drawDataNodes(g, child, currentStart, childEndAngle, depth + 1);
                }
            }

            currentStart = childEndAngle;
        }
    }

    /**
     * 绘制数据扇区
     */
    private void drawSector(SVGGraphics2D g, JSunburstData node, int depth, double startAngle, double endAngle) {
        if (depth >= RADII.length - 1) return;
        int innerRadius = RADII[depth];
        int outerRadius = RADII[depth + 1];
        Arc2D sector = new Arc2D.Double(
                CENTER_X - outerRadius, CENTER_Y - outerRadius,
                outerRadius * 2, outerRadius * 2,
                startAngle, endAngle - startAngle, Arc2D.PIE
        );

        if (innerRadius > 0) {
            Area sectorArea = new Area(sector);
            Area innerCircle = new Area(new Arc2D.Double(
                    CENTER_X - innerRadius, CENTER_Y - innerRadius,
                    innerRadius * 2, innerRadius * 2,
                    0, 360, Arc2D.PIE
            ));
            sectorArea.subtract(innerCircle);
            g.setColor(getColorForDepth(depth, node.getName()));
            g.fill(sectorArea);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(1.5f));
            g.draw(sectorArea);
        } else {
            g.setColor(getColorForDepth(depth, node.getName()));
            g.fill(sector);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(1.5f));
            g.draw(sector);
        }
        double angleRange = endAngle - startAngle;
        if (angleRange > 8) {
            addSectorLabel(g, node, depth, startAngle, endAngle);
        }
    }

    /**
     * 为扇区添加标签
     */
    private void addSectorLabel(SVGGraphics2D g, JSunburstData node, int depth, double startAngle, double endAngle) {
        int innerRadius = RADII[depth];
        int outerRadius = RADII[depth + 1];
        double midRadius = (innerRadius + outerRadius) / 2.0;
        double midAngle = (startAngle + endAngle) / 2.0;
        double rad = Math.toRadians(90 - midAngle);
        double x = CENTER_X + midRadius * Math.cos(rad);
        double y = CENTER_Y - midRadius * Math.sin(rad);
        Color textColor = getTextColorForDepth(depth);
        g.setColor(textColor);
        g.setFont(new Font("Arial", Font.PLAIN, depth == 0 ? 12 : 10));

        String displayText = node.getName();
        if (displayText.length() > 8) {
            displayText = displayText.substring(0, 8) + "...";
        }
        AffineTransform originalTransform = g.getTransform();

        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        if (midAngle > 90 && midAngle < 270) {
            transform.rotate(Math.toRadians(midAngle + 180));
        } else {
            transform.rotate(Math.toRadians(midAngle));
        }

        g.setTransform(transform);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(displayText);
        g.drawString(displayText, -textWidth / 2, 0);
        if (depth >= 1) {
            String percentText = String.format("%.1f%%", node.getValue() * 100);
            g.setFont(new Font("Arial", Font.PLAIN, 9));
            int percentWidth = g.getFontMetrics().stringWidth(percentText);
            g.drawString(percentText, -percentWidth / 2, 12);
        }
        g.setTransform(originalTransform);
    }

    /**
     * 绘制标题
     */
    private void drawTitle(SVGGraphics2D g, String titleText) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        drawCenteredString(g, titleText, CENTER_X, 40);
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D g, JSunburstData root) {
        int legendX = 50;
        int legendY = 665;
        g.setColor(new Color(85, 85, 85));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("数据分类图例", legendX, legendY - 10);
        List<JSunburstData> legendNodes = new ArrayList<>();
        collectLegendNodes(root, legendNodes, 0, 2); // 只收集前3层
        int x = legendX, y = legendY;
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < legendNodes.size(); i++) {
            if (i > 0 && i % 4 == 0) {
                x = legendX;
                y += 25;
            }

            JSunburstData node = legendNodes.get(i);
            Color itemColor = getColorForDepth(getNodeDepth(root, node, 0), node.getName());
            g.setColor(itemColor);
            g.fillRect(x, y, 12, 12);
            g.setColor(Color.LIGHT_GRAY);
            g.setStroke(new BasicStroke(1));
            g.drawRect(x, y, 12, 12);
            g.setColor(Color.DARK_GRAY);
            String legendText = String.format("%s (%.1f%%)", node.getName(), node.getValue() * 100);
            g.drawString(legendText, x + 18, y + 10);
            x += 180;
        }
        addNoDataLegendItem(g, x, y);
    }

    /**
     * 添加无数据图例项
     */
    private void addNoDataLegendItem(SVGGraphics2D g, int x, int y) {
        g.setColor(NO_DATA_FILL);
        g.fillRect(x, y, 12, 12);
        g.setColor(NO_DATA_STROKE);
        g.setStroke(new BasicStroke(1));
        g.drawRect(x, y, 12, 12);
        g.setColor(Color.DARK_GRAY);
        g.drawString("无数据区域", x + 18, y + 10);
    }

    /**
     * 绘制交互说明
     */
    private void drawInteractionInfo(SVGGraphics2D g) {
        g.setColor(new Color(119, 119, 119));
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("提示：悬停查看详细信息", 400, 775);
    }

    /**
     * 绘制居中文字
     */
    private void drawCenteredString(SVGGraphics2D g, String text, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g.drawString(text, x - textWidth / 2, y + textHeight / 2 - fm.getDescent());
    }


    /**
     * 根据深度和节点信息获取颜色
     */
    private Color getColorForDepth(int depth, String name) {
        int hash = Math.abs((depth * 31 + name.hashCode())) % THEME_COLORS.length;
        return THEME_COLORS[hash];
    }

    /**
     * 根据深度获取文字颜色
     */
    private Color getTextColorForDepth(int depth) {
        return depth < 2 ? new Color(51, 51, 51) : Color.WHITE;
    }

    /**
     * 收集图例节点
     */
    private void collectLegendNodes(JSunburstData node, List<JSunburstData> nodes, int depth, int maxDepth) {
        if (depth > maxDepth) return;
        for (JSunburstData child : node.getChildren()) {
            nodes.add(child);
            collectLegendNodes(child, nodes, depth + 1, maxDepth);
        }
    }

    /**
     * 获取节点深度
     */
    private int getNodeDepth(JSunburstData root, JSunburstData target, int currentDepth) {
        if (root == target) return currentDepth;
        for (JSunburstData child : root.getChildren()) {
            int depth = getNodeDepth(child, target, currentDepth + 1);
            if (depth != -1) return depth;
        }
        return -1;
    }
}
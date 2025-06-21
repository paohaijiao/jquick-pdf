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
package com.paohaijiao.echart.sunburst;
import com.paohaijiao.data.JOption;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


/**
 * packageName com.paohaijiao.echart.generate
 *
 * @author Martin
 * @version 1.0.0
 * @className SunburstChart
 * @date 2025/6/14
 * @description
 */
public class JSunburstChart extends JAbstractChartRenderer {
    private static class SunburstNode {
        String name;
        Color color;
        Color borderColor;
        List<SunburstNode> children = new ArrayList<>();
        double value = 1; // Default value
        double startAngle;
        double endAngle;
        int depth;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        // Parse data and build tree
        SunburstNode root = buildTreeFromOption(option);
        calculateTreeAngles(root, 0, 2 * Math.PI);

        // Draw title
        drawTitle(svgGenerator, option, width);

        // Draw sunburst with multiple levels
        int[] levelRadii = calculateRadii(centerX, centerY);
        drawMultiLevelSunburst(svgGenerator, root, centerX, centerY, levelRadii);
    }

    private SunburstNode buildTreeFromOption(JOption option) {
        SunburstNode root = new SunburstNode();
        root.depth = -1;
        String[] categories = {"Flora", "Fruity", "Sour/Fermented", "Green/Vegetative",
                "Other", "Roasted", "Spices", "Nutty/Cocoa", "Sweet"};
        Color[] colors = {
                Color.decode("#da0d68"), Color.decode("#da1d23"), Color.decode("#ebb40f"),
                Color.decode("#187a2f"), Color.decode("#0aa3b5"), Color.decode("#c94930"),
                Color.decode("#ad213e"), Color.decode("#a87b64"), Color.decode("#e65832")
        };

        for (int i = 0; i < categories.length; i++) {
            SunburstNode category = new SunburstNode();
            category.name = categories[i];
            category.color = colors[i];
            category.borderColor = colors[i].darker();
            category.depth = 0;

            // 添加子节点
            int subCount = 3;
            for (int j = 0; j < subCount; j++) {
                SunburstNode subcategory = new SunburstNode();
                subcategory.name = "Sub-" + categories[i].charAt(0) + (j+1);
                subcategory.color = interpolateColor(colors[i], Color.WHITE, 0.3f);
                subcategory.borderColor = colors[i].darker();
                subcategory.depth = 1;
                subcategory.value = 1 + Math.random() * 2;

                category.children.add(subcategory);
            }

            root.children.add(category);
        }

        return root;
    }

    private int[] calculateRadii(int centerX, int centerY) {
        int maxRadius = Math.min(centerX, centerY) - 20;
        return new int[]{maxRadius/4, maxRadius/2, maxRadius*3/4, maxRadius};
    }

    private double calculateTreeAngles(SunburstNode node, double startAngle, double endAngle) {
        node.startAngle = startAngle;

        if (node.children.isEmpty()) {
            node.endAngle = endAngle;
            return node.value > 0 ? node.value : 1;
        }

        double total = 0;
        for (SunburstNode child : node.children) {
            total += child.value > 0 ? child.value : 1;
        }

        double currentStart = startAngle;
        for (SunburstNode child : node.children) {
            double childValue = child.value > 0 ? child.value : 1;
            double childSpan = (endAngle - startAngle) * (childValue / total);
            calculateTreeAngles(child, currentStart, currentStart + childSpan);
            currentStart += childSpan;
        }

        node.endAngle = endAngle;
        return total;
    }

    private void drawMultiLevelSunburst(SVGGraphics2D g, SunburstNode root, int centerX, int centerY, int[] levelRadii) {
        drawNodeArcs(g, root, centerX, centerY, levelRadii);
    }

    private void drawNodeArcs(SVGGraphics2D g, SunburstNode node, int centerX, int centerY, int[] levelRadii) {
        if (node.depth >= 0 && node.depth < levelRadii.length) {
            int outerRadius = levelRadii[node.depth];
            int innerRadius = node.depth > 0 ? levelRadii[node.depth - 1] : 0;

            // 绘制扇形
            g.setPaint(node.color);
            g.fillArc(centerX - outerRadius, centerY - outerRadius,
                    outerRadius * 2, outerRadius * 2,
                    (int)Math.toDegrees(node.startAngle),
                    (int)Math.toDegrees(node.endAngle - node.startAngle));

            // 绘制边框
            g.setPaint(node.borderColor);
            g.drawArc(centerX - outerRadius, centerY - outerRadius,
                    outerRadius * 2, outerRadius * 2,
                    (int)Math.toDegrees(node.startAngle),
                    (int)Math.toDegrees(node.endAngle - node.startAngle));
        }

        for (SunburstNode child : node.children) {
            drawNodeArcs(g, child, centerX, centerY, levelRadii);
        }
    }

    // Helper methods
    private static Point2D getPoint(int centerX, int centerY, int radius, double angle) {
        return new Point2D.Double(
                centerX + radius * Math.cos(angle),
                centerY + radius * Math.sin(angle)
        );
    }

    private static Color interpolateColor(Color c1, Color c2, float ratio) {
        int r = (int)(c1.getRed() * ratio + c2.getRed() * (1 - ratio));
        int g = (int)(c1.getGreen() * ratio + c2.getGreen() * (1 - ratio));
        int b = (int)(c1.getBlue() * ratio + c2.getBlue() * (1 - ratio));
        return new Color(r, g, b);
    }
}

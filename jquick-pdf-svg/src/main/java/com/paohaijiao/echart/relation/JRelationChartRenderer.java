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
package com.paohaijiao.echart.relation;
import com.paohaijiao.data.JOption;
import com.paohaijiao.data.json.JGsonOption;
import com.paohaijiao.data.series.JGraph;
import com.paohaijiao.data.series.JSeries;
import com.paohaijiao.data.series.force.JCategory;
import com.paohaijiao.data.series.force.JLink;
import com.paohaijiao.data.series.force.JNode;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.*;
import java.util.List;

/**
 * packageName com.paohaijiao.echart.generate
 *
 * @author Martin
 * @version 1.0.0
 * @className JHeatMap
 * @date 2025/6/13
 * @description
 */
public class JRelationChartRenderer extends JAbstractChartRenderer {
    private static final Font LABEL_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);
    private static final BasicStroke LINK_STROKE = new BasicStroke(1.5f);
    private static final float CURVENESS = 0.3f;

    private static final Color[] CATEGORY_COLORS = {
            new Color(31, 119, 180),  // 蓝色
            new Color(255, 127, 14),  // 橙色
            new Color(44, 160, 44),   // 绿色
            new Color(214, 39, 40),   // 红色
            new Color(148, 103, 189), // 紫色
            new Color(140, 86, 75),   // 棕色
            new Color(227, 119, 194), // 粉色
            new Color(127, 127, 127), // 灰色
            new Color(188, 189, 34),  // 橄榄色
            new Color(23, 190, 207)   // 青色
    };

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption opt, int width, int height) {
        JGsonOption jGsonOption=(JGsonOption)opt;
        svgGenerator.setPaint(BACKGROUND_COLOR); // 设置背景
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, opt, width);// 绘制标题
        List<JSeries> series = jGsonOption.getSeries(); // 获取图形数据
        if (series == null||series.isEmpty()) {
            return;
        }
        JGraph graph=(JGraph)series.get(0);

        List<JNode> nodes =  graph.getData();
        List<JLink> links = graph.getLinks();
        List<JCategory> categories = graph.getCategories();
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        Map<String, Point> nodePositions = calculateNodePositions(nodes, width, height);        // 计算节点位置（这里简化处理，实际应该使用力导向布局算法）
        drawLinks(svgGenerator, nodes, links, nodePositions);// 绘制连接线
        drawNodes(svgGenerator, nodes, categories, nodePositions);// 绘制节点
        if (categories != null && !categories.isEmpty()) {// 绘制图例（可选）
            drawLegend(svgGenerator, categories, width, height);
        }
    }

    private Map<String, Point> calculateNodePositions(List<JNode> nodes, int width, int height) {
        Map<String, Point> positions = new HashMap<>();
        Random random = new Random();
        int padding = 100;
        for (Map<String, Object> node : nodes) {
            String id = (String) node.get("id");
            double x = node.containsKey("x") ? ((Number) node.get("x")).doubleValue() :
                    padding + random.nextInt(width - 2 * padding);
            double y = node.containsKey("y") ? ((Number) node.get("y")).doubleValue() :
                    padding + random.nextInt(height - 2 * padding);
            // 缩放坐标以适应画布
            x = width * 0.1 + x * width * 0.8 / 1000;
            y = height * 0.1 + y * height * 0.8 / 1000;
            positions.put(id, new Point((int)x, (int)y));
        }
        return positions;
    }

    private void drawLinks(SVGGraphics2D svgGenerator, List<JNode> nodes,
                           List<JLink> links, Map<String, Point> nodePositions) {
        if (links == null || links.isEmpty()) {
            return;
        }
        for (JLink link : links) {
            String source = link.getSource().toString();
            String target = link.getTarget().toString();
            Point start = nodePositions.get(source);
            Point end = nodePositions.get(target);
            if (start != null && end != null) {
                // 获取源节点颜色
                Color sourceColor = getNodeColor(nodes, link, source);
                svgGenerator.setPaint(sourceColor);
                svgGenerator.setStroke(LINK_STROKE);
                // 绘制曲线连接线
                drawCurvedLine(svgGenerator, start, end, CURVENESS);
            }
        }
    }

    private void drawCurvedLine(SVGGraphics2D svgGenerator, Point start, Point end, float curveness) {
        // 计算控制点
        int ctrlX1 = start.x + (int) ((end.x - start.x) * 0.5);
        int ctrlY1 = start.y + (int) ((end.y - start.y) * curveness);
        int ctrlX2 = end.x - (int) ((end.x - start.x) * 0.5);
        int ctrlY2 = end.y - (int) ((end.y - start.y) * curveness);
        Path2D path = new Path2D.Double();// 使用 Path2D 创建三次贝塞尔曲线
        path.moveTo(start.x, start.y);
        path.curveTo(ctrlX1, ctrlY1, ctrlX2, ctrlY2, end.x, end.y);
        // 绘制曲线
        svgGenerator.draw(path);
    }
    private void drawNodes(SVGGraphics2D svgGenerator, List<JNode> nodes,
                           List<JCategory> categories, Map<String, Point> nodePositions) {
        svgGenerator.setFont(LABEL_FONT);

        for (Map<String, Object> node : nodes) {
            String id = (String) node.get("id");
            String name = (String) node.get("name");
            int symbolSize = ((Number) node.get("symbolSize")).intValue();
            int category = node.containsKey("category") ? ((Number) node.get("category")).intValue() : 0;

            Point position = nodePositions.get(id);
            if (position != null) {
                // 获取节点颜色
                Color nodeColor = getCategoryColor(category);

                // 绘制节点
                svgGenerator.setPaint(nodeColor);
                svgGenerator.fillOval(position.x - symbolSize/2, position.y - symbolSize/2,
                        symbolSize, symbolSize);

                // 绘制节点边框
                svgGenerator.setPaint(Color.BLACK);
                svgGenerator.drawOval(position.x - symbolSize/2, position.y - symbolSize/2,
                        symbolSize, symbolSize);

                // 绘制节点标签
                if (name != null && !name.isEmpty()) {
                    svgGenerator.setPaint(Color.BLACK);
                    int labelX = position.x + symbolSize/2 + 5;
                    int labelY = position.y + 5;
                    svgGenerator.drawString(name, labelX, labelY);
                }
            }
        }
    }

    private void drawLegend(SVGGraphics2D svgGenerator, List<JCategory> categories,
                            int width, int height) {
        int legendX = width - 150;
        int legendY = 50;
        int itemHeight = 20;

        svgGenerator.setFont(LABEL_FONT);

        for (int i = 0; i < categories.size(); i++) {
            JCategory category = categories.get(i);
            String name = category.getName();

            // 绘制图例颜色标记
            Color color = getCategoryColor(i);
            svgGenerator.setPaint(color);
            svgGenerator.fillRect(legendX, legendY + i * itemHeight, 15, 15);

            // 绘制图例文本
            svgGenerator.setPaint(Color.BLACK);
            svgGenerator.drawString(name, legendX + 20, legendY + 12 + i * itemHeight);
        }
    }

    private Color getNodeColor(List<JNode> nodes, JLink link, String nodeId) {
        // 这里可以根据需要实现更复杂的颜色逻辑
        // 简单实现：返回节点的类别颜色
        for (Map<String, Object> node : nodes) {
            if (node.get("id").equals(nodeId)) {
                int category = node.containsKey("category") ? ((Number) node.get("category")).intValue() : 0;
                return getCategoryColor(category);
            }
        }
        return CATEGORY_COLORS[0];
    }

    private Color getCategoryColor(int categoryIndex) {
        return CATEGORY_COLORS[categoryIndex % CATEGORY_COLORS.length];
    }

    @Override
    protected int getDefaultWidth() {
        return 800;
    }

    @Override
    protected int getDefaultHeight() {
        return 600;
    }


}

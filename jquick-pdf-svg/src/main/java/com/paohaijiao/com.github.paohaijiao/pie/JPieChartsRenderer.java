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
package com.paohaijiao.echart.pie;

import com.paohaijiao.data.JOption;
import com.paohaijiao.data.data.JData;
import com.paohaijiao.data.series.JPie;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JPieChartsRenderer extends JAbstractChartRenderer {
    private static final Color[] PIE_COLORS = {
            new Color(65, 105, 225),
            new Color(220, 20, 60),
            new Color(34, 139, 34),
            new Color(255, 165, 0),
            new Color(138, 43, 226),
            new Color(255, 215, 0)
    };
    private static final int LEGEND_ITEM_HEIGHT = 25;
    private static final int LEGEND_COLOR_BOX_WIDTH = 20;
    private static final int LEGEND_COLOR_BOX_HEIGHT = 15;
    private static final double LABEL_DISTANCE_FACTOR = 1.2;

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        // 设置背景
        svgGenerator.setPaint(BACKGROUND_COLOR);
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);// 绘制标题
        if (option.series() == null || option.series().isEmpty() || !(option.series().get(0) instanceof JPie)) {
            return;// 检查是否有饼图数据
        }
        JPie pieSeries = (JPie) option.series().get(0);      // 获取饼图数据
        List<JData> data = pieSeries.data();
        if (data == null || data.isEmpty()) {
            return;
        }
        int centerX = width / 2;// 计算饼图中心位置和半径
        int centerY = height / 2 + 30; // 向下偏移30像素给标题留空间
        int radius = Math.min(width, height) / 3;

        // 计算总值
        double total = data.stream()
                .mapToDouble(d -> Double.parseDouble(d.value().toString()))
                .sum();
        drawPieSectors(svgGenerator, data, total, centerX, centerY, radius);// 绘制饼图扇形
        drawLegend(svgGenerator, data, width, height);   // 绘制图例
    }

    private void drawPieSectors(SVGGraphics2D svgGenerator, List<JData> data,
                                double total, int centerX, int centerY, int radius) {
        double startAngle = 0;
        for (int i = 0; i < data.size(); i++) {
            JData item = data.get(i);
            double value = Double.parseDouble(item.value().toString());
            double extent = 360 * (value / total);
            // 绘制扇形
            svgGenerator.setPaint(PIE_COLORS[i % PIE_COLORS.length]);
            svgGenerator.fillArc(centerX - radius, centerY - radius,
                    radius * 2, radius * 2,
                    (int) startAngle, (int) extent);

            // 绘制标签
            drawSectorLabel(svgGenerator, item, value, total,
                    centerX, centerY, radius, startAngle, extent);

            startAngle += extent;
        }
    }

    private void drawSectorLabel(SVGGraphics2D svgGenerator, JData item, double value,
                                 double total, int centerX, int centerY, int radius,
                                 double startAngle, double extent) {
        double midAngle = Math.toRadians(startAngle + extent / 2);
        int labelX = centerX + (int) (radius * LABEL_DISTANCE_FACTOR * Math.cos(midAngle));
        int labelY = centerY + (int) (radius * LABEL_DISTANCE_FACTOR * Math.sin(midAngle));

        svgGenerator.setPaint(Color.BLACK);
        String label = String.format("%s: %.1f%%", item.name(), (value / total * 100));
        int textWidth = svgGenerator.getFontMetrics().stringWidth(label);
        // 根据标签位置调整绘制方向
        if (labelX > centerX) {
            svgGenerator.drawString(label, labelX, labelY);
        } else {
            svgGenerator.drawString(label, labelX - textWidth, labelY);
        }
    }

    private void drawLegend(SVGGraphics2D svgGenerator, List<JData> data, int width, int height) {
        int legendX = 50;
        int legendY = height - 100;
        svgGenerator.setFont(LABEL_FONT);
        for (int i = 0; i < data.size(); i++) {
            JData item = data.get(i);
            // 绘制颜色方块
            svgGenerator.setPaint(PIE_COLORS[i % PIE_COLORS.length]);
            svgGenerator.fillRect(legendX, legendY + i * LEGEND_ITEM_HEIGHT,
                    LEGEND_COLOR_BOX_WIDTH, LEGEND_COLOR_BOX_HEIGHT);
            // 绘制图例文本
            svgGenerator.setPaint(Color.BLACK);
            svgGenerator.drawString(item.name() + " (" + item.value() + ")",
                    legendX + LEGEND_COLOR_BOX_WIDTH + 5,
                    legendY + 12 + i * LEGEND_ITEM_HEIGHT);
        }
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

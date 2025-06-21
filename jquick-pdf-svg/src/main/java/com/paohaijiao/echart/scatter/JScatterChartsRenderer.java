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
package com.paohaijiao.echart.scatter;

import com.paohaijiao.data.JOption;
import com.paohaijiao.data.data.JData;
import com.paohaijiao.data.series.JScatter;
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

public class JScatterChartsRenderer  extends JAbstractChartRenderer {
    private static final Color SCATTER_COLOR = new Color(65, 105, 225); // 钢蓝色
    private static final Color AXIS_COLOR = Color.BLACK;
    private static final Font AXIS_LABEL_FONT = new Font("Microsoft YaHei", Font.PLAIN, 10);
    private static final Font TITLE_FONT = new Font("Microsoft YaHei", Font.BOLD, 14);
    private static final BasicStroke AXIS_STROKE = new BasicStroke(1.5f);
    private static final int MARGIN = 60;
    private static final double AXIS_PADDING_FACTOR = 0.1;

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {

        svgGenerator.setPaint(BACKGROUND_COLOR);// 设置背景
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);// 绘制标题
        // 检查散点图数据
        if (option.series() == null || option.series().isEmpty() || !(option.series().get(0) instanceof JScatter)) {
            return;
        }
        JScatter scatter = (JScatter) option.series().get(0);
        List<JData> dataList = scatter.data();
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        // 计算数据范围
        double[] ranges = calculateDataRanges(dataList);
        double minX = ranges[0];
        double maxX = ranges[1];
        double minY = ranges[2];
        double maxY = ranges[3];
        drawAxes(svgGenerator, minX, maxX, minY, maxY, width, height); // 绘制坐标轴
        drawScatters(svgGenerator, dataList, minX, maxX, minY, maxY, width, height, (int)scatter.symbolSize()); // 绘制散点
    }

    private double[] calculateDataRanges(List<JData> dataList) {
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        for (JData data : dataList) {
            List<Object> point = (List<Object>) data.value();
            double x = ((Number) point.get(0)).doubleValue();
            double y = ((Number) point.get(1)).doubleValue();
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        double xRange = maxX - minX;
        double yRange = maxY - minY;
        minX -= xRange * AXIS_PADDING_FACTOR;
        maxX += xRange * AXIS_PADDING_FACTOR;
        minY -= yRange * AXIS_PADDING_FACTOR;
        maxY += yRange * AXIS_PADDING_FACTOR;
        return new double[]{minX, maxX, minY, maxY};
    }

    private void drawAxes(SVGGraphics2D svgGenerator, double minX, double maxX,
                          double minY, double maxY, int width, int height) {
        int plotWidth = width - 2 * MARGIN;
        int plotHeight = height - 2 * MARGIN;
        // 设置轴样式
        svgGenerator.setPaint(AXIS_COLOR);
        svgGenerator.setStroke(AXIS_STROKE);
        svgGenerator.setFont(AXIS_LABEL_FONT);
        // 绘制X轴
        svgGenerator.drawLine(MARGIN, height - MARGIN, width - MARGIN, height - MARGIN);
        // 绘制Y轴
        svgGenerator.drawLine(MARGIN, MARGIN, MARGIN, height - MARGIN);
        // 绘制X轴刻度
        for (double x = Math.ceil(minX); x <= Math.floor(maxX); x++) {
            int xPos = MARGIN + (int) ((x - minX) / (maxX - minX) * plotWidth);
            svgGenerator.drawLine(xPos, height - MARGIN, xPos, height - MARGIN + 5);
            svgGenerator.drawString(String.format("%.1f", x), xPos - 10, height - MARGIN + 20);
        }
        // 绘制Y轴刻度
        for (double y = Math.ceil(minY); y <= Math.floor(maxY); y++) {
            int yPos = height - MARGIN - (int) ((y - minY) / (maxY - minY) * plotHeight);
            svgGenerator.drawLine(MARGIN - 5, yPos, MARGIN, yPos);
            svgGenerator.drawString(String.format("%.1f", y), MARGIN - 40, yPos + 5);
        }
    }

    private void drawScatters(SVGGraphics2D svgGenerator, List<JData> dataList,
                              double minX, double maxX, double minY, double maxY,
                              int width, int height, double symbolSize) {
        int plotWidth = width - 2 * MARGIN;
        int plotHeight = height - 2 * MARGIN;
        int halfSize = (int) symbolSize / 2;
        svgGenerator.setPaint(SCATTER_COLOR);

        for (JData data : dataList) {
            List<Object> point = (List<Object>) data.value();
            double x = ((Number) point.get(0)).doubleValue();
            double y = ((Number) point.get(1)).doubleValue();
            int xPos = MARGIN + (int) ((x - minX) / (maxX - minX) * plotWidth);
            int yPos = height - MARGIN - (int) ((y - minY) / (maxY - minY) * plotHeight);
            svgGenerator.fillOval(xPos - halfSize, yPos - halfSize, (int) symbolSize, (int) symbolSize);
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

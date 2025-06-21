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
package com.paohaijiao.echart.line;

import com.paohaijiao.data.JOption;
import com.paohaijiao.data.axis.JCategoryAxis;
import com.paohaijiao.data.axis.JValueAxis;
import com.paohaijiao.data.series.JLine;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class JLineChartsRenderer extends JAbstractChartRenderer {
    private static final Color DEFAULT_LINE_COLOR = Color.BLUE;
    private static final Color DATA_POINT_COLOR = Color.BLUE;
    private static final Font AXIS_LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final int DATA_POINT_RADIUS = 3;

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        // 设置背景
        svgGenerator.setPaint(BACKGROUND_COLOR);
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);      // 绘制标题
        drawAxes(svgGenerator, option, width, height);    // 绘制坐标轴
        drawSeries(svgGenerator, option, width, height);// 绘制折线系列
    }

    private void drawAxes(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        int marginLeft = 80;
        int marginRight = 50;
        int marginTop = 60;
        int marginBottom = 80;
        int chartWidth = width - marginLeft - marginRight;
        int chartHeight = height - marginTop - marginBottom;

        // 设置轴线和标签颜色
        svgGenerator.setColor(AXIS_COLOR);
        svgGenerator.setFont(AXIS_LABEL_FONT);

        // 绘制X轴
        svgGenerator.drawLine(marginLeft, height - marginBottom, width - marginRight, height - marginBottom);

        // 绘制Y轴
        svgGenerator.drawLine(marginLeft, height - marginBottom, marginLeft, marginTop);

        // 绘制X轴标签
        if (option.getxAxis() != null && !option.getxAxis().isEmpty()
                && option.getxAxis().get(0) instanceof JCategoryAxis) {
            JCategoryAxis xAxis = (JCategoryAxis) option.getxAxis().get(0);
            List<String> xData = xAxis.getData();
            if (xData != null && !xData.isEmpty()) {
                int xStep = chartWidth / (xData.size() + 1);
                for (int i = 0; i < xData.size(); i++) {
                    String label = xData.get(i);
                    int labelWidth = svgGenerator.getFontMetrics().stringWidth(label);
                    svgGenerator.drawString(label,
                            marginLeft + i * xStep - labelWidth/2,
                            height - marginBottom + 20);
                }
            }
        }

        // 绘制Y轴标签
        if (option.getyAxis() != null && !option.getyAxis().isEmpty()
                && option.getyAxis().get(0) instanceof JValueAxis) {
            JValueAxis yAxis = (JValueAxis) option.getyAxis().get(0);
            double maxValue = yAxis.getMax() != null ?(double) yAxis.getMax() : 250;
            int yStepCount = 5;
            double yStepValue = maxValue / yStepCount;

            for (int i = 0; i <= yStepCount; i++) {
                double value = i * yStepValue;
                String valueStr = String.format("%.1f", value);
                int labelWidth = svgGenerator.getFontMetrics().stringWidth(valueStr);
                svgGenerator.drawString(valueStr,
                        marginLeft - labelWidth - 5,
                        height - marginBottom - (int)(i * (chartHeight / (double)yStepCount) + 5));
            }
        }
    }

    private void drawSeries(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        if (option.getSeries() == null || option.getSeries().isEmpty()) {
            return;
        }

        int marginLeft = 80;
        int marginRight = 50;
        int marginTop = 60;
        int marginBottom = 80;
        int chartWidth = width - marginLeft - marginRight;
        int chartHeight = height - marginTop - marginBottom;

        for (Object seriesObj : option.getSeries()) {
            if (seriesObj instanceof JLine) {
                JLine line = (JLine) seriesObj;
                List<?> data = line.getData();
                if (data != null && !data.isEmpty()) {
                    // 获取系列颜色，如果没有则使用默认颜色
                    Color lineColor =  DEFAULT_LINE_COLOR;
                    //line.getColor() != null ?
                    //   new Color(line.getColor().getRGB()) :
                    svgGenerator.setColor(lineColor);
                    // 计算Y轴范围
                    double[] yRange = calculateYRange(option);
                    double minValue = yRange[0];
                    double maxValue = yRange[1];
                    double valueRange = maxValue - minValue;
                    int xStep = chartWidth / (data.size() + 1);
                    // 绘制折线
                    for (int i = 0; i < data.size() - 1; i++) {
                        Number yValue = (Number) data.get(i);
                        Number nextYValue = (Number) data.get(i + 1);

                        int x1 = marginLeft + i * xStep;
                        int y1 = height - marginBottom - (int)((yValue.doubleValue() - minValue) / valueRange * chartHeight);
                        int x2 = marginLeft + (i + 1) * xStep;
                        int y2 = height - marginBottom - (int)((nextYValue.doubleValue() - minValue) / valueRange * chartHeight);

                        svgGenerator.drawLine(x1, y1, x2, y2);
                    }

                    // 绘制数据点
                    svgGenerator.setColor(DATA_POINT_COLOR);
                    for (int i = 0; i < data.size(); i++) {
                        Number yValue = (Number) data.get(i);
                        int x = marginLeft + i * xStep;
                        int y = height - marginBottom - (int)((yValue.doubleValue() - minValue) / valueRange * chartHeight);
                        svgGenerator.fillOval(x - DATA_POINT_RADIUS, y - DATA_POINT_RADIUS,
                                DATA_POINT_RADIUS * 2, DATA_POINT_RADIUS * 2);
                    }
                }
            }
        }
    }

    private double[] calculateYRange(JOption option) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // 从Y轴配置获取范围
        if (option.getyAxis() != null && !option.getyAxis().isEmpty()
                && option.getyAxis().get(0) instanceof JValueAxis) {
            JValueAxis yAxis = (JValueAxis) option.getyAxis().get(0);
            if (yAxis.getMin() != null) min =(double) yAxis.getMin();
            if (yAxis.getMax() != null) max = (double) yAxis.getMax();
        }

        // 如果Y轴没有配置范围，则从数据中计算
        if (min == Double.MAX_VALUE || max == Double.MIN_VALUE) {
            for (Object seriesObj : option.getSeries()) {
                if (seriesObj instanceof JLine) {
                    JLine line = (JLine) seriesObj;
                    List<?> data = line.getData();
                    if (data != null) {
                        for (Object item : data) {
                            if (item instanceof Number) {
                                double value = ((Number) item).doubleValue();
                                min = Math.min(min, value);
                                max = Math.max(max, value);
                            }
                        }
                    }
                }
            }
        }

        // 添加一些边距
        double margin = (max - min) * 0.1;
        return new double[]{min - margin, max + margin};
    }

    @Override
    protected int getDefaultWidth() {
        return 600;
    }

    @Override
    protected int getDefaultHeight() {
        return 400;
    }
}

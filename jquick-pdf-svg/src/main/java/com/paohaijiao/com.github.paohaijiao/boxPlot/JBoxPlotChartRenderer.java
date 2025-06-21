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
package com.paohaijiao.echart.boxPlot;
import com.paohaijiao.data.JOption;
import com.paohaijiao.data.series.JBoxplot;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.paohaijiao.echart.generate
 *
 * @author Martin
 * @version 1.0.0
 * @className JBoxPlotChartRenderer
 * @date 2025/6/13
 * @description
 */
public class JBoxPlotChartRenderer extends JAbstractChartRenderer {
    private static final Color BOX_FILL_COLOR = new Color(100, 149, 237);
    private static final Color OUTLIER_COLOR = Color.RED;

    @Override
    protected void drawChart(SVGGraphics2D svg, JOption option, int width, int height) {
        drawTitle(svg, option, width);
        int margin = calculateMargin(width, height);   // 计算边距和绘图区域
        int plotWidth = width - 2 * margin;
        int plotHeight = height - 2 * margin;
        BoxplotDataExtractor extractor = new BoxplotDataExtractor(option);// 提取数据
        drawAxes(svg, margin, width, height, plotWidth, plotHeight);// 绘制坐标轴
        drawBoxplots(svg, extractor, margin, width, height, plotWidth, plotHeight);// 绘制盒须图
    }

    private int calculateMargin(int width, int height) {
        return Math.min(width, height) / 10;
    }

    private void drawAxes(SVGGraphics2D svg, int margin,
                          int width, int height,
                          int plotWidth, int plotHeight) {
        svg.setColor(AXIS_COLOR);
        svg.drawLine(margin, height - margin, width - margin, height - margin); // X轴
        svg.drawLine(margin, margin, margin, height - margin); // Y轴
    }

    private void drawBoxplots(SVGGraphics2D svg, BoxplotDataExtractor extractor,
                              int margin, int width, int height,
                              int plotWidth, int plotHeight) {
        int boxWidth = 30;
        int categoryCount = extractor.getCategories().size();
        int spacing = plotWidth / (categoryCount + 1);
        svg.setFont(LABEL_FONT);
        for (int i = 0; i < categoryCount; i++) {
            double[] box = extractor.getBoxData(i);
            String category = extractor.getCategories().get(i);
            // 计算坐标
            int yMin = scaleToPlot(box[0], height, margin, plotHeight, extractor.getValueRange());
            int yQ1 = scaleToPlot(box[1], height, margin, plotHeight, extractor.getValueRange());
            int yMedian = scaleToPlot(box[2], height, margin, plotHeight, extractor.getValueRange());
            int yQ3 = scaleToPlot(box[3], height, margin, plotHeight, extractor.getValueRange());
            int yMax = scaleToPlot(box[4], height, margin, plotHeight, extractor.getValueRange());
            int x = margin + (i + 1) * spacing - boxWidth / 2;
            // 绘制须线
            svg.setColor(AXIS_COLOR);
            svg.drawLine(x + boxWidth/2, yMin, x + boxWidth/2, yMax);
            svg.drawLine(x, yMin, x + boxWidth, yMin);
            svg.drawLine(x, yMax, x + boxWidth, yMax);
            // 绘制箱体
            svg.setColor(BOX_FILL_COLOR);
            svg.fillRect(x, yQ3, boxWidth, yQ1 - yQ3);
            svg.setColor(AXIS_COLOR);
            svg.drawRect(x, yQ3, boxWidth, yQ1 - yQ3);
            // 绘制中位数
            svg.drawLine(x, yMedian, x + boxWidth, yMedian);
            // 绘制类别标签
            svg.drawString(category, x, height - margin + 20);
            // 绘制异常值（如果有）
            if (box.length > 5) {
                svg.setColor(OUTLIER_COLOR);
                for (int j = 5; j < box.length; j++) {
                    int yOutlier = scaleToPlot(box[j], height, margin, plotHeight, extractor.getValueRange());
                    svg.fillOval(x + boxWidth/2 - 3, yOutlier - 3, 6, 6);
                }
            }
        }
        // 绘制Y轴刻度
        drawYAxisTicks(svg, margin, height, plotHeight, extractor.getValueRange());
    }

    private int scaleToPlot(double value, int height, int margin,
                            int plotHeight, ValueRange valueRange) {
        return (int)(height - margin - (value - valueRange.min) / valueRange.range * plotHeight);
    }

    private void drawYAxisTicks(SVGGraphics2D svg, int margin,
                                int height, int plotHeight,
                                ValueRange valueRange) {
        int tickCount = 5;
        double tickStep = valueRange.range / (tickCount - 1);
        for (int i = 0; i < tickCount; i++) {
            double value = valueRange.min + i * tickStep;
            int y = scaleToPlot(value, height, margin, plotHeight, valueRange);
            svg.drawString(String.format("%.1f", value), margin - 40, y);
            svg.drawLine(margin - 5, y, margin, y);
        }
    }

    /**
     * 值范围辅助类
     */
    private static class ValueRange {
        final double min;
        final double max;
        final double range;
        ValueRange(double min, double max) {
            this.min = min;
            this.max = max;
            this.range = max - min;
        }
    }

    /**
     * Option数据提取器
     */
    private static class BoxplotDataExtractor {
        private final List<String> categories;
        private final List<double[]> boxData;
        private final ValueRange valueRange;
        private final String title;

        public BoxplotDataExtractor(JOption option) {
            this.categories = option.getxAxis() != null ?
                    (List<String>) option.getxAxis().get(0).getData() : new ArrayList<>();

            this.boxData = new ArrayList<>();
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            if (option.getSeries() != null) {
                for (Object series : option.getSeries()) {
                    if (series instanceof JBoxplot) {
                        JBoxplot boxplot = (JBoxplot) series;
                        for (Object data : boxplot.getData()) {
                            if (data instanceof Object[]) {
                                Object[] values = (Object[]) data;
                                double[] box = new double[values.length];
                                for (int i = 0; i < values.length; i++) {
                                    box[i] = ((Number) values[i]).doubleValue();
                                    if (i < 5) { // 只考虑min,Q1,median,Q3,max计算范围
                                        min = Math.min(min, box[i]);
                                        max = Math.max(max, box[i]);
                                    }
                                }
                                boxData.add(box);
                            }
                        }
                    }
                }
            }

            // 确保有合理的值范围
            if (min == Double.MAX_VALUE) min = 0;
            if (max == Double.MIN_VALUE) max = 100;
            this.valueRange = new ValueRange(min, max);
            this.title = option.getTitle() != null ? option.getTitle().getText() : "";
        }

        public List<String> getCategories() {
            return categories;
        }

        public double[] getBoxData(int index) {
            return boxData.get(index);
        }

        public ValueRange getValueRange() {
            return valueRange;
        }

        public String getTitle() {
            return title;
        }
    }

}

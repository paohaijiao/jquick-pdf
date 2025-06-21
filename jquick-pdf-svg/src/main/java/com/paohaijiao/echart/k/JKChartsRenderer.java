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
package com.paohaijiao.echart.k;

import com.paohaijiao.data.JOption;
import com.paohaijiao.data.axis.JCategoryAxis;
import com.paohaijiao.data.series.JCandlestick;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.util.List;

public class JKChartsRenderer extends JAbstractChartRenderer{
    private static final Color RISING_COLOR = Color.RED;
    private static final Color FALLING_COLOR = Color.GREEN;
    private static final Color CANDLE_BORDER_COLOR = Color.BLACK;
    private static final Font AXIS_LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(BACKGROUND_COLOR);// 设置背景
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width);// 绘制标题
        drawAxes(svgGenerator, option, width, height);// 绘制坐标轴
        drawCandlestickData(svgGenerator, option, width, height); // 绘制K线数据
    }

    private void drawAxes(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        int marginLeft = 80;
        int marginRight = 80;
        int marginTop = 60;
        int marginBottom = 80;
        int chartWidth = width - marginLeft - marginRight;
        int chartHeight = height - marginTop - marginBottom;
        svgGenerator.setColor(AXIS_COLOR);// 绘制X轴
        svgGenerator.drawLine(marginLeft, height - marginBottom, width - marginRight, height - marginBottom);
        // 绘制Y轴
        svgGenerator.drawLine(marginLeft, height - marginBottom, marginLeft, marginTop);
        // 设置轴标签字体
        svgGenerator.setFont(AXIS_LABEL_FONT);
        // 绘制X轴标签
        if (option.getxAxis() != null && !option.getxAxis().isEmpty()
                && option.getxAxis().get(0) instanceof JCategoryAxis) {
            JCategoryAxis xAxis = (JCategoryAxis) option.getxAxis().get(0);
            List<String> xData = xAxis.getData();
            if (xData != null && !xData.isEmpty()) {
                int xStep = chartWidth / xData.size();
                for (int i = 0; i < xData.size(); i++) {
                    if (i % 2 == 0) { // 每隔一个显示标签，避免拥挤
                        String label = xData.get(i);
                        int labelWidth = svgGenerator.getFontMetrics().stringWidth(label);
                        svgGenerator.drawString(label,
                                marginLeft + i * xStep - labelWidth/2,
                                height - marginBottom + 20);
                    }
                }
            }
        }

        // 绘制Y轴标签
        svgGenerator.drawString("价格", marginLeft - 40, marginTop + 20);
    }
    private void drawCandlestickData(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        if (option.getSeries() == null || option.getSeries().isEmpty()) {
            return;
        }
        int marginLeft = 80;
        int marginRight = 80;
        int marginTop = 60;
        int marginBottom = 80;
        int chartWidth = width - marginLeft - marginRight;
        int chartHeight = height - marginTop - marginBottom;
        for (Object seriesObj : option.getSeries()) {
            if (seriesObj instanceof JCandlestick) {
                JCandlestick candlestick = (JCandlestick) seriesObj;
                List<?> data = candlestick.getData();
                if (data != null && !data.isEmpty()) {
                    double[] priceRange = calculatePriceRange(data);
                    double minPrice = priceRange[0];
                    double maxPrice = priceRange[1];
                    double priceDiff = maxPrice - minPrice;
                    int xStep = chartWidth / data.size();
                    int candleWidth = Math.max(5, xStep / 2);
                    for (int i = 0; i < data.size(); i++) {
                        Object item = data.get(i);
                        if (item instanceof Object[]) {
                            Object[] values = (Object[]) item;
                            if (values.length >= 4) {
                                double open = ((Number) values[0]).doubleValue();
                                double close = ((Number) values[1]).doubleValue();
                                double low = ((Number) values[2]).doubleValue();
                                double high = ((Number) values[3]).doubleValue();
                                int x = marginLeft + i * xStep;
                                int yOpen = height - marginBottom - (int)((open - minPrice) / priceDiff * chartHeight);
                                int yClose = height - marginBottom - (int)((close - minPrice) / priceDiff * chartHeight);
                                int yLow = height - marginBottom - (int)((low - minPrice) / priceDiff * chartHeight);
                                int yHigh = height - marginBottom - (int)((high - minPrice) / priceDiff * chartHeight);
                                boolean isRising = close >= open;
                                svgGenerator.setColor(isRising ? RISING_COLOR : FALLING_COLOR);
                                svgGenerator.drawLine(x, yHigh, x, yLow);// 绘制上下影线
                                int candleTop = Math.min(yOpen, yClose);// 绘制蜡烛实体
                                int candleHeight = Math.abs(yOpen - yClose);
                                candleHeight = Math.max(candleHeight, 1); // 确保最小高度
                                svgGenerator.fillRect(x - candleWidth/2, candleTop, candleWidth, candleHeight);
                                svgGenerator.setColor(CANDLE_BORDER_COLOR);// 绘制蜡烛边框
                                svgGenerator.drawRect(x - candleWidth/2, candleTop, candleWidth, candleHeight);
                            }
                        }
                    }
                }
            }
        }
    }

    private double[] calculatePriceRange(List<?> data) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Object item : data) {
            if (item instanceof Object[]) {
                Object[] values = (Object[]) item;
                if (values.length >= 4) {
                    double low = ((Number) values[2]).doubleValue();
                    double high = ((Number) values[3]).doubleValue();

                    min = Math.min(min, low);
                    max = Math.max(max, high);
                }
            }
        }

        // 添加一些边距
        double margin = (max - min) * 0.1;
        return new double[]{min - margin, max + margin};
    }

    @Override
    protected int getDefaultWidth() {
        return 800;
    }

    @Override
    protected int getDefaultHeight() {
        return 500;
    }


}

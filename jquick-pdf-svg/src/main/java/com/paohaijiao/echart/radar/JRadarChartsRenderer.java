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
package com.paohaijiao.echart.radar;

import com.paohaijiao.data.JOption;
import com.paohaijiao.data.JRadar;
import com.paohaijiao.data.series.JRadarSeries;
import com.paohaijiao.echart.provider.JAbstractChartRenderer;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JRadarChartsRenderer extends JAbstractChartRenderer {
    private static final Color RADAR_BACKGROUND_COLOR = new Color(220, 220, 220);
    private static final Color RADAR_AXIS_COLOR = Color.GRAY;
    private static final Color RADAR_DATA_COLOR = Color.BLUE;
    private static final BasicStroke RADAR_BACKGROUND_STROKE = new BasicStroke(1);
    private static final BasicStroke RADAR_DATA_STROKE = new BasicStroke(2);
    private static final int RADAR_LEVELS = 5;
    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        svgGenerator.setPaint(BACKGROUND_COLOR);// 设置背景
        svgGenerator.fillRect(0, 0, width, height);
        drawTitle(svgGenerator, option, width); // 绘制标题
        // 检查雷达图数据
        if (option.getRadar() == null || option.getSeries() == null || option.getSeries().isEmpty()) {
            return;
        }
        JRadar radar = option.getRadar();
        List<JRadar.Indicator> indicators = radar.getIndicator();
        JRadarSeries series = (JRadarSeries) option.getSeries().get(0);
        List<Number> dataPoints = series.getData();
        if (indicators == null || indicators.isEmpty() || dataPoints == null || dataPoints.isEmpty()) {
            return;
        }
        int centerX = width / 2;// 计算中心点和半径
        int centerY = height / 2;
        int radius = Math.min(width, height) / 3;
        // 绘制雷达图背景
        drawRadarBackground(svgGenerator, centerX, centerY, radius, indicators.size());
        // 绘制雷达图数据
        drawRadarData(svgGenerator, centerX, centerY, radius, indicators, dataPoints);
    }

    private void drawRadarBackground(SVGGraphics2D svgGenerator, int centerX, int centerY,
                                     int maxRadius, int sides) {
        svgGenerator.setPaint(RADAR_BACKGROUND_COLOR);// 绘制雷达图背景多边形
        svgGenerator.setStroke(RADAR_BACKGROUND_STROKE);
        for (int level = 1; level <= RADAR_LEVELS; level++) {
            int currentRadius = maxRadius * level / RADAR_LEVELS;
            drawPolygon(svgGenerator, centerX, centerY, currentRadius, sides);
        }
        svgGenerator.setPaint(RADAR_AXIS_COLOR);// 绘制雷达图轴线
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            int x = centerX + (int) (maxRadius * Math.sin(angle));
            int y = centerY - (int) (maxRadius * Math.cos(angle));
            svgGenerator.drawLine(centerX, centerY, x, y);
        }
    }

    private void drawRadarData(SVGGraphics2D svgGenerator, int centerX, int centerY,
                               int maxRadius, List<JRadar.Indicator> indicators,
                               List<Number> dataPoints) {
        int[] xPoints = new int[indicators.size()];
        int[] yPoints = new int[indicators.size()];
        svgGenerator.setPaint(RADAR_DATA_COLOR);
        svgGenerator.setStroke(RADAR_DATA_STROKE);
        for (int i = 0; i < indicators.size(); i++) {
            double angle = 2 * Math.PI * i / indicators.size();
            double value = dataPoints.get(i).doubleValue();
            double maxValue = (int)indicators.get(i).getMax();
            int radius = (int) (maxRadius * (value / maxValue));
            xPoints[i] = centerX + (int) (radius * Math.sin(angle));
            yPoints[i] = centerY - (int) (radius * Math.cos(angle));
        }
        svgGenerator.drawPolygon(xPoints, yPoints, indicators.size());// 绘制数据多边形
        Color fillColor = new Color(RADAR_DATA_COLOR.getRed(),
                RADAR_DATA_COLOR.getGreen(),
                RADAR_DATA_COLOR.getBlue(), 100);// 填充数据区域（半透明）
        svgGenerator.setPaint(fillColor);
        svgGenerator.fillPolygon(xPoints, yPoints, indicators.size());
    }

    private void drawPolygon(SVGGraphics2D svgGenerator, int centerX, int centerY,
                             int radius, int sides) {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            xPoints[i] = centerX + (int) (radius * Math.sin(angle));
            yPoints[i] = centerY - (int) (radius * Math.cos(angle));
        }
        svgGenerator.drawPolygon(xPoints, yPoints, sides);
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

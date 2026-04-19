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
package com.github.paohaijiao.combol;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.util.List;

@Data
public class JDoubleRadarChartRenderer extends JAbstractChartRenderer {

    public static final Color[] DEFAULT_COLORS = {

            new Color(84, 112, 198),  // 蓝色

            new Color(250, 200, 88),  // 橙黄色

            new Color(238, 102, 102), // 红色

            new Color(80, 180, 150),  // 绿色

            new Color(159, 120, 196), // 紫色

            new Color(255, 150, 90),  // 橙色

            new Color(97, 187, 211),  // 青色

            new Color(230, 130, 170)  // 粉色
    };

    private final LayoutParams layoutParams;

    private JDoubleRadarChartData config;

    public JDoubleRadarChartRenderer() {
        this.layoutParams = new LayoutParams();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 1000;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 600;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        if (option == null || option.getData() == null) {
            drawErrorMessage(svgGenerator, width, height, "错误：图表数据为空");
            return;
        }
        Object dataObj = option.getData();
        if (!(dataObj instanceof JDoubleRadarChartData)) {
            drawErrorMessage(svgGenerator, width, height, "错误：数据类型错误！期望 JDoubleRadarChartData，实际: " + dataObj.getClass().getSimpleName());
            return;
        }
        JDoubleRadarChartData config = (JDoubleRadarChartData) dataObj;
        // 验证必要字段
        if (config.getDimensions() == null || config.getDimensions().isEmpty()) {
            drawErrorMessage(svgGenerator, width, height, "错误：维度标签(dimensions)为空");
            return;
        }
        if (config.getLeftRadar() == null || config.getRightRadar() == null) {
            drawErrorMessage(svgGenerator, width, height, "错误：左右雷达图数据为空");
            return;
        }
        this.config = config;
        updateConfigDimensions(width, height);
        config.updateMaxValues();
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);
        drawTitle(svgGenerator, option, width);
        // 绘制左侧雷达图
        drawSingleRadar(svgGenerator, layoutParams.leftRadarX, layoutParams.radarCenterY, layoutParams.radarRadius, config.getLeftRadar(), config.getLeftTitle() != null ? config.getLeftTitle() : "左侧雷达图");
        // 绘制右侧雷达图
        drawSingleRadar(svgGenerator, layoutParams.rightRadarX, layoutParams.radarCenterY, layoutParams.radarRadius, config.getRightRadar(), config.getRightTitle() != null ? config.getRightTitle() : "右侧雷达图");
        drawLegend(svgGenerator);
        drawFooter(svgGenerator);
    }

    private void drawErrorMessage(SVGGraphics2D svg, int width, int height, String message) {
        svg.setPaint(Color.WHITE);
        svg.fillRect(0, 0, width, height);
        svg.setPaint(Color.RED);
        svg.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        FontMetrics fm = svg.getFontMetrics();
        svg.drawString(message, width / 2 - fm.stringWidth(message) / 2, height / 2);
        svg.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        String tip = "请使用 JDoubleRadarChartData 对象传入数据";
        fm = svg.getFontMetrics();
        svg.drawString(tip, width / 2 - fm.stringWidth(tip) / 2, height / 2 + 30);
    }

    private void updateConfigDimensions(int width, int height) {
        this.config.setWidth(width);
        this.config.setHeight(height);
    }

    private void calculateLayout() {
        int width = config.getWidth();
        int height = config.getHeight();
        // 计算标题占用的高度
        int titleHeight = 0;
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 45;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 28;
            }
        } else {
            titleHeight = 20;
        }
        // 底部空间
        int leftSeriesCount = config.getLeftRadar().getSeriesList().size();
        int rightSeriesCount = config.getRightRadar().getSeriesList().size();
        int maxSeriesCount = Math.max(leftSeriesCount, rightSeriesCount);
        int legendHeight = (maxSeriesCount <= 4) ? 60 : 80;
        int footerHeight = (config.getFooterText() != null && !config.getFooterText().isEmpty()) ? 35 : 10;
        int bottomSpace = legendHeight + footerHeight;
        // 计算雷达图半径 - 确保有足够空间
        int maxRadiusByWidth = (width / 2) - 80;
        int maxRadiusByHeight = height - titleHeight - bottomSpace - 60;
        int radarRadius = Math.min(maxRadiusByWidth, maxRadiusByHeight) / 2;
        radarRadius = Math.max(radarRadius, 100);  // 最小半径100px
        // 左右雷达图中心点
        int leftRadarX = width / 4;
        int rightRadarX = width * 3 / 4;
        int radarCenterY = titleHeight + radarRadius + 50;
        // 图例Y坐标
        int legendY = radarCenterY + radarRadius + 40;
        // 保存布局参数
        layoutParams.radarRadius = radarRadius;
        layoutParams.leftRadarX = leftRadarX;
        layoutParams.rightRadarX = rightRadarX;
        layoutParams.radarCenterY = radarCenterY;
        layoutParams.legendY = legendY;
        layoutParams.footerY = height - 18;
        layoutParams.titleHeight = titleHeight;
    }

    private void drawSingleRadar(SVGGraphics2D svg, int centerX, int centerY, int radius, JDoubleRadarChartData.RadarData radarData, String title) {
        List<String> dimensions = config.getDimensions();
        int dimensionCount = dimensions.size();
        double angleStep = 2 * Math.PI / dimensionCount;
        // 绘制雷达图标题
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getTextColor());
        FontMetrics fm = svg.getFontMetrics();
        svg.drawString(title, centerX - fm.stringWidth(title) / 2, centerY - radius - 15);
        // 绘制网格层
        int gridLevels = config.getGridLevels();
        for (int level = 1; level <= gridLevels; level++) {
            double levelRadius = radius * level / (double) gridLevels;
            int[] xPoints = new int[dimensionCount];
            int[] yPoints = new int[dimensionCount];
            for (int i = 0; i < dimensionCount; i++) {
                double angle = -Math.PI / 2 + i * angleStep;
                xPoints[i] = (int) (centerX + levelRadius * Math.cos(angle));
                yPoints[i] = (int) (centerY + levelRadius * Math.sin(angle));
            }
            svg.setPaint(config.getGridColor());
            svg.setStroke(new BasicStroke(1));
            svg.drawPolygon(xPoints, yPoints, dimensionCount);
        }
        // 绘制径向线和维度标签
        svg.setFont(config.getAxisFont());
        for (int i = 0; i < dimensionCount; i++) {
            double angle = -Math.PI / 2 + i * angleStep;
            int endX = (int) (centerX + radius * Math.cos(angle));
            int endY = (int) (centerY + radius * Math.sin(angle));
            // 径向线
            svg.setPaint(config.getGridColor());
            svg.drawLine(centerX, centerY, endX, endY);
            // 维度标签
            String label = dimensions.get(i);
            int labelX = (int) (centerX + (radius + 12) * Math.cos(angle));
            int labelY = (int) (centerY + (radius + 5) * Math.sin(angle));
            svg.setPaint(config.getLabelColor());
            double cos = Math.cos(angle);
            if (cos > 0.1) {
                svg.drawString(label, labelX, labelY + 4);
            } else if (cos < -0.1) {
                int stringWidth = fm.stringWidth(label);
                svg.drawString(label, labelX - stringWidth, labelY + 4);
            } else {
                svg.drawString(label, labelX - fm.stringWidth(label) / 2, angle > 0 ? labelY + 12 : labelY - 4);
            }
        }
        // 绘制数值刻度标签
        svg.setFont(config.getAxisFont().deriveFont(Font.PLAIN, 10));
        svg.setPaint(new Color(136, 136, 136));
        double maxValue = radarData.getMaxValue();
        for (int level = 1; level <= gridLevels; level++) {
            double value = maxValue * level / gridLevels;
            String valueLabel = formatValue(value);
            double levelRadius = radius * level / (double) gridLevels;
            int labelX = (int) (centerX + levelRadius * Math.cos(-Math.PI / 2 + 0.15));
            int labelY = (int) (centerY + levelRadius * Math.sin(-Math.PI / 2 + 0.15));
            svg.drawString(valueLabel, labelX - 8, labelY - 5);
        }
        // 绘制数据系列
        List<JDoubleRadarChartData.Series> seriesList = radarData.getSeriesList();
        for (int seriesIdx = 0; seriesIdx < seriesList.size(); seriesIdx++) {
            JDoubleRadarChartData.Series series = seriesList.get(seriesIdx);
            List<Double> values = series.getValues();
            int[] xPoints = new int[dimensionCount];
            int[] yPoints = new int[dimensionCount];
            for (int i = 0; i < dimensionCount; i++) {
                double value = values.get(i);
                double ratio = Math.min(1.0, value / maxValue);
                double pointRadius = radius * ratio;
                double angle = -Math.PI / 2 + i * angleStep;
                xPoints[i] = (int) (centerX + pointRadius * Math.cos(angle));
                yPoints[i] = (int) (centerY + pointRadius * Math.sin(angle));
            }
            Color seriesColor = series.getColor();
            if (seriesColor == null) {
                seriesColor = DEFAULT_COLORS[seriesIdx % DEFAULT_COLORS.length];
            }
            // 填充区域
            Color fillColor = new Color(seriesColor.getRed(), seriesColor.getGreen(), seriesColor.getBlue(), config.getFillAlpha());
            svg.setPaint(fillColor);
            svg.fillPolygon(xPoints, yPoints, dimensionCount);
            // 边框
            svg.setPaint(seriesColor);
            svg.setStroke(new BasicStroke(config.getLineWidth()));
            svg.drawPolygon(xPoints, yPoints, dimensionCount);
            // 数据点
            if (config.isShowDataPoints()) {
                for (int i = 0; i < dimensionCount; i++) {
                    double value = values.get(i);
                    double ratio = Math.min(1.0, value / maxValue);
                    double pointRadius = radius * ratio;
                    double angle = -Math.PI / 2 + i * angleStep;
                    int pointX = (int) (centerX + pointRadius * Math.cos(angle));
                    int pointY = (int) (centerY + pointRadius * Math.sin(angle));
                    svg.setPaint(seriesColor);
                    svg.fillOval(pointX - 4, pointY - 4, 8, 8);
                    svg.setPaint(Color.WHITE);
                    svg.fillOval(pointX - 2, pointY - 2, 4, 4);
                }
            }
        }
        // 绘制中心点
        svg.setPaint(config.getAxisColor());
        svg.fillOval(centerX - 3, centerY - 3, 6, 6);
    }

    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getBackgroundColor());
        svg.fillRect(0, 0, config.getWidth(), config.getHeight());
    }

    private void drawLegend(SVGGraphics2D svg) {
        int width = config.getWidth();
        List<JDoubleRadarChartData.Series> leftSeries = config.getLeftRadar().getSeriesList();
        List<JDoubleRadarChartData.Series> rightSeries = config.getRightRadar().getSeriesList();
        int totalSeriesCount = leftSeries.size() + rightSeries.size();
        int itemsPerRow = Math.min(6, totalSeriesCount);
        int legendItemWidth = 130;
        int totalLegendWidth = itemsPerRow * legendItemWidth;
        int legendStartX = (width - totalLegendWidth) / 2;
        int rectSize = 14;
        int rowHeight = 28;
        int legendCount = 0;
        for (int i = 0; i < leftSeries.size(); i++) {
            JDoubleRadarChartData.Series series = leftSeries.get(i);
            int row = legendCount / itemsPerRow;
            int col = legendCount % itemsPerRow;
            int legendX = legendStartX + col * legendItemWidth;
            int legendY = layoutParams.legendY + row * rowHeight;
            Color seriesColor = series.getColor();
            if (seriesColor == null) {
                seriesColor = DEFAULT_COLORS[i % DEFAULT_COLORS.length];
            }
            svg.setPaint(seriesColor);
            svg.fillRoundRect(legendX, legendY - rectSize, rectSize, rectSize, 3, 3);
            svg.setPaint(config.getTextColor());
            svg.setFont(config.getLegendFont());
            String legendText = series.getName() + (config.isShowLegendSide() ? " (左)" : "");
            svg.drawString(legendText, legendX + rectSize + 6, legendY);
            legendCount++;
        }

        for (int i = 0; i < rightSeries.size(); i++) {
            JDoubleRadarChartData.Series series = rightSeries.get(i);
            int row = legendCount / itemsPerRow;
            int col = legendCount % itemsPerRow;
            int legendX = legendStartX + col * legendItemWidth;
            int legendY = layoutParams.legendY + row * rowHeight;
            Color seriesColor = series.getColor();
            if (seriesColor == null) {
                seriesColor = DEFAULT_COLORS[(leftSeries.size() + i) % DEFAULT_COLORS.length];
            }
            svg.setPaint(seriesColor);
            svg.fillRoundRect(legendX, legendY - rectSize, rectSize, rectSize, 3, 3);
            svg.setPaint(config.getTextColor());
            svg.setFont(config.getLegendFont());
            String legendText = series.getName() + (config.isShowLegendSide() ? " (右)" : "");
            svg.drawString(legendText, legendX + rectSize + 6, legendY);
            legendCount++;
        }
    }

    private void drawFooter(SVGGraphics2D svg) {
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            svg.setFont(config.getFooterFont());
            svg.setPaint(config.getFooterColor());
            String footer = config.getFooterText();
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(footer, config.getWidth() / 2 - fm.stringWidth(footer) / 2, layoutParams.footerY);
        }
    }

    @Override
    protected void drawTitle(SVGGraphics2D svgGenerator, JOption option, int width) {
        String title = config.getTitleText();
        String subtitle = config.getSubtitleText();
        if (title != null && !title.isEmpty()) {
            svgGenerator.setFont(config.getTitleFont());
            svgGenerator.setPaint(config.getTextColor());
            FontMetrics fm = svgGenerator.getFontMetrics();
            svgGenerator.drawString(title, width / 2 - fm.stringWidth(title) / 2, 38);
            if (subtitle != null && !subtitle.isEmpty()) {
                svgGenerator.setFont(config.getSubtitleFont());
                fm = svgGenerator.getFontMetrics();
                svgGenerator.drawString(subtitle, width / 2 - fm.stringWidth(subtitle) / 2, 62);
            }
        }
    }

    private String formatValue(double value) {
        if (config.isValueWithPercent()) {
            if (value == (int) value) {
                return (int) value + "%";
            } else {
                return String.format("%.1f%%", value);
            }
        } else {
            if (value == (int) value) {
                return String.valueOf((int) value);
            } else {
                return String.format("%.1f", value);
            }
        }
    }

    @Data
    private static class LayoutParams {

        int radarRadius;

        int leftRadarX;

        int rightRadarX;

        int radarCenterY;

        int legendY;

        int footerY;

        int titleHeight;
    }
}
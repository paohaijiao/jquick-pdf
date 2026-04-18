package com.github.paohaijiao.combol;/*
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
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 多折线图渲染器 - 支持多条折线对比显示
 * 完全自适应布局，根据数据量自动调整所有元素位置和大小
 */
@Data
public class JMultiLineChartRenderer extends JAbstractChartRenderer {

    private final LayoutParams layoutParams;

    private JMultiLineChartData config;

    public JMultiLineChartRenderer() {
        this.layoutParams = new LayoutParams();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 800;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 600;
    }
    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JMultiLineChartData config = (JMultiLineChartData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getLineDataList(), "line data list require not null");
        JAssert.notNull(config.getXAxisLabels(), "xAxisLabels require not null");
        int dataCount = config.getXAxisLabels().size();
        for (JMultiLineChartData.LineData lineData : config.getLineDataList()) {
            JAssert.notNull(lineData.getValues(), "line data values require not null");
            JAssert.isTrue(lineData.getValues().size() == dataCount, "all line data must have same length as xAxisLabels");
        }

        this.config = config;
        updateConfigDimensions(width, height);
        config.updateMaxValues();
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);
        drawGridAndAxes(svgGenerator);
        drawAllLines(svgGenerator);
        drawXAxisLabels(svgGenerator);
        drawLegend(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
        drawAxisTitles(svgGenerator);
    }
    /**
     * 更新配置中的宽高
     */
    private void updateConfigDimensions(int width, int height) {
        this.config.setWidth(width);
        this.config.setHeight(height);
    }
    /**
     * 计算自适应布局参数
     */
    private void calculateLayout() {
        int dataCount = config.getDataCount();
        int width = config.getWidth();
        int height = config.getHeight();
        // 计算标题占用的高度
        int titleHeight = 0;
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 40;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 25;
            }
        } else {
            titleHeight = 30;
        }
        // 图例和底部说明占用的高度
        int legendHeight = config.getLineDataList().size() <= 4 ? 50 : 70;
        int footerHeight = 30;
        int xAxisLabelHeight = 40;
        // 计算Y轴标签宽度
        FontMetrics fm = getDefaultFontMetrics();
        int maxYLabelWidth = 0;
        if (fm != null) {
            String maxLabel = formatValue(config.getMaxValue());
            maxYLabelWidth = fm.stringWidth(maxLabel) + 15;
        }
        // 计算可用图表区域
        int topMargin = titleHeight;
        int bottomMargin = xAxisLabelHeight + legendHeight + footerHeight;
        int leftMargin = Math.max(60, maxYLabelWidth);
        int rightMargin = 40;
        int chartTop = topMargin;
        int chartBottom = height - bottomMargin;
        int chartLeft = leftMargin;
        int chartRight = width - rightMargin;
        int chartWidth = chartRight - chartLeft;
        int chartHeight = chartBottom - chartTop;
        // 计算X轴标签间距
        int step;
        int pointStartX;
        if (dataCount <= 1) {
            step = chartWidth / 2;
            pointStartX = chartLeft + chartWidth / 2;
        } else {
            step = chartWidth / (dataCount - 1);
            pointStartX = chartLeft;
        }
        // 保存布局参数
        layoutParams.chartTop = chartTop;
        layoutParams.chartBottom = chartBottom;
        layoutParams.chartLeft = chartLeft;
        layoutParams.chartRight = chartRight;
        layoutParams.chartWidth = chartWidth;
        layoutParams.chartHeight = chartHeight;
        layoutParams.step = step;
        layoutParams.pointStartX = pointStartX;
        layoutParams.xAxisLabelY = chartBottom + 25;
        layoutParams.legendY = chartBottom + 45;
        layoutParams.footerY = height - 15;
        layoutParams.topMargin = topMargin;
        layoutParams.bottomMargin = bottomMargin;
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
    }

    /**
     * 获取默认字体度量
     */
    private FontMetrics getDefaultFontMetrics() {
        try {
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setFont(config.getAxisFont());
            FontMetrics fm = g2d.getFontMetrics();
            g2d.dispose();
            return fm;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 绘制图表背景
     */
    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getChartAreaColor());
        svg.fillRect(layoutParams.chartLeft - 5, layoutParams.chartTop - 5, layoutParams.chartWidth + 10, layoutParams.chartHeight + 10);
    }

    /**
     * 绘制网格线和Y轴
     */
    private void drawGridAndAxes(SVGGraphics2D svg) {
        int gridCount = config.getGridCount();
        double maxValue = config.getMaxValue();
        svg.setStroke(new BasicStroke(1));
        // 绘制水平网格线和Y轴标签
        for (int i = 0; i <= gridCount; i++) {
            int y = layoutParams.chartTop + (int) ((double) i / gridCount * layoutParams.chartHeight);
            double value = maxValue * (1 - (double) i / gridCount);
            // 网格线
            svg.setPaint(config.getGridColor());
            svg.drawLine(layoutParams.chartLeft, y, layoutParams.chartRight, y);
            // Y轴标签
            svg.setFont(config.getAxisFont());
            svg.setPaint(config.getTextColor());
            String label = formatValue(value);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layoutParams.chartLeft - fm.stringWidth(label) - 8, y + 5);
        }

        // 绘制Y轴轴线
        svg.setPaint(config.getAxisColor());
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartTop, layoutParams.chartLeft, layoutParams.chartBottom);
        // 绘制X轴轴线
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartBottom, layoutParams.chartRight, layoutParams.chartBottom);
    }

    /**
     * 绘制所有折线
     */
    private void drawAllLines(SVGGraphics2D svg) {
        for (JMultiLineChartData.LineData lineData : config.getLineDataList()) {
            drawSingleLine(svg, lineData);
        }
    }

    /**
     * 绘制单条折线
     */
    private void drawSingleLine(SVGGraphics2D svg, JMultiLineChartData.LineData lineData) {
        List<Double> values = lineData.getValues();
        double maxValue = config.getMaxValue();
        // 收集所有点坐标
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            int x = layoutParams.pointStartX + i * layoutParams.step;
            double value = values.get(i);
            int y = layoutParams.chartBottom - (int) ((value / maxValue) * layoutParams.chartHeight);
            y = Math.max(layoutParams.chartTop + 2, Math.min(layoutParams.chartBottom - 2, y));
            points.add(new Point(x, y));
        }
        // 绘制折线
        if (points.size() >= 2) {
            Path2D path = new Path2D.Double();
            path.moveTo(points.get(0).x, points.get(0).y);
            for (int i = 1; i < points.size(); i++) {
                path.lineTo(points.get(i).x, points.get(i).y);
            }
            svg.setPaint(lineData.getLineColor());
            svg.setStroke(new BasicStroke(lineData.getLineWidth()));
            svg.draw(path);
        }
        // 绘制数据点和标签
        int pointRadius = config.getPointRadius();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            double value = values.get(i);
            // 绘制圆点
            svg.setPaint(lineData.getLineColor());
            svg.fillOval(p.x - pointRadius, p.y - pointRadius, pointRadius * 2, pointRadius * 2);
            // 白色内点
            if (config.isShowInnerPoint()) {
                int innerRadius = config.getInnerPointRadius();
                svg.setPaint(Color.WHITE);
                svg.fillOval(p.x - innerRadius, p.y - innerRadius, innerRadius * 2, innerRadius * 2);
            }
            // 数据标签
            if (config.isShowDataLabels()) {
                svg.setFont(config.getDataLabelFont());
                svg.setPaint(lineData.getLineColor());
                String label = formatValue(value);
                FontMetrics fm = svg.getFontMetrics();
                int labelX = p.x - fm.stringWidth(label) / 2;
                int labelY;
                if (p.y < layoutParams.chartTop + layoutParams.chartHeight / 2) {
                    labelY = p.y + pointRadius + 12;
                } else {
                    labelY = p.y - pointRadius - 5;
                }
                // 边界检查
                if (labelY > layoutParams.chartBottom - 5) {
                    labelY = p.y - pointRadius - 5;
                }
                if (labelY < layoutParams.chartTop + 10) {
                    labelY = p.y + pointRadius + 12;
                }
                svg.drawString(label, labelX, labelY);
            }
        }
    }

    /**
     * 绘制X轴标签
     */
    private void drawXAxisLabels(SVGGraphics2D svg) {
        List<String> labels = config.getXAxisLabels();
        svg.setFont(config.getAxisFont());
        svg.setPaint(config.getTextColor());
        for (int i = 0; i < labels.size(); i++) {
            int x = layoutParams.pointStartX + i * layoutParams.step;
            String label = labels.get(i);
            FontMetrics fm = svg.getFontMetrics();
            int labelX = x - fm.stringWidth(label) / 2;
            // 边界检查
            labelX = Math.max(layoutParams.chartLeft + 2, Math.min(layoutParams.chartRight - fm.stringWidth(label) - 2, labelX));
            // 倾斜角度处理（当标签过多时）
            if (labels.size() > 8 && config.isRotateXAxisLabels()) {
                Graphics2D g2d = svg;
                g2d.translate(x, layoutParams.xAxisLabelY);
                g2d.rotate(-Math.PI / 4);
                g2d.drawString(label, 0, 0);
                g2d.rotate(Math.PI / 4);
                g2d.translate(-x, -layoutParams.xAxisLabelY);
            } else {
                svg.drawString(label, labelX, layoutParams.xAxisLabelY);
            }
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        int width = config.getWidth();
        List<JMultiLineChartData.LineData> lineDataList = config.getLineDataList();
        int legendCount = lineDataList.size();
        // 计算图例总宽度
        int legendItemWidth = 120;
        int totalLegendWidth = legendCount * legendItemWidth;
        int legendStartX = Math.max(20, (width - totalLegendWidth) / 2);
        int itemHeight = 14;
        int lineLength = 30;
        for (int i = 0; i < legendCount; i++) {
            JMultiLineChartData.LineData lineData = lineDataList.get(i);
            int legendX = legendStartX + i * legendItemWidth;
            // 绘制线条示例
            svg.setPaint(lineData.getLineColor());
            svg.setStroke(new BasicStroke(lineData.getLineWidth()));
            svg.drawLine(legendX, layoutParams.legendY - 2, legendX + lineLength, layoutParams.legendY - 2);
            // 绘制圆点示例
            int pointRadius = config.getPointRadius();
            svg.fillOval(legendX + lineLength / 2 - pointRadius, layoutParams.legendY - 2 - pointRadius, pointRadius * 2, pointRadius * 2);
            if (config.isShowInnerPoint()) {
                svg.setPaint(Color.WHITE);
                svg.fillOval(legendX + lineLength / 2 - config.getInnerPointRadius(), layoutParams.legendY - 2 - config.getInnerPointRadius(), config.getInnerPointRadius() * 2, config.getInnerPointRadius() * 2);
            }
            // 绘制图例文字
            svg.setPaint(config.getTextColor());
            svg.setFont(config.getLegendFont());
            svg.drawString(lineData.getLegendText(), legendX + lineLength + 8, layoutParams.legendY);
        }
    }

    /**
     * 绘制底部说明
     */
    private void drawFooter(SVGGraphics2D svg) {
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            svg.setFont(config.getFooterFont());
            svg.setPaint(config.getFooterColor());
            String footer = config.getFooterText();
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(footer, config.getWidth() / 2 - fm.stringWidth(footer) / 2, layoutParams.footerY);
        }
    }

    /**
     * 绘制Y轴标题
     */
    private void drawAxisTitles(SVGGraphics2D svg) {
        if (config.getYAxisTitle() == null || config.getYAxisTitle().isEmpty()) {
            return;
        }
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getTextColor());
        Graphics2D g2d = svg;
        FontMetrics fm = g2d.getFontMetrics();
        // 左侧Y轴标题（垂直旋转）
        int titleX = layoutParams.leftMargin - 35;
        int titleY = config.getHeight() / 2;
        g2d.translate(titleX, titleY);
        g2d.rotate(-Math.PI / 2);
        g2d.drawString(config.getYAxisTitle(), -fm.stringWidth(config.getYAxisTitle()) / 2, 0);
        g2d.rotate(Math.PI / 2);
        g2d.translate(-titleX, -titleY);
    }

    @Override
    protected void drawTitle(SVGGraphics2D svgGenerator, JOption option, int width) {
        String title = config.getTitleText();
        String subtitle = config.getSubtitleText();
        if (title != null && !title.isEmpty()) {
            svgGenerator.setFont(config.getTitleFont());
            svgGenerator.setPaint(config.getTextColor());
            FontMetrics fm = svgGenerator.getFontMetrics();
            svgGenerator.drawString(title, width / 2 - fm.stringWidth(title) / 2, 35);
            if (subtitle != null && !subtitle.isEmpty()) {
                svgGenerator.setFont(config.getSubtitleFont());
                fm = svgGenerator.getFontMetrics();
                svgGenerator.drawString(subtitle, width / 2 - fm.stringWidth(subtitle) / 2, 58);
            }
        }
    }

    /**
     * 格式化数值
     */
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

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {
        int chartTop, chartBottom, chartLeft, chartRight;
        int chartWidth, chartHeight;
        int step, pointStartX;
        int xAxisLabelY, legendY, footerY;
        int topMargin, bottomMargin, leftMargin, rightMargin;
    }
}
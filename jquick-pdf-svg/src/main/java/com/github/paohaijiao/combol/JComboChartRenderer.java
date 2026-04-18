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
 * 复合图表渲染器 - 支持条形图和折线图的组合显示
 * 完全自适应布局，根据数据量自动调整所有元素位置和大小
 */
@Data
public class JComboChartRenderer extends JAbstractChartRenderer {


    private final LayoutParams layoutParams;

    private JComboLineBarChartData config;

    public JComboChartRenderer() {
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
        JComboLineBarChartData config=(JComboLineBarChartData)option.getData();
        JAssert.notNull(config, "config require not  null");
        JAssert.notNull(config.getBarData(), "bar data require not  null");
        JAssert.notNull(config.getLineData(), "line data require not  null");
        JAssert.notNull(config.getXAxisLabels(), "xAxisLabels data require not  null");
        if(config.getBarData().size()!=config.getLineData().size()&&config.getLineData().size()!=config.getXAxisLabels().size()){
            JAssert.throwNewException("the length of data should be the same");
        }
        this.config = config;
        if (config == null) {
            return;
        }
        updateConfigDimensions(width, height);// 更新宽高
        config.updateMaxValues();// 更新最大值
        calculateLayout(); // 计算自适应布局参数
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 启用抗锯齿
        drawChartBackground(svgGenerator);// 绘制图表区域背景
        drawGridAndAxes(svgGenerator);// 绘制网格线和Y轴
        drawBars(svgGenerator); // 绘制条形图
        drawLineChart(svgGenerator);// 绘制折线图
        drawXAxisLabels(svgGenerator);// 绘制X轴标签
        drawLegend(svgGenerator);// 绘制图例
        drawTitle(svgGenerator, option, width);// 绘制标题
        drawFooter(svgGenerator);// 绘制底部说明
        drawAxisTitles(svgGenerator); // 绘制坐标轴标题
    }

    /**
     * 更新配置中的宽高
     */
    private void updateConfigDimensions(int width, int height) {
        this.config.setWidth(width);
        this.config.setHeight(height);
        if (config.getMaxBarValue() > 0 && !config.isAutoCalculateMax()) {
            this.config.setMaxBarValue(config.getMaxBarValue());
        }
        if (config.getMaxLineValue() > 0 && !config.isAutoCalculateMax()) {
            this.config.setMaxLineValue(config.getMaxLineValue());
        }
    }

    /**
     * 计算自适应布局参数
     * 根据数据量动态计算所有位置和尺寸
     */
    private void calculateLayout() {
        int dataCount = config.getDataCount();
        int width = config.getWidth();
        int height = config.getHeight();
        int titleHeight = 0;// 计算标题占用的高度
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 40;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 25;
            }
        } else {
            titleHeight = 30;
        }
        // 计算图例和底部说明占用的高度
        int legendHeight = 50;
        int footerHeight = 30;
        // 计算X轴标签占用的高度
        int xAxisLabelHeight = 30;
        //计算Y轴标签占用的宽度
        FontMetrics fm = getDefaultFontMetrics();
        int maxBarLabelWidth = 0;
        if (fm != null && config.getBarData() != null) {
            double maxVal = config.getMaxBarValue();
            String maxLabel = formatValue(maxVal, false);
            maxBarLabelWidth = fm.stringWidth(maxLabel) + 10;
        }
        int maxLineLabelWidth = 0;
        if (fm != null && config.getLineData() != null) {
            double maxVal = config.getMaxLineValue();
            String maxLabel = formatValue(maxVal, true);
            maxLineLabelWidth = fm.stringWidth(maxLabel) + 10;
        }

        //计算实际可用的图表区域
        int topMargin = titleHeight;
        int bottomMargin = xAxisLabelHeight + legendHeight + footerHeight;
        int leftMargin = Math.max(50, maxBarLabelWidth);
        int rightMargin = Math.max(50, maxLineLabelWidth);
        int chartTop = topMargin;
        int chartBottom = height - bottomMargin;
        int chartLeft = leftMargin;
        int chartRight = width - rightMargin;
        int chartWidth = chartRight - chartLeft;
        int chartHeight = chartBottom - chartTop;
        //根据数据量计算条形宽度和间距
        int barWidth;
        int barGap;
        if (dataCount <= 6) {
            // 数据量少时，使用较大的条形宽度
            barWidth = Math.min(80, chartWidth / (dataCount * 2));
            barGap = Math.min(40, (chartWidth - barWidth * dataCount) / (dataCount - 1));
            if (barGap < 10) barGap = 10;
        } else if (dataCount <= 12) {
            // 中等数据量
            barWidth = Math.min(50, chartWidth / (dataCount * 2));
            barGap = Math.min(25, (chartWidth - barWidth * dataCount) / (dataCount - 1));
            if (barGap < 8) barGap = 8;
        } else {
            // 大量数据
            barWidth = Math.min(35, chartWidth / (dataCount * 2));
            barGap = Math.min(15, (chartWidth - barWidth * dataCount) / (dataCount - 1));
            if (barGap < 5) barGap = 5;
        }
        // 确保条形宽度合理
        barWidth = Math.max(20, barWidth);
        barGap = Math.max(5, barGap);
        // 计算起始位置
        int totalWidth = dataCount * barWidth + (dataCount - 1) * barGap;
        int startX = chartLeft + (chartWidth - totalWidth) / 2;
        int step = barWidth + barGap;
        int pointStartX = startX + barWidth / 2;
        // 保存布局参数
        layoutParams.chartTop = chartTop;
        layoutParams.chartBottom = chartBottom;
        layoutParams.chartLeft = chartLeft;
        layoutParams.chartRight = chartRight;
        layoutParams.chartWidth = chartWidth;
        layoutParams.chartHeight = chartHeight;
        layoutParams.barWidth = barWidth;
        layoutParams.barGap = barGap;
        layoutParams.step = step;
        layoutParams.startX = startX;
        layoutParams.pointStartX = pointStartX;
        layoutParams.topMargin = topMargin;
        layoutParams.bottomMargin = bottomMargin;
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
        layoutParams.xAxisLabelY = chartBottom + 20;
        layoutParams.legendY = chartBottom + 45;
        layoutParams.footerY = height - 15;
    }

    /**
     * 获取默认字体度量（用于计算）
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
     * 绘制网格线和坐标轴
     */
    private void drawGridAndAxes(SVGGraphics2D svg) {
        int gridCount = config.getGridCount();
        svg.setStroke(new BasicStroke(1));
        // 绘制水平网格线和左侧Y轴标签
        for (int i = 0; i <= gridCount; i++) {
            int y = layoutParams.chartTop + (int) ((double) i / gridCount * layoutParams.chartHeight);
            double value = config.getMaxBarValue() * (1 - (double) i / gridCount);
            // 网格线
            svg.setPaint(config.getGridColor());
            svg.drawLine(layoutParams.chartLeft, y, layoutParams.chartRight, y);
            // Y轴标签
            svg.setFont(config.getAxisFont());
            svg.setPaint(config.getTextColor());
            String label = formatValue(value, false);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layoutParams.chartLeft - fm.stringWidth(label) - 8, y + 4);
        }
        // 绘制左侧Y轴轴线
        svg.setPaint(config.getAxisColor());
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartTop, layoutParams.chartLeft, layoutParams.chartBottom);
        // 绘制底部X轴轴线
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartBottom, layoutParams.chartRight, layoutParams.chartBottom);
        // 绘制右侧Y轴轴线和标签
        svg.setPaint(config.getAxisColor());
        svg.drawLine(layoutParams.chartRight, layoutParams.chartTop, layoutParams.chartRight, layoutParams.chartBottom);
        for (int i = 0; i <= gridCount; i++) {
            int y = layoutParams.chartTop + (int) ((double) i / gridCount * layoutParams.chartHeight);
            double value = config.getMaxLineValue() * (1 - (double) i / gridCount);
            svg.setFont(config.getAxisFont());
            svg.setPaint(config.getTextColor());
            String label = formatValue(value, true);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layoutParams.chartRight + 8, y + 4);
        }
    }

    /**
     * 绘制条形图
     */
    private void drawBars(SVGGraphics2D svg) {
        List<Double> barData = config.getBarData();
        double maxBarValue = config.getMaxBarValue();
        for (int i = 0; i < barData.size(); i++) {
            int x = layoutParams.startX + i * layoutParams.step;
            double value = barData.get(i);
            int barHeight = (int) ((value / maxBarValue) * layoutParams.chartHeight);
            barHeight = Math.max(2, barHeight);
            int y = layoutParams.chartBottom - barHeight;
            // 绘制条形
            svg.setPaint(config.getBarColor());
            svg.fillRoundRect(x, y, layoutParams.barWidth, barHeight, 4, 4);
            // 绘制数据标签
            if (config.isShowBarLabels()) {
                svg.setFont(config.getDataLabelFont());
                svg.setPaint(config.getBarColor());
                String label = formatValue(value, false);
                FontMetrics fm = svg.getFontMetrics();
                int labelX = x + layoutParams.barWidth / 2 - fm.stringWidth(label) / 2;
                int labelY = y - 5;
                if (labelY < layoutParams.chartTop + 10) {
                    labelY = y + barHeight + 15;
                }
                svg.drawString(label, labelX, labelY);
            }
        }
    }

    /**
     * 绘制折线图
     */
    private void drawLineChart(SVGGraphics2D svg) {
        List<Double> lineData = config.getLineData();
        double maxLineValue = config.getMaxLineValue();
        // 收集所有点坐标
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < lineData.size(); i++) {
            int x = layoutParams.pointStartX + i * layoutParams.step;
            double value = lineData.get(i);
            int y = layoutParams.chartBottom - (int) ((value / maxLineValue) * layoutParams.chartHeight);
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
            svg.setPaint(config.getLineColor());
            svg.setStroke(config.getLineStroke());
            svg.draw(path);
        }
        // 绘制数据点和标签
        int pointRadius = config.getPointRadius();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            double value = lineData.get(i);
            // 绘制圆点
            svg.setPaint(config.getLineColor());
            svg.fillOval(p.x - pointRadius, p.y - pointRadius, pointRadius * 2, pointRadius * 2);
            // 白色内点
            int innerRadius = config.getInnerPointRadius();
            svg.setPaint(Color.WHITE);
            svg.fillOval(p.x - innerRadius, p.y - innerRadius, innerRadius * 2, innerRadius * 2);
            // 数据标签
            if (config.isShowLineLabels()) {
                svg.setFont(config.getDataLabelFont());
                svg.setPaint(config.getLineColor());
                String label = formatValue(value, true);
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
            svg.drawString(label, labelX, layoutParams.xAxisLabelY);
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        int width = config.getWidth();
        int legendStartX = width / 2 - 150;
        int itemWidth = 16;
        int itemHeight = 16;
        // 条形图图例
        svg.setPaint(config.getBarColor());
        svg.fillRoundRect(legendStartX, layoutParams.legendY - 10, itemWidth, itemHeight, 3, 3);
        svg.setFont(config.getLegendFont());
        svg.setPaint(config.getTextColor());
        svg.drawString(config.getBarLegendText(), legendStartX + itemWidth + 6, layoutParams.legendY);
        // 折线图图例
        int lineLegendX = legendStartX + 180;
        svg.setPaint(config.getLineColor());
        svg.setStroke(config.getLineStroke());
        svg.drawLine(lineLegendX, layoutParams.legendY - 2, lineLegendX + 20, layoutParams.legendY - 2);
        svg.fillOval(lineLegendX + 8, layoutParams.legendY - 6, 8, 8);
        svg.setPaint(Color.WHITE);
        svg.fillOval(lineLegendX + 10, layoutParams.legendY - 4, 4, 4);
        svg.setPaint(config.getTextColor());
        svg.drawString(config.getLineLegendText(), lineLegendX + 28, layoutParams.legendY);
    }

    /**
     * 绘制底部说明
     */
    private void drawFooter(SVGGraphics2D svg) {
        svg.setFont(config.getFooterFont());
        svg.setPaint(config.getFooterColor());
        String footer = this.config.getFooterText();
        FontMetrics fm = svg.getFontMetrics();
        svg.drawString(footer, config.getWidth() / 2 - fm.stringWidth(footer) / 2, layoutParams.footerY);
    }

    /**
     * 绘制坐标轴标题
     */
    private void drawAxisTitles(SVGGraphics2D svg) {
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getTextColor());
        Graphics2D g2d = svg;
        FontMetrics fm = g2d.getFontMetrics();
        // 左侧Y轴标题
        String leftTitle = config.getLeftAxisTitle();
        int leftTitleX = layoutParams.leftMargin - 35;
        int leftTitleY = config.getHeight() / 2;
        g2d.translate(leftTitleX, leftTitleY);
        g2d.rotate(-Math.PI / 2);
        g2d.drawString(leftTitle, -fm.stringWidth(leftTitle) / 2, 0);
        g2d.rotate(Math.PI / 2);
        g2d.translate(-leftTitleX, -leftTitleY);
        // 右侧Y轴标题
        String rightTitle = config.getRightAxisTitle();
        int rightTitleX = config.getWidth() - layoutParams.rightMargin + 30;
        int rightTitleY = config.getHeight() / 2;
        g2d.translate(rightTitleX, rightTitleY);
        g2d.rotate(Math.PI / 2);
        g2d.drawString(rightTitle, -fm.stringWidth(rightTitle) / 2, 0);
        g2d.rotate(-Math.PI / 2);
        g2d.translate(-rightTitleX, -rightTitleY);
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

    private String formatValue(double value, boolean withPercent) {
        if (withPercent) {
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
        int barWidth, barGap, step;
        int startX, pointStartX;
        int topMargin, bottomMargin, leftMargin, rightMargin;
        int xAxisLabelY, legendY, footerY;
    }
}
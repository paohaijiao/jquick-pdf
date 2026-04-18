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
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 多重条形图渲染器 - 支持多组数据并列显示
 * 完全自适应布局，根据数据量自动调整所有元素位置和大小
 * <p>
 * 颜色风格参考:
 * - 产品A: #5470c6 (蓝色)
 * - 产品B: #fac858 (橙黄色)
 * - 产品C: #ee6666 (红色)
 */
@Data
public class JMultiBarChartRenderer extends JAbstractChartRenderer {

    /**
     * 预设颜色方案
     */
    public static final Color COLOR_A = new Color(84, 112, 198);  // #5470c6

    public static final Color COLOR_B = new Color(250, 200, 88);  // #fac858

    public static final Color COLOR_C = new Color(238, 102, 102); // #ee6666

    public static final Color COLOR_BACKGROUND = new Color(249, 249, 249); // #f9f9f9

    public static final Color COLOR_GRID = new Color(221, 221, 221); // #ddd

    public static final Color COLOR_AXIS = new Color(102, 102, 102); // #666

    public static final Color COLOR_TEXT = new Color(51, 51, 51);    // #333

    public static final Color COLOR_LABEL = new Color(85, 85, 85);   // #555

    public static final Color COLOR_YAXIS_TEXT = new Color(136, 136, 136); // #888

    private final LayoutParams layoutParams;

    private JMultiBarChartData config;

    public JMultiBarChartRenderer() {
        this.layoutParams = new LayoutParams();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 800;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 500;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JMultiBarChartData config = (JMultiBarChartData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getBarDataList(), "bar data list require not null");
        JAssert.notNull(config.getXAxisLabels(), "xAxisLabels require not null");
        int dataCount = config.getXAxisLabels().size();
        int groupCount = config.getBarDataList().size();
        for (JMultiBarChartData.BarData barData : config.getBarDataList()) {
            JAssert.notNull(barData.getValues(), "bar data values require not null");
            JAssert.isTrue(barData.getValues().size() == dataCount, "all bar data must have same length as xAxisLabels");
        }
        this.config = config;
        updateConfigDimensions(width, height);
        config.updateMaxValues();
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);
        drawGridAndAxes(svgGenerator);
        drawAllBars(svgGenerator);
        drawXAxisLabels(svgGenerator);
        drawXAxisTitle(svgGenerator);
        drawLegend(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
        drawYAxisTitle(svgGenerator);
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
        int groupCount = config.getBarDataList().size();
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
        int legendHeight = groupCount <= 4 ? 50 : 70;// 图例占用的高度
        int footerHeight = 30;
        int xAxisLabelHeight = 60;  // X轴标签 + 轴标题
        int xAxisTitleHeight = 30;
        // 计算Y轴标签宽度
        FontMetrics fm = getDefaultFontMetrics();
        int maxYLabelWidth = 0;
        if (fm != null) {
            String maxLabel = formatValue(config.getMaxValue());
            maxYLabelWidth = fm.stringWidth(maxLabel) + 15;
        }
        // 计算可用图表区域
        int topMargin = titleHeight;
        int bottomMargin = xAxisLabelHeight + xAxisTitleHeight + legendHeight + footerHeight;
        int leftMargin = Math.max(50, maxYLabelWidth + 5);
        int rightMargin = 40;
        int chartTop = topMargin;
        int chartBottom = height - bottomMargin;
        int chartLeft = leftMargin;
        int chartRight = width - rightMargin;
        int chartWidth = chartRight - chartLeft;
        int chartHeight = chartBottom - chartTop;
        // 计算每组柱子的宽度和间距
        double groupSpacingRatio = config.getGroupSpacingRatio();
        double barSpacingRatio = config.getBarSpacingRatio();
        // 总组数 = dataCount
        double groupUnitWidth = (double) chartWidth / dataCount;
        double groupInnerWidth = groupUnitWidth * (1 - groupSpacingRatio);
        double barWidth = groupInnerWidth / (groupCount + (groupCount - 1) * barSpacingRatio);
        double barSpacing = barWidth * barSpacingRatio;
        // 保存布局参数
        layoutParams.chartTop = chartTop;
        layoutParams.chartBottom = chartBottom;
        layoutParams.chartLeft = chartLeft;
        layoutParams.chartRight = chartRight;
        layoutParams.chartWidth = chartWidth;
        layoutParams.chartHeight = chartHeight;
        layoutParams.groupUnitWidth = groupUnitWidth;
        layoutParams.groupInnerWidth = groupInnerWidth;
        layoutParams.barWidth = barWidth;
        layoutParams.barSpacing = barSpacing;
        layoutParams.groupCount = groupCount;
        layoutParams.dataCount = dataCount;
        layoutParams.xAxisLabelY = chartBottom + 25;
        layoutParams.xAxisTitleY = chartBottom + 55;
        layoutParams.legendY = chartBottom + 75;
        layoutParams.footerY = height - 15;
        layoutParams.yAxisTitleX = leftMargin - 35;
        layoutParams.yAxisTitleY = height / 2;
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
     * 绘制图表背景 (与参考SVG一致: #f9f9f9, 圆角8px)
     */
    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getBackgroundColor());
        svg.fillRoundRect(0, 0, config.getWidth(), config.getHeight(), 8, 8);
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
            // 网格线 (实线为0线，虚线为其他)
            svg.setPaint(config.getGridColor());
            if (i == gridCount) {
                // 底部0线为实线
                svg.drawLine(layoutParams.chartLeft, y, layoutParams.chartRight, y);
            } else {
                // 其他网格线为虚线
                svg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
                svg.drawLine(layoutParams.chartLeft, y, layoutParams.chartRight, y);
                svg.setStroke(new BasicStroke(1));
            }
            // Y轴标签
            svg.setFont(config.getAxisFont());
            svg.setPaint(config.getYAxisTextColor());
            String label = formatValue(value);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layoutParams.chartLeft - fm.stringWidth(label) - 8, y + 5);
        }
        // 重置描边
        svg.setStroke(new BasicStroke(1));
        // 绘制Y轴轴线
        svg.setPaint(config.getAxisColor());
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartTop, layoutParams.chartLeft, layoutParams.chartBottom);

        // 绘制X轴轴线
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartBottom, layoutParams.chartRight, layoutParams.chartBottom);
    }

    /**
     * 绘制所有柱状图组
     */
    private void drawAllBars(SVGGraphics2D svg) {
        List<JMultiBarChartData.BarData> barDataList = config.getBarDataList();
        double maxValue = config.getMaxValue();
        for (int groupIdx = 0; groupIdx < layoutParams.dataCount; groupIdx++) {
            // 计算当前组起始X坐标
            double groupStartX = layoutParams.chartLeft + groupIdx * layoutParams.groupUnitWidth + layoutParams.groupUnitWidth * config.getGroupSpacingRatio() / 2;
            double firstBarStartX = groupStartX;
            for (int barIdx = 0; barIdx < layoutParams.groupCount; barIdx++) {
                JMultiBarChartData.BarData barData = barDataList.get(barIdx);
                List<Double> values = barData.getValues();
                double value = values.get(groupIdx);
                // 计算柱子X坐标
                double barX = firstBarStartX + barIdx * (layoutParams.barWidth + layoutParams.barSpacing);
                int barWidthInt = (int) Math.max(3, layoutParams.barWidth);
                int barXInt = (int) barX;
                // 计算柱子高度和Y坐标
                int barHeight = (int) ((value / maxValue) * layoutParams.chartHeight);
                barHeight = Math.max(1, Math.min(barHeight, layoutParams.chartHeight));
                int barY = layoutParams.chartBottom - barHeight;
                // 边界检查
                if (barXInt + barWidthInt > layoutParams.chartRight) {
                    barWidthInt = layoutParams.chartRight - barXInt;
                }
                if (barWidthInt < 1) continue;
                // 绘制柱子 (带圆角)
                svg.setPaint(barData.getBarColor());
                svg.fillRoundRect(barXInt, barY, barWidthInt, barHeight, 3, 3);
                // 绘制数据标签
                if (config.isShowDataLabels()) {
                    svg.setFont(config.getDataLabelFont());
                    svg.setPaint(barData.getLabelColor() != null ? barData.getLabelColor() : barData.getBarColor());
                    String label = formatValue(value);
                    FontMetrics fm = svg.getFontMetrics();
                    int labelX = barXInt + barWidthInt / 2 - fm.stringWidth(label) / 2;
                    int labelY;
                    if (barHeight < 25) {
                        // 柱子太矮，标签放在柱子上方
                        labelY = barY - 5;
                        if (labelY < layoutParams.chartTop + 10) {
                            labelY = barY + barHeight + 15;
                        }
                        svg.setPaint(barData.getLabelColor() != null ? barData.getLabelColor() : barData.getBarColor());
                    } else {
                        // 标签放在柱子内部
                        labelY = barY + barHeight / 2 + fm.getHeight() / 3;
                        svg.setPaint(Color.WHITE);
                    }
                    // 边界调整
                    if (labelY > layoutParams.chartBottom - 5) {
                        labelY = barY - 5;
                        svg.setPaint(barData.getLabelColor() != null ? barData.getLabelColor() : barData.getBarColor());
                    }
                    if (labelY < layoutParams.chartTop + 10 && barHeight >= 25) {
                        labelY = barY + barHeight / 2 + fm.getHeight() / 3;
                        svg.setPaint(Color.WHITE);
                    }
                    svg.drawString(label, labelX, labelY);
                }
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
            // X轴标签位于每组柱子的中心
            double groupCenterX = layoutParams.chartLeft + i * layoutParams.groupUnitWidth + layoutParams.groupUnitWidth / 2;
            int x = (int) groupCenterX;
            String label = labels.get(i);
            FontMetrics fm = svg.getFontMetrics();
            int labelX = x - fm.stringWidth(label) / 2;
            // 边界检查
            labelX = Math.max(layoutParams.chartLeft + 2, Math.min(layoutParams.chartRight - fm.stringWidth(label) - 2, labelX));
            // 加粗样式
            svg.setFont(config.getAxisFont().deriveFont(Font.BOLD));
            svg.drawString(label, labelX, layoutParams.xAxisLabelY);
            svg.setFont(config.getAxisFont());
        }
    }

    /**
     * 绘制X轴标题
     */
    private void drawXAxisTitle(SVGGraphics2D svg) {
        if (config.getXAxisTitle() == null || config.getXAxisTitle().isEmpty()) {
            return;
        }
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getLabelColor());
        String title = config.getXAxisTitle();
        FontMetrics fm = svg.getFontMetrics();
        int x = (layoutParams.chartLeft + layoutParams.chartRight) / 2;
        svg.drawString(title, x - fm.stringWidth(title) / 2, layoutParams.xAxisTitleY);
    }

    /**
     * 绘制图例 (与参考SVG风格一致)
     */
    private void drawLegend(SVGGraphics2D svg) {
        int width = config.getWidth();
        List<JMultiBarChartData.BarData> barDataList = config.getBarDataList();
        int legendCount = barDataList.size();
        int legendItemWidth = 110;
        int totalLegendWidth = legendCount * legendItemWidth;
        int legendStartX = Math.max(20, (width - totalLegendWidth) / 2);
        int rectSize = 18;
        int rectRx = 3;
        for (int i = 0; i < legendCount; i++) {
            JMultiBarChartData.BarData barData = barDataList.get(i);
            int legendX = legendStartX + i * legendItemWidth;
            // 绘制色块示例 (带圆角)
            svg.setPaint(barData.getBarColor());
            svg.fillRoundRect(legendX, layoutParams.legendY - rectSize, rectSize, rectSize, rectRx, rectRx);
            // 绘制图例文字
            svg.setPaint(config.getTextColor());
            svg.setFont(config.getLegendFont());
            svg.drawString(barData.getLegendText(), legendX + rectSize + 8, layoutParams.legendY);
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
    private void drawYAxisTitle(SVGGraphics2D svg) {
        if (config.getYAxisTitle() == null || config.getYAxisTitle().isEmpty()) {
            return;
        }
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getLabelColor());
        Graphics2D g2d = svg;
        FontMetrics fm = g2d.getFontMetrics();
        int titleX = layoutParams.yAxisTitleX;
        int titleY = layoutParams.yAxisTitleY;
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
        double groupUnitWidth;
        double groupInnerWidth;
        double barWidth;
        double barSpacing;
        int groupCount;
        int dataCount;
        int xAxisLabelY, xAxisTitleY, legendY, footerY;
        int yAxisTitleX, yAxisTitleY;
        int topMargin, bottomMargin, leftMargin, rightMargin;
    }
}
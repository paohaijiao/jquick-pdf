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
 * 横向多条形图渲染器 - 支持多组数据并列显示（横向分组条形图）
 * 完全自适应布局，根据数据量自动调整所有元素位置和大小
 * <p>
 * 特点：
 * - 每个类别下有多组条形并列显示
 * - 横向布局，便于显示较长的类别名称
 * - 支持数据标签自动定位
 */
@Data
public class JHorizontalMultiBarChartRenderer extends JAbstractChartRenderer {

    /**
     * 预设颜色方案
     */
    public static final Color[] DEFAULT_COLORS = {

            new Color(84, 112, 198),  // #5470c6 蓝色

            new Color(250, 200, 88),  // #fac858 橙黄色

            new Color(238, 102, 102), // #ee6666 红色

            new Color(80, 180, 150),  // #50b496 绿色

            new Color(159, 120, 196), // #9f78c4 紫色

            new Color(255, 150, 90),  // #ff965a 橙色

            new Color(97, 187, 211),  // #61bbd3 青色

            new Color(230, 130, 170)  // #e682aa 粉色
    };

    private final LayoutParams layoutParams;

    private JHorizontalMultiBarChartData config;

    public JHorizontalMultiBarChartRenderer() {
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
        JHorizontalMultiBarChartData config = (JHorizontalMultiBarChartData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getCategories(), "categories require not null");
        JAssert.notNull(config.getSeriesList(), "seriesList require not null");
        int categoryCount = config.getCategories().size();
        int seriesCount = config.getSeriesList().size();
        for (JHorizontalMultiBarChartData.Series series : config.getSeriesList()) {
            JAssert.notNull(series.getValues(), "series values require not null");
            JAssert.isTrue(series.getValues().size() == categoryCount, "all series must have same length as categories");
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
        drawYAxisLabels(svgGenerator);
        drawYAxisTitle(svgGenerator);
        drawLegend(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
        drawXAxisTitle(svgGenerator);
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
        int categoryCount = config.getCategories().size();
        int seriesCount = config.getSeriesList().size();
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
        // 计算图例高度（图例放在顶部或底部）
        int legendHeight = (seriesCount <= 4 && seriesCount <= 6) ? 45 : 60;
        int footerHeight = 30;
        // 计算Y轴标签最大宽度（用于左侧类别标签）
        FontMetrics fm = getDefaultFontMetrics();
        int maxYLabelWidth = 0;
        if (fm != null && config.getCategories() != null) {
            for (String category : config.getCategories()) {
                int labelWidth = fm.stringWidth(category);
                if (labelWidth > maxYLabelWidth) {
                    maxYLabelWidth = labelWidth;
                }
            }
        }
        maxYLabelWidth = Math.max(80, maxYLabelWidth + 15);
        // 计算X轴数值标签宽度
        int maxXLabelWidth = 0;
        if (fm != null) {
            String maxLabel = formatValue(config.getMaxValue());
            maxXLabelWidth = fm.stringWidth(maxLabel) + 20;
        }
        // 计算可用图表区域
        int topMargin;
        if (config.isLegendAtTop()) {
            topMargin = titleHeight + legendHeight + 10;
        } else {
            topMargin = titleHeight + 20;
        }
        int bottomMargin;
        if (config.isLegendAtTop()) {
            bottomMargin = footerHeight + 40;
        } else {
            bottomMargin = legendHeight + footerHeight + 40;
        }
        int leftMargin = maxYLabelWidth + 10;
        int rightMargin = Math.max(50, maxXLabelWidth + 15);
        int chartTop = topMargin;
        int chartBottom = height - bottomMargin;
        int chartLeft = leftMargin;
        int chartRight = width - rightMargin;
        int chartWidth = chartRight - chartLeft;
        int chartHeight = chartBottom - chartTop;
        // 计算每组条形的高度和间距
        double groupSpacingRatio = config.getGroupSpacingRatio();
        double barSpacingRatio = config.getBarSpacingRatio();
        // 总行数 = categoryCount
        double groupUnitHeight = (double) chartHeight / categoryCount;
        double groupInnerHeight = groupUnitHeight * (1 - groupSpacingRatio);
        double barHeight = groupInnerHeight / (seriesCount + (seriesCount - 1) * barSpacingRatio);
        double barSpacing = barHeight * barSpacingRatio;
        // 保存布局参数
        layoutParams.chartTop = chartTop;
        layoutParams.chartBottom = chartBottom;
        layoutParams.chartLeft = chartLeft;
        layoutParams.chartRight = chartRight;
        layoutParams.chartWidth = chartWidth;
        layoutParams.chartHeight = chartHeight;
        layoutParams.groupUnitHeight = groupUnitHeight;
        layoutParams.groupInnerHeight = groupInnerHeight;
        layoutParams.barHeight = barHeight;
        layoutParams.barSpacing = barSpacing;
        layoutParams.seriesCount = seriesCount;
        layoutParams.categoryCount = categoryCount;
        layoutParams.yAxisLabelX = leftMargin - 10;
        layoutParams.yAxisTitleX = leftMargin - 45;
        layoutParams.yAxisTitleY = height / 2;
        if (config.isLegendAtTop()) {
            layoutParams.legendY = titleHeight + 20;
        } else {
            layoutParams.legendY = chartBottom + 35;
        }

        layoutParams.footerY = height - 15;
        layoutParams.xAxisTitleY = chartBottom + 40;
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
        svg.setPaint(config.getBackgroundColor());
        svg.fillRoundRect(0, 0, config.getWidth(), config.getHeight(), 8, 8);
    }

    /**
     * 绘制网格线和X轴
     */
    private void drawGridAndAxes(SVGGraphics2D svg) {
        int gridCount = config.getGridCount();
        double maxValue = config.getMaxValue();
        svg.setStroke(new BasicStroke(1));
        // 绘制垂直网格线和X轴标签
        for (int i = 0; i <= gridCount; i++) {
            int x = layoutParams.chartLeft + (int) ((double) i / gridCount * layoutParams.chartWidth);
            double value = maxValue * ((double) i / gridCount);

            // 网格线
            svg.setPaint(config.getGridColor());
            if (i == gridCount) {
                // 右边框线
                svg.drawLine(x, layoutParams.chartTop, x, layoutParams.chartBottom);
            } else if (i > 0) {
                // 其他网格线为虚线
                svg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
                svg.drawLine(x, layoutParams.chartTop, x, layoutParams.chartBottom);
                svg.setStroke(new BasicStroke(1));
            }
            // X轴标签
            svg.setFont(config.getAxisFont());
            svg.setPaint(config.getYAxisTextColor());
            String label = formatValue(value);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, x - fm.stringWidth(label) / 2, layoutParams.chartBottom + 20);
        }

        // 重置描边
        svg.setStroke(new BasicStroke(1));
        // 绘制X轴轴线
        svg.setPaint(config.getAxisColor());
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartBottom, layoutParams.chartRight, layoutParams.chartBottom);
        // 绘制Y轴轴线
        svg.drawLine(layoutParams.chartLeft, layoutParams.chartTop, layoutParams.chartLeft, layoutParams.chartBottom);
    }

    /**
     * 绘制所有横向条形图组（多组并列）
     */
    private void drawAllBars(SVGGraphics2D svg) {
        List<JHorizontalMultiBarChartData.Series> seriesList = config.getSeriesList();
        double maxValue = config.getMaxValue();
        for (int categoryIdx = 0; categoryIdx < layoutParams.categoryCount; categoryIdx++) {
            // 计算当前类别起始Y坐标
            double groupStartY = layoutParams.chartTop + categoryIdx * layoutParams.groupUnitHeight + layoutParams.groupUnitHeight * config.getGroupSpacingRatio() / 2;
            double firstBarStartY = groupStartY;
            for (int seriesIdx = 0; seriesIdx < layoutParams.seriesCount; seriesIdx++) {
                JHorizontalMultiBarChartData.Series series = seriesList.get(seriesIdx);
                List<Double> values = series.getValues();
                double value = values.get(categoryIdx);
                // 计算条形Y坐标
                double barY = firstBarStartY + seriesIdx * (layoutParams.barHeight + layoutParams.barSpacing);
                int barHeightInt = (int) Math.max(3, layoutParams.barHeight);
                int barYInt = (int) barY;
                // 计算条形宽度
                int barWidth = (int) ((value / maxValue) * layoutParams.chartWidth);
                barWidth = Math.max(1, Math.min(barWidth, layoutParams.chartWidth));
                int barX = layoutParams.chartLeft;
                // 边界检查
                if (barYInt + barHeightInt > layoutParams.chartBottom) {
                    barHeightInt = layoutParams.chartBottom - barYInt;
                }
                if (barHeightInt < 1) continue;
                // 获取颜色
                Color barColor = series.getColor();
                if (barColor == null) {
                    barColor = DEFAULT_COLORS[seriesIdx % DEFAULT_COLORS.length];
                }
                // 绘制条形
                svg.setPaint(barColor);
                svg.fillRoundRect(barX, barYInt, barWidth, barHeightInt, 3, 3);
                // 绘制数据标签
                if (config.isShowDataLabels()) {
                    svg.setFont(config.getDataLabelFont());
                    String label = formatValue(value);
                    FontMetrics fm = svg.getFontMetrics();
                    int labelX;
                    int labelY = barYInt + barHeightInt / 2 + fm.getHeight() / 3;
                    if (barWidth < 50) {
                        // 条形太窄，标签放在条形右侧
                        labelX = barX + barWidth + 5;
                        svg.setPaint(series.getLabelColor() != null ? series.getLabelColor() : barColor);
                    } else {
                        // 标签放在条形内部
                        labelX = barX + barWidth / 2 - fm.stringWidth(label) / 2;
                        svg.setPaint(Color.WHITE);
                    }
                    // 边界调整
                    if (labelX + fm.stringWidth(label) > layoutParams.chartRight + 20) {
                        labelX = barX - fm.stringWidth(label) - 5;
                        if (labelX < layoutParams.chartLeft) {
                            labelX = barX + 5;
                        }
                        svg.setPaint(series.getLabelColor() != null ? series.getLabelColor() : barColor);
                    }
                    svg.drawString(label, labelX, labelY);
                }
            }
        }
    }

    /**
     * 绘制Y轴标签（类别标签）
     */
    private void drawYAxisLabels(SVGGraphics2D svg) {
        List<String> categories = config.getCategories();
        svg.setFont(config.getAxisFont());
        svg.setPaint(config.getTextColor());
        for (int i = 0; i < categories.size(); i++) {
            // Y轴标签位于每组条形的中心
            double groupCenterY = layoutParams.chartTop + i * layoutParams.groupUnitHeight + layoutParams.groupUnitHeight / 2;
            int y = (int) groupCenterY;
            String category = categories.get(i);
            FontMetrics fm = svg.getFontMetrics();
            int labelY = y + fm.getHeight() / 3;
            // 边界检查
            labelY = Math.max(layoutParams.chartTop + 15, Math.min(layoutParams.chartBottom - 5, labelY));
            // 右对齐Y轴标签
            int labelX = layoutParams.yAxisLabelX - fm.stringWidth(category);
            // 加粗样式
            svg.setFont(config.getAxisFont().deriveFont(Font.BOLD));
            svg.drawString(category, labelX, labelY);
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
     * 绘制Y轴标题（垂直旋转）
     */
    private void drawYAxisTitle(SVGGraphics2D svg) {
        if (config.getYAxisTitle() == null || config.getYAxisTitle().isEmpty()) {
            return;
        }
        svg.setFont(config.getAxisTitleFont());
        svg.setPaint(config.getLabelColor());
        Graphics2D g2d = svg;
        int titleX = layoutParams.yAxisTitleX;
        int titleY = layoutParams.yAxisTitleY;
        g2d.translate(titleX, titleY);
        g2d.rotate(-Math.PI / 2);
        g2d.drawString(config.getYAxisTitle(), -g2d.getFontMetrics().stringWidth(config.getYAxisTitle()) / 2, 0);
        g2d.rotate(Math.PI / 2);
        g2d.translate(-titleX, -titleY);
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        int width = config.getWidth();
        List<JHorizontalMultiBarChartData.Series> seriesList = config.getSeriesList();
        int legendCount = seriesList.size();
        // 计算图例行数（最多每行6个）
        int itemsPerRow = Math.min(6, legendCount);
        int rows = (legendCount + itemsPerRow - 1) / itemsPerRow;
        int legendItemWidth = 110;
        int totalLegendWidth = itemsPerRow * legendItemWidth;
        int legendStartX = Math.max(20, (width - totalLegendWidth) / 2);
        int rectSize = 16;
        int rectRx = 3;
        int rowHeight = 28;
        for (int i = 0; i < legendCount; i++) {
            JHorizontalMultiBarChartData.Series series = seriesList.get(i);
            int row = i / itemsPerRow;
            int col = i % itemsPerRow;
            int legendX = legendStartX + col * legendItemWidth;
            int legendY = layoutParams.legendY + row * rowHeight;
            // 获取颜色
            Color seriesColor = series.getColor();
            if (seriesColor == null) {
                seriesColor = DEFAULT_COLORS[i % DEFAULT_COLORS.length];
            }
            // 绘制色块
            svg.setPaint(seriesColor);
            svg.fillRoundRect(legendX, legendY - rectSize, rectSize, rectSize, rectRx, rectRx);
            // 绘制图例文字
            svg.setPaint(config.getTextColor());
            svg.setFont(config.getLegendFont());
            svg.drawString(series.getName(), legendX + rectSize + 8, legendY);
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

        double groupUnitHeight;

        double groupInnerHeight;

        double barHeight;

        double barSpacing;

        int seriesCount;

        int categoryCount;

        int yAxisLabelX, yAxisTitleX, yAxisTitleY;

        int legendY, footerY, xAxisTitleY;

        int topMargin, bottomMargin, leftMargin, rightMargin;
    }
}

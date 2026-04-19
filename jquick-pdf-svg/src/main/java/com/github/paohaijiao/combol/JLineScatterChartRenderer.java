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
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

/**
 * 折线+散点组合图渲染器
 * 支持两组独立数据：一组用折线表示，一组用散点表示
 */
public class JLineScatterChartRenderer extends JAbstractChartRenderer {

    /**
     * 预设颜色
     */
    private static final Color LINE_COLOR = new Color(16, 185, 129);  // 绿色

    private static final Color SCATTER_COLOR = new Color(239, 68, 68); // 红色

    private static final Color GRID_COLOR = new Color(221, 221, 221);

    private static final Color AXIS_COLOR = new Color(153, 153, 153);

    private static final Color TEXT_COLOR = new Color(51, 51, 51);

    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);

    /**
     * 字体
     */
    private static final Font TITLE_FONT = new Font("Microsoft YaHei", Font.BOLD, 22);

    private static final Font AXIS_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private static final Font LABEL_FONT = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private static final Font LEGEND_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);

    // 图表数据

    private JLineScatterChartData config;

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 800;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 500;
    }



    @Override
    protected void drawChart(SVGGraphics2D svg, JOption option, int width, int height) {
        this.config = (JLineScatterChartData) option.getData();
        validateData();
        // 开启抗锯齿
        svg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawBackground(svg, width, height);// 绘制背景
        LayoutParams layout = calculateLayout(width, height);// 计算布局参数
        drawGridAndAxes(svg, layout);// 绘制网格线和坐标轴
        drawLineChart(svg, layout);// 绘制折线（计划数据）
        drawScatterChart(svg, layout);// 绘制散点（实际数据）
        drawXAxisLabels(svg, layout);// 绘制X轴标签
        drawTitle(svg, option, width);// 绘制标题
        drawLegend(svg, layout, width);// 绘制图例
        drawFooter(svg, layout, width);// 绘制底部说明
    }

    /**
     * 验证数据有效性
     */
    private void validateData() {
        if (config == null) {
            throw new IllegalArgumentException("config cannot be null");
        }
        if (config.getCategories() == null || config.getCategories().isEmpty()) {
            throw new IllegalArgumentException("categories cannot be null or empty");
        }
        if (config.getLineValues() == null || config.getLineValues().size() != config.getCategories().size()) {
            throw new IllegalArgumentException("lineValues must have same size as categories");
        }
        if (config.getScatterValues() == null || config.getScatterValues().size() != config.getCategories().size()) {
            throw new IllegalArgumentException("scatterValues must have same size as categories");
        }
    }

    /**
     * 绘制背景
     */
    private void drawBackground(SVGGraphics2D svg, int width, int height) {
        svg.setPaint(BACKGROUND_COLOR);
        svg.fillRoundRect(0, 0, width, height, 10, 10);
    }

    /**
     * 计算布局参数
     */
    private LayoutParams calculateLayout(int width, int height) {
        LayoutParams layout = new LayoutParams();
        int titleHeight = 60;// 标题高度
        if (config.getTitleText() == null || config.getTitleText().isEmpty()) {
            titleHeight = 30;
        }
        int legendHeight = 40;// 图例高度
        // 底部说明高度
        int footerHeight = config.getFooterText() != null && !config.getFooterText().isEmpty() ? 30 : 0;
        // 计算Y轴标签最大宽度
        FontMetrics fm = getFontMetrics(AXIS_FONT);
        int maxCategoryWidth = 0;
        for (String category : config.getCategories()) {
            int w = fm.stringWidth(category);
            if (w > maxCategoryWidth) maxCategoryWidth = w;
        }
        int leftMargin = Math.max(60, maxCategoryWidth + 20);
        // 计算X轴数值标签宽度
        String maxValueStr = formatValue(config.getMaxValue());
        int rightMargin = Math.max(50, fm.stringWidth(maxValueStr) + 30);
        // 图表区域
        layout.chartTop = titleHeight + 10;
        layout.chartBottom = height - legendHeight - footerHeight - 40;
        layout.chartLeft = leftMargin;
        layout.chartRight = width - rightMargin;
        layout.chartWidth = layout.chartRight - layout.chartLeft;
        layout.chartHeight = layout.chartBottom - layout.chartTop;
        // 数据点之间的间距
        layout.categoryCount = config.getCategories().size();
        layout.xStep = (double) layout.chartWidth / (layout.categoryCount - 1);
        // Y轴缩放比例
        layout.yScale = layout.chartHeight / config.getMaxValue();
        // 各元素位置
        layout.legendX = (width - 250) / 2;
        layout.legendY = height - legendHeight - footerHeight - 10;
        layout.footerY = height - 15;

        return layout;
    }

    /**
     * 获取字体度量
     */
    private FontMetrics getFontMetrics(Font font) {
        try {
            java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.dispose();
            return fm;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 绘制网格线和坐标轴
     */
    private void drawGridAndAxes(SVGGraphics2D svg, LayoutParams layout) {
        int gridCount = config.getGridCount() > 0 ? config.getGridCount() : 5;
        svg.setStroke(new BasicStroke(1));
        // 绘制水平网格线和Y轴标签
        for (int i = 0; i <= gridCount; i++) {
            double value = config.getMaxValue() * i / gridCount;
            int y = layout.chartBottom - (int) (value * layout.yScale);
            // 网格线
            svg.setPaint(GRID_COLOR);
            if (i > 0 && i < gridCount) {
                svg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
                svg.drawLine(layout.chartLeft, y, layout.chartRight, y);
                svg.setStroke(new BasicStroke(1));
            } else if (i == gridCount) {
                svg.drawLine(layout.chartLeft, y, layout.chartRight, y);
            }
            // Y轴标签
            svg.setFont(AXIS_FONT);
            svg.setPaint(TEXT_COLOR);
            String label = formatValue(value);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layout.chartLeft - fm.stringWidth(label) - 8, y + fm.getHeight() / 3);
        }
        // 绘制X轴和Y轴轴线
        svg.setPaint(AXIS_COLOR);
        svg.setStroke(new BasicStroke(1.5f));
        svg.drawLine(layout.chartLeft, layout.chartBottom, layout.chartRight, layout.chartBottom);
        svg.drawLine(layout.chartLeft, layout.chartTop, layout.chartLeft, layout.chartBottom);
    }

    /**
     * 绘制折线图（计划数据）
     */
    private void drawLineChart(SVGGraphics2D svg, LayoutParams layout) {
        List<Double> values = config.getLineValues();
        if (values == null || values.isEmpty()) return;
        // 构建折线路径
        java.awt.geom.GeneralPath path = new java.awt.geom.GeneralPath();
        boolean first = true;
        for (int i = 0; i < layout.categoryCount; i++) {
            double value = values.get(i);
            int x = layout.chartLeft + (int) (i * layout.xStep);
            int y = layout.chartBottom - (int) (value * layout.yScale);
            if (first) {
                path.moveTo(x, y);
                first = false;
            } else {
                path.lineTo(x, y);
            }
        }
        // 绘制折线
        svg.setPaint(LINE_COLOR);
        svg.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.draw(path);
        // 绘制折线上的数据点
        for (int i = 0; i < layout.categoryCount; i++) {
            double value = values.get(i);
            int x = layout.chartLeft + (int) (i * layout.xStep);
            int y = layout.chartBottom - (int) (value * layout.yScale);
            // 绘制圆点
            svg.setPaint(LINE_COLOR);
            svg.fill(new Ellipse2D.Double(x - 5, y - 5, 10, 10));
            svg.setPaint(Color.WHITE);
            svg.fill(new Ellipse2D.Double(x - 3, y - 3, 6, 6));
            // 绘制数据标签
            if (config.isShowDataLabels()) {
                svg.setFont(LABEL_FONT);
                svg.setPaint(LINE_COLOR);
                String label = formatValue(value);
                FontMetrics fm = svg.getFontMetrics();
                svg.drawString(label, x - fm.stringWidth(label) / 2, y - 8);
            }
        }
    }

    /**
     * 绘制散点图（实际数据）
     */
    private void drawScatterChart(SVGGraphics2D svg, LayoutParams layout) {
        List<Double> values = config.getScatterValues();
        if (values == null || values.isEmpty()) return;
        for (int i = 0; i < layout.categoryCount; i++) {
            double value = values.get(i);
            int x = layout.chartLeft + (int) (i * layout.xStep);
            int y = layout.chartBottom - (int) (value * layout.yScale);
            // 绘制散点（圆形）
            svg.setPaint(SCATTER_COLOR);
            svg.fill(new Ellipse2D.Double(x - 7, y - 7, 14, 14));
            svg.setPaint(Color.WHITE);
            svg.fill(new Ellipse2D.Double(x - 4, y - 4, 8, 8));
            // 添加光晕效果
            svg.setPaint(new Color(239, 68, 68, 50));
            svg.fill(new Ellipse2D.Double(x - 12, y - 12, 24, 24));
            svg.setPaint(SCATTER_COLOR);
            svg.fill(new Ellipse2D.Double(x - 7, y - 7, 14, 14));
            svg.setPaint(Color.WHITE);
            svg.fill(new Ellipse2D.Double(x - 4, y - 4, 8, 8));
            // 绘制数据标签
            if (config.isShowDataLabels()) {
                svg.setFont(LABEL_FONT);
                svg.setPaint(SCATTER_COLOR);
                String label = formatValue(value);
                FontMetrics fm = svg.getFontMetrics();
                svg.drawString(label, x - fm.stringWidth(label) / 2, y - 12);
            }
        }
    }

    /**
     * 绘制X轴标签（类别标签）
     */
    private void drawXAxisLabels(SVGGraphics2D svg, LayoutParams layout) {
        List<String> categories = config.getCategories();
        svg.setFont(AXIS_FONT);
        svg.setPaint(TEXT_COLOR);
        for (int i = 0; i < categories.size(); i++) {
            int x = layout.chartLeft + (int) (i * layout.xStep);
            String category = categories.get(i);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(category, x - fm.stringWidth(category) / 2, layout.chartBottom + 25);
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg, LayoutParams layout, int width) {
        int rectSize = 16;
        int spacing = 40;
        // 折线图例
        int lineLegendX = (width - 250) / 2;
        int legendY = layout.legendY;
        svg.setPaint(LINE_COLOR);
        svg.fillRoundRect(lineLegendX, legendY - rectSize + 4, rectSize, rectSize, 3, 3);
        svg.setPaint(TEXT_COLOR);
        svg.setFont(LEGEND_FONT);
        svg.drawString(config.getLineSeriesName(), lineLegendX + rectSize + 8, legendY);
        // 散点图例
        int scatterLegendX = lineLegendX + 130;
        svg.setPaint(SCATTER_COLOR);
        svg.fillRoundRect(scatterLegendX, legendY - rectSize + 4, rectSize, rectSize, 3, 3);
        svg.setPaint(TEXT_COLOR);
        svg.drawString(config.getScatterSeriesName(), scatterLegendX + rectSize + 8, legendY);
    }

    /**
     * 绘制底部说明
     */
    private void drawFooter(SVGGraphics2D svg, LayoutParams layout, int width) {
        if (config.getFooterText() != null && !config.getFooterText().isEmpty()) {
            svg.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
            svg.setPaint(new Color(153, 153, 153));
            String footer = config.getFooterText();
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(footer, width / 2 - fm.stringWidth(footer) / 2, layout.footerY);
        }
    }

    @Override
    protected void drawTitle(SVGGraphics2D svg, JOption option, int width) {
        String title = config.getTitleText();
        String subtitle = config.getSubtitleText();
        if (title != null && !title.isEmpty()) {
            svg.setFont(TITLE_FONT);
            svg.setPaint(TEXT_COLOR);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(title, width / 2 - fm.stringWidth(title) / 2, 35);
            if (subtitle != null && !subtitle.isEmpty()) {
                svg.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
                svg.setPaint(new Color(102, 102, 102));
                fm = svg.getFontMetrics();
                svg.drawString(subtitle, width / 2 - fm.stringWidth(subtitle) / 2, 58);
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

        int categoryCount;

        double xStep;

        double yScale;

        int legendX, legendY;

        int footerY;
    }

}
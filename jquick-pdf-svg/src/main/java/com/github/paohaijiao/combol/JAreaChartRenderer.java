package com.github.paohaijiao.combol;


import com.github.paohaijiao.JOption;
import com.github.paohaijiao.combol.area.JAreaChartData;
import com.github.paohaijiao.combol.area.JSeriesData;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

/**
 * 面积图渲染器
 */
public class JAreaChartRenderer extends JAbstractChartRenderer {


    /**
     * 计算布局参数
     */
    private LayoutParams calculateLayout(JAreaChartData config) {
        LayoutParams layout = new LayoutParams();
        int width = config.getWidth();
        int height = config.getHeight();
        layout.marginTop = hasTitle(config) ? 60 : 30;
        layout.marginBottom = 70;
        layout.marginLeft = 60;
        layout.marginRight = 40;

        layout.chartLeft = layout.marginLeft;
        layout.chartRight = width - layout.marginRight;
        layout.chartTop = layout.marginTop;
        layout.chartBottom = height - layout.marginBottom;
        layout.chartWidth = layout.chartRight - layout.chartLeft;
        layout.chartHeight = layout.chartBottom - layout.chartTop;
        List<Double> firstSeries = config.getSeriesList().get(0).getValues();
        layout.dataCount = firstSeries.size();
        if (layout.dataCount > 1) {
            layout.stepX = (double) layout.chartWidth / (layout.dataCount - 1);
        }
        double[] range = config.getYAxisRange();
        layout.yMin = range[0];
        layout.yMax = range[1];
        layout.yRange = layout.yMax - layout.yMin;
        return layout;
    }

    /**
     * 绘制背景
     */
    private void drawBackground(SVGGraphics2D svg, JAreaChartData config) {
        svg.setPaint(config.getEffectiveBackgroundColor());
        svg.fillRoundRect(0, 0, config.getWidth(), config.getHeight(), 8, 8);
    }

    /**
     * 绘制网格线和Y轴
     */
    private void drawGridAndYAxis(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        int gridCount = config.getGridCount();
        svg.setFont(config.getFonts().getAxisFont());
        for (int i = 0; i <= gridCount; i++) {
            double ratio = (double) i / gridCount;
            int y = layout.chartBottom - (int) (ratio * layout.chartHeight);
            double value = layout.yMin + ratio * layout.yRange;
            // 网格线
            svg.setPaint(config.getEffectiveGridColor());
            if (i == 0 || i == gridCount) {
                svg.setStroke(new BasicStroke(1));
                svg.drawLine(layout.chartLeft, y, layout.chartRight, y);
            } else {
                svg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 4}, 0));
                svg.drawLine(layout.chartLeft, y, layout.chartRight, y);
                svg.setStroke(new BasicStroke(1));
            }
            // Y轴标签
            svg.setPaint(config.getEffectiveAxisTextColor());
            String label = formatValue(value);
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(label, layout.chartLeft - fm.stringWidth(label) - 8, y + 5);
        }
        // 轴线
        svg.setPaint(config.getEffectiveAxisColor());
        svg.setStroke(new BasicStroke(1));
        svg.drawLine(layout.chartLeft, layout.chartTop, layout.chartLeft, layout.chartBottom);
        svg.drawLine(layout.chartLeft, layout.chartBottom, layout.chartRight, layout.chartBottom);
    }

    /**
     * 绘制面积和折线
     */
    private void drawAreaAndLines(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        List<JSeriesData> seriesList = config.getSeriesList();
        if (seriesList == null || seriesList.isEmpty()) return;
        // 只渲染第一个系列（面积图通常只有一个主系列）
        JSeriesData series = seriesList.get(0);
        List<Double> values = series.getValues();
        if (values.size() < 2) return;
        // 构建面积路径
        Path2D.Double areaPath = new Path2D.Double();
        Path2D.Double linePath = new Path2D.Double();
        // 第一个点
        double firstY = getYCoordinate(values.get(0), layout);
        areaPath.moveTo(layout.chartLeft, firstY);
        linePath.moveTo(layout.chartLeft, firstY);
        // 中间点
        for (int i = 1; i < values.size(); i++) {
            double x = layout.chartLeft + i * layout.stepX;
            double y = getYCoordinate(values.get(i), layout);
            areaPath.lineTo(x, y);
            linePath.lineTo(x, y);
        }
        // 闭合面积路径
        double lastX = layout.chartLeft + (values.size() - 1) * layout.stepX;
        areaPath.lineTo(lastX, layout.chartBottom);
        areaPath.lineTo(layout.chartLeft, layout.chartBottom);
        areaPath.closePath();
        // 绘制面积填充（渐变）
        GradientPaint gradient = new GradientPaint(0, layout.chartTop, config.getEffectiveAreaStartColor(), 0, layout.chartBottom, config.getEffectiveAreaEndColor());
        svg.setPaint(gradient);
        svg.fill(areaPath);
        // 绘制折线
        svg.setPaint(config.getEffectiveLineColor());
        svg.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.draw(linePath);
    }

    /**
     * 绘制X轴和标签
     */
    private void drawXAxisAndLabels(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        List<String> labels = config.getXAxisLabels();
        if (labels == null || labels.isEmpty()) return;
        svg.setFont(config.getFonts().getAxisFont());
        svg.setPaint(config.getEffectiveTextColor());
        for (int i = 0; i < labels.size(); i++) {
            int x = (int) (layout.chartLeft + i * layout.stepX);
            String label = labels.get(i);
            FontMetrics fm = svg.getFontMetrics();
            int labelX = x - fm.stringWidth(label) / 2;
            // 边界检查
            labelX = Math.max(layout.chartLeft + 2, Math.min(layout.chartRight - fm.stringWidth(label) - 2, labelX));
            svg.drawString(label, labelX, layout.chartBottom + 20);
        }
        // 刻度线
        svg.setPaint(config.getEffectiveAxisColor());
        for (int i = 0; i < labels.size(); i++) {
            int x = (int) (layout.chartLeft + i * layout.stepX);
            svg.drawLine(x, layout.chartBottom, x, layout.chartBottom + 5);
        }
    }

    /**
     * 绘制X轴标题
     */
    private void drawXAxisTitle(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        if (config.getXAxisTitle() == null || config.getXAxisTitle().isEmpty()) return;
        svg.setFont(config.getFonts().getAxisFont());
        svg.setPaint(config.getEffectiveTextColor());
        String title = config.getXAxisTitle();
        FontMetrics fm = svg.getFontMetrics();
        int x = (layout.chartLeft + layout.chartRight) / 2;
        svg.drawString(title, x - fm.stringWidth(title) / 2, layout.chartBottom + 45);
    }

    /**
     * 绘制Y轴标题
     */
    private void drawYAxisTitle(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        if (config.getYAxisTitle() == null || config.getYAxisTitle().isEmpty()) return;
        svg.setFont(config.getFonts().getAxisFont());
        svg.setPaint(config.getEffectiveTextColor());
        String title = config.getYAxisTitle();
        FontMetrics fm = svg.getFontMetrics();
        int x = layout.chartLeft - 35;
        int y = (layout.chartTop + layout.chartBottom) / 2;
        Graphics2D g2d = svg;
        g2d.translate(x, y);
        g2d.rotate(-Math.PI / 2);
        g2d.drawString(title, -fm.stringWidth(title) / 2, 0);
        g2d.rotate(Math.PI / 2);
        g2d.translate(-x, -y);
    }

    /**
     * 绘制标题
     */
    private void drawTitle(SVGGraphics2D svg, JAreaChartData config) {
        int width = config.getWidth();
        if (config.getTitle() != null && !config.getTitle().isEmpty()) {
            svg.setFont(config.getFonts().getTitleFont());
            svg.setPaint(config.getEffectiveTextColor());
            FontMetrics fm = svg.getFontMetrics();
            svg.drawString(config.getTitle(), width / 2 - fm.stringWidth(config.getTitle()) / 2, 35);
            if (config.getSubtitle() != null && !config.getSubtitle().isEmpty()) {
                svg.setFont(config.getFonts().getSubtitleFont());
                fm = svg.getFontMetrics();
                svg.drawString(config.getSubtitle(), width / 2 - fm.stringWidth(config.getSubtitle()) / 2, 58);
            }
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        int width = config.getWidth();
        int legendY = layout.chartBottom + 65;
        int rectSize = 18;
        String legendText = config.getLegendText();
        if (legendText == null && !config.getSeriesList().isEmpty()) {
            legendText = config.getSeriesList().get(0).getName();
        }
        if (legendText == null) {
            legendText = "数据系列";
        }
        int legendX = (width - 120) / 2;
        // 色块
        svg.setPaint(config.getEffectiveLineColor());
        svg.fillRoundRect(legendX, legendY - rectSize, rectSize, rectSize, 3, 3);
        // 文字
        svg.setPaint(config.getEffectiveTextColor());
        svg.setFont(config.getFonts().getLegendFont());
        svg.drawString(legendText, legendX + rectSize + 8, legendY);
    }

    /**
     * 绘制数据标签
     */
    private void drawDataLabels(SVGGraphics2D svg, JAreaChartData config, LayoutParams layout) {
        List<JSeriesData> seriesList = config.getSeriesList();
        if (seriesList == null || seriesList.isEmpty()) return;
        JSeriesData series = seriesList.get(0);
        List<Double> values = series.getValues();
        svg.setFont(config.getFonts().getDataLabelFont());
        svg.setPaint(config.getEffectiveLineColor());
        for (int i = 0; i < values.size(); i++) {
            double x = layout.chartLeft + i * layout.stepX;
            double y = getYCoordinate(values.get(i), layout);
            String label = formatValue(values.get(i));
            FontMetrics fm = svg.getFontMetrics();
            int labelX = (int) (x - fm.stringWidth(label) / 2);
            int labelY = (int) (y - 8);
            // 边界检查，避免超出顶部
            if (labelY < layout.chartTop + 10) {
                labelY = (int) (y + 20);
            }
            svg.drawString(label, labelX, labelY);
        }
    }

    /**
     * 获取Y坐标
     */
    private double getYCoordinate(double value, LayoutParams layout) {
        double ratio = (value - layout.yMin) / layout.yRange;
        double y = layout.chartBottom - ratio * layout.chartHeight;
        return Math.max(layout.chartTop, Math.min(layout.chartBottom, y));
    }

    /**
     * 格式化数值
     */
    private String formatValue(double value) {
        if (value == (int) value) {
            return String.valueOf((int) value);
        } else {
            return String.format("%.1f", value);
        }
    }

    private boolean hasTitle(JAreaChartData config) {
        return config.getTitle() != null && !config.getTitle().isEmpty();
    }

    @Override
    protected void drawChart(SVGGraphics2D svg, JOption option, int width, int height) {
        JAreaChartData config = (JAreaChartData) option.getData();
        LayoutParams layout = calculateLayout(config);  // 计算布局参数
        drawBackground(svg, config);// 绘制背景
        drawGridAndYAxis(svg, config, layout);// 绘制网格和Y轴
        drawAreaAndLines(svg, config, layout);// 绘制面积和折线
        drawXAxisAndLabels(svg, config, layout);// 绘制X轴和标签
        drawXAxisTitle(svg, config, layout);// 绘制X轴标题
        drawYAxisTitle(svg, config, layout); // 绘制Y轴标题
        drawTitle(svg, config);// 绘制标题
        if (config.isShowLegend()) { // 绘制图例
            drawLegend(svg, config, layout);
        }
        // 绘制数据标签
        if (config.isShowDataLabels()) {
            drawDataLabels(svg, config, layout);
        }
    }

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {
        int marginTop, marginBottom, marginLeft, marginRight;
        int chartLeft, chartRight, chartTop, chartBottom;
        int chartWidth, chartHeight;
        int dataCount;
        double stepX;
        double yMin, yMax, yRange;
    }
}
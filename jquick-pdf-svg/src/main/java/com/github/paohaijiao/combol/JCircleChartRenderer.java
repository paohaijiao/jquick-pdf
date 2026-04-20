package com.github.paohaijiao.combol;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import lombok.Data;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;

/**
 * 环形图渲染器 - 商务风格环形图
 * 支持百分比数据展示，中心显示总计数值
 */
@Data
public class JCircleChartRenderer extends JAbstractChartRenderer {

    private final LayoutParams layoutParams;

    private JCircleChartData config;

    public JCircleChartRenderer() {
        this.layoutParams = new LayoutParams();
    }

    @Override
    protected int getDefaultWidth() {
        return config != null ? config.getWidth() : 500;
    }

    @Override
    protected int getDefaultHeight() {
        return config != null ? config.getHeight() : 400;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JCircleChartData config = (JCircleChartData) option.getData();
        JAssert.notNull(config, "config require not null");
        JAssert.notNull(config.getSectorDataList(), "sector data list require not null");
        this.config = config;
        updateConfigDimensions(width, height);
        config.validateAndCalculate();
        calculateLayout();
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawChartBackground(svgGenerator);
        drawRingChart(svgGenerator);
        drawCenterText(svgGenerator);
        drawLegend(svgGenerator);
        drawTitle(svgGenerator, option, width);
        drawFooter(svgGenerator);
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
        int width = config.getWidth();
        int height = config.getHeight();
        int titleHeight = 0;// 计算标题占用的高度
        if (config.getTitleText() != null && !config.getTitleText().isEmpty()) {
            titleHeight += 45;
            if (config.getSubtitleText() != null && !config.getSubtitleText().isEmpty()) {
                titleHeight += 25;
            }
        } else {
            titleHeight = 20;
        }
        int legendHeight = config.getSectorDataList().size() <= 4 ? 80 : 100;// 图例和底部说明占用的高度
        int footerHeight = 30;
        int ringSize = Math.min(width - 180, height - titleHeight - legendHeight - footerHeight - 40);// 环形图区域
        int ringX = (width - ringSize) / 2;
        int ringY = titleHeight + 20;
        int ringDiameter = ringSize;
        int ringRadius = ringSize / 2;
        int holeRadius = (int) (ringRadius * 0.65);
        int legendStartX = width - 110;// 图例起始位置
        int legendStartY = titleHeight + 30;
        layoutParams.ringX = ringX;// 保存布局参数
        layoutParams.ringY = ringY;
        layoutParams.ringDiameter = ringDiameter;
        layoutParams.ringRadius = ringRadius;
        layoutParams.holeRadius = holeRadius;
        layoutParams.centerX = ringX + ringRadius;
        layoutParams.centerY = ringY + ringRadius;
        layoutParams.legendStartX = legendStartX;
        layoutParams.legendStartY = legendStartY;
        layoutParams.titleHeight = titleHeight;
        layoutParams.footerY = height - 15;
    }

    /**
     * 绘制图表背景
     */
    private void drawChartBackground(SVGGraphics2D svg) {
        svg.setPaint(config.getBackgroundColor());
        svg.fillRect(0, 0, config.getWidth(), config.getHeight());
    }

    /**
     * 绘制环形图
     */
    private void drawRingChart(SVGGraphics2D svg) {
        List<JCircleChartData.SectorData> sectors = config.getSectorDataList();
        float startAngle = config.getStartAngle();
        int outerR = layoutParams.ringRadius;// 先绘制灰色背景圆环
        int innerR = layoutParams.holeRadius;

        // 绘制外圈装饰线（商务风格）
        svg.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svg.setPaint(new Color(230, 235, 240));
        svg.drawOval(layoutParams.centerX - outerR - 2, layoutParams.centerY - outerR - 2, (outerR + 2) * 2, (outerR + 2) * 2);
        // 绘制各个扇形区块
        for (JCircleChartData.SectorData sector : sectors) {
            double angle = sector.getPercentage() * 3.6f;
            // 构建扇形路径（环形）
            Arc2D.Double arc = new Arc2D.Double(layoutParams.centerX - outerR, layoutParams.centerY - outerR, outerR * 2, outerR * 2, startAngle, angle, Arc2D.PIE);
            svg.setPaint(sector.getSectorColor());// 设置颜色和渐变（商务风格）
            svg.fill(arc);
            startAngle += angle; // 添加内圈白色圆覆盖形成环形效果
        }
        // 绘制中心白色圆（形成环形）
        svg.setPaint(config.getCenterColor());
        svg.fillOval(layoutParams.centerX - layoutParams.holeRadius, layoutParams.centerY - layoutParams.holeRadius, layoutParams.holeRadius * 2, layoutParams.holeRadius * 2);
        // 添加中心阴影效果
        svg.setPaint(new Color(0, 0, 0, 20));
        svg.drawOval(layoutParams.centerX - layoutParams.holeRadius, layoutParams.centerY - layoutParams.holeRadius, layoutParams.holeRadius * 2, layoutParams.holeRadius * 2);
        // 绘制扇形边框分割线
        float lineStartAngle = config.getStartAngle();
        for (JCircleChartData.SectorData sector : sectors) {
            double angle = sector.getPercentage() * 3.6f;
            double rad = Math.toRadians(lineStartAngle);
            int x1 = layoutParams.centerX + (int) (outerR * Math.cos(rad));
            int y1 = layoutParams.centerY + (int) (outerR * Math.sin(rad));
            int x2 = layoutParams.centerX + (int) (innerR * Math.cos(rad));
            int y2 = layoutParams.centerY + (int) (innerR * Math.sin(rad));
            svg.setStroke(new BasicStroke(1.5f));
            svg.setPaint(Color.WHITE);
            svg.drawLine(x1, y1, x2, y2);
            lineStartAngle += angle;
        }
    }

    /**
     * 绘制中心文字
     */
    private void drawCenterText(SVGGraphics2D svg) {
        if (!config.isShowCenterText()) {
            return;
        }
        svg.setFont(config.getCenterTitleFont());
        svg.setPaint(config.getCenterTitleColor());
        String centerTitle = config.getCenterTitle() != null ? config.getCenterTitle() : "总计";
        FontMetrics fm = svg.getFontMetrics();
        int titleX = layoutParams.centerX - fm.stringWidth(centerTitle) / 2;
        int titleY = layoutParams.centerY - 8;
        svg.drawString(centerTitle, titleX, titleY);
        svg.setFont(config.getCenterValueFont());
        svg.setPaint(config.getCenterValueColor());
        String centerValue = config.getCenterValue() != null ? config.getCenterValue() : "";
        fm = svg.getFontMetrics();
        int valueX = layoutParams.centerX - fm.stringWidth(centerValue) / 2;
        int valueY = layoutParams.centerY + 18;
        svg.drawString(centerValue, valueX, valueY);
        // 可选：显示单位
        if (config.getCenterUnit() != null && !config.getCenterUnit().isEmpty()) {
            svg.setFont(config.getCenterUnitFont());
            svg.setPaint(config.getCenterUnitColor());
            String unit = config.getCenterUnit();
            fm = svg.getFontMetrics();
            int unitX = layoutParams.centerX + fm.stringWidth(centerValue) / 2 + 5;
            svg.drawString(unit, unitX, valueY);
        }
    }

    /**
     * 绘制图例
     */
    private void drawLegend(SVGGraphics2D svg) {
        List<JCircleChartData.SectorData> sectors = config.getSectorDataList();
        int itemHeight = 28;
        int rectSize = 12;
        int textOffset = 18;
        svg.setFont(config.getLegendFont());
        for (int i = 0; i < sectors.size(); i++) {
            JCircleChartData.SectorData sector = sectors.get(i);
            int y = layoutParams.legendStartY + i * itemHeight;
            // 绘制颜色方块
            svg.setPaint(sector.getSectorColor());
            svg.fillRect(layoutParams.legendStartX, y, rectSize, rectSize);
            // 绘制图例文字
            svg.setPaint(config.getTextColor());
            String legendText = sector.getLegendText() != null ? sector.getLegendText() : sector.getName();
            svg.drawString(legendText, layoutParams.legendStartX + textOffset, y + rectSize - 2);
            // 绘制百分比
            svg.setFont(config.getLegendPercentFont());
            svg.setPaint(config.getTextSecondaryColor());
            String percent = String.format("%.1f%%", sector.getPercentage());
            FontMetrics fm = svg.getFontMetrics();
            int percentX = layoutParams.legendStartX + 80;
            svg.drawString(percent, percentX, y + rectSize - 2);
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
                svgGenerator.setPaint(config.getTextSecondaryColor());
                svgGenerator.drawString(subtitle, width / 2 - fm.stringWidth(subtitle) / 2, 58);
            }
        }
    }

    /**
     * 布局参数内部类
     */
    private static class LayoutParams {

        int ringX, ringY, ringDiameter, ringRadius, holeRadius;

        int centerX, centerY;

        int legendStartX, legendStartY;

        int titleHeight;

        int footerY;
    }
}
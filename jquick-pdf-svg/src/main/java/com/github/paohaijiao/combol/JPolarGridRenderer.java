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
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.io.IOException;
import java.util.List;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @classname JPolarGridRenderer
 * @date 2026/11/8
 * @description Polar chart renderer.
 * <p>
 * Draws a polar grid (concentric rings plus radial spokes), optional background sectors and
 * one closed or open curve per {@link JPolarChartData.PolarSeries}. Everything the figure
 * shows — canvas size, slot count, radius range, ring count, colours, fonts, legend and
 * footer — is read from {@link JPolarChartData}, which arrives through
 * {@code JOption.setData(...)}.
 * <p>
 * When no model is supplied the renderer falls back to {@link JPolarChartData#sample()}, so
 * an empty option still produces the gallery preview instead of a blank canvas.
 */
@JQuickSpi(name = "polar", order = 36, chartType = JChartType.Polar)
public class JPolarGridRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_CANVAS = 500;

    private static final int ARC_STEPS = 96;

    private static final int ANGLE_LABEL_GAP = 16;

    private int canvasWidth = DEFAULT_CANVAS;

    private int canvasHeight = DEFAULT_CANVAS;

    /**
     * Model prepared by {@link #renderToString(JOption)} and consumed by
     * {@link #drawChart(SVGGraphics2D, JOption, int, int)}. The factory hands out a fresh
     * renderer per call, so the field is never shared between callers.
     */
    private JPolarChartData current;

    @Override
    public String renderToString(JOption option) throws IOException {
        JPolarChartData data = resolve(option);
        data.validateAndCalculate();
        this.current = data;
        this.canvasWidth = data.getWidth() > 0 ? data.getWidth() : DEFAULT_CANVAS;
        this.canvasHeight = data.getHeight() > 0 ? data.getHeight() : DEFAULT_CANVAS;
        return super.renderToString(option);
    }

    @Override
    protected int getDefaultWidth() {
        return canvasWidth;
    }

    @Override
    protected int getDefaultHeight() {
        return canvasHeight;
    }

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        JPolarChartData data = current;
        if (data == null) {
            data = resolve(option);
            data.validateAndCalculate();
        }
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        svgGenerator.setPaint(data.getBackgroundColor());
        svgGenerator.fillRect(0, 0, width, height);

        int centerX = width / 2;
        int centerY = height / 2 + 8;
        int radius = Math.min(width, height) / 2 - data.getPadding();
        if (radius < 40) {
            radius = 40;
        }

        String title = data.getTitleText() != null
                ? data.getTitleText()
                : option != null && option.title() != null ? option.title().text() : null;
        String subtitle = data.getSubtitleText();
        if (subtitle == null && option != null && option.title() != null) {
            subtitle = option.title().subtext();
        }

        drawHeader(svgGenerator, data, title, subtitle);
        drawSectors(svgGenerator, data, centerX, centerY, radius);
        drawGrid(svgGenerator, data, centerX, centerY, radius);
        drawAngleLabels(svgGenerator, data, centerX, centerY, radius);
        drawRadiusLabels(svgGenerator, data, centerX, centerY, radius);
        drawSeries(svgGenerator, data, centerX, centerY, radius);
        drawLegend(svgGenerator, data, width);
        drawFooter(svgGenerator, data, width, height);
    }

    private static JPolarChartData resolve(JOption option) {
        if (option != null && option.getData() instanceof JPolarChartData) {
            return (JPolarChartData) option.getData();
        }
        return JPolarChartData.sample();
    }

    private void drawHeader(SVGGraphics2D svg, JPolarChartData data, String title, String subtitle) {
        int y = 26;
        if (title != null && !title.isEmpty()) {
            svg.setFont(data.getTitleFont());
            svg.setPaint(new Color(0x333333));
            svg.drawString(title, 16, y);
            y += 18;
        }
        if (subtitle != null && !subtitle.isEmpty()) {
            svg.setFont(data.getSubtitleFont());
            svg.setPaint(data.getLabelColor());
            svg.drawString(subtitle, 16, y);
        }
    }

    private void drawSectors(SVGGraphics2D svg, JPolarChartData data, int cx, int cy, int radius) {
        List<JPolarChartData.PolarSector> sectors = data.getSectors();
        if (sectors == null) {
            return;
        }
        for (JPolarChartData.PolarSector sector : sectors) {
            double r = radius * clamp(sector.getRadiusRatio(), 0d, 1d);
            Path2D path = new Path2D.Double();
            path.moveTo(cx, cy);
            appendArc(path, cx, cy, r, sector.getStartAngle(), sector.getEndAngle() - sector.getStartAngle(), false);
            path.closePath();
            svg.setPaint(withAlpha(sector.getColor(), sector.getAlpha()));
            svg.fill(path);
            svg.setStroke(new BasicStroke(1.6f));
            svg.setPaint(withAlpha(sector.getColor(), Math.min(1f, sector.getAlpha() * 6f)));
            svg.draw(path);
        }
    }

    private void drawGrid(SVGGraphics2D svg, JPolarChartData data, int cx, int cy, int radius) {
        int ticks = data.getRadiusTickCount();
        svg.setStroke(new BasicStroke(1f));
        svg.setPaint(data.getGridColor());
        for (int i = 1; i <= ticks; i++) {
            double r = radius * (double) i / ticks;
            Path2D ring = new Path2D.Double();
            appendArc(ring, cx, cy, r, data.getStartAngle(), data.getAngleSpan(), false);
            if (data.getAngleSpan() >= 360f) {
                ring.closePath();
            }
            svg.draw(ring);
        }

        svg.setStroke(new BasicStroke(0.8f));
        svg.setPaint(data.getSpokeColor());
        for (int i = 0; i < data.getSlotCount(); i++) {
            double angle = Math.toRadians(data.angleOf(i));
            svg.drawLine(cx, cy,
                    cx + (int) Math.round(radius * Math.cos(angle)),
                    cy + (int) Math.round(radius * Math.sin(angle)));
        }

        if (data.isShowAxes()) {
            svg.setStroke(new BasicStroke(1.2f));
            svg.setPaint(data.getAxisColor());
            for (double angle : new double[]{data.getStartAngle(), data.getStartAngle() + 180d}) {
                double rad = Math.toRadians(angle);
                svg.drawLine(cx, cy,
                        cx + (int) Math.round(radius * Math.cos(rad)),
                        cy + (int) Math.round(radius * Math.sin(rad)));
            }
        }
    }

    private void drawAngleLabels(SVGGraphics2D svg, JPolarChartData data, int cx, int cy, int radius) {
        List<String> labels = data.getAngleLabels();
        if (!data.isShowAngleLabels() || labels == null || labels.isEmpty()) {
            return;
        }
        svg.setFont(data.getLabelFont());
        svg.setPaint(data.getLabelColor());
        for (int i = 0; i < labels.size() && i < data.getSlotCount(); i++) {
            double angle = Math.toRadians(data.angleOf(i));
            int x = cx + (int) Math.round((radius + ANGLE_LABEL_GAP) * Math.cos(angle));
            int y = cy + (int) Math.round((radius + ANGLE_LABEL_GAP) * Math.sin(angle));
            drawCenteredText(svg, labels.get(i), x, y);
        }
    }

    private void drawRadiusLabels(SVGGraphics2D svg, JPolarChartData data, int cx, int cy, int radius) {
        if (!data.isShowRadiusLabels()) {
            return;
        }
        svg.setFont(data.getLabelFont());
        svg.setPaint(data.getLabelColor());
        String unit = data.getValueUnit() == null ? "" : data.getValueUnit();
        double axis = Math.toRadians(data.getStartAngle());
        double cos = Math.cos(axis);
        double sin = Math.sin(axis);
        for (int i = 1; i <= data.getRadiusTickCount(); i++) {
            double r = radius * (double) i / data.getRadiusTickCount();
            int x = cx + (int) Math.round(r * cos) + 4;
            int y = cy + (int) Math.round(r * sin) + 4;
            svg.drawString(formatValue(data.tickValue(i)) + unit, x, y);
        }
    }

    private void drawSeries(SVGGraphics2D svg, JPolarChartData data, int cx, int cy, int radius) {
        List<JPolarChartData.PolarSeries> series = data.getSeries();
        for (JPolarChartData.PolarSeries item : series) {
            Path2D path = new Path2D.Double();
            int[] xs = new int[item.getValues().size()];
            int[] ys = new int[item.getValues().size()];
            for (int i = 0; i < item.getValues().size(); i++) {
                double ratio = clamp(data.radiusRatio(item.getValues().get(i)), 0d, 1d);
                double angle = Math.toRadians(data.angleOf(i));
                xs[i] = cx + (int) Math.round(radius * ratio * Math.cos(angle));
                ys[i] = cy + (int) Math.round(radius * ratio * Math.sin(angle));
                if (i == 0) {
                    path.moveTo(xs[i], ys[i]);
                } else {
                    path.lineTo(xs[i], ys[i]);
                }
            }
            if (item.isClosed()) {
                path.closePath();
            }

            if (item.isFilled()) {
                svg.setPaint(withAlpha(item.getColor(), item.getFillAlpha()));
                svg.fill(path);
            }

            svg.setPaint(withAlpha(item.getColor(), 1f));
            svg.setStroke(strokeOf(item));
            svg.draw(path);

            if (item.isShowPoints()) {
                for (int i = 0; i < xs.length; i++) {
                    int d = item.getPointRadius() * 2;
                    svg.fillOval(xs[i] - item.getPointRadius(), ys[i] - item.getPointRadius(), d, d);
                }
            }
        }

        svg.setPaint(new Color(0xFF6B6B));
        svg.fillOval(cx - 4, cy - 4, 8, 8);
        svg.setPaint(Color.WHITE);
        svg.fillOval(cx - 2, cy - 2, 4, 4);
    }

    private void drawLegend(SVGGraphics2D svg, JPolarChartData data, int width) {
        if (!data.isShowLegend() || data.getSeries().isEmpty()) {
            return;
        }
        svg.setFont(data.getLegendFont());
        FontMetrics metrics = svg.getFontMetrics();
        int y = 26;
        for (JPolarChartData.PolarSeries item : data.getSeries()) {
            String text = item.getName();
            int x = width - 16 - metrics.stringWidth(text);
            svg.setPaint(item.getColor());
            svg.setStroke(strokeOf(item));
            svg.drawLine(x - 28, y - 4, x - 8, y - 4);
            if (item.isShowPoints()) {
                svg.fillOval(x - 21, y - 7, 6, 6);
            }
            svg.setPaint(data.getLegendTextColor());
            svg.drawString(text, x, y);
            y += 16;
        }
    }

    private void drawFooter(SVGGraphics2D svg, JPolarChartData data, int width, int height) {
        if (data.getFooterText() == null || data.getFooterText().isEmpty()) {
            return;
        }
        svg.setFont(data.getFooterFont());
        svg.setPaint(data.getFooterColor());
        drawCenteredText(svg, data.getFooterText(), width / 2, height - 8);
    }

    private static BasicStroke strokeOf(JPolarChartData.PolarSeries item) {
        if (item.getDash() != null && item.getDash().length > 0) {
            return new BasicStroke(item.getLineWidth(), BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND, 10f, item.getDash(), 0f);
        }
        return new BasicStroke(item.getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }

    /**
     * Appends an arc to the path, sampling it in screen coordinates: angle 0 points at three
     * o'clock and grows clockwise, matching {@link JPolarChartData#angleOf(int)}.
     */
    private static void appendArc(Path2D path, int cx, int cy, double radius,
                                  double startAngle, double sweep, boolean moveToStart) {
        double step = sweep / ARC_STEPS;
        for (int i = 0; i <= ARC_STEPS; i++) {
            double angle = Math.toRadians(startAngle + step * i);
            double x = cx + radius * Math.cos(angle);
            double y = cy + radius * Math.sin(angle);
            if (i == 0 && moveToStart) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
    }

    private static Color withAlpha(Color color, float alpha) {
        int value = Math.max(0, Math.min(255, Math.round(alpha * 255f)));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), value);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static String formatValue(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.0001d) {
            return String.valueOf((long) Math.rint(value));
        }
        return String.format("%.1f", value);
    }

    private void drawCenteredText(SVGGraphics2D svg, String text, int x, int y) {
        FontMetrics fm = svg.getFontMetrics();
        int textX = x - fm.stringWidth(text) / 2;
        int textY = y + fm.getHeight() / 3;
        svg.drawString(text, textX, textY);
    }
}

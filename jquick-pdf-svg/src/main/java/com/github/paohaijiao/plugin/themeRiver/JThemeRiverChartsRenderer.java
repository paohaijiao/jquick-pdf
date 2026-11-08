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
package com.github.paohaijiao.plugin.themeRiver;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.plugin.themeRiver
 *
 * @author Martin
 * @version 1.0.0
 * @classname JThemeRiverChartsRenderer
 * @date 2026/11/8
 * @description Theme river renderer, also called a streamgraph.
 * <p>
 * The data is taken from {@link JThemeRiverChartData} through {@code JOption.setData(...)}.
 * Every time slice is stacked around the zero line, so the river stays symmetric: the baseline
 * of a slice is {@code -total / 2} and the streams grow upwards from it. Series are sorted by
 * total value and then placed alternately at the tail and the head of the stack, which keeps
 * the widest stream close to the centre of the river.
 * <p>
 * Both edges of a band are Catmull-Rom splines converted to cubic Bezier segments, so the bands
 * flow smoothly instead of showing a polyline at every time point. All painting is done with
 * Graphics2D calls on the given SVGGraphics2D instance.
 */
@JQuickSpi(name = "theme-river", order = 35, chartType = JChartType.ThemeRiver)
public class JThemeRiverChartsRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_CANVAS_WIDTH = 900;

    private static final int DEFAULT_CANVAS_HEIGHT = 560;

    /**
     * Horizontal margin of the plot area. No value axis is drawn, so the margin only keeps
     * the bands away from the canvas border.
     */
    private static final double PLOT_MARGIN_X = 52d;

    private static final double PLOT_TOP = 104d;

    /**
     * Space kept below the plot for the time labels, the legend rows and the footer.
     */
    private static final double PLOT_BOTTOM_OFFSET = 130d;

    /**
     * A band thinner than this cannot hold a readable name, so its label is skipped.
     */
    private static final double MIN_LABEL_THICKNESS = 16d;

    /**
     * Width of the band column drawn when the time axis holds a single point.
     */
    private static final double SINGLE_POINT_WIDTH = 26d;

    private static final Font SUBTITLE_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private static final Font TIME_FONT = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private static final Font LEGEND_FONT = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private static final Font BAND_FONT = new Font("Microsoft YaHei", Font.BOLD, 10);

    private static final Font NOTE_FONT = new Font("Microsoft YaHei", Font.PLAIN, 9);

    private static final Font EMPTY_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);

    private static final Color SEPARATOR_COLOR = new Color(255, 255, 255, 150);

    private static final Color BAND_LABEL_COLOR = new Color(255, 255, 255, 235);

    private static final Color EMPTY_COLOR = new Color(0x90A4AE);

    private static final Color TICK_COLOR = new Color(0xB0, 0xBE, 0xC5);

    private JThemeRiverChartData config;

    private int canvasWidth = DEFAULT_CANVAS_WIDTH;

    private int canvasHeight = DEFAULT_CANVAS_HEIGHT;

    @Override
    public String renderToString(JOption option) throws IOException {
        JThemeRiverChartData data = resolveData(option);
        this.config = data;
        this.canvasWidth = data.getWidth() > 0 ? data.getWidth() : DEFAULT_CANVAS_WIDTH;
        this.canvasHeight = data.getHeight() > 0 ? data.getHeight() : DEFAULT_CANVAS_HEIGHT;
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
        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        JThemeRiverChartData data = config != null ? config : resolveData(option);
        svgGenerator.setPaint(data.getBackgroundColor());
        svgGenerator.fillRect(0, 0, width, height);
        drawHeader(svgGenerator, option, data, width);
        if (data.getSeries().isEmpty() || data.getTimeLabels().isEmpty()) {
            drawEmptyHint(svgGenerator, width, height);
            return;
        }
        if (data.getPointCount() <= 0 || data.getMaxTotal() <= 0d) {
            data.validateAndCalculate();
        }
        Chart chart = new Chart(width, height, data);
        Layout layout = new Layout(chart, data);
        drawGrid(svgGenerator, chart, data);
        drawRiver(svgGenerator, data, layout);
        drawCenterAxis(svgGenerator, chart, data);
        drawTimeLabels(svgGenerator, chart, data, width);
        drawBandLabels(svgGenerator, data, layout);
        drawLegend(svgGenerator, chart, data);
        drawUnit(svgGenerator, chart, data);
        drawFooter(svgGenerator, data, width, height);
    }

    private JThemeRiverChartData resolveData(JOption option) {
        if (option != null && option.getData() instanceof JThemeRiverChartData) {
            return (JThemeRiverChartData) option.getData();
        }
        JThemeRiverChartData data = new JThemeRiverChartData();
        if (option != null && option.title() != null) {
            data.setTitleText(option.title().text());
            data.setSubtitleText(option.title().subtext());
        }
        return data;
    }

    private void drawHeader(SVGGraphics2D svgGenerator, JOption option, JThemeRiverChartData data, int width) {
        if (data.getTitleText() == null) {
            drawTitle(svgGenerator, option, width);
            return;
        }
        svgGenerator.setFont(TITLE_FONT);
        svgGenerator.setPaint(data.getTitleColor());
        String title = data.getTitleText();
        svgGenerator.drawString(title, width / 2f - svgGenerator.getFontMetrics().stringWidth(title) / 2f, 40);
        if (data.getSubtitleText() != null) {
            svgGenerator.setFont(SUBTITLE_FONT);
            svgGenerator.setPaint(data.getSubtitleColor());
            String subtitle = data.getSubtitleText();
            svgGenerator.drawString(subtitle, width / 2f - svgGenerator.getFontMetrics().stringWidth(subtitle) / 2f, 66);
        }
    }

    /**
     * Light horizontal guides, they only help the eye to follow the thickness of a band.
     */
    private void drawGrid(SVGGraphics2D svgGenerator, Chart chart, JThemeRiverChartData data) {
        svgGenerator.setPaint(data.getGridColor());
        svgGenerator.setStroke(new BasicStroke(0.8f));
        for (int i = 0; i <= 4; i++) {
            double y = chart.top + chart.height() * i / 4d;
            if (Math.abs(y - chart.centerY) < 1d) {
                continue;
            }
            svgGenerator.draw(new Line2D.Double(chart.left, y, chart.right, y));
        }
    }

    private void drawRiver(SVGGraphics2D svgGenerator, JThemeRiverChartData data, Layout layout) {
        for (int position : layout.paintOrder) {
            Color color = data.colorAt(layout.stack.get(position));
            svgGenerator.setPaint(color);
            svgGenerator.fill(layout.band(position));
        }
        svgGenerator.setStroke(new BasicStroke(1.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        svgGenerator.setPaint(SEPARATOR_COLOR);
        for (int position : layout.paintOrder) {
            svgGenerator.draw(layout.upperEdge(position));
        }
    }

    /**
     * Dashed zero line of the river, the axis every slice is balanced around.
     */
    private void drawCenterAxis(SVGGraphics2D svgGenerator, Chart chart, JThemeRiverChartData data) {
        svgGenerator.setPaint(data.getAxisColor());
        svgGenerator.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10f, new float[]{4f, 5f}, 0f));
        svgGenerator.draw(new Line2D.Double(chart.left, chart.centerY, chart.right, chart.centerY));
        svgGenerator.setStroke(new BasicStroke(1f));
    }

    private void drawTimeLabels(SVGGraphics2D svgGenerator, Chart chart, JThemeRiverChartData data, int width) {
        int points = data.getTimeLabels().size();
        svgGenerator.setFont(TIME_FONT);
        FontMetrics metrics = svgGenerator.getFontMetrics();
        int maxLabels = Math.max(2, (int) (chart.width() / 62d));
        int step = Math.max(1, (int) Math.ceil(points / (double) maxLabels));
        int lastDrawn = -1;
        for (int i = 0; i < points; i++) {
            boolean last = i == points - 1;
            if (i % step != 0 && !last) {
                continue;
            }
            if (last && lastDrawn >= 0 && i - lastDrawn < step) {
                continue;
            }
            String text = data.getTimeLabels().get(i);
            if (text == null) {
                continue;
            }
            double x = chart.x(i);
            svgGenerator.setPaint(TICK_COLOR);
            svgGenerator.draw(new Line2D.Double(x, chart.bottom, x, chart.bottom + 4));
            svgGenerator.setPaint(data.getLabelColor());
            int textWidth = metrics.stringWidth(text);
            double textX = Math.max(4d, Math.min(x - textWidth / 2d, width - textWidth - 4d));
            svgGenerator.drawString(text, (float) textX, (float) (chart.bottom + 19));
            lastDrawn = i;
        }
    }

    /**
     * Paints the series name inside its band at the widest time point. A band that is too thin
     * keeps its name in the legend only, and a label that would collide with an already painted
     * one is dropped.
     */
    private void drawBandLabels(SVGGraphics2D svgGenerator, JThemeRiverChartData data, Layout layout) {
        if (data.getTimeLabels().size() < 2) {
            return;
        }
        List<Rectangle2D> occupied = new ArrayList<>();
        svgGenerator.setFont(BAND_FONT);
        FontMetrics metrics = svgGenerator.getFontMetrics();
        for (int index = 0; index < data.getSeries().size(); index++) {
            JThemeRiverChartData.Series item = data.getSeries().get(index);
            String text = item.getName();
            if (text == null || text.isEmpty()) {
                continue;
            }
            if (item.peak() * layout.chart.scale < MIN_LABEL_THICKNESS) {
                continue;
            }
            int peakIndex = interiorPeakIndex(item, data.getTimeLabels().size());
            double x = layout.chart.x(peakIndex);
            double centerY = layout.bandCenterY(index, peakIndex);
            int textWidth = metrics.stringWidth(text);
            int ascent = metrics.getAscent();
            Rectangle2D bounds = new Rectangle2D.Double(x - textWidth / 2d - 3, centerY - ascent / 2d - 2,
                    textWidth + 6, ascent + 4);
            if (bounds.getMinX() < layout.chart.left || bounds.getMaxX() > layout.chart.right
                    || intersects(occupied, bounds)) {
                continue;
            }
            occupied.add(bounds);
            svgGenerator.setPaint(BAND_LABEL_COLOR);
            svgGenerator.drawString(text, (float) (x - textWidth / 2d), (float) (centerY + ascent / 2d - 2));
        }
    }

    private void drawLegend(SVGGraphics2D svgGenerator, Chart chart, JThemeRiverChartData data) {
        svgGenerator.setFont(LEGEND_FONT);
        FontMetrics metrics = svgGenerator.getFontMetrics();
        double swatch = 11d;
        double swatchGap = 6d;
        double itemGap = 26d;
        List<List<Integer>> rows = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        double rowWidth = 0d;
        for (int i = 0; i < data.getSeries().size(); i++) {
            double itemWidth = swatch + swatchGap + metrics.stringWidth(nameOf(data, i));
            if (!row.isEmpty() && rowWidth + itemGap + itemWidth > chart.width()) {
                rows.add(row);
                row = new ArrayList<>();
                rowWidth = 0d;
            }
            rowWidth = row.isEmpty() ? itemWidth : rowWidth + itemGap + itemWidth;
            row.add(i);
        }
        if (!row.isEmpty()) {
            rows.add(row);
        }
        double startY = chart.bottom + 62d;
        for (int r = 0; r < rows.size(); r++) {
            List<Integer> items = rows.get(r);
            double width = 0d;
            for (int i = 0; i < items.size(); i++) {
                width += swatch + swatchGap + metrics.stringWidth(nameOf(data, items.get(i)));
                if (i > 0) {
                    width += itemGap;
                }
            }
            double x = chart.centerX - width / 2d;
            double y = startY + r * 20d;
            for (Integer index : items) {
                svgGenerator.setPaint(data.colorAt(index));
                svgGenerator.fill(new RoundRectangle2D.Double(x, y - 7d, swatch, swatch, 3d, 3d));
                svgGenerator.setPaint(data.getLabelColor());
                double textX = x + swatch + swatchGap;
                svgGenerator.drawString(nameOf(data, index), (float) textX, (float) (y + 2d));
                x = textX + metrics.stringWidth(nameOf(data, index)) + itemGap;
            }
        }
    }

    private void drawUnit(SVGGraphics2D svgGenerator, Chart chart, JThemeRiverChartData data) {
        String unit = data.getValueUnit();
        if (unit == null || unit.trim().isEmpty()) {
            return;
        }
        svgGenerator.setFont(NOTE_FONT);
        svgGenerator.setPaint(data.getNoteColor());
        String text = "单位：" + unit.trim();
        svgGenerator.drawString(text, (float) (chart.right - svgGenerator.getFontMetrics().stringWidth(text)),
                (float) (chart.top - 14d));
    }

    private void drawFooter(SVGGraphics2D svgGenerator, JThemeRiverChartData data, int width, int height) {
        String note = data.getFooterText();
        if (note == null) {
            return;
        }
        svgGenerator.setFont(NOTE_FONT);
        svgGenerator.setPaint(data.getNoteColor());
        int textWidth = svgGenerator.getFontMetrics().stringWidth(note);
        svgGenerator.drawString(note, (float) (width / 2d - textWidth / 2d), (float) (height - 16d));
    }

    private void drawEmptyHint(SVGGraphics2D svgGenerator, int width, int height) {
        svgGenerator.setFont(EMPTY_FONT);
        svgGenerator.setPaint(EMPTY_COLOR);
        String hint = "未提供河流数据：请通过 JOption.setData(new JThemeRiverChartData(...)) 传入时间轴与系列";
        int textWidth = svgGenerator.getFontMetrics().stringWidth(hint);
        svgGenerator.drawString(hint, (float) (width / 2d - textWidth / 2d), (float) (height / 2d));
    }

    private static String nameOf(JThemeRiverChartData data, int index) {
        String name = data.getSeries().get(index).getName();
        return name == null ? "系列 " + (index + 1) : name;
    }

    /**
     * Index of the widest interior slice, used to place the band label. The first and the last
     * time point sit on the plot border where a centred label would be clipped, so they are
     * skipped whenever an interior slice exists.
     */
    private static int interiorPeakIndex(JThemeRiverChartData.Series item, int points) {
        if (points < 3) {
            return item.peakIndex();
        }
        int index = -1;
        double peak = -Double.MAX_VALUE;
        List<Double> values = item.getValues();
        for (int i = 1; i < points - 1; i++) {
            double value = values.get(i) == null ? 0d : values.get(i);
            if (value > peak) {
                peak = value;
                index = i;
            }
        }
        return index < 0 ? item.peakIndex() : index;
    }

    private static boolean intersects(List<Rectangle2D> occupied, Rectangle2D bounds) {
        for (Rectangle2D item : occupied) {
            if (item.intersects(bounds)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Canvas geometry and the value to pixel mapping of one render pass.
     */
    private static final class Chart {

        private final double left;

        private final double top;

        private final double right;

        private final double bottom;

        private final double centerX;

        private final double centerY;

        private final double scale;

        private final int pointCount;

        private Chart(int width, int height, JThemeRiverChartData data) {
            this.left = PLOT_MARGIN_X;
            this.top = PLOT_TOP;
            this.right = width - PLOT_MARGIN_X;
            this.bottom = height - PLOT_BOTTOM_OFFSET;
            this.centerX = width / 2d;
            this.centerY = (top + bottom) / 2d;
            this.pointCount = data.getTimeLabels().size();
            this.scale = (bottom - top) / Math.max(1e-6d, data.getMaxTotal());
        }

        private double width() {
            return right - left;
        }

        private double height() {
            return bottom - top;
        }

        private double x(int index) {
            if (pointCount <= 1) {
                return centerX;
            }
            return left + width() * index / (pointCount - 1d);
        }

        private double y(double value) {
            return centerY - value * scale;
        }
    }

    /**
     * Resolved stacking geometry of one render pass. Every slice is stacked around zero, so the
     * band edges can be reused by the fill pass, the separator pass and the label pass.
     */
    private static final class Layout {

        private final Chart chart;

        private final List<Integer> stack;

        private final List<Integer> paintOrder;

        private final double[][] low;

        private final double[][] high;

        private final int pointCount;

        private Layout(Chart chart, JThemeRiverChartData data) {
            this.chart = chart;
            this.stack = stackOrder(data);
            this.paintOrder = outerInOrder(stack.size());
            this.pointCount = data.getTimeLabels().size();
            int count = stack.size();
            this.low = new double[count][pointCount];
            this.high = new double[count][pointCount];
            for (int point = 0; point < pointCount; point++) {
                double total = 0d;
                for (int index = 0; index < count; index++) {
                    total += Math.max(0d, valueOf(data, index, point));
                }
                double cursor = -total / 2d;
                for (int position = 0; position < count; position++) {
                    double value = Math.max(0d, valueOf(data, stack.get(position), point));
                    low[position][point] = cursor;
                    high[position][point] = cursor + value;
                    cursor += value;
                }
            }
        }

        private Path2D.Double band(int position) {
            Path2D.Double path = new Path2D.Double();
            if (pointCount == 1) {
                double x = chart.x(0);
                double top = chart.y(high[position][0]);
                double bottom = chart.y(low[position][0]);
                path.append(new RoundRectangle2D.Double(x - SINGLE_POINT_WIDTH / 2d, top, SINGLE_POINT_WIDTH,
                        Math.max(1d, bottom - top), SINGLE_POINT_WIDTH, SINGLE_POINT_WIDTH), false);
                return path;
            }
            List<Point2D> upper = screenPoints(high[position]);
            List<Point2D> lower = screenPoints(low[position]);
            appendSpline(path, upper, true);
            Point2D tail = lower.get(lower.size() - 1);
            path.lineTo(tail.getX(), tail.getY());
            List<Point2D> reversed = new ArrayList<>(lower);
            Collections.reverse(reversed);
            appendSpline(path, reversed, false);
            path.closePath();
            return path;
        }

        private Path2D.Double upperEdge(int position) {
            Path2D.Double path = new Path2D.Double();
            appendSpline(path, screenPoints(high[position]), true);
            return path;
        }

        private List<Point2D> screenPoints(double[] values) {
            List<Point2D> points = new ArrayList<>(values.length);
            for (int i = 0; i < values.length; i++) {
                points.add(new Point2D.Double(chart.x(i), chart.y(values[i])));
            }
            return points;
        }

        private double bandCenterY(int seriesIndex, int point) {
            int position = stack.indexOf(seriesIndex);
            double middle = (low[position][point] + high[position][point]) / 2d;
            return chart.y(middle);
        }
    }

    /**
     * Appends a Catmull-Rom spline through the points, converted to cubic Bezier segments.
     *
     * @param path      target path
     * @param points    anchor points in drawing order
     * @param moveFirst true to start a new sub path, false to continue from the current point
     */
    private static void appendSpline(Path2D.Double path, List<Point2D> points, boolean moveFirst) {
        int size = points.size();
        if (size == 0) {
            return;
        }
        if (moveFirst) {
            path.moveTo(points.get(0).getX(), points.get(0).getY());
        }
        if (size < 2) {
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            Point2D previous = points.get(Math.max(0, i - 1));
            Point2D current = points.get(i);
            Point2D next = points.get(i + 1);
            Point2D following = points.get(Math.min(size - 1, i + 2));
            double firstX = current.getX() + (next.getX() - previous.getX()) / 6d;
            double firstY = current.getY() + (next.getY() - previous.getY()) / 6d;
            double secondX = next.getX() - (following.getX() - current.getX()) / 6d;
            double secondY = next.getY() - (following.getY() - current.getY()) / 6d;
            path.curveTo(firstX, firstY, secondX, secondY, next.getX(), next.getY());
        }
    }

    /**
     * Orders the stack so the largest series sits near the centre of the river: the first one
     * is appended at the tail, then the remaining ones alternate between head and tail.
     */
    private static List<Integer> stackOrder(final JThemeRiverChartData data) {
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < data.getSeries().size(); i++) {
            sorted.add(i);
        }
        Collections.sort(sorted, new Comparator<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return Double.compare(data.getSeries().get(right).total(), data.getSeries().get(left).total());
            }
        });
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < sorted.size(); i++) {
            if (i % 2 == 0) {
                stack.addLast(sorted.get(i));
            } else {
                stack.addFirst(sorted.get(i));
            }
        }
        return new ArrayList<>(stack);
    }

    /**
     * Paint order of the stack positions: the outer bands first, so the inner bands stay on top
     * and keep their full colour.
     */
    private static List<Integer> outerInOrder(int count) {
        List<Integer> order = new ArrayList<>(count);
        int bottom = 0;
        int top = count - 1;
        boolean fromBottom = true;
        for (int i = 0; i < count; i++) {
            order.add(fromBottom ? bottom++ : top--);
            fromBottom = !fromBottom;
        }
        return order;
    }

    private static double valueOf(JThemeRiverChartData data, int seriesIndex, int pointIndex) {
        Double value = data.getSeries().get(seriesIndex).getValues().get(pointIndex);
        return value == null ? 0d : value;
    }
}

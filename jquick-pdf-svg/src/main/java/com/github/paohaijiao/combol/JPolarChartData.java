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

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @classname JPolarChartData
 * @date 2026/11/8
 * @description Data model of the polar chart.
 * <p>
 * The renderer reads it from {@code JOption.setData(...)}, the same way
 * {@code JCircleChartData} is passed to the ring chart. The model describes three things:
 * the polar grid (angle slots and radius rings), the data series drawn on it, and the
 * background sectors used to highlight an angle range.
 * <p>
 * Angles are screen angles: {@code 0} points at three o'clock and the value increases
 * clockwise, because the canvas has its origin in the top left corner. Every series is
 * sampled at the same {@code slotCount} positions, so a value index always lines up with
 * the same angle label.
 */
@Data
public class JPolarChartData implements JGraphData {

    /**
     * Canvas size.
     */
    private int width = 500;

    private int height = 500;

    /**
     * Distance kept between the outermost ring and the canvas edge, room for the angle labels.
     */
    private int padding = 58;

    private String titleText;

    private String subtitleText;

    private String footerText;

    /**
     * Unit printed next to the radius labels, e.g. {@code 万元}. May be null.
     */
    private String valueUnit;

    /**
     * Screen angle of the first slot in degrees: 0 is three o'clock, the value grows clockwise.
     */
    private float startAngle = 0f;

    /**
     * Sweep covered by the whole plot. A value below 360 leaves an open wedge.
     */
    private float angleSpan = 360f;

    /**
     * Angle labels, one per slot. When empty the renderer keeps the slots derived from the
     * series length but prints no angle text.
     */
    private List<String> angleLabels = new ArrayList<>();

    /**
     * Lower bound of the radius axis.
     */
    private double radiusMin = 0d;

    /**
     * Upper bound of the radius axis. Zero means "derive from the data" and round up to the
     * next readable value.
     */
    private double radiusMax = 0d;

    /**
     * Number of concentric rings drawn between {@code radiusMin} and the computed radius max.
     */
    private int radiusTickCount = 4;

    private boolean showAngleLabels = true;

    private boolean showRadiusLabels = true;

    /**
     * Draws a small marker in the centre. Off when the centre must stay clean.
     */
    private boolean showCenterPoint = true;

    /**
     * Draws the two thicker lines through the centre.
     */
    private boolean showAxes = true;

    private boolean showLegend = true;

    private Color backgroundColor = Color.WHITE;

    private Color gridColor = new Color(0xDDDDDD);

    private Color spokeColor = new Color(0xEEEEEE);

    private Color axisColor = new Color(0xAAAAAA);

    private Color labelColor = new Color(0x666666);

    private Color legendTextColor = new Color(0x777777);

    private Color footerColor = new Color(0x999999);

    private Font titleFont = new Font("Microsoft YaHei", Font.BOLD, 15);

    private Font subtitleFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font labelFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    private Font legendFont = new Font("Microsoft YaHei", Font.PLAIN, 11);

    private Font footerFont = new Font("Microsoft YaHei", Font.PLAIN, 10);

    /**
     * Data series drawn on the grid, in drawing order.
     */
    private List<PolarSeries> series = new ArrayList<>();

    /**
     * Background sectors drawn under the series.
     */
    private List<PolarSector> sectors = new ArrayList<>();

    /**
     * Palette applied to series that carry no colour of their own.
     */
    private List<Color> colors = new ArrayList<>(Arrays.asList(
            new Color(0xFF9F43), new Color(0x2F6F8F), new Color(0x3E8E7E), new Color(0xC95C5C),
            new Color(0x8A6FB0), new Color(0x4A6FA5), new Color(0x7F9A3C), new Color(0xA85B78)));

    /**
     * Number of angle slots, filled by {@link #validateAndCalculate()}.
     */
    private int slotCount;

    /**
     * Radius bound actually used for drawing, filled by {@link #validateAndCalculate()}.
     */
    private double computedRadiusMax;

    /**
     * Validates the series and fills the derived fields: slot count, radius bound, series
     * colours and the footer text when none was given.
     * <p>
     * Every series must expose exactly as many values as there are slots. The requirement is
     * strict on purpose: a shorter series would silently shift every following value to a
     * different angle.
     */
    public void validateAndCalculate() {
        JAssert.isTrue(series != null && !series.isEmpty(), "polar series require not empty");
        JAssert.isTrue(angleSpan > 0f && angleSpan <= 360f, "angle span require in (0, 360]");
        JAssert.isTrue(radiusTickCount > 0, "radius tick count require greater than zero");
        JAssert.isTrue(padding > 0, "padding require greater than zero");

        int slots = angleLabels == null || angleLabels.isEmpty() ? 0 : angleLabels.size();
        for (PolarSeries item : series) {
            JAssert.notNull(item, "polar series require not null");
            JAssert.isTrue(item.getValues() != null && !item.getValues().isEmpty(),
                    "polar series values require not empty");
            if (slots == 0) {
                slots = item.getValues().size();
            }
            JAssert.isTrue(item.getValues().size() == slots,
                    "polar series '" + item.getName() + "' require " + slots + " values but was "
                            + item.getValues().size());
        }
        JAssert.isTrue(slots > 0, "polar chart require at least one angle slot");
        this.slotCount = slots;

        double dataMax = radiusMin;
        for (PolarSeries item : series) {
            for (Double value : item.getValues()) {
                JAssert.notNull(value, "polar series value require not null");
                dataMax = Math.max(dataMax, value);
            }
        }
        this.computedRadiusMax = radiusMax > 0d ? radiusMax : niceCeil(dataMax);
        JAssert.isTrue(computedRadiusMax > radiusMin,
                "radius max require greater than radius min");

        List<Color> palette = colors == null || colors.isEmpty()
                ? new ArrayList<>(Arrays.asList(new Color(0xFF9F43)))
                : colors;
        for (int i = 0; i < series.size(); i++) {
            PolarSeries item = series.get(i);
            if (item.getColor() == null) {
                item.setColor(palette.get(Math.floorMod(i, palette.size())));
            }
            if (item.getName() == null || item.getName().isEmpty()) {
                item.setName("系列 " + (i + 1));
            }
        }
        if (footerText == null) {
            footerText = buildFooterText();
        }
    }

    /**
     * Angle of a slot in degrees, using the model start angle and sweep.
     *
     * @param index slot index
     * @return screen angle in degrees
     */
    public double angleOf(int index) {
        double step = angleSpan / slotCount;
        return startAngle + step * index;
    }

    /**
     * Angle distance between two neighbouring slots in degrees.
     *
     * @return slot width
     */
    public double slotAngle() {
        return angleSpan / slotCount;
    }

    /**
     * Radius position of a value inside the current radius range.
     *
     * @param value data value
     * @return position in {@code [0, 1]}
     */
    public double radiusRatio(double value) {
        double span = computedRadiusMax - radiusMin;
        return span <= 0d ? 0d : (value - radiusMin) / span;
    }

    /**
     * Value printed on a ring of the radius axis.
     *
     * @param index ring index, 1 based from the inner ring
     * @return axis value of that ring
     */
    public double tickValue(int index) {
        return radiusMin + (computedRadiusMax - radiusMin) * index / radiusTickCount;
    }

    /**
     * Default data used when the option carries no model: two rose curves on a twelve slot
     * grid with a highlighted sector, which is the preview shipped in the gallery.
     *
     * @return ready to render sample data
     */
    public static JPolarChartData sample() {
        JPolarChartData data = new JPolarChartData();
        data.setTitleText("极坐标图");
        data.setSubtitleText("r = a·sin(4θ) · 12 个角度刻度");
        data.setAngleLabels(new ArrayList<>(Arrays.asList(
                "0°", "30°", "60°", "90°", "120°", "150°",
                "180°", "210°", "240°", "270°", "300°", "330°")));
        data.setRadiusMax(100d);
        data.setRadiusTickCount(4);
        data.setSectors(new ArrayList<>(Arrays.asList(
                new PolarSector(45d, 135d, new Color(0xFF6B6B), 0.10f))));

        PolarSeries outer = new PolarSeries("r = a·sin(4θ)", rose(100d, 12));
        outer.setColor(new Color(0xFF9F43));
        outer.setDash(new float[]{4f, 3f});
        outer.setShowPoints(true);

        PolarSeries inner = new PolarSeries("r = 0.5a·sin(4θ)", rose(50d, 12));
        inner.setColor(new Color(0xFFC078));
        inner.setLineWidth(1.4f);

        data.setSeries(new ArrayList<>(Arrays.asList(outer, inner)));
        return data;
    }

    /**
     * Samples {@code amplitude * |sin(4 * theta)|} on an even slot grid.
     *
     * @param amplitude peak radius of the curve
     * @param slots     number of samples
     * @return sampled values
     */
    private static List<Double> rose(double amplitude, int slots) {
        List<Double> values = new ArrayList<>(slots);
        for (int i = 0; i < slots; i++) {
            double theta = Math.toRadians(360d / slots * i);
            values.add(Math.abs(Math.sin(4d * theta)) * amplitude);
        }
        return values;
    }

    private String buildFooterText() {
        String unit = valueUnit == null ? "" : valueUnit;
        return "共 " + series.size() + " 个系列 · " + slotCount + " 个角度刻度 · 半径 "
                + trim(radiusMin) + "–" + trim(computedRadiusMax) + unit;
    }

    private static String trim(double value) {
        return value == Math.rint(value) ? String.valueOf((long) value) : String.valueOf(value);
    }

    private static double niceCeil(double value) {
        if (value <= 0d) {
            return 1d;
        }
        double exponent = Math.floor(Math.log10(value));
        double base = Math.pow(10d, exponent);
        double fraction = value / base;
        double nice;
        if (fraction <= 1d) {
            nice = 1d;
        } else if (fraction <= 2d) {
            nice = 2d;
        } else if (fraction <= 2.5d) {
            nice = 2.5d;
        } else if (fraction <= 5d) {
            nice = 5d;
        } else {
            nice = 10d;
        }
        return nice * base;
    }

    /**
     * One curve drawn on the polar grid.
     * <p>
     * Build it with the varargs constructor or with the chained {@link #addValue(double)}:
     * <pre>
     * PolarSeries series = new PolarSeries("自然搜索").addValue(420).addValue(510);
     * </pre>
     */
    @Data
    public static class PolarSeries {

        private String name;

        private Color color;

        /**
         * One value per angle slot, in slot order.
         */
        private List<Double> values = new ArrayList<>();

        /**
         * Closes the curve from the last slot back to the first one.
         */
        private boolean closed = true;

        /**
         * Fills the area between the curve and the centre.
         */
        private boolean filled = false;

        private float fillAlpha = 0.15f;

        private float lineWidth = 1.8f;

        /**
         * Dash pattern such as {@code {4, 3}}. Null draws a solid line.
         */
        private float[] dash;

        private boolean showPoints = false;

        private int pointRadius = 3;

        public PolarSeries() {
        }

        public PolarSeries(String name) {
            this.name = name;
        }

        public PolarSeries(String name, List<Double> values) {
            this.name = name;
            this.values = new ArrayList<>(values);
        }

        public PolarSeries(String name, Double... values) {
            this.name = name;
            this.values = new ArrayList<>(Arrays.asList(values));
        }

        public PolarSeries(String name, Color color, Double... values) {
            this.name = name;
            this.color = color;
            this.values = new ArrayList<>(Arrays.asList(values));
        }

        public PolarSeries addValue(double value) {
            this.values.add(value);
            return this;
        }
    }

    /**
     * A filled wedge drawn under the series, used to highlight an angle range.
     */
    @Data
    public static class PolarSector {

        private double startAngle;

        private double endAngle;

        /**
         * Radius of the wedge as a share of the computed radius max, {@code 1} reaches the
         * outer ring.
         */
        private double radiusRatio = 1d;

        private Color color = new Color(0xFF6B6B);

        private float alpha = 0.10f;

        private String label;

        public PolarSector() {
        }

        public PolarSector(double startAngle, double endAngle) {
            this.startAngle = startAngle;
            this.endAngle = endAngle;
        }

        public PolarSector(double startAngle, double endAngle, Color color, float alpha) {
            this.startAngle = startAngle;
            this.endAngle = endAngle;
            this.color = color;
            this.alpha = alpha;
        }
    }
}

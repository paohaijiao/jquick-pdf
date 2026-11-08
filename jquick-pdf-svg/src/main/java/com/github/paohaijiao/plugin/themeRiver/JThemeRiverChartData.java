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

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.graph.JGraphData;
import lombok.Data;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.plugin.themeRiver
 *
 * @author Martin
 * @version 1.0.0
 * @classname JThemeRiverChartData
 * @date 2026/11/8
 * @description Data model of the theme river chart.
 * <p>
 * The renderer reads it from {@code JOption.setData(...)}, the same way
 * {@code JCircleChartData} is passed to the ring chart. Build one time axis with
 * {@link #timeLabels(String...)} and one series per stream with
 * {@link #addSeries(String, double...)}; every series must hold exactly one value per
 * time label.
 * <p>
 * {@link #validateAndCalculate()} checks the shape of the data, assigns the palette colour
 * to series that carry none, and fills the derived values used by the layout: point count,
 * the widest total of one time slice and the largest series total.
 */
@Data
public class JThemeRiverChartData implements JGraphData {

    private static final long serialVersionUID = 1L;

    /**
     * Canvas size. Zero means the renderer uses its default size.
     */
    private int width = 0;

    private int height = 0;

    private String titleText;

    private String subtitleText;

    private String footerText;

    /**
     * Unit printed at the top right corner, for example 万元. Null or empty hides it.
     */
    private String valueUnit = "件";

    private Color backgroundColor = Color.WHITE;

    private Color titleColor = new Color(0x263238);

    private Color subtitleColor = new Color(0x78909C);

    private Color labelColor = new Color(0x546E7A);

    private Color noteColor = new Color(0x90A4AE);

    private Color axisColor = new Color(0xB0BEC5);

    private Color gridColor = new Color(0xECEFF1);

    /**
     * Time axis labels, one per position on the horizontal axis.
     */
    private List<String> timeLabels = new ArrayList<>();

    /**
     * Streams of the river, drawn in stacked order from the bottom edge to the top edge.
     */
    private List<Series> series = new ArrayList<>();

    /**
     * Palette used for series that carry no explicit colour.
     */
    private List<Color> palette = new ArrayList<>(Arrays.asList(
            new Color(0x2F6F8F), new Color(0xC97B4A), new Color(0x3E8E7E), new Color(0x8A6FB0),
            new Color(0x4A6FA5), new Color(0x7F9A3C), new Color(0xA85B78), new Color(0x5C7A8A),
            new Color(0xD1A24C), new Color(0x9C5B8A)));

    /**
     * Number of time points, filled by {@link #validateAndCalculate()}.
     */
    private int pointCount;

    /**
     * Widest total of one time slice, filled by {@link #validateAndCalculate()}.
     */
    private double maxTotal;

    /**
     * Largest series total over the whole time axis, filled by {@link #validateAndCalculate()}.
     */
    private double maxSeriesTotal;

    /**
     * Sets the time axis in one call.
     *
     * @param labels time labels, ordered from left to right
     * @return this instance
     */
    public JThemeRiverChartData timeLabels(String... labels) {
        this.timeLabels = new ArrayList<>();
        if (labels != null) {
            this.timeLabels.addAll(Arrays.asList(labels));
        }
        return this;
    }

    /**
     * Appends a series that takes its colour from the palette.
     *
     * @param name   series name, printed in the legend
     * @param values one value per time label
     * @return this instance
     */
    public JThemeRiverChartData addSeries(String name, double... values) {
        this.series.add(new Series(name, values));
        return this;
    }

    /**
     * Appends a series with an explicit colour.
     *
     * @param name   series name, printed in the legend
     * @param color  band colour
     * @param values one value per time label
     * @return this instance
     */
    public JThemeRiverChartData addSeries(String name, Color color, double... values) {
        this.series.add(new Series(name, color, values));
        return this;
    }

    /**
     * Returns the colour of a series, falling back to the palette when none is set.
     *
     * @param index series index
     * @return band colour
     */
    public Color colorAt(int index) {
        Color color = series.get(index).getColor();
        if (color != null) {
            return color;
        }
        return palette.get(Math.floorMod(index, palette.size()));
    }

    /**
     * Validates the data shape and fills the derived fields.
     * <p>
     * The number of values of every series must equal the number of time labels, otherwise
     * the layout would leave holes on the time axis.
     */
    public void validateAndCalculate() {
        JAssert.isTrue(series != null && !series.isEmpty(), "theme river requires at least one series");
        JAssert.isTrue(timeLabels != null && !timeLabels.isEmpty(), "theme river requires time labels");
        JAssert.isTrue(palette != null && !palette.isEmpty(), "theme river requires a color palette");
        int points = timeLabels.size();
        double widest = 0d;
        double largest = 0d;
        for (int i = 0; i < series.size(); i++) {
            Series item = series.get(i);
            JAssert.isTrue(item.getValues() != null && item.getValues().size() == points,
                    "series " + item.getName() + " requires " + points + " values to match the time axis");
            if (item.getColor() == null) {
                item.setColor(palette.get(Math.floorMod(i, palette.size())));
            }
            largest = Math.max(largest, item.total());
        }
        for (int i = 0; i < points; i++) {
            double total = 0d;
            for (Series item : series) {
                total += Math.max(0d, item.getValues().get(i));
            }
            widest = Math.max(widest, total);
        }
        this.pointCount = points;
        this.maxTotal = widest;
        this.maxSeriesTotal = largest;
        if (footerText == null) {
            footerText = buildFooterText();
        }
    }

    /**
     * Formats a value with a thousands separator and at most two decimals.
     *
     * @param value raw value
     * @return formatted text
     */
    public String formatValue(double value) {
        return new DecimalFormat("#,##0.##").format(value);
    }

    private String buildFooterText() {
        String unit = valueUnit == null ? "" : valueUnit;
        return "共 " + series.size() + " 个系列 · " + pointCount + " 个时间点 · 峰值合计 "
                + formatValue(maxTotal) + unit;
    }

    /**
     * One stream of the river. Build it with the chained {@link #add(double)} method when the
     * values are produced one by one:
     * <pre>
     * new Series("搜索引擎").add(120).add(132).add(101);
     * </pre>
     */
    @Data
    public static class Series {

        private static final long serialVersionUID = 1L;

        private String name;

        private Color color;

        private List<Double> values = new ArrayList<>();

        public Series() {
        }

        public Series(String name, double... values) {
            this.name = name;
            append(values);
        }

        public Series(String name, Color color, double... values) {
            this.name = name;
            this.color = color;
            append(values);
        }

        /**
         * Appends one value to the time axis.
         *
         * @param value value of the next time point
         * @return this instance
         */
        public Series add(double value) {
            this.values.add(value);
            return this;
        }

        /**
         * Sum of all values of this series.
         *
         * @return total value
         */
        public double total() {
            double sum = 0d;
            for (Double value : values) {
                sum += value == null ? 0d : Math.max(0d, value);
            }
            return sum;
        }

        /**
         * Largest value of this series, used to place the band label.
         *
         * @return peak value, zero when the series is empty
         */
        public double peak() {
            double peak = 0d;
            for (Double value : values) {
                peak = Math.max(peak, value == null ? 0d : value);
            }
            return peak;
        }

        /**
         * Index of the peak value, used to place the band label.
         *
         * @return index of the largest value
         */
        public int peakIndex() {
            int index = 0;
            double peak = -Double.MAX_VALUE;
            for (int i = 0; i < values.size(); i++) {
                double value = values.get(i) == null ? 0d : values.get(i);
                if (value > peak) {
                    peak = value;
                    index = i;
                }
            }
            return index;
        }

        private void append(double[] values) {
            if (values == null) {
                return;
            }
            for (double value : values) {
                this.values.add(value);
            }
        }
    }
}

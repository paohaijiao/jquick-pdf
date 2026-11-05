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
package com.github.paohaijiao.progress;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import com.github.paohaijiao.series.JSeries;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.Color;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.progress
 *
 * @author Martin
 * @version 1.0.0
 * @className JProgressBarChartRenderer
 * @date 2026/11/5
 * @description Horizontal progress bar renderer, registered through {@link JQuickSpi}.
 * <p>
 * Categories come from the x axis when a single series is given, otherwise each series
 * name is used as the row label and its first value as the progress. Values are treated
 * as percentages and clipped to 0-100.
 */
@JQuickSpi(name = "progress", order = 33, chartType = JChartType.Progress)
public class JProgressBarChartRenderer extends JAbstractChartRenderer {

    private static final int DEFAULT_WIDTH = 720;

    private static final int DEFAULT_HEIGHT = 300;

    private static final Color TRACK_COLOR = new Color(0xEC, 0xEF, 0xF1);

    private static final Color LABEL_COLOR = new Color(0x2C, 0x3E, 0x50);

    private static final Color VALUE_COLOR = new Color(0x34, 0x49, 0x5E);

    private static final Color EXCELLENT_COLOR = new Color(0x2E, 0xCC, 0x71);

    private static final Color GOOD_COLOR = new Color(0x34, 0x98, 0xDB);

    private static final Color WARNING_COLOR = new Color(0xF3, 0x9C, 0x12);

    private static final Color RISK_COLOR = new Color(0xE7, 0x4C, 0x3C);

    @Override
    protected int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    protected int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    @Override
    protected void drawChart(SVGGraphics2D g2d, JOption option, int width, int height) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawTitle(g2d, option, width);
        List<String> labels = resolveLabels(option);
        List<Double> values = resolveValues(option);
        int rows = Math.min(labels.size(), values.size());
        if (rows == 0) {
            return;
        }
        boolean titled = option.title() != null && option.title().text() != null;
        int top = titled ? 100 : 40;
        int rowHeight = Math.max(28, (height - top - 24) / rows);
        int barHeight = Math.min(18, rowHeight / 2);
        int labelRight = 150;
        int trackX = labelRight + 16;
        int trackWidth = width - trackX - 76;
        int valueX = trackX + trackWidth + 12;
        g2d.setFont(LABEL_FONT);
        for (int i = 0; i < rows; i++) {
            int centerY = top + i * rowHeight + rowHeight / 2;
            int y = centerY - barHeight / 2;
            String label = labels.get(i);
            double value = Math.max(0d, Math.min(100d, values.get(i)));
            g2d.setPaint(LABEL_COLOR);
            g2d.drawString(label, labelRight - g2d.getFontMetrics().stringWidth(label), centerY + 5);
            g2d.setPaint(TRACK_COLOR);
            g2d.fillRoundRect(trackX, y, trackWidth, barHeight, barHeight, barHeight);
            int filled = (int) Math.round(trackWidth * value / 100d);
            if (filled > 0) {
                g2d.setPaint(colorOf(value));
                g2d.fillRoundRect(trackX, y, Math.min(Math.max(filled, barHeight), trackWidth), barHeight, barHeight, barHeight);
            }
            g2d.setPaint(VALUE_COLOR);
            String text = format(value);
            g2d.drawString(text, valueX, centerY + 5);
        }
    }

    private List<String> resolveLabels(JOption option) {
        List<String> labels = new ArrayList<>();
        List seriesList = option.series();
        if (seriesList == null || seriesList.isEmpty()) {
            return labels;
        }
        if (seriesList.size() == 1) {
            List categories = categories(option);
            if (!categories.isEmpty()) {
                for (Object item : categories) {
                    labels.add(String.valueOf(item));
                }
                return labels;
            }
        }
        for (Object item : seriesList) {
            JSeries series = (JSeries) item;
            String name = series.getName();
            labels.add(name == null || name.isEmpty() ? "指标" + (labels.size() + 1) : name);
        }
        return labels;
    }

    private List<Double> resolveValues(JOption option) {
        List<Double> values = new ArrayList<>();
        List seriesList = option.series();
        if (seriesList == null || seriesList.isEmpty()) {
            return values;
        }
        if (seriesList.size() == 1) {
            JSeries series = (JSeries) seriesList.get(0);
            List data = series.data();
            if (data != null) {
                for (Object item : data) {
                    addValue(values, item);
                }
            }
            return values;
        }
        for (Object item : seriesList) {
            JSeries series = (JSeries) item;
            List data = series.data();
            if (data != null && !data.isEmpty()) {
                addValue(values, data.get(0));
            }
        }
        return values;
    }

    private List categories(JOption option) {
        List<Object> categories = new ArrayList<>();
        List axisList = option.xAxis();
        if (axisList == null || axisList.isEmpty()) {
            return categories;
        }
        Object axis = axisList.get(0);
        if (!(axis instanceof JCategoryAxis)) {
            return categories;
        }
        List data = ((JCategoryAxis) axis).data();
        if (data != null) {
            categories.addAll(data);
        }
        return categories;
    }

    private void addValue(List<Double> values, Object item) {
        if (item instanceof Number) {
            values.add(((Number) item).doubleValue());
        } else if (item instanceof Object[]) {
            Object[] array = (Object[]) item;
            if (array.length > 0) {
                addValue(values, array[array.length - 1]);
            }
        } else if (item != null) {
            try {
                values.add(Double.parseDouble(String.valueOf(item).replace("%", "").trim()));
            } catch (NumberFormatException ignore) {
                // non numeric item is skipped, the remaining rows keep their own values
            }
        }
    }

    private Color colorOf(double value) {
        if (value >= 90d) {
            return EXCELLENT_COLOR;
        }
        if (value >= 80d) {
            return GOOD_COLOR;
        }
        if (value >= 70d) {
            return WARNING_COLOR;
        }
        return RISK_COLOR;
    }

    private String format(double value) {
        if (value == Math.rint(value)) {
            return String.format("%.0f%%", value);
        }
        return String.format("%.1f%%", value);
    }
}

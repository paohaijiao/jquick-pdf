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
package com.github.paohaijiao.plugin.waterfall;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JQuickSpi;
import org.apache.batik.svggen.SVGGraphics2D;

import java.awt.Color;

/**
 * packageName com.github.paohaijiao.demo
 *
 * @author Martin
 * @version 1.0.0
 * @className JWaterfallRenderer
 * @date 2026/11/5
 * @description Sample custom renderer: waterfall chart.
 * <p>
 * Extend JAbstractChartRenderer and add {@link JQuickSpi} bound to
 * {@link JChartType#Waterfall}. The factory picks it up without any change.
 */
@JQuickSpi(name = "waterfall", order = 100, chartType = JChartType.Waterfall)
public class JWaterfallRenderer extends JAbstractChartRenderer {

    private static final int[] VALUES = {120, -40, 60, -30, 90};

    private static final Color RISE_COLOR = new Color(0xC0, 0x39, 0x2B);

    private static final Color FALL_COLOR = new Color(0x27, 0xAE, 0x60);

    @Override
    protected void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height) {
        drawTitle(svgGenerator, option, width);
        int baseline = height - 80;
        int barWidth = 60;
        int gap = 30;
        int startX = (width - (barWidth + gap) * VALUES.length + gap) / 2;
        int cumulative = 0;
        for (int i = 0; i < VALUES.length; i++) {
            int value = VALUES[i];
            int top = value >= 0 ? baseline - (cumulative + value) : baseline - cumulative;
            int barHeight = Math.abs(value) * 2;
            svgGenerator.setPaint(value >= 0 ? RISE_COLOR : FALL_COLOR);
            svgGenerator.fillRect(startX + i * (barWidth + gap), top, barWidth, barHeight);
            cumulative += value;
        }
        svgGenerator.setPaint(Color.DARK_GRAY);
        svgGenerator.drawLine(startX - 20, baseline, startX + VALUES.length * (barWidth + gap), baseline);
    }
}

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
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @classname JPolarChartDataTest
 * @date 2026/11/8
 * @description Tests of the polar chart data model and of the renderer that consumes it.
 * Every SVG is written to {@code d:/test/polar}.
 */
public class JPolarChartDataTest {

    private static final String OUTPUT_DIR = "d://test/polar/";

    @Test
    public void testSampleModel() {
        JPolarChartData data = JPolarChartData.sample();
        data.validateAndCalculate();

        assertEquals(12, data.getSlotCount());
        assertEquals(100d, data.getComputedRadiusMax(), 0.0001d);
        assertEquals(30d, data.slotAngle(), 0.0001d);
        assertEquals(0d, data.angleOf(0), 0.0001d);
        assertEquals(90d, data.angleOf(3), 0.0001d);
        assertEquals(2, data.getSeries().size());
        assertNotNull(data.getFooterText());
        assertNotNull(data.getSeries().get(0).getColor());
    }

    @Test
    public void testCustomModelAndAutoRadius() {
        JPolarChartData data = new JPolarChartData();
        data.setTitleText("季度指标");
        data.setSubtitleText("极坐标折线 · 四季度");
        data.setValueUnit("%");
        data.setAngleLabels(new ArrayList<>(Arrays.asList("Q1", "Q2", "Q3", "Q4")));

        JPolarChartData.PolarSeries revenue = new JPolarChartData.PolarSeries("营收", 40d, 62d, 87d, 55d);
        JPolarChartData.PolarSeries cost = new JPolarChartData.PolarSeries("成本", 30d, 35d, 41d, 38d);
        cost.setColor(new Color(0x3E8E7E));
        cost.setFilled(true);
        data.setSeries(new ArrayList<>(Arrays.asList(revenue, cost)));
        data.validateAndCalculate();

        assertEquals(4, data.getSlotCount());
        assertEquals(100d, data.getComputedRadiusMax(), 0.0001d);
        assertEquals(0.87d, data.radiusRatio(87d), 0.0001d);
        assertEquals(25d, data.tickValue(1), 0.0001d);
        assertEquals(0d, data.radiusRatio(0d), 0.0001d);
        assertTrue(data.getFooterText().contains("4 个角度刻度"));
    }

    @Test
    public void testSeriesLengthIsChecked() {
        JPolarChartData data = new JPolarChartData();
        data.setAngleLabels(new ArrayList<>(Arrays.asList("A", "B")));
        data.setSeries(new ArrayList<>(Collections.singletonList(
                new JPolarChartData.PolarSeries("x", 1d))));
        try {
            data.validateAndCalculate();
            fail("a series with fewer values than slots must be rejected");
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("2 values"));
        }
    }

    @Test
    public void testEmptySeriesIsRejected() {
        try {
            new JPolarChartData().validateAndCalculate();
            fail("an empty series list must be rejected");
        } catch (RuntimeException expected) {
            assertTrue(expected.getMessage().contains("series"));
        }
    }

    @Test
    public void testRenderCustomModel() throws Exception {
        JPolarChartData data = customModel();
        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.Polar, option);
        assertTrue(svg.startsWith("<?xml"));
        assertTrue(svg.contains("季度指标"));
        assertTrue(svg.contains("营收"));

        File target = new File(OUTPUT_DIR + "polar_custom.svg");
        JChartRendererFactory.renderChart(JChartType.Polar, option, target.getPath());
        assertTrue(target.exists());
        String written = new String(Files.readAllBytes(target.toPath()), StandardCharsets.UTF_8);
        assertTrue(written.contains("季度指标"));
        assertTrue(written.contains("encoding=\"UTF-8\""));
    }

    @Test
    public void testRenderByStringKey() {
        JOption option = new JOption();
        option.setData(customModel());
        String svg = JChartRendererFactory.renderChart("polar", option);
        assertTrue(svg.contains("季度指标"));
    }

    @Test
    public void testCanvasSizeComesFromModel() {
        JPolarChartData data = customModel();
        data.setWidth(640);
        data.setHeight(420);
        JOption option = new JOption();
        option.setData(data);
        String svg = JChartRendererFactory.renderChart(JChartType.Polar, option);
        assertTrue(svg.contains("width=\"640\""));
        assertTrue(svg.contains("height=\"420\""));
    }

    private static JPolarChartData customModel() {
        JPolarChartData data = new JPolarChartData();
        data.setTitleText("季度指标");
        data.setSubtitleText("极坐标折线 · 四季度");
        data.setValueUnit("%");
        data.setAngleLabels(new ArrayList<>(Arrays.asList("Q1", "Q2", "Q3", "Q4")));
        data.setSectors(new ArrayList<>(Collections.singletonList(
                new JPolarChartData.PolarSector(0d, 90d, new Color(0xFF6B6B), 0.12f))));

        JPolarChartData.PolarSeries revenue = new JPolarChartData.PolarSeries("营收", 40d, 62d, 87d, 55d);
        revenue.setFilled(true);
        revenue.setShowPoints(true);

        JPolarChartData.PolarSeries cost = new JPolarChartData.PolarSeries("成本", 30d, 35d, 41d, 38d);
        cost.setColor(new Color(0x3E8E7E));

        data.setSeries(new ArrayList<>(Arrays.asList(revenue, cost)));
        return data;
    }
}

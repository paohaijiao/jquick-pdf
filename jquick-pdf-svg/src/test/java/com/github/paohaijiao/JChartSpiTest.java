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
package com.github.paohaijiao;

import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.plugin.waterfall.JWaterfallRenderer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.k.JKChartsRenderer;
import com.github.paohaijiao.pie.JPieChartsRenderer;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.series.JCandlestick;
import org.junit.Assert;
import org.junit.Test;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @className JChartSpiTest
 * @date 2026/11/5
 * @description Coverage for the renderer SPI migration: legacy calls, prototype instances,
 * custom extensions and the unsupported type path.
 */
public class JChartSpiTest {

    @Test
    public void testAllChartTypesResolvedBySpi() {
        for (JChartType type : JChartType.values()) {
            if (type == JChartType.NONE) {
                continue;
            }
            JAbstractChartRenderer renderer = JChartRendererFactory.createRenderer(type, new JOption());
            Assert.assertNotNull("chart type not resolved: " + type, renderer);
        }
    }

    @Test
    public void testBuiltInRendererBinding() {
        Assert.assertTrue(JChartRendererFactory.createRenderer(JChartType.K, new JOption()) instanceof JKChartsRenderer);
        Assert.assertTrue(JChartRendererFactory.createRenderer(JChartType.PIE, new JOption()) instanceof JPieChartsRenderer);
    }

    @Test
    public void testPrototypeInstance() {
        JAbstractChartRenderer first = JChartRendererFactory.createRenderer(JChartType.BAR, new JOption());
        JAbstractChartRenderer second = JChartRendererFactory.createRenderer(JChartType.BAR, new JOption());
        Assert.assertNotSame("renderer must be created per call", first, second);
    }

    @Test
    public void testRenderKLineByLegacyFactory() {
        String svg = JChartRendererFactory.renderChart(JChartType.K, kLineOption());
        Assert.assertNotNull(svg);
        Assert.assertTrue(svg.contains("<svg"));
    }

    @Test
    public void testCustomRendererExtension() {
        JAbstractChartRenderer renderer = JChartRendererFactory.createRenderer(JChartType.Waterfall, new JOption());
        Assert.assertTrue(renderer instanceof JWaterfallRenderer);
        Assert.assertNotNull(JChartRendererFactory.renderChart(JChartType.Waterfall, new JOption()));
    }

    @Test
    public void testUnsupportedChartType() {
        try {
            JChartRendererFactory.createRenderer((JChartType) null, new JOption());
            Assert.fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Unsupported chart type: null", e.getMessage());
        }
    }

    @Test
    public void testNewRendererStillWorks() throws Exception {
        JKChartsRenderer renderer = new JKChartsRenderer();
        Assert.assertNotNull(renderer.renderToString(kLineOption()));
    }

    private JOption kLineOption() {
        JOption option = new JOption();
        option.title().text("股票K线图");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("01/01", "01/02", "01/03", "01/04", "01/05");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JCandlestick candlestick = new JCandlestick();
        candlestick.name("股价").data(
                new Object[]{105.2, 108.5, 104.8, 109.1},
                new Object[]{108.6, 107.8, 106.5, 109.5},
                new Object[]{107.9, 105.3, 104.2, 108.0},
                new Object[]{105.4, 106.1, 104.5, 107.2},
                new Object[]{106.2, 104.8, 103.0, 107.5}
        );
        option.series(candlestick);
        return option;
    }
}

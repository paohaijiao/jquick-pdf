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

import com.github.paohaijiao.bar.JBarChartsRenderer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.k.JKChartsRenderer;
import com.github.paohaijiao.plugin.sankey.JSankeyChartRenderer;
import org.junit.Assert;
import org.junit.Test;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @className JChartSpiPluginTest
 * @date 2026/11/5
 * @description Coverage for the string type keys: a plugin renderer that is not declared in
 * the chart type enum, its aliases, the unknown type path and the untouched enum API.
 */
public class JChartSpiPluginTest {

    private static final String SANKEY = "sankey";

    private JOption option() {
        JOption option = new JOption();
        option.title().text("资金流向桑基图").subtext("2026 年度预算分配");
        return option;
    }

    @Test
    public void testPluginResolvedByStringType() {
        Assert.assertTrue(JChartRendererFactory.createRenderer(SANKEY, option()) instanceof JSankeyChartRenderer);
        Assert.assertTrue(JChartRendererFactory.createRenderer("SANKEY", option()) instanceof JSankeyChartRenderer);
    }

    @Test
    public void testPluginRendersSvg() {
        String svg = JChartRendererFactory.renderChart(SANKEY, option());
        Assert.assertNotNull(svg);
        Assert.assertTrue(svg.contains("<svg"));
        Assert.assertTrue(svg.contains("研发投入"));
    }

    @Test
    public void testBuiltInResolvedByCodeNameAndAlias() {
        Assert.assertTrue(JChartRendererFactory.createRenderer("bar", option()) instanceof JBarChartsRenderer);
        Assert.assertTrue(JChartRendererFactory.createRenderer("BAR", option()) instanceof JBarChartsRenderer);
        Assert.assertTrue(JChartRendererFactory.createRenderer("candlestick", option()) instanceof JKChartsRenderer);
        Assert.assertTrue(JChartRendererFactory.createRenderer("K", option()) instanceof JKChartsRenderer);
    }

    @Test
    public void testUnknownStringType() {
        try {
            JChartRendererFactory.createRenderer("doughnut3d", option());
            Assert.fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Unsupported chart type: doughnut3d", e.getMessage());
        }
    }

    @Test
    public void testUnboundEnumType() {
        try {
            JChartRendererFactory.createRenderer(JChartType.NONE, option());
            Assert.fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Unsupported chart type: NONE", e.getMessage());
        }
    }

    @Test
    public void testEnumApiUnchanged() {
        Assert.assertTrue(JChartRendererFactory.createRenderer(JChartType.BAR, option()) instanceof JBarChartsRenderer);
        Assert.assertNotNull(JChartRendererFactory.renderChart(JChartType.PIE, option()));
    }
}

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
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * packageName com.github.paohaijiao.plugin.themeRiver
 *
 * @author Martin
 * @version 1.0.0
 * @classname JThemeRiverChartsRendererTest
 * @date 2026/11/8
 * @description Theme river rendering tests, all output goes to D:/test/themeriver.
 */
public class JThemeRiverChartsRendererTest {

    private static final String OUTPUT_DIR = "D:/test/themeriver";

    @Test
    public void rendererIsRegisteredBySpi() {
        Object renderer = JChartRendererFactory.createRenderer(JChartType.ThemeRiver, new JOption());
        assertEquals(JThemeRiverChartsRenderer.class, renderer.getClass());
        Object byName = JChartRendererFactory.createRenderer("theme-river", new JOption());
        assertEquals(JThemeRiverChartsRenderer.class, byName.getClass());
        Object byUpperCase = JChartRendererFactory.createRenderer("THEME-RIVER", new JOption());
        assertEquals(JThemeRiverChartsRenderer.class, byUpperCase.getClass());
    }

    @Test
    public void dataModelDerivesTotals() {
        JThemeRiverChartData data = sampleData();
        data.validateAndCalculate();
        assertEquals(7, data.getPointCount());
        assertEquals(5, data.getSeries().size());
        assertEquals(2000d, data.getMaxTotal(), 0.001d);
        assertEquals(3800d, data.getMaxSeriesTotal(), 0.001d);
        assertTrue(data.getFooterText().contains("5 个系列"));
        assertTrue(data.getFooterText().contains("7 个时间点"));
        assertTrue(data.getSeries().get(0).getColor() != null);
        assertTrue(data.getSeries().get(0).peakIndex() == 6);
    }

    @Test
    public void renderThemeRiver() throws IOException {
        JOption option = new JOption();
        option.setData(sampleData());
        String svg = JChartRendererFactory.renderChart(JChartType.ThemeRiver, option);
        assertTrue(svg.contains("<svg"));
        assertTrue(svg.contains("流量来源主题河流图"));
        assertTrue(svg.contains("自然搜索"));
        assertTrue(svg.contains("直接访问"));
        assertTrue(svg.contains("共 5 个系列"));
        assertTrue(svg.contains("07-14"));
        save("theme_river.svg", svg);
    }

    @Test
    public void renderThemeRiverByStringType() throws IOException {
        JOption option = new JOption();
        option.setData(sampleData());
        String path = OUTPUT_DIR + "/theme_river_by_name.svg";
        JChartRendererFactory.renderChart("theme-river", option, path);
        File file = new File(path);
        assertTrue(file.exists());
        assertTrue(file.length() > 1024L);
    }

    @Test
    public void renderSingleSeriesAndSinglePoint() throws IOException {
        JThemeRiverChartData single = new JThemeRiverChartData();
        single.timeLabels("2026-11");
        single.addSeries("新增客户", 1280d);
        single.setTitleText("单点主题河流图");
        JOption option = new JOption();
        option.setData(single);
        String svg = JChartRendererFactory.renderChart(JChartType.ThemeRiver, option);
        assertTrue(svg.contains("<svg"));
        save("theme_river_single_point.svg", svg);

        JThemeRiverChartData oneSeries = new JThemeRiverChartData();
        oneSeries.timeLabels("周一", "周二", "周三", "周四", "周五");
        oneSeries.addSeries("工单量", 120d, 160d, 140d, 210d, 190d);
        oneSeries.setTitleText("单系列主题河流图");
        JOption second = new JOption();
        second.setData(oneSeries);
        String secondSvg = JChartRendererFactory.renderChart(JChartType.ThemeRiver, second);
        assertTrue(secondSvg.contains("工单量"));
        save("theme_river_single_series.svg", secondSvg);
    }

    @Test
    public void renderWithoutDataShowsHint() {
        JOption option = new JOption();
        String svg = JChartRendererFactory.renderChart(JChartType.ThemeRiver, option);
        assertTrue(svg.contains("未提供河流数据"));
    }

    private static JThemeRiverChartData sampleData() {
        JThemeRiverChartData data = new JThemeRiverChartData();
        data.setTitleText("流量来源主题河流图");
        data.setSubtitleText("数据由 JThemeRiverChartData 传入 · 每个时间点按总量居中堆叠");
        data.setValueUnit("次");
        data.timeLabels("07-08", "07-09", "07-10", "07-11", "07-12", "07-13", "07-14");
        data.addSeries("自然搜索", 420d, 510d, 470d, 530d, 590d, 620d, 660d);
        data.addSeries("付费推广", 260d, 300d, 280d, 310d, 340d, 330d, 360d);
        data.addSeries("社交媒体", new Color(0x3E8E7E), 180d, 220d, 260d, 300d, 320d, 360d, 400d);
        data.addSeries("邮件营销", 90d, 110d, 120d, 140d, 150d, 160d, 170d);
        data.addSeries("直接访问", 300d, 320d, 340d, 330d, 360d, 380d, 410d);
        return data;
    }

    private static void save(String name, String content) throws IOException {
        File dir = new File(OUTPUT_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("cannot create " + OUTPUT_DIR);
        }
        Files.write(Paths.get(OUTPUT_DIR, name), content.getBytes(StandardCharsets.UTF_8));
    }
}

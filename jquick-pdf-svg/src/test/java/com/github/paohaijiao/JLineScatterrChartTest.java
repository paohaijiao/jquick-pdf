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

import com.github.paohaijiao.combol.JLineScatterChartData;
import com.github.paohaijiao.combol.JLineScatterChartRenderer;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/19
 */
public class JLineScatterrChartTest {
    @Test
    public void testMutipleChar1() throws IOException {
        List<String> categories = Arrays.asList(
                "1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月"
        );

        List<Double> lineValues = Arrays.asList(
                100.0, 120.0, 140.0, 160.0, 70.0, 200.0,
                290.0, 240.0, 130.0, 330.0, 100.0, 320.0
        );

        List<Double> scatterValues = Arrays.asList(
                85.0,
                145.0,
                20.0,
                195.0,
                155.0,
                400.0,
                180.0,
                210.0,
                40.0,
                245.0,
                275.0,
                450.0
        );
        JLineScatterChartData data = new JLineScatterChartData();
        data.setTitleText("计划销售额 vs 实际完成额");
        data.setSubtitleText("2024年度销售趋势分析");
        data.setFooterText("数据来源：销售部月度报表");
        data.setCategories(categories);
        data.setLineValues(lineValues);
        data.setScatterValues(scatterValues);
        data.setLineSeriesName("计划销售额");
        data.setScatterSeriesName("实际完成额");
        data.setMaxValue(500);
        data.setGridCount(5);
        data.setShowDataLabels(true);
        JOption option = new JOption();
        option.setData(data);
        JLineScatterChartRenderer renderer = new JLineScatterChartRenderer();
        renderer.render(option, "d://test//lineRadar.svg");
    }
}

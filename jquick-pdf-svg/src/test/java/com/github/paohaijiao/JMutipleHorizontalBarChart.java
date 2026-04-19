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

import com.github.paohaijiao.combol.JHorizontalBarChartData;
import com.github.paohaijiao.combol.JHorizontalBarChartRenderer;
import com.github.paohaijiao.combol.JHorizontalMultiBarChartData;
import com.github.paohaijiao.combol.JHorizontalMultiBarChartRenderer;
import org.junit.Test;

import java.awt.*;
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
public class JMutipleHorizontalBarChart {
    @Test
    public void testMutipleChar1() throws IOException {
        JHorizontalMultiBarChartData chartData = new JHorizontalMultiBarChartData();
        chartData.setTitleText("2024年度各产品销售数据");
        chartData.setSubtitleText("单位：万元");
        chartData.setXAxisTitle("销售额（万元）");
//        chartData.setYAxisTitle("产品类别");
        chartData.setValueWithPercent(false);
        chartData.setShowDataLabels(true);
        chartData.setLegendAtTop(true);
        chartData.setGroupSpacingRatio(0.15);
        chartData.setBarSpacingRatio(0.2);
        chartData.addCategory("智能手机");
        chartData.addCategory("笔记本电脑");
        chartData.addCategory("平板电脑");
        chartData.addCategory("智能手表");
        chartData.addCategory("耳机音箱");
        List<Double> productAValues = Arrays.asList(125.5, 98.3, 65.8, 45.2, 78.6);
        List<Double> productBValues = Arrays.asList(88.2, 112.5, 72.1, 38.5, 55.3);
        List<Double> productCValues = Arrays.asList(45.6, 68.9, 52.4, 28.7, 42.1);
        chartData.addSeries("品牌 A", productAValues, new Color(52, 73, 94));    // 深灰蓝 #34495e
        chartData.addSeries("品牌 B", productBValues, new Color(41, 128, 185));   // 中蓝 #2980b9
        chartData.addSeries("品牌 C", productCValues, new Color(26, 188, 156));   // 薄荷绿 #1abc9c
        JOption option = new JOption();
        option.setData(chartData);
        JHorizontalMultiBarChartRenderer renderer = new JHorizontalMultiBarChartRenderer();
        renderer.render(option, "d://test//mhBarChart.svg");
    }
}

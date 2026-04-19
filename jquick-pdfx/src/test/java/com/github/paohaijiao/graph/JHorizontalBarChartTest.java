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
package com.github.paohaijiao.graph;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.combol.JHorizontalBarChartData;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JHorizontalBarChartTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.HorizontalBar);
        JHorizontalBarChartData chartData = new JHorizontalBarChartData();
        chartData.setTitleText("2024年度销售数据");
        chartData.setSubtitleText("各产品线销售占比");
        chartData.setXAxisTitle("销售额（万元）");
        chartData.setYAxisTitle("产品类别");
        chartData.setValueWithPercent(false);
        chartData.setShowDataLabels(true);
        chartData.addYAxisLabel("电子产品");
        chartData.addYAxisLabel("服装服饰");
        chartData.addYAxisLabel("家居用品");
        chartData.addYAxisLabel("美妆个护");
        chartData.addYAxisLabel("食品饮料");
        java.util.List<Double> productAValues = Arrays.asList(85.5, 62.3, 45.8, 71.2, 93.6);
        java.util.List<Double> productBValues = Arrays.asList(45.2, 78.9, 52.1, 38.5, 67.4);
        chartData.addBarData(new JHorizontalBarChartData.BarData("产品A", productAValues, JHorizontalBarChartData.COLOR_A));
        chartData.addBarData(new JHorizontalBarChartData.BarData("产品B", productBValues, JHorizontalBarChartData.COLOR_B));
        JOption option = new JOption();
        option.setData(chartData);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
    }
}

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
import com.github.paohaijiao.combol.JMultiBarChartData;
import com.github.paohaijiao.combol.JMultiBarChartRenderer;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JMutipleBarTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.MultipleBar);
        JMultiBarChartData regionalData = new JMultiBarChartData();
        regionalData.setTitleText("2024年上半年各区域业绩对比（万元）");
        regionalData.setSubtitleText("华东、华南、华北、西部四区表现");
        regionalData.setXAxisLabels(Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月"));
        regionalData.setXAxisTitle("月份");
        regionalData.setYAxisTitle("业绩（万元）");
        // 华东区域
        JMultiBarChartData.BarData eastChina = new JMultiBarChartData.BarData();
        eastChina.setLegendText("华东");
        eastChina.setBarColor(JMultiBarChartRenderer.COLOR_A);
        eastChina.setValues(Arrays.asList(120.5, 135.2, 148.0, 162.5, 175.3, 190.8));
        // 华南区域
        JMultiBarChartData.BarData southChina = new JMultiBarChartData.BarData();
        southChina.setLegendText("华南");
        southChina.setBarColor(JMultiBarChartRenderer.COLOR_B);
        southChina.setValues(Arrays.asList(98.3, 112.6, 128.4, 145.2, 158.7, 172.5));
        // 华北区域
        JMultiBarChartData.BarData northChina = new JMultiBarChartData.BarData();
        northChina.setLegendText("华北");
        northChina.setBarColor(JMultiBarChartRenderer.COLOR_C);
        northChina.setValues(Arrays.asList(85.6, 92.3, 105.8, 118.4, 132.6, 148.2));
        // 西部区域
        JMultiBarChartData.BarData westChina = new JMultiBarChartData.BarData();
        westChina.setLegendText("西部");
        westChina.setBarColor(new Color(80, 180, 120));
        westChina.setValues(Arrays.asList(52.4, 61.8, 73.5, 85.2, 96.8, 108.5));
        regionalData.setBarDataList(Arrays.asList(eastChina, southChina, northChina, westChina));
        JOption option = new JOption();
        option.setData(regionalData);
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

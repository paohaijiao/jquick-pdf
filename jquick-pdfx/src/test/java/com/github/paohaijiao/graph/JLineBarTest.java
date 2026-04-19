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
import com.github.paohaijiao.combol.JComboLineBarChartData;
import com.github.paohaijiao.combol.JComboLineBarChartRenderer;
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
import java.util.List;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JLineBarTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();


        graphContainer.setType(JChartType.LineBar);
        JOption jOption = new JOption();
        List<Double> sales = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0);
        List<Double> profits = Arrays.asList(15.0, 16.5, 18.0, 19.2, 21.0, 22.5);
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月");
        JComboLineBarChartData jComboLineBarChartData = JComboLineBarChartData.builder()
                .width(1000)
                .height(600)
                .title("2024年上半年销售分析", "半年度数据报告")
                .barData(sales)
                .lineData(profits)
                .xAxisLabels(months)
                .barColor(new Color(46, 204, 113))      // 绿色条形
                .lineColor(new Color(155, 89, 182))     // 紫色折线
                .leftAxisTitle("销售额（万元）")
                .rightAxisTitle("利润率（%）")
                .barLegendText("月销售额")
                .lineLegendText("利润率")
                .footerText("数据来源：财务系统")
                .build();
        JComboLineBarChartRenderer customRenderer = new JComboLineBarChartRenderer();
        jOption.setData(jComboLineBarChartData);
        graphContainer.setOption(jOption);
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

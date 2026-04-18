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
package com.github.paohaijiao.ele;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.combol.JComboLineBarChartData;
import com.github.paohaijiao.combol.JComboLineBarChartRenderer;
import com.github.paohaijiao.combol.JMultiLineChartData;
import com.github.paohaijiao.combol.JMultiLineChartRenderer;
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
public class JMutipleLineTest {

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.MultipleLine);
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        List<Double> productA = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0, 205.0, 218.0, 230.0, 245.0, 258.0, 270.0);
        List<Double> productB = Arrays.asList(95.0, 108.0, 112.0, 130.0, 125.0, 145.0, 150.0, 168.0, 172.0, 185.0, 190.0, 200.0);
        List<Double> productC = Arrays.asList(80.0, 82.0, 85.0, 88.0, 90.0, 92.0, 95.0, 98.0, 100.0, 102.0, 105.0, 108.0);
        List<Double> productD = Arrays.asList(45.0, 58.0, 72.0, 89.0, 105.0, 128.0, 150.0, 175.0, 198.0, 225.0, 248.0, 275.0);
        JMultiLineChartData chartData = new JMultiLineChartData();
        chartData.setXAxisLabels(months);
        chartData.setWidth(900);
        chartData.setHeight(600);
        chartData.setTitleText("2024年度产品销售趋势分析");
        chartData.setSubtitleText("各产品线月度销售额对比（单位：万元）");
        chartData.setYAxisTitle("销售额（万元）");
        chartData.setFooterText("数据来源：销售系统报表 | 统计时间：2024年1月-12月");
        chartData.setGridCount(6);
        chartData.setShowDataLabels(false);
        chartData.setShowInnerPoint(true);
        chartData.setPointRadius(5);
        chartData.setInnerPointRadius(2);
        chartData.setChartAreaColor(new Color(248, 249, 250));
        chartData.setAxisColor(Color.BLACK);
        chartData.setGridColor(new Color(220, 220, 220));
        chartData.setTextColor(Color.BLACK);
        chartData.setFooterColor(new Color(128, 128, 128));
        chartData.setValueWithPercent(false);
        chartData.setAutoCalculateMax(true);
        chartData.setRotateXAxisLabels(false);
        JMultiLineChartData.LineData lineA = new JMultiLineChartData.LineData();
        lineA.setName("产品A");
        lineA.setLegendText("产品A - 高端系列");
        lineA.setValues(productA);
        lineA.setLineColor(new Color(66, 133, 244));
        lineA.setLineWidth(2.5f);

        JMultiLineChartData.LineData lineB = new JMultiLineChartData.LineData();
        lineB.setName("产品B");
        lineB.setLegendText("产品B - 中端系列");
        lineB.setValues(productB);
        lineB.setLineColor(new Color(234, 67, 53));
        lineB.setLineWidth(2.5f);

        JMultiLineChartData.LineData lineC = new JMultiLineChartData.LineData();
        lineC.setName("产品C");
        lineC.setLegendText("产品C - 入门系列");
        lineC.setValues(productC);
        lineC.setLineColor(new Color(52, 168, 83));
        lineC.setLineWidth(2.5f);

        JMultiLineChartData.LineData lineD = new JMultiLineChartData.LineData();
        lineD.setName("产品D");
        lineD.setLegendText("产品D - 创新系列");
        lineD.setValues(productD);
        lineD.setLineColor(new Color(251, 188, 5));
        lineD.setLineWidth(2.5f);
        chartData.setLineDataList(Arrays.asList(lineA, lineB, lineC, lineD));
        chartData.updateMaxValues();
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("2024年度产品销售趋势分析");
        title.setSubtext("各产品线月度销售额对比");
        option.setTitle(title);
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

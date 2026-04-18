package com.github.paohaijiao;/*
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

import com.github.paohaijiao.combol.JComboLineBarChartData;
import com.github.paohaijiao.combol.JComboLineBarChartRenderer;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class CombolTest {
    @Test
    public void testBarChar1() throws IOException {
        JOption jOption = new JOption();
        List<Double> sales = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0);
        List<Double> profits = Arrays.asList(15.0, 16.5, 18.0, 19.2, 21.0, 22.5);
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月");
        JComboLineBarChartData config = JComboLineBarChartData.builder()
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
        jOption.setData(config);
        customRenderer.render(jOption, "d://test/custom_chart.svg");
    }
    @Test
    public void testBarChar2() throws IOException {
        JOption jOption = new JOption();
        List<Double> salesData = Arrays.asList(45.0, 38.0, 52.0, 48.0, 62.0, 58.0, 72.0, 78.0, 65.0, 70.0, 82.0, 88.0);
        List<Double> profitData =Arrays.asList(12.0, 10.0, 15.0, 14.0, 18.0, 17.0, 22.0, 25.0, 20.0, 21.0, 26.0, 28.0);
        List<String> months =Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        JComboLineBarChartData data = JComboLineBarChartData.builder()
                .width(1000)                    // 宽度1000像素
                .height(600)                    // 高度600像素
                .title("2024年度销售数据分析", "全年12个月数据趋势")
                .barData(salesData)
                .lineData(profitData)
                .xAxisLabels(months)
                .leftAxisTitle("销售额（万元）")
                .rightAxisTitle("利润率（%）")
                .barLegendText("月销售额（万元）")
                .lineLegendText("利润率趋势")
                .footerText("数据来源：2024年度财务报告")
                .barColor(new Color(52, 152, 219))   // 蓝色
                .lineColor(new Color(231, 76, 60))   // 红色
                .showBarLabels(true)
                .showLineLabels(true)
                .autoCalculateMax(true)
                .build();
        jOption.setData(data);
        JComboLineBarChartRenderer customRenderer = new JComboLineBarChartRenderer();
        customRenderer.render(jOption, "d://test/custom12_chart.svg");
    }
    @Test
    public void beauty() throws IOException {
        List<Double> salesData = Arrays.asList(42.5, 45.8, 52.3, 48.6, 58.9, 63.2, 68.5, 72.8, 66.5, 75.2, 85.6, 95.8);
        List<Double> profitData = Arrays.asList(11.2, 12.5, 13.8, 14.2, 15.6, 16.8, 17.5, 18.2, 17.8, 19.5, 20.2, 21.5);
        List<String> months =Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        Color barColor = new Color(41, 128, 185);      // 专业蓝色
        Color lineColor = new Color(231, 76, 60);       // 活力红色
        Color chartBgColor = new Color(250, 251, 252);  // 浅灰背景
        Color gridColor = new Color(236, 240, 241);     // 浅灰色网格

        JComboLineBarChartData config = JComboLineBarChartData.builder()
                .width(1000)
                .height(600)
                .title("2024年度销售业绩分析", "销售额与利润率趋势对比")
                .barData(salesData)
                .lineData(profitData)
                .xAxisLabels(months)
                .leftAxisTitle("销售额（万元）")
                .rightAxisTitle("利润率（%）")
                .barLegendText("月销售额（万元）")
                .lineLegendText("利润率趋势（%）")
                .footerText("数据来源：财务系统 · 单位：万元 / 百分比")
                .barColor(barColor)
                .lineColor(lineColor)
                .chartAreaColor(chartBgColor)
                .showBarLabels(true)
                .showLineLabels(true)
                .autoCalculateMax(true)
                .build();

        JComboLineBarChartRenderer renderer = new JComboLineBarChartRenderer();
        JOption option = new JOption();
        option.setData(config);
        renderer.render(option, "d://test/beautiful_12_months_chart.svg");
        System.out.println("✓ 12个月美观图表已生成: beautiful_12_months_chart.svg");
    }
}

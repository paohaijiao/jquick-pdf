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

import com.github.paohaijiao.combol.JMultiLineChartData;
import com.github.paohaijiao.combol.JMultiLineChartRenderer;
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
 * @since 2026/4/18
 */
public class MutipleLineChartTest {
    @Test
    public void testMutipleChar1() throws IOException {
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
        JMultiLineChartRenderer renderer = new JMultiLineChartRenderer();
        renderer.render(option, "d://test//multiple-line.svg");
    }
    @Test
    public void testMutipleChar5() throws IOException {
        List<String> xLabels = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        List<Double> line1 = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0, 205.0, 218.0, 230.0, 245.0, 258.0, 270.0);
        List<Double> line2 = Arrays.asList(95.0, 108.0, 112.0, 130.0, 125.0, 145.0, 150.0, 168.0, 172.0, 185.0, 190.0, 200.0);
        List<Double> line3 = Arrays.asList(80.0, 82.0, 85.0, 88.0, 90.0, 92.0, 95.0, 98.0, 100.0, 102.0, 105.0, 108.0);
        List<Double> line4 = Arrays.asList(45.0, 58.0, 72.0, 89.0, 105.0, 128.0, 150.0, 175.0, 198.0, 225.0, 248.0, 275.0);
        List<Double> line5 = Arrays.asList(60.0, 65.0, 70.0, 78.0, 85.0, 95.0, 108.0, 120.0, 135.0, 150.0, 168.0, 185.0);
        JMultiLineChartData chartData = new JMultiLineChartData();
        chartData.setXAxisLabels(xLabels);
        chartData.setWidth(900);
        chartData.setHeight(600);
        chartData.setTitleText("五条产品线销售趋势");
        chartData.setSubtitleText("2024年度月度销售额对比");
        chartData.setYAxisTitle("销售额（万元）");
        chartData.setFooterText("数据来源：销售系统");
        chartData.setGridCount(6);
        chartData.setShowDataLabels(false);
        chartData.setShowInnerPoint(true);
        chartData.setPointRadius(5);
        chartData.setAutoCalculateMax(true);
        Color[] colors = {
                new Color(66, 133, 244),  // 蓝色
                new Color(234, 67, 53),   // 红色
                new Color(52, 168, 83),   // 绿色
                new Color(251, 188, 5),   // 黄色
                new Color(104, 58, 183)   // 紫色
        };
        chartData.setLineDataList(Arrays.asList(
                createLine("产品A", line1, colors[0]),
                createLine("产品B", line2, colors[1]),
                createLine("产品C", line3, colors[2]),
                createLine("产品D", line4, colors[3]),
                createLine("产品E", line5, colors[4])
        ));

        chartData.updateMaxValues();
        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText("五条产品线销售趋势");
        title.setSubtext("2024年度月度销售额对比");
        option.setTitle(title);
        option.setData(chartData);
        JMultiLineChartRenderer renderer = new JMultiLineChartRenderer();
        renderer.render(option, "d://test//FIVE-line.svg");
    }
    private JMultiLineChartData.LineData createLine(String name, List<Double> values, Color color) {
        JMultiLineChartData.LineData line = new JMultiLineChartData.LineData();
        line.setName(name);
        line.setLegendText(name);
        line.setValues(values);
        line.setLineColor(color);
        line.setLineWidth(2.5f);
        return line;
    }
}

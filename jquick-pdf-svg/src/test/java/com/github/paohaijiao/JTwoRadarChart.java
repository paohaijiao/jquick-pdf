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

import com.github.paohaijiao.combol.JDoubleRadarChartData;
import com.github.paohaijiao.combol.JDoubleRadarChartRenderer;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/19
 */
public class JTwoRadarChart {
    @Test
    public void testMutipleChar1() throws IOException {
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setWidth(1000);
        chartData.setHeight(600);
        chartData.setTitleText("多维度数据对比雷达图");
        chartData.setSubtitleText("左右两组数据对比分析");
        chartData.setLeftTitle("实验组数据");
        chartData.setRightTitle("对照组数据");
        List<String> dimensions = Arrays.asList("维度A", "维度B", "维度C", "维度D", "维度E");
        chartData.setDimensions(dimensions);
        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> leftSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series1 = new JDoubleRadarChartData.Series();
        series1.setName("节点1");
        List<Double> values1 = Arrays.asList(85.0, 70.0, 65.0, 80.0, 75.0);
        series1.setValues(values1);
        series1.setColor(new Color(84, 112, 198));  // 蓝色
        leftSeriesList.add(series1);
        JDoubleRadarChartData.Series series2 = new JDoubleRadarChartData.Series();
        series2.setName("节点2");
        List<Double> values2 = Arrays.asList(70.0, 85.0, 80.0, 65.0, 70.0);
        series2.setValues(values2);
        series2.setColor(new Color(250, 200, 88));  // 黄色
        leftSeriesList.add(series2);

        leftRadar.setSeriesList(leftSeriesList);
        chartData.setLeftRadar(leftRadar);
        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> rightSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series3 = new JDoubleRadarChartData.Series();
        series3.setName("节点3");
        List<Double> values3 = Arrays.asList(90.0, 85.0, 88.0, 92.0, 87.0);
        series3.setValues(values3);
        series3.setColor(new Color(238, 102, 102));  // 红色
        rightSeriesList.add(series3);
        JDoubleRadarChartData.Series series4 = new JDoubleRadarChartData.Series();
        series4.setName("节点4");
        List<Double> values4 = Arrays.asList(75.0, 80.0, 72.0, 78.0, 82.0);
        series4.setValues(values4);
        series4.setColor(new Color(80, 180, 150));  // 绿色
        rightSeriesList.add(series4);

        rightRadar.setSeriesList(rightSeriesList);
        chartData.setRightRadar(rightRadar);
        chartData.setGridLevels(4);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(false);
        chartData.setShowLegendSide(true);
        chartData.setFooterText("数据来源：示例数据");
        JOption option = new JOption();
        option.setData(chartData);

        JDoubleRadarChartRenderer renderer = new JDoubleRadarChartRenderer();
        renderer.render(option, "d://test//mutipleRadar.svg");
    }
}

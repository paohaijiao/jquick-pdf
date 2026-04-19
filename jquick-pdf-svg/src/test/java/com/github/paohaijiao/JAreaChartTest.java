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

import com.github.paohaijiao.combol.*;
import com.github.paohaijiao.combol.area.JAreaChartData;
import com.github.paohaijiao.combol.area.JSeriesData;
import com.github.paohaijiao.combol.area.JTheme;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/18
 */
public class JAreaChartTest {

    @Test
    public void testMutipleChar1() throws IOException {
        List<Double> values =Arrays.asList(85.0, 120.0, 150.0, 210.0, 280.0, 350.0, 420.0, 400.0, 380.0, 450.0, 480.0, 520.0);
        List<String> labels = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        JAreaChartData config = new JAreaChartData();
        config.setWidth(800);
        config.setHeight(500);
        config.setTitle("2024年度销售趋势");
        config.setSubtitle("数据来源：销售系统");
        config.setXAxisTitle("月份");
        config.setYAxisTitle("销售额（万元）");
        config.setLegendText("销售额");
        config.setShowDataLabels(true);
        config.setSeriesList(Arrays.asList(new JSeriesData("销售额", values)));
        config.setXAxisLabels(labels);
        config.setTheme(JTheme.DEFAULT);      // 默认主题
        JOption option = new JOption();
        option.setData(config);
        JAreaChartRenderer renderer = new JAreaChartRenderer();
        renderer.render(option, "d://test//area.svg");
    }

    @Test
    public void testArea() throws IOException {
        List<Double> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        java.time.LocalDate startDate = java.time.LocalDate.of(2020, 1, 1);
        int dataPoints = 1000;  // 1000个数据点
        Random random = new Random(42);  // 固定种子，保证可重复性
        double baseValue = 100.0;
        for (int i = 0; i < dataPoints; i++) {
            double seasonal = 30 * Math.sin(2 * Math.PI * i / 365.0);  // 年度周期
            double trend = 0.05 * i;  // 缓慢增长趋势
            double noise = random.nextDouble() * 20 - 10;  // 随机噪声
            double value = baseValue + seasonal + trend + noise;
            value = Math.max(0, value);  // 确保非负
            values.add(value);
            // 生成标签（每隔30天显示一个标签，避免X轴拥挤）
            if (i % 30 == 0 || i == dataPoints - 1) {
                java.time.LocalDate currentDate = startDate.plusDays(i);
                labels.add(currentDate.toString());
            } else {
                labels.add("");  // 空标签，不显示
            }
        }

        // 构建配置
        JAreaChartData config = new JAreaChartData();
        config.setWidth(1200);   // 更宽的画布适应大数据
        config.setHeight(600);
        config.setTitle("大数据量面积图性能测试");
        config.setSubtitle(String.format("数据点数: %d 个", dataPoints));
        config.setXAxisTitle("日期");
        config.setYAxisTitle("数值");
        config.setLegendText("数据系列");
        config.setShowDataLabels(false);  // 大数据量时关闭数据标签，避免拥挤
        config.setGridCount(10);          // 增加网格线数量
        config.setSeriesList(Arrays.asList(new JSeriesData("趋势数据", values)));
        config.setXAxisLabels(labels);
        config.setTheme(JTheme.BLUE);

        JOption option = new JOption();
        option.setData(config);
        JAreaChartRenderer renderer = new JAreaChartRenderer();
        renderer.render(option, "d://test//area1.svg");
    }


}

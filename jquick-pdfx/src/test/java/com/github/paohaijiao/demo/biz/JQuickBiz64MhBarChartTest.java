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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.combol.JHorizontalMultiBarChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 业务场景 Demo 64：各产品线年度预算与实际支出对比（财务管理）
 *
 * <p>业务场景：财务管理部门对各产品线的预算、实际支出、偏差率进行横向分组对比，
 * 便于快速识别预算执行偏差。</p>
 * <p>实际图表类型：{@link JChartType#MutipleHorizontalBar} 多重横向柱状图。</p>
 *
 * <p>模板：{@code report/biz/64_mhBarChart.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\64_mhBarChart.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz64MhBarChartTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz64MhBarChart() throws IOException {
        JHorizontalMultiBarChartData chartData = new JHorizontalMultiBarChartData();
        chartData.setTitleText("各产品线年度预算与实际支出对比");
        chartData.setSubtitleText("单位：万元 | 2024 年度");
        chartData.setXAxisTitle("金额（万元）");
        chartData.setValueWithPercent(false);
        chartData.setShowDataLabels(true);
        chartData.setLegendAtTop(true);
        chartData.setGroupSpacingRatio(0.15);
        chartData.setBarSpacingRatio(0.2);
        chartData.addCategory("产品线 A");
        chartData.addCategory("产品线 B");
        chartData.addCategory("产品线 C");
        chartData.addCategory("产品线 D");
        chartData.addCategory("产品线 E");

        // 预算
        chartData.addSeries("预算",
                Arrays.asList(1200.0, 980.0, 750.0, 520.0, 300.0),
                new Color(49, 27, 146));
        // 实际
        chartData.addSeries("实际",
                Arrays.asList(1180.0, 960.0, 885.0, 510.0, 290.0),
                new Color(103, 80, 174));
        // 偏差率（放大 10 倍以便与金额同量级展示，仅作视觉对比）
        chartData.addSeries("偏差率(×10)",
                Arrays.asList(-16.0, -20.0, 135.0, -10.0, -10.0),
                new Color(149, 117, 205));

        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MutipleHorizontalBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/64_mhBarChart.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/64_mhBarChart.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

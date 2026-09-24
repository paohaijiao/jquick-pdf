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
import com.github.paohaijiao.combol.JMultiBarChartData;
import com.github.paohaijiao.combol.JMultiBarChartRenderer;
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
 * 业务场景 Demo 26：多区域多指标对比分组柱状图（企业经营分析）
 *
 * <p>业务场景：企业运营部对比华东、华南、华北、西部四个区域 1-6 月的业绩完成情况，用于半年度经营分析会。</p>
 * <p>实际图表类型：{@link JChartType#MultipleBar} 分组柱状图，横轴为月份、每个月份并列四根区域柱。</p>
 *
 * <p>模板：{@code report/biz/26_multi_metric_bar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\26_multi_metric_bar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>} 只声明宽度、不声明高度，
 * 宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz26MultiMetricBarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz26MultiMetricBar() throws IOException {
        JMultiBarChartData regionalData = new JMultiBarChartData();
        regionalData.setTitleText("2024年上半年各区域业绩对比");
        regionalData.setSubtitleText("华东、华南、华北、西部四区月度表现（单位：万元）");
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
        westChina.setBarColor(new Color(62, 107, 157));
        westChina.setValues(Arrays.asList(52.4, 61.8, 73.5, 85.2, 96.8, 108.5));
        regionalData.setBarDataList(Arrays.asList(eastChina, southChina, northChina, westChina));
        JOption option = new JOption();
        option.setData(regionalData);

        String svg = JChartRendererFactory.renderChart(JChartType.MultipleBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/26_multi_metric_bar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/26_multi_metric_bar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

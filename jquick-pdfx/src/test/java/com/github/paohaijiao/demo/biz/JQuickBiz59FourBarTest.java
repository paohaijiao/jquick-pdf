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
 * 业务场景 Demo 59：分区域季度销售四系列对比（销售运营）
 *
 * <p>业务场景：销售运营部门对比华东、华北、华南、西部四大区域在 2024 年四季度
 * 各月的销售额，采用四系列分组柱状图并列展示。</p>
 * <p>实际图表类型：{@link JChartType#MultipleBar} 多系列分组柱状图。</p>
 *
 * <p>模板：{@code report/biz/59_fourBar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\59_fourBar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz59FourBarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz59FourBar() throws IOException {
        JMultiBarChartData chartData = new JMultiBarChartData();
        chartData.setTitleText("分区域季度销售四系列对比");
        chartData.setSubtitleText("华东 / 华北 / 华南 / 西部  2024 年 10-12 月销售额（万元）");
        chartData.setXAxisLabels(Arrays.asList("10月", "11月", "12月"));
        chartData.setXAxisTitle("月份");
        chartData.setYAxisTitle("销售额（万元）");
        chartData.setFooterText("数据来源：销售运营系统 | 统计口径：自然月");

        JMultiBarChartData.BarData east = new JMultiBarChartData.BarData();
        east.setLegendText("华东");
        east.setBarColor(new Color(230, 81, 0));
        east.setValues(Arrays.asList(780.0, 830.0, 910.0));

        JMultiBarChartData.BarData north = new JMultiBarChartData.BarData();
        north.setLegendText("华北");
        north.setBarColor(new Color(251, 140, 0));
        north.setValues(Arrays.asList(560.0, 590.0, 570.0));

        JMultiBarChartData.BarData south = new JMultiBarChartData.BarData();
        south.setLegendText("华南");
        south.setBarColor(new Color(255, 183, 77));
        south.setValues(Arrays.asList(620.0, 660.0, 720.0));

        JMultiBarChartData.BarData west = new JMultiBarChartData.BarData();
        west.setLegendText("西部");
        west.setBarColor(new Color(255, 213, 79));
        west.setValues(Arrays.asList(310.0, 380.0, 460.0));

        chartData.setBarDataList(Arrays.asList(east, north, south, west));

        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MultipleBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/59_fourBar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/59_fourBar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

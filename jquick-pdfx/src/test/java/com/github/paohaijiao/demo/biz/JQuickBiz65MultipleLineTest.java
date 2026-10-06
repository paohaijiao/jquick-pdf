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
import com.github.paohaijiao.combol.JMultiLineChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 65：三大业务板块月度营收走势（经营分析）
 *
 * <p>业务场景：经营分析部门以多重折线图展示软件、硬件、服务三条业务线
 * 2024 年逐月营收变化趋势。</p>
 * <p>实际图表类型：{@link JChartType#MultipleLine} 多重折线图。</p>
 *
 * <p>模板：{@code report/biz/65_multipleLine.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\65_multipleLine.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz65MultipleLineTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz65MultipleLine() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月");

        JMultiLineChartData chartData = new JMultiLineChartData();
        chartData.setTitleText("三大业务板块月度营收走势");
        chartData.setSubtitleText("软件 / 硬件 / 服务  2024 年逐月营收（万元）");
        chartData.setXAxisLabels(months);
        chartData.setYAxisTitle("营收（万元）");
        chartData.setFooterText("数据来源：经营分析系统");
        chartData.setGridCount(6);
        chartData.setShowDataLabels(false);

        JMultiLineChartData.LineData software = new JMultiLineChartData.LineData();
        software.setName("软件");
        software.setLegendText("软件业务");
        software.setValues(Arrays.asList(320.0, 335.0, 350.0, 368.0, 385.0, 402.0, 420.0, 438.0, 455.0, 472.0, 490.0, 510.0));
        software.setLineColor(new Color(0, 96, 100));
        software.setLineWidth(2.5f);

        JMultiLineChartData.LineData hardware = new JMultiLineChartData.LineData();
        hardware.setName("硬件");
        hardware.setLegendText("硬件业务");
        hardware.setValues(Arrays.asList(420.0, 410.0, 435.0, 390.0, 405.0, 425.0, 440.0, 430.0, 450.0, 460.0, 475.0, 485.0));
        hardware.setLineColor(new Color(38, 166, 154));
        hardware.setLineWidth(2.5f);

        JMultiLineChartData.LineData service = new JMultiLineChartData.LineData();
        service.setName("服务");
        service.setLegendText("服务业务");
        service.setValues(Arrays.asList(180.0, 195.0, 210.0, 228.0, 245.0, 262.0, 280.0, 300.0, 322.0, 345.0, 368.0, 392.0));
        service.setLineColor(new Color(77, 182, 172));
        service.setLineWidth(2.5f);

        chartData.setLineDataList(Arrays.asList(software, hardware, service));
        chartData.updateMaxValues();

        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.MultipleLine, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/65_multipleLine.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/65_multipleLine.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

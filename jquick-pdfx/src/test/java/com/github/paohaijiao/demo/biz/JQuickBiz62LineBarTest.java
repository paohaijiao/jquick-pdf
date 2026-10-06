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
import com.github.paohaijiao.combol.JComboLineBarChartData;
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
 * 业务场景 Demo 62：项目交付量与按时交付率趋势（项目管理）
 *
 * <p>业务场景：项目管理办公室（PMO）按月统计交付项目数与按时交付率，
 * 柱表示月度交付项目数（左轴），线表示按时交付率（右轴），用于过程管控。</p>
 * <p>实际图表类型：{@link JChartType#LineBar} 折线柱状混合图（双 Y 轴）。</p>
 *
 * <p>模板：{@code report/biz/62_linebar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\62_linebar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz62LineBarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz62LineBar() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月");
        List<Double> bars = Arrays.asList(18.0, 15.0, 22.0, 20.0, 26.0, 24.0, 30.0, 28.0, 33.0, 36.0, 35.0, 42.0);
        List<Double> lines = Arrays.asList(82.0, 80.0, 84.0, 83.0, 86.0, 85.0, 88.0, 87.0, 90.0, 91.0, 92.0, 95.0);

        JComboLineBarChartData chartData = JComboLineBarChartData.builder()
                .title("项目交付量与按时交付率趋势", "月度交付项目数与按时交付率")
                .barData(bars)
                .lineData(lines)
                .xAxisLabels(months)
                .barColor(new Color(13, 71, 161))
                .lineColor(new Color(239, 108, 0))
                .leftAxisTitle("交付项目数（个）")
                .rightAxisTitle("按时交付率（%）")
                .barLegendText("月度交付项目数")
                .lineLegendText("按时交付率")
                .footerText("数据来源：PMO 项目管理系统")
                .build();

        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.LineBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/62_linebar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/62_linebar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

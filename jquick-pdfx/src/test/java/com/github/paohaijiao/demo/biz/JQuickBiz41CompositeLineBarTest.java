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
 * 业务场景 Demo 41：多指标复合折线柱状混合图（工业经济运行监测）
 *
 * <p>业务场景：工业经济运行监测平台按月复合展示工业产值与单位产值综合能耗，柱表示月度产值、
 * 线表示能耗强度，用于研判「增产」与「降耗」是否脱钩，主色采用深紫 #6A1B9A。</p>
 * <p>实际图表类型：{@link JChartType#LineBar} 折线柱状混合图（双 Y 轴：左轴产值亿元、右轴能耗强度）。</p>
 *
 * <p>模板：{@code report/biz/41_composite_linebar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\41_composite_linebar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz41CompositeLineBarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz41CompositeLineBar() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        // 柱：月度工业产值（亿元）
        List<Double> bars = Arrays.asList(42.6, 38.9, 45.2, 47.8, 49.5, 52.1, 54.6, 53.2, 56.4, 58.9, 61.3, 64.8);
        // 线：单位产值综合能耗（千克标煤/万元），逐月下降体现能效提升
        List<Double> lines = Arrays.asList(392.0, 384.0, 376.0, 365.0, 356.0, 347.0, 338.0, 326.0, 315.0, 302.0, 289.0, 275.0);
        JComboLineBarChartData chartData = JComboLineBarChartData.builder()
                .title("2024年工业产值与能耗复合分析", "月度工业产值与单位产值综合能耗")
                .barData(bars)
                .lineData(lines)
                .xAxisLabels(months)
                .barColor(new Color(106, 27, 154))
                .lineColor(new Color(0, 105, 92))
                .leftAxisTitle("工业产值（亿元）")
                .rightAxisTitle("单位产值能耗（千克标煤/万元）")
                .barLegendText("月度工业产值")
                .lineLegendText("单位产值综合能耗")
                .footerText("数据来源：工业经济运行监测平台 | 统计口径：自然月")
                .build();
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.LineBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/41_composite_linebar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/41_composite_linebar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

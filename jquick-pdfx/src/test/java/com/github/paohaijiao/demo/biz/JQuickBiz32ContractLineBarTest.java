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
 * 业务场景 Demo 32：合同签订趋势（企业法务与合同管理）
 *
 * <p>业务场景：企业合同管理系统按月统计签订合同数与平均履约率，柱表示月度签订量、
 * 线表示履约质量，用于法务风险管控与经营例会汇报，主色采用墨绿 #1B5E20。</p>
 * <p>实际图表类型：{@link JChartType#LineBar} 折线柱状混合图（双 Y 轴：左轴合同份数、右轴履约率百分比）。</p>
 *
 * <p>模板：{@code report/biz/32_contract_linebar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\32_contract_linebar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz32ContractLineBarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz32ContractLineBar() throws IOException {
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        // 柱：月度签订合同数（份）
        List<Double> bars = Arrays.asList(186.0, 152.0, 205.0, 228.0, 216.0, 241.0, 258.0, 236.0, 265.0, 289.0, 302.0, 328.0);
        // 线：平均履约率（%）
        List<Double> lines = Arrays.asList(82.6, 79.8, 83.1, 84.7, 85.9, 87.4, 88.6, 87.8, 90.2, 91.5, 93.3, 95.2);
        JComboLineBarChartData chartData = JComboLineBarChartData.builder()
                .title("2024年合同签订趋势分析", "月度签订合同数与平均履约率")
                .barData(bars)
                .lineData(lines)
                .xAxisLabels(months)
                .barColor(new Color(27, 94, 32))
                .lineColor(new Color(191, 54, 12))
                .leftAxisTitle("签订合同数（份）")
                .rightAxisTitle("平均履约率（%）")
                .barLegendText("月度签订合同数")
                .lineLegendText("平均履约率")
                .footerText("数据来源：合同管理系统 | 统计口径：自然月")
                .build();
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.LineBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/32_contract_linebar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/32_contract_linebar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

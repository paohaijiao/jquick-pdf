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
package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.funnel.*;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * 统一信用报告 —— 风格 08：漏斗图（深橙）
 *
 * <p>以 {@code report/credit_report_08_funnel.txt} 为模板，把漏斗图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\08_funnel.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport08FunnelTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report08Funnel() throws IOException {
        JFunnelOption funnelOption = JFunnelOption.createDefaultFunnel();
        funnelOption = funnelOption
                .title(new Title().text("客户转化漏斗").subtext("2023年度数据"))
                .funnel(new Funnel()
                        .width(600)
                        .topY(80)
                        .bottomY(200)
                        .gap(2)
                        .borderColor(Color.GRAY)
                ).series(Collections.singletonList(
                        new Series()
                                .name("转化")
                                .type("funnel")
                                .data(Arrays.asList(
                                        new DataItem("线索获取", 12000),
                                        new DataItem("商务洽谈", 6000),
                                        new DataItem("方案确认", 2800),
                                        new DataItem("合同签订", 900)
                                ))))
                .colors(
                        new Color(191, 54, 12),
                        new Color(216, 67, 21),
                        new Color(255, 87, 34),
                        new Color(255, 138, 101)
                );
        JOption option = new JOption();
        option.setFunnelOption(funnelOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Funnel, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/08_funnel.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_08_funnel.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

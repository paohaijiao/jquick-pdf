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
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 风格 01：条形图（经典蓝）
 *
 * <p>以 {@code report/credit_report_01_bar.txt} 为模板，把条形图渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\01_bar.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport01BarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report01Bar() throws IOException {
        JOption option = new JOption();
        option.title().text("信用评级指标对比").subtext("2020-2023年度（单位：万元）");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("2020年", "2021年", "2022年", "2023年");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JBar revenue = new JBar();
        revenue.name("营业收入").data(8560, 12340, 18920, 25680);
        JBar profit = new JBar();
        profit.name("净利润").data(1240, 2150, 3890, 5420);
        option.series(revenue, profit);

        String svg = JChartRendererFactory.renderChart(JChartType.BAR, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/01_bar.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_01_bar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

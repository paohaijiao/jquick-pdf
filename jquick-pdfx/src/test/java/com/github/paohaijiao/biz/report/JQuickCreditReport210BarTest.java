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
package com.github.paohaijiao.biz.report;

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
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 编号 210：进度条 + 条形图
 *
 * <p>进度条由 {@code @JQuickSpi} 注册的 {@code JProgressBarChartRenderer} 渲染，
 * 通过 {@link JChartRendererFactory#renderChart(JChartType, JOption)} 取 SVG，
 * 与条形图一起绑定到 {@code report/credit_report_210_progress_bar.xml} 的
 * {@code ${progress}}、{@code ${svg}} 占位符，输出到
 * {@code D:\test\creditreport\210_credit_report_progress.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport210BarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report210Bar() throws IOException {
        String barSvg = JChartRendererFactory.renderChart(JChartType.BAR, barOption());
        String progressSvg = JChartRendererFactory.renderChart(JChartType.Progress, progressOption());
        Assert.assertTrue(barSvg.contains("<svg"));
        Assert.assertTrue(progressSvg.contains("<svg"));

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File output = new File(dir, "210_credit_report_progress.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", barSvg);
        factory.bind("progress", progressSvg);
        byte[] bytes = factory.executeResource("report/credit_report_210_progress_bar.xml");
        try (FileOutputStream fileOutputStream = new FileOutputStream(output)) {
            fileOutputStream.write(bytes);
        }
        Assert.assertTrue(output.exists());
        Assert.assertTrue(output.length() > 0);
    }

    private JOption barOption() {
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
        return option;
    }

    private JOption progressOption() {
        JOption option = new JOption();
        option.title().text("信用评分维度完成度").subtext("满分 100 分 | 数据截至 2026 年 10 月");
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("财务状况", "经营能力", "偿债能力", "发展潜力", "合规记录");
        option.xAxis(xAxis);
        JBar score = new JBar();
        score.name("信用评分").data(90, 95, 85, 92, 88);
        option.series(score);
        return option;
    }
}

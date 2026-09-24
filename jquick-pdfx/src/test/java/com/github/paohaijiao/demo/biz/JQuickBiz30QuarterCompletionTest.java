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
import com.github.paohaijiao.combol.area.JAreaChartData;
import com.github.paohaijiao.combol.area.JSeriesData;
import com.github.paohaijiao.combol.area.JTheme;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 业务场景 Demo 30：季度指标完成度对比（企业运营）
 *
 * <p>业务场景：企业运营管理部按季度对比「计划目标」与「实际完成」的营业收入，
 * 用于观察季度指标完成度与经营节奏。</p>
 * <p>实际图表类型：{@link JChartType#AREA} 区域图（计划值与实际完成值的区域对比）。</p>
 *
 * <p>模板：{@code report/biz/30_quarter_completion.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\30_quarter_completion.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz30QuarterCompletionTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz30QuarterCompletion() throws IOException {
        JAreaChartData data = new JAreaChartData();
        data.setTitle("季度营业收入完成度");
        data.setSubtitle("计划目标 vs 实际完成（单位：万元）");
        data.setXAxisTitle("季度");
        data.setYAxisTitle("金额（万元）");
        data.setLegendText("实际完成");
        data.setShowDataLabels(true);
        // 两个系列：实际完成与计划目标，形成区域对比
        data.setSeriesList(Arrays.asList(
                new JSeriesData("实际完成", Arrays.asList(3200.0, 3480.0, 3910.0, 4260.0)),
                new JSeriesData("计划目标", Arrays.asList(3000.0, 3400.0, 3800.0, 4100.0))
        ));
        data.setXAxisLabels(Arrays.asList("Q1", "Q2", "Q3", "Q4"));
        data.setTheme(JTheme.DEFAULT);
        JOption option = new JOption();
        option.setData(data);

        String svg = JChartRendererFactory.renderChart(JChartType.AREA, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/30_quarter_completion.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/30_quarter_completion.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

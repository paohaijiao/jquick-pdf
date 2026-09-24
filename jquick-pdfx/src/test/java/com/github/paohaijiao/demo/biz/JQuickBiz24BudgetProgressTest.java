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
import com.github.paohaijiao.combol.JHorizontalBarChartData;
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
import java.util.List;

/**
 * 业务场景 Demo 24：年度预算执行进度仪表盘（政务/企业财务）
 *
 * <p>业务场景：市财政局预算管理科跟踪各业务部门年度预算下达额与已执行额，用于市政府常务会议预算执行进度汇报。</p>
 * <p>实际图表类型：{@link JChartType#HorizontalBar} 横向条形图。jquick-pdf 暂无进度图（进度条）渲染器，
 * 因此用「预算下达 vs 已执行」两条横向条形并列的方式替代进度图，执行率由两段条形的长度对比直观呈现。</p>
 *
 * <p>模板：{@code report/biz/24_budget_progress.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\24_budget_progress.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>} 只声明宽度、不声明高度，
 * 宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz24BudgetProgressTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz24BudgetProgress() throws IOException {
        JHorizontalBarChartData chartData = new JHorizontalBarChartData();
        chartData.setTitleText("2024年度各部门预算执行进度");
        chartData.setSubtitleText("预算下达额与已执行额对比（截至2024年9月30日）");
        chartData.setXAxisTitle("金额（万元）");
        chartData.setYAxisTitle("部门");
        chartData.setValueWithPercent(false);
        chartData.setShowDataLabels(true);
        chartData.addYAxisLabel("教育局");
        chartData.addYAxisLabel("卫健委");
        chartData.addYAxisLabel("交通局");
        chartData.addYAxisLabel("农业农村局");
        chartData.addYAxisLabel("民政局");
        // 两条系列分别表示预算下达额与已执行额，颜色沿用政务系统低饱和主色
        List<Double> budget = Arrays.asList(12800.0, 9600.0, 8400.0, 6200.0, 5100.0);
        List<Double> executed = Arrays.asList(11260.0, 8120.0, 6930.0, 5480.0, 4320.0);
        chartData.addBarData(new JHorizontalBarChartData.BarData("预算下达", budget, JHorizontalBarChartData.COLOR_A));
        chartData.addBarData(new JHorizontalBarChartData.BarData("已执行", executed, JHorizontalBarChartData.COLOR_B));
        JOption option = new JOption();
        option.setData(chartData);

        String svg = JChartRendererFactory.renderChart(JChartType.HorizontalBar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/24_budget_progress.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/24_budget_progress.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

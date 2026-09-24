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
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JScatter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 29：预警事件时序散点分布（应急/监管平台）
 *
 * <p>业务场景：应急/监管平台将当月预警事件按「时间轴（第1~30天）」与「预警等级分值（0-100）」
 * 绘制散点，用于识别预警集中时段与高烈度事件。</p>
 * <p>实际图表类型：{@link JChartType#SCATTER} 散点图（x=天数、y=预警分值）。</p>
 *
 * <p>模板：{@code report/biz/29_warning_scatter.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\29_warning_scatter.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz29WarningScatterTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz29WarningScatter() throws IOException {
        JOption option = new JOption();
        option.title().text("2024年12月预警事件时序分布").subtext("横轴：第1~30天 | 纵轴：预警等级分值（0-100）");
        option.tooltip().trigger(JTrigger.axis);
        option.xAxis(new JValueAxis().scale(true));
        option.yAxis(new JValueAxis().scale(true));
        JScatter scatter = new JScatter();
        scatter.name("预警事件");
        // 每个点必须是长度 2 的 Double[]（x=第N天, y=预警分值）；symbolSize 必须设置
        scatter.symbolSize(20).data(
                new JData().value(new Double[]{1.0, 22.0}),
                new JData().value(new Double[]{2.0, 35.0}),
                new JData().value(new Double[]{3.0, 18.0}),
                new JData().value(new Double[]{5.0, 46.0}),
                new JData().value(new Double[]{6.0, 52.0}),
                new JData().value(new Double[]{8.0, 30.0}),
                new JData().value(new Double[]{9.0, 68.0}),
                new JData().value(new Double[]{11.0, 41.0}),
                new JData().value(new Double[]{12.0, 25.0}),
                new JData().value(new Double[]{13.0, 58.0}),
                new JData().value(new Double[]{15.0, 76.0}),
                new JData().value(new Double[]{16.0, 44.0}),
                new JData().value(new Double[]{17.0, 33.0}),
                new JData().value(new Double[]{18.0, 88.0}),
                new JData().value(new Double[]{19.0, 61.0}),
                new JData().value(new Double[]{20.0, 29.0}),
                new JData().value(new Double[]{22.0, 54.0}),
                new JData().value(new Double[]{23.0, 39.0}),
                new JData().value(new Double[]{24.0, 72.0}),
                new JData().value(new Double[]{25.0, 47.0}),
                new JData().value(new Double[]{26.0, 95.0}),
                new JData().value(new Double[]{28.0, 63.0}),
                new JData().value(new Double[]{29.0, 36.0}),
                new JData().value(new Double[]{30.0, 50.0})
        );
        option.series(scatter);

        String svg = JChartRendererFactory.renderChart(JChartType.SCATTER, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/29_warning_scatter.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/29_warning_scatter.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

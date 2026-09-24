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
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JPie;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 39：客户分层饼图（企业运营 / 客户经营）
 *
 * <p>业务场景：企业运营平台按客户价值把活跃客户划分为战略客户、重点客户、成长客户、
 * 一般客户、潜在客户五层，用于客户经营策略与资源投放决策，主色采用商务稳健蓝 #0D47A1。</p>
 * <p>实际图表类型：{@link JChartType#PIE} 饼图（展示各客户层级的数量占比）。</p>
 *
 * <p>模板：{@code report/biz/39_customer_segment_pie.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\39_customer_segment_pie.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz39CustomerSegmentPieTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz39CustomerSegmentPie() throws IOException {
        JOption option = new JOption();
        option.title().text("客户价值分层构成").subtext("2024年活跃客户分层分布（单位：户）");
        option.tooltip().trigger(JTrigger.item);
        JPie pie = new JPie("客户分层");
        // 客户分层户数：呈金字塔结构，层级越高客户数越少但价值贡献越高
        pie.data(
                new JData().name("战略客户").value(86),
                new JData().name("重点客户").value(412),
                new JData().name("成长客户").value(1680),
                new JData().name("一般客户").value(5260),
                new JData().name("潜在客户").value(3140)
        );
        option.series(pie);

        String svg = JChartRendererFactory.renderChart(JChartType.PIE, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/39_customer_segment_pie.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/39_customer_segment_pie.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

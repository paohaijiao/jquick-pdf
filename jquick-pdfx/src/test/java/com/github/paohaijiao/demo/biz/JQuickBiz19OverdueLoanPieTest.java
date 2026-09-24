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
 * 业务场景 Demo 19：逾期贷款占比（金融 / 信贷平台）
 *
 * <p>业务场景：信贷平台按贷款五级分类（正常、关注、次级、可疑、损失）统计资产笔数构成，
 * 用于资产质量月度例会与风险预警，主色采用风险主题的低饱和深红 #B71C1C。</p>
 * <p>实际图表类型：{@link JChartType#PIE} 饼图（展示各风险档位的资产占比）。</p>
 *
 * <p>模板：{@code report/biz/19_overdue_loan_pie.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\19_overdue_loan_pie.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 400pt（宽 × 高）的方形比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz19OverdueLoanPieTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz19OverdueLoanPie() throws IOException {
        JOption option = new JOption();
        option.title().text("贷款五级分类构成").subtext("2024年三季度末信贷资产（单位：笔）");
        option.tooltip().trigger(JTrigger.item);
        JPie pie = new JPie("逾期账龄分布");
        // 五级分类笔数：正常占绝对多数，次级及以下为关注类风险敞口
        pie.data(
                new JData().name("正常").value(6850),
                new JData().name("关注").value(1240),
                new JData().name("次级").value(560),
                new JData().name("可疑").value(280),
                new JData().name("损失").value(150)
        );
        option.series(pie);

        String svg = JChartRendererFactory.renderChart(JChartType.PIE, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/19_overdue_loan_pie.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/19_overdue_loan_pie.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

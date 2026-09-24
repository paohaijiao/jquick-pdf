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
 * 业务场景 Demo 17：项目审批数量趋势（政务/项目监管平台）
 *
 * <p>业务场景：项目监管平台按月统计「受理 / 审批通过 / 退回补正」三类的项目审批数量，
 * 用于观察审批负荷与办理质量的变化。</p>
 * <p>实际图表类型：{@link JChartType#BAR} 柱状图（分组柱，用于对比三类审批结果）。</p>
 *
 * <p>模板：{@code report/biz/17_project_approval.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\17_project_approval.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz17ProjectApprovalTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz17ProjectApproval() throws IOException {
        JOption option = new JOption();
        option.title().text("2024年项目审批数量趋势").subtext("受理 / 审批通过 / 退回补正（单位：件）");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        // 三类审批结果各一根柱，沿用政务系统低饱和主色
        JBar accepted = new JBar();
        accepted.name("受理").data(142, 138, 155, 163, 158, 171, 182, 176, 195, 203, 214, 226);
        JBar approved = new JBar();
        approved.name("审批通过").data(112, 110, 124, 132, 128, 140, 150, 145, 161, 169, 178, 189);
        JBar returned = new JBar();
        returned.name("退回补正").data(22, 20, 24, 25, 23, 26, 27, 25, 28, 27, 29, 30);
        option.series(accepted, approved, returned);

        String svg = JChartRendererFactory.renderChart(JChartType.BAR, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/17_project_approval.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/17_project_approval.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

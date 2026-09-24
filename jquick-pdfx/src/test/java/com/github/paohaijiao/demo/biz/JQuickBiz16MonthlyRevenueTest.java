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
import com.github.paohaijiao.series.JLine;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 业务场景 Demo 16：月度营收统计表（企业运营）
 *
 * <p>业务场景：企业运营管理系统按月汇总营业收入与净利润，用于管理层月度经营例会。</p>
 * <p>实际图表类型：{@link JChartType#LINE} 折线图（多重折线，用于观察两项指标的月度走势）。</p>
 *
 * <p>模板：{@code report/biz/16_monthly_revenue.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\16_monthly_revenue.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt（宽 × 高）的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，因此宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz16MonthlyRevenueTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz16MonthlyRevenue() throws IOException {
        JOption option = new JOption();
        option.title().text("2024年月度营收统计").subtext("营业收入与净利润走势（单位：万元）");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        // 两条折线分别表示营业收入与净利润，颜色沿用政务/企业系统低饱和主色
        JLine revenue = new JLine();
        revenue.name("营业收入").data(1860, 1720, 2140, 2380, 2260, 2510, 2680, 2540, 2820, 3050, 3180, 3460);
        JLine profit = new JLine();
        profit.name("净利润").data(232, 205, 286, 322, 298, 351, 384, 342, 415, 462, 486, 538);
        option.series(revenue, profit);

        String svg = JChartRendererFactory.renderChart(JChartType.LINE, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/16_monthly_revenue.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/16_monthly_revenue.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

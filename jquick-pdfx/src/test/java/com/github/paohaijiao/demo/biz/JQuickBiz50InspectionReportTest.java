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
 * 检验报告 Demo 50：产品质量检验报告（质检机构场景）
 *
 * <p>业务场景：质检机构出具委托检验报告，记录样品信息、各检验项目的技术要求与实测结果，
 * 给出单项判定与整份报告的最终结论。</p>
 *
 * <p>版式：页头左标题区 + 右编号信息盒；样品信息四列表；检验项目五列表；
 * 实测值与标准限值对比柱状图；整块绿底大字判定结论；三栏签字区 + 声明小字。</p>
 *
 * <p>图表说明：使用 {@link JChartType#BAR} 分组柱状图对比「标准限值 / 实测值」，
 * 使判定结论有可核对的数据支撑；模板中 {@code <svg>} 只声明宽度，高度按原比例自动缩放。</p>
 *
 * <p>模板：{@code report/biz/50_inspection_report.txt}，输出：{@code D:\test\biz\50_inspection_report.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz50InspectionReportTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz50InspectionReport() throws IOException {
        JOption option = new JOption();
        option.title().text("各检验项目实测值与标准限值对比").subtext("绝缘电阻按 1/20 比例折算显示");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("绝缘电阻", "耐电压", "空载电流", "空载损耗", "效率");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        // 两组柱：标准限值与实测值，便于目视判断余量
        JBar standard = new JBar();
        standard.name("标准限值").data(20, 100, 5.6, 190, 87);
        JBar measured = new JBar();
        measured.name("实测值").data(24, 100, 4.8, 172, 89.4);
        option.series(standard, measured);

        String svg = JChartRendererFactory.renderChart(JChartType.BAR, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/50_inspection_report.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（页头双栏、图表居中、三栏签字区）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/50_inspection_report.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

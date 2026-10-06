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
 * 标书 Demo 53：投标文件（多页标书场景）
 *
 * <p>业务场景：投标人编制「技术标 + 商务标」合订本，需包含封面、目录、投标函、
 * 类似项目业绩、技术方案与商务报价等章次，并要求各章起始页稳定。</p>
 *
 * <p>版式：使用 {@code <areaBreak></areaBreak>} 显式分页，共 4 页——封面（粗框 + 无竖线
 * 填写式信息表）→ 目录（章次 / 内容 / 页码三列表）→ 投标函 + 企业业绩（含折线图）→
 * 技术方案 + 商务报价（末行合计）+ 三栏页脚。</p>
 *
 * <p>图表说明：使用 {@link JChartType#LINE} 折线图展示近三年同类项目合同额与验收合格率，
 * 作为「类似项目业绩」章节的数据支撑。</p>
 *
 * <p>模板：{@code report/biz/53_tender_multipage.txt}，输出：{@code D:\test\biz\53_tender_multipage.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz53TenderMultipageTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz53TenderMultipage() throws IOException {
        JOption option = new JOption();
        option.title().text("近三年同类项目业绩").subtext("合同额（万元）与验收合格率（%）");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("2022年", "2023年", "2024年");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JLine amount = new JLine();
        amount.name("合同额（万元）").data(1860, 2540, 3180);
        JLine rate = new JLine();
        rate.name("验收合格率（%）").data(96, 98, 100);
        option.series(amount, rate);

        String svg = JChartRendererFactory.renderChart(JChartType.LINE, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/53_tender_multipage.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（封面密级两端对齐、图表居中、页脚三栏）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/53_tender_multipage.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

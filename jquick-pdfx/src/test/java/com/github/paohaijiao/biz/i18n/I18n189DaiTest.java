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
package com.github.paohaijiao.biz.i18n;

import org.junit.Test;

import java.awt.Color;
import java.io.IOException;

/**
 * 单据 156（系列）· i18n 序号 189 —— 傣语（新傣仂文）版全球经营年报摘要
 *
 * <p>语种 dai：傣语（新傣仂文）。字体 Noto Sans New Tai Lue，视觉主题「贝叶竹青·孔雀式（#557C1F + #8B5A00，左对齐 + ᧑᧒᧓ 色块 + 发丝线表）」。</p>
 *
 * <p>模板：i18n/global_bank_report_dai.txt
 * 输出：D://test//i18n//global_bank_report_dai.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/dai.txt} → {@code D://test//i18n//dai.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n189DaiTest {

    /**
     * 主产物：傣语（新傣仂文）版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n189() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("dai", "Noto Sans New Tai Lue");
        p.reportDate = "ᦞᧃ ᧒᧖ ᦵᦡᦲᧃ ᧑᧐ ᦔᦲ ᧒᧐᧒᧖";
        p.orgName = "ᦷᦣᧂᦇᦹᧃᦷᦜᧂ ᦂᦱᧃ ᦵᦙᦲᧂ";
        p.dataScope = "ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧙ ᦔᦲ ᧒᧐᧒᧖";
        p.totalAssets = "᧘.᧖ ᦜᦱᧃ ᦍᦲᧃ";
        p.crossBorderSettlement = "᧑.᧒᧔ ᦜᦱᧃ ᦍᦲᧃ";
        p.globalBranches = "᧑᧒᧘᧖ ᦺᦆᧈ";
        p.countries = "᧖᧘ ᦵᦙᦲᧂ";
        p.revenue = "᧒᧑᧘ ᦜᦱᧃ ᦍᦲᧃ";
        p.netProfit = "᧖᧘᧖ ᦜᦱᧃ ᦍᦲᧃ";
        p.employees = "᧘᧖᧐᧐᧐ ᦅᦳᧃ";
        p.customers = "᧙᧖᧐᧐ ᦜᦱᧃ ᦅᦳᧃ";
        p.preparedBy = "ᦷᦣᧂ ᦂᦱᧃ ᦷᦜᧂ";
        p.reviewedBy = "ᦞᦱᧂ ᦍᦲᧃ ᦈᦸᧈ";
        p.issuedBy = "ᦷᦣᧂᦇᦹᧃᦷᦜᧂ";
        p.barTitle = "ᦇᦹᧃ ᦃᧁᧉ ᦺᦓ ᦵᦙᦲᧂ";
        p.barSub = "ᦔᦲ ᧒᧐᧒᧖ (ᦜᦱᧃ ᦍᦲᧃ)";
        p.barSeriesName = "ᦇᦹᧃ ᦃᧁᧉ";
        p.barRegions = new String[]{"ᦵᦙᦲᧂ ᦷᦜᧂ", "ᦵᦙᦲᧂ ᧑", "ᦵᦙᦲᧂ ᧒", "ᦵᦙᦲᧂ ᧓", "ᦵᦙᦲᧂ ᧔", "ᦵᦙᦲᧂ ᧕"};
        p.donutTitle = "ᦇᦹᧃ ᦃᧁᧉ ᦀᦸᧅᧈ ᦺᦓ ᦵᦙᦲᧂ";
        p.donutSub = "ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧙ ᦔᦲ ᧒᧐᧒᧖";
        p.donutCenterTitle = "ᦂᦱᧃ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᦂᦱᧃ ᦇᦹᧃ ᦵᦙᦲᧂ";
        p.donutNames = new String[]{"ᦍᦲᧃ CNY", "ᦡᦸᧃᧈ USD", "ᦍᦴᦷᦣ EUR", "ᦍᦲᧃ JPY", "ᦣᦴᦔ RUB", "ᦡᦲᧃ AED"};
        p.lineTitle = "ᦂᦱᧃ ᦇᦹᧃ ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧑᧐ ᦔᦲ ᧒᧐᧒᧖";
        p.lineSub = "ᦂᦱᧃ ᦇᦹᧃ / ᦂᦱᧃ ᦃᦻ / ᦂᦱᧃ ᦇᦹᧃ ᦎᦱᧂᧈ";
        p.lineYAxis = "ᦜᦱᧃ ᦍᦲᧃ";
        p.lineFooter = "ᦂᦱᧃ ᦇᦹᧃ ᦷᦜᧂ | ᦜᦱᧃ ᦍᦲᧃ";
        p.lineMonths = new String[]{"ᦵᦡᦲᧃ ᧑", "ᦵᦡᦲᧃ ᧒", "ᦵᦡᦲᧃ ᧓", "ᦵᦡᦲᧃ ᧔", "ᦵᦡᦲᧃ ᧕", "ᦵᦡᦲᧃ ᧖", "ᦵᦡᦲᧃ ᧗", "ᦵᦡᦲᧃ ᧘", "ᦵᦡᦲᧃ ᧙", "ᦵᦡᦲᧃ ᧑᧐"};
        p.lineNames = new String[]{"ᦂᦱᧃ ᦇᦹᧃ", "ᦂᦱᧃ ᦃᦻ", "ᦂᦱᧃ ᦇᦹᧃ ᦎᦱᧂᧈ"};
        p.donutPalette = new Color[]{
                new Color(85, 124, 31), new Color(139, 90, 0), new Color(140, 170, 80),
                new Color(180, 200, 130), new Color(210, 222, 175), new Color(238, 242, 220)
        };
        p.lineColors = new Color[]{new Color(85, 124, 31), new Color(139, 90, 0), new Color(140, 170, 80)};
        p.chartArea = new Color(246, 248, 236);
        p.barColor = "#557C1F";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：傣语（新傣仂文）脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple189() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/dai.txt", "Noto Sans New Tai Lue", "fonts/NotoSansNewTaiLue-Regular.ttf");
    }
}

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
 * 单据 156（系列）· i18n 序号 207 —— 藏语版全球经营年报摘要
 *
 * <p>语种 bo：藏语。字体 Noto Serif Tibetan，视觉主题「绛红藏式横幅（#7B2D26 + #D4A017，绛红横幡 + 金色经文带）」。</p>
 *
 * <p>模板：i18n/global_bank_report_bo.txt
 * 输出：D://test//i18n//global_bank_report_bo.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/tibetan.txt} → {@code D://test//i18n//tibetan.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n207TibetanTest {

    /**
     * 主产物：藏语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n207() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("bo", "Noto Serif Tibetan");
        p.reportDate = "2026 ཟླ 10 ཚེས 26";
        p.orgName = "གོ་ལ་ཡོངས་དངུལ་ཁང";
        p.dataScope = "2026 ལོའི་དུས་ཚིགས་དང་པོ་གསུམ";
        p.totalAssets = "8.6 སྒོར་དུང་ཕྱུར་ཁྲི";
        p.crossBorderSettlement = "1.24 སྒོར་དུང་ཕྱུར་ཁྲི";
        p.globalBranches = "1,286 ཡན་ལག";
        p.countries = "68 རྒྱལ་ཁབ";
        p.revenue = "2,180 སྒོར་དུང་ཕྱུར";
        p.netProfit = "686 སྒོར་དུང་ཕྱུར";
        p.employees = "86,000 ལས་བྱེད";
        p.customers = "9,600 ཁྲི མགྲོན་པོ";
        p.preparedBy = "ལྷན་ཚོགས་གཞུང་ལས་ཁང";
        p.reviewedBy = "ཝང་ཡན་ཇོ།";
        p.issuedBy = "གོ་ལ་ཡོངས་དངུལ་ཁང";
        p.barTitle = "ཡུལ་ཁུལ་བཞིན་གྱི་ཡོང་འབབ";
        p.barSub = "2026 ལོའི་དུས་ཚིགས་དང་པོ་གསུམ（སྒོར་དུང་ཕྱུར）";
        p.barSeriesName = "ཡོང་འབབ";
        p.barRegions = new String[]{"ཀྲུང་གོའི་ནང་ལོགས", "ལྷོ་ཨེ་ཤེ་ཡ", "ཡོ་རོབ", "དབུས་ཤར", "རི་པིན", "ཨུ་རུ་སུ"};
        p.donutTitle = "རྒྱལ་མཚམས་རྩིས་སྤྲོད་ཀྱི་དངུལ་རིགས་བསྡུར";
        p.donutSub = "རྩིས་སྤྲོད་བསྡོམས་འབོར་བཞིན";
        p.donutCenterTitle = "བསྡུར་ཚད";
        p.donutCenterUnit = "%";
        p.donutFooter = "འབྱུང་ཁུངས། རྒྱལ་མཚམས་རྩིས་སྤྲོད་མ་ལག";
        p.donutNames = new String[]{"སྒོར", "ཨ་མེ་རི་ཀའི་སྒོར", "ཡུ་རོ", "རི་པིན་སྒོར", "ལུ་སུ", "ཌིར་ཧམ"};
        p.lineTitle = "2026 ལོའི་ཟླ་བ 1-10 ནང་རྒྱལ་མཚམས་ལས་ཀའི་འཕེལ་ཕྱོགས";
        p.lineSub = "རྩིས་སྤྲོད / ཚོང་དོན་མ་དངུལ / ཕྱིའི་དངུལ་བརྗེས";
        p.lineYAxis = "སྒོར་དུང་ཕྱུར";
        p.lineFooter = "འབྱུང་ཁུངས། གོ་ལའི་ལས་ཀ་ལྟ་ཞིབ་ལྟེ་གནས | སྒོར་དུང་ཕྱུར";
        p.lineMonths = new String[]{"ཟླ 1", "ཟླ 2", "ཟླ 3", "ཟླ 4", "ཟླ 5", "ཟླ 6", "ཟླ 7", "ཟླ 8", "ཟླ 9", "ཟླ 10"};
        p.lineNames = new String[]{"རྒྱལ་མཚམས་རྩིས་སྤྲོད", "ཚོང་དོན་མ་དངུལ", "ཕྱིའི་དངུལ་བརྗེས"};
        p.donutPalette = new Color[]{
                new Color(123, 45, 38), new Color(212, 160, 23), new Color(168, 91, 50),
                new Color(198, 139, 91), new Color(224, 185, 140), new Color(240, 220, 188)
        };
        p.lineColors = new Color[]{new Color(123, 45, 38), new Color(212, 160, 23), new Color(168, 91, 50)};
        p.chartArea = new Color(251, 243, 232);
        p.barColor = "#7B2D26";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：藏语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple207() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/tibetan.txt", "Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");
    }
}

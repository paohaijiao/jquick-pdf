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
 * 单据 156（系列）· i18n 序号 199 —— 传统蒙古语（蒙古文）版全球经营年报摘要
 *
 * <p>语种 mn：传统蒙古语（蒙古文）。字体 Noto Sans Mongolian，视觉主题「草原翠留白式（#2F6B3C + #C8A24B，留白章节 + 草原纹样条）」。</p>
 *
 * <p>模板：i18n/global_bank_report_mn.txt
 * 输出：D://test//i18n//global_bank_report_mn.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/traditional_mongolian.txt} → {@code D://test//i18n//traditional_mongolian.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n199MongolianTest {

    /**
     * 主产物：传统蒙古语（蒙古文）版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n199() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("mn", "Noto Sans Mongolian");
        p.reportDate = "2026 ᠵᠢᠯ 10 ᠰᠠᠷᠠ 26 ᠡᠳᠦᠷ";
        p.orgName = "ᠮᠣᠨᠭᠭᠣᠯ ᠪᠠᠨᠭᠺᠢ";
        p.dataScope = "2026 ᠵᠢᠯ ᠤᠨ ᠡᠺᠢᠨ ᠭᠤᠷᠪᠠᠨ ᠰᠠᠷᠠ";
        p.totalAssets = "8.6 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.crossBorderSettlement = "1.24 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.globalBranches = "1,286 ᠰᠠᠯᠠ";
        p.countries = "68 ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ";
        p.revenue = "2,180 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.netProfit = "686 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.employees = "86,000 ᠠᠵᠢᠯᠴᠢᠨ";
        p.customers = "9,600 ᠲᠤᠮᡝᠨ ᠬᠡᠷᠡᠭᠯᠡᠭᠴᠢᠳ";
        p.preparedBy = "ᠽᠣᠾᠢᠭᠴᠢ";
        p.reviewedBy = "ᠰᠢᠯᠢᠭᠴᠢᠨ";
        p.issuedBy = "ᠭᠠᠷᠭᠠᠭᠴᠢ";
        p.barTitle = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠨᡝᠢᠲᡝ ᠪᠠᠶᠢᠳᠠᠯ";
        p.barSub = "2026 ᠵᠢᠯ 10 ᠰᠠᠷᠠ 26 ᠡᠳᠦᠷ";
        p.barSeriesName = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.barRegions = new String[]{"ᠬᠢᠲᠠᠳ", "ᠠᠽᠢ", "ᠡᠤᠷᠣᠫᡝ", "ᠠᠷᠠᠪ", "ᠶᠠᠫᠣᠨ", "ᠣᠷᠤᠰ"};
        p.donutTitle = "ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ ᠤᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.donutSub = "2026 ᠵᠢᠯ ᠤᠨ ᠡᠺᠢᠨ ᠭᠤᠷᠪᠠᠨ ᠰᠠᠷᠠ";
        p.donutCenterTitle = "ᠴᠡᠪᡝᡵ ᠠᠰᠢᠭ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ ᠤᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.donutNames = new String[]{"ᠶᠤᠠᠨ", "ᠳᠣᠯᠯᠠᡵ", "ᡝᠤᠷᠣ", "ᠶᡝᠨ", "ᠷᠤᠪᠯᠢ", "ᠳᠢᠷᠬᠠᠮ"};
        p.lineTitle = "ᠵᡝᠴᡝᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠰᠠᠷᠠ ᠤᠨ ᠬᠠᠨᠳᠢᠯ";
        p.lineSub = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠨᡝᠢᠲᡝ ᠪᠠᠶᠢᠳᠠᠯ";
        p.lineYAxis = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.lineFooter = "ᠵᡝᠴᡝᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠰᠠᠷᠠ ᠤᠨ ᠬᠠᠨᠳᠢᠯ";
        p.lineMonths = new String[]{"1 ᠰᠠᠷᠠ", "2 ᠰᠠᠷᠠ", "3 ᠰᠠᠷᠠ", "4 ᠰᠠᠷᠠ", "5 ᠰᠠᠷᠠ", "6 ᠰᠠᠷᠠ", "7 ᠰᠠᠷᠠ", "8 ᠰᠠᠷᠠ", "9 ᠰᠠᠷᠠ", "10 ᠰᠠᠷᠠ"};
        p.lineNames = new String[]{"ᠳᠠᠠᠯᠠ", "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ", "ᠬᠠᠷᠢᠠ ᠮᠥᠨᠭᠭᠥ"};
        p.donutPalette = new Color[]{
                new Color(47, 107, 60), new Color(200, 162, 75), new Color(123, 160, 91),
                new Color(168, 196, 141), new Color(201, 219, 177), new Color(228, 238, 215)
        };
        p.lineColors = new Color[]{new Color(47, 107, 60), new Color(200, 162, 75), new Color(123, 160, 91)};
        p.chartArea = new Color(234, 243, 230);
        p.barColor = "#2F6B3C";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：传统蒙古语（蒙古文）脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple199() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/traditional_mongolian.txt", "Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
    }
}

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
 * 单据 156（系列）· i18n 序号 198 —— 满语（满文）版全球经营年报摘要
 *
 * <p>语种 manchu：满语（满文）。字体 Noto Sans Mongolian，视觉主题「玄青档案框式（#1B2A4A + #B08D57，档案封套式外框 + 竖排字符区）」。</p>
 *
 * <p>模板：i18n/global_bank_report_manchu.txt
 * 输出：D://test//i18n//global_bank_report_manchu.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/manchu.txt} → {@code D://test//i18n//manchu.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n198ManchuTest {

    /**
     * 主产物：满语（满文）版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n198() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("manchu", "Noto Sans Mongolian");
        p.reportDate = "2026 ᠠᠨᡳᠶᠠ 10 ᠪᡳᠶᠠ 26 ᡳᠨᠡᠨᡤᡤᡳ";
        p.orgName = "ᠠᠮᠪᠠ ᠮᡝᠨᡤᡤᡠᠨ ᠪᠣᠣ";
        p.dataScope = "2026 ᠠᠨᡳᠶᠠ ᡠᠶᡠᠨ ᠪᡳᠶᠠ";
        p.totalAssets = "8.6 ᡨᡠᠮᠡᠨ ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.crossBorderSettlement = "1.24 ᡨᡠᠮᠡᠨ ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.globalBranches = "1,286 ᠪᠣᠣ";
        p.countries = "68 ᡤᡠᠷᡠᠨ ᠪᠠ";
        p.revenue = "2,180 ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.netProfit = "686 ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.employees = "86,000 ᠨᡳᠶᠠᠯᠮᠠ";
        p.customers = "9,600 ᡨᡠᠮᡝᠨ ᡳᠷᡤᡝᠨ";
        p.preparedBy = "ᠠᠷᠠᠷᠠ";
        p.reviewedBy = "ᠠᡴᡩᠠᡵᠠ";
        p.issuedBy = "ᠠᠯᡤᡳᠪᡠᡵᡠ";
        p.barTitle = "ᡠᠵᠡᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᠪᠠᡩᠠᠷᠠᠨ";
        p.barSub = "2026 ᠠᠨᡳᠶᠠ 10 ᠪᡳᠶᠠ 26 ᡳᠨᠡᠨᡤᡤᡳ";
        p.barSeriesName = "ᡴᠠᠯᡳᠴᡠᠨ ᠮᠡᠨᡤᡤᡠᠨ";
        p.barRegions = new String[]{"ᠨᡳᡴᠠᠨ", "ᡨᡠᠯᠡᠷᡤᡳ ᠵᡝᠴᡝᠨ", "ᡶᠠᠯᠠᠨᠰᡝ ᡤᡠᠷᡠᠨ", "ᠣᠷᡠᠰᠠ ᡤᡠᠷᡠᠨ", "ᠨᡳᠶᠠᠨ ᡤᡠᠷᡠᠨ", "ᡳᠨᡤᡤᡳᠷᡳ ᡤᡠᠷᡠᠨ"};
        p.donutTitle = "ᠪᠠ ᡳ ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutSub = "2026 ᠠᠨᡳᠶᠠ ᡠᠶᡠᠨ ᠪᡳᠶᠠ";
        p.donutCenterTitle = "ᠰᠠᡳᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᠪᠠ ᡳ ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutNames = new String[]{"ᠶᠠᠨ", "ᡩᠣᠯᠠᡵ", "ᡝᡠᠷᠣ", "ᠶᡝᠨ", "ᠯᡠᠪᠯᡠ", "ᡩᡳᠷᡥᠠᠮ"};
        p.lineTitle = "ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᡝᡵᡳᠨ";
        p.lineSub = "ᡠᠵᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᠪᠠᡩᠠᠷᠠᠨ";
        p.lineYAxis = "ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.lineFooter = "ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᡝᡵᡳᠨ";
        p.lineMonths = new String[]{"1 ᠪᡳᠶᠠ", "2 ᠪᡳᠶᠠ", "3 ᠪᡳᠶᠠ", "4 ᠪᡳᠶᠠ", "5 ᠪᡳᠶᠠ", "6 ᠪᡳᠶᠠ", "7 ᠪᡳᠶᠠ", "8 ᠪᡳᠶᠠ", "9 ᠪᡳᠶᠠ", "10 ᠪᡳᠶᠠ"};
        p.lineNames = new String[]{"ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ", "ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ", "ᡥᠠᠴᡳᠨ ᠮᡝᠨᡤᡤᡠᠨ"};
        p.donutPalette = new Color[]{
                new Color(27, 42, 74), new Color(176, 141, 87), new Color(110, 127, 158),
                new Color(154, 167, 192), new Color(197, 204, 220), new Color(225, 228, 236)
        };
        p.lineColors = new Color[]{new Color(27, 42, 74), new Color(176, 141, 87), new Color(110, 127, 158)};
        p.chartArea = new Color(244, 239, 227);
        p.barColor = "#1B2A4A";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：满语（满文）脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple198() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/manchu.txt", "Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
    }
}

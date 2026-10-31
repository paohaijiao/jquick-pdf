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
 * 单据 156（系列）· i18n 序号 208 —— 维吾尔语版全球经营年报摘要
 *
 * <p>语种 ug：维吾尔语。字体 Noto Sans Arabic，视觉主题「翡翠铜右起式（#0F6B5C，RTL 右起表格 + 铜色描边）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ug.txt
 * 输出：D://test//i18n//global_bank_report_ug.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/uyghur.txt} → {@code D://test//i18n//uyghur.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n208UyghurTest {

    /**
     * 主产物：维吾尔语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n208() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ug", "Noto Sans Arabic");
        p.reportDate = "2026-يىلى 10-ئاينىڭ 26-كۈنى";
        p.orgName = "يەر شارى بانكىسى گۇرۇھ ئىشخانىسى";
        p.dataScope = "2026-يىلنىڭ ئالدىنقى ئۈچ پەسلى، گۇرۇھ بىرلەشمە ئۆلچىمى";
        p.totalAssets = "8.6 تىرىليون يۈەن";
        p.crossBorderSettlement = "1.24 تىرىليون يۈەن";
        p.globalBranches = "1,286 تارماق";
        p.countries = "68 دۆلەت ۋە رايون";
        p.revenue = "218 مىليارد يۈەن";
        p.netProfit = "68.6 مىليارد يۈەن";
        p.employees = "86,000 خىزمەتچى";
        p.customers = "96 مىليون خېرىدار";
        p.preparedBy = "گۇرۇھ ئىشخانىسى كۆپ تىللىق خىزمەت گۇرۇپپىسى";
        p.reviewedBy = "ۋاڭ يەنجۇ";
        p.issuedBy = "يەر شارى بانكىسى";
        p.barTitle = "رايونلار بويىچە تىجارەت كىرىمى";
        p.barSub = "2026-يىلنىڭ ئالدىنقى ئۈچ پەسلى（بىرلىك: 100 مىليون يۈەن）";
        p.barSeriesName = "تىجارەت كىرىمى";
        p.barRegions = new String[]{"جۇڭگو ئىچكى قۇرۇقلۇقى", "ئاسىيا-تىنچ ئوكيان", "ياۋروپا", "ئوتتۇرا شەرق", "ياپونىيە", "رۇس تىل رايونى"};
        p.donutTitle = "چېگرا ھالقىغان راسچوتنىڭ پۇل تۈرى تەركىبى";
        p.donutSub = "راسچوت سوممىسى بويىچە نىسبەت";
        p.donutCenterTitle = "راسچوت نىسبىتى";
        p.donutCenterUnit = "%";
        p.donutFooter = "مەنبە: چېگرا ھالقىغان راسچوت سىستېمىسى";
        p.donutNames = new String[]{"يۈەن", "ئامېرىكا دوللىرى", "ياۋرو", "يېن", "رۇبلى", "دىرھەم"};
        p.lineTitle = "2026-يىلى 1-ئايدىن 10-ئايغىچە چېگرا ھالقىغان كەسىپ ھەجمى";
        p.lineSub = "راسچوت / سودا مۇلازىمىتى / تاشقى پېرېۋوت";
        p.lineYAxis = "100 مىليون يۈەن";
        p.lineFooter = "مەنبە: دۇنياۋى كەسىپ كۆزىتىش سۇپىسى | بىرلىك: 100 مىليون يۈەن";
        p.lineMonths = new String[]{"يانۋار", "فېۋرال", "مارت", "ئاپرېل", "ماي", "ئىيۇن", "ئىيۇل", "ئاۋغۇست", "سېنتەبىر", "ئۆكتەبىر"};
        p.lineNames = new String[]{"چېگرا ھالقىغان راسچوت", "سودا مۇلازىمىتى", "تاشقى پېرېۋوت"};
        p.donutPalette = new Color[]{
                new Color(15, 107, 92), new Color(194, 118, 43), new Color(62, 142, 126),
                new Color(116, 179, 165), new Color(171, 208, 199), new Color(216, 234, 229)
        };
        p.lineColors = new Color[]{new Color(15, 107, 92), new Color(194, 118, 43), new Color(62, 142, 126)};
        p.chartArea = new Color(233, 245, 241);
        p.barColor = "#0F6B5C";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：维吾尔语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple208() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/uyghur.txt", "Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
    }
}

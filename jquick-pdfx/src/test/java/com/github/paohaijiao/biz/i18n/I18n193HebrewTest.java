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
 * 单据 156（系列）· i18n 序号 193 —— 希伯来语版全球经营年报摘要
 *
 * <p>语种 he：希伯来语。字体 Noto Sans Hebrew，视觉主题「迦南·橄榄式（#1A7F37 + 石灰 #6B7280，右粗色条 + 全篇 RTL 右对齐）」。</p>
 *
 * <p>模板：i18n/global_bank_report_he.txt
 * 输出：D://test//i18n//global_bank_report_he.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/hebrew.txt} → {@code D://test//i18n//hebrew.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n193HebrewTest {

    /**
     * 主产物：希伯来语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n193() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("he", "Noto Sans Hebrew");
        p.reportDate = "26 באוקטובר 2026";
        p.orgName = "מזכירות קבוצת בנק הוויטונג הגלובלי";
        p.dataScope = "מצטבר לרבעון השלישי של 2026, בסיס מאוחד";
        p.totalAssets = "8.6 טריליון יואן";
        p.crossBorderSettlement = "1.24 טריליון יואן";
        p.globalBranches = "1,286 סניפים";
        p.countries = "68 מדינות";
        p.revenue = "218 מיליארד יואן";
        p.netProfit = "68.6 מיליארד יואן";
        p.employees = "86,000";
        p.customers = "96 מיליון";
        p.preparedBy = "צוות רב-לשוני של המזכירות";
        p.reviewedBy = "וואנג יאן-ג'ואו";
        p.issuedBy = "בנק הוויטונג הגלובלי";
        p.barTitle = "הכנסות תפעוליות לפי אזור";
        p.barSub = "מצטבר לרבעון השלישי של 2026 (מאה מיליון יואן)";
        p.barSeriesName = "הכנסות תפעוליות";
        p.barRegions = new String[]{"סין", "אסיה-פסיפיק", "אירופה", "מזרח תיכון", "יפן", "רוסיה"};
        p.donutTitle = "הרכב מטבעות בסליקה חוצת גבולות";
        p.donutSub = "לפי שווי הסליקה, מצטבר לרבעון השלישי של 2026";
        p.donutCenterTitle = "חלק";
        p.donutCenterUnit = "%";
        p.donutFooter = "מקור: מערכת הסליקה חוצת הגבולות";
        p.donutNames = new String[]{"יואן CNY", "דולר USD", "אירו EUR", "ין JPY", "רובל RUB", "דירהם AED"};
        p.lineTitle = "מגמה חודשית של מחזור חוצה גבולות, ינואר–אוקטובר 2026";
        p.lineSub = "סליקה חוצת גבולות / מימון סחר / מט\"ח";
        p.lineYAxis = "שווי (מאה מיליון יואן)";
        p.lineFooter = "מקור: פלטפורמת ניטור פעילות גלובלית | יחידה: מאה מיליון יואן";
        p.lineMonths = new String[]{"ינו", "פבר", "מרץ", "אפר", "מאי", "יונ", "יול", "אוג", "ספט", "אוק"};
        p.lineNames = new String[]{"סליקה חוצת גבולות", "מימון סחר", "מט\"ח"};
        p.donutPalette = new Color[]{
                new Color(26, 127, 55), new Color(15, 82, 35), new Color(107, 114, 128),
                new Color(94, 163, 113), new Color(160, 200, 172), new Color(232, 244, 234)
        };
        p.lineColors = new Color[]{new Color(26, 127, 55), new Color(107, 114, 128), new Color(200, 144, 27)};
        p.chartArea = new Color(246, 250, 246);
        p.barColor = "#1A7F37";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：希伯来语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple193() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/hebrew.txt", "Noto Sans Hebrew", "fonts/NotoSansHebrew.ttf");
    }
}

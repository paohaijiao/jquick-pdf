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
 * 单据 156（系列）· i18n 序号 190 —— English版全球经营年报摘要
 *
 * <p>语种 en：English。字体 Noto Sans，视觉主题「Graphite Swiss 石墨极简式（#232323 + 信号橙 #E8590C，左对齐无框细线表）」。</p>
 *
 * <p>模板：i18n/global_bank_report_en.txt
 * 输出：D://test//i18n//global_bank_report_en.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n190EnglishTest {

    /**
     * 主产物：English版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n190() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("en", "Noto Sans");
        p.reportDate = "26 October 2026";
        p.orgName = "Global Union Bank, Group Office";
        p.dataScope = "First three quarters of 2026, group consolidated";
        p.totalAssets = "RMB 8.6 trillion";
        p.crossBorderSettlement = "RMB 1.24 trillion";
        p.globalBranches = "1,286 branches";
        p.countries = "68 countries";
        p.revenue = "RMB 218.0 bn";
        p.netProfit = "RMB 68.6 bn";
        p.employees = "86,000 staff";
        p.customers = "96 million customers";
        p.preparedBy = "Group Office, Multilingual Task Force";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Global Union Bank";
        p.barTitle = "Operating Revenue by Region";
        p.barSub = "First three quarters of 2026 (RMB 100 million)";
        p.barSeriesName = "Operating revenue";
        p.barRegions = new String[]{"Mainland China", "Asia-Pacific (ex-China)", "Europe", "Middle East", "Japan", "Russia & CIS"};
        p.donutTitle = "Currency Composition of Cross-border Settlement";
        p.donutSub = "Share of settlement value, first three quarters of 2026";
        p.donutCenterTitle = "Total Share";
        p.donutCenterUnit = "%";
        p.donutFooter = "Source: cross-border settlement system";
        p.donutNames = new String[]{"CNY", "USD", "EUR", "JPY", "RUB", "AED"};
        p.lineTitle = "Monthly Cross-border Business Volume, Jan-Oct 2026";
        p.lineSub = "Settlement / Trade finance / FX trading";
        p.lineYAxis = "RMB 100 million";
        p.lineFooter = "Source: global business monitoring platform | Unit: RMB 100 million";
        p.lineMonths = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct"};
        p.lineNames = new String[]{"Settlement", "Trade finance", "FX trading"};
        // Visual theme "Graphite Swiss": ink + signal orange
        p.donutPalette = new Color[]{
                new Color(35, 35, 35), new Color(232, 89, 12), new Color(110, 110, 110),
                new Color(160, 160, 160), new Color(207, 207, 207), new Color(236, 236, 236)
        };
        p.lineColors = new Color[]{new Color(35, 35, 35), new Color(232, 89, 12), new Color(110, 110, 110)};
        p.chartArea = new Color(246, 246, 244);
        p.barColor = "#E8590C";
        I18nDemoSupport.bankReport(p);
    }
}

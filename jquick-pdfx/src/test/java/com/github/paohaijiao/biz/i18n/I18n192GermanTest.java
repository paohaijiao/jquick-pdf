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
 * 单据 156（系列）· i18n 序号 192 —— 德语版全球经营年报摘要
 *
 * <p>语种 de：德语。字体 Noto Sans，视觉主题「钢灰严谨·德式网格式（#37474F + 芥末黄 #C99700，满格深线网格）」。</p>
 *
 * <p>模板：i18n/global_bank_report_de.txt
 * 输出：D://test//i18n//global_bank_report_de.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/german.txt} → {@code D://test//i18n//german.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n192GermanTest {

    /**
     * 主产物：德语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n192() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("de", "Noto Sans");
        p.reportDate = "26. Oktober 2026";
        p.orgName = "Global Union Bank, Gruppenzentrale";
        p.dataScope = "Erste drei Quartale 2026, konsolidierte Gruppenbasis";
        p.totalAssets = "8,6 Billionen Yuan";
        p.crossBorderSettlement = "1,24 Billionen Yuan";
        p.globalBranches = "1.286 Niederlassungen";
        p.countries = "68 Länder und Regionen";
        p.revenue = "218 Mrd. Yuan";
        p.netProfit = "68,6 Mrd. Yuan";
        p.employees = "86.000 Mitarbeiter";
        p.customers = "96 Mio. Kunden";
        p.preparedBy = "Gruppenzentrale, mehrsprachiges Projektteam";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Global Union Bank";
        p.barTitle = "Betriebsertrag nach Regionen";
        p.barSub = "Erste drei Quartale 2026 (100 Mio. Yuan)";
        p.barSeriesName = "Betriebsertrag";
        p.barRegions = new String[]{"Festlandchina", "Asien-Pazifik (ohne China)", "Europa", "Naher Osten", "Japan", "Russland und GUS"};
        p.donutTitle = "Währungsstruktur der grenzüberschreitenden Abwicklung";
        p.donutSub = "Anteil am Abwicklungsvolumen, erste drei Quartale 2026";
        p.donutCenterTitle = "Gesamtanteil";
        p.donutCenterUnit = "%";
        p.donutFooter = "Quelle: grenzüberschreitendes Abwicklungssystem";
        p.donutNames = new String[]{"Yuan (CNY)", "US-Dollar (USD)", "Euro (EUR)", "Yen (JPY)", "Rubel (RUB)", "Dirham (AED)"};
        p.lineTitle = "Monatliches Volumen des grenzüberschreitenden Geschäfts, Jan.-Okt. 2026";
        p.lineSub = "Abwicklung / Handelsfinanzierung / Devisenhandel";
        p.lineYAxis = "100 Mio. Yuan";
        p.lineFooter = "Quelle: globale Geschäftsüberwachungsplattform | Einheit: 100 Mio. Yuan";
        p.lineMonths = new String[]{"Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt"};
        p.lineNames = new String[]{"Abwicklung", "Handelsfinanzierung", "Devisenhandel"};
        // 视觉主题「钢灰严谨·德式网格式」
        p.donutPalette = new Color[]{
                new Color(55, 71, 79), new Color(201, 151, 0), new Color(107, 129, 137),
                new Color(163, 178, 182), new Color(211, 198, 154), new Color(232, 232, 228)
        };
        p.lineColors = new Color[]{new Color(55, 71, 79), new Color(201, 151, 0), new Color(107, 129, 137)};
        p.chartArea = new Color(240, 242, 241);
        p.barColor = "#37474F";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：德语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple192() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/german.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

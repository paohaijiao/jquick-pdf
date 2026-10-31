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
 * 单据 156（系列）· i18n 序号 191 —— 法语版全球经营年报摘要
 *
 * <p>语种 fr：法语。字体 Noto Sans，视觉主题「雾蓝优雅·细线式（#34597E，斜体章节标题 + 仅横向细线表格）」。</p>
 *
 * <p>模板：i18n/global_bank_report_fr.txt
 * 输出：D://test//i18n//global_bank_report_fr.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/french.txt} → {@code D://test//i18n//french.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n191FrenchTest {

    /**
     * 主产物：法语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n191() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("fr", "Noto Sans");
        p.reportDate = "26 octobre 2026";
        p.orgName = "Banque Union Mondiale, siège du groupe";
        p.dataScope = "Trois premiers trimestres 2026, base consolidée";
        p.totalAssets = "8 600 milliards de yuans";
        p.crossBorderSettlement = "1 240 milliards de yuans";
        p.globalBranches = "1 286 agences";
        p.countries = "68 pays et régions";
        p.revenue = "218 milliards de yuans";
        p.netProfit = "68,6 milliards de yuans";
        p.employees = "86 000 collaborateurs";
        p.customers = "96 millions de clients";
        p.preparedBy = "Bureau du groupe, cellule multilingue";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Banque Union Mondiale";
        p.barTitle = "Produit net bancaire par région";
        p.barSub = "Trois premiers trimestres 2026 (cent millions de yuans)";
        p.barSeriesName = "Produit net bancaire";
        p.barRegions = new String[]{"Chine continentale", "Asie-Pacifique (hors Chine)", "Europe", "Moyen-Orient", "Japon", "Russie et CEI"};
        p.donutTitle = "Composition monétaire des règlements transfrontaliers";
        p.donutSub = "Part en valeur, trois premiers trimestres 2026";
        p.donutCenterTitle = "Part totale";
        p.donutCenterUnit = "%";
        p.donutFooter = "Source : système de règlement transfrontalier";
        p.donutNames = new String[]{"Yuan (CNY)", "Dollar (USD)", "Euro (EUR)", "Yen (JPY)", "Rouble (RUB)", "Dirham (AED)"};
        p.lineTitle = "Volume mensuel des opérations transfrontalières, janv.-oct. 2026";
        p.lineSub = "Règlements / Financement du commerce / Change";
        p.lineYAxis = "cent millions de yuans";
        p.lineFooter = "Source : plateforme de suivi mondial | Unité : cent millions de yuans";
        p.lineMonths = new String[]{"janv.", "févr.", "mars", "avr.", "mai", "juin", "juil.", "août", "sept.", "oct."};
        p.lineNames = new String[]{"Règlements", "Financement du commerce", "Change"};
        // 视觉主题「雾蓝优雅·细线式」
        p.donutPalette = new Color[]{
                new Color(52, 89, 126), new Color(127, 168, 201), new Color(91, 127, 163),
                new Color(169, 196, 220), new Color(201, 220, 235), new Color(228, 238, 245)
        };
        p.lineColors = new Color[]{new Color(52, 89, 126), new Color(127, 168, 201), new Color(166, 123, 79)};
        p.chartArea = new Color(242, 246, 250);
        p.barColor = "#34597E";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：法语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple191() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/french.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

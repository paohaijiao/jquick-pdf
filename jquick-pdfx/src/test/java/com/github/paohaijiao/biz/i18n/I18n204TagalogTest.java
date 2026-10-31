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
 * 单据 156（系列）· i18n 序号 204 —— 他加禄语版全球经营年报摘要
 *
 * <p>语种 tl：他加禄语。字体 Noto Sans，视觉主题「群岛·芒果式（琥珀 #D97706 + 深海蓝 #0B6E8A，双色块抬头 + 数字徽标章节）」。</p>
 *
 * <p>模板：i18n/global_bank_report_tl.txt
 * 输出：D://test//i18n//global_bank_report_tl.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/tagalog.txt} → {@code D://test//i18n//tagalog.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n204TagalogTest {

    /**
     * 主产物：他加禄语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n204() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("tl", "Noto Sans");
        p.reportDate = "Oktubre 26, 2026";
        p.orgName = "Sekretaryat ng Huitong Global Bank Group";
        p.dataScope = "Naipon hanggang Q3 2026, pinagsamang base";
        p.totalAssets = "8.6 trilyong RMB";
        p.crossBorderSettlement = "1.24 trilyong RMB";
        p.globalBranches = "1,286 sangay";
        p.countries = "68 bansa";
        p.revenue = "218 bilyong RMB";
        p.netProfit = "68.6 bilyong RMB";
        p.employees = "86,000";
        p.customers = "96 milyon";
        p.preparedBy = "Multilingguwal na task force ng sekretaryat";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Huitong Global Bank";
        p.barTitle = "Kita sa operasyon ayon sa rehiyon";
        p.barSub = "Naipon hanggang Q3 2026 (100 milyong RMB)";
        p.barSeriesName = "Kita sa operasyon";
        p.barRegions = new String[]{"China", "Asia-Pac.", "Europa", "Mid. East", "Hapon", "Rusya/CEI"};
        p.donutTitle = "Komposisyon ng salapi sa cross-border settlement";
        p.donutSub = "Ayon sa halagang naisaayos, naipon hanggang Q3 2026";
        p.donutCenterTitle = "Bahagdan";
        p.donutCenterUnit = "%";
        p.donutFooter = "Pinagmulan: cross-border settlement system";
        p.donutNames = new String[]{"Yuan CNY", "Dolyar USD", "Euro EUR", "Yen JPY", "Ruble RUB", "Dirham AED"};
        p.lineTitle = "Buwanang takbo ng cross-border volume, Ene–Okt 2026";
        p.lineSub = "Cross-border settlement / trade finance / foreign exchange";
        p.lineYAxis = "Halaga (100 milyong RMB)";
        p.lineFooter = "Pinagmulan: global operations platform | yunit: 100 milyong RMB";
        p.lineMonths = new String[]{"Ene", "Peb", "Mar", "Abr", "May", "Hun", "Hul", "Ago", "Set", "Okt"};
        p.lineNames = new String[]{"Cross-border settlement", "Trade finance", "Foreign exchange"};
        p.donutPalette = new Color[]{
                new Color(217, 119, 6), new Color(154, 77, 2), new Color(11, 110, 138),
                new Color(238, 168, 90), new Color(245, 205, 158), new Color(254, 241, 224)
        };
        p.lineColors = new Color[]{new Color(217, 119, 6), new Color(11, 110, 138), new Color(154, 77, 2)};
        p.chartArea = new Color(254, 249, 240);
        p.barColor = "#D97706";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：他加禄语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple204() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/tagalog.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

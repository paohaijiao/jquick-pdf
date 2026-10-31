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
 * 单据 156（系列）· i18n 序号 197 —— 马来语版全球经营年报摘要
 *
 * <p>语种 ms：马来语。字体 Noto Sans，视觉主题「蜡染·热带赭（#C2410C + #1F3B2C，双框通栏 + 单行 8 格卡 + 橙色斑马纹）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ms.txt
 * 输出：D://test//i18n//global_bank_report_ms.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/malay.txt} → {@code D://test//i18n//malay.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n197MalayTest {

    /**
     * 主产物：马来语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n197() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ms", "Noto Sans");
        p.reportDate = "26 Oktober 2026";
        p.orgName = "Bank Kesatuan Global, Pejabat Kumpulan";
        p.dataScope = "Tiga suku pertama 2026, disatukan kumpulan";
        p.totalAssets = "RMB 8.6T";
        p.crossBorderSettlement = "RMB 1.24T";
        p.globalBranches = "1,286";
        p.countries = "68";
        p.revenue = "RMB 218B";
        p.netProfit = "RMB 68.6B";
        p.employees = "86,000";
        p.customers = "96 jt";
        p.preparedBy = "Pejabat Kumpulan, Pasukan Pelbagai Bahasa";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Bank Kesatuan Global";
        p.barTitle = "Hasil mengikut wilayah";
        p.barSub = "Tiga suku pertama 2026 (unit: 100 juta yuan)";
        p.barSeriesName = "Hasil";
        p.barRegions = new String[]{"China", "Asia Pas.", "Eropah", "Timur Tengah", "Jepun", "Rusia/CIS"};
        p.donutTitle = "Struktur mata wang penyelesaian rentas sempadan";
        p.donutSub = "Mengikut nilai penyelesaian, tiga suku pertama 2026";
        p.donutCenterTitle = "Bahagian";
        p.donutCenterUnit = "%";
        p.donutFooter = "Sumber: sistem penyelesaian rentas sempadan";
        p.donutNames = new String[]{"Yuan CNY", "Dolar USD", "Euro EUR", "Yen JPY", "Ruble RUB", "Dirham AED"};
        p.lineTitle = "Jumlah urus niaga rentas sempadan bulanan, Januari-Oktober 2026";
        p.lineSub = "Penyelesaian / Pembiayaan perdagangan / Pertukaran asing";
        p.lineYAxis = "Nilai (juta yuan)";
        p.lineFooter = "Sumber: platform pemantauan perniagaan global | Unit: juta yuan";
        p.lineMonths = new String[]{"Jan", "Feb", "Mac", "Apr", "Mei", "Jun", "Jul", "Ogo", "Sep", "Okt"};
        p.lineNames = new String[]{"Penyelesaian rentas sempadan", "Pembiayaan perdagangan", "Pertukaran asing"};
        p.donutPalette = new Color[]{
                new Color(194, 65, 12), new Color(31, 59, 44), new Color(224, 130, 70),
                new Color(236, 170, 120), new Color(245, 205, 170), new Color(253, 241, 224)
        };
        p.lineColors = new Color[]{new Color(194, 65, 12), new Color(31, 59, 44), new Color(224, 130, 70)};
        p.chartArea = new Color(253, 241, 224);
        p.barColor = "#C2410C";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：马来语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple197() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/malay.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

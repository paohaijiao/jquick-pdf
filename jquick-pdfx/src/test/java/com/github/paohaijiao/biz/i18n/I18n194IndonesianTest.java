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
 * 单据 156（系列）· i18n 序号 194 —— 印尼语版全球经营年报摘要
 *
 * <p>语种 id：印尼语。字体 Noto Sans，视觉主题「巴迪·靛紫（#6B2F6B + #C9A227，全幅细框 + 居中底纹章节）」。</p>
 *
 * <p>模板：i18n/global_bank_report_id.txt
 * 输出：D://test//i18n//global_bank_report_id.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/indonesian.txt} → {@code D://test//i18n//indonesian.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n194IndonesianTest {

    /**
     * 主产物：印尼语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n194() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("id", "Noto Sans");
        p.reportDate = "26 Oktober 2026";
        p.orgName = "Bank Persatuan Global, Kantor Grup";
        p.dataScope = "Tiga kuartal pertama 2026, konsolidasi grup";
        p.totalAssets = "RMB 8,6 triliun";
        p.crossBorderSettlement = "RMB 1,24 triliun";
        p.globalBranches = "1.286 cabang";
        p.countries = "68 negara";
        p.revenue = "RMB 218 miliar";
        p.netProfit = "RMB 68,6 miliar";
        p.employees = "86.000 karyawan";
        p.customers = "96 juta nasabah";
        p.preparedBy = "Kantor Grup, Tim Multibahasa";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Bank Persatuan Global";
        p.barTitle = "Pendapatan menurut wilayah";
        p.barSub = "Tiga kuartal pertama 2026 (satuan: 100 juta yuan)";
        p.barSeriesName = "Pendapatan";
        p.barRegions = new String[]{"Tiongkok", "Asia Pas.", "Eropa", "Timur Tengah", "Jepang", "Rusia/CIS"};
        p.donutTitle = "Komposisi mata uang penyelesaian lintas batas";
        p.donutSub = "Menurut nilai penyelesaian, tiga kuartal pertama 2026";
        p.donutCenterTitle = "Porsi";
        p.donutCenterUnit = "%";
        p.donutFooter = "Sumber: sistem penyelesaian lintas batas";
        p.donutNames = new String[]{"Yuan CNY", "Dolar USD", "Euro EUR", "Yen JPY", "Rubel RUB", "Dirham AED"};
        p.lineTitle = "Volume bisnis lintas batas bulanan, Januari-Oktober 2026";
        p.lineSub = "Penyelesaian / Pembiayaan perdagangan / Valuta asing";
        p.lineYAxis = "Nilai (juta yuan)";
        p.lineFooter = "Sumber: platform pemantauan bisnis global | Satuan: juta yuan";
        p.lineMonths = new String[]{"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt"};
        p.lineNames = new String[]{"Penyelesaian lintas batas", "Pembiayaan perdagangan", "Valuta asing"};
        p.donutPalette = new Color[]{
                new Color(107, 47, 107), new Color(201, 162, 39), new Color(150, 110, 160),
                new Color(190, 160, 200), new Color(215, 200, 230), new Color(240, 236, 248)
        };
        p.lineColors = new Color[]{new Color(107, 47, 107), new Color(201, 162, 39), new Color(150, 110, 160)};
        p.chartArea = new Color(243, 240, 250);
        p.barColor = "#6B2F6B";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：印尼语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple194() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/indonesian.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

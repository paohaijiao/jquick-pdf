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
 * 单据 156（系列）· i18n 序号 203 —— 西班牙语版全球经营年报摘要
 *
 * <p>语种 es：西班牙语。字体 Noto Sans，视觉主题「弗拉门戈·赤金（#B3123C + 墨黑，左粗竖条 + 超大标题 + 黑底 KPI）」。</p>
 *
 * <p>模板：i18n/global_bank_report_es.txt
 * 输出：D://test//i18n//global_bank_report_es.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/spanish.txt} → {@code D://test//i18n//spanish.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n203SpanishTest {

    /**
     * 主产物：西班牙语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n203() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("es", "Noto Sans");
        p.reportDate = "26 de octubre de 2026";
        p.orgName = "Banco Unión Global, Oficina del Grupo";
        p.dataScope = "Primeros tres trimestres de 2026, consolidado";
        p.totalAssets = "8,6 billones ¥";
        p.crossBorderSettlement = "1,24 billones ¥";
        p.globalBranches = "1.286";
        p.countries = "68";
        p.revenue = "218.000 M ¥";
        p.netProfit = "68.600 M ¥";
        p.employees = "86.000";
        p.customers = "96 millones";
        p.preparedBy = "Oficina del Grupo, Equipo Multilingüe";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Banco Unión Global";
        p.barTitle = "Ingresos por región";
        p.barSub = "Primeros tres trimestres de 2026 (millones de yuanes)";
        p.barSeriesName = "Ingresos";
        p.barRegions = new String[]{"China", "Asia-Pac.", "Europa", "Or. Medio", "Japón", "Rusia/CEI"};
        p.donutTitle = "Composición por divisa de la liquidación transfronteriza";
        p.donutSub = "Por valor liquidado, primeros tres trimestres de 2026";
        p.donutCenterTitle = "Peso total";
        p.donutCenterUnit = "%";
        p.donutFooter = "Fuente: sistema de liquidación transfronteriza";
        p.donutNames = new String[]{"Yuan CNY", "Dólar USD", "Euro EUR", "Yen JPY", "Rublo RUB", "Dirham AED"};
        p.lineTitle = "Volumen mensual de negocio transfronterizo, enero-octubre 2026";
        p.lineSub = "Liquidación / Financiación comercial / Divisas";
        p.lineYAxis = "Valor (millones de yuanes)";
        p.lineFooter = "Fuente: plataforma de seguimiento global | Unidad: millones de yuanes";
        p.lineMonths = new String[]{"ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sept", "oct"};
        p.lineNames = new String[]{"Liquidación transfronteriza", "Financiación comercial", "Divisas"};
        p.donutPalette = new Color[]{
                new Color(179, 18, 60), new Color(26, 26, 26), new Color(210, 90, 110),
                new Color(225, 150, 165), new Color(238, 200, 208), new Color(253, 243, 244)
        };
        p.lineColors = new Color[]{new Color(179, 18, 60), new Color(26, 26, 26), new Color(210, 90, 110)};
        p.chartArea = new Color(253, 243, 244);
        p.barColor = "#B3123C";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：西班牙语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple203() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/spanish.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

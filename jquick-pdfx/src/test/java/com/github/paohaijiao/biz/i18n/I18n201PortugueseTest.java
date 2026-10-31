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
 * 单据 156（系列）· i18n 序号 201 —— 葡萄牙语版全球经营年报摘要
 *
 * <p>语种 pt：葡萄牙语。字体 Noto Sans，视觉主题「阿兹勒赫瓷砖·海航式（#1C7ED6 + 金橙 #C8901B，瓷砖色条 + 虚线 KPI + 编号章节）」。</p>
 *
 * <p>模板：i18n/global_bank_report_pt.txt
 * 输出：D://test//i18n//global_bank_report_pt.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/portuguese.txt} → {@code D://test//i18n//portuguese.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n201PortugueseTest {

    /**
     * 主产物：葡萄牙语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n201() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("pt", "Noto Sans");
        p.reportDate = "26 de outubro de 2026";
        p.orgName = "Secretaria do Grupo Bancário Global Huitong";
        p.dataScope = "Acumulado até o terceiro trimestre de 2026, base consolidada";
        p.totalAssets = "8,6 trilhões RMB";
        p.crossBorderSettlement = "1,24 trilhão RMB";
        p.globalBranches = "1.286 agências";
        p.countries = "68 países";
        p.revenue = "218 bi RMB";
        p.netProfit = "68,6 bi RMB";
        p.employees = "86.000";
        p.customers = "96 milhões";
        p.preparedBy = "Força-tarefa multilíngue da secretaria";
        p.reviewedBy = "Wang Yanzhou";
        p.issuedBy = "Banco Global Huitong";
        p.barTitle = "Receita operacional por região";
        p.barSub = "Acumulado até o terceiro trimestre de 2026 (100 milhões RMB)";
        p.barSeriesName = "Receita operacional";
        p.barRegions = new String[]{"China", "Ásia-Pac.", "Europa", "Or. Médio", "Japão", "Rússia/CEI"};
        p.donutTitle = "Composição por moeda da liquidação transfronteiriça";
        p.donutSub = "Por valor liquidado, acumulado até o terceiro trimestre de 2026";
        p.donutCenterTitle = "Participação";
        p.donutCenterUnit = "%";
        p.donutFooter = "Fonte: sistema de liquidação transfronteiriça";
        p.donutNames = new String[]{"Iuane CNY", "Dólar USD", "Euro EUR", "Iene JPY", "Rublo RUB", "Dirham AED"};
        p.lineTitle = "Evolução mensal do volume transfronteiriço, jan.–out. 2026";
        p.lineSub = "Liquidação transfronteiriça / financiamento comercial / câmbio";
        p.lineYAxis = "Valor (100 milhões RMB)";
        p.lineFooter = "Fonte: plataforma global de monitoramento | unidade: 100 milhões RMB";
        p.lineMonths = new String[]{"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out"};
        p.lineNames = new String[]{"Liquidação transfronteiriça", "Financiamento comercial", "Câmbio"};
        p.donutPalette = new Color[]{
                new Color(28, 126, 214), new Color(11, 79, 138), new Color(200, 144, 27),
                new Color(122, 168, 220), new Color(176, 204, 236), new Color(224, 236, 248)
        };
        p.lineColors = new Color[]{new Color(28, 126, 214), new Color(200, 144, 27), new Color(11, 79, 138)};
        p.chartArea = new Color(250, 247, 240);
        p.barColor = "#1C7ED6";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：葡萄牙语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple201() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/portuguese.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

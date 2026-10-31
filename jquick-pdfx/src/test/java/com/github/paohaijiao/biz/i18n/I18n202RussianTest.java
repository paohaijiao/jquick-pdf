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
 * 单据 156（系列）· i18n 序号 202 —— 俄语版全球经营年报摘要
 *
 * <p>语种 ru：俄语。字体 Noto Sans，视觉主题「玫红庄重·对称横幅式（玫红 #A61E4D 满幅横幅，居中对称，双粗线夹标题）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ru.txt
 * 输出：D://test//i18n//global_bank_report_ru.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/russian.txt} → {@code D://test//i18n//russian.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n202RussianTest {

    /**
     * 主产物：俄语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n202() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ru", "Noto Sans");
        p.reportDate = "26 октября 2026 г.";
        p.orgName = "Глобальный Юнион Банк, офис группы";
        p.dataScope = "Первые три квартала 2026 года, консолидировано";
        p.totalAssets = "8,6 трлн юаней";
        p.crossBorderSettlement = "1,24 трлн юаней";
        p.globalBranches = "1 286 отделений";
        p.countries = "68 стран";
        p.revenue = "218 млрд юаней";
        p.netProfit = "68,6 млрд юаней";
        p.employees = "86 000 человек";
        p.customers = "96 млн клиентов";
        p.preparedBy = "Офис группы, многоязычная рабочая группа";
        p.reviewedBy = "Ван Яньчжоу";
        p.issuedBy = "Глобальный Юнион Банк";
        p.barTitle = "Операционный доход по регионам";
        p.barSub = "Первые три квартала 2026 года (млн юаней)";
        p.barSeriesName = "Операционный доход";
        p.barRegions = new String[]{"Китай", "АТР (без Китая)", "Европа", "Ближний Восток", "Япония", "Россия и СНГ"};
        p.donutTitle = "Валютная структура трансграничных расчётов";
        p.donutSub = "Доля в объёме расчётов, первые три квартала 2026 года";
        p.donutCenterTitle = "Доля расчётов";
        p.donutCenterUnit = "%";
        p.donutFooter = "Источник: система трансграничных расчётов";
        p.donutNames = new String[]{"Юань (CNY)", "Доллар (USD)", "Евро (EUR)", "Иена (JPY)", "Рубль (RUB)", "Дирхам (AED)"};
        p.lineTitle = "Ежемесячный объём трансграничных операций, янв-окт 2026";
        p.lineSub = "Расчёты / Торговое финансирование / Валютные операции";
        p.lineYAxis = "млн юаней";
        p.lineFooter = "Источник: платформа мониторинга глобального бизнеса | Ед.: млн юаней";
        p.lineMonths = new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт"};
        p.lineNames = new String[]{"Расчёты", "Торговое финансирование", "Валютные операции"};
        // 视觉主题「玫红庄重·对称横幅式」
        p.donutPalette = new Color[]{
                new Color(166, 30, 77), new Color(74, 111, 165), new Color(217, 139, 165),
                new Color(123, 153, 196), new Color(231, 195, 209), new Color(242, 224, 231)
        };
        p.lineColors = new Color[]{new Color(166, 30, 77), new Color(74, 111, 165), new Color(217, 139, 165)};
        p.chartArea = new Color(251, 239, 243);
        p.barColor = "#A61E4D";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：俄语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple202() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/russian.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

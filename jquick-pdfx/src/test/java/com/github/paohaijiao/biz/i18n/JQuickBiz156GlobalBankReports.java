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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.combol.JCircleChartData;
import com.github.paohaijiao.combol.JMultiLineChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.series.JBar;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 单据 156（系列）：环球汇通银行 · 全球经营年报摘要 —— 每种语言一份独立单语种报告。
 *
 * <p>i18n 领域改造要求：<b>不混合语言</b>——俄语的报告就是俄语的报告。本 Demo 按
 * 7 种语言各维护一套「语言包」（元数据 + KPI + 三张 ECharts 图表的全部文案），
 * 循环生成 7 份互不掺杂外语的独立 PDF：</p>
 * <ul>
 *   <li>zh —— 简体中文版（SimHei）</li>
 *   <li>en —— English（Noto Sans）</li>
 *   <li>ru —— Русский（Noto Sans，西里尔字形）</li>
 *   <li>fr —— Français（Noto Sans，重音字母）</li>
 *   <li>de —— Deutsch（Noto Sans，Umlaut/ß）</li>
 *   <li>ja —— 日本語（Noto Sans JP）</li>
 *   <li>ar —— العربية（Noto Sans Arabic，全 RTL：baseDirection:rtl + textAlignment:right）</li>
 * </ul>
 *
 * <p>版式：每种语言一份<b>独立视觉主题</b>（布局、配色、边框语言互不相同）：</p>
 * <ul>
 *   <li>zh —— 黛紫鎏银·圆角卡片式（主色 #52307C，borderRadius 圆角卡片，居中）</li>
 *   <li>en —— Graphite Swiss 石墨极简式（墨黑 #232323 + 信号橙 #E8590C，左对齐，无边框细线表）</li>
 *   <li>ru —— 玫红庄重·对称横幅式（玫红 #A61E4D 满幅横幅，居中对称，双粗线夹标题）</li>
 *   <li>fr —— 雾蓝优雅·细线式（雾蓝 #34597E，斜体章节标题 + 仅横向细线表格）</li>
 *   <li>de —— 钢灰严谨·德式网格式（钢灰 #37474F + 芥末黄 #C99700，满格深线网格，左对齐）</li>
 *   <li>ja —— 藍染朱印·和风余白式（藍 #2B4570 + 朱印 #C73E3A，顶部压线卡，■ 章节）</li>
 *   <li>ar —— 沙漠琥珀·RTL奢华式（深棕 #5C4326 + 琥珀金 #B9861C，RTL 右竖条）</li>
 *   <li>zhHant/manchu/mn/ug/bo —— 朱砂礼仪式 / 玄青档案框式 / 草原翠留白式 / 翡翠铜右起式 / 绛红藏式横幅</li>
 * </ul>
 * <p>三张 ECharts 图表的配色（柱色 / 环形色盘 / 折线色 / 图区底色）随语言包逐一覆盖，
 * 与模板主题一一对应。</p>
 *
 * <p>模板：{@code i18n/global_bank_report_<lang>.txt}（7 份单语种模板）；
 * 输出：{@code D:/test/i18n/global_bank_report_<lang>.pdf}（7 份单语种 PDF）。</p>
 *
 * @author Martin
 * @version 1.1.0
 */
public class JQuickBiz156GlobalBankReports {

    private static final String PDF_DIR = "D:/test/i18n/";
    private static final String TEMPLATE_PREFIX = "i18n/global_bank_report_";
    private static final String OUT_PREFIX = PDF_DIR + "global_bank_report_";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        generate(zhPack());
        generate(enPack());
        generate(ruPack());
        generate(frPack());
        generate(dePack());
        generate(jaPack());
        generate(arPack());
        generate(zhHantPack());
        generate(manchuPack());
        generate(mnPack());
        generate(ugPack());
        generate(boPack());
    }

    // ==================================================================
    // 渲染：一种语言一份配置、一份模板、一份 PDF
    // ==================================================================
    private static void generate(LanguagePack p) throws IOException {
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        config.getFontConfig().setDefaultFontName(p.defaultFont);
        // 四套字体统一注册，模板内按 fontFamilyNames 回退链显式声明
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        config.getFontConfig().registerFont("Noto Sans", "fonts/NotoSans-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans TC", "fonts/NotoSansTC-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
        config.getFontConfig().registerFont("Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", p.reportNo);
        data.put("reportDate", p.reportDate);
        data.put("orgName", p.orgName);
        data.put("dataScope", p.dataScope);
        data.put("totalAssets", p.totalAssets);
        data.put("crossBorderSettlement", p.crossBorderSettlement);
        data.put("globalBranches", p.globalBranches);
        data.put("countries", p.countries);
        data.put("revenue", p.revenue);
        data.put("netProfit", p.netProfit);
        data.put("employees", p.employees);
        data.put("customers", p.customers);
        data.put("preparedBy", p.preparedBy);
        data.put("reviewedBy", p.reviewedBy);
        data.put("issuedBy", p.issuedBy);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        factory.bind("svgBar", renderBar(p));
        factory.bind("svgDonut", renderDonut(p));
        factory.bind("svgLine", renderLine(p));
        byte[] pdf = factory.executeResource(TEMPLATE_PREFIX + p.lang + ".txt");
        File out = new File(OUT_PREFIX + p.lang + ".pdf");
        Files.write(out.toPath(), pdf);
        System.out.println("已生成：" + out.getPath() + "（" + pdf.length + " 字节）");
    }

    // ==================================================================
    // 图表 1：柱状图 —— 全球分区域营业收入
    // ==================================================================
    private static String renderBar(LanguagePack p) {
        JOption option = new JOption();
        option.title().text(p.barTitle).subtext(p.barSub);
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data(p.barRegions);
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JBar bar = new JBar();
        bar.name(p.barSeriesName).data(986, 412, 268, 186, 174, 154);
        bar.itemStyle().setColor(p.barColor);
        option.series(bar);
        return JChartRendererFactory.renderChart(JChartType.BAR, option);
    }

    // ==================================================================
    // 图表 2：环形图 —— 跨境结算币种构成（扇形值按占比权重，合计 100）
    // ==================================================================
    private static String renderDonut(LanguagePack p) {
        JCircleChartData donut = new JCircleChartData();
        donut.setTitleText(p.donutTitle);
        donut.setSubtitleText(p.donutSub);
        donut.setCenterTitle(p.donutCenterTitle);
        donut.setCenterUnit(p.donutCenterUnit);
        donut.setFooterText(p.donutFooter);
        int[] weights = {38, 24, 15, 9, 7, 7};
        List<JCircleChartData.SectorData> sectors = new ArrayList<JCircleChartData.SectorData>();
        for (int i = 0; i < weights.length; i++) {
            sectors.add(new JCircleChartData.SectorData(p.donutNames[i], weights[i], p.donutPalette[i]));
        }
        donut.setSectorDataList(sectors);
        JOption option = new JOption();
        option.setData(donut);
        return JChartRendererFactory.renderChart(JChartType.Circle, option);
    }

    // ==================================================================
    // 图表 3：多重折线图 —— 1-10 月跨境业务量趋势（三条业务线）
    // ==================================================================
    private static String renderLine(LanguagePack p) {
        JMultiLineChartData lineData = new JMultiLineChartData();
        lineData.setXAxisLabels(Arrays.asList(p.lineMonths));
        lineData.setTitleText(p.lineTitle);
        lineData.setSubtitleText(p.lineSub);
        lineData.setYAxisTitle(p.lineYAxis);
        lineData.setFooterText(p.lineFooter);
        lineData.setGridCount(6);
        lineData.setShowDataLabels(false);
        lineData.setShowInnerPoint(true);
        lineData.setPointRadius(5);
        lineData.setInnerPointRadius(2);
        lineData.setChartAreaColor(p.chartArea);
        lineData.setAxisColor(Color.BLACK);
        lineData.setGridColor(new Color(220, 220, 220));
        lineData.setTextColor(Color.BLACK);
        lineData.setFooterColor(new Color(128, 128, 128));
        lineData.setValueWithPercent(false);
        lineData.setAutoCalculateMax(true);
        lineData.setRotateXAxisLabels(false);

        double[][] values = {
                {860, 902, 938, 971, 1005, 1042, 1088, 1121, 1156, 1193},
                {318, 296, 342, 365, 388, 402, 431, 447, 470, 496},
                {540, 512, 586, 561, 603, 624, 611, 648, 672, 655}
        };
        Color[] lineColors = p.lineColors;
        List<JMultiLineChartData.LineData> lines = new ArrayList<JMultiLineChartData.LineData>();
        for (int i = 0; i < p.lineNames.length; i++) {
            JMultiLineChartData.LineData line = new JMultiLineChartData.LineData();
            line.setName(p.lineNames[i]);
            line.setLegendText(p.lineNames[i]);
            line.setLineColor(lineColors[i]);
            line.setLineWidth(2.5f);
            Double[] boxed = new Double[values[i].length];
            for (int j = 0; j < values[i].length; j++) {
                boxed[j] = values[i][j];
            }
            line.setValues(Arrays.asList(boxed));
            lines.add(line);
        }
        lineData.setLineDataList(lines);
        // 设置完折线数据后必须刷新 Y 轴最大值
        lineData.updateMaxValues();

        JOption option = new JOption();
        JTitle title = new JTitle();
        title.setText(p.lineTitle);
        title.setSubtext(p.lineSub);
        option.setTitle(title);
        option.setData(lineData);
        return JChartRendererFactory.renderChart(JChartType.MultipleLine, option);
    }

    // ==================================================================
    // 语言包：每种语言一份完整文案（单语种，不掺杂外语）
    // ==================================================================
    private static class LanguagePack {
        String lang;
        String defaultFont;
        // 报告元数据
        String reportNo;
        String reportDate;
        String orgName;
        String dataScope;
        // KPI
        String totalAssets;
        String crossBorderSettlement;
        String globalBranches;
        String countries;
        String revenue;
        String netProfit;
        String employees;
        String customers;
        // 落款
        String preparedBy;
        String reviewedBy;
        String issuedBy;
        // 图表 1（柱状图）
        String barTitle;
        String barSub;
        String barSeriesName;
        String[] barRegions;
        // 图表 2（环形图）
        String donutTitle;
        String donutSub;
        String donutCenterTitle;
        String donutCenterUnit;
        String donutFooter;
        String[] donutNames;
        // 图表 3（多重折线图）
        String lineTitle;
        String lineSub;
        String lineYAxis;
        String lineFooter;
        String[] lineMonths;
        String[] lineNames;
        // 每种语言的独立视觉主题（图表配色随模板主题走）
        String barColor;
        Color[] donutPalette;
        Color[] lineColors;
        Color chartArea;
    }

    private static LanguagePack base(String lang, String defaultFont) {
        LanguagePack p = new LanguagePack();
        p.lang = lang;
        p.defaultFont = defaultFont;
        p.reportNo = "GUB-I18N-2026-0156-" + lang.toUpperCase();
        // 图表配色兜底：12 个语种均在自己的语言包里显式覆盖为各自的视觉主题色
        p.donutPalette = new Color[]{
                new Color(11, 60, 93), new Color(29, 111, 165), new Color(70, 143, 191),
                new Color(126, 175, 209), new Color(175, 203, 224), new Color(212, 226, 238)
        };
        p.lineColors = new Color[]{new Color(11, 60, 93), new Color(184, 134, 11), new Color(29, 111, 165)};
        p.chartArea = new Color(248, 249, 250);
        p.barColor = "#4169E1";
        return p;
    }

    private static LanguagePack zhPack() {
        LanguagePack p = base("zh", "SimHei");
        p.reportDate = "2026年10月26日";
        p.orgName = "环球汇通银行 集团办公室";
        p.dataScope = "2026 年前三季度合并口径";
        p.totalAssets = "8.6 万亿元人民币";
        p.crossBorderSettlement = "1.24 万亿元人民币";
        p.globalBranches = "1,286 家";
        p.countries = "68 个";
        p.revenue = "2,180 亿元";
        p.netProfit = "686 亿元";
        p.employees = "86,000 人";
        p.customers = "9,600 万户";
        p.preparedBy = "集团办公室 多语言工作组";
        p.reviewedBy = "王砚舟";
        p.issuedBy = "环球汇通银行";
        p.barTitle = "全球分区域营业收入";
        p.barSub = "2026 年前三季度（单位：亿元人民币）";
        p.barSeriesName = "营业收入";
        p.barRegions = new String[]{"中国内地", "亚太(除中国)", "欧洲", "中东", "日本", "俄语区"};
        p.donutTitle = "跨境结算币种构成";
        p.donutSub = "2026 年前三季度按结算金额占比";
        p.donutCenterTitle = "结算占比";
        p.donutCenterUnit = "%";
        p.donutFooter = "数据来源：跨境结算系统";
        p.donutNames = new String[]{"人民币 CNY", "美元 USD", "欧元 EUR", "日元 JPY", "卢布 RUB", "迪拉姆 AED"};
        p.lineTitle = "2026 年 1-10 月跨境业务量月度趋势";
        p.lineSub = "跨境结算量 / 贸易融资投放 / 外汇交易量";
        p.lineYAxis = "金额（亿元）";
        p.lineFooter = "数据来源：全球业务监控平台 | 单位：亿元";
        p.lineMonths = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月"};
        p.lineNames = new String[]{"跨境结算量", "贸易融资投放", "外汇交易量"};
        // 视觉主题「黛紫鎏银·圆角卡片式」
        p.donutPalette = new Color[]{
                new Color(82, 48, 124), new Color(142, 124, 195), new Color(106, 90, 160),
                new Color(169, 155, 214), new Color(201, 192, 230), new Color(228, 223, 242)
        };
        p.lineColors = new Color[]{new Color(82, 48, 124), new Color(142, 124, 195), new Color(201, 192, 230)};
        p.chartArea = new Color(245, 242, 250);
        p.barColor = "#52307C";
        return p;
    }

    private static LanguagePack enPack() {
        LanguagePack p = base("en", "Noto Sans");
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
        return p;
    }

    private static LanguagePack ruPack() {
        LanguagePack p = base("ru", "Noto Sans");
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
        return p;
    }

    private static LanguagePack frPack() {
        LanguagePack p = base("fr", "Noto Sans");
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
        return p;
    }

    private static LanguagePack dePack() {
        LanguagePack p = base("de", "Noto Sans");
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
        return p;
    }

    private static LanguagePack jaPack() {
        LanguagePack p = base("ja", "Noto Sans JP");
        p.reportDate = "2026年10月26日";
        p.orgName = "グローバルユニオン銀行 グループ事務局";
        p.dataScope = "2026年第1～3四半期 グループ連結ベース";
        p.totalAssets = "8.6兆人民元";
        p.crossBorderSettlement = "1.24兆人民元";
        p.globalBranches = "1,286店";
        p.countries = "68カ国・地域";
        p.revenue = "2,180億元";
        p.netProfit = "686億元";
        p.employees = "86,000人";
        p.customers = "9,600万顧客";
        p.preparedBy = "グループ事務局 多言語ワーキンググループ";
        p.reviewedBy = "王硯舟";
        p.issuedBy = "グローバルユニオン銀行";
        p.barTitle = "地域別営業収入";
        p.barSub = "2026年第1～3四半期（単位：億元）";
        p.barSeriesName = "営業収入";
        p.barRegions = new String[]{"中国本土", "アジア太平洋(中国除く)", "欧州", "中東", "日本", "ロシア・CIS"};
        p.donutTitle = "越境決済の通貨構成";
        p.donutSub = "2026年第1～3四半期の決済額比率";
        p.donutCenterTitle = "決済比率";
        p.donutCenterUnit = "%";
        p.donutFooter = "出所：越境決済システム";
        p.donutNames = new String[]{"人民元 (CNY)", "米ドル (USD)", "ユーロ (EUR)", "日本円 (JPY)", "ルーブル (RUB)", "ディルハム (AED)"};
        p.lineTitle = "2026年1～10月 越境業務量の月次推移";
        p.lineSub = "越境決済量 / 貿易金融 / 外国為替";
        p.lineYAxis = "金額（億元）";
        p.lineFooter = "出所：グローバル業務監視プラットフォーム | 単位：億元";
        p.lineMonths = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月"};
        p.lineNames = new String[]{"越境決済量", "貿易金融", "外国為替"};
        // 视觉主题「藍染朱印·和风余白式」
        p.donutPalette = new Color[]{
                new Color(43, 69, 112), new Color(199, 62, 58), new Color(122, 147, 184),
                new Color(217, 138, 128), new Color(185, 198, 220), new Color(230, 225, 214)
        };
        p.lineColors = new Color[]{new Color(43, 69, 112), new Color(199, 62, 58), new Color(122, 147, 184)};
        p.chartArea = new Color(247, 246, 242);
        p.barColor = "#2B4570";
        return p;
    }

    private static LanguagePack arPack() {
        LanguagePack p = base("ar", "Noto Sans Arabic");
        p.reportDate = "26 أكتوبر 2026";
        p.orgName = "البنك العالمي الموحد - مكتب المجموعة";
        p.dataScope = "الأشهر التسعة الأولى من 2026 على أساس المجموعة الموحدة";
        p.totalAssets = "8.6 تريليون يوان";
        p.crossBorderSettlement = "1.24 تريليون يوان";
        p.globalBranches = "1286 فرعا";
        p.countries = "68 دولة ومنطقة";
        p.revenue = "218 مليار يوان";
        p.netProfit = "68.6 مليار يوان";
        p.employees = "86000 موظف";
        p.customers = "96 مليون عميل";
        p.preparedBy = "مكتب المجموعة، فريق العمل متعدد اللغات";
        p.reviewedBy = "وانغ يانتشو";
        p.issuedBy = "البنك العالمي الموحد";
        p.barTitle = "الدخل التشغيلي حسب المناطق";
        p.barSub = "الأشهر التسعة الأولى من 2026 (مئة مليون يوان)";
        p.barSeriesName = "الدخل التشغيلي";
        p.barRegions = new String[]{"الصين الرئيسية", "آسيا والهادي (دون الصين)", "أوروبا", "الشرق الأوسط", "اليابان", "روسيا والكومنولث"};
        p.donutTitle = "التركيبة العملية للتسوية عبر الحدود";
        p.donutSub = "حصة من حجم التسوية، الأشهر التسعة الأولى من 2026";
        p.donutCenterTitle = "حصة التسوية";
        p.donutCenterUnit = "%";
        p.donutFooter = "المصدر: نظام التسوية عبر الحدود";
        p.donutNames = new String[]{"اليوان (CNY)", "الدولار (USD)", "اليورو (EUR)", "الين (JPY)", "الروبل (RUB)", "الدرهم (AED)"};
        p.lineTitle = "الحجم الشهري للأعمال عبر الحدود، يناير-أكتوبر 2026";
        p.lineSub = "التسوية / تمويل التجارة / الصرف الأجنبي";
        p.lineYAxis = "مئة مليون يوان";
        p.lineFooter = "المصدر: منصة مراقبة الأعمال العالمية | الوحدة: مئة مليون يوان";
        p.lineMonths = new String[]{"يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر", "أكتوبر"};
        p.lineNames = new String[]{"التسوية", "تمويل التجارة", "الصرف الأجنبي"};
        // 视觉主题「沙漠琥珀·RTL奢华式」
        p.donutPalette = new Color[]{
                new Color(92, 67, 38), new Color(201, 154, 61), new Color(143, 110, 63),
                new Color(217, 185, 120), new Color(232, 213, 174), new Color(244, 234, 213)
        };
        p.lineColors = new Color[]{new Color(92, 67, 38), new Color(201, 154, 61), new Color(143, 110, 63)};
        p.chartArea = new Color(251, 245, 233);
        p.barColor = "#5C4326";
        return p;
    }

    // ==================================================================
    // 繁體中文 —— 视觉主题「朱砂鎏金·礼仪式」（Noto Sans TC）
    // ==================================================================
    private static LanguagePack zhHantPack() {
        LanguagePack p = base("zhHant", "Noto Sans TC");
        p.reportDate = "2026年10月26日";
        p.orgName = "環球匯通銀行 集團辦公室";
        p.dataScope = "2026 年前三季度集團合併口徑";
        p.totalAssets = "8.6 萬億元人民幣";
        p.crossBorderSettlement = "1.24 萬億元人民幣";
        p.globalBranches = "1,286 家";
        p.countries = "68 個";
        p.revenue = "2,180 億元";
        p.netProfit = "686 億元";
        p.employees = "86,000 人";
        p.customers = "9,600 萬戶";
        p.preparedBy = "集團辦公室 多語言工作組";
        p.reviewedBy = "王硯舟";
        p.issuedBy = "環球匯通銀行";
        p.barTitle = "全球分區域營業收入";
        p.barSub = "2026 年前三季度（單位：億元人民幣）";
        p.barSeriesName = "營業收入";
        p.barRegions = new String[]{"中國內地", "亞太(除中國)", "歐洲", "中東", "日本", "俄語區"};
        p.donutTitle = "跨境結算幣別構成";
        p.donutSub = "2026 年前三季度按結算金額占比";
        p.donutCenterTitle = "結算占比";
        p.donutCenterUnit = "%";
        p.donutFooter = "資料來源：跨境結算系統";
        p.donutNames = new String[]{"人民幣", "美元", "歐元", "日元", "盧布", "迪拉姆"};
        p.lineTitle = "2026 年 1-10 月跨境業務量月度趨勢";
        p.lineSub = "跨境結算量 / 貿易融資投放 / 外匯交易量";
        p.lineYAxis = "金額（億元）";
        p.lineFooter = "資料來源：全球業務監控平台 | 單位：億元";
        p.lineMonths = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月"};
        p.lineNames = new String[]{"跨境結算量", "貿易融資投放", "外匯交易量"};
        p.donutPalette = new Color[]{
                new Color(158, 43, 37), new Color(201, 162, 39), new Color(217, 116, 92),
                new Color(228, 170, 110), new Color(238, 203, 152), new Color(246, 232, 201)
        };
        p.lineColors = new Color[]{new Color(158, 43, 37), new Color(201, 162, 39), new Color(217, 116, 92)};
        p.chartArea = new Color(253, 248, 239);
        p.barColor = "#9E2B25";
        return p;
    }

    // ==================================================================
    // 满文 —— 视觉主题「玄青鎏铜·档案框式」（Noto Sans Mongolian，收尾竖排）
    // ==================================================================
    private static LanguagePack manchuPack() {
        LanguagePack p = base("manchu", "Noto Sans Mongolian");
        p.reportDate = "2026 ᠠᠨᡳᠶᠠ 10 ᠪᡳᠶᠠ 26 ᡳᠨᠡᠨᡤᡤᡳ";
        p.orgName = "ᠠᠮᠪᠠ ᠮᡝᠨᡤᡤᡠᠨ ᠪᠣᠣ";
        p.dataScope = "2026 ᠠᠨᡳᠶᠠ ᡠᠶᡠᠨ ᠪᡳᠶᠠ";
        p.totalAssets = "8.6 ᡨᡠᠮᠡᠨ ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.crossBorderSettlement = "1.24 ᡨᡠᠮᠡᠨ ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.globalBranches = "1,286 ᠪᠣᠣ";
        p.countries = "68 ᡤᡠᠷᡠᠨ ᠪᠠ";
        p.revenue = "2,180 ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.netProfit = "686 ᡨᡠᠮᡝᠨ ᡨᡠᠮᡝᠨ ᠶᠠᠨ";
        p.employees = "86,000 ᠨᡳᠶᠠᠯᠮᠠ";
        p.customers = "9,600 ᡨᡠᠮᡝᠨ ᡳᠷᡤᡝᠨ";
        p.preparedBy = "ᠠᠷᠠᠷᠠ";
        p.reviewedBy = "ᠠᡴᡩᠠᡵᠠ";
        p.issuedBy = "ᠠᠯᡤᡳᠪᡠᡵᡠ";
        p.barTitle = "ᡠᠵᠡᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᠪᠠᡩᠠᠷᠠᠨ";
        p.barSub = "2026 ᠠᠨᡳᠶᠠ 10 ᠪᡳᠶᠠ 26 ᡳᠨᠡᠨᡤᡤᡳ";
        p.barSeriesName = "ᡴᠠᠯᡳᠴᡠᠨ ᠮᠡᠨᡤᡤᡠᠨ";
        p.barRegions = new String[]{"ᠨᡳᡴᠠᠨ", "ᡨᡠᠯᠡᠷᡤᡳ ᠵᡝᠴᡝᠨ", "ᡶᠠᠯᠠᠨᠰᡝ ᡤᡠᠷᡠᠨ", "ᠣᠷᡠᠰᠠ ᡤᡠᠷᡠᠨ", "ᠨᡳᠶᠠᠨ ᡤᡠᠷᡠᠨ", "ᡳᠨᡤᡤᡳᠷᡳ ᡤᡠᠷᡠᠨ"};
        p.donutTitle = "ᠪᠠ ᡳ ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutSub = "2026 ᠠᠨᡳᠶᠠ ᡠᠶᡠᠨ ᠪᡳᠶᠠ";
        p.donutCenterTitle = "ᠰᠠᡳᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᠪᠠ ᡳ ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.donutNames = new String[]{"ᠶᠠᠨ", "ᡩᠣᠯᠠᡵ", "ᡝᡠᠷᠣ", "ᠶᡝᠨ", "ᠯᡠᠪᠯᡠ", "ᡩᡳᠷᡥᠠᠮ"};
        p.lineTitle = "ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᡝᡵᡳᠨ";
        p.lineSub = "ᡠᠵᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᠪᠠᡩᠠᠷᠠᠨ";
        p.lineYAxis = "ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ";
        p.lineFooter = "ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ ᡳ ᡝᡵᡳᠨ";
        p.lineMonths = new String[]{"1 ᠪᡳᠶᠠ", "2 ᠪᡳᠶᠠ", "3 ᠪᡳᠶᠠ", "4 ᠪᡳᠶᠠ", "5 ᠪᡳᠶᠠ", "6 ᠪᡳᠶᠠ", "7 ᠪᡳᠶᠠ", "8 ᠪᡳᠶᠠ", "9 ᠪᡳᠶᠠ", "10 ᠪᡳᠶᠠ"};
        p.lineNames = new String[]{"ᠵᡝᠴᡝᠨ ᡴᠠᠯᡳᠴᡠᠨ", "ᡴᠠᠯᡳᠴᡠᠨ ᠮᡝᠨᡤᡤᡠᠨ", "ᡥᠠᠴᡳᠨ ᠮᡝᠨᡤᡤᡠᠨ"};
        p.donutPalette = new Color[]{
                new Color(27, 42, 74), new Color(176, 141, 87), new Color(110, 127, 158),
                new Color(154, 167, 192), new Color(197, 204, 220), new Color(225, 228, 236)
        };
        p.lineColors = new Color[]{new Color(27, 42, 74), new Color(176, 141, 87), new Color(110, 127, 158)};
        p.chartArea = new Color(244, 239, 227);
        p.barColor = "#1B2A4A";
        return p;
    }

    // ==================================================================
    // 传统蒙古文 —— 视觉主题「草原翠·开放留白式」（Noto Sans Mongolian，收尾竖排）
    // ==================================================================
    private static LanguagePack mnPack() {
        LanguagePack p = base("mn", "Noto Sans Mongolian");
        p.reportDate = "2026 ᠵᠢᠯ 10 ᠰᠠᠷᠠ 26 ᠡᠳᠦᠷ";
        p.orgName = "ᠮᠣᠨᠭᠭᠣᠯ ᠪᠠᠨᠭᠺᠢ";
        p.dataScope = "2026 ᠵᠢᠯ ᠤᠨ ᠡᠺᠢᠨ ᠭᠤᠷᠪᠠᠨ ᠰᠠᠷᠠ";
        p.totalAssets = "8.6 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.crossBorderSettlement = "1.24 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.globalBranches = "1,286 ᠰᠠᠯᠠ";
        p.countries = "68 ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ";
        p.revenue = "2,180 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.netProfit = "686 ᠲᠤᠮᡝᠨ ᠲᠤᠮᡝᠨ ᠶᠤᠠᠨ";
        p.employees = "86,000 ᠠᠵᠢᠯᠴᠢᠨ";
        p.customers = "9,600 ᠲᠤᠮᡝᠨ ᠬᠡᠷᠡᠭᠯᠡᠭᠴᠢᠳ";
        p.preparedBy = "ᠽᠣᠾᠢᠭᠴᠢ";
        p.reviewedBy = "ᠰᠢᠯᠢᠭᠴᠢᠨ";
        p.issuedBy = "ᠭᠠᠷᠭᠠᠭᠴᠢ";
        p.barTitle = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠨᡝᠢᠲᡝ ᠪᠠᠶᠢᠳᠠᠯ";
        p.barSub = "2026 ᠵᠢᠯ 10 ᠰᠠᠷᠠ 26 ᠡᠳᠦᠷ";
        p.barSeriesName = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.barRegions = new String[]{"ᠬᠢᠲᠠᠳ", "ᠠᠽᠢ", "ᠡᠤᠷᠣᠫᡝ", "ᠠᠷᠠᠪ", "ᠶᠠᠫᠣᠨ", "ᠣᠷᠤᠰ"};
        p.donutTitle = "ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ ᠤᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.donutSub = "2026 ᠵᠢᠯ ᠤᠨ ᠡᠺᠢᠨ ᠭᠤᠷᠪᠠᠨ ᠰᠠᠷᠠ";
        p.donutCenterTitle = "ᠴᠡᠪᡝᡵ ᠠᠰᠢᠭ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᠤᠯᠤᠰ ᠪᠠ ᠨᠤᠲᠠᠭ ᠤᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.donutNames = new String[]{"ᠶᠤᠠᠨ", "ᠳᠣᠯᠯᠠᡵ", "ᡝᠤᠷᠣ", "ᠶᡝᠨ", "ᠷᠤᠪᠯᠢ", "ᠳᠢᠷᠬᠠᠮ"};
        p.lineTitle = "ᠵᡝᠴᡝᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠰᠠᠷᠠ ᠤᠨ ᠬᠠᠨᠳᠢᠯ";
        p.lineSub = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠨᡝᠢᠲᡝ ᠪᠠᠶᠢᠳᠠᠯ";
        p.lineYAxis = "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ";
        p.lineFooter = "ᠵᡝᠴᡝᠨ ᠬᠤᠳᠠᠯᠳᠤᠠ ᠶᠢᠨ ᠰᠠᠷᠠ ᠤᠨ ᠬᠠᠨᠳᠢᠯ";
        p.lineMonths = new String[]{"1 ᠰᠠᠷᠠ", "2 ᠰᠠᠷᠠ", "3 ᠰᠠᠷᠠ", "4 ᠰᠠᠷᠠ", "5 ᠰᠠᠷᠠ", "6 ᠰᠠᠷᠠ", "7 ᠰᠠᠷᠠ", "8 ᠰᠠᠷᠠ", "9 ᠰᠠᠷᠠ", "10 ᠰᠠᠷᠠ"};
        p.lineNames = new String[]{"ᠳᠠᠠᠯᠠ", "ᠬᠤᠳᠠᠯᠳᠤᠠ ᠮᠥᠨᠭᠭᠥ", "ᠬᠠᠷᠢᠠ ᠮᠥᠨᠭᠭᠥ"};
        p.donutPalette = new Color[]{
                new Color(47, 107, 60), new Color(200, 162, 75), new Color(123, 160, 91),
                new Color(168, 196, 141), new Color(201, 219, 177), new Color(228, 238, 215)
        };
        p.lineColors = new Color[]{new Color(47, 107, 60), new Color(200, 162, 75), new Color(123, 160, 91)};
        p.chartArea = new Color(234, 243, 230);
        p.barColor = "#2F6B3C";
        return p;
    }

    // ==================================================================
    // 维吾尔文 —— 视觉主题「翡翠铜·右起式」（Noto Sans Arabic，全 RTL）
    // ==================================================================
    private static LanguagePack ugPack() {
        LanguagePack p = base("ug", "Noto Sans Arabic");
        p.reportDate = "2026-يىلى 10-ئاينىڭ 26-كۈنى";
        p.orgName = "يەر شارى بانكىسى گۇرۇھ ئىشخانىسى";
        p.dataScope = "2026-يىلنىڭ ئالدىنقى ئۈچ پەسلى، گۇرۇھ بىرلەشمە ئۆلچىمى";
        p.totalAssets = "8.6 تىرىليون يۈەن";
        p.crossBorderSettlement = "1.24 تىرىليون يۈەن";
        p.globalBranches = "1,286 تارماق";
        p.countries = "68 دۆلەت ۋە رايون";
        p.revenue = "218 مىليارد يۈەن";
        p.netProfit = "68.6 مىليارد يۈەن";
        p.employees = "86,000 خىزمەتچى";
        p.customers = "96 مىليون خېرىدار";
        p.preparedBy = "گۇرۇھ ئىشخانىسى كۆپ تىللىق خىزمەت گۇرۇپپىسى";
        p.reviewedBy = "ۋاڭ يەنجۇ";
        p.issuedBy = "يەر شارى بانكىسى";
        p.barTitle = "رايونلار بويىچە تىجارەت كىرىمى";
        p.barSub = "2026-يىلنىڭ ئالدىنقى ئۈچ پەسلى（بىرلىك: 100 مىليون يۈەن）";
        p.barSeriesName = "تىجارەت كىرىمى";
        p.barRegions = new String[]{"جۇڭگو ئىچكى قۇرۇقلۇقى", "ئاسىيا-تىنچ ئوكيان", "ياۋروپا", "ئوتتۇرا شەرق", "ياپونىيە", "رۇس تىل رايونى"};
        p.donutTitle = "چېگرا ھالقىغان راسچوتنىڭ پۇل تۈرى تەركىبى";
        p.donutSub = "راسچوت سوممىسى بويىچە نىسبەت";
        p.donutCenterTitle = "راسچوت نىسبىتى";
        p.donutCenterUnit = "%";
        p.donutFooter = "مەنبە: چېگرا ھالقىغان راسچوت سىستېمىسى";
        p.donutNames = new String[]{"يۈەن", "ئامېرىكا دوللىرى", "ياۋرو", "يېن", "رۇبلى", "دىرھەم"};
        p.lineTitle = "2026-يىلى 1-ئايدىن 10-ئايغىچە چېگرا ھالقىغان كەسىپ ھەجمى";
        p.lineSub = "راسچوت / سودا مۇلازىمىتى / تاشقى پېرېۋوت";
        p.lineYAxis = "100 مىليون يۈەن";
        p.lineFooter = "مەنبە: دۇنياۋى كەسىپ كۆزىتىش سۇپىسى | بىرلىك: 100 مىليون يۈەن";
        p.lineMonths = new String[]{"يانۋار", "فېۋرال", "مارت", "ئاپرېل", "ماي", "ئىيۇن", "ئىيۇل", "ئاۋغۇست", "سېنتەبىر", "ئۆكتەبىر"};
        p.lineNames = new String[]{"چېگرا ھالقىغان راسچوت", "سودا مۇلازىمىتى", "تاشقى پېرېۋوت"};
        p.donutPalette = new Color[]{
                new Color(15, 107, 92), new Color(194, 118, 43), new Color(62, 142, 126),
                new Color(116, 179, 165), new Color(171, 208, 199), new Color(216, 234, 229)
        };
        p.lineColors = new Color[]{new Color(15, 107, 92), new Color(194, 118, 43), new Color(62, 142, 126)};
        p.chartArea = new Color(233, 245, 241);
        p.barColor = "#0F6B5C";
        return p;
    }

    // ==================================================================
    // 藏文 —— 视觉主题「绛红鎏金·藏式横幅」（Noto Serif Tibetan）
    // ==================================================================
    private static LanguagePack boPack() {
        LanguagePack p = base("bo", "Noto Serif Tibetan");
        p.reportDate = "2026 ཟླ 10 ཚེས 26";
        p.orgName = "གོ་ལ་ཡོངས་དངུལ་ཁང";
        p.dataScope = "2026 ལོའི་དུས་ཚིགས་དང་པོ་གསུམ";
        p.totalAssets = "8.6 སྒོར་དུང་ཕྱུར་ཁྲི";
        p.crossBorderSettlement = "1.24 སྒོར་དུང་ཕྱུར་ཁྲི";
        p.globalBranches = "1,286 ཡན་ལག";
        p.countries = "68 རྒྱལ་ཁབ";
        p.revenue = "2,180 སྒོར་དུང་ཕྱུར";
        p.netProfit = "686 སྒོར་དུང་ཕྱུར";
        p.employees = "86,000 ལས་བྱེད";
        p.customers = "9,600 ཁྲི མགྲོན་པོ";
        p.preparedBy = "ལྷན་ཚོགས་གཞུང་ལས་ཁང";
        p.reviewedBy = "ཝང་ཡན་ཇོ།";
        p.issuedBy = "གོ་ལ་ཡོངས་དངུལ་ཁང";
        p.barTitle = "ཡུལ་ཁུལ་བཞིན་གྱི་ཡོང་འབབ";
        p.barSub = "2026 ལོའི་དུས་ཚིགས་དང་པོ་གསུམ（སྒོར་དུང་ཕྱུར）";
        p.barSeriesName = "ཡོང་འབབ";
        p.barRegions = new String[]{"ཀྲུང་གོའི་ནང་ལོགས", "ལྷོ་ཨེ་ཤེ་ཡ", "ཡོ་རོབ", "དབུས་ཤར", "རི་པིན", "ཨུ་རུ་སུ"};
        p.donutTitle = "རྒྱལ་མཚམས་རྩིས་སྤྲོད་ཀྱི་དངུལ་རིགས་བསྡུར";
        p.donutSub = "རྩིས་སྤྲོད་བསྡོམས་འབོར་བཞིན";
        p.donutCenterTitle = "བསྡུར་ཚད";
        p.donutCenterUnit = "%";
        p.donutFooter = "འབྱུང་ཁུངས། རྒྱལ་མཚམས་རྩིས་སྤྲོད་མ་ལག";
        p.donutNames = new String[]{"སྒོར", "ཨ་མེ་རི་ཀའི་སྒོར", "ཡུ་རོ", "རི་པིན་སྒོར", "ལུ་སུ", "ཌིར་ཧམ"};
        p.lineTitle = "2026 ལོའི་ཟླ་བ 1-10 ནང་རྒྱལ་མཚམས་ལས་ཀའི་འཕེལ་ཕྱོགས";
        p.lineSub = "རྩིས་སྤྲོད / ཚོང་དོན་མ་དངུལ / ཕྱིའི་དངུལ་བརྗེས";
        p.lineYAxis = "སྒོར་དུང་ཕྱུར";
        p.lineFooter = "འབྱུང་ཁུངས། གོ་ལའི་ལས་ཀ་ལྟ་ཞིབ་ལྟེ་གནས | སྒོར་དུང་ཕྱུར";
        p.lineMonths = new String[]{"ཟླ 1", "ཟླ 2", "ཟླ 3", "ཟླ 4", "ཟླ 5", "ཟླ 6", "ཟླ 7", "ཟླ 8", "ཟླ 9", "ཟླ 10"};
        p.lineNames = new String[]{"རྒྱལ་མཚམས་རྩིས་སྤྲོད", "ཚོང་དོན་མ་དངུལ", "ཕྱིའི་དངུལ་བརྗེས"};
        p.donutPalette = new Color[]{
                new Color(123, 45, 38), new Color(212, 160, 23), new Color(168, 91, 50),
                new Color(198, 139, 91), new Color(224, 185, 140), new Color(240, 220, 188)
        };
        p.lineColors = new Color[]{new Color(123, 45, 38), new Color(212, 160, 23), new Color(168, 91, 50)};
        p.chartArea = new Color(251, 243, 232);
        p.barColor = "#7B2D26";
        return p;
    }
}

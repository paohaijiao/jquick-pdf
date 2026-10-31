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
 *   <li>vi —— Tiếng Việt（Noto Sans，越南语声调附加符号）</li>
 *   <li>th —— ไทย（Noto Sans Thai）</li>
 *   <li>dai —— 傣语 / 新傣仂文（Noto Sans New Tai Lue）</li>
 *   <li>ms —— Bahasa Melayu（Noto Sans）</li>
 *   <li>id —— Bahasa Indonesia（Noto Sans）</li>
 *   <li>es —— Español（Noto Sans，重音与 ñ）</li>
 *   <li>ko —— 한국어（Noto Sans KR）</li>
 *   <li>pt —— Português（Noto Sans，波浪号/软音符）</li>
 *   <li>bn —— বাংলা（Noto Sans Bengali，连字与元音符号）</li>
 *   <li>tl —— Tagalog（Noto Sans，拉丁字母）</li>
 *   <li>fa —— فارسی（Noto Sans Arabic，RTL + 波斯语增补字母与东阿拉伯数字）</li>
 *   <li>ta —— தமிழ்（Noto Sans Tamil，辅音元音合字）</li>
 *   <li>he —— עברית（Noto Sans Hebrew，RTL 希伯来字母）</li>
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
 *   <li>vi —— 青瓷·顺化式（#0B6E7F，满幅色带 + 左竖线章节 + 满格细线表）</li>
 *   <li>th —— 金箔紫檀·泰庙式（#8A5A00 + #7A1F3D，居中金双线 + 2×2 大卡 + 仅上下金线表）</li>
 *   <li>dai —— 贝叶竹青·孔雀式（#557C1F + #8B5A00，左对齐 + 壹贰叁色块 + 发丝线表）</li>
 *   <li>ms —— 蜡染·热带赭（#C2410C + #1F3B2C，双框通栏 + 单行 8 格卡 + 橙色斑马纹）</li>
 *   <li>id —— 巴迪·靛紫（#6B2F6B + #C9A227，全幅细框 + 居中底纹章节）</li>
 *   <li>es —— 弗拉门戈·赤金（#B3123C + 墨黑，左粗竖条 + 超大标题 + 黑底 KPI）</li>
 *   <li>ko —— 丹青·韩纸（#1F3B57 + 五方色条，全框线网格 + 数字方块章节）</li>
 *   <li>pt —— 阿兹勒赫瓷砖·海航式（#1C7ED6 + 金橙 #C8901B，瓷砖色条 + 虚线 KPI + 编号章节 + 通栏线表）</li>
 *   <li>bn —— 恒河朱砂·莲花式（#B0322F + 恒河绿 #0F7B5F，左竖色块 + 圆角卡片 KPI + 居中带线章节）</li>
 *   <li>tl —— 群岛·芒果式（琥珀 #D97706 + 深海蓝 #0B6E8A，双色块抬头 + 数字徽标章节 + 下粗线 KPI）</li>
 *   <li>fa —— 波斯细密画·绿松石（紫罗兰 #5F3DC4 + 绿松石 #0E9384，居中对称装饰 + 全篇 RTL 右对齐）</li>
 *   <li>ta —— 庙塔·黄檀式（玫红 #C2185B + 深靛 #26456B，三层递减色条 + 交叉网格 KPI）</li>
 *   <li>he —— 迦南·橄榄式（橄榄翠绿 #1A7F37 + 石灰 #6B7280，右粗色条 + 全篇 RTL 右对齐 + 底线章节）</li>
 * </ul>
 * <p>三张 ECharts 图表的配色（柱色 / 环形色盘 / 折线色 / 图区底色）随语言包逐一覆盖，
 * 与模板主题一一对应。</p>
 *
 * <p>模板：{@code i18n/global_bank_report_<lang>.txt}（25 份单语种模板）；
 * 输出：{@code D:/test/i18n/global_bank_report_<lang>.pdf}（25 份单语种 PDF）。</p>
 *
 * @author Martin
 * @version 1.3.0
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
        generate(viPack());
        generate(thPack());
        generate(daiPack());
        generate(msPack());
        generate(idPack());
        generate(esPack());
        generate(koPack());
        generate(ptPack());
        generate(bnPack());
        generate(tlPack());
        generate(faPack());
        generate(taPack());
        generate(hePack());
    }

    // ==================================================================
    // 渲染：一种语言一份配置、一份模板、一份 PDF
    // ==================================================================
    private static void generate(LanguagePack p) throws IOException {
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        // 4.6.0 起目录（TOC）默认开启，且目录页固定使用默认 CJK 字体（SimHei）度量标题，
        // 非中文标题（ä / ไทย / 한국어 等）会因缺字形抛 "No glyph for ..."。
        // 本 Demo 的版式是「单页头 + 全篇表格」，没有多级标题，目录页没有意义，故关闭。
        config.getCatalogConfig().setEnabled(false);
        config.getFontConfig().setDefaultFontName(p.defaultFont);
        // 四套字体统一注册，模板内按 fontFamilyNames 回退链显式声明
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        config.getFontConfig().registerFont("Noto Sans", "fonts/NotoSans-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans TC", "fonts/NotoSansTC-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
        config.getFontConfig().registerFont("Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");
        // 4.6.0 起补充的三款字体：泰文 / 新傣仂文（傣语） / 韩文
        config.getFontConfig().registerFont("Noto Sans Thai", "fonts/NotoSansThai-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans New Tai Lue", "fonts/NotoSansNewTaiLue-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans KR", "fonts/NotoSansKR-Regular.ttf");
        // 4.6.0 起继续补充的三款字体：孟加拉文 / 泰米尔文 / 希伯来文
        // （这三款字体只含本文种字形，拉丁字母与中文由回退链后段的 Noto Sans / SimHei 逐字符兜底）
        config.getFontConfig().registerFont("Noto Sans Bengali", "fonts/NotoSansBengali.ttf");
        config.getFontConfig().registerFont("Noto Sans Tamil", "fonts/NotoSansTamil.ttf");
        config.getFontConfig().registerFont("Noto Sans Hebrew", "fonts/NotoSansHebrew.ttf");

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

    // ==================================================================
    // 越南语 —— 视觉主题「青瓷·顺化式」（满幅色带 + 左竖线章节 + 满格细线表）
    // ==================================================================
    private static LanguagePack viPack() {
        LanguagePack p = base("vi", "Noto Sans");
        p.reportDate = "26 tháng 10 năm 2026";
        p.orgName = "Ngân hàng Hội Thông Toàn cầu";
        p.dataScope = "Ba quý đầu năm 2026, hợp nhất toàn tập đoàn";
        p.totalAssets = "8,6 nghìn tỷ NDT";
        p.crossBorderSettlement = "1,24 nghìn tỷ NDT";
        p.globalBranches = "1.286 chi nhánh";
        p.countries = "68 quốc gia";
        p.revenue = "218 tỷ NDT";
        p.netProfit = "68,6 tỷ NDT";
        p.employees = "86.000 người";
        p.customers = "96 triệu khách hàng";
        p.preparedBy = "Văn phòng Tập đoàn, Tổ công tác đa ngữ";
        p.reviewedBy = "Vương Ngạn Châu";
        p.issuedBy = "Ngân hàng Hội Thông Toàn cầu";
        p.barTitle = "Doanh thu theo khu vực";
        p.barSub = "Ba quý đầu năm 2026 (đơn vị: 100 triệu NDT)";
        p.barSeriesName = "Doanh thu";
        p.barRegions = new String[]{"Trung Quốc", "Châu Á", "Châu Âu", "Trung Đông", "Nhật Bản", "Nga/CIS"};
        p.donutTitle = "Cơ cấu đồng tiền thanh toán xuyên biên giới";
        p.donutSub = "Tỷ trọng theo giá trị thanh toán, ba quý đầu năm 2026";
        p.donutCenterTitle = "Tỷ trọng";
        p.donutCenterUnit = "%";
        p.donutFooter = "Nguồn: hệ thống thanh toán xuyên biên giới";
        p.donutNames = new String[]{"Nhân dân tệ CNY", "Đô la Mỹ USD", "Euro EUR", "Yên JPY", "Rúp RUB", "Dirham AED"};
        p.lineTitle = "Khối lượng kinh doanh xuyên biên giới tháng 1-10/2026";
        p.lineSub = "Thanh toán / Tài trợ thương mại / Kinh doanh ngoại hối";
        p.lineYAxis = "Giá trị (100 triệu NDT)";
        p.lineFooter = "Nguồn: nền tảng giám sát kinh doanh toàn cầu | Đơn vị: 100 triệu NDT";
        p.lineMonths = new String[]{"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10"};
        p.lineNames = new String[]{"Thanh toán xuyên biên giới", "Tài trợ thương mại", "Kinh doanh ngoại hối"};
        p.donutPalette = new Color[]{
                new Color(11, 110, 127), new Color(62, 150, 163), new Color(110, 180, 190),
                new Color(160, 205, 212), new Color(200, 228, 232), new Color(232, 244, 245)
        };
        p.lineColors = new Color[]{new Color(11, 110, 127), new Color(192, 57, 43), new Color(62, 150, 163)};
        p.chartArea = new Color(234, 244, 244);
        p.barColor = "#0B6E7F";
        return p;
    }

    // ==================================================================
    // 泰语 —— 视觉主题「金箔紫檀·泰庙式」（居中金双线 + 2×2 大卡 + 仅上下金线表）
    // ==================================================================
    private static LanguagePack thPack() {
        LanguagePack p = base("th", "Noto Sans Thai");
        p.reportDate = "26 ตุลาคม 2026";
        p.orgName = "ธนาคาร สหภาพ โลก สำนักงาน กลุ่ม";
        p.dataScope = "ไตรมาส 1-3 ปี 2026 งบรวม ของ กลุ่ม";
        p.totalAssets = "8.6 ล้านล้าน หยวน";
        p.crossBorderSettlement = "1.24 ล้านล้าน หยวน";
        p.globalBranches = "1,286 แห่ง";
        p.countries = "68 ประเทศ";
        p.revenue = "218 พันล้าน หยวน";
        p.netProfit = "68.6 พันล้าน หยวน";
        p.employees = "86,000 คน";
        p.customers = "96 ล้านราย";
        p.preparedBy = "สำนักงาน กลุ่ม คณะ ทำงาน หลาย ภาษา";
        p.reviewedBy = "หวัง เหยียนโจว";
        p.issuedBy = "ธนาคาร สหภาพ โลก";
        p.barTitle = "รายได้ ตาม ภูมิภาค";
        p.barSub = "ไตรมาส 1-3 ปี 2026 (หน่วย: ล้านหยวน)";
        p.barSeriesName = "รายได้";
        p.barRegions = new String[]{"จีนแผ่นดินใหญ่", "เอเชียแปซิฟิก", "ยุโรป", "ตะวันออกกลาง", "ญี่ปุ่น", "รัสเซีย และ CIS"};
        p.donutTitle = "สัดส่วน สกุลเงิน ใน การ ชำระ ข้ามพรมแดน";
        p.donutSub = "ตาม มูลค่า การ ชำระ ไตรมาส 1-3 ปี 2026";
        p.donutCenterTitle = "สัดส่วน";
        p.donutCenterUnit = "%";
        p.donutFooter = "ที่มา: ระบบ ชำระ ข้ามพรมแดน";
        p.donutNames = new String[]{"หยวน CNY", "ดอลลาร์ USD", "ยูโร EUR", "เยน JPY", "รูเบิล RUB", "ดิรฮัม AED"};
        p.lineTitle = "ปริมาณ ธุรกิจ ข้ามพรมแดน รายเดือน มกราคม ถึง ตุลาคม 2026";
        p.lineSub = "การ ชำระ ข้ามพรมแดน / การเงิน เพื่อ การค้า / ซื้อขาย เงินตรา ต่างประเทศ";
        p.lineYAxis = "มูลค่า (ล้านหยวน)";
        p.lineFooter = "ที่มา: แพลตฟอร์ม ติดตาม ธุรกิจ ทั่วโลก | หน่วย: ล้านหยวน";
        p.lineMonths = new String[]{"ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค."};
        p.lineNames = new String[]{"การ ชำระ ข้ามพรมแดน", "การเงิน เพื่อ การค้า", "ซื้อขาย เงินตรา ต่างประเทศ"};
        p.donutPalette = new Color[]{
                new Color(138, 90, 0), new Color(122, 31, 61), new Color(196, 150, 60),
                new Color(220, 190, 130), new Color(238, 220, 180), new Color(250, 240, 220)
        };
        p.lineColors = new Color[]{new Color(138, 90, 0), new Color(122, 31, 61), new Color(196, 150, 60)};
        p.chartArea = new Color(251, 245, 228);
        p.barColor = "#8A5A00";
        return p;
    }

    // ==================================================================
    // 傣语（新傣仂文）—— 视觉主题「贝叶竹青·孔雀式」（左对齐 + 壹贰叁色块 + 发丝线表）
    // ==================================================================
    private static LanguagePack daiPack() {
        LanguagePack p = base("dai", "Noto Sans New Tai Lue");
        p.reportDate = "ᦞᧃ ᧒᧖ ᦵᦡᦲᧃ ᧑᧐ ᦔᦲ ᧒᧐᧒᧖";
        p.orgName = "ᦷᦣᧂᦇᦹᧃᦷᦜᧂ ᦂᦱᧃ ᦵᦙᦲᧂ";
        p.dataScope = "ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧙ ᦔᦲ ᧒᧐᧒᧖";
        p.totalAssets = "᧘.᧖ ᦜᦱᧃ ᦍᦲᧃ";
        p.crossBorderSettlement = "᧑.᧒᧔ ᦜᦱᧃ ᦍᦲᧃ";
        p.globalBranches = "᧑᧒᧘᧖ ᦺᦆᧈ";
        p.countries = "᧖᧘ ᦵᦙᦲᧂ";
        p.revenue = "᧒᧑᧘ ᦜᦱᧃ ᦍᦲᧃ";
        p.netProfit = "᧖᧘᧖ ᦜᦱᧃ ᦍᦲᧃ";
        p.employees = "᧘᧖᧐᧐᧐ ᦅᦳᧃ";
        p.customers = "᧙᧖᧐᧐ ᦜᦱᧃ ᦅᦳᧃ";
        p.preparedBy = "ᦷᦣᧂ ᦂᦱᧃ ᦷᦜᧂ";
        p.reviewedBy = "ᦞᦱᧂ ᦍᦲᧃ ᦈᦸᧈ";
        p.issuedBy = "ᦷᦣᧂᦇᦹᧃᦷᦜᧂ";
        p.barTitle = "ᦇᦹᧃ ᦃᧁᧉ ᦺᦓ ᦵᦙᦲᧂ";
        p.barSub = "ᦔᦲ ᧒᧐᧒᧖ (ᦜᦱᧃ ᦍᦲᧃ)";
        p.barSeriesName = "ᦇᦹᧃ ᦃᧁᧉ";
        p.barRegions = new String[]{"ᦵᦙᦲᧂ ᦷᦜᧂ", "ᦵᦙᦲᧂ ᧑", "ᦵᦙᦲᧂ ᧒", "ᦵᦙᦲᧂ ᧓", "ᦵᦙᦲᧂ ᧔", "ᦵᦙᦲᧂ ᧕"};
        p.donutTitle = "ᦇᦹᧃ ᦃᧁᧉ ᦀᦸᧅᧈ ᦺᦓ ᦵᦙᦲᧂ";
        p.donutSub = "ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧙ ᦔᦲ ᧒᧐᧒᧖";
        p.donutCenterTitle = "ᦂᦱᧃ";
        p.donutCenterUnit = "%";
        p.donutFooter = "ᦂᦱᧃ ᦇᦹᧃ ᦵᦙᦲᧂ";
        p.donutNames = new String[]{"ᦍᦲᧃ CNY", "ᦡᦸᧃᧈ USD", "ᦍᦴᦷᦣ EUR", "ᦍᦲᧃ JPY", "ᦣᦴᦔ RUB", "ᦡᦲᧃ AED"};
        p.lineTitle = "ᦂᦱᧃ ᦇᦹᧃ ᦵᦡᦲᧃ ᧑ ᦏᦹᧂ ᧑᧐ ᦔᦲ ᧒᧐᧒᧖";
        p.lineSub = "ᦂᦱᧃ ᦇᦹᧃ / ᦂᦱᧃ ᦃᦻ / ᦂᦱᧃ ᦇᦹᧃ ᦎᦱᧂᧈ";
        p.lineYAxis = "ᦜᦱᧃ ᦍᦲᧃ";
        p.lineFooter = "ᦂᦱᧃ ᦇᦹᧃ ᦷᦜᧂ | ᦜᦱᧃ ᦍᦲᧃ";
        p.lineMonths = new String[]{"ᦵᦡᦲᧃ ᧑", "ᦵᦡᦲᧃ ᧒", "ᦵᦡᦲᧃ ᧓", "ᦵᦡᦲᧃ ᧔", "ᦵᦡᦲᧃ ᧕", "ᦵᦡᦲᧃ ᧖", "ᦵᦡᦲᧃ ᧗", "ᦵᦡᦲᧃ ᧘", "ᦵᦡᦲᧃ ᧙", "ᦵᦡᦲᧃ ᧑᧐"};
        p.lineNames = new String[]{"ᦂᦱᧃ ᦇᦹᧃ", "ᦂᦱᧃ ᦃᦻ", "ᦂᦱᧃ ᦇᦹᧃ ᦎᦱᧂᧈ"};
        p.donutPalette = new Color[]{
                new Color(85, 124, 31), new Color(139, 90, 0), new Color(140, 170, 80),
                new Color(180, 200, 130), new Color(210, 222, 175), new Color(238, 242, 220)
        };
        p.lineColors = new Color[]{new Color(85, 124, 31), new Color(139, 90, 0), new Color(140, 170, 80)};
        p.chartArea = new Color(246, 248, 236);
        p.barColor = "#557C1F";
        return p;
    }

    // ==================================================================
    // 马来语 —— 视觉主题「蜡染·热带赭」（双框通栏 + 单行 8 格紧凑卡 + 橙色斑马）
    // ==================================================================
    private static LanguagePack msPack() {
        LanguagePack p = base("ms", "Noto Sans");
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
        return p;
    }

    // ==================================================================
    // 印尼语 —— 视觉主题「巴迪·靛紫」（全幅细框 + 居中底纹章节 + 浅紫表头）
    // ==================================================================
    private static LanguagePack idPack() {
        LanguagePack p = base("id", "Noto Sans");
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
        return p;
    }

    // ==================================================================
    // 西班牙语 —— 视觉主题「弗拉门戈·赤金」（左粗竖条 + 超大标题 + 黑底 KPI + 红底章节）
    // ==================================================================
    private static LanguagePack esPack() {
        LanguagePack p = base("es", "Noto Sans");
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
        return p;
    }

    // ==================================================================
    // 韩语 —— 视觉主题「丹青·韩纸」（五方色条 + 全框线 2×4 网格 + 数字方块章节）
    // ==================================================================
    private static LanguagePack koPack() {
        LanguagePack p = base("ko", "Noto Sans KR");
        p.reportDate = "2026년 10월 26일";
        p.orgName = "글로벌 유니온 은행 그룹 사무국";
        p.dataScope = "2026년 3분기 누적, 그룹 연결 기준";
        p.totalAssets = "8.6조 위안";
        p.crossBorderSettlement = "1.24조 위안";
        p.globalBranches = "1,286개";
        p.countries = "68개국";
        p.revenue = "2,180억 위안";
        p.netProfit = "686억 위안";
        p.employees = "86,000명";
        p.customers = "9,600만 명";
        p.preparedBy = "그룹 사무국 다국어 태스크포스";
        p.reviewedBy = "왕옌저우";
        p.issuedBy = "글로벌 유니온 은행";
        p.barTitle = "지역별 영업수익";
        p.barSub = "2026년 3분기 누적 (단위: 억 위안)";
        p.barSeriesName = "영업수익";
        p.barRegions = new String[]{"중국 본토", "아시아·태평양", "유럽", "중동", "일본", "러시아·CIS"};
        p.donutTitle = "국경 간 결제 통화 구성";
        p.donutSub = "결제 금액 기준, 2026년 3분기 누적";
        p.donutCenterTitle = "비중";
        p.donutCenterUnit = "%";
        p.donutFooter = "출처: 국경 간 결제 시스템";
        p.donutNames = new String[]{"위안 CNY", "달러 USD", "유로 EUR", "엔 JPY", "루블 RUB", "디르함 AED"};
        p.lineTitle = "2026년 1~10월 국경 간 업무량 월별 추이";
        p.lineSub = "국경 간 결제 / 무역금융 / 외환거래";
        p.lineYAxis = "금액 (억 위안)";
        p.lineFooter = "출처: 글로벌 업무 모니터링 플랫폼 | 단위: 억 위안";
        p.lineMonths = new String[]{"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월"};
        p.lineNames = new String[]{"국경 간 결제", "무역금융", "외환거래"};
        p.donutPalette = new Color[]{
                new Color(31, 59, 87), new Color(192, 57, 43), new Color(217, 164, 0),
                new Color(110, 140, 165), new Color(170, 185, 200), new Color(235, 238, 242)
        };
        p.lineColors = new Color[]{new Color(31, 59, 87), new Color(192, 57, 43), new Color(217, 164, 0)};
        p.chartArea = new Color(247, 245, 240);
        p.barColor = "#1F3B57";
        return p;
    }

    // ==================================================================
    // 17 · 葡萄牙语 pt —— 阿兹勒赫瓷砖·海航式（#1C7ED6 + 金橙 #C8901B）
    // ==================================================================
    private static LanguagePack ptPack() {
        LanguagePack p = base("pt", "Noto Sans");
        p.reportDate = "26 de outubro de 2026";
        p.orgName = "Secretaria do Grupo Bancário Global Huitong";
        p.dataScope = "Acumulado do 3.º trimestre de 2026, base consolidada";
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
        p.barSub = "Acumulado até o 3.º trimestre de 2026 (100 milhões RMB)";
        p.barSeriesName = "Receita operacional";
        p.barRegions = new String[]{"China", "Ásia-Pac.", "Europa", "Or. Médio", "Japão", "Rússia/CEI"};
        p.donutTitle = "Composição por moeda da liquidação transfronteiriça";
        p.donutSub = "Por valor liquidado, acumulado até o 3.º trimestre de 2026";
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
        return p;
    }

    // ==================================================================
    // 18 · 孟加拉语 bn —— 恒河朱砂·莲花式（#B0322F + 恒河绿 #0F7B5F）
    // ==================================================================
    private static LanguagePack bnPack() {
        LanguagePack p = base("bn", "Noto Sans Bengali");
        p.reportDate = "২৬ অক্টোবর ২০২৬";
        p.orgName = "হুইটং গ্লোবাল ব্যাংক গ্রুপ সচিবালয়";
        p.dataScope = "২০২৬ তৃতীয় প্রান্তিকের সঞ্চিত তথ্য, সমন্বিত ভিত্তি";
        p.totalAssets = "৮.৬ ট্রিলিয়ন রেনমিনবি";
        p.crossBorderSettlement = "১.২৪ ট্রিলিয়ন রেনমিনবি";
        p.globalBranches = "১,২৮৬ শাখা";
        p.countries = "৬৮ দেশ";
        p.revenue = "২,১৮০ কোটি রেনমিনবি";
        p.netProfit = "৬৮৬ কোটি রেনমিনবি";
        p.employees = "৮৬,০০০";
        p.customers = "৯.৬ কোটি";
        p.preparedBy = "গ্রুপ সচিবালয় বহুভাষিক টাস্কফোর্স";
        p.reviewedBy = "ওয়াং ইয়ানচৌ";
        p.issuedBy = "হুইটং গ্লোবাল ব্যাংক";
        p.barTitle = "অঞ্চলভিত্তিক পরিচালন রাজস্ব";
        p.barSub = "২০২৬ তৃতীয় প্রান্তিক সঞ্চিত (কোটি রেনমিনবি)";
        p.barSeriesName = "পরিচালন রাজস্ব";
        p.barRegions = new String[]{"চীন", "এশিয়া-প্যাসি.", "ইউরোপ", "মধ্যপ্রাচ্য", "জাপান", "রাশিয়া"};
        p.donutTitle = "সীমান্ত-পারাপার নিষ্পত্তির মুদ্রা 구성";
        p.donutSub = "নিষ্পত্তি মূল্য অনুযায়ী, ২০২৬ তৃতীয় প্রান্তিক সঞ্চিত";
        p.donutCenterTitle = "অংশ";
        p.donutCenterUnit = "%";
        p.donutFooter = "সূত্র: সীমান্ত-পারাপার নিষ্পত্তি সিস্টেম";
        p.donutNames = new String[]{"ইউয়ান CNY", "ডলার USD", "ইউরো EUR", "ইয়েন JPY", "রুবল RUB", "দিরহাম AED"};
        p.lineTitle = "জানুয়ারি-অক্টোবর ২০২৬ সীমান্ত-পারাপার লেনদেনের মাসিক ধারা";
        p.lineSub = "সীমান্ত-পারাপার নিষ্পত্তি / বাণিজ্য অর্থায়ন / বৈদেশিক মুদ্রা";
        p.lineYAxis = "পরিমাণ (কোটি রেনমিনবি)";
        p.lineFooter = "সূত্র: বৈশ্বিক কারবার পর্যবেক্ষণ প্ল্যাটফর্ম | একক: কোটি রেনমিনবি";
        p.lineMonths = new String[]{"জানু", "ফেব", "মার্চ", "এপ্রি", "মে", "জুন", "জুলা", "আগ", "সেপ", "অক্টো"};
        p.lineNames = new String[]{"সীমান্ত-পারাপার নিষ্পত্তি", "বাণিজ্য অর্থায়ন", "বৈদেশিক মুদ্রা"};
        p.donutPalette = new Color[]{
                new Color(176, 50, 47), new Color(122, 30, 28), new Color(15, 123, 95),
                new Color(212, 132, 128), new Color(232, 178, 172), new Color(250, 232, 228)
        };
        p.lineColors = new Color[]{new Color(176, 50, 47), new Color(15, 123, 95), new Color(200, 144, 27)};
        p.chartArea = new Color(253, 246, 244);
        p.barColor = "#B0322F";
        return p;
    }

    // ==================================================================
    // 19 · 他加禄语 tl —— 群岛·芒果式（琥珀 #D97706 + 深海蓝 #0B6E8A）
    // ==================================================================
    private static LanguagePack tlPack() {
        LanguagePack p = base("tl", "Noto Sans");
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
        return p;
    }

    // ==================================================================
    // 20 · 波斯语 fa —— 波斯细密画·绿松石（#5F3DC4 + #0E9384）
    // ==================================================================
    private static LanguagePack faPack() {
        LanguagePack p = base("fa", "Noto Sans Arabic");
        p.reportDate = "۴ آبان ۲۰۲۶";
        p.orgName = "دبیرخانه گروه بانک جهانی هویتونگ";
        p.dataScope = "تجمعی سه فصل ۲۰۲۶، مبنای تلفیقی";
        p.totalAssets = "۸٫۶ تریلیون یوان";
        p.crossBorderSettlement = "۱٫۲۴ تریلیون یوان";
        p.globalBranches = "۱٬۲۸۶ شعبه";
        p.countries = "۶۸ کشور";
        p.revenue = "۲۱۸ میلیارد یوان";
        p.netProfit = "۶۸٫۶ میلیارد یوان";
        p.employees = "۸۶٬۰۰۰";
        p.customers = "۹۶ میلیون";
        p.preparedBy = "کارگروه چندزبانه دبیرخانه";
        p.reviewedBy = "وانگ یانژو";
        p.issuedBy = "بانک جهانی هویتونگ";
        p.barTitle = "درآمد عملیاتی بر پایه منطقه";
        p.barSub = "تجمعی سه فصل ۲۰۲۶ (صد میلیون یوان)";
        p.barSeriesName = "درآمد عملیاتی";
        p.barRegions = new String[]{"چین", "آسیا-پاسیفیک", "اروپا", "خاورمیانه", "ژاپن", "روسیه"};
        p.donutTitle = "ترکیب ارزی تسویه فرامرزی";
        p.donutSub = "بر پایه ارزش تسویه، تجمعی سه فصل ۲۰۲۶";
        p.donutCenterTitle = "سهم";
        p.donutCenterUnit = "%";
        p.donutFooter = "منبع: سامانه تسویه فرامرزی";
        p.donutNames = new String[]{"یوان CNY", "دلار USD", "یورو EUR", "ین JPY", "روبل RUB", "درهم AED"};
        p.lineTitle = "روند ماهانه حجم فرامرزی، ژانویه تا اکتبر ۲۰۲۶";
        p.lineSub = "تسویه فرامرزی / تأمین مالی تجاری / ارز";
        p.lineYAxis = "مبلغ (صد میلیون یوان)";
        p.lineFooter = "منبع: سکوی پایش عملیات جهانی | واحد: صد میلیون یوان";
        p.lineMonths = new String[]{"ژان", "فور", "مارس", "آور", "مه", "ژوئن", "ژوئیه", "اوت", "سپت", "اکت"};
        p.lineNames = new String[]{"تسویه فرامرزی", "تأمین مالی تجاری", "ارز"};
        p.donutPalette = new Color[]{
                new Color(95, 61, 196), new Color(63, 38, 137), new Color(14, 147, 132),
                new Color(140, 116, 215), new Color(196, 182, 235), new Color(238, 234, 250)
        };
        p.lineColors = new Color[]{new Color(95, 61, 196), new Color(14, 147, 132), new Color(200, 144, 27)};
        p.chartArea = new Color(248, 246, 252);
        p.barColor = "#5F3DC4";
        return p;
    }

    // ==================================================================
    // 21 · 泰米尔语 ta —— 庙塔·黄檀式（#C2185B + 深靛 #26456B）
    // ==================================================================
    private static LanguagePack taPack() {
        LanguagePack p = base("ta", "Noto Sans Tamil");
        p.reportDate = "26 அக்டோபர் 2026";
        p.orgName = "ஹூய்த்தொங் உலக வங்கிக் குழுமச் செயலகம்";
        p.dataScope = "2026 மூன்றாம் காலாண்டு வரை குவிந்த தரவு, ஒருங்கிணைந்த அடிப்படை";
        p.totalAssets = "8.6 டிரில்லியன் யுவான்";
        p.crossBorderSettlement = "1.24 டிரில்லியன் யுவான்";
        p.globalBranches = "1,286 கிளைகள்";
        p.countries = "68 நாடுகள்";
        p.revenue = "218 பில்லியன் யுவான்";
        p.netProfit = "68.6 பில்லியன் யுவான்";
        p.employees = "86,000";
        p.customers = "96 மில்லியன்";
        p.preparedBy = "குழுமச் செயலக பல்மொழிப் பணிக்குழு";
        p.reviewedBy = "வாங் யான்சௌ";
        p.issuedBy = "ஹூய்த்தொங் உலக வங்கி";
        p.barTitle = "பிராந்திய வாரியாக இயக்க வருவாய்";
        p.barSub = "2026 மூன்றாம் காலாண்டு வரை குவிந்தது (கோடி யுவான்)";
        p.barSeriesName = "இயக்க வருவாய்";
        p.barRegions = new String[]{"சீனா", "ஆசியா", "ஐரோப்பா", "ம.கிழக்கு", "ஜப்பான்", "ரஷ்யா"};
        p.donutTitle = "எல்லைக்கடப்பு தீர்வின் நாணய அமைப்பு";
        p.donutSub = "தீர்வு மதிப்பு அடிப்படையில், 2026 மூன்றாம் காலாண்டு வரை";
        p.donutCenterTitle = "பங்கு";
        p.donutCenterUnit = "%";
        p.donutFooter = "மூலம்: எல்லைக்கடப்பு தீர்வு அமைப்பு";
        p.donutNames = new String[]{"யுவான் CNY", "டாலர் USD", "யூரோ EUR", "யென் JPY", "ரூபிள் RUB", "திர்ஹாம் AED"};
        p.lineTitle = "ஜனவரி-அக்டோபர் 2026 எல்லைக்கடப்பு அளவின் மாதப் போக்கு";
        p.lineSub = "எல்லைக்கடப்பு தீர்வு / வணிக நிதி / அயல்நாணயம்";
        p.lineYAxis = "மதிப்பு (கோடி யுவான்)";
        p.lineFooter = "மூலம்: உலக செயல்பாட்டு கண்காணிப்பு தளம் | அலகு: கோடி யுவான்";
        p.lineMonths = new String[]{"ஜன", "பிப்", "மார்", "ஏப்", "மே", "ஜூன்", "ஜூலை", "ஆக", "செப்", "அக்"};
        p.lineNames = new String[]{"எல்லைக்கடப்பு தீர்வு", "வணிக நிதி", "அயல்நாணயம்"};
        p.donutPalette = new Color[]{
                new Color(194, 24, 91), new Color(138, 15, 63), new Color(38, 69, 107),
                new Color(219, 110, 150), new Color(238, 168, 192), new Color(252, 232, 240)
        };
        p.lineColors = new Color[]{new Color(194, 24, 91), new Color(38, 69, 107), new Color(200, 144, 27)};
        p.chartArea = new Color(253, 247, 250);
        p.barColor = "#C2185B";
        return p;
    }

    // ==================================================================
    // 22 · 希伯来语 he —— 迦南·橄榄式（#1A7F37 + 石灰 #6B7280）
    // ==================================================================
    private static LanguagePack hePack() {
        LanguagePack p = base("he", "Noto Sans Hebrew");
        p.reportDate = "26 באוקטובר 2026";
        p.orgName = "מזכירות קבוצת בנק הוויטונג הגלובלי";
        p.dataScope = "מצטבר לרבעון השלישי של 2026, בסיס מאוחד";
        p.totalAssets = "8.6 טריליון יואן";
        p.crossBorderSettlement = "1.24 טריליון יואן";
        p.globalBranches = "1,286 סניפים";
        p.countries = "68 מדינות";
        p.revenue = "218 מיליארד יואן";
        p.netProfit = "68.6 מיליארד יואן";
        p.employees = "86,000";
        p.customers = "96 מיליון";
        p.preparedBy = "צוות רב-לשוני של המזכירות";
        p.reviewedBy = "וואנג יאן-ג'ואו";
        p.issuedBy = "בנק הוויטונג הגלובלי";
        p.barTitle = "הכנסות תפעוליות לפי אזור";
        p.barSub = "מצטבר לרבעון השלישי של 2026 (מאה מיליון יואן)";
        p.barSeriesName = "הכנסות תפעוליות";
        p.barRegions = new String[]{"סין", "אסיה-פסיפיק", "אירופה", "מזרח תיכון", "יפן", "רוסיה"};
        p.donutTitle = "הרכב מטבעות בסליקה חוצת גבולות";
        p.donutSub = "לפי שווי הסליקה, מצטבר לרבעון השלישי של 2026";
        p.donutCenterTitle = "חלק";
        p.donutCenterUnit = "%";
        p.donutFooter = "מקור: מערכת הסליקה חוצת הגבולות";
        p.donutNames = new String[]{"יואן CNY", "דולר USD", "אירו EUR", "ין JPY", "רובל RUB", "דירהם AED"};
        p.lineTitle = "מגמה חודשית של מחזור חוצה גבולות, ינואר–אוקטובר 2026";
        p.lineSub = "סליקה חוצת גבולות / מימון סחר / מט\"ח";
        p.lineYAxis = "שווי (מאה מיליון יואן)";
        p.lineFooter = "מקור: פלטפורמת ניטור פעילות גלובלית | יחידה: מאה מיליון יואן";
        p.lineMonths = new String[]{"ינו", "פבר", "מרץ", "אפר", "מאי", "יונ", "יול", "אוג", "ספט", "אוק"};
        p.lineNames = new String[]{"סליקה חוצת גבולות", "מימון סחר", "מט\"ח"};
        p.donutPalette = new Color[]{
                new Color(26, 127, 55), new Color(15, 82, 35), new Color(107, 114, 128),
                new Color(94, 163, 113), new Color(160, 200, 172), new Color(232, 244, 234)
        };
        p.lineColors = new Color[]{new Color(26, 127, 55), new Color(107, 114, 128), new Color(200, 144, 27)};
        p.chartArea = new Color(246, 250, 246);
        p.barColor = "#1A7F37";
        return p;
    }
}

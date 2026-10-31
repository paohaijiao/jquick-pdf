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
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.font.JFontSpec;
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
 * 单据 156（系列）· i18n Demo 公共支撑：字体注册、三张 ECharts 图表渲染、PDF 落盘。
 *
 * <p>本类只放「25 个语种 Demo 共用」的机械代码，业务文案一律留在各自的 Demo 类里，
 * 每个 Demo 类（序号 185-209）只描述自己那一份语言包。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
final class I18nDemoSupport {

    /** 银行年报摘要输出目录 */
    static final String PDF_DIR = "D:/test/i18n/";

    private static final String TEMPLATE_PREFIX = "i18n/global_bank_report_";

    private I18nDemoSupport() {
    }

    // ==================================================================
    // 语言包工厂：只填语种与默认字体，其余由 Demo 类逐项覆盖
    // ==================================================================
    static I18nLanguagePack base(String lang, String defaultFont) {
        I18nLanguagePack p = new I18nLanguagePack();
        p.lang = lang;
        p.defaultFont = defaultFont;
        p.reportNo = "GUB-I18N-2026-0156-" + lang.toUpperCase();
        // 图表配色兜底：每个 Demo 类都会覆盖为各自的视觉主题色
        p.donutPalette = new Color[]{
                new Color(11, 60, 93), new Color(29, 111, 165), new Color(70, 143, 191),
                new Color(126, 175, 209), new Color(175, 203, 224), new Color(212, 226, 238)
        };
        p.lineColors = new Color[]{new Color(11, 60, 93), new Color(184, 134, 11), new Color(29, 111, 165)};
        p.chartArea = new Color(248, 249, 250);
        p.barColor = "#4169E1";
        return p;
    }

    // ==================================================================
    // 字体：一次性注册全部 12 款，模板按 fontFamilyNames 回退链逐字符取用
    // ==================================================================
    static void registerFonts(JPdfConfig config) {
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        config.getFontConfig().registerFont("Noto Sans", "fonts/NotoSans-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans TC", "fonts/NotoSansTC-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Mongolian", "fonts/NotoSansMongolian-Regular.ttf");
        config.getFontConfig().registerFont("Noto Serif Tibetan", "fonts/NotoSerifTibetan-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Thai", "fonts/NotoSansThai-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans New Tai Lue", "fonts/NotoSansNewTaiLue-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans KR", "fonts/NotoSansKR-Regular.ttf");
        config.getFontConfig().registerFont("Noto Sans Bengali", "fonts/NotoSansBengali.ttf");
        config.getFontConfig().registerFont("Noto Sans Tamil", "fonts/NotoSansTamil.ttf");
        config.getFontConfig().registerFont("Noto Sans Hebrew", "fonts/NotoSansHebrew.ttf");
    }

    // ==================================================================
    // 主产物：全球经营年报摘要（模板 global_bank_report_<lang>.txt）
    // ==================================================================
    static byte[] bankReport(I18nLanguagePack p) throws IOException {
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(44, 44, 44, 44));
        // 4.6.0 起目录（TOC）默认开启，且目录页固定使用默认 CJK 字体（SimHei）度量标题，
        // 非中文标题（ä / ไทย / 한국어 等）会因缺字形抛 "No glyph for ..."。
        // 本 Demo 的版式是「单页头 + 全篇表格」，没有多级标题，目录页没有意义，故关闭。
        config.getCatalogConfig().setEnabled(false);
        config.getFontConfig().setDefaultFontName(p.defaultFont);
        registerFonts(config);

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
        write("global_bank_report_" + p.lang, pdf);
        return pdf;
    }

    // ==================================================================
    // 副产物：脚本渲染示例（模板 i18n/<name>.txt，用于验证单语种字形）
    // ==================================================================
    static byte[] simpleDemo(String template, String family, String file) throws IOException {
        JPdfConfig config = new JPdfConfig();
        // 4.6.0 起目录（TOC）默认开启且固定用 SimHei 度量标题，非 CJK 标题会缺字形，这里关闭
        config.getCatalogConfig().setEnabled(false);
        config.getFontConfig().registerFont(family, file);
        // 注册内置 CJK 字体，供模板中中文混排元素的 fontFamilyNames 回退链命中
        config.getFontConfig().registerFont("SimHei", "fonts/simhei.ttf");
        config.getFontConfig().setDefaultFont(new JFontSpec(family, file));

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("reportDate", "2026-10-31");
        byte[] pdf = factory.executeResource(template);
        String name = template.substring(template.lastIndexOf('/') + 1).replace(".txt", "");
        write(name, pdf);
        return pdf;
    }

    static void write(String pdfName, byte[] pdf) throws IOException {
        File dir = new File(JQuickConstant.path + "i18n");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File out = new File(dir, pdfName + ".pdf");
        Files.write(out.toPath(), pdf);
        System.out.println("已生成：" + out.getPath() + "（" + pdf.length + " 字节）");
    }

    // ==================================================================
    // 图表 1：柱状图 —— 全球分区域营业收入
    // ==================================================================
    private static String renderBar(I18nLanguagePack p) {
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
    private static String renderDonut(I18nLanguagePack p) {
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
    private static String renderLine(I18nLanguagePack p) {
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
}

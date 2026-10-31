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
 * 单据 156（系列）· i18n 序号 185 —— 阿拉伯语版全球经营年报摘要
 *
 * <p>语种 ar：阿拉伯语。字体 Noto Sans Arabic，视觉主题「沙漠琥珀·RTL 奢华式（深棕 #5C4326 + 琥珀金 #B9861C，RTL 右竖条）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ar.txt
 * 输出：D://test//i18n//global_bank_report_ar.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/arabic.txt} → {@code D://test//i18n//arabic.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n185ArabicTest {

    /**
     * 主产物：阿拉伯语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n185() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ar", "Noto Sans Arabic");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：阿拉伯语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple185() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/arabic.txt", "Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
    }
}

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
 * 单据 156（系列）· i18n 序号 200 —— 波斯语版全球经营年报摘要
 *
 * <p>语种 fa：波斯语。字体 Noto Sans Arabic，视觉主题「波斯细密画·绿松石（紫罗兰 #5F3DC4 + 绿松石 #0E9384，居中对称装饰 + 全篇 RTL）」。</p>
 *
 * <p>模板：i18n/global_bank_report_fa.txt
 * 输出：D://test//i18n//global_bank_report_fa.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/persian.txt} → {@code D://test//i18n//persian.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n200PersianTest {

    /**
     * 主产物：波斯语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n200() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("fa", "Noto Sans Arabic");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：波斯语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple200() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/persian.txt", "Noto Sans Arabic", "fonts/NotoSansArabic-Regular.ttf");
    }
}

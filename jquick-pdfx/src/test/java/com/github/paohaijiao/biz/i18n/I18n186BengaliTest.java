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
 * 单据 156（系列）· i18n 序号 186 —— 孟加拉语版全球经营年报摘要
 *
 * <p>语种 bn：孟加拉语。字体 Noto Sans Bengali，视觉主题「恒河朱砂·莲花式（#B0322F + 恒河绿 #0F7B5F，左竖色块 + 圆角卡片 KPI）」。</p>
 *
 * <p>模板：i18n/global_bank_report_bn.txt
 * 输出：D://test//i18n//global_bank_report_bn.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/bengali.txt} → {@code D://test//i18n//bengali.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n186BengaliTest {

    /**
     * 主产物：孟加拉语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n186() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("bn", "Noto Sans Bengali");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：孟加拉语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple186() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/bengali.txt", "Noto Sans Bengali", "fonts/NotoSansBengali.ttf");
    }
}

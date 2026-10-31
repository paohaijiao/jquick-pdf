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
 * 单据 156（系列）· i18n 序号 188 —— 繁体中文版全球经营年报摘要
 *
 * <p>语种 zhHant：繁体中文。字体 Noto Sans TC，视觉主题「朱砂礼仪式（#9E2B25 + #C9A227，双线礼框 + 居中抬头）」。</p>
 *
 * <p>模板：i18n/global_bank_report_zhHant.txt
 * 输出：D://test//i18n//global_bank_report_zhHant.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n188ChineseTraditionalTest {

    /**
     * 主产物：繁体中文版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n188() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("zhHant", "Noto Sans TC");
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
        I18nDemoSupport.bankReport(p);
    }
}

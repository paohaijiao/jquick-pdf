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
 * 单据 156（系列）· i18n 序号 195 —— 日语版全球经营年报摘要
 *
 * <p>语种 ja：日语。字体 Noto Sans JP，视觉主题「藍染朱印·和风余白式（藍 #2B4570 + 朱印 #C73E3A，顶部压线卡）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ja.txt
 * 输出：D://test//i18n//global_bank_report_ja.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/japanese.txt} → {@code D://test//i18n//japanese.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n195JapaneseTest {

    /**
     * 主产物：日语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n195() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ja", "Noto Sans JP");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：日语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple195() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/japanese.txt", "Noto Sans JP", "fonts/NotoSansJP-Regular.ttf");
    }
}

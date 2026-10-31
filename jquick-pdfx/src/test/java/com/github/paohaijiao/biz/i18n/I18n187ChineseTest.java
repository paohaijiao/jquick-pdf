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
 * 单据 156（系列）· i18n 序号 187 —— 简体中文版全球经营年报摘要
 *
 * <p>语种 zh：简体中文。字体 SimHei，视觉主题「黛紫鎏银·圆角卡片式（主色 #52307C，borderRadius 圆角卡片，居中）」。</p>
 *
 * <p>模板：i18n/global_bank_report_zh.txt
 * 输出：D://test//i18n//global_bank_report_zh.pdf</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n187ChineseTest {

    /**
     * 主产物：简体中文版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n187() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("zh", "SimHei");
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
        I18nDemoSupport.bankReport(p);
    }
}

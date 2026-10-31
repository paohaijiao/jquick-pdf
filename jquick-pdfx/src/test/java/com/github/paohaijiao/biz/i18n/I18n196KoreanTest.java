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
 * 单据 156（系列）· i18n 序号 196 —— 韩语版全球经营年报摘要
 *
 * <p>语种 ko：韩语。字体 Noto Sans KR，视觉主题「丹青·韩纸（#1F3B57 + 五方色条，全框线网格 + 数字方块章节）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ko.txt
 * 输出：D://test//i18n//global_bank_report_ko.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/korean.txt} → {@code D://test//i18n//korean.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n196KoreanTest {

    /**
     * 主产物：韩语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n196() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ko", "Noto Sans KR");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：韩语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple196() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/korean.txt", "Noto Sans KR", "fonts/NotoSansKR-Regular.ttf");
    }
}

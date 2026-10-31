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
 * 单据 156（系列）· i18n 序号 209 —— 越南语版全球经营年报摘要
 *
 * <p>语种 vi：越南语。字体 Noto Sans，视觉主题「青瓷·顺化式（#0B6E7F，满幅色带 + 左竖线章节 + 满格细线表）」。</p>
 *
 * <p>模板：i18n/global_bank_report_vi.txt
 * 输出：D://test//i18n//global_bank_report_vi.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/vietnamese.txt} → {@code D://test//i18n//vietnamese.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n209VietnameseTest {

    /**
     * 主产物：越南语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n209() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("vi", "Noto Sans");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：越南语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple209() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/vietnamese.txt", "Noto Sans", "fonts/NotoSans-Regular.ttf");
    }
}

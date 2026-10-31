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
 * 单据 156（系列）· i18n 序号 206 —— 泰语版全球经营年报摘要
 *
 * <p>语种 th：泰语。字体 Noto Sans Thai，视觉主题「金箔紫檀·泰庙式（#8A5A00 + #7A1F3D，居中金双线 + 2×2 大卡 + 仅上下金线表）」。</p>
 *
 * <p>模板：i18n/global_bank_report_th.txt
 * 输出：D://test//i18n//global_bank_report_th.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/thai.txt} → {@code D://test//i18n//thai.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n206ThaiTest {

    /**
     * 主产物：泰语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n206() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("th", "Noto Sans Thai");
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
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：泰语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple206() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/thai.txt", "Noto Sans Thai", "fonts/NotoSansThai-Regular.ttf");
    }
}

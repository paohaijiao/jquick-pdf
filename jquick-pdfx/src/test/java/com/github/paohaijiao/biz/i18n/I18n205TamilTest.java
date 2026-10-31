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
 * 单据 156（系列）· i18n 序号 205 —— 泰米尔语版全球经营年报摘要
 *
 * <p>语种 ta：泰米尔语。字体 Noto Sans Tamil，视觉主题「庙塔·黄檀式（玫红 #C2185B + 深靛 #26456B，三层递减色条 + 交叉网格 KPI）」。</p>
 *
 * <p>模板：i18n/global_bank_report_ta.txt
 * 输出：D://test//i18n//global_bank_report_ta.pdf</p>
 *
 * <p>副产物：脚本渲染示例 {@code i18n/tamil.txt} → {@code D://test//i18n//tamil.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class I18n205TamilTest {

    /**
     * 主产物：泰米尔语版全球经营年报摘要（含三张 ECharts 图表）。
     */
    @Test
    public void i18n205() throws IOException {
        I18nLanguagePack p = I18nDemoSupport.base("ta", "Noto Sans Tamil");
        p.reportDate = "26 அக்டோபர் 2026";
        p.orgName = "ஹூய்த்தொங் உலக வங்கிக் குழுமச் செயலகம்";
        p.dataScope = "2026 மூன்றாம் காலாண்டு வரை குவிந்த தரவு, ஒருங்கிணைந்த அடிப்படை";
        p.totalAssets = "8.6 டிரில்லியன் யுவான்";
        p.crossBorderSettlement = "1.24 டிரில்லியன் யுவான்";
        p.globalBranches = "1,286 கிளைகள்";
        p.countries = "68 நாடுகள்";
        p.revenue = "218 பில்லியன் யுவான்";
        p.netProfit = "68.6 பில்லியன் யுவான்";
        p.employees = "86,000";
        p.customers = "96 மில்லியன்";
        p.preparedBy = "குழுமச் செயலக பல்மொழிப் பணிக்குழு";
        p.reviewedBy = "வாங் யான்சௌ";
        p.issuedBy = "ஹூய்த்தொங் உலக வங்கி";
        p.barTitle = "பிராந்திய வாரியாக இயக்க வருவாய்";
        p.barSub = "2026 மூன்றாம் காலாண்டு வரை குவிந்தது (கோடி யுவான்)";
        p.barSeriesName = "இயக்க வருவாய்";
        p.barRegions = new String[]{"சீனா", "ஆசியா", "ஐரோப்பா", "ம.கிழக்கு", "ஜப்பான்", "ரஷ்யா"};
        p.donutTitle = "எல்லைக்கடப்பு தீர்வின் நாணய அமைப்பு";
        p.donutSub = "தீர்வு மதிப்பு அடிப்படையில், 2026 மூன்றாம் காலாண்டு வரை";
        p.donutCenterTitle = "பங்கு";
        p.donutCenterUnit = "%";
        p.donutFooter = "மூலம்: எல்லைக்கடப்பு தீர்வு அமைப்பு";
        p.donutNames = new String[]{"யுவான் CNY", "டாலர் USD", "யூரோ EUR", "யென் JPY", "ரூபிள் RUB", "திர்ஹாம் AED"};
        p.lineTitle = "ஜனவரி-அக்டோபர் 2026 எல்லைக்கடப்பு அளவின் மாதப் போக்கு";
        p.lineSub = "எல்லைக்கடப்பு தீர்வு / வணிக நிதி / அயல்நாணயம்";
        p.lineYAxis = "மதிப்பு (கோடி யுவான்)";
        p.lineFooter = "மூலம்: உலக செயல்பாட்டு கண்காணிப்பு தளம் | அலகு: கோடி யுவான்";
        p.lineMonths = new String[]{"ஜன", "பிப்", "மார்", "ஏப்", "மே", "ஜூன்", "ஜூலை", "ஆக", "செப்", "அக்"};
        p.lineNames = new String[]{"எல்லைக்கடப்பு தீர்வு", "வணிக நிதி", "அயல்நாணயம்"};
        p.donutPalette = new Color[]{
                new Color(194, 24, 91), new Color(138, 15, 63), new Color(38, 69, 107),
                new Color(219, 110, 150), new Color(238, 168, 192), new Color(252, 232, 240)
        };
        p.lineColors = new Color[]{new Color(194, 24, 91), new Color(38, 69, 107), new Color(200, 144, 27)};
        p.chartArea = new Color(253, 247, 250);
        p.barColor = "#C2185B";
        I18nDemoSupport.bankReport(p);
    }

    /**
     * 副产物：泰米尔语脚本渲染示例（字形与排版验证）。
     */
    @Test
    public void simple205() throws IOException {
        I18nDemoSupport.simpleDemo("i18n/tamil.txt", "Noto Sans Tamil", "fonts/NotoSansTamil.ttf");
    }
}

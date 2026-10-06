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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.lunar.LunarCalendarOption;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景 Demo 63：2024 年 5 月农历日历（行政人事）
 *
 * <p>业务场景：行政人事部制作公历农历对照日历，标注传统节日与节气，
 * 便于员工安排工作与假期。</p>
 * <p>实际图表类型：{@link JChartType#Lunar} 农历日历图。</p>
 *
 * <p>模板：{@code report/biz/63_lunar.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\63_lunar.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt 宽的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz63LunarTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz63Lunar() throws IOException {
        // 2024 年 5 月共 31 天，5 月 1 日为星期三（第 1 行第 4 列，row=0, col=3）
        List<LunarCalendarOption.DayData> dayData = new ArrayList<>();
        String[] lunarDays = {
                "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九",
                "三十", "初一", "初二", "初三", "初四", "初五", "初六",
                "初七", "初八", "初九", "初十", "十一", "十二", "十三",
                "十四", "十五", "十六", "十七", "十八", "十九", "二十",
                "廿一", "廿二", "廿三"
        };
        // 5 月 1 日是周三，起始列 col=3
        int col = 3;
        int row = 0;
        for (int day = 1; day <= 31; day++) {
            dayData.add(new LunarCalendarOption.DayData(day, lunarDays[day - 1], row, col));
            col++;
            if (col >= 7) {
                col = 0;
                row++;
            }
        }

        List<LunarCalendarOption.SpecialDay> specialDays = new ArrayList<>();
        specialDays.add(new LunarCalendarOption.SpecialDay("劳动节", 0, 3));  // 5/1
        specialDays.add(new LunarCalendarOption.SpecialDay("立夏", 0, 6));    // 5/5
        specialDays.add(new LunarCalendarOption.SpecialDay("母亲节", 1, 5));  // 5/12
        specialDays.add(new LunarCalendarOption.SpecialDay("小满", 2, 4));    // 5/21

        LunarCalendarOption.CalendarDataConfig dataConfig = new LunarCalendarOption.CalendarDataConfig()
                .setDayDataList(dayData)
                .setSpecialDays(specialDays)
                .setWeekDays(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                .setRows(5)
                .setCols(7);

        LunarCalendarOption.ColorConfig colorConfig = new LunarCalendarOption.ColorConfig()
                .setBackgroundColor(Color.white)
                .setSpecialDayColor(new Color(191, 54, 12));

        JTitle title = new JTitle();
        title.setText("2024 年 5 月农历日历");
        title.setSubtext("公历农历对照 · 传统节日与节气标注");

        LunarCalendarOption option = LunarCalendarOption.of("2024", "5月", colorConfig, title, dataConfig);

        String svg = JChartRendererFactory.renderChart(JChartType.Lunar, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/63_lunar.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/63_lunar.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

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
package com.github.paohaijiao.ele;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.lunar.LunarCalendarOption;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JLunarTest {
    private static java.util.List<LunarCalendarOption.DayData> createDefaultDayData() {
        java.util.List<LunarCalendarOption.DayData> defaultData = new ArrayList<>();
        // 第一行
        defaultData.add(new LunarCalendarOption.DayData(1, "初四", 0, 0));
        defaultData.add(new LunarCalendarOption.DayData(2, "初五", 0, 1));
        defaultData.add(new LunarCalendarOption.DayData(3, "初六", 0, 2));
        defaultData.add(new LunarCalendarOption.DayData(4, "初七", 0, 3));
        defaultData.add(new LunarCalendarOption.DayData(5, "初八", 0, 4));
        defaultData.add(new LunarCalendarOption.DayData(6, "初九", 0, 5));
        defaultData.add(new LunarCalendarOption.DayData(7, "初十", 0, 6));
        // 第二行
        defaultData.add(new LunarCalendarOption.DayData(8, "十一", 1, 0));
        defaultData.add(new LunarCalendarOption.DayData(9, "十二", 1, 1));
        defaultData.add(new LunarCalendarOption.DayData(10, "十三", 1, 2));
        defaultData.add(new LunarCalendarOption.DayData(11, "十四", 1, 3));
        defaultData.add(new LunarCalendarOption.DayData(12, "十五", 1, 4));
        defaultData.add(new LunarCalendarOption.DayData(13, "十六", 1, 5));
        defaultData.add(new LunarCalendarOption.DayData(14, "十七", 1, 6));
        // 第三行
        defaultData.add(new LunarCalendarOption.DayData(15, "十八", 2, 0));
        defaultData.add(new LunarCalendarOption.DayData(16, "十九", 2, 1));
        defaultData.add(new LunarCalendarOption.DayData(17, "二十", 2, 2));
        defaultData.add(new LunarCalendarOption.DayData(18, "廿一", 2, 3));
        defaultData.add(new LunarCalendarOption.DayData(19, "廿二", 2, 4));
        defaultData.add(new LunarCalendarOption.DayData(20, "廿三", 2, 5));
        defaultData.add(new LunarCalendarOption.DayData(21, "廿四", 2, 6));
        // 第四行
        defaultData.add(new LunarCalendarOption.DayData(22, "廿五", 3, 0));
        defaultData.add(new LunarCalendarOption.DayData(23, "廿六", 3, 1));
        defaultData.add(new LunarCalendarOption.DayData(24, "廿七", 3, 2));
        defaultData.add(new LunarCalendarOption.DayData(25, "廿八", 3, 3));
        defaultData.add(new LunarCalendarOption.DayData(26, "廿九", 3, 4));
        defaultData.add(new LunarCalendarOption.DayData(27, "三十", 3, 5));
        defaultData.add(new LunarCalendarOption.DayData(28, "三月", 3, 6));
        // 第五行
        defaultData.add(new LunarCalendarOption.DayData(29, "初二", 4, 0));
        defaultData.add(new LunarCalendarOption.DayData(30, "初三", 4, 1));
        defaultData.add(new LunarCalendarOption.DayData(31, "初四", 4, 2));
        return defaultData;
    }

    private static java.util.List<LunarCalendarOption.SpecialDay> createDefaultSpecialDays() {
        java.util.List<LunarCalendarOption.SpecialDay> specialDays = new ArrayList<>();
        specialDays.add(new LunarCalendarOption.SpecialDay("春分", 0, 6)); // 第1行第7列
        specialDays.add(new LunarCalendarOption.SpecialDay("清明", 3, 0)); // 第4行第1列
        return specialDays;
    }

    private JOption createData() {
        LunarCalendarOption.CalendarDataConfig dataConfig = new LunarCalendarOption.CalendarDataConfig()
                .setDayDataList(createDefaultDayData())
                .setSpecialDays(createDefaultSpecialDays())
                .setWeekDays(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                .setRows(5)
                .setCols(7);
        LunarCalendarOption.ColorConfig colorConfig = new LunarCalendarOption.ColorConfig()
                .setBackgroundColor(Color.white)
                .setSpecialDayColor(new Color(0, 100, 0));
        JTitle title = new JTitle();
        title.setText("2024年3月日历");
//        title.setSubtext("自定义月份");
        LunarCalendarOption option = LunarCalendarOption.of("2024", "三月", colorConfig, title, dataConfig);
        return option;
    }

    @Test
    public void svg2() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.Lunar);
        graphContainer.setOption(createData());
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
    }
}

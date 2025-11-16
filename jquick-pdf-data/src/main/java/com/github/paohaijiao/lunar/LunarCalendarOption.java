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
package com.github.paohaijiao.lunar;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.JTitle;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 农历日历配置选项
 */
@Data
public class LunarCalendarOption extends JOption {
    private final String year;
    private final String month;
    private final ColorConfig colorConfig;
    private final JTitle title;
    private final CalendarDataConfig dataConfig;

    public LunarCalendarOption(String year, String month, ColorConfig colorConfig, JTitle title, CalendarDataConfig dataConfig) {
        this.year = year;
        this.month = month;
        this.colorConfig = colorConfig != null ? colorConfig : new ColorConfig();
        this.title = title;
        this.dataConfig = dataConfig != null ? dataConfig : new CalendarDataConfig();
    }

    public static LunarCalendarOption of(String year, String month) {
        return new LunarCalendarOption(year, month, new ColorConfig(), null, new CalendarDataConfig());
    }

    public static LunarCalendarOption of(String year, String month, ColorConfig colorConfig) {
        return new LunarCalendarOption(year, month, colorConfig, null, new CalendarDataConfig());
    }

    public static LunarCalendarOption of(String year, String month, ColorConfig colorConfig, JTitle title) {
        return new LunarCalendarOption(year, month, colorConfig, title, new CalendarDataConfig());
    }

    public static LunarCalendarOption of(String year, String month, ColorConfig colorConfig, JTitle title, CalendarDataConfig dataConfig) {
        return new LunarCalendarOption(year, month, colorConfig, title, dataConfig);
    }

    public String year() {
        return year;
    }

    public String month() {
        return month;
    }

    public ColorConfig colorConfig() {
        return colorConfig;
    }

    public CalendarDataConfig dataConfig() {
        return dataConfig;
    }

    @Override
    public JTitle title() {
        return title;
    }

    /**
     * 颜色配置
     */
    public static class ColorConfig {
        private Color backgroundColor = Color.WHITE;
        private Color gridColor = new Color(232, 235, 240);
        private Color textColor = new Color(84, 85, 90);
        private Color specialDayColor = new Color(160, 0, 0);
        private Color titleColor = new Color(60, 60, 60);
        private Color solarDateColor = Color.BLACK;
        private Color lunarDateColor = Color.BLACK;
        private Color weekendColor = new Color(200, 0, 0);

        private boolean useCustomCellColors = false;
        private Color[] customCellColors = null;

        private boolean useGradientBackground = false;
        private Color gradientStartColor = new Color(240, 248, 255);
        private Color gradientEndColor = new Color(230, 240, 250);

        // Getters and Setters
        public Color getBackgroundColor() { return backgroundColor; }
        public ColorConfig setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Color getGridColor() { return gridColor; }
        public ColorConfig setGridColor(Color gridColor) {
            this.gridColor = gridColor;
            return this;
        }

        public Color getTextColor() { return textColor; }
        public ColorConfig setTextColor(Color textColor) {
            this.textColor = textColor;
            return this;
        }

        public Color getSpecialDayColor() { return specialDayColor; }
        public ColorConfig setSpecialDayColor(Color specialDayColor) {
            this.specialDayColor = specialDayColor;
            return this;
        }

        public Color getTitleColor() { return titleColor; }
        public ColorConfig setTitleColor(Color titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Color getSolarDateColor() { return solarDateColor; }
        public ColorConfig setSolarDateColor(Color solarDateColor) {
            this.solarDateColor = solarDateColor;
            return this;
        }

        public Color getLunarDateColor() { return lunarDateColor; }
        public ColorConfig setLunarDateColor(Color lunarDateColor) {
            this.lunarDateColor = lunarDateColor;
            return this;
        }

        public Color getWeekendColor() { return weekendColor; }
        public ColorConfig setWeekendColor(Color weekendColor) {
            this.weekendColor = weekendColor;
            return this;
        }

        public boolean isUseCustomCellColors() { return useCustomCellColors; }
        public ColorConfig setUseCustomCellColors(boolean useCustomCellColors) {
            this.useCustomCellColors = useCustomCellColors;
            return this;
        }

        public Color[] getCustomCellColors() { return customCellColors; }
        public ColorConfig setCustomCellColors(Color[] customCellColors) {
            this.customCellColors = customCellColors;
            return this;
        }

        public boolean isUseGradientBackground() { return useGradientBackground; }
        public ColorConfig setUseGradientBackground(boolean useGradientBackground) {
            this.useGradientBackground = useGradientBackground;
            return this;
        }

        public Color getGradientStartColor() { return gradientStartColor; }
        public ColorConfig setGradientStartColor(Color gradientStartColor) {
            this.gradientStartColor = gradientStartColor;
            return this;
        }

        public Color getGradientEndColor() { return gradientEndColor; }
        public ColorConfig setGradientEndColor(Color gradientEndColor) {
            this.gradientEndColor = gradientEndColor;
            return this;
        }
    }

    /**
     * 日历数据配置
     */
    public static class CalendarDataConfig {
        private List<DayData> dayDataList = createDefaultDayData();
        private List<SpecialDay> specialDays = createDefaultSpecialDays();
        private String[] weekDays = {"一", "二", "三", "四", "五", "六", "日"};
        private int rows = 5;
        private int cols = 7;

        public List<DayData> getDayDataList() { return dayDataList; }
        public CalendarDataConfig setDayDataList(List<DayData> dayDataList) {
            this.dayDataList = dayDataList;
            return this;
        }

        public List<SpecialDay> getSpecialDays() { return specialDays; }
        public CalendarDataConfig setSpecialDays(List<SpecialDay> specialDays) {
            this.specialDays = specialDays;
            return this;
        }

        public String[] getWeekDays() { return weekDays; }
        public CalendarDataConfig setWeekDays(String[] weekDays) {
            this.weekDays = weekDays;
            return this;
        }

        public int getRows() { return rows; }
        public CalendarDataConfig setRows(int rows) {
            this.rows = rows;
            return this;
        }

        public int getCols() { return cols; }
        public CalendarDataConfig setCols(int cols) {
            this.cols = cols;
            return this;
        }

        private static List<DayData> createDefaultDayData() {
            List<DayData> defaultData = new ArrayList<>();
            // 第一行
            defaultData.add(new DayData(1, "初四", 0, 0));
            defaultData.add(new DayData(2, "初五", 0, 1));
            defaultData.add(new DayData(3, "初六", 0, 2));
            defaultData.add(new DayData(4, "初七", 0, 3));
            defaultData.add(new DayData(5, "初八", 0, 4));
            defaultData.add(new DayData(6, "初九", 0, 5));
            defaultData.add(new DayData(7, "初十", 0, 6));
            // 第二行
            defaultData.add(new DayData(8, "十一", 1, 0));
            defaultData.add(new DayData(9, "十二", 1, 1));
            defaultData.add(new DayData(10, "十三", 1, 2));
            defaultData.add(new DayData(11, "十四", 1, 3));
            defaultData.add(new DayData(12, "十五", 1, 4));
            defaultData.add(new DayData(13, "十六", 1, 5));
            defaultData.add(new DayData(14, "十七", 1, 6));
            // 第三行
            defaultData.add(new DayData(15, "十八", 2, 0));
            defaultData.add(new DayData(16, "十九", 2, 1));
            defaultData.add(new DayData(17, "二十", 2, 2));
            defaultData.add(new DayData(18, "廿一", 2, 3));
            defaultData.add(new DayData(19, "廿二", 2, 4));
            defaultData.add(new DayData(20, "廿三", 2, 5));
            defaultData.add(new DayData(21, "廿四", 2, 6));
            // 第四行
            defaultData.add(new DayData(22, "廿五", 3, 0));
            defaultData.add(new DayData(23, "廿六", 3, 1));
            defaultData.add(new DayData(24, "廿七", 3, 2));
            defaultData.add(new DayData(25, "廿八", 3, 3));
            defaultData.add(new DayData(26, "廿九", 3, 4));
            defaultData.add(new DayData(27, "三十", 3, 5));
            defaultData.add(new DayData(28, "三月", 3, 6));
            // 第五行
            defaultData.add(new DayData(29, "初二", 4, 0));
            defaultData.add(new DayData(30, "初三", 4, 1));
            defaultData.add(new DayData(31, "初四", 4, 2));
            return defaultData;
        }

        private static List<SpecialDay> createDefaultSpecialDays() {
            List<SpecialDay> specialDays = new ArrayList<>();
            specialDays.add(new SpecialDay("春分", 0, 6)); // 第1行第7列
            specialDays.add(new SpecialDay("清明", 3, 0)); // 第4行第1列
            return specialDays;
        }
    }

    /**
     * 日期数据
     */
    public static class DayData {
        private final int solarDay;
        private final String lunarDay;
        private final int row;
        private final int col;

        public DayData(int solarDay, String lunarDay, int row, int col) {
            this.solarDay = solarDay;
            this.lunarDay = lunarDay;
            this.row = row;
            this.col = col;
        }

        public int getSolarDay() { return solarDay; }
        public String getLunarDay() { return lunarDay; }
        public int getRow() { return row; }
        public int getCol() { return col; }
    }

    /**
     * 特殊日期（节气、节日等）
     */
    public static class SpecialDay {
        private final String name;
        private final int row;
        private final int col;

        public SpecialDay(String name, int row, int col) {
            this.name = name;
            this.row = row;
            this.col = col;
        }

        public String getName() { return name; }
        public int getRow() { return row; }
        public int getCol() { return col; }
    }
}

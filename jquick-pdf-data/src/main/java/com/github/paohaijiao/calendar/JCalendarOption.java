package com.github.paohaijiao.calendar;/*
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

import lombok.Data;

import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * 日历图表配置选项
 */
@Data
public class JCalendarOption {

    private final String title;

    private final String subtext;

    private final int year;

    private final Map<LocalDate, Integer> data;

    private final Color startColor;

    private final Color endColor;

    private final Color gridColor;

    private final Color textColor;

    private final int cellSize;

    private final int margin;

    public JCalendarOption(String title, String subtext, int year, Map<LocalDate, Integer> data, Color startColor, Color endColor, Color gridColor, Color textColor, int cellSize, int margin) {
        this.title = title;
        this.subtext = subtext;
        this.year = year;
        this.data = data;
        this.startColor = startColor != null ? startColor : new Color(235, 237, 240);
        this.endColor = endColor != null ? endColor : new Color(32, 125, 222);
        this.gridColor = gridColor != null ? gridColor : new Color(232, 235, 240);
        this.textColor = textColor != null ? textColor : new Color(84, 85, 90);
        this.cellSize = cellSize > 0 ? cellSize : 20;
        this.margin = margin > 0 ? margin : 80;
    }

    public JCalendarOption(String title, int year, Map<LocalDate, Integer> data) {
        this(title, null, year, data, null, null, null, null, 0, 0);
    }

    public JCalendarOption(int year, Map<LocalDate, Integer> data) {
        this(null, null, year, data, null, null, null, null, 0, 0);
    }


    public String subtext() {
        return subtext;
    }

    public int year() {
        return year;
    }

    public Map<LocalDate, Integer> data() {
        return data;
    }

    public Color startColor() {
        return startColor;
    }

    public Color endColor() {
        return endColor;
    }

    public Color gridColor() {
        return gridColor;
    }

    public Color textColor() {
        return textColor;
    }

    public int cellSize() {
        return cellSize;
    }

    public int margin() {
        return margin;
    }

    @Override
    public String toString() {
        return "JCalendarOption{" + "title='" + title + '\'' + ", year=" + year + ", dataSize=" + (data != null ? data.size() : 0) + '}';
    }
}

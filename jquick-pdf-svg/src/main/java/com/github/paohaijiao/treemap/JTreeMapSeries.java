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
package com.github.paohaijiao.treemap;

import com.github.paohaijiao.code.JSeriesType;
import com.github.paohaijiao.series.JSeries;

import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.style.JItemStyle;
import lombok.Data;

import java.util.List;
/**
 * packageName com.github.paohaijiao.treemap
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/8/7
 */
public class JTreeMapSeries extends JSeries<JTreeMapSeries> {
    private List<JData> data;
    private JItemStyle itemStyle;
    private JItemStyle labelStyle;
    private boolean showValue = true;
    private boolean showPercentage = true;

    public JTreeMapSeries() {
        this.type(JSeriesType.treemap);
    }

    public JTreeMapSeries(String name) {
        super(name);
        this.type(JSeriesType.treemap);
    }

    public List<JData> data() {
        return data;
    }

    public JTreeMapSeries data(List<JData> data) {
        this.data = data;
        return this;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return itemStyle;
    }

    public JTreeMapSeries itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    public JItemStyle labelStyle() {
        if (this.labelStyle == null) {
            this.labelStyle = new JItemStyle();
        }
        return labelStyle;
    }

    public JTreeMapSeries labelStyle(JItemStyle labelStyle) {
        this.labelStyle = labelStyle;
        return this;
    }

    public boolean showValue() {
        return showValue;
    }

    public JTreeMapSeries showValue(boolean showValue) {
        this.showValue = showValue;
        return this;
    }

    public boolean showPercentage() {
        return showPercentage;
    }

    public JTreeMapSeries showPercentage(boolean showPercentage) {
        this.showPercentage = showPercentage;
        return this;
    }
}

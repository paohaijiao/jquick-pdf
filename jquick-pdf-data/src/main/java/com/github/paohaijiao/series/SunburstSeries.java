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
package com.github.paohaijiao.series;


import com.github.paohaijiao.code.JSeriesType;
import com.github.paohaijiao.style.itemstyle.JEmphasis;

import java.util.List;
import java.util.Map;

/**
 * Sunburst 系列类
 */
public class SunburstSeries extends JSeries<SunburstSeries> {

    /**
     * 旭日图的半径
     */
    private Object[] radius;

    /**
     * 旭日图的中心点
     */
    private Object[] center;

    /**
     * 层级配置
     */
    private List<Map<String, Object>> levels;

    /**
     * 数据
     */
    private List<Map<String, Object>> data;


    private JEmphasis emphasis;


    public JEmphasis getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(JEmphasis emphasis) {
        this.emphasis = emphasis;
    }

    public SunburstSeries() {
        this.type(JSeriesType.SUNBURST);
    }

    public SunburstSeries(String name) {
        super(name);
        this.type(JSeriesType.SUNBURST);
    }

    public Object[] radius() {
        return this.radius;
    }

    public SunburstSeries radius(Object[] radius) {
        this.radius = radius;
        return this;
    }

    public SunburstSeries radius(String innerRadius, String outerRadius) {
        this.radius = new Object[]{innerRadius, outerRadius};
        return this;
    }

    public SunburstSeries radius(int innerRadius, int outerRadius) {
        this.radius = new Object[]{innerRadius, outerRadius};
        return this;
    }

    public Object[] center() {
        return this.center;
    }

    public SunburstSeries center(Object[] center) {
        this.center = center;
        return this;
    }

    public SunburstSeries center(String x, String y) {
        this.center = new Object[]{x, y};
        return this;
    }

    public SunburstSeries center(int x, int y) {
        this.center = new Object[]{x, y};
        return this;
    }

    public List<Map<String, Object>> levels() {
        return this.levels;
    }

    public SunburstSeries levels(List<Map<String, Object>> levels) {
        this.levels = levels;
        return this;
    }
    public void setLevels(List<Map<String, Object>> levels) {
        this.levels = levels;
    }

    public List<Map<String, Object>> data() {
        return this.data;
    }

    public SunburstSeries data(List<Map<String, Object>> data) {
        this.data = data;
        return this;
    }


}

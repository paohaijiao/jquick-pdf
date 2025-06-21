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

package com.paohaijiao.data.series;

import com.paohaijiao.data.code.JDataFilter;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.style.JAreaStyle;
import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 */
@Getter
@Setter
public class JLine extends JSeries<JLine> {
    /**
     * 平滑曲线
     */
    private Boolean smooth;
    /**
     * ECharts 会在折线图的数据数量大于实际显示的像素宽度（高度）的时候会启用优化，对显示在一个像素宽度内的数据做筛选，该选项是指明数据筛选的策略
     *
     * @see JDataFilter
     */
    private Object dataFilter;
    /**
     * 区域填充样式。
     */
    private JAreaStyle areaStyle;

    /**
     * lineStyle
     */
    private JLineStyle lineStyle;

    /**
     * 构造函数
     */
    public JLine() {
        this.type(JSeriesType.line);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JLine(String name) {
        super(name);
        this.type(JSeriesType.line);
    }

    /**
     * 获取dataFilter值
     */
    public Object dataFilter() {
        return this.dataFilter;
    }

    /**
     * 设置dataFilter值
     *
     * @param dataFilter
     */
    public JLine dataFilter(Object dataFilter) {
        this.dataFilter = dataFilter;
        return this;
    }

    /**
     * 设置dataFilter值
     *
     * @param dataFilter
     */
    public JLine dataFilter(JDataFilter dataFilter) {
        this.dataFilter = dataFilter;
        return this;
    }

    /**
     * 获取smooth值
     */
    public Boolean smooth() {
        return this.smooth;
    }

    /**
     * 设置smooth值
     *
     * @param smooth
     */
    public JLine smooth(Boolean smooth) {
        this.smooth = smooth;
        return this;
    }

    /**
     * 新建并返回areaStyle
     *
     * @return
     */
    public JAreaStyle areaStyle() {
        if (this.areaStyle == null) {
            this.areaStyle = new JAreaStyle();
        }
        return this.areaStyle;
    }

    /**
     * 设置areaStyle
     *
     * @param areaStyle
     * @return
     */
    public JLine areaStyle(JAreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    /**
     * 新建并返回lineStyle
     *
     * @return
     */
    public JLineStyle lineStyle() {
        if (this.lineStyle == null) {
            this.lineStyle = new JLineStyle();
        }
        return this.lineStyle;
    }

    /**
     * 设置lineStyle
     *
     * @param lineStyle
     * @return
     */
    public JLine lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }
}

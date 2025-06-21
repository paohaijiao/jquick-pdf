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

import com.paohaijiao.data.code.JOrient;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 『箱形图』、『盒须图』、『盒式图』、『盒状图』、『箱线图』
 *
 * @author martin
 */
@Getter
@Setter
public class JBoxplot extends JSeries<JBoxplot> {
    /**
     * 布局方式
     */
    private JOrient layout;
    /**
     * box 的宽度的上下限。数组的意思是：[min, max]
     */
    private Object[] boxWidth;
    /**
     * boxplot 图形样式，有 normal 和 emphasis 两个状态，normal 是图形正常的样式，emphasis 是图形高亮的样式，比如鼠标悬浮或者图例联动高亮的时候会使用 emphasis 作为图形的样式
     */
    private JItemStyle itemStyle;

    /**
     * 构造函数
     */
    public JBoxplot() {
        this.type(JSeriesType.boxplot);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JBoxplot(String name) {
        super(name);
        this.type(JSeriesType.boxplot);
    }

    public JOrient layout() {
        return this.layout;
    }

    public JBoxplot layout(JOrient layout) {
        this.layout = layout;
        return this;
    }

    public Object[] boxWidth() {
        return this.boxWidth;
    }

    public JBoxplot boxWidth(Object[] boxWidth) {
        this.boxWidth = boxWidth;
        return this;
    }

    public JBoxplot boxWidth(Object min, Object max) {
        this.boxWidth = new Object[]{min, max};
        return this;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    public JBoxplot itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }
}

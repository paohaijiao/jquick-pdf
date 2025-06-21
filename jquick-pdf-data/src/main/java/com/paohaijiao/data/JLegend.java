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

package com.paohaijiao.data;

import com.paohaijiao.data.code.JAlign;
import com.paohaijiao.data.code.JLegendType;
import com.paohaijiao.data.code.JOrient;
import com.paohaijiao.data.code.JSelectedMode;
import com.paohaijiao.data.data.JLegendData;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author martin
 */
@Getter
@Setter
public class JLegend extends JBasic<JLegend> implements JData<JLegend>, JComponent {
    /**
     * 布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
     *
     * @see JOrient
     */
    private JOrient orient;

    /**
     * 设置分页方式
     */
    private JLegendType type ;

    /**
     * 图例图形宽度
     */
    private Integer itemWidth;
    /**
     * 图例图形高度
     */
    private Integer itemHeight;
    /**
     * 文字样式
     *
     * @see JTextStyle
     */
    private JTextStyle textStyle;
    /**
     * 选择模式，默认开启图例开关
     *
     * @see JSelectedMode
     */
    private Object selectedMode;
    /**
     * 配置默认选中状态，可配合LEGEND.SELECTED事件做动态数据载入
     */
    private Map<String, Boolean> selected;
    /**
     * 图例内容数组，数组项通常为{string}，每一项代表一个系列的name。
     *
     * @see JLegendData
     */
    private List data;

    private JAlign align;
    private String formatter;

    /**
     * 构造函数
     */
    public JLegend() {
    }

    /**
     * 构造函数,参数:values
     *
     * @param values
     */
    public JLegend(Object... values) {
        this.data(values);
    }

    public JLegendType type(){
        return this.type;
    }

    public JLegend type(JLegendType type) {
        this.type = type;
        return this;
    }

    public JAlign align() {
        return this.align;
    }

    public JLegend align(JAlign align) {
        this.align = align;
        return this;
    }

    public String formatter() {
        return this.formatter;
    }

    public JLegend formatter(String formatter) {
        this.formatter = formatter;
        return this;
    }
    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JLegend textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 设置data值
     *
     * @param data
     */
    public JLegend data(List data) {
        this.data = data;
        return this;
    }

    /**
     * 获取orient值
     */
    public JOrient orient() {
        return this.orient;
    }

    /**
     * 设置orient值
     *
     * @param orient
     */
    public JLegend orient(JOrient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * 获取itemWidth值
     */
    public Integer itemWidth() {
        return this.itemWidth;
    }

    /**
     * 设置itemWidth值
     *
     * @param itemWidth
     */
    public JLegend itemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    /**
     * 获取itemHeight值
     */
    public Integer itemHeight() {
        return this.itemHeight;
    }

    /**
     * 设置itemHeight值
     *
     * @param itemHeight
     */
    public JLegend itemHeight(Integer itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    /**
     * 文字样式
     *
     * @see JTextStyle
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    /**
     * 获取selectedMode值
     */
    public Object selectedMode() {
        return this.selectedMode;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public JLegend selectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }


    /**
     * 获取selected值
     *
     * @param name
     */
    public Boolean selected(String name) {
        if (this.selected == null) {
            return null;
        } else {
            return selected.get(name);
        }
    }

    /**
     * 设置默认选中状态
     *
     * @param name
     * @param selected
     * @return
     */
    public JLegend selected(String name, Boolean selected) {
        if (!this.data.contains(name)) {
            throw new RuntimeException("Legend中不包含name为" + name + "的图例");
        }
        if (this.selected == null) {
            this.selected = new LinkedHashMap<String, Boolean>();
        }
        this.selected.put(name, selected);
        return this;
    }

    /**
     * 获取data值
     */
    public List data() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
        return this.data;
    }

    /**
     * 添加图例属性
     *
     * @param values
     * @return
     */
    public JLegend data(Object... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.data().addAll(Arrays.asList(values));
        return this;
    }
}

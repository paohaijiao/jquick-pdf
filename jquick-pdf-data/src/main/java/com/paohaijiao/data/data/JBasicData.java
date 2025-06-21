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

package com.paohaijiao.data.data;

import com.paohaijiao.data.code.JMarkType;
import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.style.JItemStyle;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: BasicData
 *
 * @author martin
 */
@Getter
@Setter
public abstract class JBasicData<T> implements Serializable {

    private static final long serialVersionUID = 3510060011221090087L;

    private String name;
    private String text;
    private Object value;
    /**
     * 饼图、雷达图、力导、和弦图使用x,y
     */
    private Object x;
    private Object y;
    /**
     * 在存在直角坐标系的图表如折线、柱形、K线、散点图中
     * 除了通过直接指定位置外，如果希望标注基于直角系的定位，可以通过xAxis，yAxis
     */
    private Integer xAxis;
    private Integer yAxis;
    private JMarkType type;
    private Object symbol;
    private Object symbolSize;
    private JItemStyle itemStyle;
    /**
     * 特殊样式
     *
     * @see JTextStyle
     */
    private JTextStyle textStyle;

    /**
     * 构造函数
     */
    public JBasicData() {
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    protected JBasicData(String name) {
        this.name = name;
    }

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JBasicData(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 构造函数,参数:name,symbol,symbolSize
     *
     * @param name
     * @param symbol
     * @param symbolSize
     */
    public JBasicData(String name, Object symbol, Object symbolSize) {
        this.name = name;
        this.symbol = symbol;
        this.symbolSize = symbolSize;
    }

    /**
     * 构造函数,参数:value,symbol
     *
     * @param value
     * @param symbol
     */
    public JBasicData(Object value, Object symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * 构造函数,参数:value,symbol,symbolSize
     *
     * @param value
     * @param symbol
     * @param symbolSize
     */
    public JBasicData(Object value, Object symbol, Object symbolSize) {
        this.value = value;
        this.symbol = symbol;
        this.symbolSize = symbolSize;
    }

    /**
     * 获取textStyle值
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public T textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return (T) this;
    }

    /**
     * 获取text值
     */
    public String text() {
        return this.text;
    }

    /**
     * 设置text值
     *
     * @param text
     */
    public T text(String text) {
        this.text = text;
        return (T) this;
    }

    /**
     * 获取name值
     */
    public String name() {
        return this.name;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * 获取value值
     */
    public Object value() {
        return this.value;
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public T value(Object value) {
        this.value = value;
        return (T) this;
    }

    /**
     * 设置value值
     *
     * @param values
     */
    public T value(Object... values) {
        if (values == null || values.length == 0) {
            return (T) this;
        }
        if (this.value == null) {
            this.value = new ArrayList<Object>(values.length);
        }
        if (this.value instanceof List) {
            ((List) this.value).addAll(Arrays.asList(values));
        }
        return (T) this;
    }

    /**
     * 获取x值
     */
    public Object x() {
        return this.x;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public T x(Object x) {
        this.x = x;
        return (T) this;
    }

    /**
     * 获取y值
     */
    public Object y() {
        return this.y;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public T y(Object y) {
        this.y = y;
        return (T) this;
    }

    /**
     * 获取xAxis值
     */
    public Integer xAxis() {
        return this.xAxis;
    }

    /**
     * 设置xAxis值
     *
     * @param xAxis
     */
    public T xAxis(Integer xAxis) {
        this.xAxis = xAxis;
        return (T) this;
    }

    /**
     * 获取yAxis值
     */
    public Integer yAxis() {
        return this.yAxis;
    }

    /**
     * 设置yAxis值
     *
     * @param yAxis
     */
    public T yAxis(Integer yAxis) {
        this.yAxis = yAxis;
        return (T) this;
    }

    /**
     * 获取type值
     */
    public JMarkType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public T type(JMarkType type) {
        this.type = type;
        return (T) this;
    }

    /**
     * 获取symbol值
     */
    public Object symbol() {
        return this.symbol;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public T symbol(Object symbol) {
        this.symbol = symbol;
        return (T) this;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public T symbol(JSymbol symbol) {
        this.symbol = symbol;
        return (T) this;
    }

    /**
     * 获取symbolSize值
     */
    public Object symbolSize() {
        return this.symbolSize;
    }

    /**
     * 设置symbolSize值
     *
     * @param symbolSize
     */
    public T symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return (T) this;
    }

    /**
     * 获取itemStyle值
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public T itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return (T) this;
    }
}

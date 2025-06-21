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

import com.paohaijiao.data.JTooltip;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用的Data对象...和Data接口同名，Data接口中的data使用的就是这里的Data..
 *
 * @author martin
 */
@Getter
@Setter
public class JData extends JBasicData<JData> {
    /**
     * 可以通过valueIndex:0指定为横轴特殊点
     */
    private Integer valueIndex;

    private Object min;
    private Object max;
    /**
     * 图标
     */
    private String icon;
    private Boolean selected;
    private JTooltip tooltip;

    /**
     * 平滑曲线弧度，smooth为true时有效，指定平滑曲线弧度
     */
    private Double smoothRadian;

    /**
     * 构造函数
     */
    public JData() {
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JData(String name) {
        super(name);
    }

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JData(String name, Object value) {
        super(name, value);
    }

    /**
     * 构造函数,参数:name,symbol,symbolSize
     *
     * @param name
     * @param symbol
     * @param symbolSize
     */
    public JData(String name, Object symbol, Object symbolSize) {
        super(name, symbol, symbolSize);
    }

    /**
     * 构造函数,参数:value,symbol
     *
     * @param value
     * @param symbol
     */
    public JData(Object value, Object symbol) {
        super(value, symbol);
    }

    /**
     * 构造函数,参数:value,symbol,symbolSize
     *
     * @param value
     * @param symbol
     * @param symbolSize
     */
    public JData(Object value, Object symbol, Object symbolSize) {
        super(value, symbol, symbolSize);
    }

    /**
     * 获取平滑曲线弧度
     */
    public Double smoothRadian() {
        return this.smoothRadian;
    }

    /**
     * 设置平滑曲线弧度
     *
     * @param smoothRadian
     */
    public JData smoothRadian(Double smoothRadian) {
        this.smoothRadian = smoothRadian;
        return this;
    }

    /**
     * 获取tooltip值
     */
    public JTooltip tooltip() {
        if (this.tooltip == null) {
            this.tooltip = new JTooltip();
        }
        return this.tooltip;
    }

    /**
     * 设置tooltip值
     *
     * @param tooltip
     */
    public JData tooltip(JTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * 获取selected值
     */
    public Boolean selected() {
        return this.selected;
    }

    /**
     * 设置selected值
     *
     * @param selected
     */
    public JData selected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    /**
     * 获取icon值
     */
    public String icon() {
        return this.icon;
    }

    /**
     * 设置icon值
     *
     * @param icon
     */
    public JData icon(String icon) {
        this.icon = icon;
        return this;
    }

    /**
     * 获取min值
     */
    public Object min() {
        return this.min;
    }

    /**
     * 设置min值
     *
     * @param min
     */
    public JData min(Object min) {
        this.min = min;
        return this;
    }

    /**
     * 获取max值
     */
    public Object max() {
        return this.max;
    }

    /**
     * 设置max值
     *
     * @param max
     */
    public JData max(Object max) {
        this.max = max;
        return this;
    }

    /**
     * 获取valueIndex值
     */
    public Integer valueIndex() {
        return this.valueIndex;
    }

    /**
     * 设置valueIndex值
     *
     * @param valueIndex
     */
    public JData valueIndex(Integer valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }

    /**
     * 获取valueIndex值
     */
    public Integer getValueIndex() {
        return valueIndex;
    }

    /**
     * 设置valueIndex值
     *
     * @param valueIndex
     */
    public JData setValueIndex(Integer valueIndex) {
        this.valueIndex = valueIndex;
        return this;
    }
}

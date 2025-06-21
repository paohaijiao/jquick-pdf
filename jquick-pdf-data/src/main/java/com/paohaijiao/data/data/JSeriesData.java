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
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description: Series.Data
 *
 * @author martin
 */
@Getter
@Setter
public class JSeriesData implements Serializable {

    private static final long serialVersionUID = -3295595963653443202L;

    private Object value;
    private JTooltip tooltip;
    private JItemStyle itemStyle;

    /**
     * 构造函数,参数:value
     *
     * @param value
     */
    public JSeriesData(Object value) {
        this.value = value;
    }

    /**
     * 构造函数,参数:value,tooltip
     *
     * @param value
     * @param tooltip
     */
    public JSeriesData(Object value, JTooltip tooltip) {
        this.value = value;
        this.tooltip = tooltip;
    }

    /**
     * 构造函数,参数:value,itemStyle
     *
     * @param value
     * @param itemStyle
     */
    public JSeriesData(Object value, JItemStyle itemStyle) {
        this.value = value;
        this.itemStyle = itemStyle;
    }

    /**
     * 构造函数,参数:value,tooltip,itemStyle
     *
     * @param value
     * @param tooltip
     * @param itemStyle
     */
    public JSeriesData(Object value, JTooltip tooltip, JItemStyle itemStyle) {
        this.value = value;
        this.tooltip = tooltip;
        this.itemStyle = itemStyle;
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
    public JSeriesData value(Object value) {
        this.value = value;
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
    public JSeriesData tooltip(JTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
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
    public JSeriesData itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 获取value值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取tooltip值
     */
    public JTooltip getTooltip() {
        return tooltip;
    }

    /**
     * 设置tooltip值
     *
     * @param tooltip
     */
    public void setTooltip(JTooltip tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * 获取itemStyle值
     */
    public JItemStyle getItemStyle() {
        return itemStyle;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public void setItemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}

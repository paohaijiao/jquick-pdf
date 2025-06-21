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

import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description : LegendData
 *
 * @author martin
 */
@Getter
@Setter
public class JLegendData implements Serializable {

    private static final long serialVersionUID = 7218201600361155091L;

    /**
     * 名称
     */
    private String name;
    /**
     * 文字样式
     *
     * @see JTextStyle
     */
    private JTextStyle textStyle;
    /**
     * 图标
     */
    private String icon;

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JLegendData(String name) {
        this.name = name;
    }

    /**
     * 构造函数,参数:name,textStyle
     *
     * @param name
     * @param textStyle
     */
    public JLegendData(String name, JTextStyle textStyle) {
        this.name = name;
        this.textStyle = textStyle;
    }

    /**
     * 构造函数,参数:name,textStyle,icon
     *
     * @param name
     * @param textStyle
     * @param icon
     */
    public JLegendData(String name, JTextStyle textStyle, String icon) {
        this.name = name;
        this.textStyle = textStyle;
        this.icon = icon;
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
    public JLegendData name(String name) {
        this.name = name;
        return this;
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
    public JLegendData textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
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
    public JLegendData icon(String icon) {
        this.icon = icon;
        return this;
    }
}

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

package com.paohaijiao.data.style;

import com.paohaijiao.data.code.JLinkType;

import java.io.Serializable;

/**
 * Description: LinkStyle
 *
 * @author martin
 */
public class JLinkStyle implements Serializable {

    private static final long serialVersionUID = -547421614869188616L;

    /**
     * 线条类型，可选为：'curve'（曲线） | 'line'（直线）
     */
    private JLinkType type;
    /**
     * 线条颜色
     */
    private String borderColor;
    /**
     * 线宽
     */
    private Integer borderWidth;

    /**
     * 获取type值
     */
    public JLinkType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JLinkStyle type(JLinkType type) {
        this.type = type;
        return this;
    }

    /**
     * 获取borderColor值
     */
    public String borderColor() {
        return this.borderColor;
    }

    /**
     * 设置borderColor值
     *
     * @param borderColor
     */
    public JLinkStyle borderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    /**
     * 获取borderWidth值
     */
    public Integer borderWidth() {
        return this.borderWidth;
    }

    /**
     * 设置borderWidth值
     *
     * @param borderWidth
     */
    public JLinkStyle borderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    /**
     * 获取type值
     */
    public JLinkType getType() {
        return type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public void setType(JLinkType type) {
        this.type = type;
    }

    /**
     * 获取borderColor值
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * 设置borderColor值
     *
     * @param borderColor
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * 获取borderWidth值
     */
    public Integer getBorderWidth() {
        return borderWidth;
    }

    /**
     * 设置borderWidth值
     *
     * @param borderWidth
     */
    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }
}

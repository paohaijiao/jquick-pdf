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

import com.paohaijiao.data.code.JBrushType;

/**
 * Description: NodeStyle
 *
 * @author martin
 */
public class JNodeStyle extends JLinkStyle {
    /**
     * 可选 'both', 'stroke', 'fill'
     */
    private JBrushType brushType;
    /**
     * 填充颜色
     */
    private String color;

    /**
     * 获取brushType值
     */
    public JBrushType brushType() {
        return this.brushType;
    }

    /**
     * 设置brushType值
     *
     * @param brushType
     */
    public JNodeStyle brushType(JBrushType brushType) {
        this.brushType = brushType;
        return this;
    }

    /**
     * 获取color值
     */
    public String color() {
        return this.color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public JNodeStyle color(String color) {
        this.color = color;
        return this;
    }

    /**
     * 获取brushType值
     */
    public JBrushType getBrushType() {
        return brushType;
    }

    /**
     * 设置brushType值
     *
     * @param brushType
     */
    public void setBrushType(JBrushType brushType) {
        this.brushType = brushType;
    }

    /**
     * 获取color值
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }
}

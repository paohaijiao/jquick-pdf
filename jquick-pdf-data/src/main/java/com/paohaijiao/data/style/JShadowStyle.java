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

import java.io.Serializable;

/**
 * 阴影指示器样式设置
 *
 * @author martin
 */
public class JShadowStyle implements Serializable {

    private static final long serialVersionUID = -1996366699438984171L;

    /**
     * 阴影颜色
     */
    private String color;
    /**
     * 默认auto，阴影大小
     */
    private Object width;
    /**
     * 填充方式，默认只有default
     */
    private String type;

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
    public JShadowStyle color(String color) {
        this.color = color;
        return this;
    }

    /**
     * 获取width值
     */
    public Object width() {
        return this.width;
    }

    /**
     * 设置width值
     *
     * @param width
     */
    public JShadowStyle width(Object width) {
        this.width = width;
        return this;
    }

    /**
     * 获取type值
     */
    public String type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JShadowStyle type(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取typeDefault值
     */
    public JShadowStyle typeDefault() {
        this.type = "default";
        return this;
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

    /**
     * 获取width值
     */
    public Object getWidth() {
        return width;
    }

    /**
     * 设置width值
     *
     * @param width
     */
    public void setWidth(Object width) {
        this.width = width;
    }

    /**
     * 获取type值
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}

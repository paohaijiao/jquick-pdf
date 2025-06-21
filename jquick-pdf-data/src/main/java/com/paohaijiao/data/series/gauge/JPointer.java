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

package com.paohaijiao.data.series.gauge;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 仪表盘 - 指针样式
 *
 * @author martin
 */
@Getter
@Setter
public class JPointer implements Serializable {

    private static final long serialVersionUID = 2575834098541231673L;

    /**
     * 属性length控制线长，百分比相对的是仪表盘的外半径
     */
    private Object length;
    /**
     * 属性width控制指针最宽处，
     */
    private Object width;
    /**
     * 属性color控制指针颜色
     */
    private String color;

    /**
     * 获取length值
     */
    public Object length() {
        return this.length;
    }

    /**
     * 设置length值
     *
     * @param length
     */
    public JPointer length(Object length) {
        this.length = length;
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
    public JPointer width(Object width) {
        this.width = width;
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
    public JPointer color(String color) {
        this.color = color;
        return this;
    }

    /**
     * 获取length值
     */
    public Object getLength() {
        return length;
    }

    /**
     * 设置length值
     *
     * @param length
     */
    public void setLength(Object length) {
        this.length = length;
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

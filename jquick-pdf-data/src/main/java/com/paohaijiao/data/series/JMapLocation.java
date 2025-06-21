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

import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.code.JY;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 地图位置设置，默认只适应上下左右居中可配x，y，width，height，任意参数为空都将根据其他参数自适应
 *
 * @author martin
 */
@Getter
@Setter
public class JMapLocation implements Serializable {

    private static final long serialVersionUID = -9175820401945407194L;

    private Object x;
    private Object y;
    private Object width;
    private Object height;

    /**
     * 构造函数
     */
    public JMapLocation() {
    }

    /**
     * 构造函数,参数:x,y
     *
     * @param x
     * @param y
     */
    public JMapLocation(Object x, Object y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 构造函数,参数:x,y,width
     *
     * @param x
     * @param y
     * @param width
     */
    public JMapLocation(Object x, Object y, Object width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    /**
     * 构造函数,参数:x,y,width,height
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public JMapLocation(Object x, Object y, Object width, Object height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 构造函数,参数:x,y
     *
     * @param x
     * @param y
     */
    public JMapLocation(JX x, JY y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 构造函数,参数:x,y,width
     *
     * @param x
     * @param y
     * @param width
     */
    public JMapLocation(JX x, JY y, Object width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    /**
     * 构造函数,参数:x,y,width,height
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public JMapLocation(JX x, JY y, Object width, Object height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    public JMapLocation x(Object x) {
        this.x = x;
        return this;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JMapLocation x(JX x) {
        this.x = x;
        return this;
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
    public JMapLocation y(JY y) {
        this.y = y;
        return this;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JMapLocation y(Object y) {
        this.y = y;
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
    public JMapLocation width(Object width) {
        this.width = width;
        return this;
    }

    /**
     * 获取height值
     */
    public Object height() {
        return this.height;
    }

    /**
     * 设置height值
     *
     * @param height
     */
    public JMapLocation height(Object height) {
        this.height = height;
        return this;
    }
}

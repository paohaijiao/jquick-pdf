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

package com.paohaijiao.data.series.other;

import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.code.JY;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述信息
 *
 * @author martin
 */
@Getter
@Setter
public class JRootLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object x;
    private Object y;

    /**
     * 构造函数
     */
    public JRootLocation() {
    }

    /**
     * 构造函数,参数:x,y
     *
     * @param x
     * @param y
     */
    public JRootLocation(Object x, Object y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JRootLocation x(JX x) {
        this.x = x;
        return this;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JRootLocation x(Integer x) {
        this.x = x;
        return this;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JRootLocation x(String x) {
        this.x = x;
        return this;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JRootLocation x(Object x) {
        this.x = x;
        return this;
    }

    /**
     * 获取x值
	 */
	public Object x() {
        return this.x;
    }

    /**
     * 设置y值
     *
     * @param y
     */
	public JRootLocation y(JY y) {
        this.y = y;
        return this;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JRootLocation y(Integer y) {
        this.y = y;
        return this;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JRootLocation y(String y) {
        this.y = y;
        return this;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JRootLocation y(Object y) {
        this.y = y;
        return this;
    }

    /**
     * 获取y值
	 */
	public Object y() {
        return this.y;
    }

    /**
     * 获取x值
     */
	public Object getX() {
        return x;
    }

    /**
     * 设置x值
     *
     * @param x
     */
	public void setX(Object x) {
        this.x = x;
    }

    /**
     * 获取y值
     */
	public Object getY() {
        return y;
    }

    /**
     * 设置y值
     *
     * @param y
     */
	public void setY(Object y) {
        this.y = y;
    }
}

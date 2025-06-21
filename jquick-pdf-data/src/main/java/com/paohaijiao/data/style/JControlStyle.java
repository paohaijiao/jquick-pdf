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
 * 时间轴控制器样式
 *
 * @author martin
 */
public class JControlStyle implements Serializable {

    private static final long serialVersionUID = -3442438026749918760L;
    /**
     * 按钮大小
     */
    private Integer itemSize;
    /**
     * 按钮间隔
     */
    private Integer itemGap;
    /**
     * 正常
     */
    private Color normal;
    /**
     * 高亮
     */
    private Color emphasis;

    /**
     * 构造函数
     */
    public JControlStyle() {
    }

    /**
     * 获取itemSize值
     */
    public Integer itemSize() {
        return this.itemSize;
    }

    /**
     * 设置itemSize值
     *
     * @param itemSize
     */
    public JControlStyle itemSize(Integer itemSize) {
        this.itemSize = itemSize;
        return this;
    }

    /**
     * 获取itemGap值
     */
    public Integer itemGap() {
        return this.itemGap;
    }

    /**
     * 设置itemGap值
     *
     * @param itemGap
     */
    public JControlStyle itemGap(Integer itemGap) {
        this.itemGap = itemGap;
        return this;
    }

    /**
     * 获取normal值
     */
    public Color normal() {
        if (this.normal == null) {
            this.normal = new Color();
        }
        return this.normal;
    }

    /**
     * 设置normal值
     *
     * @param normal
     */
    public JControlStyle normal(Color normal) {
        this.normal = normal;
        return this;
    }

    /**
     * 获取emphasis值
     */
    public Color emphasis() {
        if (this.emphasis == null) {
            this.emphasis = new Color();
        }
        return this.emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public JControlStyle emphasis(Color emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    /**
     * 获取normal值
     */
    public Color getNormal() {
        return normal;
    }

    /**
     * 设置normal值
     *
     * @param normal
     */
    public void setNormal(Color normal) {
        this.normal = normal;
    }

    /**
     * 获取emphasis值
     */
    public Color getEmphasis() {
        return emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public void setEmphasis(Color emphasis) {
        this.emphasis = emphasis;
    }

    /**
     * 获取itemSize值
     */
    public Integer getItemSize() {
        return itemSize;
    }

    /**
     * 设置itemSize值
     *
     * @param itemSize
     */
    public void setItemSize(Integer itemSize) {
        this.itemSize = itemSize;
    }

    /**
     * 获取itemGap值
     */
	public Integer getItemGap() {
        return itemGap;
    }

    /**
     * 设置itemGap值
     *
     * @param itemGap
     */
    public void setItemGap(Integer itemGap) {
        this.itemGap = itemGap;
    }

    public class Color {
        /**
         * 时间轴控制器样式颜色
         */
        private String color;

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
        public Color color(String color) {
            this.color = color;
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
    }
}

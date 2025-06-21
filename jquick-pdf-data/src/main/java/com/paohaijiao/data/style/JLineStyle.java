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

import com.paohaijiao.data.code.JLineType;
import com.paohaijiao.data.style.itemstyle.JEmphasis;
import com.paohaijiao.data.style.itemstyle.JNormal;

import java.io.Serializable;

/**
 * @author martin
 */
public class JLineStyle implements Serializable {

    private static final long serialVersionUID = 4765717693423256102L;

    /**
     * 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形
     */
    private Double opacity;

    /**
     * 阳线颜色
     */
    private Object color;
    /**
     * 阴线颜色
     */
    private Object color0;
    /**
     * 线条样式，可选为：'solid' | 'dotted' | 'dashed'
     *
     * @see JLineType
     */
    private JLineType type;
    /**
     * 线宽
     */
    private Integer width;
    /**
     * 折线主线(IE8+)有效，阴影色彩，支持rgba
     */
    private String shadowColor;
    /**
     * 默认值5，折线主线(IE8+)有效，阴影模糊度，大于0有效
     */
    private Integer shadowBlur;
    /**
     * 默认值3，折线主线(IE8+)有效，阴影横向偏移，正值往右，负值往左
     */
    private Integer shadowOffsetX;
    /**
     * 默认值3，折线主线(IE8+)有效，阴影纵向偏移，正值往下，负值往上
     */
    private Integer shadowOffsetY;

    /**
     * normal属性
     */
    private JNormal normal;
    /**
     * emphasis属性
     */
    private JEmphasis emphasis;

    /**
     * 获取color值
     */
    public Object color() {
        return this.color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public JLineStyle color(Object color) {
        this.color = color;
        return this;
    }

    /**
     * 获取color0值
     */
    public Object color0() {
        return this.color0;
    }

    /**
     * 设置color0值
     *
     * @param color0
     */
    public JLineStyle color0(Object color0) {
        this.color0 = color0;
        return this;
    }

    /**
     * 获取type值
     */
    public JLineType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JLineStyle type(JLineType type) {
        this.type = type;
        return this;
    }

    /**
     * 获取width值
     */
    public Integer width() {
        return this.width;
    }

    /**
     * 设置width值
     *
     * @param width
     */
    public JLineStyle width(Integer width) {
        this.width = width;
        return this;
    }

    /**
     * 获取shadowColor值
     */
    public String shadowColor() {
        return this.shadowColor;
    }

    /**
     * 设置shadowColor值
     *
     * @param shadowColor
     */
    public JLineStyle shadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    /**
     * 获取shadowBlur值
     */
    public Integer shadowBlur() {
        return this.shadowBlur;
    }

    /**
     * 设置shadowBlur值
     *
     * @param shadowBlur
     */
    public JLineStyle shadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    /**
     * 获取shadowOffsetX值
     */
    public Integer shadowOffsetX() {
        return this.shadowOffsetX;
    }

    /**
     * 设置shadowOffsetX值
     *
     * @param shadowOffsetX
     */
    public JLineStyle shadowOffsetX(Integer shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    /**
     * 获取shadowOffsetY值
     */
    public Integer shadowOffsetY() {
        return this.shadowOffsetY;
    }

    /**
     * 设置shadowOffsetY值
     *
     * @param shadowOffsetY
     */
    public JLineStyle shadowOffsetY(Integer shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    /**
     * 获取color值
     */
    public Object getColor() {
        return color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public void setColor(Object color) {
        this.color = color;
    }

    /**
     * 获取color0值
     */
    public Object getColor0() {
        return color0;
    }

    /**
     * 设置color0值
     *
     * @param color0
     */
    public void setColor0(Object color0) {
        this.color0 = color0;
    }

    /**
     * 获取type值
     */
    public JLineType getType() {
        return type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public void setType(JLineType type) {
        this.type = type;
    }

    /**
     * 获取width值
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置width值
     *
     * @param width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取shadowColor值
     */
    public String getShadowColor() {
        return shadowColor;
    }

    /**
     * 设置shadowColor值
     *
     * @param shadowColor
     */
    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }

    /**
     * 获取shadowBlur值
     */
    public Integer getShadowBlur() {
        return shadowBlur;
    }

    /**
     * 设置shadowBlur值
     *
     * @param shadowBlur
     */
    public void setShadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    /**
     * 获取shadowOffsetX值
     */
    public Integer getShadowOffsetX() {
        return shadowOffsetX;
    }

    /**
     * 设置shadowOffsetX值
     *
     * @param shadowOffsetX
     */
    public void setShadowOffsetX(Integer shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    /**
     * 获取shadowOffsetY值
     */
    public Integer getShadowOffsetY() {
        return shadowOffsetY;
    }

    /**
     * 设置shadowOffsetY值
     *
     * @param shadowOffsetY
     */
    public void setShadowOffsetY(Integer shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
    }

    /**
     * 获取normal
     *
     * @return
     */
    public JNormal getNormal() {
        return normal;
    }

    /**
     * 设置normal
     *
     * @param normal
     */
    public void setNormal(JNormal normal) {
        this.normal = normal;
    }

    /**
     * 新建返回normal
     *
     * @return
     */
    public JNormal normal() {
        if (this.normal == null) {
            this.normal = new JNormal();
        }
        return this.normal;

    }

    /**
     * 设置normal
     *
     * @param normal
     * @return
     */
    public JLineStyle normal(JNormal normal) {
        this.normal = normal;
        return this;
    }

    /**
     * 获取emphasis
     *
     * @return
     */
    public JEmphasis getEmphasis() {
        return emphasis;
    }

    /**
     * 设置emphasis
     *
     * @param emphasis
     */
    public void setEmphasis(JEmphasis emphasis) {
        this.emphasis = emphasis;
    }

    /**
     * 新建返回emphasis
     *
     * @return
     */
    public JEmphasis emphasis() {
        if (this.emphasis == null) {
            this.emphasis = new JEmphasis();
        }
        return this.emphasis;

    }

    /**
     * 设置emphasis
     *
     * @param emphasis
     * @return
     */
    public JLineStyle emphasis(JEmphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    public Double opacity() {
        return this.opacity;
    }

    public JLineStyle opacity(Double opacity) {
        this.opacity = opacity;
        return this;
    }
}

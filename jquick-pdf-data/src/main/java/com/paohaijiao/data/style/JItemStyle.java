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

import com.paohaijiao.data.style.itemstyle.JEmphasis;
import com.paohaijiao.data.style.itemstyle.JNormal;

import java.io.Serializable;

/**
 * Description: ItemStyle
 *
 * @author martin
 */
public class JItemStyle implements Serializable {

    private static final long serialVersionUID = 418674375057055357L;

    /**
     * 阳线颜色
     */
    private Object color;
    /**
     * 阴线颜色
     */
    private Object color0;

    /**
     * 默认样式
     */
    private JNormal normal;
    /**
     * 强调样式（悬浮时样式）
     */
    private JEmphasis emphasis;
    /**
     * 面包屑
     */
    private JBreadcrumb breadcrumb;
    /**
     * 二级边框宽度
     */
    private Integer childBorderWidth;
    /**
     * 二级边框颜色
     */
    private Object childBorderColor;

    /**
     * 获取normal值
     */
    public JNormal normal() {
        if (this.normal == null) {
            this.normal = new JNormal();
        }
        return this.normal;
    }

    /**
     * 设置normal值
     *
     * @param normal
     */
    public JItemStyle normal(JNormal normal) {
        this.normal = normal;
        return this;
    }

    /**
     * 获取emphasis值
     */
    public JEmphasis emphasis() {
        if (this.emphasis == null) {
            this.emphasis = new JEmphasis();
        }
        return this.emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public JItemStyle emphasis(JEmphasis emphasis) {
        this.emphasis = emphasis;
        return this;
    }

    /**
     * 获取normal值
     */
    public JNormal getNormal() {
        return normal;
    }

    //以下属性是TreeMap特有

    /**
     * 设置normal值
     *
     * @param normal
     */
    public void setNormal(JNormal normal) {
        this.normal = normal;
    }

    /**
     * 获取emphasis值
     */
    public JEmphasis getEmphasis() {
        return emphasis;
    }

    /**
     * 设置emphasis值
     *
     * @param emphasis
     */
    public void setEmphasis(JEmphasis emphasis) {
        this.emphasis = emphasis;
    }

    /**
     * 设置breadcrumb值
     *
     * @param breadcrumb
     */
    public JItemStyle breadcrumb(JBreadcrumb breadcrumb) {
        this.breadcrumb = breadcrumb;
        return this;
    }

    /**
     * 获取breadcrumb值
     */
    public JBreadcrumb breadcrumb() {
        if (this.breadcrumb == null) {
            this.breadcrumb = new JBreadcrumb();
        }
        return this.breadcrumb;
    }

    /**
     * 设置childBorderWidth值
     *
     * @param childBorderWidth
     */
    public JItemStyle childBorderWidth(Integer childBorderWidth) {
        this.childBorderWidth = childBorderWidth;
        return this;
    }

    /**
     * 获取childBorderWidth值
     */
    public Integer childBorderWidth() {
        return this.childBorderWidth;
    }

    /**
     * 设置childBorderColor值
     *
     * @param childBorderColor
     */
    public JItemStyle childBorderColor(Object childBorderColor) {
        this.childBorderColor = childBorderColor;
        return this;
    }

    /**
     * 获取childBorderColor值
     */
    public Object childBorderColor() {
        return this.childBorderColor;
    }

    /**
     * 获取breadcrumb值
     */
    public JBreadcrumb getBreadcrumb() {
        return breadcrumb;
    }

    /**
     * 设置breadcrumb值
     *
     * @param breadcrumb
     */
    public void setBreadcrumb(JBreadcrumb breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    /**
     * 获取childBorderWidth值
     */
    public Integer getChildBorderWidth() {
        return childBorderWidth;
    }

    /**
     * 设置childBorderWidth值
     *
     * @param childBorderWidth
     */
    public void setChildBorderWidth(Integer childBorderWidth) {
        this.childBorderWidth = childBorderWidth;
    }

    /**
     * 获取childBorderColor值
     */
    public Object getChildBorderColor() {
        return childBorderColor;
    }

    /**
     * 设置childBorderColor值
     *
     * @param childBorderColor
     */
    public void setChildBorderColor(Object childBorderColor) {
        this.childBorderColor = childBorderColor;
    }

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
    public JItemStyle color(Object color) {
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
    public JItemStyle color0(Object color0) {
        this.color0 = color0;
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
}

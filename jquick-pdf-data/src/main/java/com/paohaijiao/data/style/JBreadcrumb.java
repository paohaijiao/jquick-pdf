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

import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.code.JY;

import java.io.Serializable;

/**
 * 面包屑
 *
 * @author martin
 */
public class JBreadcrumb implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean show;
    /**
     * treemap 组件离容器左侧的距离
     */
    private Object left;
    /**
     * treemap 组件离容器上侧的距离
     */
    private Object top;
    /**
     * treemap 组件离容器右侧的距离
     */
    private Object right;
    /**
     * treemap 组件离容器下侧的距离
     */
    private Object bottom;
    /**
     * treemap 组件的宽度
     */
    private Object width;
    /**
     * treemap 组件的高度
     */
    private Object height;
    /**
     * 图形样式
     *
     * @see JItemStyle
     */
    private JItemStyle itemStyle;
    /**
     * 样式
     */
    private JTextStyle textStyle;

    /**
     * 构造函数
     */
    public JBreadcrumb() {
    }

    /**
     * 构造函数,参数:show
     *
     * @param show
     */
    public JBreadcrumb(Boolean show) {
        this.show = show;
    }

    /**
     * 构造函数,参数:show,textStyle
     *
     * @param show
     * @param textStyle
     */
    public JBreadcrumb(Boolean show, JTextStyle textStyle) {
        this.show = show;
        this.textStyle = textStyle;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    public JBreadcrumb itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public JBreadcrumb show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取show值
     */
    public Boolean show() {
        return this.show;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JBreadcrumb textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
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

    public Object left() {
        return this.left;
    }

    public JBreadcrumb left(Object left) {
        this.left = left;
        return this;
    }

    public JBreadcrumb left(Integer left) {
        this.left = left;
        return this;
    }

    public JBreadcrumb left(JX left) {
        this.left = left;
        return this;
    }

    public Object top() {
        return this.top;
    }

    public JBreadcrumb top(Object top) {
        this.top = top;
        return this;
    }

    public JBreadcrumb top(Integer top) {
        this.top = top;
        return this;
    }

    public JBreadcrumb top(JY top) {
        this.top = top;
        return this;
    }

    public Object right() {
        return this.right;
    }

    public JBreadcrumb right(Object right) {
        this.right = right;
        return this;
    }

    public JBreadcrumb right(Integer right) {
        this.right = right;
        return this;
    }

    public Object bottom() {
        return this.bottom;
    }

    public JBreadcrumb bottom(Object bottom) {
        this.bottom = bottom;
        return this;
    }

    public JBreadcrumb bottom(Integer bottom) {
        this.bottom = bottom;
        return this;
    }

    public Object width() {
        return this.width;
    }

    public JBreadcrumb width(Object width) {
        this.width = width;
        return this;
    }

    public JBreadcrumb width(Integer width) {
        this.width = width;
        return this;
    }

    public Object height() {
        return this.height;
    }

    public JBreadcrumb height(Object height) {
        this.height = height;
        return this;
    }

    public JBreadcrumb height(Integer height) {
        this.height = height;
        return this;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getTop() {
        return top;
    }

    public void setTop(Object top) {
        this.top = top;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }

    public Object getBottom() {
        return bottom;
    }

    public void setBottom(Object bottom) {
        this.bottom = bottom;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public JItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public JTextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
    }
}

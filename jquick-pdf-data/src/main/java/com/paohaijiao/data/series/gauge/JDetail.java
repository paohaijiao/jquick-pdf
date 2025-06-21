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

import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 仪表盘详情
 *
 * @author martin
 */
@Getter
@Setter
public class JDetail implements Serializable {

    private static final long serialVersionUID = 4155443904476463247L;

    /**
     * 属性show控制显示与否
     */
    private Boolean show;
    /**
     * 标题背景颜色，默认透明
     */
    private String backgroundColor;
    /**
     * 标题边框颜色
     */
    private String borderColor;
    /**
     * borderWidth
     */
    private Integer borderWidth;
    /**
     * 属性width控制详情宽度
     */
    private Object width;
    /**
     * 属性height控制详情高度
     */
    private Object height;
    /**
     * 属性offsetCenter用于详情定位，数组为横纵相对仪表盘圆心坐标偏移，支持百分比（相对外半径）
     */
    private Object offsetCenter;
    /**
     * 属性formatter可以格式化文本
     */
    private String formatter;
    /**
     * 属性textStyle（详见textStyle）控制文本样式
     */
    private JTextStyle textStyle;

    /**
     * 获取show值
     */
    public Boolean show() {
        return this.show;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public JDetail show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取backgroundColor值
     */
    public String backgroundColor() {
        return this.backgroundColor;
    }

    /**
     * 设置backgroundColor值
     *
     * @param backgroundColor
     */
    public JDetail backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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
    public JDetail borderColor(String borderColor) {
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
    public JDetail borderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
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
    public JDetail width(Object width) {
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
    public JDetail height(Object height) {
        this.height = height;
        return this;
    }

    /**
     * 获取offsetCenter值
     */
    public Object offsetCenter() {
        return this.offsetCenter;
    }

    /**
     * 设置offsetCenter值
     *
     * @param offsetCenter
     */
    public JDetail offsetCenter(Object offsetCenter) {
        this.offsetCenter = offsetCenter;
        return this;
    }

    /**
     * 获取formatter值
     */
    public String formatter() {
        return this.formatter;
    }

    /**
     * 设置formatter值
     *
     * @param formatter
     */
    public JDetail formatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    /**
     * 属性textStyle（详见textStyle）控制文本样式
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    /**
     * 获取textStyle值
     */
    public JTextStyle getTextStyle() {
        return textStyle;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public void setTextStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
    }

    /**
     * 获取show值
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public void setShow(Boolean show) {
        this.show = show;
    }

    /**
     * 获取backgroundColor值
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置backgroundColor值
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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
     * 获取height值
     */
    public Object getHeight() {
        return height;
    }

    /**
     * 设置height值
     *
     * @param height
     */
    public void setHeight(Object height) {
        this.height = height;
    }

    /**
     * 获取offsetCenter值
     */
    public Object getOffsetCenter() {
        return offsetCenter;
    }

    /**
     * 设置offsetCenter值
     *
     * @param offsetCenter
     */
    public void setOffsetCenter(Object offsetCenter) {
        this.offsetCenter = offsetCenter;
    }

    /**
     * 获取formatter值
     */
    public String getFormatter() {
        return formatter;
    }

    /**
     * 设置formatter值
     *
     * @param formatter
     */
    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}

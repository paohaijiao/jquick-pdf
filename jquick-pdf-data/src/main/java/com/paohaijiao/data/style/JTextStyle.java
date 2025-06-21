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

import com.paohaijiao.data.code.JBaseline;
import com.paohaijiao.data.code.JFontStyle;
import com.paohaijiao.data.code.JFontWeight;
import com.paohaijiao.data.code.JX;

import java.io.Serializable;

/**
 * 文字样式
 *
 * @author martin
 */
public class JTextStyle implements Serializable {

    private static final long serialVersionUID = 5748410562515851843L;

    /**
     * 颜色
     */
    private String color;
    /**
     * 水平对齐方式，可选为：'left' | 'right' | 'center'
     *
     * @see JX
     */
    private JX align;
    /**
     * 垂直对齐方式
     */
    private JBaseline baseline;
    /**
     * 修饰，仅对tooltip.textStyle生效
     */
    private String decoration;
    /**
     * 字号 ，单位px
     */
    private Integer fontSize;
    /**
     * 字体系列
     */
    private String fontFamily;
    /**
     * 字体系列
     * IE8- 字体模糊并且，不支持不同字体混排，额外指定一份
     */
    private String fontFamily2;
    /**
     * 样式，可选为：'normal' | 'italic' | 'oblique'
     */
    private JFontStyle fontStyle;
    /**
     * 粗细，可选为：'normal' | 'bold' | 'bolder' | 'lighter' | 100 | 200 |... | 900
     *
     * @see JFontWeight
     */
    private Object fontWeight;
    /**
     * 背景色
     */
    private String backgroundColor;
    /**
     * 文字块的圆角
     */
    private Object borderRadius;
    /**
     * 边距
     */
    private Object padding;


    /**
     * 获取baseline值
     */
    public JBaseline baseline() {
        return this.baseline;
    }

    /**
     * 设置baseline值
     *
     * @param baseline
     */
    public JTextStyle baseline(JBaseline baseline) {
        this.baseline = baseline;
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
    public JTextStyle color(String color) {
        this.color = color;
        return this;
    }

    /**
     * 获取decoration值
     */
    public String decoration() {
        return this.decoration;
    }

    /**
     * 设置decoration值
     *
     * @param decoration
     */
    public JTextStyle decoration(String decoration) {
        this.decoration = decoration;
        return this;
    }

    /**
     * 获取align值
     */
    public JX align() {
        return this.align;
    }

    /**
     * 设置align值
     *
     * @param align
     */
    public JTextStyle align(JX align) {
        this.align = align;
        return this;
    }

    /**
     * 获取fontSize值
     */
    public Integer fontSize() {
        return this.fontSize;
    }

    /**
     * 设置fontSize值
     *
     * @param fontSize
     */
    public JTextStyle fontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * 获取fontFamily值
     */
    public String fontFamily() {
        return this.fontFamily;
    }

    /**
     * 设置fontFamily值
     *
     * @param fontFamily
     */
    public JTextStyle fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    /**
     * 获取fontFamily2值
     */
    public String fontFamily2() {
        return this.fontFamily2;
    }

    /**
     * 设置fontFamily2值
     *
     * @param fontFamily2
     */
    public JTextStyle fontFamily2(String fontFamily2) {
        this.fontFamily2 = fontFamily2;
        return this;
    }

    /**
     * 获取fontStyle值
     */
    public JFontStyle fontStyle() {
        return this.fontStyle;
    }

    /**
     * 设置fontStyle值
     *
     * @param fontStyle
     */
    public JTextStyle fontStyle(JFontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    /**
     * 获取fontWeight值
     */
    public Object fontWeight() {
        return this.fontWeight;
    }

    /**
     * 设置fontWeight值
     *
     * @param fontWeight
     */
    public JTextStyle fontWeight(Object fontWeight) {
        this.fontWeight = fontWeight;
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
     * 获取decoration值
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * 设置decoration值
     *
     * @param decoration
     */
    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    /**
     * 获取align值
     */
    public JX getAlign() {
        return align;
    }

    /**
     * 设置align值
     *
     * @param align
     */
    public void setAlign(JX align) {
        this.align = align;
    }

    /**
     * 获取fontSize值
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * 设置fontSize值
     *
     * @param fontSize
     */
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * 获取fontFamily值
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * 设置fontFamily值
     *
     * @param fontFamily
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * 获取fontFamily2值
     */
    public String getFontFamily2() {
        return fontFamily2;
    }

    /**
     * 设置fontFamily2值
     *
     * @param fontFamily2
     */
    public void setFontFamily2(String fontFamily2) {
        this.fontFamily2 = fontFamily2;
    }

    /**
     * 获取fontStyle值
     */
    public JFontStyle getFontStyle() {
        return fontStyle;
    }

    /**
     * 设置fontStyle值
     *
     * @param fontStyle
     */
    public void setFontStyle(JFontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * 获取fontWeight值
     */
    public Object getFontWeight() {
        return fontWeight;
    }

    /**
     * 设置fontWeight值
     *
     * @param fontWeight
     */
    public void setFontWeight(Object fontWeight) {
        this.fontWeight = fontWeight;
    }

    /**
     * 获取baseline值
     */
    public JBaseline getBaseline() {
        return baseline;
    }

    /**
     * 设置baseline值
     *
     * @param baseline
     */
    public void setBaseline(JBaseline baseline) {
        this.baseline = baseline;
    }

    /**
     * 背景色
     *
     * @param backgroundColor
     * @return
     */
    public JTextStyle backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * 文字块的圆角
     *
     * @param borderRadius
     * @return
     */
    public JTextStyle borderRadius(Object borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }

    /**
     * 边距
     *
     * @param padding
     * @return
     */
    public JTextStyle padding(Object padding) {
        this.padding = padding;
        return this;
    }

}

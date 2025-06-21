package com.paohaijiao.data.style;

import com.paohaijiao.data.code.JFontStyle;
import com.paohaijiao.data.code.JFontWeight;

import java.io.Serializable;

/**
 * 坐标轴名称的文字样式
 *
 * @author martin
 */
public class JNameTextStyle implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 坐标轴名称文字的颜色
     */
    private String color;
    /**
     * 坐标轴名称文字字体的风格
     */
    private JFontStyle fontStyle;
    /**
     * 坐标轴名称文字字体的粗细
     */
    private JFontWeight fontWeight;
    /**
     * 坐标轴名称文字的字体系列
     */
    private String fontFamily;
    /**
     * 坐标轴名称文字的字体大小
     */
    private Double fontSize;

    public String color() {
        return this.color;
    }

    public JNameTextStyle color(String color) {
        this.color = color;
        return this;
    }

    public JFontStyle fontStyle() {
        return this.fontStyle;
    }

    public JNameTextStyle fontStyle(JFontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public JFontWeight fontWeight() {
        return this.fontWeight;
    }

    public JNameTextStyle fontWeight(JFontWeight fontWeight) {
        this.fontWeight = fontWeight;
        return this;
    }

    public String fontFamily() {
        return this.fontFamily;
    }

    public JNameTextStyle fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Double fontSize() {
        return this.fontSize;
    }

    public JNameTextStyle fontSize(Double fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public JFontStyle getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(JFontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public JFontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(JFontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Double getFontSize() {
        return fontSize;
    }

    public void setFontSize(Double fontSize) {
        this.fontSize = fontSize;
    }
}

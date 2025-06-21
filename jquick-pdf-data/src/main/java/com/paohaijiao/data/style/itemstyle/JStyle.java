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

package com.paohaijiao.data.style.itemstyle;

import com.paohaijiao.data.JLabel;
import com.paohaijiao.data.JLabelLine;
import com.paohaijiao.data.code.JPosition;
import com.paohaijiao.data.style.*;

import java.io.Serializable;

/**
 * @author martin
 */
public abstract class JStyle<T> implements Serializable {

    private static final long serialVersionUID = -3920258078225722832L;

    /**
     * 标签，饼图默认显示在外部，离饼图距离由labelLine.length决定，地图标签不可指定位置
     * 折线图，柱形图，K线图，散点图可指定position见下
     *
     * @see JLabel
     */
    private JLabel label;
    /**
     * 饼图标签视觉引导线，默认显示
     *
     * @see JLabelLine
     */
    private JLabelLine labelLine;
    /**
     * 阳线颜色
     */
    private String color;
    /**
     * 阴线颜色
     */
    private String color0;
    /**
     * 线条样式
     *
     * @see JLineStyle
     */
    private JLineStyle lineStyle;
    /**
     * 区域样式
     *
     * @see JAreaStyle
     */
    private JAreaStyle areaStyle;
    /**
     * 和弦图 - 弦样式
     *
     * @see JChordStyle
     */
    private JChordStyle chordStyle;
    /**
     * 力导向图 - 弦样式
     *
     * @see JNodeStyle
     */
    private JNodeStyle nodeStyle;
    /**
     * 力导向图 - 弦样式
     *
     * @see JLinkStyle
     */
    private JLinkStyle linkStyle;
    /**
     * 边框颜色
     */
    private String borderColor;
    /**
     * 边框圆角，单位px，默认为0
     */
    private Integer borderRadius;
    /**
     * 边框线宽，单位px，默认为1
     */
    private Integer borderWidth;
    /**
     * 柱形边框颜色
     */
    private String barBorderColor;
    /**
     * 柱形边框圆角，单位px，默认为0
     */
    private Integer barBorderRadius;
    /**
     * 柱形边框线宽，单位px，默认为1
     */
    private Integer barBorderWidth;
    /**
     * 显示
     */
    private Boolean show;
    /**
     * 图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果
     */
    private Integer shadowBlur;
    /**
     * 阴影颜色
     */
    private String shadowColor;
    /**
     * 阴影水平方向上的偏移距离
     */
    private Integer shadowOffsetX;
    /**
     * 阴影垂直方向上的偏移距离
     */
    private Integer shadowOffsetY;
    /**
     * 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形
     */
    private Double opacity;
    /**
     * 标签内容格式器，支持字符串模板和回调函数两种形式
     */
    private String formatter;
    /**
     * 标签的位置
     */
    private JPosition position;
    /**
     * 标签的字体样式
     */
    private JTextStyle textStyle;

    /**
     * 边的曲度，支持从 0 到 1 的值，值越大曲度越大。
     */
    private Double curveness;

    public JTextStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
    }

    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    public T textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return (T) this;
    }

    public JPosition getPosition() {
        return position;
    }

    public void setPosition(JPosition position) {
        this.position = position;
    }

    public JPosition position() {
        return this.position;
    }

    public T position(JPosition position) {
        this.position = position;
        return (T) this;
    }

    public String formatter() {
        return this.formatter;
    }

    public T formatter(String formatter) {
        this.formatter = formatter;
        return (T) this;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Integer shadowBlur() {
        return this.shadowBlur;
    }

    public T shadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
        return (T) this;
    }

    public String shadowColor() {
        return this.shadowColor;
    }

    public T shadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return (T) this;
    }

    public Integer shadowOffsetX() {
        return this.shadowOffsetX;
    }

    public T shadowOffsetX(Integer shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return (T) this;
    }

    public Integer shadowOffsetY() {
        return this.shadowOffsetY;
    }

    public T shadowOffsetY(Integer shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return (T) this;
    }

    public Double opacity() {
        return this.opacity;
    }

    public T opacity(Double opacity) {
        this.opacity = opacity;
        return (T) this;
    }

    public Integer getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }

    public Integer getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(Integer shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public Integer getShadowOffsetY() {
        return shadowOffsetY;
    }

    public void setShadowOffsetY(Integer shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Boolean show() {
        return this.show;
    }

    public T show(Boolean show) {
        this.show = show;
        return (T) this;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    /**
     * 设置label值
     *
     * @param label
     */
    public T label(JLabel label) {
        this.label = label;
        return (T) this;
    }

    /**
     * 设置labelLine值
     *
     * @param labelLine
     */
    public T labelLine(JLabelLine labelLine) {
        this.labelLine = labelLine;
        return (T) this;
    }

    /**
     * 设置areaStyle值
     *
     * @param areaStyle
     */
    public T areaStyle(JAreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return (T) this;
    }

    /**
     * 设置chordStyle值
     *
     * @param chordStyle
     */
    public T chordStyle(JChordStyle chordStyle) {
        this.chordStyle = chordStyle;
        return (T) this;
    }

    /**
     * 设置nodeStyle值
     *
     * @param nodeStyle
     */
    public T nodeStyle(JNodeStyle nodeStyle) {
        this.nodeStyle = nodeStyle;
        return (T) this;
    }

    /**
     * 设置linkStyle值
     *
     * @param linkStyle
     */
    public T linkStyle(JLinkStyle linkStyle) {
        this.linkStyle = linkStyle;
        return (T) this;
    }

    /**
     * 标签，饼图默认显示在外部，离饼图距离由labelLine.length决定，地图标签不可指定位置
     * 折线图，柱形图，K线图，散点图可指定position见下
     *
     * @see JLabel
     */
    public JLabel label() {
        if (this.label == null) {
            this.label = new JLabel();
        }
        return this.label;
    }

    /**
     * 饼图标签视觉引导线，默认显示
     *
     * @see JLabelLine
     */
    public JLabelLine labelLine() {
        if (this.labelLine == null) {
            this.labelLine = new JLabelLine();
        }
        return this.labelLine;
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
    public T color(String color) {
        this.color = color;
        return (T) this;
    }

    /**
     * 获取color0值
     */
    public String color0() {
        return this.color0;
    }

    /**
     * 设置color0值
     *
     * @param color0
     */
    public T color0(String color0) {
        this.color0 = color0;
        return (T) this;
    }

    /**
     * 设置lineStyle值
     *
     * @param lineStyle
     */
    public T lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return (T) this;
    }

    /**
     * 线条样式
     *
     * @see JLineStyle
     */
    public JLineStyle lineStyle() {
        if (this.lineStyle == null) {
            this.lineStyle = new JLineStyle();
        }
        return this.lineStyle;
    }

    /**
     * 区域样式
     *
     * @see JAreaStyle
     */
    public JAreaStyle areaStyle() {
        if (this.areaStyle == null) {
            this.areaStyle = new JAreaStyle();
        }
        return this.areaStyle;
    }

    /**
     * 和弦图 - 弦样式
     *
     * @see JChordStyle
     */
    public JChordStyle chordStyle() {
        if (this.chordStyle == null) {
            this.chordStyle = new JChordStyle();
        }
        return this.chordStyle;
    }

    /**
     * 力导向图 - 弦样式
     *
     * @see JNodeStyle
     */
    public JNodeStyle nodeStyle() {
        if (this.nodeStyle == null) {
            this.nodeStyle = new JNodeStyle();
        }
        return this.nodeStyle;
    }

    /**
     * 力导向图 - 弦样式
     *
     * @see JLinkStyle
     */
    public JLinkStyle linkStyle() {
        if (this.linkStyle == null) {
            this.linkStyle = new JLinkStyle();
        }
        return this.linkStyle;
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
    public T borderColor(String borderColor) {
        this.borderColor = borderColor;
        return (T) this;
    }

    /**
     * 获取borderRadius值
     */
    public Integer borderRadius() {
        return this.borderRadius;
    }

    /**
     * 设置borderRadius值
     *
     * @param borderRadius
     */
    public T borderRadius(Integer borderRadius) {
        this.borderRadius = borderRadius;
        return (T) this;
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
    public T borderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
        return (T) this;
    }

    /**
     * 获取barBorderColor值
     */
    public String barBorderColor() {
        return this.barBorderColor;
    }

    /**
     * 设置barBorderColor值
     *
     * @param barBorderColor
     */
    public T barBorderColor(String barBorderColor) {
        this.barBorderColor = barBorderColor;
        return (T) this;
    }

    /**
     * 获取barBorderRadius值
     */
    public Integer barBorderRadius() {
        return this.barBorderRadius;
    }

    /**
     * 设置barBorderRadius值
     *
     * @param barBorderRadius
     */
    public T barBorderRadius(Integer barBorderRadius) {
        this.barBorderRadius = barBorderRadius;
        return (T) this;
    }

    /**
     * 获取barBorderWidth值
     */
    public Integer barBorderWidth() {
        return this.barBorderWidth;
    }

    /**
     * 设置barBorderWidth值
     *
     * @param barBorderWidth
     */
    public T barBorderWidth(Integer barBorderWidth) {
        this.barBorderWidth = barBorderWidth;
        return (T) this;
    }

    /**
     * 获取label值
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * 设置label值
     *
     * @param label
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * 获取labelLine值
     */
    public JLabelLine getLabelLine() {
        return labelLine;
    }

    /**
     * 设置labelLine值
     *
     * @param labelLine
     */
    public void setLabelLine(JLabelLine labelLine) {
        this.labelLine = labelLine;
    }

    /**
     * 获取areaStyle值
     */
    public JAreaStyle getAreaStyle() {
        return areaStyle;
    }

    /**
     * 设置areaStyle值
     *
     * @param areaStyle
     */
    public void setAreaStyle(JAreaStyle areaStyle) {
        this.areaStyle = areaStyle;
    }

    /**
     * 获取chordStyle值
     */
    public JChordStyle getChordStyle() {
        return chordStyle;
    }

    /**
     * 设置chordStyle值
     *
     * @param chordStyle
     */
    public void setChordStyle(JChordStyle chordStyle) {
        this.chordStyle = chordStyle;
    }

    /**
     * 获取nodeStyle值
     */
    public JNodeStyle getNodeStyle() {
        return nodeStyle;
    }

    /**
     * 设置nodeStyle值
     *
     * @param nodeStyle
     */
    public void setNodeStyle(JNodeStyle nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    /**
     * 获取linkStyle值
     */
    public JLinkStyle getLinkStyle() {
        return linkStyle;
    }

    /**
     * 设置linkStyle值
     *
     * @param linkStyle
     */
    public void setLinkStyle(JLinkStyle linkStyle) {
        this.linkStyle = linkStyle;
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
     * 获取color0值
     */
    public String getColor0() {
        return color0;
    }

    /**
     * 设置color0值
     *
     * @param color0
     */
    public void setColor0(String color0) {
        this.color0 = color0;
    }

    /**
     * 获取lineStyle值
     */
    public JLineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * 设置lineStyle值
     *
     * @param lineStyle
     */
    public void setLineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
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
     * 获取borderRadius值
     */
    public Integer getBorderRadius() {
        return borderRadius;
    }

    /**
     * 设置borderRadius值
     *
     * @param borderRadius
     */
    public void setBorderRadius(Integer borderRadius) {
        this.borderRadius = borderRadius;
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
     * 获取barBorderColor值
     */
    public String getBarBorderColor() {
        return barBorderColor;
    }

    /**
     * 设置barBorderColor值
     *
     * @param barBorderColor
     */
    public void setBarBorderColor(String barBorderColor) {
        this.barBorderColor = barBorderColor;
    }

    /**
     * 获取barBorderRadius值
     */
    public Integer getBarBorderRadius() {
        return barBorderRadius;
    }

    /**
     * 设置barBorderRadius值
     *
     * @param barBorderRadius
     */
    public void setBarBorderRadius(Integer barBorderRadius) {
        this.barBorderRadius = barBorderRadius;
    }

    /**
     * 获取barBorderWidth值
     */
    public Integer getBarBorderWidth() {
        return barBorderWidth;
    }

    /**
     * 设置barBorderWidth值
     *
     * @param barBorderWidth
     */
    public void setBarBorderWidth(Integer barBorderWidth) {
        this.barBorderWidth = barBorderWidth;
    }

    /**
     * 设置curveness
     *
     * @param curveness
     */
    public void setCurveness(Double curveness) {
        this.curveness = curveness;
    }

    /**
     * 获取curveness
     *
     * @return
     */
    public Double getCurveness() {
        return curveness;
    }

    /**
     * 设置curveness ，返回T对象
     *
     * @param curveness
     * @return
     */
    public T curveness(Double curveness) {
        this.curveness = curveness;
        return (T) this;
    }
}

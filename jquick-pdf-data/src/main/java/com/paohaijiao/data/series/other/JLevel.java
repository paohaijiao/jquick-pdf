package com.paohaijiao.data.series.other;

import com.paohaijiao.data.code.JColorMappingBy;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 多层配置 - treemap
 *
 * @author martin
 */
@Getter
@Setter
public class JLevel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * treemap 中支持对数据其他维度进行视觉映射
     */
    private Integer visualDimension;
    /**
     * 表示同一层级的节点的 颜色 选取列表。默认为空时，选取系统color列表
     */
    private Object[] color;
    /**
     * 表示同一层级的节点的 颜色透明度 选取范围。数值范围 0 ~ 1
     */
    private Object[] colorAlpha;
    /**
     * 表示同一层级的节点的 颜色饱和度 选取范围。数值范围 0 ~ 1
     */
    private Double colorSaturation;
    /**
     * 表示同一层级节点，在颜色列表中（参见 color 属性）选择时，按照什么来选择
     */
    private Object colorMappingBy;
    /**
     * 如果某个节点的矩形的面积，小于这个数值（单位：px平方），这个节点就不显示
     */
    private Double visibleMin;
    /**
     * 如果某个节点的矩形面积，小于这个数值（单位：px平方），则不显示这个节点的子节点
     */
    private Double childrenVisibleMin;
    /**
     * 图形上的文本标签，课用于说明图形的一些数据信息，比如值，名称等，label选项在 ECharts 2.x 中放置于itemStyle.normal下，在 ECharts 3 中为了让整个配置项结构更扁平合理，label被拿出来跟 itemStyle 平级，并且跟 itemStyle 一样拥有 normal, emphasis 两个状态
     */
    private JItemStyle label;
    /**
     * 图形样式
     *
     * @see JItemStyle
     */
    private JItemStyle itemStyle;

    public JItemStyle label() {
        if (this.label == null) {
            this.label = new JItemStyle();
        }
        return this.label;
    }

    public JLevel label(JItemStyle label) {
        this.label = label;
        return this;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    public JLevel itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }


    public Integer visualDimension() {
        return this.visualDimension;
    }

    public JLevel visualDimension(Integer visualDimension) {
        this.visualDimension = visualDimension;
        return this;
    }

    public Object[] color() {
        return this.color;
    }

    public JLevel color(Object[] color) {
        this.color = color;
        return this;
    }

    public Object[] colorAlpha() {
        return this.colorAlpha;
    }

    public JLevel colorAlpha(Object[] colorAlpha) {
        this.colorAlpha = colorAlpha;
        return this;
    }

    public Double colorSaturation() {
        return this.colorSaturation;
    }

    public JLevel colorSaturation(Double colorSaturation) {
        this.colorSaturation = colorSaturation;
        return this;
    }

    public Object colorMappingBy() {
        return this.colorMappingBy;
    }

    public JLevel colorMappingBy(Object colorMappingBy) {
        this.colorMappingBy = colorMappingBy;
        return this;
    }

    public JLevel colorMappingBy(JColorMappingBy colorMappingBy) {
        this.colorMappingBy = colorMappingBy;
        return this;
    }

    public Double visibleMin() {
        return this.visibleMin;
    }

    public JLevel visibleMin(Double visibleMin) {
        this.visibleMin = visibleMin;
        return this;
    }

    public Double childrenVisibleMin() {
        return this.childrenVisibleMin;
    }

    public JLevel childrenVisibleMin(Double childrenVisibleMin) {
        this.childrenVisibleMin = childrenVisibleMin;
        return this;
    }

    public Integer getVisualDimension() {
        return visualDimension;
    }

    public void setVisualDimension(Integer visualDimension) {
        this.visualDimension = visualDimension;
    }

    public Object[] getColor() {
        return color;
    }

    public void setColor(Object[] color) {
        this.color = color;
    }

    public Object[] getColorAlpha() {
        return colorAlpha;
    }

    public void setColorAlpha(Object[] colorAlpha) {
        this.colorAlpha = colorAlpha;
    }

    public Double getColorSaturation() {
        return colorSaturation;
    }

    public void setColorSaturation(Double colorSaturation) {
        this.colorSaturation = colorSaturation;
    }

    public Object getColorMappingBy() {
        return colorMappingBy;
    }

    public void setColorMappingBy(Object colorMappingBy) {
        this.colorMappingBy = colorMappingBy;
    }

    public Double getVisibleMin() {
        return visibleMin;
    }

    public void setVisibleMin(Double visibleMin) {
        this.visibleMin = visibleMin;
    }

    public Double getChildrenVisibleMin() {
        return childrenVisibleMin;
    }

    public void setChildrenVisibleMin(Double childrenVisibleMin) {
        this.childrenVisibleMin = childrenVisibleMin;
    }

    public JItemStyle getLabel() {
        return label;
    }

    public void setLabel(JItemStyle label) {
        this.label = label;
    }

    public JItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}

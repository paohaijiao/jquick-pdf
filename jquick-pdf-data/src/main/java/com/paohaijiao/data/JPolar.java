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

package com.paohaijiao.data;

import com.paohaijiao.data.axis.JAxisLabel;
import com.paohaijiao.data.axis.JAxisLine;
import com.paohaijiao.data.axis.JSplitArea;
import com.paohaijiao.data.axis.JSplitLine;
import com.paohaijiao.data.code.JPolarType;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 极坐标
 *
 * @author martin
 */
@Getter
@Setter
public class JPolar extends JAbstractData<JPolar> implements JComponent {
    /**
     * 圆心坐标，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
     */
    private Object[] center;
    /**
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%，
     * 传数组实现环形图，[内半径，外半径]
     */
    private Object radius;
    /**
     * 开始角度, 有效输入范围：[-180,180]
     */
    private Integer startAngle;
    /**
     * 分割段数，默认为5
     */
    private Integer splitNumber;
    /**
     * 坐标轴名称
     */
    private Name name;
    /**
     * 数值轴两端空白策略，数组内数值代表百分比，[原始数据最小值与最终最小值之间的差额，原始数据最大值与最终最大值之间的差额]
     */
    private Object[] boundaryGap;
    /**
     * 脱离0值比例，放大聚焦到最终_min，_max区间
     */
    private Boolean scale;
    /**
     * 小数精度，默认为0，无小数点
     */
    private Integer precision;
    /**
     * 整数精度，默认为100，个位和百位为0
     */
    private Integer power;
    /**
     * 坐标轴线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisLine
     */
    private JAxisLine axisLine;
    /**
     * 坐标轴文本标签，详见axis.axisLabel
     *
     * @see JAxisLabel
     */
    private JAxisLabel axisLabel;
    /**
     * 分隔区域，默认不显示，属性show控制显示与否，属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see JSplitArea
     */
    private JSplitArea splitArea;
    /**
     * 分隔线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JSplitLine
     */
    private JSplitLine splitLine;
    /**
     * 极坐标的形状，'polygon'|'circle' 多边形|圆形
     */
    private JPolarType type;
    /**
     * 雷达指标列表，同时也是label内容
     */
    private List<Object> indicator;
    /**
     * 一级层叠控制
     */
    private Integer zlevel;
    /**
     * 二级层叠控制
     */
    private Integer z;

    /**
     * 设置zlevel值
     *
     * @param zlevel
     */
    public JPolar zlevel(Integer zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    /**
     * 获取zlevel值
     */
    public Integer zlevel() {
        return this.zlevel;
    }

    /**
     * 设置z值
     *
     * @param z
     */
    public JPolar z(Integer z) {
        this.z = z;
        return this;
    }

    /**
     * 获取z值
     */
    public Integer z() {
        return this.z;
    }
    /**
     * 获取center值
     */
    public Object[] center() {
        return this.center;
    }

    /**
     * 设置center值
     *
     * @param center
     */
    public JPolar center(Object[] center) {
        this.center = center;
        return this;
    }

    /**
     * 设置values值
     *
     * @param values
     */
    public JPolar indicator(Object... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.indicator().addAll(Arrays.asList(values));
        return this;
    }

    /**
     * 获取radius值
     */
    public Object radius() {
        return this.radius;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public JPolar name(Name name) {
        this.name = name;
        return this;
    }

    /**
     * 获取boundaryGap值
     */
    public Object[] boundaryGap() {
        return this.boundaryGap;
    }

    /**
     * 设置boundaryGap值
     *
     * @param boundaryGap
     */
    public JPolar boundaryGap(Object[] boundaryGap) {
        this.boundaryGap = boundaryGap;
        return this;
    }

    /**
     * 设置axisLine值
     *
     * @param axisLine
     */
    public JPolar axisLine(JAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    /**
     * 设置axisLabel值
     *
     * @param axisLabel
     */
    public JPolar axisLabel(JAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    /**
     * 设置splitArea值
     *
     * @param splitArea
     */
    public JPolar splitArea(JSplitArea splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    /**
     * 设置splitLine值
     *
     * @param splitLine
     */
    public JPolar splitLine(JSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    /**
     * 设置indicator值
     *
     * @param indicator
     */
    public JPolar indicator(List<Object> indicator) {
        this.indicator = indicator;
        return this;
    }

    /**
     * 圆心坐标，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
     *
     * @param width
     * @param height
     * @return
     */
    public JPolar center(Object width, Object height) {
        this.center = new Object[]{width, height};
        return this;
    }

    /**
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%，
     * 传数组实现环形图，[内半径，外半径]
     *
     * @param value
     * @return
     */
    public JPolar radius(Object value) {
        this.radius = value;
        return this;
    }

    /**
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%，
     * 传数组实现环形图，[内半径，外半径]
     *
     * @param width
     * @param height
     * @return
     */
    public JPolar radius(Object width, Object height) {
        radius = new Object[]{width, height};
        return this;
    }

    /**
     * 获取startAngle值
     */
    public Integer startAngle() {
        return this.startAngle;
    }

    /**
     * 设置startAngle值
     *
     * @param startAngle
     */
    public JPolar startAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    /**
     * 获取splitNumber值
     */
    public Integer splitNumber() {
        return this.splitNumber;
    }

    /**
     * 设置splitNumber值
     *
     * @param splitNumber
     */
    public JPolar splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    /**
     * 坐标轴名称
     */
    public Name name() {
        if (this.name == null) {
            this.name = new Name();
        }
        return this.name;
    }

    /**
     * 数值轴两端空白策略，数组内数值代表百分比，[原始数据最小值与最终最小值之间的差额，原始数据最大值与最终最大值之间的差额]
     */
    public JPolar boundaryGap(Object min, Object max) {
        this.boundaryGap = new Object[]{min, max};
        return this;
    }

    /**
     * 获取scale值
     */
    public Boolean scale() {
        return this.scale;
    }

    /**
     * 设置scale值
     *
     * @param scale
     */
    public JPolar scale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    /**
     * 获取precision值
     */
    public Integer precision() {
        return this.precision;
    }

    /**
     * 设置precision值
     *
     * @param precision
     */
    public JPolar precision(Integer precision) {
        this.precision = precision;
        return this;
    }

    /**
     * 获取power值
     */
    public Integer power() {
        return this.power;
    }

    /**
     * 设置power值
     *
     * @param power
     */
    public JPolar power(Integer power) {
        this.power = power;
        return this;
    }

    /**
     * 坐标轴线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisLine
     */
    public JAxisLine axisLine() {
        if (this.axisLine == null) {
            this.axisLine = new JAxisLine();
        }
        return this.axisLine;
    }

    /**
     * 坐标轴文本标签，详见axis.axisLabel
     *
     * @see JAxisLabel
     */
    public JAxisLabel axisLabel() {
        if (this.axisLabel == null) {
            this.axisLabel = new JAxisLabel();
        }
        return this.axisLabel;
    }

    /**
     * 分隔区域，默认不显示，属性show控制显示与否，属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see JSplitArea
     */
    public JSplitArea splitArea() {
        if (this.splitArea == null) {
            this.splitArea = new JSplitArea();
        }
        return this.splitArea;
    }

    /**
     * 分隔线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JSplitLine
     */
    public JSplitLine splitLine() {
        if (this.splitLine == null) {
            this.splitLine = new JSplitLine();
        }
        return this.splitLine;
    }

    /**
     * 获取type值
     */
    public JPolarType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JPolar type(JPolarType type) {
        this.type = type;
        return this;
    }

    /**
     * 雷达指标列表，同时也是label内容
     */
    public List<Object> indicator() {
        if (this.indicator == null) {
            this.indicator = new ArrayList<Object>();
        }
        return this.indicator;
    }

    @Setter
    @Getter
    public static class Name {
        private Boolean show;
        private JTextStyle textStyle;

        /**
         * 构造函数
         */
        public Name() {
            this.show(true);
            this.textStyle(new JTextStyle());
            this.textStyle.color("#333");
        }

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
        public Name show(Boolean show) {
            this.show = show;
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

        /**
         * 设置textStyle值
         *
         * @param textStyle
         */
        public Name textStyle(JTextStyle textStyle) {
            this.textStyle = textStyle;
            return this;
        }
    }
}

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

package com.paohaijiao.data.series;

import com.paohaijiao.data.JLabel;
import com.paohaijiao.data.JTitle;
import com.paohaijiao.data.axis.JAxisLine;
import com.paohaijiao.data.axis.JAxisTick;
import com.paohaijiao.data.axis.JSplitLine;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.series.gauge.JDetail;
import com.paohaijiao.data.series.gauge.JPointer;
import lombok.Getter;
import lombok.Setter;

/**
 * 仪表盘
 *
 * @author martin
 */
@Getter
@Setter
public class JGauge extends JSeries<JGauge> {
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
     * 开始角度, 饼图（90）、仪表盘（225），有效输入范围：[-360,360]
     */
    private Integer startAngle;
    /**
     * 结束角度,有效输入范围：[-360,360]，保证startAngle - endAngle为正值
     */
    private Integer endAngle;
    /**
     * 指定的最小值
     */
    private Integer min;
    /**
     * 指定的最大值
     */
    private Integer max;
    /**
     * 分割段数，默认为5，为0时为线性渐变，calculable为true是默认均分100份
     */
    private Integer splitNumber;
    /**
     * 坐标轴线，默认显示
     *
     * @see JLine
     */
    private JAxisLine axisLine;
    /**
     * 坐标轴小标记，默认显示
     *
     * @see JAxisTick
     */
    private JAxisTick axisTick;
    /**
     * 坐标轴文本标签
     *
     * @see JLabel
     */
    private JLabel axisLabel;
    /**
     * 主分隔线，默认显示
     *
     * @see JSplitLine
     */
    private JSplitLine splitLine;
    /**
     * 指针样式
     *
     * @see JPointer
     */
    private JPointer pointer;
    /**
     * 仪表盘标题
     *
     * @see JTitle
     */
    private JTitle title;
    /**
     * 仪表盘详情
     *
     * @see JDetail
     */
    private JDetail detail;

    /**
     * 构造函数
     */
    public JGauge() {
        this.type(JSeriesType.gauge);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JGauge(String name) {
        super(name);
        this.type(JSeriesType.gauge);
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
    public JGauge center(Object[] center) {
        this.center = center;
        return this;
    }

    /**
     * 获取radius值
     */
    public Object radius() {
        return this.radius;
    }

    /**
     * 设置axisLine值
     *
     * @param axisLine
     */
    public JGauge axisLine(JAxisLine axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    /**
     * 设置axisTick值
     *
     * @param axisTick
     */
    public JGauge axisTick(JAxisTick axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    /**
     * 设置axisLabel值
     *
     * @param axisLabel
     */
    public JGauge axisLabel(JLabel axisLabel) {
        this.axisLabel = axisLabel;
        return this;
    }

    /**
     * 设置splitLine值
     *
     * @param splitLine
     */
    public JGauge splitLine(JSplitLine splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    /**
     * 设置pointer值
     *
     * @param pointer
     */
    public JGauge pointer(JPointer pointer) {
        this.pointer = pointer;
        return this;
    }

    /**
     * 设置title值
     *
     * @param title
     */
    public JGauge title(JTitle title) {
        this.title = title;
        return this;
    }

    /**
     * 设置detail值
     *
     * @param detail
     */
    public JGauge detail(JDetail detail) {
        this.detail = detail;
        return this;
    }

    /**
     * 圆心坐标，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
     */
    public JGauge center(Object width, Object height) {
        this.center = new Object[]{width, height};
        return this;
    }

    /**
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%，
     * 传数组实现环形图，[内半径，外半径]
     *
     * @param radius
     * @return
     */
    public JGauge radius(Object radius) {
        this.radius = radius;
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
    public JGauge radius(Object width, Object height) {
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
    public JGauge startAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    /**
     * 获取endAngle值
     */
    public Integer endAngle() {
        return this.endAngle;
    }

    /**
     * 设置endAngle值
     *
     * @param endAngle
     */
    public JGauge endAngle(Integer endAngle) {
        this.endAngle = endAngle;
        return this;
    }

    /**
     * 获取min值
     */
    public Integer min() {
        return this.min;
    }

    /**
     * 设置min值
     *
     * @param min
     */
    public JGauge min(Integer min) {
        this.min = min;
        return this;
    }

    /**
     * 获取max值
     */
    public Integer max() {
        return this.max;
    }

    /**
     * 设置max值
     *
     * @param max
     */
    public JGauge max(Integer max) {
        this.max = max;
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
    public JGauge splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    /**
     * 坐标轴线，默认显示
     *
     * @see JLine
     */
    public JAxisLine axisLine() {
        if (this.axisLine == null) {
            this.axisLine = new JAxisLine();
        }
        return this.axisLine;
    }

    /**
     * 坐标轴小标记，默认显示
     *
     * @see JAxisTick
     */
    public JAxisTick axisTick() {
        if (this.axisTick == null) {
            this.axisTick = new JAxisTick();
        }
        return this.axisTick;
    }

    /**
     * 坐标轴文本标签
     *
     * @see JLabel
     */
    public JLabel axisLabel() {
        if (this.axisLabel == null) {
            this.axisLabel = new JLabel();
        }
        return this.axisLabel;
    }

    /**
     * 主分隔线，默认显示
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
     * 指针样式
     *
     * @see JPointer
     */
    public JPointer pointer() {
        if (this.pointer == null) {
            this.pointer = new JPointer();
        }
        return this.pointer;
    }

    /**
     * 仪表盘标题
     *
     * @see JTitle
     */
    public JTitle title() {
        if (this.title == null) {
            this.title = new JTitle();
        }
        return this.title;
    }

    /**
     * 仪表盘详情
     *
     * @see JDetail
     */
    public JDetail detail() {
        if (this.detail == null) {
            this.detail = new JDetail();
        }
        return this.detail;
    }
}

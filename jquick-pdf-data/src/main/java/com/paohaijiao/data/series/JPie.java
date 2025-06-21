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

import com.paohaijiao.data.code.JRoseType;
import com.paohaijiao.data.code.JSelectedMode;
import com.paohaijiao.data.code.JSeriesType;
import lombok.Getter;
import lombok.Setter;

/**
 * 饼图
 *
 * @author martin
 */
@Getter
@Setter
public class JPie extends JSeries<JPie> {
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
     * 最小角度，可用于防止某item的值过小而影响交互
     */
    private Integer minAngle;
    /**
     * 显示是否顺时针
     */
    private Boolean clockWise;
    /**
     * 南丁格尔玫瑰图模式，'radius'（半径） | 'area'（面积）
     *
     * @see JRoseType
     */
    private JRoseType roseType;
    /**
     * 选中是扇区偏移量
     */
    private Integer selectedOffset;
    /**
     * 选中模式，默认关闭，可选single，multiple
     *
     * @see JSelectedMode
     */
    private JSelectedMode selectedMode;

    /**
     * 是否启用防止标签重叠策略，默认开启
     */
    private Boolean avoidLabelOverlap;

    /**
     * 构造函数
     */
    public JPie() {
        this.type(JSeriesType.pie);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JPie(String name) {
        super(name);
        this.type(JSeriesType.pie);
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
    public JPie center(Object[] center) {
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
     * 圆心坐标，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
     *
     * @param width
     * @param height
     * @return
     */
    public JPie center(Object width, Object height) {
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
    public JPie radius(Object radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%，
     * 传数组实现环形图，[内半径，外半径]
     *
     * @param width  内半径
     * @param height 外半径
     * @return
     */
    public JPie radius(Object width, Object height) {
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
    public JPie startAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    /**
     * 获取minAngle值
     */
    public Integer minAngle() {
        return this.minAngle;
    }

    /**
     * 设置minAngle值
     *
     * @param minAngle
     */
    public JPie minAngle(Integer minAngle) {
        this.minAngle = minAngle;
        return this;
    }

    /**
     * 获取clockWise值
     */
    public Boolean clockWise() {
        return this.clockWise;
    }

    /**
     * 设置clockWise值
     *
     * @param clockWise
     */
    public JPie clockWise(Boolean clockWise) {
        this.clockWise = clockWise;
        return this;
    }

    /**
     * 获取roseType值
     */
    public JRoseType roseType() {
        return this.roseType;
    }

    /**
     * 设置roseType值
     *
     * @param roseType
     */
    public JPie roseType(JRoseType roseType) {
        this.roseType = roseType;
        return this;
    }

    /**
     * 设置 avoidLabelOverlap
     *
     * @param avoidLabelOverlap
     * @return
     */
    public JPie avoidLabelOverlap(Boolean avoidLabelOverlap) {
        this.avoidLabelOverlap = avoidLabelOverlap;
        return this;
    }
}

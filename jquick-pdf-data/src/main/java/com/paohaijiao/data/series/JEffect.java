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

import com.paohaijiao.data.code.JSymbol;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 图形炫光特效
 *
 * @author martin
 */
@Getter
@Setter
public class JEffect implements Serializable {

    private static final long serialVersionUID = 2768587032945006946L;
    /**
     * 特效类型，默认为'scale'（放大），可选还有'bounce'（跳动）
     *
     * @since 2.2.0
     */
    private Type type;
    /**
     * 是否开启，默认关闭
     */
    private Boolean show;
    /**
     * 循环动画，默认开启
     */
    private Boolean loop;
    /**
     * 运动周期，无单位，值越大越慢，默认为15
     */
    private Integer period;
    /**
     * 放大倍数，以markPoint symbolSize为基准
     */
    private Integer scaleSize;
    /**
     * 炫光颜色，默认跟随markPoint itemStyle定义颜色
     */
    private String color;
    /**
     * 光影颜色，默认跟随color
     */
    private String shadowColor;
    /**
     * 光影模糊度，默认为0
     */
    private Integer shadowBlur;
    /**
     * 跳动距离，单位为px，type为bounce时有效
     */
    private Integer bounceDistance;
    /**
     * 特效图形的标记
     */
    private Object symbol;
    /**
     * 特效标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示高和宽，例如 [20, 10] 表示标记宽为20，高为10
     */
    private Object symbolSize;
    /**
     * 特效尾迹的长度。取从 0 到 1 的值，数值越大尾迹越长
     */
    private Double trailLength;

    /**
     * 获取type值
     */
    public Type type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JEffect type(Type type) {
        this.type = type;
        return this;
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
    public JEffect show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取loop值
     */
    public Boolean loop() {
        return this.loop;
    }

    /**
     * 设置loop值
     *
     * @param loop
     */
    public JEffect loop(Boolean loop) {
        this.loop = loop;
        return this;
    }

    /**
     * 获取period值
     */
    public Integer period() {
        return this.period;
    }

    /**
     * 设置period值
     *
     * @param period
     */
    public JEffect period(Integer period) {
        this.period = period;
        return this;
    }

    /**
     * 获取scaleSize值
     */
    public Integer scaleSize() {
        return this.scaleSize;
    }

    /**
     * 设置scaleSize值
     *
     * @param scaleSize
     */
    public JEffect scaleSize(Integer scaleSize) {
        this.scaleSize = scaleSize;
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
    public JEffect color(String color) {
        this.color = color;
        return this;
    }

    /**
     * 获取shadowColor值
     */
    public String shadowColor() {
        return this.shadowColor;
    }

    /**
     * 设置shadowColor值
     *
     * @param shadowColor
     */
    public JEffect shadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    /**
     * 获取shadowBlur值
     */
    public Integer shadowBlur() {
        return this.shadowBlur;
    }

    /**
     * 设置shadowBlur值
     *
     * @param shadowBlur
     */
    public JEffect shadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
        return this;
    }

    /**
     * 获取bounceDistance值
     */
    public Integer bounceDistance() {
        return this.bounceDistance;
    }

    /**
     * 设置bounceDistance值
     *
     * @param bounceDistance
     */
    public JEffect bounceDistance(Integer bounceDistance) {
        this.bounceDistance = bounceDistance;
        return this;
    }

    public Object symbol() {
        return this.symbol;
    }

    public JEffect symbol(Object symbol) {
        this.symbol = symbol;
        return this;
    }

    public JEffect symbol(JSymbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public Object symbolSize() {
        return this.symbolSize;
    }

    public JEffect symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public JEffect symbolSize(Object[] symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    public JEffect symbolSize(Object width, Object height) {
        this.symbolSize = new Object[]{width, height};
        return this;
    }

    public Double trailLength() {
        return this.trailLength;
    }

    public JEffect trailLength(Double trailLength) {
        this.trailLength = trailLength;
        return this;
    }
    public static enum Type {
        scale, bounce
    }
}

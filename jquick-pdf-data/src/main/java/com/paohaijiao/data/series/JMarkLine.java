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

import com.paohaijiao.data.JAbstractData;
import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: MarkLine
 *
 * @author martin
 */
@Getter
@Setter
public class JMarkLine extends JAbstractData<JMarkLine> {
    /**
     * 标线起始和结束的symbol介绍类型，如果都一样，可以直接传string，同series中的symbol
     *
     * @see JSymbol
     * @see JSeries#symbol
     */
    private Object symbol;
    /**
     * 标线起始和结束的symbol大小，半宽（半径）参数，如果都一样，可以直接传number或function，同series中的symbolSize
     *
     * @see JSeries#symbolSize
     */
    private Object symbolSize;
    /**
     * 标线起始和结束的symbol旋转控制，同series中的symbolRotate
     *
     * @see JSeries#symbolRoate
     */
    private Object symbolRoate;
    /**
     * 标线图形炫光特效
     *
     * @see JEffect
     */
    private JEffect effect;
    /**
     * 标线图形样式属性
     *
     * @see JItemStyle
     * @see JSeries#itemStyle
     */
    private JItemStyle itemStyle;
    /**
     * 地图特有，标线图形定位坐标
     *
     * @see JMap#geoCoord
     */
    private JGeoCoord geoCoord;
    /**
     * 平滑曲线
     */
    private Boolean smooth;
    /**
     * 平滑度，默认0.2
     *
     * @since 2.2.0
     */
    private Double smoothness;
    /**
     * 小数精度，默认2
     *
     * @since 2.2.0
     */
    private Integer precision;
    /**
     * 边捆绑
     *
     * @since 2.2.0
     */
    private Bundling bundling;

    /**
     * 获取边捆绑
     */
    public Bundling bundling() {
        if (this.bundling == null) {
            this.bundling = new Bundling();
        }
        return this.bundling;
    }

    /**
     * 设置边捆绑
     *
     * @param bundling
     */
    public JMarkLine bundling(Bundling bundling) {
        this.bundling = bundling;
        return this;
    }

    /**
     * 获取平滑度
     */
    public Double smoothness() {
        return this.smoothness;
    }

    /**
     * 设置平滑度
     *
     * @param smoothness
     */
    public JMarkLine smoothness(Double smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    /**
     * 获取小数精度
     */
    public Integer precision() {
        return this.precision;
    }

    /**
     * 设置小数精度
     *
     * @param precision
     */
    public JMarkLine precision(Integer precision) {
        this.precision = precision;
        return this;
    }

    /**
     * 设置effect值
     *
     * @param effect
     */
    public JMarkLine effect(JEffect effect) {
        this.effect = effect;
        return this;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JMarkLine itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 获取symbol值
     */
    public Object symbol() {
        return this.symbol;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public JMarkLine symbol(Object symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * 获取symbolSize值
     */
    public Object symbolSize() {
        return this.symbolSize;
    }

    /**
     * 设置symbolSize值
     *
     * @param symbolSize
     */
    public JMarkLine symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    /**
     * 获取symbolRoate值
     */
    public Object symbolRoate() {
        return this.symbolRoate;
    }

    /**
     * 设置symbolRoate值
     *
     * @param symbolRoate
     */
    public JMarkLine symbolRoate(Object symbolRoate) {
        this.symbolRoate = symbolRoate;
        return this;
    }

    /**
     * 标线图形炫光特效
     *
     * @see JEffect
     */
    public JEffect effect() {
        if (this.effect == null) {
            this.effect = new JEffect();
        }
        return this.effect;
    }

    /**
     * 标线图形样式属性
     *
     * @see JItemStyle
     * @see JSeries#itemStyle
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    /**
     * 获取geoCoord值
     */
    public JGeoCoord geoCoord() {
        if (this.geoCoord == null) {
            this.geoCoord = new JGeoCoord();
        }
        return this.geoCoord;
    }

    /**
     * 设置name,x,y值
     *
     * @param name
     * @param x
     * @param y
     */
    public JMarkLine geoCoord(String name, String x, String y) {
        this.geoCoord().put(name, x, y);
        return this;
    }

    /**
     * 获取smooth值
     */
    public Boolean smooth() {
        return this.smooth;
    }

    /**
     * 设置smooth值
     *
     * @param smooth
     */
    public JMarkLine smooth(Boolean smooth) {
        this.smooth = smooth;
        return this;
    }
    /**
     * 边捆绑
     *
     * @since 2.2.0
     */
    @Getter
    @Setter
    public static class Bundling {
        private Boolean enable;
        private Integer maxTurningAngle;

        /**
         * 获取enable值
         */
	public Boolean enable() {
        return this.enable;
    }

        /**
         * 设置enable值
         *
         * @param enable
         */
        public Bundling enable(Boolean enable) {
            this.enable = enable;
        return this;
    }

        /**
         * 获取maxTurningAngle值
         */
	public Integer maxTurningAngle() {
        return this.maxTurningAngle;
    }

        /**
         * 设置maxTurningAngle值
         *
         * @param maxTurningAngle
         */
        public Bundling maxTurningAngle(Integer maxTurningAngle) {
            this.maxTurningAngle = maxTurningAngle;
            return this;
        }
    }
}

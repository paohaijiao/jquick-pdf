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
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: MarkPoint
 *
 * @author martin
 */
@Getter
@Setter
public class JMarkPoint extends JAbstractData<JMarkPoint> {
    /**
     * 标注类型
     *
     * @see JSeries#symbol
     */
    private Object symbol;
    /**
     * 标注大小
     *
     * @see JSeries#symbolSize
     */
    private Object symbolSize;
    /**
     * 标注图形旋转角度
     *
     * @see JSeries#symbolRoate
     */
    private Object symbolRoate;
    /**
     * 是否启动大规模标注模式
     */
    private Boolean large;
    /**
     * 标注图形炫光特效
     *
     * @see JEffect
     */
    private JEffect effect;
    /**
     * 标注图形样式属性
     *
     * @see JSeries#itemStyle
     */
    private JItemStyle itemStyle;
    /**
     * 地图特有，标注图形定位坐标
     *
     * @see JMap#geoCoord
     */
    private JGeoCoord geoCoord;

    /**
     * 设置effect值
     *
     * @param effect
     */
    public JMarkPoint effect(JEffect effect) {
        this.effect = effect;
        return this;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JMarkPoint itemStyle(JItemStyle itemStyle) {
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
    public JMarkPoint symbol(Object symbol) {
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
    public JMarkPoint symbolSize(Object symbolSize) {
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
    public JMarkPoint symbolRoate(Object symbolRoate) {
        this.symbolRoate = symbolRoate;
        return this;
    }

    /**
     * 获取large值
     */
    public Boolean large() {
        return this.large;
    }

    /**
     * 设置large值
     *
     * @param large
     */
    public JMarkPoint large(Boolean large) {
        this.large = large;
        return this;
    }

    /**
     * 标注图形炫光特效
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
    public JMarkPoint geoCoord(String name, String x, String y) {
        this.geoCoord().put(name, x, y);
        return this;
    }
}

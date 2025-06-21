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

package com.paohaijiao.data.axis;

import com.paohaijiao.data.JAbstractData;
import com.paohaijiao.data.JComponent;
import com.paohaijiao.data.code.JAxisType;
import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.code.JY;
import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 坐标轴
 *
 * @author martin
 */
@Getter
@Setter
public abstract class JAxis<T> extends JAbstractData<T> implements JComponent {
    /**
     * 是否显示
     */
    private Boolean show;
    /**
     * 坐标轴类型，横轴默认为类目型'category'，纵轴默认为数值型'value'
     *
     * @see JAxisType
     */
    private JAxisType type;
    /**
     * 坐标轴类型，横轴默认为类目型'bottom'，纵轴默认为数值型'left'，可选为：'bottom' | 'top' | 'left' |
     * 'right'
     *
     * @see JX
     * @see JY
     */
    private Object position;
    /**
     * 坐标轴名称，默认为空
     */
    private String name;
    /**
     * 坐标轴线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisLine
     * @see JLineStyle
     */
    private JAxisLine axisLine;
    /**
     * 坐标轴小标记，默认不显示，属性show控制显示与否，属性length控制线长，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisTick
     * @see JLineStyle
     */
    private JAxisTick axisTick;
    /**
     * 坐标轴文本标签，详见axis.axisLabel
     *
     * @see JAxisLabel
     */
    private JAxisLabel axisLabel;
    /**
     * 分隔线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JSplitLine
     */
    private JSplitLine splitLine;
    /**
     * 分隔区域，默认不显示，属性show控制显示与否，属性areaStyle（详见areaStyle）控制区域样式
     */
    private JSplitArea splitArea;
    /**
     * 一级层叠控制
     */
    private Integer zlevel;
    /**
     * 二级层叠控制
     */
    private Integer z;
    /**
     * x 轴所在的 grid 的索引，默认位于第一个 grid
     */
    private Integer gridIndex;
    /**
     * 坐标轴名称与轴线之间的距离
     */
    private Integer nameGap;
    /**
     * 是否是反向坐标轴。ECharts 3 中新加
     */
    private Boolean inverse;
    /**
     * 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样
     */
    private Object boundaryGap;
    /**
     * 坐标轴刻度最小值，在类目轴中无效
     */
    private Object min;
    /**
     * 坐标轴刻度最大值，在类目轴中无效
     */
    private Object max;
    /**
     * 只在数值轴中（type: 'value'）有效
     */
    private Boolean scale;
    /**
     * 坐标轴分割间隔
     */
    private Object interval;

    private Number minInterval;

    public Boolean scale() {
        return this.scale;
    }

    public T scale(Boolean scale) {
        this.scale = scale;
        return (T) this;
    }

    public Object interval() {
        return this.interval;
    }

    public T interval(Object interval) {
        this.interval = interval;
        return (T) this;
    }

    public T interval(Double interval) {
        this.interval = interval;
        return (T) this;
    }

    public Integer gridIndex() {
        return this.gridIndex;
    }

    public T gridIndex(Integer gridIndex) {
        this.gridIndex = gridIndex;
        return (T) this;
    }

    public Integer nameGap() {
        return this.nameGap;
    }

    public T nameGap(Integer nameGap) {
        this.nameGap = nameGap;
        return (T) this;
    }

    public Boolean inverse() {
        return this.inverse;
    }

    public T inverse(Boolean inverse) {
        this.inverse = inverse;
        return (T) this;
    }

    public Object boundaryGap() {
        return this.boundaryGap;
    }

    public T boundaryGap(Object boundaryGap) {
        this.boundaryGap = boundaryGap;
        return (T) this;
    }

    public T boundaryGap(Object[] boundaryGap) {
        this.boundaryGap = boundaryGap;
        return (T) this;
    }

    public T boundaryGap(Object o1, Object o2) {
        this.boundaryGap = new Object[]{o1, o2};
        return (T) this;
    }

    public Object min() {
        return this.min;
    }

    public T min(Object min) {
        this.min = min;
        return (T) this;
    }

    public T min(Double min) {
        this.min = min;
        return (T) this;
    }

    public Object max() {
        return this.max;
    }

    public T max(Object max) {
        this.max = max;
        return (T) this;
    }

    public T max(Double max) {
        this.max = max;
        return (T) this;
    }

    /**
     * 设置zlevel值
     *
     * @param zlevel
     */
    public T zlevel(Integer zlevel) {
        this.zlevel = zlevel;
        return (T) this;
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
    public T z(Integer z) {
        this.z = z;
        return (T) this;
    }

    /**
     * 获取z值
     */
    public Integer z() {
        return this.z;
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
    public T show(Boolean show) {
        this.show = show;
        return (T) this;
    }

    /**
     * 获取type值
     */
    public JAxisType type() {
        return this.type;
    }

    /**
     * 获取type值
     */
    public JAxisType getType() {
        return type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public void setType(JAxisType type) {
        this.type = type;
    }

    /**
     * 获取position值
     */
    public Object getPosition() {
        return position;
    }

    /**
     * 设置position值
     *
     * @param position
     */
    public void setPosition(Object position) {
        this.position = position;
    }

    /**
     * 获取name值
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public T type(JAxisType type) {
        this.type = type;
        return (T) this;

    }

    /**
     * 获取position值
     */
    public Object position() {
        return this.position;
    }

    /**
     * 设置position值
     *
     * @param position
     */
    public T position(Object position) {
        this.position = position;
        return (T) this;
    }

    /**
     * 设置position值
     *
     * @param position
     */
    public T position(JX position) {
        this.position = position;
        return (T) this;
    }

    /**
     * 设置position值
     *
     * @param position
     */
    public T position(JY position) {
        this.position = position;
        return (T) this;
    }

    /**
     * 获取name值
     */
    public String name() {
        return this.name;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * 坐标轴线，默认显示，属性show控制显示与否，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisLine
     * @see JLineStyle
     */
    public JAxisLine axisLine() {
        if (this.axisLine == null) {
            this.axisLine = new JAxisLine();
        }
        return this.axisLine;
    }

    /**
     * 设置axisLine值
     *
     * @param axisLine
     */
    public T axisLine(JAxisLine axisLine) {
        this.axisLine = axisLine;
        return (T) this;
    }

    /**
     * 坐标轴小标记，默认不显示，属性show控制显示与否，属性length控制线长，属性lineStyle（详见lineStyle）控制线条样式
     *
     * @see JAxisTick
     * @see JLineStyle
     */
    public JAxisTick axisTick() {
        if (this.axisTick == null) {
            this.axisTick = new JAxisTick();
        }
        return this.axisTick;
    }

    /**
     * 设置axisTick值
     *
     * @param axisTick
     */
    public T axisTick(JAxisTick axisTick) {
        this.axisTick = axisTick;
        return (T) this;
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
     * @param label
     * @return
     */
    public T axisLabel(JAxisLabel label) {
        this.axisLabel = label;
        return (T) this;
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
     * 设置splitLine值
     *
     * @param splitLine
     */
    public T splitLine(JSplitLine splitLine) {
        if (this.splitLine == null) {
            this.splitLine = splitLine;
        }
        return (T) this;
    }

    /**
     * 分隔区域，默认不显示，属性show控制显示与否，属性areaStyle（详见areaStyle）控制区域样式
     */
    public JSplitArea splitArea() {
        if (this.splitArea == null) {
            this.splitArea = new JSplitArea();
        }
        return this.splitArea;
    }

    /**
     * 设置splitArea值
     *
     * @param splitArea
     */
    public T splitArea(JSplitArea splitArea) {
        this.splitArea = splitArea;
        return (T) this;
    }

    /**
     * 添加坐标轴的类目属性
     *
     * @param values
     * @return
     */
    @Override
    public T data(Object... values) {
        if (values == null || values.length == 0) {
            return (T) this;
        }
        if (this.data == null) {
            if (this.type == JAxisType.category) {
                data = new ArrayList<Object>();
            } else {
                throw new RuntimeException("数据轴不能添加类目信息!");
            }
        }
        this.data.addAll(Arrays.asList(values));
        return (T) this;
    }

    public Number minInterval() {
        return this.minInterval;
    }

    public T minInterval(Number minInterval) {
        this.minInterval = minInterval;
        return (T) this;
    }
}

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
import com.paohaijiao.data.JChart;
import com.paohaijiao.data.JTooltip;
import com.paohaijiao.data.code.*;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: Series
 *
 * @author martin
 */
@Getter
@Setter
public abstract class JSeries<T> extends JAbstractData<T> implements JChart {
    /**
     * 是否启用图例（legend）hover时的联动响应（高亮显示）
     */
    private Boolean legendHoverLink;
    /**
     * xAxis坐标轴数组的索引，指定该系列数据所用的横坐标轴
     */
    private Integer xAxisIndex;
    /**
     * yAxis坐标轴数组的索引，指定该系列数据所用的纵坐标轴
     */
    private Integer yAxisIndex;
    /**
     * 使用的地理坐标系的 index，在单个图表实例中存在多个地理坐标系的时候有用
     */
    private Integer geoIndex;
    /**
     * 系列名称，如启用legend，该值将被legend.data索引相关
     */
    private String name;
    /**
     * 图表类型，必要参数！如为空或不支持类型，则该系列数据不被显示
     *
     * @see JSeriesType
     */
    private JSeriesType type;
    /**
     * 组合名称，多组数据的堆积图时使用，eg：stack:'group1'，则series数组中stack值等于'group1'的数据做堆积计算
     */
    private String stack;
    /**
     * 提示框样式，仅对本系列有效，如不设则用option.tooltip（详见tooltip）,鼠标悬浮交互时的信息提示
     *
     * @see JTooltip
     */
    private JTooltip tooltip;
    /**
     * 图形样式
     *
     * @see JItemStyle
     */
    private JItemStyle itemStyle;
    /**
     * 标注
     *
     * @see JMarkPoint
     */
    private JMarkPoint markPoint;
    /**
     * 标线
     *
     * @see JMarkLine
     */
    private JMarkLine markLine;
    /**
     * 标志图形类型，默认自动选择（8种类型循环使用，不显示标志图形可设为'none'）
     *
     * @see JSymbol
     */
    private Object symbol;
    /**
     * 标志图形大小，可计算特性启用情况建议增大以提高交互体验。实现气泡图时symbolSize需为Function，气泡大小取决于该方法返回值，传入参数为当前数据项（value数组）
     */
    private Object symbolSize;
    /**
     * 标志图形旋转角度[-180,180]
     */
    private Object symbolRoate;
    /**
     * 标记相对于原本位置的偏移
     */
    private Object[] symbolOffset;

    /**
     * treemap 组件离容器左侧的距离
     */
    private Object left;
    /**
     * treemap 组件离容器上侧的距离
     */
    private Object top;
    /**
     * treemap 组件离容器右侧的距离
     */
    private Object right;
    /**
     * treemap 组件离容器下侧的距离
     */
    private Object bottom;
    /**
     * treemap 组件的宽度
     */
    private Object width;
    /**
     * treemap 组件的高度
     */
    private Object height;
    /**
     * 标志图形默认只有主轴显示（随主轴标签间隔隐藏策略），如需全部显示可把showAllSymbol设为true
     */
    private Boolean showAllSymbol;
    /**
     * 重新计算
     */
    private Boolean calculable;
    /**
     * 一级层叠控制
     */
    private Integer zlevel;
    /**
     * 二级层叠控制
     */
    private Integer z;
    /**
     * 图形上的文本标签，课用于说明图形的一些数据信息，比如值，名称等，label选项在 ECharts 2.x 中放置于itemStyle.normal下，在 ECharts 3 中为了让整个配置项结构更扁平合理，label被拿出来跟 itemStyle 平级，并且跟 itemStyle 一样拥有 normal, emphasis 两个状态
     */
    private JItemStyle label;

    /**
     * 坐标系
     */
    private Object coordinateSystem;

    /**
     * 构造函数
     */
    protected JSeries() {
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    protected JSeries(String name) {
        this.name = name;
    }

    public Object left() {
        return this.left;
    }

    public T left(Object left) {
        this.left = left;
        return (T) this;
    }

    public T left(Integer left) {
        this.left = left;
        return (T) this;
    }

    public T left(JX left) {
        this.left = left;
        return (T) this;
    }

    public Object top() {
        return this.top;
    }

    public T top(Object top) {
        this.top = top;
        return (T) this;
    }

    public T top(Integer top) {
        this.top = top;
        return (T) this;
    }

    public T top(JY top) {
        this.top = top;
        return (T) this;
    }

    public Object right() {
        return this.right;
    }

    public T right(Object right) {
        this.right = right;
        return (T) this;
    }

    public T right(Integer right) {
        this.right = right;
        return (T) this;
    }

    public Object bottom() {
        return this.bottom;
    }

    public T bottom(Object bottom) {
        this.bottom = bottom;
        return (T) this;
    }

    public T bottom(Integer bottom) {
        this.bottom = bottom;
        return (T) this;
    }

    public Object width() {
        return this.width;
    }

    public T width(Object width) {
        this.width = width;
        return (T) this;
    }

    public T width(Integer width) {
        this.width = width;
        return (T) this;
    }

    public Object height() {
        return this.height;
    }

    public T height(Object height) {
        this.height = height;
        return (T) this;
    }

    public T height(Integer height) {
        this.height = height;
        return (T) this;
    }

    public Object[] symbolOffset() {
        return this.symbolOffset;
    }

    public T symbolOffset(Object[] symbolOffset) {
        this.symbolOffset = symbolOffset;
        return (T) this;
    }

    public T symbolOffset(Object o1, Object o2) {
        this.symbolOffset = new Object[]{o1, o2};
        return (T) this;
    }
    public Object coordinateSystem() {
        return this.coordinateSystem;
    }

    public T coordinateSystem(Object coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return (T) this;
    }

    public T coordinateSystem(JCoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return (T) this;
    }

    public T label(JItemStyle label) {
        this.label = label;
        return (T) this;
    }

    public JItemStyle label() {
        if (this.label == null) {
            this.label = new JItemStyle();
        }
        return this.label;
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
     * 设置tooltip值
     *
     * @param tooltip
     */
    public JSeries tooltip(JTooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JSeries itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 设置markPoint值
     *
     * @param markPoint
     */
    public JSeries markPoint(JMarkPoint markPoint) {
        this.markPoint = markPoint;
        return this;
    }

    /**
     * 设置markLine值
     *
     * @param markLine
     */
    public JSeries markLine(JMarkLine markLine) {
        this.markLine = markLine;
        return this;
    }

    /**
     * 获取legendHoverLink值
     *
     * @return
     */
    public Boolean legendHoverLink() {
        return this.legendHoverLink;
    }

    /**
     * 设置legendHoverLink值
     *
     * @param legendHoverLink
     */
    public T legendHoverLink(Boolean legendHoverLink) {
        this.legendHoverLink = legendHoverLink;
        return (T) this;
    }

    /**
     * 获取xAxisIndex值
     */
    public Integer xAxisIndex() {
        return this.xAxisIndex;
    }

    /**
     * 设置xAxisIndex值
     *
     * @param xAxisIndex
     */
    public T xAxisIndex(Integer xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return (T) this;
    }

    /**
     * 获取yAxisIndex值
     */
    public Integer yAxisIndex() {
        return this.yAxisIndex;
    }

    /**
     * 设置yAxisIndex值
     *
     * @param yAxisIndex
     */
    public T yAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return (T) this;
    }

    public Integer geoIndex() {
        return this.geoIndex;
    }

    public T geoIndex(Integer geoIndex) {
        this.geoIndex = geoIndex;
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
     * 获取type值
     */
    public JSeriesType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public T type(JSeriesType type) {
        this.type = type;
        return (T) this;
    }

    /**
     * 获取stack值
     */
    public String stack() {
        return this.stack;
    }

    /**
     * 设置stack值
     *
     * @param stack
     */
    public T stack(String stack) {
        this.stack = stack;
        return (T) this;
    }

    /**
     * 提示框样式，仅对本系列有效，如不设则用option.tooltip（详见tooltip）,鼠标悬浮交互时的信息提示
     *
     * @see JTooltip
     */
    public JTooltip tooltip() {
        if (this.tooltip == null) {
            this.tooltip = new JTooltip();
        }
        return this.tooltip;
    }

    /**
     * 图形样式
     *
     * @see JItemStyle
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    /**
     * 标注
     *
     * @see JMarkPoint
     */
    public JMarkPoint markPoint() {
        if (this.markPoint == null) {
            this.markPoint = new JMarkPoint();
        }
        return this.markPoint;
    }

    /**
     * 标线
     *
     * @see JMarkLine
     */
    public JMarkLine markLine() {
        if (this.markLine == null) {
            this.markLine = new JMarkLine();
        }
        return this.markLine;
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
    public T symbol(Object symbol) {
        this.symbol = symbol;
        return (T) this;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public T symbol(JSymbol symbol) {
        this.symbol = symbol;
        return (T) this;
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
    public T symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return (T) this;
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
    public T symbolRoate(Object symbolRoate) {
        this.symbolRoate = symbolRoate;
        return (T) this;
    }

    /**
     * 获取showAllSymbol值
     */
    public Boolean showAllSymbol() {
        return this.showAllSymbol;
    }

    /**
     * 设置calculable值
     *
     * @param calculable
     * @return
     */
    public T calcuable(Boolean calculable) {
        this.calculable = calculable;
        return (T) this;
    }

    /**
     * 获取calculable值
     */
    public Boolean calculable() {
        return this.calculable;
    }

    /**
     * 设置showAllSymbol值
     *
     * @param showAllSymbol
     */
    public T showAllSymbol(Boolean showAllSymbol) {
        this.showAllSymbol = showAllSymbol;
        return (T) this;
    }
}

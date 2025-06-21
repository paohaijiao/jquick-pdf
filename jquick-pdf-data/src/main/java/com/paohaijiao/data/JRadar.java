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

import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 雷达图
 * <p>
 * 雷达图坐标系组件，只适用于雷达图。该组件等同 ECharts 2 中的 polar 组件。因为 3 中的 polar 被重构为标准的极坐标组件，为避免混淆，雷达图使用 radar 组件作为其坐标系。
 *
 * @author martin
 */
@Getter
@Setter
public class JRadar extends JAbstractData<JRadar> implements JComponent {

    /**
     * 组件 ID。默认不指定。指定则可用于在 option 或者 API 中引用组件。
     */
    private String id;

    /**
     * 所有图形的 zlevel 值。zlevel用于 Canvas 分层，不同zlevel值的图形会放置在不同的 Canvas 中，
     * Canvas 分层是一种常见的优化手段。我们可以把一些图形变化频繁（例如有动画）的组件设置成一个单独的zlevel。
     * 需要注意的是过多的 Canvas 会引起内存开销的增大，在手机端上需要谨慎使用以防崩溃。zlevel 大的 Canvas 会放在 zlevel 小的 Canvas 的上面。
     */
    private Integer zlevel;

    /**
     * [ default: 2 ]组件的所有图形的z值。控制图形的前后顺序。z值小的图形会被z值大的图形覆盖。z相比zlevel优先级更低，而且不会创建新的 Canvas。
     */
    private Integer z;

    /**
     * [ default: ['50%', '50%'] ]的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
     * 支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度。
     */
    private Object[] center;

    /**
     * [ default: 75% ]的半径，数组的第一项是内半径，第二项是外半径。支持设置成百分比，相对于容器高宽中较小的一项的一半。
     */
    private Object radius;

    /**
     * [ default: 90 ]坐标系起始角度，也就是第一个指示器轴的角度。
     */
    private Integer startAngle;

    /**
     * 雷达图每个指示器名称的配置项。
     */
    private Name name;

    /**
     * [ default: 15 ]指示器名称和指示器轴的距离。
     */
    private Integer nameGap;

    /**
     * [ default: 5 ]指示器轴的分割段数。
     */
    private Integer splitNumber;

    /**
     * [ default: 'polygon' ]雷达图绘制类型，支持 'polygon' 和 'circle'。
     */
    private String shape;

    /**
     * [ default: false ]是否是脱离 0 值比例。设置成 true 后坐标刻度不会强制包含零刻度。在双数值轴的散点图中比较有用。
     */
    private Boolean scale;

    /**
     * [ default: false ]坐标轴是否是静态无法交互。
     */
    private Boolean silent;

    /**
     * [ default: false ]坐标轴的标签是否响应和触发鼠标事件，默认不响应。
     */
    private Boolean triggerEvent;

    /**
     * 坐标轴轴线相关设置。
     */
    private Object axisLine;

    /**
     * 坐标轴刻度相关设置。
     */
    private Object axisTick;

    /**
     * 坐标轴刻度标签的相关设置。
     */
    private Object splitLine;

    /**
     * 坐标轴在 grid 区域中的分隔线。
     */
    private Object splitArea;

    /**
     * 坐标轴在 grid 区域中的分隔区域，默认不显示。
     */
    private List<Indicator> indicator;

    public JRadar id(String id) {
        this.id = id;
        return this;
    }

    public JRadar zlevel(Integer zlevel) {
        this.zlevel = zlevel;
        return this;
    }

    public JRadar z(Integer z) {
        this.z = z;
        return this;
    }

    public JRadar center(Object[] center) {
        this.center = center;
        return this;
    }

    public JRadar radius(Object radius) {
        this.radius = radius;
        return this;
    }

    public JRadar startAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public JRadar name(Name name) {
        this.name = name;
        return this;
    }

    public JRadar nameGap(Integer nameGap) {
        this.nameGap = nameGap;
        return this;
    }

    public JRadar splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    public JRadar shape(String shape) {
        this.shape = shape;
        return this;
    }

    public JRadar scale(Boolean scale) {
        this.scale = scale;
        return this;
    }

    public JRadar silent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public JRadar triggerEvent(Boolean triggerEvent) {
        this.triggerEvent = triggerEvent;
        return this;
    }

    public JRadar axisLine(Object axisLine) {
        this.axisLine = axisLine;
        return this;
    }

    public JRadar axisTick(Object axisTick) {
        this.axisTick = axisTick;
        return this;
    }

    public JRadar splitLine(Object splitLine) {
        this.splitLine = splitLine;
        return this;
    }

    public JRadar splitArea(Object splitArea) {
        this.splitArea = splitArea;
        return this;
    }

    public JRadar indicator(List<Indicator> indicator) {
        this.indicator = indicator;
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
     * 雷达指标列表，同时也是label内容
     */
    public List<Indicator> indicator() {
        if (this.indicator == null) {
            this.indicator = new ArrayList<Indicator>();
        }
        return this.indicator;
    }

    /**
     * 设置values值
     *
     * @param values
     */
    public JRadar indicator(Indicator... values) {
        if (values == null || values.length == 0) {
            return this;
        }
        this.indicator().addAll(Arrays.asList(values));
        return this;
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

    @Setter
    @Getter
    public static class Indicator {

        /**
         * 指示器名称
         */
        private String name;

        /**
         * 指示器的最大值，可选，建议设置
         */
        private Object max;

        /**
         * 指示器的最小值，可选，默认为 0
         */
        private Object min;

        /**
         * 标签特定的颜色
         */
        private String color;

        public Indicator name(String name) {
            this.name = name;
            return this;
        }

        public Indicator max(Object max) {
            this.max = max;
            return this;
        }

        public Indicator min(Object min) {
            this.min = min;
            return this;
        }

        public Indicator color(String color) {
            this.color = color;
            return this;
        }

    }

}

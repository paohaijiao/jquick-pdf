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

import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.code.JSort;
import com.paohaijiao.data.code.JX;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 漏斗图
 *
 * @author martin
 */
@Getter
@Setter
public class JFunnel extends JSeries<JFunnel> {
    /**
     * 左上角横坐标，数值单位px，支持百分比（字符串），如'50%'(显示区域横向中心)
     */
    private Object x;
    /**
     * 左上角纵坐标，数值单位px，支持百分比（字符串），如'50%'(显示区域纵向中心)
     */
    private Object y;
    /**
     * 右下角横坐标，数值单位px，支持百分比（字符串），如'50%'(显示区域横向中心)
     */
    private Object x2;
    /**
     * 右下角纵坐标，数值单位px，支持百分比（字符串），如'50%'(显示区域纵向中心)
     */
    private Object y2;
    /**
     * 水平方向对齐布局类型，默认居中对齐，可用选项还有：'left' | 'right' | 'center'
     */
    private JX funnelAlign;
    /**
     * 指定的最小值
     */
    private Integer min;
    /**
     * 指定的最大值
     */
    private Integer max;
    /**
     * 最小值min映射到总宽度的比例，如果需要最小值的图形并不是尖端三角，可设置minSize实现
     */
    private String minSize;
    /**
     * 最大值max映射到总宽度的比例
     */
    private String maxSize;
    /**
     * 数据排序， 可以取ascending, descending
     *
     * @see JSort
     */
    private JSort sort;
    /**
     * 数据图形间距
     */
    private Integer gap;
    /**
     * 标签的视觉引导线样式，在 label 位置 设置为'left'或者'right'的时候会显示视觉引导线
     */
    private JItemStyle labelLine;

    /**
     * 构造函数
     */
    public JFunnel() {
        this.type(JSeriesType.funnel);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JFunnel(String name) {
        super(name);
        this.type(JSeriesType.funnel);
    }

    public JItemStyle labelLine() {
        if (this.labelLine == null) {
            this.labelLine = new JItemStyle();
        }
        return this.labelLine;
    }

    public JFunnel labelLine(JItemStyle labelLine) {
        this.labelLine = labelLine;
        return this;
    }

    /**
     * 获取x值
     */
    public Object x() {
        return this.x;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JFunnel x(Object x) {
        this.x = x;
        return this;
    }

    /**
     * 获取y值
     */
    public Object y() {
        return this.y;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JFunnel y(Object y) {
        this.y = y;
        return this;
    }

    /**
     * 获取x2值
     */
    public Object x2() {
        return this.x2;
    }

    /**
     * 设置x2值
     *
     * @param x2
     */
    public JFunnel x2(Object x2) {
        this.x2 = x2;
        return this;
    }

    /**
     * 获取y2值
     */
    public Object y2() {
        return this.y2;
    }

    /**
     * 设置y2值
     *
     * @param y2
     */
    public JFunnel y2(Object y2) {
        this.y2 = y2;
        return this;
    }

    /**
     * 获取funnelAlign值
     */
    public JX funnelAlign() {
        return this.funnelAlign;
    }

    /**
     * 设置funnelAlign值
     *
     * @param funnelAlign
     */
    public JFunnel funnelAlign(JX funnelAlign) {
        this.funnelAlign = funnelAlign;
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
    public JFunnel min(Integer min) {
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
    public JFunnel max(Integer max) {
        this.max = max;
        return this;
    }

    /**
     * 获取minSize值
     */
    public String minSize() {
        return this.minSize;
    }

    /**
     * 设置minSize值
     *
     * @param minSize
     */
    public JFunnel minSize(String minSize) {
        this.minSize = minSize;
        return this;
    }

    /**
     * 获取maxSize值
     */
    public String maxSize() {
        return this.maxSize;
    }

    /**
     * 设置maxSize值
     *
     * @param maxSize
     */
    public JFunnel maxSize(String maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * 获取sort值
     */
    public JSort sort() {
        return this.sort;
    }

    /**
     * 设置sort值
     *
     * @param sort
     */
    public JFunnel sort(JSort sort) {
        this.sort = sort;
        return this;
    }

    /**
     * 获取gap值
     */
    public Integer gap() {
        return this.gap;
    }

    /**
     * 设置gap值
     *
     * @param gap
     */
    public JFunnel gap(Integer gap) {
        this.gap = gap;
        return this;
    }
}

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

import com.paohaijiao.data.code.JDataZoomType;
import com.paohaijiao.data.code.JFilterMode;
import com.paohaijiao.data.code.JOrient;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 数据区域缩放。与toolbox.feature.dataZoom同步，仅对直角坐标系图表有效
 *
 * @author martin
 */
@Getter
@Setter
public class JDataZoom extends JBasic<JDataZoom> implements JComponent {
    /**
     * 类型
     */
    private JDataZoomType type;
    /**
     * 显示label的小数精度。默认根据数据自动决定
     */
    private String labelPrecision;
    /**
     * 显示的label的格式化器
     */
    private Object labelFormatter;
    /**
     * 是否在 dataZoom-silder 组件中显示数据阴影。数据阴影可以简单得反应数据走势
     */
    private String showDataShadow;
    /**
     * 文字样式
     */
    private JTextStyle textStyle;
    /**
     * 数据窗口范围的起始数值。如果设置了 dataZoom-slider.start 则 startValue 失效
     */
    private Object startValue;
    /**
     * 数据窗口范围的结束数值。如果设置了 dataZoom-slider.end 则 endValue 失效
     */
    private Object endValue;
    /**
     * 设置 dataZoom-inside 组件控制的 角度轴
     */
    private Object angleAxisIndex;
    /**
     * 设置 dataZoom-inside 组件控制的 半径轴
     */
    private Object radiusAxisIndex;
    /**
     * dataZoom 的运行原理是通过 数据过滤 来达到 数据窗口缩放 的效果
     */
    private JFilterMode filterMode;
    /**
     * 设置触发视图刷新的频率。单位为毫秒（ms）。一般不需要更改这个值
     */
    private Integer throttle;
    /**
     * 布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
     */
    private JOrient orient;
    /**
     * 默认#ccc，数据缩略背景颜色
     */
    private String dataBackgroundColor;
    /**
     * 默认值rgba(144,197,237,0.2)，选择区域填充颜色
     */
    private String fillerColor;
    /**
     * 默认值rgba(70,130,180,0.8)，控制手柄颜色
     */
    private String handleColor;
    /**
     * 控制手柄大小
     */
    private Integer handleSize;
    /**
     * 当不指定时默认控制所有横向类目，可通过数组指定多个需要控制的横向类目坐标轴Index，仅一个时可直接为数字
     */
    private Object xAxisIndex;
    /**
     * 当不指定时默认控制所有纵向类目，可通过数组指定多个需要控制的纵向类目坐标轴Index，仅一个时可直接为数字
     */
    private Object yAxisIndex;
    /**
     * 数据缩放，选择起始比例，默认为0（%），从首个数据起选择
     */
    private Integer start;
    /**
     * 数据缩放，选择结束比例，默认为100（%），到最后一个数据选择结束
     */
    private Integer end;
    /**
     * 缩放变化是否实时显示，建议性能较低的浏览器或数据量巨大时不启动实时效果
     */
    private Boolean realtime;
    /**
     * 数据缩放锁，默认为false，当设置为true时选择区域不能伸缩，即(end - start)值保持不变，仅能做数据漫游
     */
    private Boolean zoomLock;
    /**
     * 缩放变化是否显示定位详情
     */
    private Boolean showDetail;

    private Object minValueSpan;

    public String labelPrecision() {
        return this.labelPrecision;
    }

    public JDataZoom labelPrecision(String labelPrecision) {
        this.labelPrecision = labelPrecision;
        return this;
    }

    public Object labelFormatter() {
        return this.labelFormatter;
    }

    public JDataZoom labelFormatter(Object labelFormatter) {
        this.labelFormatter = labelFormatter;
        return this;
    }

    public String showDataShadow() {
        return this.showDataShadow;
    }

    public JDataZoom showDataShadow(String showDataShadow) {
        this.showDataShadow = showDataShadow;
        return this;
    }

    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    public JDataZoom textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public Object startValue() {
        return this.startValue;
    }

    public JDataZoom startValue(Object startValue) {
        this.startValue = startValue;
        return this;
    }

    public JDataZoom startValue(String startValue) {
        this.startValue = startValue;
        return this;
    }

    public JDataZoom startValue(Integer startValue) {
        this.startValue = startValue;
        return this;
    }

    public JDataZoom startValue(Date startValue) {
        this.startValue = startValue;
        return this;
    }

    public Object endValue() {
        return this.endValue;
    }

    public JDataZoom endValue(Object endValue) {
        this.endValue = endValue;
        return this;
    }

    public JDataZoom endValue(Integer endValue) {
        this.endValue = endValue;
        return this;
    }

    public JDataZoom endValue(String endValue) {
        this.endValue = endValue;
        return this;
    }

    public JDataZoom endValue(Date endValue) {
        this.endValue = endValue;
        return this;
    }

    public JDataZoomType type() {
        return this.type;
    }

    public JDataZoom type(JDataZoomType type) {
        this.type = type;
        return this;
    }

    public Object angleAxisIndex() {
        return this.angleAxisIndex;
    }

    public JDataZoom angleAxisIndex(Object angleAxisIndex) {
        this.angleAxisIndex = angleAxisIndex;
        return this;
    }

    public JDataZoom angleAxisIndex(Integer angleAxisIndex) {
        this.angleAxisIndex = angleAxisIndex;
        return this;
    }

    public JDataZoom angleAxisIndex(Integer... angleAxisIndex) {
        this.angleAxisIndex = angleAxisIndex;
        return this;
    }

    public Object radiusAxisIndex() {
        return this.radiusAxisIndex;
    }

    public JDataZoom radiusAxisIndex(Object radiusAxisIndex) {
        this.radiusAxisIndex = radiusAxisIndex;
        return this;
    }

    public JDataZoom radiusAxisIndex(Integer radiusAxisIndex) {
        this.radiusAxisIndex = radiusAxisIndex;
        return this;
    }

    public JDataZoom radiusAxisIndex(Integer... radiusAxisIndex) {
        this.radiusAxisIndex = radiusAxisIndex;
        return this;
    }

    public JFilterMode filterMode() {
        return this.filterMode;
    }

    public JDataZoom filterMode(JFilterMode filterMode) {
        this.filterMode = filterMode;
        return this;
    }

    public Integer throttle() {
        return this.throttle;
    }

    public JDataZoom throttle(Integer throttle) {
        this.throttle = throttle;
        return this;
    }

    /**
     * 获取handleSize值
     */
    public Integer handleSize() {
        return this.handleSize;
    }

    /**
     * 设置handleSize值
     *
     * @param handleSize
     */
    public JDataZoom handleSize(Integer handleSize) {
        this.handleSize = handleSize;
        return this;
    }

    /**
     * 获取orient值
     */
    public JOrient orient() {
        return this.orient;
    }

    /**
     * 设置orient值
     *
     * @param orient
     */
    public JDataZoom orient(JOrient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * 获取dataBackgroundColor值
     */
    public String dataBackgroundColor() {
        return this.dataBackgroundColor;
    }

    /**
     * 设置dataBackgroundColor值
     *
     * @param dataBackgroundColor
     */
    public JDataZoom dataBackgroundColor(String dataBackgroundColor) {
        this.dataBackgroundColor = dataBackgroundColor;
        return this;
    }

    /**
     * 获取fillerColor值
     */
    public String fillerColor() {
        return this.fillerColor;
    }

    /**
     * 设置fillerColor值
     *
     * @param fillerColor
     */
    public JDataZoom fillerColor(String fillerColor) {
        this.fillerColor = fillerColor;
        return this;
    }

    /**
     * 获取handleColor值
     */
    public String handleColor() {
        return this.handleColor;
    }

    /**
     * 设置handleColor值
     *
     * @param handleColor
     */
    public JDataZoom handleColor(String handleColor) {
        this.handleColor = handleColor;
        return this;
    }

    /**
     * 获取xAxisIndex值
     */
    public Object xAxisIndex() {
        return this.xAxisIndex;
    }

    /**
     * 设置xAxisIndex值
     *
     * @param xAxisIndex
     */
    public JDataZoom xAxisIndex(Object xAxisIndex) {
        this.xAxisIndex = xAxisIndex;
        return this;
    }

    /**
     * 获取yAxisIndex值
     */
    public Object yAxisIndex() {
        return this.yAxisIndex;
    }

    /**
     * 设置yAxisIndex值
     *
     * @param yAxisIndex
     */
    public JDataZoom yAxisIndex(Object yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
        return this;
    }

    /**
     * 获取start值
     */
    public Integer start() {
        return this.start;
    }

    /**
     * 设置start值
     *
     * @param start
     */
    public JDataZoom start(Integer start) {
        this.start = start;
        return this;
    }

    /**
     * 获取end值
     */
    public Integer end() {
        return this.end;
    }

    /**
     * 设置end值
     *
     * @param end
     */
    public JDataZoom end(Integer end) {
        this.end = end;
        return this;
    }

    /**
     * 获取realtime值
     */
    public Boolean realtime() {
        return this.realtime;
    }

    /**
     * 设置realtime值
     *
     * @param realtime
     */
    public JDataZoom realtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    /**
     * 获取zoomLock值
     */
    public Boolean zoomLock() {
        return this.zoomLock;
    }

    /**
     * 设置zoomLock值
     *
     * @param zoomLock
     */
    public JDataZoom zoomLock(Boolean zoomLock) {
        this.zoomLock = zoomLock;
        return this;
    }

    /**
     * 获取showDetail值
     */
    public Boolean showDetail() {
        return this.showDetail;
    }

    /**
     * 设置showDetail值
     *
     * @param showDetail
     */
    public JDataZoom showDetail(Boolean showDetail) {
        this.showDetail = showDetail;
        return this;
    }

    /**
     * minValueSpan
     *
     * @param minValueSpan
     */
    public JDataZoom minValueSpan(Object minValueSpan) {
        this.minValueSpan = minValueSpan;
        return this;
    }

    /**
     * minValueSpan
     */
    public Object minValueSpan() {
        return this.minValueSpan;
    }
}

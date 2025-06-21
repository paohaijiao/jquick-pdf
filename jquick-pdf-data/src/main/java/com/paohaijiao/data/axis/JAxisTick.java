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

import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 坐标轴小标记
 *
 * @author martin
 */
@Getter
@Setter
public class JAxisTick implements Serializable {

    private static final long serialVersionUID = -1941931349381173253L;

    /**
     * 是否显示，默认为false，设为true后下面为默认样式
     */
    private Boolean show;
    /**
     * 小标记显示挑选间隔，默认为'auto'，可选为：
     * 'auto'（自动隐藏显示不下的） | 0（全部显示） | {number}（用户指定选择间隔）
     */
    private Object interval;
    /**
     * 小标记是否显示为间隔，默认等于boundaryGap
     */
    private Boolean onGap;
    /**
     * 小标记是否显示为在grid内部，默认在外部
     */
    private Boolean inside;
    /**
     * 默认值5，属性length控制线长
     */
    private Integer length;
    /**
     * 属性lineStyle控制线条样式，（详见lineStyle）
     *
     * @see JLineStyle
     */
    private JLineStyle lineStyle;
    /**
     * 分割段数，默认为5，为0时为线性渐变，calculable为true是默认均分100份
     */
    private Integer splitNumber;
    
    /**
     * 类目轴中在 boundaryGap 为 true 的时候有效，可以保证刻度线和标签对齐。 
     *  defalut:false
     *  @see http://echarts.baidu.com/option.html#xAxis
     *  @author w
     *  @date 2018年6月27日 16:31:27
     */
    private Boolean alignWithLabel = false;
    
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
    public JAxisTick show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取interval值
     */
    public Object interval() {
        return this.interval;
    }

    /**
     * 设置interval值
     *
     * @param interval
     */
    public JAxisTick interval(Object interval) {
        this.interval = interval;
        return this;
    }

    /**
     * 获取onGap值
     */
    public Boolean onGap() {
        return this.onGap;
    }

    /**
     * 设置onGap值
     *
     * @param onGap
     */
    public JAxisTick onGap(Boolean onGap) {
        this.onGap = onGap;
        return this;
    }

    /**
     * 获取inside值
     */
    public Boolean inside() {
        return this.inside;
    }

    /**
     * 设置inside值
     *
     * @param inside
     */
    public JAxisTick inside(Boolean inside) {
        this.inside = inside;
        return this;
    }

    /**
     * 获取length值
     */
    public Integer length() {
        return this.length;
    }

    /**
     * 设置length值
     *
     * @param length
     */
    public JAxisTick length(Integer length) {
        this.length = length;
        return this;
    }

    /**
     * 属性lineStyle控制线条样式，（详见lineStyle）
     *
     * @see JLineStyle
     */
    public JLineStyle lineStyle() {
        if (this.lineStyle == null) {
            this.lineStyle = new JLineStyle();
        }
        return this.lineStyle;
    }

    /**
     * 设置style值
     *
     * @param style
     */
    public JAxisTick lineStyle(JLineStyle style) {
        this.lineStyle = style;
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
    public JAxisTick splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }
    
    /**
     * description: 设置 alignWithLabel 的值。
     * @param alignWithLabel
     * @return
     * AxisTick
     * @version v1.0
     * @author w
     * @return 
     * @date 2018年6月27日 下午4:33:23
     *
     */
    public JAxisTick alignWithLabel(Boolean alignWithLabel){
    	this.alignWithLabel=alignWithLabel;
    	return this;
    }
    
    /**
     * description: 获取 alignWithLabel 的值。
     * @return
     * Boolean
     * @version v1.0
     * @author w
     * @date 2018年6月27日 下午4:34:19
     *
     */
    public Boolean alignWithLabel(){
    	return this.alignWithLabel;
    }
    
}

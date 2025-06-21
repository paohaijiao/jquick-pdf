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

package com.paohaijiao.data.series.force;

import com.paohaijiao.data.style.JItemStyle;
import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 力导向图的边数据
 *
 * @author martin
 */
@Getter
@Setter
public class JLink implements Serializable {

    private static final long serialVersionUID = 7882141565696266870L;

    /**
     * 源节点的index或者源节点的name
     */
    private Object source;
    /**
     * 目标节点的index或者目标节点的name
     */
    private Object target;
    /**
     * 边的权重，权重越大邻接节点越靠拢
     */
    private Integer weight;
    /**
     * 详见 itemStyle, 只能设置 lineWidth, strokeColor, lineType 等描边的属性
     */
    private JItemStyle itemStyle;

    /**
     * lineStyle样式
     */
    private JLineStyle lineStyle;

    /**
     * 构造函数
     */
    public JLink() {
    }

    /**
     * 构造函数,参数:source,target,weight
     *
     * @param source
     * @param target
     * @param weight
     */
    public JLink(Object source, Object target, Integer weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
    public JLink(Object source, Object target) {
        this.source = source;
        this.target = target;
        this.weight = 1;
    }

    /**
     * 获取source值
     */
    public Object source() {
        return this.source;
    }

    /**
     * 设置source值
     *
     * @param source
     */
    public JLink source(Object source) {
        this.source = source;
        return this;
    }

    /**
     * 获取target值
     */
    public Object target() {
        return this.target;
    }

    /**
     * 设置target值
     *
     * @param target
     */
    public JLink target(Object target) {
        this.target = target;
        return this;
    }

    /**
     * 获取weight值
     */
    public Integer weight() {
        return this.weight;
    }

    /**
     * 设置weight值
     *
     * @param weight
     */
    public JLink weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * 详见 itemStyle, 只能设置 lineWidth, strokeColor, lineType 等描边的属性
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }
    /**
     * 设置lineStyle，返回object
     *
     * @param lineStyle
     * @return
     */
    public JLink lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    /**
     * 返回lineStyle
     *
     * @return
     */
    public JLineStyle lineStyle() {
        if (lineStyle == null) {
            this.lineStyle = new JLineStyle();
        }
        return this.lineStyle;
    }
}

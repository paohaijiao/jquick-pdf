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

import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 力导向图中节点的分类
 *
 * @author martin
 */
@Getter
@Setter
public class JCategory implements Serializable {

    private static final long serialVersionUID = 5805816011061262622L;

    /**
     * 类目名称
     */
    private String name;
    /**
     * 所有该类目的节点的形状, 详见 symbolList
     *
     * @see JSymbol
     */
    private Object symbol;
    /**
     * 所有该类目的节点的大小
     */
    private Object symbolSize;
    /**
     * 该类目节点标记的旋转角度
     */
    private Integer symbolRotate;
    /**
     * 该类目节点标记相对于原本位置的偏移
     */
    private Object[] symbolOffset;
    /**
     * 所有该类目的节点是否能被拖拽
     */
    private Boolean draggable;
    /**
     * 详见 itemStyle
     *
     * @see JItemStyle
     */
    private JItemStyle itemStyle;
    /**
     * 详见 itemStyle
     *
     * @see JItemStyle
     */
    private JItemStyle label;

    /**
     * 构造函数
     */
    public JCategory() {
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JCategory(String name) {
        this.name = name;
    }

    public Integer symbolRotate() {
        return this.symbolRotate;
    }

    public JCategory symbolRotate(Integer symbolRotate) {
        this.symbolRotate = symbolRotate;
        return this;
    }

    public Object[] symbolOffset() {
        return this.symbolOffset;
    }

    public JCategory symbolOffset(Object[] symbolOffset) {
        this.symbolOffset = symbolOffset;
        return this;
    }

    public JCategory symbolOffset(Object o1, Object o2) {
        this.symbolOffset = new Object[]{o1, o2};
        return this;
    }

    public JItemStyle label() {
        if (this.label == null) {
            this.label = new JItemStyle();
        }
        return this.label;
    }

    public JCategory label(JItemStyle label) {
        this.label = label;
        return this;
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
    public JCategory name(String name) {
        this.name = name;
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
    public JCategory symbol(Object symbol) {
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
    public JCategory symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    /**
     * 获取draggable值
     */
    public Boolean draggable() {
        return this.draggable;
    }

    /**
     * 设置draggable值
     *
     * @param draggable
     */
    public JCategory draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    /**
     * 详见 itemStyle
     *
     * @see JItemStyle
     */
    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }
}

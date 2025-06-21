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

package com.paohaijiao.data.data;

import com.paohaijiao.data.code.JSymbol;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据列表，每一个数组项为Object {}
 *
 * @author martin
 */
@Getter
@Setter
public class JTreeData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Double value;
    private Object symbol;
    private Object symbolSize;
    private List<JTreeData> children;
    private JItemStyle itemStyle;

    /**
     * 构造函数
     */
    public JTreeData() {
    }

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JTreeData(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JTreeData(String name, Integer value) {
        this.name = name;
        this.value = value.doubleValue();
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public JTreeData name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取name值
     */
    public String name() {
        return this.name;
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public JTreeData value(Double value) {
        this.value = value;
        return this;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public JTreeData symbol(Object symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public JTreeData symbol(JSymbol symbol) {
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
    public JTreeData symbolSize(Object symbolSize) {
        this.symbolSize = symbolSize;
        return this;
    }

    /**
     * 设置treeDatas值
     *
     * @param treeDatas
     */
    public JTreeData children(JTreeData... treeDatas) {
        if (treeDatas == null || treeDatas.length == 0) {
            return this;
        }
        this.children().addAll(Arrays.asList(treeDatas));
        return this;
    }

    /**
     * 获取children值
     */
	public List<JTreeData> children() {
        if (this.children == null) {
            this.children = new LinkedList<JTreeData>();
        }
        return this.children;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JTreeData itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }

    /**
     * 获取itemStyle值
	 */
	public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }
}

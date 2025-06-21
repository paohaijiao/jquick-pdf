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
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 力导向图的顶点数据
 *
 * @author martin
 */
@Getter
@Setter
public class JNode extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 4254895945303983318L;

    /**
     * 构造函数
     */
    public JNode() {
        super();
    }

    /**
     * 构造函数,参数:category,name,value
     *
     * @param category
     * @param name
     * @param value
     */
    public JNode(Integer category, String name, Integer value) {
        super();
        put("category", category);
        put("name", name);
        put("value", value);
    }
    public JNode( String id, String name) {
        super();
        put("id", id);
        put("name", name);
    }

    /**
     * 获取name值
     */
    public String name() {
        return (String) get("name");
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public JNode name(String name) {
        put("name", name);
        return this;
    }

    /**
     * 获取label值
     *
     * @since 2.1.9
     */
    public String label() {
        return (String) get("label");
    }

    /**
     * 设置label值
     *
     * @param label
     * @since 2.1.9
     */
    public JNode label(String label) {
        put("label", label);
        return this;
    }

    /**
     * 获取value值
     */
    public Integer value() {
        return (Integer) get("value");
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public JNode value(Integer value) {
        put("value", value);
        return this;
    }

    /**
     * 获取initial值
     */
    public Object initial() {
        return get("initial");
    }

    /**
     * 设置initial值
     *
     * @param initial
     */
    public JNode initial(Object initial) {
        put("initial", initial);
        return this;
    }

    /**
     * 获取fixX值
     */
    public Boolean fixX() {
        return (Boolean) get("fixX");
    }

    /**
     * 设置fixX值
     *
     * @param fixX
     */
    public JNode fixX(Boolean fixX) {
        put("fixX", fixX);
        return this;
    }

    /**
     * 获取fixY值
     */
    public Boolean fixY() {
        return (Boolean) get("fixY");
    }

    /**
     * 设置fixY值
     *
     * @param fixY
     */
    public JNode fixY(Boolean fixY) {
        put("fixY", fixY);
        return this;
    }

    /**
     * 获取ignore值
     */
    public Boolean ignore() {
        return (Boolean) get("ignore");
    }

    /**
     * 设置ignore值
     *
     * @param ignore
     */
    public JNode ignore(Boolean ignore) {
        put("ignore", ignore);
        return this;
    }

    /**
     * 获取symbol值
     */
    public Object symbol() {
        return get("symbol");
    }

    /**
     * 设置symbol值
     *
     * @param symbol
     */
    public JNode symbol(Object symbol) {
        put("symbol", symbol);
        return this;
    }

    /**
     * 获取symbolSize值
     */
    public Object symbolSize() {
        return get("symbolSize");
    }

    /**
     * 设置symbolSize值
     *
     * @param symbolSize
     */
    public JNode symbolSize(Object symbolSize) {
        put("symbolSize", symbolSize);
        return this;
    }

    /**
     * 获取draggable值
     */
    public Boolean draggable() {
        return (Boolean) get("draggable");
    }

    /**
     * 设置draggable值
     *
     * @param draggable
     */
    public JNode draggable(Boolean draggable) {
        put("draggable", draggable);
        return this;
    }

    /**
     * 获取category值
     */
    public Integer category() {
        return (Integer) get("category");
    }

    /**
     * 设置category值
     *
     * @param category
     */
    public JNode category(Integer category) {
        put("category", category);
        return this;
    }

    /**
     * 详见 itemStyle
     *
     * @see JItemStyle
     */
    public JItemStyle itemStyle() {
        if (get("itemStyle") == null) {
            put("itemStyle", new JItemStyle());
        }
        return (JItemStyle) get("itemStyle");
    }
}

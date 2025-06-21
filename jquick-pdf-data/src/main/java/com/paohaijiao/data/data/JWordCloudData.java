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

import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 词云
 *
 * @author martin
 */
@Getter
@Setter
public class JWordCloudData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer value;
    private JItemStyle itemStyle;

    /**
     * 构造函数
     */
    public JWordCloudData() {
    }

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JWordCloudData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 设置name值
     *
     * @param name
     */
    public JWordCloudData name(String name) {
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
    public JWordCloudData value(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * 获取value值
     */
	public Integer value() {
        return this.value;
    }

    /**
     * 设置itemStyle值
     *
     * @param itemStyle
     */
    public JWordCloudData itemStyle(JItemStyle itemStyle) {
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

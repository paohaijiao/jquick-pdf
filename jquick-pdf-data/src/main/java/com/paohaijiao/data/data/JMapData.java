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

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * MapData
 *
 * @author martin
 */
@Getter
@Setter
public class JMapData implements Serializable {

    private static final long serialVersionUID = 7814199168511760158L;

    private String name;
    private Object value;
    private Boolean selected;

    /**
     * 构造函数,参数:name,value
     *
     * @param name
     * @param value
     */
    public JMapData(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 构造函数,参数:name,value,selected
     *
     * @param name
     * @param value
     * @param selected
     */
    public JMapData(String name, Object value, Boolean selected) {
        this.name = name;
        this.value = value;
        this.selected = selected;
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
    public JMapData name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取value值
     */
    public Object value() {
        return this.value;
    }

    /**
     * 设置value值
     *
     * @param value
     */
    public JMapData value(Object value) {
        this.value = value;
        return this;
    }

    /**
     * 获取selected值
     */
    public Boolean selected() {
        return this.selected;
    }

    /**
     * 设置selected值
     *
     * @param selected
     */
    public JMapData selected(Boolean selected) {
        this.selected = selected;
        return this;
    }
}

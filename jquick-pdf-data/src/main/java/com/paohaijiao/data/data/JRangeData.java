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
 * 描述信息
 *
 * @author martin
 */
@Getter
@Setter
public class JRangeData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer start;
    private Integer end;

    private String label;
    private Object color;

    /**
     * 构造函数
     */
    public JRangeData() {
    }

    /**
     * 构造函数,参数:start,end
     *
     * @param start
     * @param end
     */
    public JRangeData(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 设置start值
     *
     * @param start
     */
    public JRangeData start(Integer start) {
        this.start = start;
        return this;
    }

    /**
     * 获取start值
     */
    public Integer start() {
        return this.start;
    }

    /**
     * 设置end值
     *
     * @param end
     */
    public JRangeData end(Integer end) {
        this.end = end;
        return this;
    }

    /**
     * 获取end值
     */
	public Integer end() {
        return this.end;
    }

    /**
     * 设置label值
     *
     * @param label
     */
    public JRangeData label(String label) {
        this.label = label;
        return this;
    }

    /**
     * 获取label值
     */
    public String label() {
        return this.label;
    }

    /**
     * 设置color值
     *
     * @param color
     */
	public JRangeData color(Object color) {
        this.color = color;
        return this;
    }

    /**
     * 获取color值
     */
    public Object color() {
        return this.color;
    }

}

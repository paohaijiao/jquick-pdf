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

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author martin
 */
@Getter
@Setter
public class JScaleLimit implements Serializable {

    private static final long serialVersionUID = 6026916937450874614L;

    private Double min;
    private Double max;

    /**
     * 构造函数
     */
    public JScaleLimit() {
    }

    /**
     * 构造函数,参数:min,max
     *
     * @param min
     * @param max
     */
    public JScaleLimit(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    /**
     * 获取min值
     */
    public Double min() {
        return this.min;
    }

    /**
     * 设置min值
     *
     * @param min
     */
    public JScaleLimit min(Double min) {
        this.min = min;
        return this;
    }

    /**
     * 获取max值
     */
    public Double max() {
        return this.max;
    }

    /**
     * 设置max值
     *
     * @param max
     */
    public JScaleLimit max(Double max) {
        this.max = max;
        return this;
    }
}

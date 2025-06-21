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

/**
 * 可以是一个包含 offset 和 color 的 Object 的数组，如 [{ offset: 0.2, color: 'blue' }, { offset 0.8, color: 'cyan' }]；也可以是一个颜色字符串的数组如 ['blue', 'cyan', 'lime', 'yellow', 'red']，颜色将均匀分布
 *
 * @author martin
 */
@Getter
@Setter
public class JGradientColor {
    private Double offset;
    private Object color;

    /**
     * 构造函数,参数:offset,color
     *
     * @param offset
     * @param color
     */
    public JGradientColor(Double offset, Object color) {
        this.offset = offset;
        this.color = color;
    }

    /**
     * 获取offset值
     */
    public Double offset() {
        return this.offset;
    }

    /**
     * 设置offset值
     *
     * @param offset
     */
    public JGradientColor offset(Double offset) {
        this.offset = offset;
        return this;
    }

    /**
     * 获取color值
     */
    public Object color() {
        return this.color;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public JGradientColor color(Object color) {
        this.color = color;
        return this;
    }
}

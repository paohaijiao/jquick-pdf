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

import com.paohaijiao.data.code.JSeriesType;
import lombok.Getter;
import lombok.Setter;

/**
 * 平行坐标系
 *
 * @author martin
 */
@Getter
@Setter
public class JParallel extends JSeries<JParallel> {
    /**
     * 使用的平行坐标系的 index，在单个图表实例中存在多个平行坐标系的时候有用
     */
    private Integer parallelIndex;
    /**
     * 框选时，未被选中的条线会设置成这个『透明度』（从而可以达到变暗的效果）
     */
    private Double inactiveOpacity;
    /**
     * 框选时，选中的条线会设置成这个『透明度』（从而可以达到高亮的效果）
     */
    private Double activeOpacity;

    /**
     * 构造函数
     */
    public JParallel() {
        this.type(JSeriesType.parallel);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JParallel(String name) {
        super(name);
        this.type(JSeriesType.parallel);
    }

    public Integer parallelIndex() {
        return this.parallelIndex;
    }

    public JParallel parallelIndex(Integer parallelIndex) {
        this.parallelIndex = parallelIndex;
        return this;
    }

    public Double inactiveOpacity() {
        return this.inactiveOpacity;
    }

    public JParallel inactiveOpacity(Double inactiveOpacity) {
        this.inactiveOpacity = inactiveOpacity;
        return this;
    }

    public Double activeOpacity() {
        return this.activeOpacity;
    }

    public JParallel activeOpacity(Double activeOpacity) {
        this.activeOpacity = activeOpacity;
        return this;
    }
}

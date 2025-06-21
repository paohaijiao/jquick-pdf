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
 * 散点图、气泡图
 *
 * @author martin
 */
@Getter
@Setter
public class JScatter extends JSeries<JScatter> {
    /**
     * 大规模散点图
     */
    private Boolean large;
    /**
     * 大规模阀值，large为true且数据量>largeThreshold才启用大规模模式
     */
    private Long largeThreshold;

    /**
     * 构造函数
     */
    public JScatter() {
        this.type(JSeriesType.scatter);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JScatter(String name) {
        super(name);
        this.type(JSeriesType.scatter);
    }

    /**
     * 获取large值
     */
    public Boolean large() {
        return this.large;
    }

    /**
     * 设置large值
     *
     * @param large
     */
    public JScatter large(Boolean large) {
        this.large = large;
        return this;
    }

    /**
     * 获取largeThreshold值
     */
    public Long largeThreshold() {
        return this.largeThreshold;
    }

    /**
     * 设置largeThreshold值
     *
     * @param largeThreshold
     */
    public JScatter largeThreshold(Long largeThreshold) {
        this.largeThreshold = largeThreshold;
        return this;
    }
}

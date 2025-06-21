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
 * 雷达图
 *
 * @author martin
 */
@Getter
@Setter
public class JRadarSeries extends JSeries<JRadarSeries> {

    /**
     * 构造函数
     */
    public JRadarSeries() {
        this.type(JSeriesType.radar);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JRadarSeries(String name) {
        super(name);
        this.type(JSeriesType.radar);
    }

}

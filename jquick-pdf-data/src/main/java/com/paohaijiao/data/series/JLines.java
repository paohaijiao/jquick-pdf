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
 * 线图
 * 用于带有起点和终点信息的线数据的绘制，主要用于地图上的航线，路线的可视化
 *
 * @author martin
 */
@Getter
@Setter
public class JLines extends JSeries<JLines> {

    private JEffect effect;

    /**
     * 构造函数
     */
    public JLines() {
        this.type(JSeriesType.lines);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JLines(String name) {
        super(name);
        this.type(JSeriesType.lines);
    }

    public JEffect effect() {
        if (this.effect == null) {
            this.effect = new JEffect();
        }
        return this.effect;
    }

    public JLines effect(JEffect effect) {
        this.effect = effect;
        return this;
    }
}

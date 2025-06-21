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

package com.paohaijiao.data.axis;

import com.paohaijiao.data.code.JAxisType;
import lombok.Getter;
import lombok.Setter;

/**
 * 时间型坐标轴用法同数值型，只是目标处理和格式化显示时会自动转变为时间，并且随着时间跨度的不同自动切换需要显示的时间粒度
 *
 * @author martin
 */
@Getter
@Setter
public class JLogAxis extends JAxis<JLogAxis> {
    /**
     * axis.type === 'log'时生效。指定时，axisLabel显示为指数形式，如指定为4时，axisLabel可显示为4²、4³。不指定时，显示为普通形式，如 1,000,000
     */
    private Integer logLabelBase;
    /**
     * axis.type === 'log'时生效。指明是否使用反向log数轴（从而支持value为负值）。默认自适应，即如果value全为负值，则logPositive自动设为false，否则为true
     */
    private Boolean logPositive;

    /**
     * 构造函数
     */
    public JLogAxis() {
        this.type(JAxisType.log);
    }

    /**
     * 设置logLabelBase值
     *
     * @param logLabelBase
     */
    public JLogAxis logLabelBase(Integer logLabelBase) {
        this.logLabelBase = logLabelBase;
        return this;
    }

    /**
     * 获取logLabelBase值
     */
    public Integer logLabelBase() {
        return this.logLabelBase;
    }

    /**
     * 设置logPositive值
     *
     * @param logPositive
     */
    public JLogAxis logPositive(Boolean logPositive) {
        this.logPositive = logPositive;
        return this;
    }

    /**
     * 获取logPositive值
     */
    public Boolean logPositive() {
        return this.logPositive;
    }
}

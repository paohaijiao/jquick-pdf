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
package com.github.paohaijiao.series.other;

import com.github.paohaijiao.code.JBrushType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 涟漪特效相关配置
 *
 * @author martin
 */
@Getter
@Setter
public class JRippleEffect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 动画的时间
     */
    private Integer period;
    /**
     * 动画中波纹的最大缩放比例
     */
    private Double scale;
    /**
     * 波纹的填充方式，可选 'stroke' 和 'fill'
     */
    private JBrushType brushType;

    public Integer period() {
        return this.period;
    }

    public JRippleEffect period(Integer period) {
        this.period = period;
        return this;
    }

    public Double scale() {
        return this.scale;
    }

    public JRippleEffect scale(Double scale) {
        this.scale = scale;
        return this;
    }

    public JBrushType brushType() {
        return this.brushType;
    }

    public JRippleEffect brushType(JBrushType brushType) {
        this.brushType = brushType;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public JBrushType getBrushType() {
        return brushType;
    }

    public void setBrushType(JBrushType brushType) {
        this.brushType = brushType;
    }
}

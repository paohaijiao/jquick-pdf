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

import com.paohaijiao.data.code.JEffectType;
import com.paohaijiao.data.code.JSeriesType;
import com.paohaijiao.data.code.JShowEffectOn;
import com.paohaijiao.data.series.other.JRippleEffect;
import lombok.Getter;
import lombok.Setter;

/**
 * 带有涟漪特效动画的散点（气泡）图
 *
 * @author martin
 */
@Getter
@Setter
public class JEffectScatter extends JSeries<JEffectScatter> {
    /**
     * 特效类型
     */
    private Object effectType;
    /**
     * 配置何时显示特效
     */
    private Object showEffectOn;
    /**
     * 涟漪特效相关配置
     */
    private JRippleEffect rippleEffect;

    /**
     * 构造函数
     */
    public JEffectScatter() {
        this.type(JSeriesType.effectScatter);
    }

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public JEffectScatter(String name) {
        super(name);
        this.type(JSeriesType.effectScatter);
    }

    public Object effectType() {
        return this.effectType;
    }

    public JEffectScatter effectType(Object effectType) {
        this.effectType = effectType;
        return this;
    }

    public JEffectScatter effectType(JEffectType effectType) {
        this.effectType = effectType;
        return this;
    }

    public Object showEffectOn() {
        return this.showEffectOn;
    }

    public JEffectScatter showEffectOn(Object showEffectOn) {
        this.showEffectOn = showEffectOn;
        return this;
    }

    public JEffectScatter showEffectOn(JShowEffectOn showEffectOn) {
        this.showEffectOn = showEffectOn;
        return this;
    }

    public JRippleEffect rippleEffect() {
        return this.rippleEffect;
    }

    public JEffectScatter rippleEffect(JRippleEffect rippleEffect) {
        this.rippleEffect = rippleEffect;
        return this;
    }

}

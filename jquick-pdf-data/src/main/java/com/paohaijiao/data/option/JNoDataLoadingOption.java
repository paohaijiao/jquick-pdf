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

package com.paohaijiao.data.option;

import com.paohaijiao.data.code.JLoadingEffect;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述信息
 *
 * @author martin
 */
@Getter
@Setter
public class JNoDataLoadingOption {
    private String text;
    private Object x;
    private Object y;
    /**
     * 显示话术的文本样式
     */
    private JTextStyle textStyle;
    /**
     * loading效果，可以参考LoadingEffect
     */
    private Object effect;
    /**
     * loading效果选项，详见zrender
     */
    private JEffectOption effectOption;
    /**
     * 指定当前进度[0~1]，个别效果有效
     */
    private Integer progress;

    /**
     * 获取text值
     */
    public String text() {
        return this.text;
    }

    /**
     * 设置text值
     *
     * @param text
     */
    public JNoDataLoadingOption text(String text) {
        this.text = text;
        return this;
    }

    /**
     * 获取x值
     */
    public Object x() {
        return this.x;
    }

    /**
     * 设置x值
     *
     * @param x
     */
    public JNoDataLoadingOption x(Object x) {
        this.x = x;
        return this;
    }

    /**
     * 获取y值
     */
    public Object y() {
        return this.y;
    }

    /**
     * 设置y值
     *
     * @param y
     */
    public JNoDataLoadingOption y(Object y) {
        this.y = y;
        return this;
    }

    /**
     * 获取textStyle值
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JNoDataLoadingOption textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 获取effect值
     */
    public Object effect() {
        return this.effect;
    }

    /**
     * 设置effect值
     *
     * @param effect
     */
    public JNoDataLoadingOption effect(Object effect) {
        this.effect = effect;
        return this;
    }

    /**
     * 设置effect值
     *
     * @param effect
     */
    public JNoDataLoadingOption effect(JLoadingEffect effect) {
        this.effect = effect;
        return this;
    }

    /**
     * 获取effectOption值
     */
    public JEffectOption effectOption() {
        if (this.effectOption == null) {
            this.effectOption = new JEffectOption();
        }
        return this.effectOption;
    }

    /**
     * 设置effectOption值
     *
     * @param effectOption
     */
    public JNoDataLoadingOption effectOption(JEffectOption effectOption) {
        this.effectOption = effectOption;
        return this;
    }

    /**
     * 获取progress值
     */
    public Integer progress() {
        return this.progress;
    }

    /**
     * 设置progress值
     *
     * @param progress
     */
    public JNoDataLoadingOption progress(Integer progress) {
        this.progress = progress;
        return this;
    }
}

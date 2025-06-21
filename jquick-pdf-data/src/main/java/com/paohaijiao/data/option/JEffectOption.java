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

import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * loading参数
 *
 * @author martin
 */
@Getter
@Setter
public class JEffectOption {
    private Object effect;
    private Integer progress;
    private JTextStyle textStyle;

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
	public JEffectOption effect(Object effect) {
        this.effect = effect;
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
	public JEffectOption progress(Integer progress) {
        this.progress = progress;
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
	public JEffectOption textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }
}

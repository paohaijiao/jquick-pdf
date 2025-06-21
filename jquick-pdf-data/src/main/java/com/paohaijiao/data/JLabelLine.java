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

package com.paohaijiao.data;

import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 标签视觉引导线l
 *
 * @author martin
 */
@Getter
@Setter
public class JLabelLine implements Serializable {

    private static final long serialVersionUID = 3359589478018844177L;

    /**
     * 饼图标签视觉引导线显示策略，可选为：true（显示） | false（隐藏）
     */
    private Boolean show;
    /**
     * 线长 ，从外圆边缘起计算，可为负值
     */
    private Integer length;
    /**
     * 线条样式，详见lineStyle
     *
     * @see JLineStyle
     */
    private JLineStyle lineStyle;

    /**
     * 获取show值
     */
    public Boolean show() {
        return this.show;
    }

    /**
     * 设置show值
     *
     * @param show
     */
    public JLabelLine show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取length值
     */
    public Integer length() {
        return this.length;
    }

    /**
     * 设置length值
     *
     * @param length
     */
    public JLabelLine length(Integer length) {
        this.length = length;
        return this;
    }

    /**
     * 线条样式，详见lineStyle
     *
     * @see JLineStyle
     */
    public JLineStyle lineStyle() {
        if (this.lineStyle == null) {
            this.lineStyle = new JLineStyle();
        }
        return this.lineStyle;
    }

    /**
     * 设置style值
     *
     * @param style
     */
    public JLabelLine lineStyle(JLineStyle style) {
        this.lineStyle = style;
        return this;
    }
}

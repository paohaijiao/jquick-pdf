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

import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 坐标轴线
 *
 * @author martin
 */
@Getter
@Setter
public class JAxisLine implements Serializable {

    private static final long serialVersionUID = -7486014114670118509L;

    /**
     * 默认显示，属性show控制显示与否
     */
    private Boolean show;
    /**
     * 定位到垂直方向的0值坐标上
     */
    private Boolean onZero;
    /**
     * {color: '#48b', width: 2, type: 'solid'}
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
    public JAxisLine show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取onZero值
     */
    public Boolean onZero() {
        return this.onZero;
    }

    /**
     * 设置onZero值
     *
     * @param onZero
     */
    public JAxisLine onZero(Boolean onZero) {
        this.onZero = onZero;
        return this;
    }

    /**
     * {color: '#48b', width: 2, type: 'solid'}
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
     * 设置lineStyle值
     *
     * @param lineStyle
     */
    public JAxisLine lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }
}

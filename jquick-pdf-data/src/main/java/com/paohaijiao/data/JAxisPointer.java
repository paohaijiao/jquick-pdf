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

import com.paohaijiao.data.code.JPointerType;
import com.paohaijiao.data.style.JCrossStyle;
import com.paohaijiao.data.style.JLineStyle;
import com.paohaijiao.data.style.JShadowStyle;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 坐标轴指示器，坐标轴触发有效
 *
 * @author martin
 */
@Getter
@Setter
public class JAxisPointer implements Serializable {

    private static final long serialVersionUID = 6421899185681683630L;

    /**
     * 是否显示
     */
    private Boolean show;

    /**
     * 默认为直线，可选为：'line' | 'shadow' | 'cross'
     *
     * @see JPointerType
     */
    private JPointerType type;
    /**
     * 设置直线指示器
     *
     * @see JLineStyle
     */
    private JLineStyle lineStyle;
    /**
     * 设置十字准星指示器
     */
    private JCrossStyle crossStyle;
    /**
     * 设置阴影指示器
     */
    private JShadowStyle shadowStyle;
    /**
     * 文本样式
     */
    private JTextStyle textStyle;

    /**
     * 获取textStyle值
     */
    public JTextStyle textStyle() {
        return this.textStyle;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JAxisPointer textStyle(JTextStyle textStyle) {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 设置lineStyle值
     *
     * @param lineStyle
     */
    public JAxisPointer lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

    /**
     * 设置crossStyle值
     *
     * @param crossStyle
     */
    public JAxisPointer crossStyle(JCrossStyle crossStyle) {
        this.crossStyle = crossStyle;
        return this;
    }

    /**
     * 设置shadowStyle值
     *
     * @param shadowStyle
     */
    public JAxisPointer shadowStyle(JShadowStyle shadowStyle) {
        this.shadowStyle = shadowStyle;
        return this;
    }

    /**
     * 获取type值
     */
    public JPointerType type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JAxisPointer type(JPointerType type) {
        this.type = type;
        return this;
    }

    /**
     * 是否显示
     */
    public Boolean show(){
        return this.show;
    }

    /**
     * 设置是否显示
     */
    public JAxisPointer show(Boolean show){
        this.show = show;
        return this;
    }

    /**
     * 设置直线指示器
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
     * 设置十字准星指示器
     */
    public JCrossStyle crossStyle() {
        if (this.crossStyle == null) {
            this.crossStyle = new JCrossStyle();
        }
        return this.crossStyle;
    }

    /**
     * 设置阴影指示器
     */
    public JShadowStyle shadowStyle() {
        if (this.shadowStyle == null) {
            this.shadowStyle = new JShadowStyle();
        }
        return this.shadowStyle;
    }
}

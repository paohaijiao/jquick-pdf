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
import com.paohaijiao.data.code.JNameLocation;
import com.paohaijiao.data.style.JLineStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 值轴
 *
 * @author martin.
 */
@Getter
@Setter
public class JValueAxis extends JAxis<JValueAxis> {
    /**
     * 坐标轴名称位置，默认为'end'，可选为：'start' | 'end'
     *
     * @see JNameLocation
     */
    private JNameLocation nameLocation;
    /**
     * 坐标轴名称文字样式，默认取全局配置，颜色跟随axisLine主色，可设
     */
    private JLineStyle nameTextStyle;
    /**
     * 小数精度，默认为0，无小数点
     */
    private Integer precision;
    /**
     * 整数精度，默认为100，个位和百位为0
     */
    private Integer power;
    /**
     * 分割段数，默认为5
     */
    private Integer splitNumber;

    /**
     * 构造函数
     */
    public JValueAxis() {
        this.type(JAxisType.value);
    }

    /**
     * 获取precision值
     */
    public Integer precision() {
        return this.precision;
    }

    /**
     * 设置precision值
     *
     * @param precision
     */
    public JValueAxis precision(Integer precision) {
        this.precision = precision;
        return this;
    }

    /**
     * 获取power值
     */
    public Integer power() {
        return this.power;
    }

    /**
     * 设置power值
     *
     * @param power
     */
    public JValueAxis power(Integer power) {
        this.power = power;
        return this;
    }

    /**
     * 获取splitNumber值
     */
    public Integer splitNumber() {
        return this.splitNumber;
    }

    /**
     * 设置splitNumber值
     *
     * @param splitNumber
     */
    public JValueAxis splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }
    /**
     * 设置nameLocation值
     *
     * @param nameLocation
     */
    public JValueAxis nameLocation(JNameLocation nameLocation) {
        this.nameLocation = nameLocation;
        return this;
    }

    /**
     * 坐标轴名称文字样式，默认取全局配置，颜色跟随axisLine主色，可设
     */
    public JLineStyle nameTextStyle() {
        if (this.nameTextStyle == null) {
            this.nameTextStyle = new JLineStyle();
        }
        return this.nameTextStyle;
    }

    /**
     * 设置style值
     *
     * @param style
     */
    public JValueAxis nameTextStyle(JLineStyle style) {
        this.nameTextStyle = style;
        return this;
    }

}

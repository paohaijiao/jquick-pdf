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

import com.paohaijiao.data.code.JOrient;
import com.paohaijiao.data.code.JSelectedMode;
import com.paohaijiao.data.data.JRangeData;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 值域选择，每个图表最多仅有一个值域控件
 *
 * @author martin
 */
@Getter
@Setter
public class JDataRange extends JBasic<JDataRange> implements JComponent {
    /**
     * 布局方式，默认为垂直布局，可选为：'horizontal' | 'vertical'
     *
     * @see JOrient
     */
    private JOrient orient;
    /**
     * 默认值20，值域控件图形宽度
     */
    private Integer itemWidth;
    /**
     * 默认值14，值域控件图形高度
     */
    private Integer itemHeight;
    /**
     * 指定的最小值，eg: 0，默认无，必须参数
     */
    private Integer min;
    /**
     * 指定的最大值，eg: 100，默认无，必须参数
     */
    private Integer max;
    /**
     * 小数精度，默认为0，无小数点，当 min ~ max 间在当前精度下无法整除splitNumber份时，精度会自动提高以满足均分，不支持不等划分
     */
    private Integer precision;
    /**
     * 分割段数，默认为5，为0时为线性渐变，calculable为true是默认均分100份
     */
    private Integer splitNumber;
    /**
     * 是否启用值域漫游，启用后无视splitNumber，值域显示为线性渐变
     */
    private Boolean calculable;
    /**
     * 值域漫游是否实时显示
     */
    private Boolean realtime;
    /**
     * 值域颜色标识，颜色数组长度必须>=2，颜色代表从数值高到低的变化，即颜色数组低位代表数值高的颜色标识
     */
    private List<String> color;
    /**
     * 内容格式器：{string}（Template） | {Function}，模板变量为'{value}'和'{value2}'，代表数值起始值和结束值，函数参数两个，含义同模板变量
     */
    private Object formatter;
    /**
     * 值域文字显示，splitNumber生效时默认以计算所得数值作为值域文字显示，可指定长度为2的文本数组显示简介的值域文本，如['高', '低']，'\n'指定换行
     */
    private List<String> text;
    /**
     * 默认只设定了值域控件文字颜色
     */
    private JTextStyle textStyle;

    /**
     * 是否启用地图hover时的联动响应
     */
    private Boolean hoverLink;

    /**
     * 用于设置dataRange的初始选中范围。calculable为true时有效。
     */
    private JRangeData range;
    /**
     * 自定义分割方式，支持不等距分割。splitList被指定时，splitNumber将被忽略。
     */
    private List<JRangeData> splitList;
    /**
     * 选择模式，默认开启图例开关
     *
     * @see JSelectedMode
     */
    private Object selectedMode;

    /**
     * 获取selectedMode值
     */
    public Object selectedMode() {
        return this.selectedMode;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public JDataRange selectedMode(Object selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    /**
     * 设置selectedMode值
     *
     * @param selectedMode
     */
    public JDataRange selectedMode(JSelectedMode selectedMode) {
        this.selectedMode = selectedMode;
        return this;
    }

    /**
     * 设置range值
     *
     * @param range
     */
    public JDataRange range(JRangeData range) {
        this.range = range;
        return this;
    }

    /**
     * 获取range值
     */
	public JRangeData range() {
        return this.range;
    }

    /**
     * 设置splitList值
     *
     * @param splitList
     */
    public JDataRange splitList(JRangeData... splitList) {
        if (splitList == null || splitList.length == 0) {
            return this;
        }
        this.splitList().addAll(Arrays.asList(splitList));
        return this;
    }

    /**
     * 获取splitList值
     */
	public List<JRangeData> splitList() {
        if (this.splitList == null) {
            this.splitList = new ArrayList<JRangeData>();
        }
        return this.splitList;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public JDataRange color(List<String> color) {
        this.color = color;
        return this;
    }

    /**
     * 设置text值
     *
     * @param text
     */
    public JDataRange text(List<String> text) {
        this.text = text;
        return this;
    }

    /**
     * 获取hoverLink值
     */
    public Boolean hoverLink() {
        return this.hoverLink;
    }

    /**
     * 设置hoverLink值
     *
     * @param hoverLink
     */
    public JDataRange hoverLink(Boolean hoverLink) {
        this.hoverLink = hoverLink;
        return this;
    }

    /**
     * 设置textStyle值
     *
     * @param textStyle
     */
    public JDataRange textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 获取orient值
     */
    public JOrient orient() {
        return this.orient;
    }

    /**
     * 设置orient值
     *
     * @param orient
     */
    public JDataRange orient(JOrient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * 获取itemWidth值
     */
    public Integer itemWidth() {
        return this.itemWidth;
    }

    /**
     * 设置itemWidth值
     *
     * @param itemWidth
     */
    public JDataRange itemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
        return this;
    }

    /**
     * 获取itemHeight值
     */
    public Integer itemHeight() {
        return this.itemHeight;
    }

    /**
     * 设置itemHeight值
     *
     * @param itemHeight
     */
    public JDataRange itemHeight(Integer itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    /**
     * 获取min值
     */
    public Integer min() {
        return this.min;
    }

    /**
     * 设置min值
     *
     * @param min
     */
    public JDataRange min(Integer min) {
        this.min = min;
        return this;
    }

    /**
     * 获取max值
     */
    public Integer max() {
        return this.max;
    }

    /**
     * 设置max值
     *
     * @param max
     */
    public JDataRange max(Integer max) {
        this.max = max;
        return this;
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
    public JDataRange precision(Integer precision) {
        this.precision = precision;
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
    public JDataRange splitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
        return this;
    }

    /**
     * 获取calculable值
     */
    public Boolean calculable() {
        return this.calculable;
    }

    /**
     * 设置calculable值
     *
     * @param calculable
     */
    public JDataRange calculable(Boolean calculable) {
        this.calculable = calculable;
        return this;
    }

    /**
     * 获取realtime值
     */
    public Boolean realtime() {
        return this.realtime;
    }

    /**
     * 设置realtime值
     *
     * @param realtime
     */
    public JDataRange realtime(Boolean realtime) {
        this.realtime = realtime;
        return this;
    }

    /**
     * 值域颜色标识，颜色数组长度必须>=2，颜色代表从数值高到低的变化，即颜色数组低位代表数值高的颜色标识
     */
    public List<String> color() {
        if (this.color == null) {
            this.color = new ArrayList<String>();
        }
        return this.color;
    }

    /**
     * 值域颜色标识，颜色数组长度必须>=2，颜色代表从数值高到低的变化，即颜色数组低位代表数值高的颜色标识
     *
     * @param colors
     * @return
     */
    public JDataRange color(String... colors) {
        if (colors == null || colors.length == 0) {
            return this;
        }
        this.color().addAll(Arrays.asList(colors));
        return this;
    }

    /**
     * 获取formatter值
     */
    public Object formatter() {
        return this.formatter;
    }

    /**
     * 设置formatter值
     *
     * @param formatter
     */
    public JDataRange formatter(Object formatter) {
        this.formatter = formatter;
        return this;
    }

    /**
     * 值域文字显示，splitNumber生效时默认以计算所得数值作为值域文字显示，可指定长度为2的文本数组显示简介的值域文本，如['高', '低']，'\n'指定换行
     */
    public List<String> text() {
        if (this.text == null) {
            this.text = new ArrayList<String>();
        }
        return this.text;
    }

    /**
     * 值域文字显示，splitNumber生效时默认以计算所得数值作为值域文字显示，可指定长度为2的文本数组显示简介的值域文本，如['高', '低']，'\n'指定换行
     *
     * @param texts
     * @return
     */
    public JDataRange text(String... texts) {
        if (texts == null || texts.length == 0) {
            return this;
        }
        this.text().addAll(Arrays.asList(texts));
        return this;
    }

    /**
     * 默认只设定了值域控件文字颜色
     */
    public JTextStyle textStyle() {
        if (this.textStyle == null) {
            this.textStyle = new JTextStyle();
        }
        return this.textStyle;
    }
}

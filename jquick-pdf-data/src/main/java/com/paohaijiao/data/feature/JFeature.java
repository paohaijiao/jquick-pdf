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

package com.paohaijiao.data.feature;

import com.paohaijiao.data.code.JLineType;
import com.paohaijiao.data.code.JMagic;
import com.paohaijiao.data.style.JLineStyle;
import com.paohaijiao.data.style.JTextStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author martin
 */
@Getter
@Setter
public class JFeature implements Serializable {

    /**
     * 获取DataView值
     */
    public static final JDataView dataView = new JDataView();
    /**
     * 获取DataZoom值
     */
    public static final JDataZoom dataZoom = new JDataZoom();
    /**
     * 获取Mark值
     */
    public static final JMark mark = new JMark();
    /**
     * 获取SaveAsImage值
     */
    public static final JSaveAsImage saveAsImage = new JSaveAsImage();
    /**
     * 获取MagicType值
     */
    public static final JMagicType magicType = new JMagicType();
    /**
     * 获取Restore值
     */
    public static final JRestore restore = new JRestore();
    private static final long serialVersionUID = 8546465308711709471L;
    /**
     * 是否显示
     */
    private Boolean show;
    /**
     * 标题
     */
    private Object title;
    /**
     * 类型
     *
     * @see JMagic
     * @see JLineType
     */
    private Object type;
    /**
     * 只读
     */
    private Boolean readOnly;
    /**
     * lang 非IE浏览器支持点击下载，有保存话术，默认是“点击保存”，可修改
     */
    private Object lang;
    /**
     * 线条颜色
     *
     * @see JLineStyle
     */
    private JLineStyle lineStyle;
    /**
     * 文字颜色
     *
     * @see JTextStyle
     */
    private JTextStyle textStyle;
    /**
     * 图标，image://开头
     */
    private String icon;

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
    public JFeature show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 获取title值
     */
    public Object title() {
        return this.title;
    }

    /**
     * 设置title值
     *
     * @param title
     */
    public JFeature title(Object title) {
        this.title = title;
        return this;
    }

    /**
     * 获取type值
     */
    public Object type() {
        return this.type;
    }

    /**
     * 设置type值
     *
     * @param type
     */
    public JFeature type(Object type) {
        this.type = type;
        return this;
    }

    /**
     * 获取readOnly值
     */
    public Boolean readOnly() {
        return this.readOnly;
    }

    /**
     * 设置readOnly值
     *
     * @param readOnly
     */
    public JFeature readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * 获取lang值
     */
    public Object lang() {
        return this.lang;
    }

    /**
     * 设置lang值
     *
     * @param lang
     */
    public JFeature lang(Object lang) {
        this.lang = lang;
        return this;
    }

    /**
     * 获取lineStyle值
     */
    public JLineStyle lineStyle() {
        return this.lineStyle;
    }

    /**
     * 设置lineStyle值
     *
     * @param lineStyle
     */
    public JFeature lineStyle(JLineStyle lineStyle) {
        this.lineStyle = lineStyle;
        return this;
    }

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
    public JFeature textStyle(JTextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * 获取icon值
     */
    public String icon() {
        return this.icon;
    }

    /**
     * 设置icon值
     *
     * @param icon
     */
    public JFeature icon(String icon) {
        this.icon = icon;
        return this;
    }
}

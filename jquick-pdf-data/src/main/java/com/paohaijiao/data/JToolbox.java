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
import com.paohaijiao.data.code.JTool;
import com.paohaijiao.data.feature.JFeature;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author martin
 */
@Getter
@Setter
public class JToolbox extends JBasic<JToolbox> implements JComponent {
    /**
     * 启用功能，目前支持feature见下，工具箱自定义功能回调处理
     */
    private Map<String, JFeature> feature;
    /**
     * 布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
     *
     * @see JOrient
     */
    private JOrient orient;
    /**
     * 工具箱背景颜色，默认透明
     */
    private List<Object> color;
    /**
     * 无效颜色
     */
    private String disableColor;
    /**
     * 激活颜色
     */
    private String effectiveColor;
    /**
     * 工具箱icon大小，单位（px）
     */
    private Integer itemSize;
    /**
     * 是否显示工具箱文字提示，默认启用
     */
    private Boolean showTitle;
    /**
     * 公用的 icon 样式设置
     */
    private JItemStyle iconStyle;

    public JItemStyle iconStyle() {
        return this.iconStyle;
    }

    public JToolbox iconStyle(JItemStyle iconStyle) {
        this.iconStyle = iconStyle;
        return this;
    }

    /**
     * 设置color值
     *
     * @param color
     */
    public JToolbox color(List<Object> color) {
        this.color = color;
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
    public JToolbox orient(JOrient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * 工具箱背景颜色，默认透明
     */
    public List<Object> color() {
        if (this.color == null) {
            this.color = new ArrayList<Object>();
        }
        return this.color;
    }

    /**
     * 获取disableColor值
     */
    public String disableColor() {
        return this.disableColor;
    }

    /**
     * 设置disableColor值
     *
     * @param disableColor
     */
    public JToolbox disableColor(String disableColor) {
        this.disableColor = disableColor;
        return this;
    }

    /**
     * 获取effectiveColor值
     */
    public String effectiveColor() {
        return this.effectiveColor;
    }

    /**
     * 设置effectiveColor值
     *
     * @param effectiveColor
     */
    public JToolbox effectiveColor(String effectiveColor) {
        this.effectiveColor = effectiveColor;
        return this;
    }

    /**
     * 获取itemSize值
     */
    public Integer itemSize() {
        return this.itemSize;
    }

    /**
     * 设置itemSize值
     *
     * @param itemSize
     */
    public JToolbox itemSize(Integer itemSize) {
        this.itemSize = itemSize;
        return this;
    }

    /**
     * 获取showTitle值
     */
    public Boolean showTitle() {
        return this.showTitle;
    }

    /**
     * 设置showTitle值
     *
     * @param showTitle
     */
    public JToolbox showTitle(Boolean showTitle) {
        this.showTitle = showTitle;
        return this;
    }

    /**
     * 启用功能，目前支持feature见下，工具箱自定义功能回调处理
     */
    public Map<String, JFeature> feature() {
        if (this.feature == null) {
            this.feature = new LinkedHashMap<String, JFeature>();
        }
        return this.feature;
    }

    /**
     * 添加组件
     *
     * @param value
     * @return
     */
    private JToolbox _addFeature(JFeature value) {
        if (value == null) {
            return this;
        }
        //第一个字母转小写
        String name = value.getClass().getSimpleName();
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        _addFeatureOnce(name, value);
        return this;
    }

    /**
     * 添加组件
     *
     * @param values
     * @return
     */
    public JToolbox feature(Object... values) {
        if (values == null && values.length == 0) {
            return this;
        }
        if (this.feature == null) {
            this.feature = new LinkedHashMap<String, JFeature>();
        }
        for (Object t : values) {
            if (t instanceof JFeature) {
                _addFeature((JFeature) t);
            } else if (t instanceof JTool) {
                switch ((JTool) t) {
                    case dataView:
                        _addFeatureOnce(t, JFeature.dataView);
                        break;
                    case dataZoom:
                        _addFeatureOnce(t, JFeature.dataZoom);
                        break;
                    case magicType:
                        _addFeatureOnce(t, JFeature.magicType);
                        break;
                    case mark:
                        _addFeatureOnce(t, JFeature.mark);
                        break;
                    case restore:
                        _addFeatureOnce(t, JFeature.restore);
                        break;
                    case saveAsImage:
                        _addFeatureOnce(t, JFeature.saveAsImage);
                        break;
                    default:
                        //ignore
                }
            }
        }
        return this;
    }

    /**
     * 同一种组件只添加一次
     *
     * @param name
     * @param feature
     */
    private void _addFeatureOnce(Object name, JFeature feature) {
        String _name = String.valueOf(name);
        if (!this.feature().containsKey(_name)) {
            this.feature().put(_name, feature);
        }
    }
}

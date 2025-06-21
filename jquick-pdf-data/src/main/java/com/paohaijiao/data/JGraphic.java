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

import com.paohaijiao.data.code.JGraphicType;
import com.paohaijiao.data.style.JGraphicStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 图形元素组件
 *
 * @author martin
 */
@Getter
@Setter
public class JGraphic extends JBasic<JGraphic> implements JComponent {

    /**
     * id 用于在更新图形元素时指定更新哪个图形元素，如果不需要用可以忽略
     */
    private String id;

    /**
     * 这个字段在第一次设置时不能忽略，取值见上方『支持的图形元素』
     */
    private JGraphicType type;

    // 下面的各个属性如果不需要设置都可以忽略，忽略则取默认值

    /**
     * 指定本次 setOption 对此图形元素进行的操作。默认是 'merge'，还可以 'replace' 或 'remove'
     */
    private String $action;

    /**
     * 定位、形状相关的设置，如 x, y, cx, cy, width, height, r, points 等
     * 注意，如果设置了 left/right/top/bottom，这里的定位用的 x/y/cx/cy 会失效
     */
    private Object shape;

    /**
     * 样式相关的设置，如 fill, stroke, lineWidth, shadowBlur 等
     */
    private JGraphicStyle style;

    /**
     * 表示不响应事件
     */
    private Boolean silent;

    /**
     * 表示节点不显示
     */
    private Boolean invisible;

    /**
     * 设置是否整体限制在父节点范围内。可选值：'raw', 'all'
     */
    private String bouding;

    /**
     * 是否可以被拖拽
     */
    private Boolean draggable;

    /**
     * 事件的监听器，还可以是 onmousemove, ondrag 等
     */
    private String onclick;

    public JGraphic id(String id) {
        this.id = id;
        return this;
    }

    public JGraphic type(JGraphicType type) {
        this.type = type;
        return this;
    }

    public JGraphic $action(String $action) {
        this.$action = $action;
        return this;
    }

    public JGraphic shape(Object shape) {
        this.shape = shape;
        return this;
    }

    public JGraphic style(JGraphicStyle style) {
        this.style = style;
        return this;
    }

    public JGraphic silent(Boolean silent) {
        this.silent = silent;
        return this;
    }

    public JGraphic invisible(Boolean invisible) {
        this.invisible = invisible;
        return this;
    }

    public JGraphic bouding(String bouding) {
        this.bouding = bouding;
        return this;
    }

    public JGraphic draggable(Boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public JGraphic onclick(String onclick) {
        this.onclick = onclick;
        return this;
    }

}

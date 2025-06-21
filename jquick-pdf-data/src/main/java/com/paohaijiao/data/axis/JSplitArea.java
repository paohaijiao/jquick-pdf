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

import com.paohaijiao.data.style.JAreaStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * 分隔区域
 *
 * @author martin
 */
@Getter
@Setter
public class JSplitArea implements java.io.Serializable {

    private static final long serialVersionUID = -5528605504445280383L;

    /**
     * 默认不显示，属性show控制显示与否
     */
    private Boolean show;
    /**
     * 属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see JAreaStyle
     */
    private JAreaStyle areaStyle;

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
    public JSplitArea show(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * 设置areaStyle值
     *
     * @param areaStyle
     */
    public JSplitArea areaStyle(JAreaStyle areaStyle) {
        this.areaStyle = areaStyle;
        return this;
    }

    /**
     * 属性areaStyle（详见areaStyle）控制区域样式
     *
     * @see JAreaStyle
     */
    public JAreaStyle areaStyle() {
        if (this.areaStyle == null) {
            this.areaStyle = new JAreaStyle();
        }
        return this.areaStyle;
    }
}

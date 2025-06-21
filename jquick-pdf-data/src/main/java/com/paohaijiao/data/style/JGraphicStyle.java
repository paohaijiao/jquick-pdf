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

package com.paohaijiao.data.style;

import com.paohaijiao.data.code.JX;
import lombok.Getter;
import lombok.Setter;

/**
 * 图形元素样式
 *
 * @author martin
 */
@Getter
@Setter
public class JGraphicStyle {

    /**
     * 文本
     */
    private String text;

    /**
     * 文本排列
     */
    private JX textAlign;

    /**
     *文本填充颜色
     */
    private String fill;

    /**
     * 宽
     */
    private Object width;

    /**
     * 高
     */
    private Object height;

    
    public JGraphicStyle text(String text) {
        this.text = text;
        return this;
    }

    
    public JGraphicStyle textAlign(JX textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    
    public JGraphicStyle fill(String fill) {
        this.fill = fill;
        return this;
    }

    
    public JGraphicStyle width(Object width) {
        this.width = width;
        return this;
    }

    
    public JGraphicStyle height(Object height) {
        this.height = height;
        return this;
    }
    
}

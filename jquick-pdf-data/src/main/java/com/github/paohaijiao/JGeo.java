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
package com.github.paohaijiao;

import com.github.paohaijiao.code.JRoam;
import com.github.paohaijiao.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 */
@Getter
@Setter
public class JGeo extends JBasic<JGeo> implements JComponent {
    private String map;
    private JRoam roam;
    private JItemStyle label;
    private JItemStyle itemStyle;

    public String map() {
        return this.map;
    }

    public JGeo map(String map) {
        this.map = map;
        return this;
    }

    public JRoam roam() {
        return this.roam;
    }

    public JGeo roam(JRoam roam) {
        this.roam = roam;
        return this;
    }

    public JItemStyle label() {
        if (this.label == null) {
            this.label = new JItemStyle();
        }
        return this.label;
    }

    public JGeo label(JItemStyle label) {
        this.label = label;
        return this;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    public JGeo itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }
}

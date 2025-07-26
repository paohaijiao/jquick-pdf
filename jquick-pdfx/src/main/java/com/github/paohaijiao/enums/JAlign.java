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
package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.TextAlignment;
import lombok.Getter;

@Getter
public enum JAlign {
    left("left", "left"),
    center("center", "center"),
    justify("justify", "justify"),
    right("right", "right");

    private String code;
    private String name;

    JAlign(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static JAlign codeOf(String code) {
        for (JAlign j : JAlign.values()) {
            if (j.code.equals(code)) {
                return j;
            }
        }
        return null;
    }

    public static TextAlignment textAlignOf(String code) {
        if (JAlign.codeOf(code).getCode().equals(JAlign.left.getCode())) {
            return TextAlignment.LEFT;
        }
        if (JAlign.codeOf(code).getCode().equals(JAlign.right.getCode())) {
            return TextAlignment.RIGHT;
        }
        if (JAlign.codeOf(code).getCode().equals(JAlign.center.getCode())) {
            return TextAlignment.CENTER;
        }
        if (JAlign.codeOf(code).getCode().equals(JAlign.justify.getCode())) {
            return TextAlignment.JUSTIFIED;
        }
        return TextAlignment.LEFT;
    }
}

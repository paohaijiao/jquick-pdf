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

import lombok.Getter;

@Getter
public enum JFrontStyle {
    Italic("italic", "italic"),
    Underline("underline", "underline"),
    Strikethrough("strikethrough", "strikethrough"),
    oblique("oblique", "oblique"),
    normal("normal", "normal"),
    initial("initial", "initial"),
    inherit("inherit", "inherit");

    private String code;
    private String name;

    private JFrontStyle(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String codeOf(String code) {
        for (JFrontStyle style : JFrontStyle.values()) {
            if (style.code.equals(code)) {
                return style.name;
            }
        }
        return code;
    }
}

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

import com.itextpdf.layout.properties.FontKerning;
import lombok.Getter;

@Getter
public enum JFontKerning {

    yes("yes", FontKerning.YES),
    no("no", FontKerning.NO);
    private String code;

    private FontKerning type;

    private JFontKerning(String code, FontKerning type) {
        this.code = code;
        this.type = type;
    }

    public static JFontKerning codeOf(String code) {
        for (JFontKerning type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}

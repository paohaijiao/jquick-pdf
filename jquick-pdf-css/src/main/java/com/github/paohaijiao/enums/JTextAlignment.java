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
public enum JTextAlignment {
    left("left", TextAlignment.LEFT),

    center("center", TextAlignment.CENTER),
    right("right", TextAlignment.RIGHT),
    justified("justified", TextAlignment.JUSTIFIED),
    justified_all("justified_all", TextAlignment.JUSTIFIED_ALL);
    private String code;

    private TextAlignment type;

    private JTextAlignment(String code, TextAlignment type) {
        this.code = code;
        this.type = type;
    }
    public static JTextAlignment codeOf(String code) {
        for (JTextAlignment type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

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
public enum JFontEnum {

    COURIER("Courier"),
    COURIER_BOLD("Courier-Bold"),
    COURIER_OBLIQUE("Courier-Oblique"),
    COURIER_BOLDOBLIQUE("Courier-BoldOblique"),
    HELVETICA("Helvetica"),
    HELVETICA_BOLD("Helvetica-Bold"),
    HELVETICA_OBLIQUE("Helvetica-Oblique"),
    HELVETICA_BOLDOBLIQUE("Helvetica-BoldOblique"),
    SYMBOL("Symbol"),
    TIMES_ROMAN("Times-Roman"),
    TIMES_BOLD("Times-Bold"),
    TIMES_ITALIC("Times-Italic"),
    TIMES_BOLDITALIC("Times-BoldItalic"),
    ZAPFDINGBATS("ZapfDingbats");

    private final String fontName;

    JFontEnum(String fontName) {
        this.fontName = fontName;
    }

    public static JFontEnum codeOf(String code) {
        for (JFontEnum fontEnum : JFontEnum.values()) {
            if (fontEnum.getFontName().equalsIgnoreCase(code)) {
                return fontEnum;
            }
        }
        return null;
    }
}

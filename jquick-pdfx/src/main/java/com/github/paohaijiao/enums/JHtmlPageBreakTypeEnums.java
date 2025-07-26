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

import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreakType;
import lombok.Getter;

@Getter
public enum JHtmlPageBreakTypeEnums {

    next_area("next_area", HtmlPageBreakType.ALWAYS),
    next_page("next_page", HtmlPageBreakType.LEFT),
    last_page("last_page", HtmlPageBreakType.RIGHT);

    private String code;

    private HtmlPageBreakType type;

    JHtmlPageBreakTypeEnums(String code, HtmlPageBreakType type) {
        this.code = code;
        this.type = type;
    }

    public static JHtmlPageBreakTypeEnums codeOf(String code) {
        for (JHtmlPageBreakTypeEnums j : JHtmlPageBreakTypeEnums.values()) {
            if (j.getCode().equalsIgnoreCase(code)) {
                return j;
            }
        }
        return null;
    }
}

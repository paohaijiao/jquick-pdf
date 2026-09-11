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
public enum JHtmlPageBreakTypeEnums {

    next_area("next_area"),
    next_page("next_page"),
    last_page("last_page");

    private String code;

    JHtmlPageBreakTypeEnums(String code) {
        this.code = code;
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

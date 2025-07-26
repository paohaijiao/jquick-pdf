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

import com.itextpdf.layout.properties.AreaBreakType;
import lombok.Getter;

@Getter
public enum JAreaBreakEnums {

    next_area("next_area", AreaBreakType.NEXT_AREA),
    next_page("next_page", AreaBreakType.NEXT_PAGE),
    last_page("last_page", AreaBreakType.LAST_PAGE);

    private String code;

    private AreaBreakType type;

    JAreaBreakEnums(String code, AreaBreakType type) {
        this.code = code;
        this.type = type;
    }

    public static JAreaBreakEnums codeOf(String code) {
        for (JAreaBreakEnums j : JAreaBreakEnums.values()) {
            if (j.code.equalsIgnoreCase(code)) {
                return j;
            }
        }
        return null;
    }
}

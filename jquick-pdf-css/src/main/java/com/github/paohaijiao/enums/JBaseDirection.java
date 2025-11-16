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

import com.itextpdf.layout.properties.BaseDirection;
import lombok.Getter;

@Getter
public enum JBaseDirection {

    no_bidi("no_bidi", BaseDirection.NO_BIDI),
    default_bidi("default_bidi", BaseDirection.DEFAULT_BIDI),
    left_to_right("left_to_right", BaseDirection.LEFT_TO_RIGHT),
    right_to_left("right_to_left", BaseDirection.RIGHT_TO_LEFT);
    private String code;

    private BaseDirection type;

    private JBaseDirection(String code, BaseDirection type) {
        this.code = code;
        this.type = type;
    }

    public static JBaseDirection codeOf(String code) {
        for (JBaseDirection type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

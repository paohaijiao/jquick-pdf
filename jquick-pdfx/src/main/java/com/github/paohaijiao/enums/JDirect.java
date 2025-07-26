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
public enum JDirect {
    left("left", "left"),
    right("right", "right");

    private String code;
    private String name;

    JDirect(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static JDirect codeOf(String code) {
        for (JDirect j : JDirect.values()) {
            if (j.code.equals(code)) {
                return j;
            }
        }
        return null;
    }
}

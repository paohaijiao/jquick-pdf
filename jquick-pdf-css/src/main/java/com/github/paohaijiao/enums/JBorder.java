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
public enum JBorder {

    solid("solid"),
    dashedBorder("dashed"),
    dottedBorder("dotted"),
    doubleBorder("double"),
    fixedDashedBorder("fixedDashed"),
    grooveBorder("groove"),
    insetBorder("inset"),
    outsetBorder("outset"),
    ridgeBorder("ridge"),
    roundDotsBorder("roundDots"),
    solidBorder("solid");
    //no("no", UnderlineBorder.class);

    private String code;


    private JBorder(String code) {
        this.code = code;
    }

    public static JBorder codeOf(String code) {
        for (JBorder type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

}

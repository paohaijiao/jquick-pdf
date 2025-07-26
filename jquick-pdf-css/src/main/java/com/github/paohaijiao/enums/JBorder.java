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

import com.itextpdf.layout.borders.*;
import lombok.Getter;

@Getter
public enum JBorder {

    solid("solid", SolidBorder.class),
    //dashed("dashed", BeveledBorder.class),
    //border3d("border3d", Border3D.class),
    dashedBorder("dashed",  DashedBorder.class),
    dottedBorder("dotted", DottedBorder.class),
    doubleBorder("double", DoubleBorder.class),
    fixedDashedBorder("fixedDashed", FixedDashedBorder.class),
    grooveBorder("groove", GrooveBorder.class),
    insetBorder("inset", InsetBorder.class),
    outsetBorder("outset", OutsetBorder.class),
    ridgeBorder("ridge", RidgeBorder.class),
    roundDotsBorder("roundDots", RoundDotsBorder.class),
    solidBorder("solid", SolidBorder.class);
    //no("no", UnderlineBorder.class);

    private String code;

    private Class clazz;

    private JBorder(String code, Class clazz) {
        this.code = code;
        this.clazz = clazz;
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

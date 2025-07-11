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
package com.github.paohaijiao.unit;

import com.itextpdf.layout.properties.UnitValue;

/**
 * packageName com.github.paohaijiao.height
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/11
 */
public class JUnitConverter {

    private static final float PT_PER_INCH = 72f;    // 1 inch = 72 points

    private static final float PX_PER_INCH = 96f;    // 96 pixels per inch

    private static final float MM_PER_INCH = 25.4f;  // 1 inch = 25.4 mm

    private static final float CM_PER_INCH = 2.54f;   // 1 inch = 2.54 cm


    public static UnitValue create(float value, String unit) {
        if (unit == null || unit.isEmpty()) {
            throw new IllegalArgumentException("单位不能为空");
        }
        switch (unit.toLowerCase()) {
            case "px":
                return UnitValue.createPointValue(pxToPt(value));
            case "pt":
                return UnitValue.createPointValue(value);
            case "mm":
                return UnitValue.createPointValue(mmToPt(value));
            case "cm":
                return UnitValue.createPointValue(cmToPt(value));
            case "in":
                return UnitValue.createPointValue(inchToPt(value));
            case "%":
                return UnitValue.createPercentValue(value);
            default:
                throw new IllegalArgumentException("不支持的单位: " + unit);
        }
    }
    public static float pxToPt(float px) {
        return px * (PT_PER_INCH / PX_PER_INCH); // px * (72/96) = px * 0.75
    }
    public static float mmToPt(float mm) {
        return inchToPt(mm / MM_PER_INCH); // 先转英寸再转点
    }
    public static float cmToPt(float cm) {
        return inchToPt(cm / CM_PER_INCH); // 先转英寸再转点
    }
    public static float inchToPt(float inch) {
        return inch * PT_PER_INCH;
    }
    public static float ptToPx(float pt) {
        return pt * (PX_PER_INCH / PT_PER_INCH); // pt * (96/72) = pt * 1.333...
    }
    public static float ptToMm(float pt) {
        return ptToInch(pt) * MM_PER_INCH;
    }
    public static float ptToCm(float pt) {
        return ptToInch(pt) * CM_PER_INCH;
    }
    public static float ptToInch(float pt) {
        return pt / PT_PER_INCH;
    }
    public static UnitValue px(float value) {
        return create(value, "px");
    }

    public static UnitValue pt(float value) {
        return create(value, "pt");
    }

    public static UnitValue mm(float value) {
        return create(value, "mm");
    }

    public static UnitValue cm(float value) {
        return create(value, "cm");
    }

    public static UnitValue in(float value) {
        return create(value, "in");
    }

    public static UnitValue percent(float value) {
        return create(value, "%");
    }

}

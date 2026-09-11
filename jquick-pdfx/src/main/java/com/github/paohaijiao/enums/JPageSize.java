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

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public enum JPageSize {
    A0("A0", 2383.94f, 3370.39f),
    A1("A1", 1683.78f, 2383.94f),
    A2("A2", 1190.55f, 1683.78f),
    A3("A3", 841.89f, 1190.55f),
    A4("A4", 595.28f, 841.89f),
    A5("A5", 419.53f, 595.28f),
    A6("A6", 297.64f, 419.53f),
    A7("A7", 209.76f, 297.64f),
    A8("A8", 147.40f, 209.76f),
    A9("A9", 104.88f, 147.40f),
    A10("A10", 73.70f, 104.88f),
    B0("B0", 2834.65f, 4008.19f),
    B1("B1", 2004.09f, 2834.65f),
    B2("B2", 1417.32f, 2004.09f),
    B3("B3", 1000.63f, 1417.32f),
    B4("B4", 708.66f, 1000.63f),
    B5("B5", 498.90f, 708.66f),
    B6("B6", 354.33f, 498.90f),
    B7("B7", 249.45f, 354.33f),
    B8("B8", 175.75f, 249.45f),
    B9("B9", 124.72f, 175.75f),
    B10("B10", 87.87f, 124.72f),
    DEFAULT("DEFAULT", 595.28f, 841.89f),
    EXECUTIVE("EXECUTIVE", 522f, 756f),
    LEDGER("LEDGER", 1224f, 792f),
    LEGAL("LEGAL", 612f, 1008f),
    LETTER("LETTER", 612f, 792f),
    TABLOID("TABLOID", 792f, 1224f);

    private final String code;
    private final PDRectangle pageSize;

    JPageSize(String code, float width, float height) {
        this.code = code;
        this.pageSize = new PDRectangle(width, height);
    }

    public static JPageSize codeOf(String code) {
        for (JPageSize size : values()) {
            if (size.code.equalsIgnoreCase(code)) {
                return size;
            }
        }
        throw new IllegalArgumentException("unknown pageType: " + code);
    }

    public String getCode() {
        return code;
    }

    public PDRectangle getPageSize() {
        return pageSize;
    }
}

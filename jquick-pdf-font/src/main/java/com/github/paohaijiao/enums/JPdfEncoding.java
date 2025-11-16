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

import com.itextpdf.io.font.PdfEncodings;
import lombok.Getter;

@Getter
public enum JPdfEncoding {

    IDENTITY_H("IDENTITY_H", PdfEncodings.IDENTITY_H),
    IDENTITY_V("IDENTITY_V", PdfEncodings.IDENTITY_V),
    Cp1250("Cp1250", PdfEncodings.CP1250),
    Cp1252("Cp1252", PdfEncodings.CP1252),
    Cp1253("Cp1253", PdfEncodings.CP1253),
    Cp1257("Cp1257", PdfEncodings.CP1257),
    WINANSI("WINANSI", PdfEncodings.WINANSI),
    MacRoman("MacRoman", PdfEncodings.MACROMAN),
    Symbol("Symbol", PdfEncodings.SYMBOL),
    ZapfDingbats("ZapfDingbats", PdfEncodings.ZAPFDINGBATS),
    UnicodeBig("UnicodeBig", PdfEncodings.UNICODE_BIG),
    UnicodeBigUnmarked("UnicodeBigUnmarked", PdfEncodings.UNICODE_BIG_UNMARKED),
    PDF("PDF", PdfEncodings.PDF_DOC_ENCODING),
    UTF8("UTF-8", PdfEncodings.UTF8);
    private String code;


    private String encoding;

    private JPdfEncoding(String code, String encoding) {
        this.encoding = encoding;
        this.code = code;
    }

    public JPdfEncoding codeOf(String code) {
        for (JPdfEncoding encoding : JPdfEncoding.values()) {
            if (encoding.getCode().equals(code)) {
                return encoding;
            }
        }
        return null;
    }
}

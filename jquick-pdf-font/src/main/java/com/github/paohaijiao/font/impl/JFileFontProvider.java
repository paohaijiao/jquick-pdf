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
package com.github.paohaijiao.font.impl;

import com.github.paohaijiao.font.JFontProvider;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

/**
 * packageName com.github.paohaijiao.font.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JFileFontLoader
 * @date 2025/6/22
 * @description
 */
public class JFileFontProvider implements JFontProvider {
    private final String fontPath;
    private final String encoding;

    public JFileFontProvider(String fontPath, String encoding) {
        this.fontPath = fontPath;
        this.encoding = encoding;
    }

    @Override
    public PdfFont fontProvider() {
        try {
            return PdfFontFactory.createFont(FontProgramFactory.createFont(fontPath), encoding);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

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
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * packageName com.github.paohaijiao.font.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JResourceFontLoader
 * @date 2025/6/22
 * @description
 */
public class JResourceFontProvider implements JFontProvider {
    private final String resourcePath;
    private final String encoding;

    public JResourceFontProvider(String resourcePath, String encoding) {
        this.resourcePath = resourcePath;
        this.encoding = encoding;
    }

    @Override
    public PdfFont fontProvider()  {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Font resource not found: " + resourcePath);
            }
            byte[] bytes = IOUtils.toByteArray(is);
            FontProgram fontProgram = FontProgramFactory.createFont(bytes);
            return PdfFontFactory.createFont(fontProgram, encoding,PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

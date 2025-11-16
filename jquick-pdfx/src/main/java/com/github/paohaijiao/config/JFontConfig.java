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
package com.github.paohaijiao.config;

import com.github.paohaijiao.factory.JFontProviderFactory;
import com.itextpdf.kernel.font.PdfFont;
import lombok.Data;

/**
 * packageName com.github.paohaijiao.config
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
@Data
public class JFontConfig {

    private PdfFont defaultFont = JFontProviderFactory.defualtFont();

    private PdfFont titleFont = JFontProviderFactory.defualtFont();

    private PdfFont bodyFont = JFontProviderFactory.defualtFont();

    private PdfFont headerFooterFont = JFontProviderFactory.defualtFont();

    private PdfFont codeFont = JFontProviderFactory.defualtFont();

    private String fontDirectory = "fonts";

    private String defaultFontName = "SimSun";

    private String defaultEncoding = "UniGB-UCS2-H";
}

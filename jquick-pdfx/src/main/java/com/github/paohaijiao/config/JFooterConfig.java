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

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * packageName com.github.paohaijiao.config
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class JFooterConfig {

    private boolean enabled = true;

    private String text = "第 {page} 页";

    private PdfFont font;

    private float fontSize = 10;

    private Color fontColor;

    private float height = 30;

    private Color backgroundColor;

    private TextAlignment alignment = TextAlignment.CENTER;

    private boolean showPageNumber = true;

    private String pageNumberFormat = "第 %d 页";
}

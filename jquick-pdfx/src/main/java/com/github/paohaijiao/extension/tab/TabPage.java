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
package com.github.paohaijiao.extension.tab;

import com.github.paohaijiao.factory.JFontProviderFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.font.FontProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.github.paohaijiao.extension.tab
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class TabPage {
    private String title;
    private List<IBlockElement> contents;
    private boolean active;
    private PdfFont font;
    public TabPage(String title) {
        this.title = title;
        this.contents = new ArrayList<>();
        this.active = false;
        this.font= JFontProviderFactory.getFont(JFontProviderFactory.DEFAULT_FONT);
    }
    public TabPage(String title,PdfFont font) {
        this.title = title;
        this.contents = new ArrayList<>();
        this.active = false;
        this.font = font;
    }

    public void addContent(IBlockElement element) {
        FontProvider fontProvider = new FontProvider();
        String fontPath = "fonts/simhei.ttf";
        fontProvider.addFont(fontPath, PdfEncodings.IDENTITY_H);
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
            contents.add(element);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getTitle() {
        return title;
    }

    public List<IBlockElement> getContents() {
        return contents;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

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

import org.apache.pdfbox.pdmodel.font.PDFont;

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

    private List<Object> contents;

    private boolean active;

    private PDFont font;

    public TabPage(String title) {
        this(title, null);
    }

    public TabPage(String title, PDFont font) {
        this.title = title;
        this.contents = new ArrayList<>();
        this.active = false;
        this.font = font;
    }

    public void addContent(Object element) {
        if (element != null) {
            contents.add(element);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getContents() {
        return contents;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PDFont getFont() {
        return font;
    }

    public void setFont(PDFont font) {
        this.font = font;
    }
}

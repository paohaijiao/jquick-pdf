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
package com.github.paohaijiao.toc;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;

import java.util.ArrayList;
import java.util.List;

public class JTocGenerator implements IEventHandler {
    private final PdfDocument pdfDoc;
    private final List<Heading> headings;
    private int currentPage = 1;
    private List<PdfOutline> lastOutlines = new ArrayList<>();

    public JTocGenerator(PdfDocument pdfDoc, List<Heading> headings) {
        this.pdfDoc = pdfDoc;
        this.headings = headings;
        // 初始化各级别最后大纲的存储
        for (int i = 0; i < 6; i++) {
            lastOutlines.add(null);
        }
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        headings.stream()
                .filter(h -> currentPage == pageNumber)
                .forEach(h -> addBookmark(h, page));

        currentPage++;
    }

    private void addBookmark(Heading heading, PdfPage page) {
        PdfOutline root = pdfDoc.getOutlines(false);

        // 确定父级大纲
        PdfOutline parent = (heading.level == 1) ? root :
                lastOutlines.get(heading.level - 2);

        if (parent == null) {
            parent = root; // 如果找不到合适的父级，则挂到根上
        }

        // 创建新大纲
        PdfOutline outline = parent.addOutline(heading.text);
        outline.addDestination(PdfExplicitDestination.createFit(page));

        // 更新最后大纲记录
        lastOutlines.set(heading.level - 1, outline);

        // 清除更低级别的记录（表示这个级别后面的内容需要重新开始）
        for (int i = heading.level; i < 6; i++) {
            lastOutlines.set(i, null);
        }
    }

    static class Heading {
        String text;
        int level;

        public Heading(String text, int level) {
            this.text = text;
            this.level = level;
        }
    }

}

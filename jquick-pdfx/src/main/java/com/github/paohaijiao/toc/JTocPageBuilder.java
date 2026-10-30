/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.toc;

import com.github.paohaijiao.config.JCatalogConfig;
import com.github.paohaijiao.font.JFontSpec;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 目录页构建器：根据采集到的标题条目，在文档最前生成目录页。
 *
 * <p>一次性产出 TOC 三件套：</p>
 * <ol>
 *   <li><b>页面目录</b> —— 标题 + 点线 + 页码，条目区域带内部跳转链接（GoTo Destination）；</li>
 *   <li><b>内部跳转链接</b> —— 点击条目跳转到对应正文页（PDAnnotationLink + PDPageFitDestination）；</li>
 *   <li><b>书签树</b> —— 按 h1/h2/h3 层级重建 PDDocumentOutline。</li>
 * </ol>
 *
 * <p>可选页码盖印：目录页罗马数字（I、II…），正文页阿拉伯数字（1、2…），两套页码体系分离。</p>
 */
public final class JTocPageBuilder {

    private static final float MARGIN = 70f;
    private static final float TITLE_FONT_SIZE = 22f;
    private static final float ENTRY_FONT_SIZE = 12f;
    private static final float LINE_HEIGHT = 24f;
    private static final float PAGE_NUMBER_FONT_SIZE = 10f;

    private JTocPageBuilder() {
    }

    /**
     * 在文档最前插入目录页并完成链接、书签、页码盖印。
     *
     * @param document 渲染完成的文档（正文页在前）
     * @param entries  采集到的目录条目
     * @param config   目录配置
     * @return 插入的目录页数量
     * @throws IOException 构建失败
     */
    public static int build(PDDocument document, List<JTocEntry> entries, JCatalogConfig config) throws IOException {
        if (document == null || entries == null || entries.isEmpty()) {
            return 0;
        }
        JCatalogConfig cfg = config == null ? new JCatalogConfig() : config;
        int perPage = Math.max(1, cfg.getEntriesPerPage());
        int pageCount = (entries.size() + perPage - 1) / perPage;
        if (pageCount == 0) {
            pageCount = 1;
        }

        // 1. 先按登记页码捕获正文页对象（插入目录页后对象不变，直接引用即可）。
        List<PDPage> bodyPages = new ArrayList<>(entries.size());
        for (JTocEntry entry : entries) {
            int index = entry.getPageNumber() - 1;
            index = Math.max(0, Math.min(index, document.getNumberOfPages() - 1));
            bodyPages.add(document.getPage(index));
        }

        // 2. 创建目录页并插入到文档最前。
        PDRectangle template = document.getPage(0).getMediaBox();
        List<PDPage> tocPages = new ArrayList<>(pageCount);
        for (int i = 0; i < pageCount; i++) {
            PDPage tocPage = new PDPage(template);
            tocPages.add(tocPage);
            if (document.getNumberOfPages() > 0) {
                document.getPages().insertBefore(tocPage, document.getPage(0));
            } else {
                document.addPage(tocPage);
            }
        }

        PDFont font = new JFontSpec().resolve(document);
        float pageWidth = template.getWidth();

        // 3. 逐页绘制目录内容与跳转链接。
        int entryIndex = 0;
        float topY = template.getHeight() - MARGIN - TITLE_FONT_SIZE - 30f;
        for (int p = 0; p < pageCount; p++) {
            PDPage tocPage = tocPages.get(p);
            try (PDPageContentStream stream = new PDPageContentStream(document, tocPage)) {
                stream.setFont(font, TITLE_FONT_SIZE);
                stream.beginText();
                String title = cfg.getTitle() == null ? "目  录" : cfg.getTitle();
                float titleWidth = widthOf(font, title, TITLE_FONT_SIZE);
                stream.newLineAtOffset((pageWidth - titleWidth) / 2f,
                        template.getHeight() - MARGIN - TITLE_FONT_SIZE);
                stream.showText(title);
                stream.endText();

                float y = topY;
                for (int n = 0; n < perPage && entryIndex < entries.size(); n++, entryIndex++) {
                    JTocEntry entry = entries.get(entryIndex);
                    y -= LINE_HEIGHT;
                    float indent = MARGIN + (entry.getLevel() - 1) * 20f;
                    String entryTitle = entry.getTitle();
                    float numberWidth = 0f;
                    String numberText = "";
                    float numberX = pageWidth - MARGIN;
                    if (cfg.isShowPageNumbers()) {
                        numberText = String.valueOf(entry.getPageNumber());
                        numberWidth = widthOf(font, numberText, ENTRY_FONT_SIZE);
                        numberX = pageWidth - MARGIN - numberWidth;
                    }
                    entryTitle = ellipsize(font, entryTitle, numberX - indent - 24f, ENTRY_FONT_SIZE);

                    stream.setFont(font, ENTRY_FONT_SIZE);
                    stream.beginText();
                    stream.newLineAtOffset(indent, y);
                    stream.showText(entryTitle);
                    stream.endText();

                    // 点线引导：精确填充标题末端到页码之间的空白，不越过页码。
                    float dotWidth = widthOf(font, ".", ENTRY_FONT_SIZE);
                    float dotsStart = indent + widthOf(font, entryTitle, ENTRY_FONT_SIZE) + 6f;
                    float dotsWidth = numberX - dotsStart - 6f;
                    int dots = dotWidth > 0f ? Math.max(0, (int) (dotsWidth / dotWidth)) : 0;
                    if (dots > 0) {
                        stream.beginText();
                        stream.newLineAtOffset(dotsStart, y);
                        StringBuilder dotLine = new StringBuilder();
                        for (int d = 0; d < dots; d++) {
                            dotLine.append('.');
                        }
                        stream.showText(dotLine.toString());
                        stream.endText();
                    }

                    if (cfg.isShowPageNumbers()) {
                        stream.beginText();
                        stream.newLineAtOffset(numberX, y);
                        stream.showText(numberText);
                        stream.endText();
                    }

                    // 跳转链接：条目整行可点击
                    addLinkAnnotation(tocPage, font, entryTitle, numberText, indent, y,
                            pageWidth - indent - MARGIN, LINE_HEIGHT,
                            bodyPages.get(entryIndex), ENTRY_FONT_SIZE);
                }
            }
        }

        // 4. 重建书签树（层级与 h1/h2/h3 一致）。
        buildOutline(document, entries, bodyPages);

        // 5. 页码盖印：目录页罗马数字、正文页阿拉伯数字。
        if (cfg.isStampPageNumbers()) {
            stampPageNumbers(document, font, pageCount);
        }
        return pageCount;
    }

    private static void addLinkAnnotation(PDPage page, PDFont font, String title, String numberText,
                                          float x, float y, float width, float height,
                                          PDPage target, float fontSize) throws IOException {
        PDActionGoTo action = new PDActionGoTo();
        PDPageFitDestination destination = new PDPageFitDestination();
        destination.setPage(target);
        action.setDestination(destination);
        PDAnnotationLink link = new PDAnnotationLink();
        link.setAction(action);
        // 去除链接边框：默认边框会在阅读器/渲染中显示成整行方框。
        PDBorderStyleDictionary border = new PDBorderStyleDictionary();
        border.setWidth(0f);
        link.setBorderStyle(border);
        // PDF 注解坐标系以页面左下角为原点：把基线 y 换算成注解矩形。
        PDRectangle rect = new PDRectangle(x, y - 5f, width, height);
        link.setRectangle(rect);
        page.getAnnotations().add(link);
    }

    /**
     * 重建书签树：h1 为根节点，h2/h3 逐级下挂，目标页为对应正文页。
     */
    private static void buildOutline(PDDocument document, List<JTocEntry> entries, List<PDPage> bodyPages)
            throws IOException {
        PDDocumentOutline outline = new PDDocumentOutline();
        document.getDocumentCatalog().setDocumentOutline(outline);
        Stack<PDOutlineItem> stack = new Stack<>();
        for (int i = 0; i < entries.size(); i++) {
            JTocEntry entry = entries.get(i);
            PDOutlineItem item = new PDOutlineItem();
            item.setTitle(entry.getTitle());
            PDPageFitDestination destination = new PDPageFitDestination();
            destination.setPage(bodyPages.get(i));
            item.setDestination(destination);
            while (stack.size() >= entry.getLevel()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                outline.addLast(item);
            } else {
                stack.peek().addLast(item);
            }
            stack.push(item);
        }
        outline.openNode();
    }

    private static void stampPageNumbers(PDDocument document, PDFont font, int tocPageCount) throws IOException {
        int totalPages = document.getNumberOfPages();
        for (int i = 0; i < totalPages; i++) {
            PDPage page = document.getPage(i);
            PDRectangle box = page.getMediaBox();
            boolean isTocPage = i < tocPageCount;
            String label = isTocPage ? toRoman(i + 1) : String.valueOf(i - tocPageCount + 1);
            float textWidth = widthOf(font, label, PAGE_NUMBER_FONT_SIZE);
            try (PDPageContentStream stream = new PDPageContentStream(document, page,
                    PDPageContentStream.AppendMode.APPEND, true, true)) {
                stream.setFont(font, PAGE_NUMBER_FONT_SIZE);
                stream.beginText();
                stream.newLineAtOffset((box.getWidth() - textWidth) / 2f, 24f);
                stream.showText(label);
                stream.endText();
            }
        }
    }

    private static float widthOf(PDFont font, String text, float fontSize) throws IOException {
        if (text == null || text.isEmpty()) {
            return 0f;
        }
        return font.getStringWidth(text) / 1000f * fontSize;
    }

    private static String ellipsize(PDFont font, String text, float maxWidth, float fontSize) throws IOException {
        if (widthOf(font, text, fontSize) <= maxWidth) {
            return text;
        }
        String suffix = "...";
        float suffixWidth = widthOf(font, suffix, fontSize);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String candidate = builder.toString() + text.charAt(i) + suffix;
            if (widthOf(font, candidate, fontSize) > maxWidth) {
                break;
            }
            builder.append(text.charAt(i));
        }
        return builder.append(suffix).toString();
    }

    private static String toRoman(int number) {
        if (number <= 0) {
            return String.valueOf(number);
        }
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        int remaining = number;
        for (int i = 0; i < values.length; i++) {
            while (remaining >= values[i]) {
                roman.append(symbols[i]);
                remaining -= values[i];
            }
        }
        return roman.toString();
    }
}

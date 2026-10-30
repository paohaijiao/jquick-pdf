/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.merge;

import com.github.paohaijiao.config.JCatalogConfig;
import com.github.paohaijiao.toc.JTocEntry;
import com.github.paohaijiao.toc.JTocPageBuilder;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 多 PDF 合并工具：合并多份文档，并在合并结果最前重建<b>全局目录</b>。
 *
 * <p>合并时保留各源文档的书签（PDFBox 自动做页码偏移重定向）；
 * 随后把各源文档书签按合并后的页码偏移收集为目录条目，由 {@link JTocPageBuilder}
 * 在合并结果最前生成带跳转链接的全局目录页并重建根书签树——即"多 PDF 合并后重建全局 TOC"。</p>
 *
 * <p>典型场景：多卷公文、V1/V2 多版本归档、按章节分包生成后合并。</p>
 */
public final class JPdfMerger {

    private JPdfMerger() {
    }

    /**
     * 合并多份 PDF（保留源文档书签，页码自动偏移）。
     *
     * @param pdfBytes 多份 PDF 的字节数组，按合并顺序排列
     * @return 合并后的文档（调用方负责 save/close）
     * @throws IOException 合并失败
     */
    public static PDDocument merge(List<byte[]> pdfBytes) throws IOException {
        if (pdfBytes == null || pdfBytes.isEmpty()) {
            throw new IllegalArgumentException("pdf list must not be empty");
        }
        PDDocument dest = new PDDocument();
        PDFMergerUtility merger = new PDFMergerUtility();
        for (byte[] pdf : pdfBytes) {
            PDDocument source = Loader.loadPDF(pdf);
            try {
                merger.appendDocument(dest, source);
            } finally {
                source.close();
            }
        }
        return dest;
    }

    /**
     * 合并多份 PDF 并在最前重建全局目录页（跳转链接 + 根书签树）。
     *
     * @param pdfBytes 多份 PDF 的字节数组，按合并顺序排列
     * @param tocTitle 全局目录标题（null 时默认"全局目录"）
     * @return 合并并重建全局目录后的 PDF 字节
     * @throws IOException 处理失败
     */
    public static byte[] mergeWithGlobalToc(List<byte[]> pdfBytes, String tocTitle) throws IOException {
        return mergeWithGlobalToc(pdfBytes, tocTitle, null);
    }

    /**
     * 合并多份 PDF 并在最前重建全局目录页，可为每份文档附加标签（如 "V1"、"第 1 卷"）。
     *
     * <p>标签会作为一级目录条目的前缀，例如 {@code V2 · 数据安全管理制度}，
     * 便于多版本/多卷归档卷的全局目录区分来源。</p>
     *
     * @param pdfBytes 多份 PDF 的字节数组，按合并顺序排列
     * @param tocTitle 全局目录标题（null 时默认"全局目录"）
     * @param labels   与 pdfBytes 等长的文档标签列表（可为 null，表示不加前缀）
     * @return 合并并重建全局目录后的 PDF 字节
     * @throws IOException 处理失败
     */
    public static byte[] mergeWithGlobalToc(List<byte[]> pdfBytes, String tocTitle, List<String> labels)
            throws IOException {
        if (pdfBytes == null || pdfBytes.isEmpty()) {
            throw new IllegalArgumentException("pdf list must not be empty");
        }
        // 1. 合并前逐份收集书签并按页码偏移换算为合并后的页序。
        List<JTocEntry> entries = new ArrayList<>();
        int pageOffset = 0;
        for (int i = 0; i < pdfBytes.size(); i++) {
            String label = labels != null && i < labels.size() ? labels.get(i) : null;
            try (PDDocument source = Loader.loadPDF(pdfBytes.get(i))) {
                collectOutlineEntries(source, pageOffset, label, entries);
                pageOffset += source.getNumberOfPages();
            }
        }

        // 2. 合并文档。
        try (PDDocument merged = merge(pdfBytes)) {
            // 3. 前置生成全局目录页（链接 + 书签树重建）。
            if (!entries.isEmpty()) {
                JCatalogConfig config = new JCatalogConfig();
                config.setTitle(tocTitle == null || tocTitle.isEmpty() ? "全局目录" : tocTitle);
                config.setStampPageNumbers(false);
                JTocPageBuilder.build(merged, entries, config);
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            merged.save(buffer);
            return buffer.toByteArray();
        }
    }

    /**
     * 收集单份源文档的书签树，页码加上合并偏移量；可给一级条目加标签前缀。
     */
    private static void collectOutlineEntries(PDDocument source, int pageOffset, String label,
                                              List<JTocEntry> entries) {
        PDDocumentOutline outline = source.getDocumentCatalog().getDocumentOutline();
        if (outline == null) {
            return;
        }
        collectChildren(source, outline, 1, pageOffset, label, entries);
    }

    private static void collectChildren(PDDocument source, PDOutlineNode node, int level, int pageOffset,
                                        String label, List<JTocEntry> entries) {
        for (PDOutlineItem item = node.getFirstChild(); item != null; item = item.getNextSibling()) {
            collectItem(source, item, level, pageOffset, label, entries);
        }
    }

    private static void collectItem(PDDocument source, PDOutlineItem item, int level, int pageOffset,
                                    String label, List<JTocEntry> entries) {
        int pageIndex = resolvePageIndex(source, item);
        if (pageIndex > 0) {
            String title = item.getTitle() == null ? "(untitled)" : item.getTitle();
            if (label != null && !label.isEmpty() && level == 1) {
                title = label + " · " + title;
            }
            entries.add(new JTocEntry(title, pageIndex + pageOffset, Math.min(3, level)));
        }
        for (PDOutlineItem child = item.getFirstChild(); child != null; child = child.getNextSibling()) {
            collectItem(source, child, level + 1, pageOffset, label, entries);
        }
    }

    private static int resolvePageIndex(PDDocument document, PDOutlineItem item) {
        try {
            if (item.getDestination() instanceof PDPageFitDestination) {
                PDPageFitDestination destination = (PDPageFitDestination) item.getDestination();
                if (destination.getPage() != null) {
                    return document.getPages().indexOf(destination.getPage()) + 1;
                }
            }
        } catch (IOException ignored) {
            // 目标页无法解析时跳过该条目。
        }
        return -1;
    }
}

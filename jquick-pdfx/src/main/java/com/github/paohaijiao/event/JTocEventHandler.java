/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.event;

import com.github.paohaijiao.config.JCatalogConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitDestination;
import java.io.IOException;

/**
 * PDFBox 目录处理器。页面创建后显式调用 render 以登记书签。
 */
public class JTocEventHandler {

    private final JCatalogConfig config;
    private final PDDocumentOutline rootOutline;

    public JTocEventHandler(PDDocument document, JCatalogConfig config) {
        this.config = config;
        PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
        if (outline == null) {
            outline = new PDDocumentOutline();
            document.getDocumentCatalog().setDocumentOutline(outline);
        }
        this.rootOutline = outline;
    }

    public void render(PDPage page) throws IOException {
        if (config == null || !config.isEnabled()) {
            return;
        }
        if (Integer.valueOf(2).equals(config.getStartPage())) {
            PDPageFitDestination destination = new PDPageFitDestination();
            destination.setPage(page);
            PDOutlineItem outline = new PDOutlineItem();
            outline.setTitle("重要章节");
            outline.setDestination(destination);
            rootOutline.addLast(outline);
            rootOutline.openNode();
        }
        config.setStartPage(config.getStartPage() + 1);
    }
}

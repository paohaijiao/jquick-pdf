/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.extension.tree;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 * 树形组件的 PDFBox 独立示例。
 */
public class JTreeToPdf {

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode("Root");
        TreeNode documents = new TreeNode("Documents");
        TreeNode images = new TreeNode("Images");
        TreeNode system = new TreeNode("System");
        system.setSelected(true);
        root.addChild(documents);
        root.addChild(images);
        root.addChild(system);

        try (PDDocument document = new PDDocument()) {
            JQuickRenderContext context = JQuickRenderContext.builder()
                    .document(document)
                    .margins(new float[]{36f, 36f, 36f, 36f})
                    .font(new PDType1Font(Standard14Fonts.FontName.HELVETICA))
                    .fontSize(11f)
                    .build();
            try (PdfBoxLayoutEngine layout = new PdfBoxLayoutEngine(document,
                    PDRectangle.A4, context)) {
                new TreeElement(root).draw(layout.getStream(), context);
            }
            document.save("d://test//corrected_checkbox_tree.pdf");
        }
    }
}

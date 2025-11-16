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
package com.github.paohaijiao.extension.tree;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * packageName com.github.paohaijiao.extension.tree
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class JTreeToPdf {

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode("Root");
        TreeNode child1 = new TreeNode("Documents");
        TreeNode child2 = new TreeNode("Images");
        TreeNode child3 = new TreeNode("System");
        child3.setSelected(true);
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        PdfDocument pdfDoc = new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter("d://test//corrected_checkbox_tree.pdf"));
        Document document = new Document(pdfDoc);
        TreeElement treeElement = new TreeElement(root);
        treeElement.setWidth(400);
        document.add(new Paragraph("Checkbox Tree").setBold().setFontSize(16));
        document.add(treeElement);
        document.close();
    }
}

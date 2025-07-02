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

import com.itextpdf.kernel.pdf.CompressionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.Data;

/**
 * packageName com.github.paohaijiao.model.meta
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfMetaModel
 * @date 2025/6/24
 * @description
 */
@Data
public class JPdfConfig {


    private String title;

    private String author;

    private String subject;

    private String keywords;


    private int compressionLevel = CompressionConstants.DEFAULT_COMPRESSION;

    private String tempDirectory;

    private String defaultFont = "Helvetica";
    private float defaultFontSize = 12f;

    private String userPassword;

    private String ownerPassword;

    private boolean autoCloseStream = true;


    public JPdfConfig() {

    }

    public void applyTo(PdfWriter writer) {
        if (writer == null) return;
        writer.setCompressionLevel(compressionLevel);
    }

    public void applyMetadataTo(PdfDocument pdfDoc) {
        if (pdfDoc == null) return;
        PdfDocumentInfo info = pdfDoc.getDocumentInfo();
        if (title != null) info.setTitle(title);
        if (author != null) info.setAuthor(author);
        if (subject != null) info.setSubject(subject);
        if (keywords != null) info.setKeywords(keywords);
    }

}

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
package com.github.paohaijiao.security;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;

/**
 * packageName com.github.paohaijiao.security
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfxEncryption
 * @date 2025/6/22
 * @description
 */
public class JPdfxEncryption {
    public static final String DEST = "./encrypted_output.pdf";
    public static final String USER_PASSWORD = "user123";
    public static final String OWNER_PASSWORD = "owner456";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createEncryptedPdf(DEST, USER_PASSWORD, OWNER_PASSWORD);
        testReadEncryptedPdf(DEST, USER_PASSWORD);
    }

    public static void createEncryptedPdf(String dest, String userPassword, String ownerPassword) throws IOException {
        WriterProperties properties = new WriterProperties()
                .setStandardEncryption(
                        userPassword.getBytes(),
                        ownerPassword.getBytes(),
                        EncryptionConstants.ALLOW_PRINTING,
                        EncryptionConstants.ENCRYPTION_AES_256
                );

        PdfWriter writer = new PdfWriter(dest, properties);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        document.add(new Paragraph("This is a confidential document"));
        document.add(new Paragraph("Only authorized users can access this content"));
        document.add(new Paragraph("User password: " + userPassword));
        document.add(new Paragraph("Owner password: " + ownerPassword));
        document.close();
        System.out.println("Encrypted PDF created at: " + dest);
    }

    public static void testReadEncryptedPdf(String src, String password) {
        try {
            PdfReader reader = new PdfReader(src, new com.itextpdf.kernel.pdf.ReaderProperties().setPassword(password.getBytes()));
            PdfDocument pdfDoc = new PdfDocument(reader);
            System.out.println("Successfully opened encrypted PDF with password: " + password);
            System.out.println("Number of pages: " + pdfDoc.getNumberOfPages());
            pdfDoc.close();
        } catch (IOException e) {
            System.err.println("Failed to read encrypted PDF: " + e.getMessage());
        }
    }
}

package com.github.paohaijiao.font.impl;

import com.github.paohaijiao.font.JFontProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JResourceFontProvider implements JFontProvider {

    private final String resourcePath;

    public JResourceFontProvider(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public PDFont resolve(PDDocument document) throws IOException {
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new IOException("字体资源不存在: " + resourcePath);
            }
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            return PDType0Font.load(document,
                    new ByteArrayInputStream(output.toByteArray()));
        }
    }
}

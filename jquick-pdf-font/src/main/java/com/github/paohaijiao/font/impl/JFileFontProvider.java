package com.github.paohaijiao.font.impl;

import com.github.paohaijiao.font.JFontProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

public class JFileFontProvider implements JFontProvider {

    private final File fontFile;

    public JFileFontProvider(String fontPath) {
        this.fontFile = new File(fontPath);
    }

    @Override
    public PDFont resolve(PDDocument document) throws IOException {
        if (!fontFile.isFile()) {
            throw new IOException("字体文件不存在: " + fontFile);
        }
        return PDType0Font.load(document, fontFile);
    }
}

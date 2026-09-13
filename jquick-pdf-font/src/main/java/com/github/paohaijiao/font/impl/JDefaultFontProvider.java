package com.github.paohaijiao.font.impl;

import com.github.paohaijiao.font.JFontProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class JDefaultFontProvider implements JFontProvider {

    private final String fontPath;

    public JDefaultFontProvider(String fontPath) {
        this.fontPath = fontPath;
    }

    @Override
    public PDFont resolve(PDDocument document) throws IOException {
        if (fontPath != null && new File(fontPath).isFile()) {
            return new JFileFontProvider(fontPath).resolve(document);
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }
}

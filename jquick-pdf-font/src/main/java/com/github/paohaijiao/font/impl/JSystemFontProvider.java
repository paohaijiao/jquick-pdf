package com.github.paohaijiao.font.impl;

import com.github.paohaijiao.font.JFontProvider;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class JSystemFontProvider implements JFontProvider {

    private final Standard14Fonts.FontName fontName;

    public JSystemFontProvider(Standard14Fonts.FontName fontName) {
        this.fontName = fontName;
    }

    @Override
    public PDFont resolve(PDDocument document) {
        return new PDType1Font(fontName);
    }
}

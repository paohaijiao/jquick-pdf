package com.github.paohaijiao.font;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public interface JFontProvider {

    PDFont resolve(PDDocument document) throws IOException;
}

package com.github.paohaijiao.config;

import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

/**
 * Document-independent font descriptor. Fonts are resolved for the active
 * PDFBox document during rendering instead of being shared across documents.
 */
@Data
public class JFontSpec {

    private String name = "SimSun";

    private String file = "fonts/simhei.ttf";

    public JFontSpec() {
    }

    public JFontSpec(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public PDFont resolve(PDDocument document) throws IOException {
        if (file != null && !file.trim().isEmpty()) {
            File fontFile = new File(file);
            if (fontFile.isFile()) {
                return PDType0Font.load(document, fontFile);
            }
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }
}

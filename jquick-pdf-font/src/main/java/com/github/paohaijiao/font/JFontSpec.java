package com.github.paohaijiao.font;

import com.github.paohaijiao.font.impl.JResourceFontProvider;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

/**
 * 文档无关的字体描述，在当前 PDFBox 文档中按需解析字体实例。
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
            try {
                return new JResourceFontProvider(file).resolve(document);
            } catch (IOException ignored) {
                // 未找到同名资源时回退至标准字体。
            }
        }
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }
}

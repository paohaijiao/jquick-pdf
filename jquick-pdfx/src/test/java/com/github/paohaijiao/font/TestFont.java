package com.github.paohaijiao.font;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFont {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf>"
                + " <body>"
                + " <p style=\"baseDirection:rtl; fontSize:14\">${arabicText}</p>"
                + " </body>"
                + "</pdf>";
        JPdfConfig config = new JPdfConfig();
        JFontSpec titleFont = new JFontSpec();
        titleFont.setName("Vazirmatn");
        titleFont.setFile("C:\\Users\\Gou\\Downloads\\vazirmatn-v33.003\\fonts\\ttf\\Vazirmatn-Regular.ttf");
        config.getFontConfig().setDefaultFont(titleFont);
        byte[] pdf = new JQuickPdfFactory(config).bind("arabicText", "مرحبا بالعالم").executeContent(template);
        Files.write(Paths.get("d://test//arabicText.pdf"), pdf);
    }
}
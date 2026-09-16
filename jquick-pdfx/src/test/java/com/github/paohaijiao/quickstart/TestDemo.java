package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDemo {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf>"
                + " <body>"
                + " <p style=\"baseDirection:rtl; fontSize:14\">${arabicText}</p>"
                + " </body>"
                + "</pdf>";
        byte[] pdf = new JQuickPdfFactory().bind("arabicText", "مرحبا بالعالم").executeContent(template);
        Files.write(Paths.get("d://test//arabicText.pdf"), pdf);
    }
}

package com.github.paohaijiao;
import com.github.paohaijiao.font.JFontSpec;
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.Bidi;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    @Test
    public void svg2() throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            JFontSpec spec = new JFontSpec("Vazirmatn", "C:\\Users\\Gou\\Downloads\\vazirmatn-v33.003\\fonts\\ttf\\Vazirmatn-Regular.ttf");
            PDFont font = spec.resolve(doc);
            checkArabicSupport(font);
            String text = "مرحبا بالعالم";
            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(font, 20);
                cs.newLineAtOffset(400, 700); // 右侧起笔（RTL）
                showComplexText(cs, font, text);
                cs.endText();
            }

            File out = new File("d://test//out.pdf");
            out.getParentFile().mkdirs();
            doc.save(out);
            System.out.println("已生成: " + out.getAbsolutePath());
        }
    }

    /**
     * 阿拉伯语整形（连写）+ BiDi 重排。
     * ArabicShaping.LETTERS_SHAPE 会把基础阿拉伯字母替换为 presentation forms（U+FB50–U+FEFF），
     * 这些字形在多数阿拉伯字体里都存在，可直接用 showText 绘制。
     */
    private String shapeArabic(String text) {
        try {
            ArabicShaping shaping = new ArabicShaping(
                    ArabicShaping.LETTERS_SHAPE | ArabicShaping.DIGITS_AN2EN);
            String shaped = shaping.shape(text);

            Bidi bidi = new Bidi(shaped, Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
            bidi.setReorderingMode(Bidi.REORDER_DEFAULT);
            return bidi.writeReordered(Bidi.DO_MIRRORING);
        } catch (Exception e) {
            return text;
        }
    }

    private void showComplexText(PDPageContentStream cs, PDFont font, String text) throws IOException {
        String shaped = shapeArabic(text);
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < shaped.length(); ) {
            int cp = shaped.codePointAt(i);
            String s = new String(Character.toChars(cp));
            try {
                font.encode(s);      // 先校验字形存在，不存在就跳过
                items.add(s);        // 放 String，不是 byte[]
                items.add(0f);       // 与下一字形间距 0
            } catch (IllegalArgumentException | IOException ignored) {
                // 字体缺该字形，跳过
            }
            i += Character.charCount(cp);
        }

        // 去掉末尾多余的 Float
        if (!items.isEmpty() && items.get(items.size() - 1) instanceof Float) {
            items.remove(items.size() - 1);
        }

        if (!items.isEmpty()) {
            cs.showTextWithPositioning(items.toArray());
        }
    }

    /**
     * 检测字体对阿拉伯语的支持情况，方便定位问题。
     */
    private void checkArabicSupport(PDFont font) {
        // 基础阿拉伯字母（独立形式）
        String[] basics = {"م", "ر", "ح", "ب", "ا", "ع", "ل"};
        // presentation forms（连写形式）
        String[] forms = {"\uFEE1", "\uFEAE", "\uFEA3", "\uFE8F"}; // مـ ـمـ ـحـ بـ 等

        System.out.println("=== 基础字母检测 ===");
        for (String s : basics) {
            try {
                font.encode(s);
            } catch (IllegalArgumentException | IOException e) {
                System.out.printf("缺基础字母: U+%04X%n", s.codePointAt(0));
            }
        }

        System.out.println("=== 连写字形检测 ===");
        for (String s : forms) {
            try {
                font.encode(s);
            } catch (IllegalArgumentException | IOException e) {
                System.out.printf("缺连写字形: U+%04X%n", s.codePointAt(0));
            }
        }
    }
}
package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.model.JStyleAttributes;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PdfBoxRenderAdapterTest {

    @Test
    public void shouldResolveShorthandAndExplicitSpacing() {
        JStyleAttributes attributes = new JStyleAttributes();
        attributes.put("commonMargin", "8px");
        attributes.put("margins", "10px 20px 30px 40px");
        attributes.put("marginLeft", "50px");
        attributes.put("commonPadding", "4px");
        attributes.put("paddings", "5px 6px 7px 8px");
        attributes.put("paddingTop", "9px");

        PdfBoxStyleModel style = PdfBoxStyleModel.from(attributes);
        Assert.assertEquals(PdfBoxUnitConverter.toPoint("50px", 0), style.getMarginLeft(), 0.001f);
        Assert.assertEquals(PdfBoxUnitConverter.toPoint("20px", 0), style.getMarginRight(), 0.001f);
        Assert.assertEquals(PdfBoxUnitConverter.toPoint("9px", 0), style.getPaddingTop(), 0.001f);
        Assert.assertEquals(PdfBoxUnitConverter.toPoint("8px", 0), style.getPaddingLeft(), 0.001f);
    }

    @Test
    public void shouldRenderStyledContent() throws Exception {
        JStyleAttributes attributes = new JStyleAttributes();
        attributes.put("width", "360px");
        attributes.put("height", "80px");
        attributes.put("font", "HELVETICA");
        attributes.put("fontColor", "white");
        attributes.put("fontSize", "18");
        attributes.put("textAlignment", "center");
        attributes.put("characterSpacing", "1");
        attributes.put("wordSpacing", "2");
        attributes.put("underline", "true");
        attributes.put("lineThrough", "true");
        attributes.put("backgroundColor", "#336699");
        attributes.put("border", "dashed 2px white");
        attributes.put("borderRadius", "12px");
        attributes.put("opacity", "0.8");
        attributes.put("strokeColor", "white");
        attributes.put("strokeWidth", "1");

        File output = new File("target/pdfbox-style-adapter-test.pdf");
        output.getParentFile().mkdirs();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                PdfBoxStyleModel style = PdfBoxStyleModel.from(attributes);
                PdfBoxRenderAdapter.drawBox(document, stream, style, 72, 700, 270, 60);
                PdfBoxRenderAdapter.drawText(stream, style,
                        new PDType1Font(Standard14Fonts.FontName.HELVETICA),
                        "jquick-pdf PDFBox style", 72, 730, 270);
            }
            document.save(output);
        }
        Assert.assertTrue(output.isFile() && output.length() > 0);
    }
}

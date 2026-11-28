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
    @Test
    public void shouldParseBorderShorthandsShadowAndRadiusAlias() {
        JStyleAttributes attributes = new JStyleAttributes();
        attributes.put("border-width", "1px 2px 3px 4px");
        attributes.put("border-style", "solid dashed");
        attributes.put("border-color", "red blue");
        attributes.put("border-radius", "8px");
        attributes.put("radius", "12px");
        attributes.put("box-shadow", "inset 2px 3px 6px 1px rgba(0,0,0,0.35)");
        PdfBoxStyleModel style = PdfBoxStyleModel.from(attributes);
        Assert.assertEquals("1px 2px 3px 4px", style.getBorderWidth());
        Assert.assertEquals("solid dashed", style.getBorderStyle());
        Assert.assertEquals("red blue", style.getBorderColor());
        Assert.assertEquals("8px", style.getBorderRadius());
        Assert.assertEquals("radius 应解析为 border-radius 的短别名", "12px", style.getRadius());
        Assert.assertEquals("inset 2px 3px 6px 1px rgba(0,0,0,0.35)", style.getBoxShadow());
    }

    @Test
    public void shouldRenderBorderShorthandsAndShadows() throws Exception {
        JStyleAttributes attributes = new JStyleAttributes();
        attributes.put("width", "260px");
        attributes.put("height", "90px");
        attributes.put("backgroundColor", "#f5f7fa");
        attributes.put("borderWidth", "1px 2px 3px 4px");
        attributes.put("borderStyle", "solid dashed");
        attributes.put("borderColor", "#e74c3c #3498db");
        attributes.put("borderRadius", "10px");
        attributes.put("boxShadow", "3px 4px 8px rgba(0,0,0,0.3)");
        File output = new File("target/pdfbox-border-shadow-test.pdf");
        output.getParentFile().mkdirs();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                PdfBoxStyleModel style = PdfBoxStyleModel.from(attributes);
                PdfBoxRenderAdapter.drawBox(document, stream, style, 72, 720, 195, 67);
            }
            document.save(output);
        }
        Assert.assertTrue(output.isFile() && output.length() > 0);
    }

    @Test
    public void shouldRenderInsetShadow() throws Exception {
        JStyleAttributes attributes = new JStyleAttributes();
        attributes.put("width", "220px");
        attributes.put("height", "80px");
        attributes.put("backgroundColor", "#ffffff");
        attributes.put("border", "1px solid #dddddd");
        attributes.put("box-shadow", "inset 0 4px 2px rgba(0,0,0,0.3)");
        File output = new File("target/pdfbox-inset-shadow-test.pdf");
        output.getParentFile().mkdirs();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                PdfBoxStyleModel style = PdfBoxStyleModel.from(attributes);
                PdfBoxRenderAdapter.drawBox(document, stream, style, 72, 700, 165, 60);
            }
            document.save(output);
        }
        Assert.assertTrue(output.isFile() && output.length() > 0);
    }
}

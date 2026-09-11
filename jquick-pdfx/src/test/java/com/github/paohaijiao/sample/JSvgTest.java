package com.github.paohaijiao.sample;

import com.github.paohaijiao.extension.svg.SvgImage;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JSvgTest {

    @Test
    public void file() throws IOException {
        String svgContent = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"240\" height=\"120\">"
                + "<rect width=\"240\" height=\"120\" fill=\"#f4f7fb\"/>"
                + "<rect x=\"20\" y=\"65\" width=\"30\" height=\"35\" fill=\"#4169e1\"/>"
                + "<rect x=\"75\" y=\"40\" width=\"30\" height=\"60\" fill=\"#4169e1\"/>"
                + "<rect x=\"130\" y=\"20\" width=\"30\" height=\"80\" fill=\"#4169e1\"/>"
                + "<rect x=\"185\" y=\"52\" width=\"30\" height=\"48\" fill=\"#4169e1\"/>"
                + "</svg>";
        File output = new File("target/svg_example.pdf");
        File parent = output.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (PDDocument document = new PDDocument()) {
            JQuickRenderContext context = JQuickRenderContext.builder()
                    .document(document)
                    .margins(new float[]{36f, 36f, 36f, 36f})
                    .build();
            try (PdfBoxLayoutEngine layout = new PdfBoxLayoutEngine(document, PDRectangle.A4, context)) {
                new SvgImage(svgContent).draw(layout.getStream(), context);
            }
            document.save(output);
        }
    }
}

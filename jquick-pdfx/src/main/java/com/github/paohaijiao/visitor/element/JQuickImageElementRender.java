package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.factory.JImageFactory;
import com.github.paohaijiao.image.JBaseImageProvider;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxUnitConverter;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

@Data
public class JQuickImageElementRender implements JQuickElementRender {

    private String src;

    private String alt;

    private String value;

    private JStyleAttributes style;

    private float width = 120f;

    private float height = 80f;

    public JQuickImageElementRender(String src, String alt, String value, JStyleAttributes style) {
        this.src = src;
        this.alt = alt;
        this.value = value;
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || src == null || src.isEmpty()) {
            return;
        }
        PDDocument document = context.getDocument();
        if (document == null) {
            return;
        }
        JBaseImageProvider imageProvider = JImageFactory.createProvider(src);
        byte[] bytes = imageProvider.loadImage();
        if (bytes == null || bytes.length == 0) {
            return;
        }
        PDImageXObject image = PDImageXObject.createFromByteArray(document, bytes, alt != null ? alt : value);
        float drawWidth = width > 0 ? width : image.getWidth();
        float drawHeight = height > 0 ? height : image.getHeight();
        float x = context.getCursorX();
        float y = context.getCursorY() - drawHeight;
        stream.drawImage(image, x, y, drawWidth, drawHeight);
        context.setCursorY(y - 8f);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = PdfBoxUnitConverter.toPoint(style.get("width"), width);
        height = PdfBoxUnitConverter.toPoint(style.get("height"), height);
    }
}

package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;

@Data
public class JQuickTextAreaElementRender implements JQuickElementRender {

    private String value;

    private JStyleAttributes style;

    private float width = 180f;

    private float height = 72f;

    private float padding = 6f;

    public JQuickTextAreaElementRender(String value, JStyleAttributes style) {
        this.value = value;
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        float x = context.getCursorX();
        float y = context.getCursorY() - height;
        PDColor borderColor = resolveBorderColor();
        PDColor textColor = resolveTextColor(context);
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
        }
        stream.addRect(x, y, width, height);
        stream.stroke();
        String text = JStringUtils.trim(value);
        if (text != null && !text.isEmpty()) {
            float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
            float lineHeight = fontSize + 4f;
            String[] lines = text.split("\\n", -1);
            float textY = y + height - padding - fontSize;
            for (String line : lines) {
                if (textY < y + padding) {
                    break;
                }
                if (line != null && !line.isEmpty()) {
                    stream.beginText();
                    stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
                    if (textColor != null) {
                        stream.setNonStrokingColor(textColor);
                    }
                    stream.setTextMatrix(Matrix.getTranslateInstance(x + padding, textY));
                    stream.showText(line);
                    stream.endText();
                }
                textY -= lineHeight;
            }
        }
        context.setCursorY(y - 8f);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = parseFloat(style.get("width"), width);
        height = parseFloat(style.get("height"), height);
        padding = parseFloat(style.get("padding"), padding);
    }

    private float parseFloat(Object value, float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value.toString().replace("px", "").trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private PDColor resolveBorderColor() {
        if (style != null && style.get("borderColor") != null) {
            return PdfBoxRenderAdapter.color(style.get("borderColor").toString());
        }
        return PdfBoxRenderAdapter.color("BLACK");
    }

    private PDColor resolveTextColor(JQuickRenderContext context) {
        if (style != null && style.get("color") != null) {
            return PdfBoxRenderAdapter.color(style.get("color").toString());
        }
        return context.getColor();
    }
}

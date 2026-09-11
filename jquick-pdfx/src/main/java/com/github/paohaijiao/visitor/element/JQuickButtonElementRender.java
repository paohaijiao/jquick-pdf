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
public class JQuickButtonElementRender implements JQuickElementRender {

    private String text;

    private JStyleAttributes style;

    private float width = 96f;

    private float height = 28f;

    private float paddingX = 10f;

    private float paddingY = 6f;

    public JQuickButtonElementRender(String text, JStyleAttributes style) {
        this.text = text;
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        String content = JStringUtils.trim(text);
        float x = context.getCursorX();
        float y = context.getCursorY() - height;
        PDColor backgroundColor = resolveBackgroundColor();
        PDColor borderColor = resolveBorderColor();
        PDColor textColor = resolveTextColor(context);
        if (backgroundColor != null) {
            stream.setNonStrokingColor(backgroundColor);
            stream.addRect(x, y, width, height);
            stream.fill();
        }
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
            stream.addRect(x, y, width, height);
            stream.stroke();
        }
        if (content != null && !content.isEmpty()) {
            float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
            float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(content) / 1000f * fontSize;
            float textX = x + Math.max(paddingX, (width - textWidth) / 2f);
            float textY = y + Math.max(paddingY, (height - fontSize) / 2f);
            stream.beginText();
            stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
            if (textColor != null) {
                stream.setNonStrokingColor(textColor);
            }
            stream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
            stream.showText(content);
            stream.endText();
        }
        context.setCursorY(y - paddingY);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = parseFloat(style.get("width"), width);
        height = parseFloat(style.get("height"), height);
        paddingX = parseFloat(style.get("paddingLeft"), paddingX);
        paddingY = parseFloat(style.get("paddingTop"), paddingY);
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

    private PDColor resolveBackgroundColor() {
        if (style != null && style.get("backgroundColor") != null) {
            return PdfBoxRenderAdapter.color(style.get("backgroundColor").toString());
        }
        return null;
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

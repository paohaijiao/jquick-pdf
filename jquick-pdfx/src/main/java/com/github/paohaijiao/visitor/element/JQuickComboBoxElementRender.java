package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.color.JColorEnums;
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
public class JQuickComboBoxElementRender implements JQuickElementRender {

    private String value;

    private JStyleAttributes style;

    private float width = 120f;

    private float height = 24f;

    private float paddingX = 8f;

    public JQuickComboBoxElementRender(String value, JStyleAttributes style) {
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
        drawArrow(stream, x + width - 14f, y + height / 2f + 2f);
        String text = JStringUtils.trim(value);
        if (text != null && !text.isEmpty()) {
            float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
            float textY = y + Math.max(2f, (height - fontSize) / 2f + 2f);
            stream.beginText();
            stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
            if (textColor != null) {
                stream.setNonStrokingColor(textColor);
            }
            stream.setTextMatrix(Matrix.getTranslateInstance(x + paddingX, textY));
            stream.showText(text);
            stream.endText();
        }
        context.setCursorY(y - 8f);
    }

    private void drawArrow(PDPageContentStream stream, float centerX, float centerY) throws IOException {
        stream.moveTo(centerX - 4f, centerY + 2f);
        stream.lineTo(centerX, centerY - 2f);
        stream.lineTo(centerX + 4f, centerY + 2f);
        stream.stroke();
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = parseFloat(style.get("width"), width);
        height = parseFloat(style.get("height"), height);
        paddingX = parseFloat(style.get("paddingLeft"), paddingX);
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
            return JColorEnums.colorOf(style.get("borderColor").toString());
        }
        return JColorEnums.colorOf("BLACK");
    }

    private PDColor resolveTextColor(JQuickRenderContext context) {
        if (style != null && style.get("color") != null) {
            return JColorEnums.colorOf(style.get("color").toString());
        }
        return context.getColor();
    }
}

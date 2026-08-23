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
public class JQuickCheckBoxElementRender implements JQuickElementRender {

    private boolean checked;

    private String label;

    private JStyleAttributes style;

    private float boxSize = 12f;

    private float labelGap = 6f;

    public JQuickCheckBoxElementRender(boolean checked, String label, JStyleAttributes style) {
        this.checked = checked;
        this.label = label;
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        float x = context.getCursorX();
        float y = context.getCursorY() - boxSize;
        PDColor borderColor = resolveBorderColor();
        PDColor textColor = resolveTextColor(context);
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
        }
        stream.addRect(x, y, boxSize, boxSize);
        stream.stroke();
        if (checked) {
            stream.moveTo(x + 2, y + boxSize / 2f);
            stream.lineTo(x + boxSize / 2f - 1, y + 2);
            stream.lineTo(x + boxSize - 2, y + boxSize - 2);
            stream.stroke();
        }
        String text = JStringUtils.trim(label);
        if (text != null && !text.isEmpty()) {
            float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
            float textY = y + Math.max(1f, (boxSize - fontSize) / 2f + 2f);
            stream.beginText();
            stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
            if (textColor != null) {
                stream.setNonStrokingColor(textColor);
            }
            stream.setTextMatrix(Matrix.getTranslateInstance(x + boxSize + labelGap, textY));
            stream.showText(text);
            stream.endText();
        }
        context.setCursorY(y - 8f);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        boxSize = parseFloat(style.get("width"), boxSize);
        labelGap = parseFloat(style.get("marginRight"), labelGap);
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

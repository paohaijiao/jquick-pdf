package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.color.JColorEnums;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class JQuickListElementRender implements JQuickElementRender {

    private boolean ordered;

    private List<String> items = new ArrayList<>();

    private JStyleAttributes style;

    private float indent = 18f;

    private float itemGap = 6f;

    public JQuickListElementRender(boolean ordered, List<String> items, JStyleAttributes style) {
        this.ordered = ordered;
        if (items != null) {
            this.items.addAll(items);
        }
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || items.isEmpty()) {
            return;
        }
        float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
        float lineHeight = context.getLineHeight() > 0 ? context.getLineHeight() : fontSize + 6f;
        PDColor textColor = resolveTextColor(context);
        float baseX = context.getCursorX();
        float y = context.getCursorY();
        for (int i = 0; i < items.size(); i++) {
            String marker = ordered ? (i + 1) + "." : "•";
            y -= lineHeight;
            drawText(stream, textColor, marker, baseX, y, fontSize);
            drawText(stream, textColor, items.get(i), baseX + indent, y, fontSize);
            y -= itemGap;
        }
        context.setCursorY(y);
    }

    private void drawText(PDPageContentStream stream, PDColor color, String text, float x, float y, float fontSize) throws IOException {
        if (text == null || text.isEmpty()) {
            return;
        }
        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
        if (color != null) {
            stream.setNonStrokingColor(color);
        }
        stream.setTextMatrix(Matrix.getTranslateInstance(x, y));
        stream.showText(text);
        stream.endText();
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        indent = parseFloat(style.get("paddingLeft"), indent);
        itemGap = parseFloat(style.get("marginBottom"), itemGap);
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

    private PDColor resolveTextColor(JQuickRenderContext context) {
        if (style != null && style.get("color") != null) {
            return JColorEnums.colorOf(style.get("color").toString());
        }
        return context.getColor();
    }
}

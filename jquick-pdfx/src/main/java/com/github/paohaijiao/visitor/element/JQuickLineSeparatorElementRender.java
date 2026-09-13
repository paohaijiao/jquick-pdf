package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

import java.io.IOException;

@Data
public class JQuickLineSeparatorElementRender implements JQuickElementRender {

    private JStyleAttributes style;

    private float width = -1f;

    private float marginTop = 4f;

    private float marginBottom = 8f;

    public JQuickLineSeparatorElementRender(JStyleAttributes style) {
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        float startX = context.getCursorX();
        float availableWidth = width > 0 ? width : context.getWidth();
        float y = context.getCursorY() - marginTop;
        PDColor color = resolveColor(context);
        if (color != null) {
            stream.setStrokingColor(color);
        }
        stream.moveTo(startX, y);
        stream.lineTo(startX + availableWidth, y);
        stream.stroke();
        context.setCursorY(y - marginBottom);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        width = parseFloat(style.get("width"), width);
        marginTop = parseFloat(style.get("marginTop"), marginTop);
        marginBottom = parseFloat(style.get("marginBottom"), marginBottom);
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

    private PDColor resolveColor(JQuickRenderContext context) {
        // 分隔线是描边绘制，颜色以 strokeColor 为准（文档写法），兼容 color 简写。
        if (style != null) {
            Object strokeColor = style.get("strokeColor");
            if (strokeColor != null) {
                return PdfBoxRenderAdapter.color(strokeColor.toString());
            }
            Object color = style.get("color");
            if (color != null) {
                return PdfBoxRenderAdapter.color(color.toString());
            }
        }
        return context.getColor();
    }
}

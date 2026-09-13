package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

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
            // 文本走共享适配器：字体取自上下文（CJK 字体，支持中文），
            // 颜色按 fontColor → color 解析，并过滤字体缺失的字符避免整页渲染中断。
            PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
            PDFont font = PdfBoxRenderAdapter.resolveFont(model, context.getFont());
            float textWidth = PdfBoxRenderAdapter.textWidth(font, content, model.getFontSize(),
                    model.getCharacterSpacing(), model.getWordSpacing());
            float textX = x + Math.max(paddingX, (width - textWidth) / 2f);
            float textY = y + Math.max(paddingY, (height - model.getFontSize()) / 2f);
            PdfBoxRenderAdapter.drawText(stream, model, font, content, textX, textY, width);
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
}

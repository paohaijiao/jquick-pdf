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
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
        }
        stream.addRect(x, y, width, height);
        stream.stroke();
        drawArrow(stream, x + width - 14f, y + height / 2f + 2f);
        String text = JStringUtils.trim(value);
        if (text != null && !text.isEmpty()) {
            // 显示值走共享适配器：字体取自上下文（CJK 字体，支持中文），
            // 颜色按 fontColor → color 解析，并过滤字体缺失的字符避免整页渲染中断。
            PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
            PDFont font = PdfBoxRenderAdapter.resolveFont(model, context.getFont());
            float textY = y + Math.max(2f, (height - model.getFontSize()) / 2f + 2f);
            PdfBoxRenderAdapter.drawText(stream, model, font, text, x + paddingX, textY, width);
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
            return PdfBoxRenderAdapter.color(style.get("borderColor").toString());
        }
        return PdfBoxRenderAdapter.color("BLACK");
    }
}

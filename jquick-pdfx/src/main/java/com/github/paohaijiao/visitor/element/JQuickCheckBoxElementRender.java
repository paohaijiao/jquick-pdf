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
            // 标签文本走共享适配器：字体取自上下文（CJK 字体，支持中文），
            // 颜色按 fontColor → color 解析，并过滤字体缺失的字符避免整页渲染中断。
            PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
            PDFont font = PdfBoxRenderAdapter.resolveFont(model, context.getFont());
            float textY = y + Math.max(1f, (boxSize - model.getFontSize()) / 2f + 2f);
            PdfBoxRenderAdapter.drawText(stream, model, font, text, x + boxSize + labelGap, textY, 0f);
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
            return PdfBoxRenderAdapter.color(style.get("borderColor").toString());
        }
        return PdfBoxRenderAdapter.color("BLACK");
    }
}

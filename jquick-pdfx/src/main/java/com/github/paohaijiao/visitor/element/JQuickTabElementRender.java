package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.style.JQuickStyleEnum;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

@Data
public class JQuickTabElementRender implements JQuickElementRender {

    private JStyleAttributes style;

    private float tabWidth = 24f;

    public JQuickTabElementRender(JStyleAttributes style) {
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) {
        if (context == null) {
            return;
        }
        context.setCursorX(context.getCursorX() + tabWidth);
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        tabWidth = JQuickStyleEnum.width.getFloat(style, tabWidth);
    }
}

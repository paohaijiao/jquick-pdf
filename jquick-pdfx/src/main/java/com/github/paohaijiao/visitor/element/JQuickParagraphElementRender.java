package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.util.List;

@Data
public class JQuickParagraphElementRender  implements JQuickElementRender {

    private List<JQuickElementRender> children;

    private float marginTop = 0;
    private float marginBottom = 0;

    public JQuickParagraphElementRender(List<JQuickElementRender> children) {
        this.children = children;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        // 应用上边距
        float y = context.getCursorY() - marginTop;
        //绘制所有子元素
        for (JQuickElementRender child : children) {
            float requiredHeight = context.getLineHeight() > 0 ? context.getLineHeight() : context.getFontSize() * 1.5f;
            if (context.getLayoutEngine() != null) {
                context.getLayoutEngine().ensureSpace(requiredHeight, false);
                stream = context.getLayoutEngine().getStream();
            } else if (y - requiredHeight < context.getMargins()[2]) {
                y = context.getPageHeight() - context.getMargins()[0];
                context.setCursorY(y);
            }

            child.draw(stream, context);
            // 更新 Y 位置（子元素绘制完后会更新 cursorY）
            y = context.getCursorY();
        }
        // 应用下边距
        context.setCursorY(y - marginBottom);
    }
}

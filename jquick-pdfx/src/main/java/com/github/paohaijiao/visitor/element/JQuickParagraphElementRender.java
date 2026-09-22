package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;
import java.util.List;

/**
 * 段落渲染器：把 {@code <p>} 承载的行内内容（纯文本、{@code <span>} 内联文本、{@code <tab>} 制表符等）
 * 按声明顺序从左到右排布在同一行，与 HTML “段落是块级容器、内容是行内流”的语义一致。
 * <p>
 * 行内子元素只横向推进光标；整段结束后光标落到行底并回到段落左边界，
 * 保证后续块级元素（下一个 {@code <p>}、{@code <div>} 等）从新的一行开始，不被行内元素带偏。
 */
@Data
public class JQuickParagraphElementRender implements JQuickElementRender {

    private List<JQuickElementRender> children;

    private JStyleAttributes style;

    public JQuickParagraphElementRender(List<JQuickElementRender> children) {
        this.children = children;
    }

    public JQuickParagraphElementRender(List<JQuickElementRender> children, JStyleAttributes style) {
        this.children = children;
        this.style = style;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || children == null || children.isEmpty()) {
            return;
        }
        PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
        float lineHeight = resolveLineHeight(model, context);
        float savedWidth = context.getWidth();
        float savedLineStartX = context.getLineStartX();
        float left = context.getCursorX();
        float right = left + resolveWidth(model, context);
        context.setCursorY(context.getCursorY() - model.getMarginTop());
        context.setLineStartX(left);
        for (JQuickElementRender child : children) {
            if (child == null) {
                continue;
            }
            PdfBoxLayoutEngine layoutEngine = context.getLayoutEngine();
            if (layoutEngine != null) {
                int pageBefore = context.getPageNumber();
                layoutEngine.ensureSpace(lineHeight, false);
                stream = layoutEngine.getStream();
                if (context.getPageNumber() != pageBefore) {
                    left = context.getCursorX();
                    right = left + resolveWidth(model, context);
                    context.setLineStartX(left);
                }
            }
            float childX = context.getCursorX();
            // 行内元素可用的宽度是整行剩余宽度，超出后文本自行换行。
            context.setWidth(Math.max(0f, right - childX));
            stream.setFont(context.getFont(),context.getFontSize());
            child.draw(stream, context);
        }
        context.setWidth(savedWidth);
        context.setLineStartX(savedLineStartX);
        context.setCursorX(left);
        // 段落至少占一行：即使子元素只有 <tab>（不产生文本）也要保留行高。
        context.setCursorY(context.getCursorY() - lineHeight - model.getMarginBottom());
    }

    /** 行高与行内文本保持一致：按段落字号计算，缺省时回退到上下文的行高。 */
    private float resolveLineHeight(PdfBoxStyleModel model, JQuickRenderContext context) {
        if (model.getFontSize() > 0f) {
            return model.getFontSize() * 1.5f;
        }
        return context.getLineHeight() > 0f ? context.getLineHeight() : 18f;
    }

    private float resolveWidth(PdfBoxStyleModel model, JQuickRenderContext context) {
        return model.getWidth() > 0f ? model.getWidth() : context.getWidth();
    }
}

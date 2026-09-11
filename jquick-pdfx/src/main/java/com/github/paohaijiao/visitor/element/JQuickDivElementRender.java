package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class JQuickDivElementRender implements JQuickElementRender {

    private JStyleAttributes style;

    private List<JQuickElementRender> children = new ArrayList<>();

    public JQuickDivElementRender(JStyleAttributes style, List<JQuickElementRender> children) {
        this.style = style;
        if (children != null) {
            this.children.addAll(children);
        }
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }
        PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
        float lineHeight = resolveLineHeight(context, model);
        float outerWidth = resolveOuterWidth(context, model);
        float estimatedHeight = resolveEstimatedHeight(model, lineHeight);
        float requiredHeight = estimatedHeight + model.getMarginTop() + model.getMarginBottom();

        if (context.getLayoutEngine() != null) {
            context.getLayoutEngine().ensureSpace(requiredHeight, model.isKeepTogether());
            stream = context.getLayoutEngine().getStream();
        }

        float originalCursorX = context.getCursorX();
        float originalCursorY = context.getCursorY();
        float originalWidth = context.getWidth();
        float originalHeight = context.getHeight();
        float originalX = context.getX();
        float originalY = context.getY();
        int startPageNumber = context.getPageNumber();

        float boxX = originalCursorX + model.getMarginLeft();
        float boxTop = originalCursorY - model.getMarginTop();
        PdfBoxRenderAdapter.drawBox(context.getDocument(), stream, model, boxX, boxTop, outerWidth, estimatedHeight);
        PdfBoxRenderAdapter.drawBackgroundImage(context.getDocument(), stream, model,
                boxX, boxTop - estimatedHeight, outerWidth, estimatedHeight);

        context.setCursorX(boxX + model.getPaddingLeft());
        context.setCursorY(boxTop - model.getPaddingTop());
        context.setX(context.getCursorX());
        context.setY(context.getCursorY());
        context.setWidth(Math.max(0f, outerWidth - model.getPaddingLeft() - model.getPaddingRight()));
        context.setHeight(Math.max(0f, estimatedHeight - model.getPaddingTop() - model.getPaddingBottom()));

        for (JQuickElementRender child : children) {
            if (child == null) {
                continue;
            }
            if (context.getLayoutEngine() != null) {
                context.getLayoutEngine().ensureSpace(lineHeight, false);
                stream = context.getLayoutEngine().getStream();
            }
            child.draw(stream, context);
        }

        float finalCursorY;
        if (context.getPageNumber() != startPageNumber) {
            finalCursorY = context.getCursorY() - model.getPaddingBottom() - model.getMarginBottom();
        } else {
            float contentBottom = context.getCursorY() - model.getPaddingBottom();
            float boxBottom = boxTop - estimatedHeight;
            finalCursorY = Math.min(boxBottom, contentBottom) - model.getMarginBottom();
        }

        context.setCursorX(originalCursorX);
        context.setCursorY(finalCursorY);
        context.setX(originalX);
        context.setY(originalY);
        context.setWidth(originalWidth);
        context.setHeight(originalHeight);
    }

    private float resolveOuterWidth(JQuickRenderContext context, PdfBoxStyleModel model) {
        float availableWidth = context.getWidth() > 0f ? context.getWidth() : pageContentWidth(context);
        float width = model.getWidth() > 0f
                ? model.getWidth()
                : availableWidth - model.getMarginLeft() - model.getMarginRight();
        if (model.getMinWidth() > 0f) {
            width = Math.max(width, model.getMinWidth());
        }
        if (model.getMaxWidth() > 0f) {
            width = Math.min(width, model.getMaxWidth());
        }
        return Math.max(0f, width);
    }

    private float pageContentWidth(JQuickRenderContext context) {
        float[] margins = context.getMargins() == null ? new float[]{0f, 0f, 0f, 0f} : context.getMargins();
        return Math.max(0f, context.getPageWidth() - margins[1] - margins[3]);
    }

    private float resolveEstimatedHeight(PdfBoxStyleModel model, float lineHeight) {
        if (model.getHeight() > 0f) {
            return model.getHeight();
        }
        float contentHeight = Math.max(lineHeight, children.size() * lineHeight);
        float height = contentHeight + model.getPaddingTop() + model.getPaddingBottom();
        if (model.getMinHeight() > 0f) {
            height = Math.max(height, model.getMinHeight());
        }
        if (model.getMaxHeight() > 0f) {
            height = Math.min(height, model.getMaxHeight());
        }
        return Math.max(0f, height);
    }

    private float resolveLineHeight(JQuickRenderContext context, PdfBoxStyleModel model) {
        if (context.getLineHeight() > 0f) {
            return context.getLineHeight();
        }
        float fontSize = model.getFontSize() > 0f ? model.getFontSize() : context.getFontSize();
        return (fontSize > 0f ? fontSize : 12f) * 1.5f;
    }
}

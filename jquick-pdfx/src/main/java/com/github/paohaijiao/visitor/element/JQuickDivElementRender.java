package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
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
        PdfBoxLayoutEngine layoutEngine = context.getLayoutEngine();
        float lineHeight = resolveLineHeight(context, model);
        float outerWidth = resolveOuterWidth(context, model);
        // 预估值仅用于提前预留空间；背景框的真实高度由子元素排版结果决定。
        float estimatedHeight = resolveEstimatedHeight(model, lineHeight);
        float requiredHeight = estimatedHeight + model.getMarginTop() + model.getMarginBottom();

        if (layoutEngine != null) {
            layoutEngine.ensureSpace(requiredHeight, model.isKeepTogether());
            stream = layoutEngine.getStream();
        }

        float originalCursorX = context.getCursorX();
        float originalCursorY = context.getCursorY();
        float originalWidth = context.getWidth();
        float originalHeight = context.getHeight();
        float originalX = context.getX();
        float originalY = context.getY();

        float boxX = originalCursorX + model.getMarginLeft();
        float boxTop = originalCursorY - model.getMarginTop();
        float contentX = boxX + model.getPaddingLeft();
        float contentTop = boxTop - model.getPaddingTop();
        float contentWidth = Math.max(0f, outerWidth - model.getPaddingLeft() - model.getPaddingRight());

        if (hasVisibleBox(model)) {
            // 先按 div 的内容区位置试排版一次，量出子元素真实占用的高度，
            // 这样背景框的高度与位置就跟随子元素的实际排版结果，而不是凭空估算。
            float contentHeight = measureContent(stream, context, contentX, contentTop, contentWidth, layoutEngine);
            float boxHeight = resolveBoxHeight(model, contentHeight);
            // 背景必须在文字之下：用真实高度绘制背景覆盖试排版结果，再重绘子元素。
            PdfBoxRenderAdapter.drawBox(context.getDocument(), stream, model, boxX, boxTop, outerWidth, boxHeight);
            PdfBoxRenderAdapter.drawBackgroundImage(context.getDocument(), stream, model,
                    boxX, boxTop - boxHeight, outerWidth, boxHeight);
            drawChildren(stream, context, contentX, contentTop, contentWidth, layoutEngine, lineHeight);
            context.setCursorY(boxTop - boxHeight - model.getMarginBottom());
        } else {
            drawChildren(stream, context, contentX, contentTop, contentWidth, layoutEngine, lineHeight);
            if (model.getHeight() > 0f) {
                context.setCursorY(boxTop - resolveBoxHeight(model, 0f) - model.getMarginBottom());
            } else {
                context.setCursorY(context.getCursorY() - model.getPaddingBottom() - model.getMarginBottom());
            }
        }

        context.setCursorX(originalCursorX);
        context.setX(originalX);
        context.setY(originalY);
        context.setWidth(originalWidth);
        context.setHeight(originalHeight);
    }

    /** 是否绘制可见背景/边框；不可见的 div 无需试排版，直接按子元素实际位置推进光标。 */
    private boolean hasVisibleBox(PdfBoxStyleModel model) {
        return model.getBackgroundColor() != null
                || model.getBackgroundImage() != null
                || model.getBorder() != null
                || model.getBorderTop() != null
                || model.getBorderRight() != null
                || model.getBorderBottom() != null
                || model.getBorderLeft() != null;
    }

    /** 试排版：在内容区位置绘制子元素以量取真实高度（关闭分页，结果随后被背景覆盖）。 */
    private float measureContent(PDPageContentStream stream, JQuickRenderContext context, float contentX,
                                 float contentTop, float contentWidth, PdfBoxLayoutEngine layoutEngine)
            throws IOException {
        boolean pagination = layoutEngine != null && layoutEngine.isPaginationEnabled();
        if (layoutEngine != null) {
            layoutEngine.setPaginationEnabled(false);
        }
        try {
            applyContentBox(context, contentX, contentTop, contentWidth);
            for (JQuickElementRender child : children) {
                if (child == null) {
                    continue;
                }
                child.draw(stream, context);
            }
            return Math.max(0f, contentTop - context.getCursorY());
        } finally {
            if (layoutEngine != null) {
                layoutEngine.setPaginationEnabled(pagination);
            }
        }
    }

    /** 正式绘制子元素，使其位于背景之上。 */
    private void drawChildren(PDPageContentStream stream, JQuickRenderContext context, float contentX,
                              float contentTop, float contentWidth, PdfBoxLayoutEngine layoutEngine,
                              float lineHeight) throws IOException {
        applyContentBox(context, contentX, contentTop, contentWidth);
        for (JQuickElementRender child : children) {
            if (child == null) {
                continue;
            }
            if (layoutEngine != null) {
                layoutEngine.ensureSpace(lineHeight, false);
                stream = layoutEngine.getStream();
            }
            child.draw(stream, context);
        }
    }

    private void applyContentBox(JQuickRenderContext context, float contentX, float contentTop, float contentWidth) {
        context.setCursorX(contentX);
        context.setCursorY(contentTop);
        context.setX(contentX);
        context.setY(contentTop);
        context.setWidth(contentWidth);
    }

    private float resolveBoxHeight(PdfBoxStyleModel model, float contentHeight) {
        float height = model.getHeight() > 0f
                ? model.getHeight()
                : contentHeight + model.getPaddingTop() + model.getPaddingBottom();
        if (model.getMinHeight() > 0f) {
            height = Math.max(height, model.getMinHeight());
        }
        if (model.getMaxHeight() > 0f) {
            height = Math.min(height, model.getMaxHeight());
        }
        return Math.max(0f, height);
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

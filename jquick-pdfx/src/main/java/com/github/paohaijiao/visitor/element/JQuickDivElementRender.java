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
        float estimatedHeight = resolveEstimatedHeight(context, model, lineHeight);
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

        boolean visibleBox = hasVisibleBox(model);
        float contentHeight = 0f;
        float boxHeight = 0f;
        if (visibleBox) {
            // 先按 div 的内容区位置试排版一次，量出子元素真实占用的高度，
            // 这样背景框的高度与位置就跟随子元素的实际排版结果，而不是凭空估算。
            contentHeight = measureContent(stream, context, contentX, contentTop, contentWidth, layoutEngine, lineHeight);
            boxHeight = resolveBoxHeight(model, contentHeight);
            // 背景必须在文字之下：用真实高度绘制背景覆盖试排版结果，再重绘子元素。
            PdfBoxRenderAdapter.drawBox(context.getDocument(), stream, model, boxX, boxTop, outerWidth, boxHeight);
            PdfBoxRenderAdapter.drawBackgroundImage(context.getDocument(), stream, model,
                    boxX, boxTop - boxHeight, outerWidth, boxHeight);
            // 内容整体下移 verticalAlignment 产生的偏移，实现盒内垂直对齐（top 时为 0）。
            float actualContentTop = contentTop - resolveVerticalOffset(model, boxHeight, contentHeight);
            drawChildren(stream, context, contentX, actualContentTop, contentWidth, layoutEngine, lineHeight);
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

    /** 试排版：在内容区位置排出子元素以量取真实高度（关闭分页，结果随后被背景覆盖）。 */
    private float measureContent(PDPageContentStream stream, JQuickRenderContext context, float contentX,
                                 float contentTop, float contentWidth, PdfBoxLayoutEngine layoutEngine,
                                 float lineHeight) throws IOException {
        boolean pagination = layoutEngine != null && layoutEngine.isPaginationEnabled();
        if (layoutEngine != null) {
            layoutEngine.setPaginationEnabled(false);
        }
        try {
            return layoutContent(stream, context, contentX, contentTop, contentWidth, layoutEngine, lineHeight);
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
        layoutContent(stream, context, contentX, contentTop, contentWidth, layoutEngine, lineHeight);
    }

    /**
     * 排出内容并返回其占用的高度。flex 容器按行排列子元素，其余按块级垂直堆叠。
     * 分页是否生效由调用方控制（试排版阶段关闭分页）。
     */
    private float layoutContent(PDPageContentStream stream, JQuickRenderContext context, float contentX,
                                float contentTop, float contentWidth, PdfBoxLayoutEngine layoutEngine,
                                float lineHeight) throws IOException {
        PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
        if (isFlexRow(context, model)) {
            return layoutFlexRows(stream, context, model, contentX, contentTop, contentWidth, layoutEngine);
        }
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
        return Math.max(0f, contentTop - context.getCursorY());
    }

    /**
     * flex 行布局：子元素依据声明的宽度排在同一行；一行放不下时按比例收缩（等价于 HTML 默认的
     * flex-wrap:nowrap），若声明了 flex-wrap:wrap 则换行；剩余空间按 justifyContent 分配。
     * <p>
     * 每行作为整体预留分页空间并整体推进光标：行的实际高度取行内元素的最大值，
     * 而不是最后一个子元素的底部，否则后续内容会与整行重叠。
     */
    private float layoutFlexRows(PDPageContentStream stream, JQuickRenderContext context, PdfBoxStyleModel model,
                                 float contentX, float contentTop, float contentWidth,
                                 PdfBoxLayoutEngine layoutEngine) throws IOException {
        List<List<Integer>> rows = buildFlexRows(model, contentWidth);
        float top = contentTop;
        for (List<Integer> row : rows) {
            if (layoutEngine != null) {
                // 行内元素必须留在同一页：先按预估行高预留空间，避免同一行被分页拆散。
                context.setCursorY(top);
                int pageBefore = context.getPageNumber();
                layoutEngine.ensureSpace(resolveRowEstimatedHeight(row), false);
                stream = layoutEngine.getStream();
                if (context.getPageNumber() != pageBefore) {
                    top = context.getCursorY();
                    contentX = context.getCursorX() + model.getMarginLeft() + model.getPaddingLeft();
                }
            }
            float[] widths = resolveFlexWidths(row, contentWidth);
            float[] positions = resolveFlexPositions(model, row, widths, contentX, contentWidth);
            float rowHeight = 0f;
            for (int k = 0; k < row.size(); k++) {
                JQuickElementRender child = children.get(row.get(k));
                context.setCursorX(positions[k]);
                context.setCursorY(top);
                context.setX(positions[k]);
                context.setY(top);
                context.setWidth(widths[k]);
                context.setHeight(0f);
                child.draw(stream, context);
                rowHeight = Math.max(rowHeight, top - context.getCursorY());
            }
            top -= Math.max(rowHeight, 0f);
            context.setCursorY(top);
        }
        return Math.max(0f, contentTop - top);
    }

    /** 行内元素声明高度的最大值，用于给整行预留分页空间；均未声明高度时返回 0（不预留）。 */
    private float resolveRowEstimatedHeight(List<Integer> row) {
        float tallest = 0f;
        for (Integer index : row) {
            tallest = Math.max(tallest, declaredItemHeight(children.get(index)));
        }
        return Math.max(tallest, 0f);
    }

    /** 依据子元素声明宽度组织 flex 行；未声明宽度的子元素独占一行。 */
    private List<List<Integer>> buildFlexRows(PdfBoxStyleModel model, float contentWidth) {
        String wrapValue = trim(model.getFlexWrap());
        boolean wrap = "wrap".equalsIgnoreCase(wrapValue) || "wrap-reverse".equalsIgnoreCase(wrapValue);
        List<List<Integer>> rows = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        float used = 0f;
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) == null) {
                continue;
            }
            float baseWidth = declaredItemWidth(children.get(i));
            if (baseWidth <= 0f) {
                if (!current.isEmpty()) {
                    rows.add(current);
                    current = new ArrayList<>();
                    used = 0f;
                }
                List<Integer> single = new ArrayList<>();
                single.add(i);
                rows.add(single);
                continue;
            }
            if (wrap && !current.isEmpty() && used + baseWidth > contentWidth) {
                rows.add(current);
                current = new ArrayList<>();
                used = 0f;
            }
            current.add(i);
            used += baseWidth;
        }
        if (!current.isEmpty()) {
            rows.add(current);
        }
        return rows;
    }

    /** 按声明宽度等比例收缩，使整行宽度不超过可用宽度。 */
    private float[] resolveFlexWidths(List<Integer> row, float contentWidth) {
        float[] bases = new float[row.size()];
        float total = 0f;
        for (int k = 0; k < row.size(); k++) {
            float baseWidth = declaredItemWidth(children.get(row.get(k)));
            bases[k] = baseWidth > 0f ? baseWidth : contentWidth;
            total += bases[k];
        }
        float scale = total > contentWidth && total > 0f ? contentWidth / total : 1f;
        float[] widths = new float[row.size()];
        for (int k = 0; k < row.size(); k++) {
            widths[k] = bases[k] * scale;
        }
        return widths;
    }

    /** 依据 justifyContent 计算行内每个子元素的起始 x。 */
    private float[] resolveFlexPositions(PdfBoxStyleModel model, List<Integer> row, float[] widths,
                                         float contentX, float contentWidth) {
        float total = 0f;
        for (float width : widths) {
            total += width;
        }
        float free = Math.max(0f, contentWidth - total);
        String justify = trim(model.getJustifyContent());
        float startX = contentX;
        float gap = 0f;
        if ("center".equalsIgnoreCase(justify)) {
            startX = contentX + free / 2f;
        } else if ("flex-end".equalsIgnoreCase(justify) || "end".equalsIgnoreCase(justify)
                || "right".equalsIgnoreCase(justify)) {
            startX = contentX + free;
        } else if ("space-between".equalsIgnoreCase(justify)) {
            gap = row.size() > 1 ? free / (row.size() - 1) : 0f;
        } else if ("space-around".equalsIgnoreCase(justify)) {
            gap = !row.isEmpty() ? free / row.size() : 0f;
            startX = contentX + gap / 2f;
        }
        float[] positions = new float[row.size()];
        for (int k = 0; k < row.size(); k++) {
            positions[k] = k == 0 ? startX : positions[k - 1] + widths[k - 1] + gap;
        }
        return positions;
    }

    /** 子元素声明的外部宽度（含左右外边距）；未声明时返回 -1。 */
    private float declaredItemWidth(JQuickElementRender child) {
        JStyleAttributes childStyle = child.getStyle();
        if (childStyle == null) {
            return -1f;
        }
        PdfBoxStyleModel childModel = PdfBoxStyleModel.from(childStyle);
        if (childModel.getWidth() <= 0f) {
            return -1f;
        }
        return childModel.getWidth() + childModel.getMarginLeft() + childModel.getMarginRight();
    }

    /** 子元素声明的外部高度（含内边距与上下外边距）；未声明时返回 -1，用于预留分页空间。 */
    private float declaredItemHeight(JQuickElementRender child) {
        JStyleAttributes childStyle = child.getStyle();
        if (childStyle == null) {
            return -1f;
        }
        PdfBoxStyleModel childModel = PdfBoxStyleModel.from(childStyle);
        if (childModel.getHeight() <= 0f) {
            return -1f;
        }
        return childModel.getHeight() + childModel.getPaddingTop() + childModel.getPaddingBottom()
                + childModel.getMarginTop() + childModel.getMarginBottom();
    }

    /** 是否按 flex 行布局处理子元素：需要开启配置、声明 display:flex，且主轴为水平方向。 */
    private boolean isFlexRow(JQuickRenderContext context, PdfBoxStyleModel model) {
        if (!context.isFlexLayout() || children.isEmpty()) {
            return false;
        }
        String display = trim(model.getDisplay());
        if (!"flex".equalsIgnoreCase(display) && !"inline-flex".equalsIgnoreCase(display)) {
            return false;
        }
        String direction = trim(model.getFlexDirection());
        return !"column".equalsIgnoreCase(direction) && !"column-reverse".equalsIgnoreCase(direction);
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }

    private void applyContentBox(JQuickRenderContext context, float contentX, float contentTop, float contentWidth) {
        context.setCursorX(contentX);
        context.setCursorY(contentTop);
        context.setX(contentX);
        context.setY(contentTop);
        context.setWidth(contentWidth);
    }

    /**
     * {@code verticalAlignment} 产生的下移量：内容相对内容区顶部下移，middle 取空余空间的一半、
     * bottom 取全部，top 与其它取值（含未声明）为 0。空余空间为内容区高度减去内容实际高度，
     * 因此只有声明了 {@code height}/{@code minHeight}（盒高大于内容高）时才会真正生效。
     * <p>
     * 只在可见盒子（声明了背景或边框）中生效：内容高度来自试排版，试排版会先画一遍子元素，
     * 无可见盒子的背景可覆盖时会把同一段内容画出两次。
     */
    private float resolveVerticalOffset(PdfBoxStyleModel model, float boxHeight, float contentHeight) {
        float innerHeight = boxHeight - model.getPaddingTop() - model.getPaddingBottom();
        float free = innerHeight - contentHeight;
        if (free <= 0f) {
            return 0f;
        }
        String alignment = trim(model.getVerticalAlignment());
        if ("bottom".equalsIgnoreCase(alignment)) {
            return free;
        }
        if ("middle".equalsIgnoreCase(alignment) || "center".equalsIgnoreCase(alignment)) {
            return free / 2f;
        }
        return 0f;
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
        float width;
        if (model.getWidth() > 0f) {
            // 声明宽度不得超过父级分配的宽度，保证 flex 行内按比例收缩能生效，同时避免溢出页面。
            width = availableWidth > 0f ? Math.min(model.getWidth(), availableWidth) : model.getWidth();
        } else {
            width = availableWidth - model.getMarginLeft() - model.getMarginRight();
        }
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

    private float resolveEstimatedHeight(JQuickRenderContext context, PdfBoxStyleModel model, float lineHeight) {
        if (model.getHeight() > 0f) {
            return model.getHeight();
        }
        float contentHeight;
        if (isFlexRow(context, model)) {
            // flex 容器中同一行的子元素高度取最大值，用声明高度中的最大者预留分页空间。
            float tallest = 0f;
            for (JQuickElementRender child : children) {
                if (child == null) {
                    continue;
                }
                tallest = Math.max(tallest, declaredItemHeight(child));
            }
            contentHeight = tallest > 0f ? tallest : lineHeight;
        } else {
            contentHeight = Math.max(lineHeight, children.size() * lineHeight);
        }
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

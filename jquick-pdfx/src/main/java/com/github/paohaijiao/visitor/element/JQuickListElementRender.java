package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import com.github.paohaijiao.visitor.render.PdfBoxUnitConverter;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表渲染器：每个 {@code <li>} 按自身样式渲染为独立区块，
 * 支持背景色、左边框、内边距、外边距以及行内 span 与纯文本混排。
 */
@Data
public class JQuickListElementRender implements JQuickElementRender {

    /** 行内文本片段：保留片段自身样式，用于 span 与纯文本混排。 */
    @Data
    public static class Run {

        private final String text;

        private final JStyleAttributes style;

        public Run(String text, JStyleAttributes style) {
            this.text = text;
            this.style = style;
        }
    }

    /** 列表项：携带 {@code <li>} 自身样式与行内片段。 */
    @Data
    public static class Item {

        private final List<Run> runs;

        private final JStyleAttributes style;

        public Item(List<Run> runs, JStyleAttributes style) {
            this.runs = runs == null ? new ArrayList<>() : runs;
            this.style = style;
        }
    }

    private boolean ordered;

    private List<Item> items = new ArrayList<>();

    private JStyleAttributes style;

    private float indent = 18f;

    private float itemGap = 6f;

    private boolean markerVisible = true;

    public JQuickListElementRender(boolean ordered, List<Item> items, JStyleAttributes style) {
        this.ordered = ordered;
        if (items != null) {
            this.items.addAll(items);
        }
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || items.isEmpty()) {
            return;
        }
        PDFont baseFont = context.getFont() == null
                ? new PDType1Font(Standard14Fonts.FontName.HELVETICA) : context.getFont();
        float listWidth = resolveWidth(context);
        float markerGutter = markerVisible ? indent : 0f;
        float cursorX = context.getCursorX();
        float y = context.getCursorY();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            PdfBoxStyleModel model = PdfBoxStyleModel.from(item.getStyle());
            float fontSize = resolveFontSize(item.getStyle(), context);
            model.setFontSize(fontSize);
            float lineHeight = Math.max(fontSize * 1.5f, context.getLineHeight() > 0f ? context.getLineHeight() : 0f);
            float borderLeft = borderWidth(model.getBorderLeft() != null ? model.getBorderLeft() : model.getBorder());
            float contentWidth = Math.max(0f, listWidth - markerGutter - borderLeft
                    - model.getPaddingLeft() - model.getPaddingRight());
            List<List<Run>> lines = layout(item.getRuns(), baseFont, contentWidth, fontSize);
            float contentHeight = Math.max(lineHeight, lines.size() * lineHeight);
            float boxHeight = model.getPaddingTop() + contentHeight + model.getPaddingBottom();
            float marginBottom = model.getMarginBottom() > 0f ? model.getMarginBottom() : itemGap;
            float requiredHeight = model.getMarginTop() + boxHeight + marginBottom;

            // 仅当真正换页时才回读光标；ensureSpace 未换页时不会移动光标，
            // 直接回读会把 y 重置为列表起始位置，导致所有列表项重叠。
            int pageBefore = context.getPageNumber();
            context.setCursorY(y);
            if (context.getLayoutEngine() != null) {
                context.getLayoutEngine().ensureSpace(requiredHeight, false);
                stream = context.getLayoutEngine().getStream();
                if (context.getPageNumber() != pageBefore) {
                    cursorX = context.getCursorX();
                    y = context.getCursorY();
                }
            }

            float boxX = cursorX + markerGutter;
            float boxTop = y - model.getMarginTop();
            PdfBoxRenderAdapter.drawBox(context.getDocument(), stream, model, boxX, boxTop,
                    Math.max(0f, listWidth - markerGutter), boxHeight);

            float textX = boxX + borderLeft + model.getPaddingLeft();
            float baseline = boxTop - model.getPaddingTop() - fontSize;
            for (List<Run> line : lines) {
                float x = textX;
                for (Run run : line) {
                    x += drawRun(stream, baseFont, run, x, baseline, fontSize);
                }
                baseline -= lineHeight;
            }
            if (markerVisible) {
                String marker = ordered ? (i + 1) + "." : "•";
                drawRun(stream, baseFont, new Run(marker, item.getStyle()), cursorX,
                        boxTop - model.getPaddingTop() - fontSize, fontSize);
            }
            y = boxTop - boxHeight - marginBottom;
        }
        context.setCursorY(y);
    }

    /** 按可用宽度将行内片段装箱成多行，片段内部超出宽度时逐字折行。 */
    private List<List<Run>> layout(List<Run> runs, PDFont baseFont, float maxWidth, float itemFontSize)
            throws IOException {
        List<List<Run>> lines = new ArrayList<>();
        List<Run> current = new ArrayList<>();
        float used = 0f;
        for (Run run : runs) {
            String text = run.getText();
            if (text == null || text.isEmpty()) {
                continue;
            }
            float fontSize = resolveFontSize(run.getStyle(), itemFontSize);
            PDFont font = PdfBoxRenderAdapter.resolveFont(PdfBoxStyleModel.from(run.getStyle()), baseFont);
            List<String> pieces = PdfBoxRenderAdapter.wrapText(font, text, fontSize, maxWidth, 0f, 0f);
            for (int index = 0; index < pieces.size(); index++) {
                String piece = pieces.get(index);
                if (piece.isEmpty()) {
                    continue;
                }
                float pieceWidth = PdfBoxRenderAdapter.textWidth(font, piece, fontSize, 0f, 0f);
                boolean wrap = index > 0 || (!current.isEmpty() && used + pieceWidth > maxWidth);
                if (wrap && !current.isEmpty()) {
                    lines.add(current);
                    current = new ArrayList<>();
                    used = 0f;
                }
                current.add(new Run(piece, run.getStyle()));
                used += pieceWidth;
                if (maxWidth > 0f && used >= maxWidth) {
                    lines.add(current);
                    current = new ArrayList<>();
                    used = 0f;
                }
            }
        }
        if (!current.isEmpty()) {
            lines.add(current);
        }
        if (lines.isEmpty()) {
            lines.add(new ArrayList<>());
        }
        return lines;
    }

    /** 绘制单个行内片段，返回其占用的宽度。 */
    private float drawRun(PDPageContentStream stream, PDFont baseFont, Run run, float x, float baseline,
                          float itemFontSize) throws IOException {
        if (run.getText() == null || run.getText().isEmpty()) {
            return 0f;
        }
        PdfBoxStyleModel model = PdfBoxStyleModel.from(run.getStyle());
        model.setFontSize(resolveFontSize(run.getStyle(), itemFontSize));
        model.setTextAlignment("left");
        PDFont font = PdfBoxRenderAdapter.resolveFont(model, baseFont);
        PdfBoxRenderAdapter.drawText(stream, model, font, run.getText(), x, baseline, 0f);
        return PdfBoxRenderAdapter.textWidth(font, run.getText(), model.getFontSize(),
                model.getCharacterSpacing(), model.getWordSpacing());
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        indent = parseFloat(first(style, "paddingLeft", "padding-left"), indent);
        itemGap = parseFloat(first(style, "marginBottom", "margin-bottom"), itemGap);
        String listStyleType = first(style, "listStyleType", "list-style-type");
        if (listStyleType != null) {
            markerVisible = !listStyleType.toLowerCase(java.util.Locale.ROOT).contains("none");
        }
    }

    private float resolveWidth(JQuickRenderContext context) {
        if (context.getWidth() > 0f) {
            return context.getWidth();
        }
        float[] margins = context.getMargins() == null ? new float[]{0f, 0f, 0f, 0f} : context.getMargins();
        return Math.max(0f, context.getPageWidth() - margins[1] - margins[3]);
    }

    private float resolveFontSize(JStyleAttributes local, float fallback) {
        String value = first(local, "fontSize", "font-size");
        if (value == null) {
            value = first(style, "fontSize", "font-size");
        }
        return value == null ? fallback : PdfBoxUnitConverter.toPoint(value, fallback);
    }

    private float resolveFontSize(JStyleAttributes local, JQuickRenderContext context) {
        return resolveFontSize(local, context.getFontSize() > 0f ? context.getFontSize() : 12f);
    }

    /** 取边框定义中的线宽，兼容 {@code 1px solid #ccc} 与 {@code solid 1px #ccc} 两种顺序。 */
    private float borderWidth(String definition) {
        if (definition == null) {
            return 0f;
        }
        for (String token : definition.replace("'", "").trim().split("\\s+")) {
            if (isLength(token)) {
                float value = PdfBoxUnitConverter.toPoint(token, 0f);
                if (value > 0f) {
                    return value;
                }
            }
        }
        return 0f;
    }

    private boolean isLength(String token) {
        String lower = token.toLowerCase(java.util.Locale.ROOT);
        if (lower.endsWith("px") || lower.endsWith("pt") || lower.endsWith("in")
                || lower.endsWith("cm") || lower.endsWith("mm")) {
            return true;
        }
        try {
            return Float.parseFloat(lower) >= 0f;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private String first(JStyleAttributes attributes, String preferred, String fallback) {
        if (attributes == null) {
            return null;
        }
        String value = attributes.get(preferred);
        return value != null ? value : attributes.get(fallback);
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
}

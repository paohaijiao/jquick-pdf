package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

import java.io.IOException;

/** Text renderer backed by the shared PDFBox style adapter. */
@Data
public class JQuickTextElementRender implements JQuickElementRender {

    private String content;
    private JStyleAttributes style;
    private float x;
    private float y;
    private float fontSize;
    private PDFont font;
    private PDColor color;
    private float characterSpacing;
    private float wordSpacing;
    private boolean useContextPosition = true;
    /**
     * 是否为块级文本。块级文本（{@code <p>}、{@code <h1>}~{@code <h6>}）在排版时应用自身的上下外边距，
     * 与 HTML 一致；行内文本（{@code <span>}）与容器直接承载的文本忽略外边距，避免与容器的外边距重复计算。
     */
    private boolean blockLevel;

    public JQuickTextElementRender(String content) {
        this.content = content;
    }

    public JQuickTextElementRender(String content, JStyleAttributes style) {
        this.content = content;
        this.style = style;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) return;
        String text = content == null ? "" : JStringUtils.trim(content);
        if (text == null || text.isEmpty()) return;

        PdfBoxStyleModel model = PdfBoxStyleModel.from(style);
        if (fontSize > 0f) model.setFontSize(fontSize);
        if (characterSpacing != 0f) model.setCharacterSpacing(characterSpacing);
        if (wordSpacing != 0f) model.setWordSpacing(wordSpacing);
        if (color != null) stream.setNonStrokingColor(color);

        float drawX = useContextPosition ? context.getCursorX() : (x > 0 ? x : context.getX());
        // 光标代表“行的顶部”，而 PDF 文本按基线定位，因此绘制时下移一个 ascent，
        // 否则文字会整体高出所在容器（div 背景、表格单元格等）。
        // 块级文本先让出自身的外边距，使相邻块之间保持声明好的间距而不是贴在一起。
        float marginTop = blockLevel ? model.getMarginTop() : 0f;
        float marginBottom = blockLevel ? model.getMarginBottom() : 0f;
        float lineTop = (useContextPosition ? context.getCursorY() : (y > 0 ? y : context.getY())) - marginTop;
        float textSize = model.getFontSize();
        // 行高按元素自身字号计算，避免大字号标题与小字号正文共用同一行距而重叠。
        float lineHeight = textSize > 0f
                ? textSize * 1.5f
                : (context.getLineHeight() > 0 ? context.getLineHeight() : 18f);
        float availableWidth = model.getWidth() > 0 ? model.getWidth() : context.getWidth();
        PDFont effectiveFont = PdfBoxRenderAdapter.resolveFont(model, font == null ? context.getFont() : font);
        float ascent = PdfBoxRenderAdapter.ascent(effectiveFont, textSize);
        for (String rawLine : text.split("\\n", -1)) {
            for (String line : PdfBoxRenderAdapter.wrapText(effectiveFont, rawLine, model.getFontSize(),
                    availableWidth, model.getCharacterSpacing(), model.getWordSpacing())) {
                if (!line.isEmpty()) {
                    PdfBoxRenderAdapter.drawText(stream, model, effectiveFont, line, drawX, lineTop - ascent, availableWidth);
                }
                lineTop -= lineHeight;
            }
        }
        context.setCursorY(lineTop - marginBottom);
    }
}

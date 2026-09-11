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
        float drawY = useContextPosition ? context.getCursorY() : (y > 0 ? y : context.getY());
        float lineHeight = context.getLineHeight() > 0 ? context.getLineHeight() : model.getFontSize() * 1.5f;
        float availableWidth = model.getWidth() > 0 ? model.getWidth() : context.getWidth();
        for (String line : text.split("\\n", -1)) {
            if (!line.isEmpty()) {
                PdfBoxRenderAdapter.drawText(stream, model, font == null ? context.getFont() : font,
                        line, drawX, drawY, availableWidth);
            }
            drawY -= lineHeight;
        }
        context.setCursorY(drawY);
    }
}

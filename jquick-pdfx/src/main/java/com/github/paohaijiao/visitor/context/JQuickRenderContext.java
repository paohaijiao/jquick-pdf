package com.github.paohaijiao.visitor.context;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import lombok.Builder;
import lombok.Data;

/**
 * 渲染上下文
 */
@Data
@Builder
public class JQuickRenderContext {

    private float x;

    private float y;

    private float width;

    private float height;

    private PDFont font;

    private float fontSize;

    private PDColor color;

    private PDColor backgroundColor;

    private float characterSpacing;

    private float wordSpacing;

    private float lineHeight;

    private float pageWidth;

    private float pageHeight;

    private float[] margins;

    private TextAlign textAlign;

    private VerticalAlign verticalAlign;

    // 当前光标位置（用于流式布局）
    private float cursorX;

    private float cursorY;

    // 分页信息
    private int pageNumber;

    private boolean isNewPage;

    public enum TextAlign {
        LEFT, CENTER, RIGHT, JUSTIFY
    }

    public enum VerticalAlign {
        TOP, MIDDLE, BOTTOM
    }
}

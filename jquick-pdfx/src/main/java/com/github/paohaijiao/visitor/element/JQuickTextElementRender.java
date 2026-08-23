package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.color.JColorEnums;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.util.JStringUtils;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

import java.io.IOException;

/**
 * 简单的文本对象（PDFBox版本）
 */
public class JQuickTextElementRender implements JQuickElementRender {

    private String content;

    private JStyleAttributes style;

    private float x;

    private float y;

    private float fontSize;

    private PDFont font;

    private PDColor color;

    private float characterSpacing = 0f;

    private float wordSpacing = 0f;

    private boolean useContextPosition = true;


    public JQuickTextElementRender(String content) {
        this.content = content;
    }

    public JQuickTextElementRender(String content, JStyleAttributes style) {
        this.content = content;
        this.style = style;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JStyleAttributes getStyle() {
        return style;
    }

    public void setStyle(JStyleAttributes style) {
        this.style = style;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public PDFont getFont() {
        return font;
    }

    public void setFont(PDFont font) {
        this.font = font;
    }

    public PDColor getColor() {
        return color;
    }

    public void setColor(PDColor color) {
        this.color = color;
    }

    public float getCharacterSpacing() {
        return characterSpacing;
    }

    public void setCharacterSpacing(float characterSpacing) {
        this.characterSpacing = characterSpacing;
    }

    public float getWordSpacing() {
        return wordSpacing;
    }

    public void setWordSpacing(float wordSpacing) {
        this.wordSpacing = wordSpacing;
    }

    @Override
    public void  draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null) {
            return;
        }

        String text = getEffectiveContent();
        if (text == null || text.isEmpty()) {
            return;
        }

        // 3. 处理换行符（分割多行）
        String[] lines = text.split("\n", -1);
        if (lines.length > 1) {
            float currentY = getEffectiveY(context);
            for (String line : lines) {
                if (!line.isEmpty()) {
                    drawSingleLine(stream, context, line, currentY);
                }
                currentY -= getEffectiveLineHeight(context);
            }
            context.setCursorY(currentY);
            return;
        }

        float drawY = getEffectiveY(context);
        drawSingleLine(stream, context, text, drawY);
        context.setCursorY(drawY - getEffectiveLineHeight(context));

    }
    /**
     * 绘制单行文本
     */
    private void drawSingleLine(PDPageContentStream stream, JQuickRenderContext context, String text, float y) throws IOException {
        if (text == null || text.isEmpty()) {
            return;
        }
        PDFont effectiveFont = getEffectiveFont(context);
        float effectiveFontSize = getEffectiveFontSize(context);
        PDColor effectiveColor = getEffectiveColor(context);
        float effectiveCharSpacing = getEffectiveCharacterSpacing(context);
        float effectiveWordSpacing = getEffectiveWordSpacing(context);
        float textWidth = calculateTextWidth(effectiveFont, text, effectiveFontSize);

        // 计算绘制位置（考虑对齐方式）
        float x = calculateX(context, textWidth);

        // 开始绘制文本
        stream.beginText();
        stream.setFont(effectiveFont, effectiveFontSize);

        // 设置颜色
        if (effectiveColor != null) {
            stream.setNonStrokingColor(effectiveColor);
        }

        // 设置字符间距
        if (effectiveCharSpacing > 0) {
            stream.setCharacterSpacing(effectiveCharSpacing);
        }

        // 设置单词间距
        if (effectiveWordSpacing > 0) {
            stream.setWordSpacing(effectiveWordSpacing);
        }

        // 设置位置并绘制
        stream.newLineAtOffset(x, y);
        stream.showText(text);
        stream.endText();
    }
    /**
     * 获取有效的文本内容
     */
    private String getEffectiveContent() {
        if (content == null) {
            return "";
        }
        return JStringUtils.trim(content);
    }
    /**
     * 获取有效的字体
     */
    private PDFont getEffectiveFont(JQuickRenderContext context) {
        if (font != null) {
            return font;
        }
        if (style != null && style.get("font-family") != null) {

        }
        return context.getFont();
    }
    /**
     * 获取有效的字体大小
     */
    private float getEffectiveFontSize(JQuickRenderContext context) {
        if (fontSize > 0) {
            return fontSize;
        }
        if (style != null && style.get("font-size") != null) {
            try {
                return Float.parseFloat(style.get("font-size").toString());
            } catch (NumberFormatException e) {
            }
        }
        return context.getFontSize() > 0 ? context.getFontSize() : 12f;
    }
    /**
     * 获取有效的颜色
     */
    private PDColor getEffectiveColor(JQuickRenderContext context) {
        if (color != null) {
            return color;
        }
        if (style != null && style.get("color") != null) {
            return JColorEnums.colorOf(style.get("color").toString());
        }
        return context.getColor();
    }
    /**
     * 获取有效的字符间距
     */
    private float getEffectiveCharacterSpacing(JQuickRenderContext context) {
        if (characterSpacing > 0) {
            return characterSpacing;
        }
        if (style != null && style.get("letter-spacing") != null) {
            try {
                return Float.parseFloat(style.get("letter-spacing").toString());
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return context.getCharacterSpacing();
    }

    /**
     * 获取有效的单词间距
     */
    private float getEffectiveWordSpacing(JQuickRenderContext context) {
        if (wordSpacing > 0) {
            return wordSpacing;
        }
        if (style != null && style.get("word-spacing") != null) {
            try {
                return Float.parseFloat(style.get("word-spacing").toString());
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return context.getWordSpacing();
    }

    /**
     * 获取有效的X坐标
     */
    private float getEffectiveX(JQuickRenderContext context) {
        if (!useContextPosition) {
            return x > 0 ? x : context.getX();
        }
        return context.getCursorX() > 0 ? context.getCursorX() : context.getX();
    }

    /**
     * 获取有效的Y坐标
     */
    private float getEffectiveY(JQuickRenderContext context) {
        if (!useContextPosition) {
            return y > 0 ? y : context.getY();
        }
        return context.getCursorY() > 0 ? context.getCursorY() : context.getY();
    }

    /**
     * 获取有效的行高
     */
    private float getEffectiveLineHeight(JQuickRenderContext context) {
        if (style != null && style.get("line-height") != null) {
            try {
                return Float.parseFloat(style.get("line-height").toString());
            } catch (NumberFormatException e) {
            }
        }
        float fontSize = getEffectiveFontSize(context);
        return context.getLineHeight() > 0 ? context.getLineHeight() : fontSize * 1.5f;
    }

    /**
     * 计算X坐标（考虑对齐方式）
     */
    private float calculateX(JQuickRenderContext context, float textWidth) {
        float baseX = getEffectiveX(context);
        JQuickRenderContext.TextAlign align = context.getTextAlign();

        if (align == null) {
            align = JQuickRenderContext.TextAlign.LEFT;
        }

        switch (align) {
            case CENTER:
                return baseX + (context.getWidth() - textWidth) / 2;
            case RIGHT:
                return baseX + context.getWidth() - textWidth;
            case JUSTIFY:
                // 两端对齐需要特殊处理，暂时返回左对齐
                return baseX;
            case LEFT:
            default:
                return baseX;
        }
    }

    /**
     * 计算文本宽度
     */
    private float calculateTextWidth(PDFont font, String text, float fontSize) throws IOException {
        if (font == null || text == null || text.isEmpty()) {
            return 0;
        }
        try {
            return font.getStringWidth(text) / 1000f * fontSize;
        } catch (IOException e) {
            // 如果计算失败，使用估算值
            return text.length() * fontSize * 0.5f;
        }
    }
}
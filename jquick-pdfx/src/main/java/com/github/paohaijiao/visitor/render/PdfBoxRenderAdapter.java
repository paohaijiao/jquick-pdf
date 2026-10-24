package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.enums.JFontEnum;
import com.github.paohaijiao.font.JFontRegistry;
import com.github.paohaijiao.font.JFontSpec;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

/** PDFBox drawing adapter for the public jquick-pdf style contract. */
public final class PdfBoxRenderAdapter {

    /** 按文档缓存的已解析自定义字体，避免同一段落/单元格重复加载字体文件。 */
    private static final Map<PDDocument, Map<String, PDFont>> DOCUMENT_FONTS =
            Collections.synchronizedMap(new WeakHashMap<PDDocument, Map<String, PDFont>>());

    private PdfBoxRenderAdapter() {
    }

    public static void drawBox(PDDocument document, PDPageContentStream stream, PdfBoxStyleModel style,
                               float x, float top, float width, float height) throws IOException {
        float drawX = x + style.getRelativeLeft() - style.getRelativeRight();
        float drawY = top - height - style.getRelativeTop() + style.getRelativeBottom();
        stream.saveGraphicsState();
        applyOpacity(stream, style.getOpacity());
        if (style.getAngleInRadians() != 0f) {
            stream.transform(Matrix.getRotateInstance(style.getAngleInRadians(), drawX + width / 2f, drawY + height / 2f));
        }
        if (style.getBackgroundColor() != null) {
            stream.setNonStrokingColor(color(style.getBackgroundColor(), white()));
            roundRect(stream, drawX, drawY, width, height, radius(style));
            stream.fill();
        }
        drawBorder(stream, style, drawX, drawY, width, height);
        stream.restoreGraphicsState();
    }

    public static void drawBackgroundImage(PDDocument document, PDPageContentStream stream, PdfBoxStyleModel style,
                                           float x, float y, float width, float height) throws IOException {
        if (style.getBackgroundImage() == null || style.getBackgroundImage().trim().isEmpty()) {
            return;
        }
        try {
            PDImageXObject image = PDImageXObject.createFromFileByContent(new File(style.getBackgroundImage()), document);
            stream.saveGraphicsState();
            applyOpacity(stream, style.getOpacity());
            stream.drawImage(image, x, y, width, height);
            stream.restoreGraphicsState();
        } catch (IOException ignored) {
            // Missing or unsupported background images intentionally fall back to the color background.
        }
    }

    public static void drawText(PDPageContentStream stream, PdfBoxStyleModel style, PDFont fallbackFont,
                                String text, float x, float baseline, float availableWidth) throws IOException {
        drawText(stream, style, java.util.Collections.singletonList(fallbackFont), text, x, baseline, availableWidth);
    }

    /**
     * 回退链绘制：先做 RTL 连写与视觉重排（声明 {@code baseDirection:rtl} 时），
     * 再按「逐字符第一个能编码它的字体」把文本切成若干段，各段用各自的字体顺序绘制。
     * 同一元素内可以混排任意语言（如 Noto Sans Arabic 段落里的英文、中文），
     * 不再因单一字体缺字形而输出 '?' 或空格。
     */
    public static void drawText(PDPageContentStream stream, PdfBoxStyleModel style, java.util.List<PDFont> chain,
                                String text, float x, float baseline, float availableWidth) throws IOException {
        if (text == null || text.isEmpty() || chain == null || chain.isEmpty()) {
            return;
        }
        String safeText = sanitizeFontText(chain, text);
        if (safeText.isEmpty()) {
            return;
        }
        if ("rtl".equalsIgnoreCase(style.getBaseDirection())) {
            safeText = JArabicShaping.reorderRtl(JArabicShaping.shape(safeText));
        }
        float fontSize = style.getFontSize();
        float characterSpacing = style.getCharacterSpacing();
        float wordSpacing = style.getWordSpacing();
        java.util.List<TextRun> runs = segment(chain, safeText);
        float textWidth = 0f;
        for (TextRun run : runs) {
            textWidth += measure(run.font, run.text, fontSize, characterSpacing, wordSpacing);
        }
        float drawX = x + style.getRelativeLeft() - style.getRelativeRight();
        if ("center".equalsIgnoreCase(style.getTextAlignment())) {
            drawX += Math.max(0f, (availableWidth - textWidth) / 2f);
        } else if ("right".equalsIgnoreCase(style.getTextAlignment())) {
            drawX += Math.max(0f, availableWidth - textWidth);
        }
        stream.saveGraphicsState();
        applyOpacity(stream, style.getOpacity());
        stream.setNonStrokingColor(color(style.getFontColor(), black()));
        stream.setRenderingMode(renderingMode(style.getTextRenderingMode()));
        if (style.getStrokeColor() != null) {
            stream.setStrokingColor(color(style.getStrokeColor(), black()));
            stream.setLineWidth(style.getStrokeWidth());
        }
        float cursor = drawX;
        for (TextRun run : runs) {
            float runWidth = measure(run.font, run.text, fontSize, characterSpacing, wordSpacing);
            stream.beginText();
            stream.setFont(run.font, fontSize);
            stream.setCharacterSpacing(characterSpacing);
            stream.setWordSpacing(wordSpacing);
            if (style.getAngleInRadians() != 0f) {
                stream.setTextMatrix(Matrix.getRotateInstance(style.getAngleInRadians(), cursor, baseline));
            } else {
                stream.setTextMatrix(Matrix.getTranslateInstance(cursor, baseline));
            }
            stream.showText(run.text);
            stream.endText();
            cursor += runWidth;
        }
        if (style.isUnderline() || style.isLineThrough()) {
            stream.setStrokingColor(color(style.getFontColor(), black()));
            stream.setLineWidth(Math.max(0.5f, fontSize / 18f));
            if (style.isUnderline()) line(stream, drawX, baseline - fontSize / 8f, drawX + textWidth, baseline - fontSize / 8f);
            if (style.isLineThrough()) line(stream, drawX, baseline + fontSize / 3f, drawX + textWidth, baseline + fontSize / 3f);
        }
        stream.restoreGraphicsState();
    }

    /** 同字体连续文本段：每段在绘制时切换到自己的字体。 */
    private static final class TextRun {
        private final PDFont font;
        private final String text;

        private TextRun(PDFont font, String text) {
            this.font = font;
            this.text = text;
        }
    }

    /** 逐字符挑第一个能编码它的字体，把文本切成连续同字体的段。 */
    private static java.util.List<TextRun> segment(java.util.List<PDFont> chain, String text) {
        java.util.List<TextRun> runs = new java.util.ArrayList<TextRun>();
        PDFont currentFont = null;
        StringBuilder current = new StringBuilder();
        int index = 0;
        while (index < text.length()) {
            int codePoint = text.codePointAt(index);
            int charCount = Character.charCount(codePoint);
            String piece = text.substring(index, index + charCount);
            PDFont font = fontFor(chain, piece);
            if (font != currentFont) {
                if (current.length() > 0) {
                    runs.add(new TextRun(currentFont, current.toString()));
                    current.setLength(0);
                }
                currentFont = font;
            }
            current.append(piece);
            index += charCount;
        }
        if (current.length() > 0) {
            runs.add(new TextRun(currentFont, current.toString()));
        }
        return runs;
    }

    /** 回退链中第一个能编码该文本的字体；全部缺失时返回链首（字符已被清洗替换）。 */
    private static PDFont fontFor(java.util.List<PDFont> chain, String text) {
        for (PDFont font : chain) {
            if (encodable(font, text)) {
                return font;
            }
        }
        return chain.get(0);
    }

    public static float textWidth(PDFont font, String text, float fontSize, float characterSpacing, float wordSpacing) throws IOException {
        java.util.List<PDFont> chain = font == null
                ? new java.util.ArrayList<PDFont>()
                : java.util.Collections.singletonList(font);
        return textWidth(chain, text, fontSize, characterSpacing, wordSpacing);
    }

    /** 回退链宽度：逐字符用各自命中的字体测量，混排文本不再因主字体缺字形而估宽失真。 */
    public static float textWidth(java.util.List<PDFont> chain, String text, float fontSize,
                                  float characterSpacing, float wordSpacing) {
        if (chain == null || chain.isEmpty() || text == null || text.isEmpty()) {
            return 0f;
        }
        String safe = sanitizeFontText(chain, text);
        float total = 0f;
        int index = 0;
        while (index < safe.length()) {
            int codePoint = safe.codePointAt(index);
            int charCount = Character.charCount(codePoint);
            String piece = safe.substring(index, index + charCount);
            total += width(fontFor(chain, piece), piece, fontSize, 0f, 0f);
            index += charCount;
        }
        int spaces = 0;
        for (int i = 0; i < safe.length(); i++) if (safe.charAt(i) == ' ') spaces++;
        return total + Math.max(0, safe.length() - 1) * characterSpacing + spaces * wordSpacing;
    }

    /**
     * 返回字体在指定字号下的上坡度（ascent）。流式布局中光标代表“行的顶部”，
     * 因此绘制文本时需要把基线从行顶下移一个 ascent，否则文字会整体高出容器。
     */
    public static float ascent(PDFont font, float fontSize) {
        if (font == null || fontSize <= 0f) {
            return fontSize > 0f ? fontSize * 0.8f : 0f;
        }
        try {
            if (font.getFontDescriptor() != null) {
                float ascent = font.getFontDescriptor().getAscent();
                if (ascent > 0f) {
                    return ascent / 1000f * fontSize;
                }
            }
        } catch (Exception ignored) {
            // 字体缺少描述信息时退回经验值，保证文字始终落在容器内。
        }
        return fontSize * 0.8f;
    }

    /**
     * 过滤字体不支持的字符：将无法编码的码点替换为等价的可显示写法，避免
     * {@code showText}/{@code getStringWidth} 抛出异常导致整个渲染中断。
     * 例如内置 SimHei 子集缺少 © (U+00A9) 等符号，替换为 (C) 比输出 '?' 更接近原文。
     */
    public static String sanitizeFontText(PDFont font, String text) {
        java.util.List<PDFont> chain = font == null
                ? new java.util.ArrayList<PDFont>()
                : java.util.Collections.singletonList(font);
        return sanitizeFontText(chain, text);
    }

    /**
     * 过滤回退链内所有字体都无法编码的码点：任一字体能编码的字符全部保留，
     * 无法编码的替换为等价可显示写法（? -> 空格 -> 丢弃逐级降级），
     * 避免 {@code showText}/{@code getStringWidth} 抛异常导致渲染中断。
     */
    public static String sanitizeFontText(java.util.List<PDFont> chain, String text) {
        if (chain == null || chain.isEmpty() || text == null || text.isEmpty()) {
            return text;
        }
        StringBuilder builder = null;
        int index = 0;
        while (index < text.length()) {
            int codePoint = text.codePointAt(index);
            int charCount = Character.charCount(codePoint);
            String piece = text.substring(index, index + charCount);
            if (!encodableInChain(chain, piece)) {
                if (builder == null) {
                    builder = new StringBuilder(text.length());
                    builder.append(text, 0, index);
                }
                String replacement = symbolFallback(codePoint);
                if (!encodableInChain(chain, replacement)) {
                    replacement = " ";
                    if (!encodableInChain(chain, replacement)) {
                        replacement = "";
                    }
                }
                builder.append(replacement);
            } else if (builder != null) {
                builder.append(piece);
            }
            index += charCount;
        }
        return builder == null ? text : builder.toString();
    }

    private static boolean encodableInChain(java.util.List<PDFont> chain, String text) {
        for (PDFont font : chain) {
            if (encodable(font, text)) {
                return true;
            }
        }
        return false;
    }

    private static boolean encodable(PDFont font, String text) {
        try {
            font.getStringWidth(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** 字体缺失符号时的等价写法，避免直接输出 '?'。 */
    private static String symbolFallback(int codePoint) {
        switch (codePoint) {
            case 0x00A9:
                return "(C)";
            case 0x00AE:
                return "(R)";
            case 0x2122:
                return "(TM)";
            case 0x2022:
            case 0x00B7:
                return "-";
            case 0x2013:
            case 0x2014:
                return "-";
            case 0x2026:
                return "...";
            case 0x00A0:
                return " ";
            default:
                return "?";
        }
    }

    private static float measure(PDFont font, String text, float fontSize, float characterSpacing, float wordSpacing) throws IOException {
        float width = font.getStringWidth(text) / 1000f * fontSize;
        int spaces = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == ' ') spaces++;
        return width + Math.max(0, text.length() - 1) * characterSpacing + spaces * wordSpacing;
    }

    /**
     * 按可用宽度将文本折行（中文逐字断开，英文回退到字符边界），
     * 避免长文本单行溢出页面。返回结果至少包含一个元素。
     */
    public static java.util.List<String> wrapText(PDFont font, String text, float fontSize, float maxWidth,
                                                  float characterSpacing, float wordSpacing) {
        java.util.List<PDFont> chain = font == null
                ? new java.util.ArrayList<PDFont>()
                : java.util.Collections.singletonList(font);
        return wrapText(chain, text, fontSize, maxWidth, characterSpacing, wordSpacing);
    }

    /** 回退链折行：逐字符按命中字体的真实宽度测量，支持任意语言混排的换行。 */
    public static java.util.List<String> wrapText(java.util.List<PDFont> chain, String text, float fontSize,
                                                  float maxWidth, float characterSpacing, float wordSpacing) {
        java.util.List<String> lines = new java.util.ArrayList<String>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }
        if (chain == null || chain.isEmpty() || maxWidth <= 0f) {
            lines.add(text);
            return lines;
        }
        StringBuilder current = new StringBuilder();
        int index = 0;
        while (index < text.length()) {
            int codePoint = text.codePointAt(index);
            int charCount = Character.charCount(codePoint);
            String piece = text.substring(index, index + charCount);
            current.append(piece);
            if (current.length() > charCount
                    && textWidth(chain, current.toString(), fontSize, characterSpacing, wordSpacing) > maxWidth) {
                current.setLength(current.length() - charCount);
                lines.add(current.toString());
                current.setLength(0);
                current.append(piece);
            }
            index += charCount;
        }
        if (current.length() > 0) {
            lines.add(current.toString());
        }
        if (lines.isEmpty()) {
            lines.add("");
        }
        return lines;
    }

    private static float width(PDFont font, String text, float fontSize, float characterSpacing, float wordSpacing) {
        try {
            return measure(font, text, fontSize, characterSpacing, wordSpacing);
        } catch (Exception e) {
            return text.length() * fontSize * 0.6f;
        }
    }

    private static void drawBorder(PDPageContentStream stream, PdfBoxStyleModel style,
                                   float x, float y, float width, float height) throws IOException {
        drawBorderSide(stream, first(style.getBorderTop(), style.getBorder()), x, y + height, x + width, y + height, style);
        drawBorderSide(stream, first(style.getBorderRight(), style.getBorder()), x + width, y, x + width, y + height, style);
        drawBorderSide(stream, first(style.getBorderBottom(), style.getBorder()), x, y, x + width, y, style);
        drawBorderSide(stream, first(style.getBorderLeft(), style.getBorder()), x, y, x, y + height, style);
    }

    private static void drawBorderSide(PDPageContentStream stream, String definition, float x1, float y1,
                                       float x2, float y2, PdfBoxStyleModel style) throws IOException {
        if (definition == null || definition.trim().isEmpty()) return;
        // CSS 声明顺序为 “线宽 线型 颜色”（如 1px solid #ccc），此处按语义识别各 token，
        // 不依赖固定位置，避免把线宽当成线型、把线型当成线宽。
        String type = "solid";
        float width = -1f;
        String color = null;
        for (String token : definition.replace("'", "").trim().split("\\s+")) {
            String value = token.trim();
            if (value.isEmpty()) continue;
            String lower = value.toLowerCase(Locale.ROOT);
            if (isBorderWidth(lower)) {
                width = PdfBoxUnitConverter.toPoint(value, style.getStrokeWidth());
            } else if ("none".equals(lower) || "solid".equals(lower) || lower.contains("dashed") || lower.contains("dotted")) {
                type = lower;
            } else {
                color = value;
            }
        }
        if ("none".equals(type)) return;
        if (width < 0f) width = style.getStrokeWidth();
        if (color == null) color = style.getStrokeColor();
        stream.setLineWidth(width);
        stream.setStrokingColor(color(color, black()));
        if ("dashed".equals(type) || "dasheborder".equals(type) || "fixeddashedborder".equals(type)) {
            stream.setLineDashPattern(new float[]{width * 3f, width * 2f}, 0);
        } else if ("dotted".equals(type) || "dottedborder".equals(type) || "rounddotsborder".equals(type)) {
            stream.setLineDashPattern(new float[]{width, width * 1.5f}, 0);
        }
        line(stream, x1, y1, x2, y2);
        stream.setLineDashPattern(new float[]{}, 0);
    }

    /** 判断 token 是否为边框线宽（如 {@code 1px}、{@code 0.5}）。 */
    private static boolean isBorderWidth(String token) {
        if (token.endsWith("px") || token.endsWith("pt") || token.endsWith("in")
                || token.endsWith("cm") || token.endsWith("mm")) {
            return true;
        }
        try {
            return Float.parseFloat(token) >= 0f;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private static void roundRect(PDPageContentStream stream, float x, float y, float width, float height, float radius) throws IOException {
        radius = Math.max(0f, Math.min(radius, Math.min(width, height) / 2f));
        if (radius == 0f) {
            stream.addRect(x, y, width, height);
            return;
        }
        float c = radius * 0.55228475f;
        stream.moveTo(x + radius, y);
        stream.lineTo(x + width - radius, y);
        stream.curveTo(x + width - radius + c, y, x + width, y + radius - c, x + width, y + radius);
        stream.lineTo(x + width, y + height - radius);
        stream.curveTo(x + width, y + height - radius + c, x + width - radius + c, y + height, x + width - radius, y + height);
        stream.lineTo(x + radius, y + height);
        stream.curveTo(x + radius - c, y + height, x, y + height - radius + c, x, y + height - radius);
        stream.lineTo(x, y + radius);
        stream.curveTo(x, y + radius - c, x + radius - c, y, x + radius, y);
        stream.closePath();
    }

    public static PDFont resolveFont(PdfBoxStyleModel style, PDFont fallback) {
        return resolveFont(style, fallback, null);
    }

    public static PDFont resolveFont(PdfBoxStyleModel style, PDFont fallback, PDDocument document) {
        return resolveFontChain(style, fallback, document).get(0);
    }

    /**
     * 按 fontFamilyNames 回退链解析候选字体列表：链中每个名称先命中 PDF 标准 14 字体，
     * 再查找已注册的外部字体家族（config.fontConfig().registerFont），最后追加调用方
     * 默认字体兜底。绘制时逐字符在链内挑第一个能编码该字符的字体。
     */
    public static java.util.List<PDFont> resolveFontChain(PdfBoxStyleModel style, PDFont fallback, PDDocument document) {
        java.util.List<PDFont> chain = new java.util.ArrayList<PDFont>();
        String names = style.getFont() == null ? style.getFontFamilyNames() : style.getFont();
        if (names != null) {
            for (String token : names.split(",")) {
                PDFont font = resolveToken(token.trim(), style, document);
                if (font != null && !containsFont(chain, font)) {
                    chain.add(font);
                }
            }
        }
        if (fallback != null && !containsFont(chain, fallback)) {
            chain.add(fallback);
        }
        if (chain.isEmpty()) {
            chain.add(new PDType1Font(Standard14Fonts.FontName.HELVETICA));
        }
        return chain;
    }

    private static boolean containsFont(java.util.List<PDFont> chain, PDFont font) {
        for (PDFont candidate : chain) {
            if (candidate.getName().equals(font.getName())) {
                return true;
            }
        }
        return false;
    }

    /** 解析回退链中的单个家族名：标准 14 字体或注册表中的外部字体。 */
    private static PDFont resolveToken(String name, PdfBoxStyleModel style, PDDocument document) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        String value = name.toUpperCase(Locale.ROOT).replace('-', '_');
        if (style.isBold()) value += "_BOLD";
        if (style.isItalic()) value += style.isBold() ? "ITALIC" : "_OBLIQUE";
        try {
            JFontEnum font = JFontEnum.valueOf(value);
            return new PDType1Font(Standard14Fonts.FontName.valueOf(font.getFontName().toUpperCase(Locale.ROOT).replace('-', '_')));
        } catch (IllegalArgumentException ignored) {
            // 非 PDF 标准 14 字体名，继续走注册表。
        }
        JFontSpec spec = JFontRegistry.find(name);
        if (spec != null && document != null) {
            return resolveRegistered(spec, document);
        }
        return null;
    }

    private static PDFont resolveRegistered(JFontSpec spec, PDDocument document) {
        Map<String, PDFont> fonts = DOCUMENT_FONTS.get(document);
        if (fonts == null) {
            fonts = new HashMap<String, PDFont>();
            DOCUMENT_FONTS.put(document, fonts);
        }
        synchronized (fonts) {
            PDFont cached = fonts.get(spec.getName());
            if (cached != null) {
                return cached;
            }
            try {
                PDFont loaded = spec.resolve(document);
                fonts.put(spec.getName(), loaded);
                return loaded;
            } catch (IOException e) {
                return null;
            }
        }
    }

    private static RenderingMode renderingMode(String value) {
        try {
            return value == null ? RenderingMode.FILL : RenderingMode.fromInt(Integer.parseInt(value));
        } catch (RuntimeException ignored) {
            return RenderingMode.FILL;
        }
    }

    public static PDColor color(String value) {
        return color(value, null);
    }

    public static PDColor color(String value, PDColor fallback) {
        if (value == null || value.trim().isEmpty()) return fallback;
        try {
            String normalized = value.trim();
            if (normalized.startsWith("#")) return hexColor(normalized);
            String lower = normalized.toLowerCase(Locale.ROOT);
            if (lower.startsWith("rgb(") || lower.startsWith("rgba(")) {
                PDColor functional = functionalColor(normalized, fallback);
                return functional == null ? fallback : functional;
            }
            if (lower.startsWith("linear-gradient(") || lower.startsWith("radial-gradient(")) {
                PDColor gradient = gradientColor(normalized);
                return gradient == null ? fallback : gradient;
            }
            PDColor named = namedColor(lower);
            return named == null ? fallback : named;
        } catch (RuntimeException ignored) {
            return fallback;
        }
    }

    /**
     * 解析 {@code rgb(r,g,b)} / {@code rgba(r,g,b,a)}：支持整数与百分比通道，
     * alpha 通道暂不参与合成（保持完全不透明），避免深色背景上的文字退化为默认黑色。
     */
    private static PDColor functionalColor(String value, PDColor fallback) {
        int open = value.indexOf('(');
        int close = value.lastIndexOf(')');
        if (open < 0 || close <= open) return fallback;
        String[] parts = value.substring(open + 1, close).split(",");
        if (parts.length < 3) return fallback;
        int red = channel(parts[0]);
        int green = channel(parts[1]);
        int blue = channel(parts[2]);
        if (red < 0 || green < 0 || blue < 0) return fallback;
        return rgb(red, green, blue);
    }

    /**
     * 渐变的降级处理：取各颜色停靠点的平均值作为纯色填充。
     * PDF 端未实现真正的轴向渐变，但可避免整个背景退化为默认白色。
     */
    private static PDColor gradientColor(String value) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("#[0-9a-fA-F]{3,6}|rgba?\\([^)]*\\)").matcher(value);
        float red = 0f;
        float green = 0f;
        float blue = 0f;
        int size = 0;
        while (matcher.find()) {
            PDColor stop = color(matcher.group(), null);
            if (stop == null) continue;
            float[] components = stop.getComponents();
            red += components[0];
            green += components[1];
            blue += components[2];
            size++;
        }
        if (size == 0) return null;
        return rgb(Math.round(red / size * 255f), Math.round(green / size * 255f), Math.round(blue / size * 255f));
    }

    private static int channel(String value) {
        try {
            String text = value.trim();
            if (text.endsWith("%")) {
                return Math.round(Float.parseFloat(text.substring(0, text.length() - 1).trim()) * 255f / 100f);
            }
            return Math.round(Float.parseFloat(text));
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }

    private static float radius(PdfBoxStyleModel style) {
        String value = style.getBorderRadius();
        if (value == null) value = first(style.getBorderTopLeftRadius(), first(style.getBorderTopRightRadius(), first(style.getBorderBottomLeftRadius(), style.getBorderBottomRightRadius())));
        return PdfBoxUnitConverter.toPoints(value, 1, 0f)[0];
    }

    private static void applyOpacity(PDPageContentStream stream, float opacity) throws IOException {
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        state.setNonStrokingAlphaConstant(opacity);
        state.setStrokingAlphaConstant(opacity);
        stream.setGraphicsStateParameters(state);
    }

    private static void line(PDPageContentStream stream, float x1, float y1, float x2, float y2) throws IOException {
        stream.moveTo(x1, y1);
        stream.lineTo(x2, y2);
        stream.stroke();
    }

    private static String first(String preferred, String fallback) { return preferred == null ? fallback : preferred; }
    private static PDColor namedColor(String value) {
        if ("black".equals(value)) return black();
        if ("white".equals(value)) return white();
        if ("red".equals(value)) return rgb(255, 0, 0);
        if ("green".equals(value) || "lime".equals(value)) return rgb(0, 255, 0);
        if ("blue".equals(value)) return rgb(0, 0, 255);
        if ("yellow".equals(value)) return rgb(255, 255, 0);
        if ("cyan".equals(value) || "aqua".equals(value)) return rgb(0, 255, 255);
        if ("magenta".equals(value) || "fuchsia".equals(value)) return rgb(255, 0, 255);
        if ("gray".equals(value) || "grey".equals(value)) return rgb(128, 128, 128);
        if ("dark_gray".equals(value) || "darkgray".equals(value)) return rgb(64, 64, 64);
        if ("light_gray".equals(value) || "lightgray".equals(value)) return rgb(192, 192, 192);
        if ("orange".equals(value)) return rgb(255, 200, 0);
        if ("pink".equals(value)) return rgb(255, 175, 175);
        if ("purple".equals(value)) return rgb(128, 0, 128);
        if ("brown".equals(value)) return rgb(165, 42, 42);
        if ("maroon".equals(value)) return rgb(128, 0, 0);
        if ("olive".equals(value)) return rgb(128, 128, 0);
        if ("navy".equals(value)) return rgb(0, 0, 128);
        if ("teal".equals(value)) return rgb(0, 128, 128);
        if ("silver".equals(value)) return rgb(192, 192, 192);
        return null;
    }

    private static PDColor hexColor(String value) {
        String hex = value.startsWith("#") ? value.substring(1) : value;
        if (hex.length() == 3) {
            int red = Integer.parseInt(hex.substring(0, 1), 16) * 17;
            int green = Integer.parseInt(hex.substring(1, 2), 16) * 17;
            int blue = Integer.parseInt(hex.substring(2, 3), 16) * 17;
            return rgb(red, green, blue);
        }
        if (hex.length() == 6) {
            int red = Integer.parseInt(hex.substring(0, 2), 16);
            int green = Integer.parseInt(hex.substring(2, 4), 16);
            int blue = Integer.parseInt(hex.substring(4, 6), 16);
            return rgb(red, green, blue);
        }
        return black();
    }

    private static PDColor rgb(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f}, PDDeviceRGB.INSTANCE);
    }

    private static PDColor black() { return new PDColor(new float[]{0f, 0f, 0f}, PDDeviceRGB.INSTANCE); }
    private static PDColor white() { return new PDColor(new float[]{1f, 1f, 1f}, PDDeviceRGB.INSTANCE); }
}

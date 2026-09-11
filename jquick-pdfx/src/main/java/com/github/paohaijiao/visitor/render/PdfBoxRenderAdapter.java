package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.enums.JFontEnum;
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
import java.util.Locale;

/** PDFBox drawing adapter for the public jquick-pdf style contract. */
public final class PdfBoxRenderAdapter {

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
        if (text == null || text.isEmpty()) {
            return;
        }
        PDFont font = resolveFont(style, fallbackFont);
        float fontSize = style.getFontSize();
        float textWidth = textWidth(font, text, fontSize, style.getCharacterSpacing(), style.getWordSpacing());
        float drawX = x + style.getRelativeLeft() - style.getRelativeRight();
        if ("center".equalsIgnoreCase(style.getTextAlignment())) {
            drawX += Math.max(0f, (availableWidth - textWidth) / 2f);
        } else if ("right".equalsIgnoreCase(style.getTextAlignment())) {
            drawX += Math.max(0f, availableWidth - textWidth);
        }
        stream.saveGraphicsState();
        applyOpacity(stream, style.getOpacity());
        stream.beginText();
        stream.setFont(font, fontSize);
        stream.setNonStrokingColor(color(style.getFontColor(), black()));
        stream.setCharacterSpacing(style.getCharacterSpacing());
        stream.setWordSpacing(style.getWordSpacing());
        stream.setRenderingMode(renderingMode(style.getTextRenderingMode()));
        if (style.getStrokeColor() != null) {
            stream.setStrokingColor(color(style.getStrokeColor(), black()));
            stream.setLineWidth(style.getStrokeWidth());
        }
        if (style.getAngleInRadians() != 0f) {
            stream.setTextMatrix(Matrix.getRotateInstance(style.getAngleInRadians(), drawX, baseline));
        } else {
            stream.setTextMatrix(Matrix.getTranslateInstance(drawX, baseline));
        }
        stream.showText(text);
        stream.endText();
        if (style.isUnderline() || style.isLineThrough()) {
            stream.setStrokingColor(color(style.getFontColor(), black()));
            stream.setLineWidth(Math.max(0.5f, fontSize / 18f));
            if (style.isUnderline()) line(stream, drawX, baseline - fontSize / 8f, drawX + textWidth, baseline - fontSize / 8f);
            if (style.isLineThrough()) line(stream, drawX, baseline + fontSize / 3f, drawX + textWidth, baseline + fontSize / 3f);
        }
        stream.restoreGraphicsState();
    }

    public static float textWidth(PDFont font, String text, float fontSize, float characterSpacing, float wordSpacing) throws IOException {
        float width = font.getStringWidth(text) / 1000f * fontSize;
        int spaces = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == ' ') spaces++;
        return width + Math.max(0, text.length() - 1) * characterSpacing + spaces * wordSpacing;
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
        String[] parts = definition.replace("'", "").trim().split("\\s+");
        String type = parts.length > 0 ? parts[0].toLowerCase(Locale.ROOT) : "solid";
        float width = parts.length > 1 ? PdfBoxUnitConverter.toPoint(parts[1], style.getStrokeWidth()) : style.getStrokeWidth();
        String color = parts.length > 2 ? parts[2] : style.getStrokeColor();
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

    private static PDFont resolveFont(PdfBoxStyleModel style, PDFont fallback) {
        String name = style.getFont() == null ? style.getFontFamilyNames() : style.getFont();
        if (name == null) return fallback == null ? new PDType1Font(Standard14Fonts.FontName.HELVETICA) : fallback;
        String value = name.split(",")[0].trim().toUpperCase(Locale.ROOT).replace('-', '_');
        if (style.isBold()) value += "_BOLD";
        if (style.isItalic()) value += style.isBold() ? "ITALIC" : "_OBLIQUE";
        try {
            JFontEnum font = JFontEnum.valueOf(value);
            return new PDType1Font(Standard14Fonts.FontName.valueOf(font.getFontName().toUpperCase(Locale.ROOT).replace('-', '_')));
        } catch (IllegalArgumentException ignored) {
            return fallback == null ? new PDType1Font(Standard14Fonts.FontName.HELVETICA) : fallback;
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
            PDColor named = namedColor(normalized.toLowerCase(Locale.ROOT));
            return named == null ? fallback : named;
        } catch (RuntimeException ignored) {
            return fallback;
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

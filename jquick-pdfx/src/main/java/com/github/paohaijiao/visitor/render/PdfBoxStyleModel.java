package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.model.JStyleAttributes;
import lombok.Data;

import java.util.Locale;

/**
 * Normalized style values consumed by the PDFBox renderer. Public style names
 * remain in JStyleAttributes; this class only resolves shorthand precedence.
 */
@Data
public class PdfBoxStyleModel {

    private float marginTop;
    private float marginRight;
    private float marginBottom;
    private float marginLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private float paddingLeft;
    private float width = -1f;
    private float height = -1f;
    private float minWidth = -1f;
    private float maxWidth = -1f;
    private float minHeight = -1f;
    private float maxHeight = -1f;
    private float angleInRadians;
    private float relativeLeft;
    private float relativeTop;
    private float relativeRight;
    private float relativeBottom;
    private float fontSize = 12f;
    private float characterSpacing;
    private float wordSpacing;
    private float strokeWidth = 1f;
    private float opacity = 1f;
    private float spacingRatio;
    private boolean keepTogether;
    private boolean keepWithNext;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private boolean lineThrough;
    private String font;
    private String fontFamilyNames;
    private String fontColor;
    private String backgroundColor;
    private String backgroundImage;
    private String border;
    private String borderTop;
    private String borderRight;
    private String borderBottom;
    private String borderLeft;
    private String borderRadius;
    private String borderTopLeftRadius;
    private String borderTopRightRadius;
    private String borderBottomRightRadius;
    private String borderBottomLeftRadius;
    private String strokeColor;
    private String textAlignment;
    private String verticalAlignment;
    private String fontKerning;
    private String fontScript;
    private String textRenderingMode;
    private String baseDirection;
    private String destination;
    private String display;
    private String flexDirection;
    private String flexWrap;
    private String justifyContent;
    private String alignItems;

    public static PdfBoxStyleModel from(JStyleAttributes attributes) {
        PdfBoxStyleModel model = new PdfBoxStyleModel();
        if (attributes == null) {
            return model;
        }
        float[] margins = box(attributes.get("commonMargin"), first(attributes, "margins", "margin"));
        model.marginTop = side(attributes, "marginTop", "margin-top", margins[0]);
        model.marginRight = side(attributes, "marginRight", "margin-right", margins[1]);
        model.marginBottom = side(attributes, "marginBottom", "margin-bottom", margins[2]);
        model.marginLeft = side(attributes, "marginLeft", "margin-left", margins[3]);

        float[] paddings = box(attributes.get("commonPadding"), first(attributes, "paddings", "padding"));
        model.paddingTop = side(attributes, "paddingTop", "padding-top", paddings[0]);
        model.paddingRight = side(attributes, "paddingRight", "padding-right", paddings[1]);
        model.paddingBottom = side(attributes, "paddingBottom", "padding-bottom", paddings[2]);
        model.paddingLeft = side(attributes, "paddingLeft", "padding-left", paddings[3]);

        model.width = value(attributes, "width", -1f);
        model.height = value(attributes, "height", -1f);
        model.minWidth = value(attributes, "minWidth", -1f);
        model.maxWidth = value(attributes, "maxWidth", -1f);
        model.minHeight = value(attributes, "minHeight", -1f);
        model.maxHeight = value(attributes, "maxHeight", -1f);
        model.angleInRadians = value(attributes, "angleInRadians", 0f);
        float[] position = PdfBoxUnitConverter.toPoints(attributes.get("relativePosition"), 4, 0f);
        model.relativeLeft = position[0];
        model.relativeTop = position[1];
        model.relativeRight = position[2];
        model.relativeBottom = position[3];
        model.fontSize = value(attributes, "fontSize", value(attributes, "font-size", 12f));
        model.characterSpacing = value(attributes, "characterSpacing", value(attributes, "splitCharacters", 0f));
        model.wordSpacing = value(attributes, "wordSpacing", 0f);
        model.strokeWidth = value(attributes, "strokeWidth", 1f);
        model.opacity = Math.max(0f, Math.min(1f, number(attributes.get("opacity"), 1f)));
        model.spacingRatio = number(attributes.get("spacingRatio"), 0f);
        model.keepTogether = bool(attributes.get("keepTogether"));
        model.keepWithNext = bool(attributes.get("keepWithNext"));
        model.bold = bool(attributes.get("bold"));
        model.italic = bool(attributes.get("italic"));
        model.underline = bool(attributes.get("underline"));
        model.lineThrough = bool(attributes.get("lineThrough"));
        model.font = attributes.get("font");
        model.fontFamilyNames = attributes.get("fontFamilyNames");
        model.fontColor = first(attributes, "fontColor", "color");
        // 注意：first() 的第二个候选参数必须是“键名”，不能嵌套传入已解析出的值，
        // 否则会退化为 attributes.get(值) 从而丢失 background 简写。
        model.backgroundColor = first(attributes, "backgroundColor", "background");
        if (model.backgroundColor == null) {
            model.backgroundColor = attributes.get("background-color");
        }
        model.backgroundImage = attributes.get("backgroundImage");
        model.border = attributes.get("border");
        model.borderTop = first(attributes, "borderTop", "border-top");
        model.borderRight = first(attributes, "borderRight", "border-right");
        model.borderBottom = first(attributes, "borderBottom", "border-bottom");
        model.borderLeft = first(attributes, "borderLeft", "border-left");
        model.borderRadius = first(attributes, "borderRadius", "border-radius");
        model.borderTopLeftRadius = attributes.get("borderTopLeftRadius");
        model.borderTopRightRadius = attributes.get("borderTopRightRadius");
        model.borderBottomRightRadius = attributes.get("borderBottomRightRadius");
        model.borderBottomLeftRadius = attributes.get("borderBottomLeftRadius");
        model.strokeColor = attributes.get("strokeColor");
        model.textAlignment = first(attributes, "textAlignment", "text-align");
        model.verticalAlignment = first(attributes, "verticalAlignment", "vertical-align");
        model.fontKerning = attributes.get("fontKerning");
        model.fontScript = attributes.get("fontScript");
        model.textRenderingMode = attributes.get("textRenderingMode");
        model.baseDirection = attributes.get("baseDirection");
        model.destination = attributes.get("destination");
        model.display = first(attributes, "display", "display");
        model.flexDirection = first(attributes, "flexDirection", "flex-direction");
        model.flexWrap = first(attributes, "flexWrap", "flex-wrap");
        model.justifyContent = first(attributes, "justifyContent", "justify-content");
        model.alignItems = first(attributes, "alignItems", "align-items");
        return model;
    }

    private static float[] box(String common, String shorthand) {
        if (shorthand != null) {
            float[] source = PdfBoxUnitConverter.toPoints(shorthand, 4, 0f);
            String[] tokens = shorthand.replace("'", "").replace("\"", "").trim().split("\\s+");
            if (tokens.length == 1) return new float[]{source[0], source[0], source[0], source[0]};
            if (tokens.length == 2) return new float[]{source[0], source[1], source[0], source[1]};
            if (tokens.length == 3) return new float[]{source[0], source[1], source[2], source[1]};
            return source;
        }
        float all = PdfBoxUnitConverter.toPoint(common, 0f);
        return new float[]{all, all, all, all};
    }

    private static float value(JStyleAttributes attributes, String key, float fallback) {
        return attributes.containsKey(key) ? PdfBoxUnitConverter.toPoint(attributes.get(key), fallback) : fallback;
    }

    private static float side(JStyleAttributes attributes, String camelKey, String kebabKey, float fallback) {
        return value(attributes, camelKey, value(attributes, kebabKey, fallback));
    }

    private static float number(String value, float fallback) {
        try {
            return value == null ? fallback : Float.parseFloat(value.trim());
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static boolean bool(String value) {
        return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
    }

    private static String first(JStyleAttributes attributes, String preferred, String fallback) {
        String value = attributes.get(preferred);
        return value == null ? attributes.get(fallback) : value;
    }
}

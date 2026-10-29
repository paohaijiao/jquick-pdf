package com.github.paohaijiao.visitor.render;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.style.JQuickStyleEnum;
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
        String marginsShorthand = firstOf(attributes, JQuickStyleEnum.margins, JQuickStyleEnum.margin);
        float[] margins = box(JQuickStyleEnum.commonMargin.getString(attributes, null), marginsShorthand);
        model.marginTop = valueOf(attributes, JQuickStyleEnum.marginTop, margins[0]);
        model.marginRight = valueOf(attributes, JQuickStyleEnum.marginRight, margins[1]);
        model.marginBottom = valueOf(attributes, JQuickStyleEnum.marginBottom, margins[2]);
        model.marginLeft = valueOf(attributes, JQuickStyleEnum.marginLeft, margins[3]);

        String paddingsShorthand = firstOf(attributes, JQuickStyleEnum.paddings, JQuickStyleEnum.padding);
        float[] paddings = box(JQuickStyleEnum.commonPadding.getString(attributes, null), paddingsShorthand);
        model.paddingTop = valueOf(attributes, JQuickStyleEnum.paddingTop, paddings[0]);
        model.paddingRight = valueOf(attributes, JQuickStyleEnum.paddingRight, paddings[1]);
        model.paddingBottom = valueOf(attributes, JQuickStyleEnum.paddingBottom, paddings[2]);
        model.paddingLeft = valueOf(attributes, JQuickStyleEnum.paddingLeft, paddings[3]);

        model.width = valueOf(attributes, JQuickStyleEnum.width, -1f);
        model.height = valueOf(attributes, JQuickStyleEnum.height, -1f);
        model.minWidth = valueOf(attributes, JQuickStyleEnum.minWidth, -1f);
        model.maxWidth = valueOf(attributes, JQuickStyleEnum.maxWidth, -1f);
        model.minHeight = valueOf(attributes, JQuickStyleEnum.minHeight, -1f);
        model.maxHeight = valueOf(attributes, JQuickStyleEnum.maxHeight, -1f);
        model.angleInRadians = valueOf(attributes, JQuickStyleEnum.angleInRadians, 0f);
        float[] position = PdfBoxUnitConverter.toPoints(JQuickStyleEnum.relativePosition.getString(attributes, null), 4, 0f);
        model.relativeLeft = position[0];
        model.relativeTop = position[1];
        model.relativeRight = position[2];
        model.relativeBottom = position[3];
        model.fontSize = valueOf(attributes, JQuickStyleEnum.fontSize, 12f);
        model.characterSpacing = valueOf(attributes, JQuickStyleEnum.characterSpacing,
                valueOf(attributes, JQuickStyleEnum.splitCharacters, 0f));
        model.wordSpacing = valueOf(attributes, JQuickStyleEnum.wordSpacing, 0f);
        model.strokeWidth = valueOf(attributes, JQuickStyleEnum.strokeWidth, 1f);
        model.opacity = Math.max(0f, Math.min(1f, number(JQuickStyleEnum.opacity.getString(attributes, null), 1f)));
        model.spacingRatio = number(JQuickStyleEnum.spacingRatio.getString(attributes, null), 0f);
        model.keepTogether = bool(JQuickStyleEnum.keepTogether.getString(attributes, null));
        model.keepWithNext = bool(JQuickStyleEnum.keepWithNext.getString(attributes, null));
        model.bold = bool(JQuickStyleEnum.bold.getString(attributes, null));
        model.italic = bool(JQuickStyleEnum.italic.getString(attributes, null));
        model.underline = bool(JQuickStyleEnum.underline.getString(attributes, null));
        model.lineThrough = bool(JQuickStyleEnum.lineThrough.getString(attributes, null));
        model.font = JQuickStyleEnum.font.getString(attributes, null);
        model.fontFamilyNames = JQuickStyleEnum.fontFamilyNames.getString(attributes, null);
        model.fontColor = firstOf(attributes, JQuickStyleEnum.fontColor, JQuickStyleEnum.color);
        // 注意：简写优先级链的每一级都必须是“键名”枚举，不能把已解析出的值当作键名回退，
        // 否则会退化为 attributes.get(值) 从而丢失 background 简写。
        model.backgroundColor = firstOf(attributes, JQuickStyleEnum.backgroundColor, JQuickStyleEnum.background);
        model.backgroundImage = JQuickStyleEnum.backgroundImage.getString(attributes, null);
        model.border = JQuickStyleEnum.border.getString(attributes, null);
        model.borderTop = JQuickStyleEnum.borderTop.getString(attributes, null);
        model.borderRight = JQuickStyleEnum.borderRight.getString(attributes, null);
        model.borderBottom = JQuickStyleEnum.borderBottom.getString(attributes, null);
        model.borderLeft = JQuickStyleEnum.borderLeft.getString(attributes, null);
        model.borderRadius = JQuickStyleEnum.borderRadius.getString(attributes, null);
        model.borderTopLeftRadius = JQuickStyleEnum.borderTopLeftRadius.getString(attributes, null);
        model.borderTopRightRadius = JQuickStyleEnum.borderTopRightRadius.getString(attributes, null);
        model.borderBottomRightRadius = JQuickStyleEnum.borderBottomRightRadius.getString(attributes, null);
        model.borderBottomLeftRadius = JQuickStyleEnum.borderBottomLeftRadius.getString(attributes, null);
        model.strokeColor = JQuickStyleEnum.strokeColor.getString(attributes, null);
        model.textAlignment = firstOf(attributes, JQuickStyleEnum.textAlignment, JQuickStyleEnum.textAlign);
        model.verticalAlignment = firstOf(attributes, JQuickStyleEnum.verticalAlignment, JQuickStyleEnum.verticalAlign);
        model.fontKerning = JQuickStyleEnum.fontKerning.getString(attributes, null);
        model.fontScript = JQuickStyleEnum.fontScript.getString(attributes, null);
        model.textRenderingMode = JQuickStyleEnum.textRenderingMode.getString(attributes, null);
        model.baseDirection = JQuickStyleEnum.baseDirection.getString(attributes, null);
        model.destination = JQuickStyleEnum.destination.getString(attributes, null);
        model.display = JQuickStyleEnum.display.getString(attributes, null);
        model.flexDirection = JQuickStyleEnum.flexDirection.getString(attributes, null);
        model.flexWrap = JQuickStyleEnum.flexWrap.getString(attributes, null);
        model.justifyContent = JQuickStyleEnum.justifyContent.getString(attributes, null);
        model.alignItems = JQuickStyleEnum.alignItems.getString(attributes, null);
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

    /** 按枚举读取样式值并换算为 pt，未声明时返回默认值。 */
    private static float valueOf(JStyleAttributes attributes, JQuickStyleEnum key, float fallback) {
        return PdfBoxUnitConverter.toPoint(key.getString(attributes, null), fallback);
    }

    /** 依次尝试两个枚举键，返回第一个非空值（用于简写 → 具体名的回退链）。 */
    private static String firstOf(JStyleAttributes attributes, JQuickStyleEnum preferred, JQuickStyleEnum fallback) {
        String value = preferred.get(attributes);
        return value != null ? value : fallback.get(attributes);
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
}

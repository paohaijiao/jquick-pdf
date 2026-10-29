package com.github.paohaijiao.style;

import com.github.paohaijiao.model.JStyleAttributes;

/**
 * 样式 key 枚举：集中定义所有元素样式名，避免渲染代码里散落硬编码字符串。
 * 每个枚举项同时登记 kebab-case(key) 与 camelCase(code) 两种写法，
 * 取值时通过 {@link #get(JStyleAttributes)} 自动兼容两种形式，
 * 例如 padding-left 与 paddingLeft 均可命中。
 */
public enum JQuickStyleEnum {

    width("width", "width"),

    height("height", "height"),

    padding("padding", "padding"),

    paddings("paddings", "paddings"),

    commonPadding("commonPadding", "commonPadding"),

    paddingLeft("padding-left", "paddingLeft"),

    paddingRight("padding-right", "paddingRight"),

    paddingTop("padding-top", "paddingTop"),

    paddingBottom("padding-bottom", "paddingBottom"),

    margin("margin", "margin"),

    margins("margins", "margins"),

    commonMargin("commonMargin", "commonMargin"),

    marginLeft("margin-left", "marginLeft"),

    marginRight("margin-right", "marginRight"),

    marginTop("margin-top", "marginTop"),

    marginBottom("margin-bottom", "marginBottom"),

    color("color", "color"),

    backgroundColor("background-color", "backgroundColor"),

    background("background", "background"),

    backgroundImage("background-image", "backgroundImage"),

    border("border", "border"),

    borderTop("border-top", "borderTop"),

    borderRight("border-right", "borderRight"),

    borderBottom("border-bottom", "borderBottom"),

    borderLeft("border-left", "borderLeft"),

    borderColor("border-color", "borderColor"),

    fontSize("font-size", "fontSize"),

    fontWeight("font-weight", "fontWeight"),

    lineHeight("line-height", "lineHeight"),

    listStyleType("list-style-type", "listStyleType"),

    font("font", "font"),

    fontFamilyNames("font-family-names", "fontFamilyNames"),

    fontColor("font-color", "fontColor"),

    fontKerning("font-kerning", "fontKerning"),

    fontScript("font-script", "fontScript"),

    minWidth("min-width", "minWidth"),

    maxWidth("max-width", "maxWidth"),

    minHeight("min-height", "minHeight"),

    maxHeight("max-height", "maxHeight"),

    relativePosition("relative-position", "relativePosition"),

    angleInRadians("angle-in-radians", "angleInRadians"),

    characterSpacing("character-spacing", "characterSpacing"),

    splitCharacters("split-characters", "splitCharacters"),

    wordSpacing("word-spacing", "wordSpacing"),

    spacingRatio("spacing-ratio", "spacingRatio"),

    keepTogether("keep-together", "keepTogether"),

    keepWithNext("keep-with-next", "keepWithNext"),

    bold("bold", "bold"),

    italic("italic", "italic"),

    underline("underline", "underline"),

    lineThrough("line-through", "lineThrough"),

    verticalAlign("vertical-align", "verticalAlign"),

    verticalAlignment("vertical-alignment", "verticalAlignment"),

    textRenderingMode("text-rendering-mode", "textRenderingMode"),

    baseDirection("base-direction", "baseDirection"),

    destination("destination", "destination"),

    borderRadius("border-radius", "borderRadius"),

    borderTopLeftRadius("border-top-left-radius", "borderTopLeftRadius"),

    borderTopRightRadius("border-top-right-radius", "borderTopRightRadius"),

    borderBottomRightRadius("border-bottom-right-radius", "borderBottomRightRadius"),

    borderBottomLeftRadius("border-bottom-left-radius", "borderBottomLeftRadius"),

    display("display", "display"),

    flexDirection("flex-direction", "flexDirection"),

    flexWrap("flex-wrap", "flexWrap"),

    justifyContent("justify-content", "justifyContent"),

    alignItems("align-items", "alignItems"),

    textAlign("text-align", "textAlign"),

    textAlignment("text-alignment", "textAlignment"),

    strokeColor("stroke-color", "strokeColor"),

    strokeWidth("stroke-width", "strokeWidth"),

    opacity("opacity", "opacity"),

    pageSize("page-size", "pageSize");

    private final String key;

    private final String code;

    JQuickStyleEnum(String key, String code) {
        this.key = key;
        this.code = code;
    }

    public String getKey()  { return key; }

    public String getCode() { return code; }

    /**
     * 同时兼容 key(kebab) 与 code(camel) 两种写法取值。
     * 先查 key，再查 code，最后宽松兜底（忽略 -、_、空格与大小写）。
     */
    public String get(JStyleAttributes style) {
        if (style == null) return null;
        String v = style.get(key);
        if (v != null) return v;
        if (!code.equals(key)) {
            v = style.get(code);
            if (v != null) return v;
        }
        String target = normalize(key);
        for (String k : style.keySet()) {
            if (normalize(k).equals(target)) {
                return style.get(k);
            }
        }
        return null;
    }
    public String getString(JStyleAttributes style, String defaultValue) {
        String v = get(style);
        return v == null ? defaultValue : v;
    }

    public float getFloat(JStyleAttributes style, float defaultValue) {
        String v = get(style);
        if (v == null) return defaultValue;
        String s = v.toString().replace("px", "").trim();
        if (s.isEmpty()) return defaultValue;
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static String normalize(String k) {
        if (k == null) return "";
        return k.replace("-", "")
                .replace("_", "")
                .replace(" ", "")
                .toLowerCase();
    }


    }

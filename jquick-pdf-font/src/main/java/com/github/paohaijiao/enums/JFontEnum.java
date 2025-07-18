package com.github.paohaijiao.enums;

import lombok.Getter;

@Getter
public enum JFontEnum {

    COURIER("Courier"),
    COURIER_BOLD("Courier-Bold"),
    COURIER_OBLIQUE("Courier-Oblique"),
    COURIER_BOLDOBLIQUE("Courier-BoldOblique"),
    HELVETICA("Helvetica"),
    HELVETICA_BOLD("Helvetica-Bold"),
    HELVETICA_OBLIQUE("Helvetica-Oblique"),
    HELVETICA_BOLDOBLIQUE("Helvetica-BoldOblique"),
    SYMBOL("Symbol"),
    TIMES_ROMAN("Times-Roman"),
    TIMES_BOLD("Times-Bold"),
    TIMES_ITALIC("Times-Italic"),
    TIMES_BOLDITALIC("Times-BoldItalic"),
    ZAPFDINGBATS("ZapfDingbats");

    private final String fontName;

    JFontEnum(String fontName) {
        this.fontName = fontName;
    }
    public static JFontEnum codeOf(String code){
        for(JFontEnum fontEnum : JFontEnum.values()){
            if(fontEnum.getFontName().equalsIgnoreCase(code)){
                return fontEnum;
            }
        }
        return null;
    }
}

package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.FontKerning;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.Getter;

@Getter
public enum JFontKerning {

    yes("yes", FontKerning.YES),
    no("no", FontKerning.NO);
    private String code;

    private FontKerning type;

    private JFontKerning(String code, FontKerning type) {
        this.code = code;
        this.type = type;
    }
    public static JFontKerning codeOf(String code) {
        for (JFontKerning type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.Getter;

@Getter
public enum JVerticalAlignment {

    top("top", VerticalAlignment.TOP),

    middle("middle", VerticalAlignment.MIDDLE),
    bottom("bottom", VerticalAlignment.BOTTOM);

    private String code;

    private VerticalAlignment type;

    private JVerticalAlignment(String code, VerticalAlignment type) {
        this.code = code;
        this.type = type;
    }

    public static JVerticalAlignment codeOf(String code) {
        for (JVerticalAlignment type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

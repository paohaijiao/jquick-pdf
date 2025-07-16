package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.TextAlignment;
import lombok.Getter;

@Getter
public enum JTextAlignment {
    left("left", TextAlignment.LEFT),

    center("center", TextAlignment.CENTER),
    right("right", TextAlignment.RIGHT),
    justified("justified", TextAlignment.JUSTIFIED),
    justified_all("justified_all", TextAlignment.JUSTIFIED_ALL);
    private String code;

    private TextAlignment type;

    private JTextAlignment(String code, TextAlignment type) {
        this.code = code;
        this.type = type;
    }
    public static JTextAlignment codeOf(String code) {
        for (JTextAlignment type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

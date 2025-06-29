package com.github.paohaijiao.enums;

import lombok.Getter;

@Getter
public enum JFrontStyle {
    Italic("italic", "italic"),
    Underline("underline", "underline"),
    Strikethrough("strikethrough", "strikethrough"),
    oblique("oblique", "oblique"),
    normal("normal", "normal"),
    initial("initial", "initial"),
    inherit("inherit", "inherit");

    private String code;
    private String name;

    private JFrontStyle(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String codeOf(String code) {
        for (JFrontStyle style : JFrontStyle.values()) {
            if (style.code.equals(code)) {
                return style.name;
            }
        }
        return code;
    }
}

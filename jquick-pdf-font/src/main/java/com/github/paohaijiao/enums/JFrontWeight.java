package com.github.paohaijiao.enums;

import lombok.Getter;

@Getter
public enum JFrontWeight {
    Italic("normal", "normal"),
    Bold("bold", "bold");

    private String code;
    private String name;

    private JFrontWeight(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String codeOf(String code) {
        for (JFrontWeight style : JFrontWeight.values()) {
            if (style.code.equals(code)) {
                return style.name;
            }
        }
        return code;
    }
}

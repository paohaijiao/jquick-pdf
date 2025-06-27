package com.github.paohaijiao.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum JListType {
    ol("ol","order list"),
    ul("ul","un order list");

    private String code;
    private String name;

    private JListType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static JListType codeOf(String code) {
        for (JListType jListType : JListType.values()) {
            if (jListType.code.equals(code)) {
                return jListType;
            }
        }
        return null;
    }
}

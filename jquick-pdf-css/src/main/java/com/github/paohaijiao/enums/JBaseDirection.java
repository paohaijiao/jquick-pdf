package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.BaseDirection;
import lombok.Getter;

@Getter
public enum JBaseDirection {

    no_bidi("no_bidi", BaseDirection.NO_BIDI),
    default_bidi("default_bidi", BaseDirection.DEFAULT_BIDI),
    left_to_right("left_to_right", BaseDirection.LEFT_TO_RIGHT),
    right_to_left("right_to_left", BaseDirection.RIGHT_TO_LEFT);
    private String code;

    private BaseDirection type;

    private JBaseDirection(String code, BaseDirection type) {
        this.code = code;
        this.type = type;
    }
    public static JBaseDirection codeOf(String code) {
        for (JBaseDirection type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}

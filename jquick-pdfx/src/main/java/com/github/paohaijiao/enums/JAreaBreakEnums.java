package com.github.paohaijiao.enums;

import com.itextpdf.layout.properties.AreaBreakType;
import lombok.Getter;

@Getter
public enum JAreaBreakEnums {

    next_area("next_area", AreaBreakType.NEXT_AREA),
    next_page("next_page", AreaBreakType.NEXT_PAGE),
    last_page("last_page", AreaBreakType.LAST_PAGE);

    private String code;

    private AreaBreakType type;

    JAreaBreakEnums(String code, AreaBreakType type) {
        this.code = code;
        this.type = type;
    }

    public static JAreaBreakEnums codeOf(String code) {
        for (JAreaBreakEnums j : JAreaBreakEnums.values()) {
            if (j.code.equalsIgnoreCase(code)) {
                return j;
            }
        }
        return null;
    }
}

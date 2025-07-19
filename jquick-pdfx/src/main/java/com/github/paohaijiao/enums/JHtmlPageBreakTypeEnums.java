package com.github.paohaijiao.enums;

import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreakType;
import lombok.Getter;

@Getter
public enum JHtmlPageBreakTypeEnums {

    next_area("next_area", HtmlPageBreakType.ALWAYS),
    next_page("next_page", HtmlPageBreakType.LEFT),
    last_page("last_page", HtmlPageBreakType.RIGHT);

    private String code;

    private HtmlPageBreakType type;

    JHtmlPageBreakTypeEnums(String code, HtmlPageBreakType type) {
        this.code = code;
        this.type = type;
    }

    public static JHtmlPageBreakTypeEnums codeOf(String code) {
        for (JHtmlPageBreakTypeEnums j : JHtmlPageBreakTypeEnums.values()) {
            if (j.getCode().equalsIgnoreCase(code)) {
                return j;
            }
        }
        return null;
    }
}

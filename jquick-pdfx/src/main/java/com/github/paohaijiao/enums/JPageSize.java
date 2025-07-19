package com.github.paohaijiao.enums;

import com.itextpdf.kernel.geom.PageSize;

public enum JPageSize {
    A0("A0", PageSize.A0),
    A1("A1", PageSize.A1),
    A2("A2", PageSize.A2),
    A3("A3", PageSize.A3),
    A4("A4", PageSize.A4),
    A5("A5", PageSize.A5),
    A6("A6", PageSize.A6),
    A7("A7",PageSize.A7),
    A8("A8", PageSize.A8),
    A9("A9", PageSize.A9),
    A10("A10",PageSize.A10),
    B0("B0", PageSize.B0),
    B1("B1",PageSize.B1),
    B2("B2",PageSize.B2),
    B3("B3", PageSize.B3),
    B4("B4", PageSize.B4),
    B5("B5",PageSize.B5),
    B6("B6",PageSize.B6),
    B7("B7", PageSize.B7),
    B8("B8", PageSize.B8),
    B9("B9", PageSize.B9),
    B10("B10", PageSize.B10),

    DEFAULT("DEFAULT", PageSize.DEFAULT),
    EXECUTIVE("EXECUTIVE", new PageSize(522.0F, 756.0F)),
    LEDGER("LEDGER", new PageSize(1224.0F, 792.0F)),
    LEGAL("LEGAL", new PageSize(612.0F, 1008.0F)),
    LETTER("LETTER", new PageSize(612.0F, 792.0F)),
    TABLOID("TABLOID", new PageSize(792.0F, 1224.0F));

    private final String code;

    private final PageSize pageSize;

    JPageSize(String code, PageSize pageSize) {
        this.code = code;
        this.pageSize = pageSize;
    }

    public String getCode() {
        return code;
    }

    public PageSize getPageSize() {
        return pageSize;
    }
    public static JPageSize codeOf(String code) {
        for (JPageSize size : values()) {
            if (size.code.equalsIgnoreCase(code)) {
                return size;
            }
        }
        throw new IllegalArgumentException("unknow pageType: " + code);
    }
}

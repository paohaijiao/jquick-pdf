package com.github.paohaijiao.enums;

import lombok.Getter;

/**
 * 保留旧配置的编码标识。PDFBox 嵌入字体时不依赖该值。
 */
@Getter
public enum JPdfEncoding {
    IDENTITY_H("Identity-H"),
    UNIGB_UCS2_H("UniGB-UCS2-H");

    private final String encoding;

    JPdfEncoding(String encoding) {
        this.encoding = encoding;
    }
}

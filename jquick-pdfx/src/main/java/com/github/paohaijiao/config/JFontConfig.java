/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.font.JFontSpec;
import lombok.Data;

@Data
public class JFontConfig {

    private JFontSpec defaultFont = new JFontSpec();

    private JFontSpec titleFont = new JFontSpec();

    private JFontSpec bodyFont = new JFontSpec();

    private JFontSpec headerFooterFont = new JFontSpec();

    private JFontSpec codeFont = new JFontSpec("Courier", null);

    private String fontDirectory = "fonts";

    private String defaultFontName = "SimSun";

    /**
     * Retained as metadata for existing configurations. PDFBox embeds Type0
     * fonts from font files and does not use iText CJK encoding values.
     */
    private String defaultEncoding = "UniGB-UCS2-H";

    /**
     * 注册自定义字体家族：模板中 fontFamilyNames 回退链里的自定义家族名
     * （如 Noto Sans Arabic）按名字命中这里注册的字体文件，缺字时向后回退。
     *
     * @param familyName 模板中声明的家族名，大小写不敏感
     * @param file       字体文件路径（磁盘路径或 classpath 资源路径）
     */
    public void registerFont(String familyName, String file) {
        com.github.paohaijiao.font.JFontRegistry.register(familyName, file);
    }
}

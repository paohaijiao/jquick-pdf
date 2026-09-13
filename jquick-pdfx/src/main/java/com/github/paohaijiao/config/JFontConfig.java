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
}

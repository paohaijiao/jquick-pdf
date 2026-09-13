/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.font.JFontSpec;
import lombok.Data;

@Data
public class JWaterRemarkConfig {

    private Boolean enabled = false;

    private String watermarkText = "水印";

    private JFontSpec font = new JFontSpec();

    private Float fillOpacity = 0.3f;
}

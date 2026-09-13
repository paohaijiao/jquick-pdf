/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.font.JFontSpec;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class JDocConfig {
    private List<Integer> margins = Arrays.asList(50, 60, 50, 60);
    private JFontSpec font = new JFontSpec();
    private float fontSize = 10.5f;
    private float characterSpacing = 0.1f;
}

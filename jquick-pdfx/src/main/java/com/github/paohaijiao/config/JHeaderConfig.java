/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.enums.JAlign;
import com.github.paohaijiao.font.JFontSpec;
import lombok.Data;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

@Data
public class JHeaderConfig {

    private boolean enabled = true;

    private String text = "";

    private JFontSpec font = new JFontSpec();

    private float fontSize = 10;

    private PDColor fontColor = rgb(37, 98, 206);

    private float height = 30;

    private PDColor backgroundColor;

    private JAlign alignment = JAlign.center;

    private static PDColor rgb(int red, int green, int blue) {
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f},
                PDDeviceRGB.INSTANCE);
    }
}

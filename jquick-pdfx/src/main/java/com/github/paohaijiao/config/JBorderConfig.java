/*
 * Licensed under the GNU Affero General Public License, Version 3.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.enums.JBorder;
import lombok.Data;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

@Data
public class JBorderConfig {

    private float width = 0.5f;

    private PDColor color = new PDColor(
            new float[]{200f / 255f, 200f / 255f, 200f / 255f},
            PDDeviceRGB.INSTANCE);

    private JBorder type = JBorder.solid;
}

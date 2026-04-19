/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.combol.area;

import lombok.Data;

import java.awt.*;

/**
 * packageName com.github.paohaijiao.combol
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/19
 */
@Data
public class JTheme {
    // 预定义主题
    public static final JTheme DEFAULT = new JTheme(
            new Color(255, 158, 68, 200),   // areaStartColor
            new Color(255, 70, 131, 100),   // areaEndColor
            new Color(255, 70, 131),        // lineColor
            new Color(249, 249, 249),       // backgroundColor
            new Color(221, 221, 221),       // gridColor
            new Color(102, 102, 102),       // axisColor
            new Color(51, 51, 51),          // textColor
            new Color(136, 136, 136)        // axisTextColor
    );

    public static final JTheme BLUE = new JTheme(
            new Color(84, 112, 198, 200),
            new Color(84, 112, 198, 80),
            new Color(84, 112, 198),
            new Color(249, 249, 249),
            new Color(221, 221, 221),
            new Color(102, 102, 102),
            new Color(51, 51, 51),
            new Color(136, 136, 136)
    );

    public static final JTheme GREEN = new JTheme(
            new Color(82, 196, 114, 200),   // areaStartColor
            new Color(82, 196, 114, 80),
            new Color(82, 196, 114),
            new Color(249, 249, 249),
            new Color(221, 221, 221),
            new Color(102, 102, 102),
            new Color(51, 51, 51),
            new Color(136, 136, 136)
    );

    public static final JTheme PURPLE = new JTheme(
            new Color(114, 46, 209, 200),   // areaStartColor
            new Color(114, 46, 209, 80),
            new Color(114, 46, 209),
            new Color(249, 249, 249),
            new Color(221, 221, 221),
            new Color(102, 102, 102),
            new Color(51, 51, 51),
            new Color(136, 136, 136)
    );

    public static final JTheme DARK = new JTheme(
            new Color(0, 188, 212, 200),
            new Color(0, 188, 212, 80),
            new Color(0, 188, 212),
            new Color(30, 30, 35),
            new Color(68, 68, 75),
            new Color(150, 150, 160),
            new Color(220, 220, 230),
            new Color(150, 150, 160)
    );

    private final Color areaStartColor;

    private final Color areaEndColor;

    private final Color lineColor;

    private final Color backgroundColor;

    private final Color gridColor;

    private final Color axisColor;

    private final Color textColor;

    private final Color axisTextColor;

    public JTheme(Color areaStartColor, Color areaEndColor, Color lineColor, Color backgroundColor, Color gridColor, Color axisColor, Color textColor, Color axisTextColor) {
        this.areaStartColor = areaStartColor;
        this.areaEndColor = areaEndColor;
        this.lineColor = lineColor;
        this.backgroundColor = backgroundColor;
        this.gridColor = gridColor;
        this.axisColor = axisColor;
        this.textColor = textColor;
        this.axisTextColor = axisTextColor;
    }
}

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
package com.github.paohaijiao.color;

import java.awt.*;

/**
 * packageName com.github.paohaijiao.color
 *
 * @author Martin
 * @version 1.0.0
 * @className JColoUtil
 * @date 2025/7/1
 * @description
 */
public class JColoUtil {


    public static Color getColorByHexString(String hex) {
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        Color color = new Color(r, g, b);
        return color;
    }
    public static String rgbToHex(Color color) {
        String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
        return hex;
    }
}

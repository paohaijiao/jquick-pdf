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

import lombok.Getter;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

@Getter
public enum JColorEnums {

    BLACK("black", new PDColor(new float[]{0f, 0f, 0f}, PDDeviceRGB.INSTANCE)),

    BLUE("blue", new PDColor(new float[]{0f, 0f, 1f}, PDDeviceRGB.INSTANCE)),

    CYAN("cyan", new PDColor(new float[]{0f, 1f, 1f}, PDDeviceRGB.INSTANCE)),

    DARK_GRAY("dark_gray", new PDColor(new float[]{64f/255f, 64f/255f, 64f/255f}, PDDeviceRGB.INSTANCE)),

    GRAY("gray", new PDColor(new float[]{128f/255f, 128f/255f, 128f/255f}, PDDeviceRGB.INSTANCE)),

    GREEN("green", new PDColor(new float[]{0f, 1f, 0f}, PDDeviceRGB.INSTANCE)),

    LIGHT_GRAY("light_gray", new PDColor(new float[]{192f/255f, 192f/255f, 192f/255f}, PDDeviceRGB.INSTANCE)),

    MAGENTA("magenta", new PDColor(new float[]{1f, 0f, 1f}, PDDeviceRGB.INSTANCE)),

    ORANGE("orange", new PDColor(new float[]{1f, 200f/255f, 0f}, PDDeviceRGB.INSTANCE)),

    PINK("pink", new PDColor(new float[]{1f, 175f/255f, 175f/255f}, PDDeviceRGB.INSTANCE)),

    RED("red", new PDColor(new float[]{1f, 0f, 0f}, PDDeviceRGB.INSTANCE)),

    WHITE("white", new PDColor(new float[]{1f, 1f, 1f}, PDDeviceRGB.INSTANCE)),

    YELLOW("yellow", new PDColor(new float[]{1f, 1f, 0f}, PDDeviceRGB.INSTANCE)),

    PURPLE("purple", new PDColor(new float[]{128f/255f, 0f, 128f/255f}, PDDeviceRGB.INSTANCE)),

    BROWN("brown", new PDColor(new float[]{165f/255f, 42f/255f, 42f/255f}, PDDeviceRGB.INSTANCE)),



    LIME("lime", new PDColor(new float[]{0f, 1f, 0f}, PDDeviceRGB.INSTANCE)),  // 同GREEN


    MAROON("maroon", new PDColor(new float[]{128f/255f, 0f, 0f}, PDDeviceRGB.INSTANCE)),

    OLIVE("olive", new PDColor(new float[]{128f/255f, 128f/255f, 0f}, PDDeviceRGB.INSTANCE)),

    NAVY("navy", new PDColor(new float[]{0f, 0f, 128f/255f}, PDDeviceRGB.INSTANCE)),

    TEAL("teal", new PDColor(new float[]{0f, 128f/255f, 128f/255f}, PDDeviceRGB.INSTANCE)),

    AQUA("aqua", new PDColor(new float[]{0f, 1f, 1f}, PDDeviceRGB.INSTANCE)),  // 同CYAN

    FUCHSIA("fuchsia", new PDColor(new float[]{1f, 0f, 1f}, PDDeviceRGB.INSTANCE)), // 同MAGENTA

    SILVER("silver", new PDColor(new float[]{192f/255f, 192f/255f, 192f/255f}, PDDeviceRGB.INSTANCE)),

    GREY("grey", new PDColor(new float[]{128f/255f, 128f/255f, 128f/255f}, PDDeviceRGB.INSTANCE));

    private String code;

    private PDColor color;

    private JColorEnums(String code, PDColor color) {
        this.code = code;
        this.color = color;

    }

    public static PDColor colorOf(String color) {
        for (JColorEnums c : JColorEnums.values()) {
            if (c.code.equals(color)) {
                return c.color;
            }
        }
        return null;
    }

    public static PDColor colorOf(Integer red, Integer green, Integer blue) {
        if (red == null || green == null || blue == null) {
            return null;
        }
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException(String.format("RGB值必须在0-255范围内: red=%d, green=%d, blue=%d", red, green, blue));
        }
        return new PDColor(new float[]{red / 255f, green / 255f, blue / 255f}, PDDeviceRGB.INSTANCE);
    }

    public static PDColor colorOf(Float c, Float m, Float y, Float k) {
        if (c == null || m == null || y == null || k == null) {
            return null;
        }
        if (c < 0 || c > 1 || m < 0 || m > 1 || y < 0 || y > 1 || k < 0 || k > 1) {
            throw new IllegalArgumentException(String.format("CMYK值必须在0.0-1.0范围内: c=%.2f, m=%.2f, y=%.2f, k=%.2f", c, m, y, k));
        }
        return new PDColor(new float[]{c, m, y, k}, PDDeviceCMYK.INSTANCE
        );
    }

    public static PDColor colorOfPercent(Float c, Float m, Float y, Float k) {
        if (c < 0 || c > 100 || m < 0 || m > 100 || y < 0 || y > 100 || k < 0 || k > 100) {
            throw new IllegalArgumentException(String.format("CMYK百分比必须在0.0-100.0范围内: c=%.2f%%, m=%.2f%%, y=%.2f%%, k=%.2f%%", c, m, y, k));
        }
        return new PDColor(new float[]{c / 100f, m / 100f, y / 100f, k / 100f}, PDDeviceCMYK.INSTANCE);
    }

    public static PDColor convertHexToRgb(String hexColor) {
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;
        float r, g, b;
        switch (hex.length()) {
            case 3: // "#RGB"
                r = Integer.parseInt(hex.substring(0, 1), 16) * 17 / 255f;
                g = Integer.parseInt(hex.substring(1, 2), 16) * 17 / 255f;
                b = Integer.parseInt(hex.substring(2, 3), 16) * 17 / 255f;
                break;

            case 6: // "#RRGGBB"
                r = Integer.parseInt(hex.substring(0, 2), 16) / 255f;
                g = Integer.parseInt(hex.substring(2, 4), 16) / 255f;
                b = Integer.parseInt(hex.substring(4, 6), 16) / 255f;
                break;

            default:
                throw new IllegalArgumentException("无效的16进制颜色格式: " + hexColor);
        }

        return new PDColor(new float[]{r, g, b}, PDDeviceRGB.INSTANCE);
    }
}

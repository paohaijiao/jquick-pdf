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

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceRgb;
import lombok.Getter;

@Getter
public enum JColorEnums {

    BLACK("black",DeviceRgb.BLACK),

    BLUE("blue",DeviceRgb.BLUE),

    CYAN("cyan",new DeviceRgb(0, 255, 255)),

    DARK_GRAY("dark_gray",new DeviceRgb(64, 64, 64)),

    GRAY("gray",new DeviceRgb(128, 128, 128)),

    GREEN("green",DeviceRgb.GREEN),

    LIGHT_GRAY("light_gray",new DeviceRgb(192, 192, 192)),

    MAGENTA("magenta",new DeviceRgb(255, 0, 255)),

    ORANGE("orange", new DeviceRgb(255, 200, 0)),

    PINK("pink", new DeviceRgb(255, 175, 175)),

    RED("red",  DeviceRgb.RED),

    WHITE("white",  DeviceRgb.WHITE),

    YELLOW("yellow",  new DeviceRgb(255, 255, 0)),

    PURPLE("purple",  new DeviceRgb(128, 0, 128)),

    BROWN("brown",  new DeviceRgb(165, 42, 42)),
    magenta("magenta",  new DeviceRgb(255, 0, 255)),

    lime("lime",  new DeviceRgb(0, 255, 0)),

    maroon("maroon",  new DeviceRgb(128, 0, 0)),

    olive("olive",  new DeviceRgb(128, 128, 0)),

    navy("navy",  new DeviceRgb(0, 0, 128)),

    teal("teal",  new DeviceRgb(0, 128, 128)),

    aqua("aqua",  new DeviceRgb(0, 255, 255)),

    fuchsia("fuchsia",  new DeviceRgb(255, 0, 255)),

    silver("silver",  new DeviceRgb(192, 192, 192)),

    GREY ("grey",  new DeviceRgb(128, 128, 128));

    private String code;

    private Color  color;

    private JColorEnums(String code,Color color){
        this.code=code;
        this.color=color;

    }
    public static Color colorOf(String color) {
        for (JColorEnums c : JColorEnums.values()) {
            if (c.code.equals(color)) {
                return c.color;
            }
        }
        return null;
    }
    public static Color colorOf(Integer red, Integer green, Integer blue) {
        return  new DeviceRgb(red, green, blue);
    }
    public static Color colorOf(Float c, Float m,Float y,  Float k) {
        DeviceCmyk cmykColor = new DeviceCmyk(c, m, y, k);
        return cmykColor;
    }
    public static Color colorOfPercent(Float c, Float m,Float y,  Float k) {
        float c1 = c / 100f;
        float m1 = m / 100f;
        float y1 = y / 100f;
        float k1 = k / 100f;
        DeviceCmyk cmykColor = new DeviceCmyk(c1, m1, y1, k1);
        return cmykColor;
    }
    public static DeviceRgb convertHexToRgb(String hexColor) {

        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;
        switch (hex.length()) {
            case 3: // "#RGB"
                int r = Integer.parseInt(hex.substring(0, 1), 16) * 17;
                int g = Integer.parseInt(hex.substring(1, 2), 16) * 17;
                int b = Integer.parseInt(hex.substring(2, 3), 16) * 17;
                return new DeviceRgb(r, g, b);

            case 6: // "#RRGGBB"
                int red = Integer.parseInt(hex.substring(0, 2), 16);
                int green = Integer.parseInt(hex.substring(2, 4), 16);
                int blue = Integer.parseInt(hex.substring(4, 6), 16);
                return new DeviceRgb(red, green, blue);

            default:
                throw new IllegalArgumentException("无效的16进制颜色格式: " + hexColor);
        }
    }
}

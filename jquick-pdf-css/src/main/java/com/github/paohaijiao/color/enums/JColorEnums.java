package com.github.paohaijiao.color.enums;

import com.itextpdf.kernel.colors.DeviceRgb;

public enum JColorEnums {
    WHITE(255, 255, 255, "#FFFFFF"),
    BLACK(0, 0, 0, "#000000"),
    RED(255, 0, 0, "#FF0000"),
    GREEN(0, 255, 0, "#00FF00"),
    BLUE(0, 0, 255, "#0000FF"),
    CYAN(0, 255, 255, "#00FFFF"),
    MAGENTA(255, 0, 255, "#FF00FF"),
    YELLOW(255, 255, 0, "#FFFF00"),
    PINK(255, 175, 175, "#FFAFAF"),
    ORANGE(255, 200, 0, "#FFC800"),
    GRAY(128, 128, 128, "#808080"),
    LIGHT_GRAY(192, 192, 192, "#C0C0C0"),
    DARK_GRAY(64, 64, 64, "#404040");

    private final int r;
    private final int g;
    private final int b;
    private final String hex;

    JColorEnums(int r, int g, int b, String hex) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hex = hex;
    }

    public DeviceRgb toBaseColor() {
        return new DeviceRgb(r, g, b);
    }

    public int[] getRgb() {
        return new int[]{r, g, b};
    }

    public String getHex() {
        return hex;
    }
    public static JColorEnums colorOf(DeviceRgb baseColor) {
        for (JColorEnums color : JColorEnums.values()) {
            if (color.toBaseColor().equals(baseColor)) {
                return color;
            }
        }
        return null;
    }
}

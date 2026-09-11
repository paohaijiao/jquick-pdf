package com.github.paohaijiao.visitor.render;

/**
 * Converts the public CSS-like units used by jquick-pdf to PDF user-space points.
 * Pixels use the CSS reference resolution (96 dpi).
 */
public final class PdfBoxUnitConverter {

    private static final float PX_TO_POINT = 72f / 96f;

    private PdfBoxUnitConverter() {
    }

    public static float toPoint(String value, float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        String normalized = value.trim().replace("'", "").replace("\"", "");
        if (normalized.isEmpty() || "auto".equalsIgnoreCase(normalized)) {
            return defaultValue;
        }
        try {
            if (normalized.endsWith("px")) {
                return Float.parseFloat(normalized.substring(0, normalized.length() - 2).trim()) * PX_TO_POINT;
            }
            if (normalized.endsWith("pt")) {
                return Float.parseFloat(normalized.substring(0, normalized.length() - 2).trim());
            }
            if (normalized.endsWith("in")) {
                return Float.parseFloat(normalized.substring(0, normalized.length() - 2).trim()) * 72f;
            }
            if (normalized.endsWith("cm")) {
                return Float.parseFloat(normalized.substring(0, normalized.length() - 2).trim()) * 72f / 2.54f;
            }
            if (normalized.endsWith("mm")) {
                return Float.parseFloat(normalized.substring(0, normalized.length() - 2).trim()) * 72f / 25.4f;
            }
            return Float.parseFloat(normalized);
        } catch (NumberFormatException ignored) {
            return defaultValue;
        }
    }

    public static float[] toPoints(String value, int expectedSize, float defaultValue) {
        float[] values = new float[expectedSize];
        for (int i = 0; i < expectedSize; i++) {
            values[i] = defaultValue;
        }
        if (value == null) {
            return values;
        }
        String[] tokens = value.replace("'", "").replace("\"", "").trim().split("\\s+");
        if (tokens.length == 0 || tokens[0].isEmpty()) {
            return values;
        }
        for (int i = 0; i < expectedSize; i++) {
            values[i] = toPoint(tokens[Math.min(i, tokens.length - 1)], defaultValue);
        }
        return values;
    }
}

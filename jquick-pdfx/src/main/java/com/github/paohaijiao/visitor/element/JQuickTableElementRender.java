package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxUnitConverter;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class JQuickTableElementRender implements JQuickElementRender {

    private static final float DEFAULT_FONT_SIZE = 12f;

    private static final float LINE_HEIGHT_RATIO = 1.35f;

    private static final float BORDER_WIDTH = 0.5f;

    private List<JRowModel> rows = new ArrayList<>();

    private JStyleAttributes style;

    private float cellPadding = 6f;

    private float rowHeight = 28f;

    public JQuickTableElementRender(List<JRowModel> rows, JStyleAttributes style) {
        if (rows != null) {
            this.rows.addAll(rows);
        }
        this.style = style;
        applyStyle();
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        if (stream == null || context == null || rows.isEmpty()) {
            return;
        }
        int columnCount = resolveColumnCount();
        if (columnCount <= 0) {
            return;
        }
        float availableWidth = context.getWidth() > 0f ? context.getWidth() : context.getPageWidth();
        float tableWidth = resolveTableWidth(availableWidth);
        float[] columnWidths = resolveColumnWidths(columnCount, tableWidth);
        PDFont font = resolveFont(context);
        float tableFontSize = resolveFontSize(style, context.getFontSize() > 0f ? context.getFontSize() : DEFAULT_FONT_SIZE);
        float x = context.getCursorX();
        float y = context.getCursorY();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            JRowModel row = rows.get(rowIndex);
            List<JColumnModel> columns = row.getColumnList();
            if (columns == null || columns.isEmpty()) {
                continue;
            }
            List<List<String>> wrappedLines = new ArrayList<>();
            float rowTextHeight = 0f;
            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                JColumnModel column = columns.get(columnIndex);
                float columnWidth = columnWidths[Math.min(columnIndex, columnWidths.length - 1)];
                float padding = resolvePadding(column.getStyle());
                float cellFontSize = resolveFontSize(column.getStyle(), tableFontSize);
                float contentWidth = Math.max(1f, columnWidth - padding * 2f);
                List<String> lines = wrapText(font, PdfBoxRenderAdapter.sanitizeFontText(font, mergeText(column.getObject())),
                        cellFontSize, contentWidth);
                wrappedLines.add(lines);
                rowTextHeight = Math.max(rowTextHeight, lines.size() * lineHeight(cellFontSize) + padding * 2f);
            }
            float currentRowHeight = Math.max(resolveRowHeight(row), rowTextHeight);
            PdfBoxLayoutEngine layoutEngine = context.getLayoutEngine();
            if (layoutEngine != null) {
                int pageNumber = context.getPageNumber();
                layoutEngine.ensureSpace(currentRowHeight, true);
                if (context.getPageNumber() != pageNumber) {
                    stream = layoutEngine.getStream();
                    x = context.getCursorX();
                    y = context.getCursorY();
                }
            }
            float cellY = y - currentRowHeight;
            float cellX = x;
            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                JColumnModel column = columns.get(columnIndex);
                float columnWidth = columnWidths[Math.min(columnIndex, columnWidths.length - 1)];
                boolean header = "th".equalsIgnoreCase(column.getType());
                boolean striped = !header && rowIndex % 2 == 1;
                drawCell(stream, column.getStyle(), cellX, cellY, columnWidth, currentRowHeight, header, striped);
                drawCellText(stream, font, column, cellX, cellY, columnWidth, currentRowHeight,
                        wrappedLines.get(columnIndex), tableFontSize);
                cellX += columnWidth;
            }
            y = cellY;
        }
        context.setCursorY(y - 8f);
    }

    /**
     * 行高：优先使用 {@code <tr style="height:...">} 为该行声明的行高，
     * 未声明时回退到表格级行高（默认 28）。行内文本过高时由调用方再取最大值。
     */
    private float resolveRowHeight(JRowModel row) {
        if (row.getStyle() == null) {
            return rowHeight;
        }
        float declared = PdfBoxUnitConverter.toPoint(row.getStyle().get("height"), -1f);
        return declared > 0f ? declared : rowHeight;
    }

    private void drawCell(PDPageContentStream stream, JStyleAttributes cellStyle, float x, float y,
                          float width, float height, boolean header, boolean striped) throws IOException {
        PDColor background = resolveBackground(cellStyle);
        if (background == null) {
            background = header
                    ? PdfBoxRenderAdapter.color("#4285F4")
                    : (striped ? PdfBoxRenderAdapter.color("#F5F5F5") : null);
        }
        if (background != null) {
            stream.setNonStrokingColor(background);
            stream.addRect(x, y, width, height);
            stream.fill();
        }
        PDColor borderColor = resolveBorderColor(cellStyle);
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
            stream.setLineWidth(BORDER_WIDTH);
            stream.addRect(x, y, width, height);
            stream.stroke();
        }
    }

    private void drawCellText(PDPageContentStream stream, PDFont font, JColumnModel column, float x, float y,
                              float width, float height, List<String> lines, float tableFontSize) throws IOException {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        JStyleAttributes cellStyle = column.getStyle();
        float fontSize = resolveFontSize(cellStyle, tableFontSize);
        float padding = resolvePadding(cellStyle);
        float lineHeight = lineHeight(fontSize);
        float contentWidth = Math.max(0f, width - padding * 2f);
        String alignment = resolveTextAlign(cellStyle);
        boolean header = "th".equalsIgnoreCase(column.getType());
        String colorValue = cellStyle == null ? null
                : (cellStyle.get("color") != null ? cellStyle.get("color") : cellStyle.get("fontColor"));
        PDColor defaultColor = header
                ? PdfBoxRenderAdapter.color("WHITE")
                : PdfBoxRenderAdapter.color("#333333");
        PDColor color = PdfBoxRenderAdapter.color(colorValue, defaultColor);
        float baseline = y + height - padding - fontSize;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                continue;
            }
            float lineWidth = textWidth(font, line, fontSize);
            float drawX = x + padding;
            if ("center".equalsIgnoreCase(alignment)) {
                drawX += Math.max(0f, (contentWidth - lineWidth) / 2f);
            } else if ("right".equalsIgnoreCase(alignment)) {
                drawX += Math.max(0f, contentWidth - lineWidth);
            }
            stream.beginText();
            stream.setFont(font, fontSize);
            if (color != null) {
                stream.setNonStrokingColor(color);
            }
            stream.setTextMatrix(Matrix.getTranslateInstance(drawX, baseline - i * lineHeight));
            stream.showText(line);
            stream.endText();
        }
    }

    /**
     * 解析表格宽度：优先使用声明的 width 样式，并约束在当前内容宽度内，
     * 避免表格超出页面被裁切。
     */
    private float resolveTableWidth(float availableWidth) {
        float declared = style == null ? -1f : PdfBoxUnitConverter.toPoint(style.get("width"), -1f);
        if (declared <= 0f) {
            return Math.max(0f, availableWidth);
        }
        return availableWidth > 0f ? Math.min(declared, availableWidth) : declared;
    }

    /**
     * 计算列宽：遵循单元格 width 声明并按比例缩放以填满表格宽度；
     * 未声明的列平分剩余空间；都未声明时等分。
     */
    private float[] resolveColumnWidths(int columnCount, float tableWidth) {
        float[] widths = new float[columnCount];
        float declaredTotal = 0f;
        int flexibleCount = 0;
        for (int i = 0; i < columnCount; i++) {
            float declared = resolveDeclaredColumnWidth(i);
            if (declared > 0f) {
                widths[i] = declared;
                declaredTotal += declared;
            } else {
                widths[i] = -1f;
                flexibleCount++;
            }
        }
        if (declaredTotal <= 0f) {
            float equal = tableWidth / columnCount;
            for (int i = 0; i < columnCount; i++) {
                widths[i] = equal;
            }
            return widths;
        }
        if (declaredTotal >= tableWidth) {
            float scale = tableWidth / declaredTotal;
            for (int i = 0; i < columnCount; i++) {
                if (widths[i] > 0f) {
                    widths[i] *= scale;
                }
            }
            return widths;
        }
        float remaining = tableWidth - declaredTotal;
        if (flexibleCount > 0) {
            float share = remaining / flexibleCount;
            for (int i = 0; i < columnCount; i++) {
                if (widths[i] < 0f) {
                    widths[i] = share;
                }
            }
        } else {
            float scale = tableWidth / declaredTotal;
            for (int i = 0; i < columnCount; i++) {
                widths[i] *= scale;
            }
        }
        return widths;
    }

    private float resolveDeclaredColumnWidth(int columnIndex) {
        for (JRowModel row : rows) {
            List<JColumnModel> columns = row.getColumnList();
            if (columns == null || columnIndex >= columns.size()) {
                continue;
            }
            JColumnModel column = columns.get(columnIndex);
            if (column.getStyle() == null) {
                continue;
            }
            float width = PdfBoxUnitConverter.toPoint(column.getStyle().get("width"), -1f);
            if (width > 0f) {
                return width;
            }
        }
        return -1f;
    }

    /**
     * 按可用宽度对单元格文本换行：中文逐字断开，英文仍按字符边界回退，
     * 保证长文本不会溢出单元格。
     */
    private List<String> wrapText(PDFont font, String text, float fontSize, float maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }
        if (maxWidth <= 0f) {
            lines.add(text);
            return lines;
        }
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            current.append(character);
            if (current.length() > 1 && textWidth(font, current.toString(), fontSize) > maxWidth) {
                current.deleteCharAt(current.length() - 1);
                lines.add(current.toString());
                current.setLength(0);
                current.append(character);
            }
        }
        if (current.length() > 0) {
            lines.add(current.toString());
        }
        return lines;
    }

    private float textWidth(PDFont font, String text, float fontSize) {
        try {
            return PdfBoxRenderAdapter.textWidth(font, text, fontSize, 0f, 0f);
        } catch (Exception e) {
            return text.length() * fontSize * 0.6f;
        }
    }

    private float lineHeight(float fontSize) {
        return fontSize * LINE_HEIGHT_RATIO;
    }

    private PDFont resolveFont(JQuickRenderContext context) {
        PDFont font = context.getFont();
        return font == null ? new PDType1Font(Standard14Fonts.FontName.HELVETICA) : font;
    }

    private float resolveFontSize(JStyleAttributes attributes, float fallback) {
        if (attributes == null) {
            return fallback;
        }
        String value = attributes.get("fontSize") != null ? attributes.get("fontSize") : attributes.get("font-size");
        if (value == null) {
            return fallback;
        }
        float size = PdfBoxUnitConverter.toPoint(value, fallback);
        return size > 0f ? size : fallback;
    }

    private float resolvePadding(JStyleAttributes attributes) {
        if (attributes == null) {
            return cellPadding;
        }
        String value = attributes.get("padding");
        if (value == null) {
            value = attributes.get("paddings");
        }
        if (value == null) {
            value = attributes.get("commonPadding");
        }
        if (value == null) {
            return cellPadding;
        }
        float padding = PdfBoxUnitConverter.toPoint(value, cellPadding);
        return padding >= 0f ? padding : cellPadding;
    }

    private String resolveTextAlign(JStyleAttributes attributes) {
        if (attributes == null) {
            return null;
        }
        String value = attributes.get("textAlign");
        return value != null ? value : attributes.get("textAlignment");
    }

    private PDColor resolveBackground(JStyleAttributes attributes) {
        if (attributes == null) {
            return null;
        }
        PDColor color = PdfBoxRenderAdapter.color(attributes.get("backgroundColor"), null);
        return color != null ? color : PdfBoxRenderAdapter.color(attributes.get("background"), null);
    }

    private PDColor resolveBorderColor(JStyleAttributes attributes) {
        if (attributes != null) {
            String border = attributes.get("border");
            if (border == null) {
                border = attributes.get("borderBottom");
            }
            if (border != null) {
                String[] parts = border.replace("'", "").trim().split("\\s+");
                for (int i = parts.length - 1; i >= 0; i--) {
                    PDColor color = PdfBoxRenderAdapter.color(parts[i], null);
                    if (color != null) {
                        return color;
                    }
                }
            }
        }
        return PdfBoxRenderAdapter.color("#DDDDDD");
    }

    private int resolveColumnCount() {
        for (JRowModel row : rows) {
            if (row.getColumnList() != null && !row.getColumnList().isEmpty()) {
                return row.getColumnList().size();
            }
        }
        return 0;
    }

    private String mergeText(List<Object> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object value : values) {
            if (value instanceof String) {
                builder.append(value);
            }
        }
        return builder.toString().trim();
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        cellPadding = parseFloat(style.get("padding"), cellPadding);
        rowHeight = parseFloat(style.get("height"), rowHeight);
    }

    private float parseFloat(Object value, float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value.toString().replace("px", "").trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

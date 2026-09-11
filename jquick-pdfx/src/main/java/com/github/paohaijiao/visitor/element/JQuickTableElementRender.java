package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class JQuickTableElementRender implements JQuickElementRender {

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
        float tableWidth = context.getWidth();
        float cellWidth = tableWidth / columnCount;
        float x = context.getCursorX();
        float y = context.getCursorY();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            JRowModel row = rows.get(rowIndex);
            List<JColumnModel> columns = row.getColumnList();
            if (columns == null) {
                continue;
            }
            float cellX = x;
            float cellY = y - rowHeight;
            for (JColumnModel column : columns) {
                boolean header = "th".equalsIgnoreCase(column.getType());
                drawCell(stream, cellX, cellY, cellWidth, rowHeight, header, rowIndex % 2 == 0 && !header);
                drawCellText(stream, context, column, cellX, cellY, cellWidth);
                cellX += cellWidth;
            }
            y = cellY;
        }
        context.setCursorY(y - 8f);
    }

    private void drawCell(PDPageContentStream stream, float x, float y, float width, float height, boolean header, boolean evenRow) throws IOException {
        if (header) {
            PDColor headerColor = PdfBoxRenderAdapter.color("#4285F4");
            if (headerColor != null) {
                stream.setNonStrokingColor(headerColor);
            }
            stream.addRect(x, y, width, height);
            stream.fill();
        } else if (evenRow) {
            PDColor rowColor = PdfBoxRenderAdapter.color("#F5F5F5");
            if (rowColor != null) {
                stream.setNonStrokingColor(rowColor);
            }
            stream.addRect(x, y, width, height);
            stream.fill();
        }
        PDColor borderColor = PdfBoxRenderAdapter.color("#DDDDDD");
        if (borderColor != null) {
            stream.setStrokingColor(borderColor);
        }
        stream.addRect(x, y, width, height);
        stream.stroke();
    }

    private void drawCellText(PDPageContentStream stream, JQuickRenderContext context, JColumnModel column, float x, float y, float width) throws IOException {
        String text = mergeText(column.getObject());
        if (text == null || text.isEmpty()) {
            return;
        }
        boolean header = "th".equalsIgnoreCase(column.getType());
        float fontSize = context.getFontSize() > 0 ? context.getFontSize() : 12f;
        float textY = y + rowHeight - cellPadding - fontSize;
        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
        PDColor color = header ? PdfBoxRenderAdapter.color("WHITE", null) : PdfBoxRenderAdapter.color("#333333", null);
        if (color != null) {
            stream.setNonStrokingColor(color);
        }
        stream.setTextMatrix(Matrix.getTranslateInstance(x + cellPadding, textY));
        stream.showText(text);
        stream.endText();
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

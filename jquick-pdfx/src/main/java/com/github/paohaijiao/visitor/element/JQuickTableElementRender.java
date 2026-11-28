package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.config.JTableConfig;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.model.table.JRowModel;
import com.github.paohaijiao.style.JQuickStyleEnum;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import com.github.paohaijiao.visitor.render.PdfBoxLayoutEngine;
import com.github.paohaijiao.visitor.render.PdfBoxRenderAdapter;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import com.github.paohaijiao.visitor.render.PdfBoxUnitConverter;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Data
public class JQuickTableElementRender implements JQuickElementRender {

    private static final float DEFAULT_FONT_SIZE = 12f;

    private static final float LINE_HEIGHT_RATIO = 1.35f;

    private static final float BORDER_WIDTH = 0.5f;

    /**
     * 数字/金额列判定：去除货币符号、千分位逗号与百分号后是纯数字即视为数值单元格，
     * 整列（除表头）均为数值时按右对齐绘制，与 HTML 数字列排版习惯一致。
     */
    private static final Pattern NUMERIC_VALUE = Pattern.compile("[+-]?\\d+(\\.\\d+)?");

    private List<JRowModel> rows = new ArrayList<>();

    private JStyleAttributes style;

    /** 表格级默认单元格 padding（pt），可被单元格样式覆盖；未声明时用 JTableConfig 默认值。 */
    private float cellPadding = 6f;

    private float rowHeight = 28f;

    private JTableConfig tableConfig = new JTableConfig();

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
        List<CellPlacement> placements = buildPlacements();
        int gridColumns = gridColumns(placements);
        if (gridColumns <= 0) {
            return;
        }
        float availableWidth = context.getWidth() > 0f ? context.getWidth() : context.getPageWidth();
        float tableWidth = resolveTableWidth(availableWidth);
        float[] columnWidths = resolveColumnWidths(gridColumns, tableWidth, placements);
        boolean[] numericColumns = resolveNumericColumns(gridColumns, placements);
        PDFont font = resolveFont(context);
        float tableFontSize = resolveFontSize(style, context.getFontSize() > 0f ? context.getFontSize() : DEFAULT_FONT_SIZE);
        // 逐单元格测量：宽度按跨列求和，行高取内容最大值
        Map<CellPlacement, CellMeasure> measures = new HashMap<>();
        float[] rowHeights = measureRows(placements, columnWidths, font, tableFontSize, context.getDocument(), measures);
        float x = context.getCursorX();
        float y = context.getCursorY();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            JRowModel row = rows.get(rowIndex);
            List<JColumnModel> columns = row.getColumnList();
            if (columns == null || columns.isEmpty()) {
                continue;
            }
            float needed = rowHeights[rowIndex];
            for (CellPlacement placement : placements) {
                if (placement.row == rowIndex && placement.rowSpan > 1) {
                    needed = Math.max(needed, spanHeight(rowHeights, placement.row, placement.rowSpan));
                }
            }
            PdfBoxLayoutEngine layoutEngine = context.getLayoutEngine();
            if (layoutEngine != null) {
                int pageNumber = context.getPageNumber();
                layoutEngine.ensureSpace(needed, true);
                if (context.getPageNumber() != pageNumber) {
                    stream = layoutEngine.getStream();
                    x = context.getCursorX();
                    y = context.getCursorY();
                }
            }
            float rowHeight = rowHeights[rowIndex];
            float rowBottom = y - rowHeight;
            for (CellPlacement placement : placements) {
                if (placement.row != rowIndex) {
                    continue;
                }
                CellMeasure measure = measures.get(placement);
                float cellX = x + spanWidth(columnWidths, 0, placement.col);
                float cellWidth = spanWidth(columnWidths, placement.col, placement.colSpan);
                // rowspan 单元格锚定整个跨行区间：顶端=起始行顶（y），底端=末行底
                float spannedHeight = placement.rowSpan == 1 ? rowHeight
                        : spanHeight(rowHeights, rowIndex, placement.rowSpan);
                float cellY = y - spannedHeight;
                float cellHeight = spannedHeight;
                boolean header = "th".equalsIgnoreCase(placement.column.getType());
                boolean striped = !header && rowIndex % 2 == 1;
                drawCell(stream, placement.column.getStyle(), cellX, cellY, cellWidth, cellHeight, header, striped);
                boolean numericColumn = placement.col < numericColumns.length && numericColumns[placement.col];
                drawCellText(stream, measure.chain, placement.column, numericColumn, cellX, cellY, cellWidth, cellHeight, measure.lineFragments, measure.lines, measure.model, tableFontSize);
            }
            y = rowBottom;
            context.setCursorY(y);
        }
        context.setCursorY(y - 8f);
    }

    /** 网格占位单元格：单元格模型、网格列起点与跨列/跨行数。 */
    private static final class CellPlacement {
        private final JColumnModel column;
        private final int row;
        private final int col;
        private final int colSpan;
        private final int rowSpan;

        private CellPlacement(JColumnModel column, int row, int col, int colSpan, int rowSpan) {
            this.column = column;
            this.row = row;
            this.col = col;
            this.colSpan = colSpan;
            this.rowSpan = rowSpan;
        }
    }

    /** 单元格测量结果：字体回退链、样式模型、折行片段与内容高度。 */
    private static final class CellMeasure {
        private List<PDFont> chain;
        private PdfBoxStyleModel model;
        private List<CellFragment[]> lineFragments;
        private List<String> lines;
    }

    /**
     * 把所有单元格安放到网格：逐行从列 0 向右扫描，跳过被前面单元格
     * （colspan 向右、rowspan 向下）占用的格子，落位后标记占位。
     */
    private List<CellPlacement> buildPlacements() {
        List<CellPlacement> placements = new ArrayList<>();
        Map<Integer, Set<Integer>> occupied = new HashMap<>();
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            JRowModel row = rows.get(rowIndex);
            List<JColumnModel> columns = row.getColumnList();
            if (columns == null || columns.isEmpty()) {
                continue;
            }
            int cursor = 0;
            for (JColumnModel column : columns) {
                int colSpan = Math.max(1, column.getColSpan());
                int rowSpan = Math.max(1, column.getRowSpan());
                while (isOccupied(occupied, rowIndex, cursor)) {
                    cursor++;
                }
                placements.add(new CellPlacement(column, rowIndex, cursor, colSpan, rowSpan));
                for (int r = rowIndex; r < rowIndex + rowSpan; r++) {
                    for (int c = cursor; c < cursor + colSpan; c++) {
                        occupied.computeIfAbsent(r, key -> new HashSet<>()).add(c);
                    }
                }
                cursor += colSpan;
            }
        }
        return placements;
    }

    private boolean isOccupied(Map<Integer, Set<Integer>> occupied, int row, int col) {
        Set<Integer> occupiedRow = occupied.get(row);
        return occupiedRow != null && occupiedRow.contains(col);
    }

    /** 网格总列数：所有单元格 (列起点 + 跨列数) 的最大值。 */
    private int gridColumns(List<CellPlacement> placements) {
        int max = 0;
        for (CellPlacement placement : placements) {
            max = Math.max(max, placement.col + placement.colSpan);
        }
        return max;
    }

    /** 列区间宽度求和：从 startCol 起 spanCount 列（越界部分忽略）。 */
    private float spanWidth(float[] widths, int startCol, int spanCount) {
        float sum = 0f;
        for (int i = startCol; i < Math.min(startCol + spanCount, widths.length); i++) {
            sum += widths[i];
        }
        return sum;
    }

    /** 行区间高度求和：从 startRow 起 spanCount 行。 */
    private float spanHeight(float[] rowHeights, int startRow, int spanCount) {
        float sum = 0f;
        for (int i = startRow; i < Math.min(startRow + spanCount, rowHeights.length); i++) {
            sum += rowHeights[i];
        }
        return sum;
    }

    /**
     * 测量所有单元格与行高：每行取「声明的行高」与「自身单元格内容高度」的较大值；
     * rowspan 单元格内容超过跨行总高时，把差额补到跨行区间的末行。
     * 测量结果（字体链、折行、内边距模型）缓存到 measures 供绘制阶段复用。
     */
    private float[] measureRows(List<CellPlacement> placements, float[] columnWidths, PDFont font,
                                float tableFontSize, org.apache.pdfbox.pdmodel.PDDocument document,
                                Map<CellPlacement, CellMeasure> measures) {
        float[] rowHeights = new float[rows.size()];
        for (CellPlacement placement : placements) {
            JColumnModel column = placement.column;
            float cellWidth = spanWidth(columnWidths, placement.col, placement.colSpan);
            float cellFontSize = resolveFontSize(column.getStyle(), tableFontSize);
            // 单元格内边距：单元格样式 > 表格级 cellPadding > PdfBoxStyleModel 内置默认值
            PdfBoxStyleModel cellModel = PdfBoxStyleModel.from(
                    column.getStyle(), column.getType(), cellPadding);
            float contentWidth = Math.max(1f,
                    cellWidth - cellModel.getPaddingLeft() - cellModel.getPaddingRight());
            // 单元格按自身 fontFamilyNames 回退链解析字体：表头中文、正文阿拉伯文
            // 各自命中正确字体，而不是统一使用表格级默认字体。
            List<PDFont> cellChain = PdfBoxRenderAdapter.resolveFontChain(
                    PdfBoxStyleModel.fromOriginal(column.getStyle()), font, document);
            List<CellFragment[]> lineFragments = fragmentsByLine(column);
            List<String> lines = new ArrayList<>();
            for (CellFragment[] fragments : lineFragments) {
                lines.addAll(PdfBoxRenderAdapter.wrapText(cellChain, lineText(fragments),
                        cellFontSize, contentWidth, 0f, 0f));
            }
            CellMeasure measure = new CellMeasure();
            measure.chain = cellChain;
            measure.model = cellModel;
            measure.lineFragments = lineFragments;
            measure.lines = lines;
            measures.put(placement, measure);
            float contentHeight = lines.size() * lineHeight(cellFontSize)
                    + cellModel.getPaddingTop() + cellModel.getPaddingBottom();
            rowHeights[placement.row] = Math.max(rowHeights[placement.row], contentHeight);
        }
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            rowHeights[rowIndex] = Math.max(rowHeights[rowIndex], resolveRowHeight(rows.get(rowIndex)));
        }
        // rowspan 内容不足总跨高时不 stretch；超出时差额补到末行，避免内容溢出
        for (CellPlacement placement : placements) {
            if (placement.rowSpan > 1) {
                CellMeasure measure = measures.get(placement);
                float contentHeight = measure.lines.size() * lineHeight(
                        resolveFontSize(placement.column.getStyle(), tableFontSize))
                        + measure.model.getPaddingTop() + measure.model.getPaddingBottom();
                float deficit = contentHeight - spanHeight(rowHeights, placement.row, placement.rowSpan);
                if (deficit > 0f) {
                    int lastRow = Math.min(placement.row + placement.rowSpan - 1, rows.size() - 1);
                    rowHeights[lastRow] += deficit;
                }
            }
        }
        return rowHeights;
    }

    /**
     * 行高：优先使用 {@code <tr style="height:...">} 为该行声明的行高，
     * 未声明时回退到表格级行高（默认 28）。行内文本过高时由调用方再取最大值。
     */
    private float resolveRowHeight(JRowModel row) {
        if (row.getStyle() == null) {
            return rowHeight;
        }
        float declared = PdfBoxUnitConverter.toPoint(JQuickStyleEnum.height.get(row.getStyle()), -1f);
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

    private void drawCellText(PDPageContentStream stream, List<PDFont> cellChain, JColumnModel column,
                              boolean numericColumn, float x, float y, float width, float height,
                              List<CellFragment[]> lineFragments, List<String> lines,
                              PdfBoxStyleModel cellModel, float tableFontSize) throws IOException {
        if (cellChain == null || cellChain.isEmpty() || lineFragments.isEmpty() || lines.isEmpty()) {
            return;
        }
        boolean header = "th".equalsIgnoreCase(column.getType());
        float fontSize = resolveFontSize(column.getStyle(), tableFontSize);
        float lineHeight = lineHeight(fontSize);
        float paddingLeft = cellModel.getPaddingLeft();
        float paddingTop = cellModel.getPaddingTop();
        float paddingBottom = cellModel.getPaddingBottom();
        float contentWidth = Math.max(0f, width - paddingLeft - cellModel.getPaddingRight());
        String colorValue = declaredColor(column.getStyle());
        // 对齐链：声明 > 数字列右对齐 > 表头居中 > 左对齐
        String alignment = resolveTextAlign(column.getStyle());
        if (alignment == null && numericColumn && isNumericRightAlignEnabled()) {
            alignment = "right";
        }
        if (alignment == null && header && isHeaderCenterAlignEnabled()) {
            alignment = "center";
        }
        // 垂直对齐：默认居中（对齐 HTML vertical-align:middle），声明 top/bottom 时按声明
        float textBlockHeight = lines.size() * lineHeight;
        float verticalGap = 0f;
        String vertical = cellModel.getVerticalAlignment();
        if (vertical == null || vertical.isEmpty() || "middle".equalsIgnoreCase(vertical)) {
            verticalGap = Math.max(0f, (height - paddingTop - paddingBottom - textBlockHeight) / 2f);
        } else if ("bottom".equalsIgnoreCase(vertical)) {
            verticalGap = Math.max(0f, height - paddingTop - paddingBottom - textBlockHeight);
        }
        float firstLineTop = y + height - paddingTop - verticalGap;
        int drawnLines = 0;
        for (CellFragment[] fragments : lineFragments) {
            String lineText = lineText(fragments);
            if (lineText.isEmpty()) {
                continue;
            }
            List<String> wrapped = PdfBoxRenderAdapter.wrapText(cellChain, lineText, fontSize, contentWidth, 0f, 0f);
            for (int i = 0; i < wrapped.size(); i++) {
                String line = wrapped.get(i);
                if (line.isEmpty()) {
                    continue;
                }
                float baseline = firstLineTop - drawnLines * lineHeight - fontSize;
                if (fragments.length == 1) {
                    drawFragment(stream, cellChain, cellModel, fontSize, alignment, fragments[0], colorValue, header, line, x + paddingLeft, baseline, contentWidth);
                } else {
                    if (wrapped.size() == 1) {
                        float cursor = x + paddingLeft;
                        for (CellFragment fragment : fragments) {
                            if (fragment.text.isEmpty()) {
                                continue;
                            }
                            float fragmentWidth = PdfBoxRenderAdapter.textWidth(cellChain, fragment.text,
                                    fontSize, 0f, 0f);
                            drawFragment(stream, cellChain, cellModel, fontSize, null, fragment,
                                    colorValue, header, fragment.text, cursor, baseline,
                                    Math.max(1f, fragmentWidth));
                            cursor += fragmentWidth;
                        }
                    } else {
                        drawFragment(stream, cellChain, cellModel, fontSize, null, fragments[0], colorValue, header, line, x + paddingLeft, baseline, contentWidth);
                    }
                }
                drawnLines++;
            }
        }
    }

    private void drawFragment(PDPageContentStream stream, List<PDFont> cellChain, PdfBoxStyleModel cellModel,
                              float fontSize, String alignment, CellFragment fragment, String cellColorValue,
                              boolean header, String line, float drawX, float baseline,
                              float availableWidth) throws IOException {
        PdfBoxStyleModel lineModel = new PdfBoxStyleModel();
        lineModel.setFontSize(fontSize);
        lineModel.setCharacterSpacing(cellModel.getCharacterSpacing());
        lineModel.setWordSpacing(cellModel.getWordSpacing());
        lineModel.setFont(cellModel.getFont());
        lineModel.setFontFamilyNames(cellModel.getFontFamilyNames());
        lineModel.setTextAlignment(alignment);
        lineModel.setBaseDirection(cellModel.getBaseDirection());
        lineModel.setFontColor(fragmentColorValue(fragment, cellColorValue, header));
        PdfBoxRenderAdapter.drawText(stream, lineModel, cellChain, line, drawX, baseline, availableWidth);
    }

    private String fragmentColorValue(CellFragment fragment, String cellColorValue, boolean header) {
        if (fragment.color != null && !fragment.color.trim().isEmpty()) {
            return fragment.color.trim();
        }
        if (cellColorValue != null && !cellColorValue.trim().isEmpty()) {
            return cellColorValue.trim();
        }
        return header ? "WHITE" : "#333333";
    }

    private String declaredColor(JStyleAttributes cellStyle) {
        if (cellStyle == null) {
            return null;
        }
        String color = JQuickStyleEnum.color.get(cellStyle);
        if (color == null) {
            color = JQuickStyleEnum.fontColor.get(cellStyle);
        }
        return color;
    }

    /** 一行的纯文本（多片段拼接，用于宽度测量与折行）。 */
    private String lineText(CellFragment[] fragments) {
        StringBuilder builder = new StringBuilder();
        for (CellFragment fragment : fragments) {
            builder.append(fragment.text);
        }
        return builder.toString();
    }

    /**
     * 单元格内容 → 按行分组的片段数组：String 值按 \n 拆分；行内子元素
     * （span 等）取其文本与字色成片段，换行符保持分行语义。
     */
    private List<CellFragment[]> fragmentsByLine(JColumnModel column) {
        List<Object> values = column.getObject();
        List<CellFragment[]> result = new ArrayList<>();
        if (values == null || values.isEmpty()) {
            return result;
        }
        List<CellFragment> currentLine = new ArrayList<>();
        for (Object value : values) {
            if (value == null) {
                continue;
            }
            if (value instanceof String) {
                appendFragments(currentLine, result, (String) value, null);
            } else if (value instanceof JQuickTextElementRender) {
                JQuickTextElementRender render = (JQuickTextElementRender) value;
                appendFragments(currentLine, result,
                        render.getContent() == null ? "" : render.getContent(),
                        render.getStyle() == null ? null : declaredColor(render.getStyle()));
            }
        }
        if (!currentLine.isEmpty()) {
            result.add(currentLine.toArray(new CellFragment[0]));
        }
        return result;
    }

    private void appendFragments(List<CellFragment> currentLine, List<CellFragment[]> result, String text, String color) {
        String[] parts = (text == null ? "" : text).split("\\n", -1);
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.add(currentLine.toArray(new CellFragment[0]));
                currentLine.clear();
            }
            String part = parts[i];
            if (!part.isEmpty()) {
                currentLine.add(new CellFragment(part, color));
            }
        }
    }

    private static final class CellFragment {

        private final String text;

        private final String color;

        private CellFragment(String text, String color) {
            this.text = text;
            this.color = color;
        }
    }

    /**
     * 数字/金额列判定：除表头外，某列（网格列，跳过跨列单元格）所有非空数据单元格
     * 均为数值/金额（可含货币符号、千分位逗号、百分号）时该列按数字列处理。
     */
    private boolean[] resolveNumericColumns(int columnCount, List<CellPlacement> placements) {
        boolean[] numeric = new boolean[columnCount];
        if (!isNumericRightAlignEnabled()) {
            return numeric;
        }
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            boolean hasValue = false;
            boolean allNumeric = true;
            for (CellPlacement placement : placements) {
                if (placement.col != columnIndex || placement.colSpan > 1) {
                    continue;
                }
                JColumnModel column = placement.column;
                if ("th".equalsIgnoreCase(column.getType())) {
                    continue;
                }
                String text = mergeText(column.getObject());
                if (text.isEmpty()) {
                    continue;
                }
                hasValue = true;
                if (!isNumericValue(text)) {
                    allNumeric = false;
                    break;
                }
            }
            numeric[columnIndex] = hasValue && allNumeric;
        }
        return numeric;
    }

    private boolean isNumericValue(String text) {
        String value = text.replace("¥", "").replace("$", "").replace("€", "")
                .replace("£", "").replace(",", "").replace("%", "").trim();
        return NUMERIC_VALUE.matcher(value).matches();
    }

    private boolean isNumericRightAlignEnabled() {
        return tableConfig == null || tableConfig.getNumericRightAlign() == null
                || tableConfig.getNumericRightAlign();
    }

    private boolean isHeaderCenterAlignEnabled() {
        return tableConfig == null || tableConfig.getHeaderCenterAlign() == null || tableConfig.getHeaderCenterAlign();
    }

    /**
     * 解析表格宽度：优先使用声明的 width 样式，并约束在当前内容宽度内，
     * 避免表格超出页面被裁切。
     */
    private float resolveTableWidth(float availableWidth) {
        float declared = style == null ? -1f : PdfBoxUnitConverter.toPoint(JQuickStyleEnum.width.get(style), -1f);
        if (declared <= 0f) {
            return Math.max(0f, availableWidth);
        }
        return availableWidth > 0f ? Math.min(declared, availableWidth) : declared;
    }

    /**
     * 计算列宽：遵循单元格 width 声明并按比例缩放以填满表格宽度；
     * 未声明的列平分剩余空间；都未声明时等分。
     */
    private float[] resolveColumnWidths(int columnCount, float tableWidth, List<CellPlacement> placements) {
        float[] widths = new float[columnCount];
        float declaredTotal = 0f;
        int flexibleCount = 0;
        for (int i = 0; i < columnCount; i++) {
            float declared = resolveDeclaredColumnWidth(i, placements);
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

    /**
     * 列宽声明：取该网格列上第一个「不跨列」单元格的 width 声明；
     * colspan 大于 1 的单元格不参与单列宽声明。
     */
    private float resolveDeclaredColumnWidth(int columnIndex, List<CellPlacement> placements) {
        for (CellPlacement placement : placements) {
            if (placement.col != columnIndex || placement.colSpan > 1) {
                continue;
            }
            JColumnModel column = placement.column;
            if (column.getStyle() == null) {
                continue;
            }
            float width = PdfBoxUnitConverter.toPoint(JQuickStyleEnum.width.get(column.getStyle()), -1f);
            if (width > 0f) {
                return width;
            }
        }
        return -1f;
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
        String value = JQuickStyleEnum.fontSize.getString(attributes, null);
        if (value == null) {
            return fallback;
        }
        float size = PdfBoxUnitConverter.toPoint(value, fallback);
        return size > 0f ? size : fallback;
    }

    private String resolveTextAlign(JStyleAttributes attributes) {
        if (attributes == null) {
            return null;
        }
        String value = JQuickStyleEnum.textAlign.getString(attributes, null);
        return value != null ? value : JQuickStyleEnum.textAlignment.getString(attributes, null);
    }

    private PDColor resolveBackground(JStyleAttributes attributes) {
        if (attributes == null) {
            return null;
        }
        PDColor color = PdfBoxRenderAdapter.color(JQuickStyleEnum.backgroundColor.getString(attributes, null), null);
        return color != null ? color : PdfBoxRenderAdapter.color(JQuickStyleEnum.background.getString(attributes, null), null);
    }

    private PDColor resolveBorderColor(JStyleAttributes attributes) {
        if (attributes != null) {
            String border = JQuickStyleEnum.border.getString(attributes, null);
            if (border == null) {
                border = JQuickStyleEnum.borderBottom.getString(attributes, null);
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

    private String mergeText(List<Object> values) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Object value : values) {
            if (value instanceof String) {
                builder.append(value);
            } else if (value instanceof JQuickTextElementRender) {
                builder.append(((JQuickTextElementRender) value).getContent());
            }
        }
        return builder.toString().trim();
    }

    private void applyStyle() {
        if (style == null) {
            return;
        }
        cellPadding = JQuickStyleEnum.padding.getFloat(style, cellPadding);
        rowHeight = JQuickStyleEnum.height.getFloat(style, rowHeight);
    }
}

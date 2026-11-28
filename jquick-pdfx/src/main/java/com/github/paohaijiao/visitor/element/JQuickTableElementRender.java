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
import java.util.List;
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

    /** 表格配置（数字列右对齐 / 表头居中 / 默认内边距），由 Visitor 从 JPdfConfig 注入。 */
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
        int columnCount = resolveColumnCount();
        if (columnCount <= 0) {
            return;
        }
        float availableWidth = context.getWidth() > 0f ? context.getWidth() : context.getPageWidth();
        float tableWidth = resolveTableWidth(availableWidth);
        float[] columnWidths = resolveColumnWidths(columnCount, tableWidth);
        boolean[] numericColumns = resolveNumericColumns(columnCount);
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
            List<List<CellFragment[]>> cellLineFragments = new ArrayList<>();
            List<List<String>> cellLines = new ArrayList<>();
            List<List<PDFont>> cellChains = new ArrayList<>();
            List<PdfBoxStyleModel> cellModels = new ArrayList<>();
            float rowTextHeight = 0f;
            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                JColumnModel column = columns.get(columnIndex);
                float columnWidth = columnWidths[Math.min(columnIndex, columnWidths.length - 1)];
                float cellFontSize = resolveFontSize(column.getStyle(), tableFontSize);
                // 单元格内边距：单元格样式 > 表格级 cellPadding > PdfBoxStyleModel 内置默认值
                PdfBoxStyleModel cellModel = PdfBoxStyleModel.from(
                        column.getStyle(), column.getType(), cellPadding);
                cellModels.add(cellModel);
                float contentWidth = Math.max(1f,
                        columnWidth - cellModel.getPaddingLeft() - cellModel.getPaddingRight());
                // 单元格按自身 fontFamilyNames 回退链解析字体：表头中文、正文阿拉伯文
                // 各自命中正确字体，而不是统一使用表格级默认字体。
                List<PDFont> cellChain = PdfBoxRenderAdapter.resolveFontChain(
                        PdfBoxStyleModel.fromOriginal(column.getStyle()), font, context.getDocument());
                cellChains.add(cellChain);
                // 内容按行分组后逐行折行：英文按单词边界断行，CJK 逐字断行
                List<CellFragment[]> lineFragments = fragmentsByLine(column);
                cellLineFragments.add(lineFragments);
                List<String> lines = new ArrayList<>();
                for (CellFragment[] fragments : lineFragments) {
                    lines.addAll(PdfBoxRenderAdapter.wrapText(cellChain, lineText(fragments),
                            cellFontSize, contentWidth, 0f, 0f));
                }
                cellLines.add(lines);
                float textBlockHeight = lines.size() * lineHeight(cellFontSize);
                rowTextHeight = Math.max(rowTextHeight,
                        textBlockHeight + cellModel.getPaddingTop() + cellModel.getPaddingBottom());
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
                boolean numericColumn = columnIndex < numericColumns.length && numericColumns[columnIndex];
                drawCellText(stream, cellChains.get(columnIndex), column, numericColumn,
                        cellX, cellY, columnWidth, currentRowHeight,
                        cellLineFragments.get(columnIndex), cellLines.get(columnIndex),
                        cellModels.get(columnIndex), tableFontSize);
                cellX += columnWidth;
            }
            y = cellY;
            context.setCursorY(y);
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

    /**
     * 单元格文本绘制：
     * 对齐优先级为 单元格声明 textAlignment &gt; 数字列右对齐（可配置关闭）&gt;
     * 表头居中（对齐 HTML UA 样式）&gt; 左对齐；垂直方向默认居中（对齐 HTML 的
     * vertical-align:middle），声明 verticalAlignment 时按声明。
     * <p>
     * 单元格内容支持行内子元素（span 等）：按声明顺序聚合为片段，每个片段恰好
     * 绘制一次（修复 Visitor 重复遍历造成的重复渲染与内容丢失），片段保留自身
     * 字色。单片段行参与整体折行；多片段行按声明顺序行内排布。
     */
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
            List<String> wrapped = PdfBoxRenderAdapter.wrapText(cellChain, lineText,
                    fontSize, contentWidth, 0f, 0f);
            for (int i = 0; i < wrapped.size(); i++) {
                String line = wrapped.get(i);
                if (line.isEmpty()) {
                    continue;
                }
                float baseline = firstLineTop - drawnLines * lineHeight - fontSize;
                if (fragments.length == 1) {
                    drawFragment(stream, cellChain, cellModel, fontSize, alignment, fragments[0],
                            colorValue, header, line, x + paddingLeft, baseline, contentWidth);
                } else {
                    // 多片段行：按声明顺序行内排布，每片段保留自身颜色；
                    // 跨片段折行时整行回退为单色（首片段色），保证不重叠
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
                        drawFragment(stream, cellChain, cellModel, fontSize, null, fragments[0],
                                colorValue, header, line, x + paddingLeft, baseline, contentWidth);
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

    /** 片段颜色值：片段自带 fontColor 优先，其次单元格声明色，最后表头白/正文深灰。 */
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

    private void appendFragments(List<CellFragment> currentLine, List<CellFragment[]> result,
                                 String text, String color) {
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

    /** 行内片段：文本 + 自身颜色。 */
    private static final class CellFragment {
        private final String text;
        private final String color;

        private CellFragment(String text, String color) {
            this.text = text;
            this.color = color;
        }
    }

    /**
     * 数字/金额列判定：除表头外，所有非空数据单元格均为数值/金额（可含货币符号、
     * 千分位逗号、百分号）时该列按数字列处理。
     */
    private boolean[] resolveNumericColumns(int columnCount) {
        boolean[] numeric = new boolean[columnCount];
        if (!isNumericRightAlignEnabled()) {
            return numeric;
        }
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            boolean hasValue = false;
            boolean allNumeric = true;
            for (JRowModel row : rows) {
                List<JColumnModel> columns = row.getColumnList();
                if (columns == null || columnIndex >= columns.size()) {
                    continue;
                }
                JColumnModel column = columns.get(columnIndex);
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
        return tableConfig == null || tableConfig.getHeaderCenterAlign() == null
                || tableConfig.getHeaderCenterAlign();
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

    /** 列数取各行最大列数：模板中行与行的单元格数可能不一致。 */
    private int resolveColumnCount() {
        int max = 0;
        for (JRowModel row : rows) {
            if (row.getColumnList() != null && row.getColumnList().size() > max) {
                max = row.getColumnList().size();
            }
        }
        return max;
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

package com.github.paohaijiao.element.table;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.model.table.JColumnModel;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXTableVisitor;
import com.github.paohaijiao.visitor.element.JQuickTableElementRender;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JTableSpanLayoutTest {

    private static final float COLUMN_1_X = 148.5f;

    private static final class Glyph {
        final String text;
        final float x;
        final float y;

        Glyph(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }

    private List<Glyph> glyphs = new ArrayList<>();

    private void render(String content) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeContent(content);
        glyphs = new ArrayList<>();
        try (PDDocument document = Loader.loadPDF(pdf)) {
            PDFTextStripper stripper = new PDFTextStripper() {
                @Override
                protected void writeString(String text, List<TextPosition> positions) {
                    if (positions == null || positions.isEmpty() || text == null || text.trim().isEmpty()) {
                        return;
                    }
                    glyphs.add(new Glyph(text.trim(), positions.get(0).getXDirAdj(),
                            positions.get(0).getYDirAdj()));
                }
            };
            stripper.setSortByPosition(true);
            stripper.getText(document);
        }
    }

    private Glyph glyph(String contains) {
        for (Glyph glyph : glyphs) {
            if (glyph.text.contains(contains)) {
                return glyph;
            }
        }
        return null;
    }

    private int count(String contains) {
        int count = 0;
        for (Glyph glyph : glyphs) {
            if (glyph.text.contains(contains)) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void spanAttributesParsedIntoModel() {
        JQuickTableElementRender table = parseTable(
                "<table><tr>"
                        + "<td colspan='2'>'a'</td>"
                        + "<td rowspan=3>'b'</td>"
                        + "<td>'c'</td>"
                        + "</tr></table>");
        List<JColumnModel> columns = table.getRows().get(0).getColumnList();
        assertEquals("colspan='2' 应解析为跨 2 列", 2, columns.get(0).getColSpan());
        assertEquals("rowspan=3（裸数字）应解析为跨 3 行", 3, columns.get(1).getRowSpan());
        assertEquals("未声明跨度的单元格默认跨 1 列", 1, columns.get(2).getColSpan());
        assertEquals("未声明跨度的单元格默认跨 1 行", 1, columns.get(2).getRowSpan());
    }

    @Test
    public void invalidSpanFallsBackToDefault() {
        JQuickTableElementRender table = parseTable(
                "<table><tr>"
                        + "<td colspan='0'>'a'</td>"
                        + "<td rowspan='-2'>'b'</td>"
                        + "<td colspan='abc'>'c'</td>"
                        + "</tr></table>");
        List<JColumnModel> columns = table.getRows().get(0).getColumnList();
        assertEquals("colspan='0' 应回退 1", 1, columns.get(0).getColSpan());
        assertEquals("rowspan='-2' 应回退 1", 1, columns.get(1).getRowSpan());
        assertEquals("colspan='abc' 应回退 1", 1, columns.get(2).getColSpan());
    }

    @Test
    public void colspanWidensCellAndAvoidsWrap() throws IOException {
        String sentence = "'Alpha Beta Gamma Delta'";
        render("<pdf><body><table style='width:300px'>"
                + "<tr><td colspan='2'>" + sentence + "</td></tr>"
                + "</table></body></pdf>");
        assertEquals("跨 2 列时句子应在整行宽度内单行显示", 1, wordPieces());

        render("<pdf><body><table style='width:300px'>"
                + "<tr><td>" + sentence + "</td><td>'x'</td></tr>"
                + "</table></body></pdf>");
        assertTrue("单列内的同一句子应因宽度不足而折行（片段数 > 1）", wordPieces() > 1);
    }

    private int wordPieces() {
        int count = 0;
        for (Glyph glyph : glyphs) {
            if (glyph.text.contains("Alpha") || glyph.text.contains("Gamma") || glyph.text.contains("Delta")) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void rowspanShiftsFollowingRowRight() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><th rowspan='2'>'类别'</th><th>'数值'</th></tr>"
                + "<tr><td>'b2'</td></tr>"
                + "<tr><td>'c1'</td><td>'c2'</td></tr>"
                + "</table></body></pdf>");
        Glyph b2 = glyph("b2");
        assertNotNull("rowspan 下一行的单元格应渲染", b2);
        assertTrue("b2 应落在列 1（被类别跨行占用列 0）: x=" + b2.x, b2.x > COLUMN_1_X);
        Glyph c1 = glyph("c1");
        assertNotNull(c1);
        assertTrue("跨行结束后 c1 应回到列 0: x=" + c1.x, c1.x < COLUMN_1_X);
        Glyph c2 = glyph("c2");
        assertNotNull(c2);
        assertTrue("c2 应落在列 1: x=" + c2.x, c2.x > COLUMN_1_X);
    }

    @Test
    public void mixedColspanAndRowspan() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><th colspan='2'>'汇总表头'</th></tr>"
                + "<tr><th rowspan='2'>'类别'</th><th>'数值'</th></tr>"
                + "<tr><td>'b2'</td></tr>"
                + "<tr><td colspan='2'>'合计'</td></tr>"
                + "</table></body></pdf>");
        Glyph header = glyph("汇总表头");
        assertNotNull("colspan 表头应渲染", header);
        Glyph category = glyph("类别");
        assertNotNull(category);
        Glyph value = glyph("数值");
        assertNotNull(value);
        assertTrue("类别基线应低于同起始行的数值基线: 类别y=" + category.y + " 数值y=" + value.y,
                category.y > value.y);
        Glyph b2 = glyph("b2");
        assertNotNull(b2);
        assertTrue("rowspan 下一行应落到列 1: x=" + b2.x, b2.x > COLUMN_1_X);
        Glyph total = glyph("合计");
        assertNotNull("末行 colspan 单元格应渲染", total);
        assertTrue("合计单元格应从列 0 开始: x=" + total.x, total.x < COLUMN_1_X);
    }

    @Test
    public void spanSample() throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeContent(
                "<pdf><body><table style='width:600px'>"
                        + "<tr><th colspan='2'>'项目汇总'</th></tr>"
                        + "<tr><th rowspan='2'>'类别'</th><th>'金额'</th></tr>"
                        + "<tr><td>'120.00'</td></tr>"
                        + "<tr><td>'人工'</td><td>'80.00'</td></tr>"
                        + "<tr><td colspan='2'>'合计 200.00'</td></tr>"
                        + "</table></body></pdf>");
        FileOutputStream out = new FileOutputStream("D:/test/table-span.pdf");
        out.write(pdf);
        out.close();
        assertTrue(true);
    }

    private JQuickTableElementRender parseTable(String tableMarkup) {
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(tableMarkup));
        JQuickPDFParser parser = new JQuickPDFParser(new CommonTokenStream(lexer));
        return new JPdfXTableVisitor().visitTable(parser.table());
    }
}

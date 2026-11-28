package com.github.paohaijiao.element.table;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTableConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JTableCellLayoutTest {

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
    private int pages;

    private void render(String content) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeContent(content);
        glyphs = new ArrayList<>();
        try (PDDocument document = Loader.loadPDF(pdf)) {
            pages = document.getNumberOfPages();
            PDFTextStripper stripper = new PDFTextStripper() {
                @Override
                protected void writeString(String text, List<TextPosition> positions) {
                    if (positions == null || positions.isEmpty() || text == null || text.trim().isEmpty()) {
                        return;
                    }
                    TextPosition first = positions.get(0);
                    glyphs.add(new Glyph(text.trim(), first.getXDirAdj(), first.getYDirAdj()));
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

    private String joined() {
        StringBuilder builder = new StringBuilder();
        for (Glyph glyph : glyphs) {
            builder.append(glyph.text).append('\n');
        }
        return builder.toString();
    }

    /** 修复①：默认内边距让文本离开单元格左边框（默认 6pt，换算自 96dpi 参考系）。 */
    @Test
    public void defaultCellPadding() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><th>'标题'</th><th>'金额'</th></tr>"
                + "<tr><td>'左对齐文本'</td><td>'100.00'</td></tr>"
                + "</table></body></pdf>");
        // 表格起点 = 页面内容区左边界（约 36pt）；文本 x 应明显大于列起点
        Glyph text = glyph("左对齐文本");
        assertTrue("单元格文本与左边框之间应有默认内边距: x=" + text.x, text.x >= 40f);
        Glyph header = glyph("标题");
        assertTrue("表头文本同样应有默认内边距: x=" + header.x, header.x >= 40f);
    }

    /** 修复①：单元格声明的 padding 覆盖默认值（padding:2px = 1.5pt）。 */
    @Test
    public void declaredPaddingOverridesDefault() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><td style='padding:2px'>'贴边文本'</td></tr>"
                + "</table></body></pdf>");
        Glyph text = glyph("贴边文本");
        // 300px 表格起点 36pt + 1.5pt padding ≈ 37.5pt，明显小于默认 6pt 时的 42pt
        assertTrue("声明的 padding:2px 应覆盖默认值: x=" + text.x, text.x < 40f);
        assertTrue("仍应保留声明的最小留白: x=" + text.x, text.x >= 36f + 1f);
    }

    /** 修复②：窄列英文在单词边界换行，每行都是完整单词，不得字母中间截断。 */
    @Test
    public void englishWrapsAtWordBoundary() throws IOException {
        render("<pdf><body><table style='width:90px'>"
                + "<tr><th>'Product'</th></tr>"
                + "<tr><td>'Gaming Mouse Ultra'</td></tr>"
                + "</table></body></pdf>");
        String all = joined();
        assertFalse("不得出现字母中断词 Mou|se: " + all,
                all.contains("Mou se") || all.contains("Mou\nse"));
        assertFalse("不得出现字母中断词 Ult|ra: " + all,
                all.contains("Ult ra") || all.contains("Ult\nra"));
        assertFalse("不得出现字母中断词 Gam|ing: " + all,
                all.contains("Gam ing") || all.contains("Gam\ning"));
        // 换行后仍是完整单词的组合
        assertTrue("行内容应为完整单词: " + all,
                all.contains("Gaming Mouse") || all.contains("Gaming") || all.contains("Mouse"));
    }

    /** 修复②（回归）：CJK 文本仍按字符断行，不受单词边界规则影响。 */
    @Test
    public void cjkStillWrapsPerCharacter() throws IOException {
        render("<pdf><body><table style='width:60px'>"
                + "<tr><td>'一二三四五六七八九十'</td></tr>"
                + "</table></body></pdf>");
        assertTrue("窄列 CJK 应正常折行", glyph("一二三") != null || glyph("一二") != null);
        assertTrue("全部文本都应写入 PDF: " + joined(), joined().contains("八九十") || joined().contains("九十"));
    }

    /** 修复③：数字/金额列自动右对齐，文本末端接近列右边界。 */
    @Test
    public void numericColumnRightAligned() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><th>'Item'</th><th>'Amount'</th></tr>"
                + "<tr><td>'Coffee'</td><td>'1,234.56'</td></tr>"
                + "</table></body></pdf>");
        Glyph amount = glyph("1,234.56");
        // 第二列区间 [112.5, 225]pt，右对齐时起点应大于列中点
        assertTrue("金额单元格应右对齐: x=" + amount.x, amount.x > 112.5f + 20f);
        Glyph item = glyph("Coffee");
        assertTrue("文本列仍应左对齐: x=" + item.x, item.x < 112.5f);
    }

    /** 修复③：配置可关闭数字列右对齐。 */
    @Test
    public void numericAlignCanBeDisabled() throws IOException {
        JTableConfig tableConfig = new JTableConfig();
        tableConfig.setNumericRightAlign(false);
        JPdfConfig config = new JPdfConfig();
        config.setTableConfig(tableConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(
                "<pdf><body><table style='width:300px'>"
                        + "<tr><th>'Item'</th><th>'Amount'</th></tr>"
                        + "<tr><td>'Coffee'</td><td>'1,234.56'</td></tr>"
                        + "</table></body></pdf>");
        List<Glyph> glyphs = new ArrayList<>();
        try (PDDocument document = Loader.loadPDF(pdf)) {
            PDFTextStripper stripper = new PDFTextStripper() {
                @Override
                protected void writeString(String text, List<TextPosition> positions) {
                    if (positions == null || positions.isEmpty() || text == null || text.trim().isEmpty()) {
                        return;
                    }
                    glyphs.add(new Glyph(text.trim(), positions.get(0).getXDirAdj(), positions.get(0).getYDirAdj()));
                }
            };
            stripper.setSortByPosition(true);
            stripper.getText(document);
        }
        Glyph amount = null;
        for (Glyph glyph : glyphs) {
            if (glyph.text.contains("1,234.56")) {
                amount = glyph;
            }
        }
        // 第二列起点 = 36 + 112.5 = 148.5pt；左对齐时文本起点 ≈ 148.5 + padding
        assertTrue("关闭配置后金额列应回到左对齐: x=" + amount.x, amount.x < 148.5f + 30f);
    }

    /** 修复④：td 内嵌套行内元素（span）按声明顺序恰好渲染一次。 */
    @Test
    public void nestedSpanRenderedExactlyOnce() throws IOException {
        render("<pdf><body><table><tr>"
                + "<td><span style='color:#333333'>'Apple'</span><br/><span style='color:#888888'>'Banana'</span></td>"
                + "<td>'plain'</td>"
                + "</tr></table></body></pdf>");
        assertEquals("Apple 只应渲染一次", 1, count("Apple"));
        assertEquals("Banana 只应渲染一次", 1, count("Banana"));
        assertEquals("同单元格后续列 plain 只应渲染一次", 1, count("plain"));
        // span 按声明顺序纵向排列（<br/> 折算为换行）
        Glyph apple = glyph("Apple");
        Glyph banana = glyph("Banana");
        assertTrue("span 应按声明顺序排列", apple.y < banana.y);
    }

    /** 修复④（回归）：纯文本 td 不受影响。 */
    @Test
    public void plainTextCellsUnaffected() throws IOException {
        render("<pdf><body><table>"
                + "<tr><td>'甲'</td><td>'乙'</td></tr>"
                + "</table></body></pdf>");
        assertEquals(1, count("甲"));
        assertEquals(1, count("乙"));
    }

    /** 修复⑤：th 默认居中（HTML UA 样式），td 默认左对齐。 */
    @Test
    public void headerCenteredByDefault() throws IOException {
        render("<pdf><body><table style='width:300px'>"
                + "<tr><th>'H'</th><th>'Amount'</th></tr>"
                + "<tr><td>'Left'</td><td>'42'</td></tr>"
                + "</table></body></pdf>");
        // 第一列区间 [36, 148.5]pt；居中的 "H"（约 9pt 宽）起点应明显大于列起点+padding
        Glyph header = glyph("H");
        assertTrue("th 应默认居中: x=" + header.x, header.x > 36f + 6f + 20f);
        Glyph left = glyph("Left");
        assertTrue("td 文本应保持左对齐: x=" + left.x, left.x < 60f);
    }
}

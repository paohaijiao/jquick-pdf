package com.github.paohaijiao.element.table;

import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 列表与表格元素的单元用例：{@code <list>} 列表、{@code <li>} 列表项、
 * {@code <table>} 表格、{@code <tr>} 表格行、{@code <td>} 数据单元格、{@code <th>} 表头单元格。
 * <p>
 * 每个用例渲染 {@code src/test/resources/sample/table} 下的模板（写法与 README 的列表/表格元素表一致），
 * 再从生成的 PDF 中提取文本坐标与内容流中的填充颜色，验证元素确实参与了表格/列表布局：
 * 列表项自上而下排列且带项目符号、单元格同行同基线并按列横向排开、表头行在数据行之上并使用约定的配色。
 * <p>
 * 注意：{@code <li>} 必须位于 {@code <list>} 内，{@code <td>}/{@code <th>} 必须位于 {@code <tr>} 内，
 * {@code <tr>} 必须位于 {@code <table>} 内，脱离父元素书写不会被解析。
 */
public class JTableElementTest {
    public static final String  path= JQuickConstant.path;

    /** 列表：列表项自上而下排列，每项左侧带有项目符号，文本相对页面左边界缩进。 */
    @Test
    public void list() throws IOException {
        Rendered rendered = render("sample/table/list.txt");
        assertEquals(1, rendered.pages);
        Glyph first = rendered.glyph("选项1");
        Glyph second = rendered.glyph("选项2");
        Glyph third = rendered.glyph("选项3");
        assertTrue("列表项应按声明顺序自上而下排列", first.y < second.y && second.y < third.y);
        assertEquals("列表项的行距应保持一致", second.y - first.y, third.y - second.y, 0.5f);
        assertTrue("列表文本应相对页面左边界缩进", first.x > 36f);
        assertTrue("每个列表项左侧都应绘制项目符号",
                rendered.glyphAt(36f, first.y) != null
                        && rendered.glyphAt(36f, second.y) != null
                        && rendered.glyphAt(36f, third.y) != null);
    }

    /** 列表项：{@code <li>} 自身的样式（此处为 fontColor:red）生效，且不污染其它列表项。 */
    @Test
    public void li() throws IOException {
        Rendered rendered = render("sample/table/li.txt");
        Glyph styled = rendered.glyph("红色选项");
        Glyph plain = rendered.glyph("普通选项");
        assertTrue("列表项应按声明顺序自上而下排列", styled.y < plain.y);
        assertTrue("li 声明的 fontColor:red 应生效", rendered.hasFillColor(1f, 0f, 0f));
    }

    /** 表格：表格渲染出行，且后续内容顺流排在表格下方而不重叠。 */
    @Test
    public void table() throws IOException {
        Rendered rendered = render("sample/table/table.txt");
        assertEquals(1, rendered.pages);
        Glyph data = rendered.glyph("数据");
        Glyph after = rendered.glyph("表格之后的内容");
        assertTrue("表格之后的内容应排在表格下方", after.y > data.y);
        assertTrue("表格应至少占据一行行高", after.y - data.y >= 25f);
    }

    /** 表格行：{@code <tr>} 声明的 height 决定该行行高，进而影响与下一行的间距。 */
    @Test
    public void tr() throws IOException {
        Rendered rendered = render("sample/table/tr.txt");
        Glyph first = rendered.glyph("第一行");
        Glyph second = rendered.glyph("第二行");
        assertTrue("表格行应按声明顺序自上而下排列", first.y < second.y);
        // 声明的 height:60px 按 CSS 参考分辨率（96dpi）换算为 45pt，作为该行的行高。
        assertEquals("tr 声明的 height:60px 应作为该行行高（60px = 45pt）", 45f, second.y - first.y, 1f);
    }

    /** 数据单元格：同行的多个 {@code <td>} 处于同一基线，并按列等宽横向排开。 */
    @Test
    public void td() throws IOException {
        Rendered rendered = render("sample/table/td.txt");
        Glyph a = rendered.glyph("甲");
        Glyph b = rendered.glyph("乙");
        Glyph c = rendered.glyph("丙");
        assertEquals("同行单元格应处于同一行", a.y, b.y, 1f);
        assertEquals("同行单元格应处于同一行", b.y, c.y, 1f);
        assertTrue("单元格应按列从左到右排开", a.x < b.x && b.x < c.x);
        assertEquals("未声明列宽时各列应等宽", b.x - a.x, c.x - b.x, 0.5f);
    }

    /** 表头单元格：{@code <th>} 所在行位于数据行之上，列与数据行对齐，并使用约定的表头配色。 */
    @Test
    public void th() throws IOException {
        Rendered rendered = render("sample/table/th.txt");
        Glyph firstHeader = rendered.glyph("标题一");
        Glyph secondHeader = rendered.glyph("标题二");
        Glyph firstData = rendered.glyph("数据一");
        Glyph secondData = rendered.glyph("数据二");
        assertTrue("表头行应位于数据行之上", firstHeader.y < firstData.y);
        assertTrue("表头列应从左到右排开", firstHeader.x < secondHeader.x);
        assertEquals("表头与数据列应对齐", firstHeader.x, firstData.x, 0.5f);
        assertEquals("表头与数据列应对齐", secondHeader.x, secondData.x, 0.5f);
        // 表头固定使用 #4285F4 背景 + 白色文字，数据行使用 #F5F5F5 斑马纹。
        assertTrue("表头应使用约定的蓝色背景 #4285F4", rendered.hasFillColor(0.2588f, 0.5216f, 0.9569f));
        assertTrue("表头文字应为白色", rendered.hasFillColor(1f, 1f, 1f));
    }

    /** 渲染模板并提取文本坐标、页数与填充颜色。 */
    private Rendered render(String resource) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource(resource);
        List<Glyph> glyphs = new ArrayList<>();
        List<float[]> fillColors = new ArrayList<>();
        int pages;
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
            fillColors.addAll(fillColors(document));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        fileOutputStream.write(pdf);
        return new Rendered(pages, glyphs, fillColors);
    }

    /**
     * 扫描每页内容流，返回所有非描边（填充）颜色。单元格背景与文字都使用填充绘制，
     * PDFBox 既可能写 {@code rg}（直接声明分量），也可能写 {@code CS} + {@code sc}（先切换色彩空间），两者都要识别。
     */
    private List<float[]> fillColors(PDDocument document) throws IOException {
        List<float[]> colors = new ArrayList<>();
        for (PDPage page : document.getPages()) {
            List<COSBase> operands = new ArrayList<>();
            for (Object token : new PDFStreamParser(page).parse()) {
                if (token instanceof Operator) {
                    String name = ((Operator) token).getName();
                    if (("rg".equals(name) || "sc".equals(name) || "scn".equals(name)) && operands.size() >= 3) {
                        int size = operands.size();
                        colors.add(new float[]{operand(operands.get(size - 3)), operand(operands.get(size - 2)),
                                operand(operands.get(size - 1))});
                    }
                    operands.clear();
                } else if (token instanceof COSBase) {
                    operands.add((COSBase) token);
                }
            }
        }
        return colors;
    }

    private float operand(COSBase base) {
        return base instanceof COSNumber ? ((COSNumber) base).floatValue() : 0f;
    }

    /** 一段已渲染的文本及其在页面中的位置（x/y 为元素左上角，y 自上而下增大）。 */
    private static final class Glyph {

        private final String text;
        private final float x;
        private final float y;

        private Glyph(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }

    /** 一次渲染的结果：页数、全部文本片段与填充颜色。 */
    private static final class Rendered {

        private final int pages;
        private final List<Glyph> glyphs;
        private final List<float[]> fillColors;

        private Rendered(int pages, List<Glyph> glyphs, List<float[]> fillColors) {
            this.pages = pages;
            this.glyphs = glyphs;
            this.fillColors = fillColors;
        }

        private Glyph glyph(String text) {
            for (Glyph glyph : glyphs) {
                if (glyph.text.equals(text)) {
                    return glyph;
                }
            }
            fail("未在渲染结果中找到文本：" + text + "，实际包含：" + texts());
            return null;
        }

        /** 查找指定坐标附近的文本片段，忽略具体字符（用于校验项目符号等无法稳定提取的符号）。 */
        private Glyph glyphAt(float x, float y) {
            for (Glyph glyph : glyphs) {
                if (Math.abs(glyph.x - x) <= 1f && Math.abs(glyph.y - y) <= 1f) {
                    return glyph;
                }
            }
            return null;
        }

        private boolean hasFillColor(float red, float green, float blue) {
            for (float[] color : fillColors) {
                if (Math.abs(color[0] - red) <= 0.02f && Math.abs(color[1] - green) <= 0.02f
                        && Math.abs(color[2] - blue) <= 0.02f) {
                    return true;
                }
            }
            return false;
        }

        private String texts() {
            StringBuilder builder = new StringBuilder();
            for (Glyph glyph : glyphs) {
                builder.append('[').append(glyph.text).append(']');
            }
            return builder.toString();
        }
    }
}

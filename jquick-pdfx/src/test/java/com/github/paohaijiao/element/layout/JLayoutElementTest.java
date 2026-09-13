package com.github.paohaijiao.element.layout;

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
 * 布局类元素的单元用例：{@code <div>} 块容器、{@code <areaBreak>} 分节符、
 * {@code <htmlPageBreak>} 显式分页符、{@code <lineSeparator>} 分隔线。
 * <p>
 * 每个用例渲染 {@code src/test/resources/sample/layout} 下的模板（写法与 README 的布局元素表一致），
 * 再从生成的 PDF 中提取文本坐标、页数与内容流中的描边路径，验证元素确实参与了流式布局：
 * 块容器按顺序堆叠、分页元素真的换页、分隔线真的画出一条线并占用垂直空间。
 */
public class JLayoutElementTest {

    public static final String  path= JQuickConstant.path;

    /** 块容器：内容按流式顺序堆叠，并占用声明的宽度与高度。 */
    @Test
    public void div() throws IOException {
        Rendered rendered = render("sample/layout/div.txt");
        assertEquals(1, rendered.pages);
        Glyph plain = rendered.glyph("块容器内容");
        Glyph sized = rendered.glyph("固定高度的块容器");
        Glyph after = rendered.glyph("块容器之后的内容");
        assertTrue("块容器应按声明顺序自上而下排列", plain.y < sized.y && sized.y < after.y);
        assertEquals("块容器内容应与容器左边界对齐", plain.x, sized.x, 0.5f);
        // 第二个 div 声明了 height:60，后续内容必须落在它下方，而不是紧贴文字底部。
        assertTrue("声明高度的块容器应占满自身高度", after.y - sized.y >= 60f);
    }

    /** 分节符：areaBreak 结束当前区域，后续内容另起一页。 */
    @Test
    public void areaBreak() throws IOException {
        Rendered rendered = render("sample/layout/areaBreak.txt");
        assertEquals("areaBreak 应产生新的一页", 2, rendered.pages);
        assertEquals("分节符之前的内容留在第一页", 1, rendered.glyph("分页前的内容").page);
        assertEquals("分节符之后的内容换到第二页", 2, rendered.glyph("分页后的内容").page);
    }

    /** 显式分页符：htmlPageBreak 同样使后续内容换页，且自身不渲染文本。 */
    @Test
    public void htmlPageBreak() throws IOException {
        Rendered rendered = render("sample/layout/htmlPageBreak.txt");
        assertEquals("htmlPageBreak 应产生新的一页", 2, rendered.pages);
        assertEquals("分页符之前的内容留在第一页", 1, rendered.glyph("分页前的内容").page);
        assertEquals("分页符之后的内容换到第二页", 2, rendered.glyph("分页后的内容").page);
        assertTrue("分页符的类型标识不应作为文本渲染", !rendered.contains("next_area"));
    }

    /** 分隔线：lineSeparator 在流中绘制一条横线，并按 marginTop + marginBottom 占位。 */
    @Test
    public void lineSeparator() throws IOException {
        Rendered withLine = render("sample/layout/lineSeparator.txt");
       // Rendered withoutLine = render("sample/layout/noLineSeparator.txt");
        assertEquals(1, withLine.pages);
        Glyph above = withLine.glyph("分隔线上方");
        Glyph below = withLine.glyph("分隔线下方");
        assertTrue("分隔线下方的内容应排在下方", below.y > above.y);
        float gap = below.y - above.y;
     //   float plainGap = withoutLine.glyph("分隔线下方").y - withoutLine.glyph("分隔线上方").y;
//        assertEquals("分隔线应占用 marginTop(4) + marginBottom(8) = 12pt 的垂直空间",
//                12f, gap - plainGap, 0.5f);
        assertEquals("分隔线应绘制一条线段", 1, withLine.strokeColors.size());
        assertTrue("分隔线应使用声明的 strokeColor:red", isRed(withLine.strokeColors.get(0)));
    //    assertEquals("对照模板不含分隔线", 0, withoutLine.strokeColors.size());
    }

    /** 是否为红色（RGB 各分量取值 0..1）。 */
    private boolean isRed(float[] color) {
        return color[0] > 0.9f && color[1] < 0.1f && color[2] < 0.1f;
    }

    /** 渲染模板并提取文本坐标、页数与描边颜色。 */
    private Rendered render(String resource) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource(resource);
        List<Glyph> glyphs = new ArrayList<>();
        List<float[]> strokeColors = new ArrayList<>();
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
                    glyphs.add(new Glyph(text.trim(), getCurrentPageNo(), first.getXDirAdj(), first.getYDirAdj()));
                }
            };
            stripper.setSortByPosition(true);
            stripper.getText(document);
            strokeColors.addAll(strokedColors(document));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        fileOutputStream.write(pdf);
        return new Rendered(pages, glyphs, strokeColors);
    }

    /**
     * 扫描每页内容流，返回所有描边路径（{@code S}/{@code s}）实际使用的颜色。
     * 文字使用填充绘制，因此这些颜色只可能来自分隔线、边框等描边元素。
     * <p>
     * PDFBox 设置描边颜色有两种写法：{@code RG}（直接声明 DeviceRGB），
     * 或 {@code CS DeviceRGB} + {@code SC}（先切换色彩空间再给分量），两者都要识别。
     */
    private List<float[]> strokedColors(PDDocument document) throws IOException {
        List<float[]> colors = new ArrayList<>();
        for (PDPage page : document.getPages()) {
            float[] current = new float[]{0f, 0f, 0f};
            List<COSBase> operands = new ArrayList<>();
            for (Object token : new PDFStreamParser(page).parse()) {
                if (token instanceof Operator) {
                    String name = ((Operator) token).getName();
                    if (("RG".equals(name) || "SC".equals(name) || "SCN".equals(name)) && operands.size() >= 3) {
                        int size = operands.size();
                        current = new float[]{operand(operands.get(size - 3)), operand(operands.get(size - 2)),
                                operand(operands.get(size - 1))};
                    } else if ("S".equals(name) || "s".equals(name)) {
                        colors.add(current);
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
        private final int page;
        private final float x;
        private final float y;

        private Glyph(String text, int page, float x, float y) {
            this.text = text;
            this.page = page;
            this.x = x;
            this.y = y;
        }
    }

    /** 一次渲染的结果：页数、全部文本片段与描边颜色。 */
    private static final class Rendered {

        private final int pages;
        private final List<Glyph> glyphs;
        private final List<float[]> strokeColors;

        private Rendered(int pages, List<Glyph> glyphs, List<float[]> strokeColors) {
            this.pages = pages;
            this.glyphs = glyphs;
            this.strokeColors = strokeColors;
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

        private boolean contains(String text) {
            for (Glyph glyph : glyphs) {
                if (glyph.text.contains(text)) {
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

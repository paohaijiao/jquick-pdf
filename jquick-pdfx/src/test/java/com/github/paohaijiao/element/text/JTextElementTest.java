package com.github.paohaijiao.element.text;

import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 文本类元素的单元用例：{@code <p>} 段落、{@code <h1>}~{@code <h6>} 标题、{@code <span>} 内联文本、
 * {@code <br>} 换行、{@code <tab>} 制表符。
 * <p>
 * 每个用例渲染 {@code src/test/resources/sample/text} 下的模板（写法与 {@code sample/textArea.txt} 一致），
 * 再从生成的 PDF 中提取文本及其坐标，验证元素确实被渲染、流向正确且彼此不重叠。
 * <p>
 * 说明：{@code <br type="after"></br>} 这种写法当前语法不支持（{@code <br>} 仅接受可选的 style 属性），
 * 换行请使用 {@code <br>} 或 {@code <br style="...">}；{@code <br>} 需要出现在 {@code <p>} 或 {@code <span>}
 * 的行内内容中，直接写在 {@code <body>} 下不会被解析。
 */
public class JTextElementTest {

    public static final String  path= JQuickConstant.path;


    /** 段落：纯文本段落应用自身样式；段落内的 span/tab 与文本按行内流依次排布。 */
    @Test
    public void paragraph() throws IOException {
        Rendered rendered = render("sample/text/paragraph.txt");
        assertEquals(1, rendered.pages);
        Glyph plain = rendered.glyph("段落内容");
        Glyph inline = rendered.glyph("段落内的内联文本");
        Glyph indented = rendered.glyph("制表符后的文本");
        // 三个段落自上而下排列，且都从页面左边界开始（内联文本与制表符后的文本不会顶到行首之外）。
        assertTrue("段落应按声明顺序自上而下排列", plain.y < inline.y && inline.y < indented.y);
        assertEquals("段落内的行内文本与段落左边界对齐", plain.x, inline.x, 0.5f);
        assertTrue("制表符应把后续文本推到右侧", indented.x > plain.x + 40f);
    }

    /** 标题：h1~h6 依次排列，字号逐级减小（用字高间接验证）。 */
    @Test
    public void heading() throws IOException {
        Rendered rendered = render("sample/text/heading.txt");
        assertEquals(1, rendered.pages);
        String[] titles = {"一级标题", "二级标题", "三级标题", "四级标题", "五级标题", "六级标题"};
        Glyph previous = null;
        for (String title : titles) {
            Glyph current = rendered.glyph(title);
            if (previous != null) {
                assertTrue(title + "应排在上一级标题之下", current.y > previous.y);
                assertTrue(title + "字号应小于上一级标题", current.height <= previous.height);
            }
            previous = current;
        }
    }

    /** 内联文本：span 可独立作为块级内容渲染。 */
    @Test
    public void span() throws IOException {
        Rendered rendered = render("sample/text/span.txt");
        assertEquals(1, rendered.pages);
        rendered.glyph("内联文本");
    }

    /** 换行：br 使后面的文本另起一行，并回到段落左边界。 */
    @Test
    public void br() throws IOException {
        Rendered rendered = render("sample/text/br.txt");
        assertEquals(1, rendered.pages);
        Glyph first = rendered.glyph("第一行文本");
        Glyph second = rendered.glyph("第二行文本");
        assertTrue("br 之后的文本应换到下一行", second.y > first.y);
        assertEquals("br 之后的文本应回到行首", first.x, second.x, 0.5f);
        assertTrue("br 不应作为文本渲染", !rendered.contains("br"));
    }

    /** 制表符：tab 只横向推进光标，按声明的宽度叠加。 */
    @Test
    public void tab() throws IOException {
        Rendered rendered = render("sample/text/tab.txt");
        assertEquals(1, rendered.pages);
        Glyph text = rendered.glyph("制表符后的文本");
        // 模板为 48px 的 tab + 默认 24 的 tab，文本因此右移 72pt，且仍与段落同行。
        Glyph plain = render("sample/text/paragraph.txt").glyph("段落内容");
        assertEquals("两个制表符应使文本右移 72pt", plain.x + 72f, text.x, 1f);
    }

    /** 渲染模板并提取文本及坐标。 */
    private Rendered render(String resource) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource(resource);
        List<Glyph> glyphs = new ArrayList<>();
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
                    glyphs.add(new Glyph(text.trim(), first.getXDirAdj(), first.getYDirAdj(), first.getHeightDir()));
                }
            };
            stripper.setSortByPosition(true);
            stripper.getText(document);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path+"test.pdf");
        fileOutputStream.write(pdf);
        return new Rendered(pages, glyphs);
    }

    /** 一段已渲染的文本及其在页面中的位置（x/y 为元素左上角，y 自上而下增大）。 */
    private static final class Glyph {

        private final String text;
        private final float x;
        private final float y;
        private final float height;

        private Glyph(String text, float x, float y, float height) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    /** 一次渲染的结果：页数与全部文本片段。 */
    private static final class Rendered {

        private final int pages;
        private final List<Glyph> glyphs;

        private Rendered(int pages, List<Glyph> glyphs) {
            this.pages = pages;
            this.glyphs = glyphs;
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

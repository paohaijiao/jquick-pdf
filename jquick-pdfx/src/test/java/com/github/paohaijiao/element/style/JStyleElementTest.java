package com.github.paohaijiao.element.style;

import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXStyleVisitor;
import com.github.paohaijiao.visitor.render.PdfBoxStyleModel;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 样式属性的单元用例：边距（margin*）、内边距（padding*）、对齐与布局、尺寸与旋转角度。
 * <p>
 * 每个属性都同时验证两种声明写法：
 * <ul>
 *     <li>驼峰写法，如 {@code marginLeft}、{@code minHeight}（历史写法）；</li>
 *     <li>标准 CSS/HTML 写法，如 {@code margin-left}、{@code min-height}（kebab-case）。</li>
 * </ul>
 * 写法统一由 {@link JStyleAttributes} 规范为驼峰键，因此两种写法必须得到完全相同的样式模型。
 * <p>
 * 用例分两层：
 * <ol>
 *     <li>样式解析层：用真实解析器把 {@code style="..."} 解析为 {@link JStyleAttributes}，
 *         再断言 {@link PdfBoxStyleModel} 上对应字段的取值，覆盖全部属性；</li>
 *     <li>渲染层：渲染 {@code <div>} 后从 PDF 内容流中提取文本坐标与背景矩形，
 *         断言边距、内边距、尺寸与旋转确实作用到了页面上。</li>
 * </ol>
 * 说明：{@code spacingRatio}、{@code keepWithNext} 目前只在样式模型中解析，渲染器尚未消费它们
 * （{@code keepTogether} 已用于分页预留空间），因此只用解析层用例覆盖；
 * {@code verticalAlignment} 已由 {@code <div>} 渲染器消费，另有渲染层用例。
 */
public class JStyleElementTest {

    public static final String path = JQuickConstant.path;

    /** 页面左上角的默认内容起点（A4、默认 36pt 页边距）。 */
    private static final float PAGE_ORIGIN = 36f;

    /** 渲染层用例使用的文本，用引号包裹以走字符串取值分支。 */
    private static final String TEXT = "内容";


    /** {@code marginLeft/Right/Top/Bottom}：驼峰与标准写法都应解析为同一个值。 */
    @Test
    public void marginSides() {
        PdfBoxStyleModel camel = model("marginLeft:1px;marginRight:2px;marginTop:3px;marginBottom:4px");
        assertEquals(0.75f, camel.getMarginLeft(), 0.01f);
        assertEquals(1.5f, camel.getMarginRight(), 0.01f);
        assertEquals(2.25f, camel.getMarginTop(), 0.01f);
        assertEquals(3f, camel.getMarginBottom(), 0.01f);

        PdfBoxStyleModel standard = model("margin-left:1px;margin-right:2px;margin-top:3px;margin-bottom:4px");
        assertEquals("标准写法 margin-left 应与 marginLeft 等价", camel.getMarginLeft(), standard.getMarginLeft(), 0.01f);
        assertEquals("标准写法 margin-right 应与 marginRight 等价", camel.getMarginRight(), standard.getMarginRight(), 0.01f);
        assertEquals("标准写法 margin-top 应与 marginTop 等价", camel.getMarginTop(), standard.getMarginTop(), 0.01f);
        assertEquals("标准写法 margin-bottom 应与 marginBottom 等价", camel.getMarginBottom(), standard.getMarginBottom(), 0.01f);
    }

    /** {@code commonMargin} 与 {@code margins} 简写：按上、右、下、左展开，单边声明优先于简写。 */
    @Test
    public void marginShorthands() {
        PdfBoxStyleModel common = model("commonMargin:100px");
        assertEquals(75f, common.getMarginTop(), 0.01f);
        assertEquals(75f, common.getMarginRight(), 0.01f);
        assertEquals(75f, common.getMarginBottom(), 0.01f);
        assertEquals(75f, common.getMarginLeft(), 0.01f);

        PdfBoxStyleModel four = model("margins:'20px 30px 40px 50px'");
        assertEquals(15f, four.getMarginTop(), 0.01f);
        assertEquals(22.5f, four.getMarginRight(), 0.01f);
        assertEquals(30f, four.getMarginBottom(), 0.01f);
        assertEquals(37.5f, four.getMarginLeft(), 0.01f);

        PdfBoxStyleModel one = model("margins:10px");
        assertEquals(7.5f, one.getMarginTop(), 0.01f);
        assertEquals(7.5f, one.getMarginLeft(), 0.01f);

        // 单边声明优先于简写；margins 优先于 commonMargin。
        PdfBoxStyleModel override = model("margins:'20px 30px 40px 50px';marginLeft:100px");
        assertEquals(15f, override.getMarginTop(), 0.01f);
        assertEquals(75f, override.getMarginLeft(), 0.01f);

        PdfBoxStyleModel shorthandFirst = model("commonMargin:100px;margins:10px");
        assertEquals(7.5f, shorthandFirst.getMarginTop(), 0.01f);
    }


    /** {@code paddingLeft/Right/Top/Bottom}：驼峰与标准写法都应解析为同一个值。 */
    @Test
    public void paddingSides() {
        PdfBoxStyleModel camel = model("paddingLeft:1px;paddingRight:2px;paddingTop:3px;paddingBottom:4px");
        assertEquals(0.75f, camel.getPaddingLeft(), 0.01f);
        assertEquals(1.5f, camel.getPaddingRight(), 0.01f);
        assertEquals(2.25f, camel.getPaddingTop(), 0.01f);
        assertEquals(3f, camel.getPaddingBottom(), 0.01f);

        PdfBoxStyleModel standard = model("padding-left:1px;padding-right:2px;padding-top:3px;padding-bottom:4px");
        assertEquals("标准写法 padding-left 应与 paddingLeft 等价", camel.getPaddingLeft(), standard.getPaddingLeft(), 0.01f);
        assertEquals("标准写法 padding-right 应与 paddingRight 等价", camel.getPaddingRight(), standard.getPaddingRight(), 0.01f);
        assertEquals("标准写法 padding-top 应与 paddingTop 等价", camel.getPaddingTop(), standard.getPaddingTop(), 0.01f);
        assertEquals("标准写法 padding-bottom 应与 paddingBottom 等价", camel.getPaddingBottom(), standard.getPaddingBottom(), 0.01f);
    }

    /** {@code commonPadding} 与 {@code paddings} 简写：按上、右、下、左展开，单边声明优先于简写。 */
    @Test
    public void paddingShorthands() {
        PdfBoxStyleModel common = model("commonPadding:50px");
        assertEquals(37.5f, common.getPaddingTop(), 0.01f);
        assertEquals(37.5f, common.getPaddingRight(), 0.01f);
        assertEquals(37.5f, common.getPaddingBottom(), 0.01f);
        assertEquals(37.5f, common.getPaddingLeft(), 0.01f);

        PdfBoxStyleModel four = model("paddings:'50px 50px 60px 70px'");
        assertEquals(37.5f, four.getPaddingTop(), 0.01f);
        assertEquals(37.5f, four.getPaddingRight(), 0.01f);
        assertEquals(45f, four.getPaddingBottom(), 0.01f);
        assertEquals(52.5f, four.getPaddingLeft(), 0.01f);

        PdfBoxStyleModel override = model("paddings:'50px 50px 60px 70px';paddingLeft:100px");
        assertEquals(37.5f, override.getPaddingTop(), 0.01f);
        assertEquals(75f, override.getPaddingLeft(), 0.01f);
    }

    /** {@code verticalAlignment}、{@code spacingRatio}、{@code keepTogether}、{@code keepWithNext}。 */
    @Test
    public void layoutProperties() {
        PdfBoxStyleModel camel = model("verticalAlignment:top;spacingRatio:30;keepTogether:true;keepWithNext:true");
        assertEquals("top", camel.getVerticalAlignment());
        assertEquals(30f, camel.getSpacingRatio(), 0.01f);
        assertTrue(camel.isKeepTogether());
        assertTrue(camel.isKeepWithNext());

        PdfBoxStyleModel standard = model("vertical-align:middle;spacing-ratio:40;keep-together:yes;keep-with-next:yes");
        assertEquals("middle", standard.getVerticalAlignment());
        assertEquals(40f, standard.getSpacingRatio(), 0.01f);
        assertTrue("标准写法 keep-together 应生效", standard.isKeepTogether());
        assertTrue("标准写法 keep-with-next 应生效", standard.isKeepWithNext());

        PdfBoxStyleModel defaults = model("verticalAlignment:bottom");
        assertEquals("bottom", defaults.getVerticalAlignment());
        assertFalse(defaults.isKeepTogether());

        // text-align 的内部键名是 textAlignment 而非 textAlign，标准写法同样必须生效。
        assertEquals("center", model("text-align:center").getTextAlignment());
        assertEquals("justify", model("textAlignment:justify").getTextAlignment());
    }


    /** {@code width/height/maxHeight/minHeight/minWidth/maxWidth}：驼峰与标准写法都应解析为同一个值。 */
    @Test
    public void sizeProperties() {
        PdfBoxStyleModel camel = model("width:100px;height:200px;maxHeight:300px;minHeight:400px;minWidth:500px;maxWidth:600px");
        assertEquals(75f, camel.getWidth(), 0.01f);
        assertEquals(150f, camel.getHeight(), 0.01f);
        assertEquals(225f, camel.getMaxHeight(), 0.01f);
        assertEquals(300f, camel.getMinHeight(), 0.01f);
        assertEquals(375f, camel.getMinWidth(), 0.01f);
        assertEquals(450f, camel.getMaxWidth(), 0.01f);

        PdfBoxStyleModel standard = model("width:100px;height:200px;max-height:300px;min-height:400px;min-width:500px;max-width:600px");
        assertEquals("标准写法 min-width 应与 minWidth 等价", camel.getMinWidth(), standard.getMinWidth(), 0.01f);
        assertEquals("标准写法 max-width 应与 maxWidth 等价", camel.getMaxWidth(), standard.getMaxWidth(), 0.01f);
        assertEquals("标准写法 min-height 应与 minHeight 等价", camel.getMinHeight(), standard.getMinHeight(), 0.01f);
        assertEquals("标准写法 max-height 应与 maxHeight 等价", camel.getMaxHeight(), standard.getMaxHeight(), 0.01f);
        assertEquals(camel.getWidth(), standard.getWidth(), 0.01f);
        assertEquals(camel.getHeight(), standard.getHeight(), 0.01f);
    }


    /** {@code angleInRadians}：驼峰与标准写法都应解析为同一个弧度值。 */
    @Test
    public void angleInRadians() {
        assertEquals(30f, model("angleInRadians:30").getAngleInRadians(), 0.01f);
        assertEquals(0.5f, model("angle-in-radians:0.5").getAngleInRadians(), 0.01f);
        assertEquals("未声明时旋转角度为 0", 0f, model("width:10px").getAngleInRadians(), 0.01f);
    }


    /** {@code marginLeft}/{@code marginTop} 应真实把内容推离原位。 */
    @Test
    public void marginShiftsContent() throws IOException {
        Rendered plain = renderText("");
        Rendered shifted = renderText("marginLeft:100px;marginTop:50px");
        Glyph base = plain.glyph(TEXT);
        Glyph moved = shifted.glyph(TEXT);
        assertEquals("marginLeft:100px 应使内容右移 75pt", 75f, moved.x - base.x, 0.5f);
        assertEquals("marginTop:50px 应使内容下移 37.5pt", 37.5f, moved.y - base.y, 0.5f);
    }

    /** {@code commonMargin}、{@code margins} 简写应与逐边声明得到相同的排版结果。 */
    @Test
    public void marginShorthandMatchesPerSide() throws IOException {
        Glyph base = renderText("").glyph(TEXT);
        Glyph common = renderText("commonMargin:100px").glyph(TEXT);
        assertEquals("commonMargin:100px 应四边同时生效（x）", 75f, common.x - base.x, 0.5f);
        assertEquals("commonMargin:100px 应四边同时生效（y）", 75f, common.y - base.y, 0.5f);

        Glyph shorthand = renderText("margins:'20px 30px 40px 50px'").glyph(TEXT);
        Glyph perSide = renderText("marginTop:20px;marginRight:30px;marginBottom:40px;marginLeft:50px").glyph(TEXT);
        assertEquals("margins 简写应与逐边声明得到相同 x", perSide.x, shorthand.x, 0.01f);
        assertEquals("margins 简写应与逐边声明得到相同 y", perSide.y, shorthand.y, 0.01f);
        assertEquals("margins 简写左边距 50px 应使内容右移 37.5pt", 37.5f, shorthand.x - base.x, 0.5f);
        assertEquals("margins 简写上边距 20px 应使内容下移 15pt", 15f, shorthand.y - base.y, 0.5f);
    }

    /** {@code paddingLeft} 应把内容推入盒内，盒宽由 {@code width} 决定。 */
    @Test
    public void paddingOffsetsContentInsideBox() throws IOException {
        Rendered rendered = renderText("backgroundColor:red;width:300px;paddingLeft:40px;paddingTop:20px");
        float[] box = rendered.rect(0);
        assertEquals("width:300px 应换算为 225pt 的盒宽", 225f, box[2], 0.5f);
        assertEquals("盒左边界应为默认内容起点", PAGE_ORIGIN, box[0], 0.5f);
        Glyph glyph = rendered.glyph(TEXT);
        assertEquals("paddingLeft:40px 应使文字相对盒左边界内缩 30pt", box[0] + 30f, glyph.x, 1f);
    }

    /** {@code width}/{@code height} 与 {@code min-*}/{@code max-*} 应决定背景盒的实际尺寸。 */
    @Test
    public void boxSizeFollowsDimensions() throws IOException {
        float[] fixed = renderText("backgroundColor:red;width:300px;height:200px").rect(0);
        assertEquals("width:300px → 225pt", 225f, fixed[2], 0.5f);
        assertEquals("height:200px → 150pt", 150f, fixed[3], 0.5f);

        float[] minHeight = renderText("backgroundColor:red;height:20px;min-height:300px").rect(0);
        assertEquals("min-height:300px 应把盒高抬到 225pt", 225f, minHeight[3], 0.5f);

        float[] maxHeight = renderText("backgroundColor:red;height:300px;max-height:100px").rect(0);
        assertEquals("max-height:100px 应把盒高压到 75pt", 75f, maxHeight[3], 0.5f);

        float[] minWidth = renderText("backgroundColor:red;width:100px;min-width:300px").rect(0);
        assertEquals("min-width:300px 应把盒宽抬到 225pt", 225f, minWidth[2], 0.5f);

        float[] maxWidth = renderText("backgroundColor:red;width:400px;max-width:200px").rect(0);
        assertEquals("max-width:200px 应把盒宽压到 150pt", 150f, maxWidth[2], 0.5f);
    }

    /** 标准写法与驼峰写法必须渲染出完全一致的版面。 */
    @Test
    public void standardNamesRenderIdenticallyToCamelCase() throws IOException {
        Rendered camel = renderText("backgroundColor:red;width:250px;min-height:200px;max-width:400px;"
                + "marginLeft:40px;marginTop:20px;paddingLeft:30px;paddingTop:10px");
        Rendered standard = renderText("backgroundColor:red;width:250px;min-height:200px;max-width:400px;"
                + "margin-left:40px;margin-top:20px;padding-left:30px;padding-top:10px");
        Glyph camelGlyph = camel.glyph(TEXT);
        Glyph standardGlyph = standard.glyph(TEXT);
        assertEquals("两种写法的文字 x 应一致", camelGlyph.x, standardGlyph.x, 0.01f);
        assertEquals("两种写法的文字 y 应一致", camelGlyph.y, standardGlyph.y, 0.01f);
        float[] camelBox = camel.rect(0);
        float[] standardBox = standard.rect(0);
        assertEquals("两种写法的盒宽应一致", camelBox[2], standardBox[2], 0.01f);
        assertEquals("两种写法的盒高应一致", camelBox[3], standardBox[3], 0.01f);
    }

    /** {@code verticalAlignment} 应决定内容在声明高度内的垂直位置：top/middle/bottom 依次下移。 */
    @Test
    public void verticalAlignmentOffsetsContentInsideBox() throws IOException {
        float top = lowestGlyphY(renderText("backgroundColor:red;height:120px;verticalAlignment:top"));
        float middle = lowestGlyphY(renderText("backgroundColor:red;height:120px;verticalAlignment:middle"));
        float bottom = lowestGlyphY(renderText("backgroundColor:red;height:120px;verticalAlignment:bottom"));
        assertTrue("middle 的内容应比 top 靠下", middle > top);
        assertTrue("bottom 的内容应比 middle 靠下", bottom > middle);
        assertEquals("middle 应位于 top 与 bottom 的正中间", (top + bottom) / 2f, middle, 0.5f);

        float standard = lowestGlyphY(renderText("backgroundColor:red;height:120px;vertical-align:bottom"));
        assertEquals("标准写法 vertical-align:bottom 应与 verticalAlignment:bottom 等价", bottom, standard, 0.01f);
    }

    /** {@code angleInRadians} 应把文本旋转矩阵写成非单位矩阵。 */
    @Test
    public void angleRotatesText() throws IOException {
        float[] rotated = renderText("angleInRadians:0.5").textMatrix(0);
        assertEquals("旋转矩阵 a 分量应为 cos(0.5)", (float) Math.cos(0.5), rotated[0], 0.01f);
        assertEquals("旋转矩阵 b 分量应为 sin(0.5)", (float) Math.sin(0.5), rotated[1], 0.01f);

        float[] plain = renderText("").textMatrix(0);
        assertEquals("未声明旋转时文本矩阵应为单位矩阵", 0f, plain[1], 0.001f);
    }


    /**
     * 样例：渲染 {@code src/test/resources/sample/style/style.txt}（覆盖清单中的全部样式属性），
     * 并用 {@link FileOutputStream} 输出到本地 {@code D:\test\style.pdf}（目录取自 {@code JQuickConstant.path}），
     * 可直接打开 PDF 肉眼核对效果。
     * <p>
     * 输出文件名为 {@code style.pdf} 而非其它元素用例共用的 {@code test.pdf}，避免与它们互相覆盖。
     * <p>
     * 模板中每段都声明了 {@code backgroundColor}，便于观察盒子边界：margin 是盒外空白、padding 是盒内空白。
     * 第 5 段演示标准写法（{@code margin-left}、{@code min-height}）与驼峰写法（{@code marginTop}）可以混用。
     */
    @Test
    public void styleSample() throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource("sample/style/style.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(path + "style.pdf");
        fileOutputStream.write(pdf);
    }
    @Test
    public void styleSample1() throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource("sample/style/style1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(path + "style1.pdf");
        fileOutputStream.write(pdf);
    }

    /** 把 {@code style="..."} 交给真实解析器，得到样式声明的键值集合。 */
    private static JStyleAttributes style(String declaration) {
        String input = "style=\"" + declaration + "\"";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        JQuickPDFParser parser = new JQuickPDFParser(new CommonTokenStream(lexer));
        return new JPdfXStyleVisitor().visitStyleEle(parser.styleEle());
    }

    /** 解析样式声明并规范化，得到渲染层实际消费的样式模型。 */
    private static PdfBoxStyleModel model(String declaration) {
        return PdfBoxStyleModel.from(style(declaration));
    }

    /** 渲染只含一个 {@code <div>} 的模板；{@code declaration} 为空时表示不声明样式。 */
    private Rendered renderText(String declaration) throws IOException {
        String style = declaration.isEmpty() ? "" : " style=\"" + declaration + "\"";
        return render("<div" + style + ">" + "'" + TEXT + "'</div>");
    }

    /**
     * 取文本 {@link #TEXT} 最下方（y 最大）的一处位置。
     * <p>
     * 可见盒子（有背景/边框）会先试排版一遍以量取内容高度，同一段文字因此在内容流中出现两次：
     * 一次在内容区顶部（随后被背景覆盖），一次在最终位置。文本提取只认坐标，不认遮挡，
     * 所以取最下方的一处才是内容的真实位置。
     */
    private float lowestGlyphY(Rendered rendered) {
        float y = Float.NaN;
        for (Glyph glyph : rendered.glyphs) {
            if (TEXT.equals(glyph.text)) {
                y = Float.isNaN(y) ? glyph.y : Math.max(y, glyph.y);
            }
        }
        assertFalse("未在渲染结果中找到文本：" + TEXT, Float.isNaN(y));
        return y;
    }

    /** 渲染 {@code body} 内片段并提取文本坐标、背景矩形与文本矩阵。 */
    private Rendered render(String body) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeContent("<pdf><body>" + body + "</body></pdf>");
        Rendered rendered = new Rendered();
        try (PDDocument document = Loader.loadPDF(pdf)) {
            rendered.pages = document.getNumberOfPages();
            PDFTextStripper stripper = new PDFTextStripper() {
                @Override
                protected void writeString(String text, List<TextPosition> positions) {
                    if (positions == null || positions.isEmpty() || text == null || text.trim().isEmpty()) {
                        return;
                    }
                    TextPosition first = positions.get(0);
                    rendered.glyphs.add(new Glyph(text.trim(), first.getXDirAdj(), first.getYDirAdj()));
                }
            };
            stripper.setSortByPosition(true);
            stripper.getText(document);
            for (PDPage page : document.getPages()) {
                collect(page, rendered);
            }
        }
        return rendered;
    }

    /** 扫描内容流，收集矩形（{@code re}）与文本矩阵（{@code Tm}）。 */
    private void collect(PDPage page, Rendered rendered) throws IOException {
        List<COSBase> operands = new ArrayList<>();
        for (Object token : new PDFStreamParser(page).parse()) {
            if (token instanceof Operator) {
                String name = ((Operator) token).getName();
                if ("re".equals(name) && operands.size() >= 4) {
                    int size = operands.size();
                    rendered.rects.add(new float[]{operand(operands.get(size - 4)), operand(operands.get(size - 3)),
                            operand(operands.get(size - 2)), operand(operands.get(size - 1))});
                } else if ("Tm".equals(name) && operands.size() >= 6) {
                    int size = operands.size();
                    float[] matrix = new float[6];
                    for (int i = 0; i < 6; i++) {
                        matrix[i] = operand(operands.get(size - 6 + i));
                    }
                    rendered.textMatrices.add(matrix);
                }
                operands.clear();
            } else if (token instanceof COSBase) {
                operands.add((COSBase) token);
            }
        }
    }

    private float operand(COSBase base) {
        return base instanceof COSNumber ? ((COSNumber) base).floatValue() : 0f;
    }

    /** 一段已渲染的文本及其位置（y 自上而下增大）。 */
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

    /** 一次渲染的结果：页数、文本片段、背景矩形与文本矩阵。 */
    private static final class Rendered {

        private int pages;
        private final List<Glyph> glyphs = new ArrayList<>();
        private final List<float[]> rects = new ArrayList<>();
        private final List<float[]> textMatrices = new ArrayList<>();

        private Glyph glyph(String text) {
            for (Glyph glyph : glyphs) {
                if (glyph.text.equals(text)) {
                    return glyph;
                }
            }
            fail("未在渲染结果中找到文本：" + text);
            return null;
        }

        /** 第 index 个背景矩形 {@code [x, y, width, height]}。 */
        private float[] rect(int index) {
            assertTrue("应至少绘制 " + (index + 1) + " 个矩形，实际为 " + rects.size(), rects.size() > index);
            return rects.get(index);
        }

        /** 第 index 个文本矩阵 {@code [a, b, c, d, e, f]}。 */
        private float[] textMatrix(int index) {
            assertTrue("应至少写入 " + (index + 1) + " 个文本矩阵，实际为 " + textMatrices.size(),
                    textMatrices.size() > index);
            return textMatrices.get(index);
        }
    }
}

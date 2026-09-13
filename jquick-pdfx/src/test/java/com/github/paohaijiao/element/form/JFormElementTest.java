package com.github.paohaijiao.element.form;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 表单类元素的单元用例：{@code <button>} 按钮、{@code <checkbox>} 复选框、
 * {@code <comboBoxField>} 下拉选择框、{@code <textArea>} 多行文本输入框。
 * <p>
 * 每个用例渲染 {@code src/test/resources/sample/form} 下的模板（写法与 README 的表单元素表一致），
 * 再从生成的 PDF 中提取文本坐标、内容流算子与填充色，验证元素确实按文档语义绘制：
 * 控件外框尺寸、内容落在框内、勾选/箭头等装饰被画出、{@code fontColor} 生效。
 * <p>
 * 注意：{@code <inputField>}（文本输入框）目前不在解析器语法中（词法里没有 {@code <inputField} 字面量，
 * 也无对应语法规则），模板无法解析，因此暂无对应用例。
 */
public class JFormElementTest {

    public static final String path = JQuickConstant.path;

    /** 按钮：绘制按钮外框，文本居中落在框内，fontColor 作为文字填充色生效。 */
    @Test
    public void button() throws IOException {
        Rendered rendered = render("sample/form/button.txt");
        assertEquals(1, rendered.pages);
        Glyph label = rendered.glyph("提交");
        float[] box = rendered.rect(0);
        assertEquals("按钮默认宽度为 96pt", 96f, box[2], 0.5f);
        assertEquals("按钮默认高度为 28pt", 28f, box[3], 0.5f);
        assertTrue("按钮文本应落在按钮外框内", rendered.inside(label, box));
        assertTrue("按钮文本应相对按钮左边界内缩", label.x > box[0]);
        assertTrue("fontColor:blue 应作为按钮文字填充色生效", rendered.hasFillColor(0f, 0f, 1f));
    }

    /** 复选框：绘制方形选框，checked 时额外绘制勾选符号，标签排在选框右侧。 */
    @Test
    public void checkbox() throws IOException {
        Rendered rendered = render("sample/form/checkbox.txt");
        assertEquals(1, rendered.pages);
        Glyph label = rendered.glyph("提交");
        float[] box = rendered.rect(0);
        assertEquals("复选框默认边长为 12pt", 12f, box[2], 0.5f);
        assertEquals("复选框默认边长为 12pt", 12f, box[3], 0.5f);
        assertTrue("标签应绘制在复选框右侧", label.x > box[0] + box[2]);
        assertTrue("checked 应绘制勾选符号", rendered.operatorCount("l") >= 2);
        assertTrue("fontColor:blue 应作为标签文字填充色生效", rendered.hasFillColor(0f, 0f, 1f));
    }

    /** 下拉选择框：绘制矩形外框与下拉箭头，当前值绘制在框内左侧。 */
    @Test
    public void comboBoxField() throws IOException {
        Rendered rendered = render("sample/form/comboBoxField.txt");
        assertEquals(1, rendered.pages);
        Glyph value = rendered.glyph("提交");
        float[] box = rendered.rect(0);
        assertEquals("下拉框默认宽度为 120pt", 120f, box[2], 0.5f);
        assertEquals("下拉框默认高度为 24pt", 24f, box[3], 0.5f);
        assertTrue("显示值应落在下拉框内", rendered.inside(value, box));
        assertTrue("显示值应相对下拉框左边界内缩", value.x > box[0]);
        assertTrue("应绘制下拉箭头", rendered.operatorCount("l") >= 2);
        assertTrue("fontColor:blue 应作为显示值填充色生效", rendered.hasFillColor(0f, 0f, 1f));
    }

    /** 多行文本输入框：绘制文本区域外框，内容绘制在框内左上角。 */
    @Test
    public void textArea() throws IOException {
        Rendered rendered = render("sample/form/textArea.txt");
        assertEquals(1, rendered.pages);
        Glyph content = rendered.glyph("你好中国");
        float[] box = rendered.rect(0);
        assertEquals("多行文本框默认宽度为 180pt", 180f, box[2], 0.5f);
        assertEquals("多行文本框默认高度为 72pt", 72f, box[3], 0.5f);
        assertTrue("文本内容应落在文本框内", rendered.inside(content, box));
        assertTrue("文本内容应相对文本框左边界内缩", content.x > box[0]);
        // 模板未声明 fontColor，内容应使用默认的黑色填充绘制（外框是描边，不产生填充色）。
        assertTrue("文本内容应使用默认填充色绘制", rendered.hasFillColor(0f, 0f, 0f));
    }

    /** 渲染模板并提取文本坐标、页数、填充色、内容流算子与外框矩形。 */
    private Rendered render(String resource) throws IOException {
        byte[] pdf = new JQuickPdfFactory().executeResource(resource);
        List<Glyph> glyphs = new ArrayList<>();
        List<float[]> fillColors = new ArrayList<>();
        Map<String, Integer> operators = new HashMap<>();
        List<float[]> boxes = new ArrayList<>();
        int pages;
        float pageHeight;
        try (PDDocument document = Loader.loadPDF(pdf)) {
            pages = document.getNumberOfPages();
            pageHeight = document.getPage(0).getMediaBox().getHeight();
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
            for (PDPage page : document.getPages()) {
                collect(page, fillColors, operators, boxes);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "test.pdf");
        fileOutputStream.write(pdf);
        return new Rendered(pages, pageHeight, glyphs, fillColors, operators, boxes);
    }

    /**
     * 扫描单页内容流，收集填充色（{@code rg}/{@code sc}/{@code scn}）、算子计数与矩形（{@code re}）。
     * PDFBox 既可能直接写 {@code rg}，也可能先切色彩空间再写 {@code sc}，两种写法都要识别。
     */
    private void collect(PDPage page, List<float[]> fillColors, Map<String, Integer> operators,
                         List<float[]> boxes) throws IOException {
        List<COSBase> operands = new ArrayList<>();
        for (Object token : new PDFStreamParser(page).parse()) {
            if (token instanceof Operator) {
                String name = ((Operator) token).getName();
                operators.merge(name, 1, Integer::sum);
                if (("rg".equals(name) || "sc".equals(name) || "scn".equals(name)) && operands.size() >= 3) {
                    int size = operands.size();
                    fillColors.add(new float[]{operand(operands.get(size - 3)), operand(operands.get(size - 2)),
                            operand(operands.get(size - 1))});
                } else if ("re".equals(name) && operands.size() >= 4) {
                    int size = operands.size();
                    boxes.add(new float[]{operand(operands.get(size - 4)), operand(operands.get(size - 3)),
                            operand(operands.get(size - 2)), operand(operands.get(size - 1))});
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

    /** 一段已渲染的文本及其位置（x/y 为元素左上角，y 自上而下增大）。 */
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

    /** 一次渲染的结果：页数、页面高度、文本片段、填充色、算子计数与外框矩形。 */
    private static final class Rendered {

        private final int pages;
        private final float pageHeight;
        private final List<Glyph> glyphs;
        private final List<float[]> fillColors;
        private final Map<String, Integer> operators;
        private final List<float[]> boxes;

        private Rendered(int pages, float pageHeight, List<Glyph> glyphs, List<float[]> fillColors,
                         Map<String, Integer> operators, List<float[]> boxes) {
            this.pages = pages;
            this.pageHeight = pageHeight;
            this.glyphs = glyphs;
            this.fillColors = fillColors;
            this.operators = operators;
            this.boxes = boxes;
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

        /** 第 index 个矩形外框，返回 PDF 用户空间下的 {@code [x, y, width, height]}。 */
        private float[] rect(int index) {
            assertTrue("应至少绘制 " + (index + 1) + " 个矩形外框，实际为 " + boxes.size(), boxes.size() > index);
            return boxes.get(index);
        }

        /** 判断文本是否落在矩形外框内（把 PDF 自下而上的 y 换算为自上而下的 y 后比较）。 */
        private boolean inside(Glyph glyph, float[] box) {
            float top = pageHeight - (box[1] + box[3]);
            float bottom = pageHeight - box[1];
            return glyph.x >= box[0] && glyph.x <= box[0] + box[2] && glyph.y >= top && glyph.y <= bottom;
        }

        private int operatorCount(String name) {
            return operators.getOrDefault(name, 0);
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

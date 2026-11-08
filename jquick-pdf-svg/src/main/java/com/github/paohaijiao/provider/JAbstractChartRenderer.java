/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.provider;

import com.github.paohaijiao.JOption;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public abstract class JAbstractChartRenderer implements JChartRenderer {

    protected static final Color BACKGROUND_COLOR = Color.WHITE;

    protected static final Color AXIS_COLOR = Color.BLACK;

    protected static final Font TITLE_FONT = new Font("Microsoft YaHei", Font.BOLD, 18);

    protected static final Font LABEL_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);

    /**
     * XML declaration used by every generated document.
     * <p>
     * The encoding is stated explicitly because the Batik SVG generator only writes
     * {@code <?xml version="1.0"?>} when it streams to a Writer. Without the attribute a
     * browser falls back to UTF-8 to decode the file, so a document written in the platform
     * charset fails with "Encoding error" as soon as it contains Chinese text.
     */
    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public void render(JOption option, String outputPath) throws IOException {
        String svgContent = renderToString(option);
        writeUtf8(svgContent, new File(outputPath));
    }

    /**
     * Writes the document as UTF-8, matching the encoding declared at the head of the file.
     *
     * @param content SVG document
     * @param file    target file, the parent directory is created when missing
     * @throws IOException when the file cannot be written
     */
    protected static void writeUtf8(String content, File file) throws IOException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("cannot create directory: " + parent.getAbsolutePath());
        }
        try (Writer out = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            out.write(content);
        }
    }

    @Override
    public String renderToString(JOption option) throws IOException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        int width = getDefaultWidth();
        int height = getDefaultHeight();
        svgGenerator.setSVGCanvasSize(new Dimension(width, height));
        svgGenerator.setPaint(BACKGROUND_COLOR);
        svgGenerator.fillRect(0, 0, width, height);
        drawChart(svgGenerator, option, width, height);
        try (StringWriter out = new StringWriter()) {
            svgGenerator.stream(out, true);
            return withUtf8Declaration(out.toString());
        }
    }

    /**
     * Forces the XML declaration of a generated document to declare UTF-8.
     * <p>
     * Batik streams to a Writer, which carries characters rather than bytes, so it omits the
     * encoding attribute. Adding it back keeps every caller that writes the string as UTF-8
     * safe, whether the bytes come from this class or from the caller.
     *
     * @param svg generated document
     * @return document with an explicit UTF-8 declaration
     */
    protected static String withUtf8Declaration(String svg) {
        if (svg == null || svg.isEmpty()) {
            return svg;
        }
        int end = svg.indexOf("?>");
        if (!svg.startsWith("<?xml") || end < 0) {
            return XML_DECLARATION + svg;
        }
        String declaration = svg.substring(0, end);
        declaration = declaration.replaceAll("encoding\\s*=\\s*(\"[^\"]*\"|'[^']*')", "encoding=\"UTF-8\"");
        if (declaration.indexOf("encoding") < 0) {
            declaration = declaration + " encoding=\"UTF-8\"";
        }
        return declaration + "?>" + svg.substring(end + 2);
    }

    /**
     * 获取默认画布宽度
     *
     * @return 默认宽度
     */
    protected int getDefaultWidth() {
        return 800;
    }

    /**
     * 获取默认画布高度
     *
     * @return 默认高度
     */
    protected int getDefaultHeight() {
        return 600;
    }

    /**
     * 绘制图表内容
     *
     * @param svgGenerator SVG图形生成器
     * @param option       图表配置选项
     * @param width        画布宽度
     * @param height       画布高度
     */
    protected abstract void drawChart(SVGGraphics2D svgGenerator, JOption option, int width, int height);

    /**
     * 绘制图表标题
     *
     * @param svgGenerator SVG图形生成器
     * @param option       图表配置选项
     * @param width        画布宽度
     */
    protected void drawTitle(SVGGraphics2D svgGenerator, JOption option, int width) {
        if (option.title() != null && option.title().text() != null) {
            svgGenerator.setFont(TITLE_FONT);
            svgGenerator.setPaint(Color.BLACK);
            String title = option.title().text();
            svgGenerator.drawString(title, width / 2 - svgGenerator.getFontMetrics().stringWidth(title) / 2, 40);
            if (option.title().subtext() != null) {
                svgGenerator.setFont(LABEL_FONT);
                String subTitle = option.title().subtext();
                svgGenerator.drawString(subTitle, width / 2 - svgGenerator.getFontMetrics().stringWidth(subTitle) / 2, 80);
            }
        }
    }
}

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
package com.github.paohaijiao;

import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXCommonVisitor;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.github.paohaijiao.param.JContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

/**
 * packageName com.github.paohaijiao
 *
 * @author Martin
 * @version 1.0.0
 * @className JValueTest
 * @date 2025/6/22
 * @description
 */
public class JTableTest {

    @Test
    public void file() throws IOException {
        String input = "<html>\n" +
                "<head>\n" +
                "  <style>\n" +
                "    @page{\n" +
                "      size: A4;\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "    body {\n" +
                "      width: '210mm';\n" +
                "      height: '297mm';\n" +
                "      margin: 0;\n" +
                "      padding: '10mm'; \n" +
                "      'box-sizing': 'border-box';\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table >\n" +
                "        <tr>\n" +
                "            <th style=\"font-family:simhei\">编号</th>\n" +
                "            <th>姓名</th>\n" +
                "            <th>年龄</th>\n" +
                "            <th>城市</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>1</td>\n" +
                "            <td>张三</td>\n" +
                "            <td>28</td>\n" +
                "            <td>北京</td>\n" +
                "        </tr>\n" +
                "        \n" +
                "        <tr>\n" +
                "            <td>2</td>\n" +
                "            <td>李四</td>\n" +
                "            <td>32</td>\n" +
                "            <td>上海</td>\n" +
                "        </tr>\n" +
                "        \n" +
                "        <tr>\n" +
                "            <td>3</td>\n" +
                "            <td>王五</td>\n" +
                "            <td>25</td>\n" +
                "            <td>广州</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.document();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        String key=(String) visitor.visit(tree);
        System.out.println("pdf generate");
    }

    @Test
    public void buildFile() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("d://test//DivBasedHeadings.pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph("Main content goes here after the styled header."));
        document.close();
    }

}

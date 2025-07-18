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

import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXCommonVisitor;
import com.itextpdf.kernel.colors.Color;
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
public class JValueTest {

    @Test
    public void variable() throws IOException {
        String input = "${key}";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.variable();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        String key = (String) visitor.visit(tree);
        System.out.println("pdf generate");
    }

    @Test
    public void unit() throws IOException {
        String input = "px";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        Object key = (Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }

    @Test
    public void number() throws IOException {
        String input = "1";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.number();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        Object key = (Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }

    @Test
    public void color() throws IOException {
        String input = "#FF0000";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void color2() throws IOException {
        String input = "red";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JContext params = new JContext();
        params.put("key", "value");
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(params);
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void color3() throws IOException {
        String input = "rgb(255,244,255)\n" +
                "\n";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void color4() throws IOException {
        String input = "cmyk(100,50,0,25)\n" +
                "\n";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void colo5() throws IOException {
        String input = "cmyk(100%,50%,0%,25%)\n" +
                "\n";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void colo6() throws IOException {
        String input = "red";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.color();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Color) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void defaulUnit() throws IOException {
        String input = "2";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void px() throws IOException {
        String input = "2.9px";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void pt() throws IOException {
        String input = "2pt";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void mm() throws IOException {
        String input = "2mm";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void cm() throws IOException {
        String input = "2cm";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void in() throws IOException {
        String input = "2in";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void perce() throws IOException {
        String input = "2 %";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }
    @Test
    public void per () throws IOException {
        String input = "2in";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.unit();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }



    @Test
    public void string() throws IOException {
        String input = "'xsaxsaxsa'" +
                "\n";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.string();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

}

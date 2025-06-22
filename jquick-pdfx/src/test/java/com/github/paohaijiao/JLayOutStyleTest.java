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
public class JLayOutStyleTest {

    @Test
    public void dimensionWidth() throws IOException {
        String input = "width 1 px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
    @Test
    public void dimensionHeight1() throws IOException {
        String input = "height 1 px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
    @Test
    public void dimensionSize1() throws IOException {
        String input = "size 1 px 2px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
    @Test
    public void mutiple() throws IOException {
        String input = "size 1 px 2px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
    @Test
    public void border() throws IOException {
        String input = "border 1 px red rounded 2 px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
    @Test
    public void spacing() throws IOException {
        String input = "margin 1 px 4px" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }

    @Test
    public void floatDirection() throws IOException {
        String input = "float right" ;
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.divStyle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key=(Object) visitor.visit(tree);
        System.out.println("pdf generate");
    }
}

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
public class JStyleTest {

    @Test
    public void value() throws IOException {
        String input = "aq123";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.value();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void value1() throws IOException {
        String input = "'aq123'";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.value();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void key() throws IOException {
        String input = "dwdwedwe";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.value();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void attr() throws IOException {
        String input = "key:value";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.attr();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void attr1() throws IOException {
        String input = "key:'value1'";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.attr();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void attr2() throws IOException {
        String input = "height:'1px';width:'2px'";
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.style();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void attr3() throws IOException {
        String input = "style=\"height:1px;width:2px\"";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.styleEle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

    @Test
    public void attr4() throws IOException {
        String input = "style=height:1px;width:2px";
        System.out.println(input);
        JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickPDFParser parser = new JQuickPDFParser(tokens);
        ParseTree tree = parser.styleEle();
        JPdfXCommonVisitor visitor = new JPdfXCommonVisitor();
        Object key = (Object) visitor.visit(tree);
        System.out.println(key);
    }

}

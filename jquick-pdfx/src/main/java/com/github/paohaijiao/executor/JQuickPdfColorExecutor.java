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
package com.github.paohaijiao.executor;

import com.github.paohaijiao.antlr.impl.JAbstractAntlrExecutor;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXStyleVisitor;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.properties.UnitValue;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

public class JQuickPdfColorExecutor extends JAbstractAntlrExecutor<String, Color> {
    private JContext context;

    public JQuickPdfColorExecutor() {
        this.context = new JContext();
    }

    public JQuickPdfColorExecutor(JContext context) {
        this.context = context;
    }

    @Override
    protected Lexer createLexer(CharStream input) {
        JConsole console=new JConsole();
        console.debug("color:\n"+input.toString());
        return new JQuickPDFLexer(input);
    }

    @Override
    protected Parser createParser(TokenStream tokens) {
        return new JQuickPDFParser(tokens);
    }

    @Override
    protected Color parse(Parser parser) throws JAntlrExecutionException {
        JQuickPDFParser calcParser = (JQuickPDFParser) parser;
        JQuickPDFParser.ColorContext tree = calcParser.color();
        JPdfXStyleVisitor visitor  = new JPdfXStyleVisitor();
        Color color = (Color)visitor.visit(tree);
        return color;
    }
}

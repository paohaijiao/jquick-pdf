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
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.JPdfXCommonVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

import java.io.FileNotFoundException;

public class JQuickPdfXExecutor extends JAbstractAntlrExecutor<String, Object> {
    private JContext context;

    private JPdfConfig config;

    public JQuickPdfXExecutor() throws FileNotFoundException {
        this.context = new JContext();
        this.config=new JPdfConfig();
    }
    public JQuickPdfXExecutor(JContext context) throws FileNotFoundException {
        this.context = context;
        this.config=new JPdfConfig();
    }
    public JQuickPdfXExecutor(JPdfConfig config) throws FileNotFoundException {
        this.context = new JContext();
        this.config= config;
    }

    public JQuickPdfXExecutor(JContext context,JPdfConfig config) throws FileNotFoundException {
        this.context = context;
        this.config = config;
    }

    @Override
    protected Lexer createLexer(CharStream input) {
        JConsole console=new JConsole();
        console.debug("evalue:\n"+input.toString());
        return new JQuickPDFLexer(input);
    }

    @Override
    protected Parser createParser(TokenStream tokens) {
        return new JQuickPDFParser(tokens);
    }

    @Override
    protected Object parse(Parser parser) throws JAntlrExecutionException {
        JQuickPDFParser calcParser = (JQuickPDFParser) parser;
        JQuickPDFParser.DocumentContext tree = calcParser.document();
        JPdfXCommonVisitor visitor = null;
        try {
            visitor = new JPdfXCommonVisitor(context,config);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }
        Object response = visitor.visit(tree);
        return response;
    }
}

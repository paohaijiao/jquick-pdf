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
package com.github.paohaijiao.visitor;

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFLexer;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.util.JStringUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXStyleVisitor extends JPdfXValueVisitor {
    @Override
    public JStyleAttributes visitStyleEle(JQuickPDFParser.StyleEleContext ctx) {
        if (null != ctx.style()) {
            return visitStyle(ctx.style());
        } else if (null != ctx.STRING()) {
            String string = ctx.STRING().getText();
            String value = JStringUtils.trim(string);
            JQuickPDFLexer lexer = new JQuickPDFLexer(CharStreams.fromString(value));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JQuickPDFParser parser = new JQuickPDFParser(tokens);
            ParseTree tree = parser.style();
            try {
                JPdfXCommonVisitor visitor = new JPdfXCommonVisitor(this.context);
                JStyleAttributes attributes = (JStyleAttributes) visitor.visit(tree);
                return attributes;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new JStyleAttributes();
    }


    @Override
    public JStyleAttributes visitAttr(JQuickPDFParser.AttrContext ctx) {
        String key = null;
        Object value = null;
        if (ctx.key() != null) {
            key = visitKey(ctx.key());
        }
        JAssert.notNull(key, "key is null");
        if (ctx.value() != null) {
            String str = ctx.getText();
            value = visitValue(ctx.value());
        }
        JAssert.notNull(value, "value is null");
        JStyleAttributes attr = new JStyleAttributes();
        attr.put(key, value.toString());
        return attr;
    }

    @Override
    public JStyleAttributes visitStyle(JQuickPDFParser.StyleContext ctx) {
        JStyleAttributes data = new JStyleAttributes();
        for (JQuickPDFParser.AttrContext attrContext : ctx.attr()) {
            JStyleAttributes attr = visitAttr(attrContext);
            for (String key : attr.keySet()) {
                data.put(key, attr.get(key));
            }
        }
        return data;
    }


}

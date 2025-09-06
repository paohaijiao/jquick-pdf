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
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.util.JStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXValueVisitor extends JPdfXCoreVisitor {

    @Override
    public Object visitVariable(JQuickPDFParser.VariableContext ctx) {
        String key = null;
        if (ctx.IDENTIFIER() != null) {
            key = JStringUtils.trim(ctx.IDENTIFIER().getText());
        }
        JAssert.notNull(key, "variable text must not be null");
        return this.context.get(key);
    }

    @Override
    public String visitString(JQuickPDFParser.StringContext ctx) {
        return JStringUtils.trim(ctx.getText());
    }




    @Override
    public BigDecimal visitNumber(JQuickPDFParser.NumberContext ctx) {
        return new BigDecimal(ctx.getText());
    }


    @Override
    public Object visitValue(JQuickPDFParser.ValueContext ctx) {
       if (ctx.string() != null) {
            return visitString(ctx.string());
        } else if (null != ctx.variable()) {
            return visitVariable(ctx.variable());
        } else if (null != ctx.number()) {
            return visitNumber(ctx.number());
        } else {
            return ctx.getText();
        }
    }

    @Override
    public String visitKey(JQuickPDFParser.KeyContext ctx) {
        if (null != ctx.IDENTIFIER()) {
            return ctx.IDENTIFIER().getText();
        } else if (null != ctx.string()) {
            return JStringUtils.trim(visitString(ctx.string()));
        }
        JAssert.throwNewException("invalid key");
        return null;
    }

    @Override
    public String visitSrc(JQuickPDFParser.SrcContext ctx) {
        if (null != ctx.value()) {
            return visit(ctx.value()).toString();
        }
        return null;
    }

    @Override
    public String visitAlt(JQuickPDFParser.AltContext ctx) {
        if (null != ctx.value()) {
            return visit(ctx.value()).toString();
        }
        return null;
    }
    @Override
    public List<Object> visitElemValue(JQuickPDFParser.ElemValueContext ctx) {
        List<Object> elements = new ArrayList<>();
        if(ctx.value() != null) {
             elements.add(ctx.value().getText());
        } else if (null != ctx.element()&&!ctx.element().isEmpty()) {
            for (JQuickPDFParser.ElementContext elementContext:ctx.element()){
                Object object=visitElement(elementContext);
                elements.add(object);
            }
        }
        return elements;
    }

}

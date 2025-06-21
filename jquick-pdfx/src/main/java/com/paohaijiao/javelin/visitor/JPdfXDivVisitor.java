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
package com.paohaijiao.javelin.visitor;

import com.paohaijiao.javelin.parser.JQuickPDFParser;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXDivVisitor
 * @date 2025/6/15
 * @description
 */
public class JPdfXDivVisitor extends JPdfXSvgVisitor{
    @Override
    public Void visitDiv(JQuickPDFParser.DivContext ctx) {
        if(ctx.divStyle()!=null){
            visitDivStyle( ctx.divStyle());
        }
        for(JQuickPDFParser.ElementContext elementContext:ctx.element()){
            visitElement(elementContext);
        }
        return null;
    }
    @Override
    public Void visitDivStyle(JQuickPDFParser.DivStyleContext ctx) {
        for (JQuickPDFParser.DivStyleItemContext item:ctx.divStyleItem()){
            visitDivStyleItem(item);
        }
        return null;
    }
    @Override
    public Void visitDivStyleItem(JQuickPDFParser.DivStyleItemContext ctx) {
        if(null!=ctx.dimension()){
             visit(ctx.dimension());
        }
        if(null!=ctx.background()){
            visit(ctx.background());
        }
        if(null!=ctx.border()){
            visit(ctx.border());
        }
        if(null!=ctx.spacing()){
            visit(ctx.spacing());
        }
        if(null!=ctx.float_()){
            visit(ctx.float_());
        }
        return null;
    }


}

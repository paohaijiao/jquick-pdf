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

import com.github.paohaijiao.model.style.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.paohaijiao.javelin.exception.JAssert;


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
    public JStyleAttributes visitAttr(JQuickPDFParser.AttrContext ctx) {
        String key=null;
        Object value=null;
        if(ctx.key()!=null){
            key=visitKey(ctx.key());
        }
        JAssert.notNull(key,"key is null");
        if(ctx.value()!=null){
            value=visitValue(ctx.value());
        }
        JAssert.notNull(value,"value is null");
        JStyleAttributes attr=new JStyleAttributes();
        attr.put(key,value.toString());
        return attr;
    }
    @Override
    public JStyleAttributes visitStyle(JQuickPDFParser.StyleContext ctx) {
        JStyleAttributes data=new JStyleAttributes();
        for(JQuickPDFParser.AttrContext attrContext:ctx.attr()){
            JStyleAttributes attr= visitAttr(attrContext);
            for (String key:attr.keySet()){
                data.put(key,attr.get(key));
            }
        }
        return data;
    }



}

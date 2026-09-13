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

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.parser.JQuickPDFParser;
import com.github.paohaijiao.visitor.element.JQuickImageElementRender;

/**
 * packageName com.paohaijiao.javelin.visitor
 *
 * @author Martin
 * @version 1.0.0
 * @className JPdfXCommonVisitor
 * @date 2025/6/14
 * @description
 */
public class JPdfXImageVisitor extends JPdfXListVisitor {

    @Override
    public JQuickImageElementRender visitImage(JQuickPDFParser.ImageContext ctx) {
        String src = null;
        if (ctx.src() != null) {
            src = visitSrc(ctx.src());
        }
        String alt = null;
        if (ctx.alt() != null) {
            alt = visitAlt(ctx.alt());
        }
        String value = null;
        if (ctx.value() != null) {
            value = visitValue(ctx.value()).toString();
        }
        JStyleAttributes style;
        if (ctx.styleEle() != null) {
            style = visitStyleEle(ctx.styleEle());
        } else {
            style = new JStyleAttributes();
        }
        JQuickImageElementRender image = new JQuickImageElementRender(src, alt, value, style);
        super.buildStyle(image, style);
        return image;
    }
}

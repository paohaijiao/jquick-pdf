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
package com.github.paohaijiao.factory;

import com.github.paohaijiao.render.JStyleRenderer;
import com.github.paohaijiao.render.impl.*;
import com.itextpdf.layout.element.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JStyleRendererFactory
 * @date 2025/6/27
 * @description
 */
public class JStyleRendererFactory {

    private static final Map<Class<?>, JStyleRenderer> renderers = new HashMap<>();

    static {
        renderers.put(Text.class, new JTextRenderer());
        renderers.put(Paragraph.class, new JBlockRenderer());
        renderers.put(Div.class, new JBlockRenderer());
        renderers.put(Image.class, new JImageRenderer());
        renderers.put(Link.class, new JLinkRenderer());
        renderers.put(List.class, new JListRenderer());
        renderers.put(ListItem.class, new JListItemRenderer());
        renderers.put(Table.class, new JTableRenderer());
        renderers.put(LineSeparator.class, new JLineSeperaterRenderer());
        renderers.put(TabStop.class, new JTabTypeRenderer());
    }

    public static JStyleRenderer getRenderer(IElement element) {
        return renderers.getOrDefault(element.getClass(), new JDefautRenderer());
    }

    public static void registerRenderer(Class<?> elementClass, JStyleRenderer renderer) {
        renderers.put(elementClass, renderer);
    }
}

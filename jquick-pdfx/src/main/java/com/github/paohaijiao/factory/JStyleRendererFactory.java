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

import java.util.HashMap;
import java.util.Map;

/**
 * PDFBox style renderer registry.
 */
public class JStyleRendererFactory {

    private static final JStyleRenderer DEFAULT_RENDERER = (element, styles) -> { };

    private static final Map<Class<?>, JStyleRenderer> renderers = new HashMap<>();

    public static JStyleRenderer getRenderer(Object element) {
        if (element == null) {
            return DEFAULT_RENDERER;
        }
        return renderers.getOrDefault(element.getClass(), DEFAULT_RENDERER);
    }

    public static void registerRenderer(Class<?> elementClass, JStyleRenderer renderer) {
        if (elementClass == null || renderer == null) {
            return;
        }
        renderers.put(elementClass, renderer);
    }
}

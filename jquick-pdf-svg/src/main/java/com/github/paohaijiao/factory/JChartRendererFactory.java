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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.provider.JAbstractChartRenderer;
import com.github.paohaijiao.provider.spi.JChartSpiLoader;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.factory
 *
 * @author Martin
 * @version 1.0.0
 * @className JChartRendererFactory
 * @date 2025/6/27
 * @description Factory for chart renderers.
 * <p>
 * The class no longer holds a switch over chart types. On the first call {@link JChartSpiLoader}
 * scans the {@link com.github.paohaijiao.provider.spi.JQuickSpi} annotations and builds the
 * JChartType to renderer mapping. Later calls read the map and create a new instance each time.
 * <p>
 * The public static methods keep their original signatures, so existing callers need no change.
 */
public class JChartRendererFactory {

    public static JAbstractChartRenderer createRenderer(JChartType type, JOption option) {
        Class<? extends JAbstractChartRenderer> rendererType = type == null ? null : RendererRegistry.RENDERERS.get(type);
        if (rendererType == null) {
            throw new IllegalArgumentException("Unsupported chart type: " + type);
        }
        return newInstance(rendererType);
    }

    public static String renderChart(JChartType type, JOption option) {
        JAbstractChartRenderer renderer = createRenderer(type, option);
        try {
            String content = renderer.renderToString(option);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void renderChart(JChartType type, JOption option, String filePath) {
        JAbstractChartRenderer renderer = createRenderer(type, option);
        try {
            renderer.render(option, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JAbstractChartRenderer newInstance(Class<? extends JAbstractChartRenderer> rendererType) {
        try {
            Constructor<? extends JAbstractChartRenderer> constructor = rendererType.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create chart renderer: " + rendererType.getName(), e);
        }
    }

    /**
     * Holds the SPI mapping. Initialization is deferred until the factory is first used,
     * so class loading stays cheap.
     */
    private static final class RendererRegistry {

        private static final Map<JChartType, Class<? extends JAbstractChartRenderer>> RENDERERS = JChartSpiLoader.loadTypeMapping();
    }
}

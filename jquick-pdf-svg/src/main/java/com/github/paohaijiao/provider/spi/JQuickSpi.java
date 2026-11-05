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
package com.github.paohaijiao.provider.spi;

import com.github.paohaijiao.enums.JChartType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName com.github.paohaijiao.provider.spi
 *
 * @author Martin
 * @version 1.0.0
 * @className JQuickSpi
 * @date 2026/11/5
 * @description SPI declaration for chart renderers.
 * <p>
 * Placed on implementations of {@link com.github.paohaijiao.provider.JAbstractChartRenderer}.
 * {@link JChartSpiLoader} scans the annotation on the first factory call and builds the
 * {@link JChartType} to renderer mapping, so no META-INF/services file is needed.
 * <p>
 * Example:
 * <pre>
 * &#64;JQuickSpi(name = "candlestick", order = 4, chartType = JChartType.K)
 * public class JKChartsRenderer extends JAbstractChartRenderer {
 *     // drawing code stays unchanged
 * }
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JQuickSpi {

    /**
     * Renderer alias, unique across the classpath. Use the lower-case chart code,
     * for example candlestick, bar or pie.
     *
     * @return renderer alias
     */
    String name() default "";

    /**
     * Loading order, lower value wins.
     * <p>
     * When several renderers bind the same {@link JChartType}, the one with the smallest
     * order is used. This allows replacing a built-in renderer without touching the factory.
     *
     * @return order value
     */
    int order() default 0;

    /**
     * Chart type handled by this renderer. The factory routes by this value.
     *
     * @return chart type
     */
    JChartType chartType();

    /**
     * Free-form description, used for diagnostics and documentation only.
     *
     * @return description
     */
    String description() default "";
}

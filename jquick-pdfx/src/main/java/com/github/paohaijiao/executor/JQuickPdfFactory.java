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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.param.JContext;
import com.itextpdf.kernel.geom.PageSize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * JQuickPdfFactory - PDF Shortcut Factory Wrapper
 *
 * <p>Fluent API wrapper around {@link JQuickPdfXExecutor} that consolidates variable
 * binding, chart/template configuration and rendering shortcuts. The PDF is always
 * streamed to an {@link OutputStream}; it is never written to disk automatically.</p>
 *
 * <h3>Output modes</h3>
 * <ul>
 *   <li><b>Direct streaming</b>: pass an {@link OutputStream} to the constructor, then
 *       call {@link #execute(String)}; the stream is flushed and closed afterwards.</li>
 *   <li><b>Byte array / InputStream</b>: use {@link #executeToBytes(String)} or
 *       {@link #executeToStream(String)} for in-memory results; no constructor stream
 *       is required.</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <pre>{@code
 * // 1. Direct streaming via constructor (stream is closed after execute)
 * new JQuickPdfFactory(response.getOutputStream())
 *     .bind("name", "Martin")
 *     .execute(content);
 *
 * // 2. Fluent config + byte array result
 * byte[] pdf = JQuickPdfFactory.create()
 *     .bind("name", "Martin")
 *     .graph("svg", JChartType.BAR, barOption)
 *     .pageSize(PageSize.A4)
 *     .executeToBytes(content);
 *
 * // 3. One-liner: return as InputStream
 * InputStream pdf = JQuickPdfFactory.renderToStream(content);
 * }</pre>
 *
 * @author Martin
 * @version 2.0.0
 * @since 2.0.0
 */
public class JQuickPdfFactory {

    /** Variable context (key-value bindings for ${...} placeholders). */
    private final JContext context;

    /** PDF configuration object (page size, margins, charts, templates ...). */
    private final JPdfConfig config;


    /**
     * Default constructor: creates an empty {@link JContext} and a {@link JPdfConfig}
     * with default values. Use this for {@link #executeToBytes(String)} /
     * {@link #executeToStream(String)} shortcuts. Calling {@link #execute(String)} on
     * a factory created with this constructor throws {@link IllegalStateException}.
     *
     * @example {@code JQuickPdfFactory factory = new JQuickPdfFactory();}
     */
    public JQuickPdfFactory() {
        this.context = new JContext();
        this.config = new JPdfConfig();
    }


    /**
     * Construct with an existing variable context.
     *
     * @param context existing variable context (must not be null)
     * @example {@code JQuickPdfFactory factory = new JQuickPdfFactory(preparedContext);}
     */
    public JQuickPdfFactory(JContext context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.context = context;
        this.config = new JPdfConfig();
    }

    /**
     * Construct with an existing PDF configuration.
     *
     * @param config existing PDF configuration (must not be null)
     * @example {@code JQuickPdfFactory factory = new JQuickPdfFactory(myConfig);}
     */
    public JQuickPdfFactory(JPdfConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("config must not be null");
        }
        this.context = new JContext();
        this.config = config;
    }

    /**
     * Construct with both a variable context and a PDF configuration.
     *
     * @param context existing variable context (must not be null)
     * @param config  existing PDF configuration (must not be null)
     * @example {@code JQuickPdfFactory factory = new JQuickPdfFactory(ctx, cfg);}
     */
    public JQuickPdfFactory(JContext context, JPdfConfig config) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (config == null) {
            throw new IllegalArgumentException("config must not be null");
        }
        this.context = context;
        this.config = config;
    }



    /**
     * Bind a single template variable, equivalent to {@code context.put(key, value)}.
     * The variable is referenced in the template via {@code ${key}}.
     *
     * @param key   variable name
     * @param value variable value
     * @return this factory instance for chaining
     * @example {@code factory.bind("name", "Martin").bind("score", 95);}
     */
    public JQuickPdfFactory bind(String key, Object value) {
        this.context.put(key, value);
        return this;
    }

    /**
     * Bind multiple template variables at once.
     *
     * @param variables variable map (must not be null)
     * @return this factory instance
     * @example {@code factory.bindAll(map);}
     */
    public JQuickPdfFactory bindAll(Map<String, Object> variables) {
        if (variables == null) {
            throw new IllegalArgumentException("variables must not be null");
        }
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            this.context.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Bind chart data referenced by the {@code <svg>&{key}</svg>} syntax in the template.
     * The graph config is lazily created if it has not been set yet.
     *
     * @param key       chart placeholder name
     * @param container assembled chart container
     * @return this factory instance
     * @example {@code factory.graph("svg", graphContainer);}
     */
    public JQuickPdfFactory graph(String key, JGraphContainer container) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be blank");
        }
        if (container == null) {
            throw new IllegalArgumentException("container must not be null");
        }
        ensureGraphConfig().put(key, container);
        return this;
    }

    /**
     * Convenience chart binding: pass chart type and option directly, the method
     * internally builds a {@link JGraphContainer} for you.
     *
     * @param key    chart placeholder name
     * @param type   chart type (see {@link JChartType})
     * @param option chart option (see {@link JOption})
     * @return this factory instance
     * @example {@code factory.graph("svg", JChartType.BAR, barOption);}
     */
    public JQuickPdfFactory graph(String key, JChartType type, JOption option) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        JGraphContainer container = new JGraphContainer();
        container.setType(type);
        container.setOption(option);
        return graph(key, container);
    }

    /**
     * Bind a reusable template fragment referenced by the
     * {@code <template>&html</template>} syntax. The template config is lazily created
     * if it has not been set yet.
     *
     * @param key  template placeholder name
     * @param html template fragment HTML content
     * @return this factory instance
     * @example {@code factory.template("html", "&lt;div&gt;'header'&lt;/div&gt;");}
     */
    public JQuickPdfFactory template(String key, String html) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be blank");
        }
        if (html == null) {
            throw new IllegalArgumentException("html must not be null");
        }
        ensureTemplateConfig().put(key, html);
        return this;
    }

    /**
     * Bind multiple reusable template fragments at once.
     *
     * @param templates template fragment map (must not be null)
     * @return this factory instance
     * @example {@code factory.templateAll(templateMap);}
     */
    public JQuickPdfFactory templateAll(Map<String, String> templates) {
        if (templates == null) {
            throw new IllegalArgumentException("templates must not be null");
        }
        JTemplateConfig templateConfig = ensureTemplateConfig();
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            templateConfig.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Set the default page size.
     *
     * @param pageSize page size (e.g. {@link PageSize#A4})
     * @return this factory instance
     * @example {@code factory.pageSize(PageSize.A4);}
     */
    public JQuickPdfFactory pageSize(PageSize pageSize) {
        if (pageSize == null) {
            throw new IllegalArgumentException("pageSize must not be null");
        }
        this.config.setDefaultPageSize(pageSize);
        return this;
    }

    /**
     * Set four-way page margins (top, right, bottom, left).
     *
     * @param top    top margin
     * @param right  right margin
     * @param bottom bottom margin
     * @param left   left margin
     * @return this factory instance
     * @example {@code factory.margins(36, 36, 36, 36);}
     */
    public JQuickPdfFactory margins(int top, int right, int bottom, int left) {
        this.config.setMargins(Arrays.asList(top, right, bottom, left));
        return this;
    }

    /**
     * Set margins via a list (top, right, bottom, left).
     *
     * @param margins margin list of length 4
     * @return this factory instance
     * @example {@code factory.margins(Arrays.asList(36, 36, 36, 36));}
     */
    public JQuickPdfFactory margins(List<Integer> margins) {
        if (margins == null || margins.size() != 4) {
            throw new IllegalArgumentException("margins must have 4 elements");
        }
        this.config.setMargins(margins);
        return this;
    }

    /**
     * Set whether to render in reverse order.
     *
     * @param reverse whether to reverse
     * @return this factory instance
     * @example {@code factory.reverse(false);}
     */
    public JQuickPdfFactory reverse(boolean reverse) {
        this.config.setReverse(reverse);
        return this;
    }

    /**
     * Build and return a configured {@link JQuickPdfXExecutor} that streams to the
     * constructor-provided {@link OutputStream}. The caller decides when to invoke
     * {@code execute()} and is responsible for closing the stream afterwards (iText's
     * PdfWriter is configured with {@code setCloseStream(false)}).
     *
     * @return configured executor
     * @throws FileNotFoundException           thrown by the executor constructor
     * @throws IllegalStateException if no OutputStream was provided via constructor
     * @example {@code factory.build().execute(content);}
     */
    public JQuickPdfXExecutor build() throws FileNotFoundException {
        return new JQuickPdfXExecutor(context, config);
    }

    /**
     * Render the given template string, streaming the PDF directly to the
     * constructor-provided {@link OutputStream}. The stream is flushed and closed in a
     * {@code finally} block, guaranteeing release even if rendering fails.
     *
     * @param templateContent template content
     * @return execution result
     * @throws IOException           if rendering fails
     * @throws IllegalStateException if no OutputStream was provided via constructor;
     *         use {@link #executeToBytes(String)} or {@link #executeToStream(String)}
     *         for in-memory results without a constructor stream
     * @example {@code factory.execute("&lt;pdf&gt;&lt;body&gt;&lt;h1&gt;'Hi'&lt;/h1&gt;&lt;/body&gt;&lt;/pdf&gt;");}
     */
    private Object execute(String templateContent) throws IOException {
        if (templateContent == null) {
            throw new IllegalArgumentException("templateContent must not be null");
        }
        return new JQuickPdfXExecutor(context, config).execute(templateContent);
    }

    /**
     * Load template from classpath resource and render to the constructor-provided
     * {@link OutputStream}. The stream is flushed and closed after rendering.
     *
     * @param resourcePath classpath resource path
     * @return execution result
     * @throws IOException if the resource is missing or rendering fails
     * @example {@code factory.executeResource("sample/svg1.txt");}
     */
    public Object executeResource(String resourcePath) throws IOException {
        return execute(readResource(resourcePath));
    }

    /**
     * Load template from file system and render to the constructor-provided
     * {@link OutputStream}. The stream is flushed and closed after rendering.
     *
     * @param filePath file system path
     * @return execution result
     * @throws IOException if the file is missing or rendering fails
     * @example {@code factory.executeFile("d:/templates/rule.txt");}
     */
    public Object executeFile(String filePath) throws IOException {
        return execute(readFile(filePath));
    }

    /**
     * Render the template and return the PDF bytes as a {@code byte[]}, convenient for
     * persistence or further wrapping. Output is collected in an in-memory
     * {@link ByteArrayOutputStream}; no constructor stream is required.
     *
     * @param templateContent template content
     * @return PDF byte array
     * @throws IOException if rendering fails
     * @example {@code byte[] pdf = factory.executeToBytes(content);}
     */
    public byte[] executeToBytes(String templateContent) throws IOException {
        if (templateContent == null) {
            throw new IllegalArgumentException("templateContent must not be null");
        }
        return renderToBytes(templateContent, context, config);
    }

    /**
     * Render the template and return the PDF bytes as an {@link InputStream}. Internally
     * delegates to {@link #executeToBytes(String)} and wraps the result in a
     * {@link ByteArrayInputStream}.
     *
     * @param templateContent template content
     * @return PDF byte input stream (closing it is a no-op)
     * @throws IOException if rendering fails
     * @example {@code InputStream pdf = factory.executeToStream(content);}
     */
    public InputStream executeToStream(String templateContent) throws IOException {
        if (templateContent == null) {
            throw new IllegalArgumentException("templateContent must not be null");
        }
        return new ByteArrayInputStream(renderToBytes(templateContent, context, config));
    }

    /**
     * Load template from classpath resource and render to an in-memory PDF stream.
     *
     * @param resourcePath classpath resource path
     * @return PDF byte input stream
     * @throws IOException if the resource is missing or rendering fails
     * @example {@code factory.executeResourceToStream("sample/svg1.txt");}
     */
    public InputStream executeResourceToStream(String resourcePath) throws IOException {
        return executeToStream(readResource(resourcePath));
    }

    /**
     * Load template from file system and render to an in-memory PDF stream.
     *
     * @param filePath file system path
     * @return PDF byte input stream
     * @throws IOException if the file is missing or rendering fails
     * @example {@code factory.executeFileToStream("d:/templates/rule.txt");}
     */
    public InputStream executeFileToStream(String filePath) throws IOException {
        return executeToStream(readFile(filePath));
    }

    /**
     * Return the current variable context.
     *
     * @return current JContext
     */
    public JContext context() {
        return context;
    }

    /**
     * Return the current PDF configuration.
     *
     * @return current JPdfConfig
     */
    public JPdfConfig config() {
        return config;
    }


    /**
     * Static factory entry, equivalent to {@code new JQuickPdfFactory()}. Intended for
     * fluent configuration followed by {@link #executeToBytes(String)} or
     * {@link #executeToStream(String)}.
     *
     * @return a new factory instance (no OutputStream configured)
     * @example {@code JQuickPdfFactory.create().bind("k", "v").executeToBytes(content);}
     */
    public static JQuickPdfFactory create() {
        return new JQuickPdfFactory();
    }

    /**
     * One-liner: render template string and return PDF bytes.
     *
     * @param templateContent template content
     * @return PDF byte array
     * @throws IOException if rendering fails
     * @example {@code byte[] pdf = JQuickPdfFactory.renderToBytes(content);}
     */
    public static byte[] renderToBytes(String templateContent) throws IOException {
        return new JQuickPdfFactory().executeToBytes(templateContent);
    }

    /**
     * One-liner: render template string and return a PDF {@link InputStream}.
     *
     * @param templateContent template content
     * @return PDF byte input stream
     * @throws IOException if rendering fails
     * @example {@code InputStream pdf = JQuickPdfFactory.renderToStream(content);}
     */
    public static InputStream renderToStream(String templateContent) throws IOException {
        return new JQuickPdfFactory().executeToStream(templateContent);
    }

    /**
     * One-liner: load classpath resource and return a PDF {@link InputStream}.
     *
     * @param resourcePath classpath resource path
     * @return PDF byte input stream
     * @throws IOException if the resource is missing or rendering fails
     * @example {@code InputStream pdf = JQuickPdfFactory.renderResourceToStream("sample/svg1.txt");}
     */
    public static InputStream renderResourceToStream(String resourcePath) throws IOException {
        return new JQuickPdfFactory().executeResourceToStream(resourcePath);
    }

    /**
     * One-liner: load file system file and return a PDF {@link InputStream}.
     *
     * @param filePath file system path
     * @return PDF byte input stream
     * @throws IOException if the file is missing or rendering fails
     * @example {@code InputStream pdf = JQuickPdfFactory.renderFileToStream("d:/templates/rule.txt");}
     */
    public static InputStream renderFileToStream(String filePath) throws IOException {
        return new JQuickPdfFactory().executeFileToStream(filePath);
    }

    /**
     * Lazily create or reuse the graph config attached to the current {@link JPdfConfig}.
     *
     * @return graph config instance
     */
    private JGraphConfig ensureGraphConfig() {
        JGraphConfig graphConfig = this.config.getGraphConfig();
        if (graphConfig == null) {
            graphConfig = new JGraphConfig();
            this.config.setGraphConfig(graphConfig);
        }
        return graphConfig;
    }

    /**
     * Lazily create or reuse the template config attached to the current {@link JPdfConfig}.
     *
     * @return template config instance
     */
    private JTemplateConfig ensureTemplateConfig() {
        JTemplateConfig templateConfig = this.config.getTemplateConfig();
        if (templateConfig == null) {
            templateConfig = new JTemplateConfig();
            this.config.setTemplateConfig(templateConfig);
        }
        return templateConfig;
    }

    /**
     * Read a classpath resource as a UTF-8 string.
     *
     * @param resourcePath classpath resource path
     * @return resource content string
     * @throws IOException if the resource is missing or unreadable
     */
    private static String readResource(String resourcePath) throws IOException {
        if (resourcePath == null || resourcePath.isEmpty()) {
            throw new IllegalArgumentException("resourcePath must not be blank");
        }
        ClassLoader loader = JQuickPdfFactory.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("classpath resource not found: " + resourcePath);
            }
            return readStream(is);
        }
    }

    /**
     * Read a file system file as a UTF-8 string.
     *
     * @param filePath file system path
     * @return file content string
     * @throws IOException if the file is missing or unreadable
     */
    private static String readFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("filePath must not be blank");
        }
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Read an input stream into a UTF-8 string (JDK 8 compatible, does not rely on
     * {@code InputStream.readAllBytes}).
     *
     * @param is input stream
     * @return string content
     * @throws IOException if reading fails
     */
    private static String readStream(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] block = new byte[4096];
        int read;
        while ((read = is.read(block)) != -1) {
            buffer.write(block, 0, read);
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * Render the template into an in-memory {@link ByteArrayOutputStream} and return
     * the byte array. A {@link ByteArrayOutputStream} is passed directly to
     * {@link JQuickPdfXExecutor}'s constructor so iText's {@code PdfWriter} streams
     * to it during rendering; bytes are returned afterwards.
     *
     * @param templateContent template content
     * @param context         variable context
     * @param config          PDF configuration
     * @return PDF byte array
     * @throws IOException if rendering fails
     */
    private static byte[] renderToBytes(String templateContent, JContext context, JPdfConfig config) throws IOException {
        OutputStream outputStream=new JQuickPdfXExecutor(context, config).execute(templateContent);;
        ByteArrayOutputStream baos = (ByteArrayOutputStream)outputStream;
        return baos.toByteArray();
    }
}

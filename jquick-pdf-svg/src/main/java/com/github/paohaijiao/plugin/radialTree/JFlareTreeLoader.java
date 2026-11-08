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
package com.github.paohaijiao.plugin.radialTree;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.paohaijiao.treemap.JTreeMapNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * packageName com.github.paohaijiao.plugin.radialTree
 *
 * @author Martin
 * @version 1.0.0
 * @classname JFlareTreeLoader
 * @date 2026/11/8
 * @description Loads the hierarchical flare data set into {@link JTreeMapNode}.
 * <p>
 * The JSON shape is the one used by the ECharts radial tree example:
 * {@code {"name": "...", "value": 123, "children": [...]}}. Levels without an explicit
 * value keep zero here, the renderer weights them by their leaf count.
 */
public final class JFlareTreeLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JFlareTreeLoader() {
    }

    /**
     * Reads a JSON tree from a reader.
     *
     * @param reader JSON source, closed by the caller
     * @return tree root
     * @throws IOException when the JSON cannot be parsed
     */
    public static JTreeMapNode load(Reader reader) throws IOException {
        return convert(MAPPER.readTree(reader));
    }

    /**
     * Reads a JSON tree from an input stream.
     *
     * @param input JSON source, closed by the caller
     * @return tree root
     * @throws IOException when the JSON cannot be parsed
     */
    public static JTreeMapNode load(InputStream input) throws IOException {
        return convert(MAPPER.readTree(input));
    }

    /**
     * Reads a JSON tree from a file.
     *
     * @param file UTF-8 JSON file
     * @return tree root
     * @throws IOException when the file is missing or malformed
     */
    public static JTreeMapNode load(Path file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            return load(reader);
        }
    }

    /**
     * Reads a JSON tree from the classpath.
     *
     * @param resource classpath location, for example {@code data/flare.json}
     * @return tree root
     * @throws IOException when the resource is missing or malformed
     */
    public static JTreeMapNode loadResource(String resource) throws IOException {
        try (InputStream input = JFlareTreeLoader.class.getClassLoader().getResourceAsStream(resource)) {
            if (input == null) {
                throw new FileNotFoundException("classpath resource not found: " + resource);
            }
            return load(input);
        }
    }

    private static JTreeMapNode convert(JsonNode node) {
        String name = node.path("name").asText("");
        double value = node.path("value").asDouble(0d);
        JTreeMapNode result = new JTreeMapNode(name, value);
        JsonNode children = node.path("children");
        if (children.isArray()) {
            for (JsonNode child : children) {
                result.addChild(convert(child));
            }
        }
        return result;
    }

    /**
     * Reads a JSON tree into the radial tree data model, ready for
     * {@code JOption.setData(...)}.
     *
     * @param reader JSON source, closed by the caller
     * @return chart data holding the converted node tree
     * @throws IOException when the JSON cannot be parsed
     */
    public static JRadialTreeChartData loadChartData(Reader reader) throws IOException {
        JRadialTreeChartData data = new JRadialTreeChartData();
        data.setRoot(toRadialNode(MAPPER.readTree(reader)));
        return data;
    }

    /**
     * Reads a JSON tree from the classpath into the radial tree data model.
     *
     * @param resource classpath location, for example {@code data/flare.json}
     * @return chart data holding the converted node tree
     * @throws IOException when the resource is missing or malformed
     */
    public static JRadialTreeChartData loadChartDataResource(String resource) throws IOException {
        try (InputStream input = JFlareTreeLoader.class.getClassLoader().getResourceAsStream(resource)) {
            if (input == null) {
                throw new FileNotFoundException("classpath resource not found: " + resource);
            }
            JRadialTreeChartData data = new JRadialTreeChartData();
            data.setRoot(toRadialNode(MAPPER.readTree(input)));
            return data;
        }
    }

    /**
     * Converts an existing {@link JTreeMapNode} tree into the radial tree data model.
     *
     * @param node source tree
     * @return converted node tree
     */
    public static JRadialTreeChartData.RadialNode toRadialNode(JTreeMapNode node) {
        JRadialTreeChartData.RadialNode result =
                new JRadialTreeChartData.RadialNode(node.getName(), node.getValue());
        for (JTreeMapNode child : node.getChildren()) {
            result.addChild(toRadialNode(child));
        }
        return result;
    }

    private static JRadialTreeChartData.RadialNode toRadialNode(JsonNode node) {
        JRadialTreeChartData.RadialNode result = new JRadialTreeChartData.RadialNode(
                node.path("name").asText(""), node.path("value").asDouble(0d));
        JsonNode children = node.path("children");
        if (children.isArray()) {
            for (JsonNode child : children) {
                result.addChild(toRadialNode(child));
            }
        }
        return result;
    }
}

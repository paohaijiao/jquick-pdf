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
package com.github.paohaijiao.demo.geo;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.geo.GeoOption;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * packageName com.github.paohaijiao.ele
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/4
 */
public class JGeoTest {

    public static final String  path= JQuickConstant.path;

    private String readFromClasspath(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            if (is == null) {
                throw new IOException("File not found in classpath: " + fileName);
            }
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    @Test
    public void geo() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        JOption jOption = new JOption();
        String geoJsonContent = readFromClasspath("sample/test.geojson");
        GeoOption geoOption = new GeoOption();
        geoOption.setGeoJsonContent(geoJsonContent);
        graphContainer.setType(JChartType.Geo);
        jOption.setGeoOption(geoOption);
        graphContainer.setOption(jOption);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);

        FileOutputStream fileOutputStream = new FileOutputStream(path + "test.pdf");
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("sample/svg2.txt");
        fileOutputStream.write(bytes);
    }
}

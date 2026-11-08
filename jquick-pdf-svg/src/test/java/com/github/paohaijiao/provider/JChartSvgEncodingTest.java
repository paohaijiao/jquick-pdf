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
package com.github.paohaijiao.provider;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.plugin.themeRiver.JThemeRiverChartData;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * packageName com.github.paohaijiao.provider
 *
 * @author Martin
 * @version 1.0.0
 * @classname JChartSvgEncodingTest
 * @date 2026/11/8
 * @description Guards the output encoding of the generated documents.
 * <p>
 * The SVG declaration states UTF-8 and the file is written as UTF-8, so a browser or an XML
 * parser never has to guess. Breaking either half makes a document that contains Chinese text
 * fail with "Encoding error" on open, which is what this test protects against.
 */
public class JChartSvgEncodingTest {

    private static final String OUTPUT_DIR = "D:/test/encoding";

    private static final String DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Test
    public void renderToStringDeclaresUtf8() {
        String svg = JChartRendererFactory.renderChart(JChartType.ThemeRiver, sampleOption());
        assertTrue(svg.startsWith(DECLARATION));
        assertTrue(svg.contains("主题河流图"));
        assertEquals(1, countOccurrences(svg, "<?xml"));
    }

    @Test
    public void renderWritesUtf8File() throws IOException {
        File dir = new File(OUTPUT_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("cannot create " + OUTPUT_DIR);
        }
        File file = new File(dir, "charset_check.svg");
        JChartRendererFactory.renderChart(JChartType.ThemeRiver, sampleOption(), file.getPath());
        byte[] bytes = Files.readAllBytes(file.toPath());
        String decoded = decodeStrictUtf8(bytes);
        assertTrue(decoded.startsWith(DECLARATION));
        assertTrue(decoded.contains("主题河流图"));
        assertTrue(decoded.contains("华东大区"));
    }

    private static JOption sampleOption() {
        JThemeRiverChartData data = new JThemeRiverChartData();
        data.setTitleText("区域销量主题河流图");
        data.timeLabels("一季度", "二季度", "三季度");
        data.addSeries("华东大区", 320d, 380d, 420d);
        data.addSeries("华南大区", 260d, 300d, 340d);
        JOption option = new JOption();
        option.setData(data);
        return option;
    }

    private static String decodeStrictUtf8(byte[] bytes) throws CharacterCodingException {
        return StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT)
                .decode(ByteBuffer.wrap(bytes))
                .toString();
    }

    private static int countOccurrences(String text, String token) {
        int count = 0;
        int index = text.indexOf(token);
        while (index >= 0) {
            count++;
            index = text.indexOf(token, index + token.length());
        }
        return count;
    }
}

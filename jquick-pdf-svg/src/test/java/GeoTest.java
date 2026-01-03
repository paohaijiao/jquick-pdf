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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.geo.GeoOption;
import com.github.paohaijiao.geo.JGeoJsonRenderer;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class GeoTest {
    /**
     * 工具方法：读取文件
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    @Test
    public void testBarChar1() throws IOException {
        String geoJsonContent = readFile("d://sample//test.geojson");
        GeoOption option = new GeoOption();
        option.setGeoJsonContent(geoJsonContent);
        JGeoJsonRenderer renderer = new JGeoJsonRenderer();
        JOption jOption = new JOption();
        jOption.setGeoOption(option);
        renderer.render(jOption, "d://test/geo.svg");
    }
}

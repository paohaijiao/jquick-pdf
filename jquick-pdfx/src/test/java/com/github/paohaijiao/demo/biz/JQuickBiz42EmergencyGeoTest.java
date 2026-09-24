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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.geo.GeoOption;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 业务场景 Demo 42：应急事件地域分布表（应急指挥平台）
 *
 * <p>业务场景：省应急指挥平台按行政区划统计突发事件起数与响应时效，用于汛期风险研判与救援力量前置部署。</p>
 * <p>实际图表类型：{@link JChartType#Geo} 地图（GeoJSON 行政区划图，底图区域为四川省 21 个市/州）。</p>
 *
 * <p>模板：{@code report/biz/42_emergency_geo.txt}；地图以 SVG 文本形式绑定到模板的 {@code ${svg}} 占位符，
 * 输出到 {@code D:\test\biz\42_emergency_geo.pdf}。</p>
 *
 * <p>图表尺寸说明：地图按渲染器默认画布 800pt × 600pt（宽 × 高）绘制；模板中 {@code <svg>} 只声明宽度、
 * 不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放，不会纵向拉伸变形。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz42EmergencyGeoTest {

    public static final String path = JQuickConstant.path;

    /**
     * 从 classpath 读取文本内容（GeoJSON 底图）
     */
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
    public void biz42EmergencyGeo() throws IOException {
        JOption option = new JOption();
        GeoOption geoOption = new GeoOption();
        // 读取 classpath 下的四川省行政区划 GeoJSON（21 个市/州）作为底图
        geoOption.setGeoJsonContent(readFromClasspath("sample/test.geojson"));
        // 应急主题使用低饱和警示红作为地图填充色
        geoOption.setBackgroundColor("#B71C1C");
        option.setGeoOption(geoOption);

        String svg = JChartRendererFactory.renderChart(JChartType.Geo, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/42_emergency_geo.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局，使模板中的 display:flex 生效（图表居中）
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/42_emergency_geo.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

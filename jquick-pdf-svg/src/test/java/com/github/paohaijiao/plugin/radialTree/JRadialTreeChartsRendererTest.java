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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.factory.JChartRendererFactory;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.util.XMLResourceDescriptor;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * packageName com.github.paohaijiao.plugin.radialTree
 *
 * @author Martin
 * @version 1.0.0
 * @classname JRadialTreeChartsRendererTest
 * @date 2026/11/8
 * @description Radial tree rendering and SVG replay tests, all output goes to D:/test/radialtree.
 */
public class JRadialTreeChartsRendererTest {

    private static final String OUTPUT_DIR = "D:/test/radialtree";

    private static final String SVG_NS = "http://www.w3.org/2000/svg";

    @Test
    public void rendererIsRegisteredBySpi() {
        Object renderer = JChartRendererFactory.createRenderer(JChartType.RadialTree, new JOption());
        assertEquals(JRadialTreeChartsRenderer.class, renderer.getClass());
        Object byName = JChartRendererFactory.createRenderer("radial-tree", new JOption());
        assertEquals(JRadialTreeChartsRenderer.class, byName.getClass());
    }

    @Test
    public void renderWithChartData() throws IOException {
        JRadialTreeChartData.RadialNode root = new JRadialTreeChartData.RadialNode("集团总部")
                .addChild(new JRadialTreeChartData.RadialNode("产品中心", 40)
                        .addChild(new JRadialTreeChartData.RadialNode("研发一部", 18))
                        .addChild(new JRadialTreeChartData.RadialNode("研发二部", 12))
                        .addChild(new JRadialTreeChartData.RadialNode("产品设计部", 10)))
                .addChild(new JRadialTreeChartData.RadialNode("市场中心", 30)
                        .addChild(new JRadialTreeChartData.RadialNode("华东大区", 12))
                        .addChild(new JRadialTreeChartData.RadialNode("华南大区", 10))
                        .addChild(new JRadialTreeChartData.RadialNode("华北大区", 8)))
                .addChild(new JRadialTreeChartData.RadialNode("交付中心", 30)
                        .addChild(new JRadialTreeChartData.RadialNode("实施一部", 14))
                        .addChild(new JRadialTreeChartData.RadialNode("运维服务部", 10))
                        .addChild(new JRadialTreeChartData.RadialNode("客户成功部", 6)));
        JRadialTreeChartData data = new JRadialTreeChartData();
        data.setRoot(root);
        data.setTitleText("组织架构径向树");
        data.setSubtitleText("数据由 JRadialTreeChartData 传入 · 角度按叶子数量分配");
        JOption option = new JOption();
        option.setData(data);
        String svg = JChartRendererFactory.renderChart(JChartType.RadialTree, option);
        assertTrue(svg.contains("<svg"));
        assertTrue(svg.contains("组织架构径向树"));
        assertTrue(svg.contains("研发一部"));
        assertTrue(svg.contains("共 13 个节点"));
        save("radial_tree.svg", svg);
    }

    @Test
    public void renderFlareRadialTree() throws IOException {
        JRadialTreeChartData data = JFlareTreeLoader.loadChartDataResource("data/flare.json");
        data.setTitleText("Flare 类库径向树");
        data.setSubtitleText("数据来源：ECharts flare.json · 角度按叶子数量分配");
        JOption option = new JOption();
        option.setData(data);
        String svg = JChartRendererFactory.renderChart(JChartType.RadialTree, option);
        assertTrue(svg.contains("<svg"));
        assertTrue(svg.contains("width=\"960\""));
        assertTrue(svg.contains("共 252 个节点"));
        save("flare_radial_tree.svg", svg);
    }

    @Test
    public void replaySvgDocumentThroughGraphics2D() throws IOException {
        String resource = JRadialTreeChartsRendererTest.class.getClassLoader().getResource("svg/tree-radial.svg").toString();
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory documentFactory = new SAXSVGDocumentFactory(parser);
        Document source = documentFactory.createDocument(resource);
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(SVG_NS, "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        svgGenerator.setSVGCanvasSize(new Dimension(600, 420));
        svgGenerator.setPaint(Color.WHITE);
        svgGenerator.fillRect(0, 0, 600, 420);
        JSvgShapeTranslator.paint(source, svgGenerator);
        StringWriter writer = new StringWriter();
        svgGenerator.stream(writer, true);
        String svg = writer.toString();
        assertTrue(svg.contains("<svg"));
        assertTrue(svg.contains("path"));
        assertTrue(svg.contains("产品中心"));
        save("replayed_tree.svg", svg);
    }

    private static void save(String name, String content) throws IOException {
        File dir = new File(OUTPUT_DIR);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("cannot create " + OUTPUT_DIR);
        }
        try (Writer writer = Files.newBufferedWriter(Paths.get(OUTPUT_DIR, name), StandardCharsets.UTF_8)) {
            writer.write(content);
        }
    }
}

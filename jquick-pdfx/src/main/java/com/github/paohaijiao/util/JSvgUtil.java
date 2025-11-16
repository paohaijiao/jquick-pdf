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
package com.github.paohaijiao.util;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

/**
 * packageName com.github.paohaijiao.util
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/20
 */
public class JSvgUtil {

    public static float[] parseSvgDimensions(String svgContent) {
        float[] dimensions = new float[]{100f, 100f}; // [宽度, 高度] 默认值

        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument document = factory.createSVGDocument(null, new java.io.StringReader(svgContent));

            org.w3c.dom.Element root = document.getRootElement();
            String widthAttr = root.getAttribute("width");
            String heightAttr = root.getAttribute("height");
            if (!widthAttr.isEmpty() && !heightAttr.isEmpty()) {
                dimensions[0] = parseSvgValue(widthAttr);
                dimensions[1] = parseSvgValue(heightAttr);
                return dimensions;
            }
            String viewBox = root.getAttribute("viewBox");
            if (!viewBox.isEmpty()) {
                String[] parts = viewBox.split("\\s+");
                if (parts.length >= 4) {
                    dimensions[0] = Float.parseFloat(parts[2]);
                    dimensions[1] = Float.parseFloat(parts[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimensions;
    }

    private static float parseSvgValue(String value) {
        return Float.parseFloat(value.replaceAll("[^0-9.]", ""));
    }

}

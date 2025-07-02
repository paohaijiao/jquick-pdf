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
package com.github.paohaijiao.css.e.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesEProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {



    /**
     * Converts JCSSPropertiesEModel properties to iText Style
     *
     * @param model the CSS properties model
     * @return iText Style object with configured properties
     */
    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel model) {
        Style style = new Style();
        if (model.getEmptycells() != null) {
            switch (model.getEmptycells().toLowerCase()) {
                case "show":
                    // In iText, empty cells are shown by default
                    break;
                case "hide":
                    // To hide empty cells, we need to add minimal content
                    // This is handled at the Cell level rather than Style
                    break;
                default:
                    // Default is "show"
            }
        }
        element.addStyle(style);
    }
}
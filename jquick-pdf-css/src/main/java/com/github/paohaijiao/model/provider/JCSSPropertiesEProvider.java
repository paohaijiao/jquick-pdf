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
package com.github.paohaijiao.model.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesEModel;
import com.itextpdf.layout.Style;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesEProvider extends JCSSPropertiesBaseProvider {


    /**
     * Converts JCSSPropertiesEModel properties to iText Style
     *
     * @param model the CSS properties model
     * @return iText Style object with configured properties
     */
    public static Style convertToStyle(JCSSPropertiesEModel model) {
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

        return style;
    }

}
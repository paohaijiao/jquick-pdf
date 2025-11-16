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
package com.github.paohaijiao.css.q.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.github.paohaijiao.model.provider.JCSSPropertiesBaseProvider;
import com.github.paohaijiao.model.provider.JCSSPropertiesProvider;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.BlockElement;

import java.util.Arrays;
import java.util.List;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesQProvider extends JCSSPropertiesBaseProvider implements JCSSPropertiesProvider {
    private static final List<String> VALID_QUOTES_VALUES = Arrays.asList(
            "none", "auto", "initial", "inherit"
    );

    private static void applyQuotesProperty(Style style, String quotesValue) {
        // iText doesn't directly support the quotes property in its Style class,
        // so we need to handle it appropriately

        if (VALID_QUOTES_VALUES.contains(quotesValue)) {
            // For standard values, we might need to implement custom behavior
            // Since iText doesn't support quotes directly, we could:
            // 1. Ignore it (as it's mainly for generated content)
            // 2. Implement custom rendering logic for quotes
            // For this example, we'll just store it as a custom property
            // style.setProperty(Property.QUOTES, quotesValue);
        } else if (isValidQuotesString(quotesValue)) {
            // For string pairs, we could parse them and use them in content generation
            //   style.setProperty(Property.QUOTES, parseQuotesString(quotesValue));
        }
        // Note: iText won't automatically apply these quotes to generated content
        // You would need to implement that logic separately
    }

    private static boolean isValidQuotesString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        try {
            String[] parts = value.split(" ");
            if (parts.length % 2 != 0) {
                return false;
            }

            for (String part : parts) {
                if (!part.startsWith("\"") || !part.endsWith("\"")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String[] parseQuotesString(String quotesValue) {
        // Parse the quotes string into an array of quote pairs
        String[] rawParts = quotesValue.split(" ");
        String[] cleanedParts = new String[rawParts.length];

        for (int i = 0; i < rawParts.length; i++) {
            // Remove the surrounding quotes
            cleanedParts[i] = rawParts[i].substring(1, rawParts[i].length() - 1);
        }

        return cleanedParts;
    }

    @Override
    public void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) {
        Style style = new Style();

        String quotes = cssProperties.getQuotes();
        if (quotes != null && !quotes.isEmpty()) {
            applyQuotesProperty(style, quotes);
        }
        element.addStyle(style);
    }


}
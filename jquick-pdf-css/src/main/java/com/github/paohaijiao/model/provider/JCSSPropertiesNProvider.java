/// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
// */
//package com.github.paohaijiao.model.provider;
//
//import com.github.paohaijiao.model.css.JCSSPropertiesMModel;
//import com.itextpdf.layout.properties.*;
//
//import javax.swing.text.Style;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * packageName com.github.paohaijiao.model.provider
// *
// * @author Martin
// * @version 1.0.0
// * @className JCSSPropertiesAProvider
// * @date 2025/7/1
// * @description
// */
//public class JCSSPropertiesNProvider extends JCSSPropertiesBaseProvider {
//    private static final List<String> VALID_QUOTES_VALUES = Arrays.asList(
//            "none", "auto", "initial", "inherit"
//    );
//
//
//
//    /**
//     * Helper method to validate quotes string values
//     *
//     * @param value The value to validate
//     * @return true if valid, false otherwise
//     */
//    private boolean isValidQuotesString(String value) {
//        if (value == null || value.trim().isEmpty()) {
//            return false;
//        }
//
//        // Check for string pairs (e.g., '"«" "»"' or '"“" "”" "‘" "’"')
//        try {
//            String[] parts = value.split(" ");
//            if (parts.length % 2 != 0) {
//                return false;
//            }
//
//            for (String part : parts) {
//                if (!part.startsWith("\"") || !part.endsWith("\"")) {
//                    return false;
//                }
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public Style clone() {
//        JCSSPropertiesNProvider style = new JCSSPropertiesNProvider();
//        style.setQuotes(this.getQuotes());
//        return style;
//    }
//}
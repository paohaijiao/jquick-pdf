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
package com.github.paohaijiao.model.css;

import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesNModel extends JCSSPropertiesMModel {

    public static final String NAMESPACE = "@namespace";

    private static final List<String> VALID_OBJECT_FIT_VALUES = Arrays.asList(
            "fill", "contain", "cover", "none", "scale-down"
    );

    /**
     * Defines an XML namespace for CSS selectors
     *
     * @param prefix Optional namespace prefix (may be null)
     * @param url    Namespace URL (e.g., "http://www.w3.org/1999/xhtml")
     */
    public void setNamespaceRule(String prefix, String url) {
        String key = NAMESPACE + (prefix != null ? " " + prefix : "");
        put(key, url);
    }

    public String getNamespaceRule(String prefix) {
        return get(NAMESPACE + (prefix != null ? " " + prefix : ""));
    }


}

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

/**
 * packageName com.github.paohaijiao.model.css
 *
 * @author Martin
 * @version 1.0.0
 * @className CSSPropertiesBModel
 * @date 2025/6/29
 * @description
 */
public class JCSSPropertiesKModel extends JCSSPropertiesJModel {

    public static final String KEYFRAMES = "@keyframes";


    /**
     * Defines a keyframes animation rule
     *
     * @param name    Animation name (must be valid CSS identifier)
     * @param content Keyframe steps (e.g., "0% {opacity: 0;} 100% {opacity: 1;}")
     * @throws IllegalArgumentException if name or content is invalid
     */
    public void setKeyframesRule(String name, String content) {
        if (isValidKeyframesName(name) && isValidKeyframesContent(content)) {
            put(KEYFRAMES + " " + name, content);
        } else {
            throw new IllegalArgumentException("Invalid @keyframes declaration");
        }
    }

    public String getKeyframesRule(String name) {
        return get(KEYFRAMES + " " + name);
    }


    private boolean isValidKeyframesName(String name) {
        return name != null && name.matches("^[a-zA-Z][a-zA-Z0-9-_]*$");
    }

    private boolean isValidKeyframesContent(String content) {
        return content != null && content.contains("{") && content.contains("}");
    }

}

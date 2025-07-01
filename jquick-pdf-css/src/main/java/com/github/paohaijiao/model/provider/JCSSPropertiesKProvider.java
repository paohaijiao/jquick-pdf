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

import com.github.paohaijiao.model.css.JCSSPropertiesKModel;

import java.util.Map;


/**
 * packageName com.github.paohaijiao.model.provider
 *
 * @author Martin
 * @version 1.0.0
 * @className JCSSPropertiesAProvider
 * @date 2025/7/1
 * @description
 */
public class JCSSPropertiesKProvider extends JCSSPropertiesBaseProvider {
    public static String createStyleSheetContent(JCSSPropertiesKModel cssModel) {
        StringBuilder styleSheetContent = new StringBuilder();

        // Process keyframes rules
        for (Map.Entry<String, String> entry : cssModel.entrySet()) {
            if (entry.getKey().startsWith(JCSSPropertiesKModel.KEYFRAMES)) {
                // Extract animation name (everything after "@keyframes ")
                String animationName = entry.getKey().substring(JCSSPropertiesKModel.KEYFRAMES.length()).trim();

                // Create CSS rules for keyframes
                styleSheetContent.append(JCSSPropertiesKModel.KEYFRAMES)
                        .append(" ")
                        .append(animationName)
                        .append(" { ")
                        .append(entry.getValue())
                        .append(" }\n");

                // Create a sample class that uses this animation
                styleSheetContent.append(".animate-")
                        .append(animationName)
                        .append(" { ")
                        .append("animation-name: ").append(animationName).append("; ")
                        .append("animation-duration: 2s; ")
                        .append("animation-iteration-count: infinite; ")
                        .append("}\n");
            }
        }

        return styleSheetContent.toString();
    }

    public static String createAnimationStyle(String animationName, String duration, String iterationCount) {
        StringBuilder style = new StringBuilder();
        style.append("animation-name: ").append(animationName).append("; ");

        if (duration != null) {
            style.append("animation-duration: ").append(duration).append("; ");
        }

        if (iterationCount != null) {
            style.append("animation-iteration-count: ").append(iterationCount).append("; ");
        }

        return style.toString();
    }

}
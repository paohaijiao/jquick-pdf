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
package com.github.paohaijiao.model;


public class JStyleDivAttributes extends JStyleBlockAttributes {


    public static final String fillArea = "fillArea";

    public static final String fillAreaOnSplit = "fillAreaOnSplit";

    public String getFillArea() {
        return get(JStyleDivAttributes.fillArea);
    }

    public void setFillArea(String fillArea) {
        put(JStyleDivAttributes.fillArea, fillArea);
    }

    public String getFillAreaOnSplit() {
        return get(JStyleDivAttributes.fillAreaOnSplit);
    }

    public void setFillAreaOnSplit(String fillAreaOnSplit) {
        put(JStyleDivAttributes.fillAreaOnSplit, fillAreaOnSplit);
    }
}

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
package com.github.paohaijiao.geo;

import lombok.Data;

/**
 * packageName com.github.paohaijiao.geo
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/1/3
 */
@Data
public class GeoOption {

    private String geoJsonContent;

    private float padding = 0.005f;

    private String backgroundColor ="#4CAF50";

    private String title = "地图";
}

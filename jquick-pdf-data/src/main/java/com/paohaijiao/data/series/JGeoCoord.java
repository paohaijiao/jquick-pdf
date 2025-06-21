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

package com.paohaijiao.data.series;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 地图特有，标线图形定位坐标
 *
 * @author martin
 */
public class JGeoCoord extends HashMap<String, BigDecimal[]> implements Serializable {

    private static final long serialVersionUID = 7548362611708057870L;

    /**
     * 设置key,x,y值
     *
     * @param key
     * @param x
     * @param y
     */
    public JGeoCoord put(String key, String x, String y) {
        super.put(key, new BigDecimal[]{new BigDecimal(x), new BigDecimal(y)});
        return this;
    }

}

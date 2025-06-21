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
package com.paohaijiao.echart.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EChartConfigBuilder
 * @Description: Description
 * @Author: gou
 * @CreateDate: 2025/6/12 8:43
 * @UpdateUser: UpdateUser
 * @UpdateDate: 2025/6/12 8:43
 * @UpdateRemark:
 * @Version: 1.0
 */
public class EChartConfigBuilder {
    public static class EChartConfig {
        private Map<String, Object> options = new LinkedHashMap<>();

        public EChartConfig add(String key, Object value) {
            options.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return options;
        }

        @Override
        public String toString() {
            return options.toString();
        }
    }

    protected static Map<String, Object> mapOf(Object... args) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i].toString(), args[i + 1]);
        }
        return map;
    }

    protected static List<Object> listOf(Object... items) {
        return Arrays.asList(items);
    }
}

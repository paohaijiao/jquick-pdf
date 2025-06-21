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
package com.paohaijiao.echart.provider;

import com.paohaijiao.data.JOption;

import java.io.IOException;

public interface JChartRenderer {
    /**
     * 渲染图表到文件
     * @param option 图表配置选项
     * @param outputPath 输出文件路径
     * @throws IOException 如果文件操作出错
     */
    void render(JOption option, String outputPath) throws IOException;

    /**
     * 渲染图表到字符串
     * @param option 图表配置选项
     * @return SVG字符串
     * @throws IOException 如果操作出错
     */
    String renderToString(JOption option) throws IOException;
}

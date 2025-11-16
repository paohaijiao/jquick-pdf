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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.matrix.JCorrelationMatrixRenderer;
import com.github.paohaijiao.matrix.JCorrelationMatrixOption;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * packageName PACKAGE_NAME
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/16
 */
public class JCollectionMatrixTest {
    @Test
    public void testBarChar1() throws IOException, ParserConfigurationException {
        try {
            double[][] correlationData = {
                    {1.00, -0.20, 0.03, -0.62, -0.54, -0.21, 0.63, 0.30},
                    {-0.20, 1.00, 0.36, -0.61, -0.26, 0.05, 0.16, 0.41},
                    {0.03, 0.36, 1.00, -0.74, -0.94, 0.71, -0.90, -0.66},
                    {-0.62, -0.61, -0.74, 1.00, 0.37, -0.66, 0.54, -0.66},
                    {-0.54, -0.26, -0.94, 0.37, 1.00, -0.05, -0.46, 0.71},
                    {-0.21, 0.05, 0.71, -0.66, -0.05, 1.00, -0.84, -0.40},
                    {0.63, 0.16, -0.90, 0.54, -0.46, -0.84, 1.00, -0.55},
                    {0.30, 0.41, -0.66, -0.66, 0.71, -0.40, -0.55, 1.00}
            };
            String[] dimensions = {"销售额", "广告费", "促销费", "竞品价", "季节指数", "GDP", "人口", "天气"};
            JCorrelationMatrixOption option = JCorrelationMatrixOption.builder()
                    .title("销售因素相关系数矩阵", "各因素之间的相关性分析")
                    .dataset(correlationData)
                    .build();
            option.dataset().dimensions(dimensions);
            JOption jOption = new JOption();
            jOption.setCorrelationMatrixOption(option);
            JCorrelationMatrixRenderer renderer = new JCorrelationMatrixRenderer();
            renderer.render(jOption, "d://test//Matrix.svg");
            System.out.println("相关系数矩阵图表生成完成！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

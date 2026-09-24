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
package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.matrix.JCorrelationMatrixOption;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 统一信用报告 —— 风格 11：相关矩阵（靛蓝）
 *
 * <p>以 {@code report/credit_report_11_matrix.txt} 为模板，把相关矩阵渲染成 SVG 后
 * 绑定到模板中的 {@code ${svg}} 占位符，输出到 {@code D:\test\creditreport\11_matrix.pdf}。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickCreditReport11MatrixTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void report11Matrix() throws IOException {
        double[][] correlationData = new double[][]{
                {1.00, 0.63, 0.30, -0.62, -0.54, 0.42},
                {0.63, 1.00, 0.55, -0.41, -0.36, 0.38},
                {0.30, 0.55, 1.00, -0.28, -0.24, 0.31},
                {-0.62, -0.41, -0.28, 1.00, 0.72, -0.46},
                {-0.54, -0.36, -0.24, 0.72, 1.00, -0.39},
                {0.42, 0.38, 0.31, -0.46, -0.39, 1.00}
        };
        JCorrelationMatrixOption matrixOption = JCorrelationMatrixOption.builder()
                .title("财务指标相关矩阵", "各财务指标之间的相关性分析")
                .dataset(correlationData)
                .build();
        matrixOption.dataset().dimensions(new String[]{"营业收入", "净利润", "总资产", "资产负债率", "流动比率", "研发投入"});
        JOption option = new JOption();
        option.setCorrelationMatrixOption(matrixOption);

        String svg = JChartRendererFactory.renderChart(JChartType.CorrectionMatrix, option);

        File dir = new File(path + "creditreport");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "creditreport/11_matrix.pdf");
        JPdfConfig config = new JPdfConfig();
        // 开启 flex 行布局：图表与其右侧的指标说明面板在宽度允许时并排，而不是换行堆叠。
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/credit_report_11_matrix.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

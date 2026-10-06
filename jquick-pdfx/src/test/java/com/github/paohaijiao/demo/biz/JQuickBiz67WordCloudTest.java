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
package com.github.paohaijiao.demo.biz;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;
import com.github.paohaijiao.words.JWordCloudSeries;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景 Demo 67：产品用户评价关键词词云（用户研究）
 *
 * <p>业务场景：用户研究部门对 50,000 条用户评价进行 NLP 分词统计，
 * 以词云图展示高频关键词，字体越大代表出现频次越高。</p>
 * <p>实际图表类型：{@link JChartType#WORDCLOUD} 词云图。</p>
 *
 * <p>模板：{@code report/biz/67_wordcloud.txt}；图表以 SVG 文本形式绑定到模板的
 * {@code ${svg}} 占位符，输出到 {@code D:\test\biz\67_wordcloud.pdf}。</p>
 *
 * <p>图表尺寸说明：图表按 700pt × 380pt 的横版比例设计；模板中 {@code <svg>}
 * 只声明宽度、不声明高度，宽度被页面自动收窄时高度会按原比例同步缩放。</p>
 *
 * @author Martin
 * @version 1.0.0
 */
public class JQuickBiz67WordCloudTest {

    public static final String path = JQuickConstant.path;

    @Test
    public void biz67WordCloud() throws IOException {
        List<JData> wordList = new ArrayList<>();
        // 正面高频词
        wordList.add(new JData("稳定", 9800));
        wordList.add(new JData("高效", 8600));
        wordList.add(new JData("易用", 7500));
        wordList.add(new JData("界面清晰", 6200));
        wordList.add(new JData("响应快", 5800));
        wordList.add(new JData("功能丰富", 5100));
        wordList.add(new JData("体验好", 4700));
        wordList.add(new JData("性价比高", 4300));
        wordList.add(new JData("服务周到", 3900));
        wordList.add(new JData("更新及时", 3500));
        // 中性词
        wordList.add(new JData("一般", 2800));
        wordList.add(new JData("还可以", 2200));
        wordList.add(new JData("有待提升", 1800));
        // 需关注的负面词
        wordList.add(new JData("卡顿", 1600));
        wordList.add(new JData("闪退", 900));
        wordList.add(new JData("加载慢", 700));
        wordList.add(new JData("偶尔崩溃", 500));
        wordList.add(new JData("耗电", 450));
        wordList.add(new JData("广告多", 380));

        JWordCloudSeries wordCloudSeries = new JWordCloudSeries("用户评价关键词");
        wordCloudSeries.data(wordList);
        wordCloudSeries.minFontSize(14);
        wordCloudSeries.maxFontSize(80);

        JOption option = new JOption();
        option.title("年度产品用户评价关键词词云", "样本量：50,000 条评价 | 字体大小代表出现频次");
        option.series(wordCloudSeries);

        String svg = JChartRendererFactory.renderChart(JChartType.WORDCLOUD, option);

        File dir = new File(path + "biz");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "biz/67_wordcloud.pdf");
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svg);
        byte[] bytes = factory.executeResource("report/biz/67_wordcloud.txt");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

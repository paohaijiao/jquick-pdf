package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.k.JKChartsRenderer;
import com.github.paohaijiao.series.JCandlestick;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class KReportDemo {
    public static void main(String[] args) throws Exception {
        JOption option = new JOption();
        option.title().text("股票K线图");
        option.tooltip().trigger(JTrigger.axis);
        option.xAxis(new JCategoryAxis().data("01/01", "01/02", "01/03", "01/04"));
        option.yAxis(new JValueAxis());

        JCandlestick k = new JCandlestick().name("股价").data(
                new Object[]{105.2, 108.5, 104.8, 109.1},
                new Object[]{108.6, 107.8, 106.5, 109.5},
                new Object[]{107.9, 105.3, 104.2, 108.0},
                new Object[]{105.4, 106.1, 104.5, 107.2});
        option.series(k);

        String template = "<pdf><body><h1>'投研日报'</h1><svg>&{svg}</svg>"
                + "<p>'数据仅用于报告展示，不构成投资建议。'</p></body></pdf>";
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.K);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);
        Files.write(Paths.get("d://test//k-report.pdf"), pdf);    }
}
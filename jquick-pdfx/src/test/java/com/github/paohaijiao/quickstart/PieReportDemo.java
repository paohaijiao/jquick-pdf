package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JData;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.pie.JPieChartsRenderer;
import com.github.paohaijiao.series.JPie;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class PieReportDemo {
    public static void main(String[] args) throws Exception {
        JOption option = new JOption();
        option.title().text("部门费用占比").subtext("本季度");
        option.tooltip().trigger(JTrigger.item);
        JPie pie = new JPie("费用");
        pie.data(new JData().name("研发").value(420), new JData().name("销售").value(260),
                new JData().name("行政").value(120), new JData().name("客服").value(200));
        option.series(pie);
        String template = "<pdf><body><h1>'费用结构报告'</h1><svg>&{svg}</svg>"
                + "<p>'金额单位：万元，合计：1000。'</p></body></pdf>";
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.PIE);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);
        Files.write(Paths.get("d://test//pie-report.pdf"), pdf);
    }
}

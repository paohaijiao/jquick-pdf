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
import com.github.paohaijiao.series.JBar;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BarReportDemo {
    public static void main(String[] args) throws Exception {
        JOption option = new JOption();
        option.title().text("区域销售额").subtext("2024年度");
        option.tooltip().trigger(JTrigger.axis);
        option.xAxis(new JCategoryAxis().data("华东", "华南", "华北", "西部"));
        option.yAxis(new JValueAxis());
        option.series(new JBar().name("销售额").data(520, 430, 390, 280));
        String template = "<pdf><body><h1>'区域销售报表'</h1><svg>&{svg}</svg>"
                + "<p>'单位：万元；数据来自月度结算表。'</p></body></pdf>";
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.BAR);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);
        Files.write(Paths.get("d://test//bar-report.pdf"), pdf);
    }
}

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
import com.github.paohaijiao.line.JLineChartsRenderer;
import com.github.paohaijiao.series.JLine;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class LineReportDemo {
    public static void main(String[] args) throws Exception {
        JOption option = new JOption();
        option.title().text("月活用户趋势");
        option.tooltip().trigger(JTrigger.axis);
        option.xAxis(new JCategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月"));
        option.yAxis(new JValueAxis());
        option.series(new JLine().name("MAU").data(120, 132, 156, 149, 188, 214));
        String template = "<pdf><body><h1>'用户增长趋势报表'</h1><svg>&{svg}</svg>"
                + "<p>'统计口径：去重登录用户；数据截止每月末。'</p></body></pdf>";
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.LINE);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);
        Files.write(Paths.get("d://test//line-report.pdf"), pdf);
    }
}
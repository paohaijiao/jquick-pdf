package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.*;
import com.github.paohaijiao.axis.*;
import com.github.paohaijiao.code.*;
import com.github.paohaijiao.config.*;
import com.github.paohaijiao.data.*;
import com.github.paohaijiao.enums.*;
import com.github.paohaijiao.executor.*;
import com.github.paohaijiao.heatMap.*;
import com.github.paohaijiao.radar.*;
import com.github.paohaijiao.series.*;
import java.nio.file.*;

public class ServiceAnalysisPdf {
    public static void main(String[] args) throws Exception {
        JOption heat = new JOption();
        heat.title("客服响应时长热力图");
        heat.xAxis(new JCategoryAxis().data("1月", "2月", "3月"));
        heat.yAxis(new JCategoryAxis().data("上午", "下午"));
        heat.series(new JHeatmap().data(new Object[]{0, 0, 18}, new Object[]{0, 1, 12},
                new Object[]{1, 0, 20}, new Object[]{1, 1, 14},
                new Object[]{2, 0, 16}, new Object[]{2, 1, 11}));
        new JHeatMapChartRenderer().renderToString(heat);

        JOption radarOption = new JOption();
        radarOption.title().text("团队能力雷达图");
        JRadar radar = new JRadar();
        radar.indicator(new JRadar.Indicator().name("响应").max(100),
                new JRadar.Indicator().name("解决").max(100),
                new JRadar.Indicator().name("满意度").max(100),
                new JRadar.Indicator().name("履约").max(100),
                new JRadar.Indicator().name("复盘").max(100));
        radarOption.radar(radar);
        radarOption.series(
                new JRadarSeries().name("本月").type(JSeriesType.radar).data(86, 78, 91, 82, 75),
                new JRadarSeries().name("上月").type(JSeriesType.radar).data(79, 74, 87, 80, 68));
        new JRadarChartsRenderer().renderToString(radarOption);

        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("heatmap", chart(JChartType.HEATMAP, heat));
        graphConfig.put("radar", chart(JChartType.RADAR, radarOption));
        JPdfConfig pdfConfig = new JPdfConfig();
        pdfConfig.setGraphConfig(graphConfig);

        byte[] pdf = new JQuickPdfFactory(pdfConfig)
                .bind("period", "2026年8月")
                .executeResource("templates/service-analysis.xml");
        Files.write(Paths.get("d://test//service-analysis.pdf"), pdf);
    }

    private static JGraphContainer chart(JChartType type, JOption option) {
        JGraphContainer container = new JGraphContainer();
        container.setOption(option);
        container.setType(type);
        return container;
    }
}

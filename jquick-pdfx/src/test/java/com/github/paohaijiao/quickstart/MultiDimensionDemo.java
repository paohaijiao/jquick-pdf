package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.JOption;
import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.heatMap.JHeatMapChartRenderer;
import com.github.paohaijiao.series.JHeatmap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class MultiDimensionDemo {


    public static void main(String[] args) throws Exception {
        JOption option = new JOption();
        option.title().text("客服忙闲热力图");
        option.xAxis(new JCategoryAxis().data("周一", "周二", "周三", "周四"));
        option.yAxis(new JCategoryAxis().data("上午", "下午", "晚上"));
        option.series(new JHeatmap().data(new Object[]{0, 0, 12}, new Object[]{1, 0, 18},
                new Object[]{2, 1, 32}, new Object[]{3, 2, 25}));
        String template = "<pdf><body><h1>'客服运营多维分析'</h1><svg>&{svg}</svg>"
                + "<p>'热力值为进入工单数；雷达图建议由独立 SVG 绑定。'</p></body></pdf>";
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.HEATMAP);
        graphContainer.setOption(option);
        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);
        Files.write(Paths.get("d://test//heat-report.pdf"), pdf);
    }

}
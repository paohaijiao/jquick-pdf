package com.github.paohaijiao;

import com.github.paohaijiao.combol.JCircleChartData;
import com.github.paohaijiao.combol.JCircleChartRenderer;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JCircleChartTest {

    @Test
    public void testCircleChar1() throws IOException {
        JCircleChartData chartData = new JCircleChartData();
        chartData.setWidth(500);
        chartData.setHeight(400);
        chartData.setTitleText("2024年度销售分布");
        chartData.setSubtitleText("按产品类别统计");
        chartData.setCenterTitle("总销售额");
        chartData.setCenterUnit("万");
        chartData.setFooterText("数据基于2024年度销售报告");
        List<JCircleChartData.SectorData> sectors = new ArrayList<>();
        sectors.add(new JCircleChartData.SectorData("产品A", 4480, new Color(46, 125, 100)));
        sectors.add(new JCircleChartData.SectorData("产品B", 3584, new Color(74, 144, 196)));
        sectors.add(new JCircleChartData.SectorData("产品C", 2816, new Color(91, 108, 142)));
        sectors.add(new JCircleChartData.SectorData("产品D", 1920, new Color(154, 172, 184)));
        chartData.setSectorDataList(sectors);
        JOption option = new JOption();
        option.setData(chartData);
        JCircleChartRenderer renderer = new JCircleChartRenderer();
        renderer.render(option, "d://test//circle-chart.svg");
    }
}

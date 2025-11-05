package com.github.paohaijiao.ele;

import java.util.*;

/**
 * 气泡图数据创建工具类
 */
public class BubbleDataCreator {

    /**
     * 创建AQI空气质量监测示例数据
     */
    public static List<Map<String, Object>> createAQISampleData() {
        List<Map<String, Object>> data = new ArrayList<>();
        Random random = new Random(42); // 固定种子以便重现

        String[] dates = {"01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "01-09", "01-10", "01-11", "01-12", "01-13", "01-14", "01-15"};
        for (int i = 0; i < dates.length; i++) {
            int aqi = 20 + random.nextInt(180); // AQI 20-200
            double pm25 = 10 + random.nextDouble() * 150; // PM2.5 10-160

            String category;
            if (aqi <= 50) category = "优";
            else if (aqi <= 100) category = "良";
            else if (aqi <= 150) category = "轻度污染";
            else if (aqi <= 200) category = "中度污染";
            else category = "重度污染";

            String name = String.format("日期:%s, AQI:%d, PM2.5:%.1f", dates[i], aqi, pm25);

            Map<String, Object> dataPoint = new HashMap<>();
            dataPoint.put("x", dates[i]);
            dataPoint.put("y", aqi);
            dataPoint.put("size", pm25);
            dataPoint.put("category", category);
            dataPoint.put("name", name);

            data.add(dataPoint);
        }

        return data;
    }

    /**
     * 创建完整的AQI气泡图系列数据
     */
    public static List<Object> createAQIBubbleSeriesData() {
        return new ArrayList<>(createAQISampleData());
    }
}
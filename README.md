<p align="center">
  <img src="./images/jquick-logo.svg" width="680" alt="jquick-pdf logo" />
</p>

<h1 align="center">jquick-pdf</h1>

<p align="center">
  A pure-Java PDF toolkit that turns an HTML/CSS-like template into PDF documents — no browser, no WebKit, no external rendering engine.
</p>

<p align="center">
  <b>English</b> | <a href="./README_zh.md">简体中文</a>
</p>

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-pdfx"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-pdfx.svg?style=for-the-badge&label=Maven%20Central" alt="Maven Central" /></a>
  <a href="#version-matrix"><img src="https://img.shields.io/badge/license-Apache--2.0%20(%E2%89%A5%204.0.1)%20%7C%20AGPL--3.0%20(%E2%89%A4%204.0.0)-blue.svg?style=for-the-badge" alt="License" /></a>
  <a href="https://github.com/paohaijiao/jquick-pdf"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-pdf.svg?style=for-the-badge&logo=github&label=Stars" alt="GitHub stars" /></a>
  <a href="https://github.com/paohaijiao/jquick-pdf/issues"><img src="https://img.shields.io/github/issues/paohaijiao/jquick-pdf.svg?style=for-the-badge&label=Issues" alt="GitHub issues" /></a>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-8%2B-orange.svg" alt="Java 8+" />
  <img src="https://img.shields.io/badge/PDFBox-3.0.x-brightgreen.svg" alt="Apache PDFBox 3" />
  <img src="https://img.shields.io/badge/ANTLR-4.x-blue.svg" alt="ANTLR 4" />
</p>

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Quick Start](#quick-start)
  - [Requirements](#requirements)
  - [Modules](#modules)
  - [Maven dependency — 4.0.1 and above (Apache-2.0)](#maven-dependency--401-and-above-apache-20)
  - [Maven dependency — 4.0.0 and below (AGPL-3.0, iText 7)](#maven-dependency--400-and-below-agpl-30-itext-7)
  - [Hello world](#hello-world)
- [Demo Gallery](#demo-gallery)
  - [Image index: image → test class → method](#image-index-image--test-class--method)
    - [1. Bar chart](#1-bar-chart--imagesbarchartsvg)
    - [2. Line chart](#2-line-chart--imagesline_chartsvg)
    - [3. Pie chart](#3-pie-chart--imagespie-chartsvg)
    - [4. Radar chart](#4-radar-chart--imagesradar_chartsvg)
    - [5. Box plot](#5-box-plot--imagesboxchartsvg)
    - [6. Heat map](#6-heat-map--imagesheatmapsvg)
    - [7. K-line (candlestick)](#7-k-line-candlestick--imagesk_chartsvg)
    - [8. Scatter](#8-scatter--imagesscattersvg)
    - [9. Bubble](#9-bubble--imagesbubblesvg)
    - [10. Funnel](#10-funnel--imagesfunnelsvg)
    - [11. Gauge](#11-gauge--imagesgaugesvg)
    - [12. Gantt](#12-gantt--imagesganttsvg)
    - [13. Calendar](#13-calendar--imagescalendarsvg)
    - [14. Lunar calendar](#14-lunar-calendar--imageslunarsvg)
    - [15. Word cloud](#15-word-cloud--imageswordcloudsvg)
    - [16. Geo map](#16-geo-map--imagesgeosvg)
    - [17. Relation graph](#17-relation-graph--imagesrelation_chartsvg)
    - [18. Sunburst](#18-sunburst--imagessunburstsvg)
    - [19. Treemap](#19-treemap--imagestreemapsvg)
    - [20. Correlation matrix](#20-correlation-matrix--imagesmatrixsvg)
    - [21. Stacked area](#21-stacked-area--imagesareasvg)
    - [22. Line + bar combo](#22-line--bar-combo--imageslinebarsvg)
    - [23. Multi line](#23-multi-line--imagesmultiplelinesvg)
    - [24. Multi bar](#24-multi-bar--imagesfourbarsvg)
    - [25. Horizontal bar](#25-horizontal-bar--imageshorizontalbarsvg)
    - [26. Multi horizontal bar](#26-multi-horizontal-bar--imagesmhbarchartsvg)
    - [27. Double radar](#27-double-radar--imagestworadarsvg)
    - [28. Line + scatter](#28-line--scatter--imageslineradarsvg)
    - [29. Circle / ring](#29-circle--ring--imagescircle-chartsvg)
    - [30. Timeline](#30-timeline--imagestimelinesvg)
    - [31. Advanced topology (microservice)](#31-advanced-topology-microservice--imagesadvance_topologysvg)
    - [32. Cloud architecture topology](#32-cloud-architecture-topology--imagestoplogycloud_architecturesvg)
    - [33. Data center topology](#33-data-center-topology--imagestoplogydatacenter_topologysvg)
    - [34. Enterprise network topology](#34-enterprise-network-topology--imagestoplogyenterprise_networksvg)
    - [35. Manual-layout topology](#35-manual-layout-topology--imagestoplogymanual_layout_topologysvg)
    - [36. Credit report page](#36-credit-report-page--imagescredit_reportpng)
  - [Demo 1 — Enterprise credit rating report (composite)](#demo-1--enterprise-credit-rating-report-composite)
  - [Demo 2 — Bar chart (chart module)](#demo-2--bar-chart-chart-module)
  - [Demo 3 — SVG chart embedded into a PDF page](#demo-3--svg-chart-embedded-into-a-pdf-page)
  - [Element and style reference](#element-and-style-reference)
- [Version Matrix](#version-matrix)
- [License](#license)
- [Contribution Guide](#contribution-guide)

---

## Introduction

**jquick-pdf** is the PDF toolkit of the JQuick ecosystem. It targets **generating, rendering and exporting** PDF documents from Java: write a template that looks like HTML with inline CSS, bind your data, and get a PDF back as a `byte[]`, a stream, or a file.

Templates are parsed by an ANTLR4 grammar and drawn directly onto the page with [Apache PDFBox](https://pdfbox.apache.org/) — there is **no browser, no headless Chrome and no native library** in the pipeline, which makes the library easy to embed in a server, a scheduled job or a desktop application.

The toolkit covers two layers:

- **Document layer** (`jquick-pdfx`): an HTML/CSS-like template language (headings, paragraphs, blocks, lists, tables, forms, images, SVG) with data binding, pagination control, tables of contents, watermarks and encryption.
- **Chart layer** (`jquick-pdf-svg`): 30+ chart types (bar, line, pie, radar, scatter, boxplot, heatmap, K-line, funnel, gauge, gantt, word cloud, geo map, sunburst, treemap, bubble, calendar, timeline, topology and more) rendered as vector graphics — bound into a document with a single placeholder.

> **License boundary — read [Version Matrix](#version-matrix) before shipping.**
> Version **4.0.0** is **AGPL-3.0** (iText 7 based). Version **4.0.1 and above** is **Apache-2.0** (migrated to Apache PDFBox, AGPL dependency removed).

## Features

- **Pure Java, no external engine** — no WebKit, no Chrome, no OS-level dependency; one JVM process is enough.
- **HTML/CSS-like templates** — 14 elements plus a `style="..."` attribute on every element.
- **Both naming conventions accepted** — camelCase (`minHeight`) and standard CSS kebab-case (`min-height`) are interchangeable, and may be mixed in one declaration.
- **Data binding** — `${variable}` for bound values, `&{resource}` for registered resources (charts, templates, trees).
- **30+ chart types** — the full chart gallery with preview images and the test class that generates each one is in the [Demo Gallery](#demo-gallery).
- **Rich document capabilities** — automatic pagination, table of contents, header/footer, page counters, watermarks, PDF encryption.
- **CJK ready** — a Chinese font is bundled (`jquick-pdf-font`), so CJK text renders without extra configuration.
- **Small, modular artifacts** — depend on the parser/renderer only, then add charts, CSS or fonts as needed.
- **Java 8 compatible** — released artifacts target Java 8 bytecode.

## Quick Start

### Requirements

| Item | Version |
|---|---|
| JDK | 8 or higher |
| Maven | 3.6 or higher (only needed to build from source) |
| Runtime dependencies | Apache PDFBox 3.0.x, ANTLR4 runtime, SLF4J API (resolved transitively) |

### Modules

| Module (directory) | Artifact | Description |
|---|---|---|
| `jquick-pdfx` | `io.github.paohaijiao:jquick-pdfx` | Parser, renderers, layout engine and the entry point `JQuickPdfFactory`. Most users only need this artifact. |
| `jquick-pdf-css` | `io.github.paohaijiao:jquick-pdf-css` | CSS model: style attributes, units and colors. |
| `jquick-pdf-svg` | `io.github.paohaijiao:jquick-pdf-svg` | SVG / ECharts-style chart generation (30+ chart types). |
| `jquick-pdf-data` | `io.github.paohaijiao:jquick-pdf-data` | Chart option model (`JOption`, `JChart`, `JTitle`, `JLegend`, ...). |
| `jquick-pdf-font` | `io.github.paohaijiao:jquick-pdf-font` | Bundled CJK font resources. |

### Maven dependency — 4.0.1 and above (Apache-2.0)

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdfx</artifactId>
    <version>4.0.0</version>
</dependency>
```

Chart support (optional):

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdf-svg</artifactId>
    <version>4.0.0</version>
</dependency>
```

### Maven dependency — 4.0.0 and below (AGPL-3.0, iText 7)

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdfx</artifactId>
    <version>4.0.0</version>
</dependency>
```

> **License risk:** 4.0.0 and below are licensed under **AGPL-3.0** because they depend on **iText 7 Core**. Using them in a closed-source product requires full AGPL compliance or a commercial license from iText Group NV. PDFs produced by these versions carry the `Powered by iText` notice.
> Migrate to **4.0.1 or later** to get **Apache-2.0** and drop the obligation — see [License](#license).

### Hello world

```java
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloDemo {

    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf>"
                + "  <body>"
                + "    <h1 style=\"fontSize:24;fontColor:blue\">'Hello jquick-pdf'</h1>"
                + "    <p>'Customer: '${customer}</p>"
                + "  </body>"
                + "</pdf>";

        byte[] pdf = JQuickPdfFactory.create()
                .bind("customer", "Martin")
                .executeContent(template);

        Files.write(Paths.get("hello.pdf"), pdf);
    }
}
```

Three ways to feed a template, all returning `byte[]`:

| Method | Source |
|---|---|
| `executeContent(String)` | template text in memory |
| `executeResource(String)` | classpath resource, e.g. `"report.txt"` |
| `executeFile(String)` | file on disk, e.g. `"D:/templates/report.txt"` |

Text nodes are always single-quoted (`'Hello jquick-pdf'`); `<pdf>` and `<html>` are both accepted as the document root.

## Demo Gallery

Every image under `images/` is produced by a **real test class** in this repository. Each entry below shows the preview first and the key code that produces it second; the file path inside the code is the literal path the test writes (`D:\test\` by default, see `com.github.paohaijiao.demo.constant.JQuickConstant`). Long data lists are elided with `// ...`.

### Image index: image → test class → method

Two notes before the list:

1. `images/` is a curated copy: a few files were renamed when they were added to the repository, so the file name may differ from the literal path written by the test (for example `images/linebar.svg` ← `d://test/custom_chart.svg`). The path inside each code block is the authoritative value.
2. `TreemapTest` is entirely commented out in the source, so the treemap preview corresponds to `JTreemapRendererExample#main` instead.

#### 1. Bar chart — `images/barchart.svg`

![barchart](images/barchart.svg)

**Test Class:** `com.github.paohaijiao.BarCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title().text("销售数据").subtext("2023年度");
option.tooltip().trigger(JTrigger.axis);
JCategoryAxis xAxis = new JCategoryAxis();
xAxis.data("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子");
option.xAxis(xAxis);
option.yAxis(new JValueAxis());
JBar bar = new JBar();
bar.name("销量").data(5, 20, 36, 10, 10, 20);
option.series(bar);
JBarChartsRenderer jBarChartsRenderer = new JBarChartsRenderer();
jBarChartsRenderer.render(option, "D://test//barchart.svg");
String str = jBarChartsRenderer.renderToString(option);
System.out.println(str);
```

#### 2. Line chart — `images/line_chart.svg`

![line_chart](images/line_chart.svg)

**Test Class:** `com.github.paohaijiao.LineCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title().text("销售数据折线图");
option.tooltip().trigger(JTrigger.axis);
JCategoryAxis xAxis = new JCategoryAxis();
xAxis.data("1月", "2月", "3月", "4月", "5月", "6月", "7月");
option.xAxis(xAxis);
option.yAxis(new JValueAxis());
JLine line = new JLine();
line.name("销售额").data(120, 132, 101, 134, 90, 230, 210);
option.series(line);
JLineChartsRenderer renderer = new JLineChartsRenderer();
renderer.render(option, "D://test//line_chart.svg");
```

#### 3. Pie chart — `images/pie-chart.svg`

![pie-chart](images/pie-chart.svg)

**Test Class:** `com.github.paohaijiao.PieCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title().text("销售占比").subtext("2023年度");
option.tooltip().trigger(JTrigger.item);
JPie pie = new JPie("销售占比");
pie.data(
        new JData().name("衬衫").value(35),
        new JData().name("羊毛衫").value(20),
        new JData().name("雪纺衫").value(15),
        new JData().name("裤子").value(18),
        new JData().name("高跟鞋").value(8),
        new JData().name("袜子").value(4)
);
option.series(pie);
JPieChartsRenderer renderer = new JPieChartsRenderer();
renderer.render(option, "d://test//pie-chart.svg");
```

#### 4. Radar chart — `images/radar_chart.svg`

![radar_chart](images/radar_chart.svg)

**Test Class:** `com.github.paohaijiao.RadarCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
// 创建图表选项
JOption option = new JOption();
option.title().text("雷达图示例")
        .subtext("预算 vs 开销对比")
        .left("center")
        .textStyle(new JTextStyle().color("#333"));
// 设置提示框
option.tooltip().trigger(JTrigger.item);
// 设置雷达图指标
JRadar radar = new JRadar();
radar.indicator(
        new JRadar.Indicator().name("销售").max(6500),
        new JRadar.Indicator().name("管理").max(16000),
        new JRadar.Indicator().name("信息技术").max(30000),
        new JRadar.Indicator().name("客服").max(38000),
        new JRadar.Indicator().name("研发").max(52000),
        new JRadar.Indicator().name("市场").max(25000)
);
option.radar(radar);
// 添加雷达图系列数据
JRadarSeries budgetSeries = new JRadarSeries();
budgetSeries.name("预算")
        .type(JSeriesType.radar)
        .data(4300, 10000, 28000, 35000, 50000, 19000);
JRadarSeries actualSeries = new JRadarSeries();
actualSeries.name("实际开销")
        .type(JSeriesType.radar)
        .data(5000, 14000, 28000, 31000, 42000, 21000);
option.series(budgetSeries, actualSeries);
JRadarChartsRenderer renderer = new JRadarChartsRenderer();
renderer.render(option, "d://test//radar_chart.svg");
```

#### 5. Box plot — `images/boxchart.svg`

![boxchart](images/boxchart.svg)

**Test Class:** `com.github.paohaijiao.BoxPlotTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title().text("销售数据分布");
option.xAxis(new JCategoryAxis().data("一季度", "二季度", "三季度", "四季度"));
option.series(new JBoxplot().data(
        new Object[]{10, 15, 20, 25, 30},
        new Object[]{12, 18, 22, 28, 35},
        new Object[]{8, 14, 19, 26, 32},
        new Object[]{11, 16, 21, 27, 33}
));
JBoxPlotChartRenderer jBarChartsRenderer = new JBoxPlotChartRenderer();
jBarChartsRenderer.render(option, "D://test//boxchart.svg");
```

#### 6. Heat map — `images/heatmap.svg`

![heatmap](images/heatmap.svg)

**Test Class:** `com.github.paohaijiao.HeatMapCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title("2023年月度温度分布热力图");
option.xAxis(new JCategoryAxis()
        .data("1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月"));
option.yAxis(new JCategoryAxis()
        .data("凌晨(0-6)", "早晨(6-9)", "上午(9-12)",
                "中午(12-14)", "下午(14-18)", "晚上(18-24)"));
JHeatmap heatmap = new JHeatmap();
heatmap.data(
        new Object[]{0, 0, -5.2}, new Object[]{0, 1, -3.8}, new Object[]{0, 2, 1.5},
        new Object[]{0, 3, 4.2}, new Object[]{0, 4, 2.8}, new Object[]{0, 5, -2.1},
        // ... remaining data points {1,*} to {10,*}
        new Object[]{11, 0, -2.8}, new Object[]{11, 1, -0.5}, new Object[]{11, 2, 3.5},
        new Object[]{11, 3, 6.8}, new Object[]{11, 4, 4.2}, new Object[]{11, 5, 0.0}
);
option.series(heatmap);
JHeatMapChartRenderer renderer = new JHeatMapChartRenderer();
renderer.render(option, "d://test//heatmap.svg");
```

#### 7. K-line (candlestick) — `images/k_chart.svg`

![k_chart](images/k_chart.svg)

**Test Class:** `com.github.paohaijiao.KCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
option.title().text("股票K线图(含数据)");
option.tooltip().trigger(JTrigger.axis);
JCategoryAxis xAxis = new JCategoryAxis();
xAxis.data("01/01", "01/02", "01/03", "01/04", "01/05",
        "01/06", "01/07", "01/08", "01/09", "01/10");
option.xAxis(xAxis);
option.yAxis(new JValueAxis());
JCandlestick candlestick = new JCandlestick();
candlestick.name("股价")
        .data(
                new Object[]{105.2, 108.5, 104.8, 109.1},
                new Object[]{108.6, 107.8, 106.5, 109.5},
                new Object[]{107.9, 105.3, 104.2, 108.0},
                new Object[]{105.4, 106.1, 104.5, 107.2},
                new Object[]{106.2, 104.8, 103.0, 107.5},
                // ... remaining candles
                new Object[]{110.4, 112.1, 109.5, 112.8}
        );
option.series(candlestick);
JKChartsRenderer renderer = new JKChartsRenderer();
renderer.render(option, "d://test//k_chart.svg");
```

#### 8. Scatter — `images/scatter.svg`

![scatter](images/scatter.svg)

**Test Class:** `com.github.paohaijiao.ScatterCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JData[] data = new JData[]{
        new JData().value(new Double[]{10.0, 8.04}),
        new JData().value(new Double[]{8.07, 6.95}),
        new JData().value(new Double[]{13.0, 7.58}),
        new JData().value(new Double[]{9.05, 8.81}),
        // ... remaining points
        new JData().value(new Double[]{7.08, 5.82}),
        new JData().value(new Double[]{5.02, 5.68})
};
JOption option = new JOption();
option.title().text("散点图示例");
option.tooltip().trigger(JTrigger.axis);
option.xAxis(new JValueAxis().scale(true));
option.yAxis(new JValueAxis().scale(true));
JScatter scatter = new JScatter();
scatter.symbolSize(20)
        .data(data);
option.series(scatter);
JScatterChartsRenderer renderer = new JScatterChartsRenderer();
renderer.render(option, "d://test//scatter.svg");
```

#### 9. Bubble — `images/bubble.svg`

![bubble](images/bubble.svg)

**Test Class:** `com.github.paohaijiao.BubbleTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JTitle title = new JTitle();
title.setText("空气质量指数 (AQI) 监测气泡图");
title.setSubtext("图表说明：本气泡图展示了空气质量指数(AQI)的时间变化趋势。X轴表示日期，Y轴表示AQI数值，气泡大小反映PM2.5浓度，气泡颜色表示AQI等级。");
JOption option = new JOption()
        .title(title)
        .legend("优", "良", "轻度污染", "中度污染", "重度污染")
        .xAxis(new CategoryAxis().name("日期"))
        .yAxis(new ValueAxis().name("AQI数值"));
ScatterSeries series = new ScatterSeries("空气质量监测");
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
series.data(data.toArray());
option.series(series);
JBubbleChartRenderer renderer = new JBubbleChartRenderer();
renderer.render(option, "d://test//bubble.svg");
```

#### 10. Funnel — `images/funnel.svg`

![funnel](images/funnel.svg)

**Test Class:** `com.github.paohaijiao.FunelTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JFunnelOption option = JFunnelOption.createDefaultFunnel();
JFunnelOption customOption = option
        .title(new Title().text("销售漏斗").subtext("2024年数据"))
        .funnel(new Funnel()
                .width(600)
                .topY(80)
                .bottomY(200)
                .gap(2)
                .borderColor(Color.GRAY)
        )
        .series(Collections.singletonList(
                new Series()
                        .name("sales")
                        .type("funnel")
                        .data(Arrays.asList(
                                new DataItem("展现", 10000),
                                new DataItem("点击", 5000),
                                new DataItem("咨询", 2000),
                                new DataItem("订单", 500)
                        ))
        ))
        .colors(
                new Color(12, 168, 223),
                new Color(255, 153, 77),
                new Color(80, 112, 221),
                new Color(182, 214, 52)
        );
JFunnelChartRenderer renderer = new JFunnelChartRenderer();
JOption jOption = new JOption();
jOption.setFunnelOption(customOption);
renderer.render(jOption, "d://test/funnel.svg");
```

#### 11. Gauge — `images/gauge.svg`

![gauge](images/gauge.svg)

**Test Class:** `com.github.paohaijiao.JGaugeTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
// 创建配置
GuageConfig scoreConfig = GuageConfig.builder()
        .score(75)  // 设置分数为75
        .pointerColor(new Color(220, 80, 80))  // 红色指针
        .backgroundColor(new Color(240, 240, 245))  // 浅灰色背景
        .title("PERFORMANCE")
        .build();
JGuageOption option = JGuageOption.builder().scoreMeter(scoreConfig).build();
JGuageRenderer renderer = new JGuageRenderer();
JOption option1 = new JOption();
option1.setGuageOption(option);
renderer.render(option1, "d://test//gauge.svg");
```

#### 12. Gantt — `images/gantt.svg`

![gantt](images/gantt.svg)

**Test Class:** `com.github.paohaijiao.JGanttTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JGanttOption option = new JGanttOption();
option.setTitle(new JGanttOption.Title("Gantt of Airport Flight", "航班调度甘特图"));
option.setFlightData(Arrays.asList(
        new JGanttOption.FlightData("Y3683", "681", "X", 21, 0, 360, 0, 0.7),
        new JGanttOption.FlightData("EKXAD", "682I", "W", 21, 0, 360, 1, 0.7),
        new JGanttOption.FlightData("Y4682", "682O", "W", 21, 0, 360, 2, 0.7),
        new JGanttOption.FlightData("Y4393", "682", "X", 21, 0, 360, 3, 0.7),
        new JGanttOption.FlightData("Y2238", "683", "X", 21, 0, 360, 4, 0.7),
        // ... remaining flights
        new JGanttOption.FlightData("Y7421", "691", "X", 21, 0, 120, 8, 0.7),
        new JGanttOption.FlightData("Y4619", "692", "X", 21, 0, 300, 9, 0.7)
));
option.setChartStyle(new JGanttOption.ChartStyle(
        Color.WHITE,
        new Color(146, 154, 186),
        new Color(54, 140, 108),
        new Color(80, 112, 221),
        new Color(221, 179, 11),
        new Font("微软雅黑", Font.BOLD, 18),
        new Font("微软雅黑", Font.PLAIN, 12),
        872,
        282
));
option.setTimeRange(new JGanttOption.TimeRange(21, 3, new String[]{"21:00", "22:00", "23:00", "00:00", "01:00", "02:00", "03:00"}));
JChartRenderer renderer = new JGanttChartRenderer();
JOption jOption = new JOption();
jOption.setGanttOption(option);
renderer.render(jOption, "d://test//gantt.svg");
```

#### 13. Calendar — `images/calendar.svg`

![calendar](images/calendar.svg)

**Test Class:** `com.github.paohaijiao.CalendarTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
Map<LocalDate, Integer> data = new HashMap<>();
LocalDate startDate = LocalDate.of(2024, 1, 1);
for (int i = 0; i < 365; i++) {
    LocalDate date = startDate.plusDays(i);
    int value = (int) (Math.random() * 15);
    data.put(date, value);
}
JOption option = new JOption();
JCalendarOption calendarOption = new JCalendarOption("2024年活动日历", "类似GitHub贡献图", 2024, data,
        new Color(235, 237, 240),
        new Color(32, 125, 222),
        new Color(232, 235, 240),
        new Color(84, 85, 90),
        20,
        80
);
option.setJCalendarOption(calendarOption);
JChartRenderer renderer = new JCalendarChartRenderer();
renderer.render(option, "d://test//calendar.svg");
String svgContent = renderer.renderToString(option);
System.out.println("SVG内容长度: " + svgContent.length());
```

#### 14. Lunar calendar — `images/lunar.svg`

![lunar](images/lunar.svg)

**Test Class:** `com.github.paohaijiao.LunarTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
private static java.util.List<LunarCalendarOption.DayData> createDefaultDayData() {
    java.util.List<LunarCalendarOption.DayData> defaultData = new ArrayList<>();
    defaultData.add(new LunarCalendarOption.DayData(1, "初四", 0, 0));
    defaultData.add(new LunarCalendarOption.DayData(2, "初五", 0, 1));
    defaultData.add(new LunarCalendarOption.DayData(3, "初六", 0, 2));
    // ... remaining day cells of the month
    defaultData.add(new LunarCalendarOption.DayData(31, "初四", 4, 2));
    return defaultData;
}

private static java.util.List<LunarCalendarOption.SpecialDay> createDefaultSpecialDays() {
    java.util.List<LunarCalendarOption.SpecialDay> specialDays = new ArrayList<>();
    specialDays.add(new LunarCalendarOption.SpecialDay("春分", 0, 6));
    specialDays.add(new LunarCalendarOption.SpecialDay("清明", 3, 0));
    return specialDays;
}

// test method body
LunarCalendarOption.CalendarDataConfig dataConfig = new LunarCalendarOption.CalendarDataConfig()
        .setDayDataList(createDefaultDayData())
        .setSpecialDays(createDefaultSpecialDays())
        .setWeekDays(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
        .setRows(5)
        .setCols(7);
LunarCalendarOption.ColorConfig colorConfig = new LunarCalendarOption.ColorConfig()
        .setBackgroundColor(null)
        .setSpecialDayColor(new Color(0, 100, 0));
JTitle title = new JTitle();
title.setText("2024年3月日历");
LunarCalendarOption option = LunarCalendarOption.of("2024", "三月", colorConfig, title, dataConfig);
JLunarCalendarRenderer renderer = new JLunarCalendarRenderer();
renderer.render(option, "d://test//lunar.svg");
```

#### 15. Word cloud — `images/wordcloud.svg`

![wordcloud](images/wordcloud.svg)

**Test Class:** `com.github.paohaijiao.WordsCloudTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption()
.title(new JTitle().text("热门编程语言"))
.series(Arrays.asList(
   new JWordCloudSeries("语言热度")
       .data(Arrays.asList(
           new JData("Java", 100),
           new JData("Python", 85),
           new JData("JavaScript", 75),
           new JData("C++", 60),
           new JData("Go", 50),
           new JData("Rust", 45),
           new JData("Kotlin", 40),
           new JData("Swift", 35),
           new JData("TypeScript", 30),
           new JData("Scala", 25)
   ))
.minFontSize(20)
.maxFontSize(60)
.gridSize(10)
.rotationStep(15)
.rotationRange(90)
.textStyle(new JItemStyle().color(Color.BLUE))
));
JWordCloudRenderer renderer = new JWordCloudRenderer();
renderer.render(option, "d://test//wordcloud.svg");
```

#### 16. Geo map — `images/geo.svg`

![geo](images/geo.svg)

**Test Class:** `com.github.paohaijiao.GeoTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
private static String readFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
    }
    return content.toString();
}

// test method body
String geoJsonContent = readFile("d://sample//test.geojson");
GeoOption option = new GeoOption();
option.setGeoJsonContent(geoJsonContent);
JGeoJsonRenderer renderer = new JGeoJsonRenderer();
JOption jOption = new JOption();
jOption.setGeoOption(option);
renderer.render(jOption, "d://test/geo.svg");
```

#### 17. Relation graph — `images/relation_chart.svg`

![relation_chart](images/relation_chart.svg)

**Test Class:** `com.github.paohaijiao.RelationTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JGsonOption option = new JGsonOption();
option.title("Relationship Chart Test");
// 创建图系列
JGraph graph = new JGraph();
graph.name("关系图");
graph.layout(JLayout.force); // 使用力导向布局
graph.force().repulsion(100); // 设置排斥力
graph.draggable(true); // 节点可拖动
// 添加节点 - 修正了ID问题
List<JNode> nodes = new ArrayList<>();
nodes.add(new JNode("1", "Node A").symbolSize(30).category(0));//id 1
nodes.add(new JNode("2", "Node B").symbolSize(25).category(1));
nodes.add(new JNode("3", "Node C").symbolSize(20).category(2));
nodes.add(new JNode("4", "Node D").symbolSize(15).category(0));
nodes.add(new JNode("5", "Node E").symbolSize(35).category(1));
nodes.add(new JNode("6", "Node F").symbolSize(20).category(3));
nodes.add(new JNode("7", "Node G").symbolSize(25).category(2));
nodes.add(new JNode("8", "Node H").symbolSize(15).category(4));
nodes.add(new JNode("9", "Node I").symbolSize(30).category(3));
nodes.add(new JNode("10", "Node J").symbolSize(20).category(0));
graph.setData(nodes);
// ... links (14 JLink entries) and categories (5 JCategory entries)
option.series(graph);
option.legend().data("Category 1", "Category 2", "Category 3", "Category 4", "Category 5");
JRelationChartRenderer renderer = new JRelationChartRenderer();
renderer.render(option, "d://test//relation_chart.svg");
```

#### 18. Sunburst — `images/sunburst.svg`

![sunburst](images/sunburst.svg)

**Test Class:** `com.github.paohaijiao.SunBirdTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
JOption option = new JOption();
// 设置标题
JTitle title = new JTitle();
title.setText("咖啡风味分析");
option.setTitle(title);
JSunburstData root = new JSunburstData("总数据", 1.0);
JSunburstData main1 = new JSunburstData("电子产品", 0.4);
JSunburstData main2 = new JSunburstData("服装", 0.3);
JSunburstData main3 = new JSunburstData("食品", 0.3);
// 第二层：子分类
JSunburstData main1Sub1 = new JSunburstData("手机", 0.6);
JSunburstData main1Sub2 = new JSunburstData("电脑", 0.4);
JSunburstData main2Sub1 = new JSunburstData("男装", 0.5);
JSunburstData main2Sub2 = new JSunburstData("女装", 0.5);
JSunburstData main3Sub1 = new JSunburstData("生鲜", 0.4);
JSunburstData main3Sub2 = new JSunburstData("零食", 0.6);
// 第三层：孙分类
main1Sub1.addChild(new JSunburstData("智能手机", 0.7));
main1Sub1.addChild(new JSunburstData("功能手机", 0.3));
// ... remaining addChild calls
root.addChild(main1);
root.addChild(main2);
root.addChild(main3);
option.setSunburstData(root);
JSunburstChart chart = new JSunburstChart();
chart.render(option, "d://test//sunburst.svg");
```

#### 19. Treemap — `images/treemap.svg`

![treemap](images/treemap.svg)

**Test Class:** `com.github.paohaijiao.JTreemapRendererExample` (module `jquick-pdf-svg`) | **Method:** `main(String[])` — no `@Test`, `TreemapTest` is fully commented out

```java
JTreeMapNode root = createTestData();
TreeMapOption treemapOption = new TreeMapOption();
treemapOption.setRoot(root);
treemapOption.setDepartmentColors(DEPARTMENT_COLORS);
treemapOption.setCategoryColors(CATEGORY_COLORS);
treemapOption.getDepartmentRules().add(new TreeMapMapping("开发", "技术部"));
// ... remaining TreeMapMapping rules
JOption option = new JOption();
option.setTreemapOption(treemapOption);
option.title("公司业务分布矩形树图（JTreemapRenderer）");
JTreeMapRenderer renderer = new JTreeMapRenderer();
String outputPath = "d://test//treemap.svg";
renderer.render(option, outputPath);
System.out.println("JTreemapRenderer 树形图生成成功！");
```


#### 20. Correlation matrix — `images/Matrix.svg`

![Matrix](images/Matrix.svg)

**Test Class:** `com.github.paohaijiao.JCollectionMatrixTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

```java
double[][] correlationData = {
        {1.00, -0.20, 0.03, -0.62, -0.54, -0.21, 0.63, 0.30},
        {-0.20, 1.00, 0.36, -0.61, -0.26, 0.05, 0.16, 0.41},
        {0.03, 0.36, 1.00, -0.74, -0.94, 0.71, -0.90, -0.66},
        {-0.62, -0.61, -0.74, 1.00, 0.37, -0.66, 0.54, -0.66},
        {-0.54, -0.26, -0.94, 0.37, 1.00, -0.05, -0.46, 0.71},
        {-0.21, 0.05, 0.71, -0.66, -0.05, 1.00, -0.84, -0.40},
        {0.63, 0.16, -0.90, 0.54, -0.46, -0.84, 1.00, -0.55},
        {0.30, 0.41, -0.66, -0.66, 0.71, -0.40, -0.55, 1.00}
};
String[] dimensions = {"销售额", "广告费", "促销费", "竞品价", "季节指数", "GDP", "人口", "天气"};
JCorrelationMatrixOption option = JCorrelationMatrixOption.builder()
        .title("销售因素相关系数矩阵", "各因素之间的相关性分析")
        .dataset(correlationData)
        .build();
option.dataset().dimensions(dimensions);
JOption jOption = new JOption();
jOption.setCorrelationMatrixOption(option);
JCorrelationMatrixRenderer renderer = new JCorrelationMatrixRenderer();
renderer.render(jOption, "d://test//Matrix.svg");
```

#### 21. Stacked area — `images/area.svg`

![area](images/area.svg)

**Test Class:** `com.github.paohaijiao.JAreaChartTest` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

```java
List<Double> values = Arrays.asList(85.0, 120.0, 150.0, 210.0, 280.0, 350.0, 420.0, 400.0, 380.0, 450.0, 480.0, 520.0);
List<String> labels = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
JAreaChartData config = new JAreaChartData();
config.setWidth(800);
config.setHeight(500);
config.setTitle("2024年度销售趋势");
config.setSubtitle("数据来源：销售系统");
config.setXAxisTitle("月份");
config.setYAxisTitle("销售额（万元）");
config.setLegendText("销售额");
config.setShowDataLabels(true);
config.setSeriesList(Arrays.asList(new JSeriesData("销售额", values)));
config.setXAxisLabels(labels);
config.setTheme(JTheme.DEFAULT);      // 默认主题
JOption option = new JOption();
option.setData(config);
JAreaChartRenderer renderer = new JAreaChartRenderer();
renderer.render(option, "d://test//area.svg");
```

#### 22. Line + bar combo — `images/linebar.svg`

![linebar](images/linebar.svg)

**Test Class:** `com.github.paohaijiao.CombolTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

Note the file was renamed on import: the test writes `d://test/custom_chart.svg`, the preview is stored as `images/linebar.svg`.

```java
JOption jOption = new JOption();
List<Double> sales = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0);
List<Double> profits = Arrays.asList(15.0, 16.5, 18.0, 19.2, 21.0, 22.5);
List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月");
JComboLineBarChartData config = JComboLineBarChartData.builder()
        .width(1000)
        .height(600)
        .title("2024年上半年销售分析", "半年度数据报告")
        .barData(sales)
        .lineData(profits)
        .xAxisLabels(months)
        .barColor(new Color(46, 204, 113))      // 绿色条形
        .lineColor(new Color(155, 89, 182))     // 紫色折线
        .leftAxisTitle("销售额（万元）")
        .rightAxisTitle("利润率（%）")
        .barLegendText("月销售额")
        .lineLegendText("利润率")
        .footerText("数据来源：财务系统")
        .build();
JComboLineBarChartRenderer customRenderer = new JComboLineBarChartRenderer();
jOption.setData(config);
customRenderer.render(jOption, "d://test/custom_chart.svg");
```

#### 23. Multi line — `images/multipleLine.svg`

![multipleLine](images/multipleLine.svg)

**Test Class:** `com.github.paohaijiao.MutipleLineChartTest` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

```java
List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
List<Double> productA = Arrays.asList(120.0, 135.0, 148.0, 162.0, 175.0, 190.0, 205.0, 218.0, 230.0, 245.0, 258.0, 270.0);
List<Double> productB = Arrays.asList(95.0, 108.0, 112.0, 130.0, 125.0, 145.0, 150.0, 168.0, 172.0, 185.0, 190.0, 200.0);
List<Double> productC = Arrays.asList(80.0, 82.0, 85.0, 88.0, 90.0, 92.0, 95.0, 98.0, 100.0, 102.0, 105.0, 108.0);
List<Double> productD = Arrays.asList(45.0, 58.0, 72.0, 89.0, 105.0, 128.0, 150.0, 175.0, 198.0, 225.0, 248.0, 275.0);
JMultiLineChartData chartData = new JMultiLineChartData();
chartData.setXAxisLabels(months);
chartData.setWidth(900);
chartData.setHeight(600);
chartData.setTitleText("2024年度产品销售趋势分析");
chartData.setSubtitleText("各产品线月度销售额对比（单位：万元）");
chartData.setYAxisTitle("销售额（万元）");
chartData.setFooterText("数据来源：销售系统报表 | 统计时间：2024年1月-12月");
chartData.setGridCount(6);
chartData.setShowDataLabels(false);
chartData.setShowInnerPoint(true);
chartData.setPointRadius(5);
chartData.setInnerPointRadius(2);
chartData.setChartAreaColor(new Color(248, 249, 250));
chartData.setAxisColor(Color.BLACK);
chartData.setGridColor(new Color(220, 220, 220));
chartData.setTextColor(Color.BLACK);
chartData.setFooterColor(new Color(128, 128, 128));
chartData.setValueWithPercent(false);
chartData.setAutoCalculateMax(true);
chartData.setRotateXAxisLabels(false);
JMultiLineChartData.LineData lineA = new JMultiLineChartData.LineData();
lineA.setName("产品A");
lineA.setLegendText("产品A - 高端系列");
lineA.setValues(productA);
lineA.setLineColor(new Color(66, 133, 244));
lineA.setLineWidth(2.5f);
// ... lineB / lineC / lineD, same shape (产品B 中端系列, 产品C 入门系列, 产品D 创新系列)
chartData.setLineDataList(Arrays.asList(lineA, lineB, lineC, lineD));
chartData.updateMaxValues();
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("2024年度产品销售趋势分析");
title.setSubtext("各产品线月度销售额对比");
option.setTitle(title);
option.setData(chartData);
JMultiLineChartRenderer renderer = new JMultiLineChartRenderer();
renderer.render(option, "d://test//multiple-line.svg");
```

#### 24. Multi bar — `images/fourBar.svg`

![fourBar](images/fourBar.svg)

**Test Class:** `com.github.paohaijiao.MutipleBarChartTest` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar5()`

```java
JMultiBarChartData regionalData = new JMultiBarChartData();
regionalData.setTitleText("2024年上半年各区域业绩对比（万元）");
regionalData.setSubtitleText("华东、华南、华北、西部四区表现");
regionalData.setXAxisLabels(Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月"));
regionalData.setXAxisTitle("月份");
regionalData.setYAxisTitle("业绩（万元）");
// 华东区域
JMultiBarChartData.BarData eastChina = new JMultiBarChartData.BarData();
eastChina.setLegendText("华东");
eastChina.setBarColor(JMultiBarChartRenderer.COLOR_A);
eastChina.setValues(Arrays.asList(120.5, 135.2, 148.0, 162.5, 175.3, 190.8));
// ... southChina / northChina / westChina, same shape (华南/华北/西部, COLOR_B / COLOR_C / new Color(80, 180, 120))
regionalData.setBarDataList(Arrays.asList(eastChina, southChina, northChina, westChina));
JOption option = new JOption();
option.setData(regionalData);
JMultiBarChartRenderer renderer = new JMultiBarChartRenderer();
renderer.render(option, "d://test//fourBar.svg");
```

#### 25. Horizontal bar — `images/horizontalBar.svg`

![horizontalBar](images/horizontalBar.svg)

**Test Class:** `com.github.paohaijiao.JHorizontalBarChart` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

```java
JHorizontalBarChartData chartData = new JHorizontalBarChartData();
chartData.setTitleText("2024年度销售数据");
chartData.setSubtitleText("各产品线销售占比");
chartData.setXAxisTitle("销售额（万元）");
chartData.setYAxisTitle("产品类别");
chartData.setValueWithPercent(false);
chartData.setShowDataLabels(true);
chartData.addYAxisLabel("电子产品");
chartData.addYAxisLabel("服装服饰");
chartData.addYAxisLabel("家居用品");
chartData.addYAxisLabel("美妆个护");
chartData.addYAxisLabel("食品饮料");
java.util.List<Double> productAValues = Arrays.asList(85.5, 62.3, 45.8, 71.2, 93.6);
java.util.List<Double> productBValues = Arrays.asList(45.2, 78.9, 52.1, 38.5, 67.4);
chartData.addBarData(new JHorizontalBarChartData.BarData("产品A", productAValues, JHorizontalBarChartData.COLOR_A));
chartData.addBarData(new JHorizontalBarChartData.BarData("产品B", productBValues, JHorizontalBarChartData.COLOR_B));
JOption option = new JOption();
option.setData(chartData);
JHorizontalBarChartRenderer renderer = new JHorizontalBarChartRenderer();
renderer.render(option, "d://test//horizontalBarChart.svg");
```

#### 26. Multi horizontal bar — `images/mhBarChart.svg`

![mhBarChart](images/mhBarChart.svg)

**Test Class:** `com.github.paohaijiao.JMutipleHorizontalBarChart` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

```java
JHorizontalMultiBarChartData chartData = new JHorizontalMultiBarChartData();
chartData.setTitleText("2024年度各产品销售数据");
chartData.setSubtitleText("单位：万元");
chartData.setXAxisTitle("销售额（万元）");
chartData.setValueWithPercent(false);
chartData.setShowDataLabels(true);
chartData.setLegendAtTop(true);
chartData.setGroupSpacingRatio(0.15);
chartData.setBarSpacingRatio(0.2);
chartData.addCategory("智能手机");
chartData.addCategory("笔记本电脑");
chartData.addCategory("平板电脑");
chartData.addCategory("智能手表");
chartData.addCategory("耳机音箱");
List<Double> productAValues = Arrays.asList(125.5, 98.3, 65.8, 45.2, 78.6);
List<Double> productBValues = Arrays.asList(88.2, 112.5, 72.1, 38.5, 55.3);
List<Double> productCValues = Arrays.asList(45.6, 68.9, 52.4, 28.7, 42.1);
chartData.addSeries("品牌 A", productAValues, new Color(52, 73, 94));    // 深灰蓝 #34495e
chartData.addSeries("品牌 B", productBValues, new Color(41, 128, 185));   // 中蓝 #2980b9
chartData.addSeries("品牌 C", productCValues, new Color(26, 188, 156));   // 薄荷绿 #1abc9c
JOption option = new JOption();
option.setData(chartData);
JHorizontalMultiBarChartRenderer renderer = new JHorizontalMultiBarChartRenderer();
renderer.render(option, "d://test//mhBarChart.svg");
```

#### 27. Double radar — `images/twoRadar.svg`

![twoRadar](images/twoRadar.svg)

**Test Class:** `com.github.paohaijiao.JTwoRadarChart` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

The full, unabridged source is in [Demo 3](#demo-3--svg-chart-embedded-into-a-pdf-page).

```java
JDoubleRadarChartData chartData = new JDoubleRadarChartData();
chartData.setWidth(1000);
chartData.setHeight(600);
chartData.setTitleText("多维度数据对比雷达图");
chartData.setSubtitleText("左右两组数据对比分析");
chartData.setLeftTitle("实验组数据");
chartData.setRightTitle("对照组数据");
List<String> dimensions = Arrays.asList("维度A", "维度B", "维度C", "维度D", "维度E");
chartData.setDimensions(dimensions);
JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
List<JDoubleRadarChartData.Series> leftSeriesList = new ArrayList<>();
JDoubleRadarChartData.Series series1 = new JDoubleRadarChartData.Series();
series1.setName("节点1");
List<Double> values1 = Arrays.asList(85.0, 70.0, 65.0, 80.0, 75.0);
series1.setValues(values1);
series1.setColor(new Color(84, 112, 198));  // 蓝色
leftSeriesList.add(series1);
// ... 节点2 (黄色, left) and 节点3 / 节点4 (红色 / 绿色, right), same shape
leftRadar.setSeriesList(leftSeriesList);
chartData.setLeftRadar(leftRadar);
chartData.setRightRadar(rightRadar);
chartData.setGridLevels(4);
chartData.setFillAlpha(70);
chartData.setLineWidth(2.0f);
chartData.setShowDataPoints(true);
chartData.setLegendAtTop(false);
chartData.setShowLegendSide(true);
chartData.setFooterText("数据来源：示例数据");
JOption option = new JOption();
option.setData(chartData);
JDoubleRadarChartRenderer renderer = new JDoubleRadarChartRenderer();
renderer.render(option, "d://test//mutipleRadar.svg");
```

#### 28. Line + scatter — `images/lineRadar.svg`

![lineRadar](images/lineRadar.svg)

**Test Class:** `com.github.paohaijiao.JLineScatterrChartTest` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

```java
List<String> categories = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月",
        "7月", "8月", "9月", "10月", "11月", "12月");
List<Double> lineValues = Arrays.asList(100.0, 120.0, 140.0, 160.0, 70.0, 200.0,
        290.0, 240.0, 130.0, 330.0, 100.0, 320.0);
List<Double> scatterValues = Arrays.asList(85.0, 145.0, 20.0, 195.0, 155.0, 400.0,
        180.0, 210.0, 40.0, 245.0, 275.0, 450.0);
JLineScatterChartData data = new JLineScatterChartData();
data.setTitleText("计划销售额 vs 实际完成额");
data.setSubtitleText("2024年度销售趋势分析");
data.setFooterText("数据来源：销售部月度报表");
data.setCategories(categories);
data.setLineValues(lineValues);
data.setScatterValues(scatterValues);
data.setLineSeriesName("计划销售额");
data.setScatterSeriesName("实际完成额");
data.setMaxValue(500);
data.setGridCount(5);
data.setShowDataLabels(true);
JOption option = new JOption();
option.setData(data);
JLineScatterChartRenderer renderer = new JLineScatterChartRenderer();
renderer.render(option, "d://test//lineRadar.svg");
```

#### 29. Circle / ring — `images/circle-chart.svg`

![circle-chart](images/circle-chart.svg)

**Test Class:** `com.github.paohaijiao.JCircleChartTest` (module `jquick-pdf-svg`) | **Method:** `testCircleChar1()`

```java
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
```

#### 30. Timeline — `images/timeline.svg`

![timeline](images/timeline.svg)

**Test Class:** `com.github.paohaijiao.JMilestoneGraphTest` (module `jquick-pdf-svg`) | **Method:** `testStandardFlow()`

```java
JTimeLineData data = new JTimeLineData();
List<JTimeLineData.FlowNode> nodes = new java.util.ArrayList<>();
nodes.add(new JTimeLineData.FlowNode("项目启动", "•团队组建与立项|•市场调研完成|•战略规划制定", new Color(31, 78, 121), new Color(31, 78, 121)));
nodes.add(new JTimeLineData.FlowNode("1000", "500", new Color(68, 114, 196), new Color(68, 114, 196)));
nodes.add(new JTimeLineData.FlowNode("500", "里程碑达成", new Color(112, 173, 71), new Color(112, 173, 71)));
nodes.add(new JTimeLineData.FlowNode("150%", "100%增长", new Color(237, 125, 49), new Color(237, 125, 49)));
nodes.add(new JTimeLineData.FlowNode("100%", "50%完成率", new Color(79, 129, 189), new Color(79, 129, 189)));
data.setNodes(nodes);
data.setMainTitle("MILESTONE TIMELINE");
data.setSubtitle("2021-2023 关键里程碑节点");
data.setFooterText("数据来源：年度报告 | 更新日期：2024年1月");
data.setHeight(1300);
data.setBoxWidth(200);
data.setBoxHeight(90);
data.setStartX(100);
data.setEndX(100);
JOption option = new JOption();
option.setData(data);
JTimeLineRenderer renderer = new JTimeLineRenderer();
renderer.render(option, "d://test//alternate_flow_1.svg");
```

#### 31. Advanced topology (microservice) — `images/advance_topology.svg`

![advance_topology](images/advance_topology.svg)

**Test Class:** `com.github.paohaijiao.JNetworkTopologyGraphTest` (module `jquick-pdf-svg`) | **Method:** `generateMicroserviceTopology()`

```java
JAdvancedTopologyData data = new JAdvancedTopologyData();
data.setTitleText("微服务架构拓扑图");
data.setSubtitleText("服务调用链路图");
data.setFooterText("服务网格 | Istio");
data.setWidth(1200);
data.setHeight(800);
data.setAutoLayout(true);
data.setLayoutIterations(120);
data.setCurvedLinks(true);
data.setShowDataFlow(true);
data.setFlowAnimationDuration(2000);
// ... nodes and links (createNode(...) / addLink(...))
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("微服务架构拓扑");
title.setSubtext("服务调用链路");
option.setTitle(title);
option.setData(data);
JAdvancedTopologyRenderer renderer = new JAdvancedTopologyRenderer();
renderer.render(option, "d://test//microservice_topology.svg");
```

#### 32. Cloud architecture topology — `images/toplogy/cloud_architecture.svg`

![cloud_architecture](images/toplogy/cloud_architecture.svg)

**Test Class:** `com.github.paohaijiao.JNetworkTopologyGraphTest` (module `jquick-pdf-svg`) | **Method:** `generateCloudArchitectureTopology()`

```java
JAdvancedTopologyData data = new JAdvancedTopologyData();
data.setTitleText("云服务架构拓扑图");
data.setSubtitleText("多区域高可用架构");
data.setFooterText("AWS 云架构 | 生产环境");
data.setWidth(1100);
data.setHeight(750);
data.setAutoLayout(true);
data.setLayoutIterations(60);
data.setBackgroundColor(new Color(245, 245, 250));
// ... nodes and links (createNode(...) / addLink(...))
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("云服务架构拓扑");
title.setSubtext("生产环境");
option.setTitle(title);
option.setData(data);
JAdvancedTopologyRenderer renderer = new JAdvancedTopologyRenderer();
renderer.render(option, "d://test//cloud_architecture.svg");
```

#### 33. Data center topology — `images/toplogy/datacenter_topology.svg`

![datacenter_topology](images/toplogy/datacenter_topology.svg)

**Test Class:** `com.github.paohaijiao.JNetworkTopologyGraphTest` (module `jquick-pdf-svg`) | **Method:** `generateDataCenterTopology()`

```java
JAdvancedTopologyData data = new JAdvancedTopologyData();
data.setTitleText("数据中心网络拓扑图");
data.setSubtitleText("典型的三层网络架构");
data.setFooterText("© 2025 数据中心运维团队");
data.setWidth(1000);
data.setHeight(700);
data.setAutoLayout(true);
data.setLayoutIterations(80);
// ... nodes and links (createNode(...) / addLink(...))
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("数据中心网络拓扑");
title.setSubtext("三层网络架构");
option.setTitle(title);
option.setData(data);
JAdvancedTopologyRenderer renderer = new JAdvancedTopologyRenderer();
renderer.render(option, "d://test//datacenter_topology.svg");
```

#### 34. Enterprise network topology — `images/toplogy/enterprise_network.svg`

![enterprise_network](images/toplogy/enterprise_network.svg)

**Test Class:** `com.github.paohaijiao.JNetworkTopologyGraphTest` (module `jquick-pdf-svg`) | **Method:** `generateEnterpriseNetworkTopology()`

```java
JAdvancedTopologyData data = new JAdvancedTopologyData();
data.setTitleText("企业网络拓扑图");
data.setSubtitleText("总部-分支机构网络架构");
data.setFooterText("VPN连接 | MPLS专线");
data.setWidth(1000);
data.setHeight(650);
data.setAutoLayout(true);
data.setLayoutIterations(100);
// ... nodes and links (createNode(...) / addLink(...))
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("企业网络拓扑");
title.setSubtext("总部-分支机构");
option.setTitle(title);
option.setData(data);
JAdvancedTopologyRenderer renderer = new JAdvancedTopologyRenderer();
renderer.render(option, "d://test//enterprise_network.svg");
```

#### 35. Manual-layout topology — `images/toplogy/manual_layout_topology.svg`

![manual_layout_topology](images/toplogy/manual_layout_topology.svg)

**Test Class:** `com.github.paohaijiao.JNetworkTopologyGraphTest` (module `jquick-pdf-svg`) | **Method:** `generateManualLayoutTopology()`

```java
JAdvancedTopologyData data = new JAdvancedTopologyData();
data.setTitleText("自定义布局网络拓扑");
data.setSubtitleText("手动控制节点位置");
data.setFooterText("网络监控系统");
data.setWidth(900);
data.setHeight(600);
data.setAutoLayout(false);  // 关闭自动布局
data.setShowGrid(true);
data.setGridSize(30);
data.setCurvedLinks(false);
data.setShowArrows(true);
// ... nodes with explicit coordinates and links (createNode(...) / addLink(...))
JOption option = new JOption();
JTitle title = new JTitle();
title.setText("自定义布局拓扑");
title.setSubtext("手动控制节点位置");
option.setTitle(title);
option.setData(data);
JAdvancedTopologyRenderer renderer = new JAdvancedTopologyRenderer();
renderer.render(option, "d://test//manual_layout_topology.svg");
```

#### 36. Credit report page — `images/credit_report.png`

![credit_report](images/credit_report.png)

**Test Class:** `com.github.paohaijiao.demo.creditreport.JQuickCreditReportTest` (module `jquick-pdfx`) | **Method:** `reportByContent()`

The full template and element list are in [Demo 1](#demo-1--enterprise-credit-rating-report-composite).

```java
FileOutputStream fileOutputStream = new FileOutputStream(path + "test.pdf");
JPdfConfig config = new JPdfConfig();
// 开启 flex 行布局：宽度足够时，雷达图与其右侧的指标面板并排显示，
// 而不是上下堆叠。
config.getLayoutConfig().setFlexLayout(true);
JReader fileReader = new JReSourceFileReader("report.txt");   // 文档模板
JAdaptor adaptor = new JAdaptor(fileReader);
JReader svgReader = new JReSourceFileReader("radar.txt");     // 图表的内联 SVG
JAdaptor svgAdaptor = new JAdaptor(svgReader);
JQuickPdfFactory factory = new JQuickPdfFactory(config);
factory.bind("svg", svgAdaptor.getRuleContent());             // ${svg} 占位符
byte[] bytes = factory.executeContent(adaptor.getRuleContent());
fileOutputStream.write(bytes);
```

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-banner</artifactId>
    <version>1.3.0</version>
</dependency>
```



### Demo 1 — Enterprise credit rating report (composite)

![demo](images/jquick-logo.svg)

**Test Class:** `com.github.paohaijiao.demo.creditreport.JQuickCreditReportTest` | **Method:** `reportByContent()`

A full business document: banner block, key-value table, financial table, bulleted indicator list, an embedded SVG radar chart, flex two-column layout, and a footer — all from one template.

```java
package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class JQuickCreditReportTest {

    public static final String path = JQuickConstant.path;   // "D:\\test\\"

    @Test
    public void reportByContent() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path + "test.pdf");
        JPdfConfig config = new JPdfConfig();
        // Enable flex row layout: the radar chart and the metric panel beside it
        // sit side by side instead of stacking when the width allows.
        config.getLayoutConfig().setFlexLayout(true);
        JReader fileReader = new JReSourceFileReader("report.txt");   // document template
        JAdaptor adaptor = new JAdaptor(fileReader);
        JReader svgReader = new JReSourceFileReader("radar.txt");     // inline SVG for the chart
        JAdaptor svgAdaptor = new JAdaptor(svgReader);
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svgAdaptor.getRuleContent());             // ${svg} placeholder
        byte[] bytes = factory.executeContent(adaptor.getRuleContent());
        fileOutputStream.write(bytes);
    }
}
```

The two templates are shipped with the test sources:

- `jquick-pdfx/src/test/resources/report.txt` — the document template (excerpt below)
- `jquick-pdfx/src/test/resources/radar.txt` — the inline SVG bound to `${svg}`

```html
<pdf>
  <body>
    <div style="textAlignment:center; marginBottom:5px; paddings:5px 5px 60px 70px; background:#3E6B9D; color:white; borderRadius:4px; position:relative">
      <h1 style="textAlignment:center;color:white; marginBottom:8px; fontSize:20; fontWeight:bold">'企业信用评级报告'</h1>
      <p style="textAlignment:center;color:rgba(255,255,255,0.9); fontSize:11; margin:2px">'报告编号: CR-2023-08975 | 评估日期: 2023年11月15日'</p>
    </div>

    <table style="width:600px;verticalAlignment:center; fontSize:10">
      <tr>
        <td style="backgroundColor:#f8f9fa;padding:5px; textAlign:left;width:150px;">'统一信用代码'</td>
        <td style="backgroundColor:#f8f9fa;padding:5px;width:150px;">'91110108MA01XX1234'</td>
      </tr>
    </table>

    <list style="list-style-type:none; padding-left:0; font-size:11px">
      <li style="margin-bottom:8px; padding:10px; background-color:#f8f9fa; border-left:4px solid #4CAF50; border-radius:0 4px 4px 0">
        '营收增长率:连续三年保持40%以上增长，2023年达到25,680万元'
      </li>
    </list>

    <div style="display:flex; justify-content:space-between; margin-top:10px">
      <svg style="width:400px;height:400px">${svg}</svg>
      <div style="width:300px">
        <div style="backgroundColor:#f8f9fa; padding:15px; borderRadius:4px; height:230px; overflow-y:auto">
          <h3 style="color:#2c3e50; margin-top:0; fontSize:12; marginBottom:8px">'评级指标说明'</h3>
        </div>
      </div>
    </div>
  </body>
</pdf>
```

**Elements used and their supported properties**

| Element | Purpose in this demo | Supported properties |
|---|---|---|
| `<pdf>` / `<body>` | document root and body container | root attributes of the template; `pageSize`, `margins` are configured through `JPdfConfig` |
| `<div>` | banner, panels, flex row | `marginLeft`, `marginRight`, `marginTop`, `marginBottom`, `commonMargin`/`margins`, `paddingLeft`, `paddingRight`, `paddingTop`, `paddingBottom`, `commonPadding`/`paddings`, `width`, `height`, `minWidth`, `maxWidth`, `minHeight`, `maxHeight`, `backgroundColor`, `backgroundImage`, `border`, `borderTop`, `borderRight`, `borderBottom`, `borderLeft`, `borderRadius`, `borderTopLeftRadius`, `borderTopRightRadius`, `borderBottomRightRadius`, `borderBottomLeftRadius`, `opacity`, `strokeColor`, `strokeWidth`, `angleInRadians`, `textAlignment`, `verticalAlignment`, `relativePosition`, `spacingRatio`, `keepTogether`, `keepWithNext`, `display`, `flexDirection`, `justifyContent` |
| `<h1>` `<h2>` `<h3>` | report title and section headings | `font`, `fontFamilyNames`, `fontSize`, `fontColor`, `bold`, `italic`, `underline`, `lineThrough`, `textAlignment`, `characterSpacing`, `wordSpacing`, `margin*`, `padding*`, `backgroundColor`, `border*`, plus every `<p>` property |
| `<p>` | paragraphs, sub-titles | `font`, `fontFamilyNames`, `fontSize`, `fontColor`, `fontKerning`, `fontScript`, `bold`, `italic`, `underline`, `lineThrough`, `textAlignment`, `characterSpacing`, `wordSpacing`, `splitCharacters`, `textRenderingMode`, `baseDirection`, `margin*`, `padding*`, `backgroundColor`, `border*` |
| `<span>` | inline runs inside list items | `font`, `fontColor`, `fontSize`, `bold`, `italic`, `underline`, `lineThrough`, `characterSpacing`, `wordSpacing`, `backgroundColor`, `border*`, `padding*`, `margin*` |
| `<table>` `<tr>` `<th>` `<td>` | key-value block and financial table | `width`, `height`, `fontSize`, `fontColor`, `textAlignment`, `verticalAlignment`, `backgroundColor`, `padding*`, `margin*`, `border`, `borderTop`, `borderRight`, `borderBottom`, `borderLeft`, `borderRadius`, `bold`, `italic` |
| `<list>` `<li>` | bulleted indicator list | `list-style-type`, `paddingLeft`, `marginBottom`, `backgroundColor`, `background-color`, `borderLeft`, `borderRadius`, `fontSize`, `fontColor`, `padding*` |
| `<svg>` | embedded radar chart | `width`, `height`, `margin*`, `padding*`; content comes from `${svg}` (bound value) or `&{svg}` (registered resource) |

### Demo 2 — Bar chart (chart module)

![demo](images/barchart.svg)

**Test Class:** `com.github.paohaijiao.BarCharTest` (module `jquick-pdf-svg`) | **Method:** `testBarChar1()`

The same chart API used by the document demos, rendered standalone to an SVG file by the `jquick-pdf-svg` module.

```java
package com.github.paohaijiao;

import com.github.paohaijiao.axis.JCategoryAxis;
import com.github.paohaijiao.axis.JValueAxis;
import com.github.paohaijiao.bar.JBarChartsRenderer;
import com.github.paohaijiao.code.JTrigger;
import com.github.paohaijiao.series.JBar;
import org.junit.Test;

import java.io.IOException;

public class BarCharTest {

    @Test
    public void testBarChar1() throws IOException {
        JOption option = new JOption();
        option.title().text("销售数据").subtext("2023年度");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JBar bar = new JBar();
        bar.name("销量").data(5, 20, 36, 10, 10, 20);
        option.series(bar);
        JBarChartsRenderer jBarChartsRenderer = new JBarChartsRenderer();
        jBarChartsRenderer.render(option, "D://test//barchart.svg");
        String str = jBarChartsRenderer.renderToString(option);
        System.out.println(str);
    }
}
```

**Elements used and their supported properties**

| Element | Purpose in this demo | Supported properties |
|---|---|---|
| `<pdf>` / `<body>` | not used here — the renderer writes the SVG directly; embedding into a document only needs `<svg>&{svg}</svg>` | see Demo 1 |
| `<svg>` | chart placeholder when embedded in a document | `width`, `height`, `margin*`, `padding*` |
| `JOption` (chart option, not a DSL element) | container of the chart configuration | `title()`, `subtext()`, `tooltip()`, `legend()`, `xAxis()`, `yAxis()`, `series()`, `setData()` |
| `JCategoryAxis` / `JValueAxis` | axes | `data(...)`, axis type via the concrete class |
| `JBar` (series) | bar series | `name(...)`, `data(...)`; one subclass per chart type (`JLine`, `JPie`, `JScatter`, ...) |
| `JBarChartsRenderer` | SVG renderer for this chart type | `render(option, path)`, `renderToString(option)` |

### Demo 3 — SVG chart embedded into a PDF page

![demo](images/twoRadar.svg)

**Test Class:** `com.github.paohaijiao.JTwoRadarChart` (module `jquick-pdf-svg`) | **Method:** `testMutipleChar1()`

The `<svg>` element paints a chart generated by `jquick-pdf-svg` as vector graphics inside the document — one chart, one placeholder, no image file round-trip. It takes two steps: build the chart, then register it and reference it from the template.

**Step 1 — build the chart and render it to an SVG file:**

```java
package com.github.paohaijiao;

import com.github.paohaijiao.combol.JDoubleRadarChartData;
import com.github.paohaijiao.combol.JDoubleRadarChartRenderer;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JTwoRadarChart {
    @Test
    public void testMutipleChar1() throws IOException {
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setWidth(1000);
        chartData.setHeight(600);
        chartData.setTitleText("多维度数据对比雷达图");
        chartData.setSubtitleText("左右两组数据对比分析");
        chartData.setLeftTitle("实验组数据");
        chartData.setRightTitle("对照组数据");
        List<String> dimensions = Arrays.asList("维度A", "维度B", "维度C", "维度D", "维度E");
        chartData.setDimensions(dimensions);
        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> leftSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series1 = new JDoubleRadarChartData.Series();
        series1.setName("节点1");
        List<Double> values1 = Arrays.asList(85.0, 70.0, 65.0, 80.0, 75.0);
        series1.setValues(values1);
        series1.setColor(new Color(84, 112, 198));  // 蓝色
        leftSeriesList.add(series1);
        JDoubleRadarChartData.Series series2 = new JDoubleRadarChartData.Series();
        series2.setName("节点2");
        List<Double> values2 = Arrays.asList(70.0, 85.0, 80.0, 65.0, 70.0);
        series2.setValues(values2);
        series2.setColor(new Color(250, 200, 88));  // 黄色
        leftSeriesList.add(series2);

        leftRadar.setSeriesList(leftSeriesList);
        chartData.setLeftRadar(leftRadar);
        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> rightSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series3 = new JDoubleRadarChartData.Series();
        series3.setName("节点3");
        List<Double> values3 = Arrays.asList(90.0, 85.0, 88.0, 92.0, 87.0);
        series3.setValues(values3);
        series3.setColor(new Color(238, 102, 102));  // 红色
        rightSeriesList.add(series3);
        JDoubleRadarChartData.Series series4 = new JDoubleRadarChartData.Series();
        series4.setName("节点4");
        List<Double> values4 = Arrays.asList(75.0, 80.0, 72.0, 78.0, 82.0);
        series4.setValues(values4);
        series4.setColor(new Color(80, 180, 150));  // 绿色
        rightSeriesList.add(series4);

        rightRadar.setSeriesList(rightSeriesList);
        chartData.setRightRadar(rightRadar);
        chartData.setGridLevels(4);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(false);
        chartData.setShowLegendSide(true);
        chartData.setFooterText("数据来源：示例数据");
        JOption option = new JOption();
        option.setData(chartData);

        JDoubleRadarChartRenderer renderer = new JDoubleRadarChartRenderer();
        renderer.render(option, "d://test//mutipleRadar.svg");
    }
}
```

**Step 2 — put the same chart on a PDF page:**

**Test Class:** `com.github.paohaijiao.demo.radar.JQuickTwoRadarTest` (module `jquick-pdfx`) | **Method:** `radar()`

```java
package com.github.paohaijiao.demo.radar;

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.combol.JDoubleRadarChartData;
import com.github.paohaijiao.config.JGraphConfig;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.data.JGraphContainer;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import org.junit.Test;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JQuickTwoRadarTest {

    @Test
    public void radar() throws IOException {
        JGraphContainer graphContainer = new JGraphContainer();
        graphContainer.setType(JChartType.DoubleRadar);        // which chart type to draw
        JDoubleRadarChartData chartData = new JDoubleRadarChartData();
        chartData.setWidth(1000);
        chartData.setHeight(600);
        chartData.setTitleText("多维度数据对比雷达图");
        chartData.setSubtitleText("左右两组数据对比分析");
        chartData.setLeftTitle("实验组数据");
        chartData.setRightTitle("对照组数据");
        List<String> dimensions = Arrays.asList("维度A", "维度B", "维度C", "维度D", "维度E");
        chartData.setDimensions(dimensions);
        JDoubleRadarChartData.RadarData leftRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> leftSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series1 = new JDoubleRadarChartData.Series();
        series1.setName("节点1");
        List<Double> values1 = Arrays.asList(85.0, 70.0, 65.0, 80.0, 75.0);
        series1.setValues(values1);
        series1.setColor(new Color(84, 112, 198));  // 蓝色
        leftSeriesList.add(series1);
        JDoubleRadarChartData.Series series2 = new JDoubleRadarChartData.Series();
        series2.setName("节点2");
        List<Double> values2 = Arrays.asList(70.0, 85.0, 80.0, 65.0, 70.0);
        series2.setValues(values2);
        series2.setColor(new Color(250, 200, 88));  // 黄色
        leftSeriesList.add(series2);

        leftRadar.setSeriesList(leftSeriesList);
        chartData.setLeftRadar(leftRadar);
        JDoubleRadarChartData.RadarData rightRadar = new JDoubleRadarChartData.RadarData();
        List<JDoubleRadarChartData.Series> rightSeriesList = new ArrayList<>();
        JDoubleRadarChartData.Series series3 = new JDoubleRadarChartData.Series();
        series3.setName("节点3");
        List<Double> values3 = Arrays.asList(90.0, 85.0, 88.0, 92.0, 87.0);
        series3.setValues(values3);
        series3.setColor(new Color(238, 102, 102));  // 红色
        rightSeriesList.add(series3);
        JDoubleRadarChartData.Series series4 = new JDoubleRadarChartData.Series();
        series4.setName("节点4");
        List<Double> values4 = Arrays.asList(75.0, 80.0, 72.0, 78.0, 82.0);
        series4.setValues(values4);
        series4.setColor(new Color(80, 180, 150));  // 绿色
        rightSeriesList.add(series4);

        rightRadar.setSeriesList(rightSeriesList);
        chartData.setRightRadar(rightRadar);
        chartData.setGridLevels(4);
        chartData.setFillAlpha(70);
        chartData.setLineWidth(2.0f);
        chartData.setShowDataPoints(true);
        chartData.setLegendAtTop(false);
        chartData.setShowLegendSide(true);
        chartData.setFooterText("数据来源：示例数据");
        JOption option = new JOption();
        option.setData(chartData);
        graphContainer.setOption(option);                     // attach the data model

        JGraphConfig graphConfig = new JGraphConfig();
        graphConfig.put("svg", graphContainer);               // register under the name "svg"
        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);

        FileOutputStream fileOutputStream = new FileOutputStream("D:/test/two-radar.pdf");
        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        byte[] bytes = factory.executeResource("sample/svg2.txt");
        fileOutputStream.write(bytes);
    }
}
```

The template it renders is three lines long — the placeholder name `svg` matches the name registered in `JGraphConfig`:

```html
<pdf>
<body>
<svg>&{svg}</svg>
</body>
</pdf>
```

`&{svg}` reads a **registered resource** (`JGraphConfig.put("svg", container)`, used here). `${svg}` reads a **bound string** (`factory.bind("svg", svgText)`, used by Demo 1). Both are accepted inside `<svg>`.

**Chart types** — every value accepted by `JGraphContainer.setType(...)`:

| Chart | `JChartType` value | Renderer (module `jquick-pdf-svg`) |
|---|---|---|
| Bar | `BAR` | `JBarChartsRenderer` |
| Box plot | `BOXPLOT` | `JBoxPlotChartRenderer` |
| Heat map | `HEATMAP` | `JHeatMapChartRenderer` |
| K-line (candlestick) | `K` | `JKChartsRenderer` |
| Line | `LINE` | `JLineChartsRenderer` |
| Pie | `PIE` | `JPieChartsRenderer` |
| Radar | `RADAR` | `JRadarChartsRenderer` |
| Relation graph | `RELATION` | `JRelationChartRenderer` |
| Scatter | `SCATTER` | `JScatterChartsRenderer` |
| Sunburst | `SUNBURST` | `JSunburstChart` |
| Word cloud | `WORDCLOUD` | `JWordCloudRenderer` |
| Treemap | `TREEMAP` | `JTreeMapRenderer` |
| Bubble | `Bubble` | `JBubbleChartRenderer` |
| Calendar | `Calendar` | `JCalendarChartRenderer` |
| Lunar calendar | `Lunar` | `JLunarCalendarRenderer` |
| Funnel | `Funnel` | `JFunnelChartRenderer` |
| Correlation matrix | `CorrectionMatrix` | `JCorrelationMatrixRenderer` |
| Gantt | `Gantt` | `JGanttChartRenderer` |
| Gauge (deprecated) | `Guage` | `JGuageRenderer` |
| Geo map | `Geo` | `JGeoJsonRenderer` |
| Line + bar combo | `LineBar` | `JComboLineBarChartRenderer` |
| Multi line | `MultipleLine` | `JMultiLineChartRenderer` |
| Multi bar | `MultipleBar` | `JMultiBarChartRenderer` |
| Stacked area | `AREA` | `JAreaChartRenderer` |
| Horizontal bar | `HorizontalBar` | `JHorizontalBarChartRenderer` |
| Multi horizontal bar | `MutipleHorizontalBar` | `JHorizontalMultiBarChartRenderer` |
| Double radar | `DoubleRadar` | `JDoubleRadarChartRenderer` |
| Line-radar | `LineRadar` | `JLineScatterChartRenderer` |
| Circle / ring | `Circle` | `JCircleChartRenderer` |
| Advanced topology | `AdvancedTopology` | `JAdvancedTopologyRenderer` |
| Timeline | `TimeLine` | `JTimeLineRenderer` |
| Tree | `Tree` | `JTreeChartRenderer` |

Any type can also be rendered to an SVG file or string directly, without a document:

```java
JOption option = new JOption();
option.setData(chartData);                                  // same data model as above

// to an SVG file
JChartRendererFactory.renderChart(JChartType.DoubleRadar, option, "D:/test/chart.svg");

// to an SVG string (e.g. to store it, or to bind it with ${svg})
String svg = JChartRendererFactory.renderChart(JChartType.DoubleRadar, option);
```

**Elements and APIs used in this demo**

| Element / API | Purpose | Supported properties / methods |
|---|---|---|
| `<svg>` | chart placeholder inside the template | `width`, `height`, `margin*`, `padding*`; content from `&{svg}` (registered) or `${svg}` (bound) |
| `JGraphContainer` | carries the chart type and its data model | `setType(JChartType)`, `setOption(JOption)` |
| `JGraphConfig` | resource registry of the document | `put("svg", container)` — the key is the placeholder name |
| `JPdfConfig` | document configuration | `setGraphConfig(JGraphConfig)` |
| `JOption` | chart option object | `setData(...)`, plus the fluent `title()`, `legend()`, `tooltip()`, `xAxis()`, `yAxis()`, `series()` |
| `JChartRendererFactory` | renders any type without a document | `renderChart(JChartType, JOption)` → SVG string, `renderChart(JChartType, JOption, String path)` |
| `JDoubleRadarChartData` | data model of this chart | `setWidth`, `setHeight`, `setTitleText`, `setSubtitleText`, `setLeftTitle`, `setRightTitle`, `setDimensions`, `setLeftRadar`, `setRightRadar`, `setGridLevels`, `setFillAlpha`, `setLineWidth`, `setShowDataPoints`, `setLegendAtTop`, `setShowLegendSide`, `setFooterText` |

### Element and style reference

**Elements** (`jquick-pdfx`, grammar-verified):

| Element | Description |
|---|---|
| `<h1>` … `<h6>` | headings, six levels |
| `<p>` | paragraph |
| `<span>` | inline text run |
| `<br>` / `<tab>` | line break / tab |
| `<div>` | block container: background, border, padding, size, rotation, flex |
| `<list>` / `<li>` | list container and list items |
| `<table>` / `<tr>` / `<th>` / `<td>` | table, with per-cell styles |
| `<image src="..." alt="...">` | raster image |
| `<svg>` | vector graphics, `${svg}` / `&{svg}` |
| `<tree>` | tree structure |
| `<button>` / `<checkbox>` / `<comboBoxField>` / `<textArea>` | interactive form fields |
| `<areaBreak>` / `<htmlPageBreak>` / `<lineSeparator>` | forced page break / HTML-style page break / horizontal rule |
| `<template>` | reusable template fragment |

**Style properties** — usable on any element; camelCase and kebab-case are interchangeable:

| Property | Value | Usage and description |
|---|---|---|
| `width` | `"300px"` | Element width. `<div style="width:300px">content</div>` |
| `height` | `"300px"` | Element height. `<div style="height:300px">content</div>` |
| `maxHeight` | `"300px"` | Upper bound of the element height. `<div style="maxHeight:300px">content</div>` |
| `minHeight` | `"300px"` | Lower bound of the element height. `<div style="minHeight:300px">content</div>` |
| `minWidth` | `"300px"` | Lower bound of the element width. `<div style="minWidth:300px">content</div>` |
| `maxWidth` | `"300px"` | Upper bound of the element width. `<div style="maxWidth:300px">content</div>` |
| `relativePosition` | `"30px 30px 30px 30px"` | Offset of the element inside its box, in `left top right bottom` order. `<div style="relativePosition:30px 30px 30px 30px">content</div>` |
| `font` | `"HELVETICA"` | Font face; values follow `JFontEnum`. `<span style="font:HELVETICA">text</span>` |
| `fontFamilyNames` | `"Helvetica,Arial"` | Comma-separated font family list. `<p style="fontFamilyNames:Helvetica,Arial">text</p>` |
| `fontColor` | `"red"` | Text colour; names follow `JColorEnums`. `<span style="fontColor:red">text</span>` |
| `fontSize` | `"34"` | Font size. `<p style="fontSize:34">text</p>` |
| `fontKerning` | `"yes"` | Whether kerning is applied. `<p style="fontKerning:yes">text</p>` |
| `fontScript` | `"common"` | Unicode script of the text run. `<p style="fontScript:common">text</p>` |
| `textAlignment` | `"left"` | Horizontal alignment; values follow `JTextAlignment`. `<div style="textAlignment:left">text</div>` |
| `characterSpacing` | `"30"` | Extra spacing between characters. `<p style="characterSpacing:30">text</p>` |
| `wordSpacing` | `"30"` | Extra spacing between words. `<p style="wordSpacing:30">text</p>` |
| `splitCharacters` | `"24"` | Alias of `characterSpacing`. `<p style="splitCharacters:24">text</p>` |
| `textRenderingMode` | `"24"` | How glyphs are painted (fill, stroke, clip...). `<p style="textRenderingMode:24">text</p>` |
| `baseDirection` | `"no_bidi"` | Base direction of bidirectional text. `<p style="baseDirection:no_bidi">text</p>` |
| `bold` | `"true"` | Bold text. `<span style="bold:true">text</span>` |
| `italic` | `"true"` | Italic text. `<span style="italic:true">text</span>` |
| `lineThrough` | `"true"` | Strikethrough text. `<span style="lineThrough:true">text</span>` |
| `underline` | `"true"` | Underlined text. `<span style="underline:true">text</span>` |
| `backgroundColor` | `"red"` | Fill colour behind the element; names follow `JColorEnums`. `<div style="backgroundColor:red">content</div>` |
| `backgroundImage` | `"D:/pdf/image.png"` | Background image path. `<div style="backgroundImage:D:/pdf/image.png">content</div>` |
| `border` | `"solid 32px red"` | Shorthand border written as `type width color`. `<div style="border:solid 32px red">content</div>` |
| `borderTop` | `"solid 2px red"` | Top edge only, same syntax as `border`. `<div style="borderTop:solid 2px red">content</div>` |
| `borderRight` | `"solid 2px red"` | Right edge only, same syntax as `border`. `<div style="borderRight:solid 2px red">content</div>` |
| `borderLeft` | `"solid 2px red"` | Left edge only, same syntax as `border`. `<div style="borderLeft:solid 2px red">content</div>` |
| `borderBottom` | `"solid 2px red"` | Bottom edge only, same syntax as `border`. `<div style="borderBottom:solid 2px red">content</div>` |
| `borderRadius` | `"32px 24px"` | Corner radius, one to four values. `<div style="borderRadius:32px 24px">content</div>` |
| `borderBottomLeftRadius` | `"12px"` | Radius of the bottom-left corner. `<div style="borderBottomLeftRadius:12px">content</div>` |
| `borderBottomRightRadius` | `"12px"` | Radius of the bottom-right corner. `<div style="borderBottomRightRadius:12px">content</div>` |
| `borderTopRightRadius` | `"12px"` | Radius of the top-right corner. `<div style="borderTopRightRadius:12px">content</div>` |
| `borderTopLeftRadius` | `"12px"` | Radius of the top-left corner. `<div style="borderTopLeftRadius:12px">content</div>` |
| `opacity` | `"0.5"` | Element opacity, `0`–`1`. `<div style="opacity:0.5">content</div>` |
| `strokeColor` | `"red"` | Colour of the element outline. `<div style="strokeColor:red">content</div>` |
| `strokeWidth` | `"24"` | Width of the element outline. `<div style="strokeWidth:24">content</div>` |
| `destination` | `"hello"` | Named anchor that internal links jump to. `<div style="destination:hello">content</div>` |

Units: `px` (96 DPI, `1px = 0.75pt`), `pt`, `mm`, `cm`, `in`. Colors: names (`red`, `blue`, `lightgray`, ...), `#RRGGBB`, `rgb()` / `rgba()`, `background:linear-gradient(...)`.

## Version Matrix

| Version range | PDF engine | License | Closed-source / commercial use |
|---|---|---|---|
| **≥ 4.0.1** | Apache PDFBox 3.x | **Apache License 2.0** | Allowed, no copyleft obligation |
| **4.0.0 and below** (incl. 1.x, 2.x, 3.x) | iText 7 Core | **AGPL-3.0** | Requires a commercial license from iText Group NV, or full AGPL compliance |

## License

> **The license depends on the version you use — this is the most important section of this document.**

**Version 4.0.0 — AGPL-3.0.**
4.0.0 and every earlier version were built on **iText 7 Core**, which is distributed under the **AGPL-3.0** copyleft license. AGPL-3.0 propagates to derivative works, so any version shipping iText 7 must itself be distributed under AGPL-3.0. The Apache-2.0 declaration published with some earlier releases was therefore **retracted for all versions ≤ 4.0.0**. Keeping AGPL-3.0 here is deliberate: it is what the iText 7 dependency requires.

**Version 4.0.1 and above — Apache-2.0.**
Starting with 4.0.1 the rendering core was migrated from iText 7 to **Apache PDFBox 3**, which is licensed under Apache-2.0. Removing the AGPL dependency is what allows jquick-pdf itself to be released under **Apache-2.0**, so **4.0.1 and every later version are Apache-2.0**.

Why the migration matters:

| Consequence | ≤ 4.0.0 (iText 7, AGPL-3.0) | ≥ 4.0.1 (PDFBox, Apache-2.0) |
|---|---|---|
| Linking into a closed-source product | Requires AGPL compliance or a paid iText license | Permitted, no source-disclosure obligation |
| Network/SaaS use | AGPL section 13 obliges offering the source to users | No such obligation |
| Output PDF notice | Carries the `Powered by iText` attribution notice | No iText notice, since no iText code is involved |
| Recommended action | Upgrade | Use this line for new projects |

Upgrading from `≤ 4.0.0` to `≥ 4.0.1` **removes** the AGPL obligation; downgrading re-introduces it. Full texts: [LICENSE](./LICENSE) and [NOTICE](./NOTICE).

## Contribution Guide

Contributions are welcome — bug reports, feature requests and pull requests alike.

1. Fork the repository and create a topic branch: `git checkout -b feature/my-feature`.
2. Build and test locally:

   ```bash
   mvn clean install
   mvn -pl jquick-pdfx -am test
   ```

   On headless machines (CI, containers):

   ```bash
   mvn -pl jquick-pdfx -am test \
     -DargLine="-Djava.awt.headless=true -Dsun.java2d.d3d=false -Dsun.java2d.opengl=false"
   ```

3. Keep the existing style: pure Java, no new mandatory third-party runtime dependency, javadoc on public API.
4. Add or update tests under `jquick-pdfx/src/test/java`; sample templates belong in `jquick-pdfx/src/test/resources/sample/`. If your change adds a demo image, add the image to `images/` and register the image → test class → method row in [Demo Gallery](#demo-gallery).
5. Open a pull request describing the motivation and the behavior change.

When reporting a bug, please include the template that reproduces it, the jquick-pdf version, and the produced PDF if possible.

---

<p align="center">
  Repository: <a href="https://github.com/paohaijiao/jquick-pdf">github.com/paohaijiao/jquick-pdf</a> ·
  Issues: <a href="https://github.com/paohaijiao/jquick-pdf/issues">issues</a> ·
  Maven Central: <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-pdfx">jquick-pdfx</a> ·
  Author: Martin (goudingcheng@gmail.com)
</p>

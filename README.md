# 📄 JQuickPDF

> 最轻量、最纯粹的 Java PDF 生成库

<p align="center">
  <a href="./README.md">简体中文</a> | <a href="./README-EN.md">English</a>
</p>

<p align="center">
  <a href="https://github.com/akullpp/awesome-java"><img src="https://img.shields.io/badge/Awesome-Java-ff69b4.svg" alt="Awesome Java"></a>
  <a href="https://search.maven.org/artifact/io.github.paohaijiao/jquick-pdfx"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-pdfx?style=flat-square" alt="Maven Central"></a>
  <a href="https://github.com/paohaijiao/jquick-pdf/stargazers"><img src="https://img.shields.io/github/stars/paohaijiao/jquick-pdf.svg?style=flat-square&logo=github&label=Stars" alt="Stars"></a>
  <a href="https://github.com/paohaijiao/jquick-pdf/fork"><img src="https://img.shields.io/github/forks/paohaijiao/jquick-pdf.svg?style=flat-square&logo=github&label=Forks" alt="Forks"></a>
  <a href="https://github.com/paohaijiao/jquick-pdf/issues"><img src="https://img.shields.io/github/issues/paohaijiao/jquick-pdf.svg?style=flat-square&logo=github&label=Issues" alt="Issues"></a>
  <a href="./LICENSE"><img src="https://img.shields.io/badge/License-AGPL--3.0-red.svg?style=flat-square" alt="License"></a>
</p>

> ⚡ 被 [Awesome Java](https://github.com/akullpp/awesome-java) 收录的 PDF 生成库
>
> 如果这个项目对你有帮助，欢迎点个 Star ⭐

---

## 📝 前言

Java 生成 PDF，常见问题往往不在“能不能生成”，而在“能不能好写、好维护、好部署”。

常见痛点包括：

- 依赖浏览器或 WebKit，部署重
- 模板语法难写，样式属性不清晰
- 图表渲染分散，业务报表拼装成本高
- SVG 矢量图嵌入麻烦
- 表格、表单、分页、图片等能力经常需要自己拼

**JQuickPDF** 就是为这些问题准备的。

它希望用更轻的方式，解决 Java 业务文档、报表、信用报告、图文 PDF 的生成问题。

---

## 🛠️ 项目介绍

**JQuickPDF** 是一个轻量级 Java PDF 生成库，底层依赖 **iText 7**，提供类 HTML + CSS 的模板语法，并支持模板变量、图表、SVG、表格、表单、图片、自定义字体和分页能力。

它适合这些业务场景：

- 企业信用报告
- 数据分析报告
- 图文排版型 PDF
- 带图表的业务报表
- 模板驱动的通知单、合同、说明书

项目特点很直接：

- 纯 Java 实现
- 不需要浏览器
- 不需要 WebKit 外部渲染引擎
- 支持与 Thymeleaf、FreeMarker 等模板引擎结合使用

---

## ✨ 核心特性

- 🚀 **纯 Java 生成 PDF**
  - 不依赖浏览器
  - 不依赖 WebKit
  - 适合服务端直接生成
- 🎨 **类 HTML + CSS 模板语法**
  - 更接近前端开发习惯
  - 文本、表格、图片、SVG 更容易组织
- 🧩 **模板引擎友好**
  - 支持 Thymeleaf
  - 支持 FreeMarker
  - 支持变量绑定与模板片段注入
- 📊 **30+ 图表能力**
  - 柱状图、饼图、雷达图、K 线、热力图、甘特图、词云、地图等
- 🧾 **常见 PDF 元素完备**
  - 文本
  - 表格
  - 表单
  - 图片
  - SVG
  - 分页
- 🔤 **支持字体配置**
  - 适合中文业务文档输出
- 🔗 **支持数据绑定**
  - 模板与 Java 数据模型可组合使用

---

## 📦 快速开始

### 1. Maven 依赖

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdfx</artifactId>
    <version>最新版本</version>
</dependency>
```

### 2. 极简最小示例

#### Java 代码

```java
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import java.io.FileOutputStream;

public class Demo {
    public static void main(String[] args) throws Exception {
        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", "<h1>Hello JQuickPDF</h1><p>这是一个最小示例。</p>");
        config.setTemplateConfig(templateConfig);

        String template = "<html><body><template>&html</template></body></html>";

        byte[] pdf = new JQuickPdfFactory(config).executeContent(template);

        try (FileOutputStream out = new FileOutputStream("demo.pdf")) {
            out.write(pdf);
        }
    }
}
```

#### 模板 XML 片段

```xml
<html>
<body>
  <template>&html</template>
</body>
</html>
```

---

## 📖 核心能力一览

### 支持状态

| 状态 | 含义 |
|---|---|
| ✅ 已支持 | 已在仓库主流程中可用，并有示例 |
| 🟡 迁移中 | 已有 visitor / 渲染骨架，正在从 iText 向 PDFBox 过渡 |
| ⏳ 待支持 | 仓库中已有入口或类，但未完成完整接入 |

### 元素清单

| 元素 | 状态 | 示例 / 说明 |
|---|---|---|
| `<paragraph>` | ✅ 已支持 | `sample/paragraph.txt` |
| `<heading>` | ✅ 已支持 | `sample/heading.txt` |
| `<span>` | ✅ 已支持 | `sample/span.txt` |
| `<div>` | ✅ 已支持 | `sample/div.txt` |
| `<list>` / `<li>` | ✅ 已支持 | `sample/list.txt` |
| `<table>` / `<tr>` / `<td>` / `<th>` | ✅ 已支持 | `sample/table.txt` |
| `<image>` | ✅ 已支持 | `sample/image.txt` |
| `<svg>` | ✅ 已支持 | `sample/svg1.txt`、`sample/svg2.txt` |
| `<areaBreak>` | ✅ 已支持 | `sample/areaBreak.txt` |
| `<htmlPageBreak>` | ✅ 已支持 | `sample/htmlPageBreak.txt` |
| `<lineSeparator>` | ✅ 已支持 | `sample/lineSeperator.txt` |
| `<tab>` | ✅ 已支持 | `sample/tab.txt` |
| `<button>` | ✅ 已支持 | `sample/button.txt` |
| `<checkbox>` | ✅ 已支持 | `sample/checkbox.txt` |
| `<comboBoxField>` | ✅ 已支持 | `sample/comboxFiled.txt` |
| `<textArea>` | ✅ 已支持 | `sample/textArea.txt` |
| `<tree>` | ✅ 已支持 | `sample/tree1.txt`、`sample/tree2.txt` |
| `<template>` | 🟡 迁移中 | `sample/template.txt` |
| `<inputField>` | ⏳ 待支持 | 当前仅有空 visitor，未接入完整 grammar |
| `${variable}` | ✅ 已支持 | 用于变量占位绑定 |

### 图形 Demo 索引

| 图形 / 场景 | Demo 文件 |
|---|---|
| Area | `demo/area/JQuickAreaTest.java` |
| Horizontal Bar | `demo/bar/JQuickHorizontalBarChartTest.java` |
| Multiple Bar | `demo/bar/JQuickMutipleBarTest.java` |
| Multiple Horizontal Bar | `demo/bar/JQuickMutipleHorizontalBarChartTest.java` |
| Line Radar | `demo/bar/JQuickLineRadarChartTest.java` |
| Bubble | `demo/bubble/JQuickBubbleTest.java` |
| Calendar | `demo/calendar/JQuickCalendarTest.java` |
| Circle / Donut | `demo/circle/JQuickCircleTest.java` |
| Credit Report | `demo/creditreport/JQuickCreditReportTest.java` |
| Funnel | `demo/funnel/JQuickFunnelTest.java` |
| Gantt | `demo/gantt/JGanttTest.java` |
| Geo / Map | `demo/geo/JGeoTest.java` |
| Gauge | `demo/guage/JQuickGuageTest.java` |
| Line Bar | `demo/linebar/JQuickLineBarTest.java` |
| Multiple Line | `demo/linebar/JQuickMutipleLineTest.java` |
| Lunar | `demo/lunar/JQuickLunarTest.java` |
| Matrix | `demo/matrix/JQuickMatrixTest.java` |
| TimeLine | `demo/other/JQuickTimeLineTest.java` |
| Tree | `demo/other/JQuickTreeTest.java` |
| TreeMap | `demo/other/JQuickTreeMapTest.java` |
| Double Radar | `demo/radar/JQuickTwoRadarTest.java` |
| Sunburst | `demo/sunburd/JQuickSunburdTest.java` |
| Advanced Topology | `demo/advance/JQuickAdvanceToplogyTest.java` |

---

## 📚 API 与模板标签详解

> 这一节分两部分：Java 核心 API，和 XML 模板标签与属性。

### 1. Java 核心 API

#### `JQuickPdfXExecutor`

仓库中的主执行入口：

- 支持空构造
- 支持传入 `JContext`
- 支持传入 `JPdfConfig`
- 支持同时传入 `JContext` 和 `JPdfConfig`

常见构造方式：

```java
JQuickPdfXExecutor executor1 = new JQuickPdfXExecutor();
JQuickPdfXExecutor executor2 = new JQuickPdfXExecutor(context);
JQuickPdfXExecutor executor3 = new JQuickPdfXExecutor(config);
JQuickPdfXExecutor executor4 = new JQuickPdfXExecutor(context, config);
```

主流程作用：

- 解析模板文本
- 构建 ANTLR 语法树
- 进入 `JPdfXCommonVisitor` 渲染 PDF
- 输出 `OutputStream`

#### 常见配置对象

| 配置对象 | 作用 | 说明 |
|---|---|---|
| `JPdfConfig` | PDF 总配置 | 页面、边距、字体、图表、模板等总入口 |
| `JTemplateConfig` | 模板片段配置 | `templateConfig.put("html", htmlString)` |
| `JGraphConfig` | 图表配置 | 为图表标签提供图形数据 |
| `JTreeNodeConfig` | 树结构配置 | 为 tree 标签提供树数据 |
| `JContext` | 变量上下文 | 传入 `${variable}` 所需数据 |

#### `JPdfConfig` 常见字段

| 字段 | 作用 |
|---|---|
| `defaultPageSize` | 默认页面尺寸 |
| `margins` | 页面边距列表 |
| `reverse` | 页面顺序控制 |
| `catalogConfig` | 目录配置 |
| `doc` | 文档配置 |
| `pageConfig` | 页面配置 |
| `headerConfig` | 页眉配置 |
| `footerConfig` | 页脚配置 |
| `watermarkConfig` | 水印配置 |
| `fontConfig` | 字体配置 |
| `securityConfig` | 安全配置 |
| `graphConfig` | 图表配置 |
| `comboBoxFieldConfig` | 下拉框配置 |
| `treeConfig` | 树节点配置 |
| `templateConfig` | 模板片段配置 |

### 2. XML 模板标签与属性说明

> 说明：仓库中的样式写法以实际示例为准，常见形式包括 `fontSize`、`marginBottom`、`backgroundColor`、`width`、`height` 等。

#### PDF 根标签与页面控制

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| `<pdf>` / `<html>` | `page-width` | 页面宽度，文档层配置项 | `595` |
| `<pdf>` / `<html>` | `page-height` | 页面高度，文档层配置项 | `842` |
| `<pdf>` / `<html>` | `margin-left` | 左边距 | `36` |
| `<pdf>` / `<html>` | `margin-right` | 右边距 | `36` |
| `<pdf>` / `<html>` | `margin-top` | 上边距 | `36` |
| `<pdf>` / `<html>` | `margin-bottom` | 下边距 | `36` |
| `<areaBreak>` | - | 强制分页 | - |
| `<htmlPageBreak>` | - | 页面切换 | - |

#### 文本与容器标签

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| `<div>` | `width` | 容器宽度 | `400px` |
| `<div>` | `height` | 容器高度 | `230px` |
| `<div>` | `padding` | 内边距 | `15px` |
| `<div>` | `margin` | 外边距 | `30 0 20 0` |
| `<div>` | `background` / `backgroundColor` | 背景色 | `#f8f9fa` |
| `<div>` | `borderRadius` | 圆角 | `4px` |
| `<div>` | `textAlignment` | 文本对齐 | `center` |
| `<p>` / `<paragraph>` | `fontSize` | 字号 | `11` |
| `<p>` / `<paragraph>` | `fontFamily` | 字体 | `微软雅黑` |
| `<p>` / `<paragraph>` | `color` / `fontColor` | 字体颜色 | `#2c3e50` |
| `<p>` / `<paragraph>` | `marginBottom` | 下边距 | `8px` |
| `<heading>` | `fontSize` | 标题字号 | `20` |
| `<heading>` | `fontWeight` | 字重 | `bold` |
| `<span>` | `color` | 文本颜色 | `#3498db` |
| `<span>` | `background` | 背景色 | `#ffffff` |

#### 表格标签

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| `<table>` | `width` | 表格宽度 | `600px` |
| `<table>` | `border` | 边框 | `1px solid #ddd` |
| `<table>` | `cell-padding` / `padding` | 单元格内边距常见写法 | `8px` |
| `<table>` | `cell-spacing` | 单元格间距 | `0` |
| `<table>` | `fontSize` | 表格文本字号 | `10` |
| `<td>` / `<th>` | `padding` | 单元格内边距 | `10px` |
| `<td>` / `<th>` | `backgroundColor` | 单元格背景色 | `#f8f9fa` |

#### 图片标签

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| `<image>` | `src` | 图片地址 | `https://...` |
| `<image>` | `width` | 图片宽度 | `200px` |
| `<image>` | `height` | 图片高度 | `300px` |
| `<image>` | `alt` | 备用说明 | `logo` |

#### 图表标签

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| 图表配置节点 | `type` | 图表类型 | `RADAR` |
| 图表配置节点 | `width` | 图表宽度 | `400` |
| 图表配置节点 | `height` | 图表高度 | `400` |
| 图表配置节点 | `data` | 图表数据来源 | `JGraphConfig` 绑定 |

#### SVG 标签

| 标签 | 属性名 | 说明 | 示例值 |
|---|---|---|---|
| `<svg>` | `width` | 渲染宽度 | `400px` |
| `<svg>` | `height` | 渲染高度 | `400px` |
| `<svg>` | `viewBox` | SVG 视口范围 | `0 0 500 450` |
| `<svg>` | `xmlns` | SVG 命名空间 | `http://www.w3.org/2000/svg` |

---

## 🖼️ SVG 矢量图形数据准备

JQuickPDF 支持把 SVG 字符串作为模板内容嵌入 PDF。

内置 `chart` 能力是封装好的图表体系，适合直接用 Java 图表配置生成。`svg` 标签则更适合接入外部矢量图内容，例如业务图表、设计工具导出的静态图。

### SVG 渲染效果展示

> 下方图片均来自仓库 `images/` 目录，可直接作为 SVG 渲染效果参考。

#### 基础图表

| 图形 | 预览 |
|---|---|
| 柱状图 | ![](./images/barchart.svg) |
| 折线图 | ![](./images/line_chart.svg) |
| 饼图 | ![](./images/pie-chart.svg) |
| 雷达图 | ![](./images/radar_chart.svg) |
| 热力图 | ![](./images/heatmap.svg) |
| 散点图 | ![](./images/scatter.svg) |
| 箱线图 | ![](./images/boxchart.svg) |
| 气泡图 | ![](./images/bubble.svg) |
| 区域图 | ![](./images/area.svg) |
| 漏斗图 | ![](./images/funnel.svg) |

#### 组合与业务图表

| 图形 | 预览 |
|---|---|
| 横向条形图 | ![](./images/horizontalBar.svg) |
| 多重条形图 | ![](./images/fourBar.svg) |
| 多重折线图 | ![](./images/multipleLine.svg) |
| 折线条形组合图 | ![](./images/linebar.svg) |
| 折线雷达图 | ![](./images/lineRadar.svg) |
| 双雷达图 | ![](./images/twoRadar.svg) |
| 矩形树图 | ![](./images/treemap.svg) |
| 词云 | ![](./images/wordcloud.svg) |
| 时间线 | ![](./images/timeline.svg) |
| 甘特图 | ![](./images/gantt.svg) |

#### 特殊场景图形

| 图形 | 预览 |
|---|---|
| 仪表盘 | ![](./images/gauge.svg) |
| 日历 | ![](./images/calendar.svg) |
| K 线图 | ![](./images/k_chart.svg) |
| 地图 | ![](./images/geo.svg) |
| 关系图 | ![](./images/relation_chart.svg) |
| 矩阵图 | ![](./images/Matrix.svg) |
| 旭日图 | ![](./images/sunburst.svg) |
| 树 | ![](./images/tree.svg) |
| 高级拓扑图 | ![](./images/advance_topology.svg) |
| 企业网络拓扑 | ![](./images/toplogy/enterprise_network.svg) |
| 云架构拓扑 | ![](./images/toplogy/cloud_architecture.svg) |
| 数据中心拓扑 | ![](./images/toplogy/datacenter_topology.svg) |
| 手工布局拓扑 | ![](./images/toplogy/manual_layout_topology.svg) |

### 方式一：手写原生 SVG 字符串

适合简单图形、图标、结构固定的小图。

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"120\" height=\"108\" viewBox=\"0 0 500 450\"><rect x=\"10\" y=\"10\" width=\"100\" height=\"50\" fill=\"#3498db\"/></svg>");
```

### 方式二：ECharts / 可视化工具导出 SVG

这是业务报表里最常用的方式。

你可以在前端或图表工具中生成 SVG，再把导出的 SVG 字符串传给模板。

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", svgContentFromEcharts);
```

### 方式三：读取本地 `.svg` 文件，再作为字符串传入

注意不要直接把文件路径传给 `<svg>`，而是先读文件内容，再传字符串。

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", svgFileContent);
```

### 模板内嵌语法

```xml
<pdf>
  <body>
    <svg>${svg}</svg>
  </body>
</pdf>
```

或者你也可以给 `svg` 标签本身加尺寸控制：

```xml
<pdf>
  <body>
    <svg style="width:400px;height:400px">${svg}</svg>
  </body>
</pdf>
```

### Java 传参示例

```java
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;

public class SvgDemo {
    public static void main(String[] args) throws Exception {
        JContext context = new JContext();
        context.put("svg", svgContent);

        JReader reader = new JReSourceFileReader("sample/svg1.txt");
        JAdaptor adaptor = new JAdaptor(reader);

        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(context);
        executor.execute(adaptor.getRuleContent());
    }
}
```

### 常见踩坑点

- ⚠️ SVG 必须带完整命名空间：`xmlns="http://www.w3.org/2000/svg"`
- ⚠️ 不要使用动画、脚本、交互逻辑，只使用静态 SVG
- ⚠️ `viewBox` 要设置正确，否则可能拉伸变形
- ⚠️ 不要直接传入 `.svg` 文件路径，应该先读取为字符串
- ⚠️ `svg` 标签自身的 `width` / `height` 会影响最终渲染尺寸

---

## 📊 支持图表概览

| 图表类型 | 说明 |
|---|---|
| BAR | 柱状图 |
| BOXPLOT | 箱线图 |
| HEATMAP | 热力图 |
| K | K 线图 / 蜡烛图 |
| LINE | 折线图 |
| PIE | 饼图 |
| RADAR | 雷达图 |
| RELATION | 关系图 |
| SCATTER | 散点图 |
| SUNBURST | 旭日图 |
| Treemap | 矩形树图 |
| Bubble | 气泡图 |
| Calendar | 日历活动图 |
| Lunar | 日历 |
| Funnel | 漏斗图 |
| CorrectionMatrix | 相关系数矩阵 |
| Gantt | 甘特图 |
| Gauge | 仪表盘 |
| WordsCloud | 词云 |
| GEO Json | 地图 |
| Line Bar | 折线条形组合图 |
| Multiple Line | 多重折线图 |
| Multiple Bar | 多重条形图 |
| AREA | 区域堆积图 |
| HorizontalBar | 横向条形图 |
| MutipleHorizontalBar | 多重横向条形图 |
| DoubleRadar | 双雷达图 |
| LineRadar | 折线雷达图 |
| Circle | 环形图 |
| AdvancedTopology | 高级拓扑图 |
| TimeLine | 时间线 |
| Tree | 树 |

---

## 🎯 业务实战示例

### 企业信用报告

仓库中已经提供企业信用报告相关示例：

- `jquick-pdfx/src/test/java/com/github/paohaijiao/demo/creditreport/JQuickCreditReportTest.java`
- `jquick-pdfx/src/test/resources/report.txt`
- `jquick-pdfx/src/test/resources/html.txt`
- `jquick-pdfx/src/test/resources/radar.txt`

核心 Java 用法：

```java
import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;

public class CreditReportDemo {
    public static void main(String[] args) throws Exception {
        JReader htmlReader = new JReSourceFileReader("html.txt");
        JAdaptor htmlAdaptor = new JAdaptor(htmlReader);

        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", htmlAdaptor.getRuleContent());
        config.setTemplateConfig(templateConfig);

        JReader svgReader = new JReSourceFileReader("radar.txt");
        JAdaptor svgAdaptor = new JAdaptor(svgReader);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bind("svg", svgAdaptor.getRuleContent());

        byte[] pdf = factory.executeResource("report.txt");
    }
}
```

核心模板片段：

```xml
<div style="margin-bottom:15px">
  <h2 style="color:#3498db; border-bottom:1px solid #3498db; padding-bottom:4px; font-size:13px; margin-bottom:10px">'信用评级雷达图'</h2>
  <div style="display:flex; justify-content:space-between; margin-top:10px">
    <svg style="width:400px;height:400px">${svg}</svg>
  </div>
</div>
```

> 完整示例请直接查看仓库中的 demo 和资源文件。

---

## 📌 ⚠️ 许可证重要追溯声明

> **请务必阅读。**

- **4.0.0 及以下所有版本，追溯生效为 AGPL-3.0。**
- **此前标注的 Apache-2.0 声明正式撤销作废。**
- 产生该变化的原因是：项目底层依赖 **iText 7**，而 iText 7 采用 **AGPL-3.0** 协议分发。
- 对于开源项目：4.0.0 及以下版本，可在 **AGPL-3.0** 条款下免费使用。
- ⚠️ 对于闭源商业项目：若直接集成本库用于闭源商业产品，需要向 **iText Group NV** 购买商业许可证。
- 当前 Roadmap 正在评估迁移到 **Apache PDFBox**，未来计划发布更友好的 **Apache-2.0** 版本。
- 使用本项目时，应注意保留 iText 相关署名与协议要求。

---

## 🗺️ 后续规划 Roadmap

- [ ] 评估迁移到 Apache PDFBox
- [ ] 发布 Apache-2.0 友好版本
- [ ] 持续完善模板与图表能力
- [ ] 优化中文字体与排版体验
- [ ] 补全更多业务场景示例

---

## ❓ 常见疑问 FAQ

### 1. JQuickPDF 适合什么场景？
适合企业信用报告、业务报表、图文 PDF、图表型文档、模板化文档生成。

### 2. 它需要浏览器吗？
不需要。JQuickPDF 是纯 Java 实现，不依赖浏览器或 WebKit。

### 3. 支持模板引擎吗？
支持。可以与 Thymeleaf、FreeMarker 等模板引擎结合使用。

### 4. 如何准备 SVG 数据？
可以手写 SVG 字符串，也可以从 ECharts 或其它可视化工具导出 SVG，还可以读取本地 `.svg` 文件内容再传入模板。

### 5. SVG 可以直接使用 ECharts 导出结果吗？
可以。这是业务报表场景中非常常见的用法，前提是你传入的是完整 SVG 字符串，而不是文件路径。

---

## ☕ 捐赠与支持

如果这个项目对你有帮助，欢迎点个 Star ⭐。

也欢迎通过仓库中的赞助方式支持作者。

---

## 🤝 参与贡献

欢迎提交 Issue、PR 和改进建议。

你可以从这些方向参与：

- 文档改进
- 示例补充
- 图表扩展
- 模板能力完善
- PDFBox 迁移与字体支持优化

---

## 🔗 相关链接

- GitHub: https://github.com/paohaijiao/jquick-pdf
- Gitee: https://gitee.com/paohaijiao/jquick-pdf
- Issues: https://github.com/paohaijiao/jquick-pdf/issues
- 更新日志: [change.md](./change.md)

---

## 📜 License

本项目许可声明请见 [LICENSE](./LICENSE)。

> 4.0.0 及以下版本追溯适用 AGPL-3.0。

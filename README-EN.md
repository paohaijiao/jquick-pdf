# 📄 JQuickPDF

> The lightest and purest Java PDF generation library

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

> ⚡ A PDF generation library featured in [Awesome Java](https://github.com/akullpp/awesome-java)
>
> If this project helps you, please give it a Star ⭐

---

## 📝 Foreword

In Java PDF generation, the real issue is often not whether a PDF can be generated, but whether it is easy to write, maintain, and deploy.

Common pain points include:

- Heavy browser or WebKit dependency
- Hard-to-read template syntax and unclear style attributes
- Scattered chart rendering logic
- SVG embedding is often inconvenient
- Tables, forms, images, and pagination usually need extra assembly work

**JQuickPDF** is built for these problems.

It aims to provide a lighter way to generate business PDFs, reports, credit reports, and image-text documents in Java.

---

## 🛠️ Introduction

**JQuickPDF** is a lightweight Java PDF generation library built on top of **iText 7**. It provides HTML + CSS-like template syntax and supports template variables, charts, SVG, tables, forms, images, custom fonts, and pagination.

It fits these scenarios well:

- Enterprise credit reports
- Data analysis reports
- Image-text PDFs
- Chart-rich business reports
- Template-driven notices, contracts, and manuals

Its technical direction is straightforward:

- Pure Java implementation
- No browser required
- No external WebKit rendering engine required
- Can work with Thymeleaf, FreeMarker, and other template engines

---

## ✨ Core Features

- 🚀 **Pure Java PDF generation**
  - No browser required
  - No WebKit required
  - Suitable for direct server-side generation
- 🎨 **HTML + CSS-like template syntax**
  - Closer to frontend layout habits
  - Easier composition of text, tables, images, and SVG
- 🧩 **Template-engine friendly**
  - Works with Thymeleaf
  - Works with FreeMarker
  - Supports variable binding and template fragment injection
- 📊 **30+ chart capabilities**
  - Bar, pie, radar, K-line, heatmap, Gantt, word cloud, map, and more
- 🧾 **Common PDF elements included**
  - Text
  - Tables
  - Forms
  - Images
  - SVG
  - Pagination
- 🔤 **Font configuration support**
  - Useful for Chinese business documents
- 🔗 **Data binding support**
  - Templates can work with Java-side data models

---

## 📦 Quick Start

### 1. Maven Dependency

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdfx</artifactId>
    <version>latest version</version>
</dependency>
```

### 2. Minimal runnable example

#### Java code

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

#### Template XML snippet

```xml
<html>
<body>
  <template>&html</template>
</body>
</html>
```

---

## 📖 Core Capabilities at a Glance

### Support Status

| Status | Meaning |
|---|---|
| ✅ Supported | Available in the main flow and backed by demos |
| 🟡 Migrating | Visitor / rendering skeleton exists and is moving from iText to PDFBox |
| ⏳ Pending | Entry or class exists in the repository, but full integration is not complete |

### Element List

| Element | Status | Example / Notes |
|---|---|---|
| `<paragraph>` | ✅ Supported | `sample/paragraph.txt` |
| `<heading>` | ✅ Supported | `sample/heading.txt` |
| `<span>` | ✅ Supported | `sample/span.txt` |
| `<div>` | ✅ Supported | `sample/div.txt` |
| `<list>` / `<li>` | ✅ Supported | `sample/list.txt` |
| `<table>` / `<tr>` / `<td>` / `<th>` | ✅ Supported | `sample/table.txt` |
| `<image>` | ✅ Supported | `sample/image.txt` |
| `<svg>` | ✅ Supported | `sample/svg1.txt`, `sample/svg2.txt` |
| `<areaBreak>` | ✅ Supported | `sample/areaBreak.txt` |
| `<htmlPageBreak>` | ✅ Supported | `sample/htmlPageBreak.txt` |
| `<lineSeparator>` | ✅ Supported | `sample/lineSeperator.txt` |
| `<tab>` | ✅ Supported | `sample/tab.txt` |
| `<button>` | ✅ Supported | `sample/button.txt` |
| `<checkbox>` | ✅ Supported | `sample/checkbox.txt` |
| `<comboBoxField>` | ✅ Supported | `sample/comboxFiled.txt` |
| `<textArea>` | ✅ Supported | `sample/textArea.txt` |
| `<tree>` | ✅ Supported | `sample/tree1.txt`, `sample/tree2.txt` |
| `<template>` | 🟡 Migrating | `sample/template.txt` |
| `<inputField>` | ⏳ Pending | Only a visitor stub exists; full grammar wiring is not ready |
| `${variable}` | ✅ Supported | Variable placeholder binding |

### Chart Demo Index

| Chart / Scenario | Demo File |
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

## 📚 API and Template Tag Reference

> This section is split into two parts: Java core APIs and XML template tags with attributes.

### 1. Java Core APIs

#### `JQuickPdfXExecutor`

This is the main execution entry in the repository.

It supports:

- empty constructor
- constructor with `JContext`
- constructor with `JPdfConfig`
- constructor with both `JContext` and `JPdfConfig`

Typical construction:

```java
JQuickPdfXExecutor executor1 = new JQuickPdfXExecutor();
JQuickPdfXExecutor executor2 = new JQuickPdfXExecutor(context);
JQuickPdfXExecutor executor3 = new JQuickPdfXExecutor(config);
JQuickPdfXExecutor executor4 = new JQuickPdfXExecutor(context, config);
```

Main flow responsibilities:

- parse template text
- build the ANTLR parse tree
- render PDF through `JPdfXCommonVisitor`
- return an `OutputStream`

#### Common configuration objects

| Configuration | Purpose | Notes |
|---|---|---|
| `JPdfConfig` | Top-level PDF config | Main entry for page, margin, font, chart, template, and more |
| `JTemplateConfig` | Template fragment config | `templateConfig.put("html", htmlString)` |
| `JGraphConfig` | Chart config | Provides chart data for chart-related rendering |
| `JTreeNodeConfig` | Tree config | Provides tree data for the tree tag |
| `JContext` | Variable context | Provides values for `${variable}` |

#### Common `JPdfConfig` fields

| Field | Purpose |
|---|---|
| `defaultPageSize` | Default page size |
| `margins` | Page margin list |
| `reverse` | Page order control |
| `catalogConfig` | Catalog configuration |
| `doc` | Document configuration |
| `pageConfig` | Page configuration |
| `headerConfig` | Header configuration |
| `footerConfig` | Footer configuration |
| `watermarkConfig` | Watermark configuration |
| `fontConfig` | Font configuration |
| `securityConfig` | Security configuration |
| `graphConfig` | Chart configuration |
| `comboBoxFieldConfig` | ComboBox configuration |
| `treeConfig` | Tree node configuration |
| `templateConfig` | Template fragment configuration |

### 2. XML Template Tags and Attributes

> Note: style naming in the repository follows actual examples, and commonly appears as `fontSize`, `marginBottom`, `backgroundColor`, `width`, `height`, and similar keys.

#### PDF root tags and page control

| Tag | Attribute | Description | Example |
|---|---|---|---|
| `<pdf>` / `<html>` | `page-width` | Page width, document-level control | `595` |
| `<pdf>` / `<html>` | `page-height` | Page height, document-level control | `842` |
| `<pdf>` / `<html>` | `margin-left` | Left margin | `36` |
| `<pdf>` / `<html>` | `margin-right` | Right margin | `36` |
| `<pdf>` / `<html>` | `margin-top` | Top margin | `36` |
| `<pdf>` / `<html>` | `margin-bottom` | Bottom margin | `36` |
| `<areaBreak>` | - | Force page break | - |
| `<htmlPageBreak>` | - | Page switch | - |

#### Text and container tags

| Tag | Attribute | Description | Example |
|---|---|---|---|
| `<div>` | `width` | Container width | `400px` |
| `<div>` | `height` | Container height | `230px` |
| `<div>` | `padding` | Inner padding | `15px` |
| `<div>` | `margin` | Outer margin | `30 0 20 0` |
| `<div>` | `background` / `backgroundColor` | Background color | `#f8f9fa` |
| `<div>` | `borderRadius` | Border radius | `4px` |
| `<div>` | `textAlignment` | Text alignment | `center` |
| `<p>` / `<paragraph>` | `fontSize` | Font size | `11` |
| `<p>` / `<paragraph>` | `fontFamily` | Font family | `微软雅黑` |
| `<p>` / `<paragraph>` | `color` / `fontColor` | Text color | `#2c3e50` |
| `<p>` / `<paragraph>` | `marginBottom` | Bottom margin | `8px` |
| `<heading>` | `fontSize` | Heading size | `20` |
| `<heading>` | `fontWeight` | Font weight | `bold` |
| `<span>` | `color` | Text color | `#3498db` |
| `<span>` | `background` | Background color | `#ffffff` |

#### Table tags

| Tag | Attribute | Description | Example |
|---|---|---|---|
| `<table>` | `width` | Table width | `600px` |
| `<table>` | `border` | Border | `1px solid #ddd` |
| `<table>` | `cell-padding` / `padding` | Common cell padding styles | `8px` |
| `<table>` | `cell-spacing` | Cell spacing | `0` |
| `<table>` | `fontSize` | Table text font size | `10` |
| `<td>` / `<th>` | `padding` | Cell padding | `10px` |
| `<td>` / `<th>` | `backgroundColor` | Cell background color | `#f8f9fa` |

#### Image tags

| Tag | Attribute | Description | Example |
|---|---|---|---|
| `<image>` | `src` | Image source | `https://...` |
| `<image>` | `width` | Image width | `200px` |
| `<image>` | `height` | Image height | `300px` |
| `<image>` | `alt` | Alternate description | `logo` |

#### Chart-related fields

| Tag | Attribute | Description | Example |
|---|---|---|---|
| chart configuration | `type` | Chart type | `RADAR` |
| chart configuration | `width` | Chart width | `400` |
| chart configuration | `height` | Chart height | `400` |
| chart configuration | `data` | Chart data source | `JGraphConfig` binding |

#### SVG tags

| Tag | Attribute | Description | Example |
|---|---|---|---|
| `<svg>` | `width` | Render width | `400px` |
| `<svg>` | `height` | Render height | `400px` |
| `<svg>` | `viewBox` | SVG viewport | `0 0 500 450` |
| `<svg>` | `xmlns` | SVG namespace | `http://www.w3.org/2000/svg` |

---

## 🖼️ Preparing SVG Data

JQuickPDF supports embedding SVG strings into templates.

The built-in chart capability is the wrapped chart system, suitable when you want Java-side chart configuration. The `svg` tag is more suitable for external vector graphics, such as business charts or static SVG exported from design or visualization tools.

### SVG Render Showcase

> The images below come directly from the repository `images/` directory and can be used as SVG rendering references.

#### Basic Charts

| Chart | Preview |
|---|---|
| Bar | ![](./images/barchart.svg) |
| Line | ![](./images/line_chart.svg) |
| Pie | ![](./images/pie-chart.svg) |
| Radar | ![](./images/radar_chart.svg) |
| Heatmap | ![](./images/heatmap.svg) |
| Scatter | ![](./images/scatter.svg) |
| Boxplot | ![](./images/boxchart.svg) |
| Bubble | ![](./images/bubble.svg) |
| Area | ![](./images/area.svg) |
| Funnel | ![](./images/funnel.svg) |

#### Combo and Business Charts

| Chart | Preview |
|---|---|
| Horizontal Bar | ![](./images/horizontalBar.svg) |
| Multiple Bar | ![](./images/fourBar.svg) |
| Multiple Line | ![](./images/multipleLine.svg) |
| Line Bar | ![](./images/linebar.svg) |
| Line Radar | ![](./images/lineRadar.svg) |
| Double Radar | ![](./images/twoRadar.svg) |
| Treemap | ![](./images/treemap.svg) |
| Word Cloud | ![](./images/wordcloud.svg) |
| Timeline | ![](./images/timeline.svg) |
| Gantt | ![](./images/gantt.svg) |

#### Special-Scenario Charts

| Chart | Preview |
|---|---|
| Gauge | ![](./images/gauge.svg) |
| Calendar | ![](./images/calendar.svg) |
| K-line | ![](./images/k_chart.svg) |
| Geo / Map | ![](./images/geo.svg) |
| Relation | ![](./images/relation_chart.svg) |
| Matrix | ![](./images/Matrix.svg) |
| Sunburst | ![](./images/sunburst.svg) |
| Tree | ![](./images/tree.svg) |
| Advanced Topology | ![](./images/advance_topology.svg) |
| Enterprise Network Topology | ![](./images/toplogy/enterprise_network.svg) |
| Cloud Architecture Topology | ![](./images/toplogy/cloud_architecture.svg) |
| Datacenter Topology | ![](./images/toplogy/datacenter_topology.svg) |
| Manual Layout Topology | ![](./images/toplogy/manual_layout_topology.svg) |

### Method 1: Handwritten native SVG string

Suitable for simple shapes, icons, and fixed-structure drawings.

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"120\" height=\"108\" viewBox=\"0 0 500 450\"><rect x=\"10\" y=\"10\" width=\"100\" height=\"50\" fill=\"#3498db\"/></svg>");
```

### Method 2: Export SVG from ECharts or other visualization tools

This is the most common approach in business reporting.

You generate SVG on the frontend or in a chart tool, then pass the exported SVG string into the template.

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", svgContentFromEcharts);
```

### Method 3: Read a local `.svg` file and pass its content as a string

Do not pass the file path directly to `<svg>`. Read the file content first, then pass the string.

```java
import com.github.paohaijiao.param.JContext;

JContext context = new JContext();
context.put("svg", svgFileContent);
```

### Template embedding syntax

```xml
<pdf>
  <body>
    <svg>${svg}</svg>
  </body>
</pdf>
```

You can also control rendering size on the `svg` tag itself:

```xml
<pdf>
  <body>
    <svg style="width:400px;height:400px">${svg}</svg>
  </body>
</pdf>
```

### Java parameter example

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

### Common pitfalls

- ⚠️ SVG must include the full namespace: `xmlns="http://www.w3.org/2000/svg"`
- ⚠️ Avoid animation, scripts, and interaction logic; use static SVG only
- ⚠️ `viewBox` must be correct, otherwise stretching can happen
- ⚠️ Do not pass a `.svg` file path directly; pass the string content instead
- ⚠️ `width` / `height` on the `svg` tag itself affects the final rendered size

---

## 📊 Supported Chart Overview

| Chart Type | Description |
|---|---|
| BAR | Bar chart |
| BOXPLOT | Box plot |
| HEATMAP | Heatmap |
| K | K-line / candlestick chart |
| LINE | Line chart |
| PIE | Pie chart |
| RADAR | Radar chart |
| RELATION | Relationship graph |
| SCATTER | Scatter plot |
| SUNBURST | Sunburst |
| Treemap | Treemap |
| Bubble | Bubble chart |
| Calendar | Calendar activity chart |
| Lunar | Calendar |
| Funnel | Funnel chart |
| CorrectionMatrix | Correlation matrix |
| Gantt | Gantt chart |
| Gauge | Gauge chart |
| WordsCloud | Word cloud |
| GEO Json | Map |
| Line Bar | Line + bar combo chart |
| Multiple Line | Multi-line chart |
| Multiple Bar | Multi-bar chart |
| AREA | Area stacked chart |
| HorizontalBar | Horizontal bar chart |
| MutipleHorizontalBar | Multiple horizontal bar chart |
| DoubleRadar | Double radar chart |
| LineRadar | Radar line chart |
| Circle | Donut chart |
| AdvancedTopology | Advanced topology chart |
| TimeLine | Timeline |
| Tree | Tree |

---

## 🎯 Business Scenario Example

### Enterprise Credit Report

The repository already provides enterprise credit report examples:

- `jquick-pdfx/src/test/java/com/github/paohaijiao/demo/creditreport/JQuickCreditReportTest.java`
- `jquick-pdfx/src/test/resources/report.txt`
- `jquick-pdfx/src/test/resources/html.txt`
- `jquick-pdfx/src/test/resources/radar.txt`

Core Java usage:

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

Core template snippet:

```xml
<div style="margin-bottom:15px">
  <h2 style="color:#3498db; border-bottom:1px solid #3498db; padding-bottom:4px; font-size:13px; margin-bottom:10px">'信用评级雷达图'</h2>
  <div style="display:flex; justify-content:space-between; margin-top:10px">
    <svg style="width:400px;height:400px">${svg}</svg>
  </div>
</div>
```

> Please check the repository demos and resource files for the full example.

---

## 📌 ⚠️ Important Retrospective License Statement

> **Please read carefully.**

- **All versions 4.0.0 and below are retroactively licensed under AGPL-3.0.**
- **The previously stated Apache-2.0 license has been formally revoked.**
- This change is caused by the project’s dependency on **iText 7**, which is distributed under the **AGPL-3.0** license.
- For open-source projects: versions 4.0.0 and below may be used for free under **AGPL-3.0** terms.
- ⚠️ For closed-source commercial projects: if you integrate this library directly into a closed-source commercial product, you must obtain a commercial license from **iText Group NV**.
- The current roadmap is evaluating migration to **Apache PDFBox**, and a more Apache-2.0-friendly version is planned for the future.
- When using this project, you should preserve iText attribution and comply with its license obligations.

---

## 🗺️ Roadmap

- [ ] Evaluate migration to Apache PDFBox
- [ ] Release an Apache-2.0-friendly version
- [ ] Continue improving templates and chart capabilities
- [ ] Improve Chinese font and typography support
- [ ] Add more business-oriented demos

---

## ❓ FAQ

### 1. What scenarios is JQuickPDF suitable for?
It is suitable for enterprise credit reports, business reports, image-text PDFs, chart-based documents, and template-driven document generation.

### 2. Does it require a browser?
No. JQuickPDF is pure Java and does not rely on a browser or WebKit.

### 3. Does it support template engines?
Yes. It can work with Thymeleaf, FreeMarker, and other template engines.

### 4. How should SVG data be prepared?
You can handwrite SVG strings, export SVG from ECharts or other visualization tools, or read a local `.svg` file into a string before passing it to the template.

### 5. Can SVG come directly from ECharts export?
Yes. This is a common approach in business reporting, as long as you pass a complete SVG string rather than a file path.

---

## ☕ Donate & Support

If this project helps you, please give it a Star ⭐.

You are also welcome to support the author through the sponsorship options in the repository.

---

## 🤝 Contributing

Issues, PRs, and suggestions are welcome.

You can contribute in areas such as:

- Documentation improvements
- More demos
- Chart extensions
- Template enhancements
- PDFBox migration and font support improvements

---

## 🔗 Related Links

- GitHub: https://github.com/paohaijiao/jquick-pdf
- Gitee: https://gitee.com/paohaijiao/jquick-pdf
- Issues: https://github.com/paohaijiao/jquick-pdf/issues
- Changelog: [change.md](./change.md)

---

## 📜 License

Please refer to [LICENSE](./LICENSE) for the project license statement.

> Versions 4.0.0 and below are retroactively licensed under AGPL-3.0.

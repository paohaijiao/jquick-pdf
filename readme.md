# JQuickPDF Documentation

## Overview
```string
    jQuickPDF is a lightweight Java library for generating PDF documents from HTML-like 
templates with support for dynamic content and rich styling.
```
## Installation
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdf</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Basic Syntax
```html
<!DOCTYPE html>
<html>
<head>
    <style>
        @page { margin: 2cm }
        body { font-family: Arial }
    </style>
</head>
<body>
    <!-- Content goes here -->
</body>
</html>
```
## Elements STYLE

### BlockElement Styles
| Property              | Example Value            | Description                                                                 |
|-----------------------|--------------------------|-----------------------------------------------------------------------------|
| marginLeft            | `"1px"`                  | Left margin value                                                           |
| marginRight           | `"500px"`                | Right margin value                                                          |
| marginTop             | `"500px"`                | Top margin value                                                            |
| marginBottom          | `"500px"`                | Bottom margin value                                                         |
| commonMargin          | `"100px"`                | Uniform margin for all sides                                                |
| margins               | `"'20px 30px 40px 50px'"`   | Margins for top, right, bottom, left                                        |
| paddingLeft           | `"50px"`                 | Left padding value                                                          |
| paddingRight          | `"50px"`                 | Right padding value                                                         |
| paddingTop            | `"50px"`                 | Top padding value                                                           |
| paddingBottom         | `"50px"`                 | Bottom padding value                                                        |
| commonPadding         | `"50px"`                 | Uniform padding for all sides                                               |
| paddings              | `"50px 50px 60px 70px"`  | Paddings for top, right, bottom, left                                       |
| verticalAlignment     | `"top"`                  | Vertical alignment (top/middle/bottom)                                      |
| spacingRatio          | `"30"`                   | Spacing ratio between elements                                              |
| keepTogether          | `"true"`                 | Whether to keep element together                                            |
| keepWithNext          | `"true"`                 | Whether to keep element with next element                                   |
| angleInRadians        | `"30"`                   | Rotation angle in radians                                                   |
| width                 | `"300px"`                | Element width                                                               |
| height                | `"300px"`                | Element height                                                              |
| maxHeight             | `"300px"`                | Maximum element height                                                      |
| minHeight             | `"300px"`                | Minimum element height                                                      |
| minWidth              | `"300px"`                | Minimum element width                                                       |
| maxWidth              | `"300px"`                | Maximum element width                                                       |

## ElementProperty Styles
| Property                  | Example Value             | Description                                                    |
|---------------------------|---------------------------|----------------------------------------------------------------|
| relativePosition          | `"30px 30px 30px 30px"`   | Relative position values                                       |
| font                      | `"HELVETICA"`             | Font type:the specific value you can refer JFontEnum           |
| fontFamilyNames           | `"Helvetica"`             | Font family names (comma-separated)                            |
| fontColor                 | `"red"`                   | Font color: the value refer JColorEnums  class                 |
| opacity                   | `"0.5"`                   | Element opacity                                                |
| fontSize                  | `"34"`                    | Font size                                                      |
| textAlignment             | `"left"`                  | Text alignment the specific value you can refer JTextAlignment |
| characterSpacing          | `"30"`                    | Character spacing                                              |
| wordSpacing               | `"30"`                    | Word spacing                                                   |
| fontKerning               | `"yes"`                   | Font kerning setting                                           |
| backgroundColor           | `"red"`                   | Background color  the value refer JColorEnums  class                                               |
| backgroundImage           | `"D:/pdf/image.png"`      | Background image path                                          |
| border                    | `"solid 32px red"`        | Border style (type width color)                                |
| borderTop                 | `"solid 32px red"`        | Top border style                                               |
| borderRight               | `"solid 32px red"`        | Right border style                                             |
| borderLeft                | `"solid 32px red"`        | Left border style                                              |
| borderBottom              | `"solid 32px red"`        | Bottom border style                                            |
| borderRadius              | `"32px 24px"`             | Border radius values                                           |
| borderBottomLeftRadius    | `"32px 24px"`             | Bottom-left border radius                                      |
| borderBottomRightRadius   | `"32px 24px"`             | Bottom-right border radius                                     |
| borderTopRightRadius      | `"32px 24px"`             | Top-right border radius                                        |
| borderTopLeftRadius       | `"32px 24px"`             | Top-left border radius                                         |
| splitCharacters           | `"24"`                    | Character spacing (same as characterSpacing)                   |
| textRenderingMode         | `"24"`                    | Text rendering mode                                            |
| strokeColor               | `"red"`                   | Stroke color                                                   |
| strokeWidth               | `"24"`                    | Stroke width                                                   |
| bold                      | `"true"`                  | Whether text is bold                                           |
| italic                    | `"true"`                  | Whether text is italic                                         |
| lineThrough               | `"true"`                  | Whether text has line-through                                  |
| underline                 | `"true"`                  | Whether text is underlined                                     |
| baseDirection             | `"no_bidi"`               | Text base direction                                            |
| fontScript                | `"common"`                | Font script type                                               |
| destination               | `"hello"`                 | Element destination/anchor name                                |

## Content Elements

### Text Elements

| Element    | Description                     | Style Attributes      | Values                          |
|------------|---------------------------------|-----------------------|---------------------------------|
| `<p>`      | Paragraph block (Paragraph)     | ElementProperty style | `text-align="justify"`         |
| `<h1>-<h6>`| Headings (Paragraph with style) | ElementProperty style | `font-size="24pt"`            |
| `<span>`   | Inline text container (Chunk)   | ElementProperty style | `font-style="italic"`         |
| `<br>`     | Line break (Newline)            | ElementProperty style | `<br type="after">`           |
| `<tab>`     | tab                             | ElementProperty style | `<br type="after">`           |

#### 1. paragraph
```java
     JReader fileReader = new JReSourceFileReader("sample/paragraph.txt");
     JAdaptor context = new JAdaptor(fileReader);
     JQuickPdfXExecutor executor = new JQuickPdfXExecutor();
     executor.execute(context.getRuleContent());
```
```html
<html>
<body>
<p>
     '这是 is Paragraph......................................................................'
</p>
<p>
    <tab>
    </tab>
    <tab>
    </tab>
     <tab>
     </tab>
     <span> '这种 is Paragraph1......................................................................'</span>
</p>
</body>
</html>
```
#### 2.h1-h6
```html
<html>
<body>
<h1>
    '这是 is h1......................................................................'
</h1>
<h2>
    '这是 is h2......................................................................'
</h2>
<h3>
    '这是 is h3......................................................................'
</h3>
<h4>
    '这是 is h4......................................................................'
</h4>
<h5>
    '这是 is h5......................................................................'
</h5>
<h6>
    '这是 is h6......................................................................'
</h6>
</body>
</html>
```
#### 3.span
```html
<html>
<body>
<p>
<span style="fontColor:blue">'你好中国'</span>
</p>
</body>
</html>
```
#### 4.tab
```html
<html>
<body>
<p style="fontColor:red">
<tab>/tab>
<tab>/tab>
<tab>/tab>
<span>'你好中国'</span>
</p>
</body>
</html>
```
### Layout Elements

| Element         | Description                          | Style Attributes                | Values                          |
|-----------------|-------------------------------------|---------------------------------|---------------------------------|
| `<div>`         | Block container (Div)               | ElementProperty style and Block | `width="100%"`                 |
| `<areaBreak>`   | Section break (AreaBreak)           | ElementProperty                 | `type="nextPage"`              |
| `<htmlPageBreak>`| Explicit page break (NewPage)       | ElementProperty                 | `<htmlPageBreak/>`             |
#### 1.div
```html
<html>
<body>
<div style="fontColor:red">'你好中国'</div>
</body>
</html>
```
####  2.areaBreak
```html
<html>
<body>
<p>
<span style="fontColor:blue">'你好中国'</span>
</p>
<areaBreak></areaBreak>
<p>
<span style="fontColor:blue">'你好中国'</span>
</p>
</body>
</html>
```
####  3.htmlPageBreak
```html
<html>
<body>
<htmlPageBreak style="font-color:blue">next_area</htmlPageBreak>
</body>
</html>
```

### List Elements

| Element  | Description                          | Style Attributes                | Values                          |
|----------|-------------------------------------|---------------------------------|---------------------------------|
| `<list>` | Ordered/Unordered list (List)       | ElementProperty style and Block | `list-style="circle"`       |
| `<li>`   | List item (ListItem)                | ElementProperty                 | `margin-left="15px"`          |

####  1.list
```html
<html>
<body>
<list style="symbol:hahaha">
    <li style="fontColor:red">'选项1'</li>
    <li style="fontColor:red">'选项2'</li>
    <li style="fontColor:red">'选项3'</li>
    <li style="fontColor:red">'选项4'</li>
    <li style="fontColor:red">'选项5'</li>
</list>
</body>
</html>
```

### Table Elements

| Element | Description                          | Style Attributes                | Values                          |
|---------|-------------------------------------|---------------------------------|---------------------------------|
| `<table>` | Table container (Table)            | ElementProperty style and Block | `border="1px"`                |
| `<tr>`  | Table row (TableRow)                | ElementProperty                 | `height="30px"`               |
| `<td>`  | Table data cell (TableCell)         | ElementProperty                 | `colspan="2"`                 |
| `<th>`  | Table header cell (TableCell)       | ElementProperty                 | `font-weight="bold"`          |

####  1.table
```html
<html>
<body>
<table >
    <tr>
        <td style="fontColor:red">'中国'</td>
        <td style="fontColor:red">'四川'</td>
    </tr>
    <tr>
        <td style="fontColor:red">'你好'</td>
        <td style="fontColor:red">'世界'</td>
    </tr>
</table>
</body>
</html>
```
### Form Elements

| Element           | Description                          | Attributes | Values            |
|-------------------|-------------------------------------|------------|-------------------|
| `<button>`        | Push button (PushbuttonField)       | N/A        | N/A               |
| `<checkbox>`      | Checkbox (CheckboxField)            | N/A        |  N/A        |
| `<inputField>`    | Text input (TextField)              | N/A        |  N/A       |
| `<comboBoxField>` | Dropdown selector (ComboBoxField)   | N/A        |  N/A       |

####  1.button
```html
<html>
<body>
<button style="fontColor:blue">'提交'</button>
</body>
</html>
```
####  2.checkbox
```html
<html>
<body>
<checkbox style="font-color:blue" checked>'提交'</checkbox>
</body>
</html>
```
####  3.inputField
```html
<html>
<body>
<inputField style="font-color:blue">'你好中国'</inputField>
</body>
</html>
```
####  4.comboBoxField
```html
<html>
<body>
<comboBoxField style="font-color:blue" checked>'提交'</comboBoxField>
</body>
</html>
```

### Media Elements

| Element  | Description                          | STYLE Attributes       | Values                         |
|----------|-------------------------------------|------------------------|--------------------------------|
| `<image>` | Embedded image (Image)             | ElementProperty style  | `src="logo.png" width="200px"` |
| `<svg>`   | Vector graphics (PdfTemplate)      | ElementProperty style  | N/A                            |
# ChartType Enum Values
| Enum Value  | Description/Notes                     |
|-------------|---------------------------------------|
| BAR         | Represents a bar chart                |
| BOXPLOT     | Represents a box plot chart           |
| HEATMAP     | Represents a heatmap chart            |
| K           | Represents a K-line chart (candlestick) |
| LINE        | Represents a line chart               |
| PIE         | Represents a pie chart                |
| RADAR       | Represents a radar chart              |
| RELATION    | Represents a relation chart           |
| SCATTER     | Represents a scatter plot             |
| SUNBURST    | Represents a sunburst chart           |
#### sample java code
##### 1.BAR CHART
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
```
##### 2.BOXPLOT CHART
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
```
##### 3.BOXPLOT CHART
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
        new Object[]{1, 0, -3.5}, new Object[]{1, 1, -1.2}, new Object[]{1, 2, 3.0},
        new Object[]{1, 3, 6.5}, new Object[]{1, 4, 4.2}, new Object[]{1, 5, 0.5},
        new Object[]{2, 0, 0.8}, new Object[]{2, 1, 3.5}, new Object[]{2, 2, 8.2},
        new Object[]{2, 3, 12.0}, new Object[]{2, 4, 9.5}, new Object[]{2, 5, 4.2},
        new Object[]{3, 0, 5.2}, new Object[]{3, 1, 8.0}, new Object[]{3, 2, 12.5},
        new Object[]{3, 3, 16.8}, new Object[]{3, 4, 14.2}, new Object[]{3, 5, 9.5},
        new Object[]{4, 0, 10.5}, new Object[]{4, 1, 13.2}, new Object[]{4, 2, 17.8},
        new Object[]{4, 3, 21.5}, new Object[]{4, 4, 19.0}, new Object[]{4, 5, 14.8},
        new Object[]{5, 0, 15.2}, new Object[]{5, 1, 18.5}, new Object[]{5, 2, 22.0},
        new Object[]{5, 3, 26.5}, new Object[]{5, 4, 24.2}, new Object[]{5, 5, 19.8},
        new Object[]{6, 0, 18.5}, new Object[]{6, 1, 22.0}, new Object[]{6, 2, 26.5},
        new Object[]{6, 3, 30.2}, new Object[]{6, 4, 28.5}, new Object[]{6, 5, 23.8},
        new Object[]{7, 0, 17.8}, new Object[]{7, 1, 21.5}, new Object[]{7, 2, 25.2},
        new Object[]{7, 3, 29.0}, new Object[]{7, 4, 27.5}, new Object[]{7, 5, 22.8},
        new Object[]{8, 0, 13.5}, new Object[]{8, 1, 16.2}, new Object[]{8, 2, 20.0},
        new Object[]{8, 3, 24.5}, new Object[]{8, 4, 22.0}, new Object[]{8, 5, 17.5},
        new Object[]{9, 0, 8.2}, new Object[]{9, 1, 11.5}, new Object[]{9, 2, 15.0},
        new Object[]{9, 3, 18.8}, new Object[]{9, 4, 16.5}, new Object[]{9, 5, 12.0},
        new Object[]{10, 0, 2.5}, new Object[]{10, 1, 5.0}, new Object[]{10, 2, 9.2},
        new Object[]{10, 3, 12.5}, new Object[]{10, 4, 10.0}, new Object[]{10, 5, 5.5},
        new Object[]{11, 0, -2.8}, new Object[]{11, 1, -0.5}, new Object[]{11, 2, 3.5},
        new Object[]{11, 3, 6.8}, new Object[]{11, 4, 4.2}, new Object[]{11, 5, 0.0}
        );
        option.series(heatmap);
```
##### 4.K CHART
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
        new Object[]{104.9, 107.3, 104.0, 108.2},
        new Object[]{107.4, 109.1, 106.5, 110.2},
        new Object[]{109.2, 108.8, 107.0, 110.5},
        new Object[]{108.9, 110.3, 108.2, 111.0},
        new Object[]{110.4, 112.1, 109.5, 112.8}
        );
        option.series(candlestick);
```
##### 5.LINE CHART
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
```
##### 6.PIE CHART
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
```
##### 7.RADAR CHART
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
```
##### 8.RELATION CHART
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

        // 添加连接
        List<JLink> links = new ArrayList<>();
        links.add(new JLink("1", "2"));
        links.add(new JLink("1", "3"));
        links.add(new JLink("2", "4"));
        links.add(new JLink("3", "5"));
        links.add(new JLink("4", "6"));
        links.add(new JLink("5", "7"));
        links.add(new JLink("6", "8"));
        links.add(new JLink("7", "9"));
        links.add(new JLink("8", "10"));
        links.add(new JLink("9", "1"));
        links.add(new JLink("10", "2"));
        links.add(new JLink("3", "6"));
        links.add(new JLink("4", "7"));
        links.add(new JLink("5", "8"));
        graph.setLinks(links);

        // 添加类别
        List<JCategory> categories = new ArrayList<>();
        categories.add(new JCategory().name("Category 1"));
        categories.add(new JCategory().name("Category 2"));
        categories.add(new JCategory().name("Category 3"));
        categories.add(new JCategory().name("Category 4"));
        categories.add(new JCategory().name("Category 5"));
        graph.setCategories(categories);

        option.series(graph);
        option.legend().data("Category 1", "Category 2", "Category 3", "Category 4", "Category 5");
```
#####  9.scatter
```java
        JData[] data= new JData[]{
        new JData().value(new Double[]{10.0, 8.04}),
        new JData().value(new Double[]{8.07, 6.95}),
        new JData().value(new Double[]{13.0, 7.58}),
        new JData().value(new Double[]{9.05, 8.81}),
        new JData().value(new Double[]{11.0, 8.33}),
        new JData().value(new Double[]{14.0, 7.66}),
        new JData().value(new Double[]{13.4, 6.81}),
        new JData().value(new Double[]{10.0, 6.33}),
        new JData().value(new Double[]{14.0, 8.96}),
        new JData().value(new Double[]{12.5, 6.82}),
        new JData().value(new Double[]{9.15, 7.2}),
        new JData().value(new Double[]{11.5, 7.2}),
        new JData().value(new Double[]{3.03, 4.23}),
        new JData().value(new Double[]{12.2, 7.83}),
        new JData().value(new Double[]{2.02, 4.47}),
        new JData().value(new Double[]{1.05, 3.33}),
        new JData().value(new Double[]{4.05, 4.96}),
        new JData().value(new Double[]{6.03, 7.24}),
        new JData().value(new Double[]{12.0, 6.26}),
        new JData().value(new Double[]{12.0, 8.84}),
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

```
####  1.image
```html
<html>
<body>
<image src="https://bkimg.cdn.bcebos.com/pic/8b13632762d0f703918f27f985a2463d269759ee6fc7" style="width:2000px;height:300px"></image>
<image src="https://bkimg.cdn.bcebos.com/pic/8b13632762d0f703918f27f985a2463d269759ee6fc7" style="width:2000px;height:300px"></image>
<image src="https://bkimg.cdn.bcebos.com/pic/8b13632762d0f703918f27f985a2463d269759ee6fc7" style="width:2000px;height:300px"></image>
</body>
</html>
```
####  2.svg
```java
JContext params = new JContext();
String svg = "<?xml version=\"1.0\"?>\n" +
"<!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.0//EN'\n" +
"          'http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd'>\n" +
"<svg xmlns:xlink=\"http://www.w3.org/1999/xlink\" style=\"fill-opacity:1; color-rendering:auto; color-interpolation:auto; text-rendering:auto; stroke:black; stroke-linecap:square; stroke-miterlimit:10; shape-rendering:auto; stroke-opacity:1; fill:black; stroke-dasharray:none; font-weight:normal; stroke-width:1; font-family:'Dialog'; font-style:normal; stroke-linejoin:miter; font-size:12px; stroke-dashoffset:0; image-rendering:auto;\" width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
"><!--Generated by the Batik Graphics2D SVG Generator--><defs id=\"genericDefs\"\n" +
"  /><g\n" +
"  ><g style=\"fill:white; stroke:white;\"\n" +
"    ><rect x=\"0\" width=\"800\" height=\"600\" y=\"0\" style=\"stroke:none;\"\n" +
"    /></g\n" +
"    ><g style=\"font-family:'微软雅黑'; font-size:18px; font-weight:bold;\"\n" +
"    ><text x=\"364\" xml:space=\"preserve\" y=\"40\" style=\"stroke:none;\"\n" +
"      >销售数据</text\n" +
"      ><text x=\"374\" y=\"60\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >2023年度</text\n" +
"      ><line y2=\"540\" style=\"fill:none;\" x1=\"60\" x2=\"740\" y1=\"540\"\n" +
"      /><line y2=\"60\" style=\"fill:none;\" x1=\"60\" x2=\"60\" y1=\"540\"\n" +
"      /><text x=\"175\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >衬衫</text\n" +
"      ><text x=\"254\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >羊毛衫</text\n" +
"      ><text x=\"339\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >雪纺衫</text\n" +
"      ><text x=\"430\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >裤子</text\n" +
"      ><text x=\"509\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >高跟鞋</text\n" +
"      ><text x=\"600\" y=\"560\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >袜子</text\n" +
"      ><text x=\"30\" y=\"545\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >0</text\n" +
"      ><line y2=\"540\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"540\"\n" +
"      /><text x=\"30\" y=\"452\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >7</text\n" +
"      ><line y2=\"447\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"447\"\n" +
"      /><text x=\"30\" y=\"359\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >14</text\n" +
"      ><line y2=\"354\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"354\"\n" +
"      /><text x=\"30\" y=\"265\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >21</text\n" +
"      ><line y2=\"260\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"260\"\n" +
"      /><text x=\"30\" y=\"172\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >28</text\n" +
"      ><line y2=\"167\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"167\"\n" +
"      /><text x=\"30\" y=\"79\" style=\"font-size:12px; font-weight:normal; stroke:none;\" xml:space=\"preserve\"\n" +
"      >35</text\n" +
"      ><line y2=\"74\" style=\"fill:none;\" x1=\"55\" x2=\"60\" y1=\"74\"\n" +
"    /></g\n" +
"    ><g style=\"fill:rgb(65,105,225); font-family:'微软雅黑'; stroke:rgb(65,105,225);\"\n" +
"    ><rect x=\"145\" width=\"80\" height=\"66\" y=\"474\" style=\"stroke:none;\"\n" +
"      /><text x=\"184\" xml:space=\"preserve\" y=\"469\" style=\"fill:black; stroke:none;\"\n" +
"      >5</text\n" +
"      ><rect x=\"230\" width=\"80\" height=\"266\" y=\"274\" style=\"stroke:none;\"\n" +
"      /><text x=\"265\" xml:space=\"preserve\" y=\"269\" style=\"fill:black; stroke:none;\"\n" +
"      >20</text\n" +
"      ><rect x=\"315\" width=\"80\" height=\"480\" y=\"60\" style=\"stroke:none;\"\n" +
"      /><text x=\"350\" xml:space=\"preserve\" y=\"55\" style=\"fill:black; stroke:none;\"\n" +
"      >36</text\n" +
"      ><rect x=\"400\" width=\"80\" height=\"133\" y=\"407\" style=\"stroke:none;\"\n" +
"      /><text x=\"435\" xml:space=\"preserve\" y=\"402\" style=\"fill:black; stroke:none;\"\n" +
"      >10</text\n" +
"      ><rect x=\"485\" width=\"80\" height=\"133\" y=\"407\" style=\"stroke:none;\"\n" +
"      /><text x=\"520\" xml:space=\"preserve\" y=\"402\" style=\"fill:black; stroke:none;\"\n" +
"      >10</text\n" +
"      ><rect x=\"570\" width=\"80\" height=\"266\" y=\"274\" style=\"stroke:none;\"\n" +
"      /><text x=\"605\" xml:space=\"preserve\" y=\"269\" style=\"fill:black; stroke:none;\"\n" +
"      >20</text\n" +
"    ></g\n" +
"  ></g\n" +
"></svg>";
params.put("svg", svg);
JReader fileReader = new JReSourceFileReader("sample/svg1.txt");
JAdaptor context = new JAdaptor(fileReader);
JQuickPdfXExecutor executor = new JQuickPdfXExecutor(params);
executor.execute(context.getRuleContent());
```
```html
<html>
<body>
<svg>${svg}</svg>
</body>
</html>
```
####  3.svg for java render
```java
   JGraphContainer graphContainer=new JGraphContainer();
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
                        new Object[]{104.9, 107.3, 104.0, 108.2},
                        new Object[]{107.4, 109.1, 106.5, 110.2},
                        new Object[]{109.2, 108.8, 107.0, 110.5},
                        new Object[]{108.9, 110.3, 108.2, 111.0},
                        new Object[]{110.4, 112.1, 109.5, 112.8}
                );
        option.series(candlestick);
        graphContainer.setOption(option);
        graphContainer.setType(JChartType.K);
        JGraphConfig graphConfig=new JGraphConfig();
        graphConfig.put("svg",graphContainer);

        JPdfConfig config = new JPdfConfig();
        config.setGraphConfig(graphConfig);
        JReader fileReader = new JReSourceFileReader("sample/svg2.txt");
        JAdaptor context = new JAdaptor(fileReader);
        JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(context.getRuleContent());
```
```html
<html>
<body>
<svg>svg</svg>
</body>
</html>
```

## Special Elements

| Element              | Description                          | Attributes | Values            |
|----------------------|-------------------------------------|------------|-------------------|
| `<pageCountElement>` | Page numbering (PageNumber)         | N/A        | N/A               |
| `<template>`         | Reusable content (PdfTemplate)      | N/A        | N/A               |
| `${variable}`        | Data binding (MergeField)           | N/A        | N/A               |
| `<link>`         | Hyperlink (PdfAction)                | N/A        | N/A               |
| `<listBoxField>` | Multi-select list (ListField)        | N/A        | N/A `             |
| `<textArea>`     | Multi-line text input (TextAreaField)| N/A        | N/A               |
| `<tree>`         | Hierarchical data (PdfOutline)       | N/A        | N/A               |
####  1.pageCountElement
```html
<html>
<body>
</p>
<areaBreak></areaBreak>
<p>
</p>
<areaBreak></areaBreak>
<p>
</p>
<areaBreak></areaBreak>
<p>
</p>
<areaBreak></areaBreak>
<p>
<p>
    <span>这是</span>
    <pageCountElement></pageCountElement>
    <span>页</span>
</p>

</body>
</html>
```
####  2.template
```java
        JPdfConfig config=new JPdfConfig();
JTemplateConfig templateConfig=config.getTemplateConfig();
        templateConfig.put("html", "<h1>Hello World</h1><p>This is a test PDF generated from HTML</p>");
JReader fileReader = new JReSourceFileReader("sample/template.txt");
JAdaptor adaptor = new JAdaptor(fileReader);
JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(adaptor.getRuleContent());
```
```html
<html>
<body>
<template>&html</template>
</body>
</html>
```
####  3.link -->future will support the click action
```html
<html>
<body>
<p>
<link style=”strokeColor:red“>'你好'</link>
</p>
</body>
</html>
```
####  4.listBoxField
```html
<html>
<body>
<listBoxField style="fontColor:red">'这是啥元素'
</listBoxField>
</body>
</html>
```
####  5.textArea
```html
<html>
<body>
<p style="fontColor:red">
<textArea>'你好中国'</textArea>
</p>
</body>
</html>
```
####  6.tree
```java
JContext context=new JContext();
TreeNode root = new TreeNode("Root");
TreeNode child1 = new TreeNode("Documents");
TreeNode child2 = new TreeNode("Images");
TreeNode child3 = new TreeNode("System");
child3.setSelected(true);
root.addChild(child1);
root.addChild(child2);
root.addChild(child3);
context.put("tree", root);
JReader fileReader = new JReSourceFileReader("sample/tree1.txt");
JAdaptor adaptor = new JAdaptor(fileReader);
JQuickPdfXExecutor executor = new JQuickPdfXExecutor(context);
executor.execute(adaptor.getRuleContent());
```
```html
<html>
<body>
<tree>${tree}</tree>
</body>
</html>
```


####  7.tree
```java
   JPdfConfig config=new JPdfConfig();
TreeNode root = new TreeNode("Root");
TreeNode child1 = new TreeNode("Documents");
TreeNode child2 = new TreeNode("Images");
TreeNode child3 = new TreeNode("System");
        child3.setSelected(true);
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
JTreeNodeConfig treeNodeConfig=config.getTreeConfig();
        treeNodeConfig.put("tree", root);
JReader fileReader = new JReSourceFileReader("sample/tree2.txt");
JAdaptor adaptor = new JAdaptor(fileReader);
JQuickPdfXExecutor executor = new JQuickPdfXExecutor(config);
        executor.execute(adaptor.getRuleContent());
```
```html
<html>
<body>
<tree>tree</tree>
</body>
</html>
```

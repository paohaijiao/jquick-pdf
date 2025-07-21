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
| Property              | Example Value             | Description                                                                 |
|-----------------------|---------------------------|-----------------------------------------------------------------------------|
| marginLeft            | `"1px"`                   | Left margin value                                                           |
| marginRight           | `"500px"`                 | Right margin value                                                          |
| marginTop             | `"500px"`                 | Top margin value                                                            |
| marginBottom          | `"500px"`                 | Bottom margin value                                                         |
| commonMargin          | `"100px"`                 | Uniform margin for all sides                                                |
| margins               | `"20px 30px 40px 50px"`   | Margins for top, right, bottom, left                                        |
| paddingLeft           | `"50px"`                  | Left padding value                                                          |
| paddingRight          | `"50px"`                  | Right padding value                                                         |
| paddingTop            | `"50px"`                  | Top padding value                                                           |
| paddingBottom         | `"50px"`                  | Bottom padding value                                                        |
| commonPadding         | `"50px"`                  | Uniform padding for all sides                                               |
| paddings              | `"50px 50px 60px 70px"`   | Paddings for top, right, bottom, left                                       |
| verticalAlignment     | `"top"`                   | Vertical alignment (top/middle/bottom)                                      |
| spacingRatio          | `"30"`                    | Spacing ratio between elements                                              |
| keepTogether          | `"true"`                  | Whether to keep element together                                            |
| keepWithNext          | `"true"`                  | Whether to keep element with next element                                   |
| angleInRadians        | `"30"`                    | Rotation angle in radians                                                   |
| width                 | `"300px"`                 | Element width                                                               |
| height                | `"300px"`                 | Element height                                                              |
| maxHeight             | `"300px"`                 | Maximum element height                                                      |
| minHeight             | `"300px"`                 | Minimum element height                                                      |
| minWidth              | `"300px"`                 | Minimum element width                                                       |
| maxWidth              | `"300px"`                 | Maximum element width                                                       |

## ElementProperty Styles
| Property                  | Example Value             | Description                                                                 |
|---------------------------|---------------------------|-----------------------------------------------------------------------------|
| relativePosition          | `"30px 30px 30px 30px"`   | Relative position values                                                    |
| font                      | `"HELVETICA"`             | Font type                                                                   |
| fontFamilyNames           | `"Helvetica"`             | Font family names (comma-separated)                                         |
| fontColor                 | `"red"`                   | Font color                                                                  |
| opacity                   | `"0.5"`                   | Element opacity                                                             |
| fontSize                  | `"34"`                    | Font size                                                                   |
| textAlignment             | `"left"`                  | Text alignment (left/right/center/justified)                                |
| characterSpacing          | `"30"`                    | Character spacing                                                           |
| wordSpacing               | `"30"`                    | Word spacing                                                                |
| fontKerning               | `"yes"`                   | Font kerning setting                                                        |
| backgroundColor           | `"red"`                   | Background color                                                            |
| backgroundImage           | `"D:/pdf/image.png"`      | Background image path                                                       |
| border                    | `"solid 32px red"`        | Border style (type width color)                                             |
| borderTop                 | `"solid 32px red"`        | Top border style                                                            |
| borderRight               | `"solid 32px red"`        | Right border style                                                          |
| borderLeft                | `"solid 32px red"`        | Left border style                                                           |
| borderBottom              | `"solid 32px red"`        | Bottom border style                                                         |
| borderRadius              | `"32px 24px"`             | Border radius values                                                        |
| borderBottomLeftRadius    | `"32px 24px"`             | Bottom-left border radius                                                   |
| borderBottomRightRadius   | `"32px 24px"`             | Bottom-right border radius                                                  |
| borderTopRightRadius      | `"32px 24px"`             | Top-right border radius                                                     |
| borderTopLeftRadius       | `"32px 24px"`             | Top-left border radius                                                      |
| splitCharacters           | `"24"`                    | Character spacing (same as characterSpacing)                                |
| textRenderingMode         | `"24"`                    | Text rendering mode                                                         |
| strokeColor               | `"red"`                   | Stroke color                                                                |
| strokeWidth               | `"24"`                    | Stroke width                                                                |
| bold                      | `"true"`                  | Whether text is bold                                                        |
| italic                    | `"true"`                  | Whether text is italic                                                      |
| lineThrough               | `"true"`                  | Whether text has line-through                                               |
| underline                 | `"true"`                  | Whether text is underlined                                                  |
| baseDirection             | `"no_bidi"`               | Text base direction                                                         |
| fontScript                | `"common"`                | Font script type                                                            |
| destination               | `"hello"`                 | Element destination/anchor name                                             |

## Content Elements

### Text Elements

| Element    | Description                     | Attributes                     | Values                          |
|------------|---------------------------------|--------------------------------|---------------------------------|
| `<p>`      | Paragraph block (Paragraph)     | `text-align`, `line-height`, `indent` | `text-align="justify"`         |
| `<h1>-<h6>`| Headings (Paragraph with style) | `font-size`, `color`, `margin-bottom` | `font-size="24pt"`            |
| `<span>`   | Inline text container (Chunk)   | `font-style`, `background`, `color` | `font-style="italic"`         |
| `<br>`     | Line break (Newline)            | `type` (before/after)          | `<br type="after">`           |
| `<tab>`     | tab                             | `type` (before/after)          | `<br type="after">`           |

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

| Element         | Description                          | Attributes                     | Values                          |
|-----------------|-------------------------------------|--------------------------------|---------------------------------|
| `<div>`         | Block container (Div)               | `position`, `width`, `height`  | `width="100%"`                 |
| `<areaBreak>`   | Section break (AreaBreak)           | `type`, `title`, `margin-top`  | `type="nextPage"`              |
| `<htmlPageBreak>`| Explicit page break (NewPage)       | N/A                            | `<htmlPageBreak/>`             |
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

| Element  | Description                          | Attributes                     | Values                          |
|----------|-------------------------------------|--------------------------------|---------------------------------|
| `<list>` | Ordered/Unordered list (List)       | `list-style`, `indent`, `symbol-indent` | `list-style="circle"`       |
| `<li>`   | List item (ListItem)                | `margin-left`, `list-symbol`   | `margin-left="15px"`          |

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

| Element | Description                          | Attributes                     | Values                          |
|---------|-------------------------------------|--------------------------------|---------------------------------|
| `<table>` | Table container (Table)            | `width`, `border`, `cellpadding` | `border="1px"`                |
| `<tr>`  | Table row (TableRow)                | `height`, `border`, `background` | `height="30px"`               |
| `<td>`  | Table data cell (TableCell)         | `colspan`, `rowspan`, `valign`  | `colspan="2"`                 |
| `<th>`  | Table header cell (TableCell)       | `scope`, `font-weight`         | `font-weight="bold"`          |

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

| Element           | Description                          | Attributes                     | Values                          |
|-------------------|-------------------------------------|--------------------------------|---------------------------------|
| `<button>`        | Push button (PushbuttonField)       | `name`, `value`, `font-size`   | `name="submitBtn"`             |
| `<checkbox>`      | Checkbox (CheckboxField)            | `checked`, `size`, `border`    | `checked="true"`               |
| `<inputField>`    | Text input (TextField)              | `maxlength`, `password`, `readonly` | `password="true"`          |
| `<comboBoxField>` | Dropdown selector (ComboBoxField)   | `options`, `editable`, `selectedIndex` | `options="A,B,C"`      |

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

| Element  | Description                          | Attributes                     | Values                          |
|----------|-------------------------------------|--------------------------------|---------------------------------|
| `<image>` | Embedded image (Image)             | `src`, `width`, `alt`         | `src="logo.png" width="200px"` |
| `<svg>`   | Vector graphics (PdfTemplate)      | `viewBox`, `preserveAspectRatio` | `viewBox="0 0 100 100"`      |
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

| Element              | Description                          | Attributes                     | Values                          |
|----------------------|-------------------------------------|--------------------------------|---------------------------------|
| `<pageCountElement>` | Page numbering (PageNumber)         | `format`, `startNumber`        | `format="Page {0}"`            |
| `<template>`         | Reusable content (PdfTemplate)      | `name`, `layer`               | `name="headerTpl"`             |
| `${variable}`        | Data binding (MergeField)           | N/A                            | `${currentDate}`               |
| `<link>`         | Hyperlink (PdfAction)                | `href`, `target`, `color`         | `href="https://example.com"`    |
| `<listBoxField>` | Multi-select list (ListField)        | `options`, `multiple`, `size`     | `options="A,B,C" multiple="true"` |
| `<textArea>`     | Multi-line text input (TextAreaField)| `rows`, `cols`, `wrap`            | `rows="5" cols="40"`            |
| `<tree>`         | Hierarchical data (PdfOutline)       | `collapsed`, `level`, `title`     | `collapsed="false" level="1"`   |
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

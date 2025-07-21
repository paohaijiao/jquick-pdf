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

#### paragraph
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
### Layout Elements

| Element         | Description                          | Attributes                     | Values                          |
|-----------------|-------------------------------------|--------------------------------|---------------------------------|
| `<div>`         | Block container (Div)               | `position`, `width`, `height`  | `width="100%"`                 |
| `<areaBreak>`   | Section break (AreaBreak)           | `type`, `title`, `margin-top`  | `type="nextPage"`              |
| `<htmlPageBreak>`| Explicit page break (NewPage)       | N/A                            | `<htmlPageBreak/>`             |

### List Elements

| Element  | Description                          | Attributes                     | Values                          |
|----------|-------------------------------------|--------------------------------|---------------------------------|
| `<list>` | Ordered/Unordered list (List)       | `list-style`, `indent`, `symbol-indent` | `list-style="circle"`       |
| `<li>`   | List item (ListItem)                | `margin-left`, `list-symbol`   | `margin-left="15px"`          |

### Table Elements

| Element | Description                          | Attributes                     | Values                          |
|---------|-------------------------------------|--------------------------------|---------------------------------|
| `<table>` | Table container (Table)            | `width`, `border`, `cellpadding` | `border="1px"`                |
| `<tr>`  | Table row (TableRow)                | `height`, `border`, `background` | `height="30px"`               |
| `<td>`  | Table data cell (TableCell)         | `colspan`, `rowspan`, `valign`  | `colspan="2"`                 |
| `<th>`  | Table header cell (TableCell)       | `scope`, `font-weight`         | `font-weight="bold"`          |

### Form Elements

| Element           | Description                          | Attributes                     | Values                          |
|-------------------|-------------------------------------|--------------------------------|---------------------------------|
| `<button>`        | Push button (PushbuttonField)       | `name`, `value`, `font-size`   | `name="submitBtn"`             |
| `<checkbox>`      | Checkbox (CheckboxField)            | `checked`, `size`, `border`    | `checked="true"`               |
| `<inputField>`    | Text input (TextField)              | `maxlength`, `password`, `readonly` | `password="true"`          |
| `<comboBoxField>` | Dropdown selector (ComboBoxField)   | `options`, `editable`, `selectedIndex` | `options="A,B,C"`      |

### Media Elements

| Element  | Description                          | Attributes                     | Values                          |
|----------|-------------------------------------|--------------------------------|---------------------------------|
| `<image>` | Embedded image (Image)             | `src`, `width`, `alt`         | `src="logo.png" width="200px"` |
| `<svg>`   | Vector graphics (PdfTemplate)      | `viewBox`, `preserveAspectRatio` | `viewBox="0 0 100 100"`      |

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

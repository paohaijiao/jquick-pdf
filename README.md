# 📄 JQuickPDF – 轻量级 Java PDF 生成库

简体中文 | [EN](./README-EN.md)

> 一个基于类 HTML 模板的轻量级 Java PDF 库，支持动态内容、丰富样式与多种图表。

---

## 🚀 项目状态

[![GitHub stars](https://img.shields.io/github/stars/paohaijiao/jquick-pdf.svg?style=for-the-badge&logo=github&label=Stars)](https://github.com/paohaijiao/jquick-pdf)
[![GitHub forks](https://img.shields.io/github/forks/paohaijiao/jquick-pdf.svg?style=for-the-badge&logo=github&label=Forks)](https://github.com/paohaijiao/jquick-pdf)
[![GitHub issues](https://img.shields.io/github/issues/paohaijiao/jquick-pdf.svg?style=for-the-badge&logo=github&label=Issues)](https://github.com/paohaijiao/jquick-pdf/issues)
[![GitHub license](https://img.shields.io/github/license/paohaijiao/jquick-pdf.svg?style=for-the-badge&logo=github&label=License)](https://github.com/paohaijiao/jquick-pdf/blob/master/LICENSE)

---

## 📖 目录导航

- [✨ 核心特性](#-核心特性)
- [📦 快速开始](#-快速开始)
    - [安装](#安装)
    - [基础用法](#基础用法)
- [🎨 样式系统](#-样式系统)
    - [块元素样式](#块元素样式)
    - [属性样式](#属性样式)
- [🧩 内容元素](#-内容元素)
    - [文本元素](#文本元素)
    - [布局元素](#布局元素)
    - [列表元素](#列表元素)
    - [表格元素](#表格元素)
    - [表单元素](#表单元素)
    - [媒体元素](#媒体元素)
- [📊 图表支持](#-图表支持)
- [🔧 特殊元素](#-特殊元素)
- [💌 支持项目](#-支持项目)

---
## ✨ 核心特性

JQuickPDF 是一个轻量级的 Java 库，用于从类似 HTML 的模板生成 PDF 文档。支持动态数据绑定、丰富的样式控制以及多种常见图表类型。

### 🌟 主要特点
- ✅ 类 HTML 模板语法，上手简单
- ✅ 支持动态数据绑定
- ✅ 丰富的样式控制
- ✅ 多种图表类型支持
- ✅ 轻量级，无冗余依赖

```java
JQuickPdfXExecutor executor = new JQuickPdfXExecutor();
executor.execute(templateContent);
```

## 📦 📦 快速开始

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-pdf</artifactId>
    <version>最新版本</version>
</dependency>
```

## 📝 基础语法
创建简单的 PDF 模板：
```html
<pdf>
    <body>
    <!-- 内容写在这里 -->
    <h1>Hello JQuickPDF</h1>
    <p>这是一个示例段落。</p>
    </body>
</pdf>
```

### 语法规则
```string
<element style="属性名1:值1; 属性名2:值2; ...">
    内容
</element>
```
#### 元素
##### 文本元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<p>` | 段落块(Paragraph) | `<p style="text-align:justify">'段落内容'</p>` |
| `<h1>-<h6>` | 标题(带样式的段落) | `<h1 style="font-size:24pt">'标题内容'</h1>` |
| `<span>` | 内联文本容器(Chunk) | `<span style="fontColor:blue">'内联文本'</span>` |
| `<br>` | 换行(Newline) | `<br type="after">` |
| `<tab>` | 制表符 | `<p><tab/><tab/><span>'制表符后的文本'</span></p>` |

##### 布局元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<div>` | 块容器(Div) | `<div style="width:100%">'块容器内容'</div>` |
| `<areaBreak>` | 分节符(AreaBreak) | `<areaBreak></areaBreak>` |
| `<htmlPageBreak>` | 显式分页符(NewPage) | `<htmlPageBreak style="font-color:blue">next_area</htmlPageBreak>` |
| `<lineSeparator>` | 分隔线 | `<lineSeparator style="strokeColor:red"></lineSeparator>` |

##### 列表与表格元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<list>` | 有序/无序列表(List) | `<list style="symbol:hahaha"><li>'选项1'</li></list>` |
| `<li>` | 列表项(ListItem) | `<li style="fontColor:red">'选项1'</li>` |
| `<table>` | 表格容器(Table) | `<table><tr><td>'数据'</td></tr></table>` |
| `<tr>` | 表格行(TableRow) | `<tr style="height:30px"></tr>` |
| `<td>` | 表格数据单元格(TableCell) | `<td style="fontColor:red">'数据'</td>` |
| `<th>` | 表格标题单元格(TableCell) | `<th style="font-weight:bold">'标题'</th>` |

##### 表单元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<button>` | 按钮(PushbuttonField) | `<button style="fontColor:blue">'提交'</button>` |
| `<checkbox>` | 复选框(CheckboxField) | `<checkbox style="font-color:blue" checked>'提交'</checkbox>` |
| `<inputField>` | 文本输入框(TextField) | `<inputField style="font-color:blue">'你好中国'</inputField>` |
| `<comboBoxField>` | 下拉选择框(ComboBoxField) | `<comboBoxField style="font-color:blue" checked>'提交'</comboBoxField>` |
| `<textArea>` | 多行文本输入框(TextAreaField) | `<p><textArea>'你好中国'</textArea></p>` |

##### 媒体元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<image>` | 嵌入图片(Image) | `<image src="logo.png" style="width:200px;height:150px"></image>` |
| `<svg>` | 矢量图形(PdfTemplate) | `<svg>${svg}</svg>` 或 `<svg>&{svg}</svg>` |

##### 特殊元素
| Element | Description | 示例代码 |
|---------|-------------|----------|
| `<template>` | 可重用模板内容 | `<template>&html</template>` |
| `<tree>` | 树形结构数据 | `<tree>${tree}</tree>` 或 `<tree>tree</tree>` |
| `${variable}` | 数据绑定占位符 | `<p>姓名: ${name}</p>` |
| `<textArea>` | 多行文本输入框(TextAreaField) | `<p><textArea>'多行文本'</textArea></p>` |


#### 📋 完整属性表格（含示例代码）

| 属性 | 示例值 | 说明 | 示例代码 |
|------|--------|------|----------|
| **边距属性** | | | |
| `marginLeft` | `"1px"` | 左边距值 | `<div style="marginLeft:1px">内容</div>` |
| `marginRight` | `"500px"` | 右边距值 | `<div style="marginRight:500px">内容</div>` |
| `marginTop` | `"500px"` | 上边距值 | `<div style="marginTop:500px">内容</div>` |
| `marginBottom` | `"500px"` | 下边距值 | `<div style="marginBottom:500px">内容</div>` |
| `commonMargin` | `"100px"` | 所有边统一的边距 | `<div style="commonMargin:100px">内容</div>` |
| `margins` | `"'20px 30px 40px 50px'"` | 上、右、下、左边距 | `<div style="margins:'20px 30px 40px 50px'">内容</div>` |
| **内边距属性** | | | |
| `paddingLeft` | `"50px"` | 左内边距值 | `<div style="paddingLeft:50px">内容</div>` |
| `paddingRight` | `"50px"` | 右内边距值 | `<div style="paddingRight:50px">内容</div>` |
| `paddingTop` | `"50px"` | 上内边距值 | `<div style="paddingTop:50px">内容</div>` |
| `paddingBottom` | `"50px"` | 下内边距值 | `<div style="paddingBottom:50px">内容</div>` |
| `commonPadding` | `"50px"` | 所有边统一的内边距 | `<div style="commonPadding:50px">内容</div>` |
| `paddings` | `"50px 50px 60px 70px"` | 上、右、下、左内边距 | `<div style="paddings:50px 50px 60px 70px">内容</div>` |
| **对齐与布局属性** | | | |
| `verticalAlignment` | `"top"` | 垂直对齐方式(top/middle/bottom) | `<div style="verticalAlignment:top">内容</div>` |
| `spacingRatio` | `"30"` | 元素之间的间距比例 | `<div style="spacingRatio:30">内容</div>` |
| `keepTogether` | `"true"` | 是否保持元素在一起 | `<div style="keepTogether:true">内容</div>` |
| `keepWithNext` | `"true"` | 是否与下一个元素保持在一起 | `<div style="keepWithNext:true">内容</div>` |
| **尺寸属性** | | | |
| `width` | `"300px"` | 元素宽度 | `<div style="width:300px">内容</div>` |
| `height` | `"300px"` | 元素高度 | `<div style="height:300px">内容</div>` |
| `maxHeight` | `"300px"` | 元素最大高度 | `<div style="maxHeight:300px">内容</div>` |
| `minHeight` | `"300px"` | 元素最小高度 | `<div style="minHeight:300px">内容</div>` |
| `minWidth` | `"300px"` | 元素最小宽度 | `<div style="minWidth:300px">内容</div>` |
| `maxWidth` | `"300px"` | 元素最大宽度 | `<div style="maxWidth:300px">内容</div>` |
| **其他属性** | | | |
| `angleInRadians` | `"30"` | 旋转角度(弧度) | `<div style="angleInRadians:30">内容</div>` |
#### 📐 尺寸属性表格（含示例代码）

| 属性 | 示例值 | 说明 | 示例代码 |
|------|--------|------|----------|
| `width` | `"300px"` | 元素宽度 | `<div style="width:300px">内容</div>` |
| `height` | `"300px"` | 元素高度 | `<div style="height:300px">内容</div>` |
| `maxHeight` | `"300px"` | 元素最大高度 | `<div style="maxHeight:300px">内容</div>` |
| `minHeight` | `"300px"` | 元素最小高度 | `<div style="minHeight:300px">内容</div>` |
| `minWidth` | `"300px"` | 元素最小宽度 | `<div style="minWidth:300px">内容</div>` |
| `maxWidth` | `"300px"` | 元素最大宽度 | `<div style="maxWidth:300px">内容</div>` |

#### 属性样式
## 🎨 属性样式表格（含示例代码）

| Property | 示例值 | 说明 | 示例代码 |
|----------|--------|------|----------|
| **位置与布局** | | | |
| `relativePosition` | `"30px 30px 30px 30px"` | 相对位置值(左 上 右 下) | `<div style="relativePosition:'30px 30px 30px 30px'">内容</div>` |
| **字体属性** | | | |
| `font` | `"HELVETICA"` | 字体类型，参考 JFontEnum | `<span style="font:HELVETICA">文本</span>` |
| `fontFamilyNames` | `"Helvetica"` | 字体家族名称(逗号分隔) | `<p style="fontFamilyNames:Helvetica,Arial">文本</p>` |
| `fontColor` | `"red"` | 字体颜色，参考 JColorEnums 类 | `<span style="fontColor:red">红色文本</span>` |
| `fontSize` | `"34"` | 字体大小 | `<p style="fontSize:34">大号文本</p>` |
| `fontKerning` | `"yes"` | 字体字距调整设置 | `<p style="fontKerning:yes">调整字距文本</p>` |
| `fontScript` | `"common"` | 字体脚本类型 | `<p style="fontScript:common">文本</p>` |
| **文本样式** | | | |
| `textAlignment` | `"left"` | 文本对齐方式，参考 JTextAlignment | `<div style="textAlignment:left">左对齐文本</div>` |
| `characterSpacing` | `"30"` | 字符间距 | `<p style="characterSpacing:30">文本</p>` |
| `wordSpacing` | `"30"` | 单词间距 | `<p style="wordSpacing:30">文本</p>` |
| `splitCharacters` | `"24"` | 字符间距(同 characterSpacing) | `<p style="splitCharacters:24">文本</p>` |
| `textRenderingMode` | `"24"` | 文本渲染模式 | `<p style="textRenderingMode:24">文本</p>` |
| `baseDirection` | `"no_bidi"` | 文本基础方向 | `<p style="baseDirection:no_bidi">文本</p>` |
| **文本装饰** | | | |
| `bold` | `"true"` | 文本是否加粗 | `<span style="bold:true">粗体文本</span>` |
| `italic` | `"true"` | 文本是否斜体 | `<span style="italic:true">斜体文本</span>` |
| `lineThrough` | `"true"` | 文本是否有删除线 | `<span style="lineThrough:true">删除线文本</span>` |
| `underline` | `"true"` | 文本是否有下划线 | `<span style="underline:true">下划线文本</span>` |
| **背景与边框** | | | |
| `backgroundColor` | `"red"` | 背景颜色，参考 JColorEnums 类 | `<div style="backgroundColor:red">内容</div>` |
| `backgroundImage` | `"D:/pdf/image.png"` | 背景图片路径 | `<div style="backgroundImage:'D:/pdf/image.png'">内容</div>` |
| `border` | `"solid 32px red"` | 边框样式(类型 宽度 颜色) | `<div style="border:'solid 32px red'">内容</div>` |
| `borderTop` | `"solid 32px red"` | 上边框样式 | `<div style="borderTop:'solid 32px red'">内容</div>` |
| `borderRight` | `"solid 32px red"` | 右边框样式 | `<div style="borderRight:'solid 32px red'">内容</div>` |
| `borderLeft` | `"solid 32px red"` | 左边框样式 | `<div style="borderLeft:'solid 32px red'">内容</div>` |
| `borderBottom` | `"solid 32px red"` | 下边框样式 | `<div style="borderBottom:'solid 32px red'">内容</div>` |
| `borderRadius` | `"32px 24px"` | 边框圆角值 | `<div style="borderRadius:'32px 24px'">内容</div>` |
| `borderBottomLeftRadius` | `"32px 24px"` | 左下边框圆角 | `<div style="borderBottomLeftRadius:'32px 24px'">内容</div>` |
| `borderBottomRightRadius` | `"32px 24px"` | 右下边框圆角 | `<div style="borderBottomRightRadius:'32px 24px'">内容</div>` |
| `borderTopRightRadius` | `"32px 24px"` | 右上边框圆角 | `<div style="borderTopRightRadius:'32px 24px'">内容</div>` |
| `borderTopLeftRadius` | `"32px 24px"` | 左上边框圆角 | `<div style="borderTopLeftRadius:'32px 24px'">内容</div>` |
| **效果与描边** | | | |
| `opacity` | `"0.5"` | 元素透明度 | `<div style="opacity:0.5">半透明内容</div>` |
| `strokeColor` | `"red"` | 描边颜色 | `<div style="strokeColor:red">内容</div>` |
| `strokeWidth` | `"24"` | 描边宽度 | `<div style="strokeWidth:24">内容</div>` |
| **锚点与目标** | | | |
| `destination` | `"hello"` | 元素目标/锚点名称 | `<div style="destination:hello">内容</div>` |

# 📊 图表类型
JQuickPDF 支持多种图表类型，可通过 Java 代码配置并嵌入 PDF 中：

| Enum Value       | Description/Notes |
|------------------|-------------------|
| BAR              | 柱状图               |
| BOXPLOT          | 箱线图               |
| HEATMAP          | 热力图               |
| K                | K线图(蜡烛图)          |
| LINE             | 折线图               |
| PIE              | 饼图                |
| RADAR            | 雷达图               |
| RELATION         | 关系图               |
| SCATTER          | 散点图               |
| SUNBURST         | 旭日图(1.5.1)        |
| Treemap          | 矩形树图(1.5.1)       |
| Bubble           | 气泡图(1.5.1)        |
| Calendar         | 日历活动图(1.5.3)      |
| Lunar            | 日历(1.5.3)         |
| Funnel           | 漏斗图(1.5.3)        |
| CorrectionMatrix | 相关系数矩阵(1.5.3)     |
| Gantt            | 甘特图(1.5.3)        |
| Gauge            | 仪表盘(1.5.3)        |
```string 
// ============================================================================
# 柱状图  BAR chart
// ============================================================================
    // 1. 创建图表配置
    JOption option = new JOption();
    option.title().text("销售数据").subtext("2023年度");
    option.tooltip().trigger(JTrigger.axis);
    // 2. 配置坐标轴
    JCategoryAxis xAxis = new JCategoryAxis();
    xAxis.data("衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子");
    option.xAxis(xAxis);
    option.yAxis(new JValueAxis());
    // 3. 配置数据系列
    JBar bar = new JBar();
    bar.name("销量").data(5, 20, 36, 10, 10, 20);
    option.series(bar);
```

#### 8 样例

```java
   JPdfConfig config = new JPdfConfig();
JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.

put("html","  <div style=\"marginBottom:15px\">\n"+
            "    <h2 style=\"color:#3498db;  fontSize:13; marginBottom:10px\">企业资质认证</h2>\n"+
            "    <div style=\"display:flex; marginTop:12px; gap:8px\">\n"+
            "      <div style=\"width:20%; background:linear-gradient(135deg, #4CAF50 0%, #81C784 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n"+
            "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">AAA</div>\n"+
            "        <div style=\"font-size:10px\">信用等级</div>\n"+
            "      </div>\n"+
            "      <div style=\"width:20%; background:linear-gradient(135deg, #FF9800 0%, #F57C00 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n"+
            "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">高新</div>\n"+
            "        <div style=\"font-size:10px\">高新技术企业</div>\n"+
            "      </div>\n"+
            "      <div style=\"width:20%; background:linear-gradient(135deg, #1976D2 0%, #0D47A1 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n"+
            "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">专精特新</div>\n"+
            "        <div style=\"font-size:10px\">小巨人企业</div>\n"+
            "      </div>\n"+
            "      <div style=\"width:20%; background:linear-gradient(135deg, #9C27B0 0%, #BA68C8 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n"+
            "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">水电</div>\n"+
            "        <div style=\"font-size:10px\">双软认证企业</div>\n"+
            "      </div>\n"+
            "    </div>\n"+
            "  </div>");
        config.

setTemplateConfig(templateConfig);

JReader fileReader = new JReSourceFileReader("report.txt");
JAdaptor adaptor = new JAdaptor(fileReader);
String svg = "  <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"500\" height=\"450\" viewBox=\"0 0 500 450\">\n" +
        "                <!-- 雷达图背景 -->\n" +
        "                <g transform=\"translate(250, 200)\">\n" +
        "                    <!-- 绘制5层同心六边形 -->\n" +
        "                    <polygon points=\"0,-150 129.9,-75 129.9,75 0,150 -129.9,75 -129.9,-75\" \n" +
        "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
        "                    <polygon points=\"0,-120 103.9,-60 103.9,60 0,120 -103.9,60 -103.9,-60\" \n" +
        "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
        "                    <polygon points=\"0,-90 77.9,-45 77.9,45 0,90 -77.9,45 -77.9,-45\" \n" +
        "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
        "                    <polygon points=\"0,-60 51.9,-30 51.9,30 0,60 -51.9,30 -51.9,-30\" \n" +
        "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
        "                    <polygon points=\"0,-30 25.9,-15 25.9,15 0,30 -25.9,15 -25.9,-15\" \n" +
        "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
        "                    \n" +
        "                    <!-- 坐标轴 -->\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-160\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"129.9\" y2=\"-75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"129.9\" y2=\"75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"160\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"-129.9\" y2=\"75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    <line x1=\"0\" y1=\"0\" x2=\"-129.9\" y2=\"-75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
        "                    \n" +
        "                    <!-- 维度标签 -->\n" +
        "                    <text x=\"0\" y=\"-180\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">偿债能力</text>\n" +
        "                    <text x=\"145\" y=\"-75\" text-anchor=\"start\" font-size=\"12\" fill=\"#2c3e50\">盈利能力</text>\n" +
        "                    <text x=\"145\" y=\"85\" text-anchor=\"start\" font-size=\"12\" fill=\"#2c3e50\">运营能力</text>\n" +
        "                    <text x=\"0\" y=\"190\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">成长能力</text>\n" +
        "                    <text x=\"-145\" y=\"85\" text-anchor=\"end\" font-size=\"12\" fill=\"#2c3e50\">现金流</text>\n" +
        "                    <text x=\"-145\" y=\"-75\" text-anchor=\"end\" font-size=\"12\" fill=\"#2c3e50\">信用历史</text>\n" +
        "                    \n" +
        "                    <!-- 刻度标签 -->\n" +
        "                    <text x=\"5\" y=\"-150\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">100</text>\n" +
        "                    <text x=\"5\" y=\"-120\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">80</text>\n" +
        "                    <text x=\"5\" y=\"-90\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">60</text>\n" +
        "                    <text x=\"5\" y=\"-60\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">40</text>\n" +
        "                    <text x=\"5\" y=\"-30\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">20</text>\n" +
        "                    \n" +
        "                    <!-- 当前企业数据 -->\n" +
        "                    <polygon points=\"0,-135 116.9,-67.5 103.9,60 0,120 -103.9,67.5 -116.9,-67.5\" \n" +
        "                             fill=\"#3498db\" fill-opacity=\"0.2\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    \n" +
        "                    <!-- 数据点 -->\n" +
        "                    <circle cx=\"0\" cy=\"-135\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    <circle cx=\"116.9\" cy=\"-67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    <circle cx=\"103.9\" cy=\"60\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    <circle cx=\"0\" cy=\"120\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    <circle cx=\"-103.9\" cy=\"67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    <circle cx=\"-116.9\" cy=\"-67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
        "                    \n" +
        "                    <!-- 数据值标签 -->\n" +
        "                    <text x=\"0\" y=\"-145\" text-anchor=\"middle\" font-size=\"10\" fill=\"#3498db\">90</text>\n" +
        "                    <text x=\"127\" y=\"-67.5\" text-anchor=\"start\" font-size=\"10\" fill=\"#3498db\">85</text>\n" +
        "                    <text x=\"114\" y=\"70\" text-anchor=\"start\" font-size=\"10\" fill=\"#3498db\">80</text>\n" +
        "                    <text x=\"0\" y=\"140\" text-anchor=\"middle\" font-size=\"10\" fill=\"#3498db\">75</text>\n" +
        "                    <text x=\"-114\" y=\"77\" text-anchor=\"end\" font-size=\"10\" fill=\"#3498db\">88</text>\n" +
        "                    <text x=\"-127\" y=\"-67.5\" text-anchor=\"end\" font-size=\"10\" fill=\"#3498db\">92</text>\n" +
        "                </g>\n" +
        "                \n" +
        "                <!-- 标题 -->\n" +
        "                <text x=\"250\" y=\"30\" text-anchor=\"middle\" font-size=\"16\" font-weight=\"bold\" fill=\"#2c3e50\">\n" +
        "                    科技有限公司信用评级\n" +
        "                </text>\n" +
        "                \n" +
        "                <!-- 评级说明 -->\n" +
        "                <text x=\"250\" y=\"380\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">\n" +
        "                    综合信用评分: 85/100 | 评级: AA\n" +
        "                </text>\n" +
        "            </svg>";
JContext param = new JContext();
        param.

put("svg",svg);

JQuickPdfXExecutor executor = new JQuickPdfXExecutor(param, config);
        executor.

execute(adaptor.getRuleContent());
```

![Demo](./pay/demo.png)

# **捐献 ☕**

感谢您使用这个开源项目！它完全免费并将持续维护，但开发者确实需要您的支持。

---

## **如何支持我们**

1. **请我喝杯咖啡**  
   果这个项目为您节省了时间或金钱，请考虑通过小额捐赠支持我。

2. **您的捐赠用途**

- 维持项目运行的服务器成本.
- 开发新功能以提供更多价值.
- 优化文档以提升用户体验.

3. **每一分都很重要**  
   即使是1分钱的捐赠也能激励我熬夜调试！

## **为什么捐赠?**

✔️ 保持项目永远免费且无广告.  
✔️ 支持及时响应问题和社区咨询.  
✔️ 实现计划中的未来功能.

感谢您成为让开源世界更美好的伙伴！

--- 

### **补充说明**

- 本项目和产品维护.
- 您的支持确保其可持续性和成长 .

---

## **🌟 立即支持**

赞助时欢迎通过 [email](mailto:goudingcheng@gmail.com) 留言。您的名字将被列入项目README文件的 **"特别感谢"** 名单中！
![Ali Pay](./pay/alipay.jpg)
![Wechat Pay](./pay/wechat.jpg)

---
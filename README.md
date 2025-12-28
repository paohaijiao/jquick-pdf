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
| WordsCloud            | 词云(1.5.3)         |
```string 
// ============================================================================
# 1.柱状图  BAR chart
// ============================================================================
```
<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 48%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>柱状图数据</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
          <code class="language-java">// 1. 创建图表配置
          JOption option = new JOption();
          option.title().text("销售数据")
          .subtext("2023年度");
          option.tooltip().trigger(JTrigger.axis);
          // 2. 配置坐标轴
          JCategoryAxis xAxis = new JCategoryAxis();
          xAxis.data("衬衫", "羊毛衫", "雪纺衫", 
          "裤子", "高跟鞋", "袜子");
          option.xAxis(xAxis);
          option.yAxis(new JValueAxis());
          // 3. 配置数据系列
          JBar bar = new JBar();
          bar.name("销量").data(5, 20, 36, 10, 10, 20);
          option.series(bar);</code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/barchart.svg"alt="销售数据柱状图" style="width: 100%; max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">柱状图数据</div>
    </td>
  </tr>
</table>

```string 
// ============================================================================
# 箱线图  BOXPLOT chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>箱线图数据</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
          <code class="language-java">
          // 1. 创建图表配置
           JOption option = new JOption();
           option.title().text("销售数据分布");
           option.xAxis(new JCategoryAxis().data("一季度", 
           "二季度", "三季度", "四季度"));
           option.series(new JBoxplot().data(
              new Object[]{10, 15, 20, 25, 30},
              new Object[]{12, 18, 22, 28, 35},
              new Object[]{8, 14, 19, 26, 32},
              new Object[]{11, 16, 21, 27, 33}
           ));
          </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/boxchart.svg" alt="箱线图数据" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">箱线图数据</div>
    </td>
  </tr>
</table>



```string 
// ============================================================================
# HEATMAP  热力图 chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>热力图数据</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
          <code class="language-java">
          // 1. 创建图表配置
           JOption option = new JOption();
           option.title().text("销售数据分布");
           option.xAxis(new JCategoryAxis().data("一季度", 
           "二季度", "三季度", "四季度"));
           option.series(new JBoxplot().data(
              new Object[]{10, 15, 20, 25, 30},
              new Object[]{12, 18, 22, 28, 35},
              new Object[]{8, 14, 19, 26, 32},
              new Object[]{11, 16, 21, 27, 33}
           ));
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
          new Object[]{0, 0, -5.2}, new Object[]{0, 1, -3.8}
          , new Object[]{0, 2, 1.5},
          new Object[]{0, 3, 4.2}, new Object[]{0, 4, 2.8}, 
          new Object[]{0, 5, -2.1},
          new Object[]{1, 0, -3.5}, new Object[]{1, 1, -1.2}
          , new Object[]{1, 2, 3.0},
          new Object[]{1, 3, 6.5}, new Object[]{1, 4, 4.2},
          new Object[]{1, 5, 0.5},
          new Object[]{2, 0, 0.8}, new Object[]{2, 1, 3.5},
          new Object[]{2, 2, 8.2},
          new Object[]{2, 3, 12.0}, new Object[]{2, 4, 9.5},
          new Object[]{2, 5, 4.2},
          new Object[]{3, 0, 5.2}, new Object[]{3, 1, 8.0},
          new Object[]{3, 2, 12.5},
          new Object[]{3, 3, 16.8}, new Object[]{3, 4, 14.2},
          new Object[]{3, 5, 9.5},
          new Object[]{4, 0, 10.5}, new Object[]{4, 1, 13.2}, 
          new Object[]{4, 2, 17.8},
          new Object[]{4, 3, 21.5}, new Object[]{4, 4, 19.0},
          new Object[]{4, 5, 14.8},
          new Object[]{5, 0, 15.2}, new Object[]{5, 1, 18.5},
          new Object[]{5, 2, 22.0},
          new Object[]{5, 3, 26.5}, new Object[]{5, 4, 24.2},
          new Object[]{5, 5, 19.8},
          new Object[]{6, 0, 18.5}, new Object[]{6, 1, 22.0},
          new Object[]{6, 2, 26.5},
          new Object[]{6, 3, 30.2}, new Object[]{6, 4, 28.5},
          new Object[]{6, 5, 23.8},
          new Object[]{7, 0, 17.8}, new Object[]{7, 1, 21.5},
          new Object[]{7, 2, 25.2},
          new Object[]{7, 3, 29.0}, new Object[]{7, 4, 27.5},
          new Object[]{7, 5, 22.8},
          new Object[]{8, 0, 13.5}, new Object[]{8, 1, 16.2},
          new Object[]{8, 2, 20.0},
          new Object[]{8, 3, 24.5}, new Object[]{8, 4, 22.0},
          new Object[]{8, 5, 17.5},
          new Object[]{9, 0, 8.2}, new Object[]{9, 1, 11.5},
          new Object[]{9, 2, 15.0},
          new Object[]{9, 3, 18.8}, new Object[]{9, 4, 16.5},
          new Object[]{9, 5, 12.0},
          new Object[]{10, 0, 2.5}, new Object[]{10, 1, 5.0},
          new Object[]{10, 2, 9.2},
          new Object[]{10, 3, 12.5}, new Object[]{10, 4, 10.0},
          new Object[]{10, 5, 5.5},
          new Object[]{11, 0, -2.8}, new Object[]{11, 1, -0.5},
          new Object[]{11, 2, 3.5},
          new Object[]{11, 3, 6.8}, new Object[]{11, 4, 4.2},
          new Object[]{11, 5, 0.0}
        );
    option.series(heatmap);
          </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/heatmap.svg" alt="热力图数据" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">热力图数据</div>
    </td>
  </tr>
</table>




```string 
// ============================================================================
# K线图(蜡烛图)  K chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>K线图(蜡烛图)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
          <code class="language-java">
        JOption option = new JOption();
        option.title().text("股票K线图(含数据)");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("01/01", "01/02", "01/03", "01/04", 
        "01/05","01/06", "01/07", "01/08", "01/09",
        "01/10");
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
          </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/k_chart.svg" alt="K线图(蜡烛图)" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">K线图(蜡烛图)</div>
    </td>
  </tr>
</table>


```string 
// ============================================================================
# 折线图  Line chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>折线图(Line chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
        JOption option = new JOption();
        option.title().text("销售数据折线图");
        option.tooltip().trigger(JTrigger.axis);
        JCategoryAxis xAxis = new JCategoryAxis();
        xAxis.data("1月", "2月", "3月", "4月", 
        "5月", "6月", "7月");
        option.xAxis(xAxis);
        option.yAxis(new JValueAxis());
        JLine line = new JLine();
        line.name("销售额").data(120, 132, 101,
        134, 90, 230, 210);
        option.series(line);
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/line_chart.svg" alt="折线图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">折线图</div>
    </td>
  </tr>
</table>



```string 
// ============================================================================
# 饼图  PIE chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>饼图(PIE chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
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
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/pie-chart.svg" alt="饼图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">饼图</div>
    </td>
  </tr>
</table>



```string 
// ============================================================================
# 雷达图  RADAR chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>雷达图(RADAR chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
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
        new JRadar.Indicator().name("销售")
        .max(6500),
        new JRadar.Indicator().name("管理")
        .max(16000),
        new JRadar.Indicator().name("信息技术")
        .max(30000),
        new JRadar.Indicator().name("客服")
        .max(38000),
        new JRadar.Indicator().name("研发")
        .max(52000),
        new JRadar.Indicator().name("市场")
        .max(25000)
        );
        option.radar(radar);
        // 添加雷达图系列数据
        JRadarSeries budgetSeries = new JRadarSeries();
        budgetSeries.name("预算").type(JSeriesType.radar)
        .data(4300, 10000, 28000, 35000, 50000, 19000);
        JRadarSeries actualSeries = new JRadarSeries();
        actualSeries.name("实际开销")
        .type(JSeriesType.radar)
        .data(5000, 14000, 28000, 31000, 42000, 21000);
        option.series(budgetSeries, actualSeries);
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/radar_chart.svg" alt="雷达图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">雷达图</div>
    </td>
  </tr>
</table>

```string 
// ============================================================================
# 关系图  RELATION chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>关系图(RELATION chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
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
        nodes.add(new JNode("1", "Node A")
        .symbolSize(30).category(0));//id 1
        nodes.add(new JNode("2", "Node B")
        .symbolSize(25).category(1));
        nodes.add(new JNode("3", "Node C")
        .symbolSize(20).category(2));
        nodes.add(new JNode("4", "Node D")
        .symbolSize(15).category(0));
        nodes.add(new JNode("5", "Node E")
        .symbolSize(35).category(1));
        nodes.add(new JNode("6", "Node F")
        .symbolSize(20).category(3));
        nodes.add(new JNode("7", "Node G")
        .symbolSize(25).category(2));
        nodes.add(new JNode("8", "Node H")
        .symbolSize(15).category(4));
        nodes.add(new JNode("9", "Node I")
        .symbolSize(30).category(3));
        nodes.add(new JNode("10", "Node J")
        .symbolSize(20).category(0));
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
        List<JCategory> categories =
        new ArrayList<>();
        categories.add(new JCategory()
        .name("Category 1"));
        categories.add(new JCategory()
        .name("Category 2"));
        categories.add(new JCategory()
        .name("Category 3"));
        categories.add(new JCategory()
        .name("Category 4"));
        categories.add(new JCategory()
        .name("Category 5"));
        graph.setCategories(categories);
        option.series(graph);
        option.legend().data("Category 1",
        "Category 2", "Category 3", "Category 4",
        "Category 5");
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/relation_chart.svg" alt="关系图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">关系图</div>
    </td>
  </tr>
</table>

```string 
// ============================================================================
# 散点图  SCATTER chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>散点图(SCATTER chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
      JData[] data = new JData[]{
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
      scatter.symbolSize(20).data(data);
      option.series(scatter);
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/scatter.svg" alt="散点图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">散点图</div>
    </td>
  </tr>
</table>

```string 
// ============================================================================
# 旭日图(1.5.1)  SUNBURST chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>旭日图(SUNBURST chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
        JOption option = new JOption();
        // 设置标题
        JTitle title = new JTitle();
        title.setText("咖啡风味分析");
        option.setTitle(title);
        JSunburstData root = 
        new JSunburstData("总数据", 1.0);
        JSunburstData main1 = 
        new JSunburstData("电子产品", 0.4);
        JSunburstData main2 = 
        new JSunburstData("服装", 0.3);
        JSunburstData main3 = 
        new JSunburstData("食品", 0.3);
        // 第二层：子分类
        JSunburstData main1Sub1 = 
        new JSunburstData("手机", 0.6);
        JSunburstData main1Sub2 =
        new JSunburstData("电脑", 0.4);
        JSunburstData main2Sub1 =
        new JSunburstData("男装", 0.5);
        JSunburstData main2Sub2 = 
        new JSunburstData("女装", 0.5);
        JSunburstData main3Sub1 =
        new JSunburstData("生鲜", 0.4);
        JSunburstData main3Sub2 =
        new JSunburstData("零食", 0.6);
        // 第三层：孙分类
        main1Sub1.addChild(new
          JSunburstData("智能手机", 0.7));
        main1Sub1.addChild(new 
          JSunburstData("功能手机", 0.3));
        main1Sub2.addChild(new 
          JSunburstData("笔记本电脑", 0.6));
        main1Sub2.addChild(new 
          JSunburstData("台式电脑", 0.4));
        main2Sub1.addChild(new 
          JSunburstData("衬衫", 0.4));
        main2Sub1.addChild(new 
          JSunburstData("裤子", 0.6));
        main3Sub2.addChild(new 
          JSunburstData("膨化食品", 0.5));
        main3Sub2.addChild(new 
          JSunburstData("糖果", 0.5));
        main1.addChild(main1Sub1);
        main1.addChild(main1Sub2);
        main2.addChild(main2Sub1);
        main2.addChild(main2Sub2);
        main3.addChild(main3Sub1);
        main3.addChild(main3Sub2);
        root.addChild(main1);
        root.addChild(main2);
        root.addChild(main3);
        option.setSunburstData(root);
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/sunburst.svg" alt="旭日图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">旭日图</div>
    </td>
  </tr>
</table>


```string 
// ============================================================================
# 矩形树图(1.5.1)  Treemap chart
// ============================================================================
```

<table style="width: 100%; border: none; border-collapse: collapse;">
  <tr>
    <td style="width: 30%; vertical-align: middle; padding-right: 2%; border: none;">
      <strong>矩形树图(Treemap chart)</strong><br>
      <pre style="background: #f5f5f5; padding: 10px; border-radius: 4px; font-size: 0.9em; overflow-x: auto;">
      <code class="language-java">
        JTreeMapNode root = createTestData();
        TreeMapOption treemapOption = new TreeMapOption();
        treemapOption.setRoot(root);
        treemapOption.setDepartmentColors(DEPARTMENT_COLORS);
        treemapOption.setCategoryColors(CATEGORY_COLORS);
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("开发", "技术部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("项目", "技术部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("服务", "技术部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("会计", "财务部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("预算", "财务部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("税务", "财务部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("审计", "财务部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("销售", "销售部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("区域", "销售部"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("招聘", "人力资源"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("培训", "人力资源"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("薪酬", "人力资源"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("员工", "人力资源"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("营销", "市场营销"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("品牌", "市场营销"));
        treemapOption.getDepartmentRules()
        .add(new TreeMapMapping("公关", "市场营销"));
        JOption option = new JOption();
        option.setTreemapOption(treemapOption);
        option.title("公司业务分布矩形树图（JTreemapRenderer）");
       </code>
      </pre>
    </td>
    <td style="width: 48%; vertical-align: middle; text-align: center; border: none;">
      <img src="./images/treemap.svg" alt="矩形树图" style="width: 100%; min-width: 400px ;max-width: 400px !important; height: auto;">
      <div style="font-size: 0.9em; color: #666; margin-top: 10px;">矩形树图</div>
    </td>
  </tr>
</table>

```string 
// ============================================================================
# 气泡图(1.5.1)  Bubble chart
// ============================================================================
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
// ============================================================================
# 日历(1.5.3)  Lunar chart
// ============================================================================
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
// ============================================================================
# 漏斗图(1.5.3)  Funnel chart
// ============================================================================
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
// ============================================================================
# 相关系数矩阵(1.5.3)  CorrectionMatrix chart
// ============================================================================
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
// ============================================================================
# 甘特图(1.5.3)  Gantt chart
// ============================================================================
        JGanttOption option = new JGanttOption();
        option.setTitle(new JGanttOption.Title("Gantt of Airport Flight", "航班调度甘特图"));
        option.setFlightData(Arrays.asList(
                    new JGanttOption.FlightData("Y3683", "681", "X", 21, 0, 360, 0, 0.7),
                    new JGanttOption.FlightData("EKXAD", "682I", "W", 21, 0, 360, 1, 0.7),
                    new JGanttOption.FlightData("Y4682", "682O", "W", 21, 0, 360, 2, 0.7),
                    new JGanttOption.FlightData("Y4393", "682", "X", 21, 0, 360, 3, 0.7),
                    new JGanttOption.FlightData("Y2238", "683", "X", 21, 0, 360, 4, 0.7),
                    new JGanttOption.FlightData("Y8192", "684", "W", 21, 0, 240, 5, 0.7),
                    new JGanttOption.FlightData("Y3887", "685", "X", 21, 0, 360, 6, 0.7),
                    new JGanttOption.FlightData("Y3086", "690", "X", 21, 0, 360, 7, 0.7),
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
// ============================================================================
# 仪表盘(1.5.3)  Gauge chart
// ============================================================================
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
// ============================================================================
# 词云(1.5.3)  WordsCloud chart
// ============================================================================
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
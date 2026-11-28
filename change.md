# Changelog

所有代码变更记录将在此文档中维护，遵循 [语义化版本 2.0.0](https://semver.org/) 规范。


## [1.5.1] - 2025-11-5

### 新增功能

- 优化 `旭日图`
- 实现 `Treemap`
- 实现 `Bubble chart`

## [1.5.3] - 2025-11-16

### 新增功能

- 新增 `日历活动统计`
- 新增 `日历Lunar`
- 新增 `Funnel`
- 新增 `CorrectionMatrix`
- 新增 `Gantt`
- 新增 `Gauge`

## [5.3.0] - 2026-11-28

### 新增功能

- 新增 `border-width`（borderWidth）：分侧边框线宽简写，按 CSS 规范支持 1~4 值展开（上 右 下 左），如 `border-width:'1px 2px 3px 4px'`
- 新增 `border-style`（borderStyle）：分侧边框线型简写，支持 solid / dashed / dotted / none，double / ridge / groove / inset / outset 暂降级为实线
- 启用 `border-color`（borderColor）渲染：分侧边框颜色简写，1~4 个颜色值按上右下左展开，支持 #hex / rgb() / rgba() / 颜色名
- 新增 `box-shadow`（boxShadow）：盒阴影，语法 `x偏移 y偏移 [blur] [spread] 颜色 [, 多组]`，支持 inset 内阴影与 rgba alpha 通道；外阴影以多层透明度叠加模拟模糊，内阴影用 even-odd 裁剪生成边缘暗带
- 新增 `radius`：border-radius 的短别名，渲染优先级 border-radius > radius > 四角值
- 边框渲染重构：分侧简写按 CSS 优先级叠加（边框简写 > border-width/border-style/border-color 展开 > 内置默认值）；仅声明宽度/颜色未声明线型时按实线绘制

### 完善（既有属性增强）

- `table` 表格新增 `colspan` / `rowspan` 单元格属性：支持 `colspan='2'` / `rowspan=3`（带引号字符串与裸数字两种写法），非法或非正数值回退 1；渲染器重构为占位网格布局——后续行自动跳过被跨列/跨行占用的格子，跨行单元格整块绘制并垂直居中，分页时整块预留不截断；
- `border-radius`（borderRadius）：既有圆角属性，本次纳入统一圆角解析链（border-radius > radius > 四角值），背景填充与 box-shadow 均按其裁形
- `border-top` / `border-right` / `border-bottom` / `border-left`：既有分侧边框简写（`线宽 线型 颜色` 任意顺序），本次与 border-width/border-style/border-color 分侧展开打通——每条边先取自身简写，缺失项依次回退到分侧简写对应值、strokeColor 与内置默认，与 border 简写可任意叠加

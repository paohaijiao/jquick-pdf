/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.font.JFontSpec;
import lombok.Data;

/**
 * 水印配置。
 *
 * <p>支持两种水印内容（二选一，同时配置时优先图片）：</p>
 * <ul>
 *   <li>文字水印 —— {@link #watermarkText}，支持动态注入（如密级、用户名、文档号）</li>
 *   <li>图片水印 —— {@link #imagePath}（文件系统路径或 {@code classpath:xxx}）</li>
 * </ul>
 *
 * <p>排布方式：</p>
 * <ul>
 *   <li>单枚居中（默认）—— {@link #tiled} = false，页面中心绘制一枚旋转水印</li>
 *   <li>对角平铺 —— {@link #tiled} = true，按 {@link #tileGapX}/{@link #tileGapY} 平铺整页</li>
 * </ul>
 *
 * <p>按页规则见 {@link JWatermarkPageRule}（首页不水印、仅正文、奇偶页分开等）。</p>
 *
 * <p>所有字段均可通过 {@code JPdfConfig.getWatermarkConfig()} 配置；
 * 动态值（如密级水印文字）建议由业务侧 bind 参数后写入 {@link #watermarkText}。</p>
 */
@Data
public class JWaterRemarkConfig {

    /** 是否启用水印。 */
    private Boolean enabled = false;

    /** 文字水印内容，支持动态注入（如 "${watermarkText}" 对应的业务值）。 */
    private String watermarkText = "水印";

    /** 水印字体，默认使用内置中文字体。 */
    private JFontSpec font = new JFontSpec();

    /** 水印透明度（0.0 ~ 1.0），越小越淡。 */
    private Float fillOpacity = 0.3f;

    /** 文字水印字号（pt），仅文字水印生效。 */
    private Float fontSize = 60f;

    /** 旋转角度（度，逆时针），默认 45° 对角。 */
    private Float rotationDegrees = 45f;

    /** 是否对角平铺整页；false 时仅在页面中心绘制一枚。 */
    private Boolean tiled = false;

    /** 平铺时相邻水印的横向间距（pt），仅平铺模式生效。 */
    private Float tileGapX = 150f;

    /** 平铺时相邻水印的纵向间距（pt），仅平铺模式生效。 */
    private Float tileGapY = 150f;

    /** 图片水印来源：文件系统路径或 {@code classpath:前缀} 资源路径；为空时使用文字水印。 */
    private String imagePath = null;

    /** 图片水印宽度占页面宽度的比例（0.0 ~ 1.0），默认 0.4，等比缩放。 */
    private Float imageScale = 0.4f;

    /** 按页生效规则：全部 / 首页不水印 / 仅首页 / 仅奇数页 / 仅偶数页。 */
    private JWatermarkPageRule pageRule = JWatermarkPageRule.ALL;
}

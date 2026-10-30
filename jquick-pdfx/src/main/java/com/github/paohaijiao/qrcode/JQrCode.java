/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.github.paohaijiao.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;

/**
 * 二维码渲染工具：把业务内容（文档编号、防篡改哈希、归档地址、验签地址等）渲染为二维码。
 *
 * <p>两种输出：</p>
 * <ul>
 *   <li>{@link #toSvg(String, int, int, String, ErrorCorrectionLevel)} —— 矢量 SVG 字符串，
 *       可直接绑定到模板变量，经 {@code <svg>${qr}</svg>} 嵌入文档（推荐，无栅格损失）；</li>
 *   <li>{@link #toPng(String, int, int)} —— PNG 字节数组，可用于文件水印或独立输出。</li>
 * </ul>
 *
 * <p>典型业务用法：</p>
 * <pre>{@code
 * String content = "docId=" + docId + "&hash=" + docHash + "&archive=" + archiveUrl;
 * factory.bind("qr", JQrCode.toSvg(content, 180, 2, "#000000"));
 * }</pre>
 */
public final class JQrCode {

    private JQrCode() {
    }

    /** 默认尺寸 200px、静区 2 模块、黑色、纠错级别 M。 */
    public static String toSvg(String content) {
        return toSvg(content, 200, 2, "#000000", ErrorCorrectionLevel.M);
    }

    /**
     * 渲染二维码为 SVG 字符串。
     *
     * @param content              二维码内容（文档编号、哈希、归档 URL 等业务数据）
     * @param size                 目标边长（像素，含静区）
     * @param margin               静区宽度（模块数，建议 2~4）
     * @param darkColor            深色模块颜色，如 {@code "#000000"} 或 {@code "#1a1a1a"}
     * @param errorCorrectionLevel 纠错级别 L/M/Q/H（H 最强，可修复约 30% 损毁）
     * @return 自包含的 SVG 文本，可直接绑定到模板 {@code ${qr}} 变量
     * @throws IllegalStateException 编码失败（内容过长等）
     */
    public static String toSvg(String content, int size, int margin, String darkColor,
                               ErrorCorrectionLevel errorCorrectionLevel) {
        BitMatrix matrix = encode(content, size, margin, errorCorrectionLevel);
        int dimension = matrix.getWidth();
        StringBuilder path = new StringBuilder();
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (matrix.get(x, y)) {
                    path.append('M').append(x).append(',').append(y).append("h1v1h-1z");
                }
            }
        }
        StringBuilder svg = new StringBuilder();
        svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\"")
                .append(" width=\"").append(dimension).append('"')
                .append(" height=\"").append(dimension).append('"')
                .append(" viewBox=\"0 0 ").append(dimension).append(' ').append(dimension).append('"')
                .append(" shape-rendering=\"crispEdges\">")
                .append("<rect width=\"100%\" height=\"100%\" fill=\"#ffffff\"/>")
                .append("<path d=\"").append(path).append("\" fill=\"")
                .append(darkColor == null || darkColor.isEmpty() ? "#000000" : darkColor)
                .append("\"/></svg>");
        return svg.toString();
    }

    /** 渲染二维码 PNG 字节（默认尺寸 300、静区 3）。 */
    public static byte[] toPng(String content) throws IOException {
        return toPng(content, 300, 3, ErrorCorrectionLevel.M);
    }

    /**
     * 渲染二维码为 PNG 字节数组。
     *
     * @param content              二维码内容
     * @param size                 目标边长（像素）
     * @param margin               静区宽度（模块数）
     * @param errorCorrectionLevel 纠错级别
     * @return PNG 字节
     */
    public static byte[] toPng(String content, int size, int margin,
                               ErrorCorrectionLevel errorCorrectionLevel) throws IOException {
        BitMatrix matrix = encode(content, size, margin, errorCorrectionLevel);
        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", buffer);
        return buffer.toByteArray();
    }

    private static BitMatrix encode(String content, int size, int margin,
                                    ErrorCorrectionLevel errorCorrectionLevel) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("qrcode content must not be blank");
        }
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
        hints.put(EncodeHintType.MARGIN, Math.max(0, margin));
        if (errorCorrectionLevel != null) {
            hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        }
        try {
            return new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        } catch (WriterException e) {
            throw new IllegalStateException("encode qrcode failed: " + e.getMessage(), e);
        }
    }
}

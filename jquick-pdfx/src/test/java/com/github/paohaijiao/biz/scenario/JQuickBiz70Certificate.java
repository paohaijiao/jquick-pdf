/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.biz.scenario;

import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.executor.JQuickPdfFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 70：培训结业证书 PDF（单页、居中大标题、预留盖章位，模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件 {@code report/biz/70_certificate.txt} 中。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/70_certificate.pdf}</li>
 *   <li>logo / 公章：{@code D:/test/image/70_logo.png}、{@code D:/test/image/70_seal.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz70Certificate {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "70_certificate.pdf";
    private static final String TEMPLATE = "report/biz/70_certificate.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 图片：logo + 公章 ----------
        generateLogo(IMG_DIR + "70_logo.png");
        generateSeal(IMG_DIR + "70_seal.png", "培训中心");

        // ---------- 2. 数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("studentName", "林书言");
        data.put("idCard", "3101**********1234");
        data.put("courseName", "企业级 PDF 报表引擎开发实战");
        data.put("duration", "48 学时（6 周）");
        data.put("graduateDate", "2026年10月18日");
        data.put("issuer", "××职业培训中心");
        data.put("certNo", "PX-2026-1018-0086");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(40, 40, 40, 40));
        // 单页证书不需要页码
        config.getFooterConfig().setEnabled(false);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    /** 生成 Logo 占位图：圆形底 + 中心字母。 */
    private static void generateLogo(String path) throws IOException {
        BufferedImage image = new BufferedImage(112, 112, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0x8C, 0x6D, 0x3F));
        g.fillOval(4, 4, 104, 104);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 44));
        g.drawString("培", 26, 72);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /**
     * 生成圆形公章占位图：红色圆环 + 环形单位名 + 中心五角星。
     */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 220;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(7f));
        g.drawOval(12, 12, size - 24, size - 24);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(24, 24, size - 48, size - 48);
        // 环形文字：逐字旋转，沿上半圆排布
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int radius = 78;
        char[] chars = orgName.toCharArray();
        double startAngle = Math.PI * 1.22;
        double step = Math.PI * 0.56 / Math.max(1, chars.length - 1);
        for (int i = 0; i < chars.length; i++) {
            double angle = startAngle + step * i;
            int cx = (int) (size / 2 + radius * Math.cos(angle));
            int cy = (int) (size / 2 + radius * Math.sin(angle));
            AffineTransform old = g.getTransform();
            g.rotate(angle + Math.PI / 2, cx, cy);
            g.drawString(String.valueOf(chars[i]), cx - fm.stringWidth(String.valueOf(chars[i])) / 2, cy + 7);
            g.setTransform(old);
        }
        // 中心五角星
        int[] xs = new int[10];
        int[] ys = new int[10];
        double inner = 22;
        double outer = 46;
        for (int i = 0; i < 10; i++) {
            double a = -Math.PI / 2 + i * Math.PI / 5;
            double r = (i % 2 == 0) ? outer : inner;
            xs[i] = (int) (size / 2 + r * Math.cos(a));
            ys[i] = (int) (size / 2 + r * Math.sin(a));
        }
        g.fillPolygon(xs, ys, 10);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString("专用章", size / 2 - 21, size / 2 + 70);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

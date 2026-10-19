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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 84：医疗器械产品合格证（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/84_medical_certificate.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/84_medical_certificate.pdf}；
 * 质检合格章 {@code D:/test/image/84_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz84MedicalCertificate {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "84_medical_certificate.pdf";
    private static final String TEMPLATE = "report/biz/84_medical_certificate.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "84_seal.png");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("certNo", "HC-2026-B0912-0186");
        data.put("productName", "一次性使用无菌注射器（带针）");
        data.put("model", "5ml　0.6×25TWLB");
        data.put("batchNo", "20260912-A03");
        data.put("produceDate", "2026年09月12日");
        data.put("validUntil", "2029年09月11日");
        data.put("manufacturer", "××医疗器械制造有限公司");
        data.put("licenseNo", "苏食药监械生产许 2006XXXX 号");
        data.put("address", "××省××市××区××路 66 号");
        data.put("inspector", "王丽");

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        // 上下留白多、左右收窄，让内容呈「卡片」居中效果
        config.getDoc().setMargins(Arrays.asList(70, 78, 70, 78));
        config.getFooterConfig().setEnabled(false);

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);

        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    private static void mkdirs() {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
    }

    private static void generateSeal(String path) throws IOException {
        int size = 180;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 19));
        g.drawString("质检合格", size / 2 - 44, size / 2 - 4);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
        g.drawString("QC PASSED", size / 2 - 32, size / 2 + 22);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

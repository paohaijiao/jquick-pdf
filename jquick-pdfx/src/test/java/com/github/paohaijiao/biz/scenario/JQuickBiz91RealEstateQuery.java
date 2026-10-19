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
 * 场景 91：不动产登记信息查询结果告知单（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/91_real_estate_query.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/91_real_estate_query.pdf}；
 * 查询专用章 {@code D:/test/image/91_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz91RealEstateQuery {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "91_real_estate_query.pdf";
    private static final String TEMPLATE = "report/biz/91_real_estate_query.txt";
    private static final String SEAL = IMG_DIR + "91_seal.png";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
        generateSeal(SEAL);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("queryNo", "BDC-CX-20261019-00486");
        data.put("queryPerson", "陈嘉禾");
        data.put("idNo", "3101**********1234");
        data.put("location", "××市浦东新区××路 88 弄 12 号 1802 室");
        data.put("unitNo", "310115 008001 GB00012 F00010186");
        // 权证信息拆为 4 个单元格变量（引擎不支持 colspan，模板按 2×2 展示）
        data.put("rightsInfo1", "沪（2021）浦字不动产权第 0123456 号");
        data.put("rightsInfo2", "2021-08-16");
        data.put("rightsInfo3", "单独所有");
        data.put("rightsInfo4", "2019-03-01 至 2089-02-28");
        data.put("restrictionInfo", "除上述抵押登记外，未查询到查封、异议、预告等其他限制登记信息。");
        data.put("queryTime", "2026-10-19 14:20:31");
        data.put("issueOrg", "××市不动产登记中心");
        data.put("issueDate", "2026-10-19");

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(58, 62, 58, 62));
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        FileOutputStream out = new FileOutputStream(OUT_FILE);
        out.write(pdf);
        out.close();
        System.out.println("已生成：" + OUT_FILE);
    }

    private static void generateSeal(String path) throws IOException {
        int size = 210;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.drawString("××市不动产登记中心", 18, 60);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        g.drawString("查询专用章", size / 2 - 40, size / 2 + 30);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

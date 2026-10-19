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
 * 场景 87：中标通知书（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/87_award_notice.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/87_award_notice.pdf}；
 * 招标单位公章 {@code D:/test/image/87_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz87AwardNotice {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "87_award_notice.pdf";
    private static final String TEMPLATE = "report/biz/87_award_notice.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "87_seal.png");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("noticeNo", "中标通知〔2026〕第 ZB-1124-03 号");
        data.put("projectName", "××市智慧交通数据中台建设项目");
        data.put("tenderer", "××市交通运输局");
        data.put("winner", "××数字科技有限公司");
        data.put("amount", "3,180,000.00");
        data.put("amountWords", "叁佰壹拾捌万元整");
        data.put("duration", "120 个日历日");
        data.put("projectManager", "王芳（高级项目经理）");
        data.put("issueDate", "2026年10月19日");
        data.put("tenderNo", "ZB-2026-1124");

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(62, 68, 58, 68));
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
        int size = 210;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("××市交通运输局", size / 2 - 56, 62);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
        g.drawString("公章", size / 2 - 17, size / 2 + 30);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

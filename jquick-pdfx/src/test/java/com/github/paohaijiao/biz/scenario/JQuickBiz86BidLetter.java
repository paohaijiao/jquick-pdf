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
 * 场景 86：投标文件-投标函（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/86_bid_letter.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/86_bid_letter.pdf}；
 * 公章 {@code D:/test/image/86_seal.png}；法人签字 {@code D:/test/image/86_sign.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz86BidLetter {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "86_bid_letter.pdf";
    private static final String TEMPLATE = "report/biz/86_bid_letter.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "86_seal.png");
        generateSignature(IMG_DIR + "86_sign.png", "陈立");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("projectName", "××市智慧交通数据中台建设项目");
        data.put("tenderNo", "ZB-2026-1124");
        data.put("bidder", "××数字科技有限公司");
        data.put("bidAmount", "3,180,000.00");
        data.put("bidAmountWords", "叁佰壹拾捌万元整");
        data.put("duration", "合同签订后 120 个日历日内完成建设并上线");
        data.put("validPeriod", "自投标截止之日起 90 个日历日");
        data.put("legalPerson", "陈立");
        data.put("legalIdNo", "3101**********0017");
        data.put("authorizedAgent", "王芳（项目经理）");
        data.put("tenderer", "××市交通运输局");
        data.put("bidDate", "2026年10月19日");
        // 承诺条款逐条 bind：commitment1~5
        String[] commitments = {
                "我方投标文件所载全部资料真实、准确、完整，如有虚假，愿承担相应法律责任；",
                "我方具有履行合同所必需的技术能力、人员配置与财务能力；",
                "我方承诺不与其他投标人串通报价，不以任何方式干扰招标活动；",
                "中标后按招标文件要求及投标承诺签订合同，不擅自变更报价与工期；",
                "项目交付后提供 12 个月免费质保，质保期内 2 小时响应、24 小时到场。"};
        for (int i = 0; i < commitments.length; i++) {
            data.put("commitment" + (i + 1), commitments[i]);
        }

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(56, 60, 55, 60));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("招标编号：ZB-2026-1124　|　投标函");
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(34);
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

    private static void mkdirs() {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
    }

    private static void generateSeal(String path) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("××数字科技", size / 2 - 38, 66);
        g.drawString("有限公司", size / 2 - 30, 90);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        g.drawString("公章", size / 2 - 16, size / 2 + 32);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(220, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-5), 110, 50);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 46));
        g.drawString(name, 46, 70);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

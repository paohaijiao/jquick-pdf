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
 * 场景 85：律师函（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/85_lawyer_letter.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/85_lawyer_letter.pdf}；
 * 律所公章 {@code D:/test/image/85_seal.png}；律师签章 {@code D:/test/image/85_sign.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz85LawyerLetter {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "85_lawyer_letter.pdf";
    private static final String TEMPLATE = "report/biz/85_lawyer_letter.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "85_seal.png");
        generateSignature(IMG_DIR + "85_sign.png", "沈知言");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("letterNo", "（2026）××律函字第 1026 号");
        data.put("lawFirm", "××市××律师事务所");
        data.put("lawyerName", "沈知言");
        data.put("lawyerLicense", "13101201610XXXXXX");
        data.put("recipientUnit", "××信息技术有限公司");
        data.put("recipientAddress", "××市××区××路 26 号 5 层");
        data.put("subject", "关于贵司拖欠《PDF 报表引擎采购合同》（HT-2026-0918）合同款项事宜");
        // 陈述条款逐条 bind：statement1~3
        String[] statements = {
                "2026 年 9 月 18 日，委托人与贵司签订《PDF 报表引擎采购合同》，约定由委托人向贵司提供 PDF 报表引擎企业版许可 5 套及相关实施服务，"
                        + "合同总金额为人民币 210,000 元，贵司应于合同签订后 5 个工作日内支付首期款 63,000 元。"
                        + "合同第 2 条约定的付款条件均已成就，委托人亦已于 2026 年 9 月 21 日完成全部交付并由贵司签收（回执编号 HZ-2026-1019-0043）。",
                "依据《中华人民共和国民法典》第五百零九条规定，当事人应当按照约定全面履行自己的义务。"
                        + "贵司在收到委托人于 2026 年 9 月 25 日、10 月 8 日两次书面催款通知后，仍以内部审批未完成为由拒不付款，已构成迟延履行。",
                "依据合同第 7 条约定，逾期付款应按未付金额每日 0.5‰ 支付违约金。截至本函发出之日，贵司逾期已达 24 日，"
                        + "应付违约金 756 元，且该违约金仍在持续计算中。"
                        + "另依据《民法典》第五百八十五条，委托人保留就实际损失超出违约金部分另行主张的权利。"};
        for (int i = 0; i < statements.length; i++) {
            data.put("statement" + (i + 1), statements[i]);
        }
        // 诉求逐条 bind：demand1~3
        String[] demands = {
                "请贵司于收到本函之日起 7 日内，向委托人支付首期合同款人民币 63,000 元及自 2026 年 9 月 26 日起的逾期违约金；",
                "请贵司在付款同时书面确认后续两张进度款的支付计划，避免同类违约再次发生；",
                "如贵司对上述事实或金额存在异议，请在上述期限内提供书面说明及相应证据，本所愿在平等基础上促成双方和解。"};
        for (int i = 0; i < demands.length; i++) {
            data.put("demand" + (i + 1), demands[i]);
        }
        data.put("issueDate", "2026年10月19日");
        data.put("contact", "联系人：沈知言 律师　电话：021-8888-0000　地址：××市××区××路 168 号 18 层");

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(58, 62, 60, 62));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("××市××律师事务所　律师函　（2026）××律函字第 1026 号");
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
        int size = 210;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.drawString("××市××律师事务所", 22, 60);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        g.drawString("律师事务所章", size / 2 - 54, size / 2 + 28);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(220, 104, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-3), 110, 52);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 44));
        g.drawString(name, 26, 72);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

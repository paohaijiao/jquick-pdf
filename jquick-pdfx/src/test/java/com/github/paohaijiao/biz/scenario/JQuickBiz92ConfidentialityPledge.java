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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 92：保密承诺书（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/92_confidentiality_pledge.txt} 中。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/92_confidentiality_pledge.pdf}；
 * 签名 / 公章 {@code D:/test/image/92_*.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz92ConfidentialityPledge {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "92_confidentiality_pledge.pdf";
    private static final String TEMPLATE = "report/biz/92_confidentiality_pledge.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();
        generateSignature(IMG_DIR + "92_sign.png", "许安");
        generateSeal(IMG_DIR + "92_seal.png", "××数字科技有限公司");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("pledgeNo", "BM-2026-0047");
        data.put("personName", "许安");
        data.put("department", "××数字科技有限公司 数据平台部");
        data.put("position", "资深 Java 后端工程师");
        data.put("signDate", "2026年10月19日");

        // 条款：逐条 bind 为 clauseTitle1~6 / clauseText1~6
        String[] clauseArr = buildClauses();
        for (int i = 0; i < clauseArr.length / 2; i++) {
            data.put("clauseTitle" + (i + 1), clauseArr[i * 2]);
            data.put("clauseText" + (i + 1), clauseArr[i * 2 + 1]);
        }

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(58, 62, 58, 62));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("保密承诺书　" + data.get("pledgeNo"));
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

    /**
     * 返回条款数组：偶数下标为条标题（含「第 N 条」前缀），奇数下标为正文。
     */
    private static String[] buildClauses() {
        List<String> clauses = new ArrayList<String>();
        clauses.add("保密信息的范围\n"
                + "本承诺所称保密信息，包括但不限于：公司技术架构文档、源代码、数据库结构与数据、测试用例、产品需求与技术方案、"
                + "客户名单与合同条款、报价与成本数据、财务与人事信息，以及通过会议、邮件、即时通讯等任何形式获知的未公开信息。");
        clauses.add("保密义务\n"
                + "本人承诺对保密信息严格保密，未经公司书面授权，不得以任何方式向任何第三方披露、提供、传播或允许其使用；"
                + "不得将保密信息用于与本人工作职责无关的任何目的。");
        clauses.add("资料与设备管理\n"
                + "本人承诺妥善保管涉密载体，涉密文件不得带离办公场所，不得使用个人设备存储或传输保密信息；"
                + "因工作需要使用云盘、代码托管等外部服务的，须经信息安全部门审批。");
        clauses.add("离职后的义务\n"
                + "本人离职时应将全部涉密载体、账号权限、工作成果完整交接并删除个人设备中的保密信息；"
                + "离职后仍对本人在职期间知悉的保密信息承担保密义务，期限不受限制，直至该信息公开为止。");
        clauses.add("竞业限制与利益冲突\n"
                + "本人承诺在职期间不从事与公司有竞争关系的业务，不投资、不任职于与公司存在直接竞争的企业；"
                + "发现存在潜在利益冲突的，应及时向公司如实报告。");
        clauses.add("违约责任\n"
                + "本人若违反上述承诺，愿承担相应的违约责任，包括赔偿公司因此遭受的全部损失（含调查费、律师费、诉讼费）；"
                + "构成违法犯罪的，依法承担相应法律责任。");

        String[] result = new String[clauses.size() * 2];
        for (int i = 0; i < clauses.size(); i++) {
            String[] lines = clauses.get(i).split("\n");
            result[i * 2] = "第 " + (i + 1) + " 条　" + lines[0];
            StringBuilder text = new StringBuilder();
            for (int j = 1; j < lines.length; j++) {
                text.append(lines[j]);
            }
            result[i * 2 + 1] = text.toString();
        }
        return result;
    }

    /** 生成手写签名图片（楷体 + 轻微旋转）。 */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(200, 96, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-4), 100, 48);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 42));
        g.drawString(name, 34, 66);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /** 生成单位公章（双圈 + 五角星 + 名称）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString(orgName, 16, 60);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
        g.drawString("公章", size / 2 - 17, size / 2 + 28);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

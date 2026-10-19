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
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景 98：授权委托书 PDF（多页正文 + 骑缝章 + 委托人与受托人双签字盖章）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/98_authorization_letter.txt} 中（委托事项 5 条、正文条款 5 条逐条占位）。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章 / 签名 / 骑缝章图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz98AuthorizationLetter {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "98_authorization_letter.pdf";
    private static final String TEMPLATE = "report/biz/98_authorization_letter.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成图片（合同章 + 双签名 + 骑缝章条），模板按字面量路径引用 ----------
        String seal = IMG_DIR + "98_seal.png";
        generateSeal(seal, "××智能装备制造有限公司");
        generateSignature(IMG_DIR + "98_legal_sign.png", "邵怀信");
        generateSignature(IMG_DIR + "98_agent_sign.png", "崔行知");
        splitSealForRidge(seal, 4, IMG_DIR + "98_ridge_");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        String[] matters = {
                "代为签署、递交、领取与「××产业园智能仓储系统集成项目」投标相关的全部文件，包括投标报名表、资格预审材料、投标文件、澄清说明及中标通知书。",
                "代为参加上述项目的开标、评标答疑、竞争性谈判及合同磋商会议，并代表委托人发表意见。",
                "代为办理投标保证金、履约保证金的缴纳与退还手续，签收相关凭证。",
                "代为签署项目合同、补充协议及与项目实施相关的技术确认单、验收单。",
                "代为办理与上述事项相关的工商、税务、银行、公证等其他手续。"
        };
        String[] articleTexts = {
                "受托人在办理上述事项时，有权以委托人的名义签署相关文件、提交材料、作出承诺与确认。"
                        + "但涉及合同金额调整超过原报价 3%、变更合同实质性条款、放弃投标或中标资格、处分委托人重大权益等事项，"
                        + "受托人须事先取得委托人的书面特别授权，否则不得实施。",
                "自本委托书签署之日起至 2026年12月31日 止。期限届满前委托事项已办理完毕的，本委托书自动终止；"
                        + "确需延期的，由委托人出具书面延期文件，并作为本委托书的附件。",
                "未经委托人书面同意，受托人不得将本委托事项全部或部分转委托给第三人。经同意转委托的，"
                        + "受托人仅对第三人的选任及其指示负责，因第三人行为造成的后果由委托人承担。",
                "受托人应在授权范围内谨慎、勤勉地履行代理职责，及时向委托人报告办理进展；"
                        + "对因超越授权范围、滥用代理权或与他人恶意串通而给委托人造成的损失，受托人应承担赔偿责任。",
                "本委托书未尽事宜，双方另行协商并以书面形式确认。因本委托书发生争议的，由委托人住所地人民法院管辖。"
                        + "本委托书的传真件、扫描件与原件具有同等法律效力。"
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("docNo", "WT-2026-0117");
        data.put("principal", "××智能装备制造有限公司");
        data.put("principalId", "91320100MA1X****7K");
        data.put("principalAddress", "××市××区智造大道 88 号 装备产业园 5 号楼");
        data.put("legalPerson", "邵怀信");
        data.put("agent", "崔行知");
        data.put("agentId", "3201**********0614");
        data.put("agentPost", "商务发展部 总监");
        data.put("agentPhone", "138****6620");
        data.put("period", "2026年3月1日 至 2026年12月31日");
        data.put("signDate", "2026年3月1日");
        for (int i = 0; i < matters.length; i++) {
            data.put("matter" + (i + 1), matters[i]);
        }
        for (int i = 0; i < articleTexts.length; i++) {
            data.put("articleText" + (i + 1), articleTexts[i]);
        }

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        // 文末两栏签字区依赖 flex
        config.getLayoutConfig().setFlexLayout(true);
        // 右边距留出骑缝章空间
        config.getDoc().setMargins(Arrays.asList(58, 76, 56, 62));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("授权委托书　" + data.get("docNo"));
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(34);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /**
     * 把整枚印章横向切成 strips 条窄图，用于骑缝章输出。
     */
    public static List<String> splitSealForRidge(String sealPath, int strips, String prefix) throws IOException {
        BufferedImage source = ImageIO.read(new File(sealPath));
        int stripWidth = Math.max(1, source.getWidth() / strips);
        List<String> paths = new ArrayList<String>();
        for (int i = 0; i < strips; i++) {
            int x = i * stripWidth;
            int w = Math.min(stripWidth, source.getWidth() - x);
            BufferedImage part = source.getSubimage(x, 0, w, source.getHeight());
            String path = prefix + i + ".png";
            ImageIO.write(part, "png", new File(path));
            paths.add(path);
        }
        return paths;
    }

    /** 生成合同专用章（椭圆，企业合同章常见形式）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int w = 260;
        int h = 180;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(7, 7, w - 14, h - 14);
        g.setStroke(new BasicStroke(1.5f));
        g.drawOval(17, 17, w - 34, h - 34);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        // 长单位名分两行居中
        g.drawString(orgName.length() > 11 ? orgName.substring(0, 11) : orgName, 22, h / 2 - 8);
        if (orgName.length() > 11) {
            g.drawString(orgName.substring(11), 22, h / 2 + 12);
        }
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("合同专用章", w / 2 - 42, h - 34);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /** 生成手写签名图片（楷体 + 轻微旋转）。 */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(220, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-3), 110, 50);
        g.setColor(new Color(0x1B, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 42));
        g.drawString(name, 28, 68);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

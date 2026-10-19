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
 * 场景 93：安全生产责任书（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bind(String, Object)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/93_safety_responsibility.txt} 中。</p>
 *
 * <p>产物目录约定：</p>
 * <ul>
 *   <li>PDF：{@code D:/test/pdf/93_safety_responsibility.pdf}</li>
 *   <li>印章 / 签名 / 骑缝章图片：{@code D:/test/image/93_*.png}</li>
 * </ul>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz93SafetyResponsibility {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "93_safety_responsibility.pdf";
    private static final String TEMPLATE = "report/biz/93_safety_responsibility.txt";

    public static void main(String[] args) throws IOException {
        mkdirs();

        // ---------- 1. 生成图片（印章 / 签名 / 骑缝章条），输出到 D:/test/image/ ----------
        // 模板中 image src 按字面量引用这些固定路径（当前引擎 image src 不解析 ${var}）
        String seal = IMG_DIR + "93_seal.png";
        String sign = IMG_DIR + "93_sign.png";
        generateSeal(seal, "××建工集团");
        generateSignature(sign, "郑守义");
        splitSealForRidge(seal, 4, IMG_DIR + "93_ridge_");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("docNo", "AQ-2026-0138");
        data.put("unit", "××建工集团有限公司 第三工程分公司");
        data.put("responsiblePerson", "郑守义");
        data.put("personId", "3201**********2317");
        data.put("personPost", "项目经理");
        // 模板单元格只放单个变量，组合文案在 Java 侧拼好
        data.put("personLine", "郑守义　（岗位：项目经理）");
        data.put("idLine", "3201**********2317　（签署日期：2026年1月8日）");
        data.put("period", "2026年1月1日 至 2026年12月31日");
        data.put("signDate", "2026年1月8日");
        // 条款：逐条 bind 为 clauseTitle1~8 / clauseText1~8（模板逐条引用）
        String[] clauseArr = buildClauses();
        for (int i = 0; i < clauseArr.length / 2; i++) {
            data.put("clauseTitle" + (i + 1), clauseArr[i * 2]);
            data.put("clauseText" + (i + 1), clauseArr[i * 2 + 1]);
        }

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        // 右边距留 74pt 给骑缝章条
        config.getDoc().setMargins(Arrays.asList(58, 74, 56, 62));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("安全生产责任书　" + data.get("docNo"));
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

    /**
     * 返回条款数组：偶数下标为条标题（含「第 N 条」前缀），奇数下标为正文。
     */
    private static String[] buildClauses() {
        List<String> clauses = new ArrayList<String>();
        clauses.add("组织保障\n"
                + "责任人作为本单位安全生产第一责任人，对本单位安全生产工作全面负责，须健全安全生产责任制，"
                + "逐级签订责任书并落实到班组、岗位；每月至少主持召开一次安全生产专题会议，研究解决重大安全问题。");
        clauses.add("制度建设与执行\n"
                + "组织制定并落实安全生产规章制度和安全操作规程，建立健全风险分级管控与隐患排查治理双重预防机制；"
                + "对危大工程须编制专项施工方案，组织专家论证并按方案施工，严禁擅自变更。");
        clauses.add("安全生产投入\n"
                + "按不低于产值 1.5% 的比例提取安全生产费用，专款专用于安全防护设施、劳动保护用品、教育培训、"
                + "应急物资及安全技术改造，不得挪作他用；安全费用使用情况须按季度公示。");
        clauses.add("教育培训\n"
                + "组织全员安全生产教育培训，新入场人员须完成三级安全教育并考核合格后方可上岗；"
                + "特种作业人员必须持有效证件上岗，建立培训档案并保留不少于三年的记录。");
        clauses.add("隐患排查与治理\n"
                + "组织开展日常巡查、专项检查与季节性检查，对发现的隐患实行清单化管理，做到整改措施、责任、资金、"
                + "时限、预案五落实；重大隐患须立即停止作业并向上级主管部门报告。");
        clauses.add("应急管理\n"
                + "制定生产安全事故应急救援预案并每年至少组织两次演练，配备必要的应急救援器材与装备；"
                + "发生事故后须在规定时间内上报，严禁迟报、漏报、谎报或瞒报。");
        clauses.add("职业健康\n"
                + "落实职业病危害因素检测与告知制度，为从业人员提供符合标准的劳动防护用品并监督正确佩戴使用；"
                + "组织接触职业病危害岗位人员定期体检，建立职业健康监护档案。");
        clauses.add("事故报告与处理\n"
                + "坚持事故原因未查清不放过、责任人员未处理不放过、整改措施未落实不放过、有关人员未受到教育不放过的原则，"
                + "按四不放过要求组织调查处理，并召开现场分析会举一反三。");

        String[] result = new String[clauses.size() * 2];
        for (int i = 0; i < clauses.size(); i++) {
            // 按 \n 切成段：首段是条标题，其余是正文
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

    /**
     * 把整枚印章横向切成 strips 条窄图，用于骑缝章输出。
     *
     * @param sealPath 完整印章图片路径
     * @param strips   切分条数（建议 3~5）
     * @param prefix   输出文件前缀，结果形如 prefix0.png
     * @return 各细条路径，按从左到右顺序
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

    /** 生成手写签名图片（楷体 + 轻微旋转）。 */
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

    /** 生成单位公章（双圈 + 五角星 + 名称）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 220;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(7f));
        g.drawOval(7, 7, size - 14, size - 14);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(19, 19, size - 38, size - 38);
        g.fill(star(size / 2, size / 2 - 8, 22));
        double startAngle = -160;
        double step = 200.0 / Math.max(1, orgName.length());
        for (int i = 0; i < orgName.length(); i++) {
            double angle = Math.toRadians(startAngle + step * i);
            int r = 76;
            int x = (int) (size / 2 + r * Math.cos(angle));
            int y = (int) (size / 2 + r * Math.sin(angle));
            Graphics2D g2 = (Graphics2D) g.create();
            g2.rotate(angle + Math.PI / 2, x, y);
            g2.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
            g2.drawString(String.valueOf(orgName.charAt(i)), x - 7, y + 6);
            g2.dispose();
        }
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString("公章", size / 2 - 16, size - 34);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    private static Shape star(int cx, int cy, int radius) {
        java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : radius * 0.4;
            double angle = Math.toRadians(-90 + i * 36);
            double x = cx + r * Math.cos(angle);
            double y = cy + r * Math.sin(angle);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();
        return path;
    }
}

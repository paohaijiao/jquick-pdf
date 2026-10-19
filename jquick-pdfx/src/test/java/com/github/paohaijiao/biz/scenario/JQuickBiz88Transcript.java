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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 88：学生成绩单（模板文件 + bind 数据注入版）。
 *
 * <p>本类不定义任何实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)} 注入；
 * 版式全部落在模板文件 {@code report/biz/88_transcript.txt} 中。</p>
 *
 * <p>绩点换算：成绩 ≥90 为 4.0，85-89 为 3.7，82-84 为 3.3，78-81 为 3.0，75-77 为 2.7，
 * 72-74 为 2.3，68-71 为 2.0，64-67 为 1.5，60-63 为 1.0，60 以下为 0。
 * 平均绩点（GPA）= Σ(学分 × 绩点) / Σ学分，由程序计算。</p>
 *
 * <p>产物：PDF {@code D:/test/pdf/88_transcript.pdf}；
 * 教务处公章 {@code D:/test/image/88_seal.png}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz88Transcript {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "88_transcript.pdf";
    private static final String TEMPLATE = "report/biz/88_transcript.txt";

    /** 课程原始数据：名称 / 学分 / 成绩。 */
    private static final String[][] COURSES = {
            {"高等数学（下）", "5.0", "92"},
            {"线性代数", "3.0", "88"},
            {"概率论与数理统计", "3.0", "85"},
            {"数据结构", "4.0", "94"},
            {"操作系统", "4.0", "89"},
            {"计算机网络", "3.5", "86"},
            {"数据库原理", "3.5", "91"},
            {"Java 程序设计", "3.0", "96"},
            {"软件工程", "2.5", "83"},
            {"编译原理", "3.0", "76"},
            {"算法设计与分析", "3.0", "87"},
            {"大学英语（四）", "2.0", "82"},
            {"毛泽东思想概论", "2.0", "79"},
            {"体育（四）", "1.0", "90"},
            {"计算方法", "2.5", "81"},
            {"信息安全导论", "2.0", "88"},
            {"机器学习基础", "3.0", "93"},
            {"分布式系统", "2.5", "86"},
            {"专业英语", "2.0", "78"},
            {"生产实习", "3.0", "89"},
            {"毕业设计（开题）", "4.0", "85"}};

    public static void main(String[] args) throws IOException {
        mkdirs();
        generateSeal(IMG_DIR + "88_seal.png");

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("studentName", "顾清和");
        data.put("studentNo", "20220101 0347");
        data.put("college", "计算机科学与技术学院");
        data.put("major", "软件工程（卓越工程师班）");
        data.put("term", "2025-2026 学年 第一学期");
        data.put("issueDate", "2026年10月19日");

        BigDecimal totalCredit = BigDecimal.ZERO;
        BigDecimal weighted = BigDecimal.ZERO;
        for (int i = 0; i < COURSES.length; i++) {
            String name = COURSES[i][0];
            BigDecimal credit = new BigDecimal(COURSES[i][1]);
            int score = Integer.parseInt(COURSES[i][2]);
            BigDecimal point = gradePoint(score);
            totalCredit = totalCredit.add(credit);
            weighted = weighted.add(credit.multiply(point));
            String remark = score < 60 ? "不及格" : (score >= 90 ? "优秀" : "");
            data.put("courseName" + (i + 1), name);
            data.put("courseCredit" + (i + 1), credit.toPlainString());
            data.put("courseScore" + (i + 1), String.valueOf(score));
            data.put("coursePoint" + (i + 1), point.toPlainString());
            data.put("courseRemark" + (i + 1), remark);
        }
        BigDecimal gpa = totalCredit.signum() == 0 ? BigDecimal.ZERO
                : weighted.divide(totalCredit, 2, RoundingMode.HALF_UP);
        data.put("totalCredit", totalCredit.toPlainString());
        data.put("gpa", gpa.toPlainString());

        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(52, 52, 58, 52));
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

    /** 按百分制成绩换算绩点。 */
    private static BigDecimal gradePoint(int score) {
        if (score >= 90) {
            return new BigDecimal("4.0");
        } else if (score >= 85) {
            return new BigDecimal("3.7");
        } else if (score >= 82) {
            return new BigDecimal("3.3");
        } else if (score >= 78) {
            return new BigDecimal("3.0");
        } else if (score >= 75) {
            return new BigDecimal("2.7");
        } else if (score >= 72) {
            return new BigDecimal("2.3");
        } else if (score >= 68) {
            return new BigDecimal("2.0");
        } else if (score >= 64) {
            return new BigDecimal("1.5");
        } else if (score >= 60) {
            return new BigDecimal("1.0");
        }
        return BigDecimal.ZERO;
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
        g.drawString("××大学", size / 2 - 27, 62);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
        g.drawString("教务处", size / 2 - 27, size / 2 + 28);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

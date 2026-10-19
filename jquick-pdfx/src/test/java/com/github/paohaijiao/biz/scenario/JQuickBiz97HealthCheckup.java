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

import com.github.paohaijiao.JOption;
import com.github.paohaijiao.combol.JComboLineBarChartData;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.enums.JChartType;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.factory.JChartRendererFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 97：健康体检报告 PDF（图文混排 + SVG 趋势图 + 检查项表格跨页 + 体检中心章）。
 *
 * <p>本类不定义任何业务实体类，全部数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/97_health_checkup.txt} 中（检查明细 41 行逐格占位）。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章 / 签名图片输出 {@code D:/test/image/}。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz97HealthCheckup {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "97_health_checkup.pdf";
    private static final String TEMPLATE = "report/biz/97_health_checkup.txt";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成图片（体检专用章 + 总检医师签名），模板按字面量路径引用 ----------
        generateSeal(IMG_DIR + "97_seal.png", "安康体检中心");
        generateSignature(IMG_DIR + "97_sign.png", "叶知秋");

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        // 检查明细：科室 / 项目 / 结果 / 参考范围 / 单位 / 提示
        String[][] items = {
                {"一般", "身高", "172.0", "—", "cm", "正常"},
                {"一般", "体重", "78.1", "—", "kg", "正常"},
                {"一般", "体质指数 BMI", "26.4", "18.5 ~ 23.9", "kg/m²", "↑"},
                {"一般", "腰围", "92", "< 90", "cm", "↑"},
                {"一般", "收缩压", "134", "90 ~ 139", "mmHg", "正常"},
                {"一般", "舒张压", "88", "60 ~ 89", "mmHg", "正常"},
                {"内科", "心率", "78", "60 ~ 100", "次/分", "正常"},
                {"内科", "心律", "齐", "齐", "—", "正常"},
                {"内科", "心音", "各瓣膜区未闻及病理性杂音", "—", "—", "正常"},
                {"内科", "肺呼吸音", "双肺呼吸音清", "—", "—", "正常"},
                {"内科", "腹部触诊", "肝脾肋下未触及", "—", "—", "正常"},
                {"外科", "甲状腺", "未触及肿大", "—", "—", "正常"},
                {"外科", "淋巴结", "未触及肿大", "—", "—", "正常"},
                {"外科", "脊柱四肢", "活动自如，无畸形", "—", "—", "正常"},
                {"外科", "皮肤", "无黄染、无皮疹", "—", "—", "正常"},
                {"眼科", "左眼视力", "1.0", "≥ 1.0", "—", "正常"},
                {"眼科", "右眼视力", "0.8", "≥ 1.0", "—", "↓"},
                {"眼科", "眼底", "视盘边界清，动脉稍细", "—", "—", "复查"},
                {"耳鼻喉", "听力", "双耳正常", "—", "—", "正常"},
                {"耳鼻喉", "鼻咽部", "黏膜无充血", "—", "—", "正常"},
                {"检验", "白细胞计数 WBC", "6.2", "3.5 ~ 9.5", "10^9/L", "正常"},
                {"检验", "红细胞计数 RBC", "4.86", "4.3 ~ 5.8", "10^12/L", "正常"},
                {"检验", "血红蛋白 HGB", "152", "130 ~ 175", "g/L", "正常"},
                {"检验", "血小板 PLT", "216", "125 ~ 350", "10^9/L", "正常"},
                {"检验", "空腹血糖 GLU", "6.4", "3.9 ~ 6.1", "mmol/L", "↑"},
                {"检验", "糖化血红蛋白 HbA1c", "6.2", "4.0 ~ 6.0", "%", "↑"},
                {"检验", "总胆固醇 TC", "6.18", "< 5.20", "mmol/L", "↑"},
                {"检验", "甘油三酯 TG", "2.84", "< 1.70", "mmol/L", "↑"},
                {"检验", "高密度脂蛋白 HDL-C", "1.02", "> 1.04", "mmol/L", "↓"},
                {"检验", "低密度脂蛋白 LDL-C", "4.06", "< 3.40", "mmol/L", "↑"},
                {"检验", "尿酸 UA", "468", "208 ~ 428", "μmol/L", "↑"},
                {"检验", "肌酐 CREA", "82", "57 ~ 97", "μmol/L", "正常"},
                {"检验", "尿素氮 BUN", "5.6", "3.1 ~ 8.0", "mmol/L", "正常"},
                {"检验", "总胆红素 TBIL", "16.8", "3.4 ~ 20.5", "μmol/L", "正常"},
                {"检验", "谷丙转氨酶 ALT", "48", "9 ~ 50", "U/L", "正常"},
                {"检验", "谷草转氨酶 AST", "36", "15 ~ 40", "U/L", "正常"},
                {"检验", "尿常规", "蛋白 (-)，葡萄糖 (-)", "阴性", "—", "正常"},
                {"影像", "胸部 DR 正位", "双肺纹理清晰，未见明显实质性病变", "—", "—", "正常"},
                {"影像", "腹部彩超", "轻度脂肪肝，胆囊壁毛糙", "—", "—", "复查"},
                {"影像", "甲状腺彩超", "甲状腺结节（右叶 0.6cm，TI-RADS 3 类）", "—", "—", "复查"},
                {"影像", "颈动脉超声", "双侧颈动脉内中膜增厚，未见斑块", "—", "—", "复查"},
                {"影像", "心电图", "窦性心律，大致正常心电图", "—", "—", "正常"}
        };
        String[] conclusions = {
                "1．超重：BMI 26.4，腰围 92cm，建议控制体重。",
                "2．血脂异常：总胆固醇、甘油三酯、低密度脂蛋白升高，高密度脂蛋白偏低。",
                "3．空腹血糖受损：空腹血糖 6.4 mmol/L，糖化血红蛋白 6.2%。",
                "4．高尿酸血症：尿酸 468 μmol/L。",
                "5．轻度脂肪肝；胆囊壁毛糙。",
                "6．甲状腺右叶结节（TI-RADS 3 类），建议 6 个月复查。",
                "7．右眼视力下降，眼底动脉稍细。",
                "8．颈动脉内中膜增厚，动脉硬化早期表现。"
        };
        String[] suggestions = {
                "控制总热量摄入，减少精制碳水与含糖饮料，每日食盐摄入控制在 5g 以内。",
                "每周进行 150 分钟以上中等强度有氧运动，如快走、游泳、骑行，配合抗阻训练。",
                "限制高嘌呤食物（动物内脏、海鲜、浓肉汤）与酒精摄入，每日饮水 2000ml 以上。",
                "3 个月后复查空腹血糖、糖化血红蛋白与血脂四项，必要时至内分泌科就诊。",
                "6 个月后复查甲状腺彩超，观察结节大小与形态变化。",
                "眼科就诊评估视力下降原因，注意用眼卫生，避免长时间近距离用眼。",
                "规律作息，避免熬夜与长期精神紧张，保持情绪稳定。"
        };

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("reportNo", "TJ-2026-0318-0927");
        data.put("name", "方致远");
        data.put("personLine", "方致远");
        data.put("genderAge", "男 / 38 岁");
        data.put("department", "××科技股份有限公司　技术研发中心");
        data.put("checkDate", "2026年3月18日");
        data.put("packageName", "职工年度健康体检（精英套餐 B）");
        data.put("center", "安康体检中心");
        data.put("bmi", "26.4");
        data.put("bloodPressure", "134 / 88");
        data.put("heartRate", "78");
        data.put("vision", "0.8 / 1.0");

        int abnormal = 0;
        for (int i = 0; i < items.length; i++) {
            if ("↑".equals(items[i][5]) || "↓".equals(items[i][5]) || "复查".equals(items[i][5])) {
                abnormal++;
            }
            data.put("itGroup" + (i + 1), items[i][0]);
            data.put("itName" + (i + 1), items[i][1]);
            data.put("itResult" + (i + 1), items[i][2]);
            data.put("itRef" + (i + 1), items[i][3]);
            data.put("itUnit" + (i + 1), items[i][4]);
            data.put("itFlag" + (i + 1), items[i][5]);
        }
        data.put("abnormalCount", String.valueOf(abnormal));
        for (int i = 0; i < conclusions.length; i++) {
            data.put("concl" + (i + 1), conclusions[i]);
        }
        for (int i = 0; i < suggestions.length; i++) {
            data.put("sugg" + (i + 1), suggestions[i]);
        }
        data.put("doctor", "叶知秋");
        data.put("centerAddress", "××市××区康宁路 66 号 健康大厦 3 层　预约电话 400-820-1234");

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(54, 58, 52, 58));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText("健康体检报告　" + data.get("reportNo"));
        config.getHeaderConfig().setFontSize(8);
        config.getHeaderConfig().setHeight(32);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        // SVG 图表字符串通过 bind 注入，模板中用 ${trendSvg} 占位
        factory.bind("trendSvg", buildTrendSvg());
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE);
    }

    /**
     * 用 jquick-pdf 内置图表能力生成「柱 + 线」组合图 SVG，作为模板变量注入。
     */
    private static String buildTrendSvg() {
        JComboLineBarChartData data = JComboLineBarChartData.builder()
                .title("体重", "收缩压")
                .barData(Arrays.asList(74.5, 76.2, 78.1))
                .lineData(Arrays.asList(122.0, 128.0, 134.0))
                .xAxisLabels(Arrays.asList("2023", "2024", "2025"))
                .barColor(new Color(0x0E, 0x5A, 0x4A))
                .lineColor(new Color(0xC6, 0x28, 0x28))
                .leftAxisTitle("体重 kg")
                .rightAxisTitle("血压 mmHg")
                .barLegendText("体重")
                .lineLegendText("收缩压")
                .footerText("数据来源：历年体检报告")
                .build();
        JOption option = new JOption();
        option.setData(data);
        return JChartRendererFactory.renderChart(JChartType.LineBar, option);
    }

    /** 生成体检专用章（双圈 + 中心名 + 「体检专用章」字样）。 */
    private static void generateSeal(String path, String center) throws IOException {
        int size = 200;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(6f));
        g.drawOval(8, 8, size - 16, size - 16);
        g.setStroke(new BasicStroke(1.5f));
        g.drawOval(18, 18, size - 36, size - 36);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString(center, 20, 56);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.drawString("体检专用章", 30, size / 2 + 22);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }

    /** 生成医师签名图片（楷体 + 轻微旋转）。 */
    private static void generateSignature(String path, String name) throws IOException {
        BufferedImage image = new BufferedImage(220, 104, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(-4), 110, 52);
        g.setColor(new Color(0x0E, 0x2A, 0x3A));
        g.setFont(new Font("KaiTi", Font.PLAIN, 42));
        g.drawString(name, 30, 70);
        g.dispose();
        ImageIO.write(image, "png", new File(path));
    }
}

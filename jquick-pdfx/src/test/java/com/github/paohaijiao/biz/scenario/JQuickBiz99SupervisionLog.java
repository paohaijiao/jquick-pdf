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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 场景 99：监理日志 PDF（封面 + 4 个连续记录日，一日一页 + 施工明细表 + 监理单位章 + 总监签字）。
 *
 * <p>本类不定义任何实体类，全部业务数据通过 {@link JQuickPdfFactory#bindAll(Map)}
 * 以 key-value 形式注入；版式全部落在模板文件
 * {@code report/biz/99_supervision_log.txt} 中。</p>
 *
 * <p>版式说明：封面为工程信息表 + 说明 + 监理单位章 / 总监签字；
 * 每个记录日一块，包含天气表、施工明细表（6 行 × 6 列，逐格占位）、材料进场、
 * 检查问题（3 条）与三人签字栏，日与日之间用 {@code <areaBreak></areaBreak>} 分页。</p>
 *
 * <p>产物目录约定：PDF 输出 {@code D:/test/pdf/}，印章 / 签名图片输出 {@code D:/test/image/}。
 * 注意：当前渲染引擎的 {@code <image>} 标签 {@code src} 只支持字面量路径、不解析
 * {@code ${var}}，因此模板中直接写死图片绝对路径，由本类在渲染前先生成图片。</p>
 *
 * @author Martin
 * @version 2.0.0
 */
public class JQuickBiz99SupervisionLog {

    private static final String PDF_DIR = "D:/test/pdf/";
    private static final String IMG_DIR = "D:/test/image/";
    private static final String OUT_FILE = PDF_DIR + "99_supervision_log.pdf";
    private static final String TEMPLATE = "report/biz/99_supervision_log.txt";

    /** 每个记录日的施工记录条数（与模板中逐格占位数量一致）。 */
    private static final int ROWS_PER_DAY = 6;

    private static final String PROJECT_NAME = "××产业园二期 3# 厂房及配套工程";
    private static final String SUPERVISION_UNIT = "华信工程监理有限公司";
    private static final String BUILDER_UNIT = "××建工集团有限公司 第三工程分公司";
    private static final String SUPERVISOR = "路承志";
    private static final String CHIEF = "闻仲平";

    public static void main(String[] args) throws IOException {
        new File(PDF_DIR).mkdirs();
        new File(IMG_DIR).mkdirs();

        // ---------- 1. 生成图片（监理单位章 + 总监签字），模板按字面量路径引用 ----------
        generateSeal(IMG_DIR + "99_seal.png", SUPERVISION_UNIT);
        generateSignature(IMG_DIR + "99_sign.png", CHIEF);

        // ---------- 2. 组织数据（普通 Map，无实体类）----------
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("projectName", PROJECT_NAME);
        data.put("supervisionUnit", SUPERVISION_UNIT);
        data.put("builderUnit", BUILDER_UNIT);
        data.put("logNo", "JL-2026-03");
        data.put("period", "2026年3月16日 至 2026年3月19日");

        // 4 个记录日：日期(0) / 星期(1) / 天气(2) / 气温(3) / 风力(4) / 签收人(5)
        String[][] dayHeaders = {
                {"2026年3月16日", "星期一", "晴 / 多云", "12℃ ~ 21℃", "东北风 2 级", "赵允中"},
                {"2026年3月17日", "星期二", "多云 / 阴", "14℃ ~ 23℃", "东南风 3 级", "赵允中"},
                {"2026年3月18日", "星期三", "小雨 / 阴", "10℃ ~ 16℃", "北风 4 级", "钱立诚"},
                {"2026年3月19日", "星期四", "阴 / 晴", "11℃ ~ 19℃", "西北风 3 级", "钱立诚"}
        };

        // 4 个记录日的施工明细：{施工部位, 施工内容, 投入人力, 投入机械, 质量检查情况}
        String[][][] works = {
                {
                        {"3# 厂房 A 轴 ~ F 轴", "钢柱吊装（GZ-1 ~ GZ-8），共 8 根，采用 25t 汽车吊单机起吊",
                                "起重工 4 / 焊工 6 / 普工 8", "25t 汽车吊 1 台", "垂直度偏差 2mm，符合规范要求"},
                        {"3# 厂房 A 轴 ~ F 轴", "柱脚锚栓紧固与二次灌浆（C40 无收缩灌浆料）",
                                "灌浆工 4 / 普工 4", "灌浆泵 1 台", "灌浆密实，无空鼓，养护 48 小时"},
                        {"3# 厂房 G 轴 ~ L 轴", "基础承台钢筋绑扎（HRB400 Φ20、Φ16）",
                                "钢筋工 12 人", "钢筋切断机 2 台", "间距、保护层抽检合格"},
                        {"3# 厂房 G 轴 ~ L 轴", "承台模板安装（覆膜多层板），轴线放样复核",
                                "木工 8 人", "—", "拼缝严密，轴线偏差 3mm"},
                        {"厂区东侧道路", "临时道路级配碎石铺设与碾压，长度约 180m",
                                "普工 6 人", "压路机 1 台", "压实度检测 96%"},
                        {"2# 厂房", "屋面檩条矫正与补漆，处理变形檩条 12 根",
                                "油漆工 4 人", "—", "漆膜厚度 78μm，外观均匀"}
                },
                {
                        {"3# 厂房 A 轴 ~ F 轴", "钢梁吊装（GL-1 ~ GL-6），高强螺栓初拧，共 6 榀",
                                "起重工 4 / 焊工 4 / 普工 6", "25t 汽车吊 1 台", "高强螺栓终拧扭矩抽检合格"},
                        {"3# 厂房 G 轴 ~ L 轴", "承台混凝土浇筑（C30，共 186m³），连续浇筑不留施工缝",
                                "混凝土工 14 / 试验员 1", "泵车 1 台 / 罐车 6 辆", "坍落度 180mm，试块留置 3 组"},
                        {"3# 厂房 G 轴 ~ L 轴", "承台混凝土养护（覆膜 + 定时洒水），旁站监理全程",
                                "普工 3 人", "—", "表面湿润，无裂缝"},
                        {"1# 厂房", "室内地坪基层清理与找平，面积约 1200㎡",
                                "普工 8 人", "小型夯实机 2 台", "平整度 4mm/2m"},
                        {"厂区北侧", "围墙基础开挖（长 120m，深 1.2m）",
                                "普工 6 人", "小型挖掘机 1 台", "基底标高符合设计，无扰动"},
                        {"全场", "临时用电线路巡查与三级配电箱标识完善",
                                "电工 2 人", "—", "接地电阻实测 3.8Ω，合格"}
                },
                {
                        {"3# 厂房 A 轴 ~ F 轴", "屋面檩条安装（Z 型钢），因降雨间歇施工，完成 60%",
                                "安装工 8 人", "25t 汽车吊 1 台", "檩条间距偏差 ±5mm"},
                        {"3# 厂房 G 轴 ~ L 轴", "承台混凝土拆模与外观检查，同条件试块送检",
                                "混凝土工 6 人", "—", "外观无裂缝、无缺棱掉角"},
                        {"3# 厂房 G 轴 ~ L 轴", "基础回填土分层夯实（每层 300mm）",
                                "普工 10 人", "蛙式打夯机 3 台", "压实度抽检 95%、96%"},
                        {"1# 厂房", "地坪混凝土浇筑（C25，120m³），分仓浇筑",
                                "混凝土工 10 人", "罐车 4 辆", "平整度 3mm/2m"},
                        {"办公区", "临时用电配电箱检查与整改，更换漏电保护器 2 只",
                                "电工 2 人", "—", "漏电保护动作电流 30mA，正常"},
                        {"厂区东侧道路", "排水沟砌筑（长 60m），处理前日积水问题",
                                "瓦工 4 人", "砂浆搅拌机 1 台", "沟底坡度 3‰，排水通畅"}
                },
                {
                        {"3# 厂房 A 轴 ~ F 轴", "屋面檩条安装完成，拉条与撑杆安装并张紧",
                                "安装工 10 人", "25t 汽车吊 1 台", "拉条张紧度符合要求"},
                        {"3# 厂房 G 轴 ~ L 轴", "地梁钢筋绑扎与模板安装（DL-1 ~ DL-8）",
                                "钢筋工 10 / 木工 6", "钢筋弯曲机 1 台", "钢筋规格、间距抽检合格"},
                        {"1# 厂房", "地坪切缝与养护剂喷涂，切缝间距 6m",
                                "普工 5 人", "切缝机 2 台", "切缝深度 60mm，顺直"},
                        {"厂区北侧", "围墙基础砌筑（MU10 页岩砖，M7.5 砂浆），完成 90m",
                                "瓦工 8 人", "砂浆搅拌机 1 台", "砂浆饱满度 85%，灰缝顺直"},
                        {"全场", "安全文明施工检查与春季防火专项检查",
                                "安全员 2 人", "—", "消防器材配置齐全，动火审批补办"},
                        {"3# 厂房 A 轴 ~ F 轴", "钢柱柱脚二次灌浆养护期满，拆除临时支撑",
                                "安装工 4 人", "—", "支撑拆除后柱顶位移 1mm，稳定"}
                }
        };

        // 4 个记录日的材料进场（每天 2 条）
        String[][] materials = {
                {"HRB400 Φ20 钢筋 24.6t，随车质保书、材质单齐全，见证取样 2 组送检。",
                        "C40 无收缩灌浆料 3.2t，出厂合格证齐全，进场复验合格。"},
                {"C30 商品混凝土 186m³（3 车/小时），随车配合比通知单齐全。",
                        "高强螺栓 M20×70 共 860 套，出厂合格证、复验报告齐全。"},
                {"Z 型钢檩条 12.8t，镀锌层厚度检测合格，附材质单。",
                        "C25 商品混凝土 120m³，坍落度实测 170mm，试块留置 2 组。"},
                {"MU10 页岩砖 26000 块，强度复验合格，外观无缺棱掉角。",
                        "M7.5 砌筑砂浆 6m³（现场搅拌，配合比挂牌管理）。"}
        };

        // 4 个记录日的检查问题（每天 3 条）
        String[][] issues = {
                {"G 轴 5# 承台钢筋保护层垫块间距偏大（实测 1.2m），已口头通知施工单位加密至 0.8m，当日整改完成并复查合格。",
                        "东侧临时道路局部积水，要求增设排水沟，限期 3 月 18 日前完成。",
                        "2# 厂房屋面檩条矫正作业未设置水平安全绳，已要求加设后方可继续施工。"},
                {"承台混凝土浇筑过程中发现一处漏振，已要求立即补振并加密振捣点，浇筑完成后外观检查无蜂窝麻面。",
                        "北侧围墙基槽局部超挖 15cm，已要求用 C15 混凝土回填至设计标高。",
                        "现场发现 2 名作业人员未系安全带进行钢梁高处作业，当场责令停止作业并对班组进行安全交底，记录在案。"},
                {"降雨导致 3# 厂房屋面作业面湿滑，已下达暂停高处作业指令，待天气转好、采取防滑措施后方可复工。",
                        "基础回填土局部含水率偏高，要求翻晒后再行夯实，避免形成橡皮土。",
                        "办公区二级配电箱未上锁、箱内接线凌乱，已要求当日整改并配置专职电工负责。"},
                {"G 轴地梁模板拼缝处存在漏浆风险，要求加贴海绵条，已整改完成。",
                        "1# 厂房地坪切缝局部偏斜约 40mm，要求下段作业前弹线控制，并做样板引路。",
                        "现场动火作业点 2 处未办理动火审批，已责令停工补办手续，并加强春季防火巡查。"}
        };

        // 逐日铺平为 key-value（d1Part1 / d1Content1 / ... / d4Issue3）
        for (int d = 0; d < dayHeaders.length; d++) {
            int n = d + 1;
            String[] h = dayHeaders[d];
            data.put("d" + n + "Date", h[0]);
            data.put("d" + n + "Weekday", h[1]);
            data.put("d" + n + "Weather", h[2]);
            data.put("d" + n + "Temp", h[3]);
            data.put("d" + n + "Wind", h[4]);
            data.put("d" + n + "Receiver", h[5]);
            data.put("d" + n + "Status", "正常施工（" + works[d].length + " 个作业面）");

            for (int r = 0; r < ROWS_PER_DAY; r++) {
                // 施工记录不足 6 条时补占位符，保持表格行数整齐
                String[] w = r < works[d].length ? works[d][r] : new String[]{"—", "—", "—", "—", "—"};
                int k = r + 1;
                data.put("d" + n + "Part" + k, w[0]);
                data.put("d" + n + "Content" + k, w[1]);
                data.put("d" + n + "Labor" + k, w[2]);
                data.put("d" + n + "Machine" + k, w[3]);
                data.put("d" + n + "Quality" + k, w[4]);
            }

            for (int m = 0; m < 2; m++) {
                data.put("d" + n + "Material" + (m + 1),
                        m < materials[d].length ? materials[d][m] : "本日无其他材料、设备进场。");
            }
            for (int q = 0; q < 3; q++) {
                data.put("d" + n + "Issue" + (q + 1),
                        q < issues[d].length ? issues[d][q] : "—");
            }
        }

        data.put("dayTotal", String.valueOf(dayHeaders.length));
        data.put("dayCountLine", "共 " + dayHeaders.length + " 个记录日");
        data.put("supervisor", SUPERVISOR);
        data.put("chief", CHIEF);

        // ---------- 3. 渲染 ----------
        JPdfConfig config = new JPdfConfig();
        // 日期标题与签字栏依赖 flex 排版
        config.getLayoutConfig().setFlexLayout(true);
        config.getDoc().setMargins(Arrays.asList(52, 56, 50, 56));
        config.getHeaderConfig().setEnabled(true);
        config.getHeaderConfig().setText(PROJECT_NAME + "　监理日志　JL-2026-03");
        config.getHeaderConfig().setFontSize(7.5f);
        config.getHeaderConfig().setHeight(30);
        config.getFooterConfig().setEnabled(true);
        config.getFooterConfig().setFontSize(8);
        config.getFooterConfig().setPageNumberFormat("第 %d 页");

        JQuickPdfFactory factory = new JQuickPdfFactory(config);
        factory.bindAll(data);
        byte[] pdf = factory.executeResource(TEMPLATE);
        Files.write(new File(OUT_FILE).toPath(), pdf);
        System.out.println("已生成：" + OUT_FILE + "（" + pdf.length + " 字节）");
    }

    /** 生成监理单位公章（双圈 + 五角星 + 环形单位名 + 「监理专用章」）。 */
    private static void generateSeal(String path, String orgName) throws IOException {
        int size = 240;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color red = new Color(0xC6, 0x28, 0x28);
        g.setColor(red);
        g.setStroke(new BasicStroke(7f));
        g.drawOval(7, 7, size - 14, size - 14);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(19, 19, size - 38, size - 38);

        // 五角星
        java.awt.geom.Path2D.Double star = new java.awt.geom.Path2D.Double();
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? 22 : 9;
            double angle = Math.toRadians(-90 + i * 36);
            double x = size / 2.0 + r * Math.cos(angle);
            double y = size / 2.0 - 14 + r * Math.sin(angle);
            if (i == 0) {
                star.moveTo(x, y);
            } else {
                star.lineTo(x, y);
            }
        }
        star.closePath();
        g.fill(star);

        // 单位名沿上弧排列
        double start = -170;
        double step = 220.0 / Math.max(1, orgName.length());
        for (int i = 0; i < orgName.length(); i++) {
            double angle = Math.toRadians(start + step * i);
            int r = 82;
            int x = (int) (size / 2.0 + r * Math.cos(angle));
            int y = (int) (size / 2.0 + r * Math.sin(angle));
            Graphics2D g2 = (Graphics2D) g.create();
            g2.rotate(angle + Math.PI / 2, x, y);
            g2.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
            g2.drawString(String.valueOf(orgName.charAt(i)), x - 7, y + 5);
            g2.dispose();
        }

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.drawString("监理专用章", size / 2 - 36, size - 42);
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

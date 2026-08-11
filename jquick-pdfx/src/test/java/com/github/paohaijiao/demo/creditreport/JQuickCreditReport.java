package com.github.paohaijiao.demo.creditreport;

import com.github.paohaijiao.adaptor.JAdaptor;
import com.github.paohaijiao.config.JPdfConfig;
import com.github.paohaijiao.config.JTemplateConfig;
import com.github.paohaijiao.demo.constant.JQuickConstant;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import com.github.paohaijiao.executor.JQuickPdfXExecutor;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.resouce.JReader;
import com.github.paohaijiao.resouce.impl.JReSourceFileReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class JQuickCreditReport {
    public static final String  path=JQuickConstant.path;

    @Test
    public void report() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\test.pdf");
        String html="  <div style=\"marginBottom:15px\">\n" +
                "    <h2 style=\"color:#3498db;  fontSize:13; marginBottom:10px\">企业资质认证</h2>\n" +
                "    <div style=\"display:flex; marginTop:12px; gap:8px\">\n" +
                "      <div style=\"width:20%; background:linear-gradient(135deg, #4CAF50 0%, #81C784 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n" +
                "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">AAA</div>\n" +
                "        <div style=\"font-size:10px\">信用等级</div>\n" +
                "      </div>\n" +
                "      <div style=\"width:20%; background:linear-gradient(135deg, #FF9800 0%, #F57C00 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n" +
                "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">高新</div>\n" +
                "        <div style=\"font-size:10px\">高新技术企业</div>\n" +
                "      </div>\n" +
                "      <div style=\"width:20%; background:linear-gradient(135deg, #1976D2 0%, #0D47A1 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n" +
                "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">专精特新</div>\n" +
                "        <div style=\"font-size:10px\">小巨人企业</div>\n" +
                "      </div>\n" +
                "      <div style=\"width:20%; background:linear-gradient(135deg, #9C27B0 0%, #BA68C8 100%); color:white; padding:10px; border-radius:5px; text-align:center\">\n" +
                "        <div style=\"font-size:18px; font-weight:bold; margin-bottom:3px\">水电</div>\n" +
                "        <div style=\"font-size:10px\">双软认证企业</div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>";
        JPdfConfig config = new JPdfConfig();
        JTemplateConfig templateConfig = config.getTemplateConfig();
        templateConfig.put("html", html);
        config.setTemplateConfig(templateConfig);
        JReader fileReader = new JReSourceFileReader("report.txt");
        JAdaptor adaptor = new JAdaptor(fileReader);
        JQuickPdfFactory factory=new JQuickPdfFactory(config);
        factory.bind("svg","<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"500\" height=\"450\" viewBox=\"0 0 500 450\">\n" +
                "                <!-- 雷达图背景 -->\n" +
                "                <g transform=\"translate(250, 200)\">\n" +
                "                    <!-- 绘制5层同心六边形 -->\n" +
                "                    <polygon points=\"0,-150 129.9,-75 129.9,75 0,150 -129.9,75 -129.9,-75\" \n" +
                "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
                "                    <polygon points=\"0,-120 103.9,-60 103.9,60 0,120 -103.9,60 -103.9,-60\" \n" +
                "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
                "                    <polygon points=\"0,-90 77.9,-45 77.9,45 0,90 -77.9,45 -77.9,-45\" \n" +
                "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
                "                    <polygon points=\"0,-60 51.9,-30 51.9,30 0,60 -51.9,30 -51.9,-30\" \n" +
                "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
                "                    <polygon points=\"0,-30 25.9,-15 25.9,15 0,30 -25.9,15 -25.9,-15\" \n" +
                "                             fill=\"none\" stroke=\"#e0e0e0\" stroke-width=\"1\"/>\n" +
                "\n" +
                "                    <!-- 坐标轴 -->\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-160\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"129.9\" y2=\"-75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"129.9\" y2=\"75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"160\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"-129.9\" y2=\"75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    <line x1=\"0\" y1=\"0\" x2=\"-129.9\" y2=\"-75\" stroke=\"#95a5a6\" stroke-width=\"1.5\"/>\n" +
                "                    \n" +
                "                    <!-- 维度标签 -->\n" +
                "                    <text x=\"0\" y=\"-180\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">偿债能力</text>\n" +
                "                    <text x=\"145\" y=\"-75\" text-anchor=\"start\" font-size=\"12\" fill=\"#2c3e50\">盈利能力</text>\n" +
                "                    <text x=\"145\" y=\"85\" text-anchor=\"start\" font-size=\"12\" fill=\"#2c3e50\">运营能力</text>\n" +
                "                    <text x=\"0\" y=\"190\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">成长能力</text>\n" +
                "                    <text x=\"-145\" y=\"85\" text-anchor=\"end\" font-size=\"12\" fill=\"#2c3e50\">现金流</text>\n" +
                "                    <text x=\"-145\" y=\"-75\" text-anchor=\"end\" font-size=\"12\" fill=\"#2c3e50\">信用历史</text>\n" +
                "                    \n" +
                "                    <!-- 刻度标签 -->\n" +
                "                    <text x=\"5\" y=\"-150\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">100</text>\n" +
                "                    <text x=\"5\" y=\"-120\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">80</text>\n" +
                "                    <text x=\"5\" y=\"-90\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">60</text>\n" +
                "                    <text x=\"5\" y=\"-60\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">40</text>\n" +
                "                    <text x=\"5\" y=\"-30\" text-anchor=\"start\" font-size=\"10\" fill=\"#7f8c8d\">20</text>\n" +
                "                    \n" +
                "                    <!-- 当前企业数据 -->\n" +
                "                    <polygon points=\"0,-135 116.9,-67.5 103.9,60 0,120 -103.9,67.5 -116.9,-67.5\" \n" +
                "                             fill=\"#3498db\" fill-opacity=\"0.2\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    \n" +
                "                    <!-- 数据点 -->\n" +
                "                    <circle cx=\"0\" cy=\"-135\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    <circle cx=\"116.9\" cy=\"-67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    <circle cx=\"103.9\" cy=\"60\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    <circle cx=\"0\" cy=\"120\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    <circle cx=\"-103.9\" cy=\"67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    <circle cx=\"-116.9\" cy=\"-67.5\" r=\"4\" fill=\"#ffffff\" stroke=\"#3498db\" stroke-width=\"2\"/>\n" +
                "                    \n" +
                "                    <!-- 数据值标签 -->\n" +
                "                    <text x=\"0\" y=\"-145\" text-anchor=\"middle\" font-size=\"10\" fill=\"#3498db\">90</text>\n" +
                "                    <text x=\"127\" y=\"-67.5\" text-anchor=\"start\" font-size=\"10\" fill=\"#3498db\">85</text>\n" +
                "                    <text x=\"114\" y=\"70\" text-anchor=\"start\" font-size=\"10\" fill=\"#3498db\">80</text>\n" +
                "                    <text x=\"0\" y=\"140\" text-anchor=\"middle\" font-size=\"10\" fill=\"#3498db\">75</text>\n" +
                "                    <text x=\"-114\" y=\"77\" text-anchor=\"end\" font-size=\"10\" fill=\"#3498db\">88</text>\n" +
                "                    <text x=\"-127\" y=\"-67.5\" text-anchor=\"end\" font-size=\"10\" fill=\"#3498db\">92</text>\n" +
                "                </g>\n" +
                "                \n" +
                "                <!-- 标题 -->\n" +
                "                <text x=\"250\" y=\"30\" text-anchor=\"middle\" font-size=\"16\" font-weight=\"bold\" fill=\"#2c3e50\">\n" +
                "                    科技有限公司信用评级\n" +
                "                </text>\n" +
                "                \n" +
                "                <!-- 评级说明 -->\n" +
                "                <text x=\"250\" y=\"380\" text-anchor=\"middle\" font-size=\"12\" fill=\"#2c3e50\">\n" +
                "                    综合信用评分: 85/100 | 评级: AA\n" +
                "                </text>\n" +
                "            </svg>");
        byte[] bytes=factory.executeToBytes(adaptor.getRuleContent());
        fileOutputStream.write(bytes);

    }
}

package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenSourceIntro {
    public static void main(String[] args) throws Exception {
        String template = ""
                + "<pdf><body>"
                + "<h1 style=\"fontSize:24;textAlignment:center\">'开源项目介绍'</h1>"
                + "<p>'项目名称：'${name}</p>"
                + "<p>'定位：面向 Java 后端的类 HTML PDF 生成工具'</p>"
                + "<div style=\"backgroundColor:#f3f4f6;padding:12px;keepTogether:true\">"
                + "<p>'特点：纯 Java、模板可读、支持变量绑定和自动分页。'</p>"
                + "</div>"
                + "<p>'适用场景：报表、订单、通知、合同草稿和归档文件。'</p>"
                + "</body></pdf>";
        // 绑定模板变量并执行内存模板
        byte[] pdf = JQuickPdfFactory.create()
                .bind("name", "jquick-pdf")
                .executeContent(template);
        // 将 PDF 字节保存到当前目录
        Files.write(Paths.get("d://test//project-intro.pdf"), pdf);
    }
}

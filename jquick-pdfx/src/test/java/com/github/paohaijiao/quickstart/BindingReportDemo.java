package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.*; // 导入文件 API

public class BindingReportDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>${title}</h1>" // 创建标题模板
                + "<p>'统计日期：'${date}</p><table>" // 创建摘要和表格
                + "<tr><th>'部门'</th><th>'人数'</th></tr>" // 创建表头
                + "<tr><td>'研发部'</td><td>${count}</td></tr>" // 绑定人数
                + "</table></body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory() // 创建工厂
                .bind("title", "月度人员报表") // 绑定标题
                .bind("date", "2026-09-14") // 绑定日期
                .bind("count", 36) // 绑定数字
                .executeContent(template); // 执行生成
        Files.write(Paths.get("d://test//binding-report.pdf"), pdf); // 写出文件
    }
}

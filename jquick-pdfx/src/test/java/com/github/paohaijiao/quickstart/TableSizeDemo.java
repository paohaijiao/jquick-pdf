package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.Files; // 导入文件类
import java.nio.file.Paths; // 导入路径类

public class TableSizeDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>'项目进度'</h1>" // 创建模板
                + "<table style=\"width:520px\">" // 控制总宽度
                + "<tr style=\"height:36px;backgroundColor:#DDEAFE\">" // 控制表头行高和底色
                + "<th style=\"width:160px\">'阶段'</th><th style=\"width:360px\">'说明'</th></tr>" // 控制列宽
                + "<tr style=\"height:60px\"><td>'需求'</td><td>'已完成评审，进入开发。'</td></tr>" // 控制数据行高
                + "</table><div style=\"border:1px solid #999;padding:8px\">" // 用块模拟分组视觉
                + "<p>'合并展示：项目状态'</p><p>'进行中'</p></div></body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 执行渲染
        Files.write(Paths.get("d://test//table-size.pdf"), pdf); // 保存 PDF
    }
}
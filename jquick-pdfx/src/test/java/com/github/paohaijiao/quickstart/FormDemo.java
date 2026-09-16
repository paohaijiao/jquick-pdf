package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.*; // 导入文件工具

public class FormDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>'审批回执'</h1>" // 创建模板
                + "<p>'处理结果：'</p>" // 添加说明
                + "<checkbox style=\"fontColor:blue\" checked>'同意'</checkbox>" // 添加已勾选项
                + "<checkbox>'需复核'</checkbox>" // 添加未勾选项
                + "<button style=\"fontColor:blue\">'提交'</button>" // 添加按钮
                + "</body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 渲染 PDF
        Files.write(Paths.get("d://test//approval-form.pdf"), pdf); // 保存 PDF
    }
}

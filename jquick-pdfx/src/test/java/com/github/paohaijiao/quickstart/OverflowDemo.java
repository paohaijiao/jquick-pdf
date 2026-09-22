package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.*; // 导入文件工具

public class OverflowDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>'尺寸保护'</h1>" // 创建模板
                + "<div style=\"width:460px;maxWidth:460px;maxHeight:180px;border:1px solid #555;padding:8px\">" // 设置宽高上限
                + "<p>'这是一个较长的说明文本，用于观察内容换行和最大高度配置。请在生产环境先限制输入长度。'</p>" // 添加测试文本
                + "</div></body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 渲染模板
        Files.write(Paths.get("d://test//overflow-safe.pdf"), pdf); // 保存 PDF
    }
}
package com.github.paohaijiao.quickstart;
import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.*;

public class EditableFormDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>'客户信息'</h1>" // 创建文档
                + "<p>'备注'</p><textArea style=\"width:420px;height:80px\">'请填写备注'</textArea>" // 添加输入区域
                + "<p>'类型'</p><comboBoxField style=\"width:220px\">'请选择'</comboBoxField>" // 添加下拉字段
                + "</body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 执行渲染
        Files.write(Paths.get("d://test//editable-form.pdf"), pdf); // 写出文件
    }
}
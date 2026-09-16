package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory; // 导入工厂
import java.nio.file.*; // 导入文件工具

public class ImageDemo { // 声明类
    public static void main(String[] args) throws Exception { // 声明入口
        String template = "<pdf><body><h1>'产品图片'</h1>" // 创建模板
                + "<image src=\"src/main/resources/image/img.png\" style=\"width:200px;height:150px\"></image>" // 嵌入受控本地图片
                + "</body></pdf>"; // 结束模板
        byte[] pdf = new JQuickPdfFactory().executeContent(template); // 执行渲染
        Files.write(Paths.get("image-report.pdf"), pdf); // 保存 PDF
    }
}

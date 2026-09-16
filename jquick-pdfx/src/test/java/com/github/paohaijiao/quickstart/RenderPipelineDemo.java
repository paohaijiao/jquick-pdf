package com.github.paohaijiao.quickstart;

import com.github.paohaijiao.executor.JQuickPdfFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RenderPipelineDemo {
    public static void main(String[] args) throws Exception {
        String template = "<pdf><body>"
                + "<h1>'运行链路示例'</h1>"
                + "<p>'请求编号：'${requestId}</p>"
                + "<p>'第一阶段：模板已进入解析流程。'</p>"
                + "<htmlPageBreak>'第二页'</htmlPageBreak>"
                + "<p>'第二阶段：分页节点之后继续布局和渲染。'</p>"
                + "</body></pdf>";

        // 绑定请求上下文并触发解析、布局和 PDF 输出
        byte[] pdf = new JQuickPdfFactory()
                .bind("requestId", "REQ-001")
                .executeContent(template);
        Files.write(Paths.get("d://test//pipeline.pdf"), pdf);
    }
}

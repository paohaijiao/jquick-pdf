package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import lombok.Data;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.util.List;

@Data
public class JQuickParagraphElementRender  implements JQuickElementRender {

    private List<JQuickElementRender> children;

    private float marginTop = 0;
    private float marginBottom = 0;

    public JQuickParagraphElementRender(List<JQuickElementRender> children) {
        this.children = children;
    }

    @Override
    public void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException {
        // 应用上边距
        float y = context.getCursorY() - marginTop;
        //绘制所有子元素
        for (JQuickElementRender child : children) {
            // 检查是否需要换页
            if (y < context.getMargins()[2]) { // bottom margin
                // TODO: 触发换页逻辑
                y = context.getPageHeight() - context.getMargins()[0];
                context.setCursorY(y);
            }

            // 更新子元素的 Y 坐标（流式布局）
            // 这里需要传入当前 Y 位置
            child.draw(stream, context);
            // 更新 Y 位置（子元素绘制完后会更新 cursorY）
            y = context.getCursorY();
        }
        // 应用下边距
        context.setCursorY(y - marginBottom);
    }
}

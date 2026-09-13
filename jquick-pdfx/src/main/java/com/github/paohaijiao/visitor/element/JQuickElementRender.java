package com.github.paohaijiao.visitor.element;

import com.github.paohaijiao.model.JStyleAttributes;
import com.github.paohaijiao.visitor.context.JQuickRenderContext;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public interface JQuickElementRender {

    void draw(PDPageContentStream stream, JQuickRenderContext context) throws IOException;

    /**
     * 元素自身的样式声明。用于 flex 行布局读取子元素声明的宽度等信息；
     * 无样式的纯容器元素（如段落包装）返回 {@code null}。
     * <p>
     * 带 {@code style} 字段的实现类由 Lombok 的 {@code @Data} 自动生成同名方法，无需额外实现。
     */
    default JStyleAttributes getStyle() {
        return null;
    }

}

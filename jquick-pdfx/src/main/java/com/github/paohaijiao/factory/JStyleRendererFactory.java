/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.factory;

import com.github.paohaijiao.render.JStyleRenderer;
import com.github.paohaijiao.render.impl.*;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.html2pdf.attach.impl.layout.PageCountElement;
import com.itextpdf.html2pdf.attach.impl.layout.PageTargetCountElement;
import com.itextpdf.html2pdf.attach.impl.layout.RunningElement;
import com.itextpdf.html2pdf.attach.impl.layout.form.element.*;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.render.impl
 *
 * @author Martin
 * @version 1.0.0
 * @className JStyleRendererFactory
 * @date 2025/6/27
 * @description
 */
public class JStyleRendererFactory {

    private static final Map<Class<?>, JStyleRenderer> renderers = new HashMap<>();

    static {
        renderers.put(AreaBreak.class, new JAreaBreakRender());
        renderers.put(Button.class, new JButtonRender());
        renderers.put(Cell.class, new JCellRender());
        renderers.put(CheckBox.class, new JCheckBoxRender());
        renderers.put(ComboBoxField.class, new JCombolBoxRender());
        renderers.put(FormField.class, new JFormRender());
        renderers.put(HtmlPageBreak.class, new JHtmlPageBreakRender());
        renderers.put(InputButton.class, new JInputButtonRender());
        renderers.put(InputField.class, new JInputFieldRender());
        renderers.put(LineSeparator.class, new JLineSeperaterRenderer());
        renderers.put(Link.class, new JLinkRenderer());
        renderers.put(List.class, new JListRenderer());
        renderers.put(ListBoxField.class, new JListBoxFieldRender());
        renderers.put(ListItem.class, new JListItemRenderer());
        renderers.put(PageCountElement.class, new JPageCountElementRender());
        renderers.put(PageTargetCountElement.class, new JPageTargetCountRender());
        renderers.put(Paragraph.class, new JParagraphRenderer());
        renderers.put(Radio.class, new JRadioRender());
        //renderers.put(FlexContainer.class, new JTextRenderer());
        renderers.put(Text.class, new JTextRenderer());
        renderers.put(RunningElement.class, new JRunningElementRenderer());
        renderers.put(Tab.class, new JTabRender());
        renderers.put(TextArea.class, new JTextAreaRender());

        renderers.put(Div.class, new JDivRenderer());
        renderers.put(Image.class, new JImageRenderer());
        renderers.put(Table.class, new JTableRenderer());
        renderers.put(Canvas.class, new JConvasRender());
        renderers.put(TabStop.class, new JTabTypeRenderer());
    }

    public static JStyleRenderer getRenderer(IElement element) {
        return renderers.getOrDefault(element.getClass(), new JDefautRenderer());
    }

    public static void registerRenderer(Class<?> elementClass, JStyleRenderer renderer) {
        renderers.put(elementClass, renderer);
    }
}

package com.github.paohaijiao.render;

import com.github.paohaijiao.model.JStyleAttributes;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IElement;

public interface JStyleRenderer {

    void applyStyles(Document doc, IElement element, JStyleAttributes styles);
}

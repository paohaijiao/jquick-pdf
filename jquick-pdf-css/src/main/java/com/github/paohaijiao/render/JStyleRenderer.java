package com.github.paohaijiao.render;

import com.github.paohaijiao.model.JStyleAttributes;
import com.itextpdf.layout.element.IElement;

public interface JStyleRenderer {

    void applyStyles(IElement element, JStyleAttributes styles);
}

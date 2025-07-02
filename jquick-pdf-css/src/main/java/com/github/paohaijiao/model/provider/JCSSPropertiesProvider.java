package com.github.paohaijiao.model.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.itextpdf.layout.element.BlockElement;

public interface JCSSPropertiesProvider {

    public  void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) ;
    }

package com.github.paohaijiao.model.provider;

import com.github.paohaijiao.model.css.JCSSPropertiesCoreModel;
import com.itextpdf.layout.element.BlockElement;

import java.net.MalformedURLException;

public interface JCSSPropertiesProvider {

    public  void applyCssProperties(BlockElement<?> element, JCSSPropertiesCoreModel cssProperties) throws MalformedURLException;
    }

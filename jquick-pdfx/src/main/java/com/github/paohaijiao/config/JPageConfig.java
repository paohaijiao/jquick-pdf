/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package com.github.paohaijiao.config;

import com.github.paohaijiao.visitor.JPdfXCoreVisitor;
import lombok.Data;

@Data
public class JPageConfig {

    private JPdfXCoreVisitor.PageSize pageSize = JPdfXCoreVisitor.PageSize.A4;

    private float[] margins = {50, 36, 50, 36};

    private boolean autoPageBreak = true;

    private PageOrientation orientation = PageOrientation.PORTRAIT;

    public enum PageOrientation {
        PORTRAIT, LANDSCAPE
    }
}

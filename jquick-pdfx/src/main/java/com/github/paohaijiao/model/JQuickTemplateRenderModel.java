package com.github.paohaijiao.model;

import lombok.Data;

/**
 * Template render payload for PDFBox-based html rendering.
 */
@Data
public class JQuickTemplateRenderModel {

    private String templateKey;

    private String html;

    private String baseUri;

    private String fontPath;

    private boolean pageBreakBefore = true;

    private boolean pageBreakAfter = true;
}

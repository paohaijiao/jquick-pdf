package com.github.paohaijiao.model;

import com.github.paohaijiao.enums.JBorder;
import com.itextpdf.kernel.colors.Color;
import lombok.Data;

@Data
public class JBorderModel {


    private JBorder border;

    private Float width;

    private Color color;
}

package com.paohaijiao.data;

import com.paohaijiao.data.code.JRoam;
import com.paohaijiao.data.style.JItemStyle;
import lombok.Getter;
import lombok.Setter;

/**
 * @author martin
 */
@Getter
@Setter
public class JGeo extends JBasic<JGeo> implements JComponent {
    private String map;
    private JRoam roam;
    private JItemStyle label;
    private JItemStyle itemStyle;

    public String map() {
        return this.map;
    }

    public JGeo map(String map) {
        this.map = map;
        return this;
    }

    public JRoam roam() {
        return this.roam;
    }

    public JGeo roam(JRoam roam) {
        this.roam = roam;
        return this;
    }

    public JItemStyle label() {
        if (this.label == null) {
            this.label = new JItemStyle();
        }
        return this.label;
    }

    public JGeo label(JItemStyle label) {
        this.label = label;
        return this;
    }

    public JItemStyle itemStyle() {
        if (this.itemStyle == null) {
            this.itemStyle = new JItemStyle();
        }
        return this.itemStyle;
    }

    public JGeo itemStyle(JItemStyle itemStyle) {
        this.itemStyle = itemStyle;
        return this;
    }
}

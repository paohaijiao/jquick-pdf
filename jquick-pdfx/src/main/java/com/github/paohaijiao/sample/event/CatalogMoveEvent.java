package com.github.paohaijiao.sample.event;

import com.github.paohaijiao.sample.ReportConstant;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;

import java.util.Properties;

/**
 * @author laoliangliang
 * @since 2020-05-24 13:53
 */
public class CatalogMoveEvent implements IEventHandler {

    private int startPage = 7;
    private int pageSize = 0;
    private Properties properties;

    public CatalogMoveEvent(Properties properties) {
        this.properties = properties;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void handleEvent(Event event) {
        pageSize++;
        properties.setProperty(ReportConstant.CATALOG_SIZE, pageSize + "");
    }
}

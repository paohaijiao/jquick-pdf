package com.github.paohaijiao.event;
import com.github.paohaijiao.config.JCatalogConfig;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;

public class JTocEventHandler implements IEventHandler{

    private PdfOutline rootOutline;

    private JCatalogConfig  config;

    public JTocEventHandler(PdfDocument pdfDoc, JCatalogConfig pdfConfig) {
        this.rootOutline = pdfDoc.getOutlines(false);
        this.config = pdfConfig;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        if (config.getStartPage() == 2) {
            PdfDestination dest = PdfExplicitDestination.createFit(page);
            PdfOutline outline = rootOutline.addOutline("重要章节");
            outline.addDestination(dest);
        }
        config.setStartPage(config.getStartPage()+1);
    }
}

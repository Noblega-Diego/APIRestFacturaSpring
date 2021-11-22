package com.progIII.exam_api.utils;

import com.lowagie.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author diego
 */
@Component
public class GeneratePDF {
    @Autowired
    private TemplateEngine templateEngine;
    
    public ByteArrayOutputStream generatePdfByThymaleaft(String templete, IContext context){
        String htmlTemplete = templateEngine.process(templete, context);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlTemplete);
        renderer.layout();
        try {
            renderer.createPDF(baos);
            baos.close();
        } catch (DocumentException ex) {
        } catch (IOException ex) {
        }
        return baos;
    }
}

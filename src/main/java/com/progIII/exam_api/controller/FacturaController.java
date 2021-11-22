package com.progIII.exam_api.controller;

import com.lowagie.text.DocumentException;
import com.progIII.exam_api.models.Factura;
import com.progIII.exam_api.repository.FacturaRespository;
import com.progIII.exam_api.utils.GeneratePDF;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author diego
 */
@Controller()
public class FacturaController {
    
    @Autowired
    private FacturaRespository facturaRepository;
    
    @Autowired
    private GeneratePDF generaePDF;
    
    
    @GetMapping(value = "facturas")
    public String getFacturas(Model model){
        List<Factura> facturas = facturaRepository.getFacturas();
        model.addAttribute("facturas", facturas);
        return "facturas";
    }
    
    @GetMapping(value = "factura/{id}")
    public String getFactura(@PathVariable long id, Model model){
        Factura factura = facturaRepository.getFactura(id);
        model.addAttribute("factura", factura);
        return "detallefactura";
    }
    
    @GetMapping(value = "factura/pdf/{id}")
    public ResponseEntity<?> getFacturaPDF(@PathVariable long id){
        ByteArrayOutputStream baos;
        Factura factura = facturaRepository.getFactura(id);
        if(factura != null){
            String fileName = "factura_" + id + ".pdf";
            Context context = new Context();
            context.setVariable("factura", factura);
            baos = generaePDF.generatePdfByThymaleaft("detallefactura", context);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(baos.toByteArray());
        }else
        return ResponseEntity.status(404).build();
    }
}

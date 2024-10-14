/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.reportes.controller.impl;

import org.pe.mpc.api.reportes.controller.inter.InterControllerReportParametria;
import org.pe.mpc.api.reportes.handler.inter.InterHandlerReportParametria;
import java.io.IOException;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reportes/parametria")
public class ControllerReportParametria implements InterControllerReportParametria{
    
    @Autowired
    private InterHandlerReportParametria report;
    
    @PostMapping(value = "/pdf", produces = {MediaType.APPLICATION_PDF_VALUE})
    @Override
    public ResponseEntity<InputStreamResource> exportReportParametriaPdf() throws JRException, IOException,Exception {
        InputStream input = report.createReportParametriaPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "inline; filename=reportParametria.pdf");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(input));
    }
    
    @PostMapping(value = "/excel")
    @Override
    public ResponseEntity<InputStreamResource> exportReportParametriaExcel() throws JRException, IOException ,Exception{
        InputStream input = report.createReportParametriaXls();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=reportParametria.xlsx");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).body(new InputStreamResource(input));
    }
    
}

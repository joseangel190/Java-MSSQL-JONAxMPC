/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.reportes.controller.impl;

import org.pe.mpc.api.reportes.controller.inter.InterControllerReportEcho;
import org.pe.mpc.api.reportes.handler.inter.InterHandlerReportEcho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/reportes/echo")
public class ControllerReportEcho implements InterControllerReportEcho {

    @Autowired
    private InterHandlerReportEcho report;

    @GetMapping(value = "/pdf", produces = {MediaType.APPLICATION_PDF_VALUE})
    @Override
    public ResponseEntity<InputStreamResource> exportReportEchoPdf() throws JRException, IOException {
        InputStream input = report.createReportEchoPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "inline; filename=reportEcho.pdf");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(input));
    }

    @GetMapping(value = "/excel")
    @Override
    public ResponseEntity<InputStreamResource> exportReportEchoExcel() throws JRException, IOException {
        InputStream input = report.createReportEchoXls();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=reportEcho.xlsx");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).body(new InputStreamResource(input));
    }
}

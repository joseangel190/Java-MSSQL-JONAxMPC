/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.pe.mpc.api.reportes.controller.inter;

import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface InterControllerReportParametria {

    ResponseEntity<InputStreamResource> exportReportParametriaPdf() throws JRException, IOException,Exception;
    
    ResponseEntity<InputStreamResource> exportReportParametriaExcel() throws JRException, IOException,Exception;
}

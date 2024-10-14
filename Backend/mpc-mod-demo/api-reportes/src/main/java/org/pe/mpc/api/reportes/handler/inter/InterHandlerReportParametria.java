/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.pe.mpc.api.reportes.handler.inter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;

public interface InterHandlerReportParametria {

    ByteArrayInputStream createReportParametriaPdf() throws JRException, IOException, Exception;

    ByteArrayInputStream createReportParametriaXls() throws JRException, IOException, Exception;
}

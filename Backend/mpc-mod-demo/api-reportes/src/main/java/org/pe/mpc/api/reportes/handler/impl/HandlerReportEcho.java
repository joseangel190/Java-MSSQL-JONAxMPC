/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.reportes.handler.impl;

import org.pe.mpc.api.reportes.handler.inter.InterHandlerReportEcho;
import org.pe.mpc.demo.dto.reports.PathReports;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Service
public class HandlerReportEcho implements InterHandlerReportEcho {

    @Override
    public ByteArrayInputStream createReportEchoPdf() throws JRException, IOException {
        ByteArrayOutputStream out = null;
        try {
            String subDirectorio = PathReports.DIRECTORYJAR + PathReports.DIRECTORYREPORTSDEFAULT;
            Resource resource = new FileSystemResource(subDirectorio + "reportEcho.jasper");
            JasperReport jasper = (JasperReport) JRLoader.loadObject(resource.getFile());
            Map<String, Object> parameters = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters);
            out = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            SimpleExporterInput simpleInput = new SimpleExporterInput(jasperPrint);
            SimpleOutputStreamExporterOutput simpleOut = new SimpleOutputStreamExporterOutput(out);

            exporter.setExporterInput(simpleInput);
            exporter.setExporterOutput(simpleOut);
            exporter.exportReport();
            ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
            return input;
        } catch (JRException | IOException ex) {
            throw ex;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    @Override
    public ByteArrayInputStream createReportEchoXls() throws JRException, IOException {
        ByteArrayOutputStream out = null;
        try {
            String subDirectorio = PathReports.DIRECTORYJAR + PathReports.DIRECTORYREPORTSDEFAULT;
            Resource resource = new FileSystemResource(subDirectorio + "reportEcho.jasper");
            JasperReport jasper = (JasperReport) JRLoader.loadObject(resource.getFile());
            Map<String, Object> parameters = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters);
            out = new ByteArrayOutputStream();
            JasperPrintOut jpu = new JasperPrintOut(jasperPrint, out);
            JasperPrintOut reportData = exportToExcel(jpu);
            return new ByteArrayInputStream(reportData.getOut().toByteArray());
        } catch (JRException | IOException ex) {
            throw ex;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    private JasperPrintOut exportToExcel(JasperPrintOut reportData) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(reportData.getJasperPrint()));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportData.getOut()));
        exporter.exportReport();
        return reportData;
    }
}

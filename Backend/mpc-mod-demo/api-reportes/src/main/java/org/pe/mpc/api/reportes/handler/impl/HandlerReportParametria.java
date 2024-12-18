/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.reportes.handler.impl;

import org.springframework.stereotype.Service;
import org.pe.mpc.api.reportes.handler.inter.InterHandlerReportParametria;
import org.pe.mpc.demo.dto.reports.PathReports;
import org.pe.mpc.process.mantenimiento.inter.InterProcessParametria;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.pe.mpc.demo.entity.Parametria;
import java.util.List;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Service
public class HandlerReportParametria implements InterHandlerReportParametria {

    @Autowired
    private InterProcessParametria process;

    @Override
    public ByteArrayInputStream createReportParametriaPdf() throws JRException, IOException, Exception {
        ByteArrayOutputStream out = null;
        try {
            String subDirectorio = PathReports.DIRECTORYJAR + PathReports.DIRECTORYREPORTSDEFAULT;
            Resource resource = new FileSystemResource(subDirectorio + "reportParametrias.jasper");
            JasperReport jasper = (JasperReport) JRLoader.loadObject(resource.getFile());
            Map<String, Object> parameters = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, this.createDsMain());
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
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    @Override
    public ByteArrayInputStream createReportParametriaXls() throws JRException, IOException, Exception {
        ByteArrayOutputStream out = null;
        try {
            String subDirectorio = PathReports.DIRECTORYJAR + PathReports.DIRECTORYREPORTSDEFAULT;
            Resource resource = new FileSystemResource(subDirectorio + "reportParametrias.jasper");
            JasperReport jasper = (JasperReport) JRLoader.loadObject(resource.getFile());
            Map<String, Object> parameters = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, this.createDsMain());
            out = new ByteArrayOutputStream();
            JasperPrintOut jpu = new JasperPrintOut(jasperPrint, out);
            JasperPrintOut reportData = exportToExcel(jpu);
            return new ByteArrayInputStream(reportData.getOut().toByteArray());
        } catch (JRException | IOException ex) {
            throw ex;
        } catch (Exception ex) {
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

    private JRBeanCollectionDataSource createDsMain() throws Exception {
        List<Parametria> listMain = process.listProcedure();
        JRBeanCollectionDataSource dataSourceMain = new JRBeanCollectionDataSource(listMain);
        return dataSourceMain;
    }
}

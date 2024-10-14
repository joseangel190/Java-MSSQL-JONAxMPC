/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.reportes.handler.impl;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperPrintOut implements Serializable {

    private JasperPrint jasperPrint;
    private ByteArrayOutputStream out;

    public JasperPrintOut(JasperPrint jasperPrint, ByteArrayOutputStream out) {
        this.jasperPrint = jasperPrint;
        this.out = out;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public ByteArrayOutputStream getOut() {
        return out;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.dto.beans;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jtorresb
 */
@Data
public class DtoConnection implements Serializable{
    public static String host;
    public static Integer port;
    public static String nameDatabase;
    public static String userDatabase;
    public static String passwordDatabase;
}

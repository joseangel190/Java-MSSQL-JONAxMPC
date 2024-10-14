/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jofrantoba.model.jpa.daoentity.test.sqlserver.daoparametria;

import com.jofrantoba.model.jpa.daoentity.test.GlobalEntityPkNumeric;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author apoyo1953
 */
@Log4j2
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(catalog="demo",schema="demo",name = "MaeParametrias")
public class Parametria extends GlobalEntityPkNumeric implements Serializable{
    
}

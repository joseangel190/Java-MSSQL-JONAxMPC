/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 *
 * @author jtorresb
 */
@Data
@MappedSuperclass
public abstract class GlobalEntityPkNumeric implements Serializable{
    @Id    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "biId")  
    protected Long id;
    @Column(name = "biVersion")
    protected Long version;  
    @Column(name = "bIsPersistente")
    protected Boolean isPersistente;
    @Column(name = "vTag")
    protected String tag;    
    @Column(name = "vCodigo")
    protected String codigo;
    @Column(name = "vCodigoRandom")
    protected String codigoRandom;
    @JsonIgnore
    @Column(name = "dt2MarcaTiempo")
    private LocalDateTime marcaTiempo;
    @Transient   
    protected String operacion;
    @Transient          
    protected String usuario;
}
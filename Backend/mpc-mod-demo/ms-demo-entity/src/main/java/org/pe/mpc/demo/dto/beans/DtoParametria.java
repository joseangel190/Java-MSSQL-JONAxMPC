/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.dto.beans;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DtoParametria implements Serializable {

    private Long id;
    private Long version;
    private Boolean isPersistente;
    private String descripcion;
    private String abreviatura;
    private String codigo;
    private String codigoRandom;
    private Long orden;
    private Long idParametriaPadre;
    private String tag;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime marcaTiempo;
}

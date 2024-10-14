/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author apoyo1953
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Component
@Scope("prototype")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(catalog = "DEMO", schema = "dbo", name = "MaeParametrias")
public class Parametria extends GlobalEntityPkNumeric implements Serializable {
    @NotEmpty
    @Column(name = "vDescripcion")
    private String descripcion;
    @Column(name = "vAbreviatura")
    private String abreviatura;
    @Column(name = "biOrden")
    private Long orden;
    @JsonIgnoreProperties({"parent","children"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biIdParametriaPadre", referencedColumnName = "biId")
    private Parametria parent;
    @JsonIgnoreProperties({"parent","children"})    
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Parametria> children;
        
}

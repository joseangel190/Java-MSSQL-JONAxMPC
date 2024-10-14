/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.pe.mpc.api.mantenimientos.controller.inter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author apoyo1953
 */
public interface InterControllerParametria {

    ResponseEntity<String> save(Parametria entidad, HttpServletRequest request) throws Exception;

    ResponseEntity<String> update(Parametria entidad, HttpServletRequest request) throws Exception;

    ResponseEntity<String> delete(DtoParametria entidad, HttpServletRequest request) throws Exception;

    Collection<Parametria> parents() throws Exception;

    Collection<Parametria> childrens() throws Exception;

    Collection<Parametria> childrensByParents(JsonNode requestJson) throws Exception;

    Parametria getEntityByIdWithChildrens(JsonNode requestJson) throws Exception;

    Parametria getEntityByIdWithoutChildrens(JsonNode requestJson) throws Exception;

    ResponseEntity<?> getEntityById(JsonNode requestJson) throws Exception;

    //List<Parametria> listar() throws Exception;
    ResponseEntity<?> listar() throws Exception;

    ResponseEntity<?> listar(Long limit, Long offSet) throws Exception;

    ResponseEntity<String> saveProcedure(DtoParametria entidad, HttpServletRequest request) throws Exception;
    
    ResponseEntity<String> updateProcedure(DtoParametria entidad, HttpServletRequest request) throws Exception;
}

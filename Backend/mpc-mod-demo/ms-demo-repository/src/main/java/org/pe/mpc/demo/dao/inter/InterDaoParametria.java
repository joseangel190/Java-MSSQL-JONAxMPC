/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.pe.mpc.demo.dao.inter;

import com.jofrantoba.model.jpa.daoentity.InterCrud;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import java.util.List;

/**
 *
 * @author apoyo1953
 */
public interface InterDaoParametria extends InterCrud<Parametria> {

    DtoParametria insertProcedureJson(DtoParametria bean) throws Exception;

    DtoParametria updateProcedureJson(DtoParametria bean) throws Exception;
    
    void deleteProcedureJson(DtoParametria bean) throws Exception;

    List<Parametria> listProcedure() throws Exception;

    List<Parametria> listProcedurePaginacion(Long limit, Long offSet) throws Exception;
}

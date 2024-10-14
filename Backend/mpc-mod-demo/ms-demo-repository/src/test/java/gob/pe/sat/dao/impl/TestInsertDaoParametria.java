/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.sat.dao.impl;

import org.pe.mpc.demo.dao.impl.DaoParametria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofrantoba.model.jpa.daoentity.ParameterProcedure;
import org.pe.mpc.demo.dao.inter.InterDaoParametria;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import jakarta.persistence.ParameterMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 *
 * @author Sergio
 */
public class TestInsertDaoParametria extends TestBaseDao {

    /*private void marcarTiempo(Parametria entity) {
        ZoneId zone = ZoneId.of("America/Lima");
        LocalDateTime now = LocalDateTime.now(zone);
        entity.setMarcaTiempo(now);
    }

    @Test
    public void createEntity1() throws Exception {
        Parametria entity = contextEntity.getBean(Parametria.class);
        InterDaoParametria dao = contextDao.getBean(DaoParametria.class);
        entity.setIsPersistente(Boolean.TRUE);
        entity.setVersion((new Date()).getTime());        
        entity.setDescripcion("TITULAR CATASTRAL"); 
        marcarTiempo(entity);        
        Transaction tx = dao.getSession().beginTransaction();
        //dao.save(entity);
        dao.iudProcedure(entity);
        tx.commit();
    }*/
 /*@Test
    public void execProcedureJson() throws Exception {
        InterDaoParametria dao = contextDao.getBean(DaoParametria.class);
        Transaction tx = dao.getSession().beginTransaction();
        //dao.save(entity);
        dao.iudProcedureJson("InsertParametriaJson", "{\"biVersion\": 1726903649875,\"bIsPersistente\": 1,\"vDescripcion\": \"prueba json Hibernate\",\"vAbreviatura\": \"abrJson\",\"dt2MarcaTiempo\": \"2024-09-23 01:10:57.2625285\"}");
        tx.commit();
    }*/
 /*@Test
    public void execProcedure() throws Exception {
        InterDaoParametria dao = contextDao.getBean(DaoParametria.class);
        Transaction tx = dao.getSession().beginTransaction();
        List<ParameterProcedure> lstParametria = new ArrayList();
        lstParametria.add(new ParameterProcedure("id", Long.class, ParameterMode.IN, 5));
        lstParametria.add(new ParameterProcedure("descripcion", String.class, ParameterMode.IN, "test update spDin"));
        //lstParametria.add(new ParameterProcedure("id_out", Long.class, ParameterMode.OUT));
        dao.iudProcedure("UpdateParametria", lstParametria);
        tx.commit();
    }*/
    @Test
    public void execProcedureList() throws Exception {
        InterDaoParametria dao = contextDao.getBean(DaoParametria.class);
        Transaction tx = dao.getSession().beginTransaction();
        Map<String, Object> mapParameter = new HashMap();
        mapParameter.put("id", 1L);
        List<Parametria> result = dao.listProcedureMsql("EXEC ListParametriaFilter :id", mapParameter);
        for (int i = 0; i < result.size(); i++) {
            Parametria stock = result.get(i);
            System.out.println(stock.getId());
        }
        tx.commit();
    }
}

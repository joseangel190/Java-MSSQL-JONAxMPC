/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofrantoba.model.jpa.daoentity.AbstractJpaDao;
import org.pe.mpc.demo.dao.inter.InterDaoParametria;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apoyo1953
 */
@Slf4j
@Repository
public class DaoParametria extends AbstractJpaDao<Parametria> implements InterDaoParametria {

    @Autowired(required = false)
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        if (this.getSessionFactory() == null && sessionFactory != null) {
            this.setSessionFactory(sessionFactory);
        }
    }

    public DaoParametria() {
        super();
        this.setClazz(Parametria.class);
        //this.setSessionFactory(sessionFactory);
    }

    @Override
    public DtoParametria insertProcedureJson(DtoParametria bean) throws Exception {
        String jsonString = (new ObjectMapper()).writeValueAsString(bean);
        log.info("insertProcedureJson: " + jsonString);
        Long id = this.iudProcedureJson("InsertParametriaJson", jsonString);
        bean.setId(id);
        return bean;
    }

    @Override
    public DtoParametria updateProcedureJson(DtoParametria bean) throws Exception {
        String jsonString = (new ObjectMapper()).writeValueAsString(bean);
        log.info("updateProcedureJson: " + jsonString);
        this.iudProcedureJson("updateParametriaJson", jsonString);
        return bean;
    }
    
    @Override
    public void deleteProcedureJson(DtoParametria bean) throws Exception {
        String jsonString = (new ObjectMapper()).writeValueAsString(bean);
        log.info("deleteProcedureJson: " + jsonString);
        this.iudProcedureJson("deleteParametriaJson", jsonString);
    }

    @Override
    public List<Parametria> listProcedure() throws Exception {
        return this.listProcedureMsql("EXEC ListParametria", new HashMap());
    }

    @Override
    public List<Parametria> listProcedurePaginacion(Long limit, Long offSet) throws Exception {
        Map<String, Object> mapParameter = new HashMap();
        mapParameter.put("limit", limit);
        mapParameter.put("offset", offSet);
        return this.listProcedureMsql("EXEC ListParametriaLimit :limit,:offset", mapParameter);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.process.mantenimiento.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import org.pe.mpc.demo.dao.impl.DaoParametria;
import org.pe.mpc.demo.dao.inter.InterDaoParametria;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import org.pe.mpc.demo.service.impl.ServiceParametria;
import org.pe.mpc.demo.service.inter.InterServiceParametria;
import org.pe.mpc.process.mantenimiento.inter.InterProcessParametria;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author apoyo1953
 */
@Slf4j
@Service
public class ProcessParametria implements InterProcessParametria {

    @Autowired
    private Environment environment;

    @Autowired(required = false)
    private MantProcessSatInterfaceServiceImpl ctxEntDaoService;

    @Override
    public void insert(Parametria entity) throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Parametria entidad = service.insert(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void insertProcedureJson(DtoParametria entity) throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            DtoParametria entidad = service.insertProcedureJson(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void updateProcedureJson(DtoParametria entity) throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            DtoParametria entidad = service.updateProcedureJson(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }
    @Override
    public void deleteProcedureJson(DtoParametria entity) throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            service.deleteProcedureJson(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }

    @Override
    public List<Parametria> listProcedure() throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            List<Parametria> list = service.listProcedure();
            tx.commit();
            return list;
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }

    @Override
    public List<Parametria> listProcedurePaginacion(Long limit, Long offSet) throws Exception {
        InterDaoParametria dao = ctxEntDaoService.contextDao.getBean(DaoParametria.class);
        InterServiceParametria service = ctxEntDaoService.contextService.getBean(ServiceParametria.class);
        Session sesion = dao.getSession();
        Transaction tx = sesion.beginTransaction();
        try {
            List<Parametria> list = service.listProcedurePaginacion(limit, offSet);
            tx.commit();
            return list;
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            if (environment.getProperty("environment") != null && environment.getProperty("environment").equalsIgnoreCase("dev")) {
                UnknownException excepcion = new UnknownException(ProcessParametria.class, ex.getMessage(), ex);
                excepcion.traceLog(true);
            }
            throw ex;
        } finally {
            sesion.close();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.service.impl;

import org.pe.mpc.demo.dao.impl.DaoParametria;
import org.pe.mpc.demo.dao.inter.InterDaoParametria;
import org.pe.mpc.demo.dto.beans.DtoParametria;
import org.pe.mpc.demo.entity.Parametria;
import org.pe.mpc.demo.service.inter.InterServiceParametria;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author apoyo1953
 */
@Slf4j
@Service
public class ServiceParametria implements InterServiceParametria {

    @Autowired
    private ServiceInterfaceDaoImpl ctxEntDao;

    @Override
    public Parametria insert(Parametria entidad) throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        entidad.setIsPersistente(Boolean.TRUE);
        entidad.setVersion((new Date()).getTime());
        entidad.setOperacion("INSERT");
        marcarTiempo(entidad);
        if (entidad.getParent() != null) {
            entidad.setParent(dao.findById(entidad.getParent().getId()));
            if (!entidad.getParent().getIsPersistente()) {
                throw new Exception(String.format("Parametria padre %s esta eliminada", entidad.getParent().getDescripcion()));
            }
        }
        dao.save(entidad);
        return entidad;
    }

    private void marcarTiempo(Parametria entity) {
        ZoneId zone = ZoneId.of("America/Lima");
        LocalDateTime now = LocalDateTime.now(zone);
        entity.setMarcaTiempo(now);
    }

    private LocalDateTime getMarcarTiempo() {
        ZoneId zone = ZoneId.of("America/Lima");
        return LocalDateTime.now(zone);
    }

    @Override
    public DtoParametria insertProcedureJson(DtoParametria entidad) throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        entidad.setIsPersistente(Boolean.TRUE);
        entidad.setVersion((new Date()).getTime());
        entidad.setMarcaTiempo(this.getMarcarTiempo());
        if (entidad.getIdParametriaPadre() != null) {
            Parametria paramPadre = dao.findById(entidad.getIdParametriaPadre());
            if (!paramPadre.getIsPersistente()) {
                throw new Exception(String.format("Parametria padre %s esta eliminada", paramPadre.getDescripcion()));
            }
        }
        dao.insertProcedureJson(entidad);
        return entidad;
    }

    @Override
    public DtoParametria updateProcedureJson(DtoParametria entidad) throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        Parametria entity = dao.findById(entidad.getId());
        if (entity == null || !entity.getIsPersistente()) {
            throw new Exception(String.format("Parametria  con Id %d no existe o esta eliminada", entidad.getId()));
        }
        entidad.setVersion((new Date()).getTime());
        entidad.setMarcaTiempo(this.getMarcarTiempo());
        if (entidad.getIdParametriaPadre() != null) {
            Parametria paramPadre = dao.findById(entidad.getIdParametriaPadre());
            if (!paramPadre.getIsPersistente()) {
                throw new Exception(String.format("Parametria padre %s esta eliminada", paramPadre.getDescripcion()));
            }
        }
        dao.updateProcedureJson(entidad);
        return entidad;
    }
    @Override
    public void deleteProcedureJson(DtoParametria entidad) throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        Parametria entity = dao.findById(entidad.getId());
        if (entity == null) {
            throw new Exception(String.format("Parametria  con Id %d no existe o esta eliminada", entidad.getId()));
        }
        dao.deleteProcedureJson(entidad);
    }

    @Override
    public List<Parametria> listProcedure() throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        return dao.listProcedure();
    }

    @Override
    public List<Parametria> listProcedurePaginacion(Long limit, Long offSet) throws Exception {
        InterDaoParametria dao = ctxEntDao.contextDao.getBean(DaoParametria.class);
        return dao.listProcedurePaginacion(limit, offSet);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.demo.service.impl;

import org.pe.mpc.demo.config.ConfigDao;
import org.pe.mpc.demo.config.ConfigEntity;
import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jtorresb
 */
@Getter
@Configuration
public class ServiceInterfaceDaoImpl {
    protected AnnotationConfigApplicationContext contextEntity = new AnnotationConfigApplicationContext(ConfigEntity.class);    
    protected AnnotationConfigApplicationContext contextDao= new AnnotationConfigApplicationContext(ConfigDao.class);
    
}

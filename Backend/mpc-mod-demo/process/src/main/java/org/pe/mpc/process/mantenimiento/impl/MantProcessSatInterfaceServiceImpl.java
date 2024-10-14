/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.process.mantenimiento.impl;

import org.pe.mpc.demo.config.ConfigDao;
import org.pe.mpc.demo.config.ConfigEntity;
import org.pe.mpc.demo.config.ConfigService;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jtorresb
 */

@Getter
@Configuration
@ConditionalOnProperty(
    value="enabled.database.mpc", 
    havingValue = "true")
public class MantProcessSatInterfaceServiceImpl {
    public  AnnotationConfigApplicationContext contextEntity = new AnnotationConfigApplicationContext(ConfigEntity.class);
    public AnnotationConfigApplicationContext contextDao = new AnnotationConfigApplicationContext(ConfigDao.class);
    public AnnotationConfigApplicationContext contextService = new AnnotationConfigApplicationContext(ConfigService.class);   
}

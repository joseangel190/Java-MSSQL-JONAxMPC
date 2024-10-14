/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author apoyo1953
 */
@RefreshScope
@SpringBootApplication
@EnableEurekaServer
public class Application {
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

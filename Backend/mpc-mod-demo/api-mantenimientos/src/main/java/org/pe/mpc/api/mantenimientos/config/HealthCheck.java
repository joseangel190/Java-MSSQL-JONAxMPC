/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.mantenimientos.config;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author jtorresb
 */
@Slf4j
@Component
public class HealthCheck implements HealthCheckHandler {

    private int counter = -1;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus is) {
        counter++;
        switch (counter) {
            case 0:
                return is.OUT_OF_SERVICE;
            case 1:
                return is.DOWN;
            case 2:
                return is.STARTING;
            case 3:
                return is.UNKNOWN;
            default:
                return is.UP;
        }
    }

}

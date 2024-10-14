/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.api.mantenimientos.config;

import com.jofrantoba.model.jpa.shared.UnknownException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author jtorresb
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Value("${async.task.corePoolSize}")
    Integer corePoolSize;
    
    @Value("${async.task.maxPoolSize}")
    Integer maxPoolSize;
    
    @Value("${async.task.queueCapacity}")
    Integer queueCapacity;

    @Value("${async.task.threadName}")
    String threadName;

    @Bean(name = "mantIclAsyncTask")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadName);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable thrwbl, Method method, Object... os) {
                new UnknownException(this.getClass(), thrwbl.getMessage(), thrwbl).traceLog(true);
            }
        };

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.gateway.config;

import org.pe.mpc.gateway.util.UtilJwt;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 *
 * @author jtorresb
 */
@Slf4j
@Configuration
public class WebSocketConfig {

    @Autowired
    private Environment environment;
    
    @Value("${spring.cloud.gateway.httpclient.api-mantenimientos.response-timeout}")
    Long timeOutApiMantenimientos;
    
    @Value("${spring.cloud.gateway.httpclient.api-reportes.response-timeout}")
    Long timeOutApiReportes;
    
    @Value("${spring.cloud.gateway.httpclient.api-files-s1.response-timeout}")
    Long timeOutApiFiles;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, ResponseTimeoutFilter timeoutFilter) {
        return builder.routes()
                .route("api-mantenimientos", r -> r.path("/api/mantenimientos/**")
                .filters(f -> f.filter(timeoutFilter.apply(new ResponseTimeoutFilter.Config() {
            {
                setTimeout(timeOutApiMantenimientos); // 3 seconds timeout
            }
        })))
                .uri("lb://api-mantenimientos-" + environment.getProperty("environment")))
                .route("api-reportes", r -> r.path("/api/reportes/**")
                .filters(f -> f.filter(timeoutFilter.apply(new ResponseTimeoutFilter.Config() {
            {
                setTimeout(timeOutApiReportes); // 600 seconds timeout
            }
        })))
                .uri("lb://api-reportes-" + environment.getProperty("environment")))
                // Files route
                .route("api-files-s1", r -> r.path("/api/files/s1/**")
                .filters(f -> f.filter(timeoutFilter.apply(new ResponseTimeoutFilter.Config() {
            {
                setTimeout(timeOutApiFiles); // 600 seconds timeout
            }
        })))
                .uri("lb://api-files-s1-" + environment.getProperty("environment")))
                .route("websocket_route", r -> r.path("/api/files/s1/sockupload")
                .filters(f -> f.rewritePath("/api/files/s1/sockupload/(?<segment>.*)", "/${segment}")
                .filter((exchange, chain) -> {
                    log.info("Global websocket Pre Filter executed");
                    ServerHttpRequest request = exchange.getRequest();
                    String authorizationHeader = request.getHeaders().getFirst("Authorization");
                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        String token = authorizationHeader.substring(7);
                        Map<String, Object> mapValues = extractDataFromToken(token);
                        if (mapValues != null) {
                            for (Map.Entry<String, Object> entry : mapValues.entrySet()) {
                                log.info("Clave: {}, Valor: {}", entry.getKey(), entry.getValue());
                            }
                            ServerHttpRequest modifiedRequest = request.mutate()
                                    .header("X-User-Email", mapValues.get("email").toString())
                                    .header("X-Id-User-Entity", mapValues.get("sub").toString())
                                    .header("X-Client-App", mapValues.get("azp").toString())
                                    .header("X-RealmId", mapValues.get("realmid").toString())
                                    .header("X-ClientId", mapValues.get("clientId").toString())
                                    .header("X-IdSistema", mapValues.get("idSistema").toString())
                                    .header("X-IdCliente", mapValues.get("idCliente").toString())
                                    .header("X-Session-Id", mapValues.get("sid").toString())
                                    .build();
                            exchange = exchange.mutate().request(modifiedRequest).build();
                        } else {
                            log.error("Unable to extract data from token: {}", token);
                        }
                    }
                    return chain.filter(exchange);
                })
                )
                .uri("lb://api-files-s1-" + environment.getProperty("environment")))
                .build();
    }

    private Map<String, Object> extractDataFromToken(String token) {
        UtilJwt util = new UtilJwt();
        return util.extractDataFromToken(token);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.gateway.filters;

import org.pe.mpc.gateway.util.UtilJwt;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author jtorresb
 */
@Slf4j
@Component
public class GlobalPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global Pre Filter executed");
        ServerHttpRequest request = exchange.getRequest();
        String authorizationHeader = request.getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Map<String,Object> mapValues = extractDataFromToken(token);
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
    }

    private Map<String,Object> extractDataFromToken(String token) {
        UtilJwt util=new UtilJwt();
        return util.extractDataFromToken(token);
    }

}

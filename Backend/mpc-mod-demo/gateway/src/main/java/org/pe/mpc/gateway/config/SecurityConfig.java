/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.gateway.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import reactor.core.publisher.Mono;

/**
 *
 * @author jtorresb
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    String secret;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        /*
        .pathMatchers("/api/mantenimientos/**").hasAuthority("web_catastro_api_mantenimientos")
                .pathMatchers("/api/reportes/**").hasAuthority("web_catastro_api_reportes")
         */
        http
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .authenticationEntryPoint(authenticationEntryPoint())
                .opaqueToken()
                .introspectionUri(introspectionUri)
                .introspectionClientCredentials(clientId, secret);
        http.csrf().disable();
        return http.build();
    }
    
    @Bean
    public ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, exception) -> {
            HttpStatus httpStatus;
            String message;
            printStackTrace(exception, true);            
            if (exception instanceof InvalidBearerTokenException) {
                httpStatus = HttpStatus.NOT_FOUND;
                message = "x-token-invalido-expirado";
            } else {
                httpStatus = HttpStatus.UNAUTHORIZED;
                message = "x-acceso-no-autorizado";
            }

            exchange.getResponse().setStatusCode(httpStatus);
            exchange.getResponse().getHeaders().add("Content-Type", "text/plain");
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                                    .bufferFactory().wrap(message.getBytes())));
        };
    }
    
    private void printStackTrace(Exception exception, boolean print) {                
        if (print) {
            StringWriter stack = new StringWriter();
            exception.printStackTrace(new PrintWriter(stack));
            log.error(stack.toString());
        }        
    }
}

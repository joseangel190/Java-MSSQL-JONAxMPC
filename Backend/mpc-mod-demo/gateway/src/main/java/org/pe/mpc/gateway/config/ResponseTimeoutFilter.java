/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.pe.mpc.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 *
 * @author JOFRANTOBA
 */
@Component
public class ResponseTimeoutFilter extends AbstractGatewayFilterFactory<ResponseTimeoutFilter.Config> {

    public static class Config {
        private long timeout;

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }
    }

    public ResponseTimeoutFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange)
            .timeout(Mono.delay(java.time.Duration.ofMillis(config.getTimeout())))
            .onErrorResume(throwable -> {
                if (throwable instanceof java.util.concurrent.TimeoutException) {
                    throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Response Timeout");
                }
                return Mono.error(throwable);
            });
    }
}

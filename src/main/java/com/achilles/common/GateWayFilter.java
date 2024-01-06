package com.achilles.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GateWayFilter implements GlobalFilter {

    private final static Logger log = LoggerFactory.getLogger(GateWayFilter.class);

    @Value("${server.port}")
    String serverPort;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("server.port : " + serverPort );
        return chain.filter(exchange);
    }
}

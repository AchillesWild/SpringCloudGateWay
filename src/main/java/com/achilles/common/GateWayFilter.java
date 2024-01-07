package com.achilles.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

@Component
public class GateWayFilter implements GlobalFilter, Ordered {

    private final static Logger log = LoggerFactory.getLogger(GateWayFilter.class);

    @Value("${server.port}")
    String serverPort;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        long startTime = System.currentTimeMillis();

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        InetSocketAddress address = request.getRemoteAddress();
        HttpMethod method = request.getMethod();

        Mono<Void> mono = chain.filter(exchange);

        long duration = System.currentTimeMillis() - startTime;
        ServerHttpResponse response = exchange.getResponse();
        HttpStatusCode statusCode = response.getStatusCode();
        log.info("gateway.port:{}, remoteAddress:{}, statusCode:{}", serverPort, address, statusCode);
        log.info("path:{}, method:{}, time:{}", path, method, duration);

        return mono;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

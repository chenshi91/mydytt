package com.dytt.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author chenshi
 * @date 2019-05-19
 */
@Slf4j
@Component
public class LogFilter  implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("path:{},进入gateway-logFilter...",path);
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = response.getHeaders();
        //允许跨域
        headers.add("Access-Control-Allow-Origin", "*");
        Mono<Void> filter = chain.filter(exchange);
        log.info("path:{},离开gateway-logFilter!",path);
        return filter;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}

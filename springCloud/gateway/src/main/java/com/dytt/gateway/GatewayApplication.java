package com.dytt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route(p ->
                p.path("/user/**")
//                        .filters(f->f.rewritePath("/user",""))
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://SERVICE-MODULE-USER"));

        routes.route(p ->
                p.path("/movie/**")
                        .filters(url->url.stripPrefix(1))
                        .uri("lb://SERVICE-MODULE-MOVIE"));

        routes.route(p ->
                p.path("/test/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://SERVICE-TEST"));
        routes.route(p ->
                p.path("/es/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://SERVICE-MODULE-ES"));
        routes.route(p->
            p.path("/stream/**")
                    .filters(url->url.stripPrefix(1))
                    .uri("lb://SERVICE-MODULE-DEMO"));
        return routes.build();
    }


    @GetMapping("/fallBack")
    public Mono<String> fallBack(){
        return Mono.just("fallBackString");
    }


}

package com.dytt.module.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients(basePackages = {"com.dytt.bridge.services"})
@EnableCaching
@EnableAsync
@MapperScan(basePackages = {"com.dytt.test.mvc.dao"})
@ComponentScan(basePackages = {"com.dytt.test", "com.dytt.common.model"})
@SpringBootApplication
@EnableEurekaClient
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}


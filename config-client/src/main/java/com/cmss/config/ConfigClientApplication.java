/* created by chenshi at 2018-10-26 */
package com.cmss.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
@RefreshScope
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${age}")
    String age;
    @Value("${name}")
    String name;
    @Value("${mongodbname}")
    String mongodbname;
    @Value("${spring.rabbitmq.port}")
    String rabbitMqPort;

    @GetMapping(value = {"/hi"})
    public String hi() {

        return "name:" + name + "<br>age:" + age + "<br>mongodbname:" + mongodbname + "<br>rabbitMqPort:" + rabbitMqPort;
    }
}

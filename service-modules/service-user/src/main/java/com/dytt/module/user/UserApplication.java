/* created by chenshi at 2018-10-23 */
package com.dytt.module.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableFeignClients({"com.dytt.bridge"})
@EnableCaching
@EnableAsync
@EnableScheduling
@MapperScan("com.dytt.module.*.mapper")
@ComponentScan(basePackages = {"com.dytt.module","com.dytt.common"})
@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

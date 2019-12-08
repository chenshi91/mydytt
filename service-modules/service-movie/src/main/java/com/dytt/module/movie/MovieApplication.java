/* created by chenshi at 2018-11-10 */
package com.dytt.module.movie;

import com.dytt.common.mvc.BaseApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients({"com.dytt.bridge"})
@EnableCaching
@EnableAsync
@EnableScheduling
@MapperScan("com.dytt.module.*.mapper")
@ComponentScan(basePackages = {"com.dytt.module", "com.dytt.common"})
@SpringBootApplication
@EnableEurekaClient
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}

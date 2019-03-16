/* created by chenshi at 2018-11-10 */
package com.cmss.dytt.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan("com.cmss.dytt.movie.dao")
@ComponentScan(basePackages = {"com.cmss.dytt.common.web","com.cmss.dytt.movie"})
@SpringBootApplication
@EnableEurekaClient
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}

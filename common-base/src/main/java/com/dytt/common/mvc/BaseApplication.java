/*created by chenshi at   2019/5/7*/
package com.dytt.common.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chenshi
 * @date 2019-05-07
 */
@EnableFeignClients({"com.dytt.bridge"})
@EnableCaching
@EnableAsync
@EnableScheduling
@MapperScan("com.dytt.module.*.dao")
@ComponentScan(basePackages = {"com.dytt.module","com.dytt.common"})
@SpringBootApplication
@EnableEurekaClient
public  class BaseApplication {
}

package com.dytt.common.bean;

import com.dytt.common.filter.RestRequestFilter;
import com.dytt.common.log.AddLogAspect;
import com.dytt.common.redis.aop.AddRedisCashAspect;
import com.dytt.common.utils.SendMailService;
import com.dytt.common.utils.SendMailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author  chenshi
 * @date 2019-02-14
 */
@Configuration
public class CommonConfiguration {

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(1000);
        requestFactory.setConnectTimeout(1000);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public RestRequestFilter restRequestFilter() {
        return new RestRequestFilter();
    }

    @Bean
    public AddLogAspect addLogAspect() {
        return new AddLogAspect(122);
    }

    @Bean
    public AddRedisCashAspect addRedisCashAspect() {
        return new AddRedisCashAspect(121);
    }

    @Bean
    public SendMailService sendMailService() {
        return new SendMailServiceImpl();
    }



}

package com.dytt.module.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author chenshi
 * @date 2019-11-22
 */
@Component
public class WebFilter  extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器
        registry.addInterceptor(new SwaggerHandlerInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new WebHandlerInterceptor()).addPathPatterns("/role");
        super.addInterceptors(registry);
    }

}

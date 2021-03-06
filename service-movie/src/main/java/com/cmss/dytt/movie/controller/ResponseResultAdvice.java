/* created by chenshi at 2018-12-04 */
package com.cmss.dytt.movie.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {
    @Value("${server.port}")
    String port;

    @Value("${spring.application.name}")
    String applicationName;


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (JSONObject.class.isAssignableFrom(object.getClass())) {
            ((JSONObject) object).put("port", port);
            ((JSONObject) object).put("applicationName", applicationName);
        }
        return object;
    }
}

package com.dytt.module.user.service;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@DefaultProperties(threadPoolKey = "demoThreadPool")
@FeignClient(value = "service-movie", fallback = UserServiceHystric.class)
public interface UserService {

    @GetMapping(value = {"/selectById/{id}"})
    JSONObject hi(@PathVariable(value = "id") Long id);
}

package com.cmss.mydytt.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-movie", fallback = UserServiceHystric.class)
public interface UserService {

    @GetMapping(value = {"/selectById/{id}"})
    JSONObject hi(@PathVariable(value = "id") Long id);
}

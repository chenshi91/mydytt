package com.cmss.mydytt.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-demo", fallback = UserServiceHystric.class)
public interface UserService {

    @GetMapping(value = {"/detail/1"})
    JSONObject hi();
}

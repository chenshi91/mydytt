package com.dytt.web.service;

import com.alibaba.fastjson.JSONObject;
import com.dytt.web.service.MovieServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "zuul",fallback = MovieServiceHystric.class)
public interface MovieService {

    @GetMapping(value = "/api-demo/detail/1")
    public JSONObject   list();


}

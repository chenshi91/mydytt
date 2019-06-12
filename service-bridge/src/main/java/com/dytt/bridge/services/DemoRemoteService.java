/*created by chenshi at   2019/4/26*/
package com.dytt.bridge.services;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.fallback.DemoRemoteServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-module-stream",fallback = DemoRemoteServiceFallback.class)
public interface DemoRemoteService {

    @GetMapping(value = {"/selectByIdaaaa/{id}"})
    JSONObject   selectById(@PathVariable("id") int  id);

}

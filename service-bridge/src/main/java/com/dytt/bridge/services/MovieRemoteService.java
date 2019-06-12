/*created by chenshi at   2019/4/26*/
package com.dytt.bridge.services;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "service_module_movie",fallback =MovieRemoteServiceFallback.class)
public interface MovieRemoteService {

    @GetMapping(value = {"/selectById/{id}"})
    JSONObject   selectById(@PathVariable("id")int id);

}

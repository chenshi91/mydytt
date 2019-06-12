/*created by chenshi at   2019/5/16*/
package com.dytt.bridge.services;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.fallback.EsRemoteServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-16
 */
@FeignClient(value = "service-module-es",fallback = EsRemoteServiceFallback.class)
public interface EsRemoteService {

    @PostMapping("/add")
    Boolean addUser(@RequestBody JSONObject  params);

    @GetMapping("/search")
    List<Map> searchUser(@RequestParam("key") String   key, @RequestParam("value") String  value);

    @GetMapping("/delete/{id}")
    Boolean deleteUser(@PathVariable("id") String  id);
}

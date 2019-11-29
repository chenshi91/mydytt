/* created by chenshi at 2018-10-25 */
package com.cmss.ribbon;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTempleteController {

    @Autowired
    RestTemplate restTemplate;

    //个性化线程池隔离，超时时间设置，可以对接口请求的粒度更细
    @HystrixCommand(fallbackMethod = "errorMethod",threadPoolKey = "demoThreadPool",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    @GetMapping(value = {"/hi"})
    public JSONObject hi() {
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity("http://service-stream/detail/1", JSONObject.class);
        return responseEntity.getBody();

    }

    public JSONObject errorMethod() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "96445454");
        jsonObject.put("msg", "熔断器起效了223333");
        return jsonObject;
    }



}

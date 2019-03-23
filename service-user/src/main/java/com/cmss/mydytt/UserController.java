/* created by chenshi at 2018-10-24 */
package com.cmss.mydytt;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.model.mvc.BaseController;
import com.dytt.common.model.mvc.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController extends BaseController {

    @Value("${server.port}")
    String port;
    @Value("${name}")
    String name;
    @Value("${mongodbname}")
    String mongodbname;
    @Value("${spring.rabbitmq.port}")
    String rabbitMQ_ip;

    @GetMapping(value = {"/hi"})
    public JSONObject hi() {
        JSONObject responseResult = new JSONObject();
        responseResult.put("code", "200");
        responseResult.put("msg", "ok");
        responseResult.put("rabbitMQ_ip", rabbitMQ_ip);
        responseResult.put("port", port);
        responseResult.put("mongodbname", mongodbname);
        responseResult.put("name", name);
        return responseResult;
    }

    @Override
    protected BaseService getService() {
        return null;
    }
}

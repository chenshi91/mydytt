/*created by chenshi at   2019/4/27*/
package com.dytt.module.test.mvc.resource;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.services.DemoRemoteService;
import com.dytt.common.utils.JsonUtil;
import com.dytt.module.test.mvc.Demo;
import com.dytt.module.test.mvc.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoResource implements DemoRemoteService {
    @Autowired
    DemoService demoService;

    @Override
    public JSONObject selectById(int id) {
        Demo demo = demoService.getById(id);
        JSONObject jsonObject = JsonUtil.parseJsonObject(demo);
        return jsonObject;
    }
}

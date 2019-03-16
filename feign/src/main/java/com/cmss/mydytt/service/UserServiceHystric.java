/* created by chenshi at 2018-10-25 */
package com.cmss.mydytt.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystric implements UserService {
    @Override
    public JSONObject hi(Long   id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", "断路器起效了");
        return jsonObject;
    }
}

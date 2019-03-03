/* created by chenshi at 2019-02-26 */
package com.dytt.web.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MovieServiceHystric implements MovieService{
    @Override
    public JSONObject list() {
        JSONObject connectReFailResult = new JSONObject();
        connectReFailResult.put("code","94512");
        connectReFailResult.put("msg","lianjieshibai");
        return connectReFailResult;
    }
}

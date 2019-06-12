package com.dytt.bridge.fallback;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.services.EsRemoteService;
import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

/**
 * @author chenshi
 * @date 2019-05-31
 */

public class EsRemoteServiceFallback    implements FallbackFactory<EsRemoteService> {
    @Override
    public EsRemoteService create(Throwable throwable) {
        return new EsRemoteService() {
            @Override
            public Boolean addUser(JSONObject params) {

                return false;
            }

            @Override
            public List<Map> searchUser(String key, String value) {
                return null;
            }

            @Override
            public Boolean deleteUser(String id) {

                return false;
            }
        };
    }
}

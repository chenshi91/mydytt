package com.dytt.bridge.fallback;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.services.DemoRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenshi
 * @date 2019-05-31
 */
@Component
public class DemoRemoteServiceFallback  implements FallbackFactory<DemoRemoteService> {
    @Override
    public DemoRemoteService create(Throwable throwable) {
        return new DemoRemoteService() {
            @Override
            public JSONObject selectById(int id) {
                return null;
            }
        };
    }
}

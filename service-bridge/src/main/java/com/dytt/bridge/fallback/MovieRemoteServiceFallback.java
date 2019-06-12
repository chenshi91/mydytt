package com.dytt.bridge.fallback;

import com.alibaba.fastjson.JSONObject;
import com.dytt.bridge.services.MovieRemoteService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenshi
 * @date 2019-05-31
 */
@Component
public class MovieRemoteServiceFallback implements FallbackFactory<MovieRemoteService> {

    @Override
    public MovieRemoteService create(Throwable throwable) {
        return new MovieRemoteService() {
            @Override
            public JSONObject selectById(int id) {
                return null;
            }
        };
    }
}

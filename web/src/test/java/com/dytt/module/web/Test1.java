package com.dytt.module.web;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.test.BaseTest;
import com.dytt.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Slf4j
public class Test1 extends BaseTest {
    @Override
    protected ClassLoader getClassLoader() {
        return null;
    }

    @Override
    protected String getIpHost() {
        return "";
    }

    @Test
    public void aaa() {
        String content = "{}";
        MovieController movieController = JsonUtil.toJavaObject(new JSONObject(), MovieController.class);

    }

    @Test
    public void aaaa(){
        Flux<String> just = Flux.just("hello world!");
        long id = Thread.currentThread().getId();
        Disposable subscribe = just.subscribe(s -> log.info(s),
                err->{

                });

    }
}

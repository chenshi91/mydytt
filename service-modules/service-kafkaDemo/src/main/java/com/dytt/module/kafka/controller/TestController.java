package com.dytt.module.kafka.controller;

import com.alibaba.fastjson.JSONObject;
import com.dytt.module.kafka.stream.SendEventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenshi
 * @date 2019-06-13
 */
@Slf4j
@RequestMapping(produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
@RestController
public class TestController {

    @Autowired
    SendEventProcessor sendEventProcessor;

    @PostMapping(value = {"/send/msg"})
    public Boolean sendEventPush(@RequestBody JSONObject params){
        //1,制定发送规则,前3条实时发送,后面的消息定时发送
        return sendEventProcessor.output()
                .send(MessageBuilder.withPayload(params.toJSONString()).build());
    }

}

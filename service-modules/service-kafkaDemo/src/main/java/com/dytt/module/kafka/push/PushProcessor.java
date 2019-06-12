package com.dytt.module.kafka.push;

import com.alibaba.fastjson.JSONObject;
import com.dytt.module.kafka.stream.SendEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author chenshi
 * @date 2019-06-05
 */
@Component
public class PushProcessor {

    @Autowired
    SendEventProcessor  sendEventProcessor;

    public Boolean sendEventPush(JSONObject    params){
        //1,制定发送规则,前3条实时发送,后面的消息定时发送
        return sendEventProcessor.output()
                .send(MessageBuilder.withPayload(params.toJSONString()).build());
    }

}

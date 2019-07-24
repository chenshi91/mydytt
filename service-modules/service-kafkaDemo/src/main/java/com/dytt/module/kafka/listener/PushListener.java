package com.dytt.module.kafka.listener;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.utils.JsonUtil;
import com.dytt.module.kafka.stream.SendEventProcessor;
import com.gexin.rp.sdk.http.IGtPush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author chenshi
 * @date 2019-06-05
 */
@Slf4j
@Component
@EnableBinding({
        SendEventProcessor.class
})
public class PushListener {

    @StreamListener("SEND_EVENT_INPUT")
    public void receiveSendEvent(String json){
        JSONObject params = JsonUtil.parseObject(json);
        log.info(json);
        log.info("开始调用个信sdk发送消息给app...");
        //调用个信sdk发送消息给app
        IGtPush push = new IGtPush("", "");
//        push.pushMessageToList("",new List<Target>);
//        APN

    }


}

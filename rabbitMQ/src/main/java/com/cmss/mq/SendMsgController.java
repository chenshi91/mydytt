/* created by chenshi at 2018-10-28 */
package com.cmss.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {

    @Autowired
    AmqpTemplate amqpTemplate;

    @GetMapping(value = {"/send"})
    public String sendMsg() {
        String msg = "我的消息徐家立=";
        StringBuffer msgResult = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            String msgNow = msg + System.currentTimeMillis();
            amqpTemplate.convertAndSend("xu1", "第" + (i + 1) + "次," + msgNow);
            msgResult.append(msgNow + "<br>");
        }
        return msgResult.toString();
    }

}

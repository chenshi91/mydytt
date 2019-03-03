/* created by chenshi at 2018-10-28 */
package com.cmss.mq;

import com.cmss.mq.receive.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    UserService userService;

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

    @GetMapping(value = {"/hi"})
    public String async() {
        System.out.println("111111111111111111111111");
        userService.yibu2();
        System.out.println("4444444444444444444444444444");
        return "xujiali";
    }


}

/* created by chenshi at 2018-10-28 */
package com.cmss.mq.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"xu1"})
public class Receive1 {

    @RabbitHandler
    public void receive1(String msg) {
        System.out.println("receive1:" + msg);
    }
}

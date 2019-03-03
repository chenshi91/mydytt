/* created by chenshi at 2018-10-28 */
package com.cmss.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbbitMQConfig {

    @Bean
    public Queue myQueue1() {
        return new Queue("xu1");
    }


}

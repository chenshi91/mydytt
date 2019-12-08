package com.dytt.module.kafka.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

/**
 * @author chenshi
 * @date 2019-06-05
 */
@Service
public interface SendEventProcessor {

    @Input(value = "SEND_EVENT_INPUT")
    SubscribableChannel input();

    @Output(value = "SEND_EVENT_OUT")
    MessageChannel  output();
}

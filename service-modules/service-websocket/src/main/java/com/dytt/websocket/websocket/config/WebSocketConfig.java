package com.dytt.websocket.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author chenshi
 * @date 2019-06-19
 */
@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter   serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}

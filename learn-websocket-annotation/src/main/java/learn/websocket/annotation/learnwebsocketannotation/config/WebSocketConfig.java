package learn.websocket.annotation.learnwebsocketannotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @title: WebSocketConfig
 * @Description 开启WebSocket支持
 * @Author ZLT
 * @Date: 2021/12/8 9:16
 * @Version 1.0
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注入ServerEndpointExporter，@Bean会自动注册使用@ServerEndpoint注解声明的websocket端点endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}

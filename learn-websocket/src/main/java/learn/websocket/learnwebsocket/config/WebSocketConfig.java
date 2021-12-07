package learn.websocket.learnwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @title: WebSocketConfig
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/3 10:49
 * @Version 1.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册websocket端点（基站）
     * 发布或订阅消息时，需要先连接此端点
     * setAllowedOrigins("*") 可有可无；*表示允许其他域进行访问
     *      注：若使用setAllowedOrigins报错的情况下将setAllowedOrigins换成setAllowedOriginPatterns
     * withSockJS 表示开启sockjs支持
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("oneToOne").setAllowedOrigins("*").withSockJS();//点对点端点
        registry.addEndpoint("oneToOne").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("broadcast").withSockJS();//广播端点
    }

    /**
     * 配置消息代理【中介转发的意思】
     * enableSimpleBroker：服务端推送给客户端路径的前缀
     * setApplicationDestinationPrefixes：客户端发送数据到服务端路径的前缀
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/info");
    }

}

package learn.websocket.learnwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @title: SubscribeEvenListener
 * @Description 订阅监听器
 * @Author Ltter
 * @Date: 2021/12/7 11:01
 * @Version 1.0
 */
@Component
public class SubscribeEvenListener implements ApplicationListener<SessionSubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("SubscribeEvenListener 订阅监听事件 类型："+accessor.getCommand());
    }
}

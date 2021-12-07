package learn.websocket.learnwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @title: ConnectEventListener
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/7 11:05
 * @Version 1.0
 */
@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("ConnectEventListener 连接监听器 类型："+accessor.getCommand());
    }
}

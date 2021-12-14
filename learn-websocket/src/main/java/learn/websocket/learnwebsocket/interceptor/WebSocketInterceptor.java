package learn.websocket.learnwebsocket.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @title: WebSocketInterceptor
 * @Description http握手拦截器，可以通过这个类的方法获取到request、reponse
 *              拦截器需要在websocket配置文件中启用拦截器
 * @Author ZLT
 * @Date: 2021/12/7 16:35
 * @Version 1.0
 */
public class WebSocketInterceptor implements HandshakeInterceptor{ //extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("WebSocketInterceptor ------ beforeHandshake");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("WebSocketInterceptor ------ afterHandshake");
    }
}

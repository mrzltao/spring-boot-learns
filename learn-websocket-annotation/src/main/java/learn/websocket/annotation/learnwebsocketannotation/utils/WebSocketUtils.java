package learn.websocket.annotation.learnwebsocketannotation.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title: WebSocketUtils
 * @Description 自己对自己发送消息
 * @Author Ltter
 * @Date: 2021/12/8 9:27
 * @Version 1.0
 */
@Component
@ServerEndpoint("/ws/test")
public class WebSocketUtils {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        atomicInteger.incrementAndGet();
        System.out.println("建立连接成功 onOpen 线程数量："+atomicInteger.get()+" sessionID："+session.getId());
    }

    /**
     * 连接关闭调用的方法
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        atomicInteger.incrementAndGet();
        System.out.println("连接关闭成功 onOpen 线程数量："+atomicInteger.get()+" sessionID："+session.getId());
    }

    /**
     * 接收到客户端消息后调用的方法
     * @param msg 客户端发送的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        atomicInteger.incrementAndGet();
        System.out.println("接收到客户端发送的消息 onMessage："+ msg+" 线程数量："+atomicInteger.get()+" sessionID："+session.getId());
        session.getBasicRemote().sendText(msg);
    }

    /**
     * 出现错误时调用的方法
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        atomicInteger.incrementAndGet();
        System.out.println("错误 onError 线程数量："+atomicInteger.get()+" sessionID："+session.getId());
        throwable.printStackTrace();
    }
}

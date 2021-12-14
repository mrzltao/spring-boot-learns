package learn.websocket.learnwebsocket.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * @title: WebSocketChannelInterceptor
 * @Description 频道拦截器
 * @Author ZLT
 * @Date: 2021/12/8 16:13
 * @Version 1.0
 */
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    /**
     * 发送消息之前调用此方法
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("WebSocketChannelInterceptor ------ preSend");
        return ChannelInterceptor.super.preSend(message, channel);
    }

    /**
     * 调用发送消息时立即执行此方法
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("WebSocketChannelInterceptor ------ postSend");
        //业务Code...
    }

    /**
     * 无论是发送成功或者是异常之后都会调用此方法，一般用于资源释放
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println("WebSocketChannelInterceptor ------ afterSendCompletion");
    }

}

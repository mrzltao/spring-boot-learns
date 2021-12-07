package learn.websocket.learnwebsocket.service;

import learn.websocket.learnwebsocket.model.GetInMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @title: WebSocketService
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/7 13:22
 * @Version 1.0
 */
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void broadcast(GetInMessage inMessage){
        template.convertAndSend("/topic/getResponse", inMessage.toString());
    }

    public void unicast(GetInMessage inMessage){
        template.convertAndSendToUser(inMessage.getTo(),"/message",inMessage.toString());
        //或者使用convertAndSend类实现点对点的私信传输
        //template.convertAndSend("/user/message/"+in.getTo(),new GetInMessage(in.getFrom()+"发送的信息"+in.getContext()));
    }
}

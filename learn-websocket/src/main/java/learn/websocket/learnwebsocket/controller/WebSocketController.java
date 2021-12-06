package learn.websocket.learnwebsocket.controller;

import learn.websocket.learnwebsocket.model.GetInMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @title: WebSocketController
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/6 10:13
 * @Version 1.0
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/oneToBroadcast")
    public void oneToBroadcast(GetInMessage in) throws Exception{
        in.setFrom(in.getName());
        in.setTo("全体成员");
        template.convertAndSend("/topic/getResponse", in.toString());
    }

    @MessageMapping("/toOne")
    public void toOne(GetInMessage in) throws Exception{
        template.convertAndSendToUser(in.getTo(),"/message",in.toString());
    }
}

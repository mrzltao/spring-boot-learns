package learn.websocket.learnwebsocket.controller;

import learn.websocket.learnwebsocket.model.GetInMessage;
import learn.websocket.learnwebsocket.model.GetOutMessage;
import learn.websocket.learnwebsocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: WebSocketController
 * @Description TODORest@Author Ltter
 * @Date: 2021/12/6 10:13
 * @Version 1.0
 */
@RestController
public class WebSocketController {

    /**
     * 将SimpMessagingTemplate提取进行一个封装
     */
    //@Autowired
    //private SimpMessagingTemplate template;
    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/oneToBroadcast")
    public void oneToBroadcast(GetInMessage in) throws Exception{
        in.setFrom(in.getName());
        in.setTo("全体成员");
        System.out.println("WebSocketController ------ oneToBroadcast");
        //template.convertAndSend("/topic/getResponse", in.toString());
        webSocketService.broadcast(in);
    }

    @MessageMapping("/toOne")
    public void toOne(GetInMessage in) throws Exception{
        //template.convertAndSendToUser(in.getTo(),"/message",in.toString());
        //或者使用convertAndSend类实现点对点的私信传输
        //template.convertAndSend("/user/message/"+in.getTo(),new GetInMessage(in.getFrom()+"发送的信息"+in.getContext()));
        webSocketService.unicast(in);
    }

    @MessageMapping("/getInfo")
    @SendTo("/topic/get")
    public GetOutMessage getInfo(GetInMessage in){
        return new GetOutMessage(in.getContext());
    }
}

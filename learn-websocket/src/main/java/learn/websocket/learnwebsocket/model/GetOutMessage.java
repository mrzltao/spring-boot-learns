package learn.websocket.learnwebsocket.model;

import java.time.LocalDateTime;

/**
 * @title: GetOutMessage
 * @Description TODO
 * @Author ZLT
 * @Date: 2021/12/6 14:06
 * @Version 1.0
 */
public class GetOutMessage {

    private String from;

    private String to;

    private String name;

    private String context;

    private LocalDateTime time = LocalDateTime.now();

    public GetOutMessage(){

    }

    public GetOutMessage(String context){
        this.context=context;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GetOutMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", time=" + time +
                '}';
    }
}

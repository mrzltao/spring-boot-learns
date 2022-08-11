package learn.websocket.learnwebsocket.model;

import java.time.LocalDateTime;

/**
 * @title: GetInMessage
 * @Description TODO
 * @Author Ltter
 * @Date: 2021/12/6 14:04
 * @Version 1.0
 */
public class GetInMessage {

    private String from;

    private String to;

    private String name;

    private String context;

    private LocalDateTime time = LocalDateTime.now();

    public GetInMessage(){

    }

    public GetInMessage(String context){
        this.context=context;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
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
        return "GetInMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", time=" + time +
                '}';
    }
}

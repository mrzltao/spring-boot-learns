package learn.websocket.annotation.learnwebsocketannotation;

/**
 * @title: GetInMessage
 * @Description TODO
 * @Author Ltter
 * @Date: 2021/12/8 11:31
 * @Version 1.0
 */
public class GetInMessage {
    private String msg;

    private String userId;

    public GetInMessage(){}

    public GetInMessage(String msg){
        this.msg=msg;
    }

    public GetInMessage(String msg, String userId){
        this.msg=msg;
        this.userId=userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GetInMessage{" +
                "msg='" + msg + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

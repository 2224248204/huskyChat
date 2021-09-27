package cn.huskychat.service.websocket;

public class ServerUserMessage {
    private String message;              // 响应的消息
    private String messageType;         // 响应消息的类型
    private String targetAccount;       // 响应的对象
    private String receiveAccount;      // 接收的账号

    public ServerUserMessage() {
    }

    public ServerUserMessage(String message, String messageType, String targetAccount, String receiveAccount) {
        this.message = message;
        this.messageType = messageType;
        this.targetAccount = targetAccount;
        this.receiveAccount = receiveAccount;
    }

    public ServerUserMessage(String message, String messageType, String targetAccount) {
        this.message = message;
        this.messageType = messageType;
        this.targetAccount = targetAccount;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }
}

package cn.huskychat.service.websocket;

public class ClientUserMessage {
    private String message;             // 内容
    private String targetType;          // 发送的类型
    private String receiveAccount;      // 接收的账号
    private String targetAccount;       // 发送对象的账号
    private Integer targetObjectType;    //   发送给哪些对象人群 0 单人， 1 一群人

    public ClientUserMessage(String message, String targetType, String targetAccount, String receiveAccount, Integer targetObjectType) {
        this.message = message;
        this.targetType = targetType;
        this.receiveAccount = receiveAccount;
        this.targetAccount = targetAccount;
        this.targetObjectType = targetObjectType;
    }

    public ClientUserMessage(String message, String targetType, String targetAccount) {
        this.message = message;
        this.targetType = targetType;
        this.targetAccount = targetAccount;
    }

    public Integer getTargetObjectType() {
        return targetObjectType;
    }

    public void setTargetObjectType(Integer targetObjectType) {
        this.targetObjectType = targetObjectType;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public ClientUserMessage() {
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetType() {
        return targetType;
    }

    public void getTargetType(String target) {
        this.targetType = target;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }
}

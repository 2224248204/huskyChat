package cn.huskychat.pojo;

import java.util.Date;

/**
 * 通知类
 */
public class Notice {
    private Integer id;     //id
    private String account; // 账号
    private String content; // 通知内容
    private Integer type;   // 通知类型
    private Integer isRead; // 已读状态
    private Date time;      // 通知时间

    public Notice() {
    }

    public Notice(String account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

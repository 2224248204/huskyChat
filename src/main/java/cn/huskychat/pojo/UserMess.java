package cn.huskychat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户消息
 */
public class UserMess {
    private Integer id;
    private String accountA;    // 账号A
    private String accountB;    // 账号B
    private String content;     // 内容
    private Date time;          // 发送时间
    private Integer messType;   // 消息类型
    private Integer status;     // 消息状态

    private String nickNameA;
    private String nickNameB;
    private String headImgA;
    private String headImgB;



    public UserMess(String accountA, String accountB) {
        this.accountA = accountA;
        this.accountB = accountB;
    }

    public UserMess() {
    }

    public String getNickNameA() {
        return nickNameA;
    }

    public void setNickNameA(String nickNameA) {
        this.nickNameA = nickNameA;
    }

    public String getNickNameB() {
        return nickNameB;
    }

    public void setNickNameB(String nickNameB) {
        this.nickNameB = nickNameB;
    }

    public String getHeadImgA() {
        return headImgA;
    }

    public void setHeadImgA(String headImgA) {
        this.headImgA = headImgA;
    }

    public String getHeadImgB() {
        return headImgB;
    }

    public void setHeadImgB(String headImgB) {
        this.headImgB = headImgB;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAccountA() {
        return accountA;
    }

    public void setAccountA(String accountA) {
        this.accountA = accountA;
    }

    public String getAccountB() {
        return accountB;
    }

    public void setAccountB(String accountB) {
        this.accountB = accountB;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getMessType() {
        return messType;
    }

    public void setMessType(Integer messType) {
        this.messType = messType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

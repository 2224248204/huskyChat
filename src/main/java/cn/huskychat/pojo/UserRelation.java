package cn.huskychat.pojo;

import java.util.Date;

/**
 * 用户关系类
 */
public class UserRelation {
    private String accountA;    // 用户A
    private String accountB;    // 用户B
    private String remarks;     // 备注
    private String reasonRemarks;   // 添加好友备注原因
    private Date time;          // 建立好友时间
    private Integer status;     // 好友状态

    @Override
    public String toString() {
        return "UserRelation{" +
                "accountA='" + accountA + '\'' +
                ", accountB='" + accountB + '\'' +
                ", remarks='" + remarks + '\'' +
                ", reasonRemarks='" + reasonRemarks + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }

    public UserRelation() {
    }

    public UserRelation(String accountA) {
        this.accountA = accountA;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReasonRemarks() {
        return reasonRemarks;
    }

    public void setReasonRemarks(String reasonRemarks) {
        this.reasonRemarks = reasonRemarks;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

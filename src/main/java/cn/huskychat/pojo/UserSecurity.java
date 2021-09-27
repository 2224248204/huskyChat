package cn.huskychat.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户密保
 */
public class UserSecurity {
    private String account;     // 账号
    /*
     *  格式:
     *          题目:答案,
     */
    private String security;    // 密保
    private Date updateTime;    // 修改时间

    @Override
    public String toString() {
        return "UserSecurity{" +
                "account='" + account + '\'' +
                ", security='" + security + '\'' +
                ", updateTime=" + updateTime +
                ", securityList=" + securityList +
                '}';
    }

    List<Security> securityList = new ArrayList<>();

    public class Security{
        public String subject;
        public String answer;

        @Override
        public String toString() {
            return "Security{" +
                    "subject='" + subject + '\'' +
                    ", answer='" + answer + '\'' +
                    '}';
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSecurity() {
        return security;
    }

    public List<Security> getSecurities() {
        if(security != null && !security.equals("")) {
            securityList.clear();
            // 拆分密保项
            String[] securityItems = security.split(",");

            for (int i = 0; i < securityItems.length; i++) {
                // 拆分密保项 题目和答案
                String[] securityItem = securityItems[i].split(":");

                // 创建密保对象
                Security securityObj = new Security();
                securityObj.subject = securityItem[0];
                securityObj.answer = securityItem[1];

                securityList.add(securityObj);
            }
        }
        return securityList;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

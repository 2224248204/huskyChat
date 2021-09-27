package cn.huskychat.pojo;

import java.util.Date;

/**
 * 用户实体类
 */
public class User {
    private Integer id;         // 用户id
    private String account;     // 账号
    private String password;    // 密码
    private String email;       // 邮箱
    private String phone;       // 电话号码
    private String nickName;    // 昵称
    private Integer gender;     // 性别
    private Date birthday;      // 生日
    private String sign;        // 个性签名
    private String intro;       // 个人介绍
    private String region;      // 地区
    private String headImg;     // 头像
    private Integer status;      // 状态
    private Integer relationStatus; // 好友添加隐私状态

    private UserSecurity userSecurity;  // 用户密保

    private Integer online;     // 在线状态

    public Integer getOnline() {
        return online;
    }

    public UserSecurity getUserSecurity() {
        return userSecurity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", sign='" + sign + '\'' +
                ", intro='" + intro + '\'' +
                ", region='" + region + '\'' +
                ", headImg='" + headImg + '\'' +
                ", status=" + status +
                ", relationStatus=" + relationStatus +
                ", userSecurity=" + userSecurity +
                ", online=" + online +
                '}';
    }

    public void setUserSecurity(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }

    public void setOnline(Integer online) {
        this.online = online;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
    }
}

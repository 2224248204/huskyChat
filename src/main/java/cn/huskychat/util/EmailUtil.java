package cn.huskychat.util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮箱工具类
 */
public class EmailUtil {

    // 配置发送方，请勿修改
    private static final String EMAIL_USER = "2224248204@qq.com";
    private static final String EMAIL_PASS = "casofbcsdvszdiah";

    /**
     * 发送邮件方法
     * @param objEmail
     * @param title
     * @param content
     * @throws Exception
     */
    public static void sendEmail(String objEmail, String title, String content) throws Exception{

        // 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        props.put("mail.user", EMAIL_USER);
        // 此处填写16位STMP口令
        props.put("mail.password", EMAIL_PASS);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(objEmail);
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
    }


/*    public static void main(String[] args) {
        // 测试发送邮件
        try {
            EmailCommon.sendEmail("45629542@qq.com", "邮件标题", "邮件内容，验证码写在这里");
            System.out.println("发送成功！");
        } catch (Exception e) {
            System.out.println("发送失败！");
            e.printStackTrace();
        }
    }*/
}

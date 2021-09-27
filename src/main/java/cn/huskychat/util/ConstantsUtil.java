package cn.huskychat.util;

public class ConstantsUtil {

    /**
     * 邮箱注册验证码模板
     * @param name
     * @param code
     * @return
     */
    public static final String getEmailMouldRegister(String name, String code){
        String mess = "<b>您好," + name + "！您的注册验证码为：<h3>" + code + "</h3>10分钟内有效。";
        return mess;
    }

    /**
     * 生成随机的验证码
     * @param size 生成的位数
     * @return
     */
    public static String createEmailCode(int size){
        double num = Math.random(); // 随机生成的数值

        // 生成 size 位数的数值验证码
        int ten = 1;
        for (int i = 0; i < size; i++) {
            ten *= 10;
        }

        return String.valueOf((int)(num * ten));
    }


}

package cn.huskychat.service.user;

import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserSecurity;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

public interface UserService {
    public User login(String account) throws Exception;

    public int addUser(User user) throws Exception;

    /**
     * 修改密码
     * @param account
     * @param password
     * @return
     */
    public int updatePassword(String account, String password) throws Exception;

    /**
     * 修改邮箱
     * @param account
     * @param email
     * @return
     * @throws Exception
     */
    public int updateEmail(String account, String email) throws Exception;

    /**
     * 修改头像
     * @param account
     * @param headImg
     * @return
     * @throws Exception
     */
    public int updateHeadImg(String account, String headImg) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUserInfo(User user) throws Exception;



    public User getUserByAccount(@Param("account") String account) throws Exception;
}

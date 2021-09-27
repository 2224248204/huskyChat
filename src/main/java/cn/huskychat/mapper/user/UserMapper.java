package cn.huskychat.mapper.user;

import cn.huskychat.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 登录方法
     * @param account
     * @return
     * @throws Exception
     */
    public User login(@Param("account") String account) throws Exception;

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    public int addUser(User user) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUser(User user) throws Exception;

    /**
     * 根据账号获取用户信息
     * @param account
     * @return
     * @throws Exception
     */
    public User getUserByAccount(@Param("account") String account) throws Exception;

}

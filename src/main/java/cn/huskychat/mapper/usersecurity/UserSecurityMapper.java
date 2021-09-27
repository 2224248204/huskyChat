package cn.huskychat.mapper.usersecurity;

import cn.huskychat.pojo.UserSecurity;
import org.apache.ibatis.annotations.Param;

public interface UserSecurityMapper {
    /**
     * 添加密保
     * @param userSecurity
     * @return
     * @throws Exception
     */
    public int addSecurity(UserSecurity userSecurity) throws Exception;

    /**
     * 修改密保
     * @param userSecurity
     * @return
     * @throws Exception
     */
    public int updateSecurity(UserSecurity userSecurity) throws Exception;

    /**
     * 根据用户账号获取用户密保信息
     * @param account
     * @return
     * @throws Exception
     */
    public UserSecurity getUserSecurityByAccount(@Param("account") String account) throws Exception;


}

package cn.huskychat.service.usersecurity;

import cn.huskychat.pojo.UserSecurity;
import org.apache.ibatis.annotations.Param;

public interface UserSecurityService {
    public int addSecurity(UserSecurity userSecurity) throws Exception;

    public int updateSecurity(UserSecurity userSecurity) throws Exception;

    public UserSecurity getUserSecurityByAccount(String account) throws Exception;
}

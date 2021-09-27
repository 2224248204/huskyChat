package cn.huskychat.service.usersecurity;

import cn.huskychat.mapper.usersecurity.UserSecurityMapper;
import cn.huskychat.pojo.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("/userSecurityService")
public class UserSecurityServiceImpl implements UserSecurityService{

    @Autowired
    private UserSecurityMapper userSecurityMapper;

    @Override
    public int addSecurity(UserSecurity userSecurity) throws Exception {
        return userSecurityMapper.addSecurity(userSecurity);
    }

    @Override
    public int updateSecurity(UserSecurity userSecurity) throws Exception {
        return userSecurityMapper.updateSecurity(userSecurity);
    }

    @Override
    public UserSecurity getUserSecurityByAccount(String account) throws Exception {
        return userSecurityMapper.getUserSecurityByAccount(account);
    }
}

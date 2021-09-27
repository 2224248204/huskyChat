package cn.huskychat.service.user;

import cn.huskychat.mapper.user.UserMapper;
import cn.huskychat.mapper.usersecurity.UserSecurityMapper;
import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserSecurity;
import cn.huskychat.service.usersecurity.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSecurityService userSecurityService;

    public User login(String account) throws Exception {
        return userMapper.login(account);
    }

    @Override
    public int addUser(User user) throws Exception {
        int result = userMapper.addUser(user);
        result = userSecurityService.addSecurity(user.getUserSecurity());
        return result;
    }

    @Override
    public int updatePassword(String account, String password) throws Exception {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        return userMapper.updateUser(user);
    }

    @Override
    public int updateEmail(String account, String email) throws Exception {
        User user = new User();
        user.setAccount(account);
        user.setEmail(email);
        return userMapper.updateUser(user);
    }

    @Override
    public int updateHeadImg(String account, String headImg) throws Exception {
        User user = new User();
        user.setAccount(account);
        user.setHeadImg(headImg);
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserInfo(User user) throws Exception {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserByAccount(String account) throws Exception {
        return userMapper.getUserByAccount(account);
    }


}

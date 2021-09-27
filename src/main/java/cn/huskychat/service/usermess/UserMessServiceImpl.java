package cn.huskychat.service.usermess;

import cn.huskychat.mapper.usermess.UserMessMapper;
import cn.huskychat.pojo.UserMess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userMessService")
public class UserMessServiceImpl implements UserMessService{
    @Autowired
    private UserMessMapper userMessMapper;

    @Override
    public int addUserMess(UserMess userMess) throws Exception {
        return userMessMapper.addUserMess(userMess);
    }

    @Override
    public List<UserMess> getUserMess(UserMess userMess) throws Exception {
        return userMessMapper.getUserMess(userMess);
    }

    @Override
    public UserMess getMessEnd(UserMess userMess) throws Exception {
        return userMessMapper.getMessEnd(userMess);
    }
}

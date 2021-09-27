package cn.huskychat.service.usermess;

import cn.huskychat.pojo.UserMess;

import java.util.List;

public interface UserMessService {
    public int addUserMess(UserMess userMess) throws Exception;

    public List<UserMess> getUserMess(UserMess userMess) throws Exception;

    public UserMess getMessEnd(UserMess userMess) throws Exception;
}

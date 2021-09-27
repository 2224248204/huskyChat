package cn.huskychat.mapper.usermess;

import cn.huskychat.pojo.UserMess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessMapper {
    /**
     * 用户发消息类
     * @param accountA
     * @param account
     * @return
     * @throws Exception
     */
    public int addUserMess(UserMess userMess) throws Exception;

    /**
     * 获取用户消息列表
     * @param userMess
     * @return
     * @throws Exception
     */
    public List<UserMess> getUserMess(UserMess userMess) throws Exception;

    /**
     * 获取最后一条消息
     * @param userMess
     * @return
     * @throws Exception
     */
    public UserMess getMessEnd(UserMess userMess) throws Exception;
}

package cn.huskychat.service.userrelation;

import cn.huskychat.pojo.UserRelation;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserRelationService {
    public List<UserRelation> getRelation(UserRelation userRelation) throws Exception;

    public int addRelation(UserRelation userRelation) throws Exception;

    public int delRelation(@Param("accountA") String accountA, @Param("accountB") String accountB) throws Exception;

    public int updateRelation(UserRelation userRelation) throws Exception;

    /**
     * 同意添加好友
     * @param accountA
     * @param session
     * @return
     */
    public int agreeRelation(String accountA, HttpSession session);

    /**
     * 不同意添加好友
     * @param accountA
     * @param session
     * @return
     */
    public int noAgreeRelation(String accountA, HttpSession session);

}

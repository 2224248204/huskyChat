package cn.huskychat.mapper.userrelation;

import cn.huskychat.pojo.UserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRelationMapper {
    /**
     * 获取好友关系
     * @param accountA
     * @param accountB
     * @return
     * @throws Exception
     */
    public List<UserRelation> getRelation(UserRelation userRelation) throws Exception;

    /**
     * 建立好友关系
     * @param accountA
     * @param accountB
     * @param remarks
     * @param reasonRelation
     * @return
     * @throws Exception
     */
    public int addRelation(UserRelation userRelation) throws Exception;

    /**
     * 删除好友
     * @param accountA
     * @param accountB
     * @return
     * @throws Exception
     */
    public int delRelation(@Param("accountA") String accountA, @Param("accountB") String accountB) throws Exception;

    /**
     * 修改好友信息
     * @param accountA
     * @param accountB
     * @param remarks
     * @return
     */
    public int updateRelation(UserRelation userRelation) throws Exception;
}

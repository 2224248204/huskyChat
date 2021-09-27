package cn.huskychat.mapper.notice;

import cn.huskychat.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    /**
     * 查询通知
     * @param Notice
     * @return
     * @throws Exception
     */
    public List<Notice> getNotice(Notice Notice) throws Exception;

    /**
     * 添加通知
     * @param notice
     * @return
     * @throws Exception
     */
    public int addNotice(Notice notice) throws Exception;

    /**
     * 修改通知已读状态
     * @param notice
     * @return
     * @throws Exception
     */
    public int updateNotice(@Param("account") String account, @Param("isRead") Integer isRead) throws Exception;
}

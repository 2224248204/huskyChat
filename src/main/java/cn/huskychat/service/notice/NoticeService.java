package cn.huskychat.service.notice;

import cn.huskychat.pojo.Notice;

import java.util.List;

public interface NoticeService {
    /**
     * 根据账号获取通知
     * @param account
     * @return
     * @throws Exception
     */
    public List<Notice> getNoticeByAccount(String account) throws Exception;

    /**
     * 添加通知
     * @param notice
     * @return
     * @throws Exception
     */
    public int addNotice(Notice notice) throws Exception;

    /**
     * 修改已读状态
     * @param account
     * @param isRead
     * @return
     * @throws Exception
     */
    public int updateNotice(String account, Integer isRead) throws Exception;
}

package cn.huskychat.service.notice;

import cn.huskychat.mapper.notice.NoticeMapper;
import cn.huskychat.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNoticeByAccount(String account) throws Exception {
        return noticeMapper.getNotice(new Notice(account));
    }

    @Override
    public int addNotice(Notice notice) throws Exception {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int updateNotice(String account, Integer isRead) throws Exception {
        return noticeMapper.updateNotice(account, isRead);
    }
}

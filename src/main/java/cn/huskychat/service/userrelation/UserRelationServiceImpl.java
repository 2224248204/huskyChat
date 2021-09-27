package cn.huskychat.service.userrelation;

import cn.huskychat.mapper.userrelation.UserRelationMapper;
import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Transactional
@Service("userRelationService")
public class UserRelationServiceImpl implements UserRelationService{
    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    public List<UserRelation> getRelation(UserRelation userRelation) throws Exception {
        return userRelationMapper.getRelation(userRelation);
    }

    @Override
    public int addRelation(UserRelation userRelation) throws Exception {
        return userRelationMapper.addRelation(userRelation);
    }

    @Override
    public int delRelation(String accountA, String accountB) throws Exception {
        return userRelationMapper.delRelation(accountA, accountB);
    }

    @Override
    public int updateRelation(UserRelation userRelation) throws Exception {
        return userRelationMapper.updateRelation(userRelation);
    }

    @Override
    public int agreeRelation(String accountA, HttpSession session) {
        String accountB = ((User)session.getAttribute("user")).getAccount();

        UserRelation userRelation = new UserRelation();
        userRelation.setAccountA(accountB);
        userRelation.setAccountB(accountA);
        userRelation.setStatus(1);

        int result = 0;

        try {
            result = userRelationMapper.updateRelation(userRelation);
            userRelation.setReasonRemarks("账号" + accountA + "添加你为好友！");
            result = userRelationMapper.addRelation(userRelation);
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    @Override
    public int noAgreeRelation(String accountA, HttpSession session) {
        int result = 0;
        try {
            result = delRelation(accountA, ((User)session.getAttribute("user")).getAccount());
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

}

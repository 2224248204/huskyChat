package cn.huskychat.controller;

import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserRelation;
import cn.huskychat.pojo.UserSecurity;
import cn.huskychat.service.userrelation.UserRelationService;
import cn.huskychat.util.ConstantsUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/userRelation")
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    /**
     * 获取好友信息
     * @param friendAccount
     * @param session
     * @return
     */
    @RequestMapping("/getFriend")
    @ResponseBody
    public String getFriend(HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        UserRelation userRelation = new UserRelation();
        userRelation.setAccountA(((User)session.getAttribute("user")).getAccount());

        try {
            List<UserRelation> userRelationList = userRelationService.getRelation(userRelation);
            result.put("msg", "加载好友列表成功！");
            result.put("data", userRelationList);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "加载好友列表时出现异常！");
        }

        return JSON.toJSONString(result);
    }

    /**
     * 申请添加好友方法，添加好友第一步
     * @param userRelation
     * @param type
     * @return
     */
    @PostMapping("/applyAddRelation")
    @ResponseBody
    public String addRelation(UserRelation userRelation, HttpSession session){

        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        userRelation.setAccountA(((User)session.getAttribute("user")).getAccount());

        try {
            int addResult = userRelationService.addRelation(userRelation);
            if(addResult > 0){
                result.put("msg", "申请添加好友成功！");
            }else{
                result.put("msg", "申请添加好友失败！");
            }
        } catch (Exception e) {
            result.put("msg", "申请添加好友时出现异常！");
            e.printStackTrace();
        }

        return JSON.toJSONString(result);
    }

    /**
     * 用户B同意用户A的好友添加申请
     * @param accountA
     * @param session
     * @return
     */
    @GetMapping("/agreeRelation")
    @ResponseBody
    public String agreeRelation(String accountA, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        int agreeRelationResult = userRelationService.agreeRelation(accountA, session);
        if(agreeRelationResult > 0){
            result.put("msg", "添加成功！");
        }else if(agreeRelationResult == -1){
            result.put("msg", "添加好友时出现异常！");
        }else{
            result.put("msg", "添加失败！");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 不同意好友申请
     * @param accountA
     * @param session
     * @return
     */
    @PostMapping("/noAgreeRelation")
    @ResponseBody
    public String noAgreeRelation(String accountA, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        int noAgreeRelationResult = userRelationService.noAgreeRelation(accountA, session);
        if(noAgreeRelationResult > 0){
            result.put("msg", "操作成功！");
        }else if(noAgreeRelationResult == -1){
            result.put("msg", "操作失败，系统异常！");
        }else{
            result.put("msg", "操作失败！");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 修改好友信息
     * @param accountB
     * @param remarks
     * @param status
     * @param session
     * @return
     */
    @GetMapping("/updateRelation")
    @ResponseBody
    public String updateRelation(String accountB, String remarks, String status, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        UserRelation userRelation = new UserRelation();
        userRelation.setAccountA(((User)session.getAttribute("user")).getAccount());
        userRelation.setAccountB(accountB);
        if(remarks != null && remarks.trim().length() != 0){
            userRelation.setRemarks(remarks);
        }
        if(status != null && status.trim().length() != 0){
            userRelation.setStatus(Integer.parseInt(status));
        }

        try {
            int updateResult = userRelationService.updateRelation(userRelation);
            result.put("msg", "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "修改失败，出现异常！");
        }

        return JSON.toJSONString(result);
    }

    /**
     * 删除好友
     * @param accountB
     * @param session
     * @return
     */
    @PostMapping("/delRelation")
    @ResponseBody
    public String delRelation(String accountB, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        String accountA = ((User)session.getAttribute("user")).getAccount();

        try {
            int delResult = userRelationService.delRelation(accountB, accountA);
            delResult = userRelationService.delRelation(accountA, accountB);
            if(delResult > 0){
                result.put("msg", "删除好友成功！");
            }else{
                result.put("msg", "删除好友失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "删除好友时出现异常！");
        }
        return JSON.toJSONString(result);
    }
}

package cn.huskychat.controller;

import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserMess;
import cn.huskychat.service.usermess.UserMessService;
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
@RequestMapping("/userMess")
public class UserMessController {

    @Autowired
    private UserMessService userMessService;

    /**
     * 发送消息
     * @param userMess
     * @return
     */
    @PostMapping("/addMess")
    @ResponseBody
    public String addMess(UserMess userMess){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        try {
            int addResult = userMessService.addUserMess(userMess);
            if(addResult > 0){
                result.put("msg", "发送成功！");
                result.put("status", "ok");
            }else{
                result.put("msg", "发送失败！");
                result.put("status", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "发送失败，出现异常！");
            result.put("status", "err");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 获取消息内容
     * @param userMess
     * @return
     */
    @GetMapping("/getMess")
    @ResponseBody
    public String getUserMess(String accountB, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        UserMess userMess = new UserMess();
        userMess.setAccountA(((User)session.getAttribute("user")).getAccount());
        userMess.setAccountB(accountB);
        try {
            List<UserMess> userMessList = userMessService.getUserMess(userMess);
            if(userMessList != null && userMessList.size() > 0){
                result.put("data", userMessList);
                result.put("msg", "获取消息内容成功！");
            }else{
                result.put("msg", "空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "获取消息内容异常！");
        }
        return JSON.toJSONString(result);
    }
}

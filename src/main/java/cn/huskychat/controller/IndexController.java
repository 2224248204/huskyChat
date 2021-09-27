package cn.huskychat.controller;

import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserMess;
import cn.huskychat.pojo.UserRelation;
import cn.huskychat.service.user.UserService;
import cn.huskychat.service.usermess.UserMessService;
import cn.huskychat.service.userrelation.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMessService userMessService;


    /**
     * 前台首页
     * @return
     */
    @GetMapping("/")
    public String index(Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        UserRelation userRelation = new UserRelation(user.getAccount());
        List<UserMess> userMesses = new ArrayList<>();
        try {
            List<UserRelation> userRelations = userRelationService.getRelation(userRelation);
            if(userRelations.size() > 0){
                for (UserRelation userRela : userRelations) {
                    UserMess userMess = userMessService.getMessEnd(new UserMess(userRela.getAccountA(), userRela.getAccountB()));
                    if(userMess != null){
                        userMesses.add(userMess);
                    }
                }
                System.out.println(userMesses.size());
                model.addAttribute("friends", userRelations);
                model.addAttribute("userMesses", userMesses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/forgotpass")
    public String forgotPass(){
        return "forgotpass";
    }

    /**
     * 后台首页
     * @return
     */
    @GetMapping("/admin")
    public String admin(){
        return "admin/index";
    }


}

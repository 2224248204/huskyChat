package cn.huskychat.controller;


import cn.huskychat.pojo.User;
import cn.huskychat.pojo.UserRelation;
import cn.huskychat.pojo.UserSecurity;
import cn.huskychat.service.user.UserService;
import cn.huskychat.service.userrelation.UserRelationService;
import cn.huskychat.service.websocket.WebSocketUser;
import cn.huskychat.util.ConstantsUtil;
import cn.huskychat.util.EmailUtil;
import cn.huskychat.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRelationService userRelationService;

    /**
     * 请求注册验证码
     * @param email
     * @return
     */
    @PostMapping("/getRegisterEmailCode")
    @ResponseBody
    public String getRegisterEmailCode(String email, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        // 相应结果的Map集合的JSON数据
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        Cookie cookie = null;

        // 获取 Cookie 数组
        Cookie[] cookies = request.getCookies();

        // 用户获取验证码次数
        Integer userGetEmailCodeCount = 0;

        // 用户最高可以获取5词验证码
        int maxGetEmailCodeCount = 5;

        // 遍历 cookie 数组
        if(cookies != null){
            for (Cookie coo : cookies) {
                if(coo.getName().equals("userGetEmailCodeCount")){
                    userGetEmailCodeCount = Integer.parseInt(coo.getValue());

                    // 判断获取的验证码是否超过指定的次数
                    if(userGetEmailCodeCount > maxGetEmailCodeCount - 1){
                        result.put("msg", "今日获取验证码次数已超过5次");
                        return JSON.toJSONString(result);
                    }else{
                        cookie = new Cookie("userGetEmailCodeCount", (++userGetEmailCodeCount).toString());
                    }
                }
            }
        }

        // 如果没有获取过验证码，则创建一个新的cookie记录获取验证码次数
        if(cookie == null){
            cookie = new Cookie("userGetEmailCodeCount", "1");
            cookie.setMaxAge(60*60*24);	// 设置cookie有效期为24小时
        }
        response.addCookie(cookie); // 响应到客户端cookie

        String code = ConstantsUtil.createEmailCode(6); // 生成验证码

        // 存放临时验证码信息的集合
        HashMap<String, Object> codeMap = new HashMap<>();
        codeMap.put("email", email);
        codeMap.put("code", code);

        // 验证码信息放到session里
        session.setAttribute("registerEmailCode", codeMap);
        session.setMaxInactiveInterval(600);

        try {
            // 发送验证码
            EmailUtil.sendEmail(email, "HuskyChat注册验证码", ConstantsUtil.getEmailMouldRegister(email, code));
            result.put("msg", "发送成功，请注意查收！");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "发送失败，出现异常！");
        }

        return JSON.toJSONString(result);
    }

    /**
     * 验证用户注册的验证码
     * @param email
     * @param code
     * @param model
     * @return
     */
    @PostMapping("/checkEmailCode")
    @ResponseBody
    public String checkEmailCode(String email, String code, HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        // 从session获取验证码的map
        HashMap<String, Object> codeMap = (HashMap<String, Object>) session.getAttribute("registerEmailCode");

        if(codeMap != null){

            String session_email = (String) codeMap.get("email");
            String session_code = (String) codeMap.get("code");

            // 验证验证码
            if(session_email.equals(email) && session_code.equalsIgnoreCase(code)){
                result.put("msg", "验证成功！");
                session.removeAttribute("email");
                session.removeAttribute("code");
            }else{
                result.put("msg", "验证码不正确！");
            }
        }else{
            result.put("msg", "验证码不存在！");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(User user, UserSecurity userSecurity, HttpServletRequest request, @RequestParam(value = "userHeadImg", required = false) MultipartFile headImg){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        // 判断上传的头像不为空
        if(!headImg.isEmpty()){
            // 上传文件的路径
            String path = request.getSession().getServletContext().getRealPath("public" + File.separator + "user" + File.separator + user.getAccount());

            // 原文件名
            String fileName = headImg.getOriginalFilename();

            // 上传的文件格式
            String prefix = fileName.substring(fileName.lastIndexOf('.'));
            System.out.println("文件格式：" + prefix);

            // 限制上传文件的大小
            int fileSize = 500000;

            // 判断上传的头像的大小是否超过 fileSize
            if(headImg.getSize() > fileSize){
                result.put("msg", "上传的头像太大！");
            }else if(!prefix.equalsIgnoreCase(".png")){
                result.put("msg", "上传头像文件的格式只能是.png格式");
            }else{
                // 新的头像文件名
                String newFileName = user.getAccount() + prefix;

                File targetFile = new File(path);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }

                // 上传文件
                try {
                    headImg.transferTo(new File(targetFile, newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    result.put("msg", "头像上传失败！");
                }
                user.setHeadImg(File.separator + "public" + File.separator + "user" + user.getAccount() + File.separator + newFileName);

                try {
                    user.setUserSecurity(userSecurity);
                    int addResult = userService.addUser(user);
                    if(addResult > 0){
                        result.put("msg", "注册成功！");
                    }else{
                        result.put("msg", "注册失败，请检查填写是否正确！");
                    }
                } catch (Exception e) {
                    result.put("msg", "注册失败，注册时出现异常！");
                    e.printStackTrace();
                }
            }
        }else{
            result.put("msg", "上传的文件为空！");
        }
        return JSON.toJSONString(result);
    }


    /**
     * 用户登录
     * @param account
     * @param password
     * @param model
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String account, String password, HttpSession session){
        Map<String, Object> result = new HashMap<>();
        result.put("code", "200");
        try {
            User user = userService.login(account);
            if(user != null){
                if(user.getPassword() != null && user.getPassword().equals(password)){
                    System.out.println(user.toString());
                    result.put("status", "ok");
                    result.put("msg", "登录成功！");
                    redisUtil.set("user", JSON.toJSONString(user));
                    session.setAttribute("user", user);

                    // 加载用户好友列表
                    UserRelation userRelation = new UserRelation();
                    userRelation.setAccountA(account);
                    List<UserRelation> userRelations = userRelationService.getRelation(userRelation);
                    WebSocketUser.getFriends().put(account, userRelations);

                }else{
                    result.put("status", "passErr");
                    result.put("msg", "密码不正确！");
                }
            }else{
                result.put("status", "notExist");
                result.put("msg", "账号不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "err");
            result.put("msg", "登录时出现异常！");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 修改密码
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public String updateUserPassword(String account, String password){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        if(account == null && account.equals("") && password == null && password.equals("")){
            result.put("msg", "填写的信息不能为空！");
        }else{
            try {
                int updateResult = userService.updatePassword(account, password);
                result.put("msg", "修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("msg", "修改失败，出现异常！");
            }
        }

        return JSON.toJSONString(result);
    }

    /**
     * 修改头像
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/updateHeadImg")
    @ResponseBody
    public String updateUserHeadImg(String account, String password){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        return JSON.toJSONString(result);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateInfo")
    @ResponseBody
    public String updateUserInfo(User user){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");

        try {
            int updateResult = userService.updateUserInfo(user);
            result.put("msg", "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "修改失败，出现异常！");
        }

        return JSON.toJSONString(result);
    }

    @RequestMapping("/getByAccount")
    @ResponseBody
    public String getUserByAccount(String account){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "200");
        try {
            User user = userService.getUserByAccount(account);
            if(user != null){
                result.put("msg", "获取成功！");
                result.put("data", user);
            }else{
                result.put("msg", "获取失败，用户不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "获取用户异常！");
        }
        return JSON.toJSONString(result);
    }
}

package cn.huskychat.controller;

import cn.huskychat.service.websocket.WebSocketDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/userSocket")
public class UserWebSocketController {

    @RequestMapping("/checkOnline")
    @ResponseBody
    public String getOnlineUser(String account){
        ConcurrentHashMap<String, Session> online = WebSocketDemo.getSessionPools();
        if(online.containsKey(account)){
            return "true";
        }else{
            return "false";
        }
    }

}

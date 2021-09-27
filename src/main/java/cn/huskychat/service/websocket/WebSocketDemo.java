package cn.huskychat.service.websocket;

import cn.huskychat.pojo.UserRelation;
import com.alibaba.fastjson.JSON;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * socket
 */
public class WebSocketDemo {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    // 响应内容的类型
    private static ConcurrentHashMap<String, Object> responseMessage = new ConcurrentHashMap<>();

    // 好友的集合
    private static ConcurrentHashMap<String, List<UserRelation>> friends = new ConcurrentHashMap<>();

    //建立连接成功调用
    @OnOpen
    public void onOpen(@PathParam(value = "account") String account, Session session){
        // 判断用户是否已连接
        if(!sessionPools.containsKey(account)){
            sessionPools.put(account, session);
            addOnlineCount();

            ClientUserMessage clientUserMessage = new ClientUserMessage("yes", "online", account);
            message(JSON.toJSONString(clientUserMessage), account);
//            System.out.println(account + "加入webSocket！当前人数为" + onlineNum);
        }else{
            // 已有该用户
        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "account") String account){
        sessionPools.remove(account);
        subOnlineCount();

        responseMessage.put("type", "online");
        ClientUserMessage clientUserMessage = new ClientUserMessage("no", "online", account);
        message(JSON.toJSONString(clientUserMessage), account);
    }

    //收到客户端信息后
    @OnMessage
    public void onMessage(String message, Session session, @PathParam(value = "account") String account) throws IOException{
        System.out.println("server get" + message);
        ClientUserMessage clientUserMessage = JSON.parseObject(message, ClientUserMessage.class);
//        Message.ClientRequestMessage  clientRequestMessage = JSON.parseObject(message, Message.ClientRequestMessage.class);
        message(JSON.toJSONString(clientUserMessage), account);
    }


    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(Session session, String message, String account) throws IOException {
        if(session != null){
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 响应消息返回给用户
     *
     * 数据包格式
     * {
     *  message : 内容
     *  target : 发送的对象
     *  targetAccount : 发送的对象的账号
     * }
     * @param message
     */
    public void message(Object message, String account){
//        String type = (String) responseMessage.get("type");

        // 当前发送消息的账号
        responseMessage.put("account", account);

        // 解析请求到的数据包
        ClientUserMessage clientUserMessage = JSON.parseObject((String) message, ClientUserMessage.class);
        String type = clientUserMessage.getTargetType();
        switch (type){
            case "online":
                ServerUserMessage serverUserMessage_online = new ServerUserMessage(clientUserMessage.getMessage(), clientUserMessage.getTargetType(), clientUserMessage.getTargetAccount());
                // 获取当前登录账号的好友信息
                List<UserRelation> userRelations = friends.get(clientUserMessage.getTargetAccount());

                // 判断是否有好友
                if(userRelations != null){
                    // 遍历好友
                    for (UserRelation userRelation : userRelations) {
                        // 判断当前好友是否在线
                        if(sessionPools.containsKey(userRelation.getAccountB())){
                            try {
                                // 发送消息给在线好友
                                sendMessage(sessionPools.get(userRelation.getAccountB()), JSON.toJSONString(serverUserMessage_online), account);

                                // 如果好友在线，则再向自己发送消息
                                ServerUserMessage serverUserMessageSelf = new ServerUserMessage("yes", "online", userRelation.getAccountB());
                                sendMessage(sessionPools.get(account), JSON.toJSONString(serverUserMessageSelf), account);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            System.out.println(userRelation.getAccountB() + "不在线！");
                        }
                    }
                }else{
                    System.out.println("获取的数据为空！");
                }
                break;
            case "message":
                ServerUserMessage serverUserMessage_message = new ServerUserMessage(clientUserMessage.getMessage(), clientUserMessage.getTargetType(), clientUserMessage.getTargetAccount());
                try {
                    sendMessage(sessionPools.get(serverUserMessage_message.getTargetAccount()), JSON.toJSONString(serverUserMessage_message), account);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }


       /* if(type.equals("online")){
            responseMessage.put("data", message);
        }else if(type.equals("newMessage")){
            responseMessage.put("data", message);
        }*/


        // 发送消息给每一个对象
        /*for (Session session : sessionPools.values()) {
            try {
                sendMessage(session, JSON.toJSONString(responseMessage), account);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }*/

    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable, @PathParam(value = "account") String account){
        throwable.printStackTrace();
        responseMessage.put("type", "error");
        message("连接出现异常！", account);
    }


    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

    public static AtomicInteger getOnlineNumber() {
        return onlineNum;
    }

    public static ConcurrentHashMap<String, Session> getSessionPools() {
        return sessionPools;
    }

    public static ConcurrentHashMap<String, List<UserRelation>> getFriends() {
        return friends;
    }

    public static void setFriends(ConcurrentHashMap<String, List<UserRelation>> friends) {
        WebSocketDemo.friends = friends;
    }
}

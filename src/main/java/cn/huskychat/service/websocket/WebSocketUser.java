package cn.huskychat.service.websocket;

import cn.huskychat.pojo.UserRelation;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/huskychat/user/{account}")
@Component
public class WebSocketUser {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    // 响应内容的类型
    private static ConcurrentHashMap<String, Object> responseMessage = new ConcurrentHashMap<>();

    // 好友的集合
    private static ConcurrentHashMap<String, List<UserRelation>> friends = new ConcurrentHashMap<>();

    /**
     * 有用户连接时
     */
    @OnOpen
    public void onOpen(@PathParam(value = "account") String account, Session session){
        // 判断用户是否已连接
        if(!sessionPools.containsKey(account)){
            // 添加到集合
            sessionPools.put(account, session);
            addOnlineCount();

            // 创建上线消息
            ClientUserMessage clientUserMessage = new ClientUserMessage("yes", "online", account, null, 1);
            sendMessToFriends(clientUserMessage);
            System.out.println(account + "加入webSocket！当前人数为" + onlineNum);
        }else{
            // 已有该用户
        }
    }

    /**
     * 有链接断开时
     * @param account
     */
    @OnClose
    public void onClose(@PathParam(value = "account") String account){
        sessionPools.remove(account);
        subOnlineCount();

        // 创建下线消息
        ClientUserMessage clientUserMessage = new ClientUserMessage("no", "online", account, null, 1);
        sendMessToFriends(clientUserMessage);
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable, @PathParam(value = "account") String account){
        throwable.printStackTrace();
    }

    //收到客户端信息后
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        ClientUserMessage clientUserMessage = JSON.parseObject(message.trim(), ClientUserMessage.class);
        if (clientUserMessage.getTargetObjectType() == 0) {
            sendMessToFriend(clientUserMessage);
        } else if(clientUserMessage.getTargetObjectType() == 1){
            sendMessToFriends(clientUserMessage);
        }
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 向好友发送消息
     * @param clientUserMessage
     */
    public void sendMessToFriend(ClientUserMessage clientUserMessage){
        // 响应转发的消息对象
        ServerUserMessage serverUserMessage = new ServerUserMessage(clientUserMessage.getMessage(), clientUserMessage.getTargetType(), clientUserMessage.getTargetAccount(), clientUserMessage.getReceiveAccount());
        try {
            sendMessage(sessionPools.get(clientUserMessage.getReceiveAccount()), JSON.toJSONString(serverUserMessage));

            sendMessage(sessionPools.get(clientUserMessage.getTargetAccount()), JSON.toJSONString(serverUserMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送群消息对象
     * @param clientUserMessage
     */
    public void sendMessToFriends(ClientUserMessage clientUserMessage){
        // 响应转发的消息对象
        ServerUserMessage serverUserMessage = new ServerUserMessage(clientUserMessage.getMessage(), clientUserMessage.getTargetType(), clientUserMessage.getTargetAccount(), clientUserMessage.getReceiveAccount());

        // 获取当前用户账号的好友对象
        List<UserRelation> userRelations = friends.get(serverUserMessage.getTargetAccount());
        System.out.println(userRelations);
        // 遍历好友账号
        if(userRelations != null){
            for (UserRelation userRelation : userRelations) {
                // 判断好友账号是否在线
                if(sessionPools.containsKey(userRelation.getAccountB())){
                    try {

                        // 告诉好友我上线了
                        serverUserMessage.setReceiveAccount(userRelation.getAccountB());
                        sendMessage(sessionPools.get(userRelation.getAccountB()), JSON.toJSONString(serverUserMessage));

                        // 告诉自己好友也在线
                        serverUserMessage.setTargetAccount(userRelation.getAccountB());
                        serverUserMessage.setReceiveAccount(userRelation.getAccountA());
                        sendMessage(sessionPools.get(userRelation.getAccountA()), JSON.toJSONString(serverUserMessage));
                    } catch (IOException e) {
                        e.printStackTrace();
                        continue;
                    }
                }else{
                    System.out.println(userRelation.getAccountB()+ "不在线！");
                }
            }
        }else{
            // 无好友
        }
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
        WebSocketUser.friends = friends;
    }
}

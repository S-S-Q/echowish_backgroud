package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.dao.ChatMapper;
import com.echowish.android_backgroud.pojo.chat.Chat;
import com.echowish.android_backgroud.service.ConcernService;
import com.echowish.android_backgroud.service.WebSocketService;
import com.echowish.android_backgroud.util.ChatServerEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

//传入 自己的id 以及想聊天对象的id
//很奇怪 加入了多线程 却又自己处理自己 很是疑惑
@ServerEndpoint(value = "/chat/{userId}/{otherUserId}",encoders = {ChatServerEncoder.class})
@Component
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    static ChatMapper chatMapper;

    static ConcernService concernService;

    @Autowired
    public void setChatService(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    @Autowired
    public void setConcernService(ConcernService concernService){
        this.concernService=concernService;
    }

    //记录userId对应的websocketImpl
    private static ConcurrentHashMap<Integer,Session> websocketMap = new ConcurrentHashMap<>();
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    @Override
    @OnOpen
    public void onOpen(Session session, @PathParam("userId")Integer userId,@PathParam("otherUserId") Integer otherUserId) {
        //将userId 以及对应的类 放入
        websocketMap.put(userId,session);
        try
        {
            List<Chat> chatList=chatMapper.queryChatMessage(otherUserId,userId);
            if(chatList==null)
                return;
            for(Chat chat:chatList)
                sendMessage(session,chat);
            chatMapper.deleteChat(otherUserId,userId);
        }
        catch (Exception e)
        {
            log.info("取出聊天功能出错"+e);
        }
    }

    @Override
    @OnClose
    public void onClose(@PathParam("userId")Integer userId) {
        if(websocketMap.get(userId)!=null){
            websocketMap.remove(userId);
        }
    }

    @Override
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("userId")Integer userId,@PathParam("otherUserId") Integer otherUserId) {
        try {
            System.out.println(message);
//            JSONObject jsonObject=new JSONObject(message);
//            String msg=jsonObject.getString("msg");

            //将时间时区转换 因为linux系统上 获取时间是世界时间
            Date date=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 设置成东八区时间
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            String str=dateFormat.format(date);
            date=dateFormat.parse(str);

            //和对方是好友时 才能发送过去
            if(!concernService.queryIsConcern(otherUserId,userId))
            {
                sendMessage(websocketMap.get(userId),new Chat(userId,otherUserId,date,"你们还不是好友"));
//                sendMessage(websocketMap.get(userId),"你们还不是好友");
            }
            //当对方在线时才会发送
            else if(websocketMap.containsKey(otherUserId))
                sendMessage(websocketMap.get(otherUserId),new Chat(userId,otherUserId,date,message));
            else
                chatMapper.insertNewChat(new Chat(userId,otherUserId,date,message));
        }
        catch (IOException | EncodeException | ParseException e)
        {
            log.info("聊天功能出错"+e);
        }
    }

    @Override
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(error);
    }

    public void sendMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void sendMessage(Session session,Chat chat) throws IOException, EncodeException {
        session.getBasicRemote().sendObject(chat);
    }

}

package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.dao.ChatMapper;
import com.echowish.android_backgroud.pojo.Chat;
import com.echowish.android_backgroud.service.ConcernService;
import com.echowish.android_backgroud.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//传入 自己的id 以及想聊天对象的id
//很奇怪 加入了多线程 却又自己处理自己 很是疑惑
@ServerEndpoint("/chat/{userId}/{otherUserId}")
@Component
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
                sendMessage(session,chat.getContent());
            chatMapper.deleteChat(otherUserId,userId);
        }
        catch (Exception e)
        {

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
            //和对方是好友时 才能发送过去
            if(!concernService.queryIsConcern(otherUserId,userId))
            {
                sendMessage(websocketMap.get(userId),"你们还不是好友");
            }
            //当对方在线时才会发送
            else if(websocketMap.containsKey(otherUserId))
                sendMessage(websocketMap.get(otherUserId),message);
            else
                chatMapper.insertNewChat(new Chat(userId,otherUserId,new Date(),message));
        }
        catch (IOException e)
        {
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

}

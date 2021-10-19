package com.echowish.android_backgroud.service;
import javax.websocket.Session;

public interface WebSocketService {

    /**
     * 连接建立成功调用的方法
     * @param session
     * @param userId
     *
     */
    void onOpen(Session session,Integer userId,Integer otherUserId);

    /**
     * 连接关闭调用的方法
     */
    void onClose(Integer userId);

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    void onMessage(String message, Session session,Integer userId,Integer otherUserId);

    /**
     * 发生错误
     * @param session
     * @param error
     */
    void onError(Session session, Throwable error);
}



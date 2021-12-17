package com.echowish.android_backgroud.util;

import com.echowish.android_backgroud.pojo.chat.Chat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

//websocket 用来发送chat消息的
public class ChatServerEncoder implements Encoder.Text<Chat> {
    @Override
    public String encode(Chat chat) throws EncodeException {
        try {
            /*
             * 这里是重点，只需要返回Object序列化后的json字符串就行
             */
            JsonMapper jsonMapper = new JsonMapper();
            return jsonMapper.writeValueAsString(chat);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
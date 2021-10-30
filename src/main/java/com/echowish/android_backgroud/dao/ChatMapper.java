package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.chat.Chat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    void insertNewChat(Chat chat);

    List<Chat> queryChatMessage(@Param("myId") Integer myId, @Param("otherId") Integer otherId);

    void deleteChat(@Param("myId") Integer myId, @Param("otherId") Integer otherId);
}

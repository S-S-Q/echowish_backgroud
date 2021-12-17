package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.*;
import com.echowish.android_backgroud.pojo.chat.Chat;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import com.echowish.android_backgroud.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class AndroidBackgroudApplicationTests {

    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    ConcernMapper concernMapper;
    @Autowired
    ChatMapper chatMapper;

    @Test
    void get() throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        System.out.println(jsonMapper.writeValueAsString(new Chat(1,1,new Date(),"SSQ")));;
    }

}

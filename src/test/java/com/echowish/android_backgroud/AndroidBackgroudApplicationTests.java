package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.*;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import com.echowish.android_backgroud.service.UserService;
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
    void get()
    {
//        commentMapper.queryAllCommentsByPostId(1);
//        chatMapper.insertNewChat(new Chat(1,2,new Date(),"SSQ"));
        System.out.println(chatMapper.queryChatMessage(1,2));
//        userMapper.queryUserByUserId(1);
//       commentService.queryAllMyCommentBuUserId(3);
    }

}

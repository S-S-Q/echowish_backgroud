package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.*;
import com.echowish.android_backgroud.pojo.Chat;
import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import com.echowish.android_backgroud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

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
    void contextLoads() {
        System.out.print(userMapper.queryUserByAccount(String.valueOf(1)));
    }

    @Test
    void update()
    {
        postService.deleteImage("2021-09-06-01-09-15-1.jpg");
    }

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

package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
class AndroidBackgroudApplicationTests {

    @Autowired
    PostMapper postMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostService postService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentService commentService;

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
        System.out.println(commentMapper.queryAllOthersCommentByPostId(7).size());;
//        userMapper.queryUserByUserId(1);
//       commentService.queryAllMyCommentBuUserId(3);
    }

}

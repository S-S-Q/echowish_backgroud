package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Post;
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

    @Test
    void contextLoads() {
        System.out.print(userMapper.queryUserByAccount(String.valueOf(1)));
    }

    @Test
    void update()
    {
        postService.deleteImage("2021-09-06-01-09-15-1.jpg");
    }

}

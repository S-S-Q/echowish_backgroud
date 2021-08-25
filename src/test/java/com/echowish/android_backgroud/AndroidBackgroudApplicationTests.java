package com.echowish.android_backgroud;

import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Post;
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
    @Test
    void contextLoads() {
        System.out.print(userMapper.queryUserByAccount(String.valueOf(1)));
    }

}

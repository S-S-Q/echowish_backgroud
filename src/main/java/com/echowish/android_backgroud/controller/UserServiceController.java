package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.User;
import com.echowish.android_backgroud.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@MapperScan("/user")
public class UserServiceController {

    @Autowired
    UserService userService;

    @GetMapping("register")
    @ResponseBody
    String registerNewUsers(@RequestParam(value = "account",required = true) String account,
                            @RequestParam(value = "password",required = true)String password)
    {
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        return userService.registerNewUsers(user);
    }
}

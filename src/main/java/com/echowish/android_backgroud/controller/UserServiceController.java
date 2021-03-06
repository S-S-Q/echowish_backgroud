package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.user.Friend;
import com.echowish.android_backgroud.pojo.user.User;
import com.echowish.android_backgroud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("user")
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
        System.out.println(account+password);
        return userService.registerNewUsers(user);
    }

    @GetMapping("delete")
    @ResponseBody
    String deleteUserByAccount(@RequestParam("account")String account)
    {
        return deleteUserByAccount(account);
    }

    @GetMapping("login")
    @ResponseBody
    Integer loginOn(@RequestParam(value = "account",required = true) String account,
                   @RequestParam(value = "password",required = true)String password)
    {
        return userService.logOn(account,password);
    }

    @GetMapping("queryUserByUserId")
    @ResponseBody
    User queryUserByUserId(@RequestParam(value = "userId")Integer userId)
    {
        return userService.getUserMessage(userId);
    }

    @PostMapping("updateUserHeadImage")
    @ResponseBody
    String updateUserHeadImage(@RequestParam(value = "userId")Integer userId,
                               @RequestParam(value = "image")MultipartFile image)
    {
        return userService.loadImage(image,userId);
    }

    @GetMapping("updateUserInfo")
    @ResponseBody
    String updateUserInfo(@RequestParam(value = "userId")Integer userId,
                          @RequestParam(value = "name")String name,
                          @RequestParam(value = "sex")Integer sex,
                          @RequestParam(value = "grade")Integer grade,
                          @RequestParam("campus")String campus)
    {
        User user=new User();
        user.userId=userId;
        user.name=name;
        user.sex=sex;
        user.grade=grade;
        user.campus=campus;
        return userService.updateUserInfo(user);
    }

    @GetMapping("getFriendMessage")
    @ResponseBody
    Friend getFriendMessage(@RequestParam("userId")Integer userId,
                            @RequestParam("friId")Integer friId)
    {
        return userService.getFriendMessage(userId,friId);
    }

    @GetMapping("getAllUser")
    @ResponseBody
    List<User> queryAllUser()
    {
        return userService.queryAllUser();
    }
}

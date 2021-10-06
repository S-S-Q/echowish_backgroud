package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.Friend;
import com.echowish.android_backgroud.pojo.User;
import com.echowish.android_backgroud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        return userService.registerNewUsers(user);
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

    @PostMapping("getUserHeadImage")
    @ResponseBody
    ResponseEntity<FileSystemResource> getUserHeadImage(@RequestParam("userId")Integer userId)
    {
        return userService.downloadImage(userId);
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
}

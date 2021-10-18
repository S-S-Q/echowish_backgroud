package com.echowish.android_backgroud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("chat")
public class ChatServiceController {

    // 推送数据到客户端 也就是不在线时那些数据
    @GetMapping("getOldMessage")
    public List<String> pushMessage(@RequestParam("otherUserId")Integer otherUserId,
                                    @RequestParam("userId" )Integer userId) {
        return null;
    }
}

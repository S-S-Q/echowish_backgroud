package com.echowish.android_backgroud.controller;


import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.service.CommentService;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("comment")
public class CommentServiceController {

    @Autowired
    CommentService commentService;

    @GetMapping("insert")
    @ResponseBody
    public  String publishNewComment(@RequestParam(value = "postId")Integer postId,
                                     @RequestParam(value = "userId")Integer userId,
                                     @RequestParam(value = "content")String content,
                                     @RequestParam(value = "time")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date time)
    {
        return commentService.publishNewComment(new Comment(postId,userId,content, time));
    }

    @GetMapping("delete")
    @ResponseBody
    public String deleteComment(@RequestParam(value = "postId")Integer postId,
                                @RequestParam(value = "userId")Integer userId,
                                @RequestParam(value = "content")String content,
                                @RequestParam(value = "time")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date time)
    {
        return commentService.deleteMyComment(new Comment(postId,userId,content, time));
    }
}

package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/post")
public class PostServiceController {

    @Autowired
    PostService postService;

    @GetMapping("publish")
    @ResponseBody
      public String publishPost(
              @RequestParam(value = "user_id",required = true) Integer user_id,
              @RequestParam(value = "title",required = true)String title,
              @RequestParam(value = "content",required = true)String content,
              @RequestParam(value = "reward",required = false)String reward,
              @RequestParam(value = "image_uri",required = false)String imageUri,
              @RequestParam(value = "time",required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date time)
      {
          return postService.PublishPost(new Post(user_id,title,content,reward,imageUri,time));
      }
}

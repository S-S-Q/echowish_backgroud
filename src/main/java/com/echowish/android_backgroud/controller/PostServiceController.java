package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostServiceController {

    @Autowired
    PostService postService;

    @PostMapping("publish")
    @ResponseBody
      public String publishPost(
            @RequestParam(value = "file",required = false) MultipartFile file,
            @RequestParam(value = "filename",required = false) String filename,
            @RequestParam(value = "user_id",required = true) Integer user_id,
            @RequestParam(value = "title",required = true)String title,
            @RequestParam(value = "zone",required = true)String zone,
            @RequestParam(value = "content",required = true)String content,
            @RequestParam(value = "reward",required = false)String reward,
            @RequestParam(value = "time",required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date time)
      {
          return postService.publishPost(file,new Post(user_id,title,zone,content,reward,filename,time));
      }


    @GetMapping("query")
    @ResponseBody
    public List<Post> queryPost(@RequestParam(value = "start") Integer start,
                                @RequestParam(value = "end")Integer end)
    {
        return postService.queryPost(start,end);
    }



    @PostMapping("download")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadImage(@RequestParam(value = "postId",required = true) Integer postId)
    {
        return postService.downloadImage(postId);
    }
}

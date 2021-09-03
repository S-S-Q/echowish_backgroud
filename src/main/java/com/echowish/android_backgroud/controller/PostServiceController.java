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
              @RequestParam(value = "zone",required = true)String zone,
              @RequestParam(value = "content",required = true)String content,
              @RequestParam(value = "reward",required = false)String reward,
              @RequestParam(value = "image_uri",required = false) String post_image,
              @RequestParam(value = "time",required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date time)
      {
          return postService.publishPost(new Post(user_id,title,zone,content,reward,post_image,time));
      }


    @PostMapping("load")
    @ResponseBody
    public String loadImage(@RequestParam(value = "file",required = true) MultipartFile file,
                            @RequestParam(value = "filename",required = true) String filename,
                            @RequestParam(value="postId",required = true) Integer postId)
    {
        return postService.loadImage(file,filename);
    }

//    @PostMapping("load")
//    @ResponseBody
//    public String loadImage(@RequestParam(value = "file",required = true) MultipartFile file,
//                            @RequestParam(value = "filename",required = true) String filename)
//    {
//        return postService.loadImage(file,filename);
//    }

//    @PostMapping("download")
//    @ResponseBody
//    public ResponseEntity<FileSystemResource> downloadImage(@RequestParam(value = "filename",required = true) String filename)
//    {
//        return postService.downloadImage(filename);
//    }

    @PostMapping("download")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadImage(@RequestParam(value = "postId",required = true) Integer postId)
    {
        return postService.downloadImage(postId);
    }
}

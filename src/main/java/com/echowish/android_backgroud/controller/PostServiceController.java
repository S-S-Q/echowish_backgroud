package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.MyPublishPost;
import com.echowish.android_backgroud.pojo.PartPost;
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

    @GetMapping("delete")
    @ResponseBody
    public  String deletePost(@RequestParam(value = "postId")Integer postId)
    {
        return postService.deletePost(postId);
    }


    @GetMapping("query")
    @ResponseBody
    public Post queryPost(@RequestParam(value = "postId")Integer postId)
    {
        return postService.queryPost(postId);
    }

    @GetMapping("queryPartPost")
    @ResponseBody
    public List<PartPost> queryPartPost(@RequestParam(value = "start") Integer start,
                                        @RequestParam(value = "end")Integer end)
    {
        return postService.queryPartPost(start,end);
    }

    @GetMapping("queryPartPostByZone")
    @ResponseBody
    public List<PartPost> queryPartPostByZone(
            @RequestParam(value = "start") Integer start,
            @RequestParam(value = "end") Integer end,
        @RequestParam(value = "zone" )String zone)
    {
        return postService.queryPartPostByZone(start,end,zone);
    }

    @GetMapping("queryPartPostByKeyWord")
    @ResponseBody
    public List<PartPost>queryPartPostByKeyWord(@RequestParam(value = "keyword")String keyword)
    {
        return postService.queryPartPostByKeyWord(keyword);
    }

    @GetMapping("queryPartPostByZoneAndKeyWord")
    @ResponseBody
    public List<PartPost> queryPartPostByZoneAndKeyWord(@RequestParam(value = "zone")String zone,
                                                        @RequestParam(value = "keyword") String keyword)
    {
        return  postService.queryPartPostByZoneAndKeyWord(zone,keyword);
    }

    @GetMapping("queryMyPublishPostByUserId")
    @ResponseBody
    public List<MyPublishPost> queryMyPublishPostByUserId(@RequestParam(value = "userId")Integer userId)
    {
        return postService.queryMyPublishPostByUserId(userId);
    }

    @PostMapping("download")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadImage(@RequestParam(value = "postId",required = true) Integer postId)
    {
        return postService.downloadImage(postId);
    }
}

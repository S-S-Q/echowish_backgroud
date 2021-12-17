package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.post.Collection;
import com.echowish.android_backgroud.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("collection")
public class CollectionServiceController {

    @Autowired
    CollectionService collectionService;

    @GetMapping("collect")
    @ResponseBody
    String collectPost(@RequestParam("postId")Integer postId,
                       @RequestParam("userId")Integer userId)
    {
        String collectionId=String.valueOf(postId)+String.valueOf(userId);
        return collectionService.collectPost(new Collection(Integer.valueOf(collectionId),postId,userId));
    }

    @GetMapping("cancel")
    @ResponseBody
    String cancelCollection(@RequestParam("postId")Integer postId,
                            @RequestParam("userId")Integer userId)
    {
        String collectionId=String.valueOf(postId)+String.valueOf(userId);
        return collectionService.cancelCollection(new Collection(Integer.valueOf(collectionId),postId,userId));
    }

    @GetMapping("queryIsCollect")
    @ResponseBody
    Boolean queryIsCollect(@RequestParam("postId")Integer postId,
                           @RequestParam("userId")Integer userId)
    {
        String collectionId=String.valueOf(postId)+String.valueOf(userId);
        return collectionService.queryIsCollect(new Collection(Integer.valueOf(collectionId),postId,userId));
    }
}

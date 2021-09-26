package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.DetailPost;
import com.echowish.android_backgroud.pojo.MyPublishPost;
import com.echowish.android_backgroud.pojo.PartPost;
import com.echowish.android_backgroud.pojo.Post;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    String publishPost(MultipartFile file,Post post);

    String deletePost(int postId);

    //加载图片 不是接口
    boolean loadImage(MultipartFile file,String filename);

    //删除图片
    void deleteImage(String filename);

    Post queryPost(Integer postId);

    DetailPost queryDetailPost(Integer postId);

    List<PartPost> queryPartPost(int start, int end);

    List<PartPost> queryPartPostByZone(int start,int end,String zone);

    List<PartPost> queryPartPostByKeyWord(String keyword);

    List<PartPost> queryPartPostByZoneAndKeyWord(String zone,String keyword);

    List<MyPublishPost> queryMyPublishPostByUserId(Integer userId);

    ResponseEntity<FileSystemResource> downloadImage(String filename);

    ResponseEntity<FileSystemResource> downloadImage(Integer postId);
}

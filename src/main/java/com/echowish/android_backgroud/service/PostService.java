package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.Post;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    String publishPost(MultipartFile file,Post post);

    //加载图片 不是接口
    boolean loadImage(MultipartFile file,String filename);

    //删除图片
    void deleteImage(String filename);

    List<Post> queryPost(int start,int end);


    ResponseEntity<FileSystemResource> downloadImage(String filename);

    ResponseEntity<FileSystemResource> downloadImage(Integer postId);
}

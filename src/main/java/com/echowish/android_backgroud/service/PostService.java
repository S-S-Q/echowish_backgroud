package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.Post;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface PostService {
    String publishPost(Post post);

    //测试用
    String loadImage(MultipartFile file,String filename);

    String loadImage(MultipartFile file,String filename,Integer postId);

    ResponseEntity<FileSystemResource> downloadImage(String filename);

    ResponseEntity<FileSystemResource> downloadImage(Integer postId);
}

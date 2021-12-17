package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.post.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    String publishPost(MultipartFile file,Post post);

    String deletePost(int postId);

    //加载图片 不是接口
    boolean loadImage(MultipartFile file,String filename);

    //删除图片
    void deleteImage(String filename);

    //
    String deletePostByUserId(int userId);

    Post queryPost(Integer postId);

    List<Post> queryAllPost();

    String queryPostTitleByPostId(Integer postId);

    List<Integer> queryMyPostIdByUserId(Integer userId);

    DetailPost queryDetailPost(Integer postId);

    List<PartPost> queryPartPost(int start, int end);

    List<PartPost> queryPartPostByZone(int start,int end,String zone);

    List<PartPost> queryPartPostByKeyWord(String keyword);

    List<PartPost> queryPartPostByZoneAndKeyWord(String zone,String keyword);

    List<MyPublishPost> queryMyPublishPostByUserId(Integer userId);

    List<HotSearch> getHotSearch(Integer Num);
}

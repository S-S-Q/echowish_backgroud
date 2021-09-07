package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface PostMapper {
    //向数据库中插入新的帖子
 public void insertPost(Post post);

 //通过帖子id查找图片路径
    public String queryImageByPostId(Integer postId);

   //通过帖子id删除帖子
    public void deleteByPostId(Integer postId);

    //获取帖子所有信息
    public List<Post> queryAllPost();
}

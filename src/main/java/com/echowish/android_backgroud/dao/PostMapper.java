package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PostMapper {
    //向数据库中插入新的帖子
 public void insertPost(Post post);

 //通过帖子id查找图片路径
    public String queryImageByPostId(Integer postId);

    //更新帖子图片路径(@param注解 代替parameterType 添加多个元素)
    public void  updateImageByPostId(@Param("postId")Integer postId,
                                       @Param("postImage")String postImage);
}

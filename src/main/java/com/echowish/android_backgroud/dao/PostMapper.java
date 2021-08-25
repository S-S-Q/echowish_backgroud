package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Post;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PostMapper {
    //向数据库中插入新的帖子
 public void insertPost(Post post);
}

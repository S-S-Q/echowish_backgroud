package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.pojo.Post;
import com.echowish.android_backgroud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    //添加新的帖子 并且判断帖子是否符合条件
    @Override
    public String PublishPost(Post post) {
        try{
            postMapper.insertPost(post);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e) {
           return ReactInfo.FAIL_INFO;
        }
    }
}

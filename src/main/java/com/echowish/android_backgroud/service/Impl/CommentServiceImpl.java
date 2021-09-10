package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public String publishNewComment(Comment comment) {
        try {
            commentMapper.insertNewComment(comment);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            System.out.println(ReactInfo.FAIL_INFO);
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String deleteMyComment(Comment comment) {
        try{
            commentMapper.deleteComment(comment);
            return ReactInfo.SUCCESS_INFO;

        }catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }
}

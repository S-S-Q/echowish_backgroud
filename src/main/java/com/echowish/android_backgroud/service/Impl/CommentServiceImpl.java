package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.*;
import com.echowish.android_backgroud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;


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

    //多表查询优于单表多次查询 建议有空优化
    @Override
    public List<CommentAndUserInfo> queryAllCommentByPostId(Integer postId) {
        List<CommentAndUserInfo> commentAndUserInfoList=null;
        try
        {
            commentAndUserInfoList=commentMapper.queryAllCommentAndUserInfoByPostId(postId);
            return commentAndUserInfoList;
        }
        catch (Exception e)
        {
            return commentAndUserInfoList;
        }
    }

    @Override
    public List<MyComment> queryAllMyCommentBuUserId(Integer userId) {
        List<MyComment> myCommentList=new LinkedList<>();
        try
        {
           List<Comment>commentList= commentMapper.queryAllCommentsByUserId(userId);
           for(Comment comment:commentList)
           {
               String postTitle =postMapper.queryPostTitleByPostId(comment.getPostId());
               MyComment myComment=new MyComment(comment,postTitle);
               myCommentList.add(myComment);
           }
           return myCommentList;
        }
        catch (Exception e)
        {
            return myCommentList;
        }
    }

    @Override
    public List<OthersComment> queryAllCommentInMyPosts(Integer userId) {
        try {
            List<Integer> myPostIdList=postMapper.queryMyPostIdByUserId(userId);
            List<OthersComment> othersCommentList=new LinkedList<>();
            for(Integer postId:myPostIdList)
            {
                othersCommentList.addAll(commentMapper.queryAllOthersCommentByPostId(postId));
            }
            return othersCommentList;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

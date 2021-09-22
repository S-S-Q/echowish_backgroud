package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.dao.PostMapper;
import com.echowish.android_backgroud.dao.UserMapper;
import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.pojo.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.MyComment;
import com.echowish.android_backgroud.pojo.User;
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
        List<CommentAndUserInfo> commentAndUserInfoList=new LinkedList<>();
        try
        {
         List<Comment> commentList=commentMapper.queryAllCommentsByPostId(postId);
         for(Comment comment:commentList)
         {
             User user=userMapper.queryUserByUserId(comment.getUserId());
             CommentAndUserInfo commentAndUserInfo=new CommentAndUserInfo(comment,user.name,user.headImage);
             commentAndUserInfoList.add(commentAndUserInfo);
         }
            return commentAndUserInfoList;
        }
        catch (Exception e)
        {
            System.out.println(commentAndUserInfoList);
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
}

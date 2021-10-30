package com.echowish.android_backgroud.service.Impl;

import com.echowish.android_backgroud.constant.ReactInfo;
import com.echowish.android_backgroud.dao.CommentMapper;
import com.echowish.android_backgroud.pojo.comment.Comment;
import com.echowish.android_backgroud.pojo.comment.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.comment.MyComment;
import com.echowish.android_backgroud.pojo.comment.OthersComment;
import com.echowish.android_backgroud.service.CommentService;
import com.echowish.android_backgroud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    PostService postService;

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
            SimpleDateFormat filenameDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fileTime=filenameDateFormat.format(comment.getTime());
            commentMapper.deleteCommentByDate(fileTime);
            return ReactInfo.SUCCESS_INFO;

        }catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String deleteCommentByUserId(Integer userId) {
        try {
            commentMapper.deleteByUserId(userId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
        {
            return ReactInfo.FAIL_INFO;
        }
    }

    @Override
    public String deleteAllCommentByPostId(Integer postId) {
        try {
            commentMapper.deleteCommentByPostId(postId);
            return ReactInfo.SUCCESS_INFO;
        }
        catch (Exception e)
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
               String postTitle =postService.queryPostTitleByPostId(comment.postId);
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
            List<Integer> myPostIdList=postService.queryMyPostIdByUserId(userId);
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

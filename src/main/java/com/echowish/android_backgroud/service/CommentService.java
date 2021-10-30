package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.comment.Comment;
import com.echowish.android_backgroud.pojo.comment.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.comment.MyComment;
import com.echowish.android_backgroud.pojo.comment.OthersComment;

import java.util.List;

public interface CommentService {

    public String publishNewComment(Comment comment);

    public String deleteMyComment(Comment comment);

    public String deleteCommentByUserId(Integer userId);

    String deleteAllCommentByPostId(Integer postId);

    public List<CommentAndUserInfo> queryAllCommentByPostId(Integer postId);

    public List<MyComment> queryAllMyCommentBuUserId(Integer userId);


    //获取自己帖子中别人回复的信息
    public List<OthersComment> queryAllCommentInMyPosts(Integer userId);
}

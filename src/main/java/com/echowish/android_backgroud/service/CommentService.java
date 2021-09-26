package com.echowish.android_backgroud.service;

import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.pojo.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.MyComment;
import com.echowish.android_backgroud.pojo.OthersComment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    public String publishNewComment(Comment comment);

    public String deleteMyComment(Comment comment);

    public List<CommentAndUserInfo> queryAllCommentByPostId(Integer postId);

    public List<MyComment> queryAllMyCommentBuUserId(Integer userId);

    //获取自己帖子中别人回复的信息
    public List<OthersComment> queryAllCommentInMyPosts(Integer userId);
}

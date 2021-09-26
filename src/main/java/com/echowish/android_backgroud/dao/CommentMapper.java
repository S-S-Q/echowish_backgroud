package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Comment;
import com.echowish.android_backgroud.pojo.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.OthersComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    void insertNewComment(Comment comment);

    void deleteComment(Comment comment);

    void deleteCommentByPostId(Integer postId);

    List<Comment> queryAllCommentsByPostId(Integer postId);

    List<CommentAndUserInfo> queryAllCommentAndUserInfoByPostId(Integer postId);

    List<Comment> queryAllCommentsByUserId(Integer userId);

    List<OthersComment> queryAllOthersCommentByPostId(Integer postId);

}

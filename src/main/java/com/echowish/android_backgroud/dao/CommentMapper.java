package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.comment.Comment;
import com.echowish.android_backgroud.pojo.comment.CommentAndUserInfo;
import com.echowish.android_backgroud.pojo.comment.OthersComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    void insertNewComment(Comment comment);

    void deleteComment(Comment comment);

    void deleteByUserId(Integer userId);

    void deleteCommentByPostId(Integer postId);

    void deleteCommentByDate(String time);

    List<Comment> queryAllCommentsByPostId(Integer postId);

    List<CommentAndUserInfo> queryAllCommentAndUserInfoByPostId(Integer postId);

    List<Comment> queryAllCommentsByUserId(Integer userId);

    List<OthersComment> queryAllOthersCommentByPostId(Integer postId);

}

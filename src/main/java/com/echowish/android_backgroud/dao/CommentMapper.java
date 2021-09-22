package com.echowish.android_backgroud.dao;

import com.echowish.android_backgroud.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    void insertNewComment(Comment comment);

    void deleteComment(Comment comment);

    void deleteCommentByPostId(Integer postId);

    List<Comment> queryAllCommentsByPostId(Integer postId);

    List<Comment> queryAllCommentsByUserId(Integer userId);

}
